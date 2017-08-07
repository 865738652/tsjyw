package edu.iasd.pojo;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="v_notice")
public class ViewNotice implements Serializable{
	private static final long serialVersionUID = 9115482347138711L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer noticeId;
	
	@Column(name="UserId")
	private Integer userId;
	
	@Column(name="NoticeTime")
	private Timestamp noticeTime;
	
	@Column(name="NoticeTitle")
	private String noticeTitle;
	
	@Column(name="NoticeContent")
	private String noticeContent;
	
	@Column(name="NoticeIsOrNotReply")
	private Integer noticeIsOrNotReply;
	
	@Column(name="NoticeStateId")
	private Integer noticeStateId;
	
	@Column(name="NoticeAcceptTypeId")
	private Integer noticeAcceptTypeId;
	
	@Column(name="NoticeOverTime")
	private Timestamp noticeOverTime;
	
	@Column(name="NoticeAcceptTypeName")
	private String noticeAcceptTypeName;
	
	@Column(name="NoticeStateName")
	private String noticeStateName;

	public Integer getNoticeId() {
		return noticeId;
	}

	public void setNoticeId(Integer noticeId) {
		this.noticeId = noticeId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
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

	public Integer getNoticeStateId() {
		return noticeStateId;
	}

	public void setNoticeStateId(Integer noticeStateId) {
		this.noticeStateId = noticeStateId;
	}

	public Integer getNoticeAcceptTypeId() {
		return noticeAcceptTypeId;
	}

	public void setNoticeAcceptTypeId(Integer noticeAcceptTypeId) {
		this.noticeAcceptTypeId = noticeAcceptTypeId;
	}

	public Timestamp getNoticeOverTime() {
		return noticeOverTime;
	}

	public void setNoticeOverTime(Timestamp noticeOverTime) {
		this.noticeOverTime = noticeOverTime;
	}

	public String getNoticeAcceptTypeName() {
		return noticeAcceptTypeName;
	}

	public void setNoticeAcceptTypeName(String noticeAcceptTypeName) {
		this.noticeAcceptTypeName = noticeAcceptTypeName;
	}

	public String getNoticeStateName() {
		return noticeStateName;
	}

	public void setNoticeStateName(String noticeStateName) {
		this.noticeStateName = noticeStateName;
	}
	
	
	
}
