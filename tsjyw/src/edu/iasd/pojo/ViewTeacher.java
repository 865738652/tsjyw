package edu.iasd.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * ViewTeacher entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "v_teacher")
public class ViewTeacher  extends ViewUserBase {
	private static final long serialVersionUID = -7674269980281525369L;
	
    // Fields  
	@Column
	Integer schoolId;
	
	@Column
    private Integer teacherStateId;
	
	@Column
    private String teacherStateName;
	
	@Column
    private String schoolName;
	
	@Column
	private Boolean schoolMaster;

    // Constructors

    /** default constructor */
    public ViewTeacher() {
    }
    
	public Integer getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}

	public Integer getTeacherStateId() {
		return teacherStateId;
	}

	public void setTeacherStateId(Integer teacherStateId) {
		this.teacherStateId = teacherStateId;
	}

	public String getTeacherStateName() {
		return teacherStateName;
	}

	public void setTeacherStateName(String teacherStateName) {
		this.teacherStateName = teacherStateName;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public Boolean getSchoolMaster() {
		return schoolMaster;
	}

	public void setSchoolMaster(Boolean schoolMaster) {
		this.schoolMaster = schoolMaster;
	}    
}