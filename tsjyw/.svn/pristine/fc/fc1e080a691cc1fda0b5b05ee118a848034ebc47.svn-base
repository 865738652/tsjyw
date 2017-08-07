package edu.iasd.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demonstration.hibernate.dao.support.Page;

import edu.iasd.form.NetCourseForm;
import edu.iasd.pojo.NetCourseType;
import edu.iasd.pojo.User;
import edu.iasd.pojo.ViewNetCourse;

@Service
@Transactional
public interface NetCourseService {

	public ViewNetCourse getNetCourse(int netCourseId);

	public Page getAllNetCourse(int pageNumber, int pageSize);
	
	public Page getNetCourseByType(int pageNumber, int pageSize, int netCourseTypeId);
	
	public Page getNetCourseByUser(int pageNumber, int pageSize, int userId);

	public String createNetCourse(NetCourseForm netCourse);

	public boolean deleteNetCourse(int netCourseId);

	public boolean modifyNetCourse(NetCourseForm netCourse);

	public List<NetCourseType> getNetCourseType();

	

}
