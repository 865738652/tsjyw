package edu.iasd.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demonstration.hibernate.basedao.IBaseDao;
import com.demonstration.hibernate.dao.support.Page;

import edu.iasd.form.AgeLevelVO;
import edu.iasd.form.AttachVO;
import edu.iasd.form.QuestionForm;
import edu.iasd.form.ResponseForm;
import edu.iasd.pojo.AgeLevel;
import edu.iasd.pojo.AskQuestion;
import edu.iasd.pojo.AskQuestionState;
import edu.iasd.pojo.AskQuestionType;
import edu.iasd.pojo.Attachment;
import edu.iasd.pojo.FamousTeacher;
import edu.iasd.pojo.ViewFamousTeacher;
import edu.iasd.pojo.RespQuestion;
import edu.iasd.pojo.Role;
import edu.iasd.pojo.User;
import edu.iasd.pojo.ViewAskQuestion;
import edu.iasd.pojo.ViewRespQuestion;
import edu.iasd.pojo.Volunteer;
import edu.iasd.pojo.ViewVolunteer;
import edu.iasd.utils.NoticeHelper;
import edu.iasd.utils.CreditHelper;

@Service
@Transactional
public class AskQuestionServiceImpl implements AskQuestionService {

	@Resource
	private IBaseDao<edu.iasd.pojo.AskQuestion> askQuestionDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.AskQuestionState> askQuestionStateDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.AskQuestionType> askQuestionTypeDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.RespQuestion> respQuestionDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.ViewAskQuestion> viewAskQuestionDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.ViewRespQuestion> viewRespQuestionDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.Attachment> attachmentDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.AttachmentType> attachmentTypeDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.User> userDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.AgeLevel> ageLevelDao;
	
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
	
	
	@Resource
	private IBaseDao<edu.iasd.pojo.FamousTeacher> famousTeacherDao;

	@Resource
	private IBaseDao<edu.iasd.pojo.ViewFamousTeacher> viewFamousTeacherDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.Volunteer> volunteerDao;

	@Resource
	private IBaseDao<edu.iasd.pojo.ViewVolunteer> viewVolunteerDao;	

	@Override
	public Page getAllQuestion(String queryString, int pageNow, int pageSize) {
		String where = (queryString != null && !queryString.equals("")) ? " and " + queryString : "";
		return viewAskQuestionDao.pagedQuery("from ViewAskQuestion v where v.askquestionStateId != ? and v.askQuestionPublic=true " + where + " order by v.askQuestionTime desc", pageNow, pageSize, AskQuestionState.STATE_DISABLED);
	}
	
	@Override
	public Page getHotQuestion(int pageNow, int pageSize) {
		//return viewAskQuestionDao.pagedQuery("from ViewAskQuestion v where v.askquestionStateId != ? and v.askQuestionPublic=true order by v.askQuestionReadCount desc", pageNow, pageSize, AskQuestionState.STATE_DISABLED);
		return viewAskQuestionDao.pagedQuery("from ViewAskQuestion v where v.askquestionStateId != ? and v.askQuestionPublic=true and v.askQuestionSelect = ? order by v.askQuestionTime desc", pageNow, pageSize, AskQuestionState.STATE_DISABLED,AskQuestion.ASKQUESTIONSELECT_IS);
	}

	@Override
	public Page getMyAskQuestion(int userId,String queryString,  int pageNow, int pageSize) {
		String where = (queryString != null && !queryString.equals("")) ? " and " + queryString : "";
		return viewAskQuestionDao.pagedQuery("from ViewAskQuestion v where (v.askUserId = ? or v.respUserId = ?) " + where + " order by v.askQuestionTime desc", pageNow, pageSize, userId, userId);
	}

	@Override
	public Page getToMeQuestion(int userId,String queryString,  int pageNow, int pageSize) {
		String where = (queryString != null && !queryString.equals("")) ? " and " + queryString : "";
		return viewAskQuestionDao.pagedQuery("from ViewAskQuestion v where v.respUserId = ? " + where + " order by v.askQuestionTime desc", pageNow, pageSize, userId);
	}

