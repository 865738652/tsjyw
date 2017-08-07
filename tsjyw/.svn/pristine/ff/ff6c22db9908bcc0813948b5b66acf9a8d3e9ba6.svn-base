package edu.iasd.form;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

public class ResponseForm implements Serializable {
	private Integer respQuestionId;
    private Integer userId;
    private Integer askQuestionId;
    private String respQuestionContent;
    private Timestamp respQuestionTime;
    private Boolean respQuestionPublic;
    private List<AttachVO> attachments;
    
    public ResponseForm() {
    	respQuestionPublic = false;
    	respQuestionTime = new Timestamp(System.currentTimeMillis());
    }
    
	public Integer getRespQuestionId() {
		return respQuestionId;
	}
	public void setRespQuestionId(Integer respQuestionId) {
		this.respQuestionId = respQuestionId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getAskQuestionId() {
		return askQuestionId;
	}
	public void setAskQuestionId(Integer askQuestionId) {
		this.askQuestionId = askQuestionId;
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
	public List<AttachVO> getAttachments() {
		return attachments;
	}
	public void setAttachments(List<AttachVO> attachments) {
		this.attachments = attachments;
	}
}
