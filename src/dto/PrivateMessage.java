package dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PRIVATE_MESSAGES")
public class PrivateMessage {
	@Id
	@GeneratedValue
	@Column(name = "ID")
	private int id;
	@Column(name = "SENDER")
	private String sender;
	@Column(name = "RECIPIENT")
	private String recipient;
	@Column(name = "MESSAGE")
	private String message;
	@Column(name="IS_NEW")
	private boolean isNew;
	
	public boolean isNew() {
		return isNew;
	}

	public void setNew(boolean isNew) {
		this.isNew = isNew;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
