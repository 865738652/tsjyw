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
    
    <title>管理班级</title>
    
    <%@include file="head.jsp" %>
    
       <script type="text/javascript">
    	 
    	var action = "create"; 
    	var schoolid = <c:out value="${schoolid}"/>;    	
    	$(function(){
    	
    		$.ajaxSetup ({
         		cache: false //关闭AJAX缓存
     		});
    		
    		loadClass();  
    		var p = {schoolId: schoolid, pageNumber: 1, pageSize: 100 };
    		bindSelect("gradeId", "../GradeManage/getGradeBySchool", p, "gradeId", "gradeName");  	
			bindSelect("searchGradeId", "../GradeManage/getGradeBySchool", p, "gradeId", "gradeName", "", "", "全部年级");
    	});
    	
    	function queryParams(params) {  //配置参数
    		var temp;
    		temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
	            pageSize: params.pageSize,   //页面大小
	            pageNumber: params.pageNumber,
	            schoolId:schoolid
	       	};
	       	<c:if test="${not empty _csrf}">
				temp.${_csrf.parameterName} = "${_csrf.token}";
			</c:if>
			extendTalbeParams(temp, "searchForm");
        	return temp;
    	}
    	
    	function loadClass()
    	{ 
    		var url = "getSchoolClassBySchool"; 
    							  				
    		$("#TableClass").bootstrapTable({
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
			    idField: "schoolClassId",  //标识哪个字段为id主键  
			    pageSize: 10,  //每一页 的 数量
				pageNumber:1,     //当前页码
				showRefresh: false,  //显示刷新按钮  
				singleSelect: true,//复选框只能选择一条记录  
				clickToSelect: true,//点击行即可选中单选/复选框  
				columns: [
				        {
				        	field: 'schoolClassName', 
		                    title: '班级名称',		 
		                    width: 150,		 
		                    align: 'center',		 
		                    valign: 'middle'
				        },	
				        {
				        	field: 'gradeName', 
		                    title: '年级',		 
		                    width: 150,		 
		                    align: 'center',		 
		                    valign: 'middle'
				        },	
				        {
				        	field: 'schoolName', 
		                    title: '学校',		 
		                    width: 200,		 
		                    align: 'center',		 
		                    valign: 'middle'
				        },				        
				        {
		                    title: '操作',
		                    field: 'schoolClassId',
		                    formatter:function(value,row,index){		                    		                        		                        
	                   			var html = '<a class="btn btn-info" data-toggle="modal" data-target="#classDialog" href="javascript:void(0);" onclick=\"modifyClass('+value+')\">'; 
	                   			html += '<i class="glyphicon glyphicon-edit icon-white"></i>修改</a> ';
	                   			<sec:authorize access="hasAnyAuthority('SuperAdmin', 'CountyAdmin', 'SchoolMaster','SchoolAdmin')">	                   			
	                   			html += '<a class="btn btn-danger" href="javascript:void(0);" onclick=\"deleteClass('+value+')\" >';
	                   			html += '<i class="glyphicon glyphicon-remove icon-white"></i>删除</a> '; 
	                   			</sec:authorize>
	                   			html += '<a class="btn btn-success" href="../ClassMaster/' + value + '" >';
	                   			html += '<i class="glyphicon glyphicon-edit icon-white"></i>班主任</a> ';
	                   			html += '<a class="btn btn-success" href="../StudentManage/' + value + '">';
	                   			html += '<i class="glyphicon glyphicon-edit icon-white"></i>学生</a> ';
	                   			html += '<a class="btn btn-success" href="#">';
	                   			html += '<i class="glyphicon glyphicon-edit icon-white"></i>任课教师</a> ';    			
	                        	return html;  
		                  	}
                  		}				        
				]
		    });
    	}

   		$("#classDialog").on("hidden.bs.model",function(e){$(this).removeData();}); 
   		
   		function createClass() {   			
   			action = "create";
   			$("#title").html("添加班级");
   			$('#classForm')[0].reset();
   			$("#classId").val("");
   		}
   		
   		function modifyClass(schoolClassId) {
   			action = "modify";
   			$("#title").html("修改班级信息");
   			$('#classForm')[0].reset();
   			
			$.ajax({
				url:"getSchoolClass",			
				data:{"schoolClassId":schoolClassId},
				dataType:"json",
				success:function(data) {  
			        if (data.code != "succ") {
   						alert(data.data);
   						$('#classDialog').modal('hide');
   						return;
   					}
  			        $('#schoolClassId').val(data.data.schoolClassId);
					$('#schoolClassName').val(data.data.schoolClassName);
					$('#gradeId').val(data.data.gradeId);
			    },
			    error: function(XMLHttpRequest, textStatus, errorThrown) {
					alert("网络错误: " + XMLHttpRequest.status + ", " + textStatus);
			    }
			});	   						
   		}
   		
   		function deleteClass(schoolClassId) {
   			if (!confirm("确认删除该班级吗？"))
   				return;
   				
   			$.ajax({
				url:"deleteSchoolClass",			
				data:{"schoolClassId":schoolClassId},
				dataType:"json",
				success:function(data) {  
					if (data.code != "succ") {
   						alert(data.data);
   					}
      				$("#TableClass").bootstrapTable('refresh');
  				},
			    error: function(XMLHttpRequest, textStatus, errorThrown) {
					alert("网络错误: " + XMLHttpRequest.status + ", " + textStatus);
			    }
			});
   		}
   		
   		function onOKButton() {
   			var url = "createSchoolClass";
   			
   			if (action == "modify")
   				url = "modifySchoolClass";
   				
   			$.ajax({   				
	        	url:url,
	        	type:"post",
	        	data:$('#classForm').serialize(),
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
	        		$('#classDialog').modal('hide');
	        		$("#TableClass").bootstrapTable('refresh');
	        	},
			    error: function(XMLHttpRequest, textStatus, errorThrown) {
					alert("网络错误: " + XMLHttpRequest.status + ", " + textStatus);
			    }
	        });
   		}   

		function searchFunc() {
			$("#TableClass").bootstrapTable('refresh');
		}

		function clearFunc() {
			$('#searchForm')[0].reset();
			$("#TableClass").bootstrapTable('refresh');
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
           
                <div>
        <ul class="breadcrumb">
            <li>
                <a href="SchoolManage">学校管理</a>
            </li>
            <li>
                <a href="ClassManage">管理班级</a>
            </li>
        </ul>
    </div>
<!--表格显示的内容-->
<div class="box col-md-12">
        <div class="box-inner">
                <div class="box-header well" data-original-title="">
                    <h2>班级管理</h2>
                    <div class="box-icon">
                        <a href="#" class="btn btn-setting btn-round btn-default"><i class="glyphicon glyphicon-cog"></i></a>
                        <a href="#" class="btn btn-minimize btn-round btn-default"><i class="glyphicon glyphicon-chevron-up"></i></a>
                        <a href="#" class="btn btn-close btn-round btn-default"><i class="glyphicon glyphicon-remove"></i></a>
                    </div>
              </div>
                
                <div class="box-content">
               		<div class="alert alert-info">
               		<sec:authorize access="hasAnyAuthority('SuperAdmin', 'CountyAdmin', 'SchoolMaster','SchoolAdmin')">
                    <a class="btn btn-success" data-toggle="modal" data-target="#classDialog" onclick="createclass()">
                        <i class="glyphicon glyphicon-plus icon-white"></i> 添加班级</a>
                	</sec:authorize>
                	</div>  
					<div class="alert alert-info" id="search">
						<form class="form-inline" role="form" id='searchForm'>
						<label class="control-label">所在年级  </label>
						<select class="form-control" id="searchGradeId" name="[Equal]gradeId"></select>
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
						<a class="btn btn-success" onclick="searchFunc()" ><i class="glyphicon glyphicon-search icon-white"></i>查询</a> 
						<a class="btn btn-danger" onclick="clearFunc()" ><i class="glyphicon glyphicon-remove icon-white"></i>清除</a> 
						</form>
					</div>
                    <table class="table table-striped table-bordered table-hover" id="TableClass"></table>       
            </div>
        </div>
  </div>

    <!-- 添加和修改班级对话框 -->
<div class="modal fade" id="classDialog" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
         aria-hidden="true">

        <div class="modal-dialog" style="width:500px">
            <div class="modal-content">
              <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" >×</button>  
                    <h3><span id='title'>修改班级信息</span></h3>
                </div>
                <div class="modal-body">
                   <form class="form-inline" role="form" id='classForm'>
                   		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/> 
                    	<div class="form-group has-success has-feedback" id="LookClass2">
                        <table >
                        	<tr>
								<td ><label class="control-label" for="className">班级名称</label></td>
								<td ><input style="margin-left:15px;"  type="text" class="form-control" id="schoolClassName" name="schoolClassName"></td>								
							</tr>
							<tr>
								<td><label class="control-label" for="gradeId">所在年级</label></td>
								<td><select style="margin-left:15px;width:230px" class="form-control" id="gradeId" name="gradeId"></select></td>
							</tr>
						</table>
						<input type="hidden"  id="schoolClassId" name="schoolClassId"/>
						</div>
                    </form >
                    
             	</div>
                <div class="modal-footer">
                     <a href="javascript:void(0)" class="btn btn-default " data-dismiss="modal">取消</a>
                     <a href="javascript:void(0)" class="btn btn-primary" id="classOKButton" onclick="onOKButton()">确定</a>
                </div>
            </div>
        </div>
   </div>  
 </div>
 </div>
 </div>
</body>
</html>
