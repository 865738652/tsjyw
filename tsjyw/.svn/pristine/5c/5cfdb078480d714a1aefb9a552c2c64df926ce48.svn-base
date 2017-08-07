package edu.iasd.controller;

import java.sql.Date;
import java.text.SimpleDateFormat;






import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demonstration.hibernate.dao.support.Page;

import edu.iasd.pojo.Business;
import edu.iasd.pojo.Goods;
import edu.iasd.pojo.GoodsType;
import edu.iasd.pojo.ViewBusiness;
import edu.iasd.pojo.ViewBusinessManager;
import edu.iasd.pojo.ViewGoods;
import edu.iasd.pojo.ViewGoodsType;
import edu.iasd.service.BusinessManagerService;
import edu.iasd.service.BusinessService;
import edu.iasd.service.GoodsService;
import edu.iasd.service.GoodsTypeService;

@Controller
@RequestMapping(value="/Index")
public class IntergralMallController {
	
	private static final int PAGESIZE = 8;
	
	@Autowired
	private GoodsService goodsService;
	
	@Autowired
	private GoodsTypeService goodsTypeService;

	@Autowired
	private BusinessService businessService;
	
	@Autowired
	private BusinessManagerService businessManagerService;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) throws Exception 
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    dateFormat.setLenient(false);
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	
	@RequestMapping(value="/IntegralMall")
	public String IntegralMall(HttpServletRequest request,HttpServletResponse response,Model model) throws Exception {
		List<ViewGoods> viewGoodss = goodsService.getAll();
		
		Page page;
		
		//最近热卖
		page = goodsService.getAllGoods(1, PAGESIZE,null);
		model.addAttribute("hotviewGoods", (List<ViewGoods>)page.getResult());
		
		//热门交易
		page = goodsService.getAllGoods(1, PAGESIZE,null);
		model.addAttribute("exchangeviewGoods", (List<ViewGoods>)page.getResult());
		
		//往期交易
		page = goodsService.getAllGoods(1, PAGESIZE,null);
		model.addAttribute("recentviewGoods", (List<ViewGoods>)page.getResult());
		return "IntegralMall";
	}
	
	@RequestMapping(value="/BuyGoods")
	public String BuyGoods(@RequestParam Integer goodsId, Model model,HttpServletRequest request) throws Exception
	{
		ViewGoods a = goodsService.getGoods(goodsId);
		model.addAttribute("vs", a);
		Set<GoodsType> vg = goodsTypeService.getGoodsType(goodsId);
		Business bussiness = businessService.getBusinessForGoods(goodsId);
		List<ViewBusinessManager> viewBusinessManagers = businessManagerService.getBusinessManagerFor(goodsId);
		model.addAttribute("vg", vg);
		model.addAttribute("bussiness", bussiness);
		model.addAttribute("bussinessManagers", viewBusinessManagers);
		String goodsMessage = goodsService.GoodsIsOrNotBuy(goodsId);
		if(goodsMessage != null)
			model.addAttribute("goodsMessage", goodsMessage);
		return "BuyGoods";
	}
	
	@RequestMapping(value="/IntegralMallMore")
	public String IntegralMallMore(@RequestParam String type,HttpServletRequest request,HttpServletResponse response,Model model)
	{
		//最近热卖
		if(type.equals("recenthot"))
		{
			
			int totalRecords = goodsService.getAll().size();
			int i = totalRecords/PAGESIZE;
			int j = totalRecords%PAGESIZE;
			int totalPage;
			if(j == 0)
				totalPage = i;
			else
				totalPage = i+1;
			model.addAttribute("totalRecords", totalRecords);
			model.addAttribute("totalPage", totalPage);
			model.addAttribute("goodsType", Goods.HOT_BUY);
			
			return "IntergralMall";
		}
		else if(type.equals("before"))
		{
			
		}
		return "IntegralMallMore";
	}
	
	@ResponseBody
	@RequestMapping(value="getGoodsByAjax")
	public String getGoodsByAjax(int pageNumber,int type) throws Exception
	{
		
		JsonResult jsonResult;
		try
		{
			Page page = goodsService.getAllGoods(pageNumber, PAGESIZE,null);
			jsonResult = new JsonResult("succ",0,null,page.getResult());
			return jsonResult.toJsonString();
		}
		catch(Exception e)
		{
			jsonResult = new JsonResult("fail",0,null,null);
			return jsonResult.toJsonString();
		}
	}
	
	
}
