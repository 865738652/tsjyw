package edu.iasd.utils;

import java.sql.Timestamp;

import com.demonstration.hibernate.basedao.IBaseDao;

import edu.iasd.pojo.Message;
import edu.iasd.pojo.Notice;
import edu.iasd.pojo.NoticeAccept;
import edu.iasd.pojo.NoticeAcceptType;
import edu.iasd.pojo.NoticeState;

public class NoticeHelper {
	public static void sendNotice(int senderId, int receiverId, String title, String content, 
			IBaseDao<edu.iasd.pojo.User> userDao,
			IBaseDao<edu.iasd.pojo.NoticeState> noticeStateDao,
			IBaseDao<edu.iasd.pojo.Notice> noticeDao,
			IBaseDao<edu.iasd.pojo.NoticeAccept> noticeAcceptDao,
			IBaseDao<edu.iasd.pojo.NoticeAcceptType> noticeAcceptTypeDao,
			IBaseDao<edu.iasd.pojo.Message> messageDao) {
		/*
		 * base notice information
		 */
		Notice notice = new Notice();
		notice.setUser(userDao.get(senderId));
		notice.setNoticeTime(new Timestamp(System.currentTimeMillis()));
		notice.setNoticeTitle(title);
		notice.setNoticeContent(content);
		notice.setNoticeOverTime(new Timestamp(System.currentTimeMillis()+30*Notice.DAY_TIMESTAMP));
		notice.setNoticeIsOrNotReply(Notice.LOOK_NOT);		
		notice.setNoticeState(noticeStateDao.get(NoticeState.NOTICESTATE_NORMAL));
		notice.setNoticeAcceptType(noticeAcceptTypeDao.get(NoticeAcceptType.NOTICEACCEPTTYPE_PERSON));
		notice.setNoticeAttachments(null);		
		noticeDao.save(notice);
		
		/*
		 * save notice receiver
		 */
		NoticeAccept noticeAccept = new NoticeAccept();
		noticeAccept.setNotice(notice);
		noticeAccept.setNoticeAcceptType(noticeAcceptTypeDao.get(NoticeAcceptType.NOTICEACCEPTTYPE_PERSON));
		noticeAccept.setNoticeAcceptGroupId(receiverId);
		noticeAcceptDao.save(noticeAccept);
		
		/*
		 * save notice message to wechat
		 */
		Message message = new Message();
		message.setWechatMessageId(notice.getNoticeId());
		message.setMessageTypeId(Message.MESSAGE_NOTICE);
		message.setMessageStateId(Message.MESSAGE_STATE_WAIT);
		messageDao.save(message);		
	}
}
