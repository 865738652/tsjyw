<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>用户管理</title>
    <%@include file="head.jsp" %>
    
    <script type="text/javascript">
    	var action = "create";

    	$(function(){
    	
    		$.ajaxSetup ({
         		cache: false //关闭AJAX缓存
     		});
    		
    		loadUser();
    	});
    	function queryParams(params) {  //配置参数
	        var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
	            pageSize: params.pageSize,   //页面大小
	            pageNumber: params.pageNumber  //页码
	        };
	        <c:if test="${not empty _csrf}">
				temp.${_csrf.parameterName} = "${_csrf.token}";
			</c:if>
			extendTalbeParams(temp, "searchForm");
        	return temp;
    	}
    	
    	function loadUser()
    	{
    		$("#TableUser").bootstrapTable({
		    	url: "getAllUser", 
		    	method:"post",
		    	contentType: "application/x-www-form-urlencoded;charset=utf-8",
		    	queryParams: queryParams,
		    	queryParamsType:"undefined",//参数格式,发送标准的restful类型的参数请求
		    	striped: true,     //使表格带有条纹  
  				pagination: true, //在表格底部显示分页工具栏 
		    	dataType: "json",
			    search: false, //是否显示右上角的搜索框  
			    sidePagination: "server", 
			    //showHeader: false,  隐藏表头
			    idField: "userId",  //标识哪个字段为id主键  
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
				        	field: 'userName', 
		                    title: '姓名',		 
		                    width: 150,		 
		                    align: 'center',		 
		                    valign: 'middle',		 
		                    sortable: true,
				        },
				        {
				        	field: 'userNickname', 
		                    title: '昵称',		 
		                    width: 150,		 
		                    align: 'center',		 
		                    valign: 'middle',		 
		                    sortable: true,
				        },
				        {
				        	field: 'userAccount', 
		                    title: '帐号',		 
		                    width: 150,		 
		                    align: 'center',		 
		                    valign: 'middle'
				        },
				        {
				        	field: 'userGender', 
		                    title: '性别',		 
		                    width: 50,	 
		                    align: 'center',		 
		                    valign: 'middle',
		                    formatter:function(value,row,index){
		                    	return value == 0 ? "男":"女";
		                    }
				        },
				        {
				        	field: 'userOpenId', 
		                    title: '是否绑定微信',		 
		                    width: 100,	 
		                    align: 'center',		 
		                    valign: 'middle',
		                    formatter:function(value,row,index){
		                    	return (value == null || value == undefined)?"--" : "是";
		                    }	
				        },
				        {
		                    title: '操作',
		                    field: 'userId',
		                    formatter:function(value,row,index){
		                    	var a = '<a class="btn btn-info" data-toggle="modal" data-target="#showUserDialog" href="javascript:void(0);" onclick=\"showUser('+value+')\">';
   								var b = '<i class="glyphicon glyphicon-zoom-in icon-white"></i>详情信息</a> ';	
	                   			var c = '<a class="btn btn-info" data-toggle="modal" data-target="#UserDialog" href="javascript:void(0);" onclick=\"modifyUser('+value+')\">'; 
	                   			var d = '<i class="glyphicon glyphicon-edit icon-white"></i>修改</a> '; 	
	                   			var e = '<a class="btn btn-danger" href="javascript:void(0);" onclick=\"deleteUser('+value+')\" >';
	                   			var f = '<i class="glyphicon glyphicon-remove icon-white"></i>删除</a> ';  		
	                        	return a+b+c+d+e+f;  
		                  	}
                  		}				        
				],
				onLoadSuccess: function (data) {
					
				}
		    });
    	}
    	function showUser(userId)
   		{
   			$.ajax({
				url:"getUser",			
				data:{"userId":userId},
				dataType:"json",
				success:function(data) {  
					if (data.code != "succ") {
						alert(data.data);
						$('#showUserDialog').modal('hide');
						return;
					}
					
        			fillUserDetail(data.data);
   				},
			    error: function(XMLHttpRequest, textStatus, errorThrown) {
					alert("网络错误: " + XMLHttpRequest.status + ", " + textStatus);
			    }
			});	
   		}
    	
   		$("#UserDialog").on("hidden.bs.model",function(e){$(this).removeData();}); 
   		
   		function createUser() {
   			action = "create";
   			$("#title").html("创建用户");
   			$('#userForm')[0].reset();
   			$("#userId").val("");
   			onUserShow(true);
   		}
   		
   		function modifyUser(userId) {
   			action = "modify";
   			$("#title").html("修改用户");
   			$('#userForm')[0].reset();
   			onUserShow(false);
			$.ajax({
				url:"getUser",			
				data:{"userId":userId},
				dataType:"json",
				success:function(data) {  
			        if (data.code != "succ") {
   						alert(data.data);
   						$('#UserDialog').modal('hide');
   						return;
   					}
  			        fillUser(data.data);
			    },
			    error: function(XMLHttpRequest, textStatus, errorThrown) {
					alert("网络错误: " + XMLHttpRequest.status + ", " + textStatus);
			    }
			});	   						
   		}
   		
   		function deleteUser(userId) {
   			if (!confirm("确认删除用户吗？"))
   				return;
   				
   			$.ajax({
				url:"deleteUser",			
				data:{"userId":userId},
				dataType:"json",
				success:function(data) {  
					if (data.code != "succ") {
   						alert(data.data);
   					}
      				$("#TableUser").bootstrapTable('refresh');
  				},
			    error: function(XMLHttpRequest, textStatus, errorThrown) {
					alert("网络错误: " + XMLHttpRequest.status + ", " + textStatus);
			    }
			});
   		}
   		
   		function onOKButton() {
   			var url = "createUser";
   			if (action == "modify")
   				url = "modifyUser";
   				
			onUserSubmit();			
   			$.ajax({
	        	url:url,
	        	type:"post",
	        	data:$('#userForm').serialize(),
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
	        		$('#UserDialog').modal('hide');
	        		$("#TableUser").bootstrapTable('refresh');
	        	},
			    error: function(XMLHttpRequest, textStatus, errorThrown) {
					alert("网络错误: " + XMLHttpRequest.status + ", " + textStatus);
			    }
	        });
   		}  
		
		function searchFunc() {
			$("#TableUser").bootstrapTable('refresh');
		}

		function clearFunc() {
			$('#searchForm')[0].reset();
			$("#TableUser").bootstrapTable('refresh');
		}
    </script>
