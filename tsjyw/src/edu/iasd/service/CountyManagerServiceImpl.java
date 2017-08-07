package edu.iasd.service;


import java.util.List;




import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demonstration.hibernate.basedao.IBaseDao;
import com.demonstration.hibernate.dao.support.Page;

import edu.iasd.form.CountyManagerForm;
import edu.iasd.pojo.County;
import edu.iasd.pojo.CountyManager;
import edu.iasd.pojo.Role;
import edu.iasd.pojo.User;
import edu.iasd.pojo.ViewCountyManager;
import edu.iasd.utils.UserHelper;

@Service
@Transactional
public class CountyManagerServiceImpl implements CountyManagerService{

	@Resource
	private IBaseDao<edu.iasd.pojo.CountyManager> countyManagerDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.County> countyDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.ViewCountyManager> viewCountyManagerDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.User> userDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.Role> roleDao;
	
	@Override
	public Page getAllCountyManager(int pageNow,int pageSize) {
		return viewCountyManagerDao.pagedQuery("from ViewCountyManager",pageNow,pageSize);		
	}

	@Override
	public Page getCountyManagerByCounty(int pageNumber, int pageSize,int countyId) {
		return viewCountyManagerDao.pagedQuery("from ViewCountyManager  v where v.countyId=?", pageNumber, pageSize,countyId);
	}

	@Override
	public ViewCountyManager getCountyManager(int countyManagerId) {
		ViewCountyManager vcm = viewCountyManagerDao.findUniqueBy("userId", countyManagerId);
		return vcm;
	}

	@Override
	public String createCountyManager(CountyManagerForm countyManager)  throws Exception {
		/*
		 * �������û�
		 */
		User u = new User();
		countyManager.fillUser(u);
		UserHelper.saveUser(userDao, u);
		UserHelper.addRole(userDao, u, roleDao, Role.ROLE_USER);
		
		/*
		 * �������ع���Ա
		 */
		CountyManager cmr = new CountyManager();
		countyManager.fillCountyManager(cmr);
		cmr.setUser(u);
		fillCountyManager(cmr, countyManager);
		countyManagerDao.save(cmr);
		
		/*
		 * �������ع���Ա��ɫ
		 */
		UserHelper.addRole(userDao, u, roleDao, Role.ROLE_COUNTYADMIN);
		
		return u.getUserId().toString();
	}
	
	@Override
	public boolean deleteCountyManager(int countyManagerId) {
		/*
		 * ɾ����ɫ
		 */
		User u = userDao.findUniqueBy("userId", countyManagerId);
		if (u.getUserRoles() != null) {
			u.getUserRoles().clear();
			userDao.save(u);
		}
		
		/*
		 * ɾ���û�
		 */
		countyManagerDao.removeById(countyManagerId);
		userDao.removeById(countyManagerId);
		return true;
	}

	@Override
	public boolean modifyCountyManager(CountyManagerForm countyManager) throws Exception {
		// TODO Auto-generated method stub
		User u = userDao.findUniqueBy("userId", countyManager.getUserId());
		if (u == null)
			return false;
		
		CountyManager cmr = countyManagerDao.findUniqueBy("userId", countyManager.getUserId());
		if (cmr == null)
			return false;
		
		countyManager.fillUser(u);
		userDao.save(u);	
		
		countyManager.fillCountyManager(cmr);
		fillCountyManager(cmr,countyManager);
		countyManagerDao.save(cmr);	
		return true;
	}
	
	private void fillCountyManager(CountyManager cmr,CountyManagerForm countyManager) {
		// TODO Auto-generated method stub
		County county = countyDao.findUniqueBy("countyId", countyManager.getCountyId());
		cmr.setCounty(county);
	}

	

	@Override
	public String selectCountyManager(int userId, int countyId)throws Exception {
		User u = userDao.findUniqueBy("userId", userId);
		if(u == null)
			throw new Exception("δ�ҵ����û�");
		County c = countyDao.findUniqueBy("countyId", countyId);
		if(c == null)
			throw new Exception("δ�ҵ�������");
		
		CountyManager cm = new CountyManager();
		cm.setUser(u);
		cm.setCounty(c);
		countyManagerDao.save(cm);
		/*
		 *�������ع���Ľ�ɫ
		 */
		
		UserHelper.addRole(userDao, u, roleDao, Role.ROLE_COUNTYADMIN);
		return cm.getUserId().toString();
	}

	@Override
	public boolean cancelCountyManager(int countyManagerId) throws Exception {
			CountyManager cmr =countyManagerDao.findUniqueBy("userId", countyManagerId);
		
		/*
		 * ɾ�����صĹ���Ա
		 */
			countyManagerDao.remove(cmr);
		
		/*
		 * ��������������صĹ���Ա��ɾ�����ؽ�ɫ
		 */
		List<ViewCountyManager> list = (List<ViewCountyManager>)viewCountyManagerDao.find("from ViewCountyManager v where v.userId=?", cmr.getUser().getUserId());
		if (list.size() == 1) {
			User u = cmr.getUser();		
			UserHelper.removeRole(userDao, u, roleDao, Role.ROLE_COUNTYADMIN);
		}
		return true;
	}
}
