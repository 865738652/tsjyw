package edu.iasd.pojo;
// default package


import java.util.HashSet;
import java.util.Set;


/**
 * Grade entity. @author MyEclipse Persistence Tools
 */

public class Grade  implements java.io.Serializable {


    // Fields    

     private Integer gradeId;
     private School school;
     private String gradeName;
     private Set gradeMasters = new HashSet(0);
     private Set schoolClasses = new HashSet(0);


    // Constructors

    /** default constructor */
    public Grade() {
    }

	/** minimal constructor */
    public Grade(School TSchool, String gradeName) {
        this.school = TSchool;
        this.gradeName = gradeName;
    }

	public Integer getGradeId() {
		return gradeId;
	}

	public void setGradeId(Integer gradeId) {
		this.gradeId = gradeId;
	}

	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

	public String getGradeName() {
		return gradeName;
	}

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}

	public Set getGradeMasters() {
		return gradeMasters;
	}

	public void setGradeMasters(Set gradeMasters) {
		this.gradeMasters = gradeMasters;
	}

	public Set getSchoolClasses() {
		return schoolClasses;
	}

	public void setSchoolClasses(Set schoolClasses) {
		this.schoolClasses = schoolClasses;
	}
}