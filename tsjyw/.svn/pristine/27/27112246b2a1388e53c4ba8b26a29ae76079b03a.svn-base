package edu.iasd.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demonstration.hibernate.dao.support.Page;

import edu.iasd.form.CountyForm;
import edu.iasd.pojo.ViewCounty;

@Service
@Transactional
public interface CountyService {
	public Page getAllcounty(Integer userId, String queryString, int pageNow, int pageSize);

	public Page getAllcounty(String queryString, int pageNow, int pageSize);
	
	public ViewCounty getCountyInformation(int countyId);
	
	public String createCounty(CountyForm county)  throws Exception;
	
	public boolean modify(CountyForm county) throws Exception;

	public boolean deleteCounty(int countyId);	
}
