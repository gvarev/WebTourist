package actions;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;

import dao.DAO;
import dto.PrivateMessage;
import dto.UserDetails;

public class DeleteMessageAction extends ActionSupport implements
		ServletRequestAware {
	private HttpServletRequest servletRequest;
	private String messageId;
	private Map<String, String> messages = new LinkedHashMap<String, String>();
	private String userName;
	private String avatarName;
	private String aboutMe;

	public String getAvatarName() {
		return avatarName;
	}

	public String getAboutMe() {
		return aboutMe;
	}

	public String getUserName() {
		return userName;
	}

	public Map<String, String> getMessages() {
		return messages;
	}

	@Override
	public void setServletRequest(HttpServletRequest servletRequest) {
		this.servletRequest = servletRequest;

	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	public String getMessageId() {
		return messageId;
	}

	@Override
	public String execute() {
		HttpSession session = servletRequest.getSession();
		String recipient = (String) session.getAttribute("loged");
		int parsedMsgId = Integer.parseInt(messageId);

		List<PrivateMessage> listOfMessages = DAO.getMessages(recipient);
		PrivateMessage privateMessage = listOfMessages.get(parsedMsgId);
		int privateMessageId = privateMessage.getId();
		DAO.deleteMsg(privateMessageId);

		listOfMessages = DAO.getMessages(recipient);
		UserDetails user = null;
		
		for (int i = listOfMessages.size() - 1; i >= 0; i--) {
			PrivateMessage message = listOfMessages.get(i);
			user = DAO.getUserDetails(message.getSender());
			messages.put(
					"\n" + message.getSender() + "," + message.getMessage(),
					user.getAvatar());
		}

		return SUCCESS;
	}
}
