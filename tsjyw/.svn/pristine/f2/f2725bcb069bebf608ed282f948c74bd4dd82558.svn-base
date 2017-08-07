package edu.iasd.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.io.FileUtils;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demonstration.hibernate.dao.support.Page;

import edu.iasd.form.AgeLevelVO;
import edu.iasd.form.AskQuestionTypeVO;
import edu.iasd.form.AttachVO;
import edu.iasd.form.QuestionForm;
import edu.iasd.form.ReplyForm;
import edu.iasd.form.ResponseForm;
import edu.iasd.form.UserForm;
import edu.iasd.pojo.AgeLevel;
import edu.iasd.pojo.AskQuestionState;
import edu.iasd.pojo.AskQuestionType;
import edu.iasd.pojo.Attachment;
import edu.iasd.pojo.FamousTeacher;
import edu.iasd.pojo.Module;
import edu.iasd.pojo.ModuleType;
import edu.iasd.pojo.Notice;
import edu.iasd.pojo.NoticeAcceptType;
import edu.iasd.pojo.Reply;
import edu.iasd.pojo.Role;
import edu.iasd.pojo.User;
import edu.iasd.pojo.ViewAdvertisement;
import edu.iasd.pojo.ViewArticle;
import edu.iasd.pojo.ViewAskQuestion;
import edu.iasd.pojo.ViewFamousTeacher;
import edu.iasd.pojo.ViewModule;
import edu.iasd.pojo.ViewNoticeAccept;
import edu.iasd.pojo.ViewRespQuestion;
import edu.iasd.pojo.ViewSchool;
import edu.iasd.pojo.ViewUser;
import edu.iasd.pojo.ViewVolunteer;
import edu.iasd.search.QueryModel;
import edu.iasd.search.SearchParam;
import edu.iasd.service.AnnouncementService;
import edu.iasd.service.ArticleService;
import edu.iasd.service.AskQuestionService;
import edu.iasd.service.FamousTeacherService;
import edu.iasd.service.ModuleService;
import edu.iasd.service.SchoolService;
import edu.iasd.service.SendNoticeService;
import edu.iasd.service.UserService;
import edu.iasd.service.VolunteerService;
import edu.iasd.service.WechatService;
import edu.iasd.utils.Constants;
import edu.iasd.utils.HttpRequestDeviceUtils;
import edu.iasd.utils.RecvNoticeObject;
import edu.iasd.wechat.utils.InterfaceUtil;


@Controller
public class WeChatController{
	@Autowired
	private WechatService weChatService;
	
	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private ModuleService moduleService;
	
	@Autowired
	private SendNoticeService sendNoticeService;
	
	@Autowired
	private UserService userService;
		
	@Autowired
	private SchoolService schoolService;
	
	@Autowired
	private FamousTeacherService famousTeacherService;
	
	@Autowired
	private AskQuestionService askQuestionService;
	
	@Autowired
	private VolunteerService volunteerService;
	
