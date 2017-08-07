package edu.iasd.pojo;
// default package

import java.sql.Timestamp;


/**
 * Reply entity. @author MyEclipse Persistence Tools
 */

public class Reply  implements java.io.Serializable {


    // Fields    
	 public static final int LOOK_IS = 1;
	 public static final int LOOK_NOT = 0;
	
	
     private Integer replyId;
     private Notice notice;
     private User user;
     private Integer isOrNotLook;
     private Timestamp lookTime;
     private String replyContent;
     private Timestamp replyTime;


    // Constructors

    /** default constructor */
    public Reply() {
    }

    
    /** full constructor */
    public Reply(Notice notice, User user, Integer isOrNotLook, Timestamp lookTime, String replyContent, Timestamp replyTime) {
        this.notice = notice;
        this.user = user;
        this.isOrNotLook = isOrNotLook;
        this.lookTime = lookTime;
        this.replyContent = replyContent;
        this.replyTime = replyTime;
    }

   
    // Property accessors

    public Integer getReplyId() {
        return this.replyId;
    }
    
    public void setReplyId(Integer replyId) {
        this.replyId = replyId;
    }

    public Notice getNotice() {
        return this.notice;
    }
    
    public void setNotice(Notice notice) {
        this.notice = notice;
    }

    public User getUser() {
        return this.user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }

    public Integer getIsOrNotLook() {
        return this.isOrNotLook;
    }
    
    public void setIsOrNotLook(Integer isOrNotLook) {
        this.isOrNotLook = isOrNotLook;
    }

    public Timestamp getLookTime() {
        return this.lookTime;
    }
    
    public void setLookTime(Timestamp lookTime) {
        this.lookTime = lookTime;
    }

    public String getReplyContent() {
        return this.replyContent;
    }
    
    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }

    public Timestamp getReplyTime() {
        return this.replyTime;
    }
    
    public void setReplyTime(Timestamp replyTime) {
        this.replyTime = replyTime;
    }
   








}