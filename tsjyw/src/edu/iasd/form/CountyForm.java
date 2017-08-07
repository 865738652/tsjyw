package edu.iasd.form;

import java.io.Serializable;

public class CountyForm implements Serializable{
	
	
	private Integer countyId;
    private String countyName;
	public Integer getCountyId() {
		return countyId;
	}
	
	public String getCountyName() {
		return countyName;
	}
	public void setCountyName(String countyName) {
		this.countyName = countyName;
	}

	public void setCountyId(Integer countyId) {
		this.countyId = countyId;
	}

	
	

}
