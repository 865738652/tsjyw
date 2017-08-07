package edu.iasd.service;

import java.util.List;
import java.util.Set;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.demonstration.hibernate.dao.support.Page;

import edu.iasd.form.AttachVO;
import edu.iasd.form.NoticeForm;
import edu.iasd.form.NoticeReceiver;
import edu.iasd.form.ReplyForm;
import edu.iasd.pojo.Attachment;
import edu.iasd.pojo.Reply;
import edu.iasd.pojo.ViewNotice;
import edu.iasd.pojo.ViewNoticeAccept;


@Service
@Transactional
public interface SendNoticeService {
	public List<NoticeReceiver> getNoticeReceiver(int userId);
	public String getTheSelect2School(Integer userId); 
	public String getTheSelect2Grade(Integer userId,Integer schoolId);
	public String getTheSelect2Classes(Integer userId,Object object);
	public String sendNotice(Integer userId,NoticeForm form) throws Exception;
	public Page getAllMyRecvNotice(Integer userId,int pageNow,int pageSize);
	public ViewNotice getNotice(Integer noticeId);
	public Set<Attachment> getAttachemnts(Integer noticeId);
	public Attachment getAttachment(Integer attachmentId);
	
	
	public List<ViewNoticeAccept> getRecvWeChatNotice(String userOpendId);
	
	public List<ViewNoticeAccept> getRecvWeChatNoticeByUserId(Integer userId,Integer roleId);
	
	public boolean weChatSendNotice(Integer userId,JSONObject jsonObject);
	
	public List<AttachVO> getNoticeAttachment(Integer noticeId,int pageSize,int pageNumber);
	
	public Page getAllSendedNotice(Integer userId,int pageNow,int pageSize);
	
	public boolean deleteMyNotice(Integer noticeId,Integer userId) throws Exception;
	
	public Reply checkNoticeReply(Integer userId,Integer noticeId);
	
	
	public boolean checkNoticeIsMine(Integer noticeId,Integer userId);
	public List<ReplyForm> getReplyforNotice(Integer noticeId);
	
	public boolean addNoticeReplyContent(Integer replyId,String replyContent);
	
	public Reply getReplyById(Integer replyId);
	
}