	@Override
	public Page getMyAnswerQuestion(int userId,String queryString,  int pageNow, int pageSize) {
		String where = (queryString != null && !queryString.equals("")) ? " and " + queryString : "";
		String hql = "select distinct v from ViewAskQuestion v, RespQuestion r where v.askQuestionId=r.askQuestion.askQuestionId and r.user.userId=? " + where + " order by v.askQuestionTime desc";
		return viewAskQuestionDao.pagedQuery(hql, pageNow, pageSize, userId);
	}

	@Override
	public ViewAskQuestion getQuestion(int askQuestionId) {
		/* 获取问题信息*/
		ViewAskQuestion vq = viewAskQuestionDao.findUniqueBy("askQuestionId", askQuestionId);
		AskQuestion q = askQuestionDao.findUniqueBy("askQuestionId", askQuestionId);
		
		/* 获取年龄*/
		List<AgeLevelVO> als = new ArrayList<AgeLevelVO>();
		if (q.getAgeLevels() != null) {
			for (Object a : q.getAgeLevels()) {
				AgeLevel al = (AgeLevel)a;
				AgeLevelVO av = new AgeLevelVO();
				av.setAgeName(al.getAgeName());
				av.setAgeLevelId(al.getAgeLevelId());
				als.add(av);
			}
		}
		vq.setAgeLevels(als);
		
		/* 获取问题的附件*/
		List<AttachVO> list = new ArrayList<AttachVO>();
		if (q.getAskQuestionAttachments() != null) {
			for (Object o : q.getAskQuestionAttachments()) {
				Attachment a = (Attachment)o;
				AttachVO v = new AttachVO();
				v.setAttachId(a.getAttachmentId());
				v.setAttachName(a.getAttachmentName());
				v.setAttachSize(a.getAttachmentSize());
				v.setAttachUrl(a.getAttachmentUrl());
				v.setAttachTypeId(a.getAttachmentType().getAttachmentTypeId());
				v.setAttachTypeName(a.getAttachmentType().getAttachmentTypeName());
				v.setOwnerId(askQuestionId);
				list.add(v);
			}
		}
		vq.setAttachments(list);
		
		/* 获取全部回答*/
		List<ViewRespQuestion> vr = viewRespQuestionDao.findBy("askQuestionId", askQuestionId);
		vq.setResponses(vr);
		if (vr == null || vr.size() == 0)
			return vq;
		
		/* 获取每个回答的附件*/
		for (ViewRespQuestion r : vr) {
			list = new ArrayList<AttachVO>();
			RespQuestion rq = respQuestionDao.findUniqueBy("respQuestionId", r.getRespQuestionId());
			if (rq.getAttachments() != null && rq.getAttachments().size() > 0) {
				for (Object o : rq.getAttachments()) {
					Attachment a = (Attachment)o;
					AttachVO v = new AttachVO();
					v.setAttachId(a.getAttachmentId());
					v.setAttachName(a.getAttachmentName());
					v.setAttachSize(a.getAttachmentSize());
					v.setAttachUrl(a.getAttachmentUrl());
					v.setAttachTypeId(a.getAttachmentType().getAttachmentTypeId());
					v.setAttachTypeName(a.getAttachmentType().getAttachmentTypeName());
					v.setOwnerId(r.getRespQuestionId());
					list.add(v);
				}
			}
			r.setAttachments(list);
		}
		return vq;
	}

