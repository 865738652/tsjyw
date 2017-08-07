package edu.iasd.pojo;
// default package

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;


/**
 * Notice entity. @author MyEclipse Persistence Tools
 */

public class Notice  implements java.io.Serializable {


    // Fields    
	 //一天的毫秒数
	 public static final long DAY_TIMESTAMP = 86400000;
	 
	 public static final Integer LOOK_IS = 1;
	 public static final Integer LOOK_NOT = 0;
	 
	 
	
     private Integer noticeId;
     private Timestamp noticeTime;
     private String noticeTitle;
     private String noticeContent;
     private Integer noticeIsOrNotReply;
     private Timestamp noticeOverTime;
     
     //发件人
     private User user;
     private NoticeState noticeState;
     private NoticeAcceptType noticeAcceptType;
     private Set noticeAttachments = new HashSet(0);
     private Set noticeAccepts = new HashSet(0);
     private Set replies = new HashSet(0);


    // Constructors

    /** default constructor */
    public Notice() {
    }


	public Integer getNoticeId() {
		return noticeId;
	}


	public void setNoticeId(Integer noticeId) {
		this.noticeId = noticeId;
	}


	public Timestamp getNoticeTime() {
		return noticeTime;
	}


	public void setNoticeTime(Timestamp noticeTime) {
		this.noticeTime = noticeTime;
	}


	public String getNoticeTitle() {
		return noticeTitle;
	}


	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}


	public String getNoticeContent() {
		return noticeContent;
	}


	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}


	public Integer getNoticeIsOrNotReply() {
		return noticeIsOrNotReply;
	}


	public void setNoticeIsOrNotReply(Integer noticeIsOrNotReply) {
		this.noticeIsOrNotReply = noticeIsOrNotReply;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public NoticeState getNoticeState() {
		return noticeState;
	}


	public void setNoticeState(NoticeState noticeState) {
		this.noticeState = noticeState;
	}


	public NoticeAcceptType getNoticeAcceptType() {
		return noticeAcceptType;
	}


	public void setNoticeAcceptType(NoticeAcceptType noticeAcceptType) {
		this.noticeAcceptType = noticeAcceptType;
	}


	public Set getNoticeAttachments() {
		return noticeAttachments;
	}


	public void setNoticeAttachments(Set noticeAttachments) {
		this.noticeAttachments = noticeAttachments;
	}


	public Set getNoticeAccepts() {
		return noticeAccepts;
	}


	public void setNoticeAccepts(Set noticeAccepts) {
		this.noticeAccepts = noticeAccepts;
	}


	public Set getReplies() {
		return replies;
	}


	public void setReplies(Set replies) {
		this.replies = replies;
	}


	public Timestamp getNoticeOverTime() {
		return noticeOverTime;
	}


	public void setNoticeOverTime(Timestamp noticeOverTime) {
		this.noticeOverTime = noticeOverTime;
	}


	

	/** minimal constructor */
    





}