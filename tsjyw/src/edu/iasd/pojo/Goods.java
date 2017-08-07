package edu.iasd.pojo;
// default package

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;


/**
 * Goods entity. @author MyEclipse Persistence Tools
 */

public class Goods  implements Serializable {


	public static final int HOT_BUY = 1;
	public static final int RECENT_EXCHANGE = 2;
	public static final int HOT_EXCHANGE = 3;
    // Fields    
	 
     private Integer goodsId;
	 private GoodsState goodsState;
     private String goodsIntro;
     private String goodsImagaPath;
	 private Float goodsPrice;
     private Integer goodsCount;
     private Integer goodsLimitNumber;
     private Integer goodsSerial;
     private String goodsName;
     private Business business;
     private Timestamp exchangeStartTime;
     private Timestamp exchangeStopTime;
     private Set goodsType = new HashSet(0);

     private Set exchangeRecords = new HashSet(0);
    // Constructors




	/** default constructor */
    public Goods() {
    }
   

	public Integer getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}


	public GoodsState getGoodsState() {
		return goodsState;
	}

	public void setGoodsState(GoodsState goodsState) {
		this.goodsState = goodsState;
	}

	public String getGoodsIntro() {
		return goodsIntro;
	}

	public void setGoodsIntro(String goodsIntro) {
		this.goodsIntro = goodsIntro;
	}

	public String getGoodsImagaPath() {
		return goodsImagaPath;
	}

	public void setGoodsImagaPath(String goodsImagaPath) {
		this.goodsImagaPath = goodsImagaPath;
	}

	public Float getGoodsPrice() {
		return goodsPrice;
	}

	public void setGoodsPrice(Float goodsPrice) {
		this.goodsPrice = goodsPrice;
	}

	public Integer getGoodsCount() {
		return goodsCount;
	}

	public void setGoodsCount(Integer goodsCount) {
		this.goodsCount = goodsCount;
	}

	public Integer getGoodsLimitNumber() {
		return goodsLimitNumber;
	}

	public void setGoodsLimitNumber(Integer goodsLimitNumber) {
		this.goodsLimitNumber = goodsLimitNumber;
	}

	public Integer getGoodsSerial() {
		return goodsSerial;
	}

	public void setGoodsSerial(Integer goodsSerial) {
		this.goodsSerial = goodsSerial;
	}

		public String getGoodsName() {
			return goodsName;
		}

		public void setGoodsName(String goodsName) {
			this.goodsName = goodsName;
		}

		public Timestamp getExchangeStartTime() {
			return exchangeStartTime;
		}

		public void setExchangeStartTime(Timestamp exchangeStartTime) {
			this.exchangeStartTime = exchangeStartTime;
		}

		public Timestamp getExchangeStopTime() {
			return exchangeStopTime;
		}

		public void setExchangeStopTime(Timestamp exchangeStopTime) {
			this.exchangeStopTime = exchangeStopTime;
		}
		public Business getBusiness() {
			return business;
		}


		public Set getGoodsType() {
			return goodsType;
		}


		public void setGoodsType(Set goodsType) {
			this.goodsType = goodsType;
		}


		public void setBusiness(Business business) {
			this.business = business;
		}
    // Property accessors


		public Set getExchangeRecords() {
			return exchangeRecords;
		}


		public void setExchangeRecords(Set exchangeRecords) {
			this.exchangeRecords = exchangeRecords;
		}



}