package edu.iasd.service;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demonstration.hibernate.basedao.IBaseDao;
import com.demonstration.hibernate.dao.support.Page;

import edu.iasd.form.AgeLevelVO;
import edu.iasd.form.ApplyForm;
import edu.iasd.form.AskQuestionTypeVO;
import edu.iasd.pojo.AgeLevel;
import edu.iasd.pojo.Apply;
import edu.iasd.pojo.ApplyState;
import edu.iasd.pojo.AskQuestionType;
import edu.iasd.pojo.Role;
import edu.iasd.pojo.User;
import edu.iasd.pojo.ViewApply;
import edu.iasd.pojo.Volunteer;
import edu.iasd.utils.NoticeHelper;
import edu.iasd.utils.UserHelper;

@Service
@Transactional
public class ApplyServiceImpl implements ApplyService {

	@Resource
	private IBaseDao<edu.iasd.pojo.Apply> applyDao;

	@Resource
	private IBaseDao<edu.iasd.pojo.ViewApply> viewApplyDao;

	@Resource
	private IBaseDao<edu.iasd.pojo.ApplyState> applyStateDao;

	@Resource
	private IBaseDao<edu.iasd.pojo.User> userDao;

	@Resource
	private IBaseDao<edu.iasd.pojo.AgeLevel> ageLevelDao;

	@Resource
	private IBaseDao<edu.iasd.pojo.AskQuestionType> askQuestionTypeDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.Volunteer> volunteerDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.Role> roleDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.NoticeState> noticeStateDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.Notice> noticeDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.NoticeAccept> noticeAcceptDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.NoticeAcceptType> noticeAcceptTypeDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.Message> messageDao;
		
	@Override
	public Page getMyApply(int userId, int pageNow,int pageSize) {
		return viewApplyDao.pagedQuery("from ViewApply v where v.userId=?", pageNow, pageSize, userId);
	}
	
	@Override
	public Page getAllApply(int pageNow,int pageSize) {
		return viewApplyDao.pagedQuery("from ViewApply v", pageNow, pageSize);
	}
	
	@Override
	public ViewApply getApply(int applyId) {
		ViewApply va = viewApplyDao.findUniqueBy("applyId", applyId);
		Apply a = applyDao.findUniqueBy("applyId", applyId);
		va.setAgeLevels(AgeLevelVO.convert(a.getApplyAgeLevels()));
		va.setAskQuestionTypes(AskQuestionTypeVO.convert(a.getApplyAskQuestionTypes()));
		return va;
	}

	@Override
	public String createApply(ApplyForm form) {
		/*form.setApplyApinion("");*/
		Apply apply = new Apply();
		fillApply(apply, form);
		applyDao.save(apply);
		return apply.getApplyId().toString();
	}
	
	@Override
	public Boolean deleteApply(int applyId) {
		applyDao.removeById(applyId);
		return true;
	}
	
	@Override
	public Boolean modifyApply(ApplyForm form) {
		Apply apply = applyDao.findUniqueBy("applyId", form.getApplyId());
		apply.getApplyAgeLevels().clear();
		apply.getApplyAskQuestionTypes().clear();
		applyDao.save(apply);
		
		fillApply(apply, form);
		applyDao.save(apply);
		return true;
	}
	
