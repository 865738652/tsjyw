package edu.iasd.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demonstration.hibernate.dao.support.Page;

import edu.iasd.pojo.Business;
import edu.iasd.pojo.Goods;
import edu.iasd.pojo.ViewExchangeRecord;
import edu.iasd.pojo.ViewGoods;
import edu.iasd.pojo.ViewUser;
import edu.iasd.security.UserInfo;
import edu.iasd.service.BusinessService;
import edu.iasd.service.ExchangeRecordService;
import edu.iasd.service.GoodsService;
import edu.iasd.service.UserService;


@Controller
public class ShoppingController extends ControllerBase{

	private static final int PAGESIZE = 8;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private BusinessService businessService;
	
	@Autowired
	private GoodsService goodsService;
	
	@Autowired
	private ExchangeRecordService exchangeRecordService;
	
	@RequestMapping("Index/Shopping")
	public String Shopping(Model model)
	{
		JSONObject searchJson = new JSONObject();
		searchJson.element("type", Goods.HOT_EXCHANGE);
		model.addAttribute("hotrank", (List<ViewGoods>)goodsService.getAllGoods(1, PAGESIZE, searchJson).getResult());
		return "shopping/ShoppingIndex";
	}
	
	@RequestMapping("Index/Shopping/getHotBuy")
	@ResponseBody
	public String getHotBuy(HttpServletRequest request,int pageNumber,String search) throws Exception
	{
		
		
		JsonResult jsonResult;
		try
		{
			JSONObject searchJson = JSONObject.fromObject(search);
			Page page = goodsService.getAllGoods(pageNumber, PAGESIZE, searchJson);
			jsonResult = new JsonResult("succ",0,null,page.getResult());
			return jsonResult.toJsonString();
		}
		catch(Exception e)
		{
			jsonResult = new JsonResult("fail",0,null,null);
			return jsonResult.toJsonString();
		}
	}
	
	@RequestMapping("Index/GoodsDetial")
	public String goodsDetial(@RequestParam Integer goodsId,Model model,HttpServletRequest request)
	{
		UserInfo userInfo;
		try{
			userInfo = super.getCurrentUser(request);
			ViewUser user = userService.getUser(userInfo.getViewUser().getUserId());
			model.addAttribute("user", user);
		}
		catch(Exception e)
		{
			
		}
		//����Ʒ
		ViewGoods viewGoods = goodsService.getGoods(goodsId);
		model.addAttribute("viewGoods", viewGoods);
		
		//��ȡ��Ʒʣ������
		int remainCount = goodsService.getGoodsRemainNum(viewGoods.getGoodsId());
		model.addAttribute("remainCount", remainCount);
		
		//���̼��µ�������Ʒ
		Page page = goodsService.getGoodsByBusiness(1, 4, viewGoods.getBusinessId());
		List<ViewGoods> goodsSameBusiness = (List<ViewGoods>)page.getResult();
		model.addAttribute("goodsSameBusiness", goodsSameBusiness);
		//ͬ���͵�������Ʒ
		System.out.println(viewGoods.getGoodsTypes().size());
		List<ViewGoods> goodsSameType = goodsService.getGoodsByType(viewGoods.getGoodsTypes());
		model.addAttribute("goodsSameType", goodsSameType);
		
		//�̼�
		Business business = businessService.getBusinessForGoods(goodsId);
		model.addAttribute("business", business);
		
		//����Ʒ���еĶһ���¼
		List<ViewExchangeRecord> viewExchangeRecords = exchangeRecordService.getAllExchangeRecordByGoods(goodsId);
		model.addAttribute("viewExchangeRecords", viewExchangeRecords);
		
		
		String IsOrNotBuy = goodsService.GoodsIsOrNotBuy(goodsId);
		if(IsOrNotBuy != null)
			model.addAttribute("IsOrNotBuy", IsOrNotBuy);
		
		return "shopping/ShoppingGoodsDetial";
	}
	
	@RequestMapping("Index/MyExchange")
	public String myExchange(HttpServletRequest request,Model model)
	{
		UserInfo userInfo;
		try
		{
			userInfo = super.getCurrentUser(request);
			ViewUser user = userInfo.getViewUser();
			
			//���ֵ�����
			int rank = userService.getUserIntegralRank(user.getUserId().intValue());
			
			
			model.addAttribute("user", user);
			model.addAttribute("rank", rank);
			
			
			
			//����������״̬��ǰ̨ɸѡ
			model.addAttribute("exchangeRecordStates", exchangeRecordService.getAllExchangeRecordState());
			
			//������Ʒ
			JSONObject searchJson = new JSONObject();
			searchJson.element("type", Goods.HOT_EXCHANGE);
			model.addAttribute("hotrank", (List<ViewGoods>)goodsService.getAllGoods(1, 3, searchJson).getResult());
			
			
			return "shopping/ShoppingMyForm";
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return "403";
		}
		
	}
	
	
	
}
