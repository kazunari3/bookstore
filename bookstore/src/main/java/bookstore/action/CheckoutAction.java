package bookstore.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.ServletRequestAware;

import bookstore.logic.BookLogic;
import bookstore.vbean.VBook;
import bookstore.vbean.VCheckout;

@Results({
	@Result(name="ToCheck"	,			location="check.vm", type="velocity"),
	@Result(name="ZeroItemSelected",    location="bookstore.vm", type="velocity"),
	@Result(name="SessionErrorOccured", location="/index.jsp")
})
public class CheckoutAction implements ServletRequestAware {
	
	BookLogic bookLogic;

	private HttpServletRequest request;
	private List<VBook> productListView;
	
	private VCheckout itemsToBuy;
	private String errorMessage;

	@Action("/Checkout")
    public String checkout() {

		HttpSession httpSession = request.getSession(false);
		if (httpSession == null) {
			return("SessionErrorOccured");
		}
		
		List<String> selecteditems = (List<String>)httpSession.getAttribute("Cart");

		if (selecteditems == null || selecteditems.size() == 0) {
			// 商品が選択されていなかった。
			setErrorMessage("商品が選択されていません。");

			List<String> productListAll = bookLogic.getAllBookISBNs();
			productListView = bookLogic.createVBookList(
											 productListAll, null);
			
			httpSession.setAttribute("ProductList", productListAll);
			httpSession.setAttribute("ProductListView", productListView);
			
			return("ZeroItemSelected");
		}
		
		itemsToBuy = bookLogic.createVCheckout(selecteditems);
		
		return ("ToCheck");
	}

	public void setBookLogic(BookLogic bookLogic) {
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
	
	public VCheckout getItemsToBuy() {
		return itemsToBuy;
	}
	public void setItemsToBuy(VCheckout itemsToBuy) {
		this.itemsToBuy = itemsToBuy;
	}

	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}