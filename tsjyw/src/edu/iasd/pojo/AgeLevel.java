package edu.iasd.pojo;
// default package

import java.util.HashSet;
import java.util.Set;


/**
 * AgeLevel entity. @author MyEclipse Persistence Tools
 * 
 * 
 *            包括用户提问问题的年龄段
 *            名师擅长的年龄段
 *            志愿者的擅长的年龄段
 *            申请成为志愿者时擅长的年龄段
 */

public class AgeLevel  implements java.io.Serializable {


    // Fields    

     private Integer ageLevelId;
     private String ageName;
     //private Set userAgeLevels = new HashSet(0);
     private Set famousTeachers = new HashSet(0);
     private Set volunteers = new HashSet(0);
     private Set applys = new HashSet(0);
     private Set askQuestions = new HashSet(0);

    // Constructors

    /** default constructor */
    public AgeLevel() {
    }

	/** minimal constructor */
    public AgeLevel(String ageName) {
        this.ageName = ageName;
    }

	public Integer getAgeLevelId() {
		return ageLevelId;
	}

	public void setAgeLevelId(Integer ageLevelId) {
		this.ageLevelId = ageLevelId;
	}

	public String getAgeName() {
		return ageName;
	}

	public void setAgeName(String ageName) {
		this.ageName = ageName;
	}

	public Set getFamousTeachers() {
		return famousTeachers;
	}

	public void setFamousTeachers(Set famousTeachers) {
		this.famousTeachers = famousTeachers;
	}

	public Set getVolunteers() {
		return volunteers;
	}

	public void setVolunteers(Set volunteers) {
		this.volunteers = volunteers;
	}

	public Set getApplys() {
		return applys;
	}

	public void setApplys(Set applys) {
		this.applys = applys;
	}

	public Set getAskQuestions() {
		return askQuestions;
	}

	public void setAskQuestions(Set askQuestions) {
		this.askQuestions = askQuestions;
	}
    
    /** full constructor */
    

	

}