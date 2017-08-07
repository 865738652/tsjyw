<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
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
    
    <title>学校管理</title>
    <%@include file="head.jsp" %>
 
	<script type="text/javascript">
		var action = "create";
		var byCounty=<c:out value="${bycounty}"/>;
		var countyid=<c:out value="${countyid}"/>;
		$(function(){
			
			$.ajaxSetup({
				cache:false  //关闭AJAX缓存
			});
			loadSchool();
			bindSelect("schoolTypeId", "getSchoolType", null, "schoolTypeId", "schoolTypeName");
			initSamlpleUpload("btnUploadFile", onUploadComplete);
		});
		
		function queryParams(params){//配置参数
			var temp;
			if(byCounty)
				temp={  //这里的键的名字和控制器的变量名必须一致
					
					pageSize:params.pageSize,  //页面大小
					pageNumber:params.pageNumber,//页码
					countyId:countyid
				};
			else
				temp={  //这里的键的名字和控制器的变量名必须一致
					pageSize:params.pageSize,  //页面大小
					pageNumber:params.pageNumber//页码
					};
			<c:if test="${not empty _csrf}">
				temp.${_csrf.parameterName} = "${_csrf.token}";
			</c:if>
			extendTalbeParams(temp, "searchForm");
			return temp;
		}
		
		function loadSchool()
		{
			var url = "getAllSchool";
			if (byCounty)
   				url = "getSchoolByCounty";	
			$("#TableSchool").bootstrapTable({
				url:url,
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
			    idField: "SchoolId",  //标识哪个字段为id主键  
			    pageSize: 10,  //每一页 的 数量
				pageNumber:1,      //当前页码
				showRefresh:false,  //显示刷新按钮
				singleSelect:true,  //复选框只能选择一条记录
				clickToSelect:true,  //点击行即可选中单选/复选框
				columns:[
				 	{
				 		field:'schoolNumber',
				 		title:'学校代码',
				 		width:'100',
				 		align:'center',
				 		valign:'maddle',
				 		sortable:true,
				 	},
				 	{
				 		field:'schoolName',
				 		title:'学校名称',
				 		width:150,
				 		align:'center',
				 		valigh:'middle'
				 	},
				 	{
				 		field:'schoolContactInformation',
				 		title:'联系方式',
				 		width:150,
				 		align:'center',
				 		valigh:'middle',
				 	},
				 	{		 	
				 		title:'操作',
				 		field:'schoolId',
				 		formatter:function(value,row,index){
				 			var html = '<a class="btn btn-info" data-toggle="modal" data-target="#showSchoolDialog" href="javascript:void(0);" onclick=\"showSchool('+value+')\">';
   							html += '<i class="glyphicon glyphicon-zoom-in icon-white"></i>查看</a> ';		                        
	                   		html += '<a class="btn btn-info" data-toggle="modal" data-target="#schoolDialog" href="javascript:void(0);" onclick=\"modifySchool('+value+')\">'; 
	                   		html += '<i class="glyphicon glyphicon-edit icon-white"></i>修改</a> ';	                 		               		
	                   		<sec:authorize access="hasAnyAuthority('SuperAdmin', 'CountyAdmin')">
	                   		html += '<a class="btn btn-danger" href="javascript:void(0);" onclick=\"deleteSchool('+value+')\" >';
	                   		html += '<i class="glyphicon glyphicon-remove icon-white"></i>删除</a> ';
	                   		</sec:authorize> 
	                   		html += '<a class="btn btn-success" href="javascript:void(0);" onclick=\"ExcelImportAllStudent('+value+')\" >';
	                   		html += '<i class="glyphicon glyphicon-edit icon-white"></i>导入</a> ';
	                   		html += '<a class="btn btn-success" href="javascript:void(0);" onclick=\"ExcelExportAllStudent('+value+')\" >';
	                   		html += '<i class="glyphicon glyphicon-edit icon-white"></i>导出学生</a> ';
	                   		html += '<a class="btn btn-success" href=\"../TeacherManage/' + value +'\" >';
	                   		html += '<i class="glyphicon glyphicon-edit icon-white"></i>教师</a> ';  
	                   		html += '<a class="btn btn-success" href=\"../GradeManage/' + value +'\" >';
	                   		html += '<i class="glyphicon glyphicon-edit icon-white"></i>年级</a> '; 
	                   		html += '<a class="btn btn-success" href=\"../SchoolClassManage/' + value +'\" >';
	                   		html += '<i class="glyphicon glyphicon-edit icon-white"></i>班级</a> '; 
	                   		html += '<a class="btn btn-success" href=\"../CourseManage/' + value +'\" >';
	                   		html += '<i class="glyphicon glyphicon-edit icon-white"></i>课程</a> '; 
	                   		html += '<a class="btn btn-danger" href="../SchoolPeople/' + value + '">';
	                   		html += '<i class="glyphicon glyphicon-edit icon-white"></i>管理员</a> '; 	
	                        return html; 
				 		}
				 	}
				 ],
				 onLoadSuccess: function (data) {
				 }
			});
		}
	
		function showSchool(schoolId)
		{
			$('#showSchoolDialog').on('show.bs.modal', function () {
   				$.ajax({
   					url:"getSchool",			
   					data:{"schoolId":schoolId},
   					dataType:"json",
   					success:function(data) {  
   						if (data.code != "succ") {
   							alert(data.data);
   							$('#showSchoolDialog').modal('hide');
   							return;
   						}
   						
				        $("#schoolNumberDetail").text(data.data.schoolNumber);
						$("#schoolNameDetail").text(data.data.schoolName);
						$("#schoolAddressDetail").text(data.data.schoolAddress);
						$("#schoolIntroductionDetail").text(data.data.schoolIntroduction);
						$("#schoolContactInformationDetail").text(data.data.schoolContactInformation);		
				        $("#schoolTypeNameDetail").text(data.data.schoolTypeName);
				        $("#countyNameDetail").text(data.data.countyName);
				    }
   				});	
   			});
		}
		
		$("#schoolDialog").on("hidden.bs.model",function(e){$(this).removeData();}); 
		
		function createSchool() {
			action = "create";
   			$("#title").html("添加新学校");
   			$("#schoolForm")[0].reset();
   			
   			$("#schoolId").val("");
   			$("#countyId").val(""+countyid);
   		}
		
		function modifySchool(schoolId) {
			action = "modify";
   			$("#title").html("修改学校信息");
   			$("#schoolForm")[0].reset();
   			
			$.ajax({
				url:"getSchool",			
				data:{"schoolId":schoolId},
				dataType:"json",
				success:function(data) {  
			        if (data.code != "succ") {
   						alert(data.data);
   						$('#schoolDialog').modal('hide');
   						return;
   					}
  			        $('#schoolId').val(data.data.schoolId);
					$('#schoolNumber').val(data.data.schoolNumber);
					$('#schoolName').val(data.data.schoolName);
					$('#schoolAddress').val(data.data.schoolAddress);
					$('#schoolIntroduction').val(data.data.schoolIntroduction);
					$('#schoolContactInformation').val(data.data.schoolContactInformation);
					$('#countyName').text(data.data.countyName);
			        $('#countyId').val(data.data.countyId);
					$('#schoolTypeId').val(data.data.schoolTypeId);
					if (data.data.schoolLogo != null) {
						$('#schoolLogo').val(data.data.schoolLogo);
						$('#logo').attr("src", data.data.schoolLogo);
					}
			      
			    },
			    error: function(XMLHttpRequest, textStatus, errorThrown) {
					alert("网络错误: " + XMLHttpRequest.status + ", " + textStatus);
			    }
			    
			});	 						
   		}
		
		function deleteSchool(schoolId) {
			if (!confirm("确认删除学校吗？"))
   				return;
   			$.ajax({
				url:"deleteSchool",			
				data:{"schoolId":schoolId},
				dataType:"json",
				success:function(data) {  
					if (data.code != "succ") {
   						alert(data.data);
   					}
      				$("#TableSchool").bootstrapTable('refresh');
  				},
			    error: function(XMLHttpRequest, textStatus, errorThrown) {
					alert("网络错误: " + XMLHttpRequest.status + ", " + textStatus);
			    }
			});
   		}
		
		function onOKButton() {
   			var url = "createSchool";
   			if (action == "modify")
   				url = "modifySchool";
   				
   			$.ajax({
	        	url:url,
	        	type:"post",
	        	data:$('#schoolForm').serialize(),
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
	        		$('#schoolDialog').modal('hide');
	        		$("#TableSchool").bootstrapTable('refresh');
	        	},
			    error: function(XMLHttpRequest, textStatus, errorThrown) {
					alert("网络错误: " + XMLHttpRequest.status + ", " + textStatus);
			    }
	        });
   		} 
   		
   		function onSelect() {
   			selectCounty('countyId', 'countyName');
   		}  
   		
   		function onUploadComplete(data) {
	   		if (data == null || data == undefined) {
	   			alert("上传错误!");
	   			return;
	   		}
	   		$("#schoolLogo").val(data.data.attachUrl);
	   		$('#logo').attr("src", data.data.attachUrl);
	   	}		
		
		function searchFunc() {
			$("#TableSchool").bootstrapTable('refresh');
		}

		function clearFunc() {
			$('#searchForm')[0].reset();
			$("#TableSchool").bootstrapTable('refresh');
		}
		
		function ExcelExportAllStudent(value) {
			window.location.href = "downloadStudent?schoolid=" + value;
		}

		function ExcelImportAllStudent(value)
		{
			$("#IsOrNorImport").modal("show");
			initSamlpleExcelUpload("importSchoolStudent",2,value,onUploadExcelComplete);
		}
		function onUploadExcelComplete(data)
   		{
   			$("#IsOrNorImport").modal("hide");
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
   				//$("#TableStudent").bootstrapTable('refresh');
   				downStudentExcel(userIds);
   			}
   		}
		function downStudentExcel(userIds)
   		{
   			document.getElementById("downLoadStudentExcel").href="../StudentManage/downLoadStudentExcel?userIds="+JSON.stringify(userIds); 
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
            <a href="#">home</a>
        </li>
        <li>
            <a href="#">学校管理</a>
        </li>
    </ul>
</div>

<!-- 表格显示内容-->
<div class="box col-md-12">
            <div class="box-inner">
                <div class="box-header well" data-original-title="">
                   <h2>学校管理</h2>

                    <div class="box-icon">
                        <a href="#" class="btn btn-minimize btn-round btn-default"><i
                                class="glyphicon glyphicon-chevron-up"></i></a>
                        <a href="#" class="btn btn-close btn-round btn-default"><i
                                class="glyphicon glyphicon-remove"></i></a>
                    </div>
                </div>
                             
               
                <div class="box-content">
					<div class="alert alert-info">
						<sec:authorize access="hasAnyAuthority('SuperAdmin', 'CountyAdmin')">
						<a class="btn btn-success" data-toggle="modal"  data-target="#schoolDialog" onclick="createSchool()">
							<i class="glyphicon glyphicon-plus icon-white"></i> 添加新学校 </a>
						</sec:authorize>
					</div>
					<div class="alert alert-info" id="search">
						<form class="form-inline" role="form" id='searchForm'>
						<label class="control-label">学校名称  </label>
						<input type="text" class="form-control" name="[Like]schoolName" id="[Like]schoolName">
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
						<a class="btn btn-success" onclick="searchFunc()" ><i class="glyphicon glyphicon-search icon-white"></i>查询</a> 
						<a class="btn btn-danger" onclick="clearFunc()" ><i class="glyphicon glyphicon-remove icon-white"></i>清除</a> 
						</form>
					</div>
                    <table class="table table-striped table-bordered table-hover" id="TableSchool"></table>
                </div>
            </div>
</div>
        	
<!-- 查看对话框 -->																
<div class="modal fade" id="showSchoolDialog" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
         aria-hidden="true">

        <div class="modal-dialog">
            <div class="modal-content">
              <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>  
                    <h4 class="modal-title">查看学校</h4> 
                </div>
                <div class="modal-body">
                   <table width="90%">
                   		<tr>
							<td width="20%"><label  class="control-label">学校代码</label></td>
							<td width="30%"><label  id=schoolNumberDetail class="control-label">学校代码</label></td>
							<td width="20%"><label  class="control-label">学校名称</label></td>
							<td width="30%"><label  id="schoolNameDetail" class="control-label">学校名称</label></td>
						</tr>
						<tr>
							<td><label  class="control-label">学校地址</label></td>
							<td><label  id="schoolAddressDetail" class="control-label">学校地址</label></td>
							<td><label  class="control-label">学校简介</label></td>
							<td><label  id="schoolIntroductionDetail" class="control-label">学校简介</label></td>
						</tr>
						<tr>
							<td><label  class="control-label">联系方式</label></td>
							<td><label  id="schoolContactInformationDetail" class="control-label">联系方式</label></td>
							<td></td>
							<td></td>
                		<tr>
                			<td ><label  class="control-label">所在区县</label></td>
                			<td ><label  id="countyNameDetail" class="control-label">区县</label></td>
                			<td ><label  class="control-label">学校类型</label></td>
                			<td ><label  id="schoolTypeNameDetail" class="control-label">状态</label></td>
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
<!--新建和修改学校信息 -->
<div class="modal fade" id="schoolDialog" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
         aria-hidden="true">

        <div class="modal-dialog" style="width:800px">
            <div class="modal-content">
              <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" >×</button>  
                    <h3><span id='title'>添加学校信息</span></h3>
                </div>
                <div class="modal-body">
                   <form class="form-inline" role="form" id='schoolForm'>
                   		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/> 
                    	<div class="form-group has-success has-feedback" id="LookSchool2">
                        <table >
                        	<tr>
								<td width="20"><label class="control-label" for="schoolNumber">学校代码</label></td>
								<td width="80"><input type="text" style="width:500px" class="form-control" id="schoolNumber" name="schoolNumber"></td>
							</tr>
							<tr>
								<td ><label class="control-label" for="schoolName">学校名称</label></td>
								<td ><input type="text" style="width:500px" class="form-control" id="schoolName" name="schoolName"></td>
							</tr>
							<tr>
								<td><label class="control-label" for="schoolAddress">学校地址</label></td>
								<td><input type="text" style="width:500px" class="form-control" id="schoolAddress" name="schoolAddress"></td>
							</tr>
							<tr>
								<td><label class="control-label" for="schoolContactInformation">联系方式</label></td>
								<td><input type="text" style="width:500px" class="form-control" id="schoolContactInformation" name="schoolContactInformation"></td>
							</tr>
							
							<tr>
								<td><label class="control-label" for="schoolTypeId">学校类型</label></td>
								<td><select style="width:500px" id="schoolTypeId" name="schoolTypeId" class="form-control"></select></td>
							</tr>
							<tr>
								<td><input type="button" id="selectCounty" value="选择区县..." class="form-control" onclick="onSelect()"/></td>
								<td><span id="countyName">未选择</span></td>
							</tr>
							<tr>
								<td><input type="button" value="网站图标..." class="form-control" id="btnUploadFile" name="upload" /></td>
								<td><img id="logo" src="../images/no.jpg" width="300px" /></td>															
							</tr>
							<tr>
								<td><label class="control-label" for="schoolIntroduction">学校简介</label></td>
								<td><textarea style="width:500px;height:200px"  class="form-control" id="schoolIntroduction" name="schoolIntroduction"></textarea></td>															
							</tr>
							
						</table>
						<input type="hidden"  id="schoolId" name="schoolId">
						<input type="hidden"  id="countyId" name="countyId">
						<input type="hidden" name="schoolLogo" id="schoolLogo"/>
						</div>
                    </form >
                    
             	</div>
                <div class="modal-footer">
                     <a href="javascript:void(0)" class="btn btn-default " data-dismiss="modal">取消</a>
                     <a href="javascript:void(0)" class="btn btn-primary" id="schoolOKButton" onclick="onOKButton()">确定</a>
                </div>
            </div>
        </div>
</div>

        <!-- 提示是否要导入学生 -->
		   <div class="modal fade" id="IsOrNorImport" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		        <div class="modal-dialog"  style="width:300px">
		            <div class="modal-content">
		                <div class="modal-header">
		                    <button type="button" class="close" data-dismiss="modal">×</button>
		                    <h3><span id='title'>是否导入</span></h3>
		                </div>
		                <div class="modal-body">
			                	点击确定是否导入
		                </div>
						<div class="modal-footer">
		                     <a href="javascript:void(0)" class="btn btn-default " data-dismiss="modal">取消</a>
		                     <input type="button" id="importSchoolStudent" class="btn btn-success" value="导入学生"/>
		                </div>	
		            </div>
		        </div>
		    </div>
        <!-- 、、提示是否要导入学生 -->
            
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




<%@include file="selectcounty.jsp" %>
</body>
</html>

