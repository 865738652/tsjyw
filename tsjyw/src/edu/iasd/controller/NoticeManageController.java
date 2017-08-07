package edu.iasd.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.io.FileUtils;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.demonstration.hibernate.dao.support.Page;

import edu.iasd.form.AttachVO;
import edu.iasd.form.NoticeForm;
import edu.iasd.form.NoticeReceiver;
import edu.iasd.form.ReplyForm;
import edu.iasd.pojo.Attachment;
import edu.iasd.pojo.County;
import edu.iasd.pojo.Reply;
import edu.iasd.pojo.Role;
import edu.iasd.pojo.School;
import edu.iasd.pojo.ViewNotice;
import edu.iasd.pojo.ViewNoticeAccept;
import edu.iasd.pojo.ViewSchool;
import edu.iasd.security.NotLoginException;
import edu.iasd.security.UserInfo;
import edu.iasd.service.CountyService;
import edu.iasd.service.SchoolService;
import edu.iasd.service.SendNoticeService;
import edu.iasd.service.UserService;
import edu.iasd.utils.JsonHelper;


@Controller
public class NoticeManageController extends ControllerBase
{
	

	
	@Autowired
	private UserService userService;
	
	@Autowired
	private SendNoticeService sendNoticeService;
	
	@RequestMapping("SendNotice")
	public String SendNotice(HttpServletRequest request,Model model) throws Exception
	{
		try{
			UserInfo userInfo = super.getCurrentUser(request);
			Set<Integer> roles = userService.getUserRoles(userInfo.getViewUser().getUserId());
			model.addAttribute("userRoles", roles);
			return "SendNotice";
		}
		catch(Exception e){
			return "403";
		}
		
	}
	
	@ResponseBody
	@RequestMapping("SendNotice/getReceiverTree")
	public String getReceiverTree(HttpServletRequest request) throws Exception
	{
		try{
			List<NoticeReceiver> recv = sendNoticeService.getNoticeReceiver(super.getCurrentUser(request).getViewUser().getUserId());
			JsonResult jsonResult = new JsonResult("succ", 0, null, recv);
			//JsonResult jsonResult = new JsonResult("succ",0,null,JSONArray.fromObject(sendNoticeService.getTheSelect2School(super.getCurrentUser(request).getViewUser().getUserId())));
			return jsonResult.toJsonString();
		}
		catch(Exception e){
			JsonResult jsonResult = new JsonResult("fail",0,null,e.getMessage());
			return jsonResult.toJsonString();
		}
	}
	
	@ResponseBody
	@RequestMapping("SendNotice/getSchool")
	public String getSchool(HttpServletRequest request) throws Exception
	{
		try{
			JsonResult jsonResult = new JsonResult("succ",0,null,JSONArray.fromObject(sendNoticeService.getTheSelect2School(super.getCurrentUser(request).getViewUser().getUserId())));
			return jsonResult.toJsonString();
		}
		catch(Exception e){
			JsonResult jsonResult = new JsonResult("fail",0,null,e.getMessage());
			return jsonResult.toJsonString();
		}
	}
	
	@ResponseBody
	@RequestMapping("SendNotice/getGrade/{schoolid}")
	public String getGrade(@PathVariable int schoolid,HttpServletRequest request) throws Exception
	{
		try {
			UserInfo userInfo = super.getCurrentUser(request);
			JsonResult jsonResult = new JsonResult("succ",0,null,JSONArray.fromObject(sendNoticeService.getTheSelect2Grade(userInfo.getViewUser().getUserId(),schoolid)));
			return jsonResult.toJsonString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JsonResult jsonResult = new JsonResult("fail",0,null,e.getMessage());
			return jsonResult.toJsonString();
		}
		
	}
	
