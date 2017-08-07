package edu.iasd.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demonstration.hibernate.basedao.IBaseDao;
import com.demonstration.hibernate.dao.support.Page;

import edu.iasd.form.GoodsForm;
import edu.iasd.form.GoodsTypeVo;
import edu.iasd.pojo.Business;
import edu.iasd.pojo.BusinessManager;
import edu.iasd.pojo.ExchangerRecord;
import edu.iasd.pojo.Goods;
import edu.iasd.pojo.GoodsState;
import edu.iasd.pojo.GoodsType;
import edu.iasd.pojo.Role;
import edu.iasd.pojo.User;
import edu.iasd.pojo.ViewGoods;
import edu.iasd.pojo.ViewGoodsType;
@Service
@Transactional
public class GoodsServiceImpl implements GoodsService{
	
	@Resource
	private IBaseDao<edu.iasd.pojo.Goods> goodsDao;
	
	@Resource 
	private IBaseDao<edu.iasd.pojo.ViewGoods> viewGoodsDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.GoodsState> goodsStateDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.Business> businessDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.BusinessManager> businessManagerDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.GoodsType> goodsTypeDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.ViewGoodsType>  viewGoodsTypeDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.User> userDao;
	

	
	
	@Override
	public Page getAllGoods(int pageNumber, int pageSize,JSONObject searchJson) {
		// TODO Auto-generated method stub
		if(searchJson.getInt("type") == Goods.HOT_BUY)
		{
			return viewGoodsDao.pagedQuery("from ViewGoods v where v.goodsStateId=? and v.exchangeStartTime<? and v.exchangeStopTime>? order by v.exchangeRecordCount desc", pageNumber, pageSize,GoodsState.GOODSSTATE_NORMAL,new Timestamp(System.currentTimeMillis()),new Timestamp(System.currentTimeMillis()));
		}
		if(searchJson.getInt("type") == Goods.HOT_EXCHANGE)
		{
			return viewGoodsDao.pagedQuery("from ViewGoods v where v.goodsStateId=? and v.exchangeStartTime<? and v.exchangeStopTime>? order by v.exchangeRecordCount desc", pageNumber, pageSize,GoodsState.GOODSSTATE_NORMAL,new Timestamp(System.currentTimeMillis()),new Timestamp(System.currentTimeMillis()));
		}
		if(searchJson.getInt("type") == Goods.RECENT_EXCHANGE)
		{
			return viewGoodsDao.pagedQuery("from ViewGoods v where v.goodsStateId=? and v.exchangeStopTime<? order by v.exchangeRecordCount desc", pageNumber, pageSize,GoodsState.GOODSSTATE_NORMAL,new Timestamp(System.currentTimeMillis()));
		}
		return null;
	}

	@Override
	public Page getGoodsByBusiness(int pageNumber, int pageSize, int businessId) {
		// TODO Auto-generated method stub
		return viewGoodsDao.pagedQuery("from ViewGoods v where v.businessId=?", pageNumber, pageSize, businessId);
	}

	@Override
	public ViewGoods getGoods(int goodsId) {
		// TODO Auto-generated method stub
		ViewGoods v=viewGoodsDao.findUniqueBy("goodsId", goodsId);
		Goods t = goodsDao.findUniqueBy("goodsId", goodsId);
		
		v.setGoodsTypes(GoodsTypeVo.convert(t.getGoodsType()));
		return v;
	}

	@Override
	public String createGoods(GoodsForm goods) {
		// TODO Auto-generated method stub
		Goods c=new Goods();
		fillGoods(c,goods);
		c.setGoodsName(goods.getGoodsName());
		c.setBusiness(businessDao.findUniqueBy("businessId", goods.getBusinessId()));
		c.setGoodsState(goodsStateDao.findUniqueBy("goodsStateId", goods.getGoodsStateId()));
		goodsDao.save(c);
		return c.getGoodsId().toString();
	}

	@Override
	public boolean modifyGoods(GoodsForm goods) {
		// TODO Auto-generated method stub
		Goods c=goodsDao.findUniqueBy("goodsId", goods.getGoodsId());
		if(c == null)
			return false; 
		c.setGoodsName(goods.getGoodsName());
		c.setBusiness(businessDao.findUniqueBy("businessId", goods.getBusinessId()));
		c.setGoodsState(goodsStateDao.findUniqueBy("goodsStateId", goods.getGoodsStateId()));
		fillGoods(c,goods);
		goodsDao.save(c);
		
		return true;
	}

	@Override
	public boolean deleteGoods(int goodsId) {
		// TODO Auto-generated method stub
		goodsDao.removeById(goodsId);
		return true;
	}

	@Override
	public List<GoodsState> getGoodsState() {
		// TODO Auto-generated method stub
		return goodsStateDao.getAll();
	}

