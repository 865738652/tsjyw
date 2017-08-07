<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="en">
<head> 
    <title>广告管理</title>
    <%@include file="head.jsp" %>
    
    <script type="text/javascript">
    	var action = "modify";
    	$(function(){
    		initSamlpleUpload("btnUploadFile", onUploadComplete);
    		$.ajaxSetup ({
         		cache: false //关闭AJAX缓存
     		});
    		
    		loadAdvertisement();
    		
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
    	
    	function loadAdvertisement()
    	{
    		$("#TableAdvertisement").bootstrapTable({
    			
		    	url: "getAllAdvertisement", 
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
			    //showHeader: false,  隐藏表头
			    idField: "AdvertisementId",  //标识哪个字段为id主键  
			    pageSize: 10,  //每一页 的 数量
				pageNumber:1,     //当前页码
				//pageList: [3,6,9],  
				//idField: "ProductId",  //标识哪个字段为id主键  
				//showToggle: false,   //名片格式  
				//cardView: false,//设置为True时显示名片（card）布局  
				//showColumns: true, //显示隐藏列    
				showRefresh: true,  //显示刷新按钮  
				singleSelect: true,//复选框只能选择一条记录  
				clickToSelect: true,//点击行即可选中单选/复选框  
				//toolbar: "#toolbar", //设置工具栏的Id或者class  
				//silent: true,  //刷新事件 
				columns: [
				        {
				        	field: 'advertisementId', 
		                    title: '广告代码',		 
		                    width: 100,		 
		                    align: 'center',		 
		                    valign: 'middle',		 
		                    sortable: true,
				        },
				        {
				        	field: 'advertisementName', 
		                    title: '广告名称',		 
		                    width: 100,		 
		                    align: 'center',		 
		                    valign: 'middle'
				        },				        
				        {
		                    title: '操作',
		                    field: 'advertisementId',
		                    formatter:function(value,row,index){		                    		                        		                        
	                   			var a = '<a class="btn btn-info" data-toggle="modal" data-target="#advertisementDialog" href="javascript:void(0);" onclick=\"modifyAdvertisement('+value+')\">'; 
	                   			var b = '<i class="glyphicon glyphicon-edit icon-white"></i>修改</a> ';	
	                        	return a+b;  
		                  	}
                  		}				        
				],
				onLoadSuccess: function (data) {
					
				}
		    });
    	}
   		
   		$("#advertisementDialog").on("hidden.bs.model",function(e){$(this).removeData();}); 
   		
   		
   		function modifyAdvertisement(advertisementId) {
   			action = "modify";
   			$("#title").html("修改广告信息");
   			$('#advertisementForm')[0].reset();
   			$("#advertisementId").val(advertisementId);
   			
			$.ajax({
				url:"getAdvertisement",			
				data:{"advertisementId":advertisementId},
				dataType:"json",
				success:function(data) {  
			        if (data.code != "succ") {
   						alert(data.data);
   						$('#advertisementDialog').modal('hide');
   						return;
   					}
   					if(data.data.advertisementImgPath != null){
   						$('#advertisementImgPath').val(data.data.advertisementImgPath);
   						$('#logo').attr("src",data.data.advertisementImgPath);
   					}
  			        $('#advertisementId').val(data.data.advertisementId);
					$('#advertisementName').val(data.data.advertisementName);
					
			    },
			    error: function(XMLHttpRequest, textStatus, errorThrown) {
					alert("网络错误: " + XMLHttpRequest.status + ", " + textStatus);
			    }
			});	   						
   		}
   		
   		function onUploadComplete(data) {
	   		if (data == null || data == undefined) {
	   			alert("上传错误!");
	   			return;
	   		}
	   		$("#advertisementImgPath").val(data.data.attachUrl);
	   		$('#logo').attr("src", data.data.attachUrl);
	   	}
	   	
   		function onOKButton() {
   			
   			if (action == "modify")
   				url = "modifyAdvertisement";
   			alert($('#advertisementForm').serialize())	
   			$.ajax({   				
	        	url:url,
	        	type:"post",
	        	data:$('#advertisementForm').serialize(),
	        	dataType:"json",
	        	success:function(data){
	        		if (data.code != "succ") {
						alert(data.data);
						return;
					}
					if (action == "modify")
						alert("修改成功");
					
	        		$('#advertisementDialog').modal('hide');
	        		$("#TableAdvertisement").bootstrapTable('refresh');
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
                <a href="#">管理中心</a>
            </li>
            <li>
                <a href="#">广告管理</a>
            </li>
        </ul>
    </div>

    <div class="row">
    <div class="box col-md-12">
    <div class="row">
        <div class="box col-md-12">
            <div class="box-inner">
                <div class="box-header well" data-original-title="">                  
	                <div class="box-icon">
	                        <a href="#" class="btn btn-minimize btn-round btn-default"><i
	                                class="glyphicon glyphicon-chevron-up"></i></a>
	                        <a href="#" class="btn btn-close btn-round btn-default"><i
	                                class="glyphicon glyphicon-remove"></i></a>
	                 </div>
                </div>
                <div class="box-content">
                	<sec:authorize access="hasAuthority('SuperAdmin')">
                      
                    </sec:authorize> 
                     <table class="table table-striped table-bordered table-hover" id="TableAdvertisement"></table>
                </div>
            </div>
        </div>
 </div>
</div>
</div>
    
 
<div class="modal fade" id=advertisementDialog tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
         aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
              <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>  
                    <h3><span id='title'>修改广告信息</span></h3> 
                </div>
                <div class="modal-body">
                   <form class="form-inline" role="form" id='advertisementForm'>
                   		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/> 
                    	
                    	<div class="form-group has-success has-feedback" id="advertisementForm">
							<table width="90%">
								<tr>
									<td width="40%">		    
						              <label class="control-label" for="inputSuccess4">广告名称</label>
						            </td>
						            <td width="60%">
						              <input type="text" class="form-control" name="advertisementName" id="advertisementName">	
						            </td>
								</tr>
								
								<tr>
									
						        <td><input type="button" value="广告图片..." class="form-control" id="btnUploadFile" name="upload" /></td>
								<td><img id="logo" src="../images/no.jpg" width="200px" /></td>															
							</tr>
								
								<tr>
									<td width="60%">
										<img id='picture' />	
									</td>
								</tr>
							</table>
							<input type="hidden"  name="advertisementId" id="advertisementId">
							<input type="hidden"  id="advertisementImgPath" name="advertisementImgPath">
						</div>                       
 
   					</form>                   
                </div>
                <div class="modal-footer">
                    <a href="javascript:void(0)" class="btn btn-default" data-dismiss="modal">取消</a>
                    <a href="javascript:void(0)" class="btn btn-primary" id="advertisementOKButton" onclick="onOKButton()">确定</a>
                </div>
            </div>
        </div>
    <footer class="row">
        <p class="col-md-9 col-sm-9 col-xs-12 copyright">&copy; <a href="http://usman.it" target="_blank">Muhammad
                Usman</a> 2012 - 2015</p>

        <p class="col-md-3 col-sm-3 col-xs-12 powered-by">Powered by: <a
                href="http://usman.it/free-responsive-admin-template">Charisma</a></p>
    </footer>
 </div>
 </div>
 </div>
 </div>
</body>
</html>