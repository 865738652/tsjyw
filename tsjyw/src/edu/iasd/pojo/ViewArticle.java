package edu.iasd.pojo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

public class ViewArticle implements Serializable {
	// Fields    

    private Integer articleId;
    private Integer moduleId;
    private Integer articleStateId;
    private Integer userId;
    private String articleTitle;
    private String articleNumber;
    private String articleContent;
    private String articleAbstract;
	private Timestamp articleTime;
    private Integer articleReadCount;
    private Boolean articleRecommend;
    private String moduleName;
    private String userName;
    private String articleStateName;
    private String imagePath;
    private Integer moduleTypeId;
    private Integer schoolId;
    private String articleKeyword;
    
    
	public Integer getArticleId() {
		return articleId;
	}
	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}
	public Integer getModuleId() {
		return moduleId;
	}
	public void setModuleId(Integer moduleId) {
		this.moduleId = moduleId;
	}
	public Integer getArticleStateId() {
		return articleStateId;
	}
	public void setArticleStateId(Integer articleStateId) {
		this.articleStateId = articleStateId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getArticleTitle() {
		return articleTitle;
	}
	public void setArticleTitle(String articleTitle) {
		this.articleTitle = articleTitle;
	}
	public String getArticleNumber() {
		return articleNumber;
	}
	public void setArticleNumber(String articleNumber) {
		this.articleNumber = articleNumber;
	}
	public String getArticleContent() {
		return articleContent;
	}
	public void setArticleContent(String articleContent) {
		this.articleContent = articleContent;
	}
	public Timestamp getArticleTime() {
		return articleTime;
	}
	public void setArticleTime(Timestamp articleTime) {
		this.articleTime = articleTime;
	}
	public Integer getArticleReadCount() {
		return articleReadCount;
	}
	public void setArticleReadCount(Integer articleReadCount) {
		this.articleReadCount = articleReadCount;
	}
	public Boolean getArticleRecommend() {
		return articleRecommend;
	}
	public void setArticleRecommend(Boolean articleRecommend) {
		this.articleRecommend = articleRecommend;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getArticleStateName() {
		return articleStateName;
	}
	public void setArticleStateName(String articleStateName) {
		this.articleStateName = articleStateName;
	}
	public String getArticleAbstract() {
		return articleAbstract;
	}
	public void setArticleAbstract(String articleAbstract) {
		this.articleAbstract = articleAbstract;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public Integer getModuleTypeId() {
		return moduleTypeId;
	}
	public void setModuleTypeId(Integer moduleTypeId) {
		this.moduleTypeId = moduleTypeId;
	}
	public Integer getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}
	public String getArticleKeyword() {
		return articleKeyword;
	}
	public void setArticleKeyword(String articleKeyword) {
		this.articleKeyword = articleKeyword;
	}
}
