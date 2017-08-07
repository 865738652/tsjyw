package edu.iasd.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demonstration.hibernate.basedao.IBaseDao;
import com.demonstration.hibernate.dao.support.Page;

import edu.iasd.form.BusinessForm;
import edu.iasd.pojo.Business;
import edu.iasd.pojo.BusinessManager;
import edu.iasd.pojo.Goods;
import edu.iasd.pojo.Role;
import edu.iasd.pojo.User;
import edu.iasd.pojo.ViewBusiness;


@Service
@Transactional
public class BusinessServiceImpl implements BusinessService {
	
	@Resource
	private IBaseDao<edu.iasd.pojo.Goods> goodsDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.Business> businessDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.BusinessManager> businessManagerDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.ViewBusiness> viewBusinessDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.User> userDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.Role> roleDao;

	@Override
	public Page getAllBusiness(int pageNumber, int pageSize,Integer userId,String queryString) {
		
		String where = (queryString != null && !queryString.equals("")) ? " and " + queryString : "";
		System.out.println(where);
		User user = userDao.get(userId);
		Set<Role> roles = user.getUserRoles();
		for(Role o:roles)
		{
			if(o.getRoleId().intValue() == Role.ROLE_SUPERADMIN)
				return viewBusinessDao.pagedQuery("from ViewBusiness where 1=1 "+where, pageNumber, pageSize);
		}
		for(Role o:roles)
		{
			if(o.getRoleId().intValue() == Role.ROLE_BUSINESSADMIN)
			{
				BusinessManager businessManager = businessManagerDao.get(userId);
				Business business = businessManager.getBusiness();
				return viewBusinessDao.pagedQuery("from ViewBusiness v where v.businessId=?"+where, pageNumber, pageSize,business.getBusinessId());
			}	
		}
		return new Page();
	}

	@Override
	public ViewBusiness getBusiness(int businessId) {
		ViewBusiness vb=viewBusinessDao.findUniqueBy("businessId", businessId);
		return vb;
	}

	@Override
	public String createBusiness(BusinessForm business) {
		Business b= new Business();
		fillBusiness(b, business);
		businessDao.save(b);
		return b.getBusinessId().toString();
			
	}

	private void fillBusiness(Business b, BusinessForm business) {
		b.setBusinessName(business.getBusinessName());
		b.setBusinessIntro(business.getBusinessIntro());
		b.setBusinessPhone(business.getBusinessPhone());
		b.setBusinessQq(business.getBusinessQq());
		b.setBusinessStr(business.getBusinessStr());
		b.setBusinessWeixin(business.getBusinessWeixin());
		b.setBusinessImgPath(business.getBussinessImagePath());
	}

	@Override
	public boolean deleteBusiness(int businessId) {
		businessDao.removeById(businessId);
		return true;
	}

	@Override
	public boolean modifyBusiness(BusinessForm business) {
		Business b=businessDao.findUniqueBy("businessId", business.getBusinessId());
		if(b== null)
			return false;
		fillBusiness(b,business);
		businessDao.save(b);
		return true;
			
	}

	@Override
	public Business getBusinessForGoods(Integer goodsId) {
		// TODO Auto-generated method stub
		Goods goods = goodsDao.get(goodsId);
		return goods.getBusiness();
	}
}
