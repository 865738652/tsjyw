package edu.iasd.controller;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demonstration.hibernate.dao.support.Page;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.iasd.form.ClassForm;
import edu.iasd.pojo.ViewSchoolClass;
import edu.iasd.pojo.ViewSchool;
import edu.iasd.pojo.ViewTeacher;
import edu.iasd.search.QueryModel;
import edu.iasd.search.SearchParam;
import edu.iasd.security.UserInfo;
import edu.iasd.service.SchoolClassService;
import edu.iasd.service.TeacherService;


@Controller
public class SchoolClassManageController extends ControllerBase {
	
	
	@Autowired
	private SchoolClassService schoolClassService;
	
	@Autowired
	private TeacherService teacherService;
	
	@RequestMapping("SchoolClassManage")
	public String ShowSchoolClassBySchool(HttpServletRequest request, Model model)
	{
		try{
			UserInfo curUser = super.getCurrentUser(request);
			ViewTeacher vt = teacherService.getTeacher(curUser.getViewUser().getUserId());
			if (vt != null)
				model.addAttribute("schoolid",vt.getSchoolId());
			return "SchoolClassManage";
		}
		catch (Exception e) {
			return "403";
		}
	}
	
	@RequestMapping("SchoolClassManage/{schoolid}")
	public String ShowSchoolClassBySchool(HttpServletRequest request, @PathVariable int schoolid,Model model)
	{
		try{
			model.addAttribute("schoolid",schoolid);
			return "SchoolClassManage";
		}
		catch (Exception e) {
			return "403";
		}
	}
	
	@ResponseBody
	@RequestMapping(value="SchoolClassManage/getSchoolClassBySchool")
	public String getSchoolClassBySchool(HttpServletRequest request, int schoolId, @SearchParam QueryModel queryModel, int pageNumber,int pageSize) throws Exception {
		try{
			UserInfo curUser = super.getCurrentUser(request);
			Page page=schoolClassService.getSchoolClassBySchool(curUser.getViewUser().getUserId(), pageNumber, pageSize, schoolId, queryModel.toQueryString());
			JsonResult result;
			if (page.getResult() == null)
				result = new JsonResult("succ", 0, new ArrayList<ViewSchoolClass>(), null);
			else {
				List<ViewSchoolClass> classes = (List<ViewSchoolClass>)page.getResult();
				result = new JsonResult("succ", (int)page.getTotalCount(), classes, null);
			}
			return result.toJsonString();
		}
		catch (Exception e) {
			JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			return result.toJsonString();
		}
	}
	//通过年级获得班级
	@ResponseBody
	@RequestMapping(value="SchoolClassManage/getSchoolClassByGrade")
	public String getSchoolClassByGrade(HttpServletRequest request, int gradeId,int pageNumber,int pageSize) throws Exception {
		try{
			Page page=schoolClassService.getSchoolClassByGrade(pageNumber, pageSize, gradeId);
			JsonResult result;
			if (page.getResult() == null)
				result = new JsonResult("succ", 0, new ArrayList<ViewSchoolClass>(), null);
			else {
				List<ViewSchoolClass> classes = (List<ViewSchoolClass>)page.getResult();
				result = new JsonResult("succ", (int)page.getTotalCount(), classes, null);
			}
			return result.toJsonString();
		}
		catch (Exception e) {
			JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			return result.toJsonString();
		}
	}
	
	@ResponseBody
	@RequestMapping(value="SchoolClassManage/getSchoolClass")
	public String getSchoolClass(int schoolClassId) throws Exception {
		try {
			ViewSchoolClass classGrage = schoolClassService.getSchoolClass(schoolClassId);
			
			JsonResult result = new JsonResult("succ", 0, null, classGrage);
			return result.toJsonString();
		}
		catch (Exception e) {
			JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			return result.toJsonString();
		}
	 }   

	//得到全部的班级
		@ResponseBody
		@RequestMapping(value="SchoolClassManage/getAllSchoolClass")
		public String getAllSchoolClass( int pageNumber, int pageSize) throws Exception {
			
			try {
				Page page = schoolClassService.getAllSchoolClass(pageNumber, pageSize);
				JsonResult result;
				if (page.getResult() == null)
					result = new JsonResult("succ", 0, new ArrayList<ViewSchoolClass>(), null);
				else {
					List<ViewSchoolClass> classes = (List<ViewSchoolClass>)page.getResult();
					result = new JsonResult("succ",(int)page.getTotalCount(), classes, null);
				}
				return result.toJsonString();
			}
			catch (Exception e) {
				JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
				return result.toJsonString();
			}
		} 
		 //创建班级
		 @ResponseBody
		 @RequestMapping(value="SchoolClassManage/createSchoolClass")
		 public String createSchoolClass(ClassForm classForm) throws Exception
		 {
			 try {
				 String id = schoolClassService.createSchoolClass(classForm);
				 if ( id != null) {
					 
					 JsonResult result = new JsonResult("succ", 0, null, id);
					 return result.toJsonString();
					 
				 }
				 else
					 throw new Exception("创建班级失败");
			 }
			 catch (Exception e) {
				 JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
				 return result.toJsonString();
			}		
		 }
		 
		 //删除班级	 
		 @ResponseBody
		 @RequestMapping(value="SchoolClassManage/deleteSchoolClass")
		 public String deleteSchoolClass(int schoolClassId) throws Exception
		 {
			 try {
				schoolClassService.deleteSchoolClass(schoolClassId);
				JsonResult result = new JsonResult("succ", 0, null, null);
				return result.toJsonString();
			}
			catch (Exception e) {
				JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
				return result.toJsonString();
			}		 
		 }
		 //修改班级
		 @ResponseBody
		 @RequestMapping(value="SchoolClassManage/modifySchoolClass")
		 public String modifySchoolClass(ClassForm classForm) throws Exception
		 {
			 try {
				 if (schoolClassService.modifySchoolClass(classForm)) {			
					 JsonResult result = new JsonResult("succ", 0, null, null);
					 return result.toJsonString();
				 }
				 else
					 throw new Exception("修改班级失败");
			 }
			 catch (Exception e) {
				 JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
				 return result.toJsonString();
			}		
		 }
		 
}


