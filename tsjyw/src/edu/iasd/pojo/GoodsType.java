package edu.iasd.pojo;
// default package

import java.util.HashSet;
import java.util.Set;


/**
 * GoodsType entity. @author MyEclipse Persistence Tools
 */

public class GoodsType  implements java.io.Serializable {


    // Fields    

     private Integer goodsTypeId;
     private GoodsType goodsType;
     private String goodsTypeName;
     private String goodsTypeNumber;
     private Integer goodsTypeSerial;
 	 private Integer parentGoodsTypeId;
     private Set goodses = new HashSet(0);
     private Set goodsTypes = new HashSet(0);


    // Constructors

    /** default constructor */
    public GoodsType() {
    }

    // Property accessors

    public Integer getGoodsTypeId() {
        return this.goodsTypeId;
    }
    
    public void setGoodsTypeId(Integer goodsTypeId) {
        this.goodsTypeId = goodsTypeId;
    }

    public GoodsType getGoodsType() {
        return this.goodsType;
    }
    
    public void setGoodsType(GoodsType goodsType) {
        this.goodsType = goodsType;
    }

    public String getGoodsTypeName() {
        return this.goodsTypeName;
    }
    
    public void setGoodsTypeName(String goodsTypeName) {
        this.goodsTypeName = goodsTypeName;
    }

    public String getGoodsTypeNumber() {
        return this.goodsTypeNumber;
    }
    
    public void setGoodsTypeNumber(String goodsTypeNumber) {
        this.goodsTypeNumber = goodsTypeNumber;
    }

    public Integer getGoodsTypeSerial() {
        return this.goodsTypeSerial;
    }
    
    public void setGoodsTypeSerial(Integer goodsTypeSerial) {
        this.goodsTypeSerial = goodsTypeSerial;
    }

    public Set getGoodses() {
        return this.goodses;
    }
    
    public void setGoodses(Set goodses) {
        this.goodses = goodses;
    }

    public Set getGoodsTypes() {
        return this.goodsTypes;
    }
    
    public void setGoodsTypes(Set goodsTypes) {
        this.goodsTypes = goodsTypes;
    }

	public Integer getParentGoodsTypeId() {
		return parentGoodsTypeId;
	}

	public void setParentGoodsTypeId(Integer parentGoodsTypeId) {
		this.parentGoodsTypeId = parentGoodsTypeId;
	}
   

    






}