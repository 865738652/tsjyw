package edu.iasd.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demonstration.hibernate.dao.support.Page;

import edu.iasd.form.ClassForm;
import edu.iasd.pojo.ViewSchoolClass;

@Service
@Transactional
public interface SchoolClassService {
	public Page getSchoolClassBySchool(int userId, int pageNow, int pageSize, int schoolId, String queryString);
	
	public Page getSchoolClassByGrade(int pageNow, int pageSize, int gradeId);
	
	public ViewSchoolClass getSchoolClass(int schoolClassId);
	
	public String createSchoolClass(ClassForm classForm) throws Exception ;
	
	public boolean modifySchoolClass(ClassForm classForm) throws Exception ;

	public boolean deleteSchoolClass(int schoolClassId);

	public Page getAllSchoolClass(int pageNow, int pageSize);

}
  