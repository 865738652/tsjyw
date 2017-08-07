package edu.iasd.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demonstration.hibernate.basedao.IBaseDao;
import com.demonstration.hibernate.dao.support.Page;

import edu.iasd.form.GradeForm;
import edu.iasd.pojo.Grade;
import edu.iasd.pojo.Role;
import edu.iasd.pojo.School;
import edu.iasd.pojo.GradeMaster;
import edu.iasd.pojo.Teacher;
import edu.iasd.pojo.User;
import edu.iasd.pojo.ViewGrade;

@Service
@Transactional
public class GradeServiceImpl implements GradeService{
	
	@Resource
	private IBaseDao<edu.iasd.pojo.School> schoolDao;

	@Resource
	private IBaseDao<edu.iasd.pojo.Grade> gradeDao;

	@Resource
	private IBaseDao<edu.iasd.pojo.ViewGrade> viewGradeDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.User> userDao;
	
	@Override
	public Page getAllGrade(int pageNow, int pageSize) {
		return viewGradeDao.pagedQuery("from ViewGrade",pageNow,pageSize);		
	}

	@Override
	public Page getGradeBySchool(int userId, int pageNumber, int pageSize, int schoolId) {
		User user = userDao.findUniqueBy("userId", userId);
		for (Object o : user.getUserRoles()) {
			Role role = (Role)o;
			if (role.getRoleId() == Role.ROLE_SUPERADMIN || role.getRoleId() == Role.ROLE_COUNTYADMIN ||
				role.getRoleId() == Role.ROLE_SCHOOLADMIN || role.getRoleId() == Role.ROLE_SCHOOLMASTER)
				return  viewGradeDao.pagedQuery("from ViewGrade v where v.schoolId=?",pageNumber,pageSize,schoolId);
			else if (role.getRoleId() == Role.ROLE_GRADEMASTER) {
				return  viewGradeDao.pagedQuery("select distinct v from ViewGrade v, GradeMaster m where v.gradeId=m.grade.gradeId and v.schoolId=? and m.teacher.userId=?",pageNumber,pageSize,schoolId,userId);
			}
		}
		return new Page();
	}

	@Override
	public ViewGrade getGrade(int gradeId) {
		ViewGrade vg = viewGradeDao.findUniqueBy("gradeId", gradeId);
		return vg;
	}

	@Override
	public String createGrade(GradeForm grade) throws Exception {
		List<Grade> l = (List<Grade>)gradeDao.find("from Grade where gradeName=? and schoolId=?", grade.getGradeName(), grade.getSchoolId());
		if (l != null && l.size() > 0)
			throw new Exception("年级名称重复");
		Grade g=new Grade();
		fillGrade(g, grade);
		gradeDao.save(g);	
		return g.getGradeId().toString();
	}
	/*
	@Override
	public String createGrade(GradeForm grade) {
		Grade g = new Grade();
		g.setGradeName(grade.getGradeName());
		//g.setGradeMasters(grade.getGradeMasters());
		gradeDao.save(g);
		return g.getGradeId().toString();
	}*/
	
	@Override
	public boolean deleteGrade(int gradeId) {
		gradeDao.removeById(gradeId);	
		return true;
	}

	@Override
	public boolean modifyGrade(GradeForm grade) throws Exception {
		Grade g = gradeDao.findUniqueBy("gradeId", grade.getGradeId());
		if (grade == null)
			return false;
		
		if (!g.getGradeName().equals(grade.getGradeName())) {
			List<Grade> l = (List<Grade>)gradeDao.find("from Grade where gradeName=? and schoolId=?", grade.getGradeName(), grade.getSchoolId());
			if (l != null && l.size() > 0)
				throw new Exception("年级名称重复");
		}
		grade.fillGrade(g);
		fillGrade(g, grade);
		gradeDao.save(g);		
		return true;
	}
	/*
	@Override
	public boolean modifyGrade(GradeForm grade) {
		Grade g = gradeDao.findUniqueBy("gradeId", grade.getGradeId());
		if (grade == null)
			return false;
		g.setGradeName(grade.getGradeName());
		//fillGrade(grade, gradeForm);
		gradeDao.save(g);		
		return true;
	}*/

	
	private void fillGrade(Grade g, GradeForm grade) {
		g.setGradeId(grade.getGradeId());
		g.setGradeName(grade.getGradeName());
		
		School school = schoolDao.findUniqueBy("schoolId", grade.getSchoolId());
		g.setSchool(school);
		
	}

	@Override
	public List<GradeMaster> getGradeMaster() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
