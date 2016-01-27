package dto;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Pictures {
	@Column(name = "PICTURE_NAME")
	private String pictureName;

	public String getPictureName() {
		return pictureName;
	}

	public void setPictureName(String pictureName) {
		this.pictureName = pictureName;
	}

}
