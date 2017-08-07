<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>

<html lang="en">
<head>
    <title>查看通知</title>  
	<%@include file="head.jsp" %>

	
	<script type="text/javascript">
		$(document).ready(function(){
			loadNoticeAttachment();
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
			
			return temp;
		}
		function loadNoticeAttachment()
		{
			var url = "../LookNotice/getNoticeAttachment?noticeId="+<c:out value="${notice.noticeId}"/>;
			$("#tableNoticeAttachment").bootstrapTable({
				url:url,
				method:"post",
		    	contentType: "application/x-www-form-urlencoded",
		    	queryParams: queryParams,
		    	queryParamsType:"undefined",//参数格式,发送标准的restful类型的参数请求
		    	striped: true,     //使表格带有条纹  
				pagination: true, //在表格底部显示分页工具栏 
		    	dataType: "json",
			    search: true, //是否显示右上角的搜索框  
			    sidePagination: "server", 
			    //showHeader: false,  隐藏表头
			    idField: "noticeId",  //标识哪个字段为id主键  
			    pageSize: 10,  //每一页 的 数量
				pageNumber:1,      //当前页码
				showRefresh:true,  //显示刷新按钮
				singleSelect:true,  //复选框只能选择一条记录
				clickToSelect:true,  //点击行即可选中单选/复选框
				columns:[
				 	{
				 		field:'attachName',
				 		title:'附件名称',
				 		width:400,
				 		align:'center',
				 		valigh:'middle'
				 	},
				 	{
				 		field:'attachSize',
				 		title:'附件大小',
				 		width:300,
				 		align:'center',
				 		valigh:'middle',
				 	},
				 	{		 	
				 		title:'操作',
				 		field:'attachId',
				 		formatter:function(value,row,index){	                		
	                   		var a = '<a class="btn btn-danger" href="downloadAttachment/'+value+'">';
	                   		var b = '<i class="glyphicon glyphicon-zoom-in icon-white"></i>下载</a> ';
	                        return a+b; 
				 		}
				 	}
				 ],
				 onLoadSuccess: function (data) {
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
        <div id="content" class="col-lg-10 col-sm-10">
            <!-- content starts -->
                <div>
		        	<ul class="breadcrumb">
			            <li>
			                <a href="#">我的公告</a>
			            </li>
			            <li>
			                <a href="#">公告详情</a>
			            </li>
		        	</ul>
    			</div>

    <div class="row">
    <div class="box col-md-12">
    <div class="row">
        <div class="box col-md-12">
            <div class="box-inner">
                <div class="box-header well" data-original-title="">
                    <h2>公告标题   发送时间：<fmt:formatDate value='${notice.noticeTime}' pattern='yyyy-MM-dd'/></h2>
                    <div class="box-icon">
                        <a href="#" class="btn btn-setting btn-round btn-default"><i
                                class="glyphicon glyphicon-cog"></i></a>
                        <a href="#" class="btn btn-minimize btn-round btn-default"><i
                                class="glyphicon glyphicon-chevron-up"></i></a>
                        <a href="#" class="btn btn-close btn-round btn-default"><i
                                class="glyphicon glyphicon-remove"></i></a>
                    </div>
                </div>
                <div class="box-content" height="100%">
                    ${notice.noticeTitle}<br/><br/><br/>
                    	公告内容：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<p>${notice.noticeContent}</p>
                </div>
            </div>
        </div>
    </div><!--/span-->
	
	<div class="row">
    <div class="box col-md-12">
        <div class="box-inner">
            <div class="box-header well">
                <h2><i class="glyphicon glyphicon-info-sign"></i>公告附件</h2>

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
				<table class="table table-striped table-bordered table-hover" id="tableNoticeAttachment"></table>
            </div>
        </div>
    </div>
    </div>
</div>
    <!-- content ends -->
    </div><!--/#content.col-md-0-->
</div><!--/fluid-row-->

    

    <hr>



    <footer class="row">
        <p class="col-md-9 col-sm-9 col-xs-12 copyright">&copy; <a href="http://usman.it" target="_blank">Muhammad
                Usman</a> 2012 - 2015</p>

        <p class="col-md-3 col-sm-3 col-xs-12 powered-by">Powered by: <a
                href="http://usman.it/free-responsive-admin-template">Charisma</a></p>
    </footer>

</div><!--/.fluid-container-->


</body>
</html>