package edu.iasd.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demonstration.hibernate.dao.support.Page;

import edu.iasd.utils.HttpRequestDeviceUtils;
import edu.iasd.form.AgeLevelVO;
import edu.iasd.form.ArticleForm;
import edu.iasd.form.AskQuestionTypeVO;
import edu.iasd.form.AttachVO;
import edu.iasd.form.QuestionForm;
import edu.iasd.form.ResponseForm;
import edu.iasd.pojo.AgeLevel;
import edu.iasd.pojo.ArticleState;
import edu.iasd.pojo.AskQuestionState;
import edu.iasd.pojo.AskQuestionType;
import edu.iasd.pojo.Role;
import edu.iasd.pojo.ViewArticle;
import edu.iasd.pojo.ViewAskQuestion;
import edu.iasd.pojo.ViewUser;
import edu.iasd.search.QueryModel;
import edu.iasd.search.SearchParam;
import edu.iasd.security.UserInfo;
import edu.iasd.service.ArticleService;
import edu.iasd.service.AskQuestionService;
import edu.iasd.service.UserService;
import edu.iasd.utils.UserHelper;

@Controller
public class AskQuestionController extends ControllerBase {
	@Autowired
	private AskQuestionService askQuestionService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("AskQuestionManage/ShowAllQuestion")
	public String showAllQuestion(HttpServletRequest request, Model model)
	{	
		try {
			model.addAttribute("questionType", "all");
			UserInfo curUser = super.getCurrentUser(request);
			Integer userId = curUser.getViewUser().getUserId();
			model.addAttribute("isAdmin", userService.hasRole(userId, Role.ROLE_SUPERADMIN));
			return "AskQuestionManage";
		}
		catch (Exception e) {
			return "403";
		}
	}
		
	@RequestMapping("AskQuestionManage/ShowMyQuestion")
	public String showMyQuestion(Model model)
	{		
		model.addAttribute("questionType", "my");
		return "AskQuestionManage";
	}
	
	@RequestMapping("AskQuestionManage/ShowToMeQuestion")
	public String showToMeQuestion(Model model)
	{		
		model.addAttribute("questionType", "tome");
		return "AskQuestionManage";
	}
	
	@RequestMapping("AskQuestionManage/ShowMyRespQuestion")
	public String showMyRespQuestion(Model model)
	{		
		model.addAttribute("questionType", "myresp");
		return "AskQuestionManage";
	}
	
	@RequestMapping("AskQuestionManage/ShowQuestion")
	public String showQuestion(int askQuestionId,Model model)
	{
		model.addAttribute("askQuestionId", askQuestionId);
		return "ShowQuestion";
	}
	
	@RequestMapping("AskQuestionManage/AskQuestion")
	public String askQuestion(HttpServletRequest request, Model model)
	{
		String v = request.getParameter("askQuestionId");
		if (v == null || v.length() == 0) {
			model.addAttribute("askQuestionId", -1);			
		}
		else {
			model.addAttribute("askQuestionId", Integer.parseInt(v));
		}
		
		v = request.getParameter("replyId");
		if (v != null && v.length() > 0) {
			ViewUser u = userService.getUser(Integer.parseInt(v));
			model.addAttribute("replier", u);			
		}
		
		return "AskQuestion";
	}
	
