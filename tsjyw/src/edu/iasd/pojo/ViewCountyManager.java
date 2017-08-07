package edu.iasd.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "v_countymanager")
public class ViewCountyManager extends ViewUserBase {
	private static final long serialVersionUID = -7674269980281525368L;
	
	@Column
    private Integer countyId;
	
	@Column
    private String countyName;
	
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
}
