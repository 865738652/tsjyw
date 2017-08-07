package edu.iasd.service;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demonstration.hibernate.dao.support.Page;

import edu.iasd.form.ApplyForm;
import edu.iasd.form.QuestionForm;
import edu.iasd.form.ResponseForm;
import edu.iasd.pojo.AskQuestionState;
import edu.iasd.pojo.ViewApply;
import edu.iasd.pojo.ViewAskQuestion;

@Service
@Transactional
public interface ApplyService {
	
	public Page getMyApply(int userId, int pageNow,int pageSize);
	
	public Page getAllApply(int pageNow,int pageSize);
	
	public ViewApply getApply(int applyId);
	
	public String createApply(ApplyForm form);
	
	public Boolean deleteApply(int applyId);
	
	public Boolean modifyApply(ApplyForm form);
	
	public Boolean passApply(int applyId) throws Exception;
	
	public Boolean blockApply(int applyId, String opinion);	
}
