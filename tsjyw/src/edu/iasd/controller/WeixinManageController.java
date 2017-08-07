package edu.iasd.controller;

import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.iasd.pojo.MyFans;
import edu.iasd.pojo.ViewArticle;
import edu.iasd.service.ArticleService;
import edu.iasd.service.WechatService;
import edu.iasd.wechat.message.Command;
import edu.iasd.wechat.message.Custom;
import edu.iasd.wechat.message.CustomMessage;
import edu.iasd.wechat.message.CustomMessageContent;
import edu.iasd.wechat.message.TextMessage;
import edu.iasd.wechat.utils.CheckUtil;
import edu.iasd.wechat.utils.InterfaceUtil;
import edu.iasd.wechat.utils.MessageUtil;

@Controller
@RequestMapping("Weixin")
public class WeixinManageController {
	
	
	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private WechatService wechatService;
	
	
	@RequestMapping(method=RequestMethod.GET)
	@ResponseBody
	public String Check(HttpServletRequest request,HttpServletResponse respose)
	{
		System.out.println("΢�ųɹ�������֤������");
		String signature = request.getParameter("signature");//΢�ż���ǩ��
		String timestamp = request.getParameter("timestamp");//ʱ���
		String nonce = request.getParameter("nonce");//�����
		String echostr = request.getParameter("echostr");//����ַ���
		//�������ɹ�������΢��
		if(CheckUtil.check(signature, timestamp, nonce)){
			System.out.println("΢����֤�ɹ�");
			return echostr;
		}
		else{
			System.out.println("����΢�ŷ�����������������С��");
			return null;
		}
	}
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, produces ="application/xml;charset=UTF-8")  
	public String test2(HttpServletRequest request,HttpServletResponse response)
	{
		try{
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			System.out.println("�������еķ���ͨ��post�������ܲ���");
			Map<String,String> map = MessageUtil.commandXmlToMap(request);
			if(map.get("action") != null)
			{
				if(Integer.parseInt(map.get("action")) == Command.ACTION_SENDNOTICE){
					CustomMessageContent content = new CustomMessageContent();
					content.setContent(String.valueOf(Character.toChars(0x2755))+"��������֪ͨ��<a href=\""+InterfaceUtil.get_AuthUrl(InterfaceUtil.INDEX+"WeChat/LookNotice?noticeId="+map.get("noticeId"))+"\">����鿴</a>");
					CustomMessage b = new CustomMessage();
					b.setTouser(map.get("userOpenId"));
					b.setMsgtype("text");
					b.setText(content);
					System.out.println("��ʼ���ͣ�"+JSONObject.fromObject(b).toString());
					JSONObject jsonObject = InterfaceUtil.doPost(InterfaceUtil.SEND_MESSAGE,JSONObject.fromObject(b).toString());
					System.out.println(jsonObject.toString());
					return null;
					
					
					/*CustomMessageContent content = new CustomMessageContent();
					content.setContent("֪ͨ����:"+map.get("noticeTitle")+"\n֪ͨ����:"+map.get("noticeContent"));
					CustomMessage b = new CustomMessage();
					b.setTouser(map.get("userOpenId"));
					b.setMsgtype("text");
					b.setText(content);
					System.out.println(JSONObject.fromObject(b).toString());
					InterfaceUtil.doPost(InterfaceUtil.SEND_MESSAGE,JSONObject.fromObject(b).toString());
					return null;*/
				}
			}
			String toUserName = map.get("ToUserName"); //������΢�ź�
			String fromUserName = map.get("FromUserName"); //���ͷ��ʺ� OPEN_ID
			System.out.println("OPEN_ID"+fromUserName);		
			String createTime = map.get("CreateTime"); //��Ϣ�Ĵ���ʱ�䣬ʱ�����ʽLong
			String msgType = map.get("MsgType"); //��Ϣ����
			String content = map.get("Content"); //��Ϣ����
			String msgId = map.get("MsgId"); //��ϢId
			if(msgType.equals(MessageUtil.MESSAGE_TEXT))
			{
				/*
				if(content.equals("1")){
					TextMessage textMessage = MessageUtil.assemblyTextMessage(fromUserName, toUserName, MessageUtil.MESSAGE_TEXT);
					textMessage.setContent("���ǵ�һ���ؼ���");
					String returnMessage = MessageUtil.textMessageToXml(textMessage);	
					System.out.println(returnMessage);
					return returnMessage;
				}
				
				else
				{
					TextMessage textMessage = MessageUtil.assemblyTextMessage(fromUserName, toUserName, MessageUtil.MESSAGE_TEXT);
					textMessage.setContent("����,������Ѿ�����ɽ��������ע���û�����Ҫ����ķ����������½� ��������->�ֲ��еİ�����ʾ");
					String returnMessage = MessageUtil.textMessageToXml(textMessage);	
					System.out.println(returnMessage);
					return returnMessage;
				}
				*/
				TextMessage textMessage = MessageUtil.assemblyTextMessage(fromUserName, toUserName, MessageUtil.MESSAGE_TEXT);
				textMessage.setContent("��л��ע�����������������ǵ���վ www.tsjtjyw.cn");
				String returnMessage = MessageUtil.textMessageToXml(textMessage);	
				System.out.println(returnMessage);
				return returnMessage;
			}
			else if(MessageUtil.MESSAGE_EVENT.equals(msgType)){
				String event = map.get("Event");
				if(MessageUtil.MESSAGE_SUBSCRIBE.equals(event)){
					MyFans myfans = new MyFans();
					myfans.setMyfansOpenId(fromUserName);
					wechatService.saveMyfans(myfans);
					TextMessage textMessage = MessageUtil.assemblyTextMessage(fromUserName, toUserName, MessageUtil.MESSAGE_TEXT);
					textMessage.setContent(MessageUtil.FACE_SMAIL+"���ã���ӭ��ע��ɽ��ͥ������������ּ��Ϊȫ�мҳ����Ĵ���һ�������ռ���ͥ����֪ʶ�������ҳ����ʡ�"
							+ "������ͥ������������Ҫ��������ָ���ƽ���ͥ����������غ���������");
					String returnMessage = MessageUtil.textMessageToXml(textMessage);	
					System.out.println(returnMessage);
					return returnMessage;
				}
				if(MessageUtil.MESSAGE_UNSUBSCRIBE.equals(event)){
					MyFans myfans = new MyFans();
					myfans.setMyfansOpenId(fromUserName);
					wechatService.removeMyfans(myfans);
					TextMessage textMessage = MessageUtil.assemblyTextMessage(fromUserName, toUserName, MessageUtil.MESSAGE_TEXT);
					textMessage.setContent("��ã���ӭ�㣬����ȡ����ע�󴥷����¼�");
					String returnMessage = MessageUtil.textMessageToXml(textMessage);	
					System.out.println(returnMessage);
					return returnMessage;
				}
				if(MessageUtil.MESSAGE_CLICK.equals(event)){
					TextMessage textMessage = MessageUtil.assemblyTextMessage(fromUserName, toUserName, MessageUtil.MESSAGE_TEXT);
					if(map.get("EventKey").equals("shouce"))
						textMessage.setContent("����"+MessageUtil.FACE_SMAIL+"���ɹ���ע΢�Ź��ں�֮���������������еİ󶨣������¼��ɽ���������ʺ�����"
								+ "������ʾ������һ���Ĳ������󶨳ɹ����Է������ѧУ��֪ͨ���鿴֪ͨ�ȵȣ������������ɽ�������û��ɵ����ҳ���Ͻ��е�ע�ᣬ��Ϊ���ǵ��û���");
					else if(map.get("EventKey").equals("lianxi"))
						textMessage.setContent("����"+MessageUtil.FACE_SMAIL+"���ͷ��绰��0315-3209678");
					else if(map.get("EventKey").equals("jianjie"))
						textMessage.setContent("����"+MessageUtil.FACE_SMAIL+"����ɽ��ͥ������������һ��Ϊ��������ѧ���ҳ��ṩ��������רҵ��"
								+ "�������Լ�ͥ�����������վ��ּ��Ϊȫ�мҳ����Ĵ���һ�������ռ���ͥ����֪ʶ�������ҳ����ʡ���߼�ͥ������������Ҫ��������"
								+ "ָ���ƽ���ͥ����������غ��������������Դ���רҵ��ͥ��������ṩȫ��λ�ҽ���Ѷ�����������գ��õĲ��������뽨��Ϊ���"
								+ "���ó�����ѧϰ��Ϊ��ͥϰ��ΪĿ�꣬�����ø������ļ�ͥ����Ϊʵ��ΰ���й��Ρ�������г��������ס�");
					String returnMessage = MessageUtil.textMessageToXml(textMessage);	
					System.out.println(returnMessage);
					return returnMessage;
				}
				if(MessageUtil.MESSAGE_VIEW.equals(event)){
					
					TextMessage textMessage = MessageUtil.assemblyTextMessage(fromUserName, toUserName, MessageUtil.MESSAGE_TEXT);
					textMessage.setContent(MessageUtil.FACE_SMAIL+"��ӭ������ҳ,����ҳ���»������ɡ�");
					String returnMessage = MessageUtil.textMessageToXml(textMessage);	
					System.out.println(returnMessage);
					return returnMessage;
				}
			}
			TextMessage textMessage = MessageUtil.assemblyTextMessage(fromUserName, toUserName, MessageUtil.MESSAGE_TEXT);
			textMessage.setContent("δ֪");
			String returnMessage = MessageUtil.textMessageToXml(textMessage);	
			System.out.println(returnMessage);
			return returnMessage;
		}
		catch(Exception e)
		{
			System.out.println("΢��post��������:"+e.getMessage());
			return null;
		}
	}
}
