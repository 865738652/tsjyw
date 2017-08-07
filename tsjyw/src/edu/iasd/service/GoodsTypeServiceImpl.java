package edu.iasd.service;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demonstration.hibernate.basedao.IBaseDao;
import com.demonstration.hibernate.dao.support.Page;

import edu.iasd.form.GoodsTypeForm;
import edu.iasd.pojo.Goods;
import edu.iasd.pojo.GoodsType;
import edu.iasd.pojo.Role;
import edu.iasd.pojo.User;
import edu.iasd.pojo.ViewGoodsType;

@Service
@Transactional
public class GoodsTypeServiceImpl implements GoodsTypeService{
	
	@Resource
	private IBaseDao<edu.iasd.pojo.GoodsType> goodsTypeDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.Goods> goodsDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.ViewGoodsType> viewGoodsTypeDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.User> userDao;

	@Override
	public Page getAllGoodsType(Integer userId,int pageNow, int pageSize) {
		// TODO Auto-generated method stub
		User user = userDao.findUniqueBy("userId", userId);
		for (Object o :user.getUserRoles()){
			Role role=(Role)o;
			if(role.getRoleId()==Role.ROLE_SUPERADMIN)
				return viewGoodsTypeDao.pagedQuery("from ViewGoodsType", pageNow, pageSize);
			else if(role.getRoleId()==Role.ROLE_BUSINESSADMIN)
				return viewGoodsTypeDao.pagedQuery("select distinct v from ViewGoodsType v ", pageNow, pageSize,userId);
		}
		return new Page();
	}

	@Override
	public ViewGoodsType getGoodsType(int goodsTypeId) {
		// TODO Auto-generated method stub
		ViewGoodsType vg = viewGoodsTypeDao.findUniqueBy("goodsTypeId", goodsTypeId);
		return vg;
	}

	@Override
	public String createGoodsType(GoodsTypeForm goodsType) {
		// TODO Auto-generated method stub
		GoodsType c = new GoodsType();
		c.setGoodsTypeName(goodsType.getGoodsTypeName());
		c.setGoodsTypeSerial(goodsType.getGoodsTypeSerial());
		goodsTypeDao.save(c);
		return c.getGoodsTypeId().toString();
	}

	@Override
	public Boolean deleteGoodsType(int goodsTypeId) {
		// TODO Auto-generated method stub
		goodsTypeDao.removeById(goodsTypeId);
		return true;
	}

	@Override
	public Boolean modifyGoodsType(GoodsTypeForm goodsType) {
		// TODO Auto-generated method stub
		GoodsType c = goodsTypeDao.findUniqueBy("goodsTypeId", goodsType.getGoodsTypeId());
		if(c == null) 
			return false;
		c.setGoodsTypeName(goodsType.getGoodsTypeName());
		c.setGoodsTypeSerial(goodsType.getGoodsTypeSerial());
		goodsTypeDao.save(c);
		return true;
	}

	@Override
	public List<ViewGoodsType> getAll() {
		// TODO Auto-generated method stub
		return viewGoodsTypeDao.getAll();
	}

	@Override
	public Set<GoodsType> getGoodsType(Integer goodsId) {
		// TODO Auto-generated method stub
		Goods a = goodsDao.get(goodsId);
		Set<GoodsType> as = a.getGoodsType();
		return as;
	}

	/*@Override
	public List<ViewGoodsType> getGoodsTypes(int goodsId) {
		// TODO Auto-generated method stub
		return viewGoodsTypeDao.getAll();
	}*/

}
