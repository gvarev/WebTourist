package actions;

import com.opensymphony.xwork2.ActionSupport;

import java.util.List;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import services.PasswordEncryptionService;
import dao.DAO;
import dto.User;

public class PasswordRecoveryAction extends ActionSupport {

	private String userName;
	private String from;
	private String password;
	private String to;
	private String subject;
	private String body;

	public void setUserName(String userName) {
		this.userName = userName;
	}

	static Properties properties = new Properties();
	static {
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.socketFactory.port", "465");
		properties.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.port", "465");
	}

	@Override
	public String execute() {
		
		String encodePassword = DAO.getPassword(userName);
		String decodedPassword = PasswordEncryptionService.decode(encodePassword);
		
		to = DAO.getEmail(userName);
		from = "ggeorgiev.ivan";
		password = "w8goglez098";
		subject = "Password Recovery";
		body = decodedPassword;

		try {
			Session session = Session.getDefaultInstance(properties,
					new javax.mail.Authenticator() {
						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication(from, password);
						}
					});

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(to));
			message.setSubject(subject);
			message.setText(body);
			Transport.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return SUCCESS;
	}

	public void validate() {

		if (userName.length() == 0) {
			addFieldError("userName", getText("name.required"));
		}
		
		if (userName.length() >= 20) {
			addFieldError("userName", getText("userName.tooLong"));
		}

		List<User> userList = DAO.getUsertList();
		for (User user : userList) {
			if (!userName.equals(user.getUserName())) {
				addFieldError("userName", getText("name.wrongUser"));
				break;
			}
		}
	}
}
