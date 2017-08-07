package edu.iasd.controller;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demonstration.hibernate.dao.support.Page;

import edu.iasd.form.GradeForm;
import edu.iasd.pojo.ViewGrade;
import edu.iasd.pojo.ViewTeacher;
import edu.iasd.security.UserInfo;
import edu.iasd.service.GradeService;
import edu.iasd.service.TeacherService;

@Controller
public class GradeManageController extends ControllerBase {
	
	@Autowired
	private GradeService gradeService;
	
	@Autowired
	private TeacherService teacherService;
	
	@RequestMapping("GradeManage")
	public String ShowAll(HttpServletRequest request, Model model)
	{
		try{
			UserInfo curUser = super.getCurrentUser(request);
			ViewTeacher vt = teacherService.getTeacher(curUser.getViewUser().getUserId());
			if (vt != null)
				model.addAttribute("schoolid",vt.getSchoolId());
			return "GradeManage";
		}
		catch (Exception e) {
			return "403";
		}
	}
	
	@RequestMapping("GradeManage/{schoolid}")
	public String ShowAllGrade(@PathVariable int schoolid,Model model)
	{
		model.addAttribute("schoolid",schoolid);
		return "GradeManage";
	}
	
	@ResponseBody
	@RequestMapping(value="GradeManage/getGradeBySchool")
	public String getGradeBySchool(HttpServletRequest request, int schoolId,int pageNumber,int pageSize) throws Exception{
		
		try{
			UserInfo curUser = super.getCurrentUser(request);
			Page page = gradeService.getGradeBySchool(curUser.getViewUser().getUserId(), pageNumber, pageSize, schoolId);
			JsonResult result;
			if (page.getResult() == null)
				result = new JsonResult("succ", 0, new ArrayList<ViewGrade>(), null);
			else{
				List<ViewGrade> grades =(List<ViewGrade>)page.getResult();
				result = new JsonResult("succ",(int)page.getTotalCount(),grades,null);
			}
			return result.toJsonString();
		}
		catch (Exception e) {
			JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			return result.toJsonString();
		}
		
	}
	
	@ResponseBody
	@RequestMapping(value="GradeManage/getGrade")
	public String getGrade(int gradeId) throws Exception{
		try {
			ViewGrade grade = gradeService.getGrade(gradeId);
			System.out.println("get grade: "+grade.getGradeName()); 
			
			JsonResult result = new JsonResult("succ", 0, null, grade);
			return result.toJsonString();
		}
		catch (Exception e) {
			JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			return result.toJsonString();
		}
	}
	
	 @ResponseBody
	 @RequestMapping(value="GradeManage/createGrade")
	 public String createGrade(GradeForm grade) throws Exception
	 {
		 try {
			 String id = gradeService.createGrade(grade);
			 if ( id != null) {			
				 JsonResult result = new JsonResult("succ", 0, null, id);
				 return result.toJsonString();
			 }
			 else
				 throw new Exception("创建年级失败");
		 }
		 catch (Exception e) {
			 JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			 return result.toJsonString();
		}		
	 }
	 
	 @ResponseBody
	 @RequestMapping(value="GradeManage/deleteGrade")
	 public String deleteGrade(int gradeId) throws Exception
	 {
		 try {
			gradeService.deleteGrade(gradeId);
			
			JsonResult result = new JsonResult("succ", 0, null, null);
			return result.toJsonString();
		}
		catch (Exception e) {
			JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			return result.toJsonString();
		}		 
	 }
	 
	 @ResponseBody
	 @RequestMapping(value="GradeManage/modifyGrade")
	 public String modifyGrade(GradeForm grade) throws Exception
	 {
		 try {
			 if (gradeService.modifyGrade(grade)) {			
				 JsonResult result = new JsonResult("succ", 0, null, null);
				 return result.toJsonString();
			 }
			 else
				 throw new Exception("修改年级失败");
		 }
		 catch (Exception e) {
			 JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			 return result.toJsonString();
		}		
	 }
}
