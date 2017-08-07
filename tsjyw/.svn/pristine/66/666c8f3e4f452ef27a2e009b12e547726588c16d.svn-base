package edu.iasd.form;

import edu.iasd.pojo.Teacher;

public class TeacherForm extends UserForm {
	private int schoolId;
	private int teacherStateId;
	private Boolean schoolMaster;
	
	public TeacherForm() {
		schoolMaster = false;
	}
	
	public int getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(int schoolId) {
		this.schoolId = schoolId;
	}
	public int getTeacherStateId() {
		return teacherStateId;
	}
	public void setTeacherStateId(int teacherStateId) {
		this.teacherStateId = teacherStateId;
	}
	
	public void fillTeacher(Teacher t) {
		t.setUserId(getUserId());
		t.setSchoolMaster(getSchoolMaster());
	}
	public Boolean getSchoolMaster() {
		return schoolMaster;
	}
	public void setSchoolMaster(Boolean schoolMaster) {
		this.schoolMaster = schoolMaster;
	}
}
