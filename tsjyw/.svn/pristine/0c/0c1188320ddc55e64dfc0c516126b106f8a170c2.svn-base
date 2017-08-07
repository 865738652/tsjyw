package edu.iasd.pojo;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class ViewUserBase implements Serializable {
	private static final long serialVersionUID = 9115429216382631425L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;
	
	@Column
    private String userName;
	
	@Column
    private String userNickname;
	
	@Column
    private Integer userGender;
	
	@Column
	private String userIdentityCode;
	
	
	@Column
    private String userAccount;
	
	@Column
    private String userPassword;
	
	@Column
    private Timestamp userbirthday;
	
	@Column
    private String userWeixin;
	
	@Column
    private String userQq;
	
	@Column
    private String userPhone;
	
	@Column
    private String userEmail;
	
	@Column
    private Integer userIntegral;
	
	@Column
	private String userPhotoUrl;
	
	@Column
	private String userCode;
	
	@Column
	private String userWeQRCodeUrl;
	
	@Column
	private String userOpenId;

	@Column
	private Timestamp lastLoginTime;
	
	public String getUserIdentityCode() {
		return userIdentityCode;
	}
	public void setUserIdentityCode(String userIdentityCode) {
		this.userIdentityCode = userIdentityCode;
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
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}	
	public String getUserWeQRCodeUrl() {
		return userWeQRCodeUrl;
	}

	public void setUserWeQRCodeUrl(String userWeQRCodeUrl) {
		this.userWeQRCodeUrl = userWeQRCodeUrl;
	}

	public Timestamp getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Timestamp lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	
	public String getUserOpenId() {
		return userOpenId;
	}
	public void setUserOpenId(String userOpenId) {
		this.userOpenId = userOpenId;
	}
	@Override
	public String toString() {
		return "ViewUserBase [userId=" + userId + ", userName=" + userName
				+ ", userNickname=" + userNickname + ", userGender="
				+ userGender + ", userIdentityCode=" + userIdentityCode
				+ ", userAccount=" + userAccount + ", userPassword="
				+ userPassword + ", userbirthday=" + userbirthday
				+ ", userWeixin=" + userWeixin + ", userQq=" + userQq
				+ ", userPhone=" + userPhone + ", userEmail=" + userEmail
				+ ", userIntegral=" + userIntegral + ", userPhotoUrl="
				+ userPhotoUrl + ", userCode=" + userCode + "]";
	}
	
	
}