	@Override
	public String createAskQuestion(QuestionForm questionForm) {
		AskQuestion q = new AskQuestion();
		fillAskQuestion(q, questionForm);
		if (q.getAskQuestionAttachments() != null) {
			for (Object o : q.getAskQuestionAttachments()) {
				Attachment att = (Attachment)o;
				attachmentDao.save(att);
			}
		}
		
		askQuestionDao.save(q);
		
		/*
		 * send ask question notice to replier
		 */
		if (q.getUserByRespUserId() != null) {
			NoticeHelper.sendNotice(User.SYSTEM_ADMIN_ID, q.getUserByRespUserId().getUserId(), 
					q.getUserByAskUserId().getUserName() + "提问: " + q.getAskQuestionTitle(),"详情请查看<a href=\"http://www.tsjtjyw.com/WeChat/WeChatLookQuestion?askQuestionId="+q.getAskQuestionId()+"\">问题详情</a>", 
					userDao, noticeStateDao, noticeDao, noticeAcceptDao, noticeAcceptTypeDao, messageDao);
		}
		else if(questionForm.getAskQuestionPublic().booleanValue() == true || q.getUserByRespUserId() == null){
			AskQuestionType t = askQuestionTypeDao.findUniqueBy("askQuestionTypeId", q.getAskquestionType().getAskQuestionTypeId());
			List<ViewFamousTeacher> famousTeachers = viewFamousTeacherDao.find("from ViewFamousTeacher v where v.askQuestionTypeNames like '%" + t.getAskQuestionTypeName() + "%'");
			List<ViewVolunteer> volunteers = viewVolunteerDao.find("from ViewVolunteer v where v.askQuestionTypeNames like '%" + t.getAskQuestionTypeName() + "%'");
			
			Set<Integer> userIds = new HashSet<Integer>();
			for(ViewFamousTeacher famousteacher:famousTeachers){
				userIds.add(famousteacher.getUserId());
			}
			for(ViewVolunteer volunteer:volunteers){
				userIds.add(volunteer.getUserId());
			}
			for(Integer userId:userIds){
				NoticeHelper.sendNotice(User.SYSTEM_ADMIN_ID, userId, 
						q.getUserByAskUserId().getUserName() + "提问: " + q.getAskQuestionTitle(),"详情请查看<a href=\"http://www.tsjtjyw.com/WeChat/WeChatLookQuestion?askQuestionId="+q.getAskQuestionId()+"\">问题详情</a>", 
						userDao, noticeStateDao, noticeDao, noticeAcceptDao, noticeAcceptTypeDao, messageDao);
			}
			
		}
		return q.getAskQuestionId().toString();
	}

	@Override
	public Boolean deleteAskQuestion(int askQuestionId) {
		AskQuestion q = askQuestionDao.findUniqueBy("askQuestionId", askQuestionId);
		q.setAskquestionState(askQuestionStateDao.findUniqueBy("askQuestionStateId", AskQuestionState.STATE_DISABLED));
		askQuestionDao.save(q);
		return true;
	}

	@Override
	public Boolean modifyAskQuestion(QuestionForm questionForm) {
		AskQuestion q = askQuestionDao.findUniqueBy("askQuestionId", questionForm.getAskQuestionId());
		if (q.getRespQuestions() != null && q.getRespQuestions().size() > 0)
			return false;
		
		/* remove all old attachments */
		List<Attachment> attachs = new ArrayList<Attachment>();
		if (q.getAskQuestionAttachments() != null && q.getAskQuestionAttachments().size() > 0) {
			for (Object o : q.getAskQuestionAttachments())
				attachs.add((Attachment)o);
		}
		q.getAskQuestionAttachments().clear();
		q.getAgeLevels().clear();
		askQuestionDao.save(q);
		for (Attachment a : attachs)
			attachmentDao.remove(a);
		
		questionForm.setAskQuestionTime(q.getAskQuestionTime());
		fillAskQuestion(q, questionForm);
		/* save new attachments */
		if (q.getAskQuestionAttachments() != null) {
			for (Object o : q.getAskQuestionAttachments()) {
				Attachment att = (Attachment)o;
				attachmentDao.save(att);
			}
		}
		askQuestionDao.save(q);
		return true;
	}

