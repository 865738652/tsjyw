package edu.iasd.controller;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

import edu.iasd.form.GradeMasterForm;
import edu.iasd.pojo.ViewGrade;
import edu.iasd.pojo.ViewGradeMaster;
import edu.iasd.service.GradeMasterService;
import edu.iasd.service.GradeService;


@Controller
public class GradeMasterController {
	
	@Autowired
	private GradeMasterService gradeMasterService;
	
	@Autowired
	private GradeService gradeService;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) throws Exception {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    dateFormat.setLenient(false);
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));//true:允许输入空值，false:不能为空值
	}
	
	@RequestMapping("GradeMaster/{gradeId}")
	public String showAllGradeMaster(@PathVariable int gradeId,Model model)
	{
		ViewGrade g = gradeService.getGrade(gradeId);
		model.addAttribute("gradeid",gradeId);
		model.addAttribute("schoolid", g.getSchoolId());
		return "GradeMaster";
	}
	
	@ResponseBody
	@RequestMapping(value="GradeMaster/getGradeMasterByGrade")
	public String getGradeMasterByGrade(int gradeId,int pageNumber,int pageSize) throws Exception{
		
		try{
			
			Page page = gradeMasterService.getGradeMasterByGrade(pageNumber,pageSize,gradeId);
			JsonResult result;
			System.out.println(gradeId);
			if (page.getResult() == null)
				result = new JsonResult("succ", 0, new ArrayList<ViewGradeMaster>(), null);
			else{
				List<ViewGradeMaster> gradeMaster =(List<ViewGradeMaster>)page.getResult();
				result = new JsonResult("succ",(int)page.getTotalCount(),gradeMaster,null);
			}
			return result.toJsonString();
		}
		catch (Exception e) {
			JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			return result.toJsonString();
		}
		
	}
	
	@ResponseBody
	@RequestMapping(value="GradeMaster/getGradeMaster")
	public String getGradeMaster(int gradeMasterId) throws Exception{
		try {
			ViewGradeMaster gradeMaster = gradeMasterService.getGradeMaster(gradeMasterId);
			System.out.println("get gradeMaster: "+gradeMaster.getUserNickname()); 
			
			JsonResult result = new JsonResult("succ", 0, null, gradeMaster);
			return result.toJsonString();
		}
		catch (Exception e) {
			JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			return result.toJsonString();
		}
	}
	
	@ResponseBody
	 @RequestMapping(value="GradeMaster/selectGradeMaster")
	 public String selectGradeMaster(int userId, int gradeId) throws Exception
	 {
		 try {
			 String id = gradeMasterService.selectGradeMaster(userId, gradeId);
			 if ( id != null) {			
				 JsonResult result = new JsonResult("succ", 0, null, id);
				 return result.toJsonString();
			 }
			 else
				 throw new Exception("设置年级主任失败");
		 }
		 catch (Exception e) {
			 JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			 return result.toJsonString();
		}		
	 }
	
	 @ResponseBody
	 @RequestMapping(value="GradeMaster/createGradeMaster")
	 public String createGradeMaster(GradeMasterForm gradeMaster) throws Exception
	 {
		 try {
			 String id = gradeMasterService.createGradeMaster(gradeMaster);
			 if ( id != null) {			
				 JsonResult result = new JsonResult("succ", 0, null, id);
				 return result.toJsonString();
			 }
			 else
				 throw new Exception("添加年级主任失败");
		 }
		 catch (Exception e) {
			 JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			 return result.toJsonString();
		}		
	 }
	 
	 @ResponseBody
	 @RequestMapping(value="GradeMaster/deleteGradeMaster")
	 public String deleteGradeMaster(int gradeMasterId) throws Exception
	 {
		 try {
			 gradeMasterService.deleteGradeMaster(gradeMasterId);
			
			JsonResult result = new JsonResult("succ", 0, null, null);
			return result.toJsonString();
		}
		catch (Exception e) {
			JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			return result.toJsonString();
		}		 
	 }
	 
	 @ResponseBody
	 @RequestMapping(value="GradeMaster/modifyGradeMaster")
	 public String modifyGradeMaster(GradeMasterForm gradeMaster) throws Exception
	 {
		 try {
			 if (gradeMasterService.modifyGradeMaster(gradeMaster)) {			
				 JsonResult result = new JsonResult("succ", 0, null, null);
				 return result.toJsonString();
			 }
			 else
				 throw new Exception("修改年级主任失败");
		 }
		 catch (Exception e) {
			 JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			 return result.toJsonString();
		}		
	 }
	 
	 @ResponseBody
	 @RequestMapping(value="GradeMaster/cancelGradeMaster")
	 public String cancelGradeMaster(int gradeMasterId) throws Exception
	 {
		 try {
			 gradeMasterService.cancelGradeMaster(gradeMasterId);
			
			JsonResult result = new JsonResult("succ", 0, null, null);
			return result.toJsonString();
		}
		catch (Exception e) {
			JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			return result.toJsonString();
		}		 
	 }

}
