package actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;

import services.PasswordEncryptionService;

import com.opensymphony.xwork2.ActionSupport;

import dao.DAO;
import dto.UserDetails;

public class ChangePasswordAction extends ActionSupport implements
		ServletRequestAware {
	private HttpServletRequest servletRequest;
	private String changedPassword;
	private String avatarName;
	private String aboutMe;

	public String getAvatarName() {
		return avatarName;
	}

	public String getAboutMe() {
		return aboutMe;
	}

	@Override
	public void setServletRequest(HttpServletRequest servletRequest) {
		this.servletRequest = servletRequest;
	}

	public void setChangedPassword(String changedPassword) {
		this.changedPassword = changedPassword;
	}

	@Override
	public String execute() {
		HttpSession session = servletRequest.getSession();
		String userName = (String) session.getAttribute("loged");
		
		String encryptedPassword = null;
		try {
			encryptedPassword = PasswordEncryptionService.encode(changedPassword);
		} catch (Exception e) {
			e.printStackTrace();
		}

		DAO.updatePassword(userName, encryptedPassword);

		return SUCCESS;
	}

	@Override
	public void validate() {
		if (changedPassword.length() == 0) {
			addFieldError("changedPassword", getText("password.required"));
			reloadPage();
		}
		
		if (changedPassword.length() >= 20) {
			addFieldError("password", getText("password.tooLong"));
			reloadPage();
		}
	}

	private void reloadPage() {
		HttpSession session = servletRequest.getSession();

		String userName = (String) session.getAttribute("loged");
		UserDetails user = DAO.getUserDetails(userName);

		user = DAO.getUserDetails(userName);
		aboutMe = user.getInterests();
		avatarName = user.getAvatar();
	}
}
