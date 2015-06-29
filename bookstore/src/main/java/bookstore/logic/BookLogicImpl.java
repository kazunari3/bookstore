package bookstore.logic;

import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;

import bookstore.dao.BookDAO;
import bookstore.pbean.TBook;
import bookstore.vbean.VBook;
import bookstore.vbean.VCheckout;


@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class BookLogicImpl implements BookLogic{

	BookDAO bookdao;

	public List<String> getAllBookISBNs() {
		List<String> isbns = new ArrayList<String>();
		
		for (TBook bookIter :  bookdao.retrieveBooksByISBNs(null)) {
			isbns.add( bookIter.getIsbn() );
		}
		
		return( isbns );
	}
	

	public List<String> retrieveBookISBNsByKeyword(String inKeyword) {
		List<String> isbns = new ArrayList<String>();
		
		for (TBook bookIter :  bookdao.retrieveBooksByKeyword(inKeyword)) {
			isbns.add(bookIter.getIsbn());
		}
		
		return(isbns);
	}


	public List<VBook> createVBookList(List<String> inProductList, List<String> inSelectedList) {
		List<VBook> vArrayList = new ArrayList<VBook>();
		
		for(TBook bookIter :  bookdao.retrieveBooksByISBNs(inProductList)) {
			VBook currentVBook = new VBook(bookIter);
			currentVBook.setSelected(false);

			if (inSelectedList != null && inSelectedList.size() != 0){
				if (inSelectedList.contains(bookIter.getIsbn())) {
					currentVBook.setSelected(true);
				}
			}
			
			vArrayList.add(currentVBook);
		}

		return (vArrayList);
	}
	
	
	public VCheckout createVCheckout(List<String> inSelectedList) {
		VCheckout vc = new VCheckout();
		vc.setTotal(bookdao.getPriceByISBNs(inSelectedList));

		List<VBook> viewList = new ArrayList<VBook>();
		
		for (TBook iterBook : bookdao.retrieveBooksByISBNs(inSelectedList)) {
			VBook vb = new VBook(iterBook);
			vb.setSelected(true);
			
			viewList.add( vb );			
		}
		
		
		vc.setSelecteditems(viewList);
		
		return (vc);
	}

	
	public List<String> createCart(List<String> inProductList, 
							 		List<String> inSelectedList,
							 		List<String> inCart){

		inCart.removeAll(inProductList);
		if (inSelectedList != null &&
			inSelectedList.size() != 0) {
			inCart.addAll(inSelectedList);
		}
			
		return (inCart);
	}


	public void setBookdao (BookDAO bookdao){
		this.bookdao = bookdao;
	}
}