package bookstore.dao.hibernate;

import java.util.Calendar;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;

import bookstore.dao.OrderDAO;
import bookstore.pbean.TCustomer;
import bookstore.pbean.TOrder;

@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class OrderDAOImpl extends HibernateDaoSupport
										implements OrderDAO{

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public TOrder createOrder(TCustomer inCustomer){

		TOrder saveOrder = new TOrder();
		saveOrder.setTCustomer(inCustomer);
		saveOrder.setOrderday(Calendar.getInstance().getTime());
		
		getHibernateTemplate().save(saveOrder);
		
		return(saveOrder);
	}
}