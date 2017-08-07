package edu.iasd.form;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import edu.iasd.pojo.ApplyState;

public class ApplyForm implements Serializable {
	private Integer applyId;
    private Integer applyStateId;
    private Integer userId;
    private Timestamp applyTime;
    private String applyIntro;
    private List<Integer> askQuestionTypes;
    private List<Integer> ageLevels;
    private String applyAddress;
    private Double applyAddrLng;
    private Double applyAddrLat;
    private String applyPCC;
    private String applyOpinion;
    
    public ApplyForm() {
    	applyTime = new Timestamp(System.currentTimeMillis());
    	applyStateId = ApplyState.APPLY_STATE_OPEN;
    }
    
	public String getApplyAddress() {
		return applyAddress;
	}

	public void setApplyAddress(String applyAddress) {
		this.applyAddress = applyAddress;
	}

	public Integer getApplyId() {
		return applyId;
	}
	public void setApplyId(Integer applyId) {
		this.applyId = applyId;
	}
	public Integer getApplyStateId() {
		return applyStateId;
	}
	public void setApplyStateId(Integer applyStateId) {
		this.applyStateId = applyStateId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Timestamp getApplyTime() {
		return applyTime;
	}
	public void setApplyTime(Timestamp applyTime) {
		this.applyTime = applyTime;
	}
	public String getApplyIntro() {
		return applyIntro;
	}
	public void setApplyIntro(String applyIntro) {
		this.applyIntro = applyIntro;
	}
	
	



	public Double getApplyAddrLng() {
		return applyAddrLng;
	}



	public void setApplyAddrLng(Double applyAddrLng) {
		this.applyAddrLng = applyAddrLng;
	}

	public Double getApplyAddrLat() {
		return applyAddrLat;
	}

	public void setApplyAddrLat(Double applyAddrLat) {
		this.applyAddrLat = applyAddrLat;
	}

	public String getApplyPCC() {
		return applyPCC;
	}

	public void setApplyPCC(String applyPCC) {
		this.applyPCC = applyPCC;
	}
	
	public String getApplyOpinion() {
		return applyOpinion;
	}

	public void setApplyOpinion(String applyOpinion) {
		this.applyOpinion = applyOpinion;
	}

	public List<Integer> getAskQuestionTypes() {
		return askQuestionTypes;
	}
	public void setAskQuestionTypes(List<Integer> askQuestionTypes) {
		this.askQuestionTypes = askQuestionTypes;
	}
	public List<Integer> getAgeLevels() {
		return ageLevels;
	}
	public void setAgeLevels(List<Integer> ageLevels) {
		this.ageLevels = ageLevels;
	}
}
