package edu.iasd.utils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import edu.iasd.pojo.Role;

public class WeChatInterceptor extends HandlerInterceptorAdapter{
	
	
	@Autowired
	private WeChatInterceptorHelper weChatInterceptorHelper;
	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object handler) throws Exception { 			
		String requestPath = request.getServletPath();
		Set<Integer> needRoleIds = getRoleSet(requestPath);
		if(needRoleIds == null || needRoleIds.size() == 0)
			return true;
		Integer userId = null;
		try
		{
			userId = weChatInterceptorHelper.checkLoginAndWeChatAccess(request);
		}
		catch(Exception e)
		{
			System.out.println("À¹½ØÆ÷Å×³öµÄÒì³£");
			e.printStackTrace();
			System.out.println(request.getRequestURL());
			System.out.println(requestPath.split("/")[2]);
			request.getSession().setAttribute("callbackUrl", requestPath.split("/")[2]);
			response.sendRedirect("WeChat403");
		}
		if(needRoleIds.size() == 1 && needRoleIds.contains(Role.ROLE_USER))
			return true;
		Set<Integer> myRoleIds = weChatInterceptorHelper.getRoleIds(userId);
		for(Integer a:needRoleIds)
		{
			if(a != Role.ROLE_USER){
				if(myRoleIds.contains(a))
					return true;
			}
		}
		return false;
	}
	private Set<Integer> getRoleSet(String requestPath)
	{
		Map<String,Set<Integer>> User_Role = new HashMap<String,Set<Integer>>();
		User_Role.put("/WeChat/WeChatNoticeSystem", weChatInterceptorHelper.getOnlyNeedUserRole());
		User_Role.put("/WeChat/MyDetail",weChatInterceptorHelper.getOnlyNeedUserRole());
		User_Role.put("/WeChat/RecvNotice",weChatInterceptorHelper.getOnlyNeedUserRole());
		User_Role.put("/WeChat/SendNotice",weChatInterceptorHelper.getSendNoticeRole());
		User_Role.put("/WeChat/LookNotice", weChatInterceptorHelper.getOnlyNeedUserRole());
		
		return User_Role.get(requestPath);
	}
}
