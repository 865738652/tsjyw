package edu.iasd.service;



import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demonstration.hibernate.dao.support.Page;




import edu.iasd.pojo.ViewSchoolManager;
import edu.iasd.form.SchoolManagerForm;

@Service("schoolManagerService")
@Transactional
public interface SchoolManagerService {
	public Page getAllSchoolManager(int pageNow,int pageSize);
	
	public Page getSchoolManagerBySchool(int pageNow,int pageSize,int schoolid);
	
	public ViewSchoolManager getSchoolManager(int schoolManagerId);
	
	public String selectSchoolManager(int schoolManagerId, int schoolId) throws Exception;
	
	public String createSchoolManager(SchoolManagerForm schoolManager) throws Exception;
	
	public boolean deleteSchoolManager(int UserId);
	
	public boolean cancelSchoolManager(int schoolManagerId);

	boolean modifySchoolManager(SchoolManagerForm schoolManager) throws Exception;


}
