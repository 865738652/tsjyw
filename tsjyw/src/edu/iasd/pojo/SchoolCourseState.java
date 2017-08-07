package edu.iasd.pojo;
// default package

import java.util.HashSet;
import java.util.Set;


/**
 * SchoolCourseState entity. @author MyEclipse Persistence Tools
 */

public class SchoolCourseState  implements java.io.Serializable {


    // Fields    

     private Integer schoolCourseStateId;
     private String schoolCourseStateName;
     
    // Constructors

    /** default constructor */
    public SchoolCourseState() {
    }

	/** minimal constructor */
    public SchoolCourseState(String schoolCourseStateName) {
        this.schoolCourseStateName = schoolCourseStateName;
    }
   
    // Property accessors

    public Integer getSchoolCourseStateId() {
        return this.schoolCourseStateId;
    }
    
    public void setSchoolCourseStateId(Integer schoolCourseStateId) {
        this.schoolCourseStateId = schoolCourseStateId;
    }

    public String getSchoolCourseStateName() {
        return this.schoolCourseStateName;
    }
    
    public void setSchoolCourseStateName(String schoolCourseStateName) {
        this.schoolCourseStateName = schoolCourseStateName;
    }
}