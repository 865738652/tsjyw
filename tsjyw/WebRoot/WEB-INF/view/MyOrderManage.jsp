<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<META HTTP-EQUIV="pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate">
<META HTTP-EQUIV="expires" CONTENT="Wed, 26 Feb 1997 08:21:57 GMT">
<html lang="en">
<head>
    
    <title>我的订单</title>
    <%@include file="head.jsp" %>
    
    <script type="text/javascript"> 
    	var action = "create";
    	
    	$(function(){
    	
    		$.ajaxSetup ({
         		cache: false //关闭AJAX缓存
     		});
    		
    		loadMyOrder();
    	});
    	
    	function queryParams(params) {  //配置参数
	        var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
	            pageSize: params.pageSize,   //页面大小
	            pageNumber: params.pageNumber
	        };
	        <c:if test="${not empty _csrf}">
				temp.${_csrf.parameterName} = "${_csrf.token}";
			</c:if>
        	return temp;
    	}
    	
    	function loadMyOrder()
    	{
    		$("#TableMyOrder").bootstrapTable({
		    	url: "getMyOrder", 
		    	method:"post",
		    	contentType: "application/x-www-form-urlencoded",
		    	queryParams: queryParams,
		    	queryParamsType:"undefined",//参数格式,发送标准的restful类型的参数请求
		    	striped: true,     //使表格带有条纹  
  				pagination: true, //在表格底部显示分页工具栏 
		    	dataType: "json",
			    pagination: true,
			    search: true, //是否显示右上角的搜索框  
			    sidePagination: "server", 
			    idField: "exchangeRecordId",  //标识哪个字段为id主键  
			    pageSize: 10,  //每一页 的 数量
				pageNumber:1,     //当前页码
				showRefresh: true,  //显示刷新按钮  
				singleSelect: true,//复选框只能选择一条记录  
				clickToSelect: true,//点击行即可选中单选/复选框  
				columns: [
						{
				        	field: 'userId', 
		                    title: '用户',		 
		                    width: 150,		 
		                    align: 'center',		 
		                    valign: 'middle',
		                    sortable: true,
				        },
				        {
				        	field: 'goodsId', 
		                    title: '商品',		 
		                    width: 150,		 
		                    align: 'center',		 
		                    valign: 'middle'	 
		                    
				        },
				        {
				        	field: 'exchangePrice', 
		                    title: '商品单价',		 
		                    width: 100,		 
		                    align: 'center',		 
		                    valign: 'middle'
				        },
				        {
				        	field: 'exchangeRecordStateName', 
		                    title: '商品状态',		 
		                    width: 150,	 
		                    align: 'center',		 
		                    valign: 'middle'
				        },
				        
				        {
		                    title: '操作',
		                    field: 'exchangeRecordId',
		                    formatter:function(value,row,index){
		                    	var a = '<a class="btn btn-info" data-toggle="modal" data-target="#showMyOrderDialog" href="javascript:void(0);" onclick=\"showMyOrder('+value+')\">';
   								var b = '<i class="glyphicon glyphicon-zoom-in icon-white"></i>详情信息</a> ';		                 
	                   			var c = '<a class="btn btn-danger" href="javascript:void(0);" onclick=\"cancelMyOrder('+value+')\" >';
	                   			var d = '<i class="glyphicon glyphicon-chevron-right icon-white"></i>取消订单</a> ';		
	                        	return a+b+c+d;  
		                  	}
                  		}				        
				]
		    });
    	}
    	
		function showVolunteer(exchangeRecordId)
   		{
   			$.ajax({
				url:"getMyOrder",			
				data:{"exchangeRecordId":exchangeRecordId},
				dataType:"json",
				success:function(data) {  
					if (data.code != "succ") {
						alert(data.data);
						$('#showMyOrderDialog').modal('hide');
						return;
					}
					
					$('#exchangeRecordIdDetail').text(data.data.exchangeRecordId);
					$('#userIdDetail').text(data.data.userId);
					$('#goodsIdDetail').text(data.data.goodsId);
					$('#exchangeRecordStateIdDetail').text(data.data.exchangeRecordStateName);
					$('#exchangeTimeDetail').text(getSmpFormatDateByLong(data.data.exchangeTime,false));
			      	$('#orderTimeDetail').text(getSmpFormatDateByLong(data.data.orderTime,false));
			      	$('#exchangeCountDetail').text(data.data.exchangeCount);
			      	$('#exchangePriceDetail').text(data.data.exhangePrice);
			    },
			    error: function(XMLHttpRequest, textStatus, errorThrown) {
					alert("网络错误: " + XMLHttpRequest.status + ", " + textStatus);
			    }
			});	
   		}
   		
   		
   		
   		
   		function cancelMyOrder(exchangeRecordId) {
   			if (!confirm("确认撤销订单吗？"))
   				return;
   				
   			$.ajax({
				url:"cancelMyOrder",			
				data:{"exchangeRecordId":exchangeRecordId},
				dataType:"json",
				success:function(data) {  
					if (data.code != "succ") {
   						alert(data.data);
   						return;
   					}
      				$("#TableMyOrder").bootstrapTable('refresh');
  				},
			    error: function(XMLHttpRequest, textStatus, errorThrown) {
					alert("网络错误: " + XMLHttpRequest.status + ", " + textStatus);
			    }
			});
   		}
   		
 
    </script>
    
