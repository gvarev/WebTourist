package actions;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;

import dao.DAO;
import dto.UserDetails;

public class SendMessageAction extends ActionSupport implements
		ServletRequestAware {
	private HttpServletRequest servletRequest;
	private String to;
	private String from;
	private String message;
	private String userName;
	private String avatarName;
	private String aboutMe;
	private SortedSet<String> titleList = new TreeSet<String>();

	public SortedSet<String> getTitleList() {
		return titleList;
	}

	public String getUserName() {
		return userName;
	}
	
	public String getAvatarName() {
		return avatarName;
	}
	
	public String getAboutMe() {
		return aboutMe;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public void setServletRequest(HttpServletRequest servletRequest) {
		this.servletRequest = servletRequest;

	}

	@Override
	public String execute() {
		HttpSession session = servletRequest.getSession();
		from = (String) session.getAttribute("loged");
		to = (String) session.getAttribute("uploadedBy");
		
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy,HH:mm:ss");
		String formatedDate = df.format(date).toString();
		boolean isNew = true;
		
		DAO.addMessage(from, to, message, formatedDate, isNew);
		
		userName = (String) session.getAttribute("uploadedBy");
		UserDetails user = DAO.getUserDetails(userName);
		avatarName = user.getAvatar();
		aboutMe = user.getInterests();
		
		titleList = DAO.getUserPortfolioTitles(userName);
		
		return SUCCESS;
	}
}
