package edu.iasd.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demonstration.hibernate.dao.support.Page;

import edu.iasd.form.BusinessManagerForm;
import edu.iasd.pojo.ViewBusinessManager;

@Service
@Transactional
public interface BusinessManagerService {
	
	public Page getBusinessManagerByBusiness(int pageNumber,int pageSize, int businessId);
	
	public Page getAllBusinessManager(int pageNow, int pageSize);
	
	public ViewBusinessManager getBusinessManager(int businessManagerId);
	
	public String createBusinessManager(BusinessManagerForm businessManager)  throws Exception;
	
	public boolean deleteBusinessManager(int businessManagerId);
	
	public boolean modifyBusinessManager(BusinessManagerForm businessManager)  throws Exception;
	
	public List<ViewBusinessManager> getBusinessManagerFor(Integer goodsId);

}
