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
   
    <meta charset="utf-8">
    <title>管理年级</title>
    <%@include file="head.jsp" %>
    
    <script type="text/javascript">
    	var action = "create";
    	var schoolId = <c:out value="${schoolid}"/>; 
    	$(function(){
    		$.ajaxSetup ({
         		cache: false //关闭AJAX缓存
     		});
    		
    		loadGrade();
    		//bindSelect("teacherStateId", "getTeacherState", null, "teacherStateId", "teacherStateName");
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
        	return temp;
    	}
    	
    	function loadGrade()
    	{
    		$("#TableGrade").bootstrapTable({
		    	url: "getGradeBySchool", 
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
			    idField: "GradeId",  //标识哪个字段为id主键  
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
				        	field: 'gradeName', 
		                    title: '年级名称',		 
		                    width: 100,		 
		                    align: 'center',		 
		                    valign: 'middle',		 
		                    sortable: true,
				        },
				        {
		                    title: '操作',
		                    field: 'gradeId',
		                    formatter:function(value,row,index){
	                   			var html = '<a class="btn btn-info" data-toggle="modal" data-target="#gradeDialog" href="javascript:void(0);" onclick=\"modifyGrade('+value+')\">'; 
	                   			html += '<i class="glyphicon glyphicon-zoom-in icon-white"></i>修改</a> '; 	
	                   			<sec:authorize access="hasAnyAuthority('SuperAdmin', 'CountyAdmin', 'SchoolMaster','SchoolAdmin')">
	                   			html += '<a class="btn btn-danger" href="javascript:void(0);" onclick=\"deleteGrade('+value+')\" >';
	                   			html += '<i class="glyphicon glyphicon-zoom-in icon-white"></i>删除</a> ';
	                   			</sec:authorize>
	                   			html += '<a class="btn btn-success" href="../GradeMaster/' + value + '">';
                                html += '<i class="glyphicon glyphicon-edit icon-white"></i>年级主任</a> ';
	                        	return html;  
		                  	}
                  		}				        
				],
				onLoadSuccess: function (data) {
					
				}
		    });
    	}
    	
   		$("#gradeDialog").on("hidden.bs.model",function(e){$(this).removeData();}); 
   		
   		function createGrade() {
   			action = "create";
   			$("#title").html("添加年级");
   			$("#gradeForm")[0].reset();
   			$("#gradeId").val("");
   			$("#schoolId").val(""+schoolId);
   		}
   		
   		function modifyGrade(gradeId) {
   			action = "modify";
   			$("#title").html("修改年级信息");
   			$("#gradeForm")[0].reset();
   			
			$.ajax({
				url:"getGrade",			
				data:{"gradeId":gradeId},
				dataType:"json",
				success:function(data) {  
			        if (data.code != "succ") {
   						alert(data.data);
   						$('#gradeDialog').modal('hide');
   						return;
   					}
  			        
			        $('#gradeId').val(data.data.gradeId);
			        $('#gradeName').val(data.data.gradeName);
			        $("#schoolId").val(data.data.schoolId);
			        
			    },
			    error: function(XMLHttpRequest, textStatus, errorThrown) {
					alert("网络错误: " + XMLHttpRequest.status + ", " + textStatus);
			    }
			});	   						
   		}
   		
   		function deleteGrade(gradeId) {
   			if (!confirm("确认删除该年级吗？"))
   				return;
   				
   			$.ajax({
				url:"deleteGrade",			
				data:{"gradeId":gradeId},
				dataType:"json",
				success:function(data) {  
					if (data.code != "succ") {
   						alert(data.data);
   					}
      				$("#TableGrade").bootstrapTable('refresh');
  				},
			    error: function(XMLHttpRequest, textStatus, errorThrown) {
					alert("网络错误: " + XMLHttpRequest.status + ", " + textStatus);
			    }
			});
   		}
   		
   		function onOKButton() {
   			var url = "createGrade";
   			if (action == "modify")
   				url = "modifyGrade";

   			$.ajax({
	        	url:url,
	        	type:"post",
	        	data:$("#gradeForm").serialize(),
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
	        		$("#gradeDialog").modal('hide');
	        		$("#TableGrade").bootstrapTable('refresh');
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
                <a href="#">学校管理</a>
            </li>
            <li>
                <a href="#">管理年级</a>
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
                	<sec:authorize access="hasAnyAuthority('SuperAdmin', 'CountyAdmin', 'SchoolMaster','SchoolAdmin')">
                    <a class="btn btn-success" data-toggle="modal"  data-target="#gradeDialog" onclick="createGrade()" >
                        <i class="glyphicon glyphicon-plus icon-white"></i>添加年级</a>   
                    </sec:authorize>              
                	</div>
                     <table class="table table-striped table-bordered table-hover" id="TableGrade"></table>
                </div>
            </div>
        </div>
 </div>
</div>
</div>
    
 
<div class="modal fade" id=gradeDialog tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
         aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
              <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>  
                    <h3><span id='title'>修改年级信息</span></h3> 
                </div>
                <div class="modal-body">
                   <form class="form-inline" role="form" id='gradeForm'>
                   		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/> 
                    	<input type="hidden"   name="gradeId" id="gradeId">
                    	<input type="hidden" name="schoolId" id="schoolId" value='<c:out value="${schoolid}"/>' />
                    	<div class="form-group has-success has-feedback" id="showGrade">
							<table width="90%">
								<tr>
									<td width="40%">		    
						              <label class="control-label" for="inputSuccess4">年级名称</label>
						            </td>
						            <td width="60%">
						              <input type="text" class="form-control" name="gradeName" id="gradeName">	
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
</body>

</html>