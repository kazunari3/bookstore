package bookstore.dao.hibernate;

import java.util.List;
import java.util.regex.Pattern;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;

import bookstore.dao.BookDAO;
import bookstore.pbean.TBook;

@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class BookDAOImpl extends HibernateDaoSupport
											implements BookDAO{
	public int getPriceByISBNs(final List<String> inISBNList) {

		HibernateTemplate ht = getHibernateTemplate();
		
		return(((Long)ht.execute(new HibernateCallback() {

			public Object doInHibernate(Session session)
								throws HibernateException {
				
				Query priceQuery = session
					.createQuery("select sum(book.price) from TBook book where book.isbn in (:SELECTED_ITEMS)");
				priceQuery.setParameterList("SELECTED_ITEMS", inISBNList);

				return((Long)priceQuery.uniqueResult());
			}
		} )).intValue());
	}


	public List<TBook> retrieveBooksByKeyword(String inKeyword) {
		String escapedKeyword = Pattern.compile("([%_])")
											.matcher(inKeyword)
											.replaceAll("\\\\$1");
		String[] keywords = {"%" + escapedKeyword + "%",
							 "%" + escapedKeyword + "%",
							 "%" + escapedKeyword + "%"};
		
		List<TBook> booksList = getHibernateTemplate().find( 
					"from TBook book where book.author like ?" +
					"or book.title like ? or book.publisher like ?" ,
					keywords);
		
		return(booksList);
	}


	public List<TBook> retrieveBooksByISBNs(final List<String> inISBNList){

		HibernateTemplate ht = getHibernateTemplate();
		
		if(inISBNList == null){
			return(ht.find("from TBook book"));
		}else{
		
			return(((List<TBook>)ht.execute(new HibernateCallback() {

				public Object doInHibernate(Session session)
									throws HibernateException {
				
					Query retrieveQuery = session
						.createQuery("from TBook book where book.isbn in ( :ISBNS )");
					retrieveQuery.setParameterList("ISBNS", inISBNList);

					return(retrieveQuery.list());
				}
			} )));
		}
	}
}