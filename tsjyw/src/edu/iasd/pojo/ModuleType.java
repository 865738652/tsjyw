package edu.iasd.pojo;
// default package

/**
 * ModuleType entity. @author MyEclipse Persistence Tools
 */

public class ModuleType  implements java.io.Serializable {

	public final static int MODULE_TYPE_ARTICLE = 1;
	public final static int MODULE_TYPE_PHOTO = 2;
	public final static int MODULE_TYPE_VIDEO = 3;
	public final static int MODULE_TYPE_LINK = 4;
	
    // Fields    

     private Integer moduleTypeId;
     private String moduleTypeName;
     
    // Constructors

    /** default constructor */
    public ModuleType() {
    }

	/** minimal constructor */
    public ModuleType(String moduleTypeName) {
        this.moduleTypeName = moduleTypeName;
    }
    
    // Property accessors

    public Integer getModuleTypeId() {
        return this.moduleTypeId;
    }
    
    public void setModuleTypeId(Integer moduleTypeId) {
        this.moduleTypeId = moduleTypeId;
    }

    public String getModuleTypeName() {
        return this.moduleTypeName;
    }
    
    public void setModuleTypeName(String moduleTypeName) {
        this.moduleTypeName = moduleTypeName;
    }

}