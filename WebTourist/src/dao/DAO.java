package dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;
import java.util.SortedSet;
import java.util.TreeSet;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import dto.Comments;
import dto.Numbers;
import dto.Pictures;
import dto.Portfolio;
import dto.PrivateMessage;
import dto.User;
import dto.UserDetails;

public class DAO {
	private static final String USER_NAME = "userName";
	private static final String PORTFOLIO_NAME = "portfolioName";

	private static Session getSession() {
		Configuration conf = new Configuration();
		conf.configure();
		Properties confProperties = conf.getProperties();

		ServiceRegistryBuilder serviceRegistryBuilder = new ServiceRegistryBuilder();
		ServiceRegistryBuilder aplliedSettings = serviceRegistryBuilder
				.applySettings(confProperties);
		ServiceRegistry serviceRegistry = aplliedSettings
				.buildServiceRegistry();
		SessionFactory sessionFactory = conf
				.buildSessionFactory(serviceRegistry);

		return sessionFactory.openSession();
	}

	public static List<User> getUsertList() {
		Session session = getSession();
		session.beginTransaction();

		Criteria critera = session.createCriteria(User.class);
		List<User> userList = (List<User>) critera.list();

		Transaction transaction = session.getTransaction();
		transaction.commit();
		session.close();

		return userList;
	}

	public static boolean userExists(String userName, String password) {
		boolean exists = false;
		List<User> userList = DAO.getUsertList();

		for (User user : userList) {
			if ((user.getUserName().equals(userName))
					&& user.getPassword().equals(password)) {
				exists = true;
				break;
			}
		}

		return exists;
	}

	public static boolean registrationExists(String userName) {
		boolean exists = false;
		List<User> userList = DAO.getUsertList();

		for (User user : userList) {
			if ((user.getUserName().equals(userName))) {
				exists = true;
				break;
			}
		}

		return exists;
	}

	public static List<Numbers> getNumberList() {
		Session session = getSession();
		session.beginTransaction();

		Criteria critera = session.createCriteria(Numbers.class);
		List<Numbers> userList = (List<Numbers>) critera.list();

		Transaction transaction = session.getTransaction();
		transaction.commit();
		session.close();

		return userList;
	}

	public static void addUser(String userName, String password, String email) {
		User user = new User();
		user.setUserName(userName);
		user.setPassword(password);
		user.setEmail(email);

		Session session = getSession();
		session.beginTransaction();
		session.save(user);
		Transaction transaction = session.getTransaction();
		transaction.commit();
		session.close();
	}

	public static void addPortfolio(String title, String description,
			String userName, List<String> pictureList) {

		Portfolio portfolio = new Portfolio();

		String fixedDescription = " ";
		String splitedDesription[] = description.split("[\\s]");
		int counter = 0;

		for (int i = 0; i < splitedDesription.length; i++) {
			if (i == 0) {
				fixedDescription = fixedDescription + "\n";
			}
			fixedDescription = fixedDescription + splitedDesription[i] + " ";
			if (counter == 10) {
				fixedDescription = fixedDescription + "\n";
				counter = 0;
			}
			counter++;
		}

		portfolio.setDescription(fixedDescription);
		portfolio.setPortfolioName(title);
		portfolio.setUserName(userName);

		Collection<Pictures> pictures = new ArrayList<Pictures>();
		for (int i = 0; i < pictureList.size(); i++) {
			Pictures picture = new Pictures();
			picture.setPictureName(pictureList.get(i));
			pictures.add(picture);
		}

		portfolio.setPictureList(pictures);

		Session session = getSession();
		session.beginTransaction();
		session.save(portfolio);
		Transaction transaction = session.getTransaction();
		transaction.commit();
		session.close();
	}

	public static Portfolio getPortfolio(String title) {
		Portfolio portfolio = new Portfolio();
		Session session = getSession();
		session.beginTransaction();

		Criteria criteria = session.createCriteria(Portfolio.class);
		criteria.add(Restrictions.eq(PORTFOLIO_NAME, title));

		List<Portfolio> portfolioList = criteria.list();
		portfolio = portfolioList.get(0);

		Transaction transaction = session.getTransaction();
		transaction.commit();
		session.close();

		return portfolio;
	}

	public static SortedSet<String> getPortfolioTitles() {
		SortedSet<String> titleList = new TreeSet<String>();
		Session session = getSession();
		session.beginTransaction();

		Criteria criteria = session.createCriteria(Portfolio.class);
		List<Portfolio> portfolioList = criteria.list();

		Transaction transaction = session.getTransaction();
		transaction.commit();
		session.close();

		for (Portfolio portfolio : portfolioList) {
			String title = portfolio.getPortfolioName();
			titleList.add(title);
		}

		return titleList;
	}

