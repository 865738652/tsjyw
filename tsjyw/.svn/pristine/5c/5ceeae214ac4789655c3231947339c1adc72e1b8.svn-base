package edu.iasd.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demonstration.hibernate.dao.support.Page;

import edu.iasd.form.NetCourseTypeForm;
import edu.iasd.pojo.ViewNetCourseType;

@Service
@Transactional
public interface NetCourseTypeService {
	public Page getAllNetCourseType(Integer userId,int pageNow,int pageSize);
	
	public ViewNetCourseType getNetCourseType(int netCourseTypeId);
	
	public String createNetCourseType(NetCourseTypeForm netCourseType);
	
	public Boolean deleteNetCourseType(int netCourseTypeId);
	
	public Boolean modifyNetCourseType(NetCourseTypeForm netCourseType);

}
