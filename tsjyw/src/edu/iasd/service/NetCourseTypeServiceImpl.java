package edu.iasd.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demonstration.hibernate.basedao.IBaseDao;
import com.demonstration.hibernate.dao.support.Page;

import edu.iasd.form.NetCourseTypeForm;
import edu.iasd.pojo.NetCourseType;
import edu.iasd.pojo.Role;
import edu.iasd.pojo.User;
import edu.iasd.pojo.ViewNetCourseType;

@Service
@Transactional
public class NetCourseTypeServiceImpl implements NetCourseTypeService {
	
	@Resource
	private IBaseDao<edu.iasd.pojo.NetCourseType> netCourseTypeDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.ViewNetCourseType> viewNetCourseTypeDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.User> userDao;

	@Override
	public Page getAllNetCourseType(Integer userId, int pageNow, int pageSize) {
		// TODO Auto-generated method stub
		User user = userDao.findUniqueBy("userId", userId);
		for (Object o :user.getUserRoles()){
			Role role=(Role)o;
			if(role.getRoleId()==Role.ROLE_SUPERADMIN)
				return viewNetCourseTypeDao.pagedQuery("from ViewNetCourseType", pageNow, pageSize);
			else if(role.getRoleId()==Role.ROLE_FAMOUSTEACHER)
				return viewNetCourseTypeDao.pagedQuery("select distinct v from ViewNetCourseType v ", pageNow, pageSize,userId);
		}
				return new Page();
	}

	@Override
	public ViewNetCourseType getNetCourseType(int netCourseTypeId) {
		ViewNetCourseType vnc = viewNetCourseTypeDao.findUniqueBy("netCourseTypeId", netCourseTypeId);
		return vnc;
	}

	@Override
	public String createNetCourseType(NetCourseTypeForm netCourseType) {
		NetCourseType nc = new NetCourseType();
		nc.setNetCourseTypeName(netCourseType.getNetCourseTypeName());
		netCourseTypeDao.save(nc);
		return nc.getNetCourseTypeId().toString();
	}

	@Override
	public Boolean deleteNetCourseType(int netCourseTypeId) {
		netCourseTypeDao.removeById(netCourseTypeId);
		return true;
	}

	@Override
	public Boolean modifyNetCourseType(NetCourseTypeForm netCourseType) {
		NetCourseType nc = netCourseTypeDao.findUniqueBy("netCourseTypeId", netCourseType.getNetCourseTypeId());
		if(nc == null) 
			return false;
		nc.setNetCourseTypeName(netCourseType.getNetCourseTypeName());
		netCourseTypeDao.save(nc);
		return true;
	}

}
