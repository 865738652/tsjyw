package edu.iasd.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demonstration.hibernate.basedao.IBaseDao;
import com.demonstration.hibernate.dao.support.Page;

import edu.iasd.pojo.ExchangeRecord;
import edu.iasd.pojo.User;
import edu.iasd.pojo.ViewExchangeRecord;
@Service
@Transactional
public class MyOrderServiceImpl implements MyOrderService{
	
	@Resource
	private IBaseDao<edu.iasd.pojo.User> userDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.ExchangeRecord> exchangeRecordDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.ViewExchangeRecord> viewExchangeRecordDao;
	

	@Override
	public Page getAllMyOrder(int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		return viewExchangeRecordDao.pagedQuery("from ViewExchangeRecord v", pageNumber, pageSize);
	}

	@Override
	public ViewExchangeRecord getMyOeder(int exchangeRecordId) {
		// TODO Auto-generated method stub
		ViewExchangeRecord ve = viewExchangeRecordDao.findUniqueBy("userId", exchangeRecordId);
		ExchangeRecord e = exchangeRecordDao.findUniqueBy("userId", exchangeRecordId);
		return ve;
	}

	@Override
	public Boolean cancelMyOrder(int exchangeRecordId) {
		// TODO Auto-generated method stub
		ExchangeRecord t = exchangeRecordDao.findUniqueBy("userId", exchangeRecordId);
		User u = t.getUser();
		exchangeRecordDao.removeById(exchangeRecordId);
		return true;
	}

}