	@Override
	public String createResponse(ResponseForm responseForm) {
		RespQuestion r = new RespQuestion();
		fillRespQuestion(r, responseForm);
		for (Object o : r.getAttachments()) {
			Attachment att = (Attachment)o;
			attachmentDao.save(att);
		}
		respQuestionDao.save(r);
		
		/*
		 * send reply notice to asker
		 */
		NoticeHelper.sendNotice(User.SYSTEM_ADMIN_ID, r.getAskQuestion().getUserByAskUserId().getUserId(), 
			r.getUser().getUserName() + "回复提问: " + r.getAskQuestion().getAskQuestionTitle(), r.getRespQuestionContent(), 
			userDao, noticeStateDao, noticeDao, noticeAcceptDao, noticeAcceptTypeDao, messageDao);
		
		CreditHelper.trigger(userDao, responseForm.getUserId(), CreditHelper.CREDIT_TYPE_ANSWER);
		return r.getRespQuestionId().toString();
	}

	@Override
	public Boolean deleteRespQuestion(int respQuestionId) {
		return false;
	}

	@Override
	public Boolean modifyRespQuestion(ResponseForm responseForm) {
		return false;
	}

	@Override
	public List<AskQuestionState> getAskQuestionState() {
		return askQuestionStateDao.getAll();
	}

	@Override
	public List<AskQuestionType> getAskQuestionType() {
		return askQuestionTypeDao.getAll();
	}
	
	@Override
	public List<AgeLevel> getAgeLevel() {
		return ageLevelDao.getAll();
	}
		
	private void fillAskQuestion(AskQuestion q, QuestionForm form) {
		/* 基本信息 */
		q.setAskQuestionContent(form.getAskQuestionContent());
		q.setAskQuestionPublic(form.getAskQuestionPublic());
		q.setAskQuestionReadCount(form.getAskQuestionReadCount());
		q.setAskQuestionRewardIntegral(form.getAskQuestionRewardIntegral());
		q.setAskquestionState(askQuestionStateDao.findUniqueBy("askQuestionStateId", form.getAskquestionStateId()));
		q.setAskQuestionTime(form.getAskQuestionTime());
		q.setAskQuestionTitle(form.getAskQuestionTitle());
		q.setAskquestionType(askQuestionTypeDao.findUniqueBy("askQuestionTypeId", form.getAskquestionTypeId()));
		q.setUserByAskUserId(userDao.findUniqueBy("userId", form.getAskUserId()));
		if (form.getRespUserId() != null) {
			q.setUserByRespUserId(userDao.findUniqueBy("userId", form.getRespUserId()));
		}
		
		/* 年龄段列表 */
		Set<AgeLevel> ag = new HashSet<AgeLevel>();
		if (form.getAgeLevels() != null && form.getAgeLevels().size() > 0) {
			for (Integer i : form.getAgeLevels()) {
				ag.add(ageLevelDao.findUniqueBy("ageLevelId", i));
			}
		}
		q.setAgeLevels(ag);
		
		/* 附件 */
		Set<Attachment> att = new HashSet<Attachment>();
		if (form.getAttachments() != null && form.getAttachments().size() > 0) {
			for (AttachVO v : form.getAttachments()) {
				Attachment a = new Attachment();
				a.setAttachmentName(v.getAttachName());
				a.setAttachmentSize(v.getAttachSize());
				a.setAttachmentUrl(v.getAttachUrl());
				a.setAttachmentType(attachmentTypeDao.findUniqueBy("attachmentTypeId", 1));
				att.add(a);
			}
		}
		q.setAskQuestionAttachments(att);
	}

	private void fillRespQuestion(RespQuestion r, ResponseForm form) {
		/* 基本信息 */
		r.setRespQuestionContent(form.getRespQuestionContent());
		r.setRespQuestionPublic(form.getRespQuestionPublic());
		r.setRespQuestionTime(form.getRespQuestionTime());
		r.setUser(userDao.findUniqueBy("userId", form.getUserId()));
		r.setAskQuestion(askQuestionDao.findUniqueBy("askQuestionId", form.getAskQuestionId()));

		/* 附件 */
		Set<Attachment> att = new HashSet<Attachment>();
		if (form.getAttachments() != null && form.getAttachments().size() > 0) {
			for (AttachVO v : form.getAttachments()) {
				Attachment a = new Attachment();
				a.setAttachmentName(v.getAttachName());
				a.setAttachmentSize(v.getAttachSize());
				a.setAttachmentUrl(v.getAttachUrl());
				a.setAttachmentType(attachmentTypeDao.findUniqueBy("attachmentTypeId", 1));
				att.add(a);
			}
		}
		r.setAttachments(att);
	}

