package edu.iasd.wechat.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Scanner;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import edu.iasd.wechat.message.Custom;
import net.sf.json.JSONObject;

public class InterfaceUtil {

	
	public static final String GRANT_TYPE = "client_credential";
	public static final String APPID = "wx051803daed16572a";
	public static final String APPSECRET = "1a8c0a79b9b793134ac6facd2f5c2522";
	
	//»ñÈ¡access_tokenµÄurl getÇëÇó
	public static final String BASE_URL = "http://www.tsjtjyw.com/";
	//public static final String BASE_URL = "http://myapp.tunnel.qydev.com/tsjyw/";
	
	
	public static final String GETTOKENURL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	//Î¢ÐÅÉèÖÃÏÂÀ­²Ëµ¥µÄ½Ó¿Ú postÒ»¸öjson ·½·¨£ºbuttonUtil
	public static final String SETMENU = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
	//Î¢ÐÅµÄÈë¿ÚµØÖ·
	public static final String WEIXININDEX = BASE_URL + "Weixin/";
	
	
	//ģ����Ϣ
	public static final String TEMPLATE_MESSAGE = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";
	
	//ÏîÄ¿µÄµØÖ·
	public static final String INDEX = BASE_URL;
			
	public static final String UPLOADNEWS = "https://api.weixin.qq.com/cgi-bin/media/uploadnews?access_token=ACCESS_TOKEN";
	//Ìí¼Ó¿Í·þ
	public static final String ADD_CUSTOM = "https://api.weixin.qq.com/customservice/kfaccount/add?access_token=ACCESS_TOKEN";
	//»ñÈ¡È«²¿¿Í·þ
	public static final String GETALL_CUSTOM = "https://api.weixin.qq.com/cgi-bin/customservice/getkflist?access_token=ACCESS_TOKEN";
	
	//¿Í·þ·¢ËÍÏûÏ¢
	public static final String SEND_MESSAGE = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESS_TOKEN";

	
	//ÎÄÕÂ×ÊÑ¶Ê×Ò³
	public static final String ARTICLE_URL = BASE_URL + "WeChat/WeChatArticle";
	//½ñÈÕÈÈµã
	public static final String TODAY_HOT = BASE_URL+"WeChat/ShowModule?moduleId=35";
	//ÏÖ´ú¼Ò½Ì
	public static final String MODERN_EDU = BASE_URL+"WeChat/ShowModule?moduleId=2";
	//Ñ§Ï°Ô°µØ
	public static final String PLACE_EDU = BASE_URL+"WeChat/ShowModule?moduleId=4";
	//ÐÄÀí½ÌÓý
	public static final String HEART_EDU = BASE_URL+"WeChat/ShowModule?moduleId=3";
	//´«Í³½ÌÓý
	public static final String TRADITIONAL_EDU = BASE_URL+"WeChat/ShowModule?moduleId=1";
	
	
	//ÃûÊ¦ÔÚÏß
	public static final String FAMOUSTEACHER_URL = BASE_URL + "WeChat/WeChatFamousTeacher";
	//Ö¾Ô¸ÕßÔÚÏß
	public static final String VOLUNTEER_URL = BASE_URL + "WeChat/WeChatVolunteer";
	//ÎÒÒªÌáÎÊ
	public static final String ASKQUESTION_URL = BASE_URL + "WeChat/WeChatAskQuestion?respUserId=all";
	//·¢Í¨Öª
	public static final String SENDNOTICE_URL = BASE_URL + "WeChat/SendNotice";
	//¿´Í¨Öª
	public static final String LOOKNOTICE_URL = BASE_URL + "WeChat/RecvNotice";
	public static final String NOTICE_SYSTEM = BASE_URL + "WeChat/WeChatNoticeSystem";

	
	
	
	
	
	//²Ëµ¥viewµÄurl
	public static final String AUTHORIZE_URL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";
	
