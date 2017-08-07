package edu.iasd.pojo;

public class ViewCounty implements java.io.Serializable {
	private Integer countyId;
    private String countyName;
    private Integer schoolCount;
    
	public Integer getCountyId() {
		return countyId;
	}
	public void setCountyId(Integer countyId) {
		this.countyId = countyId;
	}
	public String getCountyName() {
		return countyName;
	}
	public void setCountyName(String countyName) {
		this.countyName = countyName;
	}
	public Integer getSchoolCount() {
		return schoolCount;
	}
	public void setSchoolCount(Integer schoolCount) {
		this.schoolCount = schoolCount;
	}    
}
