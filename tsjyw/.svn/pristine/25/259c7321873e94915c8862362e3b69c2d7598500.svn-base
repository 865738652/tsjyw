<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<!-- saved from url=(0039)http://wap.87870.com/account/login.html -->
<html data-dpr="2" style="font-size: 75px;"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!--编码格式-->
    
    <!--禁止浏览器从本地计算机缓存访问页面内容，这样设定，访问者将无法脱机浏览-->
    <meta http-equiv="Expires" content="-1">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-cache">
    <!--主题背景-->
    <meta name="theme-color" content="#ffffff">
    <!--禁止百度转码-->
    <meta http-equiv="Cache-Control" content="no-siteapp">
    <!--优先使用IE最新版和Chrome，避免IE使用兼容模式-->
    <meta http-equiv="X-UA-Compatible" content="IE=Edge, chrome=1">
    <!-- 启用 WebApp 全屏模式，伪装App离线应用，删除苹果默认的工具栏和菜单栏 -->
    <meta name="apple-mobile-web-app-capable" content="yes">
    <!--隐藏状态栏/设置状态栏颜色：只有在开启WebApp全屏模式时才生效。content的值为default | black | black-translucent 。-->
    <meta name="apple-mobile-web-app-status-bar-style" content="black-translucent">
    <!--忽略数字自动识别为电话号码/忽略识别邮箱-->
    <meta content="telephone=no,email=no" name="format-detection">

    <!-- 样式重置 -->
    <link rel="stylesheet" href="../newphone/login/css/reset.css">
    <!-- lib.flexible -->
	<meta name="viewport" content="initial-scale=0.5, maximum-scale=0.5, minimum-scale=0.5, user-scalable=no">
    <style>@charset "utf-8";html{color:#000;background:#fff;overflow-y:scroll;-webkit-text-size-adjust:100%;-ms-text-size-adjust:100%}html *{outline:0;-webkit-text-size-adjust:none;-webkit-tap-highlight-color:rgba(0,0,0,0)}html,body,div,dl,dt,dd,ul,ol,li,h1,h2,h3,h4,h5,h6,pre,code,form,fieldset,legend,input,textarea,p,blockquote,th,td,hr,button,article,aside,details,figcaption,figure,footer,header,hgroup,menu,nav,section{margin:0;padding:0}input,select,textarea{font-size:100%}table{border-collapse:collapse;border-spacing:0}fieldset,img{border:0}abbr,acronym{border:0;font-variant:normal}del{text-decoration:line-through}address,caption,cite,code,dfn,em,th,var{font-style:normal;font-weight:500}ol,ul{list-style:none}caption,th{text-align:left}h1,h2,h3,h4,h5,h6{font-size:100%;font-weight:500}q:before,q:after{content:''}sub,sup{font-size:75%;line-height:0;position:relative;vertical-align:baseline}sup{top:-.5em}sub{bottom:-.25em}a:hover{text-decoration:underline}ins,a{text-decoration:none}</style>
    <meta content="modeName=750-12" name="grid">
    <style>.grid {box-sizing:content-box;padding-left: 0.32rem;padding-right: 0.32rem;margin-left: -0.24rem;}.grid:before,.grid:after{content: " ";display: table;}.grid:after {clear: both;}.grid [class^="col-"] {margin-left: 0.24rem;float: left;}.grid .col-1 {width: 0.56rem;}.grid .col-2 {width: 1.36rem;}.grid .col-3 {width: 2.16rem;}.grid .col-4 {width: 2.96rem;}.grid .col-5 {width: 3.7600000000000002rem;}.grid .col-6 {width: 4.5600000000000005rem;}.grid .col-7 {width: 5.36rem;}.grid .col-8 {width: 6.16rem;}.grid .col-9 {width: 6.960000000000001rem;}.grid .col-10 {width: 7.760000000000001rem;}.grid .col-11 {width: 8.56rem;}.grid .col-12 {width: 9.36rem;}</style>
    <!-- lib.jquery -->
    <title>登录</title>
    <!--meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"-->
    <meta name="author" content="Yufal">
    <!--public css-->
    <link rel="stylesheet" href="../newphone/login/css/public.css">
    <!--private css-->
    <link rel="stylesheet" href="../newphone/login/css/login.css">
    <script src="../newphone/login/js/jquery.min-1.9.1.js"></script>
    <script type="text/javascript">
    	function submit()
    	{
    		$.ajax({
    			url:"LoginCheck",
    			type:"post",
    			data:$("#from1").serialize(),
    			dataType:"json",
    			success:function(data){
    				console.log(data);
    				if(data.code == "succ")
    				{
    					console.log(data.data);
    					location.href = data.data;
    				}
    				else
    					$("#errorMessage").html("登录失败");
    			},
    			error:function(data){
    				$("#errorMessage").html("登录失败");
    			}
    		});
    	}
    </script>
    
</head>
<body style="font-size: 24px;">
    <form id="from1">
        <div class="wrapper">
            <div class="header">
                <div class="logo">
                    <a href="WeChatNewArticle"><img src="../newphone/login/img/h-logo.png"></a>
                </div>
                <div class="header-title">
                    <h1>登录</h1>
                </div>
                <div class="registered-guide">
                    <h1><a href="Register">注册</a></h1>
                </div>
            </div>
            <div class="view">
                <div class="input-area">
                    <div class="input-box input-bottom-line">
                        <img class="fl" src="../newphone/login/img/lg-un.png">
                        <input class="fl" id="username" name="username" type="text" placeholder="输入用户名">
                        <img id="clear_userName" class="fr hidden" src="../newphone/login/img/lg-clear-un.png">
                    </div>
                    <div class="clear"></div>
                    <div class="input-box input-bottom-line">
                        <img class="fl" src="../newphone/login/img/lg-upwd.png">
                        <input class="fl" id="password" name="password" type="password" placeholder="输入登录密码">
                    </div>
                    <div class="clear"></div>
                    
                    
                    <div class="clear"></div>
                </div>
                <div class="notify block">
                    <p id="errorMessage"></p>
                </div>
                <div class="do-login">
                    <p class="active" id="login" onclick="submit()">立即登录</p>
                </div>
                <p class="forget"><a href="../ResetPassword/" >忘记密码？</a></p>
                
            </div>
            <div class="footer"></div>
        </div>
    </form>


</body></html>