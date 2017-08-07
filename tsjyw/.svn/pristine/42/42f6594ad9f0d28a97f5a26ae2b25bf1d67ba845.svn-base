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
		System.out.println("微信成功进入验证控制器");
		String signature = request.getParameter("signature");//微信加密签名
		String timestamp = request.getParameter("timestamp");//时间戳
		String nonce = request.getParameter("nonce");//随机数
		String echostr = request.getParameter("echostr");//随机字符串
		//如果检验成功，接入微信
		if(CheckUtil.check(signature, timestamp, nonce)){
			System.out.println("微信验证成功");
			return echostr;
		}
		else{
			System.out.println("不是微信服务器发来的请求，请小心");
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
			System.out.println("控制器中的方法通过post方法接受参数");
			Map<String,String> map = MessageUtil.commandXmlToMap(request);
			if(map.get("action") != null)
			{
				if(Integer.parseInt(map.get("action")) == Command.ACTION_SENDNOTICE){
					CustomMessageContent content = new CustomMessageContent();
					content.setContent(String.valueOf(Character.toChars(0x2755))+"有您的新通知，<a href=\""+InterfaceUtil.get_AuthUrl(InterfaceUtil.INDEX+"WeChat/LookNotice?noticeId="+map.get("noticeId"))+"\">点击查看</a>");
					CustomMessage b = new CustomMessage();
					b.setTouser(map.get("userOpenId"));
					b.setMsgtype("text");
					b.setText(content);
					System.out.println("开始发送："+JSONObject.fromObject(b).toString());
					JSONObject jsonObject = InterfaceUtil.doPost(InterfaceUtil.SEND_MESSAGE,JSONObject.fromObject(b).toString());
					System.out.println(jsonObject.toString());
					return null;
					
					
					/*CustomMessageContent content = new CustomMessageContent();
					content.setContent("通知标题:"+map.get("noticeTitle")+"\n通知内容:"+map.get("noticeContent"));
					CustomMessage b = new CustomMessage();
					b.setTouser(map.get("userOpenId"));
					b.setMsgtype("text");
					b.setText(content);
					System.out.println(JSONObject.fromObject(b).toString());
					InterfaceUtil.doPost(InterfaceUtil.SEND_MESSAGE,JSONObject.fromObject(b).toString());
					return null;*/
				}
			}
			String toUserName = map.get("ToUserName"); //开发者微信号
			String fromUserName = map.get("FromUserName"); //发送方帐号 OPEN_ID
			System.out.println("OPEN_ID"+fromUserName);		
			String createTime = map.get("CreateTime"); //消息的创建时间，时间戳形式Long
			String msgType = map.get("MsgType"); //消息类型
			String content = map.get("Content"); //消息内容
			String msgId = map.get("MsgId"); //消息Id
			if(msgType.equals(MessageUtil.MESSAGE_TEXT))
			{
				/*
				if(content.equals("1")){
					TextMessage textMessage = MessageUtil.assemblyTextMessage(fromUserName, toUserName, MessageUtil.MESSAGE_TEXT);
					textMessage.setContent("这是第一个关键字");
					String returnMessage = MessageUtil.textMessageToXml(textMessage);	
					System.out.println(returnMessage);
					return returnMessage;
				}
				
				else
				{
					TextMessage textMessage = MessageUtil.assemblyTextMessage(fromUserName, toUserName, MessageUtil.MESSAGE_TEXT);
					textMessage.setContent("您好,如果您已经是唐山教育网的注册用户或需要更多的服务，请点击右下角 关于我们->手册中的帮助提示");
					String returnMessage = MessageUtil.textMessageToXml(textMessage);	
					System.out.println(returnMessage);
					return returnMessage;
				}
				*/
				TextMessage textMessage = MessageUtil.assemblyTextMessage(fromUserName, toUserName, MessageUtil.MESSAGE_TEXT);
				textMessage.setContent("感谢关注，更多服务请访问我们的网站 www.tsjtjyw.cn");
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
					textMessage.setContent(MessageUtil.FACE_SMAIL+"您好，欢迎关注唐山家庭教育网！我们旨在为全市家长精心打造一个宣传普及家庭教育知识、提升家长素质、"
							+ "提升家庭教育质量的重要场所，是指导推进家庭教育的主阵地和主渠道。");
					String returnMessage = MessageUtil.textMessageToXml(textMessage);	
					System.out.println(returnMessage);
					return returnMessage;
				}
				if(MessageUtil.MESSAGE_UNSUBSCRIBE.equals(event)){
					MyFans myfans = new MyFans();
					myfans.setMyfansOpenId(fromUserName);
					wechatService.removeMyfans(myfans);
					TextMessage textMessage = MessageUtil.assemblyTextMessage(fromUserName, toUserName, MessageUtil.MESSAGE_TEXT);
					textMessage.setContent("你好，欢迎你，这是取消关注后触发的事件");
					String returnMessage = MessageUtil.textMessageToXml(textMessage);	
					System.out.println(returnMessage);
					return returnMessage;
				}
				if(MessageUtil.MESSAGE_CLICK.equals(event)){
					TextMessage textMessage = MessageUtil.assemblyTextMessage(fromUserName, toUserName, MessageUtil.MESSAGE_TEXT);
					if(map.get("EventKey").equals("shouce"))
						textMessage.setContent("您好"+MessageUtil.FACE_SMAIL+"，成功关注微信公众号之后，请点击帮助中心中的绑定，输入登录唐山教育网的帐号密码"
								+ "，按提示进行下一步的操作，绑定成功后以方便接受学校的通知、查看通知等等，如果您不是唐山教育网用户可点击首页右上角中的注册，成为我们的用户。");
					else if(map.get("EventKey").equals("lianxi"))
						textMessage.setContent("您好"+MessageUtil.FACE_SMAIL+"，客服电话：0315-3209678");
					else if(map.get("EventKey").equals("jianjie"))
						textMessage.setContent("您好"+MessageUtil.FACE_SMAIL+"，唐山家庭教育服务网是一家为我市所有学生家长提供高质量、专业化"
								+ "、公益性家庭教育服务的网站，旨在为全市家长精心打造一个宣传普及家庭教育知识、提升家长素质、提高家庭教育质量的重要场所，是"
								+ "指导推进家庭教育的主阵地和主渠道。我们以传递专业家庭教育理念，提供全方位家教资讯，分享易吸收，好的操作方法与建议为理念；"
								+ "把让持续的学习成为家庭习惯为目标，最终用高质量的家庭教育为实现伟大中国梦、创建和谐社会做贡献。");
					String returnMessage = MessageUtil.textMessageToXml(textMessage);	
					System.out.println(returnMessage);
					return returnMessage;
				}
				if(MessageUtil.MESSAGE_VIEW.equals(event)){
					
					TextMessage textMessage = MessageUtil.assemblyTextMessage(fromUserName, toUserName, MessageUtil.MESSAGE_TEXT);
					textMessage.setContent(MessageUtil.FACE_SMAIL+"欢迎进入首页,本首页上下滑动即可。");
					String returnMessage = MessageUtil.textMessageToXml(textMessage);	
					System.out.println(returnMessage);
					return returnMessage;
				}
			}
			TextMessage textMessage = MessageUtil.assemblyTextMessage(fromUserName, toUserName, MessageUtil.MESSAGE_TEXT);
			textMessage.setContent("未知");
			String returnMessage = MessageUtil.textMessageToXml(textMessage);	
			System.out.println(returnMessage);
			return returnMessage;
		}
		catch(Exception e)
		{
			System.out.println("微信post方法报错:"+e.getMessage());
			return null;
		}
	}
}
