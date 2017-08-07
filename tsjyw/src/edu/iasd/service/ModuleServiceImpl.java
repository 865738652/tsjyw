package edu.iasd.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demonstration.hibernate.basedao.IBaseDao;

import edu.iasd.form.ModuleForm;
import edu.iasd.pojo.Module;
import edu.iasd.pojo.ModuleState;
import edu.iasd.pojo.ModuleType;
import edu.iasd.pojo.School;
import edu.iasd.pojo.ViewModule;

@Service
@Transactional
public class ModuleServiceImpl implements ModuleService {

	@Resource
	private IBaseDao<edu.iasd.pojo.Module> moduleDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.ViewModule> viewModuleDao;	
	
	@Resource
	private IBaseDao<edu.iasd.pojo.ModuleType> moduleTypeDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.ModuleState> moduleStateDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.School> schoolDao;	

	@Override
	public List<ViewModule> getAllModule() {
		return (List<ViewModule>)viewModuleDao.find("from ViewModule v where v.schoolId is NULL", null);
	}
	
	@Override
	public List<ViewModule> getAllModuleBySchool(int schoolId) {
		return (List<ViewModule>)viewModuleDao.find("from ViewModule v where v.schoolId=? ", schoolId);
	}
	
	@Override
	public List<ViewModule> getTopModule(String queryString) {
		String where = (queryString != null && !queryString.equals("")) ? " and " + queryString : "";
		return (List<ViewModule>)viewModuleDao.find("from ViewModule v where v.schoolId is NULL and v.parentModuleId is NULL " + where, null);
	}

	@Override
	public List<ViewModule> getSchoolTopModule(String queryString, int schoolId) {
		String where = (queryString != null && !queryString.equals("")) ? " and " + queryString : "";
		return (List<ViewModule>)viewModuleDao.find("from ViewModule v where v.schoolId=? and v.parentModuleId is NULL " + where, schoolId);
	}

	@Override
	public List<ViewModule> getChildModule(int parentId) {
		return viewModuleDao.findBy("parentModuleId", parentId);
	}

	@Override
	public ViewModule getModule(int moduleId) {
		ViewModule m = viewModuleDao.findUniqueBy("moduleId", moduleId);
		m.setChildrenModule(viewModuleDao.findBy("parentModuleId", m.getModuleId()));
		return m;
	}

	@Override
	public String createModule(ModuleForm module) {
		Module m = new Module();
		fillModule(m, module);
		module.setModuleNumber("000");
		moduleDao.save(m);
		
		if (m.getParentModuleId() == null) {
			if (m.getSchool() == null)
				m.setModuleNumber(String.format("%06d", m.getModuleId()));
			else
				m.setModuleNumber(String.format("%03d%03d", m.getSchool().getSchoolId(), m.getModuleId()));
		}
		else {
			Module parent = moduleDao.findUniqueBy("moduleId", m.getParentModuleId());
			m.setModuleNumber(parent.getModuleNumber() + String.format("%03d", m.getModuleId()));
		}
		moduleDao.save(m);
		return m.getModuleId().toString();
	}

	@Override
	public boolean deleteModule(int moduleId) {
		Module m = moduleDao.findUniqueBy("moduleId", moduleId);
		moduleDao.remove(m);
		return true;
	}

	@Override
	public boolean modifyModule(ModuleForm module) {
		Module m = moduleDao.findUniqueBy("moduleId", module.getModuleId());
		fillModule(m, module);
		moduleDao.save(m);
		return true;
	}

	@Override
	public List<ModuleState> getModuleState() {
		return moduleStateDao.getAll();
	}

	@Override
	public List<ModuleType> getModuleType() {
		return moduleTypeDao.getAll();
	}
	
	private void fillModule(Module module, ModuleForm form) {
		ModuleType t = moduleTypeDao.findUniqueBy("moduleTypeId", form.getModuleTypeId());
		ModuleState s = moduleStateDao.findUniqueBy("moduleStateId", form.getModuleStateId());
		School h = form.getSchoolId() == null ? null : schoolDao.findUniqueBy("schoolId", form.getSchoolId());
		
		//module.setModuleId(form.getModuleId());
		module.setModuleIsShow(form.getModuleIsShow());
		module.setModuleName(form.getModuleName());
		module.setModuleEnName(form.getModuleEnName());
		//module.setModuleNumber(form.getModuleNumber());
		module.setModuleSerial(form.getModuleSerial());
		module.setModuleUrl(form.getModuleUrl());
		module.setParentModuleId(form.getParentModuleId());
		module.setModuleState(s);
		module.setModuleType(t);
		module.setSchool(h);
	}
	
	public List<ViewModule> getNavigatorMenu() {
		List<ViewModule> top = (List<ViewModule>)viewModuleDao.find("from ViewModule v where v.schoolId is NULL and v.moduleIsShow = true", null);
		for (ViewModule p : top)
			p.setChildrenModule(viewModuleDao.find("from ViewModule v where v.parentModuleId=? and v.moduleIsShow = true", p.getModuleId()));
		return top;
	}
	
	public List<ViewModule> getSchoolNavigatorMenu(int schoolId) {
		List<ViewModule> top = (List<ViewModule>)viewModuleDao.find("from ViewModule v where v.schoolId=? and v.moduleIsShow = true", schoolId);
		for (ViewModule p : top) {
			List<ViewModule> child = viewModuleDao.find("from ViewModule v  where v.parentModuleId=? and v.moduleIsShow = true", p.getModuleId());
			p.setChildrenModule(child);
		}
		return top;
	}
}
