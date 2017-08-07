package edu.iasd.wechat.utils;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;
import edu.iasd.wechat.menu.Button;
import edu.iasd.wechat.menu.ClickButton;
import edu.iasd.wechat.menu.Menu;
import edu.iasd.wechat.menu.ViewButton;

public class ButtonUtil {
	public static final String CLICK = "click";
	public static final String VIEW = "view";
	
	//1、自定义菜单最多包括3个一级菜单，每个一级菜单最多包含5个二级菜单。
	//2、一级菜单最多4个汉字，二级菜单最多7个汉字，多出来的部分将会以“...”代替。
	public static JSONObject setMeun()
	{
		
		ViewButton a = new ViewButton();
		a.setName("微主页");
		a.setUrl(InterfaceUtil.ARTICLE_URL);
		a.setSub_button(null);
		a.setType(ButtonUtil.VIEW);
		/*
		ViewButton aa = new ViewButton();
		aa.setName("通知系统");
		aa.setType(ButtonUtil.VIEW);
		aa.setUrl(InterfaceUtil.NOTICE_SYSTEM);
		aa.setSub_button(null);*/
		/*
		Button d = new Button();
		d.setName("微主页");
		
		ViewButton d1 = new ViewButton();
		d1.setName("关于我们");
		d1.setUrl(InterfaceUtil.FAMOUSTEACHER_URL);
		d1.setType(ButtonUtil.VIEW);
		d1.setSub_button(null);
		
		ViewButton d2 = new ViewButton();
		d2.setName("志愿者在线");
		d2.setUrl(InterfaceUtil.VOLUNTEER_URL);
		d2.setType(ButtonUtil.VIEW);
		d2.setSub_button(null);
		
		ViewButton d3 = new ViewButton();
		d3.setName("我要提问");
		d3.setUrl(InterfaceUtil.get_AuthUrl(InterfaceUtil.ASKQUESTION_URL));
		d3.setType(ButtonUtil.VIEW);
		d3.setSub_button(null);
		
		Button[] dd = new Button[]{d1,d2,d3};
		d.setSub_button(dd);*/
		/*
		Button b = new Button();
		b.setName("教育园地");
		
		ViewButton jinri = new ViewButton();
		jinri.setName("今日热点");
		jinri.setUrl(InterfaceUtil.TODAY_HOT);
		jinri.setType(ButtonUtil.VIEW);
		jinri.setSub_button(null);
		
		ViewButton xiandai = new ViewButton();
		xiandai.setName("现代家教");
		xiandai.setUrl(InterfaceUtil.MODERN_EDU);
		xiandai.setType(ButtonUtil.VIEW);
		xiandai.setSub_button(null);
		
		ViewButton xinli = new ViewButton();
		xinli.setName("心理教育");
		xinli.setUrl(InterfaceUtil.HEART_EDU);
		xinli.setType(ButtonUtil.VIEW);
		xinli.setSub_button(null);
		
		ViewButton chuantong = new ViewButton();
		chuantong.setName("传统教育");
		chuantong.setUrl(InterfaceUtil.TRADITIONAL_EDU);
		chuantong.setType(ButtonUtil.VIEW);
		chuantong.setSub_button(null);
		
		ViewButton xuexi = new ViewButton();
		xuexi.setName("学习园地");
		xuexi.setUrl(InterfaceUtil.PLACE_EDU);
		xuexi.setType(ButtonUtil.VIEW);
		xuexi.setSub_button(null);
		
		Button[] sd = new Button[5];
		sd[0] = jinri;
		sd[1] = xiandai;
		sd[2] = xinli;
		sd[3] = chuantong;
		sd[4] = xuexi;
		b.setSub_button(sd);
		*/
		Button d = new Button();
		d.setName("互动交流");
		
		ViewButton d1 = new ViewButton();
		d1.setName("名师在线");
		d1.setUrl(InterfaceUtil.FAMOUSTEACHER_URL);
		d1.setType(ButtonUtil.VIEW);
		d1.setSub_button(null);
		
		ViewButton d2 = new ViewButton();
		d2.setName("志愿者在线");
		d2.setUrl(InterfaceUtil.VOLUNTEER_URL);
		d2.setType(ButtonUtil.VIEW);
		d2.setSub_button(null);
		
		ViewButton d3 = new ViewButton();
		d3.setName("我要提问");
		d3.setUrl(InterfaceUtil.get_AuthUrl(InterfaceUtil.ASKQUESTION_URL));
		d3.setType(ButtonUtil.VIEW);
		d3.setSub_button(null);
		
		
		
		Button[] dd = new Button[]{d1,d2,d3};
		d.setSub_button(dd);
		
		ViewButton d4 = new ViewButton();
		d4.setName("通知系统");
		d4.setUrl(InterfaceUtil.get_AuthUrl(InterfaceUtil.NOTICE_SYSTEM));
		d4.setType(ButtonUtil.VIEW);
		d4.setSub_button(null);
		
		
		Menu menu = new Menu();
		Button[] buttons = new Button[3];
		buttons[0] = a;
	
		buttons[1] = d;
		buttons[2] = d4;
		menu.setButton(buttons);
		
		PropertyFilter filter = new PropertyFilter(){
			@Override
			public boolean apply(Object object, String objectName, Object objectValue) {
				/*
				 * arg0属性所在的对象
				 * arg1属性的名字
				 * arg2属性值
				 * 当没有设置JsonPropertyFilter时，跳过该代码段。如果设置了，就调用apply方法，返回false时，跳过该代码段，
				 * 当apply方法返回true时跳出该循环（即不解析该属性，直接处理下一属性）。
				 */
				if(objectValue == null)
					return true;
				else
					return false;
			}
			
		};
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setJsonPropertyFilter(filter);
		JSONObject v = JSONObject.fromObject(menu, jsonConfig);
		System.out.println(v.toString());
		return v;
	}
}
