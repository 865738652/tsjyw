package edu.iasd.pojo;
// default package


import java.util.HashSet;
import java.util.Set;


/**
 * School entity. @author MyEclipse Persistence Tools
 */

public class School  implements java.io.Serializable {


    // Fields    

     private Integer schoolId;
     private SchoolType schoolType;
     private County county;
     private String schoolNumber;
     private String schoolName;
     private String schoolAddress;
     private String schoolIntroduction;
     private String schoolContactInformation;
     private String schoolLogo;    
          
     private Set schoolCourses = new HashSet(0);//ok
     private Set schoolManagers = new HashSet(0);
     private Set teachers = new HashSet(0);
     private Set grades = new HashSet(0);


    // Constructors

    /** default constructor */
    public School() {
    }

	/** minimal constructor */
    public School(SchoolType TSchooltype, County county, String schoolNumber, String schoolName, String schoolAddress) {
        this.schoolType = TSchooltype;
        this.county = county;
        this.schoolNumber = schoolNumber;
        this.schoolName = schoolName;
        this.schoolAddress = schoolAddress;
    }
    
    /** full constructor */
    public School(SchoolType TSchooltype, County county, String schoolNumber, String schoolName, String schoolAddress, String schoolIntroduction, String schoolContactInformation, Set TSchoolcourses, Set schoolManagers, Set teachers, Set grades) {
        this.schoolType = TSchooltype;
        this.county = county;
        this.schoolNumber = schoolNumber;
        this.schoolName = schoolName;
        this.schoolAddress = schoolAddress;
        this.schoolIntroduction = schoolIntroduction;
        this.schoolContactInformation = schoolContactInformation;
        this.schoolCourses = TSchoolcourses;
        this.schoolManagers = schoolManagers;
        this.teachers = teachers;
        this.grades = grades;
    }

	public Integer getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}

	public SchoolType getSchoolType() {
		return schoolType;
	}

	public void setSchoolType(SchoolType schoolType) {
		this.schoolType = schoolType;
	}

	public County getCounty() {
		return county;
	}

	public void setCounty(County county) {
		this.county = county;
	}

	public String getSchoolNumber() {
		return schoolNumber;
	}

	public void setSchoolNumber(String schoolNumber) {
		this.schoolNumber = schoolNumber;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public String getSchoolAddress() {
		return schoolAddress;
	}

	public void setSchoolAddress(String schoolAddress) {
		this.schoolAddress = schoolAddress;
	}

	public String getSchoolIntroduction() {
		return schoolIntroduction;
	}

	public void setSchoolIntroduction(String schoolIntroduction) {
		this.schoolIntroduction = schoolIntroduction;
	}

	public String getSchoolContactInformation() {
		return schoolContactInformation;
	}

	public void setSchoolContactInformation(String schoolContactInformation) {
		this.schoolContactInformation = schoolContactInformation;
	}

	

	public Set getSchoolCourses() {
		return schoolCourses;
	}

	public void setSchoolCourses(Set schoolCourses) {
		this.schoolCourses = schoolCourses;
	}

	public Set getSchoolManagers() {
		return schoolManagers;
	}

	public void setSchoolManagers(Set schoolManagers) {
		this.schoolManagers = schoolManagers;
	}

	public Set getTeachers() {
		return teachers;
	}

	public void setTeachers(Set teachers) {
		this.teachers = teachers;
	}

	public Set getGrades() {
		return grades;
	}

	public void setGrades(Set grades) {
		this.grades = grades;
	}

	public String getSchoolLogo() {
		return schoolLogo;
	}

	public void setSchoolLogo(String schoolLogo) {
		this.schoolLogo = schoolLogo;
	}
}