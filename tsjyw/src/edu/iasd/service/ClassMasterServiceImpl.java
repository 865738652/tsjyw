package edu.iasd.service;

import java.util.HashSet;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demonstration.hibernate.basedao.IBaseDao;
import com.demonstration.hibernate.dao.support.Page;

import edu.iasd.form.ClassMasterForm;
import edu.iasd.pojo.ClassMaster;
import edu.iasd.pojo.Role;
import edu.iasd.pojo.SchoolClass;
import edu.iasd.pojo.Teacher;
import edu.iasd.pojo.TeacherState;
import edu.iasd.pojo.User;
import edu.iasd.pojo.ViewClassMaster;
import edu.iasd.utils.UserHelper;

@Service
@Transactional
public class ClassMasterServiceImpl implements ClassMasterService {
	
	@Resource
	private IBaseDao<edu.iasd.pojo.ClassMaster> classMasterDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.ViewClassMaster> viewClassMasterDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.Teacher> teacherDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.TeacherState> teacherStateDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.User> userDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.SchoolClass> schoolClassDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.Role> roleDao;

	@Override
	public Page getClassMasterBySchoolClass(int pageNumber, int pageSize,int schoolClassId) {
		// TODO Auto-generated method stub
		return viewClassMasterDao.pagedQuery("from ViewClassMaster  v where v.schoolClassId=?", pageNumber, pageSize, schoolClassId);
	}

	@Override
	public Page getAllClassMaster(int pageNow, int pageSize) {
		// TODO Auto-generated method stub
		return viewClassMasterDao.pagedQuery("from ViewClassMaster",pageNow,pageSize);
	}

	@Override
	public ViewClassMaster getClassMaster(int classMasterId) {
		// TODO Auto-generated method stub
		ViewClassMaster vcm = viewClassMasterDao.findUniqueBy("classMasterId", classMasterId);
		return vcm;
	}

	@Override
	public String selectClassMaster(int userId, int schoolClassId) throws Exception {
		Teacher t = teacherDao.findUniqueBy("userId", userId);
		if(t == null)
			throw new Exception("δ�ҵ��˽�ʦ");
		SchoolClass sc = schoolClassDao.findUniqueBy("schoolClassId", schoolClassId);
		if(sc == null)
			throw new Exception("δ�ҵ��˰༶");
		
		ClassMaster cm = new ClassMaster();
		cm.setTeacher(t);
		cm.setSchoolClass(sc);
		classMasterDao.save(cm);
		/*
		 *��������εĽ�ɫ
		 */
		User u = userDao.findUniqueBy("userId", userId);
		UserHelper.addRole(userDao, u, roleDao, Role.ROLE_CLASSMASTER);
		return cm.getClassMasterId().toString();
	}
	
	@Override
	public Boolean cancelClassMaster(int classMasterId) throws Exception {
		ClassMaster cmr =classMasterDao.findUniqueBy("classMasterId", classMasterId);
		
		/*
		 * ɾ��������
		 */
		classMasterDao.remove(cmr);
		
		/*
		 * �������������İ����Σ�ɾ�������ν�ɫ
		 */
		List<ViewClassMaster> list = (List<ViewClassMaster>)viewClassMasterDao.find("from ViewClassMaster v where v.userId=?", cmr.getTeacher().getUserId());
		if (list == null || list.size() == 0) {
			User u = cmr.getTeacher().getUser();		
			UserHelper.removeRole(userDao, u, roleDao, Role.ROLE_CLASSMASTER);
		}
		return true;
	}

	@Override
	public String createClassMaster(ClassMasterForm classMaster)  throws Exception {
		/*
		 * �����û�����ʦ�Ͱ�����
		 */
		User u = new User();
		classMaster.fillUser(u);
		UserHelper.saveUser(userDao, u);
		UserHelper.addRole(userDao, u, roleDao, Role.ROLE_USER);
				
		Teacher t = new Teacher();
//		t.setUserId(u.getUserId());
		t.setSchoolMaster(false);
		t.setUser(u);
		t.setTeacherState(teacherStateDao.findUniqueBy("teacherStateId", TeacherState.TEACHER_STATE_NORMAL));
		SchoolClass sc = schoolClassDao.findUniqueBy("schoolClassId", classMaster.getSchoolClassId());
		t.setSchool(sc.getGrade().getSchool());
		teacherDao.save(t);
		
		ClassMaster cmr = new ClassMaster();
		cmr.setTeacher(t);
		cmr.setSchoolClass(sc);
		classMasterDao.save(cmr);
		
		/*
		 * �����ʦ��ɫ���꼶���ν�ɫ
		 */
		UserHelper.addRole(userDao, u, roleDao, Role.ROLE_SCHOOLTEACHER);
		UserHelper.addRole(userDao, u, roleDao, Role.ROLE_CLASSMASTER);
		
		return cmr.getClassMasterId().toString();
	}

	@Override
	public boolean deleteClassMaster(int classMasterId) {
		ClassMaster cmr =classMasterDao.findUniqueBy("classMasterId", classMasterId);
		Integer userId = cmr.getTeacher().getUserId();

		/*
		 * ɾ����ɫ
		 */
		User u = userDao.findUniqueBy("userId", userId);
		if (u.getUserRoles() != null) {
			u.getUserRoles().clear();
			userDao.save(u);
		}
		
		/*
		 * ɾ���û�
		 */
		classMasterDao.removeById(classMasterId);
		teacherDao.removeById(userId);
		userDao.removeById(userId);
		return true;
	}

	@Override
	public boolean modifyClassMaster(ClassMasterForm classMaster)  throws Exception {
		// TODO Auto-generated method stub
		User u = userDao.findUniqueBy("userId", classMaster.getUserId());
		if(u == null)
			return false;
		Teacher t = teacherDao.findUniqueBy("userId", classMaster.getUserId());
		if(t == null)
			return false;
		ClassMaster  cmr = classMasterDao.findUniqueBy("classMasterId", classMaster.getClassMasterId());
	    if(cmr == null )
	    	return false;
	    
		classMaster.fillUser(u);
	    userDao.save(u);
	    
	    classMaster.fillTeacher(t);
	    teacherDao.save(t);
	    
	    classMasterDao.save(cmr);
	    return true;
	}	

}
