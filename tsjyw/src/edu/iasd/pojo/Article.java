package edu.iasd.pojo;
// default package


import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;


/**
 * Article entity. @author MyEclipse Persistence Tools
 */

public class Article  implements java.io.Serializable {


    // Fields    

     private Integer articleId;
     private Module module;
     private ArticleState articleState;
     private User user;
     private String articleTitle;
     private String articleNumber;
     private String articleContent;
     private String articleAbstract;
     private Timestamp articleTime;
     private Integer articleReadCount;
     private Boolean articleRecommend;
     private Integer moduleTypeId;
     private Integer schoolId;
     private Set images = new HashSet(0);
     private Set videos = new HashSet(0);
     private String articleKeyword;

    // Constructors

    /** default constructor */
    public Article() {
    }

	/** minimal constructor */
    public Article(Module module, ArticleState articleState, User user, String articleTitle, String articleNumber, String articleKeyword,String articleContent, Timestamp articleTime, Boolean articleRecommend) {
        this.module = module;
        this.articleState = articleState;
        this.user = user;
        this.articleTitle = articleTitle;
        this.articleNumber = articleNumber;
        this.articleContent = articleContent;
        this.articleTime = articleTime;
        this.articleRecommend = articleRecommend;
        this.articleKeyword=articleKeyword;
    }
    
    /** full constructor */
    public Article(Module module, ArticleState articleState, User user, String articleTitle, String articleNumber,String articleKeyword, String articleContent, Timestamp articleTime, Integer articleReadCount, Boolean articleRecommend, Set images, Set videos) {
        this.module = module;
        this.articleState = articleState;
        this.user = user;
        this.articleTitle = articleTitle;
        this.articleNumber = articleNumber;
        this.articleContent = articleContent;
        this.articleTime = articleTime;
        this.articleReadCount = articleReadCount;
        this.articleRecommend = articleRecommend;
        this.images = images;
        this.videos = videos;
        this.articleKeyword=articleKeyword;
    }

	public Integer getArticleId() {
		return articleId;
	}

	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}

	public Module getModule() {
		return module;
	}

	public void setModule(Module module) {
		this.module = module;
	}

	public ArticleState getArticleState() {
		return articleState;
	}

	public void setArticleState(ArticleState articleState) {
		this.articleState = articleState;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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

	public Set getImages() {
		return images;
	}

	public void setImages(Set images) {
		this.images = images;
	}

	public Set getVideos() {
		return videos;
	}

	public void setVideos(Set videos) {
		this.videos = videos;
	}

	public String getArticleAbstract() {
		return articleAbstract;
	}

	public void setArticleAbstract(String articleAbstract) {
		this.articleAbstract = articleAbstract;
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

	public void setArticleKeyword(String articleKeyword) {
		this.articleKeyword = articleKeyword;
	}

	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}
	public String getArticleKeyword() {
		return articleKeyword;
	}
}