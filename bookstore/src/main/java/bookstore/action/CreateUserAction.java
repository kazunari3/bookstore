package bookstore.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import bookstore.logic.CustomerLogic;

@Results({
	@Result(name="UserCreated",  location="/index.jsp"),
	@Result(name="ErrorOccured", location="/createAccount.jsp"),
})
public class CreateUserAction {
	
	CustomerLogic customerLogic;
	
	private String name;
	private String email;
	private String account;
	private String passwd;
	private String passwd2;
	private String errorMessage;
	
	@Action("/CreateAccount")
    public String createAccount() {

		String in_passwd = getPasswd();
		if (in_passwd != null && (in_passwd.equals(getPasswd2()) == false)) {
			// passwd��passwd2���}�b�`���Ă��Ȃ�����
			setErrorMessage("�m�F�p�p�X���[�h����v���Ă��܂���");

			return ("ErrorOccured");
		}

		String in_account = getAccount();
		if (in_account != null && (customerLogic.isAlreadyExsited(in_account))) {
			// �A�J�E���g�����łɑ��݂��Ă���
			setErrorMessage("�A�J�E���g�����݂��Ă��܂�");

			return ("ErrorOccured");
		}
	
		if (!customerLogic.createCustomer(getAccount(),
										  getPasswd(), 
										  getName(), 
										  getEmail())) {
			// ���[�U���쐬�ł��Ȃ������B
			setErrorMessage("���[�U�쐬�ł��܂���ł���");

			return ("ErrorOccured");
		}
		setErrorMessage(null);
		
		return ("UserCreated");
	}
	
	public void setCustomerLogic(CustomerLogic inCustomerLogic) {
		this.customerLogic = inCustomerLogic;
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

	public String getPasswd2() {
		return passwd2;
	}
	public void setPasswd2(String passwd2) {
		this.passwd2 = passwd2;
	}

	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}