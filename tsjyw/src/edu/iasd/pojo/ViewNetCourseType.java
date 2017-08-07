package edu.iasd.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name = "v_netcoursetype")
public class ViewNetCourseType {
	private static final long serialVersionUID = -7674269980281525370L;
	
	@Id
	@Column 
	private Integer netCourseTypeId;
	
	@Column
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}

