package edu.iasd.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.iasd.service.UserService;

@Controller
public class ManageCenterController extends ControllerBase{
	
	@Autowired
	private UserService userService;
	
	
	@RequestMapping("Admin")
	public String manageCenter(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		
		try
		{
			userService.addUserIntegral(super.getCurrentUser(request).getViewUser().getUserId());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return "ManageCenter";
	}
	@RequestMapping(value="Admin",method = {RequestMethod.GET,RequestMethod.HEAD},
            headers = "x-requested-with=XMLHttpRequest",
            produces = "application/json; charset=utf-8")
	@ResponseBody
	public String loginSucceedByAjax(HttpServletRequest request,HttpServletResponse response)	
	{
		try
		{
			userService.addUserIntegral(super.getCurrentUser(request).getViewUser().getUserId());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		String path = request.getContextPath();
        String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
        String redirectUrl = basePath+"index.html"; 
        return  "{\"status\":1,\"redirect\":\""+redirectUrl+"\"}";
	}
	
}
