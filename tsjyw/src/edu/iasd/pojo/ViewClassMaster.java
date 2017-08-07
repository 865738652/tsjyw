package edu.iasd.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "v_classmaster")
public class ViewClassMaster  extends ViewUserBase {
	
	private static final long serialVersionUID = -2979882902667512467L;
	
	@Column
	private Integer classMasterId;
	
	@Column
    private Integer schoolClassId;
	
	@Column
    private String schoolClassName;
	

	@Column
    private Integer gradeId;
	
	@Column
    private String gradeName;
	
	
	public Integer getClassMasterId() {
		return classMasterId;
	}

	public void setClassMasterId(Integer classMasterId) {
		this.classMasterId = classMasterId;
	}

	public Integer getSchoolClassId() {
		return schoolClassId;
	}

	public void setSchoolClassId(Integer schoolClassId) {
		this.schoolClassId = schoolClassId;
	}

	public String getSchoolClassName() {
		return schoolClassName;
	}

	public void setSchoolClassName(String schoolClassName) {
		this.schoolClassName = schoolClassName;
	}

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
	
	

}