	//»ñÈ¡codeºó£¬ÇëÇóÒÔÏÂÁ´½Ó»ñÈ¡access_token£º »ñÈ¡ÓÃ»§ÐÅÏ¢µÄurl
	public static final String GET_AUTH_ACCESS_TOKEN = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
	
	
	public static final String get_AuthUrl(String url)
	{
		String redi_url;
		try {
			redi_url = java.net.URLDecoder.decode(url,"UTF-8");
			System.out.println("url:" + url);
			System.out.println("re_url:" + redi_url);
			return AUTHORIZE_URL.replace("APPID",APPID).replace("REDIRECT_URI",redi_url).replace("SCOPE", "snsapi_userinfo").replace("STATE", "zhang123");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return AUTHORIZE_URL.replace("APPID",APPID).replace("REDIRECT_URI","http%3a%2f%2fmyapp.tunnel.qydev.com%2ftsjyw%2fWeChat%2fgetCode").replace("SCOPE", "snsapi_userinfo").replace("STATE", "zhang123");
	}
	
	/*
	 * HTTP/1.1 200 OK
		Response content: {"access_token":"4c1kShp6adel1GYKdlHy5kic48ECzvfKVu6pMcTe_arwsejka6fOHyc8MCZc0yswt0SxhmB9DM7c2ns-H_VpnqDdjGg4vFWSQZZo7ykY2SEZTkjgbVdZNt1LZM7hPkCbSBXiACABKG",
		"expires_in":7200}
	 */
	//Õâ¸ö·½·¨½ö½öÓÃÀ´ »ñÈ¡ access_token
	public static JSONObject doGetStr() throws IOException
	{
		CloseableHttpClient httpclient = HttpClients.createDefault();
	    HttpGet httpGet = new HttpGet(GETTOKENURL.replace("client_credential", GRANT_TYPE).replace("APPID",APPID).replace("APPSECRET", APPSECRET));
	    CloseableHttpResponse response = httpclient.execute(httpGet);
	    try {
	    	// ´òÓ¡ÏìÓ¦×´Ì¬    
            System.out.println(response.getStatusLine()); 
	    	//»ñÈ¡ ÏìÓ¦ÊµÌå
	        HttpEntity entity = response.getEntity();
	        if(response.getStatusLine() != null){
	        	JSONObject jsonObject = JSONObject.fromObject(EntityUtils.toString(entity));
		        return jsonObject;
	        }
	        return null;
	    } 
	    catch(Exception e){
	    	e.printStackTrace();
	    }
	    finally {
	        response.close();
	    }
		return null;
	}
	
	//Í¨ÓÃµÄpost·½·¨£¬urlÎªÒÔÉÏµÄ¾²Ì¬³£Á¿£¬outstrÎªpost json¸ñÊ½µÄ×Ö·û´®
	public static JSONObject doPost(String url,String outStr) throws IOException
	{
		CloseableHttpClient httpclient = HttpClients.createDefault();
		String access_token = null;
		access_token = getAccessToken();
		if(access_token != null)
		{
			HttpPost httpPost = new HttpPost(url.replace("ACCESS_TOKEN",access_token));
			httpPost.setEntity(new StringEntity(outStr,"UTF-8"));
			CloseableHttpResponse response = httpclient.execute(httpPost);
			String result = EntityUtils.toString(response.getEntity(),"UTF-8");
			JSONObject respJson = JSONObject.fromObject(result);
			return respJson;
		}
		return null;
	}
	
	//Í¨ÓÃµÄget·½·¨£¬urlÎªÒÔÉÏµÄ¾²Ì¬³£Á¿£¬outstrÎªpost json¸ñÊ½µÄ×Ö·û´®
	public static JSONObject doGet(String url,String outStr) throws IOException
	{
		CloseableHttpClient httpclient = HttpClients.createDefault();
		String access_token = getAccessToken();
		
		if(access_token != null)
		{
			HttpGet httpGet = new HttpGet(url.replace("ACCESS_TOKEN",access_token));
			CloseableHttpResponse response = httpclient.execute(httpGet);
			String result = EntityUtils.toString(response.getEntity(),"UTF-8");
			JSONObject respJson = JSONObject.fromObject(result);
			return respJson;
		}
		return null;
	}
	
	
	//Õâ¸ö·½·¨½öÓÃÓÚ¶¨Ê±ÈÎÎñ¸øÎ¢ÐÅ½Ó¿Ú´«µÝÐÅÏ¢
	public static JSONObject doPostWechat(String url,String outStr) throws IOException
	{
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		httpPost.setEntity(new StringEntity(outStr,"UTF-8"));
		CloseableHttpResponse response = httpclient.execute(httpPost);
		return null;
	}
	
	//Ìí¼Ó¿Í·þÕËºÅ
	public static boolean addCustom(Custom custom) throws IOException
	{
		System.out.println("Ìí¼Ó¿Í·þ"+JSONObject.fromObject(custom).toString());
		JSONObject jsonObject = doPost(ADD_CUSTOM,JSONObject.fromObject(custom).toString());
		if(jsonObject != null){
			if(jsonObject.getInt("errcode") == 0){
				System.out.println("³É¹¦");
				return true;
			}
			else{
				System.out.println(jsonObject.getInt("errcode"));
				System.out.println(jsonObject.getString("errmsg"));
				return false;
			}
		}
		return false;
	}
	

	//»ñÈ¡accesstokenµÄ·½·¨£¬±£´æÖÁÎÄ¼þ£¬access_token¹ýÆÚÊ±¼ä2Ð¡Ê±£¬Ã¿Ìì¿ÉÒÔ»ñÈ¡Á½´Î
	public static String getAccessToken()
	{
		try{
			File file = new File("access_token.json");
			if(!file.exists()){
				file.createNewFile();
				JSONObject jsonObject = doGetStr();
				jsonObject.element("oldtime",new Date().getTime());
				FileWriter filewriter = new FileWriter(file.getName());
				BufferedWriter writer = new BufferedWriter(filewriter);
				writer.write(jsonObject.toString());
				writer.close();
				return jsonObject.getString("access_token");
			}
			else{
				StringBuffer sb = new StringBuffer();
				Scanner scanner = new Scanner(file,"UTF-8");
				while (scanner.hasNextLine()) {
	                sb.append(scanner.nextLine());
	            }
				JSONObject jsonObject = JSONObject.fromObject(sb.toString());
				if(new Date().getTime() - jsonObject.getLong("oldtime") > 7000000){
					JSONObject json = doGetStr();
					json.element("oldtime",new Date().getTime());
					FileWriter filewriter = new FileWriter(file.getName());
					BufferedWriter writer = new BufferedWriter(filewriter);
					writer.write(json.toString());
					writer.close();
					return json.getString("access_token");
				}
				else{
					return jsonObject.getString("access_token");
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	
	//ÓÃ»§ÊÚÈ¨ºó£¬Í¨¹ýcode»ñÈ¡accessºÍrefresh,ÍøÒ³ÊÚÈ¨½Ó¿Úµ÷ÓÃÆ¾Ö¤,×¢Òâ£º´Ëaccess_tokenÓë»ù´¡Ö§³ÖµÄaccess_token²»Í¬
	public static JSONObject getOathAccess_token(String code) throws Exception
	{
		/*
		 * { "access_token":"ACCESS_TOKEN",    
			 "expires_in":7200,    
			 "refresh_token":"REFRESH_TOKEN",    
			 "openid":"OPENID",    
			 "scope":"SCOPE" } 
		 */
		
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(GET_AUTH_ACCESS_TOKEN.replace("APPID",APPID).replace("SECRET",APPSECRET).replace("CODE",code));
		CloseableHttpResponse response = httpclient.execute(httpGet);
		String result = EntityUtils.toString(response.getEntity(),"UTF-8");
		JSONObject respJson = JSONObject.fromObject(result);
		return respJson;
	}

	
}
