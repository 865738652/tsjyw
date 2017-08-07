<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
  <head xmls="http://www.w3.org/1999/xhtml">
  	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8">
	<meta name="HandheldFeiendly" content="true" />
	<meta name="MobileOptimized" content="width" />
	<meta name="applicable-device" content="mobile">
	<link rel="stylesheet" href="../css/touch.css" />
	<script src="../js/jquery-1.7.2.min.js" charset="utf-8"></script>
	<script src="../js/wap3.0-public.js" charset="utf-8"></script>
    <script src="../js/wappv.js"></script>   
    <script src="../js/touch.min.js"></script>     
    <title>公告详情</title>
    <%@include file="WeChatheader.jsp" %>
    <style type="text/css">
	    .noticealigncenter { 
		clear: both; 
		display: block; 
		margin:auto; 
		width:100%;
		height:100%;
		} 
    </style>
    
  </head>
  
  <body>
        <header class="header-index">
            <div class="header-user">
                <span class="logo">
                    <img style="width:100px;position: absolute;left:10px;top:10px;" src="../images/logol.png"  />
                </span>
                <span class="channel"></span>
            </div>
        </header>
        <div class="article-1">
	        <h1 class="h1">${notice.noticeTitle}</h1>
	        <p class="info">
	        	<a href="javascript:void(0);" class="num">${notice.noticeAcceptTypeName}</a>
	        	<fmt:formatDate value='${notice.noticeTime}' pattern='yyyy-MM-dd'/>
	        	<span class="name">公告状态:${notice.noticeStateName}&nbsp;</span></p>
	        <div class="main" id="main">
	       		${notice.noticeContent}<br/><br/><br/><br/>
	       		
	       		<c:if test="${not empty attachments_img}">
	       			<c:forEach var="attachment" items="${attachments_img}">
	       				<img class="noticealigncenter" src="${attachment.attachmentUrl}"/><br/>
	       			</c:forEach>
	       		</c:if>

	       		<!-- 需要下载的附件 -->
	       		<c:if test="${not empty attachments_other}">
	       		<div class="page__bd">
			        <div class="weui-cells__title">通知-附件</div>
			        <div class="weui-cells weui-cells_radio">
			        	<c:forEach var="attachment" items="${attachments_other}">
			            <label class="weui-cell weui-check__label" for="x11">
			                <div class="weui-cell__bd">
			                    <p><a href="Wechatdownload?attachmentId=${attachment.attachmentId}"><c:out value="${attachment.attachmentName}"/></a></p>
			                </div>
			            </label>
			            </c:forEach>
			        </div>
			    </div>
			    </c:if>
	       		<!-- /需要下载的附件 -->
	       		
	       		
	       		<br/>过期时间:<fmt:formatDate value='${notice.noticeOverTime}' pattern='yyyy-MM-dd'/><br/>
	       	</div>
	    </div>
        <div class="hot-news activity-center-section">
            <a href="javascript:void(0);" class="more">查看更多通知</a>
            <p class="info">唐山家庭教育网</p>
        </div>    
         <!-- @end 活动中心  add by hanjw 20150917-->     

        <script src="../js/wappv.js"></script>   
        <script src="../js/touch.min.js"></script>
        <%@include file="WeChatToolbar.jsp" %>
</body>
</html>