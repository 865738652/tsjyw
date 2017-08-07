<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="en">
<head>
    
    <title>管理商家</title>
    <%@include file="head.jsp" %>
    
    <script type="text/javascript">
       	 
    	var action = "create";       	
    	$(function(){
    	
    		$.ajaxSetup ({
         		cache: false //关闭AJAX缓存
     		});
    		
    		loadBusiness();
    		initSamlpleUpload("btnUploadFile",onUploadComplete);
    	});
    	
    	function queryParams(params) {  //配置参数
	        var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
	            pageSize: params.pageSize,   //页面大小
	            pageNumber: params.pageNumber
	        };
	        <c:if test="${not empty _csrf}">
				temp.${_csrf.parameterName} = "${_csrf.token}";
			</c:if>
			extendTalbeParams(temp, "searchForm");
        	return temp;
    	}
    	
    	function loadBusiness()
    	{ 
    		$("#TableBusiness").bootstrapTable({
    			
		    	url: "getAllBusiness", 
		    	method:"post",
		    	contentType: "application/x-www-form-urlencoded;charset=utf-8",
		    	queryParams: queryParams,
		    	queryParamsType:"undefined",//参数格式,发送标准的restful类型的参数请求
		    	striped: true,     //使表格带有条纹  
  				pagination: true, //在表格底部显示分页工具栏 
		    	dataType: "json",
			    pagination: true,
			    //search: true, //是否显示右上角的搜索框  
			    sidePagination: "server", 
			    //showHeader: false,  隐藏表头
			    idField: "businessId",  //标识哪个字段为id主键  
			    pageSize: 10,  //每一页 的 数量
				pageNumber:1,     //当前页码
				//pageList: [3,6,9],  
				//idField: "ProductId",  //标识哪个字段为id主键  
				//showToggle: false,   //名片格式  
				//cardView: false,//设置为True时显示名片（card）布局  
				//showColumns: true, //显示隐藏列    
				//showRefresh: true,  //显示刷新按钮  
				singleSelect: true,//复选框只能选择一条记录  
				clickToSelect: true,//点击行即可选中单选/复选框  
				//toolbar: "#toolbar", //设置工具栏的Id或者class  
				//silent: true,  //刷新事件 
				columns: [
				        {
				        	field: 'businessName', 
		                    title: '商家名称',		 
		                    width: 100,		 
		                    align: 'center',		 
		                    valign: 'middle',		 
		                    sortable: true,
				        },
				        {
				        	field: 'businessStr', 
		                    title: '商家地址',		 
		                    width: 100,		 
		                    align: 'center',		 
		                    valign: 'middle'
				        },		        
				        {
				        	field: 'businessPhone', 
		                    title: '商家电话',		 
		                    width: 100,		 
		                    align: 'center',		 
		                    valign: 'middle'
				        },		        
				        {
		                    title: '操作',
		                    field: 'businessId',
		                    formatter:function(value,row,index){
		                    	var a = '<a class="btn btn-info" data-toggle="modal" data-target="#showBusinessDialog" href="javascript:void(0);" onclick=\"showBusiness('+value+')\">';
	   							var b = '<i class="glyphicon glyphicon-zoom-in icon-white"></i>查看</a> ';		                        
		                   		var c = '<a class="btn btn-info" data-toggle="modal" data-target="#businessDialog" href="javascript:void(0);" onclick=\"modifyBusiness('+value+')\">'; 
		                   		var d = '<i class="glyphicon glyphicon-zoom-in icon-white"></i>修改</a> ';	                 		
		                   		var e = '<a class="btn btn-danger" href="javascript:void(0);" onclick=\"deleteBusiness('+value+')\" >';
		                   		var f = '<i class="glyphicon glyphicon-zoom-in icon-white"></i>删除</a> ';   
		                   		var g = '<a class="btn btn-success" href=\"../GoodsManage/' + value +'\" >';
		                   		var h = '<i class="glyphicon glyphicon-zoom-in icon-white"></i>管理商品</a> ';  
		                   		var i = '<a class="btn btn-success" href=\"../BusinessManager/' + value +'\" >';
		                   		var j = '<i class="glyphicon glyphicon-zoom-in icon-white"></i>管理员</a> '; 
	                        return a+b+c+d+e+f+g+h+i+j; 	                    		                        		                        
	                   			
		                  	}
                  		}				        
				],
				onLoadSuccess: function (data) {
					
				}
		    });
    	}
    	
    	function showBusiness(businessId)
		{
		
			$('#showBusinessDialog').on('show.bs.modal', function () {
   				$.ajax({
   					url:"getBusiness",			
   					data:{"businessId":businessId},
   					dataType:"json",
   					success:function(data) {  
   						if (data.code != "succ") {
   							alert(data.data);
   							$('#showBusinessDialog').modal('hide');
   							return;
   						}
   						
				        $("#businessNameDetail").text(data.data.businessName);
						$("#businessStrDetail").text(data.data.businessStr);
						$("#businessIntroDetail").text(data.data.businessIntro);
						$("#businessPhoneDetail").text(data.data.businessPhone);
						$("#businessWeixinDetail").text(data.data.businessWeixin);		
				        $("#businessQqDetail").text(data.data.businessQq);
				       
				    }
   				});	
   			});
		}
   		
   		$("#businessDialog").on("hidden.bs.model",function(e){$(this).removeData();}); 
   		
   		function createBusiness() {   			
   			action = "create";
   			$("#title").html("添加商家");
   			$('#businessForm')[0].reset();
   			$("#businessId").val("");
   			
   				
   		}
   		
   		function modifyBusiness(businessId) {
   			action = "modify";
   			$("#title").html("修改商家");
   			$('#businessForm')[0].reset();
   		
			$.ajax({
				url:"getBusiness",			
				data:{"businessId":businessId},
				dataType:"json",
				success:function(data) {  
			        if (data.code != "succ") {
   						alert(data.data);
   						$('#businessDialog').modal('hide');
   						return;
   					}
   					
  			        $('#businessId').val(data.data.businessId);
					$('#businessName').val(data.data.businessName);
					$('#businessStr').val(data.data.businessStr	);			
					$('#businessIntro').val(data.data.businessIntro);
					$('#businessPhone').val(data.data.businessPhone);
					$('#businessWeixin').val(data.data.businessWeixin);
					$('#businessQq').val(data.data.businessQq);
					if(data.data.bussinessImagePath !=null)
					{
						$('#bussinessImagePath').val(data.data.bussinessImagePath);
	   					$('#logo').attr("src", data.data.bussinessImagePath);
					}
			    },
			    error: function(XMLHttpRequest, textStatus, errorThrown) {
					alert("网络错误: " + XMLHttpRequest.status + ", " + textStatus);
			    }
			});	   						
   		}
   		
   		function deleteBusiness(businessId) {
   			if (!confirm("确认删除该商家吗？"))
   				return;
   				
   			$.ajax({
				url:"deleteBusiness",			
				data:{"businessId":businessId},
				dataType:"json",
				success:function(data) {  
					if (data.code != "succ") {
   						alert(data.data);
   					}
      				$("#TableBusiness").bootstrapTable('refresh');
  				},
			    error: function(XMLHttpRequest, textStatus, errorThrown) {
					alert("网络错误: " + XMLHttpRequest.status + ", " + textStatus);
			    }
			});
   		}
   		
   		function onOKButton() {
   			var url = "createBusiness";
   			
   			if (action == "modify")
   				url = "modifyBusiness";
   				
   			$.ajax({   				
	        	url:url,
	        	type:"post",
	        	data:$('#businessForm').serialize(),
	        	dataType:"json",
	        	success:function(data){
	        		if (data.code != "succ") {
						alert(data.data);
						return;
					}
					if (action == "create")
						alert("创建成功");
					else
						alert("修改成功");
	        		$('#businessDialog').modal('hide');
	        		$("#TableBusiness").bootstrapTable('refresh');
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
	   		$("#bussinessImagePath").val(data.data.attachUrl);
	   		$('#logo').attr("src", data.data.attachUrl);
	   	}
	   	
	   	function searchFunc() {
			$("#TableBusiness").bootstrapTable('refresh');
		}

		function clearFunc() {
			$('#searchForm')[0].reset();
			$("#TableBusiness").bootstrapTable('refresh');
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
                <a href="">商城管理</a>
            </li>
            <li>
                <a href="BusinessManage">商家管理</a>
            </li>
        </ul>
</div>
<!-- 表格显示内容-->
<div class="box col-md-12">
            <div class="box-inner">
                <div class="box-header well" data-original-title="">
                   <h2>商家管理</h2>

                    <div class="box-icon">
                        <a href="#" class="btn btn-minimize btn-round btn-default"><i
                                class="glyphicon glyphicon-chevron-up"></i></a>
                        <a href="#" class="btn btn-close btn-round btn-default"><i
                                class="glyphicon glyphicon-remove"></i></a>
                    </div>
                </div>
                             
               
                <div class="box-content">
                <div class="alert alert-info">
                	<a class="btn btn-success" data-toggle="modal"  data-target="#businessDialog" onclick="createBusiness()">
                        <i class="glyphicon glyphicon-plus icon-white"></i> 添加商家 </a>
                </div>
                	
                	<div class="box-content" id="search">
							<form class="form-inline" role="form" id='searchForm'>
							<label class="control-label">商家名称</label>
							<input type="text" class="form-control" name="[Like]businessName" id="[Like]businessName">
							<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
							<a class="btn btn-success" onclick="searchFunc()" ><i class="glyphicon glyphicon-search icon-white"></i>查询</a> 
							<a class="btn btn-danger" onclick="clearFunc()" ><i class="glyphicon glyphicon-remove icon-white"></i>清除</a> 
							</form>
						</div>
                	
                	
                     <table class="table table-striped table-bordered table-hover" id="TableBusiness"></table>
                </div>
            </div>
</div>



<!-- 查看对话框 -->																
<div class="modal fade" id="showBusinessDialog" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
         aria-hidden="true">

        <div class="modal-dialog">
            <div class="modal-content">
              <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>  
                    <h4 class="modal-title">查看商家</h4> 
                </div>
                <div class="modal-body">
                   <table width="90%">
                   		<tr>
							<td width="20%"><label  class="control-label">商家名称</label></td>
							<td width="30%"><label  id=businessNameDetail class="control-label">商家名称</label></td>
							<td width="20%"><label  class="control-label">商家电话</label></td>
							<td width="30%"><label  id="businessPhoneDetail" class="control-label">商家电话</label></td>	
						</tr>
						<tr>
							<td width="20%"><label  class="control-label">商家地址</label></td>
							<td width="30%"><label  id="businessStrDetail" class="control-label">商家地址</label></td>
						</tr>
						
						<tr>
							<td width="20%"><label  class="control-label">商家QQ</label></td>
							<td width="30%"><label  id="businessQqDetail" class="control-label">商家QQ</label></td>
							<td width="20%"><label  class="control-label">商家微信</label></td>
							<td width="30%"><label  id="businessWeixinDetail" class="control-label">商家微信</label></td>
                		<tr>
                			<td width="20%"><label  class="control-label">商家简介</label></td>
                		</tr>
                		<tr>									
			                <td width="20%"><label  id="businessIntroDetail" class="control-label">商家微信</label></td>
			            </tr>
                		
                			
                		
                	</table>
                          
                </div>
                <hr>
                <div class="modal-footer">
                    <a  href="#" class="btn btn-primary" data-dismiss="modal">关闭</a>
                </div>
            </div>
        </div>
</div>
<!-- 新建和修改 -->
 <div class="modal fade" id="businessDialog" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
         aria-hidden="true">

        <div class="modal-dialog" style="width:800px">
            <div class="modal-content">
              <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" >×</button>  
                    <h3><span id='title'>修改商家信息</span></h3>
                </div>
                <div class="modal-body">
                   <form class="form-inline" role="form" id='businessForm'>   
                    	
                    	<div class="form-group has-success has-feedback" id="showBusiness">
							<table >
                        	<tr>
								<td ><label class="control-label" for="businessName">商家名称</label></td>
								<td ><input type="text" class="form-control" id="businessName" name="businessName"></td>
								<td ><label class="control-label" for="businessPhone">电话</label></td>
								<td ><input type="text" class="form-control" id="businessPhone" name="businessPhone"></td>
							</tr>
							<tr>
								<td><label class="control-label" for="businessWeixin">商家微信</label></td>
								<td><input type="text" class="form-control" id="businessWeixin" name="businessWeixin"></td>
								<td><label class="control-label" for="businessQq">QQ</label></td>
								<td><input type="text" class="form-control" id="businessQq" name="businessQq"></td>
							
							</tr>
							<tr>							
								<td ><label class="control-label" for="businessStr">商家地址</label></td>
								<td colspan="3"><input style="width:535px" type="text" class="form-control" id="businessStr" name="businessStr"></td>	
							</tr>
							<tr>
						        <td><input type="button" value="网站图标..." class="form-control" id="btnUploadFile" name="upload" /></td>
								<td><img id="logo" src="../images/no.jpg" width="300px" /></td>															
							</tr>
							<tr>							
								<td ><label class="control-label" for="businessIntro">商家简介</label></td>
								<td colspan="3"><textarea rows="5" style="width:535px" class="form-control" id="businessIntro" name="businessIntro"></textarea></td>
							</tr>
						</table>
						<input type="hidden"   name="businessId" id="businessId">
                    	<input type="hidden"    id="goodsId" name="goodsId">
                    	<input type= "hidden" name="bussinessImagePath" id="bussinessImagePath" value="">
                    	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/> 
						</div>                       
 
   					</form>
                    
             	</div>
                <div class="modal-footer">
                     <a href="javascript:void(0)" class="btn btn-default " data-dismiss="modal">取消</a>
                     <a href="javascript:void(0)" class="btn btn-primary" id="courseOKButton" onclick="onOKButton()">确定</a>
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

</body>
</html>