package edu.iasd.service;

import java.util.List;

import com.demonstration.hibernate.dao.support.Page;

import edu.iasd.pojo.ExchangeRecordState;
import edu.iasd.pojo.ViewExchangeRecord;

public interface ExchangeRecordService {
	
	public Page getAllExchangeRecord(int pageNumber,int pageSize,Integer userId,String queryString);
	
	//��ȡ�ҵ�ȫ������
	public Page getMyExchangeRecord(int pageNumber,int pageSize,Integer userId,int stateType);
	
	//��ȡ�ҵ�ȫ���������в�ѯform
	public Page getMyExchangeRecordForSearchForm(int pageNumber,int pageSize,Integer userId,String queryString);
	
	
	public boolean cancelExchangeRecord(int exchangeRecordId) throws Exception;
	
	public boolean sureExchangeRecord(int exchangeRecordId) throws Exception;

	
	public boolean applyCancelExchangeRecord(int exchangeRecordId) throws Exception;
	public boolean sureMyExchangeRecord(int exchangeRecordId) throws Exception;
	
	
	
	public String createExchangeRecord(ViewExchangeRecord ViewExchangeRecord) throws Exception;
	
	
	public List<ExchangeRecordState> getAllExchangeRecordState();
	
	//public List<ViewExchangeRecordState> getAllViewExchangeRecordState
	
	public List<ViewExchangeRecord> getAllExchangeRecordByGoods(Integer goodsId);
	
	public ViewExchangeRecord getExchangeRecordById(Integer exchangeReocrdId);
	
	
}
