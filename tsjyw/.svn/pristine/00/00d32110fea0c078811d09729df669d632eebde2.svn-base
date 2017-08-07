package edu.iasd.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demonstration.hibernate.basedao.IBaseDao;
import com.demonstration.hibernate.dao.support.Page;

import edu.iasd.controller.JsonResult;
import edu.iasd.form.AgeLevelVO;
import edu.iasd.form.AskQuestionTypeVO;
import edu.iasd.form.FamousTeacherForm;
import edu.iasd.pojo.AgeLevel;
import edu.iasd.pojo.AskQuestionType;
import edu.iasd.pojo.FamousTeacher;
import edu.iasd.pojo.Grade;
import edu.iasd.pojo.GradeMaster;
import edu.iasd.pojo.Role;
import edu.iasd.pojo.Teacher;
import edu.iasd.pojo.User;
import edu.iasd.pojo.ViewFamousTeacher;
import edu.iasd.pojo.ViewGradeMaster;
import edu.iasd.pojo.ViewVolunteer;
import edu.iasd.pojo.Volunteer;
import edu.iasd.utils.UserHelper;

@Service
@Transactional
public class FamousTeacherServiceImpl implements FamousTeacherService {

	@Resource
	private IBaseDao<edu.iasd.pojo.User> userDao;

	@Resource
	private IBaseDao<edu.iasd.pojo.FamousTeacher> famousTeacherDao;

	@Resource
	private IBaseDao<edu.iasd.pojo.ViewFamousTeacher> viewFamousTeacherDao;

	@Resource
	private IBaseDao<edu.iasd.pojo.AgeLevel> ageLevelDao;

	@Resource
	private IBaseDao<edu.iasd.pojo.AskQuestionType> askQuestionTypeDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.Role> roleDao;

	@Override
	public Page getAllFamousTeacher(String queryString, int pageNumber, int pageSize) {
		if (queryString == null || queryString.equals(""))
			return viewFamousTeacherDao.pagedQuery("from ViewFamousTeacher v order by v.recommend desc", pageNumber, pageSize);
		else {
			return viewFamousTeacherDao.pagedQuery("from ViewFamousTeacher v where " + queryString + " order by v.recommend desc", pageNumber, pageSize);
		}
	}
	
	@Override
	public Page searchFamousTeacher(int pageNumber, int pageSize, int askQuestionTypeId, String userName)
	{
		String hql = "from ViewFamousTeacher v where 1=1 ";
		if (userName != null && userName.length() > 0)
			hql += " and userName like '%" + userName + "%' ";
		List<ViewFamousTeacher> list = (List<ViewFamousTeacher>)viewFamousTeacherDao.find(hql);
		if (askQuestionTypeId >= 0) {		
			List<ViewFamousTeacher> temp = new ArrayList<ViewFamousTeacher>();
			for (ViewFamousTeacher v : list) {
				FamousTeacher vt = famousTeacherDao.findUniqueBy("userId", v.getUserId());				
				for (Object o : vt.getFamousTeacherAskquestiontypes()) {
					AskQuestionType a = (AskQuestionType)o;
					if (a.getAskQuestionTypeId() == askQuestionTypeId) {
						temp.add(v);
						break;
					}
				}
			}
			list = temp;
		}
		
		List<ViewFamousTeacher> result = new ArrayList<ViewFamousTeacher>();
		int start = (pageNumber - 1) * pageSize;
		for (int i = 0; i < pageSize && start + i < list.size(); i++) {
			result.add(list.get(start + i));
		}
		
		Page page = new Page(start, list.size(), pageSize, result);
		return page; 
	}

	@Override
	public ViewFamousTeacher getFamouseTeacher(int famousTeacherId) {
		ViewFamousTeacher vf = viewFamousTeacherDao.findUniqueBy("userId", famousTeacherId);
		FamousTeacher t = famousTeacherDao.findUniqueBy("userId", famousTeacherId);
		vf.setAgeLevels(AgeLevelVO.convert(t.getFamousTeacherAgelevels()));
		vf.setAskQuestionTypes(AskQuestionTypeVO.convert(t.getFamousTeacherAskquestiontypes()));
		return vf;
	}
	