</head>
<body>
    <%@include file="top.jsp" %>
    <!--左侧开始-->
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
		                <a href="#">用户管理</a>
		            </li>
		        </ul>
		    </div> 
		    <div class="row">
		        <div class="box col-md-12">
		            <div class="box-inner">
		                <div class="box-header well" data-original-title="">
		                    <h2>用户</h2>
					
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
			               	<div class="alert alert-info">
							    <a  class="btn btn-success btn-setting" data-toggle="modal" data-target="#UserDialog" onclick="createUser()">
							      	<i class="glyphicon glyphicon-plus icon-white"></i>添加用户</a>                  
			                </div>
							<div class="alert alert-info" id="search">
								<form class="form-inline" role="form" id='searchForm'>
								<label class="control-label">姓名 </label>
								<input type="text" class="form-control" name="[Like]userName" id="[Like]userName">
								<label class="control-label">昵称 </label>
								<input type="text" class="form-control" name="[Like]userNickName" id="[Like]userNickName">
								<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
								<a class="btn btn-success" onclick="searchFunc()" ><i class="glyphicon glyphicon-search icon-white"></i>查询</a> 
								<a class="btn btn-danger" onclick="clearFunc()" ><i class="glyphicon glyphicon-remove icon-white"></i>清除</a> 
								</form>
							</div>
		                    <table id="TableUser" class="table table-striped table-bordered bootstrap-datatable responsive"></table>
		                </div>
		                <!-- 查看用户信息 -->
						 <div class="modal fade" id="showUserDialog" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
					         aria-hidden="true">
					
					        <div class="modal-dialog">
					            <div class="modal-content">
					                <div class="modal-header">
					                    <button type="button" class="close" data-dismiss="modal">×</button>
					                    <h3>查看用户信息</h3>
					                </div>
					                <div class="modal-body">
					                	<%@include file="userDetail.jsp" %>					                	
					                </div>
					                <hr>
					                <div class="modal-footer">
					                     <a href="#" class="btn btn-primary" data-dismiss="modal">关闭</a>
					                </div>
					            </div>
					        </div>
					    </div>
					 	<!-- 查看区县管理员信息end -->
		                <!-- 修改和添加用户作管理员 -->
		                <div class="modal fade" id="UserDialog" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					        <div class="modal-dialog"  style="width:800px">
					            <div class="modal-content">
					                <div class="modal-header">
					                    <button type="button" class="close" data-dismiss="modal">×</button>
					                    <h3><span id='title'>修改用户信息</span></h3>
					                </div>
					                <div class="modal-body">
					                    <form class="form-inline" role="form" id='userForm'>
					                    	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
					                    	<%@include file="user.jsp" %>
					                    </form>                                                                
					                </div>
					                <!-- body -->
					                <hr>
					                <div class="modal-footer">
					                     <a href="javascript:void(0)" class="btn btn-default " data-dismiss="modal">取消</a>
					                     <a href="javascript:void(0)" class="btn btn-primary" id="userOKButton" onclick="onOKButton()">确定</a>
					                </div>
					            </div>
					        </div>
					    </div> 
		                <!-- 修改管理员end -->
		            </div>
		        </div>
			</div>
		    </div>
		  </div><!--/span-->

</body>
</html>
