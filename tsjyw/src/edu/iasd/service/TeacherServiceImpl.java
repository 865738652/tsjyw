package edu.iasd.service;

import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demonstration.hibernate.basedao.IBaseDao;
import com.demonstration.hibernate.dao.support.Page;

import edu.iasd.form.AttachVO;
import edu.iasd.form.TeacherForm;
import edu.iasd.pojo.Role;
import edu.iasd.pojo.School;
import edu.iasd.pojo.SchoolClass;
import edu.iasd.pojo.Student;
import edu.iasd.pojo.StudentState;
import edu.iasd.pojo.Teacher;
import edu.iasd.pojo.TeacherState;
import edu.iasd.pojo.User;
import edu.iasd.pojo.ViewStudent;
import edu.iasd.pojo.ViewTeacher;
import edu.iasd.utils.ExcelHelper;
import edu.iasd.utils.UserHelper;

@Service("teacherService")
@Transactional
public class TeacherServiceImpl implements TeacherService{

	@Resource
	private IBaseDao<edu.iasd.pojo.Teacher> teacherDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.School> schoolDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.ViewTeacher> viewTeacherDao;

	@Resource
	private IBaseDao<edu.iasd.pojo.User> userDao;
		
	@Resource
	private IBaseDao<edu.iasd.pojo.TeacherState> teacherStateDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.Role> roleDao;

	@Override
	public Page getAllTeacher(int pageNow,int pageSize) {
		return viewTeacherDao.pagedQuery("from ViewTeacher",pageNow,pageSize);		
	}

	@Override
	public Page getTeacherBySchool(int pageNow,int pageSize,int schoolid, String queryString) {
		String where = (queryString != null && !queryString.equals("")) ? " and " + queryString : "";
		return viewTeacherDao.pagedQuery("from ViewTeacher v where v.schoolId=? " + where,pageNow,pageSize,schoolid);
	}

	@Override
	public ViewTeacher getTeacher(int teacherid) {
		ViewTeacher vt = viewTeacherDao.findUniqueBy("userId", teacherid);
		return vt;
	}
	
	@Override
	public String createTeacher(TeacherForm teacher) throws Exception {		
		/*
		 * �����û��ͽ�ʦ
		 */
		User u = new User();
		teacher.fillUser(u);
		UserHelper.saveUser(userDao, u);
		UserHelper.addRole(userDao, u, roleDao, Role.ROLE_USER);
		
		Teacher t = new Teacher();
		teacher.fillTeacher(t);
		//t.setUserId(u.getUserId());
		t.setUser(u);
		fillTeacher(t, teacher);
		teacherDao.save(t);
		
		/*
		 * �����ʦ��ɫ,�����У��������У����ɫ
		 */
		UserHelper.addRole(userDao, u, roleDao, Role.ROLE_SCHOOLTEACHER);		
		
		if (t.getSchoolMaster()) {
			UserHelper.addRole(userDao, u, roleDao, Role.ROLE_SCHOOLMASTER);
		}
		return u.getUserId().toString();
	}

	@Override
	public boolean deleteTeacher(int teacherid) {
		/*
		 * ɾ����ɫ
		 */
		User u = userDao.findUniqueBy("userId", teacherid);
		if (u.getUserRoles() != null) {
			u.getUserRoles().clear();
			userDao.save(u);
		}
		
		/*
		 * ɾ����ʦ���û�
		 */
		teacherDao.removeById(teacherid);
		userDao.removeById(teacherid);	
		return true;
	}

	@Override
	public boolean modifyTeacher(TeacherForm teacher) throws Exception {
		User u = userDao.findUniqueBy("userId", teacher.getUserId());
		if (u == null)
			return false;
		
		Teacher t = teacherDao.findUniqueBy("userId", teacher.getUserId());
		if (t == null)
			return false;
		
		teacher.fillUser(u);
		userDao.save(u);	
		
		teacher.fillTeacher(t);
		fillTeacher(t, teacher);
		teacherDao.save(t);	
		
		/*
		 * ����У����ɫ
		 */
		if (t.getSchoolMaster()) {
			UserHelper.addRole(userDao, u, roleDao, Role.ROLE_SCHOOLMASTER);
		}
		else {
			UserHelper.removeRole(userDao, u, roleDao, Role.ROLE_SCHOOLMASTER);
		}
		return true;
	}
	
