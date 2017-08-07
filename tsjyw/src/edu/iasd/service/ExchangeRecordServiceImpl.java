package edu.iasd.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demonstration.hibernate.basedao.IBaseDao;
import com.demonstration.hibernate.dao.support.Page;

import edu.iasd.pojo.Business;
import edu.iasd.pojo.BusinessManager;
import edu.iasd.pojo.ExchangeRecordState;
import edu.iasd.pojo.ExchangerRecord;
import edu.iasd.pojo.Goods;
import edu.iasd.pojo.Role;
import edu.iasd.pojo.User;
import edu.iasd.pojo.ViewExchangeRecord;
@Service
@Transactional
public class ExchangeRecordServiceImpl implements ExchangeRecordService{
	
	@Resource
	private IBaseDao<edu.iasd.pojo.User> userDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.ExchangerRecord> exchangeRecordDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.ViewExchangeRecord> viewExchangeRecordDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.Business> businessDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.BusinessManager> businessManagerDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.ExchangeRecordState> exchangeRecordStateDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.Goods> goodsDao;

	@Override
	public Page getAllExchangeRecord(int pageNumber, int pageSize, Integer userId,String queryString) {
		// TODO Auto-generated method stub
		User user = userDao.findUniqueBy("userId", userId);
		
		String where = (queryString != null && !queryString.equals("")) ? " and " + queryString : "";
		System.out.println(where);
		for(Object o : user.getUserRoles())
		{
			Role role = (Role)o;
			if(role.getRoleId() == Role.ROLE_SUPERADMIN)
			{
				return viewExchangeRecordDao.pagedQuery("from ViewExchangeRecord v where v.exchangeRecordStateId !=? "+where, pageNumber, pageSize,1000);
			}
			else if(role.getRoleId() == Role.ROLE_BUSINESSADMIN){
				BusinessManager businessManager = businessManagerDao.get(userId);
				Business business = businessManager.getBusiness();
				return viewExchangeRecordDao.pagedQuery("from ViewExchangeRecord v where v.businessId =? "+where, pageNumber, pageSize, business.getBusinessId());
			}
		}
		return null;
	}


	@Override
	public boolean cancelExchangeRecord(int exchangeRecordId) throws Exception {
		// TODO Auto-generated method stub
		ExchangerRecord t = exchangeRecordDao.get(exchangeRecordId);
		
		int nowState = t.getExchangeRecordState().getExchangeRecordStateId();
		if(nowState == ExchangeRecordState.businessCancel)
			throw new Exception("ȡ��ʧ�ܣ�ʧ��ԭ�򣺸ö����Ѿ�ȡ��");
		if(nowState == ExchangeRecordState.UserConfirem)
			throw new Exception("ȡ��ʧ�ܣ�ʧ��ԭ���û��Ѿ�ȷ���ջ�");
		
		t.setExchangeRecordState(exchangeRecordStateDao.get(ExchangeRecordState.businessCancel));
		User user = t.getUser();
		user.setUserIntegral((int) (user.getUserIntegral() + t.getExchangePrice()));
		userDao.save(user);
		exchangeRecordDao.save(t);
		return true;
	}


	@Override
	public boolean sureExchangeRecord(int exchangeRecordId) throws Exception{
		// TODO Auto-generated method stub
		ExchangerRecord t = exchangeRecordDao.get(exchangeRecordId);
		
		int nowState = t.getExchangeRecordState().getExchangeRecordStateId();
		if(nowState == ExchangeRecordState.businessCancel)
			throw new Exception("ȷ��ʧ�ܣ�ʧ��ԭ�򣺸ö����Ѿ�ȡ��");
		if(nowState == ExchangeRecordState.businessConfirem)
			throw new Exception("ȷ��ʧ�ܣ�ʧ��ԭ���̼��Ѿ�ȷ��");

		
		t.setExchangeRecordState(exchangeRecordStateDao.get(ExchangeRecordState.businessConfirem));
		exchangeRecordDao.save(t);
		return true;
	}


