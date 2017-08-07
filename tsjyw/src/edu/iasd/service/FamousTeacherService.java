package edu.iasd.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demonstration.hibernate.dao.support.Page;

import edu.iasd.controller.JsonResult;
import edu.iasd.form.FamousTeacherForm;
import edu.iasd.pojo.ViewFamousTeacher;
import edu.iasd.pojo.ViewVolunteer;

@Service
@Transactional
public interface FamousTeacherService {
	public Page getAllFamousTeacher(String queryString, int pageNumber, int pageSize);
	
	public ViewFamousTeacher getFamouseTeacher(int famousTeacherId);
	
	public String selectFamousTeacher(int userId) throws Exception;
	
	public String createFamousTeacher(FamousTeacherForm form) throws Exception;
	
	public Boolean deleteFamousTeacher(int famousTeacherId);
	
	public Boolean modifyFamousTeacher(FamousTeacherForm form) throws Exception;
	
	public Boolean cancelFamousTeacher(int famousTeacherId);

	public JsonResult getFamousTeachertoWeChat(int start,int size,Object ageLevelId,Object askQuestionTypeId);

	public Page searchFamousTeacher(int pageNumber, int pageSize, int askQuestionTypeId, String userName);
}