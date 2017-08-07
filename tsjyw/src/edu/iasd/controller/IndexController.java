package edu.iasd.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demonstration.hibernate.dao.support.Page;

import edu.iasd.utils.Constants;
import edu.iasd.form.AgeLevelVO;
import edu.iasd.form.AskQuestionTypeVO;
import edu.iasd.form.AttachVO;
import edu.iasd.pojo.AskQuestionType;
import edu.iasd.pojo.ModuleType;
import edu.iasd.pojo.SchoolType;
import edu.iasd.pojo.ViewAdvertisement;
import edu.iasd.pojo.ViewArticle;
import edu.iasd.pojo.ViewCounty;
import edu.iasd.pojo.ViewFamousTeacher;
import edu.iasd.pojo.ViewModule;
import edu.iasd.pojo.ViewNetCourse;
import edu.iasd.pojo.ViewSchool;
import edu.iasd.pojo.ViewVolunteer;
import edu.iasd.service.AdvertisementService;
import edu.iasd.service.ArticleService;
import edu.iasd.service.AskQuestionService;
import edu.iasd.service.CountyService;
import edu.iasd.service.FamousTeacherService;
import edu.iasd.service.ModuleService;
import edu.iasd.service.NetCourseService;
import edu.iasd.service.SchoolService;
import edu.iasd.service.VolunteerService;
import edu.iasd.search.QueryModel;
import edu.iasd.search.SearchParam;

@Controller
public class IndexController {
	@Resource
	CountyService countyService;
	
	@Resource
	ArticleService articleService;
	
	@Resource
	ModuleService moduleService;
	
	@Resource
	FamousTeacherService famousTeacherService;
	
	@Resource
	VolunteerService volunteerService;
	
	@Resource
	AskQuestionService askQuestionService;
	
	@Resource
	SchoolService schoolService;
	
	@Resource
	NetCourseService netCourseService;
	
	@Resource
	AdvertisementService advertisementService;
	
	@RequestMapping("Index")
	public String index(Model model)
	{
		List<ViewArticle> hasRecord = new ArrayList<ViewArticle>();
		List<ViewArticle> articles = null;
		List<ViewAdvertisement> advertisements=null;
		/* 棣栭〉骞荤伅  */
		articles = getArticleByModule(Constants.MODULE_ID_SLIDE, Constants.COUNT_HOME_SLIDE, false, hasRecord);
		model.addAttribute("articleSilde", articles);	
		
		/* 浠婃棩鐑偣  */
		articles = getArticleByModule(Constants.MODULE_ID_TODAY, Constants.COUNT_HOME_TODAY, false, hasRecord);
		model.addAttribute("articleToday", articles);
		
		/* 绮惧僵娲诲姩  */
		articles = getArticleByModule(Constants.MODULE_ID_PARTY, Constants.COUNT_HOME_PARTY, false, hasRecord);
		model.addAttribute("articleParty", articles);
		
		/* 娲诲姩鏃ュ巻  */
		articles = getArticleByModule(Constants.MODULE_ID_CALENDER, Constants.COUNT_HOME_CALENDER, false, hasRecord);
		model.addAttribute("articleCalender", articles);
		
		/* 鐪嬫枃绔�*/
		articles = getRecommendArticle(Constants.COUNT_HOME_ARTICLE);
		model.addAttribute("articleRecommend", articles);
		
		/* 鏍″洯涔嬬獥 */
		articles = getSchoolRecommendArticle(Constants.COUNT_HOME_SCHOOL);
		model.addAttribute("articleSchool", articles);
		
		/* 娉曞埗瀹夊叏 */
		articles = getArticleByModule(Constants.MODULE_ID_SAFETY, Constants.COUNT_HOME_SAFETY, false, hasRecord);
		model.addAttribute("articleSafety", articles);
		
		advertisements=advertisementService.getAllAdvertisement();
		model.addAttribute("advertisement", advertisements);
		return "Index";
	}	
	
