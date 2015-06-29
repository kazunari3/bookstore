package bookstore.dao.hibernate;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;

import bookstore.dao.OrderDetailDAO;
import bookstore.pbean.TBook;
import bookstore.pbean.TOrder;
import bookstore.pbean.TOrderDetail;

@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class OrderDetailDAOImpl extends HibernateDaoSupport
										implements OrderDetailDAO{

	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void createOrderDetail(TOrder inOrder, TBook inBook) {
	
		TOrderDetail saveOrderDetail = new TOrderDetail();
		
		saveOrderDetail.setTOrder(inOrder);
		saveOrderDetail.setTBook(inBook);
		
		getHibernateTemplate().save(saveOrderDetail);
	}
}