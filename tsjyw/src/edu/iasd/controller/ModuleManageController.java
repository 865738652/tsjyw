package edu.iasd.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;
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

import edu.iasd.form.ModuleForm;
import edu.iasd.pojo.ModuleState;
import edu.iasd.pojo.ModuleType;
import edu.iasd.pojo.ViewModule;
import edu.iasd.pojo.ViewSchool;
import edu.iasd.search.QueryModel;
import edu.iasd.search.SearchParam;
import edu.iasd.security.UserInfo;
import edu.iasd.service.ModuleService;
import edu.iasd.service.SchoolService;

@Controller
public class ModuleManageController extends ControllerBase {
	
	@Autowired
	private ModuleService moduleService;
	
	@Autowired
	private SchoolService schoolService;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) throws Exception {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    dateFormat.setLenient(false);
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));//true:允许输入空值，false:不能为空值
	}
	
	@RequestMapping("ModuleManage")
	public String showAll(Model model)	{
		model.addAttribute("school", false);
		model.addAttribute("schoolid",0);
		return "ModuleManage";
	}	
	
	@RequestMapping(value="ModuleManage/School")
	public String showModuleBySchool(HttpServletRequest request, Model model)	{
		
		try {
			UserInfo curUser = super.getCurrentUser(request);
			Page page = schoolService.getAllSchool(curUser.getViewUser().getUserId(), "", 1, 10);
			if (page.getTotalCount() == 1) {
				/*
				 * 如果只负责一个学校，在页面上直接显示这个学校的栏目
				 */
				List<ViewSchool> schools = (List<ViewSchool>)page.getResult();
				model.addAttribute("school", true);
				model.addAttribute("schoolid",schools.get(0).getSchoolId());
			}
			else if (page.getTotalCount() == 0)
				throw new Exception("没有管理学校权限");
			else {
				/*
				 * 如果负责多个学校，在页面上选择管理哪个学校
				 */
				model.addAttribute("school", true);
				model.addAttribute("schoolid",-1);
			}
			return "ModuleManage";
		}
		catch (Exception e) {			
			return "403";
		}
	}	
	
	/*
	@RequestMapping("ModuleManage/{schoolid}")
	public String showModuleBySchool(@PathVariable int schoolid,Model model) {
		model.addAttribute("school", true);
		model.addAttribute("schoolid",schoolid);
		return "ModuleManage";
	}
	*/
	
	@ResponseBody
	@RequestMapping(value="ModuleManage/getAllModule")
	public String getAllModule() throws Exception {
		try {
			List<ViewModule> modules = moduleService.getAllModule();
			JsonResult result;
			if (modules == null)
				result = new JsonResult("succ", 0, new ArrayList<ViewModule>(), null);
			else 
				result = new JsonResult("succ", modules.size(), modules, null);
			
			return result.toJsonString();
		}
		catch (Exception e) {
			JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			return result.toJsonString();
		}
	}
	
	@ResponseBody
	@RequestMapping(value="ModuleManage/getTopModule")
	public String getTopModule(@SearchParam QueryModel queryModel) throws Exception {
		try {
			List<ViewModule> modules = moduleService.getTopModule(queryModel.toQueryString());
			JsonResult result;
			if (modules == null)
				result = new JsonResult("succ", 0, new ArrayList<ViewModule>(), null);
			else 
				result = new JsonResult("succ", modules.size(), modules, null);
			
			return result.toJsonString();
		}
		catch (Exception e) {
			JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			return result.toJsonString();
		}
	}
	
	@ResponseBody
	@RequestMapping(value="ModuleManage/getSchoolTopModule")
	public String getSchoolTopModule(int schoolId, @SearchParam QueryModel queryModel) throws Exception {
		try {
			List<ViewModule> modules = moduleService.getSchoolTopModule(queryModel.toQueryString(), schoolId);
			JsonResult result;
			if (modules == null)
				result = new JsonResult("succ", 0, new ArrayList<ViewModule>(), null);
			else 
				result = new JsonResult("succ", modules.size(), modules, null);
			
			return result.toJsonString();
		}
		catch (Exception e) {
			JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			return result.toJsonString();
		}
	}
	
	@ResponseBody
	@RequestMapping(value="ModuleManage/getAllModuleBySchool")
	public String getAllModuleBySchool(int schoolId) throws Exception {
		try {
			List<ViewModule> modules = moduleService.getAllModuleBySchool(schoolId);
			JsonResult result;
			if (modules == null)
				result = new JsonResult("succ", 0, new ArrayList<ViewModule>(), null);
			else 
				result = new JsonResult("succ", modules.size(), modules, null);
			
			return result.toJsonString();
		}
		catch (Exception e) {
			JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			return result.toJsonString();
		}
	}
	
	@ResponseBody
	@RequestMapping(value="ModuleManage/getChildModule")
	public String getChildModule(int parentId) throws Exception {
		try {
			List<ViewModule> modules = moduleService.getChildModule(parentId);
			JsonResult result;
			if (modules == null)
				result = new JsonResult("succ", 0, new ArrayList<ViewModule>(), null);
			else 
				result = new JsonResult("succ", modules.size(), modules, null);
			
			return result.toJsonString();
		}
		catch (Exception e) {
			JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			return result.toJsonString();
		}
	}
	
	@ResponseBody
	@RequestMapping(value="ModuleManage/getModule")
	public String getModule(int moduleId) throws Exception {
		try {
			ViewModule module = moduleService.getModule(moduleId);
			
			JsonResult result = new JsonResult("succ", 0, null, module);
			return result.toJsonString();
		}
		catch (Exception e) {
			JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			return result.toJsonString();
		}
	 } 
	
	 @ResponseBody
	 @RequestMapping(value="ModuleManage/createModule")
	 public String createModule(ModuleForm module) throws Exception
	 {
		 try {
			 String id = moduleService.createModule(module);
			 if ( id != null) {			
				 JsonResult result = new JsonResult("succ", 0, null, id);
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
	 @RequestMapping(value="ModuleManage/deleteModule")
	 public String deleteModule(int moduleId) throws Exception
	 {
		 try {
			moduleService.deleteModule(moduleId);
			
			JsonResult result = new JsonResult("succ", 0, null, null);
			return result.toJsonString();
		}
		catch (Exception e) {
			JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			return result.toJsonString();
		}		 
	 }
	 
	 @ResponseBody
	 @RequestMapping(value="ModuleManage/modifyModule")
	 public String modifyModule(ModuleForm module) throws Exception
	 {
		 try {
			 if (moduleService.modifyModule(module)) {			
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
	@RequestMapping(value="ModuleManage/getModuleState")
	public String getModuleState() throws Exception {
		try {
			List<ModuleState> states = moduleService.getModuleState();
			JsonResult result = new JsonResult("succ", states.size(), states, null);
			return result.toJsonString();
		}
		catch (Exception e) {
			JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			return result.toJsonString();
		}
	}
	
	@ResponseBody
	@RequestMapping(value="ModuleManage/getModuleType")
	public String getModuleType() throws Exception {
		try {
			List<ModuleType> types = moduleService.getModuleType();
			JsonResult result = new JsonResult("succ", types.size(), types, null);
			return result.toJsonString();
		}
		catch (Exception e) {
			JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			return result.toJsonString();
		}
	}
}
