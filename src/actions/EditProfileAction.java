package actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;

import dao.DAO;
import dto.UserDetails;

public class EditProfileAction extends ActionSupport implements
		ServletRequestAware {

	private HttpServletRequest servletRequest;
	private String interests;
	private String userName;
	private String aboutMe;
	private String avatarName;

	public String getAboutMe() {
		return aboutMe;
	}

	public String getAvatarName() {
		return avatarName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getInterests() {
		return interests;
	}

	public void setInterests(String interests) {
		this.interests = interests;
	}

	@Override
	public void setServletRequest(HttpServletRequest servletRequest) {
		this.servletRequest = servletRequest;

	}

	@Override
	public String execute() {

		HttpSession session = servletRequest.getSession();
		
		if (session.getAttribute("loged") == null) {
			return ERROR;
		} else {
			userName = (String) session.getAttribute("loged");
		}

		UserDetails user = DAO.getUserDetails(userName);
		int userId = user.getId();
		DAO.updateUserDetails(userId, interests);

		user = DAO.getUserDetails(userName);
		aboutMe = user.getInterests();
		avatarName = user.getAvatar();

		interests = null;

		return SUCCESS;
	}

	@Override
	public void validate() {
		if (interests.length() == 0) {
			addFieldError("interests", getText("interests.required"));
			reloadPage();
		}

		if (interests.length() >= 255) {
			addFieldError("interests", getText("interests.tooLong"));
			reloadPage();
		}
	}

	private void reloadPage() {
		HttpSession session = servletRequest.getSession();

		userName = (String) session.getAttribute("loged");
		UserDetails user = DAO.getUserDetails(userName);

		user = DAO.getUserDetails(userName);
		aboutMe = user.getInterests();
		avatarName = user.getAvatar();
	}
}