	@RequestMapping("Index/ShowModule")
	public String showModule(int moduleId, Model model)
	{
		try {
			ViewModule m = moduleService.getModule(moduleId);
			model.addAttribute("curSubModule", m);
			if (m.getParentModuleId() == null) {
				model.addAttribute("curTopModule", m);			
			}
			else {
				model.addAttribute("curTopModule",  moduleService.getModule(m.getParentModuleId()));
			}
			
			if (m.getModuleTypeId() == ModuleType.MODULE_TYPE_PHOTO)
				return "ShowPhotoModule";
			else if (m.getModuleTypeId() == ModuleType.MODULE_TYPE_VIDEO) {
				/*
				 * 閿熸枻鎷峰彇閿熼ズ纰夋嫹閿熸枻鎷烽
				 */
				/*翟瑶瑶Edit MODULE_TYPE_VIDEO 6-->3*/
				model.addAttribute("hotVideo", getArticleByType(ModuleType.MODULE_TYPE_VIDEO, 3));
				return "ShowVideoModule";
			}
			else
				return "ShowModule";
		}
		catch (Exception e) {
			return "ShowModule";
		}
	}	
	@RequestMapping("Index/AboutUs") 
	public String aboutus() {
		return "AboutUs";
	}

	@RequestMapping("Index/ShowArticle")
	public String showArticle(int articleId, Model model)
	{
		try {
			/*
			 * 閿熸枻鎷峰彇瑕侀敓渚ヨ鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷风粏閿熸枻鎷烽敓鏂ゆ嫹
			 */
			ViewArticle a = articleService.getArticle(articleId);
			model.addAttribute("article", a);
			
			/*
			 * 閿熸枻鎷峰彇鍥剧墖閿熸枻鎷烽敓鏂ゆ嫹棰�
			 */
			List<AttachVO> photos = articleService.getImageByArticle(articleId);
			model.addAttribute("photos", photos);
			List<AttachVO> videos = articleService.getVideoByArticle(articleId);
			model.addAttribute("videos", videos);
	
			/*
			 * 閿熸枻鎷峰彇閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹鐩敓閰佃鎷烽敓鏂ゆ嫹閿熸枻鎷风洰
			 */
			model.addAttribute("article", a);
			ViewModule m = moduleService.getModule(a.getModuleId());
			model.addAttribute("curSubModule", m);
			if (m.getParentModuleId() == null) {
				model.addAttribute("curTopModule", m);			
			}
			else {
				model.addAttribute("curTopModule",  moduleService.getModule(m.getParentModuleId()));
			}
	
			/*
			 * 閿熸枻鎷峰彇鍚屼竴閿熸枻鎷风洰閿熼摪纰夋嫹閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹(閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓锟介敓鏂ゆ嫹閿熸枻鎷烽敓鏉板府鎷峰墠閿熸枻鎷风ず閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷�
			 */
			Page page=articleService.getArticleByModule(1, 11, a.getModuleId(), false);
			List<ViewArticle> as = (List<ViewArticle>)page.getResult();
			List<ViewArticle> relates = new ArrayList<ViewArticle>();
			List<ViewAdvertisement> advertisements=null;
			for (ViewArticle v : as) {
				if (v.getArticleId() != a.getArticleId())
					relates.add(v);
			}
			model.addAttribute("relates", relates);
			advertisements=advertisementService.getAllAdvertisement();
			model.addAttribute("advertisement", advertisements);
			/*
			 * 閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽閿熸枻鎷烽敓鏂ゆ嫹鍙栧墠涓�敓鏂ゆ嫹閿熼摪鍜岀尨鎷蜂竴閿熸枻鎷烽敓鏂ゆ嫹
			 */
			if (m.getModuleTypeId() == ModuleType.MODULE_TYPE_PHOTO) {
				ViewArticle prev = articleService.getPrevArticle(articleId);
				ViewArticle next = articleService.getNextvArticle(articleId);
				if (prev != null)
					model.addAttribute("prev", prev);
				if (next != null)
					model.addAttribute("next",  next);
			}
			
			if (m.getModuleTypeId() == ModuleType.MODULE_TYPE_PHOTO)
				return "ShowPhoto";
			else if (m.getModuleTypeId() == ModuleType.MODULE_TYPE_VIDEO) {
				model.addAttribute("hotVideo", getArticleByType(ModuleType.MODULE_TYPE_VIDEO, 10));
				return "ShowVideo";
			}
			else
				return "ShowArticle";
			
		}
		catch (Exception e) {
			return "ShowArticle";
		}		
	}
	
