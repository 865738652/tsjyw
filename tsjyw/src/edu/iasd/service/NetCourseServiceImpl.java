package edu.iasd.service;

import java.util.List;

import javax.annotation.Resource;
import javax.xml.ws.ServiceMode;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demonstration.hibernate.basedao.IBaseDao;
import com.demonstration.hibernate.dao.support.Page;

import edu.iasd.form.NetCourseForm;
import edu.iasd.form.NetCourseTypeForm;
import edu.iasd.form.SchoolForm;
import edu.iasd.pojo.GoodsType;
import edu.iasd.pojo.NetCourse;
import edu.iasd.pojo.NetCourseType;
import edu.iasd.pojo.School;
import edu.iasd.pojo.SchoolType;
import edu.iasd.pojo.User;
import edu.iasd.pojo.ViewNetCourse;

@Service
@Transactional
public class NetCourseServiceImpl implements NetCourseService{
	
	@Resource
	private IBaseDao<edu.iasd.pojo.NetCourse> netCourseDao;
	@Resource
	private IBaseDao<edu.iasd.pojo.ViewNetCourse> viewNetCourseDao;
	@Resource
	private IBaseDao<edu.iasd.pojo.NetCourseType> netCourseTypeDao;
	@Resource 
	private IBaseDao<edu.iasd.pojo.FamousTeacher> famousTeacherDao;
	@Resource 
	private IBaseDao<edu.iasd.pojo.User> userDao;
	
	
	@Override
	public ViewNetCourse getNetCourse(int netCourseId) {
		// TODO Auto-generated method stub
		ViewNetCourse vn = viewNetCourseDao.findUniqueBy("netCourseId", netCourseId);
		
		return vn;
	}
	
	@Override
	public Page getAllNetCourse(int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		return viewNetCourseDao.pagedQuery("from ViewNetCourse order by netCourseTime desc", pageNumber, pageSize);
	}
	
	@Override
	public Page getNetCourseByType(int pageNumber, int pageSize, int netCourseTypeId)
	{
		return viewNetCourseDao.pagedQuery("from ViewNetCourse where netCourseTypeId=? order by netCourseTime desc", pageNumber, pageSize, netCourseTypeId);
	}
	
	@Override
	public Page getNetCourseByUser(int pageNumber, int pageSize, int userId)
	{
		return viewNetCourseDao.pagedQuery("from ViewNetCourse where userId=? order by netCourseTime desc", pageNumber, pageSize, userId);
	}

	@Override
	public String createNetCourse(NetCourseForm netCourseForm) {
		// TODO Auto-generated method stub
		NetCourse n=new NetCourse();
		fillNetCourse(n,netCourseForm); 
		
		netCourseDao.save(n);
		return n.getNetCourseId().toString();
	}

	

	private void fillNetCourse(NetCourse n, NetCourseForm netCourseForm) {
		// TODO Auto-generated method stub
		n.setNetCourseId(netCourseForm.getNetCourseId());
		n.setNetCourseName(netCourseForm.getNetCourseName());
		n.setNetCourseNumber(netCourseForm.getNetCourseNumber());
		n.setNetCourseTime(netCourseForm.getNetCourseTime());
		n.setNetCourseImgPath(netCourseForm.getNetCourseImgPath());
		n.setNetCourseIntegral(netCourseForm.getNetCourseIntegral());
		n.setNetCourseIntro(netCourseForm.getNetCourseIntro());
		n.setNetCourseLink(netCourseForm.getNetCourseLink());
		n.setFamousTeacher(famousTeacherDao.findUniqueBy("userId",netCourseForm.getUserId()));

		NetCourseType type = netCourseTypeDao.findUniqueBy("netCourseTypeId", netCourseForm.getNetCourseTypeId());
		n.setNetCourseType(type);
		
		
		
	}

	@Override
	public boolean deleteNetCourse(int netCourseId) {
		// TODO Auto-generated method stub
		netCourseDao.removeById(netCourseId);
		return true;
	}

	@Override
	public boolean modifyNetCourse(NetCourseForm netCourseForm) {
		// TODO Auto-generated method stub
		NetCourse netCourse = netCourseDao.findUniqueBy("netCourseId", netCourseForm.getNetCourseId());
		
		if (netCourse==null)
			return false;
		fillNetCourse(netCourse,netCourseForm);
		netCourseDao.save(netCourse);
		return true;
	}

	@Override
	public List<NetCourseType> getNetCourseType() {
		// TODO Auto-generated method stub
		return netCourseTypeDao.getAll();
	}

	
	
	

}
