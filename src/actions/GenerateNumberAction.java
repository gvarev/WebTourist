package actions;

import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;

import dao.DAO;
import dto.Numbers;

public class GenerateNumberAction extends ActionSupport implements ServletRequestAware {
	private HttpServletRequest servletRequest;
	private String generatedNumber;
	private Random rd = new Random();
	
	@Override
	public void setServletRequest(HttpServletRequest servletRequest) {
		this.servletRequest = servletRequest;

	}
	
	public String getGeneratedNumber() {
		return generatedNumber;
	}

	@Override
	public String execute() {
		int randomNumber = 0;
		randomNumber = rd.nextInt(4);
		if (randomNumber == 0) {
			randomNumber++;
		}

		List<Numbers> numberList = DAO.getNumberList();
		for (Numbers number : numberList) {
			if (number.getId() == randomNumber) {
				generatedNumber = Integer.toString(number.getNumber());
				break;
			}
		}
		
		HttpSession session = servletRequest.getSession();
		session.setAttribute("number", generatedNumber);

		return SUCCESS;
	}
}
