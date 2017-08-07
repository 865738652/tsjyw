package edu.iasd.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demonstration.hibernate.basedao.IBaseDao;
import com.demonstration.hibernate.dao.support.Page;

import edu.iasd.form.CountyForm;
import edu.iasd.pojo.County;
import edu.iasd.pojo.CountyManager;
import edu.iasd.pojo.Role;
import edu.iasd.pojo.User;
import edu.iasd.pojo.ViewCounty;

@Service
@Transactional
public class CountyServiceImpl implements CountyService {

	@Resource
	private IBaseDao<edu.iasd.pojo.County> countyDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.ViewCounty> viewCountyDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.User> userDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.CountyManager> countyManagerDao;
	
	@Override
	public Page getAllcounty(Integer userId, String queryString, int pageNow, int pageSize) {
		User user = userDao.findUniqueBy("userId", userId);
		
		for (Object o : user.getUserRoles()) {
			Role role = (Role)o;
			if (role.getRoleId() == Role.ROLE_SUPERADMIN) {
				String condition = (queryString != null && queryString.length() > 0) ? " where " + queryString : "";
				return  viewCountyDao.pagedQuery("from ViewCounty" + condition, pageNow, pageSize);
			}
			else if (role.getRoleId() == Role.ROLE_COUNTYADMIN) {
				String condition = (queryString != null && queryString.length() > 0) ? " and " + queryString : "";
				return  viewCountyDao.pagedQuery("select distinct v from ViewCounty v, CountyManager c where v.countyId=c.county.countyId and  c.user.userId=? " + condition, pageNow, pageSize, userId);
			}
		}
		return  new Page();
	}

	@Override
	public Page getAllcounty(String queryString, int pageNow, int pageSize) {
		String condition = (queryString != null && queryString.length() > 0) ? " where " + queryString : "";
		return  viewCountyDao.pagedQuery("from ViewCounty" + condition, pageNow, pageSize);
	}
	
	@Override
	public String createCounty(CountyForm county) throws Exception{
		List<County> l = countyDao.findBy("countyName", county.getCountyName());
		if (l != null && l.size() > 0)
			throw new Exception("区县名称重复");
		
		County c = new County();
		c.setCountyName(county.getCountyName());
		countyDao.save(c);		
		return c.getCountyId().toString();
	}
	//删除
	@Override
	public boolean deleteCounty(int countyId) {
		countyDao.removeById(countyId);		
		return true;
	}

	//修改
	@Override
	public boolean modify(CountyForm county)  throws Exception {
		List<County> l = countyDao.findBy("countyName", county.getCountyName());
		if (l != null && l.size() > 0 && l.get(0).getCountyId() != county.getCountyId())
			throw new Exception("区县名称重复");
		
		County c= countyDao.findUniqueBy("countyId", county.getCountyId());
		if(c == null)			
			return false;
		c.setCountyName(county.getCountyName());
		countyDao.save(c);
		return true;		
	}

	@Override
	public ViewCounty getCountyInformation(int countyId) {
		ViewCounty vc= viewCountyDao.findUniqueBy("countyId", countyId);
		return vc;
	}
}
