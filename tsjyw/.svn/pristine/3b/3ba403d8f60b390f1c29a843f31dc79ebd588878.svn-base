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

import edu.iasd.form.CountyManagerForm;
import edu.iasd.pojo.ViewCountyManager;
import edu.iasd.service.CountyManagerService;

@Controller
public class CountyPeopleController extends ControllerBase {
	
	@Autowired
	private CountyManagerService countyManagerService;
	
	protected void initBinder(WebDataBinder binder) throws Exception {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    dateFormat.setLenient(false);
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));//true:允许输入空值，false:不能为空值
	}
	
	@RequestMapping("CountyPeople/{countyid}")
	public String showAllCountyPeople(@PathVariable int countyid,Model model)
	{
		//得出区县
		model.addAttribute("countyid",countyid);
		return "CountyPeople";
	}
	
	@ResponseBody
	@RequestMapping(value="CountyPeople/getCountyManagerByCounty")
	public String getCountyManagerByCounty(int countyId,int pageNumber,int pageSize) throws Exception{
		
		try{
			Page page = countyManagerService.getCountyManagerByCounty(pageNumber,pageSize,countyId);
			JsonResult result;
			if (page.getResult() == null)
				result = new JsonResult("succ", 0, new ArrayList<ViewCountyManager>(), null);
			else{
				List<ViewCountyManager> countyManagers =(List<ViewCountyManager>)page.getResult();
				result = new JsonResult("succ",(int)page.getTotalCount(),countyManagers,null);
			}
			return result.toJsonString();
		}
		catch (Exception e) {
			JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			return result.toJsonString();
		}
		
	}
	
	@ResponseBody
	@RequestMapping(value="CountyPeople/getCountyManager")
	public String getCountyManager(int countyManagerId) throws Exception{
		try {
			ViewCountyManager countyManager = countyManagerService.getCountyManager(countyManagerId);
			System.out.println("get countyManager: "+countyManager.getUserNickname()); 
			
			JsonResult result = new JsonResult("succ", 0, null, countyManager);
			return result.toJsonString();
		}
		catch (Exception e) {
			JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			return result.toJsonString();
		}
	}
	
	 @ResponseBody
	 @RequestMapping(value="CountyPeople/selectCountyManager")
	 public String selectCountyManager(int userId, int countyId) throws Exception
	 {
		 try {
			 String id = countyManagerService.selectCountyManager(userId, countyId);
			 if ( id != null) {			
				 JsonResult result = new JsonResult("succ", 0, null, id);
				 return result.toJsonString();
			 }
			 else
				 throw new Exception("设置区县管理员失败");
		 }
		 catch (Exception e) {
			 JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			 return result.toJsonString();
		}		
	 }
	
	 @ResponseBody
	 @RequestMapping(value="CountyPeople/createCountyManager")
	 public String createCountyManager(CountyManagerForm countyManager) throws Exception
	 {
		 try {
			 String id = countyManagerService.createCountyManager(countyManager);
			 if ( id != null) {			
				 JsonResult result = new JsonResult("succ", 0, null, id);
				 return result.toJsonString();
			 }
			 else
				 throw new Exception("修改区县管理员失败");
		 }
		 catch (Exception e) {
			 JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			 return result.toJsonString();
		}		
	 }
	 
	 @ResponseBody
	 @RequestMapping(value="CountyPeople/deleteCountyManager")
	 public String deleteCountyManager(int countyManagerId) throws Exception
	 {
		 try {
			countyManagerService.deleteCountyManager(countyManagerId);
			
			JsonResult result = new JsonResult("succ", 0, null, null);
			return result.toJsonString();
		}
		catch (Exception e) {
			JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			return result.toJsonString();
		}		 
	 }
	 
	 @ResponseBody
	 @RequestMapping(value="CountyPeople/modifyCountyManager")
	 public String modifyCountyManager(CountyManagerForm countyManager) throws Exception
	 {
		 try {
			 if (countyManagerService.modifyCountyManager(countyManager)) {			
				 JsonResult result = new JsonResult("succ", 0, null, null);
				 return result.toJsonString();
			 }
			 else
				 throw new Exception("修改区县管理员失败");
		 }
		 catch (Exception e) {
			 JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			 return result.toJsonString();
		}		
	 }
	 @ResponseBody
	 @RequestMapping(value="CountyPeople/cancelCountyManager")
	 public String cancelCountyManager(int countyManagerId) throws Exception
	 {
		 try {
			 countyManagerService.cancelCountyManager(countyManagerId);
			
			JsonResult result = new JsonResult("succ", 0, null, null);
			return result.toJsonString();
		}
		catch (Exception e) {
			JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			return result.toJsonString();
		}		 
	 }

}
