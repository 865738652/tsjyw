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

import edu.iasd.pojo.User;
import edu.iasd.pojo.ViewExchangeRecord;
import edu.iasd.pojo.ViewUser;
import edu.iasd.search.QueryModel;
import edu.iasd.search.SearchParam;
import edu.iasd.security.NotLoginException;
import edu.iasd.security.UserInfo;
import edu.iasd.service.ExchangeRecordService;

@Controller
public class ExchangeRecordController extends ControllerBase{

	@Autowired
	private ExchangeRecordService exchangeRecordService;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) throws Exception {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    dateFormat.setLenient(false);
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	
	@RequestMapping("ExchangeRecordManage")
	public String showAll(){
		
		return "ExchangeRecordManage";
	}
	@ResponseBody
	@RequestMapping(value = "ExchangeRecordManage/getAllExchangeRecord")
	public String getAllExchangeRecord (HttpServletRequest request,int pageNumber,int pageSize,@SearchParam QueryModel queryModel) throws Exception{
		try{
			UserInfo userInfo = super.getCurrentUser(request);
			ViewUser viewUser = userInfo.getViewUser();
			Page page = exchangeRecordService.getMyExchangeRecordForSearchForm(pageNumber, pageSize, viewUser.getUserId(),queryModel.toQueryString());
			
			JsonResult result;
			if(page.getResult() == null)
				result = new JsonResult("succ",0,new ArrayList<ViewExchangeRecord>(), null);
			else{
				List<ViewExchangeRecord> myOrder = (List<ViewExchangeRecord>)page.getResult();
				result = new JsonResult("succ",(int)page.getTotalCount(),myOrder,null);
			}
			return result.toJsonString();
		}
		catch(Exception e){
			JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			return result.toJsonString();
		}
	}
	
	@ResponseBody
	@RequestMapping(value="ExchangeRecordManage/createExchangeRecordManage")
	public String createExchangeRecordManage(ViewExchangeRecord viewExchangeRecord) throws Exception
	{
		JsonResult jsonResult;
		try
		{
			String serilNum = exchangeRecordService.createExchangeRecord(viewExchangeRecord);
			jsonResult = new JsonResult("succ",0,null,serilNum);
			return jsonResult.toJsonString();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			jsonResult = new JsonResult("fail",0,null,e.getMessage());
			return jsonResult.toJsonString();
		}
	}
	
	@ResponseBody
	@RequestMapping("ExchangeRecordManage/getMyExchangeRecord")
	public String getMyExchangeRecord(int pageNumber,int stateType,HttpServletRequest request) throws Exception
	{
		//pageSize固定是四
		UserInfo userInfo;
		JsonResult jsonResult;
		try
		{
			userInfo = super.getCurrentUser(request);
			int pageSize = 5;
			Page page = exchangeRecordService.getMyExchangeRecord(pageNumber, pageSize,userInfo.getViewUser().getUserId(),stateType);
			jsonResult = new JsonResult("succ",0,null,page.getResult());
			return jsonResult.toJsonString();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			jsonResult = new JsonResult("fail",0,null,e.getMessage());
			return jsonResult.toJsonString();
		}
	}
	
	@ResponseBody
	@RequestMapping("ExchangeRecordManage/cancelExchangeRecord")
	public String cancelExchangeRecord(Integer exchangeRecordId) throws Exception
	{
		JsonResult jsonResult;
		try
		{
			exchangeRecordService.applyCancelExchangeRecord(exchangeRecordId);
			jsonResult = new JsonResult("succ",0,null,null);
			return jsonResult.toJsonString();
		}
		catch(Exception e)
		{
			jsonResult = new JsonResult("fail",0,null,e.getMessage());
			return jsonResult.toJsonString();
		}
	}
	
	
	@ResponseBody
	@RequestMapping("ExchangeRecordManage/sureExchangeRecord")
	public String sureExchangeRecord(Integer exchangeRecordId) throws Exception
	{
		JsonResult jsonResult;
		try
		{
			exchangeRecordService.sureMyExchangeRecord(exchangeRecordId);
			jsonResult = new JsonResult("succ",0,null,null);
			return jsonResult.toJsonString();
		}
		catch(Exception e)
		{
			jsonResult = new JsonResult("fail",0,null,e.getMessage());
			return jsonResult.toJsonString();
		}
	}
	
}
