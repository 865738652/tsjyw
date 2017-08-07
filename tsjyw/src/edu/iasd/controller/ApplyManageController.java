package edu.iasd.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demonstration.hibernate.dao.support.Page;

import edu.iasd.form.ApplyForm;
import edu.iasd.form.QuestionForm;
import edu.iasd.pojo.ViewApply;
import edu.iasd.pojo.ViewCounty;
import edu.iasd.security.UserInfo;
import edu.iasd.service.ApplyService;

@Controller
public class ApplyManageController extends ControllerBase {
	@Autowired
	private ApplyService applyService;
	
	@RequestMapping("ApplynManage/ShowAllApply")
	public String showAllApply(Model model)
	{		
		model.addAttribute("showType", "all");
		return "ApplyManage";
	}
		
	@RequestMapping("ApplynManage/ShowMyApply")
	public String showMyApply(Model model)
	{		
		model.addAttribute("showType", "my");
		return "ApplyManage";
	}
	
	@RequestMapping("ApplynManage/Apply")
	public String article(HttpServletRequest request, Model model)
	{
		String s = request.getParameter("action");
		if (s == null || s.length() == 0)
			model.addAttribute("action", "create");	
		else
			model.addAttribute("action", "modify");	
		
		String v = request.getParameter("applyId");
		if (v == null || v.length() == 0) {
			model.addAttribute("applyId", -1);				
		}
		else {
			model.addAttribute("applyId", Integer.parseInt(v));						
		}
		return "Apply";
	}
	
	@ResponseBody
	@RequestMapping(value="ApplynManage/getAllApply")
	public String getAllApply(HttpServletRequest request, int pageNumber,int pageSize, String showType) throws Exception {
		try{
			UserInfo curUser = super.getCurrentUser(request);
			Page page;
			if (showType.equals("my"))
				page = applyService.getMyApply(curUser.getViewUser().getUserId(), pageNumber, pageSize); // to be ..
				
			else if (showType.equals("all"))
				page = applyService.getAllApply(pageNumber, pageSize);
			else {
				page = new Page();
				page.setResult(null);
			}
			
			JsonResult result;
			if (page.getResult() == null)
				result = new JsonResult("succ", 0, new ArrayList<ViewApply>(), null);
			else {
				List<ViewApply> questions = (List<ViewApply>)page.getResult();
				result = new JsonResult("succ", (int)page.getTotalCount(), questions, null);
			}
			return result.toJsonString();
		}
		catch (Exception e) {
			JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			return result.toJsonString();
		}
	}
	
	@ResponseBody
	@RequestMapping(value="ApplynManage/getApply")
	public String getApply(int applyId) throws Exception {
		try {
			ViewApply apply = applyService.getApply(applyId);
			
			JsonResult result = new JsonResult("succ", 0, null, apply);
			return result.toJsonString();
		}
		catch (Exception e) {
			JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			return result.toJsonString();
		}
	 }   

	
	 @ResponseBody
	 @RequestMapping(value="ApplynManage/createApply")
	 public String createApply(HttpServletRequest request, ApplyForm form) throws Exception
	 {
		 try {
			 UserInfo curUser = super.getCurrentUser(request);
			 form.setUserId(curUser.getViewUser().getUserId()); // to be ...
			 form.setApplyTime(new Timestamp(System.currentTimeMillis()));
			 String id = applyService.createApply(form);
			 if ( id != null) {			
				 JsonResult result = new JsonResult("succ", 0, null, id);
				 return result.toJsonString();
			 }
			 else
				 throw new Exception("����ʧ��");
		 }
		 catch (Exception e) {
			 JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			 return result.toJsonString();
		}		
	 }
	 
	 @ResponseBody
	 @RequestMapping(value="ApplynManage/modifyApply")
	 public String modifyApply(HttpServletRequest request, ApplyForm form) throws Exception
	 {
		 try {
			 UserInfo curUser = super.getCurrentUser(request);
			 form.setUserId(curUser.getViewUser().getUserId()); // to be ...
			 
			 if (applyService.modifyApply(form)) {			
				 JsonResult result = new JsonResult("succ", 0, null, null);
				 return result.toJsonString();
			 }
			 else
				 throw new Exception("�޸�����ʧ��");
		 }
		 catch (Exception e) {
			 JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			 return result.toJsonString();
		}		
	 }
	
	 @ResponseBody
	 @RequestMapping(value="ApplynManage/deleteApply")
	 public String deleteApply(int applyId) throws Exception
	 {
		 try {
			 applyService.deleteApply(applyId);
			
			 JsonResult result = new JsonResult("succ", 0, null, null);
			 return result.toJsonString();
		}
		catch (Exception e) {
			JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			return result.toJsonString();
		}		 
	 }
	 /*
	 @ResponseBody
	 @RequestMapping(value="ApplynManage/auditApply")
	 public String auditApply(HttpServletRequest request,Model model) throws Exception
	 {
		 try {
			 String apply = request.getParameter("apply");
			 JSONObject jsonObject= JSONObject.fromObject(apply);
			 if (jsonObject.getString("action").equals("pass"))
				 applyService.passApply(jsonObject.getInt("applyId"));
			 else
				 applyService.blockApply(jsonObject.getInt("applyId"),JSONObject.fromObject(apply));			
			 JsonResult result = new JsonResult("succ", 0, null, null);
			 return result.toJsonString();
		}
		catch (Exception e) {
			JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			return result.toJsonString();
		}		 
	 }	
	 */
	 @ResponseBody
	 @RequestMapping(value="ApplynManage/auditApply")
	 public String auditApply(HttpServletRequest request,int applyId, String action, String applyOpinion, Model model) throws Exception
	 {
		 try {
			 if (action.equals("pass"))
				 applyService.passApply(applyId);
			 else
				 applyService.blockApply(applyId,applyOpinion);
			
			 JsonResult result = new JsonResult("succ", 0, null, null);
			 return result.toJsonString();
		}
		catch (Exception e) {
			JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			return result.toJsonString();
		}		 
	 }	
}
