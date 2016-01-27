package actions;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;

import dao.DAO;
import dto.UserDetails;

public class EditUserAvatarAction extends ActionSupport implements
		ServletRequestAware {
	private HttpServletRequest servletRequest;
	private File avatar;
	private String avatarFileName;
	private String userName;
	private String aboutMe;
	private String avatarName;

	public String getAboutMe() {
		return aboutMe;
	}

	public String getAvatarName() {
		return avatarName;
	}

	public File getAvatar() {
		return avatar;
	}

	public void setAvatar(File avatar) {
		this.avatar = avatar;
	}

	public String getAvatarFileName() {
		return avatarFileName;
	}

	public void setAvatarFileName(String avatarFileName) {
		this.avatarFileName = avatarFileName;
	}

	@Override
	public void setServletRequest(HttpServletRequest servletRequest) {
		this.servletRequest = servletRequest;
	}

	@Override
	public String execute() {
		HttpSession session = servletRequest.getSession();

		if (session.getAttribute("loged") == null) {
			return ERROR;
		} else {
			userName = (String) session.getAttribute("loged");
		}

		ServletContext context = session.getServletContext();
		String filePath = context.getRealPath("/");
		System.out.println("Server path:" + filePath);

		try {
			File fileToCreate = new File(filePath, this.avatarFileName);
			FileUtils.copyFile(this.avatar, fileToCreate);
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(e.getMessage());
		}

		UserDetails user = DAO.getUserDetails(userName);
		int userId = user.getId();
		DAO.updateUserAvatar(userId, avatarFileName);

		user = DAO.getUserDetails(userName);
		aboutMe = user.getInterests();
		avatarName = user.getAvatar();
		return SUCCESS;
	}

	@Override
	public void validate() {
		if (avatar == null) {
			addFieldError("avatar", getText("avatar.required"));
			reloadPage();
		}
	}

	private void reloadPage() {
		HttpSession session = servletRequest.getSession();

		userName = (String) session.getAttribute("loged");
		UserDetails user = DAO.getUserDetails(userName);

		user = DAO.getUserDetails(userName);
		aboutMe = user.getInterests();
		avatarName = user.getAvatar();
	}
}
