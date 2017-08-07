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
			var url = "getNoticeAttachment?noticeId="+<c:out value="${notice.noticeId}"/>;
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
		
		
		
		function submitReplyContent()
		{
			var replyId = -1;
			<c:if test="${not empty myReply}">
				replyId = <c:out value="${myReply.replyId}"/>;
			</c:if>
			var replyContent = $("#noticeReplyContent").val();
			if(replyId == -1)
				return;
			if(replyContent == "")
				return
			$.ajax({
				url:"addNoticeReplyContent",
				type:"get",
				dataType:"json",
				data:{"replyId":replyId,"replyContent":replyContent,"${_csrf.parameterName}":"${_csrf.token}"},
				success:function(data){
					if(data.code == "succ"){
						alert("回复成功！");
					}
					else if(data.code == "fail")
						alert("回复失败！");
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
                    <h2>通知标题   发送时间：<fmt:formatDate value='${notice.noticeTime}' pattern='yyyy-MM-dd'/></h2>
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
                    	通知内容：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<p>${notice.noticeContent}</p>
                </div>
            </div>
        </div>
    </div><!--/span-->
	
	<div class="row">
    <div class="box col-md-12">
        <div class="box-inner">
            <div class="box-header well">
                <h2><i class="glyphicon glyphicon-info-sign"></i>通知附件</h2>

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
    <!-- 反馈表 -->
    <c:if test="${not empty count}">
	<div class="row">
    <div class="box col-md-12">
    <div class="box-inner">
    <div class="box-header well" data-original-title="">
        <h2><i class="glyphicon glyphicon-user"></i>通知-接受人</h2>

        <div class="box-icon">
            <a href="#" class="btn btn-setting btn-round btn-default"><i class="glyphicon glyphicon-cog"></i></a>
            <a href="#" class="btn btn-minimize btn-round btn-default"><i
                    class="glyphicon glyphicon-chevron-up"></i></a>
            <a href="#" class="btn btn-close btn-round btn-default"><i class="glyphicon glyphicon-remove"></i></a>
        </div>
    </div>
    <div class="box-content">
    <div class="alert alert-info">本通知共需要<c:out value="${count}"/>人查阅，已有<c:out value="${islook}"/>人查阅，<c:out value="${notlook}"/>人未查阅</div>
    <table class="table table-striped table-bordered bootstrap-datatable datatable responsive">
    <thead>
    <tr>
        <th>姓名</th>
        <th>查阅状态</th>
        <th>查阅时间</th>
        <th>查看回复</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="reply" items="${replys}">
	    <tr>
	        <td><c:out value="${reply.userName}"/></td>
	        <td class="center">
	            <c:if test="${reply.checkState == 1}">
	            	<span class="label-success label label-default">已查阅</span>
	            </c:if>
	            <c:if test="${reply.checkState == 0}">
	            	<span class="label-default label label-danger">未查阅</span>
	            </c:if>
	        </td>
	        <td class="center">
	        	<c:choose>
	        		<c:when test="${not empty reply.checkTime}">
	        			<fmt:formatDate value='${reply.checkTime}' pattern='yyyy-MM-dd hh:mm:ss'/>
	        		</c:when>
	        		<c:otherwise>
	        			--
	        		</c:otherwise>
	        	</c:choose>
	        </td>
	        <td class="center">
	        	<c:choose>
	        		<c:when test="${not empty reply.replyContent}">
	        			<a href="LookNoticeReplyDetial?replyId=${reply.replyId}">查看</a>
	        		</c:when>
	        		<c:otherwise>
	        			空
	        		</c:otherwise>
	        	</c:choose>
	        </td>
	    </tr>
    </c:forEach>
    </tbody>
    </table>
    </div>
    </div>
    </div>
    </div>
    </c:if>
    <!-- 反馈表结束 -->
    
  
</div>


	<!-- 回复(未回复过) -->
   	<c:if test="${empty count}">
   		<c:if test="${empty myReply.replyContent}">
   		<div class="box col-md-12">
        <div class="box-inner">
            <div class="box-header well" data-original-title="">
                <h2><i class="glyphicon glyphicon-edit"></i>回复</h2>

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
                <form role="form">
		            <div class="form-group">
		                <label for="exampleInputPassword1">回复该通知</label>
		                <textarea class="form-control" id="noticeReplyContent" name="noticeReplyContent" rows="5" placeholder="请输入回复" required></textarea>
		            </div>
                </form>
                <button type="submit" class="btn btn-default" onclick="submitReplyContent()">提交回复</button>
            </div>
        </div>
    	</div>
    	</c:if>
    	<!-- 已回复过 -->
		    <c:if test="${not empty myReply.replyContent}">
		    
		    <div class="box col-md-12">
		        <div class="box-inner">
		            <div class="box-header well" data-original-title="">
		                <h2>你的回复</h2>
		
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
		                <div class="page-header">
		                    <dl>
		                        <dt>你已回复过该通知，你的回复如下：</dt>
		                        <dd><c:out value=""/><c:out value="${myReply.replyContent}"/></dd>
		                    </dl>
		                </div>
		            </div>
		        </div>
		    </div>
		    </c:if>
		    <!-- 已恢复过结束 -->
		   	
    </c:if>
    <!-- 回复结束 -->
    
    
    
    


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