	@ResponseBody
	@RequestMapping("SendNotice/getClass/{gradeid}")
	public String getClass(@PathVariable int gradeid,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		UserInfo userInfo;
		try {
			userInfo = super.getCurrentUser(request);
			System.out.println("controller sendnotice getClass gradeid" + gradeid);
			
			//将前台传过来的json格式的字符串转换为List格式
			JSONObject jsStr = JSONObject.fromObject(request.getParameter("names")); //将字符串{“id”：1}
			List<Integer> gradeIds = new ArrayList();
	        Iterator iterator = jsStr.keys();
			while(iterator.hasNext()){
			        String key = (String) iterator.next();
			        gradeIds.add(Integer.parseInt(jsStr.getString(key)));
			}
			System.out.println(gradeIds.size());
			JsonResult jsonResult = new JsonResult("succ",0,null,JSONArray.fromObject(sendNoticeService.getTheSelect2Classes(userInfo.getViewUser().getUserId(),gradeIds)));
			return jsonResult.toJsonString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JsonResult jsonResult = new JsonResult("fail",0,null,e.getMessage());
			return jsonResult.toJsonString();
		}
	}
	
	@ResponseBody
	@RequestMapping("SendNotice/sendNotice")
	public String sendNotice(HttpServletRequest request,HttpServletResponse response, NoticeForm form) throws Exception
	{
		System.out.println(form.toString());
		UserInfo userInfo;
		try {
			userInfo = super.getCurrentUser(request);
			sendNoticeService.sendNotice(userInfo.getViewUser().getUserId(),form);
			JsonResult jsonResult = new JsonResult("succ",0,null,null);
			return jsonResult.toJsonString();
		} catch (Exception e) {
			// TODO Auto-generated catch block                                               
			JsonResult jsonResult = new JsonResult("fail",0,null,e.getMessage());
			return jsonResult.toJsonString();
		}
	}
	
	@RequestMapping("RecvNotice")
	public String recvNotice(HttpServletRequest request,HttpServletResponse response,Model model) throws NotLoginException
	{
		UserInfo userInfo = super.getCurrentUser(request);
		System.out.println("现在获取你的全部角色");
		Set<Integer> roles = userService.getUserRoles(userInfo.getViewUser().getUserId());
		model.addAttribute("userRoles", roles);
		return "RecvNotice";
	}
	
