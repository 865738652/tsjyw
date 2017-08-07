package edu.iasd.form;

import java.io.Serializable;

public class CommentTypeForm implements Serializable {
	
	private Integer commentTypeId;
	private String commentTypeName;
	public Integer getCommentTypeId() {
		return commentTypeId;
	}
	public void setCommentTypeId(Integer commentTypeId) {
		this.commentTypeId = commentTypeId;
	}
	public String getCommentTypeName() {
		return commentTypeName;
	}
	public void setCommentTypeName(String commentTypeName) {
		this.commentTypeName = commentTypeName;
	}
	
	

}
