package actions;

import java.util.SortedSet;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;

import dao.DAO;

public class CreatePortfolioRedirect extends ActionSupport implements
		ServletRequestAware {
	private HttpServletRequest servletRequest;
	private SortedSet<String> titleList = new TreeSet<String>();
	String messageImage;

	@Override
	public void setServletRequest(HttpServletRequest servletRequest) {
		this.servletRequest = servletRequest;
	}

	public String getMessageImage() {
		return messageImage;
	}

	public SortedSet<String> getTitleList() {
		return titleList;
	}

	@Override
	public String execute() {

		titleList = DAO.getPortfolioTitles();
		
		HttpSession session = servletRequest.getSession();
		String userName = (String) session.getAttribute("loged");
		boolean isNewMessage = DAO.hasNewMessage(userName);
		if (isNewMessage) {
			messageImage = "new_message.jpg";
		} else {
			messageImage = "no_new_messages.jpg";
		}

		return SUCCESS;
	}
}
