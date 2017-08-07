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
    <title>操作失败</title>
	<%@include file="WeChatheader.jsp" %>
</head>
<body ontouchstart>
<div>
    <div class="weui-msg">
        <div class="weui-msg__icon-area"><i class="weui-icon-warn weui-icon_msg"></i></div>
        <div class="weui-msg__text-area">
            <h2 class="weui-msg__title">操作失败</h2>
            <p class="weui-msg__desc" style="text-align:center;"><c:out value="${exception}"/></p>
        </div>
        <div class="weui-msg__opr-area">
            <p class="weui-btn-area">
            	<c:choose>
            		<c:when test="${empty sessionScope.userId}">
            			<a href="Login" class="weui-btn weui-btn_primary">登录</a>
            			<a href="WeChatNewArticle" class="weui-btn weui-btn_default">回到首页</a>
            		</c:when>
            		<c:otherwise>
                		<a href="WeChatNewArticle" class="weui-btn weui-btn_default">回到首页</a>
            		</c:otherwise>
            	</c:choose>
            </p>
        </div>
    </div>
    
</div>

</body>
</html>
