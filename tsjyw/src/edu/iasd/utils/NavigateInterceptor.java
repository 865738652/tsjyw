package edu.iasd.utils;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils; 
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.demonstration.hibernate.basedao.IBaseDao;

import edu.iasd.pojo.ViewModule;
import edu.iasd.service.ModuleService;

public class NavigateInterceptor extends HandlerInterceptorAdapter {
	
	private IBaseDao<edu.iasd.pojo.ViewModule> viewModuleDao;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object handler) throws Exception { 		
		/*
		 * 如果没有加载过导航菜单，加载菜单保存到session
		 */
		
		//handler.getClass()
		if (request.getSession().getAttribute("nav_menu") == null) {
			System.out.println("retrieve navigator menu.");
			ApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
			if (ac == null)
				throw new Exception("内部错误：无法获取WebApplicationContext");
			viewModuleDao = (IBaseDao<edu.iasd.pojo.ViewModule>)ac.getBean("viewModuleDao");
			List<ViewModule> menu = getNavigatorMenu();
			if (menu != null)
				request.getSession().setAttribute("nav_menu", menu);			
		}
		return true;
	}
	
	private List<ViewModule> getNavigatorMenu() {
		List<ViewModule> top = (List<ViewModule>)viewModuleDao.find("from ViewModule v where v.schoolId is NULL and v.moduleIsShow = true and v.parentModuleId is NULL order by v.moduleSerial", null);
		for (ViewModule p : top) {
			List<ViewModule> children = (List<ViewModule>)viewModuleDao.find("from ViewModule v where v.parentModuleId=? and v.moduleIsShow = true order by v.moduleSerial", p.getModuleId());
			p.setChildrenModule(children);
		}
		return top;
	}
}
