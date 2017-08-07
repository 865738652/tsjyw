package edu.iasd.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demonstration.hibernate.dao.support.Page;

import edu.iasd.form.AdvertisementForm;
import edu.iasd.form.AttachVO;
import edu.iasd.pojo.Attachment;
import edu.iasd.pojo.ViewAdvertisement;

@Service
@Transactional
public interface AdvertisementService {
	//public Page getAdvertisementByAttachment(int pageNow, int pageSize, int attachmentId);
	
	public Page getAllAdvertisement(int pageNow, int pageSize);
	
	
	
	public ViewAdvertisement getAdvertisement(int advertisementId) throws Exception;

	
	public boolean modifyAdvertisement(AdvertisementForm advertisementForm) throws Exception;



	public List<ViewAdvertisement> getAllAdvertisement();
}
