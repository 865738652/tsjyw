package edu.iasd.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;

import com.demonstration.hibernate.basedao.IBaseDao;

import edu.iasd.form.AttachVO;
import edu.iasd.pojo.Role;
import edu.iasd.pojo.School;
import edu.iasd.pojo.SchoolClass;
import edu.iasd.pojo.Student;
import edu.iasd.pojo.StudentState;
import edu.iasd.pojo.Teacher;
import edu.iasd.pojo.User;
import edu.iasd.pojo.ViewStudent;
import edu.iasd.pojo.ViewTeacher;

public class ExcelHelper {

	
	public static List<User> getTeachersForExcel(AttachVO attachvo,String realPath) throws Exception
	{
		System.out.println(attachvo.getAttachName()+","+attachvo.getAttachSize()+","+attachvo.getAttachUrl());
		
		File file = new File(realPath+attachvo.getAttachUrl());
		
		String prefix=file.getName().substring(file.getName().lastIndexOf(".")+1);
		if(!prefix.equals("xls"))
			throw new Exception("请上传正确格式的文件");
		
		
		//创建excel，读取文件内容
		HSSFWorkbook workbook = new HSSFWorkbook(FileUtils.openInputStream(file));
		//读取默认的第一个工作表
		HSSFSheet sheet = workbook.getSheetAt(0);
		int firstRowNum = sheet.getFirstRowNum();
		int lastRowNum = sheet.getLastRowNum();
		
		if(!sheet.getRow(firstRowNum).getCell(0).getStringCellValue().equals("姓名") || !sheet.getRow(firstRowNum).getCell(1).getStringCellValue().equals("身份证号")
				 || !sheet.getRow(0).getCell(2).getStringCellValue().equals("密码"))
			throw new Exception("文件格式不正确");
		
		
		List<User> users = new ArrayList<User>();
		HSSFCell cell;
		
		for(int i=firstRowNum+1;i<=lastRowNum;i++)
		{
			User user = new User();
			HSSFRow row = sheet.getRow(i);
			//int lastColumnNum = row.getLastCellNum();
			cell = row.getCell(0);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			user.setUserName(cell.getStringCellValue());
			cell = row.getCell(1);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			user.setUserIdentityCode(cell.getStringCellValue());
			cell = row.getCell(2);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			user.setUserPassword(cell.getStringCellValue());
			System.out.println("第"+i+"个用户,"+user.getUserName()+","+user.getUserIdentityCode()+","+user.getUserPassword());
			users.add(user);
		}
		return users;
	}
	
