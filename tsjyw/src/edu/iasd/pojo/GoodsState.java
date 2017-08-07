package edu.iasd.pojo;
// default package

import java.util.HashSet;
import java.util.Set;


/**
 * GoodState entity. @author MyEclipse Persistence Tools
 */

public class GoodsState  implements java.io.Serializable {


    // Fields    
	public static final Integer GOODSSTATE_NORMAL = 1;
	public static final Integer GOODSSTATE_DISABLE = 2;
	
	
     private Integer goodsStateId;
     private String goodsStateName;


    // Constructors

    /** default constructor */
    public GoodsState() {
    }

	/** minimal constructor */
    public GoodsState(String goodsStateName) {
        this.goodsStateName = goodsStateName;
    }
   
    // Property accessors

    public Integer getGoodsStateId() {
        return this.goodsStateId;
    }
    
    public void setGoodsStateId(Integer goodsStateId) {
        this.goodsStateId = goodsStateId;
    }

    public String getGoodsStateName() {
        return this.goodsStateName;
    }
    
    public void setGoodsStateName(String goodsStateName) {
        this.goodsStateName = goodsStateName;
    }
}