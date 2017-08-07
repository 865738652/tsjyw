package edu.iasd.pojo;

import java.io.Serializable;
import java.util.List;

public class ViewModule  implements Serializable {
	private Integer moduleId;
    private Integer moduleTypeId;
    private Integer moduleStateId;
    private Integer parentModuleId;
    private Integer schoolId; 
    private String moduleNumber;
    private String moduleName;
    private Boolean moduleIsShow;
    private Integer moduleSerial;
    private String moduleTypeName;
    private String moduleStateName;
    private String moduleEnName;
	private String moduleUrl;
	private List<ViewModule> childrenModule; // 用于页面显示导航菜单
    
	public Integer getModuleId() {
		return moduleId;
	}
	public void setModuleId(Integer moduleId) {
		this.moduleId = moduleId;
	}
	public Integer getModuleTypeId() {
		return moduleTypeId;
	}
	public void setModuleTypeId(Integer moduleTypeId) {
		this.moduleTypeId = moduleTypeId;
	}	
	public Integer getModuleStateId() {
		return moduleStateId;
	}
	public void setModuleStateId(Integer moduleStateId) {
		this.moduleStateId = moduleStateId;
	}
	public Integer getParentModuleId() {
		return parentModuleId;
	}
	public void setParentModuleId(Integer parentModuleId) {
		this.parentModuleId = parentModuleId;
	}
	public Integer getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}
	public String getModuleNumber() {
		return moduleNumber;
	}
	public void setModuleNumber(String moduleNumber) {
		this.moduleNumber = moduleNumber;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public Boolean getModuleIsShow() {
		return moduleIsShow;
	}
	public void setModuleIsShow(Boolean moduleIsShow) {
		this.moduleIsShow = moduleIsShow;
	}
	public Integer getModuleSerial() {
		return moduleSerial;
	}
	public void setModuleSerial(Integer moduleSerial) {
		this.moduleSerial = moduleSerial;
	}
	public String getModuleTypeName() {
		return moduleTypeName;
	}
	public void setModuleTypeName(String moduleTypeName) {
		this.moduleTypeName = moduleTypeName;
	}
	public String getModuleStateName() {
		return moduleStateName;
	}
	public void setModuleStateName(String moduleStateName) {
		this.moduleStateName = moduleStateName;
	}
	public String getModuleEnName() {
		return moduleEnName;
	}
	public void setModuleEnName(String moduleEnName) {
		this.moduleEnName = moduleEnName;
	}
	public String getModuleUrl() {
		return moduleUrl;
	}
	public void setModuleUrl(String moduleUrl) {
		this.moduleUrl = moduleUrl;
	}
	public List<ViewModule> getChildrenModule() {
		return childrenModule;
	}
	public void setChildrenModule(List<ViewModule> childrenModule) {
		this.childrenModule = childrenModule;
	}
}
