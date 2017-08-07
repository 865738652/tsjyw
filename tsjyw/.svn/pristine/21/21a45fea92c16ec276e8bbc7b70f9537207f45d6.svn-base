package edu.iasd.form;

import edu.iasd.pojo.ExchangeRecordState;

public class ExchangeRecordStateForm {
	private Integer exchangeRecordStateId;
    private String exchangeRecordStateName;
	public Integer getExchangeRecordStateId() {
		return exchangeRecordStateId;
	}
	public void setExchangeRecordStateId(Integer exchangeRecordStateId) {
		this.exchangeRecordStateId = exchangeRecordStateId;
	}
	public String getExchangeRecordStateName() {
		return exchangeRecordStateName;
	}
	public void setExchangeRecordStateName(String exchangeRecordStateName) {
		this.exchangeRecordStateName = exchangeRecordStateName;
	}
    public ExchangeRecordStateForm transition(ExchangeRecordState a)
    {
    	this.exchangeRecordStateId = a.getExchangeRecordStateId();
    	this.exchangeRecordStateName = a.getExchangeRecordStateName();
    	return this;
    }
}
