<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>设置班主任</title>
    <%@include file="head.jsp" %>
    
    <script type="text/javascript">
    	var action = "create";
    	var schoolClassId = <c:out value="${schoolClassid}"/>; 
    	var schoolId = <c:out value="${schoolid}"/>; 
    	
    	$(function(){
    	
    		$.ajaxSetup ({
         		cache: false //关闭AJAX缓存
     		});
    		
    		loadClassMaster();
    		//bindSelect("teacherStateId", "getTeacherState", null, "teacherStateId", "teacherStateName");
    	});
    	function queryParams(params) {  //配置参数
	        var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
	            pageSize: params.pageSize,   //页面大小
	            pageNumber: params.pageNumber,  //页码
				schoolClassId:<c:out value="${schoolClassid}"/>
	        };
	        <c:if test="${not empty _csrf}">
				temp.${_csrf.parameterName} = "${_csrf.token}";
			</c:if>
        	return temp;
    	}
    	
    	function loadClassMaster()
    	{
    		$("#TableClassMaster").bootstrapTable({
		    	url: "getClassMasterBySchoolClass", 
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
			    idField: "classMasterId",  //标识哪个字段为id主键  
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
		                    field: 'classMasterId',
		                    formatter:function(value,row,index){
		                    	var a = '<a class="btn btn-info" data-toggle="modal" data-target="#showClassMasterDialog" href="javascript:void(0);" onclick=\"showClassMaster('+value+')\">';
   								var b = '<i class="glyphicon glyphicon-zoom-in icon-white"></i>详情信息</a> ';	
	                   			var c = '<a class="btn btn-info" data-toggle="modal" data-target="#classMasterDialog" href="javascript:void(0);" onclick=\"modifyClassMaster('+value+')\">'; 
	                   			var d = '<i class="glyphicon glyphicon-zoom-in icon-white"></i>修改</a> '; 
	                   			var e = '<a class="btn btn-success" href="javascript:void(0);" onclick=\"cancelClassMaster('+value+')\" >';
	                   			var f = '<i class="glyphicon glyphicon-chevron-right icon-white"></i>撤销</a> ';		
	                   			var g = '<a class="btn btn-danger" href="javascript:void(0);" onclick=\"deleteClassMaster('+value+')\" >';
	                   			var h = '<i class="glyphicon glyphicon-zoom-in icon-white"></i>删除</a> ';  		
	                        	return a+b+c+d+e+f+g+h;  
		                  	}
                  		}				        
				],
				onLoadSuccess: function (data) {
					
				}
		    });
    	}
    	function showClassMaster(classMasterId)
   		{
   			$('#showClassMasterDialog').on('show.bs.modal', function () {
   				$.ajax({
   					url:"getClassMaster",			
   					data:{"classMasterId":classMasterId},
   					dataType:"json",
   					success:function(data) {  
   						if (data.code != "succ") {
   							alert(data.data);
   							$('#showClassMasterDialog').modal('hide');
   							return;
   						}
   						
				        fillUserDetail(data.data);
				        
				        $("#schoolClassNameDetail").text(data.data.schoolClassName);
				    },
				    error: function(XMLHttpRequest, textStatus, errorThrown) {
						alert("网络错误: " + XMLHttpRequest.status + ", " + textStatus);
				    }
   				});	
   			});
   			
   		}
    	
   		$("#classMasterDialog").on("hidden.bs.model",function(e){$(this).removeData();}); 
   		
   		function selectClassMaster() {
   			selectUser(null, null, "../TeacherManage/getTeacherBySchool", {"schoolId":schoolId}, onSelectComplete);
   		}
   		
   		function createClassMaster() {
   			action = "create";
   			$("#title").html("添加一个新用户作为班主任");
   			$('#classMasterForm')[0].reset();
   			$("#userId").val("");
   			$("#classMasterId").val("");
   			$("#schoolClassId").val(""+schoolClassId);
   			onUserShow(true);
   		}
   		
   		function modifyClassMaster(classMasterId) {
   			action = "modify";
   			$("#title").html("修改班主任信息");
   			$('#classMasterForm')[0].reset();
   			onUserShow(false);
			$.ajax({
				url:"getClassMaster",			
				data:{"classMasterId":classMasterId},
				dataType:"json",
				success:function(data) {  
			        if (data.code != "succ") {
   						alert(data.data);
   						$('#classMasterDialog').modal('hide');
   						return;
   					}
   					$("#classMasterId").val(classMasterId);
  			        fillUser(data.data);
			        $('#schoolClassId').val(data.data.schoolClassId);
			    },
			    error: function(XMLHttpRequest, textStatus, errorThrown) {
					alert("网络错误: " + XMLHttpRequest.status + ", " + textStatus);
			    }
			});	   						
   		}
   		
   		function cancelClassMaster(classMasterId) {
   			if (!confirm("确认撤销班主任吗？"))
   				return;
   				
   			$.ajax({
				url:"cancelClassMaster",			
				data:{"classMasterId":classMasterId},
				dataType:"json",
				success:function(data) {  
					if (data.code != "succ") {
   						alert(data.data);
   						$('#ClassMasterDialog').modal('hide');
   						return;
   					}
      				$("#TableClassMaster").bootstrapTable('refresh');
  				},
			    error: function(XMLHttpRequest, textStatus, errorThrown) {
					alert("网络错误: " + XMLHttpRequest.status + ", " + textStatus);
			    }
			});
   		}
   		
   		function deleteClassMaster(classMasterId) {
   			if (!confirm("确认删除班主任吗？"))
   				return;

   			$.ajax({
				url:"deleteClassMaster",			
				data:{"classMasterId":classMasterId},
				dataType:"json",
				success:function(data) {  
					if (data.code != "succ") {
   						alert(data.data);
   					}
      				$("#TableClassMaster").bootstrapTable('refresh');
  				},
			    error: function(XMLHttpRequest, textStatus, errorThrown) {
					alert("网络错误: " + XMLHttpRequest.status + ", " + textStatus);
			    }
			});
   		}
   		
   		function onOKButton() {
   			var url = "createClassMaster";
   			if (action == "modify")
   				url = "modifyClassMaster";
   				
   			onUserSubmit();
   			$.ajax({
	        	url:url,
	        	type:"post",
	        	data:$('#classMasterForm').serialize(),
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
	        		$('#classMasterDialog').modal('hide');
	        		$("#TableClassMaster").bootstrapTable('refresh');
	        	},
			    error: function(XMLHttpRequest, textStatus, errorThrown) {
					alert("网络错误: " + XMLHttpRequest.status + ", " + textStatus);
			    }
	        });
   		}   
   		
   		function onSelectComplete(teacher) {
   			$.ajax({
	        	url:"selectClassMaster",
	        	type:"post",
	        	data:{
	        	"userId": teacher.userId, 
	        	"schoolClassId": schoolClassId,
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
	        		$("#TableClassMaster").bootstrapTable('refresh');
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
		                <a href="SchoolClassManage">班级管理</a>
		            </li>
		            <li>
		                <a href="ClassMaster">设置班主任</a>
		            </li>
		        </ul>
		    </div> 
		    <div class="row">
		        <div class="box col-md-12">
		            <div class="box-inner">
		                <div class="box-header well" data-original-title="">
		                    <h2>设置班主任</h2>
					
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
			                    <a  class="btn btn-success btn-setting" onclick="selectClassMaster()">
							      	<i class="glyphicon glyphicon-plus icon-white"></i>选择老师作为班主任</a>
							    <a  class="btn btn-success btn-setting" data-toggle="modal" data-target="#classMasterDialog" onclick="createClassMaster()">
							      	<i class="glyphicon glyphicon-plus icon-white"></i>添加一个新用户作为班主任</a>                  
			                </div>
		                    <table id="TableClassMaster" class="table table-striped table-bordered bootstrap-datatable responsive"></table>
		                </div>
		                <!-- 查看年级主任信息 -->
						 <div class="modal fade" id="showClassMasterDialog" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
					         aria-hidden="true">
					
					        <div class="modal-dialog">
					            <div class="modal-content">
					                <div class="modal-header">
					                    <button type="button" class="close" data-dismiss="modal">×</button>
					                    <h3>查看班主任信息</h3>
					                </div>
					                <div class="modal-body">
					                	<label  id="schoolClassNameDetail" class="control-label">班级</label>
					                	<%@include file="userDetail.jsp" %>					                	
					                </div>
					                <hr>
					                <div class="modal-footer">
					                     <a href="#" class="btn btn-primary" data-dismiss="modal">关闭</a>
					                </div>
					            </div>
					        </div>
					    </div>
					 	<!-- 查看班长任信息end -->
		                <!-- 修改和添加用户作班主任 -->
		                <div class="modal fade" id="classMasterDialog" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					        <div class="modal-dialog"  style="width:800px">
					            <div class="modal-content">
					                <div class="modal-header">
					                    <button type="button" class="close" data-dismiss="modal">×</button>
					                    <h3><span id='title'>修改班主任信息</span></h3>
					                </div>
					                <div class="modal-body">
					                    <form class="form-inline" role="form" id='classMasterForm'>
					                    	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/> 
					                    	<input type="hidden" id="classMasterId" name="classMasterId">
					                    	<%@include file="user.jsp" %>
					                    	<div class="form-group has-success has-feedback" id="LookClassMaster">
					                        <table  width="680px">
					                        	<tr>
													<td width="20%"><label class="control-label" for="schoolClassId"></label></td>
													<td width="30%"><input type="hidden" id="schoolClassId" name="schoolClassId"></td>								
												</tr>
											</table>
											</div>
					                    </form>                                                                
					                </div>
					                <!-- body -->
					                <hr>
					                <div class="modal-footer">
					                     <a href="javascript:void(0)" class="btn btn-default " data-dismiss="modal">取消</a>
					                     <a href="javascript:void(0)" class="btn btn-primary" id="classMasterOKButton" onclick="onOKButton()">确定</a>
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
