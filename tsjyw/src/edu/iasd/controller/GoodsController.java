package edu.iasd.controller;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demonstration.hibernate.dao.support.Page;

import edu.iasd.form.GoodsForm;
import edu.iasd.form.GoodsTypeVo;
import edu.iasd.pojo.Business;
import edu.iasd.pojo.GoodsState;
import edu.iasd.pojo.GoodsType;
import edu.iasd.pojo.ViewGoods;
import edu.iasd.search.QueryModel;
import edu.iasd.search.SearchParam;
import edu.iasd.security.UserInfo;
import edu.iasd.service.GoodsService;

@Controller
public class GoodsController extends ControllerBase{
	
	@Autowired
	private GoodsService goodsService;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) throws Exception {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    dateFormat.setLenient(false);
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));//true:���������ֵ��false:����Ϊ��ֵ
	}
	
	@RequestMapping("GoodsManage")
	public String ShowAll(){
		return "GoodsManage";
	}
	
	@RequestMapping("GoodsManage/{businessId}")
	public String ShowGoodsByBusiness(@PathVariable int businessId,Model model){
		model.addAttribute("businessId", businessId);
		return "GoodsManage";
	}
	
	@ResponseBody
	@RequestMapping(value="GoodsManage/getGoodsByBusiness")
	public String getGoodsByBusiness(int pageNumber, int pageSize, int businessId) throws Exception{
		try{
			Page page = goodsService.getGoodsByBusiness(pageNumber, pageSize, businessId);
			JsonResult result;
			if(page.getResult()==null)
				result = new JsonResult("succ", 0, new ArrayList<ViewGoods>(),null);
			else{
				List<ViewGoods> goods = (List<ViewGoods>)page.getResult();
				result = new JsonResult("succ", (int)page.getTotalCount(),goods,null);
			}
			return result.toJsonString();
		}
		catch (Exception e) {
			JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			return result.toJsonString();
		}
	}
	
	@ResponseBody
	@RequestMapping(value="GoodsManage/getAllGoods")
	public String getAllGoods(int businessId,int pageNumber, int pageSize,@SearchParam QueryModel queryModel,HttpServletRequest request) throws Exception
	{
		
		UserInfo userInfo;
		try{
			userInfo = super.getCurrentUser(request);
			Page page = goodsService.getMyAllGoods(pageNumber, pageSize,businessId,userInfo.getViewUser().getUserId(),queryModel.toQueryString());
			JsonResult result;
			if(page.getResult()==null)
				result = new JsonResult("succ", 0, new ArrayList<ViewGoods>(),null);
			else{
				List<ViewGoods> goods = (List<ViewGoods>)page.getResult();
				result = new JsonResult("succ", (int)page.getTotalCount(),goods,null);
			}
			return result.toJsonString();
		}
		catch (Exception e) {
			JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			return result.toJsonString();
		}
	}
	
	@ResponseBody
	@RequestMapping(value="GoodsManage/getGoods")
		public String getGoods(int goodsId) throws Exception {
			try{
				ViewGoods goods = goodsService.getGoods(goodsId);
				
				JsonResult result = new JsonResult("succ", 0, null, goods);
				return result.toJsonString();
			}
			catch (Exception e) {
				JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
				return result.toJsonString();
			}
		}
	
	 @ResponseBody
	 @RequestMapping(value="GoodsManage/createGoods")
	 public String createGoods(GoodsForm goods) throws Exception {
		 try {
			 String id = goodsService.createGoods(goods);
			 if(id!=null){
				 JsonResult result = new JsonResult("succ",0,null,id);
				 return result.toJsonString();
			 }
			 else
				 throw new Exception("创建商品错误");
		 }catch (Exception e){
			 JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			 return result.toJsonString();
		 }
	 }
	 
	 @ResponseBody
	 @RequestMapping(value="GoodsManage/deleteGoods")
	 public String deleteGoods(int goodsId) throws Exception {
		 try{
			 goodsService.deleteGoods(goodsId);
			 
			 JsonResult result = new JsonResult("succ", 0, null, null);
			 return result.toJsonString();
		 }catch (Exception e) {
				JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
				return result.toJsonString();
			}	
	 }
	 
	 @ResponseBody
	 @RequestMapping(value="GoodsManage/modifyGoods")
	 public String modifyGoods(GoodsForm goods) throws Exception {
		 try {
			 if (goodsService.modifyGoods(goods)){
				 JsonResult result = new JsonResult("succ", 0, null, null);
				 return result.toJsonString();
			 }
			 else 
				 throw new Exception("修改商品错误");
		 }catch (Exception e) {
			 JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			 return result.toJsonString();
		}		
	 }
	 
	 @ResponseBody
	 @RequestMapping(value="GoodsManage/getGoodsState")
	 public String getGoodsState() throws Exception {
		 try {
			 List<GoodsState> list = goodsService.getGoodsState();
			 JsonResult result = new JsonResult("succ", list.size(), list,null);
			 return result.toJsonString();
		 }catch (Exception e) {
			 JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			 return result.toJsonString();
		}	
	 }
	 
	 @ResponseBody
	 @RequestMapping(value="GoodsManage/getBusiness")
	 public String getBusiness() throws Exception
	 {
		 try {
			 List<Business> list = goodsService.getBusiness();
			 JsonResult result = new JsonResult("succ", list.size(), list, null);
			 return result.toJsonString();		
		 }
		 catch (Exception e) {
			 JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			 return result.toJsonString();
		}		
	 }
	 
	 @ResponseBody
	 @RequestMapping(value = "GoodsManage/getGoodsType")
	 public String getGoodsType() throws Exception {
		 try{
			 List<GoodsType> levels = goodsService.getGoodsType();
			 List<GoodsTypeVo> list = new ArrayList<GoodsTypeVo>();
			 for(GoodsType l : levels) {
				 GoodsTypeVo v = new GoodsTypeVo();
				 v.setGoodsTypeId(l.getGoodsTypeId());
				 v.setGoodsTypeName(l.getGoodsTypeName());
				 v.setGoodsTypeNumber(l.getGoodsTypeNumber());
				 v.setGoodsTypeSerial(l.getGoodsTypeSerial());
				 v.setParentGoodsTypeId(l.getParentGoodsTypeId());
				 list.add(v);
			 }
			 JsonResult result = new JsonResult("succ", list.size(), list, null);
			 return result.toJsonString();
		 }
		 catch (Exception e) {
				JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
				return result.toJsonString();
			}
	 }
	 
	 
	 @RequestMapping(value = "GoodsManage/EditGoods")
	 public String EditGoods(@RequestParam Integer goodsId,Model model)
	 {
		 model.addAttribute("goodsId", goodsId);
		 return "GoodsIntroEdit";
	 }
	 
	 @ResponseBody
	 @RequestMapping(value = "GoodsManage/ModifyGoodsIntro")
	 public String modifyGoodsIntro(int goodsId,String ue_content,String editorValue) throws Exception
	 {
		 System.out.println(goodsId + "-------" + ue_content + "----" + editorValue);
		 JsonResult jsonResult;
		 try {
			goodsService.editGoodsIntrol(goodsId, ue_content);
			jsonResult = new JsonResult("succ",0,null,null);
			return jsonResult.toJsonString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			jsonResult = new JsonResult("fail",0,null,null);
			return jsonResult.toJsonString();
		}
	 }
	 
	 
}
