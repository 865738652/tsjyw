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
    <title>查看回复</title>  
	<%@include file="head.jsp" %>
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
			                <a href="#">通知回复</a>
			            </li>
		        	</ul>
    			</div>
    			
				<div class="row">
					  <div class="box col-md-9">
					      <div class="box-inner">
					          <div class="box-header well" data-original-title="">
					              <h2>回复详情</h2>
					
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
					                  	<ol>
						                    <li>回复人：<c:out value="${reply.user.userName}"/></li>
						                    <li>回复时间：<fmt:formatDate value='${reply.replyTime}' pattern='yyyy-MM-dd hh:mm:ss'/></li>
						                    <li>回复内容：<c:out value="${reply.replyContent}"/></li>
						                </ol>
					                  </dl>
					              </div>
					          </div>
					      </div>
					  </div>    
			</div>	
			</div>
			</div>
			</div>
				  
					
</body>
</html>
