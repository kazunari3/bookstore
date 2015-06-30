package bookstore.dao.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;

import bookstore.dao.CustomerDAO;
import bookstore.pbean.TCustomer;

@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class CustomerDAOImpl extends HibernateDaoSupport implements CustomerDAO{
	
	public	int	getCustomerNumberByUid(final String inUid){
		
		HibernateTemplate ht = getHibernateTemplate();
		
		return(((Long)ht.execute(new HibernateCallback() {

			public Object doInHibernate(Session session)
								throws HibernateException {
				
				Query numQuery = session
					.createQuery("select count(*) from TCustomer customer where customer.uid like :UID" );
						numQuery.setString("UID", inUid);

				return((Long) numQuery.uniqueResult());
			}
		} )).intValue());
	}
	
	
	public TCustomer findCustomerByUid(final String inUid) {

		HibernateTemplate ht = getHibernateTemplate();
		
		return(((TCustomer)ht.execute(new HibernateCallback() {

			public Object doInHibernate(Session session)
								throws HibernateException {
				
				Query priceQuery = session
					.createQuery("from TCustomer customer where customer.uid like :UID");
				priceQuery.setString("UID", inUid);

				return((TCustomer)priceQuery.uniqueResult());
			}
		} )));
	}

	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveCustomer(String inUid,
							   String inPasswordMD5,
							   String inName,
							   String inEmail){
		TCustomer saveCustomer = new TCustomer();
		
		saveCustomer.setUid(inUid);
		saveCustomer.setPasswordmd5(inPasswordMD5);
		//saveCustomer.setPasswordmd5("ss");
		saveCustomer.setName(inName);
		saveCustomer.setEmail(inEmail);
		
		getHibernateTemplate().save(saveCustomer);
	}
}