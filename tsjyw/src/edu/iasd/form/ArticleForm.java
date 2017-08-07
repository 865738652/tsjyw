package edu.iasd.form;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

public class ArticleForm implements Serializable {

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
    private List<AttachVO> pictures;
    private List<AttachVO> videos;
    private String articleKeyword;
    
    
    public String getArticleKeyword() {
		return articleKeyword;
	}
	public void setArticleKeyword(String articleKeyword) {
		this.articleKeyword = articleKeyword;
	}
	public ArticleForm() {
    	setArticleRecommend(false);
    }
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
	public List<AttachVO> getPictures() {
		return pictures;
	}
	public void setPictures(List<AttachVO> pictures) {
		this.pictures = pictures;
	}
	public List<AttachVO> getVideos() {
		return videos;
	}
	public void setVideos(List<AttachVO> videos) {
		this.videos = videos;
	}
	public String getArticleAbstract() {
		return articleAbstract;
	}
	public void setArticleAbstract(String articleAbstract) {
		this.articleAbstract = articleAbstract;
	}

}
