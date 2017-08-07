package edu.iasd.wechat.utils;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import edu.iasd.pojo.WechatNoticeUser;
import edu.iasd.service.WechatService;
import edu.iasd.wechat.message.Command;


public class TimingTaskUtil {
	
	@Autowired
	private WechatService wechatService;

	public void work() {  
        List<WechatNoticeUser> wechatNoticeUsers = wechatService.getSendUserList();
        if(wechatNoticeUsers != null && wechatNoticeUsers.size() > 0)
        {
        	//��ʼ����
        	for(int i=0;i<wechatNoticeUsers.size();i++){
        		WechatNoticeUser a = wechatNoticeUsers.get(i);
        		String url = InterfaceUtil.get_AuthUrl(InterfaceUtil.INDEX+"WeChat/LookNotice?noticeId="+a.getNoticeId());
            	try {
            		System.out.println(MessageUtil.getTemplateMessage(a.getUserOpenId(), url, "�𾴵� "+a.getRecvUserName()+" ��ã�", a.getSchoolClassName(), a.getSendUserName(), a.getNoticeTime(), a.getNoticeTitle(), "����鿴����"));
    				InterfaceUtil
    				.doPost(InterfaceUtil.TEMPLATE_MESSAGE,MessageUtil.getTemplateMessage(a.getUserOpenId(), url, "�𾴵� "+a.getSendUserName()+" ��ã�", a.getSchoolClassName(), a.getRecvUserName(), a.getNoticeTime(),a.getNoticeTitle(), ""));
    			} catch (Exception e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
        	}
        }
    }  
}
