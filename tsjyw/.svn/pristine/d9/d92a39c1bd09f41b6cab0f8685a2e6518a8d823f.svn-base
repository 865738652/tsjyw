package edu.iasd.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baidu.ueditor.PathFormat;
import com.baidu.ueditor.define.FileType;
import com.demonstration.hibernate.dao.support.Page;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.iasd.form.StudentForm;
import edu.iasd.pojo.StudentState;
import edu.iasd.pojo.ViewSchoolClass;
import edu.iasd.pojo.ViewStudent;
import edu.iasd.search.QueryModel;
import edu.iasd.search.SearchParam;
import edu.iasd.security.UserInfo;
import edu.iasd.service.SchoolClassService;
import edu.iasd.service.StudentService;

@Controller
public class StudentManageController extends ControllerBase{
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private SchoolClassService schoolClassService;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) throws Exception {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    dateFormat.setLenient(false);
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));//true:允许输入空值，false:不能为空值
	}
	
	
	@RequestMapping("StudentManage/{schoolClassId}")
	public String showStudentBySchoolClass(@PathVariable int schoolClassId,Model model) {
		ViewSchoolClass c = schoolClassService.getSchoolClass(schoolClassId);
		model.addAttribute("schoolClassid",schoolClassId);
		model.addAttribute("schoolId", c.getSchoolId());
		model.addAttribute("gradeid", c.getGradeId());
		return "StudentManage";
	}
	
	@ResponseBody
	@RequestMapping(value="StudentManage/getStudentBySchoolClass")
	public String getStudentBySchoolClass(int schoolClassId, int pageNumber, int pageSize, @SearchParam QueryModel queryModel) throws Exception {
		try {
			Page page = studentService.getStudentBySchoolClass(pageNumber, pageSize, schoolClassId, queryModel.toQueryString());
			JsonResult result;
			if (page.getResult() == null)
				result = new JsonResult("succ", 0, new ArrayList<ViewStudent>(), null);
			else {
				List<ViewStudent> Students = (List<ViewStudent>)page.getResult();
				result = new JsonResult("succ", (int)page.getTotalCount(), Students, null);
			}
			 return result.toJsonString();
		}
		catch (Exception e) {
			JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			 return result.toJsonString();
		}
	}
	
	@ResponseBody
	@RequestMapping(value="StudentManage/getStudent")
	public String getStudent(int studentId) throws Exception {
		try {
			ViewStudent vs = studentService.getStudent(studentId);
			JsonResult result = new JsonResult("succ", 0, null, vs);
			 return result.toJsonString();
		}
		catch (Exception e) {
			JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			 return result.toJsonString();
		}
	 } 
	
	
	 @ResponseBody
	 @RequestMapping(value="StudentManage/selectStudent")
	 public String selectStudent(int userId, int schoolClassId) throws Exception
	 {
		 try {
			 String id = studentService.selectStudent(userId, schoolClassId);
			 if ( id != null) {			
				 JsonResult result = new JsonResult("succ", 0, null, id);
				 return result.toJsonString();
			 }
			 else
				 throw new Exception("调入学生失败");
		 }
		 catch (Exception e) {
			 JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			 return result.toJsonString();
		}		
	 }
	
	 @ResponseBody
	 @RequestMapping(value="StudentManage/createStudent")
	 public String createStudent(StudentForm student) throws Exception
	 {
		 try {
			 String id = studentService.createStudent(student);
			 if ( id != null) {			
				 JsonResult result = new JsonResult("succ", 0, null, id);
				 return result.toJsonString();
			 }
			 else
				 throw new Exception("创建学生失败");
		 }
		 catch (Exception e) {
			 JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			 return result.toJsonString();
		}		
	 }
	 
	 @ResponseBody
	 @RequestMapping(value="StudentManage/deleteStudent")
	 public String deleteStudent(int studentId) throws Exception
	 {
		 try {
			 studentService.deleteStudent(studentId);
			
			JsonResult result = new JsonResult("succ", 0, null, null);
			 return result.toJsonString();
		}
		catch (Exception e) {
			JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			 return result.toJsonString();
		}		 
	 }
	 
	 @ResponseBody
	 @RequestMapping(value="StudentManage/modifyStudent")
	 public String modifyStudent(StudentForm student) throws Exception
	 {
		 try {
			 if (studentService.modifyStudent(student)) {			
				 JsonResult result = new JsonResult("succ", 0, null, null);
				 return result.toJsonString();
			 }
			 else
				 throw new Exception("修改学生失败");
		 }
		 catch (Exception e) {
			 JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			 return result.toJsonString();
		}		
	 }
	 
	 @ResponseBody
		@RequestMapping(value="StudentManage/getStudentState")
		public String getStudentState() throws Exception {
			try {
				List<StudentState> states = studentService.getStudentState();
				JsonResult result = new JsonResult("succ", states.size(), states, null);
				 return result.toJsonString();
			}
			catch (Exception e) {
				JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
				 return result.toJsonString();
			}
		}

	 @ResponseBody
	 @RequestMapping(value="StudentManage/cancelStudent")
	 public String cancelStudent(int studentId) throws Exception
	 {
		 try {
			 studentService.cancelStudent(studentId);
			
			JsonResult result = new JsonResult("succ", 0, null, null);
			return result.toJsonString();
		}
		catch (Exception e) {
			JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			return result.toJsonString();
		}		 
	 }
	 
	 @ResponseBody
	 @RequestMapping(value="StudentManage/goUpStudent")
	 public String goUpStudent(int schoolClassId, int newSchoolClassId) throws Exception
	 {
		 try {
			  studentService.goUpStudent(schoolClassId, newSchoolClassId);
			 		
				 JsonResult result = new JsonResult("succ", 0, null, null);
				 return result.toJsonString();
			
		 }
		 catch (Exception e) {
			 JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			 return result.toJsonString();
		}		
	 }
	 
	 @ResponseBody
	 @RequestMapping(value="StudentManage/graduatedStudent")
	 public String graduatedStudent(int schoolClassId) throws Exception
	 {
		 try {
			 studentService.graduatedStudent(schoolClassId);
			JsonResult result = new JsonResult("succ", 0, null, null);
			return result.toJsonString();
		}
		catch (Exception e) {
			JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			return result.toJsonString();
		}		 
	 }

	 @RequestMapping(value="StudentManage/downLoadStudentExcel")
	 public ResponseEntity<byte[]> downLoadStudentExcel(@RequestParam(value="userIds",required=false) String userids,HttpServletRequest request) throws Exception
	 {
		 
		 
		 JSONArray jsonArray = JSONArray.fromObject(userids);
		 List<Integer> userIds = new ArrayList<Integer>();
		 for(int i=0;i<jsonArray.size();i++)
		 {
			 userIds.add(jsonArray.getInt(i));
		 }
		 
		 String savePath = "WEB-INF/upload/{yyyy}{mm}{dd}/{time}{rand:6}";
		 String originFileName = "test.xls";
		 String suffix = FileType.getSuffixByFilename(originFileName);
		 originFileName = originFileName.substring(0,originFileName.length() - suffix.length());
		 savePath = savePath + suffix;
		 savePath = PathFormat.parse(savePath, originFileName);
		 String realPath = request.getSession().getServletContext().getRealPath("/");
		 String physicalPath = realPath + savePath;
		 
		 System.out.println(physicalPath);
		 File file = studentService.getExcelforStudent(userIds,physicalPath);
		 HttpHeaders headers = new HttpHeaders();
		 headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		 headers.setContentDispositionFormData("attachment",new String( "学生导出.xls".getBytes("gbk"),"iso-8859-1"));
		 return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),headers,HttpStatus.CREATED);
	 }
	 
	 @RequestMapping(value="StudentManage/downloadStudent")
	 public ResponseEntity<byte[]> downloadStudentExcel(@RequestParam(value="classid",required=false) Integer classid,HttpServletRequest request) throws Exception
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
		 
		 File file = studentService.getAllExcelfromStudent(classid,physicalPath);
		 
		 HttpHeaders headers = new HttpHeaders();
		 headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		 headers.setContentDispositionFormData("attachment",new String( "学生导出.xls".getBytes("gbk"),"iso-8859-1"));
		 return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),headers,HttpStatus.CREATED);
	 }
}