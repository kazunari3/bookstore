package bookstore.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.ServletRequestAware;

import bookstore.logic.BookLogic;
import bookstore.vbean.VBook;

@Results({
	@Result(name="Continue",			location="bookstore.vm", type="velocity"),
	@Result(name="SessionErrorOccured", location="/index.jsp")
})
public class AddToCartAction implements ServletRequestAware {
	
	BookLogic bookLogic;
	
	private HttpServletRequest request;
	private List<VBook> productListView;

	private String[] selecteditems;
	
	@Action("/AddToCart")
    public String addToCart() {
		
		HttpSession httpSession = request.getSession(false);
		if (httpSession == null) {
			return("SessionErrorOccured");
		}
		
		List<String> cart = (List<String>) httpSession.getAttribute("Cart");
		if (cart == null) {
			cart = new ArrayList<String>();
		}
		
		List<String> productList = (List<String>)httpSession.getAttribute("ProductList");
		
		List<String> selectedItemsList = null;

		if (selecteditems != null &&
				selecteditems.length != 0) {
			selectedItemsList = Arrays.asList(getSelecteditems());
		}
		
		List<String> newCart = bookLogic.createCart(productList,
													selectedItemsList,
													cart);
		
		httpSession.setAttribute("Cart", newCart);

		List<String> productListAll = bookLogic.getAllBookISBNs();
		productListView = bookLogic.createVBookList(
										productListAll, newCart);
		
		httpSession.setAttribute("ProductList", productListAll);
		httpSession.setAttribute("ProductListView", productListView);
		
		return ("Continue");
	}
	
	public void setBookLogic( BookLogic bookLogic ){
		this.bookLogic = bookLogic;
	}

	public void setServletRequest(HttpServletRequest inRequest) {
		this.request = inRequest;
	}

	public List<VBook> getProductListView() {
		return productListView;
	}
	public void setProductListView(List<VBook> productListView) {
		this.productListView = productListView;
	}
	
	public String[] getSelecteditems() {
		return selecteditems;
	}
	public void setSelecteditems(String[] selecteditems) {
		this.selecteditems = selecteditems;
	}
}