	@RequestMapping("Index/FamousTeacher")
	public String famousTeacher(Model model)
	{
		try {
			List<AgeLevelVO> ageLevels = AgeLevelVO.convert(askQuestionService.getAgeLevel());
			model.addAttribute("ageLevels", ageLevels);
			
			List<AskQuestionTypeVO> askQuestionTypes = AskQuestionTypeVO.convert(askQuestionService.getAskQuestionType());
			List<AskQuestionTypeVO> t = new ArrayList<AskQuestionTypeVO>();
			for (AskQuestionTypeVO v : askQuestionTypes)
				if (v.getAskQuestionTypeFlag() == AskQuestionType.ASKQUESTIONTYPE_FLAG_FAMOUSTEACHER)
					t.add(v);
			model.addAttribute("askQuestionTypes", t);
			
			model.addAttribute("hotArticles", getArticleByModule(Constants.MODULE_ID_QUESTION, 10, false, null));
			
			return "FamousTeacher";
		}
		catch (Exception e) {
			return "FamousTeacher";
		}
	}	
	
	@RequestMapping("Index/Volunteer")
	public String volunteer(Model model)	{
		try {
			Page c = netCourseService.getAllNetCourse(1, 5);
			model.addAttribute("netcourse", c.getResult());
			
			return "Volunteer";
		}
		catch (Exception e) {
			return "Volunteer";
		}
	}	
	
	@RequestMapping("Index/MoreVolunteer")
	public String moreVolunteer(Model model)
	{
		try {
			List<AgeLevelVO> ageLevels = AgeLevelVO.convert(askQuestionService.getAgeLevel());
			model.addAttribute("ageLevels", ageLevels);
			List<AskQuestionTypeVO> askQuestionTypes = AskQuestionTypeVO.convert(askQuestionService.getAskQuestionType());	
			List<AskQuestionTypeVO> t = new ArrayList<AskQuestionTypeVO>();
			for (AskQuestionTypeVO v : askQuestionTypes)
				if (v.getAskQuestionTypeFlag() == AskQuestionType.ASKQUESTIONTYPE_FLAG_VOLUNTEER)
					t.add(v);
			model.addAttribute("askQuestionTypes", t);
			model.addAttribute("hotArticles", getArticleByModule(Constants.MODULE_ID_QUESTION, 10, false, null));
			
			return "MoreVolunteer";
		}
		catch (Exception e) {
			return "MoreVolunteer";
		}
	}	
	
	@RequestMapping("Index/ShowFamousTeacher")
	public String showFamousTeacher(int userId, Model model)
	{
		try {
			ViewFamousTeacher t = famousTeacherService.getFamouseTeacher(userId);
			model.addAttribute("teacher", t);
			
			if (t.getUserbirthday() != null) {
				Calendar today = Calendar.getInstance();
				int yearNow = today.get(Calendar.YEAR);
				Calendar bir = Calendar.getInstance();
				bir.setTime(t.getUserbirthday());
				int yearBir = bir.get(Calendar.YEAR);
				model.addAttribute("age", yearNow - yearBir);
			}
			else
				model.addAttribute("age", 40);
			
			Page c = netCourseService.getNetCourseByUser(1, 3, userId);
			model.addAttribute("netcourse", c.getResult());
			
			Page p = famousTeacherService.getAllFamousTeacher(null, 1, 10);
			model.addAttribute("recommend", p.getResult());
			
			return "ShowFamousTeacher";
		}
		catch (Exception e) {
			return "ShowFamousTeacher";
		}
	}
	
