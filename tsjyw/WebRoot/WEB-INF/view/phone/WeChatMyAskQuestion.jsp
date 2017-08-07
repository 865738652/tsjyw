<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh-cmn-Hans">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    
    <%@include file="WeChatheader.jsp" %>
    <title>我的问题</title>
    <style type="text/css">
	    	img { 
		clear: both; 
		margin:auto; 
		width:100%;
		} 
    </style>
</head>
  <body>
      <div class="weui-panel">
      <div class="weui-panel__hd">提问过的问题</div>
      <div class="weui-panel__bd">
          
          	<c:choose>
          		<c:when test="${not empty myAnswerQuestions}">
          			<c:forEach var="myAnswerQuestion" items="${myAnswerQuestions}">
          				<div class="weui-media-box weui-media-box_text">
          					<h4 class="weui-media-box__title"><c:out value="${myAnswerQuestion.askQuestionTitle}" escapeXml="false"/></h4>
		                 	<p class="weui-media-box__desc"><c:out value="${myAnswerQuestion.askQuestionContent}" escapeXml="false"/></p>
		                 	<ul class="weui-media-box__info">
		                     	<li class="weui-media-box__info__meta"><c:out value="${myAnswerQuestion.askQuestionTypeName}"/></li>
		                     	<li class="weui-media-box__info__meta"><fmt:formatDate value='${myAnswerQuestion.askQuestionTime}' pattern='yyyy-MM-dd'/></li>
		                     	<li class="weui-media-box__info__meta weui-media-box__info__meta_extra"><a href="WeChatLookQuestion?askQuestionId=${myAnswerQuestion.askQuestionId}">详情</a></li>
		                 	</ul>
		                </div>
          			</c:forEach>
          		</c:when>
          		<c:otherwise>
          			未提问过问题
          		</c:otherwise>
          	</c:choose>
          
      </div>
      
      <%@include file="WeChatBottom.jsp" %>
      <div>
      
  </div>
  </body>
</html>
