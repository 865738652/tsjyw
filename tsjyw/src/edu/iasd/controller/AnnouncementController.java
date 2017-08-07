package edu.iasd.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demonstration.hibernate.dao.support.Page;

import edu.iasd.form.ReplyForm;
import edu.iasd.pojo.Reply;
import edu.iasd.pojo.Role;
import edu.iasd.pojo.ViewCountyManager;
import edu.iasd.pojo.ViewNoticeAccept;
import edu.iasd.security.UserInfo;
import edu.iasd.service.AnnouncementService;
import edu.iasd.service.CountyManagerService;
import edu.iasd.service.SendNoticeService;
import edu.iasd.service.UserService;


@Controller
public class AnnouncementController extends ControllerBase{
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CountyManagerService countyMangerService;
	
	@Autowired
	private AnnouncementService announcementService;
	
	@Autowired
	private SendNoticeService sendNoticeService;
	
	@RequestMapping("SendAnnouncement")
	public String sendAnnouncement(HttpServletRequest request,Model model)
	{
		UserInfo userInfo;
		try
		{
			userInfo = super.getCurrentUser(request);
			
			Integer userId = userInfo.getViewUser().getUserId();
			
			Set<Integer> roleIds = userService.getUserRoles(userId);
			if(!roleIds.contains(Role.ROLE_SUPERADMIN) && !roleIds.contains(Role.ROLE_COUNTYADMIN))
				throw new Exception("权限不足");
			if(roleIds.contains(Role.ROLE_SUPERADMIN))
			{
				model.addAttribute("role", 1);
				model.addAttribute("countyId", -1);
				model.addAttribute("countyName", "全部");
			}
			else
			{
				ViewCountyManager viewcountyManager = countyMangerService.getCountyManager(userId);
				model.addAttribute("role", 0);
				model.addAttribute("countyId", viewcountyManager.getCountyId());
				model.addAttribute("countyName", viewcountyManager.getCountyName());
			}
			return "Announcement";
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return "403";
		}
	}
	
	@ResponseBody
	@RequestMapping("SendAnnouncement/startSendAnnouncement")
	public String startSendAnnouncement(HttpServletRequest request) throws Exception
	{
		UserInfo userInfo;
		JsonResult jsonResult;
		try
		{
			userInfo = super.getCurrentUser(request);
			Integer userId = userInfo.getViewUser().getUserId();
			JSONObject announcementJson = JSONObject.fromObject(request.getParameter("annoument"));
			System.out.println(announcementJson.toString());
			announcementService.sendAnnoucement(announcementJson, userId);
			jsonResult = new JsonResult("succ",0,null,null);
			return jsonResult.toJsonString();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			jsonResult = new JsonResult("fail",0,null,null);
			return jsonResult.toJsonString();
		}
	}
	
	@RequestMapping("RecvAnnouncement")
	public String recvAnnouncement()
	{
		return "RecvAnnouncement";
	}
	
	@ResponseBody
	@RequestMapping("RecvAnnouncement/getAllRecvAnnouncement")
	public String getAllRecvAnnouncement(HttpServletRequest request,Integer pageNumber,Integer pageSize) throws Exception
	{
		UserInfo userInfo;
		JsonResult result;
		try
		{
			userInfo = super.getCurrentUser(request);
			Integer userId = userInfo.getViewUser().getUserId();
			Page page = announcementService.getMyRecvAnnouncement(userId,pageNumber,pageSize);
			if (page.getResult() == null)
				result = new JsonResult("succ", 0, new ArrayList<ViewNoticeAccept>(), null);
			else {
				List<ViewNoticeAccept> notices = (List<ViewNoticeAccept>)page.getResult();
				result = new JsonResult("succ", (int)page.getTotalCount(), notices, null);
			}
			return result.toJsonString();
		}
		catch(Exception e)
		{
			JsonResult jsonResult = new JsonResult("fail",0,null,e.getMessage());
			return jsonResult.toJsonString();
		}
	}
	
	@RequestMapping("LookAnnouncement/{noticeid}")
	public String lookAnnouncement(@PathVariable Integer noticeid,Model model,HttpServletRequest request)
	{
		System.out.println(noticeid);
		model.addAttribute("notice",sendNoticeService.getNotice(noticeid));
		//UserInfo userInfo;
		try
		{
			//userInfo = super.getCurrentUser(request);
			//Reply reply = sendNoticeService.checkNoticeReply(userInfo.getViewUser().getUserId(), noticeid);
			/*
			if(sendNoticeService.checkNoticeIsMine(noticeid, userInfo.getViewUser().getUserId()))
			{
				List<ReplyForm> replys = sendNoticeService.getReplyforNotice(noticeid);
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
			}*/
			//if(reply != null)
				//model.addAttribute("myReply", reply);
			return "LookAnnouncement";
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return "LookAnnouncement";
		}
	}
	
}