	private void fillGoods(Goods goods,GoodsForm form){
		goods.setExchangeStartTime(form.getExchangeStartTime());
		goods.setExchangeStopTime(form.getExchangeStopTime());
		goods.setGoodsCount(form.getGoodsCount());
		goods.setGoodsIntro(form.getGoodsIntro());
		goods.setGoodsLimitNumber(form.getGoodsLimitNumber());
		goods.setGoodsName(form.getGoodsName());
		goods.setGoodsPrice(form.getGoodsPrice());
		goods.setGoodsSerial(form.getGoodsSerial());
		goods.setGoodsImagaPath(form.getGoodsImagaPath());
		goods.setGoodsState(goodsStateDao.findUniqueBy("goodsStateId", form.getGoodsStateId()));
		goods.setBusiness(businessDao.findUniqueBy("businessId", form.getBusinessId()));
		
		Set<GoodsType> newType = new HashSet<GoodsType>();
		if(form.getGoodsType() != null && form.getGoodsType().size() > 0){
			for(Integer t: form.getGoodsType()) {
				newType.add(goodsTypeDao.findUniqueBy("goodsTypeId", t));
			}
		}
		goods.setGoodsType(newType);
	}

	@Override
	public List<GoodsType> getGoodsType() {
		// TODO Auto-generated method stub
		return goodsTypeDao.getAll();
	}

	@Override
	public List<Business> getBusiness() { 
		// TODO Auto-generated method stub
		return businessDao.getAll();
	}

	@Override
	public List<ViewGoods> getAll() {
		// TODO Auto-generated method stub
		return viewGoodsDao.find("from ViewGoods v where v.goodsStateId=?", GoodsState.GOODSSTATE_NORMAL);
	}

	@Override
	public ViewGoods getGoodsId(Integer goodsId) {
		// TODO Auto-generated method stub
		return viewGoodsDao.findUniqueBy("goodsId", goodsId);
	}

	@Override
	public List<ViewGoodsType> getgoodsType(int goodsId) {
		// TODO Auto-generated method stub
		return viewGoodsTypeDao.getAll();
	}

	@Override
	public Page getMyAllGoods(int pageNumber, int pageSize, int businessId,Integer userId,String queryString) {
		// TODO Auto-generated method stub
		
		String where = (queryString != null && !queryString.equals("")) ? " and " + queryString : "";
		
		User user = userDao.get(userId);
		Set<Role> roles = user.getUserRoles();
		for(Role o:roles)
		{
			if(o.getRoleId().intValue() == Role.ROLE_SUPERADMIN)
			{
				if(businessId == -1)
					return viewGoodsDao.pagedQuery("from ViewGoods v where v.goodsId !=?"+where, pageNumber, pageSize,-1);
				else
					return viewGoodsDao.pagedQuery("from ViewGoods v where v.businessId=?"+where, pageNumber, pageSize, businessId);
			}
		}
		for(Role o:roles)
		{
			if(o.getRoleId().intValue() == Role.ROLE_BUSINESSADMIN)
			{
				BusinessManager businessManager = businessManagerDao.get(userId);
				int businessId1 = businessManager.getBusiness().getBusinessId();
				return viewGoodsDao.pagedQuery("from ViewGoods v where v.businessId=?"+where, pageNumber, pageSize, businessId1);
			}
		}
		return null;
	}

	@Override
	public String GoodsIsOrNotBuy(Integer goodsId) {
		// TODO Auto-generated method stub
		ViewGoods goods = viewGoodsDao.get(goodsId);
		if(goods.getGoodsStateId().intValue() == GoodsState.GOODSSTATE_DISABLE.intValue())
			return "商品已作废";
		if(goods.getExchangeStartTime().getTime() > new Timestamp(System.currentTimeMillis()).getTime() || new Timestamp(System.currentTimeMillis()).getTime() > goods.getExchangeStopTime().getTime())
			return "未在出售期内";
		if(goods.getGoodsCount() == 0)
			return "商品售完";
		return null;
	}

	@Override
	public List<ViewGoods> getGoodsByType(List<GoodsTypeVo> goodsTypes) {
		// TODO Auto-generated method stub
		try
		{
			Integer goodsTypeId = goodsTypes.get(0).getGoodsTypeId();
			GoodsType goodsType = goodsTypeDao.get(goodsTypeId);
			Set<Goods> goodses = goodsType.getGoodses();
			System.out.println(goodses.size());
			List<ViewGoods> viewGoodses = new ArrayList<ViewGoods>();
			if(goodses.size() <= 4)
			{
				for(Goods a:goodses)
					viewGoodses.add(viewGoodsDao.get(a.getGoodsId()));
			}
			else
			{
				int count = 0;
				for(Goods a:goodses)
				{
					viewGoodses.add(viewGoodsDao.get(a.getGoodsId()));
					count++;
					if(count >= 4)
						break;
				}
			}
			return viewGoodses;
		}
		catch(Exception e)
		{
			return null;
		}
		
	}

	@Override
	public int getGoodsRemainNum(Integer goodsId) {
		// TODO Auto-generated method stub
		Goods goods = goodsDao.get(goodsId);
		Set<ExchangerRecord> s = goods.getExchangeRecords();
		if(s == null || s.size() == 0)
			return goods.getGoodsCount();
		int u = goods.getGoodsCount();
		for(ExchangerRecord e : s)
		{
			u = u - e.getExchangeCount();
		}
		return u;
	}

	@Override
	public boolean editGoodsIntrol(Integer goodsId, String goodsIntro) throws Exception {
		
		Goods goods = goodsDao.get(goodsId);
		if(goods == null)
			throw new Exception("商品不存在");
		goods.setGoodsIntro(goodsIntro);
		
		goodsDao.save(goods);
		
		
		return true;
	}
}
