package edu.iasd.controller;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demonstration.hibernate.dao.support.Page;




import edu.iasd.form.NetCourseTypeForm;
import edu.iasd.pojo.ViewNetCourseType;
import edu.iasd.security.UserInfo;
import edu.iasd.service.NetCourseTypeService;


@Controller
public class NetCourseTypeController extends ControllerBase{
	@Autowired
	private NetCourseTypeService netCourseTypeService;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) throws Exception {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    dateFormat.setLenient(false);
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));//true:允许输入空值，false:不能为空值
	}
	
	@RequestMapping("NetCourseTypeManage")
	public String ShowAll(){
		return "NetCourseTypeManage";
	}
	
	@ResponseBody
	@RequestMapping("NetCourseTypeManage/getNetCourseType")
	public String getNetCourseType(int netCourseTypeId) throws Exception {
		try {
			ViewNetCourseType netCourseType=netCourseTypeService.getNetCourseType(netCourseTypeId);
			
			JsonResult result = new JsonResult("succ", 0, null, netCourseType);
			return result.toJsonString();
		}
		catch (Exception e) {
			JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			return result.toJsonString();
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "NetCourseTypeManage/getAllNetCourseType")
	public String getAllNetCourseType(HttpServletRequest request,int pageNumber,int pageSize) throws Exception {
		try {
			UserInfo User = super.getCurrentUser(request);
			Page page = netCourseTypeService.getAllNetCourseType(User.getViewUser().getUserId(),pageNumber, pageSize);
			JsonResult result;
			if(page.getResult()==null)
				result = new JsonResult("succ", 0 ,new ArrayList<ViewNetCourseType>(),null);
			else{
				List<ViewNetCourseType> vnt=(List<ViewNetCourseType>)page.getResult();
				result = new JsonResult("succ",(int)page.getTotalCount(),vnt,null);
			}
			return result.toJsonString();
		}
		catch (Exception e) {
			JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			return result.toJsonString();
		}
	}
	
	 @ResponseBody
	 @RequestMapping(value="NetCourseTypeManage/createNetCourseType")
	 public String createNetCourseType(NetCourseTypeForm netCourseType) throws Exception{
		 try{
			 String id = netCourseTypeService.createNetCourseType(netCourseType);
			 if(id != null){
				 JsonResult result = new JsonResult("succ",0,null,id);
				return result.toJsonString();
			 }
			 else
				 throw new Exception("创建网络课程类型失败");
		 }
		 catch (Exception e) {
			 JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			 return result.toJsonString();
		}		
	 }
	 
	 @ResponseBody
	 @RequestMapping(value="NetCourseTypeManage/deleteNetCourseType")
	 public String deleteNetCourseType(int netCourseTypeId) throws Exception{
		 try{
			 netCourseTypeService.deleteNetCourseType(netCourseTypeId);
			 JsonResult result=new JsonResult("succ",0,null,null);
			 return result.toJsonString();
		 }
		 catch(Exception e){
			 JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			  return result.toJsonString();
		 }
	 }
	 
	 @ResponseBody
	 @RequestMapping(value="NetCourseTypeManage/modifyNetCourseType")
	 public String modifyNetCourseType(NetCourseTypeForm netCourseType) throws Exception{
		 try{
			 if(netCourseTypeService.modifyNetCourseType(netCourseType)){
				 JsonResult result=new JsonResult("succ",0,null,null);
				 return result.toJsonString();
			 }else
				 throw new Exception("修改网络课程类型失败");
		 }
		 catch(Exception e){
			 JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			 return result.toJsonString();
		 }
	 }
}


