package edu.iasd.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demonstration.hibernate.basedao.IBaseDao;
import com.demonstration.hibernate.dao.support.Page;

import edu.iasd.controller.JsonResult;
import edu.iasd.form.AgeLevelVO;
import edu.iasd.form.AskQuestionTypeVO;
import edu.iasd.form.VolunteerForm;
import edu.iasd.pojo.AgeLevel;
import edu.iasd.pojo.AskQuestionType;
import edu.iasd.pojo.FamousTeacher;
import edu.iasd.pojo.Role;
import edu.iasd.pojo.User;
import edu.iasd.pojo.ViewFamousTeacher;
import edu.iasd.pojo.ViewVolunteer;
import edu.iasd.pojo.Volunteer;
import edu.iasd.utils.UserHelper;

@Service
@Transactional
public class VolunteerServiceImpl implements VolunteerService {

	@Resource
	private IBaseDao<edu.iasd.pojo.User> userDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.Volunteer> volunteerDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.ViewVolunteer> viewVolunteerDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.AgeLevel> ageLevelDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.AskQuestionType> askQuestionTypeDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.Role> roleDao;
		
	@Override
	public Page getAllVolunteer(String queryString, int pageNumber, int pageSize) {
		String where = (queryString != null && !queryString.equals("")) ? " where " + queryString : "";
		return viewVolunteerDao.pagedQuery("from ViewVolunteer v " + where, pageNumber, pageSize);
	}

	@Override
	public ViewVolunteer getVolunteer(int volunteerId) {
		// TODO Auto-generated method stub\
		ViewVolunteer vf=viewVolunteerDao.findUniqueBy("userId", volunteerId);
		Volunteer t=volunteerDao.findUniqueBy("userId", volunteerId);
		vf.setAgeLevels(AgeLevelVO.convert(t.getVolunteerAgeLevels()));
		vf.setAskQuestionTypes(AskQuestionTypeVO.convert(t.getVolunteerAskQuestionTypes()));
		return vf;
	}

	@Override
	public String selectVolunteer(int userId) throws Exception {
		// TODO Auto-generated method stub
		User u = userDao.findUniqueBy("userId", userId);
		if(u == null)
			throw new Exception("Unknow user");
		
		Volunteer t = new Volunteer();
		t.setUser(u);
		t.setVolunteerIntro("");
		t.setVolunteerLocation("");
		volunteerDao.save(t);
		
		UserHelper.addRole(userDao, u, roleDao, Role.ROLE_VOLUNTEER);
		
		return t.getUserId().toString();
	}

	@Override
	public String createVolunteer(VolunteerForm form) throws Exception {
		User user = new User();
		form.fillUser(user);
		UserHelper.saveUser(userDao, user);
		UserHelper.addRole(userDao, user, roleDao, Role.ROLE_USER);
		
		Volunteer volunteer = new Volunteer();
		fillVolunteer(volunteer, form);
		volunteer.setUser(user);
		volunteerDao.save(volunteer);
		
		UserHelper.addRole(userDao, user, roleDao, Role.ROLE_VOLUNTEER);
		return volunteer.getUserId().toString();
	}

	@Override
	public Boolean deleteVolunteer(int volunteerId) {
		User u = userDao.findUniqueBy("userId", volunteerId);
		if (u.getUserRoles() != null) {
			u.getUserRoles().clear();
			userDao.save(u);
		}
		
		volunteerDao.removeById(volunteerId);
		userDao.removeById(volunteerId);
		return true;
	}

	@Override
	public Boolean modifyVolunteer(VolunteerForm form) throws Exception {
		// TODO Auto-generated method stub
		User user = userDao.findUniqueBy("userId", form.getUserId());
		form.fillUser(user);
		UserHelper.saveUser(userDao, user);
		
		Volunteer volunteer = volunteerDao.findUniqueBy("userId", form.getUserId());
		volunteer.getVolunteerAgeLevels().clear();
		volunteer.getVolunteerAskQuestionTypes().clear();
		volunteerDao.save(volunteer);
		fillVolunteer(volunteer, form);
		volunteerDao.save(volunteer);
		return true;
	}

	@Override
	public Boolean cancelVolunteer(int volunteerId) {
		Volunteer t = volunteerDao.findUniqueBy("userId", volunteerId);
		User u = t.getUser();		
		UserHelper.removeRole(userDao, u, roleDao, Role.ROLE_VOLUNTEER);
		volunteerDao.removeById(volunteerId);
		return true;
	}
	