	@ResponseBody
	@RequestMapping("RecvNotice/getAllRecvNotice")
	public String getAllRecvNotice(HttpServletRequest request,HttpServletResponse respose,int pageNumber,int pageSize) throws Exception
	{
		UserInfo userInfo;
		try{
			userInfo = super.getCurrentUser(request);
			Page page = sendNoticeService.getAllMyRecvNotice(userInfo.getViewUser().getUserId(), pageNumber, pageSize);
			JsonResult result; 
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
	
	@RequestMapping("LookNotice/{noticeid}")
	public String LookNitoce(@PathVariable Integer noticeid,Model model,HttpServletRequest request)
	{
		//System.out.println("controller LookNotice "+noticeid);
		
		
		model.addAttribute("notice",sendNoticeService.getNotice(noticeid));
		
		UserInfo userInfo;
		try
		{
			userInfo = super.getCurrentUser(request);
			Reply reply = sendNoticeService.checkNoticeReply(userInfo.getViewUser().getUserId(), noticeid);
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
			}
			if(reply != null)
				model.addAttribute("myReply", reply);
			return "LookNotice";
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return "LookNotice";
		}
	}
	
	@RequestMapping("LookNotice/LookNoticeReplyDetial")
	public String lookNoticeReplyDetial(@RequestParam Integer replyId,Model model)
	{
		Reply reply = sendNoticeService.getReplyById(replyId);
		model.addAttribute("reply", reply);
		return "LookNoticeReply";
	}
	
	
	@ResponseBody
	@RequestMapping("LookNotice/addNoticeReplyContent")
	public String addNoticeReplyContent(Integer replyId,String replyContent,HttpServletRequest request) throws Exception
	{
		JsonResult jsonResult;
		try
		{
			
			sendNoticeService.addNoticeReplyContent(replyId, replyContent);
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
	@RequestMapping("LookNotice/getNoticeAttachment")
	public String getNoticeAttachment(@RequestParam Integer noticeId,int pageSize,int pageNumber) throws Exception
	{
		try
		{
			List<AttachVO> attachments = sendNoticeService.getNoticeAttachment(noticeId, pageSize, pageNumber);
			JsonResult result;
			if (attachments == null || attachments.size() == 0)
				result = new JsonResult("succ",0,new ArrayList<AttachVO>(), null);
			else 
				result = new JsonResult("succ",attachments.size(),attachments, null);
			return result.toJsonString();
		}
		catch(Exception e)
		{
			JsonResult jsonResult = new JsonResult("fail",0,null,e.getMessage());
			return jsonResult.toJsonString();
		}
		
	}
	
	@ResponseBody
	@RequestMapping("RecvNotice/RecvGetMyRole")
	public String recvGetMyRole(HttpServletRequest request,Model model) throws Exception
	{
		UserInfo userInfo;
		try
		{
			userInfo = super.getCurrentUser(request);
			System.out.println("现在获取你的全部角色");
			Set<Integer> roles = userService.getUserRoles(userInfo.getViewUser().getUserId());
			model.addAttribute("userRoles", roles);
			JsonResult jsonResult = new JsonResult("succ",0,null,roles);
			return jsonResult.toJsonString();
		}
		catch(Exception e)
		{
			return new JsonResult("fail",0,null,e.getMessage()).toJsonString();
		}
	}
	
	@RequestMapping("LookNotice/downloadAttachment/{attachmentId}")
	public ResponseEntity<byte[]> downAttachment(@PathVariable Integer attachmentId,HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		String realPath = request.getSession().getServletContext().getRealPath("/");
		
		String attachmentUrl = realPath+"WEB-INF/upload/";
		
		Attachment attachment = sendNoticeService.getAttachment(attachmentId);
		String dfileName = new String(attachment.getAttachmentName().getBytes("gb2312"), "iso8859-1");
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		headers.setContentDispositionFormData("attachment",dfileName);
		return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(new File(attachmentUrl+attachment.getAttachmentUrl())),headers,HttpStatus.CREATED);
	}
	
	
	//已发通知
	@RequestMapping("SendedNotice")
	public String sendedNotice()
	{
		return "SendedNotice";
	}
	
	
	@ResponseBody
	@RequestMapping("SendedNotice/getAllSendedNotice")
	public String getAllSendedNotice(HttpServletRequest request,int pageSize,int pageNumber) throws Exception
	{
		UserInfo userInfo;
		try
		{
			userInfo = super.getCurrentUser(request);
			Page page = sendNoticeService.getAllSendedNotice(userInfo.getViewUser().getUserId(), pageNumber, pageSize);
			JsonResult result; 
			if (page.getResult() == null)
				result = new JsonResult("succ", 0, new ArrayList<ViewNotice>(), null);
			else {
				List<ViewNotice> notices = (List<ViewNotice>)page.getResult();
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
	
	@ResponseBody
	@RequestMapping(value="deleteNotice")
	public String deleteNotice(@RequestParam Integer noticeId,HttpServletRequest request) throws Exception
	{
		UserInfo userInfo;
		try
		{
			userInfo = super.getCurrentUser(request);
			JsonResult result;
			if(sendNoticeService.deleteMyNotice(noticeId, userInfo.getViewUser().getUserId()))
				result = new JsonResult("succ",0,null,null); 
			else
				result = new JsonResult("fail",0,null,"删除失败");
			return result.toJsonString();
		}
		catch(Exception e)
		{
			JsonResult jsonResult = new JsonResult("fail",0,null,e.getMessage());
			return jsonResult.toJsonString();
		}
	}
	
}
