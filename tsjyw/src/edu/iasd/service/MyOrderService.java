package edu.iasd.service;

import com.demonstration.hibernate.dao.support.Page;

import edu.iasd.pojo.ViewExchangeRecord;

public interface MyOrderService {
	
	public Page getAllMyOrder(int pageNumber,int pageSize);
	
	public ViewExchangeRecord getMyOeder(int exchangeRecordId);
	
	public Boolean cancelMyOrder(int exchangeRecordId);
}
