<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>唐山家庭教育网-账户中心-用户登录</title>
    <meta name="keywords" content="">
    <meta name="description" content="">
    <link href="../css/personal_style.css" rel="stylesheet">
    <link href="../css/personal.css" rel="stylesheet">
    <script type="text/javascript" src="../js/jquery.js"></script>
    <script src="../js/jquery.base64.js" type="text/javascript"></script>
    <script type="text/javascript" src="../js/acc_public.js"></script>
    <!-- <script type="text/javascript" src="../js/acc_login.js"></script> -->
    <!-- <script type="text/javascript" src="../js/validform.js"></script> -->
    <meta property="qc:admins" content="65472076337564607070637571337564753524771660454" />
</head>
<body class="body_bg">
    <!-- header -->
    <div class="login_bar">
        <div class="w1170 bc clearfix">
            <p class="bar fr pr10" id="header">
                <!--<script type="text/javascript" src="/personal/js/loadheader.js?v=1.0"></script>-->
            </p>
            <p class="pl10 color85">
                唐山家庭教育网</p>
        </div>
    </div>
    <!-- header end-->
    <div class="person_banner">
        <div class="w1170 banner_hei bc relative">
            <h1 class="person_h1">
                账号中心</h1>
        </div>
    </div>
    <div class="tag w1170 bc">
        <a href="http://www.tsjtjyw.com/">首页</a> &gt; 账号中心 &gt; 登录
    </div>
    <div class="qp_pageprompt" style="display: none">
    </div>
    <div class="w1170 bc clearfix personal">
        <div id="personalContent" class="personal_content_login">
            <div class="content_wp">
                <form action="../Account/Authenticate" method="post">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/> 
                <div id="acc_login" class="content_item">
                    <fieldset>
                        <legend>登录</legend>
                        <div class="line">
                        </div>
                        <div id="loginWrapper0" class="text_wrapper login_wrapper bc ">
                            <p class="account_error">
                                <span id="info">&nbsp;</span>
                            </p>
                            <p>
                                <input type="text" class="username" maxlength="16" id="userName" name="userName" placeholder="账号/手机" datatype="usernamemobile|m" nullmsg="请填写6-16位账户名或手机号" errormsg="请填写6-16位账户名或手机号" sucmsg="">
                            </p>
                            <p>
                                <input id="password" name="password" type="password" maxlength="20" class="password" placeholder="密码" datatype="*6-20" nullmsg="请填写密码" errormsg="请填写6-20位字符组成的密码" sucmsg="">
                            </p>
                            <p>
                                <input class="vcode" id="vcode" name="vcode" maxlength="4" datatype="/^[\w\W]{4}$/" nullmsg="请填写验证码" errormsg="请填写4位验证码">
                                <img id="vcodesj" src="./VR 87870-账户中心-登录_files/verify_code.ashx" width="93" height="40">
                                <a id="change_code" class="change_code" href="javascript:;">换一张</a>
                            </p>
                            <p>
                                <button id="accountBtn" class="to_login acc_submit" type="submit">
                                    登录</button>
                                <a class="find_pwd" href="../UserManage/ForgotPassword">忘记密码？</a> <a class="to_reg" href="../Account/Register">
                                    注册新账号</a><br>
                                    <span class="qqwbdlp">第三方登录：</span><a href="http://account.87870.com/qq/default.aspx?redirectUri=aHR0cDovL2QuODc4NzAuY29tL3hpYXphaW5yLTEzNzA3Lmh0bWw=" class="qqdlbut"></a><a href="http://account.87870.com/sina/default.aspx?redirectUri=aHR0cDovL2QuODc4NzAuY29tL3hpYXphaW5yLTEzNzA3Lmh0bWw=" class="wbdlbut"></a>
                            </p>
                        </div>
                    </fieldset>
                </div>
                </form>
            </div>
        </div>
    </div>
    <%@include file="footer.jsp" %>
</body></html>