	@Autowired
	private AnnouncementService announcementService;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) throws Exception 
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    dateFormat.setLenient(false);
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));//true:锟斤拷锟斤拷锟斤拷锟斤拷锟街碉拷锟絝alse:锟斤拷锟斤拷为锟斤拷值
	}
	
	@RequestMapping("WeChat/Login")
	public String WeChatLogin(HttpServletRequest request,HttpServletResponse response,Model model)
	{
		try
		{
			if(request.getSession().getAttribute("userId") != null)
				return "redirect:WeChatNewArticle";
			return "phone/WeChatLogin";
		}
		catch(Exception e)
		{
			model.addAttribute("exception", e.getMessage());
			return "phone/WeChat403";
		}

	}
	@RequestMapping("WeChat/WeChatTreaty")
	public String WeChatTreaty(HttpServletRequest request,HttpServletResponse response,Model model)
	{
		return "phone/WeChatTreaty";
	}
	
	//注销
	@ResponseBody
	@RequestMapping("WeChat/CancelLogin")
	public String CancelLogin(HttpServletRequest request) throws Exception
	{
		JsonResult jsonResult;
		try{
			System.out.println("注销开始");
			HttpSession session = request.getSession();
			session.setAttribute("userId", null);
			session.setAttribute("nowOpenId", null);
			jsonResult = new JsonResult("succ",0,null,null);
			return jsonResult.toJsonString();
		}
		catch(Exception e)
		{
			jsonResult = new JsonResult("fail",0,null,null);
			return jsonResult.toJsonString();
		}

	}
	
	@ResponseBody
	@RequestMapping("WeChat/LoginCheck")
	public String logincheck(HttpServletRequest request,Model model,HttpServletResponse response) throws Exception
	{	
		JsonResult jsonResult;
		try
		{
			String userAccount = request.getParameter("username");
			String passWord = request.getParameter("password");
			String nowOpenId = (String) request.getSession().getAttribute("nowOpenId");
			//database saved you openid,maybe null,
			User u = weChatService.loginCheck(userAccount, passWord);
			if(u == null)
			{
				jsonResult = new JsonResult("fail",0,null,null);
				return jsonResult.toJsonString();
			}
			request.getSession().setAttribute("userId",u.getUserId());
			request.getSession().setAttribute("userName",u.getUserName());
			if(nowOpenId == null)
			{
				if(request.getSession().getAttribute("callbackUrl") != null)
				{
					String callbackUrl = request.getSession().getAttribute("callbackUrl").toString();
					request.getSession().setAttribute("callbackUrl", null);
					jsonResult = new JsonResult("succ",0,null,callbackUrl);
					return jsonResult.toJsonString();
				}
				jsonResult = new JsonResult("succ",0,null,"WeChatNewArticle");
				return jsonResult.toJsonString();
			}
			else
			{
				if(nowOpenId.equals(u.getUserOpenId()))
				{
					if(request.getSession().getAttribute("callbackUrl") != null)
					{
						String callbackUrl = request.getSession().getAttribute("callbackUrl").toString();
						request.getSession().setAttribute("callbackUrl", null);
						jsonResult = new JsonResult("succ",0,null,callbackUrl);
						return jsonResult.toJsonString();
					}
					jsonResult = new JsonResult("succ",0,null,"WeChatNewArticle");
					return jsonResult.toJsonString();
				}
				else if(u.getUserOpenId() == null)
				{
					jsonResult = new JsonResult("succ",0,null,"Bind");
					return jsonResult.toJsonString();
				}
				else
				{
					jsonResult = new JsonResult("succ",0,null,"WeChatIsChangeBind");
					return jsonResult.toJsonString();
				}
			}
		}
		catch(Exception e)
		{
			jsonResult = new JsonResult("fail",0,null,null);
			return jsonResult.toJsonString();
		}
		
	}
	
	@RequestMapping("WeChat/WeChatIsChangeBind")
	public String WeChatIsChangeBind()
	{
		return "phone/WeChatIsChangeBind";
	}
	
	
	@RequestMapping("WeChat/Bind")
	public String bind(HttpServletRequest request,Model model)
	{
		try
		{
			Integer userId = (Integer) request.getSession().getAttribute("userId");
			String myOpenId = (String) request.getSession().getAttribute("nowOpenId");
			if(userId == null)
				throw new Exception("请登录后在操作");
			if(weChatService.bind(userId,myOpenId))
			{
				request.getSession().setAttribute("nowOpenId",myOpenId);
				if(request.getSession().getAttribute("callbackUrl") != null)
				{
					String callbackUrl = request.getSession().getAttribute("callbackUrl").toString();
					request.getSession().setAttribute("callbackUrl", null);
					return "redirect:../"+callbackUrl;
				}
				return "phone/WeChatNewArticle";
			}
			else
				throw new Exception("绑定失败");
		}
		catch(Exception e)
		{
			model.addAttribute("exception", e.getMessage());
			return "phone/WeChat403";
		}
	}
	
	@RequestMapping("WeChat/NoBind")
	public String Nobing()
	{
		return "redirect:WeChatNewArticle";
	}
	
	@RequestMapping("WeChat/MyDetail")
	public String WeChatMyDetail(HttpServletRequest request,HttpServletResponse response,Model model) throws Exception
	{
		try
		{
			HttpSession session = request.getSession();
			Integer userId;
			//判断用户是否登陆
			if(session.getAttribute("userId") != null)
			{
				userId = (Integer) session.getAttribute("userId");
			}
			//用户未登录
			else if(session.getAttribute("nowOpenId") == null)
			{
				String code = request.getParameter("code");
				if(code == null)
				{
					throw new Exception("请登录后在操作");
				}
				JSONObject jsonObject = InterfaceUtil.getOathAccess_token(code);
				if(jsonObject.size() == 2)
				{
					throw new Exception("网络错误请重试");
				}
				else
				{
					if(!weChatService.IsOrNotHaveYouOpenId(jsonObject.getString("openid")))
						throw new Exception("微信号与用户名不匹配，请登录");
					session.setAttribute("nowOpenId",jsonObject.getString("openid"));
					//用微信登录的业务逻辑
					User user = weChatService.getUserDetail(jsonObject.getString("openid"));
					if(session.getAttribute("userId") == null){
						session.setAttribute("userId", user.getUserId());
					}
					if(session.getAttribute("userName") ==  null){
						session.setAttribute("userName",user.getUserNickname());
					}
					userId = user.getUserId();
				}
			}
			else
			{
				if(!weChatService.IsOrNotHaveYouOpenId(session.getAttribute("nowOpenId").toString()))
					throw new Exception("微信号与用户名不匹配，请登录");
				//用户通过nowOpenId的业务逻辑
				User user = weChatService.getUserDetail(session.getAttribute("nowOpenId").toString());
				if(session.getAttribute("userId") == null){
					session.setAttribute("userId", user.getUserId());
				}
				if(session.getAttribute("userName") ==  null){
					session.setAttribute("userName",user.getUserNickname());
				}
				userId = user.getUserId();
			}
			//根据userId进行操作的业务逻辑

			User user = weChatService.getUserDetailByuserId(userId);
			request.setAttribute("user", user);
			return "phone/WeChatMyDetail";
		}
		catch(Exception e)
		{
			model.addAttribute("exception", e.getMessage());
			return "phone/WeChat403";
		}

	}
	
	
	
	
	//注锟斤拷
	@RequestMapping("WeChat/Register")
	public String WeChatRegister(HttpServletRequest request,HttpServletResponse response,Model model)
	{
		return "phone/WeChatRegister";
	}
	
	
	@RequestMapping("WeChat/RegisterCheck")
	public String WeChatRegister(HttpServletRequest request,Model model)
	{
		try
		{
			String userName = request.getParameter("userName");
			String userNickname = request.getParameter("userNickname");
			Integer userGender = Integer.parseInt(request.getParameter("userGender"));
			String userAccount = request.getParameter("userAccount");
			String userPassword = request.getParameter("userPassword");
			String userbirthday = request.getParameter("userbirthday");
			String email = request.getParameter("email");
			Timestamp ts = new Timestamp(System.currentTimeMillis());  
	        ts = Timestamp.valueOf(userbirthday+" 00:00:00");  
			
			if(userName !=null && userNickname != null && userGender != null && userAccount != null && userPassword !=null)
			{
				User user = new User();
				user.setUserName(userName);
				user.setUserNickname(userNickname);
				user.setUserGender(userGender);
				user.setUserAccount(userAccount);
				user.setUserPassword(userPassword);
				user.setUserbirthday(ts);
				user.setUserEmail(email);
				System.out.println("start register");
				System.out.println(user.getUserAccount()+" "+user.getUserEmail()+" "+user.getUserPassword()+" "+user.getUserGender());
				if(weChatService.registerCheck(user))
					return "phone/WeChatSuccess";
				else
					throw new Exception("aaa");
			}
			else
				throw new Exception("aaa");
		}
		catch(Exception e)
		{
			model.addAttribute("exception","注册失败");
			return "phone/WeChat403";
		}
	}
	@RequestMapping("WeChat/WeChatModifyUser")
	public String weChatModifyUser(Model model,HttpServletRequest request)
	{
		model.addAttribute("user", userService.getUser((Integer)request.getSession().getAttribute("userId")));
		return "phone/WeChatModifyUser";
	}
	
	@ResponseBody
	@RequestMapping("WeChat/startModifyUser")
	public String startModifyUser(UserForm userForm) throws Exception
	{
		userForm.setUserbirthday(new Timestamp(System.currentTimeMillis()));
		JsonResult jsonResult;
		try
		{
			if(userService.modifyUser(userForm))
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
			jsonResult = new JsonResult("fail",0,null,null);
			return jsonResult.toJsonString();
		}
	}

	
	
	
	//this is test phone show,should delete
	@RequestMapping("WeChat/RecvNotice")
	public String WeChatRecvNotice(@RequestParam(required=false)Integer pno,Model model,HttpServletRequest request)
	{
		try{
			int pageSize = 6;
			Integer userId = checkLoginAndWeChatAccess(request);
			pno = (pno == null)?1:pno;
			Page page = sendNoticeService.getAllMyRecvNotice(userId,pno,pageSize);
			this.pageConfig((int) page.getTotalCount(), pageSize,"RecvNotice", model);
			model.addAttribute("notices",(List<ViewNoticeAccept>)page.getResult());
			return "phone/WeChatRecvNotice";
		}
		catch(Exception e){
			model.addAttribute("exception",e.getMessage());
			return "phone/WeChat403";
		}
	}
	
	@RequestMapping("WeChat/SendNotice")
	public String WeChatSendNotice(HttpServletRequest request,Model model)
	{
		try{
			Integer userId = checkLoginAndWeChatAccess(request);
			//根据userId进行操作的业务逻辑
			RecvNoticeObject recvNoticeObject = weChatService.getMySendNoticeObject(userId);
			//JSONObject myClassJson = weChatService.getMySendNoticeObject(userId);
			if(recvNoticeObject.getSchoolClass() == null && recvNoticeObject.getGrade() == null && recvNoticeObject.getSchool() == null)
				throw new Exception("对不起，你不能发送通知");
			if(recvNoticeObject.getSchoolClass() != null)
				model.addAttribute("schoolClassObject", NoticeAcceptType.NOTICEACCEPTTYPE_SCHOOLCLASS);
			if(recvNoticeObject.getGrade() != null)
				model.addAttribute("gradeObject", NoticeAcceptType.NOTICEACCEPTTYPE_GRADE);
			if(recvNoticeObject.getSchool() != null)
				model.addAttribute("schoolObject", NoticeAcceptType.NOTICEACCEPTTYPE_SCHOOL);
			return "phone/WeChatNoticeRecvObject";
		}
		catch(Exception e){
			model.addAttribute("exception",e.getMessage());
			return "phone/WeChat403";
		}
	}
	
	
	@RequestMapping("WeChat/sendNoticeToObject")
	public String sendNoticeToObject(HttpServletRequest request,Model model)
	{
		try
		{
			
			HttpSession session = request.getSession();
			Integer userId = (Integer) session.getAttribute("userId");
			System.out.println("进入发送通知页面");
			if(request.getParameter("objectType") == null)
				throw new Exception("请重新选择通知接受对象");
			Integer NoticeAcceptTypeId = Integer.parseInt(request.getParameter("objectType"));
			RecvNoticeObject recvNoticeObject = weChatService.getMySendNoticeObject(userId);
			if(NoticeAcceptTypeId == NoticeAcceptType.NOTICEACCEPTTYPE_SCHOOLCLASS)
			{
				model.addAttribute("type", NoticeAcceptType.NOTICEACCEPTTYPE_SCHOOLCLASS);
				model.addAttribute("name", "班级");
				model.addAttribute("recvObjectList", recvNoticeObject.getSchoolClass());
			}
			else if(NoticeAcceptTypeId == NoticeAcceptType.NOTICEACCEPTTYPE_GRADE)
			{
				model.addAttribute("type", NoticeAcceptType.NOTICEACCEPTTYPE_GRADE);
				model.addAttribute("name", "年级");
				model.addAttribute("recvObjectList",recvNoticeObject.getGrade());
			}
			else if(NoticeAcceptTypeId == NoticeAcceptType.NOTICEACCEPTTYPE_SCHOOL)
			{
				model.addAttribute("type", NoticeAcceptType.NOTICEACCEPTTYPE_SCHOOL);
				model.addAttribute("name", "学校");
				model.addAttribute("recvObjectList", recvNoticeObject.getGrade());
			}
			return "phone/WeChatSendNotice";
		}
		catch(Exception e)
		{
			model.addAttribute("exception", e.getMessage());
			return "phone/WeChat403";
		}
	}
	
	
	@RequestMapping("WeChat/WeChatSendedNotice")
	public String weChatSendedNotice(@RequestParam(required=false)Integer pno, HttpServletRequest request,Model model)
	{
		try
		{
			int pageSize = 6;
			Integer userId = checkLoginAndWeChatAccess(request);
			Set<Integer> roleIds = userService.getUserRoles(userId);
			if(!roleIds.contains(Role.ROLE_CLASSMASTER) && !roleIds.contains(Role.ROLE_GRADEMASTER) && !roleIds.contains(Role.ROLE_SCHOOLADMIN)
					&& !roleIds.contains(Role.ROLE_SCHOOLMASTER))
				throw new Exception("对不起，您的权限不足");
			pno = (pno == null)?1:pno;
			
			Page page = sendNoticeService.getAllSendedNotice(userId,pno, pageSize);
			this.pageConfig((int) page.getTotalCount(), pageSize, "WeChatSendedNotice", model);
			model.addAttribute("notices",(List<ViewNoticeAccept>)page.getResult());
			return "phone/WeChatSendedNotice";
		}
		catch(Exception e)
		{
			model.addAttribute("exception", e.getMessage());
			return "phone/WeChat403";
		}
	}
	
	
	
	
	
	@ResponseBody
	@RequestMapping("WeChat/startSendNotice")
	public String startSendNotice(HttpServletRequest request,Model model)
	{
		try
		{
			String noticeDetial = request.getParameter("noticeDetial");
			System.out.println(noticeDetial);
			if(sendNoticeService.weChatSendNotice(Integer.parseInt(request.getSession().getAttribute("userId").toString()),JSONObject.fromObject(noticeDetial)))
			{
				JsonResult jsonResult = new JsonResult("succ",0,null,null);
				return jsonResult.toJsonString();
			}
			else{
				JsonResult jsonResult = new JsonResult("fail",0,null,null);
				return jsonResult.toJsonString();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	
	@RequestMapping("WeChat/LookNotice")
	public String WeChatLookNotice(@RequestParam("noticeId") Integer noticeId,Model model,HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		try
		{
			Integer userId = checkLoginAndWeChatAccess(request);
			sendNoticeService.checkNoticeReply(userId,noticeId);
			if(sendNoticeService.checkNoticeIsMine(noticeId, userId))
			{
				List<ReplyForm> replys = sendNoticeService.getReplyforNotice(noticeId);
				int count = replys.size();
				int islook = 0;
				for(ReplyForm a:replys)
				{
					if(a.getCheckState() == Reply.LOOK_IS)
						islook++;
				}
				model.addAttribute("count",count);
				model.addAttribute("islook", islook);
				model.addAttribute("notlook", count-islook);
				model.addAttribute("replys", replys);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		Set<Attachment> attachments = sendNoticeService.getAttachemnts(noticeId);
		List<Attachment> attachments_img = new ArrayList<Attachment>();
		List<Attachment> attachments_other = new ArrayList<Attachment>();
		for(Attachment attachment:attachments)
		{
			String fileName = attachment.getAttachmentName();
			String tmpName = fileName.substring(fileName.lastIndexOf(".") + 1);  
			if(Arrays.asList(Attachment.ATTACHMENT_IMG).contains(tmpName.toLowerCase()))
			{
				attachments_img.add(attachment);
			}
			else
			{
				attachments_other.add(attachment);
			}
		}
		model.addAttribute("attachments_img", attachments_img);
		model.addAttribute("attachments_other", attachments_other);
		model.addAttribute("notice",sendNoticeService.getNotice(noticeId));
		return "phone/WeChatNoticeDetail";
	}
	
	//this is test phone show,should delete
	@RequestMapping("WeChat/WeChatArticle")
	public String WeChatArticle(HttpServletRequest request,HttpServletResponse response,Model model)
	{
//		List<ViewArticle> hasRecord = new ArrayList<ViewArticle>();
//		List<ViewArticle> articles = null;
//		int showSilde=1;
//		
//		/* 首页的幻灯片 */
//		articles = getArticleByModule(Constants.MODULE_ID_SLIDE, Constants.COUNT_HOME_SLIDE, false, hasRecord);
//		model.addAttribute("articleSilde", articles);
//		model.addAttribute("showSilde",showSilde);
//		
//		/* 今日热点 */
//		articles = getArticleByModule(Constants.MODULE_ID_TODAY, Constants.COUNT_HOME_TODAY, false, hasRecord);
//		model.addAttribute("articleToday", articles);
//		
//		/* 精彩活动  */
//		articles = getArticleByModule(Constants.MODULE_ID_PARTY, Constants.COUNT_HOME_PARTY, false, hasRecord);
//		model.addAttribute("articleParty", articles);
//		
//		/* 活动日历 */
//		articles = getArticleByModule(Constants.MODULE_ID_CALENDER, Constants.COUNT_HOME_CALENDER, false, hasRecord);
//		model.addAttribute("articleCalender", articles);
//		
//		/*文章列表�*/
//		articles = getRecommendArticle(Constants.COUNT_HOME_ARTICLE);
//		model.addAttribute("articleRecommend", articles);
//		
//		/* 校园之窗*/
//		articles = getSchoolRecommendArticle(Constants.COUNT_HOME_SCHOOL);
//		model.addAttribute("articleSchool", articles);
//		
//		
//		/* 法制安全*/
//		articles = getArticleByModule(Constants.MODULE_ID_SAFETY, Constants.COUNT_HOME_SAFETY, false, hasRecord);
//		model.addAttribute("articleSafety", articles);
		return "redirect:WeChatNewArticle";
	}
	
	@RequestMapping("WeChat/WeChatArticleDetial")
	public String WeChatArticleDetial(@RequestParam Integer articleId,Model model) throws Exception
	{
		System.out.println("手机端---查看文章详情");
		model.addAttribute("article", articleService.getArticle(articleId));
		model.addAttribute("images",articleService.getImageByArticle(articleId));
		model.addAttribute("videos", articleService.getVideoByArticle(articleId));
		
		
		//相关文章
		ViewArticle a = articleService.getArticle(articleId);
		Page page=articleService.getArticleByModule(1, 11, a.getModuleId(), false);
		List<ViewArticle> as = (List<ViewArticle>)page.getResult();
		List<ViewArticle> relates = new ArrayList<ViewArticle>();
		for (ViewArticle v : as) {
			if (v.getArticleId() != a.getArticleId())
				relates.add(v);
		}
		model.addAttribute("relates", relates);
		
		return "phone/WeChatNewArticleDetial";
	}
	@RequestMapping("WeChat/WeChatArticleType")
	public String WeChatArticleType(Model model)
	{
		return "phone/WeChatArticleType";
	}
	@RequestMapping("WeChat/ShowModule")
	public String WeChatShowModule(HttpServletRequest request,Model model)
	{
		try{

//			List<ViewArticle> hasRecord = new ArrayList<ViewArticle>();
//			List<ViewArticle> articles = null;
//			int showSilde=0;
//			/* 首页的幻灯片 */
//			articles = getArticleByModule(Constants.MODULE_ID_SLIDE, Constants.COUNT_HOME_SLIDE, false, hasRecord);
//			model.addAttribute("articleSilde", articles);	
//			model.addAttribute("showSilde",showSilde);
//
//			System.out.println(request.getParameter("moduleId"));
//			if(request.getParameter("moduleId").equals("all")){
//				model.addAttribute("moduleName","全部文章");
//				model.addAttribute("articles",(List<ViewArticle>)articleService.getAllArticle(1, 100, false, null).getResult());
//				return "phone/WeChatArticleModule";
//			}
//			Integer moduleId = Integer.parseInt(request.getParameter("moduleId"));
//			List<ViewArticle> viewArticles = new ArrayList<ViewArticle>();
//			//保存所有有文章的module
//			List<ViewModule> viewModules = new ArrayList<ViewModule>();
//			ViewModule module = moduleService.getModule(moduleId);
//			model.addAttribute("moduleName",module.getModuleName());
//			if(module.getChildrenModule() == null || module.getChildrenModule().size() == 0){
//				viewArticles = (List<ViewArticle>)articleService.getArticleByModule(1,1000, moduleId,false).getResult();
//				model.addAttribute("articles",viewArticles);
//			}
//			else{
//				viewArticles = (List<ViewArticle>)articleService.getArticleByModule(1,1000, moduleId,false).getResult();
//				model.addAttribute("articles", viewArticles);
//			}
//			return "phone/WeChatArticleModule";
			
			return "redirect:WeChatNewArticle?moduleId="+request.getParameter("moduleId");
		}
		catch(Exception e){
			return "phone/WeChat403";
		}
		
	}
	@RequestMapping("WeChat/WeChatAskQuestion")
	public String WeChatAskQuestion(HttpServletRequest request,Model model)
	{
		try{
			Integer userId = this.checkLoginAndWeChatAccess(request);
		}
		catch(Exception e)
		{
			
		}
		try
		{
			if(request.getParameter("respUserId").equals("all")){
				List<AgeLevel> ageLevels = askQuestionService.getAgeLevel();
				List<AskQuestionType> askQuestionTypes = askQuestionService.getAskQuestionType();
				model.addAttribute("ageLevels", ageLevels);
				model.addAttribute("askQuestionTypes", askQuestionTypes);
				model.addAttribute("respUserId", "all");
				return "phone/WeChatAskQuestion";
			}
			else if(Integer.parseInt(request.getParameter("respUserType")) == Role.ROLE_FAMOUSTEACHER){
				Integer famousTeacherId = Integer.parseInt(request.getParameter("respUserId"));
				ViewFamousTeacher a = famousTeacherService.getFamouseTeacher(famousTeacherId);
				if(a == null)
					throw new Exception("请重新提问");
				model.addAttribute("respName", a.getUserNickname());
				model.addAttribute("respUserId", a.getUserId());
				model.addAttribute("askQuestionTypes",askQuestionService.getAskQuestionType(famousTeacherId));
				return "phone/WeChatAskQuestion";
			}
			else if(Integer.parseInt(request.getParameter("respUserType")) == Role.ROLE_VOLUNTEER){
				Integer volunteerId = Integer.parseInt(request.getParameter("respUserId"));
				ViewVolunteer viewVolunteer = volunteerService.getVolunteer(volunteerId);
				if(viewVolunteer == null)
					throw new Exception("请重新提问");
				model.addAttribute("respName", viewVolunteer.getUserNickname());
				model.addAttribute("respUserId", viewVolunteer.getUserId());
				model.addAttribute("askQuestionTypes",askQuestionService.getAskQuestionType(volunteerId));
				return "phone/WeChatAskQuestion";
			}
			model.addAttribute("exception", "请重新提问");
			return "phone/WeChat403";
		}
		catch(Exception e)
		{
			model.addAttribute("exception", e.getMessage());
			return "phone/WeChat403";
		}
	}
	
	@ResponseBody
	@RequestMapping("WeChat/startAskQuestion")
	public String startAskQuestion(HttpServletRequest request,Model model) throws Exception
	{
		String askQuestionstring = request.getParameter("askQuestion");
		//{"ageLevel":["3","4"],"askQuestionPublic":[],"askQuestionType":"2","respUserId":"all","askQuestionTitle":"12","askQuestionContent":"12","askQuestionAttach":[{"attachId":null,"attachName":"f3d3572c11dfa9ec8b04414d64d0f703918fc145.jpg","attachSize":3833,"attachUrl":"../upload/20161102/1478071033684018347.jpg"},{"attachId":null,"attachName":"123321.jpg","attachSize":61390,"attachUrl":"../upload/20161102/1478071061472008728.jpg"}]}
		
		JSONObject askQuestionJson = JSONObject.fromObject(askQuestionstring);
		QuestionForm questionForm = new QuestionForm();
		
		if(askQuestionJson.get("askQuestionPublic") == null || askQuestionJson.get("askQuestionTitle") == null || askQuestionJson.get("askQuestionContent") == null)
		{
			JsonResult jsonResult = new JsonResult("fail",0,null,null);
			return jsonResult.toJsonString();
		}
		
		//将含有提问的问题的 json转换为questionForm
		List<Integer> a = JSONArray.toList(askQuestionJson.getJSONArray("ageLevel"),Integer.class);
		
		if(a == null || a.size() == 0)
			questionForm.setAgeLevels(null);
		else
			questionForm.setAgeLevels(a);
		questionForm.setAskQuestionContent(askQuestionJson.getString("askQuestionContent"));
		
		List<Integer> aa = JSONArray.toList(askQuestionJson.getJSONArray("askQuestionPublic"), Integer.class);
		if(aa == null || aa.size() == 0)
			questionForm.setAskQuestionPublic(false);
		else
			questionForm.setAskQuestionPublic(true);
		questionForm.setAskQuestionReadCount(0);
		questionForm.setAskQuestionRewardIntegral(0);
		questionForm.setAskquestionStateId(AskQuestionState.STATE_OPEN);
		questionForm.setAskQuestionTime(new Timestamp(System.currentTimeMillis()));
		questionForm.setAskQuestionTitle(askQuestionJson.getString("askQuestionTitle"));
		
		questionForm.setAskquestionTypeId(askQuestionJson.getInt("askQuestionType"));
		questionForm.setAskUserId(Integer.parseInt(request.getSession().getAttribute("userId").toString()));
		
		List<AttachVO> attachVOs = new ArrayList<AttachVO>();
		JSONArray attachJsonArray = askQuestionJson.getJSONArray("askQuestionAttach");
		if(attachJsonArray != null && attachJsonArray.size() > 0)
		{
			for(int i=0;i<attachJsonArray.size();i++)
			{
				AttachVO attachvo = new AttachVO();
				JSONObject attachJsonObject = attachJsonArray.getJSONObject(i);
				attachvo.setAttachName(attachJsonObject.getString("attachName"));
				attachvo.setAttachSize((float) attachJsonObject.getInt("attachSize"));
				attachvo.setAttachUrl(attachJsonObject.getString("attachUrl"));
				attachVOs.add(attachvo);
			}
		}
		
		questionForm.setAttachments(attachVOs);
		if(askQuestionJson.getString("respUserId") == null || askQuestionJson.getString("respUserId").equals("all"))
			questionForm.setRespUserId(null);
		else
			questionForm.setRespUserId(askQuestionJson.getInt("respUserId"));
		
		
		if(askQuestionService.createAskQuestion(questionForm) != null){
			JsonResult jsonResult = new JsonResult("succ",0,null,null);
			return jsonResult.toJsonString();
		}
		else{
			JsonResult jsonResult = new JsonResult("fail",0,null,null);
			return jsonResult.toJsonString();
		}
	}
	
	
	@ResponseBody
	@RequestMapping("WeChat/deleteQuestionImage")
	public String deleteQuestionImage(@RequestParam("attachUrl") String attachUrl,HttpServletRequest request) throws Exception
	{
		String realPath = request.getSession().getServletContext().getRealPath("/");
		String fileSavePath = realPath + "WEB-INF/upload/"+attachUrl;
		File myFilePath = new File(fileSavePath);   
		JsonResult jsonResult;
		try {   
		    if (!myFilePath.exists()) {   
		    	jsonResult = new JsonResult("fail",0,null,"文件不存在");
		    	return jsonResult.toJsonString();
		    }   
		    myFilePath.delete();
		    jsonResult = new JsonResult("succ",0,null,null);
		    return jsonResult.toJsonString();
		} 
		catch(Exception e)
		{
			jsonResult = new JsonResult("fail",0,null,e.getMessage());
			return jsonResult.toJsonString();
		}
	}
	
	
	@ResponseBody
	@RequestMapping("WeChat/deleteNoticeAttach")
	public String deleteNoticeAttach(@RequestParam("attachUrl") String attachUrl,HttpServletRequest request) throws Exception
	{
		String realPath = request.getSession().getServletContext().getRealPath("/");
		String fileSavePath = realPath + "WEB-INF/upload/"+attachUrl;
		File myFilePath = new File(fileSavePath);   
		JsonResult jsonResult;
		try {   
		    if (!myFilePath.exists()) {   
		    	jsonResult = new JsonResult("fail",0,null,"文件不存在");
		    	return jsonResult.toJsonString();
		    }   
		    myFilePath.delete();
		    jsonResult = new JsonResult("succ",0,null,null);
		    return jsonResult.toJsonString();
		} 
		catch(Exception e)
		{
			jsonResult = new JsonResult("fail",0,null,e.getMessage());
			return jsonResult.toJsonString();
		}
	}
	
	
	
	
	@RequestMapping("WeChat/WeChatFamousTeacher")
	public String WeChatFamousTeacher()
	{
		try
		{
			
//			model.addAttribute("ageLevels", askQuestionService.getAgeLevel());
//			List<AskQuestionTypeVO> askQuestionTypes = AskQuestionTypeVO.convert(askQuestionService.getAskQuestionType());
//			List<AskQuestionTypeVO> t = new ArrayList<AskQuestionTypeVO>();
//			for (AskQuestionTypeVO v : askQuestionTypes)
//				if (v.getAskQuestionTypeFlag() == AskQuestionType.ASKQUESTIONTYPE_FLAG_FAMOUSTEACHER)
//					t.add(v);
//			model.addAttribute("questionTypes", t);
//			model.addAttribute("more", 1);
			
			return "phone/WeChatFamousTeacher";
		}
		catch(Exception e)
		{
			//model.addAttribute("exception",e.getMessage());
			return "phone/WeChat403";
		}
	}
	
	//老的方法
	@ResponseBody
	@RequestMapping("WeChat/getMoreFamousTeacher")
	public String getMoreFamousTeacher(int pageNumber,int pageSize,String action,String dt) throws Exception
	{
		
//		try
//		{
//			
//			if(request.getParameter("ageLevel") == null || request.getParameter("askQuestionType") == null || request.getParameter("start") == null)
//				throw new Exception("查询出错");
//			
//			int size = 5;
//			Integer start = Integer.parseInt(request.getParameter("start"));
//			return famousTeacherService.getFamousTeachertoWeChat(start,size,request.getParameter("ageLevel"),request.getParameter("askQuestionType")).toJsonString();
//		}
//		catch(Exception e)
//		{
//			e.printStackTrace();
//			return null;
//		}
		JsonResult jsonResult;
		try
		{
			Page page = famousTeacherService.getAllFamousTeacher(null, pageNumber, pageSize);
			jsonResult = new JsonResult("succ",(int) page.getTotalCount(),page.getResult(),null);
			return jsonResult.toJsonString();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			jsonResult = new JsonResult("succ",0,null,null);
			return jsonResult.toJsonString();
		}
		
	}
	
	
	
	
	@RequestMapping("WeChat/WeChatFamousTeacherDetial")
	public String WeChatFamousTeacherDetial(HttpServletRequest request,Model model)
	{
		try
		{
			Integer famousTeacherId = Integer.parseInt(request.getParameter("famousTeacherId"));
			
			ViewFamousTeacher famousTeacher = famousTeacherService.getFamouseTeacher(famousTeacherId);
			if(famousTeacher == null)
				throw new Exception("失败");
			
			model.addAttribute("user",famousTeacher);
			
			List<AgeLevel> ageLevels = askQuestionService.getAgeLevel(famousTeacherId);
			List<AskQuestionType> askQuestionTypes = askQuestionService.getAskQuestionType(famousTeacherId);
			model.addAttribute("ageLevels", ageLevels);
			model.addAttribute("askQuestionTypes", askQuestionTypes);
			//获得该名师回答过的问题
			model.addAttribute("myAnswerQuestions", (List<ViewAskQuestion>)askQuestionService.getMyAnswerQuestion(famousTeacherId, "", 1, 3).getResult());
			
			
			
			model.addAttribute("famousTeacherId", famousTeacherId);
			model.addAttribute("famousTeacherRoleId",Role.ROLE_FAMOUSTEACHER);
			model.addAttribute("testData", "名师");
			
			return "phone/WeChatFamousTeacherDetial";
		}
		catch(Exception e)
		{
			model.addAttribute("exception", e.getMessage());
			return "phone/WeChat403";
		}

	}
	
	//http://localhost:8080/tsjyw/WeChat/WeChatLookQuestion?askQuestionId=1
	@RequestMapping("WeChat/WeChatLookQuestion")
	public String WeChatLookQuestion(@RequestParam Integer askQuestionId,Model model,HttpServletRequest request)
	{
		if(!HttpRequestDeviceUtils.isMobileDevice(request))
			return "redirect:../AskQuestionManage/ShowQuestion?askQuestionId="+askQuestionId;
		try
		{
			ViewAskQuestion askQuestion = askQuestionService.getQuestion(askQuestionId);
			if(askQuestion == null)
				throw new Exception("该问题为空");
			List<AgeLevelVO> ageLevels = askQuestion.getAgeLevels();
			String askQuestionTypeName = askQuestion.getAskQuestionTypeName();
			List<ViewRespQuestion> responses = askQuestion.getResponses();
			
			
			
			//通知的附件------
			Set<Attachment> attachments = askQuestionService.getAttachments(askQuestionId);
			List<Attachment> attachments_img = new ArrayList<Attachment>();
			List<Attachment> attachments_other = new ArrayList<Attachment>();
			for(Attachment attachment:attachments)
			{
				String fileName = attachment.getAttachmentName();
				String tmpName = fileName.substring(fileName.lastIndexOf(".") + 1);  
				if(Arrays.asList(Attachment.ATTACHMENT_IMG).contains(tmpName.toLowerCase()))
				{
					attachments_img.add(attachment);
				}
				else
				{
					attachments_other.add(attachment);
				}
			}
			//通知的附件-----完
			
			//回答的附件
			if(responses != null && responses.size() > 0)
			{
				for(ViewRespQuestion viewRespQuestion:responses)
				{
					List<AttachVO> attachVos = viewRespQuestion.getAttachments();
					if(attachVos != null && attachVos.size()>0)
					{
						for(int i=0;i<attachVos.size();i++)
						{
							viewRespQuestion.setAttachments(null);
							List<AttachVO> attachVoss = askQuestionService.getAttachmentForAttachVO(viewRespQuestion.getRespQuestionId());
							viewRespQuestion.setAttachments(attachVoss);
						}
					}
				}
			}
			//回答的附件---------
			
			
			if(ageLevels != null)
				model.addAttribute("ageLevels", ageLevels);
			if(askQuestionTypeName != null)
				model.addAttribute("askQuestionTypeName", askQuestionTypeName);
			if(responses != null)
				model.addAttribute("responses", responses);
			model.addAttribute("askQuestion", askQuestion);
			model.addAttribute("attachments_img", attachments_img);
			model.addAttribute("attachments_other", attachments_other);
			
			
			/*
			 * response question role
			 */
			if(request.getSession().getAttribute("userId") != null)
			{
				Integer userId = (Integer) request.getSession().getAttribute("userId");
				Set<Integer> roleIds = userService.getUserRoles(userId);
				if(roleIds.contains(Role.ROLE_FAMOUSTEACHER) || roleIds.contains(Role.ROLE_VOLUNTEER))
				{
					model.addAttribute("IsOrNotAskQuestion",1);
				}
			}
			
			
			return "phone/WeChatAskQuestionDetial";
		}
		catch(Exception e)
		{
			e.printStackTrace();
			model.addAttribute("exception", e.getMessage());
			return "phone/WeChat403";
		}
	}
	
	
	@RequestMapping("WeChat/WeChatResponseQuestion")
	public String weChatResponseQuestion(@RequestParam Integer askQuestionId,Model model,HttpServletRequest request)
	{
		try
		{
			ViewAskQuestion askQuestion = askQuestionService.getQuestion(askQuestionId);
			if(askQuestion == null)
				throw new Exception("该问题为空");
			List<AgeLevelVO> ageLevels = askQuestion.getAgeLevels();
			String askQuestionTypeName = askQuestion.getAskQuestionTypeName();
			//问题的详情
			Set<Attachment> attachments = askQuestionService.getAttachments(askQuestionId);
			List<Attachment> attachments_img = new ArrayList<Attachment>();
			List<Attachment> attachments_other = new ArrayList<Attachment>();
			for(Attachment attachment:attachments)
			{
				String fileName = attachment.getAttachmentName();
				String tmpName = fileName.substring(fileName.lastIndexOf(".") + 1);  
				if(Arrays.asList(Attachment.ATTACHMENT_IMG).contains(tmpName.toLowerCase()))
				{
					attachments_img.add(attachment);
				}
				else
				{
					attachments_other.add(attachment);
				}
			}
			if(ageLevels != null)
				model.addAttribute("ageLevels", ageLevels);
			if(askQuestionTypeName != null)
				model.addAttribute("askQuestionTypeName", askQuestionTypeName);
			model.addAttribute("askQuestion", askQuestion);
			model.addAttribute("attachments_img", attachments_img);
			model.addAttribute("attachments_other", attachments_other);

			return "phone/WeChatResponseQuestion";
		}
		catch(Exception e)
		{
			model.addAttribute("exception", e.getMessage());
			return "phone/WeChat403";
		}
	}
	
	@ResponseBody
	@RequestMapping("WeChat/startResponQuestion")
	public String startResponQuestion(HttpServletRequest request) throws Exception
	{
		JsonResult jsonResult;
		try
		{
			String responQuestionDetial = request.getParameter("ResponQuestionDetial");
			System.out.println(responQuestionDetial);
			//askQuestionService.modifyRespQuestion(responseForm)
			JSONObject responJson = JSONObject.fromObject(responQuestionDetial);
			ResponseForm responseForm = new ResponseForm();
			responseForm.setAskQuestionId(responJson.getInt("respQuestionId"));
			
			JSONArray attachJsonArray = responJson.getJSONArray("respQuestionAttachs");
			
			List<AttachVO> attachVOs = new ArrayList<AttachVO>();
			if(attachJsonArray != null && attachJsonArray.size() > 0)
			{
				for(int i=0;i<attachJsonArray.size();i++)
				{
					AttachVO attachvo = new AttachVO();
					JSONObject attachJsonObject = attachJsonArray.getJSONObject(i);
					attachvo.setAttachName(attachJsonObject.getString("attachName"));
					attachvo.setAttachSize((float) attachJsonObject.getInt("attachSize"));
					attachvo.setAttachUrl(attachJsonObject.getString("attachUrl"));
					attachVOs.add(attachvo);
				}
			}
			responseForm.setAttachments(attachVOs);
			responseForm.setRespQuestionContent(responJson.getString("respQuestionContent"));
			responseForm.setRespQuestionPublic(true);
			responseForm.setRespQuestionTime(new Timestamp(System.currentTimeMillis()));
			responseForm.setUserId((Integer)request.getSession().getAttribute("userId"));
			askQuestionService.createResponse(responseForm);
			jsonResult = new JsonResult("succ",0,null,null);
			return jsonResult.toJsonString();
			
		}
		catch(Exception e)
		{
			jsonResult = new JsonResult("fail",0,null,null);
			return jsonResult.toJsonString();
		}
	}
	
	
	
	
	@RequestMapping("WeChat/WeChatVolunteer")
	public String weChatVolunteer(HttpServletRequest request,Model model)
	{
//		model.addAttribute("ageLevels", askQuestionService.getAgeLevel());
//		List<AskQuestionTypeVO> askQuestionTypes = AskQuestionTypeVO.convert(askQuestionService.getAskQuestionType());	
//		List<AskQuestionTypeVO> t = new ArrayList<AskQuestionTypeVO>();
//		for (AskQuestionTypeVO v : askQuestionTypes)
//			if (v.getAskQuestionTypeFlag() == AskQuestionType.ASKQUESTIONTYPE_FLAG_VOLUNTEER)
//				t.add(v);
//		
//		
//		model.addAttribute("questionTypes",t);
		return "phone/WeChatVolunteer";
	}
	
	@ResponseBody
	@RequestMapping("WeChat/getMoreVolunteer")
	public String getMoreVolunteer(int pageNumber,int pageSize,String action,String dt) throws Exception
	{
		try
		{
			Page p = volunteerService.getAllVolunteer(null, pageNumber, pageSize);
			JsonResult result = new JsonResult("succ", (int)p.getTotalCount(), p.getResult(), null);
			return result.toJsonString();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			JsonResult jsonResult = new JsonResult("fail",0,null,e.getMessage());
			return jsonResult.toJsonString();
		}
	}
	
	
	@RequestMapping("WeChat/WeChatVolunteerDetial")
	public String weChatVolunteerDetial(@RequestParam Integer volunteerId,Model model)
	{
		ViewVolunteer viewVolunteer = volunteerService.getVolunteer(volunteerId);
		model.addAttribute("user", viewVolunteer);
		model.addAttribute("ageLevels", askQuestionService.getAgeLevel(volunteerId));
		model.addAttribute("askQuestionTypes", askQuestionService.getAskQuestionType(volunteerId));
		model.addAttribute("myAnswerQuestions", (List<ViewAskQuestion>)askQuestionService.getMyAnswerQuestion(volunteerId, null, 1, 3).getResult());
		model.addAttribute("famousTeacherId", volunteerId);
		model.addAttribute("famousTeacherRoleId", Role.ROLE_VOLUNTEER);
		model.addAttribute("testData", "志愿者");
		
		
		return "phone/WeChatFamousTeacherDetial";
	}
	
	
	@RequestMapping("WeChat/WeChatMyAskQuestion")
	public String weChatMyAskQuestion(HttpServletRequest request,Model model)
	{
		try
		{
			HttpSession session = request.getSession();
			if(session.getAttribute("userId") == null)
				throw new Exception("未登录，请先登录在操作");
			Integer userId = (Integer)session.getAttribute("userId");
			
			
			
			Page page = askQuestionService.getMyAskQuestion(userId, null, 1, 100);
			
			model.addAttribute("myAnswerQuestions", page.getResult());
			return "phone/WeChatMyAskQuestion";
		}
		catch(Exception e)
		{
			model.addAttribute("exception", e.getMessage());
			return "phone/WeChat403";
		}
	}
	
	
	@RequestMapping("WeChat/WeChatNoticeSystem")
	public String weChatNoticeSystem(HttpServletRequest request,Model model)
	{
		try
		{
			Integer userId = (Integer)request.getSession().getAttribute("userId");
			Set<Integer> roleIds = userService.getUserRoles(userId);
			if(!roleIds.contains(Role.ROLE_CLASSMASTER) && !roleIds.contains(Role.ROLE_GRADEMASTER) &&
					 !roleIds.contains(Role.ROLE_SCHOOLADMIN) && !roleIds.contains(Role.ROLE_SCHOOLMASTER))
				model.addAttribute("IsOrNotCanSendNotice", 0);
			else
				model.addAttribute("IsOrNotCanSendNotice", 1);
			
			return "phone/WeChatNoticeSystem";
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("控制器抛出的异常"+e.getMessage());
			model.addAttribute("exception",e.getMessage());
			return "phone/WeChat403";
		}
		
	}
	
	
	@RequestMapping("WeChat/WeChatRecvAnnouncement")
	public String recvAnnouncement(@RequestParam(required=false)Integer pno,Model model,HttpServletRequest request)
	{
		pno = (pno == null)?1:pno;
		int pageSize = 6;
		try
		{
			Integer userId = (Integer)request.getSession().getAttribute("userId");
			Page page = announcementService.getMyRecvAnnouncement(userId,pno,pageSize);
			this.pageConfig((int) page.getTotalCount(), pageSize, "WeChatRecvAnnouncement", model);
			model.addAttribute("notices", page.getResult());
			return "phone/WeChatRecvAnnouncement";
		}
		catch(Exception e)
		{
			return "phone/WeChat403";
		}
		
		
	}
	
	
	@ResponseBody
	@RequestMapping("WeChat/getRecvAnnouncement")
	public String startRecvAnnouncement(HttpServletRequest request,int pageNumber,int pageSize) throws Exception
	{
		JsonResult jsonResult;
		try
		{
			Integer userId = (Integer)request.getSession().getAttribute("userId");
			Page page = announcementService.getMyRecvAnnouncement(userId,pageNumber,pageSize);
			jsonResult = new JsonResult("succ",0,null,page.getResult());
			return jsonResult.toJsonString();
		}
		catch(Exception e)
		{
			jsonResult = new JsonResult("fail",0,null,null);
			return jsonResult.toJsonString();
		}
	}
	@RequestMapping("WeChat/WeChatLookAnnouncement")
	public String WeChatLookAnnouncement(@RequestParam Integer noticeId,Model model)
	{
		Set<Attachment> attachments = sendNoticeService.getAttachemnts(noticeId);
		List<Attachment> attachments_img = new ArrayList<Attachment>();
		List<Attachment> attachments_other = new ArrayList<Attachment>();
		for(Attachment attachment:attachments)
		{
			String fileName = attachment.getAttachmentName();
			String tmpName = fileName.substring(fileName.lastIndexOf(".") + 1);  
			if(Arrays.asList(Attachment.ATTACHMENT_IMG).contains(tmpName.toLowerCase()))
			{
				attachments_img.add(attachment);
			}
			else
			{
				attachments_other.add(attachment);
			}
		}
		model.addAttribute("attachments_img", attachments_img);
		model.addAttribute("attachments_other", attachments_other);
		model.addAttribute("notice",sendNoticeService.getNotice(noticeId));
		return "phone/WeChatAnnouncementDetial";
	}
	
	
	
	@RequestMapping("WeChat/WeChatAboutUs")
	public String weChatAboutUs()
	{
		return "phone/WeChatSuccess";
	}
	
	
	@RequestMapping("WeChat/WeChatCommonQuestion")
	public String weChatCommonQuestion(HttpServletRequest request)
	{
		return "phone/WeChatCommonQuestion";
	}
	
	@ResponseBody
	@RequestMapping("WeChat/getCommonQuestion")
	public String getCommonQuestion(@RequestParam("start") Integer start,@RequestParam("size") Integer size) throws Exception
	{
		Page page = askQuestionService.getAllQuestion(null, start, size);
		JsonResult jsonResult;
		try
		{
			List<ViewAskQuestion> viewAskQuestions = (List<ViewAskQuestion>) page.getResult();
			jsonResult = new JsonResult("succ",0,null,viewAskQuestions);
			return jsonResult.toJsonString();
		}
		catch(Exception e)
		{
			jsonResult = new JsonResult("fail",0,null,e.getMessage());
			return jsonResult.toJsonString();
		}
	}
	

	@RequestMapping("WeChat/Wechatdownload")
	public void downAttachment(@RequestParam Integer attachmentId,HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		/*  原来的文件下载
		String realPath = request.getSession().getServletContext().getRealPath("/");
		String attachmentUrl = realPath+"WEB-INF/upload/";
		Attachment attachment = sendNoticeService.getAttachment(attachmentId);
		String dfileName = new String(attachment.getAttachmentName().getBytes("gb2312"), "iso8859-1");
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		headers.setContentDispositionFormData("attachment",dfileName);
		return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(new File(attachmentUrl+attachment.getAttachmentUrl())),headers,HttpStatus.CREATED);
		*/
		
		String realPath = request.getSession().getServletContext().getRealPath("/");
		String attachmentUrl = realPath+"WEB-INF/upload/";
		Attachment attachment = sendNoticeService.getAttachment(attachmentId);
		String dfileName = new String(attachment.getAttachmentName().getBytes("gb2312"), "iso8859-1");
		String fileName = attachmentUrl+attachment.getAttachmentUrl();
		
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("multipart/form-data");
		response.setHeader("Content-Disposition", "attachment;fileName="
				+ dfileName);
		try {
			InputStream inputStream = new FileInputStream(new File(fileName));
			OutputStream os = response.getOutputStream();
			byte[] b = new byte[2048];
			int length;
			while ((length = inputStream.read(b)) > 0) {
				os.write(b, 0, length);
			}
			os.close();

			inputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("WeChat/WeChat403")
	public String weChat403()
	{
		return "phone/WeChat403";
	}
	
	
	private List<ViewArticle> getArticleByModule(int moduleId, int count, Boolean recommend, List<ViewArticle> recorded) {
		try{
			Page page=articleService.getArticleByModule(1, count, moduleId, recommend);
			if (recorded != null) {
				for (ViewArticle v : (List<ViewArticle>)page.getResult())
					recorded.add(v);
			}
			return (List<ViewArticle>)page.getResult();
		}
		catch (Exception e) {
			System.out.print(e);
			return null;
		}		
	}
	
	private List<ViewArticle> getRecommendArticle(int count) {
		try{
			Page page=articleService.getAllArticle(1, count, true, null);
			return (List<ViewArticle>)page.getResult();
		}
		catch (Exception e) {
			System.out.print(e);
			return null;
		}		
	}
	
	private List<ViewArticle> getSchoolRecommendArticle(int count) {
		try{
			Page page=articleService.getAllSchoolArticle(1, count, true);
			return (List<ViewArticle>)page.getResult();
		}
		catch (Exception e) {
			System.out.print(e);
			return null;
		}		
	}
	
	private List<ViewArticle> getArticleByType(int moduleTypeId, int count) {
		try{
			Page page=articleService.getArticleByType(1, count, moduleTypeId, true);
			return (List<ViewArticle>)page.getResult();
		}
		catch (Exception e) {
			System.out.print(e);
			return null;
		}		
	}
	
	
	//公共的check方法,判断是否登陆，是否为微信登录经过授权之类的
	private Integer checkLoginAndWeChatAccess(HttpServletRequest request) throws Exception
	{
		HttpSession session = request.getSession();
		Integer userId;
		//判断用户是否登陆
		if(session.getAttribute("userId") != null)
		{
			userId = (Integer) session.getAttribute("userId");
		}
		//用户未登录
		else if(session.getAttribute("nowOpenId") == null)
		{
			String code = request.getParameter("code");
			if(code == null)
			{
				System.out.println("11111111111111111111111");
				throw new Exception("请登录后在操作");
			}
			JSONObject jsonObject = InterfaceUtil.getOathAccess_token(code);
			System.out.println(jsonObject.toString());
			
			if(jsonObject.size() == 2)
			{
				throw new Exception("网络错误请重试");
			}
			else
			{
				if(!weChatService.IsOrNotHaveYouOpenId(jsonObject.getString("openid")))
				{
					System.out.println("11111111111111111111111");
					System.out.println(jsonObject.getString("openid"));
					session.setAttribute("nowOpenId",jsonObject.getString("openid"));
					throw new Exception("请登录后在操作");
				}
				session.setAttribute("nowOpenId",jsonObject.getString("openid"));
				//用微信登录的业务逻辑
				User user = weChatService.getUserDetail(jsonObject.getString("openid"));
				if(session.getAttribute("userId") == null){
					session.setAttribute("userId", user.getUserId());
				}
				if(session.getAttribute("userName") ==  null){
					session.setAttribute("userName",user.getUserNickname());
				}
				userId = user.getUserId();
			}
		}
		else
		{
			if(!weChatService.IsOrNotHaveYouOpenId(session.getAttribute("nowOpenId").toString()))
				throw new Exception("微信号与用户名不匹配，请登录");
			//用户通过nowOpenId的业务逻辑
			User user = weChatService.getUserDetail(session.getAttribute("nowOpenId").toString());
			if(session.getAttribute("userId") == null){
				session.setAttribute("userId", user.getUserId());
			}
			if(session.getAttribute("userName") ==  null){
				session.setAttribute("userName",user.getUserNickname());
			}
			userId = user.getUserId();
		}
		return userId;
	}
	
	@RequestMapping("WeChat/WeChatNewArticle")
	public String WeChatNewArticle(@RequestParam(required=false)Integer moduleId,Model model)
	{
		moduleId = (moduleId == null)?74:moduleId;
		model.addAttribute("moduleId", moduleId);
		List<ViewArticle> hasRecord = new ArrayList<ViewArticle>();
		List<ViewArticle> articles = getArticleByModule(Constants.MODULE_ID_SLIDE, Constants.COUNT_HOME_SLIDE, false, hasRecord);
		model.addAttribute("articleSilde", articles);
		return "phone/WeChatNewArticle";
	}
	
	
	@ResponseBody
	@RequestMapping("WeChat/WeChatGetMoreArticle")
	public String WeChatGetMoreArticle(int pageNumber,int pageSize,int action,String dt) throws Exception
	{
		JsonResult jsonResult;
		try
		{
			if(action == -1)
			{
				Page page = articleService.getAllArticle(pageNumber, pageSize, true, null);
				jsonResult = new JsonResult("succ",(int) page.getTotalCount(),page.getResult(),null);
				return jsonResult.toJsonString();
			}
			else
			{
				Page page = articleService.getArticleByModule(pageNumber, pageSize, action, true);
				jsonResult = new JsonResult("succ",(int) page.getTotalCount(),page.getResult(),null);
				return jsonResult.toJsonString();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			jsonResult = new JsonResult("succ",0,null,null);
			return jsonResult.toJsonString();
		}
	}
	
	@RequestMapping("WeChat/WeChatNewHotAskQuestion")
	public String WeChatNewAskQuestion(@RequestParam(required=false)Integer pno,Model model)
	{
		int pageSize = 5;
		//分页的配置 1.totalPage 2.totalRecords 3.nowHref
		pno = (pno == null)?1:pno;
		Page page = askQuestionService.getHotQuestion(pno,pageSize);
		this.pageConfig((int) page.getTotalCount(), pageSize, "WeChatNewHotAskQuestion", model);
		model.addAttribute("askQuestions", page.getResult());
		model.addAttribute("link", "hot");
		
		
		return "phone/WeChatNewQuestion";
	}
	
	@RequestMapping("WeChat/WeChatNewAllAskQuestion")
	public String WeChatNewAllAskQuestion(@RequestParam(required=false)Integer pno,Model model)
	{
		int pageSize = 5;
		//分页的配置 1.totalPage 2.totalRecords 3.nowHref
		pno = (pno == null)?1:pno;
		Page page = askQuestionService.getAllQuestion(null, pno, pageSize);
		this.pageConfig((int) page.getTotalCount(), pageSize, "WeChatNewAllAskQuestion", model);
		model.addAttribute("askQuestions", page.getResult());
		model.addAttribute("link", "all");
		return "phone/WeChatNewQuestion";
	}
	
	@RequestMapping("WeChat/WeChatNewMyAskQuestion")
	public String WeChatNewMyAskQuestion(@RequestParam(required=false)Integer pno,Model model,HttpServletRequest request)
	{
		
		try
		{
			int userId = (Integer) request.getSession().getAttribute("userId");
			int pageSize = 5;
			pno = (pno == null)?1:pno;
			Page page = askQuestionService.getMyAskQuestion(userId, null, pno, pageSize);
			this.pageConfig((int) page.getTotalCount(), pageSize, "WeChatNewMyAskQuestion", model);
			model.addAttribute("askQuestions", page.getResult());
			model.addAttribute("link","my");
			return "phone/WeChatNewQuestion";
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return "phone/WeChat403";
		}
		
		//分页的配置 1.totalPage 2.totalRecords 3.nowHref
	}
	
	@RequestMapping("WeChat/WeChatSchool")
	public String School()
	{
		return "phone/WeChatSchool";
	}
	
	@ResponseBody
	@RequestMapping("WeChat/getAllSchool")
	public String getAllSchool(int pageNumber, int pageSize) throws Exception
	{
		try{
			Page page = schoolService.getAllSchool(null, pageNumber, pageSize);
			if (page.getResult() != null)
			{
				List<ViewSchool> schools = (List<ViewSchool>)page.getResult();
				for (ViewSchool s : schools) {
					/*
					 * get image of latest article and set it as school list image
					 */
					Page p = articleService.getAllArticleBySchool(1, 10, s.getSchoolId(), true, null);
					if (p.getResult() == null)
						continue;
					List<ViewArticle> articles = (List<ViewArticle>)p.getResult();
					for (ViewArticle a : articles) {
						if (a.getImagePath() != null) {
							s.setSchoolImagePath(a.getImagePath());
							break;
						}
					}
				}
			}
			JsonResult result = new JsonResult("succ", (int)page.getTotalCount(), page.getResult(), null);
			return result.toJsonString();
		}
		catch (Exception e) {
			JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			return result.toJsonString();
		}
	}
	
	@RequestMapping("WeChat/WeChatSchoolDetial")
	public String WeChatSchoolDetial(@RequestParam Integer schoolId,Model model)
	{
		model.addAttribute("schoolId", schoolId);
		String schoolName = schoolService.getSchool(schoolId).getSchoolName();
		model.addAttribute("schoolName", schoolName);
		return "phone/WeChatSchoolDetial";
	}
	
	@ResponseBody
	@RequestMapping("WeChat/getSchoolMoreArticle")
	public String getSchoolMoreArticle(int pageNumber, int pageSize,int schoolId) throws Exception
	{
		JsonResult jsonResult;
		try
		{
			Page page = articleService.getAllArticleBySchool(pageNumber, pageSize, schoolId, false, null);
			jsonResult = new JsonResult("succ",(int) page.getTotalCount(),page.getResult(),null);
			return jsonResult.toJsonString();
		}
		catch(Exception e)
		{
			jsonResult = new JsonResult("fail",0,null,null);
			return jsonResult.toJsonString();
		}
	}
	
	private void pageConfig(int totalRecords,int pageSize,String nowHref,Model model)
	{
		int totalPage = totalRecords/pageSize;
		if(totalRecords%pageSize > 0)
			totalPage++;
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("totalRecords", totalRecords);
		model.addAttribute("nowHref", nowHref);
	}
	
	
}
