package edu.iasd.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demonstration.hibernate.basedao.IBaseDao;
import com.demonstration.hibernate.dao.support.Page;

import edu.iasd.form.SchoolManagerForm;
import edu.iasd.pojo.Role;
import edu.iasd.pojo.School;
import edu.iasd.pojo.SchoolManager;
import edu.iasd.pojo.User;
import edu.iasd.pojo.ViewSchoolManager;
import edu.iasd.utils.UserHelper;

@Service
@Transactional
public  class SchoolManagerServiceImpl implements SchoolManagerService{

	@Resource
	private IBaseDao<edu.iasd.pojo.SchoolManager> schoolManagerDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.School> schoolDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.ViewSchoolManager> viewSchoolManagerDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.User> userDao;	
	
	@Resource
	private IBaseDao<edu.iasd.pojo.Role> roleDao;

	@Override
	public Page getAllSchoolManager(int pageNow, int pageSize) {
		// TODO Auto-generated method stub
		return viewSchoolManagerDao.pagedQuery("from ViewSchoolManager",pageNow,pageSize);		
	}

	@Override
	public Page getSchoolManagerBySchool(int pageNow, int pageSize, int schoolId) {
	
		return viewSchoolManagerDao.pagedQuery("from ViewSchoolManager v where v.schoolId=?",pageNow,pageSize,schoolId);
	}

	@Override
	public ViewSchoolManager getSchoolManager(int schoolManagerId) {
		// TODO Auto-generated method stub
		ViewSchoolManager vsm = viewSchoolManagerDao.findUniqueBy("userId", schoolManagerId);
		return vsm;
	}

	@Override
	public String createSchoolManager(SchoolManagerForm schoolManager) throws Exception {
		/*
		 * �����û�
		 */
		User u = new User();
		schoolManager.fillUser(u);
		UserHelper.saveUser(userDao, u);
		UserHelper.addRole(userDao, u, roleDao, Role.ROLE_USER);
		
		/*
		 * ����ѧУ����Ա
		 */
		SchoolManager s= new SchoolManager();
		schoolManager.fillSchoolManager(s);
		//t.setUserId(u.getUserId());
		s.setUser(u);
		fillSchoolManager(s, schoolManager);
		schoolManagerDao.save(s);
		
		/*
		 * ����ѧУ����Ա��ɫ
		 */
		UserHelper.addRole(userDao, u, roleDao, Role.ROLE_SCHOOLADMIN);
		return u.getUserId().toString();
	}
	
	private void fillSchoolManager(SchoolManager s, SchoolManagerForm SchoolManager) throws Exception {
		School school = schoolDao.findUniqueBy("schoolId", SchoolManager.getSchoolId());
		s.setSchool(school);
		
	}

	@Override
	public boolean deleteSchoolManager(int schoolManagerId) {
		/*
		 * ɾ����ɫ
		 */
		User u = userDao.findUniqueBy("userId", schoolManagerId);
		if (u.getUserRoles() != null) {
			u.getUserRoles().clear();
			userDao.save(u);
		}
		
		/*
		 * ɾ���û�
		 */
		schoolManagerDao.removeById(schoolManagerId);
		userDao.removeById(schoolManagerId);	
		return true;
	}
	
	
	@Override
	public boolean modifySchoolManager(SchoolManagerForm schoolManager) throws Exception {
		// TODO Auto-generated method stub
		User u = userDao.findUniqueBy("userId", schoolManager.getUserId());
		if (u == null)
			return false;
		
		SchoolManager s = schoolManagerDao.findUniqueBy("userId", schoolManager.getUserId());
		if (s == null)
			return false;
		
		schoolManager.fillUser(u);
		userDao.save(u);	
		
		schoolManager.fillSchoolManager(s);
		fillSchoolManager(s, schoolManager);
		schoolManagerDao.save(s);		
		return true;
	}

	@Override
	public String selectSchoolManager(int schoolManagerId, int schoolId)throws Exception {
		User u = userDao.findUniqueBy("userId", schoolManagerId);
		if(u == null)
			throw new Exception("δ�ҵ����û�");
		School s = schoolDao.findUniqueBy("schoolId", schoolId);
		if(s == null)
			throw new Exception("δ�ҵ���ѧУ");
		
		SchoolManager cm = new SchoolManager();
		cm.setUser(u);
		cm.setSchool(s);
		schoolManagerDao.save(cm);
		/*
		 *��������εĽ�ɫ
		 */
		
		UserHelper.addRole(userDao, u, roleDao, Role.ROLE_SCHOOLADMIN);
		return cm.getUserId().toString();
	}

	@Override
	public boolean cancelSchoolManager(int schoolManagerId) {
		SchoolManager smr =schoolManagerDao.findUniqueBy("userId", schoolManagerId);
		
		/*
		 * ����ѧУ����Ա
		 */
		schoolManagerDao.remove(smr);
		
		/*
		 * �����������ѧУ�Ĺ���Ա��������ɾ��
		 */
		List<ViewSchoolManager> list = (List<ViewSchoolManager>)viewSchoolManagerDao.find("from ViewSchoolManager v where v.userId=?", smr.getUser().getUserId());
		if (list.size() == 1) {	
			User u = smr.getUser();
			UserHelper.removeRole(userDao, u, roleDao, Role.ROLE_SCHOOLADMIN);
		}
		return true;
	}
	
	
	
}
