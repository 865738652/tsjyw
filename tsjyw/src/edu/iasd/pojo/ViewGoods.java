package edu.iasd.pojo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import edu.iasd.form.GoodsTypeVo;

@Entity
@Table(name = "v_goods")
public class ViewGoods  implements Serializable {
	private static final long serialVersionUID = -7674269980281525366L;
	
	
	
    @Id
	@Column
	private Integer goodsId;
    
    @Transient
	private List<GoodsTypeVo> goodsTypes;
    
	
	@Column
	private Integer businessId;
	
	@Column
	private Integer goodsStateId;
	
	@Column
	private String goodsName;
	
	@Column
	private String goodsIntro;
	
	@Column
	private String goodsImagaPath;
	
	@Column
	private Float goodsPrice;
	
	@Column
	private Integer goodsCount;
	
	@Column
	private Integer goodsLimitNumber;
	
	@Column
	private Integer goodsSerial;
	
	@Column
	private Timestamp exchangeStartTime;
	
	@Column
	private Timestamp exchangeStopTime;
	
	@Column 
	private String goodsStateName;
	
	@Column
	private String businessName;

	@Column
	private Integer exchangeRecordCount;
	
	
	public Integer getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}
	
	public String getGoodsStateName() {
		return goodsStateName;
	}

	public void setGoodsStateName(String goodsStateName) {
		this.goodsStateName = goodsStateName;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public ViewGoods(){
		goodsTypes = new ArrayList<GoodsTypeVo>();		
	}
	


	public Integer getBusinessId() {
		return businessId;
	}

	public void setBusinessId(Integer businessId) {
		this.businessId = businessId;
	}


	public Integer getGoodsStateId() {
		return goodsStateId;
	}

	public void setGoodsStateId(Integer goodsStateId) {
		this.goodsStateId = goodsStateId;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<GoodsTypeVo> getGoodsTypes() {
		return goodsTypes;
	}

	public void setGoodsTypes(List<GoodsTypeVo> goodsTypes) {
		this.goodsTypes = goodsTypes;
	}

	public Integer getExchangeRecordCount() {
		return exchangeRecordCount;
	}

	public void setExchangeRecordCount(Integer exchangeRecordCount) {
		this.exchangeRecordCount = exchangeRecordCount;
	}
	
	
	
	
}
