package edu.iasd.pojo;

import java.io.Serializable;


public class BusinessManager implements Serializable {
	
	 private Integer userId;
     private Business business;
     private User user;
     
     public BusinessManager(){
    	 
     }
	
     
	public Integer getUserId() {
		return userId;
	}


	public void setUserId(Integer userId) {
		this.userId = userId;
	}


	public Business getBusiness() {
		return business;
	}
	public void setBusiness(Business business) {
		this.business = business;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
     
     
     // Constructors

     /** default constructor */

}