	@RequestMapping("Index/ShowVolunteer")
	public String showVolunteer(int userId, Model model)
	{
		try {
			ViewVolunteer t = volunteerService.getVolunteer(userId);
			model.addAttribute("volunteer", t);
			
			if (t.getUserbirthday() != null) {
				Calendar today = Calendar.getInstance();
				int yearNow = today.get(Calendar.YEAR);
				Calendar bir = Calendar.getInstance();
				bir.setTime(t.getUserbirthday());
				int yearBir = bir.get(Calendar.YEAR);
				model.addAttribute("age", yearNow - yearBir);
			}
			else
				model.addAttribute("age", 40);
			
			Page p = volunteerService.getAllVolunteer(null, 1, 10);
			model.addAttribute("recommend", p.getResult());
			
			return "ShowVolunteer";
		}
		catch (Exception e) {
			return "ShowVolunteer";
		}
	}
	
	@RequestMapping("Index/ShowNetCourseModule")
	public String showNetCourseModule(int netCourseTypeId, Model model)
	{
		try {
			model.addAttribute("netCourseTypeId", netCourseTypeId);
			model.addAttribute("netcoursetype", netCourseService.getNetCourseType());
			
			return "ShowNetCourseModule";
		}
		catch (Exception e) {
			return "ShowNetCourseModule";
		}
	}
	
	@RequestMapping("Index/ShowNetCourse")
	public String showNetCourse(int netCourseId, Model model)
	{
		try {
			ViewNetCourse t = netCourseService.getNetCourse(netCourseId);
			model.addAttribute("netcourse", t);
			
			Page p = netCourseService.getNetCourseByType(1, 10, t.getNetCourseTypeId());
			model.addAttribute("relate", p.getResult());
			
			return "ShowNetCourse";
		}
		catch (Exception e) {
			return "ShowNetCourse";
		}
	}

	@ResponseBody
	@RequestMapping("Index/searchVolunteer")
	public String searchVolunteer(String volunteerPCC, int ageLevelId, int askQuestionTypeId, String userName) throws Exception
	{
		try{
			List<ViewVolunteer> lt = volunteerService.searchVolunteer(volunteerPCC, ageLevelId, askQuestionTypeId, userName);
			JsonResult result = new JsonResult("succ", lt.size(), lt, null);
			return result.toJsonString();
		}
		catch (Exception e) {
			JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			return result.toJsonString();
		}
	}
	
	@ResponseBody
	@RequestMapping("Index/searchFamousTeacher")
	public String searchFamousTeacher(int pageNumber, int pageSize, int askQuestionTypeId, String userName) throws Exception
	{
		try{
			Page p = famousTeacherService.searchFamousTeacher(pageNumber, pageSize, askQuestionTypeId, userName);
			JsonResult result = new JsonResult("succ", (int)p.getTotalCount(), p.getResult(), null);
			return result.toJsonString();
		}
		catch (Exception e) {
			JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			return result.toJsonString();
		}
	}
	
	@RequestMapping("Index/School")
	public String showSchoolWindow(Model model)	{
		try {
			ViewModule m = moduleService.getModule(Constants.COUNT_HOME_SCHOOL);
			model.addAttribute("curSubModule", m);
			model.addAttribute("curTopModule", m);	
			
			List<SchoolType> st = schoolService.getSchoolType();
			model.addAttribute("schoolTypes", st);	
			return "School";
		}
		catch (Exception e) {
			return "School";
		}
	}	
	
