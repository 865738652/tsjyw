package edu.iasd.pojo;
// default package




/**
 * CourseTeacher entity. @author MyEclipse Persistence Tools
 */

public class CourseTeacher  implements java.io.Serializable {


    // Fields    

     private Integer courseTeacherId;
     private SchoolClass schoolClass;
     private SchoolCourse schoolCourse;
     private Teacher teacher;


    // Constructors

    /** default constructor */
    public CourseTeacher() {
    }

	public Integer getCourseTeacherId() {
		return courseTeacherId;
	}

	public void setCourseTeacherId(Integer courseTeacherId) {
		this.courseTeacherId = courseTeacherId;
	}

	public SchoolClass getSchoolClass() {
		return schoolClass;
	}

	public void setSchoolClass(SchoolClass schoolClass) {
		this.schoolClass = schoolClass;
	}

	public SchoolCourse getSchoolCourse() {
		return schoolCourse;
	}

	public void setSchoolCourse(SchoolCourse schoolCourse) {
		this.schoolCourse = schoolCourse;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
}