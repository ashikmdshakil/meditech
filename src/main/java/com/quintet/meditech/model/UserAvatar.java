package com.quintet.meditech.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@Entity
public class UserAvatar {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int avatarId;
	@Lob
	private byte[] image;
	@OneToOne(mappedBy = "userAvatar")
	private Users user;

	public UserAvatar() {
	}

	public int getAvatarId() {
		return avatarId;
	}

	public void setAvatarId(int avatarId) {
		this.avatarId = avatarId;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

}
