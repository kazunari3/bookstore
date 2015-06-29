package bookstore.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.ServletRequestAware;

import bookstore.logic.BookLogic;
import bookstore.logic.CustomerLogic;
import bookstore.logic.OrderLogic;
import bookstore.vbean.VCheckout;
import bookstore.vbean.VCustomer;

@Results({
	@Result(name="OrderSuccess"	,		location="order.vm", type="velocity"),
	@Result(name="SessionErrorOccured", location="/index.jsp")
})
public class OrderAction implements ServletRequestAware {
	
	OrderLogic orderLogic;
	CustomerLogic customerLogic;
	BookLogic	bookLogic;
	
	private HttpServletRequest request;
	
	private VCheckout itemsToBuy;
	private VCustomer customer;

	@Action("/Order")
	public	String	order() {
		HttpSession httpSession = request.getSession(false);
		if (httpSession == null) {
			return ("SessionErrorOccured");
		}
		
		String uid = (String)httpSession.getAttribute("Login");
		List<String> cart = (List<String>)httpSession.getAttribute("Cart");
		
		// オーダ情報をDBに保存する。
		orderLogic.orderBooks(uid, cart);
		
		itemsToBuy = bookLogic.createVCheckout(
				(List<String>)httpSession.getAttribute("Cart"));
		
		
		// ユーザ情報作成
		customer = customerLogic.createVCustomer(uid);
		
		return ("OrderSuccess");
	}

	public void setOrderLogic(OrderLogic orderLogic) {
		this.orderLogic = orderLogic;
	}

	public void setCustomerLogic(CustomerLogic customerLogic) {
		this.customerLogic = customerLogic;
	}

	public void setBookLogic(BookLogic bookLogic) {
		this.bookLogic = bookLogic;
	}

	public void setServletRequest(HttpServletRequest inRequest) {
		this.request = inRequest;
	}
	
	public VCheckout getItemsToBuy() {
		return itemsToBuy;
	}
	public void setItemsToBuy(VCheckout itemsToBuy) {
		this.itemsToBuy = itemsToBuy;
	}

	public VCustomer getCustomer() {
		return customer;
	}
	public void setCustomer(VCustomer customer) {
		this.customer = customer;
	}

}