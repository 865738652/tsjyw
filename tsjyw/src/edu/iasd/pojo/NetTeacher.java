package edu.iasd.pojo;
// default package

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


/**
 * NetTeacher entity. @author MyEclipse Persistence Tools
 */

public class NetTeacher  implements Serializable {


    // Fields    

     private Integer netTeacherId;
     private String netTeacherName;
     private String netTeacherIntro;
     private Set netCourses = new HashSet(0);


    // Constructors

    /** default constructor */
    public NetTeacher() {
    }

	/** minimal constructor */
    public NetTeacher(String netTeacherName, String netTeacherIntro) {
        this.netTeacherName = netTeacherName;
        this.netTeacherIntro = netTeacherIntro;
    }
    
    /** full constructor */
    public NetTeacher(String netTeacherName, String netTeacherIntro, Set netCourses) {
        this.netTeacherName = netTeacherName;
        this.netTeacherIntro = netTeacherIntro;
        this.netCourses = netCourses;
    }

   
    // Property accessors

    public Integer getNetTeacherId() {
        return this.netTeacherId;
    }
    
    public void setNetTeacherId(Integer netTeacherId) {
        this.netTeacherId = netTeacherId;
    }

    public String getNetTeacherName() {
        return this.netTeacherName;
    }
    
    public void setNetTeacherName(String netTeacherName) {
        this.netTeacherName = netTeacherName;
    }

    public String getNetTeacherIntro() {
        return this.netTeacherIntro;
    }
    
    public void setNetTeacherIntro(String netTeacherIntro) {
        this.netTeacherIntro = netTeacherIntro;
    }

    public Set getNetCourses() {
        return this.netCourses;
    }
    
    public void setNetCourses(Set netCourses) {
        this.netCourses = netCourses;
    }
   








}