	public static AttachVO getTeacherExcel(List<ViewTeacher> viewTeachers,String physicalPath)
	{
		String[] title = {"序号","用户名","帐号","密码","学校","教师状态","登陆编号", "绑定微信"};
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet();
		HSSFRow firstRow = sheet.createRow(0);
		HSSFCell firstCell = null;
		for(int i=0;i<title.length;i++)
		{
			firstCell = firstRow.createCell(i);
			firstCell.setCellValue(title[i]);
		}
		for(int i=1;i<=viewTeachers.size();i++)
		{
			HSSFRow row = sheet.createRow(i);
			HSSFCell cell = null;
			cell = row.createCell(0);
			cell.setCellValue(i);
			
			cell = row.createCell(1);
			cell.setCellValue(viewTeachers.get(i-1).getUserName());
			
			cell = row.createCell(2);
			cell.setCellValue(viewTeachers.get(i-1).getUserAccount());
			
			cell = row.createCell(3);
			//cell.setCellValue(viewTeachers.get(i-1).getUserPassword());
			cell.setCellValue("");

			cell = row.createCell(4);
			cell.setCellValue(viewTeachers.get(i-1).getSchoolName());
			
			cell = row.createCell(5);
			cell.setCellValue(viewTeachers.get(i-1).getTeacherStateName());
			
			cell = row.createCell(6);
			cell.setCellValue(viewTeachers.get(i-1).getUserCode());

			cell = row.createCell(7);
			cell.setCellValue(viewTeachers.get(i-1).getUserOpenId() == null || viewTeachers.get(i-1).getUserOpenId().equals("") ? "N" : "Y");
		}
		
		AttachVO attachVo = new AttachVO();
		attachVo.setAttachName("test.xls");
		attachVo.setAttachUrl(physicalPath);
		
		File file = new File(attachVo.getAttachUrl());
		try {
			FileOutputStream stream = FileUtils.openOutputStream(file);
			workbook.write(stream);
			stream.close();
			workbook.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return attachVo;
	}
	
	public static List<User> getStudentsforExcel(InputStream inputStream)
	{
		//创建excel，读取文件内容
		try
		{
			HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
			//读取默认的第一个工作表
			HSSFSheet sheet = workbook.getSheetAt(0);
			int firstRowNum = sheet.getFirstRowNum();
			int lastRowNum = sheet.getLastRowNum();
			
			for(int i=0;i<5;i++)
			{
				System.out.println(sheet.getRow(firstRowNum).getCell(i).getStringCellValue());
			}
			
			
			if(!sheet.getRow(firstRowNum).getCell(0).getStringCellValue().equals("姓名") || !sheet.getRow(firstRowNum).getCell(1).getStringCellValue().equals("性别")
					 || !sheet.getRow(firstRowNum).getCell(2).getStringCellValue().equals("身份证号") || !sheet.getRow(firstRowNum).getCell(3).getStringCellValue().equals("电话")
					  || !sheet.getRow(firstRowNum).getCell(4).getStringCellValue().equals("密码"))
				throw new Exception("文件格式不正确");
			
			
			List<User> users = new ArrayList<User>();
			HSSFCell cell;
			
			for(int i=firstRowNum+1;i<=lastRowNum;i++)
			{
				User user = new User();
				HSSFRow row = sheet.getRow(i);
				cell = row.getCell(0);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				user.setUserName(cell.getStringCellValue());
				cell = row.getCell(1);
				if(cell.getStringCellValue().equals("男"))
				{
					user.setUserGender(User.GENDER_MEN);
				}
				else
				{
					user.setUserGender(User.GENDER_WOMEN);
				}
				cell = row.getCell(2);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				user.setUserIdentityCode(cell.getStringCellValue());
				cell = row.getCell(3);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				user.setUserPhone(cell.getStringCellValue());
				cell = row.getCell(4);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				//user.setUserAccount(user.getUserIdentityCode());
				user.setUserPassword(cell.getStringCellValue());
				user.setUserbirthday(new Timestamp(System.currentTimeMillis()));
				users.add(user);
			}
			return users;
			
		}
		catch(Exception e)
		{
			return null;
		}
	}
	
	
	public static File getExcelforStudent(List<ViewStudent> viewStudents,String physicalPath) throws Exception
	{
		String[] title = {"编号","姓名","帐号","密码","年级","班级","登陆编号", "绑定微信"};
		
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet();
		
		HSSFRow firstRow = sheet.createRow(0);
		HSSFCell cell = null;
		for(int i=0;i<title.length;i++)
		{
			cell = firstRow.createCell(i);
			cell.setCellValue(title[i]);
		}
		
		for(int i=1;i<=viewStudents.size();i++)
		{
			HSSFRow row = sheet.createRow(i);
			ViewStudent viewStudent = viewStudents.get(i-1);
			cell = row.createCell(0);
			cell.setCellValue(i);
			
			cell = row.createCell(1);
			cell.setCellValue(viewStudent.getUserName());
			
			cell = row.createCell(2);
			cell.setCellValue(viewStudent.getUserAccount());
			
			cell = row.createCell(3);
			//cell.setCellValue(viewStudent.getUserPassword());
			cell.setCellValue("");

			cell = row.createCell(4);
			cell.setCellValue(viewStudent.getGradeName());

			cell = row.createCell(5);
			cell.setCellValue(viewStudent.getSchoolClassName());
			
			cell = row.createCell(6);
			cell.setCellValue(viewStudent.getUserCode());

			cell = row.createCell(7);
			cell.setCellValue(viewStudent.getUserOpenId() == null || viewStudent.getUserOpenId().equals("") ? "N" : "Y");			
		}
		File file = new File(physicalPath);
		
		FileOutputStream stream = FileUtils.openOutputStream(file);
		workbook.write(stream);
		stream.close();
		workbook.close();
		
		return file;
	}
	
	public static List<Integer> getSchoolStudentsforExcel(Map<String,Map<String,Integer>> gradeSchoolClassMap,InputStream inputStream,
			IBaseDao<edu.iasd.pojo.Student> studentDao,IBaseDao<edu.iasd.pojo.Role> roleDao,
			IBaseDao<edu.iasd.pojo.User> userDao,IBaseDao<edu.iasd.pojo.SchoolClass> schoolClassDao,
			IBaseDao<edu.iasd.pojo.StudentState> studentStateDao)
	{	
		try
		{
			
			HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
			//读取默认的第一个工作表
			HSSFSheet sheet = workbook.getSheetAt(0);
			int firstRowNum = sheet.getFirstRowNum();
			int lastRowNum = sheet.getLastRowNum();
			
			for(int i=0;i<5;i++)
			{
				System.out.println(sheet.getRow(firstRowNum).getCell(i).getStringCellValue());
			}
			
			
			if(!sheet.getRow(firstRowNum).getCell(0).getStringCellValue().equals("姓名") || !sheet.getRow(firstRowNum).getCell(1).getStringCellValue().equals("性别")
					 || !sheet.getRow(firstRowNum).getCell(2).getStringCellValue().equals("身份证号") || !sheet.getRow(firstRowNum).getCell(3).getStringCellValue().equals("电话")
					  || !sheet.getRow(firstRowNum).getCell(4).getStringCellValue().equals("年级") || !sheet.getRow(firstRowNum).getCell(5).getStringCellValue().equals("班级")
					  || !sheet.getRow(firstRowNum).getCell(6).getStringCellValue().equals("密码"))
				throw new Exception("文件格式不正确");
			
			
			List<Integer> userIds = new ArrayList<Integer>();
			HSSFCell cell;
			
			for(int i=firstRowNum+1;i<=lastRowNum;i++)
			{
				User user = new User();
				HSSFRow row = sheet.getRow(i);
				cell = row.getCell(0);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				user.setUserName(cell.getStringCellValue());
				cell = row.getCell(1);
				if(cell.getStringCellValue().equals("男"))
				{
					user.setUserGender(User.GENDER_MEN);
				}
				else
				{
					user.setUserGender(User.GENDER_WOMEN);
				}
				cell = row.getCell(2);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				user.setUserIdentityCode(cell.getStringCellValue());
				cell = row.getCell(3);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				user.setUserPhone(cell.getStringCellValue());
				
				//通过年级和班级确定班级
				cell = row.getCell(4);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				String gradeName = cell.getStringCellValue();
				cell = row.getCell(5);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				String schoolClassName = cell.getStringCellValue();
				Integer schoolClassId = gradeSchoolClassMap.get(gradeName).get(schoolClassName);
				
				cell = row.getCell(6);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				//user.setUserAccount(user.getUserIdentityCode());
				user.setUserPassword(cell.getStringCellValue());
				user.setUserbirthday(new Timestamp(System.currentTimeMillis()));
				System.out.println("第"+i+"个用户,"+user.getUserName()+","+user.getUserIdentityCode()+","+user.getUserPassword());
				
				UserHelper.addRole(userDao, user, roleDao,Role.ROLE_USER);
				UserHelper.addRole(userDao, user, roleDao,Role.ROLE_STUDENT);
				
				UserHelper.saveUser(userDao, user);
				Student student = new Student();
				student.setSchoolClass(schoolClassDao.get(schoolClassId));
				student.setUser(user);
				student.setStudentCode(user.getUserCode());
				student.setStudentState(studentStateDao.get(StudentState.STUDENT_STATE_NORMAL));
				studentDao.save(student);
				
				userIds.add(student.getUserId());
				
			}
			return userIds;
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	
}
