package actions;

import java.util.SortedSet;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;

import dao.DAO;
import dto.UserDetails;

public class ViewUploaderProfileAction extends ActionSupport implements
		ServletRequestAware {
	private HttpServletRequest servletRequest;
	private String userName;
	private String avatarName;
	private String aboutMe;
	private SortedSet<String> titleList = new TreeSet<String>();

	public SortedSet<String> getTitleList() {
		return titleList;
	}

	public String getAvatarName() {
		return avatarName;
	}

	public String getAboutMe() {
		return aboutMe;
	}

	public String getUserName() {
		return userName;
	}

	@Override
	public void setServletRequest(HttpServletRequest servletRequest) {
		this.servletRequest = servletRequest;

	}

	@Override
	public String execute() {
		HttpSession session = servletRequest.getSession();
		userName = (String) session.getAttribute("uploadedBy");
		UserDetails user = null;
		String logedUser = (String) session.getAttribute("loged");

		if (userName.equals(logedUser)) {
			user = DAO.getUserDetails(logedUser);
			avatarName = user.getAvatar();
			aboutMe = user.getInterests();

			return "editProfile";
		}

		user = DAO.getUserDetails(userName);
		avatarName = user.getAvatar();
		aboutMe = user.getInterests();
		
		titleList = DAO.getUserPortfolioTitles(userName);
		
		return SUCCESS;
	}
}
