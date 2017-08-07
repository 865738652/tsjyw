package edu.iasd.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;



import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demonstration.hibernate.dao.support.Page;

import edu.iasd.form.ArticleForm;
import edu.iasd.form.AttachVO;
import edu.iasd.form.CommentForm;
import edu.iasd.pojo.ArticleState;
import edu.iasd.pojo.CommentType;
import edu.iasd.pojo.Image;
import edu.iasd.pojo.NetCourseType;
import edu.iasd.pojo.Video;
import edu.iasd.pojo.ViewAdvertisement;
import edu.iasd.pojo.ViewArticle;
import edu.iasd.pojo.ViewComment;
import edu.iasd.pojo.ViewModule;
import edu.iasd.pojo.ViewNetCourse;
import edu.iasd.pojo.ViewSchool;
import edu.iasd.pojo.ViewTeacher;
import edu.iasd.search.QueryModel;
import edu.iasd.search.SearchParam;
import edu.iasd.security.NotLoginException;
import edu.iasd.security.UserInfo;
import edu.iasd.service.AdvertisementService;
import edu.iasd.service.ArticleService;
import edu.iasd.service.SchoolService;


@Controller 
public class ArticleManageController extends ControllerBase {
	
	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private SchoolService schoolService;
	
	@Autowired
	private AdvertisementService advertisementService;
	
	@RequestMapping("ArticleManage")
	public String showAllArticle(Model model)
	{	
		List<ViewAdvertisement> advertisements=null;
		model.addAttribute("school", false);
		model.addAttribute("schoolid", -1);
		advertisements=advertisementService.getAllAdvertisement();
		model.addAttribute("advertisement", advertisements);
		return "ArticleManage";
	}
	
	@RequestMapping("ArticleManage/School")
	public String showArticleBySchool(HttpServletRequest request, Model model)	{
		
		try {
			UserInfo curUser = super.getCurrentUser(request);
			Page page = schoolService.getAllSchool(curUser.getViewUser().getUserId(), "", 1, 10);
			if (page.getTotalCount() == 1) {
				List<ViewSchool> schools = (List<ViewSchool>)page.getResult();
				model.addAttribute("school", true);
				model.addAttribute("schoolid",schools.get(0).getSchoolId());
			}
			else if (page.getTotalCount() == 0)
				throw new Exception("û�й���ѧУȨ��");
			else {
				model.addAttribute("school", true);
				model.addAttribute("schoolid",-1);
			}
			return "ArticleManage";
		}
		catch (Exception e) {			
			return "403";
		}
	}	

	@RequestMapping("ArticleManage/Blog")
	public String showBlog(Model model)
	{	
		return "BlogManage";
	}
		
	/*
	@RequestMapping("ArticleManage/{schoolid}")
	public String showArticleBySchool(@PathVariable int schoolid,Model model)
	{
		model.addAttribute("schoolid", schoolid);
		return "ArticleManage";
	}
	*/
	
	@RequestMapping("ArticleManage/Article")
	public String article(HttpServletRequest request, Model model)
	{
		String v = request.getParameter("articleid");
		if (v == null || v.length() == 0) {
			model.addAttribute("articleid", -1);			
		}
		else {
			model.addAttribute("articleid", Integer.parseInt(v));
		}
		
		String s = request.getParameter("schoolid");
		if (s == null || s.length() == 0)
			model.addAttribute("schoolid", -1);
		else
			model.addAttribute("schoolid", Integer.parseInt(s));

		String b = request.getParameter("blog");
		if (b == null || b.length() == 0)
			model.addAttribute("blog", 0);
		else
			model.addAttribute("blog", Integer.parseInt(b));

		return "Article";
	}
	
	@ResponseBody
	@RequestMapping(value={"ArticleManage/getAllArticle", "Index/getAllArticle"})
	public String getAllArticle(int pageNumber,int pageSize, Boolean recommend, @SearchParam QueryModel queryModel) throws Exception {
		try{
			Page page=articleService.getAllArticle(pageNumber, pageSize, recommend, queryModel.toQueryString());
			JsonResult result;
			if (page.getResult() == null)
				result = new JsonResult("succ", 0, new ArrayList<ViewArticle>(), null);
			else {
				List<ViewArticle> schools = (List<ViewArticle>)page.getResult();
				result = new JsonResult("succ",(int)page.getTotalCount(), schools, null);
			}
			return result.toJsonString();
		}
		catch (Exception e) {
			JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			return result.toJsonString();
		}
	}
	
