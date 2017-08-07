package edu.iasd.pojo;
// default package

import java.util.HashSet;
import java.util.Set;


/**
 * AskQuestionType entity. @author MyEclipse Persistence Tools
 */

public class AskQuestionType  implements java.io.Serializable {

	public final static int ASKQUESTIONTYPE_FLAG_FAMOUSTEACHER = 1;
	public final static int ASKQUESTIONTYPE_FLAG_VOLUNTEER = 2;
    // Fields    

     private Integer askQuestionTypeId;
     private String askQuestionTypeName;
     private Integer aspQuestionTypePublic;
     private Integer askQuestionTypeFlag;
     private Set famousTeachers = new HashSet(0);
     private Set askQuestions = new HashSet(0);
     private Set applys = new HashSet(0);
     //private Set userAskQuestions = new HashSet(0);
     private Set volunteers = new HashSet(0);


    // Constructors

    /** default constructor */
    public AskQuestionType() {
    }

	/** minimal constructor */
    public AskQuestionType(String askQuestionTypeName) {
        this.askQuestionTypeName = askQuestionTypeName;
    }

	public Integer getAskQuestionTypeId() {
		return askQuestionTypeId;
	}

	public void setAskQuestionTypeId(Integer askQuestionTypeId) {
		this.askQuestionTypeId = askQuestionTypeId;
	}

	public String getAskQuestionTypeName() {
		return askQuestionTypeName;
	}

	public void setAskQuestionTypeName(String askQuestionTypeName) {
		this.askQuestionTypeName = askQuestionTypeName;
	}

	public Integer getAspQuestionTypePublic() {
		return aspQuestionTypePublic;
	}

	public void setAspQuestionTypePublic(Integer aspQuestionTypePublic) {
		this.aspQuestionTypePublic = aspQuestionTypePublic;
	}

	public Integer getAskQuestionTypeFlag() {
		return askQuestionTypeFlag;
	}

	public void setAskQuestionTypeFlag(Integer askQuestionTypeFlag) {
		this.askQuestionTypeFlag = askQuestionTypeFlag;
	}

	public Set getFamousTeachers() {
		return famousTeachers;
	}

	public void setFamousTeachers(Set famousTeachers) {
		this.famousTeachers = famousTeachers;
	}

	public Set getAskQuestions() {
		return askQuestions;
	}

	public void setAskQuestions(Set askQuestions) {
		this.askQuestions = askQuestions;
	}

	public Set getApplys() {
		return applys;
	}

	public void setApplys(Set applys) {
		this.applys = applys;
	}

	public Set getVolunteers() {
		return volunteers;
	}

	public void setVolunteers(Set volunteers) {
		this.volunteers = volunteers;
	}
    
    /** full constructor */
    

   
    // Property accessors

    







}