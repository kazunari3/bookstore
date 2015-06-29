package bookstore.vbean;

import java.io.Serializable;

import bookstore.pbean.TCustomer;

public class VCustomer implements Serializable {

	private String uid;
	private String name;
	private String email;
	
	public VCustomer(){}
	
	public VCustomer(TCustomer customer){
		uid = customer.getUid();
		name = customer.getName();
		email = customer.getEmail();
	}
	
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}