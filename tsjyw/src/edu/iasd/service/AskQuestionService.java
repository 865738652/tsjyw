package edu.iasd.service;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demonstration.hibernate.dao.support.Page;

import edu.iasd.form.AttachVO;
import edu.iasd.form.QuestionForm;
import edu.iasd.form.ResponseForm;
import edu.iasd.pojo.AgeLevel;
import edu.iasd.pojo.AskQuestionState;
import edu.iasd.pojo.AskQuestionType;
import edu.iasd.pojo.Attachment;
import edu.iasd.pojo.ViewAskQuestion;

@Service
@Transactional
public interface AskQuestionService {
	public Page getAllQuestion(String queryString, int pageNow,int pageSize);
	
	public Page getHotQuestion(int pageNow, int pageSize);
	
	public Page getMyAskQuestion(int userId,String queryString,  int pageNow,int pageSize);
	
	public Page getToMeQuestion(int userId,String queryString,  int pageNow,int pageSize);
	
	public Page getMyAnswerQuestion(int userId, String queryString, int pageNow,int pageSize);
	
	public ViewAskQuestion getQuestion(int askQuestionId);
	
	public String createAskQuestion(QuestionForm questionForm);
	
	public Boolean deleteAskQuestion(int askQuestionId);
	
	public Boolean modifyAskQuestion(QuestionForm questionForm);
	
	public String createResponse(ResponseForm responseForm);
	
	public Boolean deleteRespQuestion(int respQuestionId);
	
	public Boolean modifyRespQuestion(ResponseForm responseForm);
	
	public List<AskQuestionState> getAskQuestionState();
	
	public List<AskQuestionType> getAskQuestionType();
	
	public List<AgeLevel> getAgeLevel();
	
	public Set<Attachment> getAttachments(int askQuestionId);
	
	
	public List<AskQuestionType> getAskQuestionType(Integer UserId);
	public List<AgeLevel> getAgeLevel(Integer UserId);
	
	public List<AttachVO> getAttachmentForAttachVO(Integer respQuestionId);
	
	public boolean setSelectAskQuestion(Set<Integer> askQuestionIds);
	
}
