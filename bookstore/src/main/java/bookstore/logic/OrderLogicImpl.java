package bookstore.logic;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;

import bookstore.dao.BookDAO;
import bookstore.dao.CustomerDAO;
import bookstore.dao.OrderDAO;
import bookstore.dao.OrderDetailDAO;
import bookstore.pbean.TBook;
import bookstore.pbean.TCustomer;
import bookstore.pbean.TOrder;


@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class OrderLogicImpl implements OrderLogic {

	BookDAO bookdao;
	CustomerDAO customerdao;
	OrderDAO orderdao;
	OrderDetailDAO odetaildao;
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void orderBooks(String inUid, List<String> inISBNs){
		TCustomer customer = customerdao.findCustomerByUid(inUid);
		TOrder order = orderdao.createOrder(customer);

		for (TBook iterBook : bookdao.retrieveBooksByISBNs(inISBNs)) {
			odetaildao.createOrderDetail(order, iterBook);
		}		
	}

	
	public void setBookdao(BookDAO bookdao) {
		this.bookdao = bookdao;
	}

	public void setCustomerdao(CustomerDAO customerdao) {
		this.customerdao = customerdao;
	}

	public void setOrderdao(OrderDAO orderdao) {
		this.orderdao = orderdao;
	}

	public void setOrderdetaildao(OrderDetailDAO odetaildao) {
		this.odetaildao = odetaildao;
	}
}