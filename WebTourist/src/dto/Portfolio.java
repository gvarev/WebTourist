package dto;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Table;

import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "PORTFOLIOS")
public class Portfolio {
	@Id
	@GeneratedValue
	@Column(name = "ID")
	private int id;
	@Column(name = "PORTFOLIO_NAME")
	private String portfolioName;
	@Column(name = "USER_NAME")
	private String userName;
	@Column(name = "DESCRIPTION")
	private String description;
	@Column(name = "PICTURES")
	@ElementCollection(fetch = FetchType.EAGER)
	@JoinTable(name = "PORTFOLIO_PICTURES", joinColumns = @JoinColumn(name = "PORTFOLIO_ID"))
	@GenericGenerator(name = "hilo-gen", strategy = "hilo")
	@CollectionId(columns = { @Column(name = "PICTURE_ID") }, generator = "hilo-gen", type = @Type(type = "long"))
	private Collection<Pictures> pictureList = new ArrayList<Pictures>();

	public Collection<Pictures> getPictureList() {
		return pictureList;
	}

	public void setPictureList(Collection<Pictures> pictureList) {
		this.pictureList = pictureList;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

}
