package edu.iasd.pojo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import edu.iasd.form.AgeLevelVO;
import edu.iasd.form.AttachVO;

public class ViewAskQuestion implements Serializable {
	private Integer askQuestionId;
    private Integer respUserId;
    private Integer askquestionTypeId;
    private Integer askUserId;
    private Integer askquestionStateId;
    private String askQuestionTitle;
    private String askQuestionContent;
    private Timestamp askQuestionTime;
    private Integer askQuestionRewardIntegral;
    private Boolean askQuestionPublic;
    private Integer askQuestionReadCount;
    private Integer askQuestionSelect;
    private String askName;
    private String askUserPhotoUrl;
    private String answerName;
    private String askQuestionTypeName;
    private String askQuestionStateName;
    
    private Integer respCount;
    private List<AttachVO> attachments;
    private List<ViewRespQuestion> responses;
    private List<AgeLevelVO> ageLevels;
    
    public ViewAskQuestion() {
    	askQuestionPublic = false;
    	respCount = 0;
    }

	public Integer getAskQuestionId() {
		return askQuestionId;
	}

	public void setAskQuestionId(Integer askQuestionId) {
		this.askQuestionId = askQuestionId;
	}

	public Integer getAskquestionTypeId() {
		return askquestionTypeId;
	}

	public void setAskquestionTypeId(Integer askquestionTypeId) {
		this.askquestionTypeId = askquestionTypeId;
	}

	public Integer getAskquestionStateId() {
		return askquestionStateId;
	}

	public void setAskquestionStateId(Integer askquestionStateId) {
		this.askquestionStateId = askquestionStateId;
	}

	public String getAskQuestionTitle() {
		return askQuestionTitle;
	}

	public void setAskQuestionTitle(String askQuestionTitle) {
		this.askQuestionTitle = askQuestionTitle;
	}

	public String getAskQuestionContent() {
		return askQuestionContent;
	}

	public void setAskQuestionContent(String askQuestionContent) {
		this.askQuestionContent = askQuestionContent;
	}

	public Timestamp getAskQuestionTime() {
		return askQuestionTime;
	}

	public void setAskQuestionTime(Timestamp askQuestionTime) {
		this.askQuestionTime = askQuestionTime;
	}

	public Integer getAskQuestionSelect() {
		return askQuestionSelect;
	}

	public void setAskQuestionSelect(Integer askQuestionSelect) {
		this.askQuestionSelect = askQuestionSelect;
	}

	public Integer getAskQuestionRewardIntegral() {
		return askQuestionRewardIntegral;
	}

	public void setAskQuestionRewardIntegral(Integer askQuestionRewardIntegral) {
		this.askQuestionRewardIntegral = askQuestionRewardIntegral;
	}

	public Boolean getAskQuestionPublic() {
		return askQuestionPublic;
	}

	public void setAskQuestionPublic(Boolean askQuestionPublic) {
		this.askQuestionPublic = askQuestionPublic;
	}

	public String getAskName() {
		return askName;
	}

	public void setAskName(String askName) {
		this.askName = askName;
	}

	public String getAnswerName() {
		return answerName;
	}

	public void setAnswerName(String answerName) {
		this.answerName = answerName;
	}

	public String getAskQuestionTypeName() {
		return askQuestionTypeName;
	}

	public void setAskQuestionTypeName(String askQuestionTypeName) {
		this.askQuestionTypeName = askQuestionTypeName;
	}

	public String getAskQuestionStateName() {
		return askQuestionStateName;
	}

	public void setAskQuestionStateName(String askQuestionStateName) {
		this.askQuestionStateName = askQuestionStateName;
	}

	public Integer getRespCount() {
		return respCount;
	}

	public void setRespCount(Integer respCount) {
		this.respCount = respCount;
	}

	public List<AttachVO> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<AttachVO> attachments) {
		this.attachments = attachments;
	}

	public List<ViewRespQuestion> getResponses() {
		return responses;
	}

	public void setResponses(List<ViewRespQuestion> responses) {
		this.responses = responses;
	}

	public Integer getRespUserId() {
		return respUserId;
	}

	public void setRespUserId(Integer respUserId) {
		this.respUserId = respUserId;
	}

	public Integer getAskUserId() {
		return askUserId;
	}

	public void setAskUserId(Integer askUserId) {
		this.askUserId = askUserId;
	}

	public List<AgeLevelVO> getAgeLevels() {
		return ageLevels;
	}

	public void setAgeLevels(List<AgeLevelVO> ageLevels) {
		this.ageLevels = ageLevels;
	}

	public Integer getAskQuestionReadCount() {
		return askQuestionReadCount;
	}

	public void setAskQuestionReadCount(Integer askQuestionReadCount) {
		this.askQuestionReadCount = askQuestionReadCount;
	}

	public String getAskUserPhotoUrl() {
		return askUserPhotoUrl;
	}

	public void setAskUserPhotoUrl(String askUserPhotoUrl) {
		this.askUserPhotoUrl = askUserPhotoUrl;
	}
	
}