	@ResponseBody
	@RequestMapping(value={"ArticleManage/getArticleByModule", "Index/getArticleByModule"})
	public String getArticleByModule(int moduleId,int pageNumber,int pageSize, Boolean recommend) throws Exception {
		try{
			Page page=articleService.getArticleByModule(pageNumber, pageSize, moduleId, recommend);
			JsonResult result;
			if (page.getResult() == null)
				result = new JsonResult("succ", 0, new ArrayList<ViewArticle>(), null);
			else {
				List<ViewArticle> schools = (List<ViewArticle>)page.getResult();
				result = new JsonResult("succ", (int)page.getTotalCount(), schools, null);
			}
			return result.toJsonString();
		}
		catch (Exception e) {
			JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			return result.toJsonString();
		}
	}
	
	@ResponseBody
	@RequestMapping(value="ArticleManage/getArticleBySchool")
	public String getArticleBySchool(int schoolId,int pageNumber,int pageSize, Boolean recommend, @SearchParam QueryModel queryModel) throws Exception {
		try{
			/* Here need new function to get articles of school */
			Page page=articleService.getAllArticleBySchool(pageNumber, pageSize, schoolId, recommend, queryModel.toQueryString());
			JsonResult result;
			if (page.getResult() == null)
				result = new JsonResult("succ", 0, new ArrayList<ViewArticle>(), null);
			else {
				List<ViewArticle> schools = (List<ViewArticle>)page.getResult();
				result = new JsonResult("succ", (int)page.getTotalCount(), schools, null);
			}
			return result.toJsonString();
		}
		catch (Exception e) {
			JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			return result.toJsonString();
		}
	}

	@ResponseBody
	@RequestMapping(value="ArticleManage/getBlogArticle")
	public String getBlogArticle(int pageNumber,int pageSize, Boolean recommend, @SearchParam QueryModel queryModel,HttpServletRequest request) throws Exception {
		try{
			/* Here need new function to get articles of school */
			UserInfo curUser = super.getCurrentUser(request);
			Page page=articleService.getBlogArticle(pageNumber, pageSize, curUser.getViewUser().getUserId(), recommend, queryModel.toQueryString());
			JsonResult result;
			if (page.getResult() == null)
				result = new JsonResult("succ", 0, new ArrayList<ViewArticle>(), null);
			else {
				List<ViewArticle> schools = (List<ViewArticle>)page.getResult();
				result = new JsonResult("succ", (int)page.getTotalCount(), schools, null);
			}
			return result.toJsonString();
		}
		catch (Exception e) {
			JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			return result.toJsonString();
		}
	}
	
	@ResponseBody
	@RequestMapping(value="ArticleManage/getArticle")
	public String getArticle(int articleId) throws Exception {
		try {
			ViewArticle article = articleService.getArticle(articleId);	
			JsonResult result = new JsonResult("succ", 0, null, article);
			return result.toJsonString();
		}
		catch (Exception e) {
			JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			return result.toJsonString();
		}
	 } 
	
	@ResponseBody
	 @RequestMapping(value="ArticleManage/createArticle")
	 public String createArticle(HttpServletRequest request, ArticleForm article) throws Exception
	 {
		 try {
			 UserInfo curUser = super.getCurrentUser(request);				
			 article.setUserId(curUser.getViewUser().getUserId()); // to be ...
			 
			 String id = articleService.createArticle(article);
			 if ( id != null) {			
				 JsonResult result = new JsonResult("succ", 0, null, id);
				 return result.toJsonString();
			 }
			 else
				 throw new Exception("�½�ѧУʧ��");
		 }
		 catch (Exception e) {
			 JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			 return result.toJsonString();
		}		
	 }
	
	 @ResponseBody
	 @RequestMapping(value="ArticleManage/modifyArticle")
	 public String modifyArticle(HttpServletRequest request, ArticleForm article) throws Exception
	 {
		 try {
			 UserInfo curUser = super.getCurrentUser(request);
			 article.setUserId(curUser.getViewUser().getUserId()); 
			 
			 if (articleService.modifyArticle(article)) {			
				 JsonResult result = new JsonResult("succ", 0, null, null);
				 return result.toJsonString();
			 }
			 else
				 throw new Exception("�޸�ѧУʧ��");
		 }
		 catch (Exception e) {
			 JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			 return result.toJsonString();
		}		
	 }
	
	@ResponseBody
	 @RequestMapping(value="ArticleManage/deleteArticle")
	 public String deleteArticle(int articleId) throws Exception
	 {
		 try {
			 articleService.deleteArticle(articleId);
			
			 JsonResult result = new JsonResult("succ", 0, null, null);
			 return result.toJsonString();
		}
		catch (Exception e) {
			JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			return result.toJsonString();
		}		 
	 }
	
