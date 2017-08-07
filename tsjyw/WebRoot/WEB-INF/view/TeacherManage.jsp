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
    
    <title>管理教师</title>
    <%@include file="head.jsp" %>
    
    <script type="text/javascript"> 
    	var action = "create";
    	var schoolid = <c:out value="${schoolid}"/>;   
    	//添加附件后的保存
    	var userIds = new Array();
    	$(function(){
    	
    		$.ajaxSetup ({
         		cache: false //关闭AJAX缓存
     		});
    		
    		loadTeacher();
    		bindSelect("teacherStateId", "getTeacherState", null, "teacherStateId", "teacherStateName");
    		initSamlpleUpload("btnUpload", onUploadExcelComplete);
    		
    		$("#exportTeacher").on("click",function(){
    			window.location.href = "./downloadTeacher?schoolid=${schoolid}";
    		});
    		
    	});
    	
    	function queryParams(params) {  //配置参数
	        var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
	            pageSize: params.pageSize,   //页面大小
	            pageNumber: params.pageNumber,  //页码
				schoolId:<c:out value="${schoolid}"/>
	        };
	        <c:if test="${not empty _csrf}">
				temp.${_csrf.parameterName} = "${_csrf.token}";
			</c:if>
			extendTalbeParams(temp, "searchForm");
        	return temp;
    	}
    	
    	function loadTeacher()
    	{
    		$("#TableTeacher").bootstrapTable({
		    	url: "getTeacherBySchool", 
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
			    idField: "teacherId",  //标识哪个字段为id主键  
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
		                    width: 100,		 
		                    align: 'center',		 
		                    valign: 'middle',		 
		                    sortable: true
				        },
				        {
				        	field: 'userPhone', 
		                    title: '电话',		 
		                    width: 100,		 
		                    align: 'center',		 
		                    valign: 'middle'
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
		                    	return value == 0?"男" : "女";
		                    }	
				        },
				        {
				        	field: 'schoolMaster', 
		                    title: '职务',		 
		                    width: 100,		 
		                    align: 'center',		 
		                    valign: 'middle',
		                    formatter:function(value,row,index){
		                    	return value?"校长" : "";
		                    }		 
				        },
				        {
				        	field: 'teacherStateName', 
		                    title: '状态',		 
		                    width: 100,		 
		                    align: 'center',		 
		                    valign: 'middle'
				        },
				        {
		                    title: '操作',
		                    field: 'userId',
		                    formatter:function(value,row,index){
		                    	var a = '<a class="btn btn-info" data-toggle="modal" data-target="#showTeacherDialog" href="javascript:void(0);" onclick=\"showTeacher('+value+')\">';
   								var b = '<i class="glyphicon glyphicon-zoom-in icon-white"></i>详情信息</a> ';		                        
	                   			var c = '<a class="btn btn-info" data-toggle="modal" data-target="#teacherDialog" href="javascript:void(0);" onclick=\"modifyTeacher('+value+')\">'; 
	                   			var d = '<i class="glyphicon glyphicon-edit icon-white"></i>修改</a> ';
	                   			var e = '<a class="btn btn-success" href="javascript:void(0);" onclick=\"cancelTeacher('+value+')\" >';
	                   			var f = '<i class="glyphicon glyphicon-chevron-right icon-white"></i>调出</a> ';
	                   			var g = '<a class="btn btn-danger" href="javascript:void(0);" onclick=\"deleteTeacher('+value+')\" >';
	                   			var h = '<i class="glyphicon glyphicon-remove icon-white"></i>删除</a> ';    			
	                        	return a+b+c+d+e+f+g+h;  
		                  	}
                  		}				        
				],
				onLoadSuccess: function (data) {
					
				}
		    });
    	}
    	
		function showTeacher(teacherId)
   		{
   			$.ajax({
				url:"getTeacher",			
				data:{"teacherId":teacherId},
				dataType:"json",
				success:function(data) {  
					if (data.code != "succ") {
						alert(data.data);
						$('#showTeacherDialog').modal('hide');
						return;
					}
					
			        fillUserDetail(data.data);
			
			        $("#teacherStateNameDetail").text(data.data.teacherStateName);
			        $("#schoolNameDetail").text(data.data.schoolName);
			        $('#schoolMasterDetail').text(data.data.schoolMaster? "校长" : "");
			    },
			    error: function(XMLHttpRequest, textStatus, errorThrown) {
					alert("网络错误: " + XMLHttpRequest.status + ", " + textStatus);
			    }
			});	
   			
   		}
   		
   		$("#teacherDialog").on("hidden.bs.model",function(e){$(this).removeData();}); 
   		
   		function selectTeacher() {
   			selectUser(null, null, "../UserManage/getAllUser",null, onSelectComplete);
   			
   		}
   		
   		function createTeacher() {
   			action = "create";
   			$("#title").html("添加新教师");
   			$('#teacherForm')[0].reset();

   			$("#userId").val("");
   			$("#schoolId").val(""+schoolid);
   			
   			onUserShow(true);   			
   		}
   		
   		function modifyTeacher(teacherId) {
   			action = "modify";
   			$("#title").html("修改教师信息");
   			$('#teacherForm')[0].reset();
   			onUserShow(false);
   			
			$.ajax({
				url:"getTeacher",			
				data:{"teacherId":teacherId},
				dataType:"json",
				success:function(data) {  
			        if (data.code != "succ") {
   						alert(data.data);
   						$('#teacherDialog').modal('hide');
   						return;
   					}
  			        fillUser(data.data);
			        $('#schoolId').val(data.data.schoolId);
					$('#teacherStateId').val(data.data.teacherStateId);
					$('#schoolMaster').attr("checked",data.data.schoolMaster);
			    },
			    error: function(XMLHttpRequest, textStatus, errorThrown) {
					alert("网络错误: " + XMLHttpRequest.status + ", " + textStatus);
			    }
			});	   						
   		}
   		
   		function deleteTeacher(teacherId) {
   			if (!confirm("确认删除教师吗？"))
   				return;
   				
   			$.ajax({
				url:"deleteTeacher",			
				data:{"teacherId":teacherId},
				dataType:"json",
				success:function(data) {  
					if (data.code != "succ") {
   						alert(data.data);
   						$('#teacherDialog').modal('hide');
   						return;
   					}
      				$("#TableTeacher").bootstrapTable('refresh');
  				},
			    error: function(XMLHttpRequest, textStatus, errorThrown) {
					alert("网络错误: " + XMLHttpRequest.status + ", " + textStatus);
			    }
			});
   		}
   		
   		
   		function cancelTeacher(teacherId) {
   			if (!confirm("确认调出教师吗？"))
   				return;
   				
   			$.ajax({
				url:"cancelTeacher",			
				data:{"teacherId":teacherId},
				dataType:"json",
				success:function(data) {  
					if (data.code != "succ") {
   						alert(data.data);
   						$('#teacherDialog').modal('hide');
   						return;
   					}
      				$("#TableTeacher").bootstrapTable('refresh');
  				},
			    error: function(XMLHttpRequest, textStatus, errorThrown) {
					alert("网络错误: " + XMLHttpRequest.status + ", " + textStatus);
			    }
			});
   		}
   		
   		function onOKButton() {
   			var url = "createTeacher";
   			if (action == "modify")
   				url = "modifyTeacher";
   			
   			onUserSubmit();	
   			$.ajax({
	        	url:url,
	        	type:"post",
	        	data:$('#teacherForm').serialize(),
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
	        		$('#teacherDialog').modal('hide');
	        		$("#TableTeacher").bootstrapTable('refresh');
	        	},
			    error: function(XMLHttpRequest, textStatus, errorThrown) {
					alert("网络错误: " + XMLHttpRequest.status + ", " + textStatus);
			    }
	        });
   		}   
   		
   		function onSelectComplete(user) {
   			$.ajax({
	        	url:"selectTeacher",
	        	type:"post",
	        	data:{
	        	"userId": user.userId, 
	        	"schoolId":schoolid,
	        	"${_csrf.parameterName}":"${_csrf.token}"},
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
	        		$("#TableTeacher").bootstrapTable('refresh');
	        	},
			    error: function(XMLHttpRequest, textStatus, errorThrown) {
					alert("网络错误: " + XMLHttpRequest.status + ", " + textStatus);
			    }
	        });
   		}
   		
   		
   		function onUploadExcelComplete(data)
   		{
   			if(data == null || data == undefined){
   				alert("上传错误");
   				return;
   			}
   			var cur = {};
   			cur.attachId = null;
   			cur.attachName = data.data.attachName;
   			cur.attachSize = data.data.attachSize;
   			cur.attachUrl = data.data.attachUrl;
   			importExcel(cur);
   		}
   		function importExcel(cur)
   		{
   			$.ajax({
   				url:"importExcel",
   				type:"post",
   				data:{"attach":JSON.stringify(cur),"${_csrf.parameterName}":"${_csrf.token}","schoolId":<c:out value="${schoolid}"/>},
   				dataType:"json",
   				success:function(data){
					if(data.code == "succ")
					{
						userIds.length = 0;
						//{"code":"succ","total":10,"rows":null,"data":[40,41,42,43,44,45,46,47,48,49]}
						$("#downloadexcel").modal("show");
						$("#importExcelNum").html(data.total);
						for(var i = 0;i<data.data.length;i++)
						{
							userIds.push(data.data[i]);
						}
						$("#TableTeacher").bootstrapTable('refresh');
						downloadExcel();
					}
					else if(data.code == "fail")
					{
						alert("导入失败，错误信息:"+data.data);					
					}
					else
					{
						alert("导入失败，未知错误");
					}
   				},
   				error:function(data){
   					alert("网络错误");
   				}
   			});
   		}
   		
   		function downloadExcel()
   		{
   			document.getElementById("downLoadTeacherExcel").href="downLoadTeacherExcel?userIds="+JSON.stringify(userIds); 
   			//alert(JSON.stringify(userIds));
   			/*
   			$.ajax({
   				url:"downLoadTeacherExcel",
   				data:{"userIds":JSON.stringify(userIds),"${_csrf.parameterName}":"${_csrf.token}"},
   				type:"post",
   				success:function(data){
   					
   				}
   			});*/
   		}
   		function modelHide()
   		{
   			$("#downloadexcel").modal("show");
   		}

		function searchFunc() {
			$("#TableTeacher").bootstrapTable('refresh');
		}

		function clearFunc() {
			$('#searchForm')[0].reset();
			$("#TableTeacher").bootstrapTable('refresh');
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
                <a href="#">学校管理</a>
            </li>
            <li>
                <a href="#">管理教师</a>
            </li>
        </ul>
    </div>
    <div class="row">
    <div class="box col-md-12">
    <div class="row">
        <div class="box col-md-12">
            <div class="box-inner">
                <div class="box-header well" data-original-title="">
                    <h2>教师管理</h2>

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
						<a class="btn btn-success" data-toggle="modal"  data-target="#teacherDialog" onclick="createTeacher()">
							<i class="glyphicon glyphicon-plus icon-white"></i>添加新教师 </a>
						<a class="btn btn-success btn-setting" onclick="selectTeacher()" >
							<i class=" glyphicon glyphicon-share-alt icon-white"></i>调入教师
						</a>  						
						<input type='button' id="btnUpload" class="btn btn-success" value='导入教师'/>		
						<a class="btn btn-success  btn-setting" id="exportTeacher" href="javascript:void(0);">导出教师</a>					  
					</div>
					<div class="alert alert-info" id="search">
						<form class="form-inline" role="form" id='searchForm'>
						<label class="control-label">姓名 </label>
						<input type="text" class="form-control" name="[Like]userName" id="[Like]userName">
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
						<a class="btn btn-success" onclick="searchFunc()" ><i class="glyphicon glyphicon-search icon-white"></i>查询</a> 
						<a class="btn btn-danger" onclick="clearFunc()" ><i class="glyphicon glyphicon-remove icon-white"></i>清除</a>  
						</form>
					</div>
                    <table class="table table-striped table-bordered table-hover" id="TableTeacher"></table>
                </div>
            </div>
        </div>

    </div><!--/span-->

    <!-- content ends -->
    </div><!--/#content.col-md-0-->
</div><!--/fluid-row-->
    <hr>

	 <!-- 查看教师对话框 -->
	 <div class="modal fade" id="showTeacherDialog" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
         aria-hidden="true">

        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">×</button>
                    <h3>查看教师信息</h3>
                </div>
                <div class="modal-body">
                	<%@include file="userDetail.jsp" %>
                	<table width="90%">
                		<tr>
                			<td width="20%"><label  class="control-label">学校</label></td>
                			<td width="30%"><label  id="schoolNameDetail" class="control-label">学校</label></td>
                			<td width="20%"><label  class="control-label">状态</label></td>
                			<td width="30%"><label  id="teacherStateNameDetail" class="control-label">状态</label></td>
                		</tr>
                		<tr>
                			<td width="20%"><label  class="control-label">职务</label></td>
                			<td width="30%"><label  id="schoolMasterDetail" class="control-label">无</label></td>
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
    
    <!--新建和修改教师信息 -->
   <div class="modal fade" id="teacherDialog" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">

        <div class="modal-dialog"  style="width:800px">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">×</button>
                    <h3><span id='title'>修改教师信息</span></h3>
                </div>
                <div class="modal-body">
                    <form class="form-inline" role="form" id='teacherForm'>
                    	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    	<input type="hidden" id="schoolId" name="schoolId">
                    	<%@include file="user.jsp" %>
                    	<div class="form-group has-success has-feedback" id="LookTeacher2">
                        <table  width="680px">
                        	<tr>
                        		<td width="20%"><label class="control-label" for="teacherStateId">状态</label></td>
								<td width="30%"><select id="teacherStateId" name="teacherStateId" class="form-control"></select></td>
								<td width="20%"><label class="control-label" for="schoolMaster"></label></td>
								<td><input type="checkbox" id="schoolMaster" name="schoolMaster" value="true" class="form-control" />校长</td>		
							</tr>
						</table>
						</div>
                    </form>                                                                
                </div>
                <!-- body -->
                <hr>
                <div class="modal-footer">
                     <a href="javascript:void(0)" class="btn btn-default " data-dismiss="modal">取消</a>
                     <a href="javascript:void(0)" class="btn btn-primary" id="teacherOKButton" onclick="onOKButton()">确定</a>
                </div>
            </div>
        </div>
    </div> 

    <!--正在导入 -->
   <div class="modal fade" id="example" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog"  style="width:300px">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">×</button>
                    <h3><span id='title'>正在导入。。。</span></h3>
                </div>
                <div class="modal-body">
	                <div class="progress">
	                    <div class="progress-bar progress-bar-success progress-bar-striped" role="progressbar"
	                         aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width: 80%">
	                        <span class="sr-only">80% Complete (success)</span>
	                    </div>
	                </div>
                </div>
            </div>
        </div>
    </div>
    
    <!--提示成功 -->
   <div class="modal fade" id="downloadexcel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog"  style="width:300px">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">×</button>
                    <h3><span id='title'>导入成功</span></h3>
                </div>
                <div class="modal-body">
	                	成功导入<span id="importExcelNum"></span>人，建议下载excel查看导入的详细信息。
                </div>
				<div class="modal-footer">
                     <a href="javascript:void(0)" class="btn btn-default " data-dismiss="modal">取消</a>
                     <a href="" class="btn btn-primary" id="downLoadTeacherExcel" onclick="modelHide()">下载</a>
                </div>	
            </div>
        </div>
    </div>
    
    
    
</div><!--/.fluid-container-->
<%@include file="selectuser.jsp" %>
</body>
</html>