package edu.iasd.service;

import java.io.File;
import java.io.InputStream;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demonstration.hibernate.dao.support.Page;
import edu.iasd.form.StudentForm;
import edu.iasd.pojo.StudentState;
import edu.iasd.pojo.ViewStudent;

@Service("studentService") 
@Transactional
public interface StudentService {
	
	public Page getStudentBySchoolClass(int pageNow,int pageSize,int schoolClassId, String queryString);
	
	public Page getAllStudent(int pageNow,int pageSize);

	public ViewStudent getStudent(int studentId);
	
	public String selectStudent(int userId, int schoolClassId) throws Exception;
	
	public void goUpStudent(int schoolClassId, int newSchoolClassId);
	
	public String createStudent(StudentForm student) throws Exception;
	
	public boolean deleteStudent(int studentId);
	
	public boolean cancelStudent(int studentId);
	
	public boolean graduatedStudent(int schoolClassId);
	
	public boolean modifyStudent(StudentForm student) throws Exception;
	
	public List<StudentState> getStudentState();

	public List<Integer> getStudentforExcel(Integer schoolClassId,InputStream inputStream) throws Exception;

	public File getExcelforStudent(List<Integer> userIds,String physicalPath) throws Exception;
	
	public List<Integer> getSchoolStudentforExcel(Integer schoolId,InputStream inputStream) throws Exception;
	
	public File getAllExcelfromStudent(Integer classIds, String physicalPath) throws Exception;
	
	public File getSchoolExcelfromStudent(Integer schoolId, String physicalPath) throws Exception;
}
