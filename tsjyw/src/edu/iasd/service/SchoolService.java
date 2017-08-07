package edu.iasd.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demonstration.hibernate.dao.support.Page;

import edu.iasd.form.SchoolForm;
import edu.iasd.pojo.SchoolType;
import edu.iasd.pojo.ViewSchool;

@Service
@Transactional
public interface SchoolService {
	public Page getAllSchool(int pageNow, int pageSize);
	
	public Page getAllSchool(String queryString, int pageNow, int pageSize);
	
	public Page getAllSchool(Integer userId, String queryString, int pageNow,int pageSize);
	
	public Page getSchoolByCounty(int pageNow,int pageSize,int countyId, String queryString);

	public ViewSchool getSchool(int schoolId);
	
	public String createSchool(SchoolForm school) throws Exception;
	
	public boolean deleteSchool(int schoolId);
	
	public boolean modify(SchoolForm school) throws Exception;
	
	public List<SchoolType> getSchoolType();
	
}
