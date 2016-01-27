package actions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;

import dao.DAO;
import dto.Comments;
import dto.Pictures;
import dto.Portfolio;
import dto.UserDetails;

public class ViewPortfolioAction extends ActionSupport implements
		ServletRequestAware {
	private HttpServletRequest servletRequest;
	private String title;
	private Collection<Pictures> pictureList;
	private List<String> pictureNames = new ArrayList<String>();
	private String userName;
	private String description;
	private SortedSet<String> titleList = new TreeSet<String>();
	private Map<String, String> comments = new LinkedHashMap<String, String>();
	private UserDetails userDetails;
	private String userAvatar;
	private List<Comments> commentsList;

	public String getUserAvatar() {
		return userAvatar;
	}

	public void setUserAvatar(String userAvatar) {
		this.userAvatar = userAvatar;
	}

	public UserDetails getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(UserDetails userDetails) {
		this.userDetails = userDetails;
	}

	@Override
	public void setServletRequest(HttpServletRequest servletRequest) {
		this.servletRequest = servletRequest;

	}

	public Map<String, String> getComments() {
		return comments;
	}

	public SortedSet<String> getTitleList() {
		return titleList;
	}

	public void setTitleList(SortedSet<String> titleList) {
		this.titleList = titleList;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<String> getPictureNames() {
		return pictureNames;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String execute() {

		Portfolio portfolio = DAO.getPortfolio(title);
		userName = portfolio.getUserName();
		description = portfolio.getDescription();
		pictureList = portfolio.getPictureList();

		for (Pictures picture : pictureList) {
			pictureNames.add(picture.getPictureName());
		}

		commentsList = DAO.getComments(title);
		UserDetails user = null;
		
		for (Comments comment : commentsList) {
			user = DAO.getUserDetails(comment.getUserName());
			comments.put("\n" +comment.getUserName() + ","  + comment.getComment(), user.getAvatar());
		}

		HttpSession session = servletRequest.getSession();
		session.setAttribute("title", getTitle());
		userDetails = DAO.getUserDetails(userName);
		userAvatar = userDetails.getAvatar();

		HttpSession userNameSession = servletRequest.getSession();
		userNameSession.setAttribute("uploadedBy", userName);
		
		titleList = DAO.getPortfolioTitles();

		return SUCCESS;
	}

}
