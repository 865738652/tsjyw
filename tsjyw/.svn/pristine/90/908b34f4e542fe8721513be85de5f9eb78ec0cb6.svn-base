package edu.iasd.pojo;
// default package


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


/**
 * Teacher entity. @author MyEclipse Persistence Tools
 */

public class Teacher  implements Serializable{
	
	private static final long serialVersionUID = -2675841143916303780L;
	public final static int TEACHER_STATE_NORMAL = 1;
	public final static int TEACHER_STATE_RETAIL = 2;


    // Fields   
	 private Integer userId;
	 private Boolean schoolMaster;
	 private User user;
     private School school;
     private TeacherState teacherState;
     //private Set schoolMasters = new HashSet(0);
     //private Set gradeMasters = new HashSet(0);
     private Set courseTeachers = new HashSet(0);


    // Constructors

    /** default constructor */
    public Teacher() {
    }

	public Boolean getSchoolMaster() {
		return schoolMaster;
	}

	public void setSchoolMaster(Boolean schoolMaster) {
		this.schoolMaster = schoolMaster;
	}

	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

	public TeacherState getTeacherState() {
		return teacherState;
	}

	public void setTeacherState(TeacherState teacherState) {
		this.teacherState = teacherState;
	}

	public Set getCourseTeachers() {
		return courseTeachers;
	}

	public void setCourseTeachers(Set courseTeachers) {
		this.courseTeachers = courseTeachers;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
}