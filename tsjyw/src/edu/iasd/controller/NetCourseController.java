package edu.iasd.controller;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;














import com.demonstration.hibernate.dao.support.Page;

import edu.iasd.form.NetCourseForm;
import edu.iasd.form.VolunteerForm;
import edu.iasd.pojo.GoodsState;
import edu.iasd.pojo.NetCourseType;
import edu.iasd.pojo.SchoolType;
import edu.iasd.pojo.User;
import edu.iasd.pojo.ViewNetCourse;
import edu.iasd.service.NetCourseService;



@Controller
public class NetCourseController {
	
	@Autowired
	private NetCourseService netcourseService;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) throws Exception {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    dateFormat.setLenient(false);
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));//true:���������ֵ��false:����Ϊ��ֵ
	}
	
	@RequestMapping("NetCourse")
	public String ShowAll(){
		return "NetCourse";
	}
	//�õ���Ƶ��Ϣ
	@ResponseBody
	@RequestMapping(value="NetCourse/getNetCourse")
	public String getNetCourse(int netCourseId) throws Exception {
		try {
			ViewNetCourse netCourse = netcourseService.getNetCourse(netCourseId);
			
			JsonResult result = new JsonResult("succ", 0, null, netCourse);
			return result.toJsonString();
		}
		catch (Exception e) {
			JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			return result.toJsonString();
		}
	 } 
	//�õ�ȫ����Ƶ�γ�
	@ResponseBody
	@RequestMapping(value={"NetCourse/getAllNetCourse"})
	public String getAllNetCourse(int pageNumber, int pageSize) throws Exception {
		try {
			Page page = netcourseService.getAllNetCourse(pageNumber, pageSize);
			JsonResult result;
			if (page.getResult() == null)
				result = new JsonResult("succ", 0, new ArrayList<ViewNetCourse>(), null);
			else {
				List<ViewNetCourse> netcourse = (List<ViewNetCourse>)page.getResult();
				result = new JsonResult("succ", (int)page.getTotalCount(), netcourse, null);
			}
			return result.toJsonString();
		}
		catch (Exception e) {
			JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			return result.toJsonString();
		}
	}
	
	@ResponseBody
	@RequestMapping(value={"NetCourse/getNetCourseByTeacher", "Index/getNetCourseByTeacher"})
	public String getNetCourseByTeacher(int pageNumber, int pageSize, int userId) throws Exception {
		try {
			System.out.println("111111111");
			Page page = netcourseService.getAllNetCourse(pageNumber, pageSize);
			JsonResult result;
			if (page.getResult() == null)
				result = new JsonResult("succ", 0, new ArrayList<ViewNetCourse>(), null);
			else {
				List<ViewNetCourse> netcourse = (List<ViewNetCourse>)page.getResult();
				result = new JsonResult("succ", (int)page.getTotalCount(), netcourse, null);
			}
			return result.toJsonString();
		}
		catch (Exception e) {
			JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			return result.toJsonString();
		}
	}
	//�����Ƶ
	@ResponseBody
	@RequestMapping(value="NetCourse/createNetCourse")
	public String createNetCourse(NetCourseForm netCourseForm) throws Exception{
		try{
			
			String id = netcourseService.createNetCourse(netCourseForm);
			if(id !=null){
				JsonResult result =new JsonResult("succ", 0, null , id);
				return result.toJsonString();
			}else
				throw new Exception("�����Ƶʧ��");
		}catch(Exception e){
			JsonResult result = new JsonResult("fail", 0, null , e.getMessage());
			return result.toJsonString();
		}
	}
	
	//ɾ����Ƶ
	 @ResponseBody
	 @RequestMapping(value="NetCourse/deleteNetCourse")
	 public String deleteNetCourse(int netCourseId) throws Exception
	 {
		 try {
			 
			 netcourseService.deleteNetCourse(netCourseId);
			
			JsonResult result = new JsonResult("succ", 0, null, null);
			return result.toJsonString();
		}
		catch (Exception e) {
			JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			return result.toJsonString();
		}		 
	 }
	 
	//�޸���Ƶ
	 @ResponseBody
	 @RequestMapping(value="NetCourse/modifyNetCourse")
	 public String modifyNetCourse(NetCourseForm netCourseForm) throws Exception
	 {
		 try {
			 System.out.println("qqqqqqqqqqqqq");
			 if (netcourseService.modifyNetCourse(netCourseForm)) {			
				 JsonResult result = new JsonResult("succ", 0, null, null);
				 return result.toJsonString();
			 }
			 else
				 throw new Exception("�޸���Ƶʧ��");
		 }
		 catch (Exception e) {
			 JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			 return result.toJsonString();
		}		
	 }
	 //�õ���Ƶ����
	 @ResponseBody
	@RequestMapping(value="NetCourse/getNetCourseType")
		public String getNetCourseType() throws Exception {
			try {
				List<NetCourseType> types = netcourseService.getNetCourseType();
				JsonResult result = new JsonResult("succ", types.size(), types, null);
				return result.toJsonString();
			}
			catch (Exception e) {
				JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
				return result.toJsonString();
			}
		}
}
