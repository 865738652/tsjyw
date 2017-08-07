package edu.iasd.pojo;
// default package

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


/**
 * FamousTeacher entity. @author MyEclipse Persistence Tools
 */

public class FamousTeacher  implements Serializable{


    // Fields    
	 private Integer userId;
	 private User user;
     private String famousTeacherIntro;
     private Boolean recommend;
     private Set famousTeacherAgelevels = new HashSet(0);
     private Set famousTeacherAskquestiontypes = new HashSet(0);


    // Constructors

    /** default constructor */
    public FamousTeacher() {
    }


	public String getFamousTeacherIntro() {
		return famousTeacherIntro;
	}

	public void setFamousTeacherIntro(String famousTeacherIntro) {
		this.famousTeacherIntro = famousTeacherIntro;
	}
	
	public Boolean getRecommend() {
		return recommend;
	}

	public void setRecommend(Boolean recommend) {
		this.recommend = recommend;
	}

	public Set getFamousTeacherAgelevels() {
		return famousTeacherAgelevels;
	}

	public void setFamousTeacherAgelevels(Set famousTeacherAgelevels) {
		this.famousTeacherAgelevels = famousTeacherAgelevels;
	}


	public Set getFamousTeacherAskquestiontypes() {
		return famousTeacherAskquestiontypes;
	}


	public void setFamousTeacherAskquestiontypes(Set famousTeacherAskquestiontypes) {
		this.famousTeacherAskquestiontypes = famousTeacherAskquestiontypes;
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
	
	

	/** minimal constructor */
    
    /** full constructor */
    
   
    // Property accessors

    

   
}