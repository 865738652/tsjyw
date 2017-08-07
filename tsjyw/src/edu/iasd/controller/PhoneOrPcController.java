package edu.iasd.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.iasd.utils.HttpRequestDeviceUtils;


@Controller
public class PhoneOrPcController {
	
	@RequestMapping("Index/checkUrlQuestion")
	public String aaa(@RequestParam Integer questionId,HttpServletRequest request)
	{
		if(HttpRequestDeviceUtils.isMobileDevice(request))
			return "redirect:../WeChat/WeChatLookQuestion?askQuestionId="+questionId;
		return "redirect";
	}
}
