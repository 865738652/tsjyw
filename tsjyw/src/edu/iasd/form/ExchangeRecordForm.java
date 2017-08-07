package edu.iasd.form;

import java.io.Serializable;
import java.sql.Timestamp;

public class ExchangeRecordForm implements Serializable{

	private static final long serialVersionUID = 455436837762002267L;
	private Integer exchangeRecordId;
	private Integer userId;
	private Integer goodsId;
	private Integer exchangeRecordStateId;
	private Timestamp exchangeTime;
	private Integer exchangeCount;
	private Float exchangePrice;
	private Timestamp orderTime;
	public Integer getExchangeRecordId() {
		return exchangeRecordId;
	}
	public void setExchangeRecordId(Integer exchangeRecordId) {
		this.exchangeRecordId = exchangeRecordId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}
	public Integer getExchangeRecordStateId() {
		return exchangeRecordStateId;
	}
	public void setExchangeRecordStateId(Integer exchangeRecordStateId) {
		this.exchangeRecordStateId = exchangeRecordStateId;
	}
	public Timestamp getExchangeTime() {
		return exchangeTime;
	}
	public void setExchangeTime(Timestamp exchangeTime) {
		this.exchangeTime = exchangeTime;
	}
	public Integer getExchangeCount() {
		return exchangeCount;
	}
	public void setExchangeCount(Integer exchangeCount) {
		this.exchangeCount = exchangeCount;
	}
	public Float getExchangePrice() {
		return exchangePrice;
	}
	public void setExchangePrice(Float exchangePrice) {
		this.exchangePrice = exchangePrice;
	}
	public Timestamp getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(Timestamp orderTime) {
		this.orderTime = orderTime;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
