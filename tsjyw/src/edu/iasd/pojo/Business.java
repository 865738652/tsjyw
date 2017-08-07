package edu.iasd.pojo;

import java.util.HashSet;
import java.util.Set;

public class Business implements java.io.Serializable{
	private Integer businessId;
    private String businessName;
    private String businessStr;//��ַ
    private String businessIntro;
    private String businessPhone;
    private String businessWeixin;
    private String businessQq;
    private String businessImgPath;
    
    Set goodses = new HashSet();
	
	public Integer getBusinessId() {
		return businessId;
	}
	public void setBusinessId(Integer businessId) {
		this.businessId = businessId;
	}
	public String getBusinessName() {
		return businessName;
	}
	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}
	public String getBusinessStr() {
		return businessStr;
	}
	public void setBusinessStr(String businessStr) {
		this.businessStr = businessStr;
	}
	public String getBusinessIntro() {
		return businessIntro;
	}
	public void setBusinessIntro(String businessIntro) {
		this.businessIntro = businessIntro;
	}
	public String getBusinessPhone() {
		return businessPhone;
	}
	public void setBusinessPhone(String businessPhone) {
		this.businessPhone = businessPhone;
	}
	public String getBusinessWeixin() {
		return businessWeixin;
	}
	public void setBusinessWeixin(String businessWeixin) {
		this.businessWeixin = businessWeixin;
	}
	public String getBusinessQq() {
		return businessQq;
	}
	public void setBusinessQq(String businessQq) {
		this.businessQq = businessQq;
	}
	public Set getGoodses() {
		return goodses;
	}
	public void setGoodses(Set goodses) {
		this.goodses = goodses;
	}
	public String getBusinessImgPath() {
		return businessImgPath;
	}
	public void setBusinessImgPath(String businessImgPath) {
		this.businessImgPath = businessImgPath;
	}
    
	
    
}
