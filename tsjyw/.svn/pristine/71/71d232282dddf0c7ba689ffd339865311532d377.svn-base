<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>

<html lang="en">
<head>
    
    <title>管理课程</title>
    <%@include file="head.jsp" %>
    
    <script type="text/javascript">
       	 
    	var action = "create";  
    	var schoolid = <c:out value="${schoolId}"/>;     	
    	$(function(){
    	
    		$.ajaxSetup ({
         		cache: false //关闭AJAX缓存
     		});
    		
    		loadSchoolCourse();
    		bindSelect("schoolCourseStateId", "getSchoolCourseState", null, "schoolCourseStateId", "schoolCourseStateName");
    	});
    	
    	function queryParams(params) {  //配置参数
	        var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
	            pageSize: params.pageSize,   //页面大小
	            pageNumber: params.pageNumber,
	            schoolId : <c:out value="${schoolId}"/>  
	        };
        	return temp;
    	}
    	
    	function loadSchoolCourse()
    	{ 
    		$("#TableCourse").bootstrapTable({
    			method:"get",
		    	url: "getCourseBySchool", 
		    	method:"post",
		    	contentType: "application/x-www-form-urlencoded",
		    	queryParams: queryParams,
		    	queryParamsType:"undefined",//参数格式,发送标准的restful类型的参数请求
		    	striped: true,     //使表格带有条纹  
  				pagination: true, //在表格底部显示分页工具栏 
		    	dataType: "json",
			    pagination: true,
			    search: false, //是否显示右上角的搜索框  
			    sidePagination: "server", 
			    //showHeader: false,  隐藏表头
			    idField: "SchoolCourseId",  //标识哪个字段为id主键  
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
				        	field: 'schoolCourseId', 
		                    title: '课程代码',		 
		                    width: 150,		 
		                    align: 'center',		 
		                    valign: 'middle',		 
		                    sortable: true,
				        },
				        {
				        	field: 'schoolCourseName', 
		                    title: '课程名称',		 
		                    width: 200,		 
		                    align: 'center',		 
		                    valign: 'middle'
				        },		        
				        {
		                    title: '操作',
		                    field: 'schoolCourseId',
		                    formatter:function(value,row,index){		                    		                        		                        
	                   			var a = '<a class="btn btn-info" data-toggle="modal" data-target="#courseDialog" href="javascript:void(0);" onclick=\"modifyCourse('+value+')\">'; 
	                   			var b = '<i class="glyphicon glyphicon-edit icon-white"></i>修改</a> ';	                   			
	                   			var c = '<a class="btn btn-danger" href="javascript:void(0);" onclick=\"deleteCourse('+value+')\" >';
	                   			var d = '<i class="glyphicon glyphicon-remove icon-white"></i>删除</a> ';   			
	                        	return a+b+c+d;  
		                  	}
                  		}				        
				],
				onLoadSuccess: function (data) {
					
				}
		    });
    	}
   		
   		$("#courseDialog").on("hidden.bs.model",function(e){$(this).removeData();}); 
   		
   		function createCourse() {   			
   			action = "create";
   			$("#title").html("添加课程");
   			$('#courseManageForm')[0].reset();
   			$("#schoolCourseId").val("");
   			$("#schoolId").val(schoolid);
   		}
   		
   		function modifyCourse(schoolCourseId) {
   			action = "modify";
   			$("#title").html("修改课程");
   			$('#courseManageForm')[0].reset();
   			
			$.ajax({
				url:"getCourse",			
				data:{"schoolCourseId":schoolCourseId},
				dataType:"json",
				success:function(data) {  
			        if (data.code != "succ") {
   						alert(data.data);
   						$('#courseDialog').modal('hide');
   						return;
   					}
  			        $('#schoolCourseId').val(data.data.schoolCourseId);
					$('#schoolCourseName').val(data.data.schoolCourseName);
					$("#schoolId").val(data.data.schoolId);
					$('#schoolCourseStateId').val(data.data.schoolCourseStateId);
			    },
			    error: function(XMLHttpRequest, textStatus, errorThrown) {
					alert("网络错误: " + XMLHttpRequest.status + ", " + textStatus);
			    }
			});	   						
   		}
   		
   		function deleteCourse(schoolCourseId) {
   			if (!confirm("确认删除该课程吗？"))
   				return;
   				
   			$.ajax({
				url:"deleteCourse",			
				data:{"schoolCourseId":schoolCourseId},
				dataType:"json",
				success:function(data) {  
					if (data.code != "succ") {
   						alert(data.data);
   					}
      				$("#TableCourse").bootstrapTable('refresh');
  				},
			    error: function(XMLHttpRequest, textStatus, errorThrown) {
					alert("网络错误: " + XMLHttpRequest.status + ", " + textStatus);
			    }
			});
   		}
   		
   		function onOKButton() {
   			var url = "createCourse";
   			
   			if (action == "modify")
   				url = "modifyCourse";
   				
   			$.ajax({   				
	        	url:url,
	        	type:"post",
	        	data:$('#courseManageForm').serialize(),
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
	        		$('#courseDialog').modal('hide');
	        		$("#TableCourse").bootstrapTable('refresh');
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
                <a href="SchoolManage">学校管理</a>
            </li>
            <li>
                <a href="CourseManage">课程管理</a>
            </li>
        </ul>
</div>
<!-- 表格显示内容-->
<div class="box col-md-12">
            <div class="box-inner">
                <div class="box-header well" data-original-title="">
                   <h2>课程管理</h2>

                    <div class="box-icon">
                        <a href="#" class="btn btn-minimize btn-round btn-default"><i
                                class="glyphicon glyphicon-chevron-up"></i></a>
                        <a href="#" class="btn btn-close btn-round btn-default"><i
                                class="glyphicon glyphicon-remove"></i></a>
                    </div>
                </div>
                             
               
                <div class="box-content">
                <div class="alert alert-info">
                	<a class="btn btn-success" data-toggle="modal"  data-target="#courseDialog" onclick="createCourse()">
                        <i class="glyphicon glyphicon-plus icon-white"></i> 添加课程 </a>
                </div>
                     <table class="table table-striped table-bordered table-hover" id="TableCourse"></table>
                </div>
            </div>
</div>

 <div class="modal fade" id="courseDialog" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
         aria-hidden="true">

        <div class="modal-dialog">
            <div class="modal-content">
              <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" >×</button>  
                    <h3><span id='title'>修改课程信息</span></h3>
                </div>
                <div class="modal-body">
                   <form class="form-inline" role="form" id='courseManageForm'>
                   		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/> 
                    	<input type="hidden"   name="schoolId" id="schoolId" value='<c:out value="${schoolId}"/>'>
                    	<input type="hidden"   name="schoolCourseId" id="schoolCourseId">
                    	<div class="form-group has-success has-feedback" id="showCourse">
							<table width="90%">
								<tr>
									<td width="40%"><label class="control-label" for="inputSuccess4">课程名称</label></td>
						            <td width="60%"><input type="text" class="form-control" name="schoolCourseName" id="schoolCourseName"></td>
								</tr>
								<tr>
									<td><label class="control-label" for="inputSuccess4">课程状态</label></td>
						            <td><select class="form-control" name="schoolCourseStateId" id="schoolCourseStateId"></select></td>
								</tr>
							</table>
						</div>                       
 
   					</form>
                    
             	</div>
                <div class="modal-footer">
                     <a href="javascript:void(0)" class="btn btn-default " data-dismiss="modal">取消</a>
                     <a href="javascript:void(0)" class="btn btn-primary" id="courseOKButton" onclick="onOKButton()">确定</a>
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

</body>
</html>