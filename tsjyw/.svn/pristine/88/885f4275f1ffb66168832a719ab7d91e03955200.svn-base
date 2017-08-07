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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demonstration.hibernate.dao.support.Page;

import edu.iasd.form.BusinessManagerForm;
import edu.iasd.pojo.ViewBusinessManager;
import edu.iasd.service.BusinessManagerService;

@Controller
public class BusinessManagerController extends ControllerBase{
	
	@Autowired
	private BusinessManagerService businessManagerService;
	
	protected void initBinder(WebDataBinder binder) throws Exception {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    dateFormat.setLenient(false);
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));//true:ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½Öµï¿½ï¿½false:ï¿½ï¿½ï¿½ï¿½Îªï¿½ï¿½Öµ
	}
	
	@RequestMapping("BusinessManager/{businessId}")
	public String showAllBusinessManager(@PathVariable int businessId,Model model)
	{
		model.addAttribute("businessId",businessId);
		return "BusinessManager";
	}
	
	@ResponseBody
	@RequestMapping(value = "BusinessManager/getBusinessManagerByBusiness")
	public String getBusinessManagerByBusiness(int businessId,int pageNumber,int pageSize) throws Exception{
		try{
			Page page = businessManagerService.getBusinessManagerByBusiness(pageNumber, pageSize, businessId);
			JsonResult result;
			if(page.getResult() == null)
				result = new JsonResult("succ",0,new ArrayList<ViewBusinessManager>(),null);
			else{
				List<ViewBusinessManager> businessManager = (List<ViewBusinessManager>)page.getResult();
				result = new JsonResult("succ",(int)page.getTotalCount(),businessManager,null);
			}
			return result.toJsonString();
		}catch(Exception e){
			JsonResult result = new JsonResult("fail", 0, null,e.getMessage());
			return result.toJsonString();
		}
	}
	
	@ResponseBody
	@RequestMapping(value="BusinessManager/getBusinessManager")
	public String getBusinessManager(int businessManagerId) throws Exception{
		try{
			ViewBusinessManager businessManager = businessManagerService.getBusinessManager(businessManagerId);
			System.out.println("get businessManager:" + businessManager.getUserNickname());
			
			JsonResult result = new JsonResult("succ", 0, null, businessManager);
			return result.toJsonString();
		}
		catch (Exception e) {
			JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			return result.toJsonString();
		}
	}
	
	@ResponseBody
	@RequestMapping(value="BusinessManager/createBusinessManager")
	public String createBusinessManager(BusinessManagerForm businessManager) throws Exception{
		try{
			String id= businessManagerService.createBusinessManager(businessManager);
			if(id != null){
				JsonResult result = new JsonResult("succ",0,null,id);
				return result.toJsonString();
			}
			else
				throw new Exception("´´½¨ÉÌ»§¹ÜÀíÔ±Ê§°Ü");
		}catch (Exception e) {
			 JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			 return result.toJsonString();
		}	
	}
	
	@ResponseBody
	 @RequestMapping(value="BusinessManager/deleteBusinessManager")
	public String deleteBusinessManager(int businessManagerId) throws Exception {
		try{
			businessManagerService.deleteBusinessManager(businessManagerId);
			JsonResult result = new JsonResult("succ", 0, null, null);
			return result.toJsonString();
		}
		catch (Exception e) {
			JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			return result.toJsonString();
		}	
	}
	
	@ResponseBody
	 @RequestMapping(value="BusinessManager/modifyBusinessManager") 
	public String modifyBusinessManager(BusinessManagerForm businessManager) throws Exception
	 {
		try{
			if(businessManagerService.modifyBusinessManager(businessManager)){
				JsonResult result = new JsonResult("succ", 0, null, null);
				 return result.toJsonString();
			}
			else
				throw new Exception("ä¿®æ”¹å¤±è´¥");
		}
		
		catch (Exception e) {
			 JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			 return result.toJsonString();
		}	
	 }
}