	@Override
	public String createExchangeRecord(ViewExchangeRecord viewExchangeRecord) throws Exception {
		// TODO Auto-generated method stub
		ExchangerRecord exchangerRecord = new ExchangerRecord();
		
		Goods goods = goodsDao.get(viewExchangeRecord.getGoodsId());
		User user = userDao.get(viewExchangeRecord.getUserId());
		
		Set<ExchangerRecord> ss = goods.getExchangeRecords();

		int i = goods.getGoodsCount();
		for(ExchangerRecord a:ss)
		{
			i = i - a.getExchangeCount();
		}
		if(viewExchangeRecord.getExchangeCount() <= 0)
			throw new Exception("��ѡ����ȷ����Ʒ����");
		
		if(viewExchangeRecord.getExchangeCount() > i)
			throw new Exception("��Ʒ��������");
		
		
		if(viewExchangeRecord.getExchangeCount() > goods.getGoodsLimitNumber())
			throw new Exception("����Ʒ�޹�"+goods.getGoodsLimitNumber()+"����");
		
		if(goods.getExchangeStartTime().getTime() > System.currentTimeMillis() || goods.getExchangeStopTime().getTime() < System.currentTimeMillis())
			throw new Exception("��Ʒ���ڶһ�����");
		
		if(user.getUserIntegral().intValue() < viewExchangeRecord.getExchangeCount()*goods.getGoodsPrice())
			throw new Exception("��Ļ��ֲ���");
		
		List<ExchangerRecord> ee = exchangeRecordDao.find("from ExchangerRecord e where e.user=? and e.goods=?", user,goods);
		if(ee != null && ee.size() >0)
			throw new Exception("���Ѿ��һ�������Ʒ");
		
		exchangerRecord.setExchangeCount(viewExchangeRecord.getExchangeCount());
		
		exchangerRecord.setExchangePrice(viewExchangeRecord.getExchangeCount()*goods.getGoodsPrice());
		exchangerRecord.setExchangeRecordState(exchangeRecordStateDao.get(ExchangeRecordState.waitbusinessConfirem));
		exchangerRecord.setExchangeTime(new Timestamp(System.currentTimeMillis()));
		exchangerRecord.setGoods(goods);
		exchangerRecord.setUser(user);
		//exchangerRecord.set
		//������ɱ��
		String base = "abcdefghijklmnopqrstuvwxyz0123456789";     
	    Random random = new Random();     
	    StringBuffer sb = new StringBuffer();     
	    for (int ii = 0; ii < 5; ii++) {     
	        int number = random.nextInt(base.length());     
	        sb.append(base.charAt(number));     
	    }     
	    sb.append(user.getUserId().toString()).append(goods.getGoodsId().toString());
	    
	    exchangerRecord.setExchangeRecordSerialNum(sb.toString());
	    exchangeRecordDao.save(exchangerRecord);
	    
	    user.setUserIntegral((int) (user.getUserIntegral() - viewExchangeRecord.getExchangeCount()*goods.getGoodsPrice()));
	    userDao.save(user);
		return exchangerRecord.getExchangeRecordSerialNum();
	}


	@Override
	public Page getMyExchangeRecord(int pageNumber, int pageSize, Integer userId,int stateType) {
		// TODO Auto-generated method stub
		if(stateType == -1)
			return viewExchangeRecordDao.pagedQuery("from ViewExchangeRecord e where e.userId = ?", pageNumber, pageSize, userId);
		else
			return viewExchangeRecordDao.pagedQuery("from ViewExchangeRecord e where e.userId = ? and e.exchangeRecordStateId=?", pageNumber, pageSize, userId,stateType);
	}


	@Override
	public List<ExchangeRecordState> getAllExchangeRecordState() {
		// TODO Auto-generated method stub
		return exchangeRecordStateDao.getAll();
	}


	@Override
	public List<ViewExchangeRecord> getAllExchangeRecordByGoods(Integer goodsId) {
		// TODO Auto-generated method stub
		return viewExchangeRecordDao.find("from ViewExchangeRecord v where v.goodsId=?", goodsId);
	}


	@Override
	public ViewExchangeRecord getExchangeRecordById(Integer exchangeReocrdId) {
		// TODO Auto-generated method stub
		return viewExchangeRecordDao.findUniqueBy("exchangeRecordId", exchangeReocrdId);
	}


	@Override
	public boolean applyCancelExchangeRecord(int exchangeRecordId)
			throws Exception {
		// TODO Auto-generated method stub
		ExchangerRecord e = exchangeRecordDao.get(exchangeRecordId);
		int nowState = e.getExchangeRecordState().getExchangeRecordStateId();
		
		if(nowState == ExchangeRecordState.businessCancel)
			throw new Exception("����ȡ��ʧ�ܣ�ʧ��ԭ���̼���ȡ��");
		if(nowState == ExchangeRecordState.UserApplyCancel)
			throw new Exception("�ö���������ȡ�����ȴ��̼�ȷ��");
		if(nowState == ExchangeRecordState.UserConfirem)
			throw new Exception("����ȡ��ʧ�ܣ�ʧ��ԭ������ȷ��ȡ��");
		
		e.setExchangeRecordState(exchangeRecordStateDao.get(ExchangeRecordState.UserApplyCancel));
		exchangeRecordDao.save(e);
		
		return true;
	}


	@Override
	public boolean sureMyExchangeRecord(int exchangeRecordId) throws Exception {
		// TODO Auto-generated method stub
		
		ExchangerRecord e = exchangeRecordDao.get(exchangeRecordId);
		int nowState = e.getExchangeRecordState().getExchangeRecordStateId();
		
		if(nowState == ExchangeRecordState.businessCancel)
			throw new Exception("ȷ�϶���ʧ�ܣ�ʧ��ԭ���̼���ȡ��");
		if(nowState == ExchangeRecordState.UserApplyCancel)
			throw new Exception("ȷ�϶���ʧ�ܣ�ʧ��ԭ������������ȡ������");
		if(nowState == ExchangeRecordState.UserConfirem)
			throw new Exception("����ȷ���ջ�");
		
		e.setExchangeRecordState(exchangeRecordStateDao.get(ExchangeRecordState.UserConfirem));
		exchangeRecordDao.save(e);
		
		return true;
	}


	@Override
	public Page getMyExchangeRecordForSearchForm(int pageNumber, int pageSize,
			Integer userId, String queryString) {
		// TODO Auto-generated method stub
		String where = (queryString != null && !queryString.equals("")) ? " and " + queryString : "";
		System.out.println(where);
		
		
		return viewExchangeRecordDao.pagedQuery("from ViewExchangeRecord v where v.userId=?"+where, pageNumber, pageSize, userId);
	}
}
