package edu.iasd.controller;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demonstration.hibernate.dao.support.Page;

import edu.iasd.form.FamousTeacherForm;
import edu.iasd.form.VolunteerForm;
import edu.iasd.pojo.ViewFamousTeacher;
import edu.iasd.pojo.ViewVolunteer;
import edu.iasd.search.QueryModel;
import edu.iasd.search.SearchParam;
import edu.iasd.service.VolunteerService;

@Controller
public class VolunteerManageController {
	@Autowired
	private VolunteerService volunteerService;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) throws Exception {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    dateFormat.setLenient(false);
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));//true:允许输入空值，false:不能为空值
	}
	
	@RequestMapping("VolunteerManage")
	public String showAll(){
		return "VolunteerManage";
	}
	
	@ResponseBody
	@RequestMapping(value={"VolunteerManage/getAllVolunteer", "Index/getAllVolunteer"})
	public String getAllVolunteer(int pageNumber, int pageSize, @SearchParam QueryModel queryModel) throws Exception {
		try {
			Page page = volunteerService.getAllVolunteer(queryModel.toQueryString(), pageNumber, pageSize);
			JsonResult result;
			if (page.getResult() == null)
				result = new JsonResult("succ", 0, new ArrayList<ViewVolunteer>(), null);
			else {
				List<ViewVolunteer> volunteer = (List<ViewVolunteer>)page.getResult();
				result = new JsonResult("succ", (int)page.getTotalCount(), volunteer, null);
			}
			return result.toJsonString();
		}
		catch (Exception e) {
			JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			return result.toJsonString();
		}
	}
	
	@ResponseBody
	@RequestMapping(value="VolunteerManage/getVolunteer")
	public String getVolunteer(int volunteerId) throws Exception {
		try {
			ViewVolunteer volunteer = volunteerService.getVolunteer(volunteerId);

			JsonResult result = new JsonResult("succ", 0, null, volunteer);
			return result.toJsonString();
		}
		catch (Exception e) {
			JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			return result.toJsonString();
		}
	 } 
	
	 @ResponseBody
	 @RequestMapping(value="VolunteerManage/selectVolunteer")
	 public String selectVolunteer(int userId) throws Exception
	 {
		 try {
			 String id = volunteerService.selectVolunteer(userId);
			 if ( id != null) {			
				 JsonResult result = new JsonResult("succ", 0, null, id);
				 return result.toJsonString();
			 }
			 else
				 throw new Exception("设置志愿者失败");
		 }
		 catch (Exception e) {
			 JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			 return result.toJsonString();
		}		
	 }
	 
	@ResponseBody
	@RequestMapping(value="VolunteerManage/createVolunteer")
	public String createVolunteer(VolunteerForm volunteer) throws Exception{
		try{
			String id = volunteerService.createVolunteer(volunteer);
			if(id !=null){
				JsonResult result =new JsonResult("succ", 0, null , id);
				return result.toJsonString();
			}else
				throw new Exception("创建志愿者失败");
		}catch(Exception e){
			JsonResult result = new JsonResult("fail", 0, null , e.getMessage());
			return result.toJsonString();
		}
	}
	 
	 @ResponseBody
	 @RequestMapping(value="VolunteerManage/deleteVolunteer")
	 public String deleteVolunteer(int volunteerId) throws Exception
	 {
		 try {
			 volunteerService.deleteVolunteer(volunteerId);
			
			JsonResult result = new JsonResult("succ", 0, null, null);
			return result.toJsonString();
		}
		catch (Exception e) {
			JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			return result.toJsonString();
		}		 
	 }
	 
	 @ResponseBody
	 @RequestMapping(value="VolunteerManage/modifyVolunteer")
	 public String modifyVolunteer(VolunteerForm volunteer) throws Exception
	 {
		 try {
			 if (volunteerService.modifyVolunteer(volunteer)) {			
				 JsonResult result = new JsonResult("succ", 0, null, null);
				 return result.toJsonString();
			 }
			 else
				 throw new Exception("修改志愿者失败");
		 }
		 catch (Exception e) {
			 JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			 return result.toJsonString();
		}		
	 }
	 
	 @ResponseBody
	 @RequestMapping(value="VolunteerManage/cancelVolunteer")
	 public String cancelVolunteer(int volunteerId) throws Exception
	 {
		 try {
			 volunteerService.cancelVolunteer(volunteerId);
			
			JsonResult result = new JsonResult("succ", 0, null, null);
			return result.toJsonString();
		}
		catch (Exception e) {
			JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			return result.toJsonString();
		}		 
	 }
}
