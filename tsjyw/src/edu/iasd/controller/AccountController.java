package edu.iasd.controller;

import java.io.IOException;

import javax.security.auth.login.CredentialException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.iasd.form.RegisterForm;
import edu.iasd.form.UserForm;
import edu.iasd.security.UserInfo;
import edu.iasd.service.UserService;
import edu.iasd.utils.HttpRequestDeviceUtils;

@Controller
public class AccountController extends ControllerBase {
	@Autowired
	private UserService userService;
	
	@RequestMapping("Account/Treaty") 
	public String treaty() {
		return "Treaty";
	}
	
	@RequestMapping("Account/Register") 
	public String register() {
		return "Register";
	}
	
	@RequestMapping(value="Account/Login",method = {RequestMethod.GET,RequestMethod.HEAD})
	public String login(Boolean error,Model model,HttpServletRequest request)	{
		
		if(HttpRequestDeviceUtils.isMobileDevice(request))
			return "redirect:../WeChat/WeChatNewArticle";
		if(error != null)
		{
			if(error == true)
				model.addAttribute("error","登录失败,用户名或者密码错误!");
		}
		return "Login";
	}
	
	
	
    @RequestMapping(value="Account/Login", method = {RequestMethod.GET,RequestMethod.HEAD},
            headers = "x-requested-with=XMLHttpRequest",
            produces = "application/json; charset=utf-8")
    @ResponseBody
    public String loginFailedByAjax(
            HttpSession session,
            RedirectAttributes redirectAttributes
            ) throws IOException{
        Object err = session.getAttribute("SPRING_SECURITY_LAST_EXCEPTION");
        if(err!=null){
            if(err instanceof UsernameNotFoundException){
                return "{\"status\":false,\"info\":\"用户名不存在！\"}";
            }else if(err instanceof BadCredentialsException){
                return "{\"status\":false,\"info\":\"密码错误！\"}";
            }else if(err instanceof LockedException){
                return "{\"status\":false,\"info\":\"账户被锁定！\"}";
            }else if(err instanceof DisabledException){
                return "{\"status\":false,\"info\":\"您需要<a href=\\'user/register/resend\\'>激活邮箱</a>才能登录！\"}";
            }else if(err instanceof CredentialException){
                return "{\"status\":false,\"info\":\"验证过期，请重新登录验证！\"}";
            }else{
                //log.error(((DisabledException) err).getMessage());
                return "{\"status\":false,\"info\":\"系统错误，请稍后重试！\"}";
            }
        }else{
            return "{\"status\":false,\"info\":\"系统错误，请稍后重试！\"}";
        }
    }
	
	
	@RequestMapping(value="Account/Authenticate",method={RequestMethod.GET,RequestMethod.HEAD})
	public String authenticate(String userName, String password, String verifyCode)	{
		System.out.println("````````````````````````````````````````````");
		return "ManageCenter";
	}
	
	@RequestMapping(value="Account/Authenticate",method = {RequestMethod.GET,RequestMethod.HEAD},
            headers = "x-requested-with=XMLHttpRequest",
            produces = "application/json; charset=utf-8")
	@ResponseBody
	public String loginSucceedByAjax(HttpServletRequest request,HttpServletResponse response)	
	{
		
		String path = request.getContextPath();
        String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
        String redirectUrl = basePath+"index.html"; 
        return  "{\"status\":true,\"redirect\":\""+redirectUrl+"\"}";
	}
	
	
	@RequestMapping("Account/Denied")
	public String denied()	{
		return "403";
	}
	
	@ResponseBody
	@RequestMapping("Account/userRegister")
	public String userRegister(RegisterForm form) throws Exception	{
		try {
			UserForm uf = form.translate();
			String id = userService.createUser(uf);
			 if ( id != null) {			
				 JsonResult result = new JsonResult("succ", 0, null, id);
				 return result.toJsonString();
			 }
			 else
				 throw new Exception("注册失败");
		}
		catch (Exception e) {
			return new JsonResult("fail", 0, null, e.getMessage()).toJsonString();
		}
	}
	
	@RequestMapping("Account/Personal")
	public String showMe(HttpServletRequest request, Model model)	{
		try {
			UserInfo curUser = super.getCurrentUser(request);
			model.addAttribute("user", curUser.getViewUser());
			return "Personal";
		}
		catch (Exception e) {			
			return "403";
		}
	}
	
	@ResponseBody
	 @RequestMapping(value="Account/changePassword")
	 public String changePassword(HttpServletRequest request, String oldPassword, String newPassword) throws Exception
	 {
		 try {
			 UserInfo curUser = super.getCurrentUser(request);
			 if (userService.changePassword(curUser.getViewUser().getUserId(), oldPassword, newPassword)) {			
				 JsonResult result = new JsonResult("succ", 0, null, null);
				 ObjectMapper objectMapper = new ObjectMapper();
				 String jsonString = objectMapper.writeValueAsString(result);
				 System.out.println(jsonString);
				 return jsonString;
			 }
			 else
				 throw new Exception("修改密码失败");
		 }
		 catch (Exception e) {
			 JsonResult result = new JsonResult("fail", 0, null, "修改密码失败: " + e.getMessage());
			 ObjectMapper objectMapper = new ObjectMapper();
			 String jsonString = objectMapper.writeValueAsString(result);
			 System.out.println(jsonString);
			 return jsonString;
		}		
	 }
	
	@ResponseBody
	 @RequestMapping(value="Account/changePhoto")
	 public String changePhoto(HttpServletRequest request, String userPhotoUrl) throws Exception
	 {
		 try {
			 UserInfo curUser = super.getCurrentUser(request);
			 if (userService.changePhoto(curUser.getViewUser().getUserId(), userPhotoUrl)) {			
				 JsonResult result = new JsonResult("succ", 0, null, null);
				 ObjectMapper objectMapper = new ObjectMapper();
				 String jsonString = objectMapper.writeValueAsString(result);
				 System.out.println(jsonString);
				 return jsonString;
			 }
			 else
				 throw new Exception("修改密码失败");
		 }
		 catch (Exception e) {
			 JsonResult result = new JsonResult("fail", 0, null, "修改密码失败: " + e.getMessage());
			 ObjectMapper objectMapper = new ObjectMapper();
			 String jsonString = objectMapper.writeValueAsString(result);
			 System.out.println(jsonString);
			 return jsonString;
		}		
	 }
}
