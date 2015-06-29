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
import bookstore.vbean.VBook;

@Results({
	@Result(name="LoginSuccess", location="bookstore.vm", type="velocity"),
	@Result(name="LoginFault",   location="/index.jsp")
})
public class LoginAction implements ServletRequestAware {
	
	CustomerLogic customerLogic;
	BookLogic bookLogic;
	
	private HttpServletRequest request;
	private List<VBook> productListView;

	private String account;
	private String passwd;
	private String errorMessage;


	@Action("/Login")
    public String login() {
		// アカウントの対処
		if (!customerLogic.isPasswordMatched(getAccount(), getPasswd())) {
			// アカウントが合致しなかった
			setErrorMessage("ログイン名とパスワードが違います");
			return ("LoginFault");
		}
		
		setErrorMessage(null);
		
		// getSession()
		HttpSession httpSession = request.getSession(false);
		if (httpSession != null) {
			httpSession.invalidate();
		}

		httpSession = request.getSession();
		
		httpSession.setAttribute("Login", getAccount());
		
		List<String> productListAll = bookLogic.getAllBookISBNs();
		productListView = bookLogic.createVBookList(
										 productListAll, null);
		
		httpSession.setAttribute("ProductList", productListAll);
		httpSession.setAttribute("ProductListView", productListView);
		
		return ("LoginSuccess");
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
	
	public List<VBook> getProductListView() {
		return productListView;
	}
	public void setProductListView(List<VBook> productListView) {
		this.productListView = productListView;
	}

	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}