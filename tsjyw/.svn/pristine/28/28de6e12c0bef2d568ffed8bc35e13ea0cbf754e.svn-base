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
    <title>管理网络课程类型</title>
    <%@include file="head.jsp" %>
    
    <script type="text/javascript">
    	 
    	var action = "create";    	
    	$(function(){
    	
    		$.ajaxSetup ({
         		cache: false //关闭AJAX缓存
     		});
    		
    		loadNetCourseType();
    		
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
    	
    	function loadNetCourseType()
    	{ 
    		
    		$("#TableNetCourseType").bootstrapTable({
    			
		    	url: "getAllNetCourseType", 
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
			    idField: "netCourseTypeId",  //标识哪个字段为id主键  
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
				        	field: 'netCourseTypeId', 
		                    title: '类型代码',		 
		                    width: 100,		 
		                    align: 'center',		 
		                    valign: 'middle',		 
		                    sortable: true,
				        },
				        {
				        	field: 'netCourseTypeName', 
		                    title: '类型名称',		 
		                    width: 100,		 
		                    align: 'center',		 
		                    valign: 'middle'
				        },	     
				        {
		                    title: '操作',
		                    field: 'netCourseTypeId',
		                    formatter:function(value,row,index){		                    		                        		                        
	                   			var a = '<a class="btn btn-info" data-toggle="modal" data-target="#netCourseTypeDialog" href="javascript:void(0);" onclick=\"modifyNetCourseType('+value+')\">'; 
	                   			var b = '<i class="glyphicon glyphicon-edit icon-white"></i>修改</a> ';	                   			
	                   			var c = '<a class="btn btn-danger" href="javascript:void(0);" onclick=\"deleteNetCourseType('+value+')\" >';
	                   			var d = '<i class="glyphicon glyphicon-remove icon-white"></i>删除</a> ';   			
	                        	return a+b+c+d;  
		                  	}
                  		}				        
				],
				onLoadSuccess: function (data) {
					
				}
		    });
    	}
   		
   		$("#netCourseTypeDialog").on("hidden.bs.model",function(e){$(this).removeData();}); 
   		
   		function createNetCourseType() {   			
   			action = "create";
   			$("#title").html("添加网络课程类型");
   			$('#netCourseTypeForm')[0].reset();
   			$("#netCourseTypeId").val("");
   		}
   		
   		function modifyNetCourseType(netCourseTypeId) {
   			action = "modify";
   			$("#title").html("修改网络课程类型信息");
   			$('#netCourseTypeForm')[0].reset();
   			$("#netCourseTypeId").val(netCourseTypeId);
   			
			$.ajax({
				url:"getNetCourseType",			
				data:{"netCourseTypeId":netCourseTypeId},
				dataType:"json",
				success:function(data) {  
			        if (data.code != "succ") {
   						alert(data.data);
   						$('#netCourseTypeDialog').modal('hide');
   						return;
   					}
  			        $('#netCourseTypeId').val(data.data.netCourseTypeId);
					$('#netCourseTypeName').val(data.data.netCourseTypeName);
				
			    },
			    error: function(XMLHttpRequest, textStatus, errorThrown) {
					alert("网络错误: " + XMLHttpRequest.status + ", " + textStatus);
			    }
			});	   						
   		}
   		
   		function deleteNetCourseType(netCourseTypeId) {
   			if (!confirm("确认删除该网络课程类型吗？"))
   				return;
   				
   			$.ajax({
				url:"deleteNetCourseType",			
				data:{"netCourseTypeId":netCourseTypeId},
				dataType:"json",
				success:function(data) {  
					if (data.code != "succ") {
   						alert(data.data);
   					}
      				$("#TableNetCourseType").bootstrapTable('refresh');
  				},
			    error: function(XMLHttpRequest, textStatus, errorThrown) {
					alert("网络错误: " + XMLHttpRequest.status + ", " + textStatus);
			    }
			});
   		}
   		
   		function onOKButton() {
   			var url = "createNetCourseType";
   			
   			if (action == "modify")
   				url = "modifyNetCourseType";
   				
   			$.ajax({   				
	        	url:url,
	        	type:"post",
	        	data:$('#netCourseTypeForm').serialize(),
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
	        		$('#netCourseTypeDialog').modal('hide');
	        		$("#TableNetCourseType").bootstrapTable('refresh');
	        	},
			    error: function(XMLHttpRequest, textStatus, errorThrown) {
					alert("网络错误: " + XMLHttpRequest.status + ", " + textStatus);
			    }
	        });
   		}  
   		
   		$(document).ready(function() {
	    $('#netCourseTypeForm').bootstrapValidator();
				}); 		
   		
    </script>
    
</head>
<body>
    <%@include file="top.jsp" %>
    <div class="ch-container">
        <div class="row">
            <%@include file="left.jsp" %>



        <div id="content" class="col-lg-10 col-sm-10">
    <div>
        <ul class="breadcrumb">
            <li>
                <a href="#">网络课程</a>
            </li>
            <li>
                <a href="#">网络课程类型管理</a>
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
                    <a class="btn btn-success" data-toggle="modal"  data-target="#netCourseTypeDialog" onclick="createNetCourseType()" >
                        <i class="glyphicon glyphicon-plus icon-white"></i>添加网络课程类型</a>                 
                </div>
                     <table class="table table-striped table-bordered table-hover" id="TableNetCourseType"></table>
                </div>
            </div>
        </div>
 </div>
</div>
</div>
    
 
<div class="modal fade" id=netCourseTypeDialog tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
         aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
              <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>  
                    <h3><span id='title'>添加商品类型</span></h3> 
                </div>
                <div class="modal-body">
                   <form class="form-inline" role="form" id='netCourseTypeForm' method="post" 
                   				data-bv-message="请输入一个值"
							    data-bv-feedbackicons-valid="glyphicon glyphicon-ok"
							    data-bv-feedbackicons-invalid="glyphicon glyphicon-remove"
							    data-bv-feedbackicons-validating="glyphicon glyphicon-refresh">
                   
                   		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/> 
                    	<input type="hidden" class="form-control"  name="netCourseTypeId" id="netCourseTypeId"/>
                    	<div class="form-group has-success has-feedback">
							<table align="center">
								
								<tr>
									<td><label class="control-label" for="netCourseTypeName">类型名称</label></td>
									<td><input type="text" class="form-control" id="netCourseTypeName" name="netCourseTypeName"
									data-bv-notempty="true"
                					data-bv-notempty-message="类型名称不能为空" /></td>
								</tr>
							</table>
						</div>                       
   					</form>                   
                </div>
                <div class="modal-footer">
                    <a href="#" class="btn btn-default" data-dismiss="modal">取消</a>
                    <a href="#" class="btn btn-primary" id="netCourseTypeOKButton" onclick="onOKButton()">确定</a>
                </div>
            </div>
        </div>
     </div>
  </div>
</div>
        
 </div>
</body>
</html>