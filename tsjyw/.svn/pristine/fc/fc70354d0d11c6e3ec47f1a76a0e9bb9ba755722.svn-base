package edu.iasd.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demonstration.hibernate.dao.support.Page;

import edu.iasd.form.ClassMasterForm;
import edu.iasd.pojo.ViewClassMaster;

@Service
@Transactional
public interface ClassMasterService {
	
    public Page getClassMasterBySchoolClass(int pageNumber, int pageSize, int schoolClassId);
	
	public Page getAllClassMaster(int pageNow, int pageSize);
	
	public ViewClassMaster getClassMaster(int classMasterId);
	
	public String selectClassMaster(int userId, int schoolClassId) throws Exception;
	
	public Boolean cancelClassMaster(int classMasterId) throws Exception;

	public String createClassMaster(ClassMasterForm classMaster)  throws Exception;

	public boolean deleteClassMaster(int classMasterId);

	public boolean modifyClassMaster(ClassMasterForm classMaster)  throws Exception;

}
