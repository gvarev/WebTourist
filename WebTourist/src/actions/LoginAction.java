package actions;

import java.util.SortedSet;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;

import dao.DAO;

import services.*;

public class LoginAction extends ActionSupport implements ServletRequestAware {
	private HttpServletRequest servletRequest;
	private String userName;
	private String password;
	private SortedSet<String> titleList = new TreeSet<String>();
	String messageImage;

	public String getMessageImage() {
		return messageImage;
	}

	public SortedSet<String> getTitleList() {
		return titleList;
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

	@Override
	public void setServletRequest(HttpServletRequest servletRequest) {
		this.servletRequest = servletRequest;

	}

	@Override
	public String execute() {
		HttpSession session = servletRequest.getSession();
		session.setAttribute("loged", getUserName());
		titleList = DAO.getPortfolioTitles();

		boolean isNewMessage = DAO.hasNewMessage(userName);
		if (isNewMessage) {
			messageImage = "new_message.jpg";
		} else {
			messageImage = "no_new_messages.jpg";
		}

		return SUCCESS;
	}

	public void validate() {
		if (getUserName().length() == 0) {
			addFieldError("userName", getText("name.required"));
		}

		if (getPassword().length() == 0) {
			addFieldError("password", getText("password.required"));
		}
		
		String encryptedPassword = PasswordEncryptionService.encode(password);
		
		if (!DAO.userExists(getUserName(), encryptedPassword)) {
			addFieldError("password", getText("name.wrong"));
		}

		if (userName.length() >= 20) {
			addFieldError("userName", getText("userName.tooLong"));
		}

		if (password.length() >= 20) {
			addFieldError("password", getText("password.tooLong"));
		}
	}
}
