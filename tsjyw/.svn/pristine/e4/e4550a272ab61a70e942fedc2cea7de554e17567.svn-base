package edu.iasd.service;

import java.io.File;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demonstration.hibernate.dao.support.Page;

import edu.iasd.pojo.TeacherState;
import edu.iasd.pojo.ViewTeacher;
import edu.iasd.form.AttachVO;
import edu.iasd.form.TeacherForm;

@Service("teacherService") 
@Transactional
public interface TeacherService {
	public Page getAllTeacher(int pageNow,int pageSize);
	
	public Page getTeacherBySchool(int pageNow,int pageSize,int schoolid, String queryString);

	public ViewTeacher getTeacher(int teacherid);
	
	public String selectTeacher(int userId, int schoolId) throws Exception;
	
	public String createTeacher(TeacherForm teacher) throws Exception;
	
	public boolean deleteTeacher(int teacherid);
	
	public boolean cancelTeacher(int teacherId);
	
	public boolean modifyTeacher(TeacherForm teacher) throws Exception;
	
	public List<TeacherState> getTeacherState();
	
	public List<Integer> importExcel(AttachVO attachVo,Integer schoolId,String realPath) throws Exception;
	
	public AttachVO getTeacherExcel(List<Integer> userId,String physicalPath);

	public AttachVO getAllExcelfromTeacher(Integer schoolId, String physicalPath)throws Exception;

	
	
}
