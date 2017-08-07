package edu.iasd.form;

import java.io.Serializable;

import edu.iasd.pojo.NetCourseType;

public class NetCourseTypeForm implements Serializable {
	private Integer netCourseTypeId;
	private String netCourseTypeName;
	
	public Integer getNetCourseTypeId() {
		return netCourseTypeId;
	}
	public void setNetCourseTypeId(Integer netCourseTypeId) {
		this.netCourseTypeId = netCourseTypeId;
	}
	public String getNetCourseTypeName() {
		return netCourseTypeName;
	}
	public void setNetCourseTypeName(String netCourseTypeName) {
		this.netCourseTypeName = netCourseTypeName;
	}
	public static NetCourseType convert(NetCourseType netCourseType) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
