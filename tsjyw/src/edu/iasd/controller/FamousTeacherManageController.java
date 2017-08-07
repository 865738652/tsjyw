package edu.iasd.controller;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demonstration.hibernate.dao.support.Page;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.iasd.form.FamousTeacherForm;
import edu.iasd.pojo.ViewFamousTeacher;
import edu.iasd.search.QueryModel;
import edu.iasd.search.SearchParam;
import edu.iasd.service.FamousTeacherService;
import edu.iasd.service.UserService;

@Controller
public class FamousTeacherManageController {
	@Autowired
	private FamousTeacherService famousTeacherService;
	
	@Autowired
	private UserService userService;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) throws Exception {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    dateFormat.setLenient(false);
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));//true:允许输入空值，false:不能为空值
	}
	
	@RequestMapping("FamousTeacherManage")
	public String showAll()	{
		return "FamousTeacherManage";
	}	
	
	@ResponseBody
	@RequestMapping(value={"FamousTeacherManage/getAllFamousTeacher", "Index/getAllFamousTeacher"})
	public String getAllFamousTeacher(int pageNumber, int pageSize, @SearchParam QueryModel queryModel) throws Exception {
		try {
			Page page = famousTeacherService.getAllFamousTeacher(queryModel.toQueryString(), pageNumber, pageSize);
			JsonResult result;
			if (page.getResult() == null)
				result = new JsonResult("succ", 0, new ArrayList<ViewFamousTeacher>(), null);
			else {
				List<ViewFamousTeacher> teachers = (List<ViewFamousTeacher>)page.getResult();
				result = new JsonResult("succ", (int)page.getTotalCount(), teachers, null);
			}
			return result.toJsonString();
		}
		catch (Exception e) {
			JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			return result.toJsonString();
		}
	}
	
	@ResponseBody
	@RequestMapping(value="FamousTeacherManage/getFamouseTeacher")
	public String getFamouseTeacher(int famousTeacherId) throws Exception {
		try {
			ViewFamousTeacher teacher = famousTeacherService.getFamouseTeacher(famousTeacherId);
			
			JsonResult result = new JsonResult("succ", 0, null, teacher);
			return result.toJsonString();
		}
		catch (Exception e) {
			JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			return result.toJsonString();
		}
	 } 
	
	 @ResponseBody
	 @RequestMapping(value="FamousTeacherManage/selectFamousTeacher")
	 public String selectFamousTeacher(int userId) throws Exception
	 {
		 try {
			 String id = famousTeacherService.selectFamousTeacher(userId);
			 if ( id != null) {			
				 JsonResult result = new JsonResult("succ", 0, null, id);
				 return result.toJsonString();
			 }
			 else
				 throw new Exception("设置名师失败");
		 }
		 catch (Exception e) {
			 JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			 return result.toJsonString();
		}		
	 }
	
	 @ResponseBody
	 @RequestMapping(value="FamousTeacherManage/createFamousTeacher")
	 public String createFamousTeacher(HttpServletRequest request, FamousTeacherForm teacher) throws Exception
	 {
		 try {
			 String id = famousTeacherService.createFamousTeacher(teacher);
			 if ( id != null) {
				 /*
				 String rootPath = request.getSession().getServletContext().getRealPath("/");
				 userService.saveWeQRCode(rootPath, Integer.parseInt(id));
				 */
				 JsonResult result = new JsonResult("succ", 0, null, id);
				 return result.toJsonString();
			 }
			 else
				 throw new Exception("创建教师失败");
		 }
		 catch (Exception e) {
			 JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			 return result.toJsonString();
		}		
	 }
	 
	 @ResponseBody
	 @RequestMapping(value="FamousTeacherManage/deleteFamousTeacher")
	 public String deleteFamousTeacher(int famousTeacherId) throws Exception
	 {
		 try {
			 famousTeacherService.deleteFamousTeacher(famousTeacherId);
			
			JsonResult result = new JsonResult("succ", 0, null, null);
			return result.toJsonString();
		}
		catch (Exception e) {
			JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			return result.toJsonString();
		}		 
	 }
	 
	 @ResponseBody
	 @RequestMapping(value="FamousTeacherManage/modifyFamousTeacher")
	 public String modifyFamousTeacher(HttpServletRequest request, FamousTeacherForm teacher) throws Exception
	 {
		 try {
			 if (famousTeacherService.modifyFamousTeacher(teacher)) {	
				 /*
				 String rootPath = request.getSession().getServletContext().getRealPath("/");
				 userService.saveWeQRCode(rootPath, teacher.getUserId());
				 */
				 JsonResult result = new JsonResult("succ", 0, null, null);
				 return result.toJsonString();
			 }
			 else
				 throw new Exception("修改教师失败");
		 }
		 catch (Exception e) {
			 JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			 return result.toJsonString();
		}		
	 }
	 
	 @ResponseBody
	 @RequestMapping(value="FamousTeacherManage/cancelFamousTeacher")
	 public String cancelFamousTeacher(int famousTeacherId) throws Exception
	 {
		 try {
			 famousTeacherService.cancelFamousTeacher(famousTeacherId);
			
			JsonResult result = new JsonResult("succ", 0, null, null);
			return result.toJsonString();
		}
		catch (Exception e) {
			JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			return result.toJsonString();
		}		 
	 }
}