package bookstore.dao;

import bookstore.pbean.TCustomer;
import bookstore.pbean.TOrder;

public interface OrderDAO{
	public TOrder createOrder(TCustomer inCustomer);
}