	@Override
	public Boolean passApply(int applyId) throws Exception {		
		Apply apply = applyDao.findUniqueBy("applyId", applyId);
		
		Volunteer v = volunteerDao.findUniqueBy("userId", apply.getUser().getUserId());
		if (v != null)
			throw new Exception("user has already been volunteer");
		
		v = new Volunteer();
		//v.setUserId(apply.getUser().getUserId());
		v.setUser(apply.getUser());
		if (apply.getApplyAgeLevels() != null) {
			Set<AgeLevel> ls = new HashSet<AgeLevel>();
			for (Object o : apply.getApplyAgeLevels()) {
				AgeLevel l = (AgeLevel)o;
				AgeLevel nl = ageLevelDao.findUniqueBy("ageLevelId", l.getAgeLevelId());
				ls.add(nl);
			}
			v.setVolunteerAgeLevels(ls);
		}
		if (apply.getApplyAskQuestionTypes() != null) {
			Set<AskQuestionType> as = new HashSet<AskQuestionType>();
			for (Object o : apply.getApplyAskQuestionTypes()) {
				AskQuestionType a = (AskQuestionType)o;
				AskQuestionType na = askQuestionTypeDao.findUniqueBy("askQuestionTypeId", a.getAskQuestionTypeId());
				as.add(na);
			}
			v.setVolunteerAskQuestionTypes(as);
		}
		v.setVolunteerIntro(apply.getApplyIntro());
		v.setVolunteerLocation(apply.getApplyAddress());
		v.setVolunteerAddrLat(apply.getApplyAddrLat());
		v.setVolunteerAddrLng(apply.getApplyAddrLng());
		v.setVolunteerPCC(apply.getApplyPCC());
		volunteerDao.save(v);
		
		UserHelper.addRole(userDao, apply.getUser(), roleDao, Role.ROLE_VOLUNTEER);
		
		apply.setApplyState(applyStateDao.findUniqueBy("applyStateId", ApplyState.APPLY_STATE_SUCC));
		applyDao.save(apply);
		
		/*
		 * send notify to applier
		 */
		NoticeHelper.sendNotice(User.SYSTEM_ADMIN_ID, apply.getUser().getUserId(), 
			"您的教师志愿者申请已批准","您的教师志愿者申请已批准", 
			userDao, noticeStateDao, noticeDao, noticeAcceptDao, noticeAcceptTypeDao, messageDao);
		return true;
	}
	
	@Override
	public Boolean blockApply(int applyId, String opinion) {
		Apply apply = applyDao.findUniqueBy("applyId", applyId);
		apply.setApplyState(applyStateDao.findUniqueBy("applyStateId", ApplyState.APPLY_STATE_FAIL));
		apply.setApplyOpinion(opinion);
		applyDao.save(apply);
		
		/*
		 * send notify to applier
		 */
		NoticeHelper.sendNotice(User.SYSTEM_ADMIN_ID, apply.getUser().getUserId(), 
			"您的教师志愿者申请已被管理员拒绝","您的教师志愿者申请已被管理员拒绝, "+apply.getApplyOpinion(), 
			userDao, noticeStateDao, noticeDao, noticeAcceptDao, noticeAcceptTypeDao, messageDao);
		return true;
	}
	
	private void fillApply(Apply a, ApplyForm f) {
		a.setApplyIntro(f.getApplyIntro());
		a.setApplyTime(f.getApplyTime());
		a.setApplyAddress(f.getApplyAddress());
		a.setApplyAddrLng(f.getApplyAddrLng());
		a.setApplyAddrLat(f.getApplyAddrLat());
		a.setApplyPCC(f.getApplyPCC());
		a.setApplyOpinion(f.getApplyOpinion());
		a.setApplyState(applyStateDao.findUniqueBy("applyStateId", f.getApplyStateId()));
		a.setUser(userDao.findUniqueBy("userId", f.getUserId()));
		
		HashSet<AskQuestionType> newTypes = new HashSet<AskQuestionType>();
		if (f.getAskQuestionTypes() != null) {
			for (Integer t : f.getAskQuestionTypes()) {
				newTypes.add(askQuestionTypeDao.findUniqueBy("askQuestionTypeId", t));
			}			
		}
		a.setApplyAskQuestionTypes(newTypes);
		
		HashSet<AgeLevel> newAgeLevels = new HashSet<AgeLevel>();
		if (f.getAgeLevels() != null) {
			for (Integer l : f.getAgeLevels()) {
				newAgeLevels.add(ageLevelDao.findUniqueBy("ageLevelId", l));
			}
		}
		a.setApplyAgeLevels(newAgeLevels);
	}

	
}
