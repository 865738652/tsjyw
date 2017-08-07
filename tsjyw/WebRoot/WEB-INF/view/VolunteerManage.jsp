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
    
    <title>管理志愿者</title>
    <%@include file="head.jsp" %>
    
    <script type="text/javascript"> 
    	var action = "create";
    	
    	$(function(){
    	
    		$.ajaxSetup ({
         		cache: false //关闭AJAX缓存
     		});
    		
    		loadVolunteer();
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
    	
    	function loadVolunteer()
    	{
    		$("#TableVolunteer").bootstrapTable({
		    	url: "getAllVolunteer", 
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
			    idField: "volunteerId",  //标识哪个字段为id主键  
			    pageSize: 10,  //每一页 的 数量
				pageNumber:1,     //当前页码
				showRefresh: false,  //显示刷新按钮  
				singleSelect: true,//复选框只能选择一条记录  
				clickToSelect: true,//点击行即可选中单选/复选框  
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
				        	field: 'userAccount', 
		                    title: '帐号',		 
		                    width: 150,		 
		                    align: 'center',		 
		                    valign: 'middle'
				        },
				        {
				        	field: 'userNickname', 
		                    title: '昵称',		 
		                    width: 150,	 
		                    align: 'center',		 
		                    valign: 'middle'
				        },
				        {
				        	field: 'userPhone', 
		                    title: '电话',		 
		                    width: 100,		 
		                    align: 'center',		 
		                    valign: 'middle'
				        },
				        {
		                    title: '操作',
		                    field: 'userId',
		                    formatter:function(value,row,index){
		                    	var a = '<a class="btn btn-info" data-toggle="modal" data-target="#showVolunteerDialog" href="javascript:void(0);" onclick=\"showVolunteer('+value+')\">';
   								var b = '<i class="glyphicon glyphicon-zoom-in icon-white"></i>详情信息</a> ';		                        
	                   			var c = '<a class="btn btn-info" data-toggle="modal" data-target="#volunteerDialog" href="javascript:void(0);" onclick=\"modifyVolunteer('+value+')\">'; 
	                   			var d = '<i class="glyphicon glyphicon-edit icon-white"></i>修改</a> ';
	                   			var e = '<a class="btn btn-info" href="javascript:void(0);" onclick=\"cancelVolunteer('+value+')\" >';
	                   			var f = '<i class="glyphicon glyphicon-chevron-right icon-white"></i>取消志愿者</a> ';
	                   			var g = '<a class="btn btn-danger" href="javascript:void(0);" onclick=\"deleteVolunteer('+value+')\" >';
	                   			var h = '<i class="glyphicon glyphicon-remove icon-white"></i>删除</a> ';    			
	                        	return a+b+c+d+e+f+g+h;  
		                  	}
                  		}				        
				]
		    });
    	}
    	
		function showVolunteer(volunteerId)
   		{
   			$.ajax({
				url:"getVolunteer",			
				data:{"volunteerId":volunteerId},
				dataType:"json",
				success:function(data) {  
					if (data.code != "succ") {
						alert(data.data);
						$('#showVolunteerDialog').modal('hide');
						return;
					}
					
			        fillUserDetail(data.data);
			        var html="";
			        for (var i = 0; i < data.data.ageLevels.length; i++)
			        	html += data.data.ageLevels[i].ageName + " ";
			        $('#ageLevelIdDetail').text(html);
			        $("#volunteerIntroDetail").text(data.data.volunteerIntro);
			        $("#volunteerLocationDetail").text(data.data.volunteerLocation);
			    },
			    error: function(XMLHttpRequest, textStatus, errorThrown) {
					alert("网络错误: " + XMLHttpRequest.status + ", " + textStatus);
			    }
			});	
   		}
   		
   		$("#volunteerDialog").on("hidden.bs.model",function(e){$(this).removeData();}); 
   		
   		function selectVolunteer() {
   			selectUser(null, null, "../UserManage/getAllUser", null, onSelectComplete);
   		}
   		
   		function createVolunteer() {
   			action = "create";
   			$("#title").html("添加志愿者");
   			$('#volunteerForm')[0].reset();

   			$("#userId").val("");
   			
   			onUserShow(true);   
   			
   			fillAgeLevels();
   			fillAskQuestionTypes(2);			
   		}
   		
   		function modifyVolunteer(volunteerId) {
   			action = "modify";
   			$("#title").html("修改志愿者信息");
   			$('#volunteerForm')[0].reset();
   			onUserShow(false);
   			
			$.ajax({
				url:"getVolunteer",			
   				data:{"volunteerId":volunteerId},
				dataType:"json",
				success:function(data) {  
			        if (data.code != "succ") {
   						alert(data.data);
   						$('#volunteerDialog').modal('hide');
   						return;
   					}
  			        fillUser(data.data);
					$('#volunteerIntro').val(data.data.volunteerIntro);
					$('#volunteerLocation').val(data.data.volunteerLocation);
					fillAgeLevels(data.data.ageLevels);
					fillAskQuestionTypes(2, data.data.askQuestionTypes);
			    },
			    error: function(XMLHttpRequest, textStatus, errorThrown) {
					alert("网络错误: " + XMLHttpRequest.status + ", " + textStatus);
			    }
			});	   						
   		}
   		
   		function deleteVolunteer(volunteerId) {
   			if (!confirm("确认删除志愿者吗？"))
   				return;
   				
   			$.ajax({
				url:"deleteVolunteer",			
				data:{"volunteerId":volunteerId},
				dataType:"json",
				success:function(data) {  
					if (data.code != "succ") {
   						alert(data.data);
   						$('#volunteerDialog').modal('hide');
   						return;
   					}
      				$("#TableVolunteer").bootstrapTable('refresh');
  				},
			    error: function(XMLHttpRequest, textStatus, errorThrown) {
					alert("网络错误: " + XMLHttpRequest.status + ", " + textStatus);
			    }
			});
   		}
   		
   		function cancelVolunteer(volunteerId) {
   			if (!confirm("确认撤销志愿者吗？"))
   				return;
   				
   			$.ajax({
				url:"cancelVolunteer",			
				data:{"volunteerId":volunteerId},
				dataType:"json",
				success:function(data) {  
					if (data.code != "succ") {
   						alert(data.data);
   						$('#volunteerDialog').modal('hide');
   						return;
   					}
      				$("#TableVolunteer").bootstrapTable('refresh');
  				},
			    error: function(XMLHttpRequest, textStatus, errorThrown) {
					alert("网络错误: " + XMLHttpRequest.status + ", " + textStatus);
			    }
			});
   		}
   		
   		
   		function onOKButton() {
   			var url = "createVolunteer";
   			if (action == "modify")
   				url = "modifyVolunteer";
   			onUserSubmit();
   			var param = $('#volunteerForm').serialize();
   			param = ageLevelOnSubmit(param);
   			param = askQuestionTypeOnSubmit(param);
   			
   			$.ajax({
	        	url:url,
	        	type:"post",
	        	data:param,
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
	        		$('#volunteerDialog').modal('hide');
	        		$("#TableVolunteer").bootstrapTable('refresh');
	        	},
			    error: function(XMLHttpRequest, textStatus, errorThrown) {
					alert("网络错误: " + XMLHttpRequest.status + ", " + textStatus);
			    }
	        });
   		}   
   		
   		function onSelectComplete(user) {
   			$.ajax({
	        	url:"selectVolunteer",
	        	type:"post",
	        	data:{
	        	"userId": user.userId,
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
						alert("授权成功");
	        		$("#TableVolunteer").bootstrapTable('refresh');
	        	},
			    error: function(XMLHttpRequest, textStatus, errorThrown) {
					alert("网络错误: " + XMLHttpRequest.status + ", " + textStatus);
			    }
	        });
   		}		
   		
		function searchFunc() {
			$("#TableVolunteer").bootstrapTable('refresh');
		}

		function clearFunc() {
			$('#searchForm')[0].reset();
			$("#TableVolunteer").bootstrapTable('refresh');
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
                <a href="#">管理志愿者</a>
            </li>
        </ul>
    </div>
    <div class="row">
    <div class="box col-md-12">
    <div class="row">
        <div class="box col-md-12">
            <div class="box-inner">
                <div class="box-header well" data-original-title="">
                    <h2>志愿者管理</h2>

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
						<a class="btn btn-success" data-toggle="modal"  data-target="#volunteerDialog" onclick="createVolunteer()">
							<i class="glyphicon glyphicon-plus icon-white"></i>添加志愿者 </a>
						<a  class="btn btn-success btn-setting" onclick="selectVolunteer()">
							<i class="glyphicon glyphicon-share-alt icon-white"></i>志愿者授权</a>                   
					</div>
					<div class="alert alert-info" id="search">
						<form class="form-inline" role="form" id='searchForm'>
						<label class="control-label">姓名</label>
						<input type="text" class="form-control" name="[Like]userName" id="[Like]userName">
						<label class="control-label">昵称</label>
						<input type="text" class="form-control" name="[Like]userNickName" id="[Like]userNickName">
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
						<a class="btn btn-success" onclick="searchFunc()" ><i class="glyphicon glyphicon-search icon-white"></i>查询</a> 
						<a class="btn btn-danger" onclick="clearFunc()" ><i class="glyphicon glyphicon-remove icon-white"></i>清除</a> 
						</form>
					</div>
                    <table class="table table-striped table-bordered table-hover" id="TableVolunteer"></table>
                </div>
            </div>
        </div>

    </div><!--/span-->

    <!-- content ends -->
    </div><!--/#content.col-md-0-->
</div><!--/fluid-row-->

   

	 <!-- 查看志愿者对话框 -->
	 <div class="modal fade" id="showVolunteerDialog" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
         aria-hidden="true">

        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">×</button>
                    <h3>查看志愿者信息</h3>
                </div>
                <div class="form-group has-success has-feedback">
                	<%@include file="userDetail.jsp" %>
                	<table width="90%">
                		<tr>
                			<td width="20%"><label  class="control-label">地址</label></td>
                			<td width="30%"><label  id="volunteerLocationDetail" class="control-label">无</label></td>
                			<td width="20%"><label  class="control-label"></label></td>
                			<td width="30%"></td>                			
                		</tr>
                		<tr>
                			<td width="20%"><label  class="control-label">擅长年龄段</label></td>
                			<td width="30%"><label  id="ageLevelIdDetail" class="control-label">擅长年龄段</label></td>                			
                		</tr>
                		<tr>
                			<td width="20%"><label  class="control-label">介绍</label></td>
                			<td width="30%"><label  id="volunteerIntroDetail" class="control-label">无</label></td>                			
                		</tr>
                	</table>
                </div>
                <hr>
                <div class="modal-footer">
                     <a href="#" class="btn btn-primary" data-dismiss="modal">关闭</a>
                </div>
            </div>
        </div>
    </div>
    
    <!--新建和修改志愿者信息 -->
   <div class="modal fade" id="volunteerDialog" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">

        <div class="modal-dialog"  style="width:1000px">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">×</button>
                    <h3><span id='title'>添加志愿者</span></h3>
                </div>
                <div class="modal-body">
                    <form class="form-inline" role="form" id='volunteerForm'>
                    	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    	<%@include file="user.jsp" %>
                    	<div class="form-group has-success has-feedback" id="showTeacher">
                        <table  width="710px">
                        	<tr>
                        		<td width="80px"><label class="control-label" >年龄</label></td>
                        		<td><%@include file="agelevel.jsp"%></td>
                        	</tr>
                        	<tr>
                        		<td><label class="control-label">擅长</label></td>
                        		<td><%@include file="askquestiontype.jsp"%></td>
                        	</tr>
                        	<tr>
                        		<td><label class="control-label" for="volunteerLocation">地址</label></td>
								<td><textarea class="form-control" id="volunteerLocation" name="volunteerLocation" style="width:600px;margin-left:32px" rows="2"  list="2" placeholder="地址" required></textarea></td>						
							</tr>
							<tr>
                        		<td style="vertical-align:top"><label class="control-label" for="volunteerIntro">介绍</label></td>
								<td><textarea class="form-control" id="volunteerIntro" name="volunteerIntro" style="width:600px;margin-left:32px" rows="12"  list="2" placeholder="介绍" required></textarea></td>						
							</tr>
						</table>
						</div>
                    </form>                                                                
                </div>
                <!-- body -->
                <hr>
                <div class="modal-footer">
                     <a href="javascript:void(0)" class="btn btn-default " data-dismiss="modal">取消</a>
                     <a href="javascript:void(0)" class="btn btn-primary" id="volunteerOKButton" onclick="onOKButton()">确定</a>
                </div>
            </div>
        </div>
    </div> 
</div><!--/.fluid-container-->  
<%@include file="selectuser.jsp" %>
</body>
</html>
