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
      
    <title>名师讲座</title>
    <%@include file="head.jsp" %>
	    <script type="text/javascript">
    	 
    	var action = "create";    	
    	$(function(){
    	
    		$.ajaxSetup ({
         		cache: false //关闭AJAX缓存
     		});
    		
    		loadNetCourse();
    		bindSelect("userId", "../FamousTeacherManage/getAllFamousTeacher", {pageNumber:1, pageSize:100}, "userId", "userName");
    		bindSelect("netCourseTypeId", "../NetCourse/getNetCourseType", null, "netCourseTypeId", "netCourseTypeName");
    		initSamlpleUpload("btnUploadFile",onUploadComplete);
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
    	
    	function loadNetCourse()
    	{ 
    		
    		$("#TableNetCourse").bootstrapTable({
    			
		    	url: "../NetCourse/getAllNetCourse", 
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
			    idField: "netCourseId",  //标识哪个字段为id主键  
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
				        	field: 'netCourseNumber', 
		                    title: '视频编号',		 
		                    width: 100,		 
		                    align: 'center',		 
		                    valign: 'middle',		 
		                    sortable: true,
				        },
				        {
				        	field: 'netCourseName', 
		                    title: '视频名称',		 
		                    width: 100,		 
		                    align: 'center',		 
		                    valign: 'middle'
				        },				        
				        {
				        	field: 'netCourseIntegral', 
		                    title: '所需积分',		 
		                    width: 100,		 
		                    align: 'center',		 
		                    valign: 'middle'
				        },
				        {
				        	field: 'userName', 
		                    title: '名师',		 
		                    width: 100,		 
		                    align: 'center',		 
		                    valign: 'middle'
				        },
				        {
		                    title: '操作',
		                    field: 'netCourseId',
		                    formatter:function(value,row,index){
		                    	var html = '<a class="btn btn-info" data-toggle="modal" data-target="#showNetCourseDialog" href="javascript:void(0);" onclick=\"showNetCourse('+value+')\">';
   								html += '<i class="glyphicon glyphicon-zoom-in icon-white"></i>查看</a> ';		
   								<sec:authorize access="hasAuthority('SuperAdmin')">                        	                    		                        		                        
	                   			html += '<a class="btn btn-info" data-toggle="modal" data-target="#netCourseDialog" href="javascript:void(0);" onclick=\"modifyNetCourse('+value+')\">'; 
	                   			html += '<i class="glyphicon glyphicon-edit icon-white"></i>修改</a> ';	                   			
	                   			html += '<a class="btn btn-danger" href="javascript:void(0);" onclick=\"deleteNetCourse('+value+')\" >';
	                   			html += '<i class="glyphicon glyphicon-remove icon-white"></i>删除</a> '; 
	                   			</sec:authorize>
	        					return html; 
		                  	}
                  		}				        
				],
				onLoadSuccess: function (data) {
					
				}
		    });
    	}
   		function showNetCourse(netCourseId)
		{
			$('#showNetCourseDialog').on('show.bs.modal', function () {
   				$.ajax({
   					url:"../NetCourse/getNetCourse",			
   					data:{"netCourseId":netCourseId},
   					dataType:"json",
   					success:function(data) {  
   						if (data.code != "succ") {
   							alert(data.data);
   							$('#showNetCourseDialog').modal('hide');
   							return;
   						}
   						$("#netCourseIdDetail").text(data.data.netCourseId);
				        $("#netCourseNumberDetail").text(data.data.netCourseNumber);
						$("#netCourseNameDetail").text(data.data.netCourseName);
						$("#netCourseLinkDetail").text(data.data.netCourseLink);
						
						$("#netCourseTimeDetail").text(getSmpFormatDateByLong(data.data.netCourseTime,false));
						$("#netCourseIntegralDetail").text(data.data.netCourseIntegral);
						$("#netCourseIntroDetail").text(data.data.netCourseIntro);		
				        $("#netCourseTypeNameDetail").text(data.data.netCourseTypeName);
				        $("#userNameDetail").text(data.data.userName);
				    }
   				});	
   			});
		}
   		$("#netCourseDialog").on("hidden.bs.model",function(e){$(this).removeData();}); 
   		
   		function createNetCourse() { 
   			  			
   			action = "create";
   			$("#title").html("添加讲座视频");
   			$('#netCourseForm')[0].reset();
   			$("#netCourseId").val("");
   			onNetCourseShow();
   		}
   		
   		function modifyNetCourse(netCourseId) {
   			
   			action = "modify";
   			$("#title").html("修改讲座视频");
   			$('#netCourseForm')[0].reset();
   			
   			onNetCourseShow();
			$.ajax({
				url:"getNetCourse",			
				data:{"netCourseId":netCourseId},
				dataType:"json",
				success:function(data) {  
			        if (data.code != "succ") {
   						alert(data.data);
   						$('#netCourseDialog').modal('hide');
   						return;	
   					}
   					
   					if(data.data.netCourseImgPath != null){
   						$('#netCourseImgPath').val(data.data.netCourseImgPath);
   						$('#logo').attr("src",data.data.netCourseImgPath);
   					}
  			        $('#netCourseId').val(data.data.netCourseId);
					$('#netCourseNumber').val(data.data.netCourseNumber);
					$('#netCourseName').val(data.data.netCourseName);
					$('#netCourseLink').val(data.data.netCourseLink);
					$('#netCourseIntegral').val(data.data.netCourseIntegral);
					$('#netCourseIntro').val(data.data.netCourseIntro);
					$('#netCourseTime').val(getSmpFormatDateByLong(data.data.netCourseTime,false));
		            $('#netCourseTime').datepicker('setDate', new Date(data.data.netCourseTime));
					
			        $('#userId').val(data.data.userId);
					$('#netCourseTypeId').val(data.data.netCourseTypeId);
			    },
			    error: function(XMLHttpRequest, textStatus, errorThrown) {
					alert("网络错误: " + XMLHttpRequest.status + ", " + textStatus);
			    }
			});	   						
   		}
   		
   		function deleteNetCourse(netCourseId) {
   			if (!confirm("确认删除该视频讲座吗？"))
   				return;
   				
   			$.ajax({
				url:"deleteNetCourse",			
				data:{"netCourseId":netCourseId},
				dataType:"json",
				success:function(data) {  
					if (data.code != "succ") {
   						alert(data.data);
   					}
      				$("#TableNetCourse").bootstrapTable('refresh');
  				},
			    error: function(XMLHttpRequest, textStatus, errorThrown) {
					alert("网络错误: " + XMLHttpRequest.status + ", " + textStatus);
			    }
			});
   		}
   		function onUploadComplete(data) {
	   		if (data == null || data == undefined) {
	   			alert("上传错误!");
	   			return;
	   		}
	   		$("#netCourseImgPath").val(data.data.attachUrl);
	   		$('#logo').attr("src", data.data.attachUrl);
	   		
	   		
	   	}	
	   	
   		function onOKButton() {
   			var url = "../NetCourse/createNetCourse";
   			
   			if (action == "modify")
   				url = "../NetCourse/modifyNetCourse";
   			onNetCourseSubmit()
   			$.ajax({   				
	        	url:url,
	        	type:"post",
	        	data:$('#netCourseForm').serialize(),
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
	        		$('#netCourseDialog').modal('hide');
	        		$("#TableNetCourse").bootstrapTable('refresh');
	        	},
			    error: function(XMLHttpRequest, textStatus, errorThrown) {
					alert("网络错误: " + XMLHttpRequest.status + ", " + textStatus);
			    }
	        });
   		} 
   		
		function onNetCourseShow() {
			$('#netCourseTime').datepicker({format: 'yyyy-mm-dd', language: 'zh-CN'}); 
		
	    }
	
		function onNetCourseSubmit() {
			var s = $('#netCourseTime').val();
			
			
			var l = new Date((s).replace(new RegExp("-","gm"),"/")).getTime();
			$('#netCourseTime').val(l);
			
		}  	

		function searchFunc() {
			$("#TableNetCourse").bootstrapTable('refresh');
		}

		function clearFunc() {
			$('#searchForm')[0].reset();
			$("#TableNetCourse").bootstrapTable('refresh');
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
						<a href="#">名师专讲</a>
					</li>
				</ul>
			</div>
			<!--neirongkaishi -->
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
					<a class="btn btn-success" data-toggle="modal"  data-target="#netCourseDialog" onclick="createNetCourse()">
					<i class="glyphicon glyphicon-plus icon-white"></i> 添加讲座视频 </a> 
					</sec:authorize>           
					</div>
					<div id="search">
						<form class="form-inline" role="form" id='searchForm'>
						<label class="control-label">视频名称</label>
						<input type="text" class="form-control" name="[Like]netCourseName" id="[Like]netCourseName">
						<label class="control-label">名师姓名</label>
						<input type="text" class="form-control" name="[Like]userName" id="[Like]userName">
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
						<a class="btn btn-success" onclick="searchFunc()" ><i class="glyphicon glyphicon-plus icon-white"></i>查询</a> 
						<a class="btn btn-success" onclick="clearFunc()" ><i class="glyphicon glyphicon-plus icon-white"></i>清除</a> 
						</form>
					</div>
					<table class="table table-striped table-bordered table-hover" id="TableNetCourse"></table>
				</div>
			  </div>
			</div>
		</div>
	    </div>
		</div><!-- /row -->
     <hr>
     
<!-- 查看对话框 -->																
<div class="modal fade" id="showNetCourseDialog" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
         aria-hidden="true">

        <div class="modal-dialog">
            <div class="modal-content">
              <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>  
                    <h4 class="modal-title">查看视频讲座</h4> 
                </div>
                <div class="modal-body">
                   <table width="90%">
                   		<tr>
							<td width="20%"><label  class="control-label">视频代码</label></td>
							<td width="30%"><label  id="netCourseNumberDetail" class="control-label">视频代码</label></td>
							<td width="20%"><label  class="control-label">视频名称</label></td>
							<td width="30%"><label  id="netCourseNameDetail" class="control-label">视频名称</label></td>
						</tr>
						<tr>
							<td><label  class="control-label">上传时间</label></td>
			                <td><label  id="netCourseTimeDetail" class="control-label">上传时间</label></td>
							<td><label  class="control-label">所需积分</label></td>
							<td><label  id="netCourseIntegralDetail" class="control-label">所需积分</label></td>
						</tr>
						<tr>
							<td><label  class="control-label">讲座类型</label></td>
							<td><label  id="netCourseTypeNameDetail" class="control-label">讲座类型</label></td>
							<td><label  class="control-label">名师</label></td>
							<td><label  id="userNameDetail" class="control-label">名师</label></td>
                			
						</tr>
						<tr>
							<td><label  class="control-label">讲座链接</label></td>
							<td><label  id="netCourseLinkDetail" class="control-label">讲座链接</label></td>
							
						</tr>
						<tr>
							<td><label  class="control-label">视频介绍</label></td>
							<td><label  id="netCourseIntroDetail" class="control-label">视频介绍</label></td>
							
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
  <!--新建修改视频讲座 -->
   <div class="modal fade" id="netCourseDialog" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">

        <div class="modal-dialog"  style="width:900px">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">×</button>
                    <h3><span id='title'>修改视频讲座</span></h3>
                </div>
                <div class="modal-body">
                    <form class="form-inline" role="form" id='netCourseForm'>
                    	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/> 
                    <div class="form-group has-success has-feedback" id="showNetCourse">
                    	<table align="center">
							<tr>
								<td ><label class="control-label" for="netCourseNumber">视频代码</label></td>
								<td ><input   type="text" class="form-control" id="netCourseNumber" name="netCourseNumber"></td>
								<td><label class="control-label" for="netCourseName">视频名称</label></td>
								<td><input type="text" class="form-control" id="netCourseName" name="netCourseName"></td>
							</tr>
							<tr>
								<td><label class="control-label" for="netCourseTime">上传时间</label></td>
								<td><input type="text" class="form-control" id="netCourseTime" name="netCourseTime"></td>
								
								<td><label class="control-label" for="netCourseIntegral">所需积分</label></td>
								<td><input type="text" class="form-control" id="netCourseIntegral" name="netCourseIntegral"></td>
							</tr>
							<tr>
								<td><label class="control-label" for="netCourseTypeId">讲座类型</label></td>
								<td><select id="netCourseTypeId" name="netCourseTypeId" class="form-control" style="width:180px"></select></td>
								<td><label class="control-label" for="userName">名师</label></td>
								<td><select type="text" class="form-control" id="userId" name="userId"></select></td>
							</tr>
							<tr>
						        <td><input type="button" value="视频图片..." class="form-control" id="btnUploadFile" name="upload" /></td>
								<td><img id="logo" src="../images/no.jpg" width="200px" /></td>															
							</tr>
							
							
						</table>
						<table >
							<tr>
								<td><label class="control-label" for="netCourseLink">文件链接</label></td>
								<td><input id="netCourseLink" name="netCourseLink" class="form-control" style="width:500px"></td>
							</tr>
							<tr >
								<td style="vertical-align:top"><label class="control-label" for="netCourseIntro">视频介绍</label></td>
								<td ><textarea class="form-control" id="netCourseIntro" name="netCourseIntro" style="width:500px;height:200px;"  placeholder="视频介绍" required></textarea>
								</td>
							</tr>	
						</table>
						<input type="hidden"  id="netCourseId" name="netCourseId">
						<input type="hidden"  id="netCourseImgPath" name="netCourseImgPath">
						<input type="hidden"  id="userId" name="userId">					
					</div>
                    </form>                                                                
                </div>
                <!-- body -->
                <hr>
                <div class="modal-footer">
                     <a href="javascript:void(0)" class="btn btn-default " data-dismiss="modal">取消</a>
                     <a href="javascript:void(0)" class="btn btn-primary" id="netCourseOKButton" onclick="onOKButton()">确定</a>
                </div>
            </div>
        </div>
    </div> 
     

    </div><!-- /row -->	
	</div><!--/.fluid-container-->
  </body>
</html>
