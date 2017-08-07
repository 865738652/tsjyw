package edu.iasd.pojo;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import edu.iasd.form.AgeLevelVO;
import edu.iasd.form.AskQuestionTypeVO;

@Entity
@Table(name = "v_apply")
public class ViewApply {
	private static final long serialVersionUID = -7674269980281525365L;
	
	@Transient
	private List<AgeLevelVO> ageLevels;
	
	@Transient
	private List<AskQuestionTypeVO> askQuestionTypes;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer applyId;
	
	@Column
    private Integer applyStateId;
	
	@Column
    private String applyStateName;
	
	@Column
    private Timestamp applyTime; 
	
	@Column
    private String applyIntro;	
	
	@Column
    private String applyOpinion;	
	
	@Column
	private Integer userId;
	
	@Column
    private String userName;
	
	@Column
    private String userNickname;
	
	@Column
    private Integer userGender;
	
	@Column
    private String userAccount;
	
	@Column
    private String userPassword;
	
	@Column
    private Timestamp userbirthday;
	
	@Column
    private String userWeixin;
	
	@Column
    private String userQq;
	
	@Column
    private String userPhone;
	
	@Column
    private String userEmail;
	
	@Column
    private Integer userIntegral;
	
	@Column
	private String userIdentityCode;
	
	@Column
	private String userPhotoUrl;
	
	@Column
	private String userCode;
	
	@Column
	private String applyAddress;
	
	@Column
	private Double applyAddrLng;
	
	@Column
	private Double applyAddrLat;
	
	@Column
	 private String applyPCC;


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

	public String getApplyStateName() {
		return applyStateName;
	}

	public void setApplyStateName(String applyStateName) {
		this.applyStateName = applyStateName;
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

	public String getApplyOpinion() {
		return applyOpinion;
	}

	public void setApplyOpinion(String applyOpinion) {
		this.applyOpinion = applyOpinion;
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

	public List<AgeLevelVO> getAgeLevels() {
		return ageLevels;
	}

	public void setAgeLevels(List<AgeLevelVO> ageLevels) {
		this.ageLevels = ageLevels;
	}

	public List<AskQuestionTypeVO> getAskQuestionTypes() {
		return askQuestionTypes;
	}

	public void setAskQuestionTypes(List<AskQuestionTypeVO> askQuestionTypes) {
		this.askQuestionTypes = askQuestionTypes;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserNickname() {
		return userNickname;
	}

	public void setUserNickname(String userNickname) {
		this.userNickname = userNickname;
	}

	public Integer getUserGender() {
		return userGender;
	}

	public void setUserGender(Integer userGender) {
		this.userGender = userGender;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public Timestamp getUserbirthday() {
		return userbirthday;
	}

	public void setUserbirthday(Timestamp userbirthday) {
		this.userbirthday = userbirthday;
	}

	public String getUserWeixin() {
		return userWeixin;
	}

	public void setUserWeixin(String userWeixin) {
		this.userWeixin = userWeixin;
	}

	public String getUserQq() {
		return userQq;
	}

	public void setUserQq(String userQq) {
		this.userQq = userQq;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public Integer getUserIntegral() {
		return userIntegral;
	}

	public void setUserIntegral(Integer userIntegral) {
		this.userIntegral = userIntegral;
	}
	
	public String getUserIdentityCode() {
		return userIdentityCode;
	}

	public void setUserIdentityCode(String userIdentityCode) {
		this.userIdentityCode = userIdentityCode;
	}
	
	public String getUserPhotoUrl() {
		return userPhotoUrl;
	}

	public void setUserPhotoUrl(String userPhotoUrl) {
		this.userPhotoUrl = userPhotoUrl;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

}
