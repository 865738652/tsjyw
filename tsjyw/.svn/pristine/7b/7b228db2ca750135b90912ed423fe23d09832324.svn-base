package edu.iasd.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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

import com.baidu.ueditor.PathFormat;
import com.baidu.ueditor.define.FileType;
import com.demonstration.hibernate.dao.support.Page;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.iasd.form.AttachVO;
import edu.iasd.form.TeacherForm;
import edu.iasd.pojo.TeacherState;
import edu.iasd.pojo.ViewTeacher;
import edu.iasd.search.QueryModel;
import edu.iasd.search.SearchParam;
import edu.iasd.service.TeacherService;

@Controller
public class TeacherManageController {
		
	@Autowired
	private TeacherService teacherService;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) throws Exception {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    dateFormat.setLenient(false);
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));//true:允许输入空值，false:不能为空值
	}
	
	@RequestMapping("TeacherManage")
	public String showAll()	{
		return "TeacherManage";
	}	
	
	@RequestMapping("TeacherManage/{schoolid}")
	public String showTeacherBySchool(@PathVariable int schoolid,Model model) {
		model.addAttribute("schoolid",schoolid);
		return "TeacherManage";
	}
	
	@ResponseBody
	@RequestMapping(value="TeacherManage/getTeacherBySchool")
	public String getTeacherBySchool(int schoolId, int pageNumber, int pageSize, @SearchParam QueryModel queryModel) throws Exception {
		try {
			Page page = teacherService.getTeacherBySchool(pageNumber, pageSize, schoolId, queryModel.toQueryString());
			JsonResult result;
			if (page.getResult() == null)
				result = new JsonResult("succ", 0, new ArrayList<ViewTeacher>(), null);
			else {
				List<ViewTeacher> teachers = (List<ViewTeacher>)page.getResult();
				result = new JsonResult("succ",(int)page.getTotalCount(), teachers, null);
			}
			ObjectMapper objectMapper = new ObjectMapper();
			String jsonString = objectMapper.writeValueAsString(result);
			System.out.println(jsonString);
			return jsonString;
		}
		catch (Exception e) {
			JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			ObjectMapper objectMapper = new ObjectMapper();
	        String jsonString = objectMapper.writeValueAsString(result);
	        System.out.println(jsonString);
	        return jsonString;
		}
	}
	
	@ResponseBody
	@RequestMapping(value="TeacherManage/getTeacher")
	public String getTeacher(int teacherId) throws Exception {
		try {
			ViewTeacher teacher = teacherService.getTeacher(teacherId);
			System.out.println("get teacher: "+teacher.getUserNickname()); 
			
			JsonResult result = new JsonResult("succ", 0, null, teacher);
			ObjectMapper objectMapper = new ObjectMapper();
	        String jsonString = objectMapper.writeValueAsString(result);
	        System.out.println(jsonString);
	        return jsonString;
		}
		catch (Exception e) {
			JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			ObjectMapper objectMapper = new ObjectMapper();
	        String jsonString = objectMapper.writeValueAsString(result);
	        System.out.println(jsonString);
	        return jsonString;
		}
	 } 
	
	 @ResponseBody
	 @RequestMapping(value="TeacherManage/selectTeacher")
	 public String selectTeacher(int userId, int schoolId) throws Exception
	 {
		 try {
			 String id = teacherService.selectTeacher(userId, schoolId);
			 if ( id != null) {			
				 JsonResult result = new JsonResult("succ", 0, null, id);
				 return result.toJsonString();
			 }
			 else
				 throw new Exception("调入教师失败");
		 }
		 catch (Exception e) {
			 JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			 return result.toJsonString();
		}		
	 }
	
	 @ResponseBody
	 @RequestMapping(value="TeacherManage/createTeacher")
	 public String createTeacher(TeacherForm teacher) throws IOException
	 {
		 try {
			 String id = teacherService.createTeacher(teacher);
			 if ( id != null) {			
				 JsonResult result = new JsonResult("succ", 0, null, id);
				 ObjectMapper objectMapper = new ObjectMapper();
				 String jsonString = objectMapper.writeValueAsString(result);
				 System.out.println(jsonString);
				 return jsonString;
			 }
			 else
				 throw new Exception("创建教师失败");
		 }
		 catch (Exception e) {
			 JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			 ObjectMapper objectMapper = new ObjectMapper();
			 String jsonString = objectMapper.writeValueAsString(result);
			 System.out.println(jsonString);
			 return jsonString;
		}		
	 }
	 
	 @ResponseBody
	 @RequestMapping(value="TeacherManage/deleteTeacher")
	 public String deleteTeacher(int teacherId) throws IOException
	 {
		 try {
			teacherService.deleteTeacher(teacherId);
			
			JsonResult result = new JsonResult("succ", 0, null, null);
			ObjectMapper objectMapper = new ObjectMapper();
	        String jsonString = objectMapper.writeValueAsString(result);
	        System.out.println(jsonString);
	        return jsonString;
		}
		catch (Exception e) {
			JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			ObjectMapper objectMapper = new ObjectMapper();
	        String jsonString = objectMapper.writeValueAsString(result);
	        System.out.println(jsonString);
	        return jsonString;
		}		 
	 }
	 
	 @ResponseBody
	 @RequestMapping(value="TeacherManage/modifyTeacher")
	 public String modifyTeacher(TeacherForm teacher) throws IOException
	 {
		 try {
			 if (teacherService.modifyTeacher(teacher)) {			
				 JsonResult result = new JsonResult("succ", 0, null, null);
				 ObjectMapper objectMapper = new ObjectMapper();
				 String jsonString = objectMapper.writeValueAsString(result);
				 System.out.println(jsonString);
				 return jsonString;
			 }
			 else
				 throw new Exception("修改教师失败");
		 }
		 catch (Exception e) {
			 JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			 ObjectMapper objectMapper = new ObjectMapper();
			 String jsonString = objectMapper.writeValueAsString(result);
			 System.out.println(jsonString);
			 return jsonString;
		}		
	 }
	 
	@ResponseBody
	@RequestMapping(value="TeacherManage/getTeacherState")
	public String getTeacherState() throws Exception {
		try {
			List<TeacherState> states = teacherService.getTeacherState();
			JsonResult result = new JsonResult("succ", states.size(), states, null);
			ObjectMapper objectMapper = new ObjectMapper();
			String jsonString = objectMapper.writeValueAsString(result);
			System.out.println(jsonString);
			return jsonString;
		}
		catch (Exception e) {
			JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			ObjectMapper objectMapper = new ObjectMapper();
	        String jsonString = objectMapper.writeValueAsString(result);
	        System.out.println(jsonString);
	        return jsonString;
		}
	}
	
	 @ResponseBody
	 @RequestMapping(value="TeacherManage/cancelTeacher")
	 public String cancelTeacher(int teacherId) throws Exception
	 {
		 try {
			 teacherService.cancelTeacher(teacherId);
			
			JsonResult result = new JsonResult("succ", 0, null, null);
			return result.toJsonString();
		}
		catch (Exception e) {
			JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			return result.toJsonString();
		}		 
	 }
	 
	 @ResponseBody
	 @RequestMapping(value="TeacherManage/importExcel")
	 public String importExcel(HttpServletRequest request)
	 {
		 try
		 {
			 String realPath = request.getSession().getServletContext().getRealPath("/")+"WEB-INF/upload/";
			 
			 String attachstring = request.getParameter("attach");
			 Integer schoolId = Integer.parseInt(request.getParameter("schoolId"));
			 JSONObject attachjson = JSONObject.fromObject(attachstring);
			 AttachVO attach = new AttachVO();
			 attach.setAttachName(attachjson.getString("attachName"));
			 attach.setAttachSize((float) attachjson.getInt("attachSize"));
			 attach.setAttachUrl(attachjson.getString("attachUrl"));
			 List<Integer> userIds = teacherService.importExcel(attach,schoolId,realPath);
			 JsonResult jsonResult = new JsonResult("succ",userIds.size(),null,userIds);
			 return jsonResult.toJsonString();
		 }
		 catch(Exception e)
		 {
			 e.printStackTrace();
			 JsonResult jsonResult = new JsonResult("fail",0,null,e.getMessage());
			 try {
				return jsonResult.toJsonString();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				return null;
			}
		 }
		 
	 }
	 
	 
	 
	 @RequestMapping("TeacherManage/downLoadTeacherExcel")
	 public ResponseEntity<byte[]> downLoadExcel(HttpServletRequest request) throws Exception
	 {
		 JSONArray jsonArray = JSONArray.fromObject(request.getParameter("userIds").toString());
		 List<Integer> userIds = new ArrayList<Integer>();
		 for(int i=0;i<jsonArray.size();i++)
		 {
			 userIds.add(jsonArray.getInt(i));
		 }
		 
		 
		 String savePath = "WEB-INF/upload/{yyyy}{mm}{dd}/{time}{rand:6}";
		 
		 String originFileName = "test.xls";
		 String suffix = FileType.getSuffixByFilename(originFileName);
		 originFileName = originFileName.substring(0,
				originFileName.length() - suffix.length());
		 savePath = savePath + suffix;

		 savePath = PathFormat.parse(savePath, originFileName);
		 String realPath = request.getSession().getServletContext().getRealPath("/");
		 String physicalPath = realPath + savePath;
		 
		 AttachVO attachVo = teacherService.getTeacherExcel(userIds,physicalPath);
		 
		 HttpHeaders headers = new HttpHeaders();
		 File file = new File(attachVo.getAttachUrl());
		 headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		 headers.setContentDispositionFormData("attachment", file.getName());
		 return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),headers,HttpStatus.CREATED);	
	 }
	 
	 @RequestMapping("TeacherManage/downloadTeacher")
	 public ResponseEntity<byte[]> downloadTeacherExcel(@RequestParam(value="schoolid",required=false)Integer schoolid,HttpServletRequest request) throws Exception
	 {
		 String savePath = "WEB-INF/upload/{yyyy}{mm}{dd}/{time}{rand:6}";
		 String originFileName = "test.xls";
		 String suffix = FileType.getSuffixByFilename(originFileName);
		 originFileName = originFileName.substring(0,originFileName.length() - suffix.length());
		 savePath = savePath + suffix;
		 savePath = PathFormat.parse(savePath, originFileName);
		 String realPath = request.getSession().getServletContext().getRealPath("/");
		 String physicalPath = realPath + savePath;
		 System.out.println(physicalPath);
		 
		 AttachVO a = teacherService.getAllExcelfromTeacher(schoolid, physicalPath);
		 
		 File file = new File(a.getAttachUrl());
		 
		 HttpHeaders headers = new HttpHeaders();
		
		 headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		 headers.setContentDispositionFormData("attachment", new String( "教师导出.xls".getBytes("gbk"),"iso-8859-1"));
		 return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),headers,HttpStatus.CREATED);	
	 }
	 /*
	 public ResponseEntity<byte[]> downLoadExcel(@PathVariable String attachUrl)
	 {
		 try
		 {
			 HttpHeaders headers = new HttpHeaders();
			 File file = new File(attachUrl);
			 headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			 headers.setContentDispositionFormData("attachment", file.getName());
			 return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),headers,HttpStatus.CREATED);			 
		 }
		 catch(Exception e)
		 {
			 return null;
		 }
	 }*/
}
