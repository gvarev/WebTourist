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
import dto.PrivateMessage;
import dto.UserDetails;

public class ReplyAction extends ActionSupport implements ServletRequestAware {
	private HttpServletRequest servletRequest;
	private String messageId;
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

	public void setMessageId(String messageId) {
		this.messageId = messageId;
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
		List<PrivateMessage> messages = DAO.getMessages(recipient);
		List<PrivateMessage> reversedMessages = new ArrayList<PrivateMessage>();

		for (int i = messages.size() - 1; i >= 0; i--) {
			reversedMessages.add(messages.get(i));
		}

		PrivateMessage privateMessage = reversedMessages.get(parsedMessageId);

		userName = privateMessage.getSender();
		UserDetails user = DAO.getUserDetails(userName);
		avatarName = user.getAvatar();
		aboutMe = user.getInterests();
		
		titleList = DAO.getUserPortfolioTitles(userName);

		return SUCCESS;
	}
}
