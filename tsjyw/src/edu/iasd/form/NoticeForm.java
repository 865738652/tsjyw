package edu.iasd.form;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class NoticeForm implements Serializable {
	private static final long serialVersionUID = -9127689771848872007L;
	private Integer noticeId;
    private String noticeTitle;
    private String noticeContent;
    private String noticeOverTime;
    private List<NoticeAcceptVO> accepts;
    private List<AttachVO> attachments;
	public Integer getNoticeId() {
		return noticeId;
	}
	public void setNoticeId(Integer noticeId) {
		this.noticeId = noticeId;
	}
	
	
	
	
	public String getNoticeOverTime() {
		return noticeOverTime;
	}
	public void setNoticeOverTime(String noticeOverTime) {
		this.noticeOverTime = noticeOverTime;
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
	public List<NoticeAcceptVO> getAccepts() {
		return accepts;
	}
	public void setAccepts(List<NoticeAcceptVO> accepts) {
		this.accepts = accepts;
	}
	public List<AttachVO> getAttachments() {
		return attachments;
	}
	public void setAttachments(List<AttachVO> attachments) {
		this.attachments = attachments;
	}
	@Override
	public String toString() {
		return "NoticeForm [noticeId=" + noticeId + ", noticeTitle="
				+ noticeTitle + ", noticeContent=" + noticeContent
				+ ", noticeOverTime=" + noticeOverTime + ", accepts=" + accepts
				+ ", attachments=" + attachments + "]";
	}
	
	
	
	
	
}
