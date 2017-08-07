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
    <title>操作成功</title>
    <%@include file="WeChatheader.jsp" %>
</head>
<body ontouchstart>
<div>
    <div class="weui-msg">
        <div class="weui-msg__icon-area"><i class="weui-icon-success weui-icon_msg"></i></div>
        <div class="weui-msg__text-area">
            <h2 class="weui-msg__title">操作成功</h2>
            <p class="weui-msg__desc">请选择导航链接</p>
        </div>
        <div class="weui-msg__opr-area">
            <p class="weui-btn-area">
                <a href="WeChatArticle" class="weui-btn weui-btn_primary">首页</a>
                <a href="WeChatNoticeSystem" class="weui-btn weui-btn_primary">通知系统</a>
                <a href="WeChatAskQuestion?respUserId=all&respUserType=all" class="weui-btn weui-btn_primary">我要提问</a>
                <a href="WeChatFamousTeacher" class="weui-btn weui-btn_primary">查看名师</a>
                <a href="WeChatVolunteer" class="weui-btn weui-btn_primary">查看志愿者</a>
            </p>
        </div>
    </div>
    <%@include file="WeChatBottom.jsp" %>
</div>

</body>
</html>