	private void fillVolunteer(Volunteer volunteer,VolunteerForm form){
		volunteer.setVolunteerIntro(form.getVolunteerIntro());
		volunteer.setVolunteerLocation(form.getVolunteerLocation());
		
		HashSet<AskQuestionType> newTypes = new HashSet<AskQuestionType>();
		if (form.getAskQuestionTypes() != null) {
			for (Integer t : form.getAskQuestionTypes()) {
				newTypes.add(askQuestionTypeDao.findUniqueBy("askQuestionTypeId", t));
			}			
		}
		volunteer.setVolunteerAskQuestionTypes(newTypes);
		
		HashSet<AgeLevel> newAgeLevels = new HashSet<AgeLevel>();
		if (form.getAgeLevels() != null) {
			for (Integer l : form.getAgeLevels()) {
				newAgeLevels.add(ageLevelDao.findUniqueBy("ageLevelId", l));
			}
		}
		volunteer.setVolunteerAgeLevels(newAgeLevels);
	}

	@SuppressWarnings( "unchecked")
	@Override
	public List<ViewVolunteer> searchVolunteer(String volunteerPCC, int ageLevelId, int askQuestionTypeId, String userName) {
		String hql = "from ViewVolunteer v where 1=1 ";
		if (volunteerPCC != null && !volunteerPCC.equals("省市县/区") && volunteerPCC.length() > 0)
			hql += " and volunteerPCC='" + volunteerPCC + "' ";
		if (userName != null && userName.length() > 0)
			hql += " and userName like '%" + userName + "%' ";
		List<ViewVolunteer> list = (List<ViewVolunteer>)viewVolunteerDao.find(hql);
		if (ageLevelId < 0 && askQuestionTypeId < 0)
			return list;
		
		List<ViewVolunteer> result = new ArrayList<ViewVolunteer>();
		for (ViewVolunteer v : list) {
			Volunteer vt = volunteerDao.findUniqueBy("userId", v.getUserId());
			
			if (ageLevelId >= 0) {
				boolean added = false;
				for (Object o : vt.getVolunteerAgeLevels()) {
					AgeLevel a = (AgeLevel)o;
					if (a.getAgeLevelId() == ageLevelId) {
						added = true;
						break;
					}
				}
				if (!added)
					continue;
			}
			
			if (askQuestionTypeId >= 0) {
				boolean added = false;
				for (Object o : vt.getVolunteerAskQuestionTypes()) {
					AskQuestionType a = (AskQuestionType)o;
					if (a.getAskQuestionTypeId() == askQuestionTypeId) {
						added = true;
						break;
					}
				}
				if (!added)
					continue;
			}
			
			result.add(v);
		}
		
		return result;
	}
	
	
	public Page searchVolunteer(int pageNumber, int pageSize, String volunteerPCC, int ageLevelId, int askQuestionTypeId) {
		String hql = "from ViewVolunteer v where 1=1 ";
		if (volunteerPCC != null && volunteerPCC.length() > 0 && !volunteerPCC.equals("all"))
			hql += " and volunteerPCC='" + volunteerPCC + "' ";
		
		List<ViewVolunteer> list = (List<ViewVolunteer>)viewVolunteerDao.find(hql);
		List<ViewVolunteer> result = new ArrayList<ViewVolunteer>();
		for (ViewVolunteer v : list) {
			Volunteer vt = volunteerDao.findUniqueBy("userId", v.getUserId());
			
			if (ageLevelId >= 0) {
				boolean added = false;
				for (Object o : vt.getVolunteerAgeLevels()) {
					AgeLevel a = (AgeLevel)o;
					if (a.getAgeLevelId() == ageLevelId) {
						added = true;
						break;
					}
				}
				if (!added)
					continue;
			}
			
			if (askQuestionTypeId >= 0) {
				boolean added = false;
				for (Object o : vt.getVolunteerAskQuestionTypes()) {
					AskQuestionType a = (AskQuestionType)o;
					if (a.getAskQuestionTypeId() == askQuestionTypeId) {
						added = true;
						break;
					}
				}
				if (!added)
					continue;
			}
			
			result.add(v);
		}
		
		int start = (pageNumber - 1) * pageSize;
		
		List<ViewVolunteer> pageResult = new ArrayList<ViewVolunteer>();
		int count = 0;
		while(count < pageSize)
		{
			if(start+count == result.size())
				break;
			pageResult.add(result.get(start+count));
			count++;
		}
		
		Page page = new Page(start, pageResult.size(), pageSize, pageResult);		
		return page;
	}
}

