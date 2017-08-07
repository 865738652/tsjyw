package edu.iasd.service;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demonstration.hibernate.basedao.IBaseDao;
import com.demonstration.hibernate.dao.support.Page;

import edu.iasd.form.StudentForm;
import edu.iasd.pojo.Grade;
import edu.iasd.pojo.Role;
import edu.iasd.pojo.School;
import edu.iasd.pojo.SchoolClass;
import edu.iasd.pojo.Student;
import edu.iasd.pojo.StudentState;
import edu.iasd.pojo.User;
import edu.iasd.pojo.ViewStudent;
import edu.iasd.utils.ExcelHelper;
import edu.iasd.utils.UserHelper;

@Service("studentService")  
@Transactional
public class StudentServiceImpl implements StudentService {
	
	@Resource
	private IBaseDao<edu.iasd.pojo.Student> studentDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.SchoolClass> schoolClassDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.ViewStudent> viewStudentDao;

	@Resource
    private IBaseDao<edu.iasd.pojo.User> userDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.StudentState> studentStateDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.School> schoolDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.Role> roleDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.Grade> gradeDao;
	
	@Override
	public Page getStudentBySchoolClass(int pageNow, int pageSize,int schoolClassId, String queryString) {
		String where = (queryString != null && !queryString.equals("")) ? " and " + queryString : "";
		return viewStudentDao.pagedQuery("from ViewStudent v where v.schoolClassId=? " + where,pageNow,pageSize,schoolClassId);
	}
	
	@Override
	public Page getAllStudent(int pageNow, int pageSize) {
		
		return viewStudentDao.pagedQuery("from ViewStudent",pageNow,pageSize);
	}

	@Override
	public ViewStudent getStudent(int studentId) {
		
		ViewStudent vs = viewStudentDao.findUniqueBy("userId", studentId);
		return vs;
	}

	@Override
	public String selectStudent(int userId, int schoolClassId) throws Exception
	{
		User u = userDao.findUniqueBy("userId", userId);
		if(u == null)
			throw new Exception("未找到此用户");
		SchoolClass sc = schoolClassDao.findUniqueBy("schoolClassId", schoolClassId);
		if(sc == null)
			throw new Exception("未找到此班级");
		
		Student s = new Student();
		s.setUser(u);
		s.setSchoolClass(sc);
		s.setStudentState(studentStateDao.findUniqueBy("studentStateId", StudentState.STUDENT_STATE_NORMAL));
		s.setStudentCode("");
		studentDao.save(s);
		/*
		 * 赋予学生的角色
		 */
		UserHelper.addRole(userDao, u, roleDao, Role.ROLE_STUDENT);
		return s.getUserId().toString();
	}
	
	@Override
	public String createStudent(StudentForm student)  throws Exception{
		/*
		 * 创建用户和学生
		 */
		User u = new User();
		student.fillUser(u);
		UserHelper.saveUser(userDao, u);
		UserHelper.addRole(userDao, u, roleDao, Role.ROLE_USER);
		
		Student s = new Student();
		student.fillStudent(s);
		//t.setUserId(u.getUserId());
		s.setUser(u);
		fillStudent(s, student);
		studentDao.save(s);
		
		/*
		 * 赋予学生角色
		 */
		UserHelper.addRole(userDao, u, roleDao, Role.ROLE_STUDENT);
		return s.getUserId().toString();
		
/*		
		Role role = roleDao.findUniqueBy("roleId", Role.ROLE_STUDENT);
		HashSet<Role> roles = new HashSet<Role>();
		roles.add(role);
		u.setUserRoles(roles);
		userDao.save(u);
		return u.getUserId().toString();
*/
	}

	private void fillStudent(Student s, StudentForm student) {
		SchoolClass schoolClass = schoolClassDao.findUniqueBy("schoolClassId", student.getSchoolClassId());
		s.setSchoolClass(schoolClass);
		StudentState studentState = studentStateDao.findUniqueBy("studentStateId", student.getStudentStateId());
		s.setStudentState(studentState);
		
	}

	@Override
	public boolean deleteStudent(int studentId) {
		/*
		 * 删除角色
		 */
		User u = userDao.findUniqueBy("userId", studentId);
		if (u.getUserRoles() != null) {
			u.getUserRoles().clear();
			userDao.save(u);
		}
		
		/*
		 * 删除用户
		 */
		studentDao.removeById(studentId);
		userDao.removeById(studentId);	
		return true;
	}
	
	@Override
	public boolean cancelStudent(int studentId)
	{
		Student s = studentDao.findUniqueBy("userId", studentId);
		User u= s.getUser();
		UserHelper.removeRole(userDao, u, roleDao, Role.ROLE_STUDENT);
		studentDao.removeById(studentId);
		return true;
		
	}

	@Override 
	public boolean modifyStudent(StudentForm student) throws Exception {
		User u = userDao.findUniqueBy("userId", student.getUserId());
		if (u == null)
			return false;
		
		Student s = studentDao.findUniqueBy("userId", student.getUserId());
		if (s == null)
			return false;
		
		student.fillUser(u);
		userDao.save(u);	
		
		student.fillStudent(s);
		fillStudent(s, student);
		studentDao.save(s);		
		return true;
		
	}

