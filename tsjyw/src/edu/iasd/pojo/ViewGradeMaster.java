package edu.iasd.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "v_grademaster")
public class ViewGradeMaster  extends ViewUserBase {
	
	private static final long serialVersionUID = -2979882902667512467L;
	
	@Column
	private Integer gradeMasterId;
	
	@Column
    private Integer gradeId;
	
	@Column
    private String gradeName;     
	
	public Integer getGradeId() {
		return gradeId;
	}
	public void setGradeId(Integer gradeId) {
		this.gradeId = gradeId;
	}
	public String getGradeName() {
		return gradeName;
	}
	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}
	public Integer getGradeMasterId() {
		return gradeMasterId;
	}
	public void setGradeMasterId(Integer gradeMasterId) {
		this.gradeMasterId = gradeMasterId;
	}
	
     
     
}