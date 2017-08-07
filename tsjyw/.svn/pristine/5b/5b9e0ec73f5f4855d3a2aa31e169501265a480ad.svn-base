package edu.iasd.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demonstration.hibernate.dao.support.Page;

import edu.iasd.form.CourseForm;
import edu.iasd.pojo.SchoolCourseState;
import edu.iasd.pojo.ViewCourse;

@Service
@Transactional
public interface CourseManageService {
	public Page getAllCourse(int pageNow,int pageSize);
	
	public Page getCourseBySchool(int pageNumber,int pageSize,int schoolId);
	
	public ViewCourse getCourse(int schoolCourseId);
	
	public String createCourse(CourseForm schoolCourse);
	
	public boolean deleteCourse(int schoolCourseId);
	
	public boolean modifyCourse(CourseForm schoolCourse);
	
	public List<SchoolCourseState> getSchoolCourseState();
}