	@ResponseBody
	@RequestMapping(value="ArticleManage/getArticleState")
	public String getArticleState() throws Exception {
		try {
			List<ArticleState> states = articleService.getArticleState();
			JsonResult result = new JsonResult("succ", states.size(), states, null);
			return result.toJsonString();
		}
		catch (Exception e) {
			JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			return result.toJsonString();
		}
	}
	
	@ResponseBody
	@RequestMapping(value="ArticleManage/getImageByArticle")
	public String getImageByArticle(int articleId) throws Exception {
		try {
			List<AttachVO> list = articleService.getImageByArticle(articleId);
			JsonResult result = new JsonResult("succ", list.size(), list, null);
			return result.toJsonString();
		}
		catch (Exception e) {
			JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			return result.toJsonString();
		}
	}
	
	@ResponseBody
	@RequestMapping(value="ArticleManage/getVideoByArticle")
	public String getVideoByArticle(int articleId) throws Exception {
		try {
			List<AttachVO> list = articleService.getVideoByArticle(articleId);
			JsonResult result = new JsonResult("succ", list.size(), list, null);
			return result.toJsonString();
		}
		catch (Exception e) {
			JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			return result.toJsonString();
		}
	}
	
	@RequestMapping("ArticleManage/TestUpload")
	public String testUpload(Model model)
	{		
		return "TestUpload";
	}
	
	@ResponseBody
	@RequestMapping(value={"ArticleManage/getAllCommentByArticle", "Index/getAllCommentByArticle"})
	public String getAllCommentByArticle(int pageNumber, int pageSize, int articleId, int commentTypeId) throws Exception {
		try {
			
			Page page = articleService.getAllCommentByArticle(pageNumber, pageSize,articleId, commentTypeId);
			JsonResult result;
			if (page.getResult() == null)
				result = new JsonResult("succ", 0, new ArrayList<ViewComment>(), null);
			else {
				List<ViewComment> comment = (List<ViewComment>)page.getResult();
				result = new JsonResult("succ", (int)page.getTotalCount(), comment, null);
			}
			return result.toJsonString();
		}
		catch (Exception e) {
			JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			return result.toJsonString();
		}
	}
	
	
	@ResponseBody
	@RequestMapping(value={"ArticleManage/getComment", "Index/getComment"})
	public String getComment(int commentId) throws Exception {
		try {
			ViewComment comment = articleService.getComment(commentId);	
			JsonResult result = new JsonResult("succ", 0, null, comment);
			return result.toJsonString();
		}
		catch (Exception e) {
			JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			return result.toJsonString();
		}
	 } 
	
	
	
	 @ResponseBody
	 @RequestMapping(value="ArticleManage/deleteComment")
	 public String deleteComment(int commentId) throws Exception
	 {
		 try {
			 articleService.deleteComment(commentId);
			
			 JsonResult result = new JsonResult("succ", 0, null, null);
			 return result.toJsonString();
		}
		catch (Exception e) {
			JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			return result.toJsonString();
		}		 
	 }
	
	 @ResponseBody
	 @RequestMapping(value={"ArticleManage/createComment", "Index/createComment"})
	 public String createComment(HttpServletRequest request, CommentForm commentForm) throws Exception
	 {
		 
		 try {
			 //System.out.println("qqqqqqqq");
			 UserInfo curUser = null;
			 try {
				 curUser = super.getCurrentUser(request);
			 }
			 catch (NotLoginException ex) {				 
			 }
			 
			 if (curUser != null)
				 commentForm.setUserId(curUser.getViewUser().getUserId());
			 commentForm.setCommentTime(new Timestamp(System.currentTimeMillis()));
			 // to be ...
			 
			 String id = articleService.createComment(commentForm);
			 if ( id != null) {			
				 JsonResult result = new JsonResult("succ", 0, null, id);
				 return result.toJsonString();
			 }
			 else
				 throw new Exception("");
		 }
		 catch (Exception e) {
			 JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			 return result.toJsonString();
		}		
	 }
	 
	
	    @ResponseBody
		@RequestMapping(value="ArticleManage/getCommentType")
			public String getCommentType() throws Exception {
				try {
					List<CommentType> types = articleService.getCommentType();
					JsonResult result = new JsonResult("succ", types.size(), types, null);
					return result.toJsonString();
				}
				catch (Exception e) {
					JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
					return result.toJsonString();
				}
			}
	 
}
