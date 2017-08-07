package edu.iasd.service;



import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demonstration.hibernate.dao.support.Page;

import edu.iasd.form.BusinessForm;
import edu.iasd.pojo.Business;
import edu.iasd.pojo.ViewBusiness;




@Service("businessService") 
@Transactional
public interface BusinessService {
	
	
	public Page getAllBusiness(int pageNumber,int pageSize,Integer userId,String queryString);

	public ViewBusiness getBusiness(int businessId);
	
	public String createBusiness(BusinessForm business);
	
	public boolean deleteBusiness(int businessId);
	
	public boolean modifyBusiness(BusinessForm business);
	
	public Business getBusinessForGoods(Integer goodsId);

}
