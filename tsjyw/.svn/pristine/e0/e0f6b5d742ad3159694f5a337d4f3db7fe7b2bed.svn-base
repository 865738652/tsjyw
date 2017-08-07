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
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demonstration.hibernate.dao.support.Page;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.iasd.form.UserForm;
import edu.iasd.pojo.ViewSchool;
import edu.iasd.pojo.ViewUser;
import edu.iasd.search.QueryModel;
import edu.iasd.search.SearchParam;
import edu.iasd.security.UserInfo;
import edu.iasd.service.UserService;

@Controller
public class UserManageController extends ControllerBase {
	@Autowired
	private UserService userService;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) throws Exception {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    dateFormat.setLenient(false);
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));//true:允许输入空值，false:不能为空值
	}
	
	@RequestMapping("UserManage")
	public String showAll()	{
		return "UserManage";
	}
	
	@RequestMapping("Register")
	public String register(Model model)	{
		return "Register";
	}
	
	@ResponseBody
	@RequestMapping(value="UserManage/getAllUser")
	public String getAllUser(int pageNumber, int pageSize, @SearchParam QueryModel queryModel) throws Exception {
		try {
			Page page = userService.getAllUser(queryModel.toQueryString(), pageNumber, pageSize);
			JsonResult result;
			if (page.getResult() == null)
				result = new JsonResult("succ", 0, new ArrayList<ViewUser>(), null);
			else {
				List<ViewUser> teachers = (List<ViewUser>)page.getResult();
				result = new JsonResult("succ",(int)page.getTotalCount(), teachers, null);
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
	@RequestMapping(value="UserManage/getUser")
	public String getUser(int userId) throws Exception {
		try {
			ViewUser user = userService.getUser(userId);
			
			JsonResult result = new JsonResult("succ", 0, null, user);
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
	 @RequestMapping(value="UserManage/createUser")
	 public String createUser(UserForm user) throws Exception
	 {
		 try {
			 String id = userService.createUser(user);
			 if ( id != null) {			
				 JsonResult result = new JsonResult("succ", 0, null, id);
				 ObjectMapper objectMapper = new ObjectMapper();
				 String jsonString = objectMapper.writeValueAsString(result);
				 System.out.println(jsonString);
				 return jsonString;
			 }
			 else
				 throw new Exception("创建用户失败");
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
	 @RequestMapping(value="UserManage/deleteUser")
	 public String deleteUser(int userId) throws Exception
	 {
		 try {
			 userService.deleteUser(userId);
			
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
	 @RequestMapping(value="UserManage/modifyUser")
	 public String modifyUser(UserForm user) throws Exception
	 {
		 try {
			 if (userService.modifyUser(user)) {			
				 JsonResult result = new JsonResult("succ", 0, null, null);
				 ObjectMapper objectMapper = new ObjectMapper();
				 String jsonString = objectMapper.writeValueAsString(result);
				 System.out.println(jsonString);
				 return jsonString;
			 }
			 else
				 throw new Exception("修改用户失败");
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
	 @RequestMapping(value="UserManage/changePassword")
	 public String changePassword(int userId, String newPassword) throws Exception
	 {
		 try {
			 if (userService.changePassword(userId, newPassword)) {			
				 JsonResult result = new JsonResult("succ", 0, null, null);
				 ObjectMapper objectMapper = new ObjectMapper();
				 String jsonString = objectMapper.writeValueAsString(result);
				 System.out.println(jsonString);
				 return jsonString;
			 }
			 else
				 throw new Exception("修改密码失败");
		 }
		 catch (Exception e) {
			 JsonResult result = new JsonResult("fail", 0, null, "修改密码失败: " + e.getMessage());
			 ObjectMapper objectMapper = new ObjectMapper();
			 String jsonString = objectMapper.writeValueAsString(result);
			 System.out.println(jsonString);
			 return jsonString;
		}		
	 }
}
