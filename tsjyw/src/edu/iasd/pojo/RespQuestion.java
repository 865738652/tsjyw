package edu.iasd.pojo;
// default package

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;


/**
 * RespQuestion entity. @author MyEclipse Persistence Tools
 */

public class RespQuestion  implements java.io.Serializable {


    // Fields    

     private Integer respQuestionId;
     private User user;
     private AskQuestion askQuestion;
     private String respQuestionContent;
     private Timestamp respQuestionTime;
     private Boolean respQuestionPublic;
     private Set attachments = new HashSet(0);


    // Constructors

    /** default constructor */
    
    // Property accessors
     public RespQuestion()
     {
    	 
     }


	public Integer getRespQuestionId() {
		return respQuestionId;
	}


	public void setRespQuestionId(Integer respQuestionId) {
		this.respQuestionId = respQuestionId;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public AskQuestion getAskQuestion() {
		return askQuestion;
	}


	public void setAskQuestion(AskQuestion askQuestion) {
		this.askQuestion = askQuestion;
	}


	public String getRespQuestionContent() {
		return respQuestionContent;
	}


	public void setRespQuestionContent(String respQuestionContent) {
		this.respQuestionContent = respQuestionContent;
	}


	public Timestamp getRespQuestionTime() {
		return respQuestionTime;
	}


	public void setRespQuestionTime(Timestamp respQuestionTime) {
		this.respQuestionTime = respQuestionTime;
	}


	public Boolean getRespQuestionPublic() {
		return respQuestionPublic;
	}


	public void setRespQuestionPublic(Boolean respQuestionPublic) {
		this.respQuestionPublic = respQuestionPublic;
	}


	public Set getAttachments() {
		return attachments;
	}


	public void setAttachments(Set attachments) {
		this.attachments = attachments;
	}
    
     
     
}