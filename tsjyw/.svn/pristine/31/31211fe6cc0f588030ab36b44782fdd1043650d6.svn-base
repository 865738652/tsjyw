package edu.iasd.utils;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.sf.json.JSONObject;
import edu.iasd.pojo.Role;
import edu.iasd.pojo.User;
import edu.iasd.service.UserService;
import edu.iasd.service.WechatService;
import edu.iasd.wechat.utils.InterfaceUtil;


@Component
public class WeChatInterceptorHelper {
	
	@Autowired
	public WechatService weChatService;
	
	@Autowired
	public UserService userService;
	
	//公共的check方法,判断是否登陆，是否为微信登录经过授权之类的
	public Integer checkLoginAndWeChatAccess(HttpServletRequest request) throws Exception
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
				{
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
	public Set<Integer> getRoleIds(Integer userId)
	{
		return userService.getUserRoles(userId);
	}
	
	//仅仅需要用户登录的权限
	public Set<Integer> getOnlyNeedUserRole()
	{
		Set<Integer> a = new HashSet<Integer>();
		a.add(Role.ROLE_USER);
		return a;
	}
	
	//发送通知的权限
	public Set<Integer> getSendNoticeRole()
	{
		Set<Integer> a = new HashSet<Integer>();
		a.add(Role.ROLE_GRADEMASTER);
		a.add(Role.ROLE_CLASSMASTER);
		a.add(Role.ROLE_SCHOOLADMIN);
		a.add(Role.ROLE_SCHOOLMASTER);
		return a;
	}
 	
	
}