	@ResponseBody
	@RequestMapping("Index/getRelateArticle")
	public String getRelateArticle(int articleId, int count) throws Exception
	{
		/*
		 * 閿熸枻鎷峰彇鍚屼竴閿熸枻鎷风洰閿熼摪纰夋嫹閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹(閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓锟介敓鏂ゆ嫹閿熸枻鎷烽敓鏉板府鎷峰墠閿熸枻鎷风ず閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷�
		 */
		try{
			ViewArticle a = articleService.getArticle(articleId);
			ViewModule m = moduleService.getModule(a.getModuleId());
			
			Page page=articleService.getArticleByModule(1, count + 1, a.getModuleId(), false);
			List<ViewArticle> as = (List<ViewArticle>)page.getResult();
			List<ViewArticle> relates = new ArrayList<ViewArticle>();
			for (ViewArticle v : as) {
				if (v.getArticleId() != a.getArticleId())
					relates.add(v);
				if (relates.size() >= count)
					break;
			}
			
			JsonResult result = new JsonResult("succ", relates.size(), relates, null);
			return result.toJsonString();
		}
		catch (Exception e) {
			JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			return result.toJsonString();
		}
	}	
	
	@ResponseBody
	@RequestMapping("Index/getHotArticle")
	public String getHotArticle(int pageNumber, int pageSize) throws Exception
	{
		/*
		 * 鑾峰彇鏈�柊鐨勬帹鑽愭枃绔�
		 */
		try{
			Page page=articleService.getAllArticle(pageNumber, pageSize, true, null);			
			JsonResult result = new JsonResult("succ", (int)page.getTotalCount(), page.getResult(), null);
			return result.toJsonString();
		}
		catch (Exception e) {
			JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			return result.toJsonString();
		}
	}	
	
	@ResponseBody
	@RequestMapping("Index/getHotNetCourse")
	public String getHotNetCourse(int pageNumber, int pageSize) throws Exception
	{
		try{
			Page page= netCourseService.getAllNetCourse(pageNumber, pageSize);		
			JsonResult result = new JsonResult("succ", (int)page.getTotalCount(), page.getResult(), null);
			return result.toJsonString();
		}
		catch (Exception e) {
			JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			return result.toJsonString();
		}
	}
	
	@ResponseBody
	@RequestMapping("Index/getAllNetCourse")
	public String getAllNetCourse(int pageNumber, int pageSize, int netCourseTypeId) throws Exception
	{
		try{
			Page page;
			if (netCourseTypeId == -1)
				page = netCourseService.getAllNetCourse(pageNumber, pageSize);
			else
				page = netCourseService.getNetCourseByType(pageNumber, pageSize, netCourseTypeId);
			JsonResult result = new JsonResult("succ", (int)page.getTotalCount(), page.getResult(), null);
			return result.toJsonString();
		}
		catch (Exception e) {
			JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			return result.toJsonString();
		}
	}
	
	/*
	 * 涓烘牎鍥箣绐楁ā鍧楄幏鍙栧叏閮ㄥ鏍★紝杩欓噷鐨勫鐞嗕笌SchoolManage閲岀殑鍚屽悕鏂规硶涓嶅悓锛岃繖閲屼笉鏍规嵁瑙掕壊鍒ゆ柇 
	 */
	@ResponseBody
	@RequestMapping("Index/getAllSchool")
	public String getAllSchool(int pageNumber, int pageSize, @SearchParam QueryModel queryModel) throws Exception
	{
		try{
			Page page = schoolService.getAllSchool(queryModel.toQueryString(), pageNumber, pageSize);
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

	@ResponseBody
	@RequestMapping("Index/getAllCounty")
	public String getAllcounty(HttpServletRequest request, int pageNumber, int pageSize, @SearchParam QueryModel queryModel) throws Exception {
		
		try {
			Page page = countyService.getAllcounty(queryModel.toQueryString(), pageNumber, pageSize);
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
}
