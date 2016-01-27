package actions;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
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

public class InsertCommentAction extends ActionSupport implements
		ServletRequestAware {
	private HttpServletRequest servletRequest;
	private Collection<Pictures> pictureList = new ArrayList<Pictures>();
	private List<String> pictureNames = new ArrayList<String>();
	private Portfolio portfolio = new Portfolio();
	private String userName;
	private String description;
	private String userNameSession;
	private String comment;
	private String portfolioTitle;
	private SortedSet<String> titleList = new TreeSet<String>();
	private Map<String, String> comments = new LinkedHashMap<String, String>();
	private UserDetails userDetails;
	private String userAvatar;
	private List<String> commentsDates;
	private String commentDate;
	private List<Comments> commentsList ;

	public String getCommentDate() {
		return commentDate;
	}

	public List<String> getCommentsDates() {
		return commentsDates;
	}

	public String getUserAvatar() {
		return userAvatar;
	}

	public Portfolio getPortfolio() {
		return portfolio;
	}

	public void setPortfolio(Portfolio portfolio) {
		this.portfolio = portfolio;
	}

	public Collection<Pictures> getPictureList() {
		return pictureList;
	}

	public List<String> getPictureNames() {
		return pictureNames;
	}

	public String getUserName() {
		return userName;
	}

	public String getDescription() {
		return description;
	}

	public Map<String, String> getComments() {
		return comments;
	}

	public SortedSet<String> getTitleList() {
		return titleList;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Override
	public void setServletRequest(HttpServletRequest servletRequest) {
		this.servletRequest = servletRequest;

	}

	@Override
	public String execute() {
		HttpSession loginSession = servletRequest.getSession();
		if (loginSession.getAttribute("loged") == null) {
			return ERROR;
		} else {
			userNameSession = (String) loginSession.getAttribute("loged");
		}

		Date date = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy,HH:mm:ss");
		String dateToString = sf.format(date).toString();
		portfolioTitle = (String) loginSession.getAttribute("title");

		DAO.insertComment(userNameSession, comment, portfolioTitle,
				dateToString);
		titleList = DAO.getPortfolioTitles();
		
		commentsList = DAO.getComments(portfolioTitle);
		UserDetails user = null;

		for (Comments comment : commentsList) {
			user = DAO.getUserDetails(comment.getUserName());
			comments.put(
					"\n" + comment.getUserName() + "," + comment.getComment(),
					user.getAvatar());
		}

		portfolio = DAO.getPortfolio(portfolioTitle);
		userName = portfolio.getUserName();
		description = portfolio.getDescription();
		pictureList = portfolio.getPictureList();
		userDetails = DAO.getUserDetails(userName);
		userAvatar = userDetails.getAvatar();

		for (Pictures picture : pictureList) {
			pictureNames.add(picture.getPictureName());
		}

		return SUCCESS;
	}

	@Override
	public void validate() {
		if (comment.length() == 0) {
			addFieldError("comment", getText("comment.required"));
		}
	}
}
