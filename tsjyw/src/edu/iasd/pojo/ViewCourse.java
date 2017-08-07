package edu.iasd.pojo;

public class ViewCourse implements java.io.Serializable {
	private Integer schoolCourseId;
	private String schoolCourseName;
	private Integer schoolId;
	private Integer schoolCourseStateId;
	private String schoolName;
	private String schoolCourseStateName;
	
	public Integer getSchoolCourseId() {
		return schoolCourseId;
	}
	public void setSchoolCourseId(Integer schoolCourseId) {
		this.schoolCourseId = schoolCourseId;
	}
	public String getSchoolCourseName() {
		return schoolCourseName;
	}
	public void setSchoolCourseName(String schoolCourseName) {
		this.schoolCourseName = schoolCourseName;
	}
	public Integer getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}
	public Integer getSchoolCourseStateId() {
		return schoolCourseStateId;
	}
	public void setSchoolCourseStateId(Integer schoolCourseStateId) {
		this.schoolCourseStateId = schoolCourseStateId;
	}
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	public String getSchoolCourseStateName() {
		return schoolCourseStateName;
	}
	public void setSchoolCourseStateName(String schoolCourseStateName) {
		this.schoolCourseStateName = schoolCourseStateName;
	}	
}