	@ResponseBody
	@RequestMapping(value="AskQuestionManage/getAllAskQuestion")
	public String getAllAskQuestion(HttpServletRequest request, int pageNumber,int pageSize, String questionType, @SearchParam QueryModel queryModel) throws Exception {
		try{
			Page page;
			UserInfo curUser = super.getCurrentUser(request);
			Integer userId = curUser.getViewUser().getUserId();
			
			if (questionType.equals("my"))
				page = askQuestionService.getMyAskQuestion(userId, queryModel.toQueryString(), pageNumber, pageSize);
			else if (questionType.equals("tome"))
				page = askQuestionService.getToMeQuestion(userId, queryModel.toQueryString(), pageNumber, pageSize);
			else if (questionType.equals("myresp"))
				page = askQuestionService.getMyAnswerQuestion(userId, queryModel.toQueryString(), pageNumber, pageSize);
			else
				page = askQuestionService.getAllQuestion(queryModel.toQueryString(), pageNumber, pageSize);
			JsonResult result;
			if (page.getResult() == null)
				result = new JsonResult("succ", 0, new ArrayList<ViewAskQuestion>(), null);
			else {
				List<ViewAskQuestion> questions = (List<ViewAskQuestion>)page.getResult();
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
	@RequestMapping(value={"AskQuestionManage/getHotAskQuestion", "Index/getHotAskQuestion"})
	public String getAllAskQuestion(int pageNumber,int pageSize) throws Exception {
		try{
			Page page = askQuestionService.getHotQuestion(pageNumber, pageSize);
			JsonResult result;
			if (page.getResult() == null)
				result = new JsonResult("succ", 0, new ArrayList<ViewAskQuestion>(), null);
			else {
				List<ViewAskQuestion> questions = (List<ViewAskQuestion>)page.getResult();
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
	@RequestMapping(value={"AskQuestionManage/getAskQuestion","Index/getAskQuestion"})
	public String getAskQuestion(int askQuestionId) throws Exception {
		try {
			ViewAskQuestion question = askQuestionService.getQuestion(askQuestionId);	
			JsonResult result = new JsonResult("succ", 0, null, question);
			return result.toJsonString();
		}
		catch (Exception e) {
			JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			return result.toJsonString();
		}
	 } 
	
	 @ResponseBody
	 @RequestMapping(value="AskQuestionManage/createAskQuestion")
	 public String createAskQuestion(HttpServletRequest request, QuestionForm form) throws Exception
	 {
		 try {
			 UserInfo curUser = super.getCurrentUser(request);
			 form.setAskUserId(curUser.getViewUser().getUserId()); 
			 form.setAskQuestionTime(new Timestamp(System.currentTimeMillis()));
			 String id = askQuestionService.createAskQuestion(form);
			 if ( id != null) {			
				 JsonResult result = new JsonResult("succ", 0, null, id);
				 return result.toJsonString();
			 }
			 else
				 throw new Exception("提问失败");
		 }
		 catch (Exception e) {
			 JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			 return result.toJsonString();
		}		
	 }
	 
	 @ResponseBody
	 @RequestMapping(value="AskQuestionManage/createRespQuestion")
	 public String createRespQuestion(HttpServletRequest request, ResponseForm form) throws Exception
	 {
		 try {
			 UserInfo curUser = super.getCurrentUser(request);
			 form.setUserId(curUser.getViewUser().getUserId()); 
			 String id = askQuestionService.createResponse(form);
			 if ( id != null) {			
				 JsonResult result = new JsonResult("succ", 0, null, id);
				 return result.toJsonString();
			 }
			 else
				 throw new Exception("回答失败");
		 }
		 catch (Exception e) {
			 JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			 return result.toJsonString();
		}		
	 }
	
	 @ResponseBody
	 @RequestMapping(value="AskQuestionManage/modifyAskQuestion")
	 public String modifyAskQuestion(HttpServletRequest request, QuestionForm form) throws Exception
	 {
		 try {
			 UserInfo curUser = super.getCurrentUser(request);
			 form.setAskUserId(curUser.getViewUser().getUserId()); 
			 
			 if (askQuestionService.modifyAskQuestion(form)) {			
				 JsonResult result = new JsonResult("succ", 0, null, null);
				 return result.toJsonString();
			 }
			 else
				 throw new Exception("修改提问失败");
		 }
		 catch (Exception e) {
			 JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			 return result.toJsonString();
		}		
	 }
	
	 @ResponseBody
	 @RequestMapping(value="AskQuestionManage/deleteAskQuestion")
	 public String deleteAskQuestion(int askQuestionId) throws Exception
	 {
		 try {
			 askQuestionService.deleteAskQuestion(askQuestionId);
			
			 JsonResult result = new JsonResult("succ", 0, null, null);
			 return result.toJsonString();
		}
		catch (Exception e) {
			JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			return result.toJsonString();
		}		 
	 }
	
	@ResponseBody
	@RequestMapping(value="AskQuestionManage/getAskQuestionState")
	public String getAskQuestionState() throws Exception {
		try {
			List<AskQuestionState> states = askQuestionService.getAskQuestionState();
			JsonResult result = new JsonResult("succ", states.size(), states, null);
			return result.toJsonString();
		}
		catch (Exception e) {
			JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			return result.toJsonString();
		}
	}
	
	@ResponseBody
	@RequestMapping(value={"AskQuestionManage/getAskQuestionType","Index/getAskQuestionType"})
	public String getAskQuestionType(HttpServletRequest request) throws Exception {
		try {
			List<AskQuestionType> types = askQuestionService.getAskQuestionType();
			List<AskQuestionTypeVO> list = new ArrayList<AskQuestionTypeVO>();
			int flag = 0;
			if (request.getParameter("flag") != null) {
				flag = Integer.parseInt(request.getParameter("flag"));
			}
			for (AskQuestionType l : types) {
				if (flag > 0 && l.getAskQuestionTypeFlag() != flag)
					continue;
				AskQuestionTypeVO v = new AskQuestionTypeVO();
				v.setAskQuestionTypeId(l.getAskQuestionTypeId());
				v.setAskQuestionTypeName(l.getAskQuestionTypeName());
				v.setAspQuestionTypePublic(l.getAspQuestionTypePublic());
				list.add(v);
			}
			JsonResult result = new JsonResult("succ", list.size(), list, null);
			return result.toJsonString();
		}
		catch (Exception e) {
			JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			return result.toJsonString();
		}
	}
	
	@ResponseBody
	@RequestMapping(value={"AskQuestionManage/getAgeLevel","Index/getAgeLevel"})
	public String getAgeLevel() throws Exception {
		try {
			List<AgeLevel> levels = askQuestionService.getAgeLevel();
			List<AgeLevelVO> list = new ArrayList<AgeLevelVO>();
			for (AgeLevel l : levels) {
				AgeLevelVO v = new AgeLevelVO();
				v.setAgeLevelId(l.getAgeLevelId());
				v.setAgeName(l.getAgeName());
				list.add(v);
			}
			JsonResult result = new JsonResult("succ", list.size(), list, null);
			return result.toJsonString();
		}
		catch (Exception e) {
			JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			return result.toJsonString();
		}
	}
	
	@ResponseBody
	@RequestMapping("AskQuestionManage/beSelectAskQuestion")
	public String beSelectAskQuestion(@RequestParam(value="askquestionIds", required = false) String questionIds) throws Exception
	{
		JsonResult jsonResult;
		try
		{
			System.out.println(questionIds);
			
			JSONArray as = JSONArray.fromObject(questionIds);
			Set<Integer> ab = new HashSet<Integer>();
			for(int i=0;i<as.size();i++)
			{
				ab.add(Integer.parseInt(as.getString(i)));
			}
			if(askQuestionService.setSelectAskQuestion(ab))
			{
				jsonResult = new JsonResult("succ",0,null,null);
				return jsonResult.toJsonString();
			}
			else
			{
				jsonResult = new JsonResult("fail",0,null,null);
				return jsonResult.toJsonString();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			jsonResult = new JsonResult("fail",0,null,e.getMessage());
			return jsonResult.toJsonString();
		}
	}
	
}
