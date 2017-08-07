package edu.iasd.wechat.utils;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.Attribute;

import edu.iasd.pojo.Article;
import edu.iasd.pojo.ViewArticle;
import edu.iasd.wechat.message.Command;
import edu.iasd.wechat.message.News;
import edu.iasd.wechat.message.NewsMessage;
import edu.iasd.wechat.message.TextMessage;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

public class MessageUtil {
	
	public static final String MESSAGE_TEXT = "text";
	public static final String MESSAGE_IMAGE = "image";
	public static final String MESSAGE_VOICE = "voice";
	public static final String MESSAGE_VIDEO = "video";
	public static final String MESSAGE_LINK = "link";
	public static final String MESSAGE_LOCATION = "location";
	public static final String MESSAGE_EVENT = "event";
	public static final String MESSAGE_SUBSCRIBE = "subscribe";
	public static final String MESSAGE_UNSUBSCRIBE = "unsubscribe";
	public static final String MESSAGE_CLICK = "CLICK";
	public static final String MESSAGE_VIEW = "VIEW";
	public static final String MESSAGE_NEWS = "news";
	
	//表情
	public static final String FACE_SMAIL = String.valueOf(Character.toChars(0x1F603)); 
	
	
	public static Map<String,String> xmlToMap(HttpServletRequest request) throws Exception
	{
		Map<String,String> map = new HashMap<String,String>();
		
		SAXReader reader = new SAXReader();
		InputStream ins = request.getInputStream();
		
		Document doc = reader.read(ins);
		Element root = doc.getRootElement();
		List<Element> list = root.elements();
		for(Element e:list)
		{
			
			map.put(e.getName(),e.getText());
		}
		ins.close();
		return map;
	}
	public static String textMessageToXml(TextMessage e)
	{
		XStream xstream=new XStream(new StaxDriver());
		xstream.alias("xml",e.getClass());
		return xstream.toXML(e);
	}
	public static TextMessage assemblyTextMessage(String fromUserName,String toUserName,String msgType)
	{
		TextMessage textMessage = new TextMessage();
		textMessage.setToUserName(fromUserName);
		textMessage.setCreateTime(new Date().getTime());
		textMessage.setFromUserName(toUserName);
		textMessage.setMsgType(msgType);
		return textMessage;
	}
	public static String createNewsMessage(String fromUserName,String toUserName,String msgType)
	{
		//String returnMessage = null;
		List<News> a = new ArrayList<News>();
		News news = new News();
		news.setTitle("title标题");
		news.setDescription("description描述");
		news.setPicUrl(InterfaceUtil.INDEX+"images/1.jpg");
		news.setUrl("www.baidu.com");
		a.add(news);
		NewsMessage newsMessage = new NewsMessage();
		newsMessage.setArticleCount(a.size());
		newsMessage.setArticles(a);
		newsMessage.setCreateTime(new Date().getTime());
		newsMessage.setFromUserName(toUserName);
		newsMessage.setToUserName(fromUserName);
		newsMessage.setMsgType(msgType);
		return newsMessageToXml(newsMessage);
	}
	
	
	
	public static String replyNewsMessage(String fromUserName,String toUserName,String msgType,ViewArticle viewArticle)
	{
		//String returnMessage = null;
		List<News> a = new ArrayList<News>();
		News news = new News();
		news.setTitle(viewArticle.getArticleTitle());
		news.setDescription(viewArticle.getArticleContent());
		news.setPicUrl("http://myapp.tunnel.qydev.com/tsjyw/images/1.jpg");
		news.setUrl("www.baidu.com");
		a.add(news);
		NewsMessage newsMessage = new NewsMessage();
		newsMessage.setArticleCount(a.size());
		newsMessage.setArticles(a);
		newsMessage.setCreateTime(new Date().getTime());
		newsMessage.setFromUserName(toUserName);
		newsMessage.setToUserName(fromUserName);
		newsMessage.setMsgType(msgType);
		return newsMessageToXml(newsMessage);
	}
	
	public static String newsMessageToXml(NewsMessage e)
	{
		XStream xstream=new XStream(new StaxDriver());
		xstream.alias("xml",e.getClass());
		xstream.alias("item",e.getArticles().get(0).getClass());
		return xstream.toXML(e);
	}
	
	public static String commandToXml(Command e)
	{
		XStream xstream=new XStream(new StaxDriver());
		xstream.alias("xml",e.getClass());
		return xstream.toXML(e);
	}
	
	//通用的 xml转map的方法
	public static Map<String,String>  commandXmlToMap(HttpServletRequest request) throws Exception{  
        //创建SAXReader对象  
		Map<String,String> map = new HashMap<String,String>();
		SAXReader reader = new SAXReader();
		InputStream ins = request.getInputStream();
		Document doc = reader.read(ins);
		Element root = doc.getRootElement();
        //获取根节点元素对象  
        //遍历  
        listNodes(root,map); 
        return map;
    }  
      
    //遍历当前节点下的所有节点  
    public static void listNodes(Element node,Map<String,String> map){  
        System.out.println("当前节点的名称：" + node.getName());  
        //首先获取当前节点的所有属性节点  
        List<Attribute> list = node.attributes();  
        //遍历属性节点  
        for(Attribute attribute : list){  
            System.out.println("属性"+attribute.getName() +":" + attribute.getValue());  
        }  
        //如果当前节点内容不为空，则输出  
        if(!(node.getTextTrim().equals(""))){  
             System.out.println( node.getName() + "：" + node.getText());    
        }  
        //同时迭代当前节点下面的所有子节点  
        //使用递归  
        Iterator<Element> iterator = node.elementIterator();  
        while(iterator.hasNext()){  
            Element e = iterator.next();  
            map.put(e.getName(),e.getText());
            listNodes(e,map);  
        }  
    }  
	public static String getTemplateMessage(String openId,String url,String firstValue,String Keyword1Value,String Keyword2Value,String Keyword3Value,String Keyword4Value,String remarkValue)
	{
		String aa =  "{\"touser\":\""+openId+"\","
		           +"\"template_id\":\"Jy0toPTAh5YzYGxGWmEU63gr00qFJDDzrToZlK6VDvM\","
		           +"\"url\":\""+url+"\","          
		           +"\"data\":{"
		                   +"\"first\": {"
		                       +"\"value\":\""+firstValue+"\","
		                       +"\"color\":\"#173177\""
		                   +"},"
		                   +"\"keyword1\":{"
		                       +"\"value\":\""+Keyword1Value+"\","
		                       +"\"color\":\"#173177\""
		                   +"},"
		                   +"\"keyword2\": {"
		                       +"\"value\":\""+Keyword2Value+"\","
		                       +"\"color\":\"#173177\""
		                   +"},"
		                   +"\"keyword3\": {"
		                       +"\"value\":\""+Keyword3Value+"\","
		                       +"\"color\":\"#173177\""
		                   +"},"
		                   +"\"keyword4\": {"
	                       +"\"value\":\""+Keyword4Value+"\","
	                       +"\"color\":\"#173177\""
	                       +"},"
		                   +"\"remark\":{"
		                       +"\"value\":\""+remarkValue+"\","
		                       +"\"color\":\"#173177\""
		                   +"}"
		           +"}"
		       +"}";
		
		return aa;
	}
	
}
