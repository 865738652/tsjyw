package edu.iasd.form;

import java.sql.Timestamp;

public class RegisterForm implements java.io.Serializable{
	private static final long serialVersionUID = 6499999258297111624L; 
	
	private String userName;
	private String password;
	private String realName;
	private String idcard;
	private String weixin;
	private String email;
	private String phone;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	public String getWeixin() {
		return weixin;
	}
	public void setWeixin(String weixin) {
		this.weixin = weixin;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public UserForm translate() {
		UserForm form = new UserForm();
		form.setUserAccount(this.getUserName());
		form.setUserbirthday(new Timestamp(System.currentTimeMillis()));
		form.setUserGender(0);
		form.setUserIdentityCode(this.getIdcard());
		form.setUserIntegral(0);
		form.setUserName(this.getRealName());
		form.setUserPassword(this.getPassword());
		form.setUserWeixin(this.getWeixin());
		form.setUserPhone(this.getPhone());
		form.setUserEmail(this.getEmail());
		return form;
	}
}
