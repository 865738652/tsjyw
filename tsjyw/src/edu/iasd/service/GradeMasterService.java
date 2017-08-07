package edu.iasd.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demonstration.hibernate.dao.support.Page;

import edu.iasd.form.GradeMasterForm;
import edu.iasd.pojo.ViewGradeMaster;

@Service
@Transactional
public interface GradeMasterService {
	

	public Page getGradeMasterByGrade(int pageNumber, int pageSize, int gradeId);
	
	public Page getAllGradeMaster(int pageNow, int pageSize);
	
	public ViewGradeMaster getGradeMaster(int gradeMasterId);
	
	public String selectGradeMaster(int userId, int gradeId) throws Exception;
	
	public Boolean cancelGradeMaster(int gradeMasterId) throws Exception;

	public String createGradeMaster(GradeMasterForm gradeMaster)  throws Exception;

	public boolean deleteGradeMaster(int gradeMasterId);

	public boolean modifyGradeMaster(GradeMasterForm gradeMaster) throws Exception;

	

}
