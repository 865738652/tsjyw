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

import edu.iasd.form.ExchangeRecordStateForm;
import edu.iasd.pojo.ExchangeRecordState;
import edu.iasd.pojo.ViewExchangeRecord;
import edu.iasd.pojo.ViewUser;
import edu.iasd.search.QueryModel;
import edu.iasd.search.SearchParam;
import edu.iasd.security.UserInfo;
import edu.iasd.service.ExchangeRecordService;

@Controller
public class AllExchangeRecordController extends ControllerBase{

	@Autowired
	private ExchangeRecordService exchangeRecordService;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) throws Exception {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    dateFormat.setLenient(false);
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));	
	}
	
	@RequestMapping("AllExchangeRecordManage")
	public String showAll(){
		return "AllExchangeRecordManage";
	}
	
	@ResponseBody
	@RequestMapping(value = "AllExchangeRecordManage/getAllExchangeRecord")
	public String getAllExchangeRecord (HttpServletRequest request,int pageNumber,int pageSize, @SearchParam QueryModel queryModel) throws Exception{
		try{
			System.out.println(queryModel.toQueryString());
			UserInfo userInfo = super.getCurrentUser(request);
			ViewUser viewUser = userInfo.getViewUser();
			Page page = exchangeRecordService.getAllExchangeRecord(pageNumber, pageSize, viewUser.getUserId(),queryModel.toQueryString());
			
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
	@RequestMapping(value = "AllExchangeRecordManage/getExchangeRecordById")
	public String getExchangeRecordById(Integer exchangeRecordId) throws Exception
	{
		JsonResult jsonResult;
		try
		{
			System.out.println(exchangeRecordId);
			ViewExchangeRecord viewExchangeRecord = exchangeRecordService.getExchangeRecordById(exchangeRecordId);
			System.out.println(viewExchangeRecord.getBusinessName());
			jsonResult = new JsonResult("succ",0,null,viewExchangeRecord);
			return jsonResult.toJsonString();
		}
		catch(Exception e)
		{
			jsonResult = new JsonResult("fail",0,null,e.getMessage());
			return jsonResult.toJsonString();
		}
	}
	
	@ResponseBody
	@RequestMapping("AllExchangeRecordManage/getExchangeRecordForSelect")
	public String getExchangeRecordForSelect() throws Exception
	{
		JsonResult jsonResult;
		try
		{
			List<ExchangeRecordStateForm> as = new ArrayList<ExchangeRecordStateForm>();
			List<ExchangeRecordState> a = exchangeRecordService.getAllExchangeRecordState();
			for(ExchangeRecordState s:a)
			{
				ExchangeRecordStateForm c = new ExchangeRecordStateForm();
				c.transition(s);
				as.add(c);
			}
			
			jsonResult = new JsonResult("succ",as.size(),as,null);
			return jsonResult.toJsonString();
		}
		catch(Exception e)
		{
			JsonResult result = new JsonResult("fail", 0, null, e.getMessage());
			return result.toJsonString();
		}
		
	}
	
	@ResponseBody
	@RequestMapping("AllExchangeRecordManage/cancelExchangeRecord")
	public String cancelExchangeRecord(Integer exchangeRecordId) throws Exception
	{
		JsonResult result;
		try
		{
			exchangeRecordService.cancelExchangeRecord(exchangeRecordId);
			result = new JsonResult("succ",0,null,null);
			return result.toJsonString();
		}
		catch(Exception e)
		{
			result = new JsonResult("fail",0,null,e.getMessage());
			return result.toJsonString();
		}
	}
	
	@ResponseBody
	@RequestMapping("AllExchangeRecordManage/sureExchangeRecord")
	public String sureExchangeRecord(Integer exchangeRecordId) throws Exception
	{
		JsonResult jsonResult;
		try
		{
			exchangeRecordService.sureExchangeRecord(exchangeRecordId);
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
