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

import edu.iasd.form.ClassMasterForm;
import edu.iasd.pojo.ViewClassMaster;
import edu.iasd.pojo.ViewSchoolClass;
import edu.iasd.service.ClassMasterService;
import edu.iasd.service.SchoolClassService;


@Controller
public class ClassMasterController {
	@Autowired
	private ClassMasterService classMasterService;
	
	@Autowired
	private SchoolClassService schoolClassService;

	@RequestMapping("ClassMaster/{schoolClassId}")
	public String showAllClassMaster(@PathVariable int schoolClassId,Model model)
	{
		ViewSchoolClass vsc= schoolClassService.getSchoolClass(schoolClassId);
		model.addAttribute("schoolClassid",schoolClassId);
		model.addAttribute("schoolid", vsc.getSchoolId());
		return "ClassMaster";
	}
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) throws Exception {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    dateFormat.setLenient(false);
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));//true:允许输入空值，false:不能为空值
	}
	
	@ResponseBody
	@RequestMapping(value="ClassMaster/getClassMasterBySchoolClass")
	public String getClassMasterBySchoolClass(int schoolClassId,int pageNumber,int pageSize) throws Exception{
		
		try{
			
			Page page = classMasterService.getClassMasterBySchoolClass(pageNumber,pageSize,schoolClassId);
			JsonResult result;
			System.out.println(schoolClassId);
			if (page.getResult() == null)
				result = new JsonResult("succ", 0, new ArrayList<ViewClassMaster>(), null);
			else{
				List<ViewClassMaster> classMaster =(List<ViewClassMaster>)page.getResult();
				result = new JsonResult("succ",(int)page.getTotalCount(),classMaster,null);
			}
			return result.toJsonString();
		}
		catch (Exception e) {
			JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			return result.toJsonString();
		}
	}
	
	@ResponseBody
	@RequestMapping(value="ClassMaster/getClassMaster")
	public String getClassMaster(int classMasterId) throws Exception{
		try {
			ViewClassMaster classMaster = classMasterService.getClassMaster(classMasterId);
			System.out.println("get classMaster: "+classMaster.getUserNickname()); 
			
			JsonResult result = new JsonResult("succ", 0, null, classMaster);
			return result.toJsonString();
		}
		catch (Exception e) {
			JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			return result.toJsonString();
		}
	}
	
	@ResponseBody
	 @RequestMapping(value="ClassMaster/selectClassMaster")
	 public String selectClassMaster(int userId, int schoolClassId) throws Exception
	 {
		 try {
			 String id = classMasterService.selectClassMaster(userId, schoolClassId);
			 if ( id != null) {			
				 JsonResult result = new JsonResult("succ", 0, null, id);
				 return result.toJsonString();
			 }
			 else
				 throw new Exception("设置班主任失败");
		 }
		 catch (Exception e) {
			 JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			 return result.toJsonString();
		}		
	 }
	
	 @ResponseBody
	 @RequestMapping(value="ClassMaster/createClassMaster")
	 public String createClassMaster(ClassMasterForm classMaster) throws Exception
	 {
		 try {
			 String id = classMasterService.createClassMaster(classMaster);
			 if ( id != null) {			
				 JsonResult result = new JsonResult("succ", 0, null, id);
				 return result.toJsonString();
			 }
			 else
				 throw new Exception("添加班主任失败");
		 }
		 catch (Exception e) {
			 JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			 return result.toJsonString();
		}		
	 }
	 
	 @ResponseBody
	 @RequestMapping(value="ClassMaster/deleteClassMaster")
	 public String deleteClassMaster(int classMasterId) throws Exception
	 {
		 try {
			 classMasterService.deleteClassMaster(classMasterId);
			
			JsonResult result = new JsonResult("succ", 0, null, null);
			return result.toJsonString();
		}
		catch (Exception e) {
			JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			return result.toJsonString();
		}		 
	 }
	 
	 @ResponseBody
	 @RequestMapping(value="ClassMaster/modifyClassMaster")
	 public String modifyClassMaster(ClassMasterForm classMaster) throws Exception
	 {
		 try {
			 if (classMasterService.modifyClassMaster(classMaster)) {			
				 JsonResult result = new JsonResult("succ", 0, null, null);
				 return result.toJsonString();
			 }
			 else
				 throw new Exception("修改班主任失败");
		 }
		 catch (Exception e) {
			 JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			 return result.toJsonString();
		}		
	 }
	 
	 @ResponseBody
	 @RequestMapping(value="ClassMaster/cancelClassMaster")
	 public String cancelClassMaster(int classMasterId) throws Exception
	 {
		 try {
			 classMasterService.cancelClassMaster(classMasterId);
			
			JsonResult result = new JsonResult("succ", 0, null, null);
			return result.toJsonString();
		}
		catch (Exception e) {
			JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			return result.toJsonString();
		}		 
	 }

}


	
