package edu.iasd.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demonstration.hibernate.dao.support.Page;
import edu.iasd.pojo.ViewNoticeAccept;
import net.sf.json.JSONObject;

@Service
@Transactional
public interface AnnouncementService {
	
	public String sendAnnoucement(JSONObject announcementJson,Integer userId);
	
	public Page getMyRecvAnnouncement(Integer userId,int pageNow,int pageSize);
}
