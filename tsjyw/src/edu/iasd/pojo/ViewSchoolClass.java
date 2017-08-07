package edu.iasd.pojo;

public class ViewSchoolClass implements java.io.Serializable{
	private Integer schoolClassId;
    private Integer gradeId;
    private Integer schoolId;
    private String schoolClassName;
    private String gradeName;
    private String schoolName;    
    
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
	public Integer getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}
	
	public String getGradeName() {
		return gradeName;
	}
	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
}