	@Override
	public String selectFamousTeacher(int userId) throws Exception{
		User u = userDao.findUniqueBy("userId", userId);
		if(u == null)
			throw new Exception("未找到此用户");
		
		FamousTeacher t = new FamousTeacher();
		t.setUser(u);
		t.setFamousTeacherIntro("暂无介绍");
		t.setRecommend(false);
		famousTeacherDao.save(t);
		/*
		 * 赋予名师的角色
		 */
		UserHelper.addRole(userDao, u, roleDao, Role.ROLE_FAMOUSTEACHER);
		return t.getUserId().toString();
	}

	@Override
	public String createFamousTeacher(FamousTeacherForm form) throws Exception {
		User user = new User();
		form.fillUser(user);
		UserHelper.saveUser(userDao, user);
		UserHelper.addRole(userDao, user, roleDao, Role.ROLE_USER);
		
		FamousTeacher teacher = new FamousTeacher();
		//teacher.setUserId(user.getUserId());
		fillFamousTeacher(teacher, form);
		teacher.setUser(user);
		famousTeacherDao.save(teacher);
		
		/*
		 * 赋予名师角色
		 */
		UserHelper.addRole(userDao, user, roleDao, Role.ROLE_FAMOUSTEACHER);
		
		return teacher.getUserId().toString();
	}

	@Override
	public Boolean deleteFamousTeacher(int famousTeacherId) {
		/*
		 * 删除角色
		 */
		User u = userDao.findUniqueBy("userId", famousTeacherId);
		if (u.getUserRoles() != null) {
			u.getUserRoles().clear();
			userDao.save(u);
		}
		
		famousTeacherDao.removeById(famousTeacherId);
		userDao.removeById(famousTeacherId);
		return true;
	}

	@Override
	public Boolean modifyFamousTeacher(FamousTeacherForm form)throws Exception {
		User user = userDao.findUniqueBy("userId", form.getUserId());
		form.fillUser(user);
		UserHelper.saveUser(userDao, user);
		
		FamousTeacher teacher = famousTeacherDao.findUniqueBy("userId", form.getUserId());
		teacher.getFamousTeacherAgelevels().clear();
		teacher.getFamousTeacherAskquestiontypes().clear();
		famousTeacherDao.save(teacher);
		
		fillFamousTeacher(teacher, form);
		famousTeacherDao.save(teacher);
		return true;
	}
	
	@Override
	public Boolean cancelFamousTeacher(int famousTeacherId) {
		FamousTeacher t = famousTeacherDao.findUniqueBy("userId", famousTeacherId);	
		User u = t.getUser();		
		UserHelper.removeRole(userDao, u, roleDao, Role.ROLE_FAMOUSTEACHER);
		famousTeacherDao.remove(t);
		return true;
	}
	
	private void fillFamousTeacher(FamousTeacher teacher, FamousTeacherForm form) {		
		teacher.setFamousTeacherIntro(form.getFamousTeacherIntro());
		teacher.setRecommend(form.getRecommend());
		
		HashSet<AskQuestionType> newTypes = new HashSet<AskQuestionType>();
		if (form.getAskQuestionTypes() != null) {
			for (Integer t : form.getAskQuestionTypes()) {
				newTypes.add(askQuestionTypeDao.findUniqueBy("askQuestionTypeId", t));
			}			
		}
		teacher.setFamousTeacherAskquestiontypes(newTypes);
		
		HashSet<AgeLevel> newAgeLevels = new HashSet<AgeLevel>();
		if (form.getAgeLevels() != null) {
			for (Integer l : form.getAgeLevels()) {
				newAgeLevels.add(ageLevelDao.findUniqueBy("ageLevelId", l));
			}
		}
		teacher.setFamousTeacherAgelevels(newAgeLevels);
	}

