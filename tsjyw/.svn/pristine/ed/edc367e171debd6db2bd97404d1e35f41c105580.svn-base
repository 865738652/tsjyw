package edu.iasd.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demonstration.hibernate.dao.support.Page;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.iasd.form.SchoolManagerForm;
import edu.iasd.pojo.School;
import edu.iasd.pojo.ViewSchoolManager;
import edu.iasd.service.SchoolManagerService;

@Controller
public class SchoolManagerController {
	@Autowired
	private SchoolManagerService schoolManagerService;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) throws Exception {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    dateFormat.setLenient(false);
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));//true:允许输入空值，false:不能为空值
	}
	
	@RequestMapping("SchoolPeople")
	public String ShowAll()	{
		return "SchoolPeople";
	}
	@RequestMapping("SchoolPeople/{schoolid}")
	public String ShowAllSchoolPeople(@PathVariable int schoolid,Model model) {
		model.addAttribute("schoolid",schoolid);
		return "SchoolPeople";
	}
	
	@ResponseBody
	@RequestMapping(value="SchoolPeople/getSchoolManagerBySchool")
	public String getSchoolManagerBySchool(int schoolId, int pageNumber, int pageSize) throws Exception {
		try {
			Page page = schoolManagerService.getSchoolManagerBySchool(pageNumber, pageSize, schoolId);
			JsonResult result;
			if (page.getResult() == null)
				result = new JsonResult("succ", 0, new ArrayList<ViewSchoolManager>(), null);
			else {
				List<ViewSchoolManager> SchoolManagers = (List<ViewSchoolManager>)page.getResult();
				result = new JsonResult("succ",(int)page.getTotalCount(), SchoolManagers, null);
			}
			ObjectMapper objectMapper = new ObjectMapper();
			String jsonString = objectMapper.writeValueAsString(result);
			System.out.println(jsonString);
			return jsonString;
		}
		catch (Exception e) {
			JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			ObjectMapper objectMapper = new ObjectMapper();
	        String jsonString = objectMapper.writeValueAsString(result);
	        System.out.println(jsonString);
	        return jsonString;
		}
	}
	
	@ResponseBody
	@RequestMapping(value="SchoolPeople/getSchoolManager")
	public String getSchoolManager(int schoolManagerId) throws Exception {
		try {
			ViewSchoolManager SchoolManager = schoolManagerService.getSchoolManager(schoolManagerId);
			System.out.println("get SchoolManager: "+SchoolManager.getUserNickname()); 
			
			JsonResult result = new JsonResult("succ", 0, null, SchoolManager);
			ObjectMapper objectMapper = new ObjectMapper();
	        String jsonString = objectMapper.writeValueAsString(result);
	        System.out.println(jsonString);
	        return jsonString;
		}
		catch (Exception e) {
			JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			ObjectMapper objectMapper = new ObjectMapper();
	        String jsonString = objectMapper.writeValueAsString(result);
	        System.out.println(jsonString);
	        return jsonString;
		}
	 } 
	
	
	 @ResponseBody
	 @RequestMapping(value="SchoolPeople/selectSchoolManager")
	 public String selectSchoolManager(int userId, int schoolId) throws Exception
	 {
		 try {
			 String id = schoolManagerService.selectSchoolManager(userId, schoolId);
			 if ( id != null) {			
				 JsonResult result = new JsonResult("succ", 0, null, id);
				 return result.toJsonString();
			 }
			 else
				 throw new Exception("调入学生失败");
		 }
		 catch (Exception e) {
			 JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			 return result.toJsonString();
		}		
	 }
	 
	@ResponseBody
	 @RequestMapping(value="SchoolPeople/createSchoolManager")
	 public String createSchoolManager(SchoolManagerForm SchoolManager) throws IOException
	 {
		 try {
			 String id = schoolManagerService.createSchoolManager(SchoolManager);
			 if ( id != null) {			
				 JsonResult result = new JsonResult("succ", 0, null, id);
				 ObjectMapper objectMapper = new ObjectMapper();
				 String jsonString = objectMapper.writeValueAsString(result);
				 System.out.println(jsonString);
				 return jsonString;
			 }
			 else
				 throw new Exception("添加学校管理员失败");
		 }
		 catch (Exception e) {
			 JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			 ObjectMapper objectMapper = new ObjectMapper();
			 String jsonString = objectMapper.writeValueAsString(result);
			 System.out.println(jsonString);
			 return jsonString;
		}		
	 }
	
	 @ResponseBody
	 @RequestMapping(value="SchoolPeople/deleteSchoolManager")
	 public String deleteSchoolManager(int schoolManagerId) throws IOException
	 {
		 try {
			 schoolManagerService.deleteSchoolManager(schoolManagerId);//userid?????
			
			JsonResult result = new JsonResult("succ", 0, null, null);
			ObjectMapper objectMapper = new ObjectMapper();
	        String jsonString = objectMapper.writeValueAsString(result);
	        System.out.println(jsonString);
	        return jsonString;
		}
		catch (Exception e) {
			JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			ObjectMapper objectMapper = new ObjectMapper();
	        String jsonString = objectMapper.writeValueAsString(result);
	        System.out.println(jsonString);
	        return jsonString;
		}		 
	 }
	 
	 @ResponseBody
	 @RequestMapping(value="SchoolPeople/modifySchoolManager")
	 public String modifySchoolManager(SchoolManagerForm SchoolManager) throws IOException
	 {
		 try {
			 if (schoolManagerService.modifySchoolManager(SchoolManager)) {			
				 JsonResult result = new JsonResult("succ", 0, null, null);
				 ObjectMapper objectMapper = new ObjectMapper();
				 String jsonString = objectMapper.writeValueAsString(result);
				 System.out.println(jsonString);
				 return jsonString;
			 }
			 else
				 throw new Exception("修改学校管理员失败");
		 }
		 catch (Exception e) {
			 JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			 ObjectMapper objectMapper = new ObjectMapper();
			 String jsonString = objectMapper.writeValueAsString(result);
			 System.out.println(jsonString);
			 return jsonString;
		}		
	 }
	
	 @ResponseBody
	 @RequestMapping(value="SchoolPeople/cancelSchoolManager")
	 public String cancelSchoolManager(int schoolManagerId) throws Exception
	 {
		 try {
			 schoolManagerService.cancelSchoolManager(schoolManagerId);
			 JsonResult result = new JsonResult("succ", 0, null, null);
			 ObjectMapper objectMapper = new ObjectMapper();
			 String jsonString = objectMapper.writeValueAsString(result);
			 System.out.println(jsonString);
			 return jsonString;
		}
		catch (Exception e) {
			 JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			 ObjectMapper objectMapper = new ObjectMapper();
			 String jsonString = objectMapper.writeValueAsString(result);
			 System.out.println(jsonString);
			 return jsonString;
		}		 
	 }
}
