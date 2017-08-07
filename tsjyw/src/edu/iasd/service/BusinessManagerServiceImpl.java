package edu.iasd.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demonstration.hibernate.basedao.IBaseDao;
import com.demonstration.hibernate.dao.support.Page;

import edu.iasd.form.BusinessManagerForm;
import edu.iasd.pojo.Business;
import edu.iasd.pojo.BusinessManager;
import edu.iasd.pojo.Goods;
import edu.iasd.pojo.Role;
import edu.iasd.pojo.User;
import edu.iasd.pojo.ViewBusinessManager;
import edu.iasd.utils.UserHelper;

@Service
@Transactional
public class BusinessManagerServiceImpl implements BusinessManagerService{
	
	@Resource
	private IBaseDao<edu.iasd.pojo.BusinessManager> businessManagerDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.Business> businessDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.ViewBusinessManager> viewBusinessManagerDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.Goods> goodsDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.User> userDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.Role> roleDao;

	@Override
	public Page getBusinessManagerByBusiness(int pageNumber, int pageSize,
			int businessId) {
		// TODO Auto-generated method stub
		return viewBusinessManagerDao.pagedQuery("from ViewBusinessManager v where v.businessId=?", pageNumber, pageSize, businessId);
	}

	@Override
	public Page getAllBusinessManager(int pageNow, int pageSize) {
		// TODO Auto-generated method stub
		return viewBusinessManagerDao.pagedQuery("from ViewBusinessManager", pageNow, pageSize);
	}

	@Override
	public ViewBusinessManager getBusinessManager(int businessManagerId) {
		// TODO Auto-generated method stub
		ViewBusinessManager vbm = viewBusinessManagerDao.findUniqueBy("userId", businessManagerId);
		return vbm;
	}

	@Override
	public String createBusinessManager(BusinessManagerForm businessManager)  throws Exception{
		// TODO Auto-generated method stub
		User u = new User();
		businessManager.fillUser(u);
		UserHelper.saveUser(userDao,u);
		BusinessManager bmr = new BusinessManager();
		businessManager.fillBusinessManager(bmr);
		bmr.setUser(u);
		fillBusinessManager(bmr,businessManager);
		businessManagerDao.save(bmr);
		UserHelper.addRole(userDao, u, roleDao, Role.ROLE_BUSINESSADMIN);
		UserHelper.addRole(userDao, u, roleDao, Role.ROLE_USER);
		return u.getUserId().toString();
	}

	@Override
	public boolean deleteBusinessManager(int businessManagerId) {
		// TODO Auto-generated method stub
		User u = userDao.findUniqueBy("userId", businessManagerId);
		if(u.getUserRoles() != null){
			u.getUserRoles().clear();
			userDao.save(u);
		}
		businessManagerDao.removeById(businessManagerId);
		userDao.removeById(businessManagerId);
		return true;
	}

	@Override
	public boolean modifyBusinessManager(BusinessManagerForm businessManager)  throws Exception {
		// TODO Auto-generated method stub
		User u = userDao.findUniqueBy("userId", businessManager.getUserId());
		if(u == null) 
			return false;
		
		BusinessManager bmr = businessManagerDao.findUniqueBy("userId", businessManager.getUserId());
		if(bmr == null)
			return false;
		
		businessManager.fillUser(u);
		userDao.save(u);
		
		businessManager.fillBusinessManager(bmr);
		fillBusinessManager(bmr,businessManager);
		businessManagerDao.save(bmr);
		
		return true;
	}
	
	private void fillBusinessManager(BusinessManager bmr,BusinessManagerForm businessManager){
		
		Business business = businessDao.findUniqueBy("businessId", businessManager.getBusinessId());
		bmr.setBusiness(business);
	}

	@Override
	public List<ViewBusinessManager> getBusinessManagerFor(Integer goodsId) {
		// TODO Auto-generated method stub
		Goods goods = goodsDao.get(goodsId);
		Integer businessId = goods.getBusiness().getBusinessId();
		List<ViewBusinessManager> viewBusinessManagers = viewBusinessManagerDao.find("from ViewBusinessManager v where v.businessId=?", businessId);
		
		return viewBusinessManagers;
	}

}
