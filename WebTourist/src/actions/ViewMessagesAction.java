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

public class ViewMessagesAction extends ActionSupport implements
		ServletRequestAware {
	private HttpServletRequest servletRequest;
	private Map<String, String> messages = new LinkedHashMap<String, String>();

	public Map<String, String> getMessages() {
		return messages;
	}

	@Override
	public void setServletRequest(HttpServletRequest servletRequest) {
		this.servletRequest = servletRequest;
	}

	@Override
	public String execute() {
		HttpSession session = servletRequest.getSession();
		String recipient = (String) session.getAttribute("loged");
		List<PrivateMessage> listOfMessages = DAO.getMessages(recipient);

		if (listOfMessages.size() == 0) {
			return ERROR;
		}

		for (int i = listOfMessages.size() - 1; i >= 0; i--) {
			PrivateMessage message = listOfMessages.get(i);
			UserDetails user = DAO.getUserDetails(message.getSender());
			messages.put(
					"\n" + message.getSender() + "," + message.getMessage(),
					user.getAvatar());
		}

		boolean hasNewMessages = false;
		DAO.updateMessages(listOfMessages, hasNewMessages);

		return SUCCESS;
	}

}
