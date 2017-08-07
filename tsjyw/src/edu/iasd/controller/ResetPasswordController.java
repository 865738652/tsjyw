package edu.iasd.controller;


import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


import edu.iasd.pojo.ViewUser;
import edu.iasd.service.UserService;


@Controller
public class ResetPasswordController extends ControllerBase{
	
	@Autowired
	private UserService userService;
	
	private static String protocol="smtp";
    private static String from="15633936860@163.com";
    private static String to="";
    private static String body="";
    private static String subject="唐山家庭教育网找回密码";
    private static String server="smtp.163.com";
    private static String username="15633936860";//from mail name
    private static String password="zhao123456";//from mail password
    
	@RequestMapping("ResetPassword")
	public String showAll(){
		return "GetPassword";
	}

	@RequestMapping(value="ResetPassword/SendEmail")
	public String sendEmail(HttpServletRequest request, String account, String email)
	{
		try{
			ViewUser u = userService.getUserByAccount(account);
			if (u == null || !u.getUserEmail().equals(email))
				throw new Exception("你不会把账号忘记了吧");
			String pwd = "" + getRandNum(1,999999);
			userService.changePassword(u.getUserId(), pwd);
			to = email;
			body = "您的密码已改为"+pwd;
			
			Properties prop=new Properties();
	        prop.setProperty("mail.transport.protocol",protocol);
	        prop.setProperty("mail.smtp.auth","true");
	        
	        Session session=Session.getInstance(prop,new Authenticator(){//用户连接认证
	            public PasswordAuthentication getPasswordAuthentication(){
	                return new PasswordAuthentication(username,password);
	            }
	        });
	        session.setDebug(true);//开启调试
	        
	        MimeMessage message=new MimeMessage(session);
	        message.setFrom(new InternetAddress(from));
	        message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(to));
	        message.setSubject(subject);
	        message.setSentDate(new Date());
	        message.setText(body);//发送纯文本消息
	        message.saveChanges();//保存消息
	        
	        Transport trans=session.getTransport();
	        trans.connect(server,username,password);
	        trans.sendMessage(message,message.getRecipients(Message.RecipientType.TO));//发送
	        trans.close();
		}
		catch (Exception e) {
			return "ErrorReset";
		}
		return "Reset";
	}
	
	public static int getRandNum(int min, int max) {
	    int randNum = min + (int)(Math.random() * ((max - min) + 1));
	    return randNum;
	}

}


