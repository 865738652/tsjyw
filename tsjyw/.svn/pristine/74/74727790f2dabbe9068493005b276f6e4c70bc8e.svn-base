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

import edu.iasd.form.BusinessForm;
import edu.iasd.pojo.ViewBusiness;
import edu.iasd.search.QueryModel;
import edu.iasd.search.SearchParam;
import edu.iasd.service.BusinessService;


@Controller
public class BusinessController extends ControllerBase{
	@Autowired
	private BusinessService businessService;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) throws Exception {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    dateFormat.setLenient(false);
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));//true:允许输入空值，false:不能为空值
	}
	
	@RequestMapping("BusinessManage")
	public String ShowAll(){
		return "BusinessManage";
	}

	
	@ResponseBody
	@RequestMapping(value="BusinessManage/getAllBusiness")
	public String getAllBusiness( int pageNumber, int pageSize,HttpServletRequest request,@SearchParam QueryModel queryModel) throws Exception {
		
		try {
			Integer userId = super.getCurrentUser(request).getViewUser().getUserId();
			Page page = businessService.getAllBusiness(pageNumber, pageSize,userId,queryModel.toQueryString());
			JsonResult result;
			if (page.getResult() == null)
				result = new JsonResult("succ", 0, new ArrayList<ViewBusiness>(), null);
			else {
				List<ViewBusiness> business = (List<ViewBusiness>)page.getResult();
				System.out.println("1111111111"+business.get(0).getBusinessName());
				result = new JsonResult("succ", (int)page.getTotalCount(), business, null);
			}
			return result.toJsonString();
		}
		catch (Exception e) {
			e.printStackTrace();
			JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			return result.toJsonString();
		}
	} 
	
	
	@ResponseBody
	@RequestMapping(value="BusinessManage/getBusiness")
	public String getBusiness(int businessId) throws Exception {
		try {
			ViewBusiness business = businessService.getBusiness(businessId);
			System.out.println("get business: "+business.getBusinessName()); 
			
			JsonResult result = new JsonResult("succ", 0, null, business);
			return result.toJsonString();
		}
		catch (Exception e) {
			JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			return result.toJsonString();
		}
	 } 
	
	@ResponseBody
	 @RequestMapping(value="BusinessManage/createBusiness")
	 public String createBusiness(BusinessForm business) throws Exception
	 {
		 try {
			 String id = businessService.createBusiness(business);
			 if ( id != null) {			
				 JsonResult result = new JsonResult("succ", 0, null, id);
				 return result.toJsonString();
			 }
			 else
				 throw new Exception("创建商家失败");
		 }
		 catch (Exception e) {
			 JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			 return result.toJsonString();
		}		
	 }
	
	 @ResponseBody
	 @RequestMapping(value="BusinessManage/deleteBusiness")
	 public String deleteBusiness(int businessId) throws Exception
	 {
		 try {
			 businessService.deleteBusiness(businessId);
			
			JsonResult result = new JsonResult("succ", 0, null, null);
			return result.toJsonString();
		}
		catch (Exception e) {
			JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			return result.toJsonString();
		}		 
	 }
	 
	 @ResponseBody
	 @RequestMapping(value="BusinessManage/modifyBusiness")
	 public String modifyBusiness(BusinessForm business) throws Exception
	 {
		 try {
			 if (businessService.modifyBusiness(business)) {			
				 JsonResult result = new JsonResult("succ", 0, null, null);
				 return result.toJsonString();
			 }
			 else
				 throw new Exception("修改商家失败");
		 }
		 catch (Exception e) {
			 JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			 return result.toJsonString();
		}		
	 }
	 
}
