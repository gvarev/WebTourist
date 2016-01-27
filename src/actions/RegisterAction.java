package actions;

import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;

import services.PasswordEncryptionService;

import com.opensymphony.xwork2.ActionSupport;

import dao.DAO;
import dto.Numbers;
import dto.User;

public class RegisterAction extends ActionSupport implements
		ServletRequestAware {
	private HttpServletRequest servletRequest;
	private String sessionNumber;
	private String userName;
	private String password;
	private String retypedPassword;
	private String email;
	private String number;
	private User user;
	private String generatedNumber;
	private Random rd = new Random();

	public void setRetypedPassword(String retypedPassword) {
		this.retypedPassword = retypedPassword;
	}

	public String getGeneratedNumber() {
		return generatedNumber;
	}

	@Override
	public void setServletRequest(HttpServletRequest servletRequest) {
		this.servletRequest = servletRequest;

	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String execute() {
		
		String encryptedPassword = null;
		encryptedPassword = PasswordEncryptionService.encode(password);
		
		DAO.addUser(getUserName(), encryptedPassword, getEmail());
		HttpSession numberSession = servletRequest.getSession();
		numberSession.removeAttribute("number");

		DAO.addUserDetails("", "default.jpg", userName);

		return SUCCESS;
	}

	public void validate() {
		if (getUserName().length() == 0) {
			addFieldError("userName", getText("name.required"));
		}
		if (getPassword().length() == 0) {
			addFieldError("password", getText("password.required"));
		}
		if (getNumber().length() == 0) {
			addFieldError("userName", getText("number.required"));
		}
		if (getEmail().length() == 0) {
			addFieldError("email", getText("email.required"));
		}
		
		if (DAO.registrationExists(userName)) {
			addFieldError("password", getText("name.exists"));
		}
		HttpSession numberSession = servletRequest.getSession();
		sessionNumber = (String) numberSession.getAttribute("number");

		if (sessionNumber.length() == 0) {
			addFieldError("password", getText("number.wrong"));
		}

		if (!sessionNumber.equals(getNumber())) {
			addFieldError("password", getText("number.wrong"));
		}

		if (!password.equals(retypedPassword)) {
			addFieldError("retypedPassword", getText("password.retypedWrong"));
		}

		if (!isValideEmail(email)) {
			addFieldError("email", getText("email.notValid"));
		}

		if (userName.length() >= 20) {
			addFieldError("userName", getText("userName.tooLong"));
		}

		if (password.length() >= 20) {
			addFieldError("password", getText("password.tooLong"));
		}

		generateNumber();
	}

	private void generateNumber() {
		int randomNumber = 0;
		randomNumber = rd.nextInt(4);
		if (randomNumber == 0) {
			randomNumber++;
		}

		List<Numbers> numberList = DAO.getNumberList();
		for (Numbers number : numberList) {
			if (number.getId() == randomNumber) {
				generatedNumber = Integer.toString(number.getNumber());
				break;
			}
		}

		HttpSession session = servletRequest.getSession();
		session.setAttribute("number", generatedNumber);
	}

	private boolean isValideEmail(String email) {
		char ch = ' ';
		boolean isValid = false;

		for (int i = 0; i < email.length(); i++) {
			ch = email.charAt(i);
			if (ch == '@') {
				isValid = true;
				break;
			}
		}

		return isValid;
	}
}
