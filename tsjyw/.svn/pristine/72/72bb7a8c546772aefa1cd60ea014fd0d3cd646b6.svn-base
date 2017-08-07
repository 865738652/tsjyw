package edu.iasd.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demonstration.hibernate.basedao.IBaseDao;
import com.demonstration.hibernate.dao.support.Page;

import edu.iasd.form.ClassForm;
import edu.iasd.pojo.Grade;
import edu.iasd.pojo.Role;
import edu.iasd.pojo.User;
import edu.iasd.pojo.ViewSchoolClass;
import edu.iasd.pojo.SchoolClass;

@Service
@Transactional
public class SchoolClassServiceImpl implements SchoolClassService {

	@Resource
	private IBaseDao<edu.iasd.pojo.SchoolClass> schoolClassDao;
	
	@Resource
	private IBaseDao<Grade> gradeDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.ViewSchoolClass> viewSchoolClassDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.User> userDao;
		
	@Override
	public Page getAllSchoolClass(int pageNow, int pageSize) {
		return viewSchoolClassDao.pagedQuery("from ViewSchoolClass",pageNow,pageSize);		
	}

	@Override
	public Page getSchoolClassBySchool(int userId, int pageNumber, int pageSize, int schoolId, String queryString) {
		User user = userDao.findUniqueBy("userId", userId);
		String where = "";
		if (queryString != null && !queryString.equals(""))
			where = "and " + queryString;
		for (Object o : user.getUserRoles()) {
			Role role = (Role)o;
			if (role.getRoleId() == Role.ROLE_SUPERADMIN || role.getRoleId() == Role.ROLE_COUNTYADMIN ||
				role.getRoleId() == Role.ROLE_SCHOOLADMIN || role.getRoleId() == Role.ROLE_SCHOOLMASTER)
				return  viewSchoolClassDao.pagedQuery("from ViewSchoolClass  v where v.schoolId=? " + where, pageNumber, pageSize,schoolId);
			else if (role.getRoleId() == Role.ROLE_GRADEMASTER) {
				return  viewSchoolClassDao.pagedQuery("select distinct v from ViewSchoolClass v, GradeMaster m where v.gradeId=m.grade.gradeId and v.schoolId=? and m.teacher.userId=? " + where,pageNumber,pageSize,schoolId,userId);
			}
			else if (role.getRoleId() == Role.ROLE_CLASSMASTER) {
				return  viewSchoolClassDao.pagedQuery("select distinct v from ViewSchoolClass v, ClassMaster m where v.schoolClassId=m.schoolClass.schoolClassId and v.schoolId=? and m.teacher.userId=? " + where,pageNumber,pageSize,schoolId,userId);
			}
		}
		return new Page();
	}
	
	@Override
	public Page getSchoolClassByGrade(int pageNumber, int pageSize, int gradeId) {
		return viewSchoolClassDao.pagedQuery("from ViewSchoolClass where gradeId=?",pageNumber, pageSize, gradeId);	
	}
	
	@Override
	public ViewSchoolClass getSchoolClass(int schoolClassId) {
		ViewSchoolClass vc = viewSchoolClassDao.findUniqueBy("schoolClassId", schoolClassId);
		return vc;
	}

	@Override
	public String createSchoolClass(ClassForm classForm) throws Exception {
		List<SchoolClass> l = schoolClassDao.find("from SchoolClass where schoolClassName=? and gradeId=?", classForm.getSchoolClassName(), classForm.getGradeId());
		if (l != null && l.size() > 0)
			throw new Exception("班级名称重复");
		
		SchoolClass c = new SchoolClass();
		c.setSchoolClassName(classForm.getSchoolClassName());
		c.setGrade(gradeDao.findUniqueBy("gradeId", classForm.getGradeId()));
		schoolClassDao.save(c);
		return c.getSchoolClassId().toString();
	}

	@Override
	public boolean modifySchoolClass(ClassForm classForm) throws Exception {
		SchoolClass c = schoolClassDao.findUniqueBy("schoolClassId", classForm.getSchoolClassId());
		if(c == null)			
			return false;
		
		if (!c.getSchoolClassName().equals(classForm.getSchoolClassName())) {
			List<SchoolClass> l = schoolClassDao.find("from SchoolClass where schoolClassName=? and gradeId=?", classForm.getSchoolClassName(), classForm.getGradeId());
			if (l != null && l.size() > 0)
				throw new Exception("班级名称重复");
		}
		c.setSchoolClassName(classForm.getSchoolClassName());
		c.setGrade(gradeDao.findUniqueBy("gradeId", classForm.getGradeId()));
		schoolClassDao.save(c);
		return true;	
	}

	@Override
	public boolean deleteSchoolClass(int schoolClassId) {
		schoolClassDao.removeById(schoolClassId);		
		return true;
	}
}
