package edu.iasd.service;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.iasd.pojo.MyFans;
import edu.iasd.pojo.User;
import edu.iasd.pojo.ViewArticle;
import edu.iasd.pojo.ViewNoticeAccept;
import edu.iasd.pojo.WechatNoticeUser;
import edu.iasd.utils.RecvNoticeObject;
import edu.iasd.wechat.message.News;


@Service
@Transactional
public interface WechatService {

	public boolean BindUser(String userAccout,String userCode,String userOpenId);

	
	public Integer sendArticleToMessage(int article);
	public boolean startSendToWechat();
	public List<News> timingCheck();
	public List<WechatNoticeUser> getSendUserList();
	public boolean saveMyfans(MyFans myfans);
	public boolean removeMyfans(MyFans myfans);
	public List<ViewArticle> getAllArticle();
	
	//获取单个文章显示
	public ViewArticle getArticle(Integer articleId);
	
	public User getUserDetail(String userOpenId);
	
	public User getUserDetailByuserId(int userId);
	
	//判断用户登录
	public User loginCheck(String userAccount,String passWord);

	public boolean bind(Integer userId, String myOpenId);

	public boolean registerCheck(User user);
	
	//check you openid in database is or Not ?
	public boolean IsOrNotHaveYouOpenId(String openid);
	
	public RecvNoticeObject getMySendNoticeObject(Integer userId);
	
}


