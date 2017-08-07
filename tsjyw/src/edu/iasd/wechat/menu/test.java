package edu.iasd.wechat.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import net.sf.json.JSONObject;
import edu.iasd.wechat.message.CustomMessage;
import edu.iasd.wechat.message.CustomMessageContent;
import edu.iasd.wechat.message.TextMessage;
import edu.iasd.wechat.utils.ButtonUtil;
import edu.iasd.wechat.utils.InterfaceUtil;
import edu.iasd.wechat.utils.MessageUtil;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			
		/*閿熼叺鍑ゆ嫹妯″紡閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷锋伅
		CustomMessageContent content = new CustomMessageContent();
		String bike = String.valueOf(Character.toChars(0x1f604));  
		content.setContent("hahahahahah\nqweqweq"+bike);
		CustomMessage b = new CustomMessage();
		b.setTouser("oXzcYwThiw4-_NERnLdfITBI9VEU");
		
		b.setMsgtype("text");
		b.setText(content);
		InterfaceUtil.doPost(InterfaceUtil.SEND_MESSAGE,JSONObject.fromObject(b).toString());*/
			
		//閿熸枻鎷烽敓鏂ゆ嫹get access_token
		//System.out.println(InterfaceUtil.getAccessToken());
			
		//System.out.println(ButtonUtil.setMeun().toString());
			
		//閿熸枻鎷烽敓鐭彍纰夋嫹
		System.out.println(InterfaceUtil.doPost(InterfaceUtil.SETMENU,ButtonUtil.setMeun().toString()).toString());
		
		

		
		//System.out.println(InterfaceUtil.doPost(InterfaceUtil.TEMPLATE_MESSAGE,
		//MessageUtil.getTemplateMessage
		//("oe7RnvyGXh452hXQxD3S6N5Ew5j0","http://www.baidu.com", "1", "2", "3", "4", "5", "6")));
		
			
			
			
		//閿熸枻鎷峰彇access_token閿熸枻鎷烽敓鏂ゆ嫹
		//System.out.println(InterfaceUtil.getAccessToken());
		
		//{"button":[{"name":"妫ｆ牠銆�,"type":"view","url":"http://myapp.tunnel.qydev.com/tsjyw/WeChat/WeChatArticle"},{"name":"娴滄帒濮╂禍銈嗙ウ","sub_button":[{"name":"閸氬秴绗�崷銊у殠","type":"view","url":"http://myapp.tunnel.qydev.com/tsjyw/WeChat/WeChatFamousTeacher"},{"name":"韫囨鍔归懓锟�"type":"view","url":"http://myapp.tunnel.qydev.com/tsjyw/WeChat/WeChatVolunteer"},{"name":"閹存垼顩﹂幓鎰版６","type":"view","url":"https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxdb5d535f4533c89b&redirect_uri=http://myapp.tunnel.qydev.com/tsjyw/WeChat/WeChatAskQuestion?respUserId=all&response_type=code&scope=snsapi_userinfo&state=zhang123#wechat_redirect"},{"name":"閸欐垿锟介柅姘辩叀","url":"https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxdb5d535f4533c89b&redirect_uri=http://myapp.tunnel.qydev.com/tsjyw/WeChat/SendNotice&response_type=code&scope=snsapi_userinfo&state=zhang123#wechat_redirect"},{"name":"閺屻儳婀呴柅姘辩叀","type":"view","url":"https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxdb5d535f4533c89b&redirect_uri=http://myapp.tunnel.qydev.com/tsjyw/WeChat/RecvNotice&response_type=code&scope=snsapi_userinfo&state=zhang123#wechat_redirect"}]},{"name":"閺佹瑨鍋涢崶顓炴勾","sub_button":[{"name":"娴犲﹥妫╅悜顓犲仯","type":"view","url":"http://myapp.tunnel.qydev.com/tsjyw/WeChat/ShowModule?moduleId=35"},{"name":"閻滈鍞�鑸垫殌","type":"view","url":"http://myapp.tunnel.qydev.com/tsjyw/WeChat/ShowModule?moduleId=2"},{"name":"韫囧啰鎮婇弫娆掑仜","type":"view","url":"http://myapp.tunnel.qydev.com/tsjyw/WeChat/ShowModule?moduleId=3"},{"name":"娴肩姷绮洪弫娆掑仜","type":"view","url":"http://myapp.tunnel.qydev.com/tsjyw/WeChat/ShowModule?moduleId=1"},{"name":"鐎涳缚绡勯崶顓炴勾","type":"view","url":"http://myapp.tunnel.qydev.com/tsjyw/WeChat/ShowModule?moduleId=4"}]}]}
        //閿熸枻鎷烽敓鏂ゆ嫹list閿熸枻鎷烽敓鏂ゆ嫹鏆敓鏂ゆ嫹閿燂拷
		

			/*閿熻瀹氳鎷烽敓鏂ゆ嫹
		String content = "name:zhangtao,code:000121";
		Pattern pattern = Pattern.compile("[, :]+");
		String[] strs = pattern.split(content);
		System.out.println(strs.length);
		for(int i=0;i<strs.length;i++){
			System.out.println(strs[i]);
		}
			
		*/}
		 catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
}
