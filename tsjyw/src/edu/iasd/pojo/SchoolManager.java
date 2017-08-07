package edu.iasd.pojo;

import java.io.Serializable;

// default package



/**
 * SchoolManager entity. @author MyEclipse Persistence Tools
 */

public class SchoolManager  implements Serializable{


    // Fields    
	 private Integer userId;
	 private User user;
     private School school;



    // Constructors

    /** default constructor */
    public SchoolManager() {
    }



	public School getSchool() {
		return school;
	}



	public void setSchool(School school) {
		this.school = school;
	}



	public Integer getUserId() {
		return userId;
	}



	public void setUserId(Integer userId) {
		this.userId = userId;
	}



	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}

    
    /** full constructor */
    

   
    // Property accessors

   
}