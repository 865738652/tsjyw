package edu.iasd.service;

import java.util.List;


import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demonstration.hibernate.basedao.IBaseDao;
import com.demonstration.hibernate.dao.support.Page;

import edu.iasd.form.CourseForm;
import edu.iasd.pojo.SchoolCourse;
import edu.iasd.pojo.SchoolCourseState;
import edu.iasd.pojo.ViewCourse;

@Service
@Transactional
public class CourseManageServiceImpl implements CourseManageService{

	@Resource
	private IBaseDao<edu.iasd.pojo.SchoolCourse> schoolCourseDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.ViewCourse> viewCourseDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.SchoolCourseState> schoolCourseStateDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.School> schoolDao;
	
	@Override
	public Page getAllCourse(int pageNow, int pageSize) {
		// TODO Auto-generated method stub
		return viewCourseDao.pagedQuery("from ViewCourse", pageNow, pageSize);
	}

	public Page getCourseBySchool(int pageNumber, int pageSize, int schoolId) {
		// TODO Auto-generated method stub
		return viewCourseDao.pagedQuery("from ViewCourse v where v.schoolId=?", pageNumber, pageSize,schoolId);
	}

	@Override
	public ViewCourse getCourse(int schoolCourseId) {
		// TODO Auto-generated method stub
		ViewCourse vc=viewCourseDao.findUniqueBy("schoolCourseId", schoolCourseId);
		return vc;
	}

	//´´½¨
	@Override
	public String createCourse(CourseForm schoolCourse) {
		// TODO Auto-generated method stub
		SchoolCourse c=new SchoolCourse();
		c.setSchoolCourseName(schoolCourse.getSchoolCourseName());
		c.setSchool(schoolDao.findUniqueBy("schoolId", schoolCourse.getSchoolId()));
		c.setSchoolCourseState(schoolCourseStateDao.findUniqueBy("schoolCourseStateId", schoolCourse.getSchoolCourseStateId()));
		schoolCourseDao.save(c);
		return c.getSchoolCourseId().toString();
	}

	//É¾³ý
	@Override
	public boolean deleteCourse(int schoolCourseId) {
		// TODO Auto-generated method stub
		schoolCourseDao.removeById(schoolCourseId);
		return true;
	}

	//ÐÞ¸Ä
	@Override
	public boolean modifyCourse(CourseForm schoolCourse) {
		// TODO Auto-generated method stub
		SchoolCourse c=schoolCourseDao.findUniqueBy("schoolCourseId", schoolCourse.getSchoolCourseId());
		if(c == null)			
			return false;
		c.setSchoolCourseName(schoolCourse.getSchoolCourseName());
		c.setSchool(schoolDao.findUniqueBy("schoolId", schoolCourse.getSchoolId()));
		c.setSchoolCourseState(schoolCourseStateDao.findUniqueBy("schoolCourseStateId", schoolCourse.getSchoolCourseStateId()));
		schoolCourseDao.save(c);
		return true;
	}
	
	@Override
	public List<SchoolCourseState> getSchoolCourseState() {
		return schoolCourseStateDao.getAll();
	}
}
