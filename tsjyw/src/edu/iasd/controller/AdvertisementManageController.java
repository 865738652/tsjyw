package edu.iasd.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demonstration.hibernate.dao.support.Page;

import edu.iasd.form.AdvertisementForm;
import edu.iasd.form.AttachVO;
import edu.iasd.pojo.ViewAdvertisement;
import edu.iasd.service.AdvertisementService;

@Controller
public class AdvertisementManageController extends ControllerBase{
	
	@Autowired
	private AdvertisementService advertisementService;
	
	@RequestMapping("AdvertisementManage")
	public String showAllAdvertisement(Model model)
	{		
		return "AdvertisementManage";
	}
	
	@ResponseBody
	@RequestMapping(value={"AdvertisementManage/getAdvertisement", "Index/getAdvertisement"})
	public String getAdvertisement(int advertisementId, Model model ) throws Exception {
		try {
			ViewAdvertisement advertisement = advertisementService.getAdvertisement(advertisementId);
			model.addAttribute("advertisementurl",advertisement.getAttachmentUrl());
			
			JsonResult result = new JsonResult("succ", 0, null, advertisement);
			return result.toJsonString();
		}
		catch (Exception e) {
			JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			return result.toJsonString();
		}
	 } 
	
	@ResponseBody
	@RequestMapping(value={"AdvertisementManage/getAllAdvertisement", "Index/getAllAdvertisement"})
	public String getAllAdvertisement(HttpServletRequest request,int pageNumber, int pageSize) throws Exception {
		try{
			Page page=advertisementService.getAllAdvertisement(pageNumber, pageSize);
			JsonResult result;
			if (page.getResult() == null)
				result = new JsonResult("succ", 0, new ArrayList<ViewAdvertisement>(), null);
			else{
				List<ViewAdvertisement> advertisements = (List<ViewAdvertisement>)page.getResult();
				result = new JsonResult("succ",(int)page.getTotalCount(),advertisements,null);
			}
			return result.toJsonString();
		}
		catch (Exception e) {
			JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			return result.toJsonString();
		}
	}
	
	@ResponseBody
	 @RequestMapping(value="AdvertisementManage/modifyAdvertisement")
	 public String modifyAdvertisement(HttpServletRequest request,AdvertisementForm advertisementForm) throws Exception
	 {
		 try {
			 //System.out.println(advertisementForm);
			 if (advertisementService.modifyAdvertisement(advertisementForm)) {	
				
				 JsonResult result = new JsonResult("succ", 0, null, null);
				 return result.toJsonString();
			 }
			 else
				 throw new Exception("修改广告失败");
		 }
		 catch (Exception e) {
			 JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			 return result.toJsonString();
		}		
	 }

	@RequestMapping("AdvertisementManage/TestUpload")
	public String testUpload(Model model)
	{		
		return "TestUpload";
	}
	
	/*
	@ResponseBody
	@RequestMapping(value="AdvertisementManage/getImageByAdvertisement")
	public String getImageByAdvertisement(int advertisementId) throws Exception {
		try {
			List<AttachVO> list = advertisementService.getImageByAdvertisement(advertisementId);
			JsonResult result = new JsonResult("succ", list.size(), list, null);
			return result.toJsonString();
		}
		catch (Exception e) {
			JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			return result.toJsonString();
		}
	}*/
	
	/*
	 @ResponseBody
	 @RequestMapping(value="AdvertisementManage/createAdvertisement")
	 public String createAdvertisement(HttpServletRequest request, AdvertisementForm advertisement) throws Exception
	 {
		 try {
			 UserInfo curUser = super.getCurrentUser(request);				
			 advertisement.setUserId(curUser.getViewUser().getUserId()); // to be ...
			 
			 String id = advertisementService.creatAdvertisement(advertisement);
			 if ( id != null) {			
				 JsonResult result = new JsonResult("succ", 0, null, id);
				 return result.toJsonString();
			 }
			 else
				 throw new Exception("新建广告失败");
		 }
		 catch (Exception e) {
			 JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			 return result.toJsonString();
		}		
	 }
	 
	 @ResponseBody
	 @RequestMapping(value="AdvertisementManage/deleteAdvertisement")
	 public String deleteAdvertisement(int advertisementId) throws Exception
	 {
		 try {
			 advertisementService.deleteAdvertisement(advertisementId);			
			 JsonResult result = new JsonResult("succ", 0, null, null);
			 return result.toJsonString();
		}
		catch (Exception e) {
			JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			return result.toJsonString();
		}		 
	 }
	*/
	

}
