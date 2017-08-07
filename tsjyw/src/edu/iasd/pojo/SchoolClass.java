package edu.iasd.pojo;
// default package

import java.util.HashSet;
import java.util.Set;


/**
 * ClassGrage entity. @author MyEclipse Persistence Tools
 */

public class SchoolClass  implements java.io.Serializable {


    // Fields    

     private Integer schoolClassId;
     private Grade grade;
     private String schoolClassName;
     
     
     
     private Set courseTeachers = new HashSet(0);
     private Set students = new HashSet(0);


    // Constructors

    /** default constructor */
    public SchoolClass() {
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



	public Grade getGrade() {
		return grade;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}

	public Set getCourseTeachers() {
		return courseTeachers;
	}

	public void setCourseTeachers(Set courseTeachers) {
		this.courseTeachers = courseTeachers;
	}

	public Set getStudents() {
		return students;
	}

	public void setStudents(Set students) {
		this.students = students;
	}

   
    // Property accessors
}