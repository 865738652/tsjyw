package edu.iasd.pojo;
import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="v_comment")
public class ViewComment extends ViewUserBase{

	private static final long serialVersionUID = -7674269980281525366L;
	@Id
	@Column
	private Integer commentId;
	
	@Column
	private Timestamp commentTime;
	
	@Column
	private String commentContent;
	
	@Column
	private Integer commentTypeId;
	
	@Column
	private Integer articleId;
	
	@Column
	private Integer userId;
	
	
	
	
	

	public Integer getArticleId() {
		return articleId;
	}

	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getCommentId() {
		return commentId;
	}

	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}

	public Timestamp getCommentTime() {
		return commentTime;
	}

	public void setCommentTime(Timestamp commentTime) {
		this.commentTime = commentTime;
	}

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	public Integer getCommentTypeId() {
		return commentTypeId;
	}

	public void setCommentTypeId(Integer commentTypeId) {
		this.commentTypeId = commentTypeId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	
	
}
