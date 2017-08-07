<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>区县管理员</title>
    <%@include file="head.jsp" %>
    
    <script type="text/javascript">
    	var action = "create";
    	var countyId = <c:out value="${countyid}"/>; 
    	$(function(){
    	
    		$.ajaxSetup ({
         		cache: false //关闭AJAX缓存
     		});
    		
    		loadCountyManager();
    		//bindSelect("teacherStateId", "getTeacherState", null, "teacherStateId", "teacherStateName");
    	});
    	function queryParams(params) {  //配置参数
	        var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
	            pageSize: params.pageSize,   //页面大小
	            pageNumber: params.pageNumber,  //页码
				countyId:<c:out value="${countyid}"/>
	        };
	        <c:if test="${not empty _csrf}">
				temp.${_csrf.parameterName} = "${_csrf.token}";
			</c:if>
        	return temp;
    	}
    	
    	function loadCountyManager()
    	{
    		$("#TableCountyManager").bootstrapTable({
		    	url: "getCountyManagerByCounty", 
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
			    idField: "CountyManagerId",  //标识哪个字段为id主键  
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
				        	field: 'userName', 
		                    title: '姓名',		 
		                    width: 100,		 
		                    align: 'center',		 
		                    valign: 'middle',		 
		                    sortable: true,
				        },
				        {
				        	field: 'userNickname', 
		                    title: '昵称',		 
		                    width: 100,		 
		                    align: 'center',		 
		                    valign: 'middle',		 
		                    sortable: true,
				        },
				        {
				        	field: 'userAccount', 
		                    title: '帐号',		 
		                    width: 100,		 
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
		                    title: '操作',
		                    field: 'userId',
		                    formatter:function(value,row,index){
		                    	var a = '<a class="btn btn-info" data-toggle="modal" data-target="#showCountyManagerDialog" href="javascript:void(0);" onclick=\"showCountyManager('+value+')\">';
   								var b = '<i class="glyphicon glyphicon-zoom-in icon-white"></i>详情信息</a> ';	
	                   			var c = '<a class="btn btn-info" data-toggle="modal" data-target="#CountyManagerDialog" href="javascript:void(0);" onclick=\"modifyCountyManager('+value+')\">'; 
	                   			var d = '<i class="glyphicon glyphicon-zoom-in icon-white"></i>修改</a> ';
	                   			var e = '<a class="btn btn-success" href="javascript:void(0);" onclick=\"cancelCountyManager('+value+')\" >';
	                   			var f = '<i class="glyphicon glyphicon-chevron-right icon-white"></i>撤销</a> '; 	
	                   			var g = '<a class="btn btn-danger" href="javascript:void(0);" onclick=\"deleteCountyManager('+value+')\" >';
	                   			var h = '<i class="glyphicon glyphicon-zoom-in icon-white"></i>删除</a> ';  		
	                        	return a+b+c+d+e+f+g+h;  
		                  	}
                  		}				        
				],
				onLoadSuccess: function (data) {
					
				}
		    });
    	}
    	function showCountyManager(countyManagerId)
   		{
   			$('#showCountyManagerDialog').on('show.bs.modal', function () {
   				$.ajax({
   					url:"getCountyManager",			
   					data:{"countyManagerId":countyManagerId},
   					dataType:"json",
   					success:function(data) {  
   						if (data.code != "succ") {
   							alert(data.data);
   							$('#showCountyManagerDialog').modal('hide');
   							return;
   						}
   						
				        fillUserDetail(data.data);
				        
				        $("#countyNameDetail").text(data.data.countyName);
				    },
				    error: function(XMLHttpRequest, textStatus, errorThrown) {
						alert("网络错误: " + XMLHttpRequest.status + ", " + textStatus);
				    }
   				});	
   			});
   			
   		}
    	
   		$("#CountyManagerDialog").on("hidden.bs.model",function(e){$(this).removeData();}); 
   		
   		function selectCountyManager() {
   			selectUser(null, null, "../UserManage/getAllUser", null, onSelectComplete);
   		}
   		
   		function createCountyManager() {
   			action = "create";
   			$("#title").html("选择用户作管理员");
   			$('#countyManagerForm')[0].reset();
   			$("#userId").val("");
   			$("#countyId").val(""+countyId);
   			onUserShow(true);
   		}
   		
   		function modifyCountyManager(countyManagerId) {
   			action = "modify";
   			$("#title").html("修改区县管理员信息");
   			$('#countyManagerForm')[0].reset();
   			onUserShow(false);
			$.ajax({
				url:"getCountyManager",			
				data:{"countyManagerId":countyManagerId},
				dataType:"json",
				success:function(data) {  
			        if (data.code != "succ") {
   						alert(data.data);
   						$('#CountyManagerDialog').modal('hide');
   						return;
   					}
  			        fillUser(data.data);
			        $('#countyId').val(data.data.countyId);
			    },
			    error: function(XMLHttpRequest, textStatus, errorThrown) {
					alert("网络错误: " + XMLHttpRequest.status + ", " + textStatus);
			    }
			});	   						
   		}
   		
   		function cancelCountyManager(countyManagerId) {
   			if (!confirm("确认撤销管理员吗？"))
   				return;
   				
   			$.ajax({
				url:"cancelCountyManager",			
				data:{"countyManagerId":countyManagerId},
				dataType:"json",
				success:function(data) {  
					if (data.code != "succ") {
   						alert(data.data);
   						$('#CountyManagerDialog').modal('hide');
   						return;
   					}
      				$("#TableCountyManager").bootstrapTable('refresh');
  				},
			    error: function(XMLHttpRequest, textStatus, errorThrown) {
					alert("网络错误: " + XMLHttpRequest.status + ", " + textStatus);
			    }
			});
   		}
   		
   		function deleteCountyManager(countyManagerId) {
   			if (!confirm("确认删除管理员吗？"))
   				return;
   				
   			$.ajax({
				url:"deleteCountyManager",			
				data:{"countyManagerId":countyManagerId},
				dataType:"json",
				success:function(data) {  
					if (data.code != "succ") {
   						alert(data.data);
   					}
      				$("#TableCountyManager").bootstrapTable('refresh');
  				},
			    error: function(XMLHttpRequest, textStatus, errorThrown) {
					alert("网络错误: " + XMLHttpRequest.status + ", " + textStatus);
			    }
			});
   		}
   		
   		function onOKButton() {
   			var url = "createCountyManager";
   			if (action == "modify")
   				url = "modifyCountyManager";

			onUserSubmit();
   			$.ajax({
	        	url:url,
	        	type:"post",
	        	data:$('#countyManagerForm').serialize(),
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
	        		$('#CountyManagerDialog').modal('hide');
	        		$("#TableCountyManager").bootstrapTable('refresh');
	        	},
			    error: function(XMLHttpRequest, textStatus, errorThrown) {
					alert("网络错误: " + XMLHttpRequest.status + ", " + textStatus);
			    }
	        });
   		} 
   		
   		
   		
   		function onSelectComplete(user) {
   			$.ajax({
	        	url:"selectCountyManager",
	        	type:"post",
	        	data:{
	        	"userId": user.userId, 
	        	"countyId": countyId,
	        	"${_csrf.parameterName}":"${_csrf.token}"
	        	},
	        	dataType:"json",
	        	success:function(data){
	        		if (data.code != "succ") {
						alert(data.data);
						return;
					}
					if (action == "create")
						alert("创建成功");
					else
						alert("调入成功");
	        		$("#TableCountyManager").bootstrapTable('refresh');
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
		                <a href="#">区县管理</a>
		            </li>
		            <li>
		                <a href="#">设置管理员</a>
		            </li>
		        </ul>
		    </div> 
		    <div class="row">
		        <div class="box col-md-12">
		            <div class="box-inner">
		                <div class="box-header well" data-original-title="">
		                    <h2>区县管理员</h2>
					
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
			                    <a  class="btn btn-success btn-setting" onclick="selectCountyManager()">
							      	<i class="glyphicon glyphicon-plus icon-white"></i>选择用户作为管理员</a>
							    <a  class="btn btn-success btn-setting" data-toggle="modal" data-target="#CountyManagerDialog" onclick="createCountyManager()">
							      	<i class="glyphicon glyphicon-plus icon-white"></i>添加一个新用户作为管理员</a>                  
			                </div>
		                    <table id="TableCountyManager" class="table table-striped table-bordered bootstrap-datatable responsive"></table>
		                </div>
		                <!-- 查看区县管理员信息 -->
						 <div class="modal fade" id="showCountyManagerDialog" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
					         aria-hidden="true">
					
					        <div class="modal-dialog">
					            <div class="modal-content">
					                <div class="modal-header">
					                    <button type="button" class="close" data-dismiss="modal">×</button>
					                    <h3>查看区县管理员信息</h3>
					                </div>
					                <div class="modal-body">
					                	<label  id="countyNameDetail" class="control-label">区县</label>
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
		                <div class="modal fade" id="CountyManagerDialog" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					        <div class="modal-dialog"  style="width:800px">
					            <div class="modal-content">
					                <div class="modal-header">
					                    <button type="button" class="close" data-dismiss="modal">×</button>
					                    <h3><span id='title'>添加管理员信息</span></h3>
					                </div>
					                <div class="modal-body">
					                    <form class="form-inline" role="form" id='countyManagerForm'>
					                    	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/> 
					                    	<%@include file="user.jsp" %>
					                    	<div class="form-group has-success has-feedback" id="LookCountyManager">
					                        <table  width="680px">
					                        	<tr>
													<td width="20%"><label class="control-label" for="countyId"></label></td>
													<td width="30%"><input type="hidden" id="countyId" name="countyId"></td>								
												</tr>
											</table>
											</div>
					                    </form>                                                                
					                </div>
					                <!-- body -->
					                <hr>
					                <div class="modal-footer">
					                     <a href="javascript:void(0)" class="btn btn-default " data-dismiss="modal">取消</a>
					                     <a href="javascript:void(0)" class="btn btn-primary" id="countyManagerOKButton" onclick="onOKButton()">确定</a>
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
		  	<%@include file="selectuser.jsp" %>

</body>
</html>