	private void fillTeacher(Teacher t, TeacherForm teacher) {
		t.setSchoolMaster(teacher.getSchoolMaster());
		
		School school = schoolDao.findUniqueBy("schoolId", teacher.getSchoolId());
		t.setSchool(school);
		
		TeacherState state = teacherStateDao.findUniqueBy("teacherStateId", teacher.getTeacherStateId());
		t.setTeacherState(state);
	}
	
	@Override
	public List<TeacherState> getTeacherState() {
		return teacherStateDao.getAll();
	}

	@Override
	public String selectTeacher(int userId, int schoolId) throws Exception {
		User u = userDao.findUniqueBy("userId", userId);
		if(u == null)
			throw new Exception("δ�ҵ����û�");
		School sd = schoolDao.findUniqueBy("schoolId", schoolId);
		if(sd == null)
			throw new Exception("δ�ҵ���ѧУ");
		
		Teacher t = new Teacher();
		t.setUser(u);
		t.setSchool(sd);
		t.setSchoolMaster(false);
		t.setTeacherState(teacherStateDao.findUniqueBy("teacherStateId", TeacherState.TEACHER_STATE_NORMAL));
		teacherDao.save(t);	
		
		/*
		 * �����ʦ��ɫ,�����У��������У����ɫ
		 */
		UserHelper.addRole(userDao, u, roleDao, Role.ROLE_SCHOOLTEACHER);		
		
		if (t.getSchoolMaster()) {
			UserHelper.addRole(userDao, u, roleDao, Role.ROLE_SCHOOLMASTER);
		}
		
		return u.getUserId().toString();
	}

	@Override
	public boolean cancelTeacher(int teacherId) {
		
		/*
		 * ɾ����ɫ
		 */
		
		Teacher t = teacherDao.findUniqueBy("userId", teacherId);
		User u = t.getUser();
		if (t.getSchoolMaster()) {
			
			UserHelper.removeRole(userDao, u, roleDao, Role.ROLE_SCHOOLMASTER);
		}
		UserHelper.removeRole(userDao, u, roleDao, Role.ROLE_SCHOOLTEACHER);
		teacherDao.removeById(teacherId);
		return true;
	}

	@Override
	public List<Integer> importExcel(AttachVO attachVo,Integer schoolId,String realPath) throws Exception {
		// TODO Auto-generated method stub
		List<User> users = ExcelHelper.getTeachersForExcel(attachVo,realPath);
		List<Teacher> teachers = new ArrayList<Teacher>();
		List<Integer> userIds = new ArrayList<Integer>();
		School school = schoolDao.get(schoolId);
		for(User user:users)
		{
			
			try
			{
				Teacher teacher = new Teacher();
				user.setUserGender(User.GENDER_MEN);
				user.setUserbirthday(new Timestamp(System.currentTimeMillis()));
				UserHelper.saveUser(userDao, user);
				
				UserHelper.addRole(userDao, user, roleDao, Role.ROLE_USER);
				UserHelper.addRole(userDao, user, roleDao, Role.ROLE_SCHOOLTEACHER);
				
				teacher.setUser(user);
				teacher.setSchool(school);
				teacher.setTeacherState(teacherStateDao.get(TeacherState.TEACHER_STATE_NORMAL));
				teacher.setSchoolMaster(false);
				teacherDao.save(teacher);
				
				userIds.add(user.getUserId());
			}
			catch(Exception e)
			{
				throw new Exception("���֤Ϊ"+user.getUserIdentityCode()+"���û�����");
			}

		}
		return userIds;
	}

	@Override
	public AttachVO getTeacherExcel(List<Integer> userIds,String physicalPath) {
		// TODO Auto-generated method stub
		List<ViewTeacher> viewTeachers = new ArrayList<ViewTeacher>();
		
		for(Integer userId:userIds)
		{
			ViewTeacher viewTeacher = viewTeacherDao.get(userId);
			if(viewTeacher != null)
				viewTeachers.add(viewTeacher);
		}
		AttachVO attachVo = ExcelHelper.getTeacherExcel(viewTeachers,physicalPath);
		
		
		
		return attachVo;
	}
	
	public AttachVO getAllExcelfromTeacher(Integer schoolId, String physicalPath)throws Exception {
		// TODO Auto-generated method stub
		List<ViewTeacher> viewTeachers = new ArrayList<ViewTeacher>();
		School s = schoolDao.get(schoolId);
		Set<Teacher> tt = s.getTeachers();
		for(Teacher t:tt)
		{
			ViewTeacher a = viewTeacherDao.get(t.getUserId());
			viewTeachers.add(a);
		}
		return ExcelHelper.getTeacherExcel(viewTeachers, physicalPath);
	}

}