</head>

<body>
    <%@include file="top.jsp" %>

    <div class="ch-container">
        <div class="row">
            <%@include file="left.jsp" %>

        <noscript>
            <div class="alert alert-block col-md-12">
                <h4 class="alert-heading">Warning!</h4>

                <p>You need to have <a href="http://en.wikipedia.org/wiki/JavaScript" target="_blank">JavaScript</a>
                    enabled to use this site.</p>
            </div>
        </noscript>

        <div id="content" class="col-lg-10 col-sm-10">
            <!-- content starts -->
                <div>
        <ul class="breadcrumb">
            <li>
                <a href="#">积分商城</a>
            </li>
            <li>
                <a href="MyOrderManage">我的订单</a>
            </li>
        </ul>
    </div>
    <div class="row">
    <div class="box col-md-12">
    <div class="row">
        <div class="box col-md-12">
            <div class="box-inner">
                <div class="box-header well" data-original-title="">
                    <h2>我的订单</h2>

                    <div class="box-icon">
                        <a href="#" class="btn btn-setting btn-round btn-default"><i
                                class="glyphicon glyphicon-cog"></i></a>
                         <a href="#" class="btn btn-minimize btn-round btn-default"><i
                                class="glyphicon glyphicon-chevron-up"></i></a>
                        <a href="#" class="btn btn-close btn-round btn-default"><i
                                class="glyphicon glyphicon-remove"></i></a>
                    </div>
                </div>
              
                <div class="box-content">
                    <table class="table table-striped table-bordered table-hover" id="TableMyOrder"></table>
                </div>
            </div>
        </div>

    </div><!--/span-->

    <!-- content ends -->
    </div><!--/#content.col-md-0-->
</div><!--/fluid-row-->

   

	 <!-- 查看我的订单对话框 -->
	 <div class="modal fade" id="showMyOrderDialog" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
         aria-hidden="true">

        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">×</button>
                    <h3>查看我的订单</h3>
                </div>
                <div class="form-group has-success has-feedback">
                	<table width="90%">
                		<tr>
                			<td width="20%"><label  class="control-label">记录编号</label></td>
                			<td width="30%"><label  id="exchangeRecordDetail" class="control-label">记录编号</label></td>
                			<td width="20%"><label  class="control-label">用户名称</label></td>
                			<td width="30%"><label  id="userIdDetail" class="control-label">用户名称</label></td>             			
                		</tr>
                		<tr>
                			<td width="20%"><label  class="control-label">商品名称</label></td>
                			<td width="30%"><label  id="goodsIdDetail" class="control-label">商品名称</label></td>                			
                			<td width="20%"><label  class="control-label">商品单价</label></td>
                			<td width="30%"><label  id="exchangePriceDetail" class="control-label">商品单价</label></td>                			
                		</tr>
                		<tr>
                			<td width="20%"><label  class="control-label">兑换数量</label></td>
                			<td width="30%"><label  id="exchangeCountDetail" class="control-label">兑换数量</label></td>                			
                			<td width="20%"><label  class="control-label">兑换状态</label></td>
                			<td width="30%"><label  id="exchangeCountDetail" class="control-label">兑换状态</label></td>   
                		</tr>
                		<tr>
                			<td width="20%"><label  class="control-label">起始时间</label></td>
                			<td width="30%"><label  id="orderTimeDetail" class="control-label">起始时间</label></td>                			
                			<td width="20%"><label  class="control-label">兑换时间</label></td>
                			<td width="30%"><label  id="exchangeTimeDetail" class="control-label">兑换时间</label></td>   
                		</tr>
                	</table>
                	<form class="form-inline" role="form" id='exchangeRecordForm'>
                    	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    </form> 
                </div>
                <hr>
                <div class="modal-footer">
                     <a href="#" class="btn btn-primary" data-dismiss="modal">关闭</a>
                </div>
            </div>
        </div>
    </div>
</div><!--/.fluid-container-->  
</body>
</html>
