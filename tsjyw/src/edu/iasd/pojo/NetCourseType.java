package edu.iasd.pojo;
// default package

import java.util.HashSet;
import java.util.Set;


/**
 * NetCourseType entity. @author MyEclipse Persistence Tools
 */

public class NetCourseType  implements java.io.Serializable {


    // Fields    

     private Integer netCourseTypeId;
     private String netCourseTypeName;


    // Constructors

    /** default constructor */
    public NetCourseType() {
    }

	/** minimal constructor */
    public NetCourseType(String netCourseTypeName) {
        this.netCourseTypeName = netCourseTypeName;
    }
    
   
    // Property accessors

    public Integer getNetCourseTypeId() {
        return this.netCourseTypeId;
    }
    
    public void setNetCourseTypeId(Integer netCourseTypeId) {
        this.netCourseTypeId = netCourseTypeId;
    }

    public String getNetCourseTypeName() {
        return this.netCourseTypeName;
    }
    
    public void setNetCourseTypeName(String netCourseTypeName) {
        this.netCourseTypeName = netCourseTypeName;
    }


}