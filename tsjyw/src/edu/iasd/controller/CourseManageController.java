package edu.iasd.controller;

import java.io.IOException;

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
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.iasd.form.CourseForm;
import edu.iasd.form.TeacherForm;
import edu.iasd.pojo.SchoolCourseState;
import edu.iasd.pojo.ViewCounty;
import edu.iasd.pojo.ViewCourse;
import edu.iasd.pojo.ViewTeacher;
import edu.iasd.service.CourseManageService;

@Controller
public class CourseManageController {

	@Autowired
	private CourseManageService courseManageService;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) throws Exception {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    dateFormat.setLenient(false);
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));//true:允许输入空值，false:不能为空值
	}
	
	@RequestMapping("CourseManage")
	public String ShowAll(){
		return "CourseManage";
	}
	
	@RequestMapping("CourseManage/{schoolId}")
	public String ShowCourseBySchool(@PathVariable int schoolId,Model model){
		model.addAttribute("schoolId",schoolId);
		return "CourseManage";
	}
	
	@ResponseBody
	@RequestMapping(value="CourseManage/getCourseBySchool")
	public String getCourseBySchool( int pageNumber, int pageSize ,int schoolId) throws Exception {
		try {
			Page page = courseManageService.getCourseBySchool(pageNumber, pageSize, schoolId);
			JsonResult result;
			if (page.getResult() == null)
				result = new JsonResult("succ", 0, new ArrayList<ViewCourse>(), null);
			else {
				List<ViewCourse> courses = (List<ViewCourse>)page.getResult();
				result = new JsonResult("succ", (int)page.getTotalCount(), courses, null);
			}
			return result.toJsonString();
		}
		catch (Exception e) {
			JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			return result.toJsonString();
		}
	}
	
	@ResponseBody
	@RequestMapping(value="CourseManage/getAllCourse")
	public String getAllCourse( int pageNumber, int pageSize) throws Exception {
		
		try {
			Page page = courseManageService.getAllCourse(pageNumber, pageSize);
			JsonResult result;
			if (page.getResult() == null)
				result = new JsonResult("succ", 0, new ArrayList<ViewCourse>(), null);
			else {
				List<ViewCourse> courses = (List<ViewCourse>)page.getResult();
				result = new JsonResult("succ", (int)page.getTotalCount(), courses, null);
			}
			return result.toJsonString();
		}
		catch (Exception e) {
			JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			return result.toJsonString();
		}
	} 
	
	@ResponseBody
	@RequestMapping(value="CourseManage/getCourse")
	public String getCourse(int schoolCourseId) throws Exception {
		try {
			ViewCourse course = courseManageService.getCourse(schoolCourseId);
			System.out.println("get course: "+course.getSchoolCourseName()); 
			
			JsonResult result = new JsonResult("succ", 0, null, course);
			return result.toJsonString();
		}
		catch (Exception e) {
			JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			return result.toJsonString();
		}
	 } 
	
	
	@ResponseBody
	 @RequestMapping(value="CourseManage/createCourse")
	 public String createCourse(CourseForm schoolCourse) throws Exception
	 {
		 try {
			 String id = courseManageService.createCourse(schoolCourse);
			 if ( id != null) {			
				 JsonResult result = new JsonResult("succ", 0, null, id);
				 return result.toJsonString();
			 }
			 else
				 throw new Exception("创建课程失败");
		 }
		 catch (Exception e) {
			 JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			 return result.toJsonString();
		}		
	 }
	
	 @ResponseBody
	 @RequestMapping(value="CourseManage/deleteCourse")
	 public String deleteCourse(int schoolCourseId) throws Exception
	 {
		 try {
			 courseManageService.deleteCourse(schoolCourseId);
			
			JsonResult result = new JsonResult("succ", 0, null, null);
			return result.toJsonString();
		}
		catch (Exception e) {
			JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			return result.toJsonString();
		}		 
	 }
	 
	 @ResponseBody
	 @RequestMapping(value="CourseManage/modifyCourse")
	 public String modifyCourse(CourseForm schoolCourse) throws Exception
	 {
		 try {
			 if (courseManageService.modifyCourse(schoolCourse)) {			
				 JsonResult result = new JsonResult("succ", 0, null, null);
				 return result.toJsonString();
			 }
			 else
				 throw new Exception("修改课程失败");
		 }
		 catch (Exception e) {
			 JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			 return result.toJsonString();
		}		
	 }
	 
	 @ResponseBody
	 @RequestMapping(value="CourseManage/getSchoolCourseState")
	 public String getSchoolCourseState() throws Exception
	 {
		 try {
	         List<SchoolCourseState> list = courseManageService.getSchoolCourseState();		
			 JsonResult result = new JsonResult("succ", list.size(), list, null);
			 return result.toJsonString();			 
		 }
		 catch (Exception e) {
			 JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			 return result.toJsonString();
		}		
	 }
}