	@Override
	public List<AskQuestionType> getAskQuestionType(Integer UserId){
		// TODO Auto-generated method stub
		List<AskQuestionType> askQuestionTypes = new ArrayList<AskQuestionType>();
		
		Set<Role> roles = userDao.get(UserId).getUserRoles();
		for(Role o:roles)
		{
			if(o.getRoleId() == Role.ROLE_FAMOUSTEACHER){
				askQuestionTypes.addAll(famousTeacherDao.get(UserId).getFamousTeacherAskquestiontypes());
			}
			if(o.getRoleId() == Role.ROLE_VOLUNTEER){
				askQuestionTypes.addAll(volunteerDao.get(UserId).getVolunteerAskQuestionTypes());
			}
		}
		
		return askQuestionTypes;
	}
	
	@Override
	public List<AgeLevel> getAgeLevel(Integer UserId){
		// TODO Auto-generated method stub
		List<AgeLevel> ageLevels = new ArrayList<AgeLevel>();
		
		Set<Role> roles = userDao.get(UserId).getUserRoles();
		for(Role o:roles)
		{
			if(o.getRoleId() == Role.ROLE_FAMOUSTEACHER){
				ageLevels.addAll(famousTeacherDao.get(UserId).getFamousTeacherAgelevels());
			}
			if(o.getRoleId() == Role.ROLE_VOLUNTEER){
				ageLevels.addAll(volunteerDao.get(UserId).getVolunteerAgeLevels());
			}
		}
		
		return ageLevels;
	}

	@Override
	public Set<Attachment> getAttachments(int askQuestionId) {
		// TODO Auto-generated method stub
		AskQuestion question = askQuestionDao.get(askQuestionId);
		return question.getAskQuestionAttachments();
	}

	@Override
	public List<AttachVO> getAttachmentForAttachVO(Integer respQuestionId) {
		// TODO Auto-generated method stub
		
		RespQuestion response = respQuestionDao.get(respQuestionId);
		
		if(response.getAttachments() == null || response.getAttachments().size() == 0)
			return null;
		Set<Attachment> attachments = response.getAttachments();
		List<AttachVO> attachVos = new ArrayList<AttachVO>();
		for(Attachment attachment:attachments)
		{
			AttachVO attachVo = new AttachVO();
			attachVo.setAttachId(attachment.getAttachmentId());
			attachVo.setAttachName(attachment.getAttachmentName());
			attachVo.setAttachUrl(attachment.getAttachmentUrl());
			
			String fileName = attachment.getAttachmentName();
			String tmpName = fileName.substring(fileName.lastIndexOf(".") + 1);  
			if(Arrays.asList(Attachment.ATTACHMENT_IMG).contains(tmpName.toLowerCase()))
			{
				attachVo.setAttachTypeName("IMG");
			}
			else
				attachVo.setAttachTypeName("OTHER");
			
			attachVos.add(attachVo);
		}
		return attachVos;
	}

	@Override
	public boolean setSelectAskQuestion(Set<Integer> askQuestionIds) {
		// TODO Auto-generated method stub
		List<AskQuestion> askQuestions = askQuestionDao.getAll();
		for(AskQuestion askQuestion:askQuestions)
		{
			if(askQuestionIds.contains(askQuestion.getAskQuestionId()))
			{
				askQuestion.setAskQuestionSelect(AskQuestion.ASKQUESTIONSELECT_IS);
			}
			else
				askQuestion.setAskQuestionSelect(AskQuestion.ASKQUESTIONSELECT_NOT);
			askQuestionDao.save(askQuestion);
		}
		return true;
	}
	
	
}
