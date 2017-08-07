package edu.iasd.controller;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demonstration.hibernate.dao.support.Page;

import edu.iasd.form.GoodsTypeForm;
import edu.iasd.pojo.ViewGoodsType;
import edu.iasd.security.UserInfo;
import edu.iasd.service.GoodsTypeService;

@Controller
public class GoodsTypeController extends ControllerBase{

	@Autowired
	private GoodsTypeService goodsTypeService;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) throws Exception {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    dateFormat.setLenient(false);
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));//true:允许输入空值，false:不能为空值
	}
	@RequestMapping("GoodsTypeManage")
	public String ShowAll(){
		return "GoodsTypeManage";
	}
	
	@ResponseBody
	@RequestMapping("GoodsTypeManage/getGoodsType")
	public String getGoodsType(int goodsTypeId) throws Exception {
		try {
			ViewGoodsType goodsType=goodsTypeService.getGoodsType(goodsTypeId);
			
			JsonResult result = new JsonResult("succ", 0, null, goodsType);
			return result.toJsonString();
		}
		catch (Exception e) {
			JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			return result.toJsonString();
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "GoodsTypeManage/getAllGoodsType")
	public String getAllGoodsType(HttpServletRequest request,int pageNumber,int pageSize) throws Exception {
		try {
			UserInfo curUser = super.getCurrentUser(request);
			Page page = goodsTypeService.getAllGoodsType(curUser.getViewUser().getUserId(),pageNumber, pageSize);
			JsonResult result;
			if(page.getResult()==null)
				result = new JsonResult("succ", 0 ,new ArrayList<ViewGoodsType>(),null);
			else{
				List<ViewGoodsType> vgt=(List<ViewGoodsType>)page.getResult();
				result = new JsonResult("succ",(int)page.getTotalCount(),vgt,null);
			}
			return result.toJsonString();
		}
		catch (Exception e) {
			JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			return result.toJsonString();
		}
	}
	
	 @ResponseBody
	 @RequestMapping(value="GoodsTypeManage/createGoodsType")
	 public String createGoodsType(GoodsTypeForm goodsType) throws Exception{
		 try{
			 String id = goodsTypeService.createGoodsType(goodsType);
			 if(id != null){
				 JsonResult result = new JsonResult("succ",0,null,id);
				return result.toJsonString();
			 }
			 else
				 throw new Exception("创建商品类型失败");
		 }
		 catch (Exception e) {
			 JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			 return result.toJsonString();
		}		
	 }
	 
	 @ResponseBody
	 @RequestMapping(value="GoodsTypeManage/deleteGoodsType")
	 public String deleteGoods(int goodsTypeId) throws Exception{
		 try{
			 goodsTypeService.deleteGoodsType(goodsTypeId);
			 JsonResult result=new JsonResult("succ",0,null,null);
			 return result.toJsonString();
		 }
		 catch(Exception e){
			 JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			  return result.toJsonString();
		 }
	 }
	 
	 @ResponseBody
	 @RequestMapping(value="GoodsTypeManage/modifyGoodsType")
	 public String modifyGoodsType(GoodsTypeForm goodsType) throws Exception{
		 try{
			 if(goodsTypeService.modifyGoodsType(goodsType)){
				 JsonResult result=new JsonResult("succ",0,null,null);
				 return result.toJsonString();
			 }else
				 throw new Exception("修改商品类型失败");
		 }
		 catch(Exception e){
			 JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			 return result.toJsonString();
		 }
	 }
}
