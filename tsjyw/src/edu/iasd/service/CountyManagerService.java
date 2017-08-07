package edu.iasd.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demonstration.hibernate.dao.support.Page;

import edu.iasd.form.CountyManagerForm;
import edu.iasd.pojo.ViewCountyManager;

@Service
@Transactional
public interface CountyManagerService {

	public Page getCountyManagerByCounty(int pageNumber, int pageSize,	int countyId);

	public ViewCountyManager getCountyManager(int countyManagerId);
	
	public String selectCountyManager(int userId, int countyId) throws Exception;
	
	public boolean cancelCountyManager(int countyManagerId) throws Exception;

	public String createCountyManager(CountyManagerForm countyManager)  throws Exception;

	public boolean deleteCountyManager(int countyManagerId);

	public boolean modifyCountyManager(CountyManagerForm countyManager)  throws Exception;

	public Page getAllCountyManager(int pageNow, int pageSize);
}
