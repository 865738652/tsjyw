package edu.iasd.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demonstration.hibernate.basedao.IBaseDao;
import com.demonstration.hibernate.dao.support.Page;

import edu.iasd.pojo.Announcement;
import edu.iasd.pojo.Attachment;
import edu.iasd.pojo.AttachmentType;
import edu.iasd.pojo.Message;
import edu.iasd.pojo.Notice;
import edu.iasd.pojo.NoticeAccept;
import edu.iasd.pojo.NoticeAcceptType;
import edu.iasd.pojo.NoticeState;
import edu.iasd.pojo.Role;
import edu.iasd.pojo.User;
import edu.iasd.pojo.ViewNoticeAccept;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


@Service
@Transactional
public class AnnouncementServiceImpl implements AnnouncementService{
	
	@Resource
	private IBaseDao<edu.iasd.pojo.Attachment> attachmentDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.AttachmentType> attachmentTypeDao;

	@Resource
	private IBaseDao<edu.iasd.pojo.County> countyDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.User> userDao;
	
	
	@Resource
	private IBaseDao<edu.iasd.pojo.CountyManager> countyManagerDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.SchoolManager> schoolManagerDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.School> schoolDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.Grade> gradeDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.SchoolClass> schoolClassDao;
	
	
	@Resource
	private IBaseDao<edu.iasd.pojo.ClassMaster> classMasterDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.Student> studentDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.Reply> replyDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.GradeMaster> gradeMasterDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.NoticeState> noticeStateDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.Notice> noticeDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.NoticeAccept> noticeAcceptDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.NoticeAcceptType> noticeAcceptTypeDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.ViewNoticeAccept> viewNoticeAcceptDao;
	
	
	@Resource
	private IBaseDao<edu.iasd.pojo.ViewNotice> viewNoticeDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.Teacher> teacherDao;
	
	
	@Resource
	private IBaseDao<edu.iasd.pojo.Message> messageDao;
	

	@Override
	public String sendAnnoucement(JSONObject announcementJson,Integer userId) {
		// TODO Auto-generated method stub
		
		String recvObject = announcementJson.getString("recvObject");
		Integer recvObjectId = announcementJson.getInt("recvObjectId");
		String announmentTitle = announcementJson.getString("announmentTitle");
		String announmentContent = announcementJson.getString("announmentContent");
		//附件
		JSONArray attachmentArrays = announcementJson.getJSONArray("attachs");
		Set<Attachment> attachments = new HashSet<Attachment>();
		
		
		
		Notice notice = new Notice();
		if(attachmentArrays != null && attachmentArrays.size() > 0 )
		{
			for(int i=0;i<attachmentArrays.size();i++)
			{
				JSONObject attachmentObject = attachmentArrays.getJSONObject(i);
				Attachment a = new Attachment();
				a.setAttachmentName(attachmentObject.getString("attachName"));
				a.setAttachmentSize((float) attachmentObject.getInt("attachSize"));
				a.setAttachmentType(attachmentTypeDao.get(AttachmentType.ATTACHMENT_OTHER));
				a.setAttachmentUrl(attachmentObject.getString("attachUrl"));
				attachmentDao.save(a);
				attachments.add(a);
			}
			notice.setNoticeAttachments(attachments);
		}
		else
			notice.setNoticeAttachments(null);
			

		
		notice.setNoticeContent(announmentContent);
		notice.setNoticeTitle(announmentTitle);
		notice.setNoticeIsOrNotReply(1);
		notice.setNoticeOverTime(new Timestamp(System.currentTimeMillis()+Notice.DAY_TIMESTAMP * 30));
		notice.setNoticeState(noticeStateDao.get(NoticeState.NOTICESTATE_NORMAL));
		notice.setNoticeTime(new Timestamp(System.currentTimeMillis()));
		notice.setReplies(null);
		notice.setUser(userDao.get(userId));
		if(recvObject.equals("allSchool"))
			notice.setNoticeAcceptType(noticeAcceptTypeDao.get(NoticeAcceptType.NOITCEACCEPTTYPE_ALLSCHOOL));
		else if(recvObject.equals("allUser"))
			notice.setNoticeAcceptType(noticeAcceptTypeDao.get(NoticeAcceptType.NOITCEACCEPTTYPE_ALLUSER));
		noticeDao.save(notice);
		
		NoticeAccept noticeAccept = new NoticeAccept();
		noticeAccept.setNotice(notice);
		noticeAccept.setNoticeAcceptGroupId(recvObjectId);
		if(recvObject.equals("allSchool"))
			noticeAccept.setNoticeAcceptType(noticeAcceptTypeDao.get(NoticeAcceptType.NOITCEACCEPTTYPE_ALLSCHOOL));
		else if(recvObject.equals("allUser"))
			noticeAccept.setNoticeAcceptType(noticeAcceptTypeDao.get(NoticeAcceptType.NOITCEACCEPTTYPE_ALLUSER));
		noticeAcceptDao.save(noticeAccept);
		
		Message message = new Message();
		message.setWechatMessageId(notice.getNoticeId());
		message.setMessageTypeId(Message.MESSAGE_NOTICE);
		message.setMessageStateId(Message.MESSAGE_STATE_WAIT);
		messageDao.save(message);
		
		
		return null;
	}


