package dev.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "USERS")
@Getter
@Setter
public class User {

	@Id
	@Column(name = "USER_ID")
	private String userId;
	@Column(name = "USER_NAME")
	private String userName;
	@Column(name = "PHONE_NO")
	private String phoneNo;
	@Column(name = "EMAIL_ID")
	private String emailId;

}
