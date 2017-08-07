package edu.iasd.service;


import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demonstration.hibernate.dao.support.Page;

import edu.iasd.form.GoodsTypeForm;
import edu.iasd.pojo.GoodsType;
import edu.iasd.pojo.ViewGoodsType;
@Service
@Transactional
public interface GoodsTypeService {
	public Page getAllGoodsType(Integer userId,int pageNow,int pageSize);
	
	public Set<GoodsType> getGoodsType(Integer goodsId);
	
	public ViewGoodsType getGoodsType(int goodsTypeId);
	
	public String createGoodsType(GoodsTypeForm goodsType);
	
	public Boolean deleteGoodsType(int goodsTypeId);
	
	public Boolean modifyGoodsType(GoodsTypeForm goodsType);
	
	/*public List<ViewGoodsType> getGoodsTypes(int goodsId);*/
	public List<ViewGoodsType> getAll();
	
}
