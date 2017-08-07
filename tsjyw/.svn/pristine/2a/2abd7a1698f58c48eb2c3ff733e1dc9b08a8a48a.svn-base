package edu.iasd.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.iasd.form.ModuleForm;
import edu.iasd.pojo.ModuleState;
import edu.iasd.pojo.ModuleType;
import edu.iasd.pojo.ViewModule;

@Service
@Transactional
public interface ModuleService {
	public List<ViewModule> getAllModule();
	
	public List<ViewModule> getAllModuleBySchool(int schoolId);
	
	public List<ViewModule> getTopModule(String queryString);
	
	public List<ViewModule> getSchoolTopModule(String queryString, int schoolId);
	
	public List<ViewModule> getChildModule(int parentId);

	public ViewModule getModule(int moduleId);
	
	public String createModule(ModuleForm module);
	
	public boolean deleteModule(int moduleId);
	
	public boolean modifyModule(ModuleForm module);
	
	public List<ModuleState> getModuleState();
	
	public List<ModuleType> getModuleType();
	
	public List<ViewModule> getNavigatorMenu();
	
	public List<ViewModule> getSchoolNavigatorMenu(int schoolId);
}
