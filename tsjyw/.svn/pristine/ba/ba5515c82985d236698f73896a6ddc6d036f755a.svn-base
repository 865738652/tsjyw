package edu.iasd.service;

import java.util.HashSet;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demonstration.hibernate.basedao.IBaseDao;
import com.demonstration.hibernate.dao.support.Page;

import edu.iasd.form.GradeMasterForm;
import edu.iasd.pojo.Grade;
import edu.iasd.pojo.GradeMaster;
import edu.iasd.pojo.Role;
import edu.iasd.pojo.TeacherState;
import edu.iasd.pojo.User;
import edu.iasd.pojo.ViewGradeMaster;
import edu.iasd.pojo.Teacher;
import edu.iasd.utils.UserHelper;

@Service
@Transactional
public class GradeMasterServiceImpl implements GradeMasterService {
	
	@Resource
	private IBaseDao<edu.iasd.pojo.GradeMaster> gradeMasterDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.Grade> gradeDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.ViewGradeMaster> viewGradeMasterDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.Teacher> teacherDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.User> userDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.TeacherState> teacherStateDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.Role> roleDao;
	
	@Override
	public Page getAllGradeMaster(int pageNow, int pageSize) {
		
		return viewGradeMasterDao.pagedQuery("from ViewGradeMaster",pageNow,pageSize);
	}

	@Override
	public Page getGradeMasterByGrade(int pageNumber, int pageSize, int gradeId) {
		// TODO Auto-generated method stub
		return viewGradeMasterDao.pagedQuery("from ViewGradeMaster  v where v.gradeId=?", pageNumber, pageSize, gradeId);
		}
	   
	public ViewGradeMaster getGradeMaster(int gradeMasterId) {
		// TODO Auto-generated method stub
		
		ViewGradeMaster vgm = viewGradeMasterDao.findUniqueBy("gradeMasterId", gradeMasterId);
		return vgm;
	}
	
	@Override
	public String selectGradeMaster(int userId, int gradeId) throws Exception{
		Teacher t = teacherDao.findUniqueBy("userId", userId);
		if(t == null)
			throw new Exception("未找到此教师");
		Grade g = gradeDao.findUniqueBy("gradeId", gradeId);
		if(g == null)
			throw new Exception("未找到此年级");
		
		GradeMaster gmr = new GradeMaster();
		gmr.setTeacher(t);
		gmr.setGrade(g);
		gradeMasterDao.save(gmr);
		/*
		 * 赋予年级主任角色
		 */
		User u = userDao.findUniqueBy("userId", userId);
		UserHelper.addRole(userDao, u, roleDao, Role.ROLE_GRADEMASTER);
		return gmr.getGradeMasterId().toString();
	}
	
	@Override
	public Boolean cancelGradeMaster(int gradeMasterId) throws Exception {	
		
		/*
		 * 删除班主任
		 */
		GradeMaster gmr = gradeMasterDao.findUniqueBy("gradeMasterId", gradeMasterId);
		gradeMasterDao.remove(gmr);

		/*
		 * 如果不任其年级主任，删除年级主任角色
		 */
		List<ViewGradeMaster> list = (List<ViewGradeMaster>)viewGradeMasterDao.find("from ViewGradeMaster v where v.userId=?", gmr.getTeacher().getUserId());
		if (list == null || list.size() == 0) {
			User u = gmr.getTeacher().getUser();		
			UserHelper.removeRole(userDao, u, roleDao, Role.ROLE_GRADEMASTER);
		}		
		
		return true;
	}

	@Override
	public String createGradeMaster(GradeMasterForm gradeMaster) throws Exception {
		User u = new User();
		gradeMaster.fillUser(u);
		UserHelper.saveUser(userDao, u);
		UserHelper.addRole(userDao, u, roleDao, Role.ROLE_USER);
				
		Teacher t = new Teacher();
//		t.setUserId(u.getUserId());
		t.setSchoolMaster(false);
		t.setUser(u);
		t.setTeacherState(teacherStateDao.findUniqueBy("teacherStateId", TeacherState.TEACHER_STATE_NORMAL));
		Grade g = gradeDao.findUniqueBy("gradeId", gradeMaster.getGradeId());
		t.setSchool(g.getSchool());
		teacherDao.save(t);
		
		GradeMaster gmr = new GradeMaster();
		gmr.setTeacher(t);
		gmr.setGrade(g);
		gradeMasterDao.save(gmr);
		
		/*
		 * 赋予教师角色、年级主任角色
		 */
		UserHelper.addRole(userDao, u, roleDao, Role.ROLE_SCHOOLTEACHER);
		UserHelper.addRole(userDao, u, roleDao, Role.ROLE_GRADEMASTER);
		
		return gmr.getGradeMasterId().toString();
	}

	@Override
	public boolean deleteGradeMaster(int gradeMasterId) {
		GradeMaster gmr =gradeMasterDao.findUniqueBy("gradeMasterId", gradeMasterId);
		Integer userId = gmr.getTeacher().getUserId();
		
		/*
		 * 删除角色
		 */
		User u = userDao.findUniqueBy("userId", userId);
		if (u.getUserRoles() != null) {
			u.getUserRoles().clear();
			userDao.save(u);
		}
		
		/*
		 * 删除用户
		 */				
		gradeMasterDao.removeById(gradeMasterId);
		teacherDao.removeById(userId);
		userDao.removeById(userId);
		return true;
	}

	@Override
	public boolean modifyGradeMaster(GradeMasterForm gradeMaster) throws Exception {
		User u = userDao.findUniqueBy("userId", gradeMaster.getUserId());
		if(u == null)
			return false;
		Teacher t = teacherDao.findUniqueBy("userId", gradeMaster.getUserId());
		if(t == null)
			return false;
	    GradeMaster  gmr = gradeMasterDao.findUniqueBy("gradeMasterId", gradeMaster.getGradeMasterId());
	    if(gmr == null )
	    	return false;
	    
	    gradeMaster.fillUser(u);
	    userDao.save(u);
	    
	    gradeMaster.fillTeacher(t);
	    teacherDao.save(t);
	    
	    gradeMasterDao.save(gmr);
	    return true;
	}
}
