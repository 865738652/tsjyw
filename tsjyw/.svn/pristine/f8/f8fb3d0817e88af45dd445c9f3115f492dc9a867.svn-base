package edu.iasd.form;

import java.io.Serializable;
import java.sql.Timestamp;

import edu.iasd.pojo.Teacher;
import edu.iasd.pojo.User;

public class UserForm implements Serializable{
	private Integer userId;
	private String userName;
	private String userNickname;
	private Integer userGender;
	private String userAccount;
	private String userPassword;
	private Timestamp userbirthday;
	private String userWeixin;
	private String userQq;
	private String userPhone;
	private String userEmail;
	private Integer userIntegral;
	private String userIdentityCode;
	private String userPhotoUrl;
    
	public String getUserIdentityCode() {
		return userIdentityCode;
	}

	public void setUserIdentityCode(String userIdentityCode) {
		this.userIdentityCode = userIdentityCode;
	}

	public UserForm() {
		userIntegral = 0;
	}
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserNickname() {
		return userNickname;
	}
	public void setUserNickname(String userNickname) {
		this.userNickname = userNickname;
	}
	public Integer getUserGender() {
		return userGender;
	}
	public void setUserGender(Integer userGender) {
		this.userGender = userGender;
	}
	public String getUserAccount() {
		return userAccount;
	}
	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public Timestamp getUserbirthday() {
		return userbirthday;
	}
	public void setUserbirthday(Timestamp userbirthday) {
		this.userbirthday = userbirthday;
	}
	public String getUserWeixin() {
		return userWeixin;
	}
	public void setUserWeixin(String userWeixin) {
		this.userWeixin = userWeixin;
	}
	public String getUserQq() {
		return userQq;
	}
	public void setUserQq(String userQq) {
		this.userQq = userQq;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public Integer getUserIntegral() {
		return userIntegral;
	}
	public void setUserIntegral(Integer userIntegral) {
		this.userIntegral = userIntegral;
	}
	
	public String getUserPhotoUrl() {
		return userPhotoUrl;
	}

	public void setUserPhotoUrl(String userPhotoUrl) {
		this.userPhotoUrl = userPhotoUrl;
	}

	public void fillUser(User u) {
		u.setUserId(this.getUserId());
		u.setUserName(this.getUserName());
		u.setUserNickname(this.getUserNickname());
		u.setUserGender(this.getUserGender());
		if (u.getUserAccount() == null || u.getUserAccount().length() == 0)
			u.setUserAccount(this.getUserAccount());
		if (userPassword != null && userPassword.length() > 0)
			u.setUserPassword(this.getUserPassword());
		u.setUserbirthday(this.getUserbirthday());
		u.setUserWeixin(this.getUserWeixin());
		u.setUserQq(this.getUserQq());
		u.setUserEmail(this.getUserEmail());
		u.setUserPhone(this.getUserPhone());
		u.setUserIntegral(this.getUserIntegral());
		u.setUserIdentityCode(this.getUserIdentityCode());
		if (userPhotoUrl != null && userPhotoUrl.length() > 0)
			u.setUserPhotoUrl(userPhotoUrl);
	}
}
