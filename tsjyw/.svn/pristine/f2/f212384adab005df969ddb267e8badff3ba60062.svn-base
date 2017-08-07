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

import edu.iasd.form.CountyForm;
import edu.iasd.pojo.ViewCounty;
import edu.iasd.search.QueryModel;
import edu.iasd.search.SearchParam;
import edu.iasd.security.UserInfo;
import edu.iasd.service.CountyService;


@Controller
public class CountyManageController extends ControllerBase{
	
	
	@Autowired
	private CountyService countyService;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) throws Exception {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    dateFormat.setLenient(false);
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));//true:允许输入空值，false:不能为空值
	}
	@RequestMapping("CountyManage")
	public String showAll(){
		
		return "CountyManage";
		
	}
	@ResponseBody
	@RequestMapping("CountyManage/getCounty")
	public String getCountyInformation(int countyId) throws Exception {
		try {
			ViewCounty county = countyService.getCountyInformation(countyId);
			
			JsonResult result = new JsonResult("succ", 0, null, county);
			return result.toJsonString();
		}
		catch (Exception e) {
			JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			return result.toJsonString();
		}
	 }   

	//得到全部的区县
	@ResponseBody
	@RequestMapping("CountyManage/getAllCounty")
	public String getAllcounty(HttpServletRequest request, int pageNumber, int pageSize, @SearchParam QueryModel queryModel) throws Exception {
		
		try {
			UserInfo curUser = super.getCurrentUser(request);
			Page page = countyService.getAllcounty(curUser.getViewUser().getUserId(), queryModel.toQueryString(), pageNumber, pageSize);
			JsonResult result;
			if (page.getResult() == null)
				result = new JsonResult("succ", 0, new ArrayList<ViewCounty>(), null);
			else {
				List<ViewCounty> countys = (List<ViewCounty>)page.getResult();
				result = new JsonResult("succ", (int)page.getTotalCount(), countys, null);
			}
			return result.toJsonString();
		}
		catch (Exception e) {
			JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			return result.toJsonString();
		}
	} 
	 
	 //创建区县
	 @ResponseBody
	 @RequestMapping(value="CountyManage/createCounty")
	 public String createCounty(CountyForm county) throws Exception
	 {
		 try {
			 String id = countyService.createCounty(county);
			 if ( id != null) {
				 
				 JsonResult result = new JsonResult("succ", 0, null, id);
				 return result.toJsonString();
				 
			 }
			 else
				 throw new Exception("创建区县失败");
		 }
		 catch (Exception e) {
			 JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			 return result.toJsonString();
		}		
	 }
	 
	 //删除区县	 
	 @ResponseBody
	 @RequestMapping(value="CountyManage/deleteCounty")
	 public String deleteCounty(int countyId) throws Exception
	 {
		 try {
			countyService.deleteCounty(countyId);
			JsonResult result = new JsonResult("succ", 0, null, null);
			return result.toJsonString();
		}
		catch (Exception e) {
			JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			return result.toJsonString();
		}		 
	 }
	 //修改区县
	 @ResponseBody
	 @RequestMapping(value="CountyManage/modifyCounty")
	 public String modifyCounty(CountyForm county) throws Exception
	 {
		 try {
			 if (countyService.modify(county)) {			
				 JsonResult result = new JsonResult("succ", 0, null, null);
				 return result.toJsonString();
			 }
			 else
				 throw new Exception("修改区县失败");
		 }
		 catch (Exception e) {
			 JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			 return result.toJsonString();
		}		
	 }
}

