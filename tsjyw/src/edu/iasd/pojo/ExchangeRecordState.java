package edu.iasd.pojo;
// default package

import java.util.HashSet;
import java.util.Set;


/**
 * ExchangeRecordState entity. @author MyEclipse Persistence Tools
 */

public class ExchangeRecordState  implements java.io.Serializable {


    // Fields    
	
	public static final int waitbusinessConfirem = 1;
	public static final int businessConfirem = 2;
	public static final int UserConfirem = 3;
	public static final int UserApplyCancel = 4;
	public static final int businessCancel = 5;
	
     private Integer exchangeRecordStateId;
     private String exchangeRecordStateName;
     private Set exchangerRecords = new HashSet(0);


    // Constructors

    /** default constructor */
    public ExchangeRecordState() {
    }

	/** minimal constructor */
    public ExchangeRecordState(String exchangeRecordStateName) {
        this.exchangeRecordStateName = exchangeRecordStateName;
    }
    
    /** full constructor */
    public ExchangeRecordState(String exchangeRecordStateName, Set exchangerRecords) {
        this.exchangeRecordStateName = exchangeRecordStateName;
        this.exchangerRecords = exchangerRecords;
    }

   
    // Property accessors

    public Integer getExchangeRecordStateId() {
        return this.exchangeRecordStateId;
    }
    
    public void setExchangeRecordStateId(Integer exchangeRecordStateId) {
        this.exchangeRecordStateId = exchangeRecordStateId;
    }

    public String getExchangeRecordStateName() {
        return this.exchangeRecordStateName;
    }
    
    public void setExchangeRecordStateName(String exchangeRecordStateName) {
        this.exchangeRecordStateName = exchangeRecordStateName;
    }

    public Set getExchangerRecords() {
        return this.exchangerRecords;
    }
    
    public void setExchangerRecords(Set exchangerRecords) {
        this.exchangerRecords = exchangerRecords;
    }
   








}