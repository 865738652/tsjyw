package edu.iasd.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demonstration.hibernate.dao.support.Page;

import edu.iasd.pojo.ViewArticle;
import edu.iasd.pojo.ViewModule;
import edu.iasd.pojo.ViewSchool;
import edu.iasd.service.ArticleService;
import edu.iasd.service.ModuleService;
import edu.iasd.service.SchoolService;

@Controller
public class SchoolController extends ControllerBase {
	
	@Autowired
	private SchoolService schoolService;
	
	@Autowired
	private ModuleService moduleService;
	
	@Autowired
	private ArticleService articleService;
	
	@RequestMapping("School/{schoolid}")
	public String showSchoolHome(@PathVariable int schoolid,Model model)
	{
		try {
			ViewSchool school = schoolService.getSchool(schoolid);
			model.addAttribute("school", school);
			
			List<ViewModule> modules = moduleService.getSchoolNavigatorMenu(schoolid);
			model.addAttribute("modules", modules);
			
			Page page = articleService.getAllArticleBySchool(1, 10000, schoolid, false, null);
			List<ViewArticle> articles = (List<ViewArticle>)page.getResult();
			
			List<ViewArticle> topArticles = new ArrayList<ViewArticle>();
			List<ViewArticle> picArticles = new ArrayList<ViewArticle>();
			List<ViewArticle> xinwen = new ArrayList<ViewArticle>();
			List<ViewArticle> fumu = new ArrayList<ViewArticle>();
			List<ViewArticle> jiajiao = new ArrayList<ViewArticle>();
			List<ViewArticle> deyu = new ArrayList<ViewArticle>();
			List<ViewArticle> xinli = new ArrayList<ViewArticle>();
			List<ViewArticle> qinzi = new ArrayList<ViewArticle>();
			if (articles != null) {
				for (ViewArticle va : articles) {
					if (va.getModuleName().equals("��ҳ�õ�"))
						topArticles.add(va);
					else if (va.getModuleName().equals("ͼƬ����"))
						picArticles.add(va);
					else if (va.getModuleName().equals("У԰����"))
						xinwen.add(va);
					else if (va.getModuleName().equals("��ĸѧ��"))
						fumu.add(va);
					else if (va.getModuleName().equals("�ҽ̶�̬"))
						jiajiao.add(va);
					else if (va.getModuleName().equals("�������"))
						deyu.add(va);
					else if (va.getModuleName().equals("������"))
						xinli.add(va);
					else if (va.getModuleName().equals("����չʾ"))
						qinzi.add(va);
				}
			}
			
			model.addAttribute("topArticles", topArticles);
			model.addAttribute("picArticles", picArticles);
			model.addAttribute("xinwen", xinwen);
			model.addAttribute("fumu", fumu);
			model.addAttribute("jiajiao", jiajiao);
			model.addAttribute("deyu", deyu);
			model.addAttribute("xinli", xinli);
			model.addAttribute("qinzi", qinzi);
			return "SchoolIndex";
		}
		catch (Exception e) {
			return "SchoolIndex";
		}
	}
	
	@RequestMapping("School/SchoolList")
	public String showSchoolModule(int moduleId, Model model)
	{
		try {
			ViewModule module = moduleService.getModule(moduleId);
			model.addAttribute("module", module);
		
			ViewSchool school = schoolService.getSchool(module.getSchoolId());
			model.addAttribute("school", school);
			
			List<ViewModule> modules = moduleService.getSchoolNavigatorMenu(module.getSchoolId());
			model.addAttribute("modules", modules);
			
			return "SchoolList";
		}
		catch (Exception e) {
			return "SchoolList";
		}
	}
	
	@RequestMapping("School/SchoolShow")
	public String showSchoolArticle(int articleId, Model model)
	{
		try {
			ViewArticle article = articleService.getArticle(articleId);
			model.addAttribute("article", article);
			
			ViewModule module = moduleService.getModule(article.getModuleId());
			model.addAttribute("module", module);
		
			ViewSchool school = schoolService.getSchool(module.getSchoolId());
			model.addAttribute("school", school);
			
			List<ViewModule> modules = moduleService.getSchoolNavigatorMenu(module.getSchoolId());
			model.addAttribute("modules", modules);
			
			return "SchoolShow";
		}
		catch (Exception e) {
			return "SchoolShow";
		}
	}
}
