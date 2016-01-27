package actions;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;

import dao.DAO;
import dto.Comments;
import dto.PrivateMessage;
import dto.UserDetails;

public class ViewSenderProfileAction extends ActionSupport implements
		ServletRequestAware {
	private HttpServletRequest servletRequest;
	private String avatarName;
	private String userName;
	private String aboutMe;
	private String messageId;
	private SortedSet<String> titleList = new TreeSet<String>();

	public SortedSet<String> getTitleList() {
		return titleList;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
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

	@Override
	public String execute() {
		int parsedMessageId = Integer.parseInt(messageId);

		HttpSession session = servletRequest.getSession();
		String recipient = (String) session.getAttribute("loged");
		
		List<PrivateMessage> listOfMessages = DAO.getMessages(recipient);
		List<PrivateMessage> reversedListOfMessages = new ArrayList<PrivateMessage>();
		
		for (int i = listOfMessages.size() - 1; i >= 0; i--) {
			reversedListOfMessages.add(listOfMessages.get(i));
		}
		
		String messageSender = reversedListOfMessages.get(parsedMessageId).getSender();

		UserDetails user = DAO.getUserDetails(messageSender);
		userName = user.getUserName();
		avatarName = user.getAvatar();
		aboutMe = user.getInterests();
		
		titleList = DAO.getUserPortfolioTitles(userName);

		return SUCCESS;
	}
}