	public static SortedSet<String> getUserPortfolioTitles(String userName) {
		SortedSet<String> portfolioTitleList = new TreeSet<String>();

		Session session = getSession();
		session.beginTransaction();

		Criteria criteria = session.createCriteria(Portfolio.class);
		criteria.add(Restrictions.eq(USER_NAME, userName));

		List<Portfolio> portfolioList = criteria.list();

		Transaction transaction = session.getTransaction();
		transaction.commit();
		session.close();

		for (Portfolio portfolio : portfolioList) {
			portfolioTitleList.add(portfolio.getPortfolioName());
		}

		return portfolioTitleList;
	}

	public static void insertComment(String userName, String comment,
			String portfolioName, String date) {
		Comments commentObject = new Comments();

		String commentWithDate = " " + date + ", " + comment;

		String[] splited = commentWithDate.split("[\\s]");
		String fixedCommentWithDate = "";
		int counter = 0;

		for (int i = 0; i < splited.length; i++) {
			if (i == 1) {
				fixedCommentWithDate = fixedCommentWithDate + "\n";
			}
			if (i == 2) {
				fixedCommentWithDate = fixedCommentWithDate + "\n";
			}
			fixedCommentWithDate = fixedCommentWithDate + splited[i] + " ";
			if (counter == 10) {
				fixedCommentWithDate = fixedCommentWithDate + "\n";
				counter = 0;
			}
			counter++;
		}

		commentObject.setUserName(userName);
		commentObject.setComment(fixedCommentWithDate);
		commentObject.setPortfolioName(portfolioName);

		Session session = getSession();
		session.beginTransaction();
		session.save(commentObject);
		Transaction transaction = session.getTransaction();
		transaction.commit();
		session.close();
	}

	public static List<Comments> getComments(String portfolioName) {
		Session session = getSession();
		session.beginTransaction();

		Criteria criteria = session.createCriteria(Comments.class);
		criteria.add(Restrictions.eq(PORTFOLIO_NAME, portfolioName));
		List<Comments> commentList = criteria.list();

		Transaction transaction = session.getTransaction();
		transaction.commit();
		session.close();

		return commentList;
	}

	public static List<Comments> getCommentsUserName(String portfolioTitle) {
		Session session = getSession();
		session.beginTransaction();

		Criteria criteria = session.createCriteria(Comments.class);
		criteria.add(Restrictions.eq(PORTFOLIO_NAME, portfolioTitle));
		List<Comments> commentList = criteria.list();

		Transaction transaction = session.getTransaction();
		transaction.commit();
		session.close();

		return commentList;
	}

	public static String getEmail(String userName) {
		Session session = getSession();
		session.beginTransaction();

		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq(USER_NAME, userName));
		List<User> userList = criteria.list();

		Transaction transaction = session.getTransaction();
		transaction.commit();
		session.close();

