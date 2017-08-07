<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>

<html lang="en">
<head>
    <title>收到的通知</title>
    
	<%@include file="head.jsp" %>
	
	<script src="<c:url value="/myjs/recvnotice.js"/>"></script>
	
	<script type="text/javascript">
		$(document).ready(function(){
			loadMyRecvNotice();
		});
		
		
		function queryParams(params){//配置参数
			var temp;
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


		function loadMyRecvNotice()
		{
			var url = "getAllRecvNotice";
			$("#tableNotice").bootstrapTable({
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
			    idField: "noticeId",  //标识哪个字段为id主键  
			    pageSize: 10,  //每一页 的 数量
				pageNumber:1,      //当前页码
				showRefresh:false,  //显示刷新按钮
				singleSelect:true,  //复选框只能选择一条记录
				clickToSelect:true,  //点击行即可选中单选/复选框
				columns:[
				 	{
				 		field:'noticeTitle',
				 		title:'通知标题',
				 		width:'200',
				 		align:'center',
				 		valign:'maddle',
				 		sortable:true,
				 	},
				 	{
				 		field:'noticeTime',
				 		title:'通知时间',
				 		width:100,
				 		align:'center',
				 		valigh:'middle',
				 		formatter:function(value,row,index){
						 	return getFormatDate(new Date(value), "yyyy-MM-dd")
						}
				 	},
				 	{
				 		field:'noticeOverTime',
				 		title:'过期时间',
				 		width:100,
				 		align:'center',
				 		valigh:'middle',
				 		formatter:function(value,row,index){
						 	return getFormatDate(new Date(value), "yyyy-MM-dd")
						}
				 	},
				 	{
				 		field: 'noticeIsOrNotReply', 
		                title: '须回执',		 
		                width: 50,	 
		                align: 'center',		 
		                valign: 'middle',
				 		formatter:function(value,row,index){
		                	return value == 0 ?"否":"是";
		                }	
				 	},
				 	{
				 		field:'noticeAcceptTypeName',
				 		title:'类别',
				 		width:50,
				 		align:'center',
				 		valigh:'middle'
				 	},
				 	{		 	
				 		title:'操作',
				 		field:'noticeId',
				 		width:50,
				 		formatter:function(value,row,index){
				 			var a = '<a class="btn btn-info" href="../LookNotice/'+value+'">';
							var b = '<i class="glyphicon glyphicon-zoom-in icon-white"></i>查看</a> ';		                        	 	
		                    return a+b;
				 		}
				 	}
				 ],
				 onLoadSuccess: function (data) {
				 }
			});
		}

		function searchFunc() {
			$("#tableNotice").bootstrapTable('refresh');
		}

		function clearFunc() {
			$('#searchForm')[0].reset();
			$("#tableNotice").bootstrapTable('refresh');
		}
	</script>
	
	
</head>

<body>
    <%@include file="top.jsp" %>
    <div class="ch-container">
        <div class="row">
			<%@include file="left.jsp" %>

        <div id="content" class="col-lg-10 col-sm-10">
            <!-- content starts -->
                <div>
        <ul class="breadcrumb">
            <li>
                <a href="#">我的通知</a>
            </li>
            <li>
                <a href="#">收到的通知</a>
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
                        <a href="#" class="btn btn-setting btn-round btn-default"><i
                                class="glyphicon glyphicon-cog"></i></a>
                        <a href="#" class="btn btn-minimize btn-round btn-default"><i
                                class="glyphicon glyphicon-chevron-up"></i></a>
                        <a href="#" class="btn btn-close btn-round btn-default"><i
                                class="glyphicon glyphicon-remove"></i></a>
                    </div>
                </div>
                <div class="box-content">
					<div id="search">
						<form class="form-inline" role="form" id='searchForm'>
						<label class="control-label">标题</label>
						<input type="text" class="form-control" name="[Like]noticeTitle" id="[Like]noticeTitle"> 
						<label class="control-label">内容</label>
						<input type="text" class="form-control" name="[Like]noticeContent" id="[Like]noticeContent">
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
						<a class="btn btn-success" onclick="searchFunc()" ><i class="glyphicon glyphicon-plus icon-white"></i>查询</a> 
						<a class="btn btn-success" onclick="clearFunc()" ><i class="glyphicon glyphicon-plus icon-white"></i>清除</a> 
						</form>
					</div>
                    <table class="table table-striped table-bordered table-hover" id="tableNotice"></table>
                </div>
            </div>
        </div>
    </div><!--/span-->

    <!-- content ends -->
    </div><!--/#content.col-md-0-->
</div><!--/fluid-row-->

    <hr>

    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
         aria-hidden="true">

        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">×</button>
                    <h3>Settings</h3>
                </div>
                <div class="modal-body">
                    <p>Here settings can be configured...</p>
                </div>
                <div class="modal-footer">
                    <a href="#" class="btn btn-default" data-dismiss="modal">Close</a>
                    <a href="#" class="btn btn-primary" data-dismiss="modal">Save changes</a>
                </div>
            </div>
        </div>
    </div>

    <footer class="row">
        <p class="col-md-9 col-sm-9 col-xs-12 copyright">&copy; <a href="http://usman.it" target="_blank">Muhammad
                Usman</a> 2012 - 2015</p>

        <p class="col-md-3 col-sm-3 col-xs-12 powered-by">Powered by: <a
                href="http://usman.it/free-responsive-admin-template">Charisma</a></p>
    </footer>

</div><!--/.fluid-container-->


</body>
</html>