	@Override
	public JsonResult getFamousTeachertoWeChat(int start, int size,Object ageLevel,Object askQuestionType) {
		// TODO Auto-generated method stub
		
		if(ageLevel.equals("all") && askQuestionType.equals("all"))
		{
			List<ViewFamousTeacher> viewFamousTeachers = (List<ViewFamousTeacher>)viewFamousTeacherDao.dataQuery("from ViewFamousTeacher", start, size).getResult();
			
			if(viewFamousTeachers.size() < size){
				return new JsonResult("last",start+size,null,viewFamousTeachers);
			}
			return new JsonResult("succ",start+size,null,viewFamousTeachers);
		}
		else if(ageLevel.equals("all"))
		{
			Integer askQuestionTypeId = Integer.parseInt(askQuestionType.toString());
			return searchFamousTeacherToWeChat(start,size,askQuestionTypeId,-1);
		}
		else if(askQuestionType.equals("all")){
			return searchFamousTeacherToWeChat(start,size,-1,Integer.parseInt(ageLevel.toString()));
		}
		else{
			return searchFamousTeacherToWeChat(start,size,Integer.parseInt(askQuestionType.toString()),Integer.parseInt(ageLevel.toString()));
		}
	}
	
	private JsonResult searchFamousTeacherToWeChat(int start,int size,Integer askQuestionTypeId,Integer ageLevelId)
	{
		List<ViewFamousTeacher> result = new ArrayList<ViewFamousTeacher>();
		while(true)
		{
			Page page = viewFamousTeacherDao.dataQuery("from ViewFamousTeacher", start,1);
			if(page == null){
				JsonResult jsonResult = new JsonResult("last",-1,null,result);
				return jsonResult;
			}
			List<ViewFamousTeacher> viewFamousTeachers = (List<ViewFamousTeacher>)page.getResult();
			if(viewFamousTeachers == null || viewFamousTeachers.size() == 0){
				JsonResult jsonResult = new JsonResult("last",-1,null,result);
				return jsonResult;
			}
			ViewFamousTeacher viewFamousTeacher = viewFamousTeachers.get(0);
			if(viewFamousTeacher == null){
				//说明取到最后一条记录
				JsonResult jsonResult = new JsonResult("last",-1,null,result);
				return jsonResult;
			}
			if(askQuestionTypeId > 0 && ageLevelId <0){
				//说明是按问题类型是筛选条件
					FamousTeacher famousTeacher = famousTeacherDao.get(viewFamousTeacher.getUserId());
					
					if(famousTeacher.getFamousTeacherAskquestiontypes() != null){
						if(famousTeacher.getFamousTeacherAskquestiontypes().contains(askQuestionTypeDao.get(askQuestionTypeId))){
							result.add(viewFamousTeacher);
							if(result.size() == size){
								JsonResult jsonResult = new JsonResult("succ",start+size,null,result);
								return jsonResult;						
							}
						}
					}
			}
			if(ageLevelId > 0 && askQuestionTypeId<0){
				//说明年龄段为筛选条件
					FamousTeacher famousTeacher = famousTeacherDao.get(viewFamousTeacher.getUserId());
					if(famousTeacher.getFamousTeacherAgelevels() != null){
						if(famousTeacher.getFamousTeacherAgelevels().contains(ageLevelDao.get(ageLevelId))){
							result.add(viewFamousTeacher);
							if(result.size() == size){
								JsonResult jsonResult = new JsonResult("succ",start+size,null,result);
								return jsonResult;						
							}
						}
					}
			}
			if(ageLevelId > 0 && askQuestionTypeId>0){
				FamousTeacher famousTeacher = famousTeacherDao.get(viewFamousTeacher.getUserId());
				if(famousTeacher.getFamousTeacherAgelevels() !=null && famousTeacher.getFamousTeacherAskquestiontypes() !=null){
					if(famousTeacher.getFamousTeacherAskquestiontypes().contains(askQuestionTypeDao.get(askQuestionTypeId)) && famousTeacher.getFamousTeacherAgelevels().contains(ageLevelDao.get(ageLevelId))){
						result.add(viewFamousTeacher);
						if(result.size() == size){
							JsonResult jsonResult = new JsonResult("succ",start+size,null,result);
							return jsonResult;	
						}
					}
				}
			}
			start = start + 1;
		}
	}

}
