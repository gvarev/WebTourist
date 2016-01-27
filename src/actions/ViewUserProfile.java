package actions;

import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;

import dao.DAO;
import dto.Comments;
import dto.UserDetails;

public class ViewUserProfile extends ActionSupport implements
		ServletRequestAware {
	private HttpServletRequest servletRequest;
	private String commentId;
	private String avatarName;
	private String userName;
	private String aboutMe;
	private SortedSet<String> titleList = new TreeSet<String>();
	
	public SortedSet<String> getTitleList() {
		return titleList;
	}

	public String getAvatarName() {
		return avatarName;
	}

	public String getUserName() {
		return userName;
	}

	public String getAboutMe() {
		return aboutMe;
	}

	@Override
	public void setServletRequest(HttpServletRequest servletRequest) {
		this.servletRequest = servletRequest;
	}

	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}

	@Override
	public String execute() {
		int parsedCommentId = Integer.parseInt(commentId);
		UserDetails user = null;
		
		HttpSession session = servletRequest.getSession();
		String portfolioTitle = (String) session.getAttribute("title");
		String logedUser = (String) session.getAttribute("loged");

		List<Comments> commentsList = DAO.getCommentsUserName(portfolioTitle);
		Comments comment = commentsList.get(parsedCommentId);
		String commentUserName = comment.getUserName();
		
		if (commentUserName.equals(logedUser)) {
			user = DAO.getUserDetails(logedUser);
			avatarName = user.getAvatar();
			aboutMe = user.getInterests();
			
			return "editProfile";
		}

		user = DAO.getUserDetails(commentUserName);
		userName = user.getUserName();
		avatarName = user.getAvatar();
		aboutMe = user.getInterests();
		
		titleList = DAO.getUserPortfolioTitles(userName);

		return SUCCESS;
	}
}
