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

@Results({
	@Result(name="SearchSuccess",       location="bookstore.vm", type="velocity"),
	@Result(name="SessionErrorOccured", location="/index.jsp"),
})
public class SearchAction implements ServletRequestAware {

	BookLogic bookLogic;

	private HttpServletRequest request;
	private List<VBook> productListView;

	private String	keyword;
	private String errorMessage;
	
	@Action("/Search")
	public	String	search() {
		
		HttpSession httpSession = request.getSession(false);
		if (httpSession == null) {
			return ("SessionErrorOccured");
		}
		
		List<String> cart = (List<String>) httpSession.getAttribute("Cart");
			
		List<String> foundBooks = bookLogic.retrieveBookISBNsByKeyword(getKeyword());
		
		if (foundBooks == null || foundBooks.size() == 0) {
			// 見つからなかった場合は、全書籍を表示し、エラーを出力する。
			// ロジックは、メッセージをセットするだけでほかは正常処理と同じ。
			foundBooks = bookLogic.getAllBookISBNs();
			
			setErrorMessage("見つかりませんでした");
		}
		
		productListView = bookLogic.createVBookList(
											foundBooks, cart);

		httpSession.setAttribute("ProductList", foundBooks);
		httpSession.setAttribute("ProductListView", productListView);		
				
		return("SearchSuccess");
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

	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}