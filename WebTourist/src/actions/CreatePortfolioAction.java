package actions;

import java.io.File;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;

import dao.DAO;

public class CreatePortfolioAction extends ActionSupport implements
		ServletRequestAware {
	private List<File> userImages;
	private List<String> userImagesContentType;
	private List<String> userImagesFileName;
	private HttpServletRequest servletRequest;
	private SortedSet<String> titleList = new TreeSet<String>();
	private String description;
	private String portfolioName;
	private String userName;
	private String messageImage;

	public String getMessageImage() {
		return messageImage;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPortfolioName() {
		return portfolioName;
	}

	public void setPortfolioName(String portfolioName) {
		this.portfolioName = portfolioName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<File> getUserImages() {
		return userImages;
	}

	public void setUserImages(List<File> userImages) {
		this.userImages = userImages;
	}

	public List<String> getUserImagesContentType() {
		return userImagesContentType;
	}

	public void setUserImagesContentType(List<String> userImagesContentType) {
		this.userImagesContentType = userImagesContentType;
	}

	public List<String> getUserImagesFileName() {
		return userImagesFileName;
	}

	public void setUserImagesFileName(List<String> userImagesFileName) {
		this.userImagesFileName = userImagesFileName;
	}

	@Override
	public void setServletRequest(HttpServletRequest servletRequest) {
		this.servletRequest = servletRequest;

	}

	public SortedSet<String> getTitleList() {
		return titleList;
	}

	public String execute() {
		HttpSession session = servletRequest.getSession();
		if (session.getAttribute("loged") == null) {
			return ERROR;
		} else {
			userName = (String) session.getAttribute("loged");
		}

		try {

			ServletContext context = session.getServletContext();
			String filePath = context.getRealPath("/");
			System.out.println("Server path:" + filePath);

			for (int i = 0; i < userImages.size(); i++) {
				File fileToCreate = new File(filePath,
						this.userImagesFileName.get(i));
				FileUtils.copyFile(this.userImages.get(i), fileToCreate);

			}

			DAO.addPortfolio(portfolioName, description, userName,
					userImagesFileName);

		} catch (Exception e) {
			e.printStackTrace();
			addActionError(e.getMessage());

			return INPUT;
		}
		titleList = DAO.getPortfolioTitles();
		description = null;
		portfolioName = null;
		userImages.clear();

		boolean isNewMessage = DAO.hasNewMessage(userName);
		if (isNewMessage) {
			messageImage = "new_message.jpg";
		} else {
			messageImage = "no_new_messages.jpg";
		}

		return SUCCESS;
	}

	@Override
	public void validate() {
		if (portfolioName.length() == 0) {
			addFieldError("portfolioName", getText("title.required"));
			reloadPage();
		}
		if (description.length() == 0) {
			addFieldError("description", getText("description.required"));
			reloadPage();
		}
		
		SortedSet<String> returnedTitleList = new TreeSet<String>();
		returnedTitleList = DAO.getPortfolioTitles();
		
		for (String portfolioTitle : returnedTitleList) {
			if (portfolioTitle.equals(portfolioName)) {
				addFieldError("portfolioName", getText("title.exists"));
				userImages.clear();

				reloadPage();

				break;
			}
		}

		if (userImagesFileName.size() == 0) {
			addFieldError("portfolioName", getText("image.reuired"));
			reloadPage();
		}

		if (description.length() >= 255) {
			addFieldError("description", getText("desription.tooLong"));
			reloadPage();
		}

		if (portfolioName.length() >= 20) {
			addFieldError("portfolioName", getText("title.tooLong"));
			reloadPage();
		}
	}

	private void reloadPage() {
		titleList = DAO.getPortfolioTitles();

		HttpSession session = servletRequest.getSession();
		userName = (String) session.getAttribute("loged");

		boolean isNewMessage = false;
		if (isNewMessage) {
			messageImage = "new_message.jpg";
		} else {
			messageImage = "no_new_messages.jpg";
		}
	}
}
