<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>

<html lang="en">
<head>
   
    <title>管理学生</title>
    <%@include file="head.jsp" %>
   
     
     <script type="text/javascript">
    	var action = "create"; 
    	var schoolClassId = <c:out value="${schoolClassid}"/>;  
    	var schoolid = <c:out value="${schoolId}"/>; 
		var gradeId = <c:out value="${gradeid}"/>;
    	$(function(){
    	
    		$.ajaxSetup ({
         		cache: false //关闭AJAX缓存
     		});
    		
    		loadStudent();
    		bindSelect("studentStateId", "getStudentState", null, "studentStateId", "studentStateName");
    		
    		//年级的下拉列表	
    		var p = {schoolId: schoolid, pageNumber: 1, pageSize: 100 };
    		bindSelect("gradeId", "../GradeManage/getGradeBySchool",p, "gradeId", "gradeName", "", "", "全部年级"); 
    		
    		 //班级的下拉列表 
    		var p = {schoolId: schoolid, pageNumber: 1, pageSize: 100 };	
    		bindSelect("newSchoolClassId", "../SchoolClassManage/getSchoolClassBySchool", p, "schoolClassId", "schoolClassName", "", "", "全部年级");
    		
    		initSamlpleExcelUpload("btnUpLoadStudentExcel",1,schoolClassId,onUploadExcelComplete); 
    		
    		
    		$("#exportStudent").on("click",function(){
    			window.location.href = "./downloadStudent?classid=${schoolClassid}";
    		});
    		
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
			extendTalbeParams(temp, "searchForm");
        	return temp;
    	}

    	function loadStudent()
    	{
    		$("#TableStudent").bootstrapTable({
		    	url: "getStudentBySchoolClass", 
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
			    idField: "studentId",  //标识哪个字段为id主键  
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
				columns: [{
				        	field: 'userName', 
		                    title: '姓名',		 
		                    width: 150,		 
		                    align: 'center',		 
		                    valign: 'middle',		 
		                    sortable: true
				        },
				        {
				        	field: 'userPhone', 
		                    title: '电话',		 
		                    width: 150,		 
		                    align: 'center',		 
		                    valign: 'middle',
		                    sortable: true,
				        },
				        {
				        	field: 'userCode', 
		                    title: '用户编码',		 
		                    width: 150,		 
		                    align: 'center',		 
		                    valign: 'middle'
				        },
				        {
				        	field: 'userGender', 
		                    title: '性别',		 
		                    width: 60,	 
		                    align: 'center',		 
		                    valign: 'middle',
		                    formatter:function(value,row,index){
		                    	return value == 0?"男" : "女";
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
				        	field: 'studentStateName', 
		                    title: '状态',		 
		                    width: 100,		 
		                    align: 'center',		 
		                    valign: 'middle'
				        },
				        {
		                    title: '操作',
		                    field: 'userId',
		                    formatter:function(value,row,index){
		                    	var a = '<a class="btn btn-info" data-toggle="modal" data-target="#showStudentDialog" href="javascript:void(0);" onclick=\"showStudent('+value+')\">';
   								var b = '<i class="glyphicon glyphicon-zoom-in icon-white"></i>详情信息</a> ';		                        
	                   			var c = '<a class="btn btn-info" data-toggle="modal" data-target="#studentDialog" href="javascript:void(0);" onclick=\"modifyStudent('+value+')\">'; 
	                   			var d = '<i class="glyphicon glyphicon-edit icon-white"></i>修改</a> ';
	                   			var e = '<a class="btn btn-success" href="javascript:void(0);" onclick=\"cancelStudent('+value+')\" >';
	                   			var f = '<i class="glyphicon glyphicon-chevron-right icon-white"></i>转出</a> ';
	                   			var g = '<a class="btn btn-danger" href="javascript:void(0);" onclick=\"deleteStudent('+value+')\" >';
	                   			var h = '<i class="glyphicon glyphicon-remove icon-white"></i>删除</a> '; 			
	                        	return a+b+c+d+e+f+g+h;  
		                  	}
                  		}				        
				],
				onLoadSuccess: function (data) {
					
				}
		    });
    	}
    	
		function showStudent(studentId)
   		{
   			$.ajax({
				url:"getStudent",			
				data:{"studentId":studentId},
				dataType:"json",
				success:function(data) {  
					if (data.code != "succ") {
						alert(data.data);
						$('#showStudentDialog').modal('hide');
						return;
					}
					
			        fillUserDetail(data.data);
			
			        $("#studentStateNameDetail").text(data.data.studentStateName);
			        $("#schoolClassNameDetail").text(data.data.schoolClassName);
			        $('#studentCodeDetail').val(data.data.studentCode);
			    },
			    error: function(XMLHttpRequest, textStatus, errorThrown) {
					alert("网络错误: " + XMLHttpRequest.status + ", " + textStatus);
			    }
			});	
   			
   		}
   		
   		$("#studentDialog").on("hidden.bs.model",function(e){$(this).removeData();}); 
   		
   		
   		function selectStudent() {
   			selectUser(null, null, "../UserManage/getAllUser",null, onSelectComplete);
   		}
   		
   		function createStudent() {
   			action = "create";
   			$("#title").html("添加新学生");
   			$('#studentForm')[0].reset();
   			$("#userId").val("");
   			$("#schoolClassId").val(""+schoolClassId);
   			
   			onUserShow(true);   			
   		}
   		
   		
   		function modifyStudent(studentId) {
   			action = "modify";
   			$("#title").html("修改学生信息");
   			$('#studentForm')[0].reset();
   			onUserShow(false);
   			
			$.ajax({
				url:"getStudent",			
				data:{"studentId":studentId},
				dataType:"json",
				success:function(data) {  
			        if (data.code != "succ") {
   						alert(data.data);
   						$('#studentDialog').modal('hide');
   						return;
   					}
  			        fillUser(data.data);
			        $('#schoolClassId').val(data.data.schoolClassId);
					$('#studentStateId').val(data.data.studentStateId);
					$('#studentCode').val(data.data.studentCode);
			    },
			    error: function(XMLHttpRequest, textStatus, errorThrown) {
					alert("网络错误: " + XMLHttpRequest.status + ", " + textStatus);
			    }
			});	   						
   		}
   		
   		function deleteStudent(studentId) {
   			if (!confirm("确认删除学生吗？"))
   				return;
   				
   			$.ajax({
				url:"deleteStudent",			
				data:{"studentId":studentId},
				dataType:"json",
				success:function(data) {  
					if (data.code != "succ") {
   						alert(data.data);
   						$('#studentDialog').modal('hide');
   						return;
   					}
      				$("#TableStudent").bootstrapTable('refresh');
  				},
			    error: function(XMLHttpRequest, textStatus, errorThrown) {
					alert("网络错误: " + XMLHttpRequest.status + ", " + textStatus);
			    }
			});
   		}
   		
   		function cancelStudent(studentId) {
   			if (!confirm("确认转出学生吗？"))
   				return;
   				
   			$.ajax({
				url:"cancelStudent",			
				data:{"studentId":studentId},
				dataType:"json",
				success:function(data) {  
					if (data.code != "succ") {
   						alert(data.data);
   						$('#studentDialog').modal('hide');
   						return;
   					}
      				$("#TableStudent").bootstrapTable('refresh');
  				},
			    error: function(XMLHttpRequest, textStatus, errorThrown) {
					alert("网络错误: " + XMLHttpRequest.status + ", " + textStatus);
			    }
			});
   		}
   		
   	
   		
   		//毕业离校
   		function graduatedStudent(){
   			if(!confirm("确认让全班学生毕业吗？"))
   				return;  
   			$.ajax({
				url:"graduatedStudent",			
				data:{"schoolClassId":schoolClassId},
				dataType:"json",
				success:function(data) {  
					if (data.code != "succ") {
   						alert(data.data);
   						$('#studentDialog').modal('hide');
   						return;
   					}
      				$("#TableStudent").bootstrapTable('refresh');
  				},
			    error: function(XMLHttpRequest, textStatus, errorThrown) {
					alert("网络错误: " + XMLHttpRequest.status + ", " + textStatus);
			    }
			});
   		}
   		
   		function onOKButton() {
   			var url = "createStudent";
   			if (action == "modify")
   				url = "modifyStudent";
   			onUserSubmit();
   			$.ajax({
	        	url:url,
	        	type:"post",
	        	data:$('#studentForm').serialize(),
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
	        		$('#studentDialog').modal('hide');
	        		$("#TableStudent").bootstrapTable('refresh');
	        	},
			    error: function(XMLHttpRequest, textStatus, errorThrown) {
					alert("网络错误: " + XMLHttpRequest.status + ", " + textStatus);
			    }
	        });
   		}   
   		
   		
   		function onSelectComplete(user) {
   			$.ajax({
	        	url:"selectStudent",
	        	type:"post",
	        	data:{
	        		"userId": user.userId, 
	        		"schoolClassId":schoolClassId,
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
	        		$("#TableStudent").bootstrapTable('refresh');
	        	},
			    error: function(XMLHttpRequest, textStatus, errorThrown) {
					alert("网络错误: " + XMLHttpRequest.status + ", " + textStatus);
			    }
	        });
   		}	
   		
		//学生升班
   		function onGoUpComplete()
   		{
   			var newId = $("#newSchoolClassId").val();
   			$.ajax({
				url:"goUpStudent",	
				type:"post",		
				data:{"schoolClassId":schoolClassId, 
				"newSchoolClassId":newId,
				"${_csrf.parameterName}":"${_csrf.token}"
				},
				dataType:"json",
				success:function(data) {  
			        if (data.code != "succ") {
   						alert(data.data);
   						return;
   					}
   					alert("升班成功");
   					$('#goUpStudentDialog').modal('hide');
   					$("#TableStudent").bootstrapTable('refresh');
   					
			    },
			    error: function(XMLHttpRequest, textStatus, errorThrown) {
					alert("网络错误: " + XMLHttpRequest.status + ", " + textStatus);
			    }
			});	   			
   			
   		} 

		//年级选择变化时
		function getClassByGrade(value) {
			if (value == "") {
				var p = {schoolId: schoolid, pageNumber: 1, pageSize: 100 };	
    			bindSelect("newSchoolClassId", "../SchoolClassManage/getSchoolClassBySchool", p, "schoolClassId", "schoolClassName");
			}
			else {
	    		var p = {gradeId: value, pageNumber: 1, pageSize: 100 };	
	    		bindSelect("newSchoolClassId", "../SchoolClassManage/getSchoolClassByGrade", p, "schoolClassId", "schoolClassName"); 
    		}		
		}
		
		function onUploadExcelComplete(data)
   		{
   			if(data == null || data == undefined){
   				alert("上传错误");
   				return;
   			}
   			if(data.code == "fail")
   				alert("上传失败");
   			if(data.code == "succ")
   			{
   				$("#downloadexcel").modal("show");
   				$("#importExcelNum").html(data.total);
   				var userIds = new Array();
   				for(var i=0;i<data.data.length;i++)
   				{
   					userIds.push(data.data[i]);
   				}
   				$("#TableStudent").bootstrapTable('refresh');
   				downStudentExcel(userIds);
   			}
   		}
   		
   		function onDownloadExcelComplete(data)
   		{
   			if(data == null || data == undefined){
   				alert("下载错误");
   				return;
   			}
   			if(data.code == "fail")
   				alert("下载失败");
   			if(data.code == "succ")
   			{
   				$("#downloadexcel").modal("show");
   				//$("#importExcelNum").html(data.total);
   				var userIds = new Array();
   				for(var i=0;i<data.data.length;i++)
   				{
   					userIds.push(data.data[i]);
   				}
   				$("#TableStudent").bootstrapTable('refresh');
   				downStudentExcel(userIds);
   			}
   		}
   		
   		function downStudentExcel(userIds)
   		{
   			document.getElementById("downLoadStudentExcel").href="downLoadStudentExcel?userIds="+JSON.stringify(userIds); 
   			document.getElementById("downLoadStudentExcel").href="downLoadStudentExcel?classIds="+JAON.stringfy(classIds);
   		}

		function searchFunc() {
			$("#TableStudent").bootstrapTable('refresh');
		}

		function clearFunc() {
			$('#searchForm')[0].reset();
			$("#TableStudent").bootstrapTable('refresh');
		}
		
		

		
    </script>   

</head>

<body>
    <%@include file="top.jsp"%>
    <!--左侧开始-->
    <div class="ch-container">
        <div class="row">
            <%@include file="left.jsp" %>
        <div id="content" class="col-lg-10 col-sm-10">
            <!-- content starts -->
	        <div>
	          <ul class="breadcrumb">
	             <li>
	                <a href="SchoolClassManage">班级管理</a>
	            </li>
	            <li>
	                <a href="StudentManage">管理学生</a>
	            </li>
	         </ul>
	      </div>
 
    <div class="row">
    <div class="box col-md-12">
    <div class="row">
        <div class="box col-md-12">
            <div class="box-inner">
                <div class="box-header well" data-original-title="">
                    <h2>学生管理</h2>

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
						<input type="button" id="btnUpLoadStudentExcel" class="btn btn-success" value="批量注册"/> 
						<a class="btn btn-success" data-toggle="modal" data-target="#studentDialog" onclick="createStudent()">
							<i class="glyphicon glyphicon-plus icon-white"></i> 添加学生
						</a>
						<a class="btn btn-success btn-setting" onclick="selectStudent()" >
							<i class=" glyphicon glyphicon-arrow-left icon-white"></i>转入学生
						</a>
						<a class="btn btn-success btn-setting" data-toggle="modal" data-target="#goUpStudentDialog"   >
							<i class="glyphicon glyphicon-share-alt icon-white"></i>升班
						</a>
						<a class="btn btn-success  btn-setting" onclick="graduatedStudent()">
							<i class="glyphicon glyphicon-arrow-right icon-white"></i>毕业离校
						</a>
						<a class="btn btn-success  btn-setting" href="javascript:void(0);" id="exportStudent">导出学生</a>
						 
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
					<table class="table table-striped table-bordered table-hover" id="TableStudent"></table>
                </div>
            </div>
        </div> 
    </div><!--/span-->

    <!-- content ends -->
    </div><!--/#content.col-md-0-->
</div>

	
      
       
       <!-- 查看学生对话框 -->
   <div class="modal fade" id="showStudentDialog" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">

        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">×</button>
                    <h3>查看学生信息</h3>
                </div>
                <div class="modal-body">
                	<%@include file="userDetail.jsp" %>
                	<table width="90%">
                		<tr>
                			<td width="20%"><label  class="control-label">年级</label></td>
                			<td width="30%"><label  id="schoolClassNameDetail" class="control-label">ni</label></td>
                			<td width="20%"><label  class="control-label">班级</label></td>
                			<td width="30%"><label  id="schoolClassNameDetail" class="control-label">班级</label></td>
                		</tr>
                		<tr>
                			<td width="20%"><label  class="control-label">学籍号</label></td>
                			<td width="30%"><label id="studentCodeDetail"  class="control-label"/></label></td>
                			<td width="20%"><label  class="control-label">状态</label></td>
                			<td width="30%"><label  id="studentStateNameDetail" class="control-label"></label></td>
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
<!-- 添加学生对话框 -->
 <div class="modal fade" id="studentDialog" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">

        <div class="modal-dialog"  style="width:800px">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">×</button>
                    <h3><span id='title'>修改学生信息</span></h3>
                </div>
                <div class="modal-body">
                    <form class="form-inline" role="form" id='studentForm'>
                    	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    	<input type="hidden" id="schoolClassId" name="schoolClassId">
                    	<%@include file="user.jsp" %>
                    	<div class="form-group has-success has-feedback" id="LookStudent2">
                        <table  width="680px">
                        	<tr>
                        		<td width="20%"><label class="control-label" for="studentStateId">状态</label></td>
								<td width="30%"><select id="studentStateId" name="studentStateId" class="form-control"></select></td>
								<td width="20%"><label class="control-label" for="studentCode">学籍号</label></td>
								<td width="30%"><input style="margin-left:15px;"  type="text" class="form-control" id="studentCode" name="studentCode"></td>
									
							</tr>
						</table>
						</div>
                    </form>                                                                
                </div>
                <!-- body -->
                <hr>
                <div class="modal-footer">
                     <a href="javascript:void(0)" class="btn btn-default " data-dismiss="modal">取消</a>
                     <a href="javascript:void(0)" class="btn btn-primary" id="studentOKButton" onclick="onOKButton()">确定</a>
                </div>
            </div>
        </div> 


		</div>
   </div>
</div>
  <!-- 学生转班对话框 -->
   <div class="modal fade" id="goUpStudentDialog" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">

        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">×</button>
                    <h3>选择转到的班级</h3>
                </div>
                <div class="modal-body">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                	<table width="80%">
                		<tr>
							<td width="30%"><label class="control-label" for="gradeId">所在年级</label></td>
							<td width="50%"><select class="form-control" id="gradeId" name="gradeId" onchange="getClassByGrade(this.value)"></select></td>
							</tr>
							<tr>
							<td width="30%"><label class="control-label" for="newSchoolClassId">所在班级</label></td>
							<td width="50%"><select class="form-control" id="newSchoolClassId" name="newSchoolClassId"></select></td>
							</tr>
					
                	</table>
                </div>
              
                <div class="modal-footer">
                     <a href="javascript:void(0)" class="btn btn-default " data-dismiss="modal">取消</a>
                      <a href="javascript:void(0)" class="btn btn-primary" id="" onclick="onGoUpComplete()">确定</a>
                </div>
            </div>
        </div>
    </div>  
    
    <!--正在导入 -->
    <div class="modal fade" id="waitWindow" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
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
	                	成功导入！<span id="importExcelNum"></span>人，建议下载excel查看全部导入学生账户的详细信息。
                </div>
				<div class="modal-footer">
                     <a href="javascript:void(0)" class="btn btn-default " data-dismiss="modal">关闭</a>
                     <a href="" class="btn btn-primary" id="downLoadStudentExcel">下载</a>
                </div>	
            </div>
        </div>
    </div>
</div><!--/.fluid-container-->
<%@include file="selectuser.jsp" %>
  </body>
</html>
