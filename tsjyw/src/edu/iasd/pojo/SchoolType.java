package edu.iasd.pojo;
// default package

import java.util.HashSet;
import java.util.Set;


/**
 * SchoolType entity. @author MyEclipse Persistence Tools
 */

public class SchoolType  implements java.io.Serializable {


    // Fields    

     private Integer schoolTypeId;
     private String schoolTypeName;
     
    // Constructors

    /** default constructor */
    public SchoolType() {
    }

	/** minimal constructor */
    public SchoolType(String schoolTypeName) {
        this.schoolTypeName = schoolTypeName;
    }
       
    // Property accessors

    public Integer getSchoolTypeId() {
        return this.schoolTypeId;
    }
    
    public void setSchoolTypeId(Integer schoolTypeId) {
        this.schoolTypeId = schoolTypeId;
    }

    public String getSchoolTypeName() {
        return this.schoolTypeName;
    }
    
    public void setSchoolTypeName(String schoolTypeName) {
        this.schoolTypeName = schoolTypeName;
    }
}