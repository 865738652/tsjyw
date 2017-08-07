package edu.iasd.pojo;
// default package


import java.sql.Timestamp;


/**
 * ExchangerRecord entity. @author MyEclipse Persistence Tools
 */

public class ExchangerRecord  implements java.io.Serializable {


    // Fields    

     private Integer exchangeRecordId;
     private Goods goods;
     private User user;
     private ExchangeRecordState exchangeRecordState;
     private Timestamp exchangeTime;
     private Integer exchangeCount;
     private Float exchangePrice;
     private Timestamp orderTime;
     private String exchangeRecordSerialNum;

    // Constructors

    /** default constructor */
    public ExchangerRecord() {
    }


	public Integer getExchangeRecordId() {
		return exchangeRecordId;
	}


	public void setExchangeRecordId(Integer exchangeRecordId) {
		this.exchangeRecordId = exchangeRecordId;
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


	public Goods getGoods() {
		return goods;
	}


	public void setGoods(Goods goods) {
		this.goods = goods;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public ExchangeRecordState getExchangeRecordState() {
		return exchangeRecordState;
	}


	public void setExchangeRecordState(ExchangeRecordState exchangeRecordState) {
		this.exchangeRecordState = exchangeRecordState;
	}


	public String getExchangeRecordSerialNum() {
		return exchangeRecordSerialNum;
	}


	public void setExchangeRecordSerialNum(String exchangeRecordSerialNum) {
		this.exchangeRecordSerialNum = exchangeRecordSerialNum;
	}

    /** full constructor */
    
   
    // Property accessors

    







}