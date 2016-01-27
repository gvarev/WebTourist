package actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;

import dao.DAO;
import dto.UserDetails;

public class ProfileRedirect extends ActionSupport implements
		ServletRequestAware {
	private HttpServletRequest servletRequest;
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

	@Override
	public String execute() {
		HttpSession session = servletRequest.getSession();
		
		String userName = (String) session.getAttribute("loged");
		UserDetails user = DAO.getUserDetails(userName);
		
		user = DAO.getUserDetails(userName);
		aboutMe = user.getInterests();
		avatarName = user.getAvatar();
		
		return SUCCESS;
	}
}