		User user = userList.get(0);
		String email = user.getEmail();
		return email;
	}

	public static String getPassword(String userName) {
		Session session = getSession();
		session.beginTransaction();

		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq(USER_NAME, userName));
		List<User> userList = criteria.list();

		Transaction transaction = session.getTransaction();
		transaction.commit();
		session.close();

		User user = userList.get(0);
		String password = user.getPassword();
		return password;
	}

	public static void addUserDetails(String interests, String avatarName,
			String userName) {
		UserDetails user = new UserDetails();

		user.setAvatar(avatarName);
		user.setInterests(interests);
		user.setUserName(userName);

		Session session = getSession();
		session.beginTransaction();
		session.save(user);
		Transaction transaction = session.getTransaction();
		transaction.commit();
		session.close();
	}

	public static UserDetails getUserDetails(String userName) {
		UserDetails user = new UserDetails();

		Session session = getSession();
		session.beginTransaction();

		Criteria criteria = session.createCriteria(UserDetails.class);
		criteria.add(Restrictions.eq(USER_NAME, userName));
		List<UserDetails> userList = criteria.list();

		Transaction transaction = session.getTransaction();
		transaction.commit();
		session.close();

		user = userList.get(0);

		return user;
	}

	public List<UserDetails> getAllUsersDetails() {
		Session session = getSession();
		session.beginTransaction();

		Criteria criteria = session.createCriteria(UserDetails.class);
		List<UserDetails> userList = criteria.list();

		Transaction transaction = session.getTransaction();
		transaction.commit();
		session.close();

		return userList;
	}

	public static void updateUserDetails(int userId, String interests) {

		String splitedIntersts[] = interests.split("[\\s]");
		String fixedInterests = "";
		int counter = 0;
		for (int i = 0; i < splitedIntersts.length; i++) {
			if (counter == 0) {
				fixedInterests = fixedInterests + "\n";
			}
			fixedInterests = fixedInterests + splitedIntersts[i] + " ";

			if (counter == 9) {
				fixedInterests = fixedInterests + "\n";
				counter = 0;
			}
			counter++;
		}

		Session session = getSession();
		session.beginTransaction();

		UserDetails user = (UserDetails) session.get(UserDetails.class, userId);
		user.setInterests(fixedInterests);
		session.update(user);

		Transaction transaction = session.getTransaction();
		transaction.commit();
		session.close();
	}

	public static void updateUserAvatar(int userId, String avatarName) {
		Session session = getSession();
		session.beginTransaction();

		UserDetails user = (UserDetails) session.get(UserDetails.class, userId);
		user.setAvatar(avatarName);
		session.update(user);

		Transaction transaction = session.getTransaction();
		transaction.commit();
		session.close();
	}

	public static void addMessage(String from, String to, String msg,
			String date, boolean isNew) {
		PrivateMessage message = new PrivateMessage();

		String messageWithDate = " " + date + ", " + msg;

		String[] splited = messageWithDate.split("[\\s]");
		String fixedMessageWithDate = "";
		int counter = 0;

		for (int i = 0; i < splited.length; i++) {
			if (i == 1) {
				fixedMessageWithDate = fixedMessageWithDate + "\n";
			}
			if (i == 2) {
				fixedMessageWithDate = fixedMessageWithDate + "\n";
			}
			fixedMessageWithDate = fixedMessageWithDate + splited[i] + " ";
			if (counter == 10) {
				fixedMessageWithDate = fixedMessageWithDate + "\n";
				counter = 0;
			}
			counter++;
		}

		message.setSender(from);
		message.setRecipient(to);
		message.setMessage(fixedMessageWithDate);
		message.setNew(isNew);

		Session session = getSession();
		session.beginTransaction();
		session.save(message);
		Transaction transaction = session.getTransaction();
		transaction.commit();
		session.close();
	}

	public static void updateMessages(List<PrivateMessage> listOfMessages,
			boolean isNew) {
		Session session = getSession();

		for (PrivateMessage message : listOfMessages) {
			session.beginTransaction();
			PrivateMessage privateMessage = (PrivateMessage) session.get(
					PrivateMessage.class, message.getId());
			privateMessage.setNew(isNew);
			session.update(privateMessage);
			Transaction transaction = session.getTransaction();
			transaction.commit();

		}
		session.close();

	}

	public static List<PrivateMessage> getMessages(String recipient) {
		List<PrivateMessage> messages = new ArrayList<PrivateMessage>();
		Session session = getSession();
		session.beginTransaction();

		Criteria criteria = session.createCriteria(PrivateMessage.class);
		criteria.add(Restrictions.eq("recipient", recipient));
		List<PrivateMessage> listOfMessages = criteria.list();

		Transaction transaction = session.getTransaction();
		transaction.commit();
		session.close();

		messages = listOfMessages;

		return listOfMessages;
	}

	public static void deleteMsg(int msgId) {
		Session session = getSession();
		session.beginTransaction();
		PrivateMessage message = (PrivateMessage) session.get(
				PrivateMessage.class, msgId);
		session.delete(message);
		Transaction transaction = session.getTransaction();
		transaction.commit();
		session.close();
	}

	public static boolean hasNewMessage(String recipient) {
		List<PrivateMessage> listOfMessages = getMessages(recipient);
		boolean isNew = false;
		for (PrivateMessage privateMessage : listOfMessages) {
			if (privateMessage.isNew()) {
				isNew = true;
				break;
			}
		}

		return isNew;
	}

	public static void updatePassword(String userName, String newPassword) {
		List<User> userList = getUsertList();
		User userObject = null;

		for (User user : userList) {
			if (user.getUserName().equals(userName)) {
				userObject = user;
				break;
			}
		}

		int userId = userObject.getId();

		Session session = getSession();
		session.beginTransaction();

		User user = (User) session.get(User.class, userId);
		user.setPassword(newPassword);
		session.update(user);

		Transaction transaction = session.getTransaction();
		transaction.commit();
		session.close();
	}

}