	@Override
	public List<StudentState> getStudentState() {
		return studentStateDao.getAll();
	}
//删除一班内所有的学生
	@Override
	public boolean graduatedStudent(int schoolClassId) {		
		SchoolClass c = schoolClassDao.findUniqueBy("schoolClassId", schoolClassId);
		for (Object o : c.getStudents()) {
			Student s = (Student)o;
			User u = s.getUser();
			UserHelper.removeRole(userDao, u, roleDao, Role.ROLE_STUDENT);
			studentDao.remove(s);
		}
		return true;
	}

	@Override
	public void goUpStudent(int schoolClassId, int newSchoolClassId)  {
		SchoolClass c = schoolClassDao.findUniqueBy("schoolClassId", schoolClassId);
		SchoolClass n = schoolClassDao.findUniqueBy("schoolClassId", newSchoolClassId);
		for (Object o : c.getStudents()) {
			Student s = (Student)o;
			s.setSchoolClass(n);
			studentDao.save(s);
		}	
		//s.setSchoolClass(schoolClassDao.findUniqueBy("SchoolClassId", studentForm.getSchoolClassId()));
		//s.setGrade(gradeDao.findUniqueBy("gradeId", classForm.getGradeId()));
		
		
		return ;
	}
	@Override
	public List<Integer> getStudentforExcel(Integer schoolClassId, InputStream inputStream) throws Exception {
		// TODO Auto-generated method stub
		
		List<Integer> userIds = new ArrayList<Integer>();
		
		
		List<User> users = ExcelHelper.getStudentsforExcel(inputStream);
		SchoolClass schoolClass = schoolClassDao.get(schoolClassId);
		StudentState studentState = studentStateDao.get(StudentState.STUDENT_STATE_NORMAL);
		Set<Role> roles = new HashSet<Role>();
		roles.add(roleDao.get(Role.ROLE_STUDENT));
		roles.add(roleDao.get(Role.ROLE_USER));
		for(User user:users)
		{
			user.setUserRoles(roles);
			UserHelper.saveUser(userDao, user);
			userIds.add(user.getUserId());
		}
		for(User user:users)
		{
			Student student = new Student();
			student.setSchoolClass(schoolClass);
			student.setStudentState(studentState);
			student.setStudentCode(user.getUserIdentityCode());
			student.setUser(user);
			studentDao.save(student);
		}
		
		
		
		return userIds;
	}

	@Override
	public File getExcelforStudent(List<Integer> userIds,String physicalPath) throws Exception {
		// TODO Auto-generated method stub
		
		List<ViewStudent> viewStudents = new ArrayList<ViewStudent>();
		
		for(Integer userId:userIds)
		{
			ViewStudent viewStudent = viewStudentDao.get(userId);
			viewStudents.add(viewStudent);
		}
		return ExcelHelper.getExcelforStudent(viewStudents,physicalPath);
	}

	@Override
	public List<Integer> getSchoolStudentforExcel(Integer schoolId,
			InputStream inputStream) throws Exception {
		// TODO Auto-generated method stub
		School school = schoolDao.get(schoolId);

		Map<String,Map<String,Integer>> gradeSchoolClassMap = new HashMap<String,Map<String,Integer>>();
		Set<Grade> grades = school.getGrades();
		for(Grade grade:grades)
		{
			Map<String,Integer> m = new HashMap<String,Integer>();
			Set<SchoolClass> schoolClasses = grade.getSchoolClasses();
			for(SchoolClass schoolClass:schoolClasses)
			{
				m.put(schoolClass.getSchoolClassName(), schoolClass.getSchoolClassId());
			}
			gradeSchoolClassMap.put(grade.getGradeName(), m);
		}
		
		List<Integer> userIds = ExcelHelper.getSchoolStudentsforExcel(gradeSchoolClassMap,inputStream,studentDao,roleDao,userDao,schoolClassDao,studentStateDao);

		return userIds;
	}

	@Override
	public File getAllExcelfromStudent(Integer classIds, String physicalPath)
			throws Exception {
		// TODO Auto-generated method stub
		List<ViewStudent> viewStudents = new ArrayList<ViewStudent>();
		SchoolClass c = schoolClassDao.get(classIds);
		Set<Student> ss = c.getStudents();
		for(Student s:ss)
		{
			ViewStudent a = viewStudentDao.get(s.getUserId());
			viewStudents.add(a);
		}
		return ExcelHelper.getExcelforStudent(viewStudents,physicalPath);
	}

	@Override
	public File getSchoolExcelfromStudent(Integer schoolId, String physicalPath)
		throws Exception {
		// TODO Auto-generated method stub
		List<ViewStudent> viewStudents = viewStudentDao.findBy("schoolId", schoolId);
		return ExcelHelper.getExcelforStudent(viewStudents,physicalPath);
	}
} 
