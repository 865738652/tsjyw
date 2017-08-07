package edu.iasd.service;

import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demonstration.hibernate.dao.support.Page;

import edu.iasd.controller.JsonResult;
import edu.iasd.form.VolunteerForm;
import edu.iasd.pojo.ViewVolunteer;

@Service
@Transactional
public interface VolunteerService {
	public Page getAllVolunteer(String queryString, int pageNumber,int pageSize);
	
	public ViewVolunteer getVolunteer(int volunteerId);
	
	public String selectVolunteer(int userId) throws Exception;
	
	public String createVolunteer(VolunteerForm form) throws Exception;
	
	public Boolean deleteVolunteer(int volunteerId);
	
	public Boolean modifyVolunteer(VolunteerForm form) throws Exception;
	
	public Boolean cancelVolunteer(int volunteerId);
	
	public List<ViewVolunteer> searchVolunteer(String volunteerPCC, int ageLevelId, int askQuestionTypeId, String userName);
	
	public Page searchVolunteer(int pageNumber, int pageSize, String volunteerPCC, int ageLevelId, int askQuestionTypeId);
}
