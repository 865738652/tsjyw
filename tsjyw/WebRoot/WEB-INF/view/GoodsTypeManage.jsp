<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<META HTTP-EQUIV="pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate">
<META HTTP-EQUIV="expires" CONTENT="Wed, 26 Feb 1997 08:21:57 GMT">


<html lang="en">
<head>    
    <title>管理商品类型</title>
    <%@include file="head.jsp" %>
    
    <script type="text/javascript">
    	 
    	var action = "create";    	
    	$(function(){
    	
    		$.ajaxSetup ({
         		cache: false //关闭AJAX缓存
     		});
    		
    		loadGoodsType();
    		
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
    	
    	function loadGoodsType()
    	{ 
    		
    		$("#TableGoodsType").bootstrapTable({
    			
		    	url: "getAllGoodsType", 
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
			    idField: "GoodsTypeId",  //标识哪个字段为id主键  
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
				        	field: 'goodsTypeId', 
		                    title: '类型代码',		 
		                    width: 100,		 
		                    align: 'center',		 
		                    valign: 'middle',		 
		                    sortable: true,
				        },
				        {
				        	field: 'goodsTypeName', 
		                    title: '类型名称',		 
		                    width: 100,		 
		                    align: 'center',		 
		                    valign: 'middle'
				        },	     
				        {
				        	field: 'goodsTypeSerial', 
		                    title: '类型序号',		 
		                    width: 100,		 
		                    align: 'center',		 
		                    valign: 'middle'
				        },	      
				        {
		                    title: '操作',
		                    field: 'goodsTypeId',
		                    formatter:function(value,row,index){		                    		                        		                        
	                   			var a = '<a class="btn btn-info" data-toggle="modal" data-target="#goodsTypeDialog" href="javascript:void(0);" onclick=\"modifyGoodsType('+value+')\">'; 
	                   			var b = '<i class="glyphicon glyphicon-edit icon-white"></i>修改</a> ';	                   			
	                   			var c = '<a class="btn btn-danger" href="javascript:void(0);" onclick=\"deleteGoodsType('+value+')\" >';
	                   			var d = '<i class="glyphicon glyphicon-remove icon-white"></i>删除</a> ';   			
	                        	return a+b+c+d;  
		                  	}
                  		}				        
				],
				onLoadSuccess: function (data) {
					
				}
		    });
    	}
   		
   		$("#goodsTypeDialog").on("hidden.bs.model",function(e){$(this).removeData();}); 
   		
   		function createGoodsType() {   			
   			action = "create";
   			$("#title").html("添加商品类型");
   			$('#goodsTypeForm')[0].reset();
   			$("#goodsTypeId").val("");
   		}
   		
   		function modifyGoodsType(goodsTypeId) {
   			action = "modify";
   			$("#title").html("修改商品类型信息");
   			$('#goodsTypeForm')[0].reset();
   			$("#goodsTypeId").val(goodsTypeId);
   			
			$.ajax({
				url:"getGoodsType",			
				data:{"goodsTypeId":goodsTypeId},
				dataType:"json",
				success:function(data) {  
			        if (data.code != "succ") {
   						alert(data.data);
   						$('#goodsTypeDialog').modal('hide');
   						return;
   					}
  			        $('#goodsTypeId').val(data.data.goodsTypeId);
					$('#goodsTypeName').val(data.data.goodsTypeName);
					$('#goodsTypeSerial').val(data.data.goodsTypeSerial);
			    },
			    error: function(XMLHttpRequest, textStatus, errorThrown) {
					alert("网络错误: " + XMLHttpRequest.status + ", " + textStatus);
			    }
			});	   						
   		}
   		
   		function deleteGoodsType(goodsTypeId) {
   			if (!confirm("确认删除该商品类型吗？"))
   				return;
   				
   			$.ajax({
				url:"deleteGoodsType",			
				data:{"goodsTypeId":goodsTypeId},
				dataType:"json",
				success:function(data) {  
					if (data.code != "succ") {
   						alert(data.data);
   					}
      				$("#TableGoodsType").bootstrapTable('refresh');
  				},
			    error: function(XMLHttpRequest, textStatus, errorThrown) {
					alert("网络错误: " + XMLHttpRequest.status + ", " + textStatus);
			    }
			});
   		}
   		
   		function onOKButton() {
   			var url = "createGoodsType";
   			
   			if (action == "modify")
   				url = "modifyGoodsType";
   				
   			$.ajax({   				
	        	url:url,
	        	type:"post",
	        	data:$('#goodsTypeForm').serialize(),
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
	        		$('#goodsTypeDialog').modal('hide');
	        		$("#TableGoodsType").bootstrapTable('refresh');
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
                <a href="GoodsTypeManage">商品类型管理</a>
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
                	<div class="alert alert-info">
                    <a class="btn btn-success" data-toggle="modal"  data-target="#goodsTypeDialog" onclick="createGoodsType()" >
                        <i class="glyphicon glyphicon-plus icon-white"></i>添加商品类型</a>                 
                </div>
                     <table class="table table-striped table-bordered table-hover" id="TableGoodsType"></table>
                </div>
            </div>
        </div>
 </div>
</div>
</div>
    
 
<div class="modal fade" id=goodsTypeDialog tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
         aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
              <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>  
                    <h3><span id='title'>添加商品类型</span></h3> 
                </div>
                <div class="modal-body">
                   <form class="form-inline" role="form" id='goodsTypeForm'>
                   		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/> 
                    	<input type="hidden" class="form-control"  name="goodsTypeId" id="goodsTypeId">
                    	<div class="form-group has-success has-feedback">
							<table align="center">
								
								<tr>
									<td><label class="control-label" for="goodsTypeName">类型名称</label></td>
									<td><input type="text" class="form-control" id="goodsTypeName" name="goodsTypeName"></td>
								</tr>
								<tr>
									<td><label class="control-label" for="goodsTypeSerial">类型序号</label></td>
									<td><input type="text" class="form-control" id="goodsTypeSerial" name="goodsTypeSerial"></td>
								</tr> 
							</table>
						</div>                       
 
   					</form>                   
                </div>
                <div class="modal-footer">
                    <a href="#" class="btn btn-default" data-dismiss="modal">取消</a>
                    <a href="#" class="btn btn-primary" id="goodsTypeOKButton" onclick="onOKButton()">确定</a>
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