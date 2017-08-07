package edu.iasd.pojo;
// default package


import java.util.HashSet;
import java.util.Set;


/**
 * Module entity. @author MyEclipse Persistence Tools
 */

public class Module  implements java.io.Serializable {


    // Fields    

     private Integer moduleId;
     private ModuleType moduleType;
     //private Module module;
     private ModuleState moduleState;
     private School school;
     private String moduleNumber;
     private String moduleName;
     private Boolean moduleIsShow;
     private Integer moduleSerial;
     private Integer parentModuleId;
     private String moduleEnName;
	 private String moduleUrl;
     private Set modules = new HashSet(0);
     private Set articles = new HashSet(0);


    // Constructors

    /** default constructor */
    public Module() {
    }


	public Integer getModuleId() {
		return moduleId;
	}


	public void setModuleId(Integer moduleId) {
		this.moduleId = moduleId;
	}


	public ModuleType getModuleType() {
		return moduleType;
	}


	public void setModuleType(ModuleType moduleType) {
		this.moduleType = moduleType;
	}


	public ModuleState getModuleState() {
		return moduleState;
	}


	public void setModuleState(ModuleState moduleState) {
		this.moduleState = moduleState;
	}


	public School getSchool() {
		return school;
	}


	public void setSchool(School school) {
		this.school = school;
	}


	public Boolean getModuleIsShow() {
		return moduleIsShow;
	}


	public void setModuleIsShow(Boolean moduleIsShow) {
		this.moduleIsShow = moduleIsShow;
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
	
	
	public Integer getModuleSerial() {
		return moduleSerial;
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

	public void setModuleSerial(Integer moduleSerial) {
		this.moduleSerial = moduleSerial;
	}

	public Integer getParentModuleId() {
		return parentModuleId;
	}

	public void setParentModuleId(Integer parentModuleId) {
		this.parentModuleId = parentModuleId;
	}


	public Set getModules() {
		return modules;
	}


	public void setModules(Set modules) {
		this.modules = modules;
	}


	public Set getArticles() {
		return articles;
	}


	public void setArticles(Set articles) {
		this.articles = articles;
	}

	/** minimal constructor */
}