<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="zh-cmn-Hans">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <title>发送通知</title>
    <%@include file="WeChatheader.jsp" %>

</head>
<body ontouchstart>
<div>
    <div class="weui-msg">
        <div class="weui-msg__icon-area"><i class="weui-icon-info weui-icon_msg"></i></div>
        <div class="weui-msg__text-area">
            <h2 class="weui-msg__title">选择</h2>
            <p class="weui-msg__desc">请选择通知的接收单位</p>
        </div>
        <div class="weui-msg__opr-area">
            <p class="weui-btn-area">
           		<c:if test="${not empty schoolClassObject}">
           			<a href="sendNoticeToObject?objectType=${schoolClassObject}" class="weui-btn weui-btn_primary">给我的班级发通知</a>
           		</c:if>
           		<c:if test="${not empty gradeObject}">
           			<a href="sendNoticeToObject?objectType=${gradeObject}" class="weui-btn weui-btn_primary">给我的年级发通知</a>
           		</c:if>
           		<c:if test="${not empty schoolObject}">
           			<a href="sendNoticeToObject?objectType=${schoolObject}" class="weui-btn weui-btn_primary">给我的学校发通知</a>
           		</c:if>            		            		
            </p>
        </div>
        
    </div>
</div>
</body>
</html>
