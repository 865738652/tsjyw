package edu.iasd.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "v_schoolmanager")
public class ViewSchoolManager extends ViewUserBase {
	private static final long serialVersionUID = -7674269980281525367L;
	
	@Column
    private Integer schoolId;
	
	@Column
    private String schoolName;
	
	public Integer getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
}