	@Override
	public Page getMyRecvAnnouncement(Integer userId,int pageNow,int pageSize) {
		// TODO Auto-generated method stub
		User user = userDao.get(userId);
		List<ViewNoticeAccept> result = new ArrayList<ViewNoticeAccept>();
		
		
		Set<Integer> countyIds = new HashSet<Integer>();
		
		for (Object o : user.getUserRoles()) {
			Role r = (Role)o;
			if(r.getRoleId() == Role.ROLE_STUDENT)
			{
				Integer countyId = studentDao.get(userId).getSchoolClass().getGrade().getSchool().getCounty().getCountyId();
				countyIds.add(countyId);
			}
			else if(r.getRoleId() == Role.ROLE_SCHOOLTEACHER)
			{
				Integer countyId = teacherDao.get(userId).getSchool().getCounty().getCountyId();
				countyIds.add(countyId);
			}
			else if(r.getRoleId() == Role.ROLE_CLASSMASTER)
			{
				Integer countyId = classMasterDao.get(userId).getSchoolClass().getGrade().getSchool().getCounty().getCountyId();
				countyIds.add(countyId);
			}
			else if(r.getRoleId() == Role.ROLE_GRADEMASTER)
			{
				Integer countyId = gradeMasterDao.get(userId).getGrade().getSchool().getCounty().getCountyId();
				countyIds.add(countyId);
			}
			else if(r.getRoleId() == Role.ROLE_SCHOOLADMIN)
			{
				Integer countyId = schoolManagerDao.get(userId).getSchool().getCounty().getCountyId();
				countyIds.add(countyId);
			}
			else if(r.getRoleId() == Role.ROLE_SCHOOLMASTER)
			{
				Integer countyId = teacherDao.get(userId).getSchool().getCounty().getCountyId();
				countyIds.add(countyId);
			}
			else if(r.getRoleId() == Role.ROLE_USER)
			{
				List<ViewNoticeAccept> l = viewNoticeAcceptDao.find("from ViewNoticeAccept v where v.noticeAcceptTypeId=? and v.noticeAcceptGroupId=? and v.noticeOverTime>?", NoticeAcceptType.NOITCEACCEPTTYPE_ALLUSER,-1,new Timestamp(System.currentTimeMillis()));
				result.addAll(l);
			}
		}
		if(countyIds != null)
		{
			List<ViewNoticeAccept> l = viewNoticeAcceptDao.find("from ViewNoticeAccept v where v.noticeAcceptTypeId=? and v.noticeAcceptGroupId=? and v.noticeOverTime>?", NoticeAcceptType.NOITCEACCEPTTYPE_ALLSCHOOL,-1,new Timestamp(System.currentTimeMillis()));
			result.addAll(l);
			for(Integer countyId:countyIds)
			{
				List<ViewNoticeAccept> ll = viewNoticeAcceptDao.find("from ViewNoticeAccept v where v.noticeAcceptTypeId=? and v.noticeAcceptGroupId=? and v.noticeOverTime>?", NoticeAcceptType.NOITCEACCEPTTYPE_ALLSCHOOL,countyId,new Timestamp(System.currentTimeMillis()));
				result.addAll(ll);
			}
		}
		
		
		
		ViewNoticeAccept [] array = new ViewNoticeAccept[result.size()];
		//List 转换为数组
		result.toArray(array);
		//排序
		Arrays.sort(array);
		
		result.clear();
		int start = (pageNow - 1) * pageSize;
		for (int i = 0; i < pageSize && start + i < array.length; i++) {
			result.add(array[start + i]);
		}
		
		Page page = new Page(start, array.length, pageSize, result);
		return page;
	}

}
