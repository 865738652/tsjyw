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
    <title>管理区县</title>
    <%@include file="head.jsp" %>
    
    <script type="text/javascript">
    	 
    	var action = "create";    	
    	$(function(){
    	
    		$.ajaxSetup ({
         		cache: false //关闭AJAX缓存
     		});
    		
    		loadCounty();
    		
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
    	
    	function loadCounty()
    	{ 
    		
    		$("#TableCounty").bootstrapTable({
    			
		    	url: "getAllCounty", 
		    	method:"post",
		    	contentType: "application/x-www-form-urlencoded;charset=utf-8",
		    	queryParams: queryParams,
		    	queryParamsType:"undefined",//参数格式,发送标准的restful类型的参数请求
		    	striped: true,     //使表格带有条纹  
  				pagination: true, //在表格底部显示分页工具栏 
		    	dataType: "json",
			    pagination: true,
			    search: false, //是否显示右上角的搜索框  
			    sidePagination: "server", 
			    //showHeader: false,  隐藏表头
			    idField: "CountyId",  //标识哪个字段为id主键  
			    pageSize: 10,  //每一页 的 数量
				pageNumber:1,     //当前页码
				//pageList: [3,6,9],  
				//idField: "ProductId",  //标识哪个字段为id主键  
				//showToggle: false,   //名片格式  
				//cardView: false,//设置为True时显示名片（card）布局  
				//showColumns: true, //显示隐藏列    
				showRefresh: false,  //显示刷新按钮  
				singleSelect: true,//复选框只能选择一条记录  
				clickToSelect: true,//点击行即可选中单选/复选框  
				//toolbar: "#toolbar", //设置工具栏的Id或者class  
				//silent: true,  //刷新事件 
				columns: [
				        {
				        	field: 'countyId', 
		                    title: '区县代码',		 
		                    width: 100,		 
		                    align: 'center',		 
		                    valign: 'middle',		 
		                    sortable: true,
				        },
				        {
				        	field: 'countyName', 
		                    title: '区县名称',		 
		                    width: 100,		 
		                    align: 'center',		 
		                    valign: 'middle'
				        },
				        {
				        	field: 'schoolCount', 
		                    title: '学校数量',		 
		                    width: 100,		 
		                    align: 'center',		 
		                    valign: 'middle'
				        },				        
				        {
		                    title: '操作',
		                    field: 'countyId',
		                    formatter:function(value,row,index){		                    		                        		                        
	                   			var a = '<a class="btn btn-info" data-toggle="modal" data-target="#countyDialog" href="javascript:void(0);" onclick=\"modifyCounty('+value+')\">'; 
	                   			var b = '<i class="glyphicon glyphicon-edit icon-white"></i>修改</a> ';	                   			
	                   			var c = '<a class="btn btn-danger" href="javascript:void(0);" onclick=\"deleteCounty('+value+')\" >';
	                   			var d = '<i class="glyphicon glyphicon-remove icon-white"></i>删除</a> '; 
	                   			var e = '<a class="btn btn-danger" href="../CountyPeople/' + value + '">';
	                   			var f = '<i class="glyphicon glyphicon-edit icon-white"></i>设置管理员</a> ';
	                   			var g = '<a class="btn btn-danger" href="../SchoolManage/' + value + '">';
	                   			var h = '<i class="glyphicon glyphicon-zoom-in icon-white"></i>管理学校</a> ';    			
	                        	return a+b+c+d+e+f+g+h;  
		                  	}
                  		}				        
				],
				onLoadSuccess: function (data) {
					
				}
		    });
    	}
   		
   		$("#countyDialog").on("hidden.bs.model",function(e){$(this).removeData();}); 
   		
   		function createCounty() {   			
   			action = "create";
   			$("#title").html("添加区县");
   			$('#countyForm')[0].reset();
   			$("#countyId").val("");
   		}
   		
   		function modifyCounty(countyId) {
   			action = "modify";
   			$("#title").html("修改区县信息");
   			$('#countyForm')[0].reset();
   			$("#countyId").val(countyId);
   			
			$.ajax({
				url:"getCounty",			
				data:{"countyId":countyId},
				dataType:"json",
				success:function(data) {  
			        if (data.code != "succ") {
   						alert(data.data);
   						$('#countyDialog').modal('hide');
   						return;
   					}
  			        $('#countyId').val(data.data.countyId);
					$('#countyName').val(data.data.countyName);
			    },
			    error: function(XMLHttpRequest, textStatus, errorThrown) {
					alert("网络错误: " + XMLHttpRequest.status + ", " + textStatus);
			    }
			});	   						
   		}
   		
   		function deleteCounty(countyId) {
   			if (!confirm("确认删除该区县吗？"))
   				return;
   				
   			$.ajax({
				url:"deleteCounty",			
				data:{"countyId":countyId},
				dataType:"json",
				success:function(data) {  
					if (data.code != "succ") {
   						alert(data.data);
   					}
      				$("#TableCounty").bootstrapTable('refresh');
  				},
			    error: function(XMLHttpRequest, textStatus, errorThrown) {
					alert("网络错误: " + XMLHttpRequest.status + ", " + textStatus);
			    }
			});
   		}
   		
   		function onOKButton() {
   			var url = "createCounty";
   			
   			if (action == "modify")
   				url = "modifyCounty";
   				
   			$.ajax({   				
	        	url:url,
	        	type:"post",
	        	data:$('#countyForm').serialize(),
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
	        		$('#countyDialog').modal('hide');
	        		$("#TableCounty").bootstrapTable('refresh');
	        	},
			    error: function(XMLHttpRequest, textStatus, errorThrown) {
					alert("网络错误: " + XMLHttpRequest.status + ", " + textStatus);
			    }
	        });
   		}  
		
		function searchFunc() {
			$("#TableCounty").bootstrapTable('refresh');
		}

		function clearFunc() {
			$('#searchForm')[0].reset();
			$("#TableCounty").bootstrapTable('refresh');
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
                <a href="#">区县管理</a>
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
                	<sec:authorize access="hasAuthority('SuperAdmin')">
                    <a class="btn btn-success" data-toggle="modal"  data-target="#countyDialog" onclick="createCounty()" >
                        <i class="glyphicon glyphicon-plus icon-white"></i>添加区县</a>    
                    </sec:authorize>             
                	</div>
					<div id="search">
						<form class="form-inline" role="form" id='searchForm'>
						<label class="control-label">区县名称</label>
						<input type="text" class="form-control" name="[Like]countyName" id="[Like]countyName">
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
						<a class="btn btn-success" onclick="searchFunc()" ><i class="glyphicon glyphicon-plus icon-white"></i>查询</a> 
						<a class="btn btn-success" onclick="clearFunc()" ><i class="glyphicon glyphicon-plus icon-white"></i>清除</a> 
						</form>
					</div>
                    <table class="table table-striped table-bordered table-hover" id="TableCounty"></table>
                </div>
            </div>
        </div>
 </div>
</div>
</div>
    
 
<div class="modal fade" id=countyDialog tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
         aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
              <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>  
                    <h3><span id='title'>修改区县信息</span></h3> 
                </div>
                <div class="modal-body">
                   <form class="form-inline" role="form" id='countyForm'>
                   		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/> 
                    	<input type="hidden" class="form-control"  name="countyId" id="countyId">
                    	<div class="form-group has-success has-feedback" id="showTeacher">
							<table width="90%">
								<tr>
									<td width="40%">		    
						              <label class="control-label" for="inputSuccess4">区县名称</label>
						            </td>
						            <td width="60%">
						              <input type="text" class="form-control" name="countyName" id="countyName">	
						            </td>
								</tr>
							</table>
						</div>                       
 
   					</form>                   
                </div>
                <div class="modal-footer">
                    <a href="#" class="btn btn-default" data-dismiss="modal">取消</a>
                    <a href="#" class="btn btn-primary" id="countyOKButton" onclick="onOKButton()">确定</a>
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