package edu.iasd.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.http.HttpHeaders;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baidu.ueditor.PathFormat;
import com.baidu.ueditor.define.FileType;
import com.demonstration.hibernate.dao.support.Page;

import edu.iasd.form.AttachVO;
import edu.iasd.form.SchoolForm;
import edu.iasd.pojo.SchoolType;
import edu.iasd.pojo.ViewSchool;
import edu.iasd.search.QueryModel;
import edu.iasd.search.SearchParam;
import edu.iasd.security.UserInfo;
import edu.iasd.service.SchoolService;
import edu.iasd.service.StudentService;


@Controller 
public class SchoolManageController extends ControllerBase {
	
	@Autowired
	private SchoolService schoolService;
	
	@Autowired
	private StudentService studentService;
	
	@RequestMapping("SchoolManage")
	public String showAllSchool(Model model)
	{		
		model.addAttribute("bycounty", false);
		model.addAttribute("countyid", -1);
		return "SchoolManage";
	}
		
	@RequestMapping("SchoolManage/{countyid}")
	public String showSchoolByCounty(@PathVariable int countyid,Model model)
	{
		model.addAttribute("bycounty", true);
		model.addAttribute("countyid", countyid);
		return "SchoolManage";
	}
	
	@ResponseBody
	@RequestMapping(value="SchoolManage/getAllSchool")
	public String getAllSchool(HttpServletRequest request, int pageNumber,int pageSize, @SearchParam QueryModel queryModel) throws Exception {
		try{
			UserInfo curUser = super.getCurrentUser(request);
			Page page=schoolService.getAllSchool(curUser.getViewUser().getUserId(), queryModel.toQueryString(), pageNumber, pageSize);
			JsonResult result;
			if (page.getResult() == null)
				result = new JsonResult("succ", 0, new ArrayList<ViewSchool>(), null);
			else {
				List<ViewSchool> schools = (List<ViewSchool>)page.getResult();
				result = new JsonResult("succ", (int)page.getTotalCount(), schools, null);
			}
			return result.toJsonString();
		}
		catch (Exception e) {
			JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			return result.toJsonString();
		}
	}
	
	@ResponseBody
	@RequestMapping(value="SchoolManage/getSchoolByCounty")
	public String getSchoolByCounty(int countyId,int pageNumber,int pageSize, @SearchParam QueryModel queryModel) throws Exception {
		try{
			Page page=schoolService.getSchoolByCounty(pageNumber, pageSize, countyId, queryModel.toQueryString());
			JsonResult result;
			if (page.getResult() == null)
				result = new JsonResult("succ", 0, new ArrayList<ViewSchool>(), null);
			else {
				List<ViewSchool> schools = (List<ViewSchool>)page.getResult();
				result = new JsonResult("succ", (int)page.getTotalCount(), schools, null);
			}
			return result.toJsonString();
		}
		catch (Exception e) {
			JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			return result.toJsonString();
		}
	}
	
	@ResponseBody
	@RequestMapping(value="SchoolManage/getSchool")
	public String getSchool(int schoolId) throws Exception {
		try {
			ViewSchool school = schoolService.getSchool(schoolId);	
			JsonResult result = new JsonResult("succ", 0, null, school);
			return result.toJsonString();
		}
		catch (Exception e) {
			JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			return result.toJsonString();
		}
	 } 
	
	@ResponseBody
	 @RequestMapping(value="SchoolManage/createSchool")
	 public String createSchool(SchoolForm school) throws Exception
	 {
		 try {
			 String id = schoolService.createSchool(school);
			 if ( id != null) {			
				 JsonResult result = new JsonResult("succ", 0, null, id);
				 return result.toJsonString();
			 }
			 else
				 throw new Exception("新建学校失败");
		 }
		 catch (Exception e) {
			 JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			 return result.toJsonString();
		}		
	 }
	
	 @ResponseBody
	 @RequestMapping(value="SchoolManage/modifySchool")
	 public String modifySchool(SchoolForm school) throws Exception
	 {
		 try {
			 if (schoolService.modify(school)) {			
				 JsonResult result = new JsonResult("succ", 0, null, null);
				 return result.toJsonString();
			 }
			 else
				 throw new Exception("修改学校失败");
		 }
		 catch (Exception e) {
			 JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			 return result.toJsonString();
		}		
	 }
	
	@ResponseBody
	 @RequestMapping(value="SchoolManage/deleteSchool")
	 public String deleteSchool(int schoolId) throws Exception
	 {
		 try {
			 schoolService.deleteSchool(schoolId);
			
			 JsonResult result = new JsonResult("succ", 0, null, null);
			 return result.toJsonString();
		}
		catch (Exception e) {
			JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			return result.toJsonString();
		}		 
	 }
	
	@ResponseBody
	@RequestMapping(value="SchoolManage/getSchoolType")
	public String getSchoolByCounty() throws Exception {
		try {
			List<SchoolType> types = schoolService.getSchoolType();
			JsonResult result = new JsonResult("succ", types.size(), types, null);
			return result.toJsonString();
		}
		catch (Exception e) {
			JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			return result.toJsonString();
		}
	}

	@RequestMapping("SchoolManage/downloadStudent")
	public ResponseEntity<byte[]> downloadStudent(@RequestParam(value="schoolid",required=false)Integer schoolid,HttpServletRequest request) throws Exception
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
		 
		 File file = studentService.getSchoolExcelfromStudent(schoolid, physicalPath);
		 HttpHeaders headers = new HttpHeaders();
		 headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		 headers.setContentDispositionFormData("attachment",new String( "学生导出.xls".getBytes("gbk"),"iso-8859-1"));
		 return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),headers,HttpStatus.CREATED);
	}
}
