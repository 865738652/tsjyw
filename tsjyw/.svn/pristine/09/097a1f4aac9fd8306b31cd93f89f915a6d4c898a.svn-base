package edu.iasd.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demonstration.hibernate.basedao.IBaseDao;
import com.demonstration.hibernate.dao.support.Page;

import edu.iasd.form.SchoolForm;
import edu.iasd.pojo.County;
import edu.iasd.pojo.Module;
import edu.iasd.pojo.ModuleState;
import edu.iasd.pojo.ModuleType;
import edu.iasd.pojo.Role;
import edu.iasd.pojo.School;
import edu.iasd.pojo.SchoolType;
import edu.iasd.pojo.Teacher;
import edu.iasd.pojo.User;
import edu.iasd.pojo.ViewSchool;

@Service
@Transactional
public class SchoolServiceImpl implements SchoolService{

	@Resource
	private IBaseDao<edu.iasd.pojo.County> countyDao;

	@Resource
	private IBaseDao<edu.iasd.pojo.School> schoolDao;

	@Resource
	private IBaseDao<edu.iasd.pojo.ViewSchool> viewSchoolDao;

	@Resource
	private IBaseDao<edu.iasd.pojo.SchoolType> schoolTypeDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.User> userDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.Teacher> teacherDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.Module> moduleDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.ModuleType> moduleTypeDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.ModuleState> moduleStateDao;
	
	@Override
	public Page getAllSchool(Integer userId, String queryString, int pageNow, int pageSize) {
		User user = userDao.findUniqueBy("userId", userId);
		String where = "";
		if (queryString != null && !queryString.equals(""))
			where = "and " + queryString;
		for (Object o : user.getUserRoles()) {
			Role role = (Role)o;
			if (role.getRoleId() == Role.ROLE_SUPERADMIN)
				return  viewSchoolDao.pagedQuery("from ViewSchool where 1=1 " + where, pageNow, pageSize);
			else if (role.getRoleId() == Role.ROLE_COUNTYADMIN) 
				return  viewSchoolDao.pagedQuery("select distinct v from ViewSchool v, CountyManager c where v.countyId=c.county.countyid and  c.user.userId=? " + where, pageNow, pageSize, userId);
			else if (role.getRoleId() == Role.ROLE_SCHOOLADMIN)
				return  viewSchoolDao.pagedQuery("select distinct v from ViewSchool v, SchoolManager m where v.schoolId=m.school.schoolId and  m.userId=? " + where, pageNow, pageSize, userId);
			else if (role.getRoleId() == Role.ROLE_SCHOOLMASTER) {
				Teacher t = teacherDao.findUniqueBy("userId",  userId);
				return  viewSchoolDao.pagedQuery("from ViewSchool v where v.schoolId=? " + where, pageNow, pageSize, t.getSchool().getSchoolId());
			}
		}
		return new Page();		
	}
	
	@Override
	public Page getAllSchool(int pageNow, int pageSize) {
		return  viewSchoolDao.pagedQuery("from ViewSchool", pageNow, pageSize);
	}
	
	@Override
	public Page getAllSchool(String queryString, int pageNow, int pageSize) {
		String where = "";
		if (queryString != null && !queryString.equals(""))
			where = "where " + queryString;
		return  viewSchoolDao.pagedQuery("from ViewSchool " + where, pageNow, pageSize);
	}

	@Override
	public Page getSchoolByCounty(int pageNow, int pageSize, int countyId, String queryString) {
		return viewSchoolDao.pagedQuery("from ViewSchool v where v.countyId=?",pageNow,pageSize,countyId);
	}

	@Override
	public ViewSchool getSchool(int schoolId) {
		ViewSchool vs = viewSchoolDao.findUniqueBy("schoolId", schoolId);
		return vs;
	}

	@Override
	public String createSchool(SchoolForm schoolForm) throws Exception {
		List<School> l = schoolDao.findBy("schoolName", schoolForm.getSchoolName());
		if (l != null && l.size() > 0)
			throw new Exception("学校名称重复");
		School school=new School();
		fillSchool(school, schoolForm);
		schoolDao.save(school);	
		
		/*
		 * 创建默认校园之窗栏目
		 */
		String [] moduleNames = {"首页幻灯", "图片轮显", "校园新闻", "父母学堂", "家教动态", "德育天地", "心理导航", "亲子展示"};
		Boolean [] isShow = { false, false, true, true, true, true, true, true };
		
		ModuleType t = moduleTypeDao.findUniqueBy("moduleTypeId", ModuleType.MODULE_TYPE_ARTICLE);
		ModuleState s = moduleStateDao.findUniqueBy("moduleStateId", ModuleState.MODULESTATE_NORMAL);
		
		for (int i = 0; i < moduleNames.length; i++) {
			Module module = new Module();
			module.setModuleIsShow(isShow[i]);
			module.setModuleName(moduleNames[i]);
			module.setModuleEnName(null);
			module.setModuleSerial(i);
			module.setModuleUrl(null);
			module.setParentModuleId(null);
			module.setModuleState(s);
			module.setModuleType(t);
			module.setSchool(school);			
			moduleDao.save(module);
			
			module.setModuleNumber(String.format("%03d%03d", school.getSchoolId(), module.getModuleId()));
			moduleDao.save(module);
		}
		return school.getSchoolId().toString();
	}

	@Override
	public boolean deleteSchool(int schoolId) {
		schoolDao.removeById(schoolId);	
		return true;
	}

	@Override
	public boolean modify(SchoolForm schoolForm) throws Exception {
		School school = schoolDao.findUniqueBy("schoolId", schoolForm.getSchoolId());
		if (school == null)
			return false;	
		
		if (!school.getSchoolName().equals(schoolForm.getSchoolName())) {
			List<School> l = schoolDao.findBy("schoolName", schoolForm.getSchoolName());
			if (l != null && l.size() > 0)
				throw new Exception("学校名称重复");
		}
		
		fillSchool(school, schoolForm);
		schoolDao.save(school);		
		return true;
	}

	private void fillSchool(School school, SchoolForm schoolForm) {
		school.setSchoolNumber(schoolForm.getSchoolNumber());
		school.setSchoolName(schoolForm.getSchoolName());
		school.setSchoolAddress(schoolForm.getSchoolAddress());
		school.setSchoolContactInformation(schoolForm.getSchoolContactInformation());
		school.setSchoolIntroduction(schoolForm.getSchoolIntroduction());
		school.setSchoolLogo(schoolForm.getSchoolLogo());
		
		County county = countyDao.findUniqueBy("countyId", schoolForm.getCountyId());
		school.setCounty(county);
		
		SchoolType type = schoolTypeDao.findUniqueBy("schoolTypeId", schoolForm.getSchoolTypeId());
		school.setSchoolType(type);
	}
	
	@Override
	public List<SchoolType> getSchoolType() {
		return schoolTypeDao.getAll();
	}
}
