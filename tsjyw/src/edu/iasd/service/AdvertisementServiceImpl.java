package edu.iasd.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demonstration.hibernate.basedao.IBaseDao;
import com.demonstration.hibernate.dao.support.Page;

import edu.iasd.form.AdvertisementForm;
import edu.iasd.form.AttachVO;
import edu.iasd.pojo.Advertisement;
import edu.iasd.pojo.Attachment;
import edu.iasd.pojo.Image;
import edu.iasd.pojo.ViewAdvertisement;

@Service
@Transactional
public class AdvertisementServiceImpl implements AdvertisementService{
	
	@Resource
	private IBaseDao<edu.iasd.pojo.Advertisement> advertisementDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.ViewAdvertisement> viewAdvertisementDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.Attachment> attachmentDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.Image> imageDao;
	
	@Override
	public Page getAllAdvertisement(int pageNow, int pageSize){
		// TODO Auto-generated method stub
		return viewAdvertisementDao.pagedQuery("from ViewAdvertisement v",pageNow,pageSize);
	}

	@Override
	public ViewAdvertisement getAdvertisement(int advertisementId) throws Exception {
		// TODO Auto-generated method stub
		Advertisement a = advertisementDao.findUniqueBy("advertisementId", advertisementId);
		ViewAdvertisement v=viewAdvertisementDao.findUniqueBy("advertisementId", advertisementId);
		return v;
	}

	@Override
	public boolean modifyAdvertisement(AdvertisementForm advertisementForm) {
		// TODO Auto-generated method stub
		Advertisement ad=advertisementDao.findUniqueBy("advertisementId", advertisementForm.getAdvertisementId());
		if(ad == null)
			return false; 
		
		fillAdvertisement(ad,advertisementForm);
		advertisementDao.save(ad);
		return true;
		
	}
	
	@Override
	public List<ViewAdvertisement> getAllAdvertisement() {
		// TODO Auto-generated method stub
		return viewAdvertisementDao.getAll();
	}

	private void fillAdvertisement(Advertisement ad,AdvertisementForm advertisementForm) {
		// TODO Auto-generated method stub
		ad.setAdvertisementId(advertisementForm.getAdvertisementId());
		//ad.setAttachment(attachmentDao.findUniqueBy("attachmentId", advertisementForm.getAttachmentId()));
		ad.setAdvertisementImgPath(advertisementForm.getAdvertisementImgPath());
		ad.setAdvertisementName(advertisementForm.getAdvertisementName());
		
	}
}
