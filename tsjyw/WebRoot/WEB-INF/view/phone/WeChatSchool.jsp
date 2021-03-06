<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<!-- saved from url=(0021)http://wap.87870.com/ -->
<html xmlns="http://www.w3.org/1999/xhtml" data-dpr="2" style="font-size: 108px;"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!--编码格式-->
   
    <meta name="viewport" content="initial-scale=1, maximum-scale=3, minimum-scale=1, user-scalable=no">
    <!--禁止浏览器从本地计算机缓存访问页面内容，这样设定，访问者将无法脱机浏览-->
    <meta http-equiv="Expires" content="-1"><meta http-equiv="Pragma" content="no-cache"><meta http-equiv="Cache-Control" content="no-cache">
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
    <link rel="stylesheet" href="../newphone/famousteacher/css/reset.css">
    <!-- lib.flexible -->
    <script src="http://hm.baidu.com/hm.js?225694c67b2c1d267db850fac9dd0170"></script>
    <script src="http://hm.baidu.com/hm.js?7cd50153b76179187abf8de7bbac9871"></script>
    <script type="text/javascript" src="../newphone/famousteacher/js/flexible.js"></script>
    <script type="text/javascript" src="../newphone/famousteacher/js/flexible_css.js"></script>
    <style>@charset "utf-8";html{color:#000;background:#fff;overflow-y:scroll;-webkit-text-size-adjust:100%;-ms-text-size-adjust:100%}html *{outline:0;-webkit-text-size-adjust:none;-webkit-tap-highlight-color:rgba(0,0,0,0)}html,body,div,dl,dt,dd,ul,ol,li,h1,h2,h3,h4,h5,h6,pre,code,form,fieldset,legend,input,textarea,p,blockquote,th,td,hr,button,article,aside,details,figcaption,figure,footer,header,hgroup,menu,nav,section{margin:0;padding:0}input,select,textarea{font-size:100%}table{border-collapse:collapse;border-spacing:0}fieldset,img{border:0}abbr,acronym{border:0;font-variant:normal}del{text-decoration:line-through}address,caption,cite,code,dfn,em,th,var{font-style:normal;font-weight:500}ol,ul{list-style:none}caption,th{text-align:left}h1,h2,h3,h4,h5,h6{font-size:100%;font-weight:500}q:before,q:after{content:''}sub,sup{font-size:75%;line-height:0;position:relative;vertical-align:baseline}sup{top:-.5em}sub{bottom:-.25em}a:hover{text-decoration:underline}ins,a{text-decoration:none}</style>
    <meta content="modeName=750-12" name="grid">
    <script type="text/javascript" src="../newphone/famousteacher/js/makegrid.js"></script>
    <!-- lib.jquery -->
    <script type="text/javascript" src="../newphone/famousteacher/js/jquery.min.js"></script>
    <script type="text/javascript" src="../newphone/famousteacher/js/touch.min.js"></script>
	<script type="text/javascript" src="../newphone/famousteacher/js/jwolf-1.2.js"></script>
    <script type="text/javascript" src="../newphone/famousteacher/js/datecommon.js"></script>
    <title>唐山家庭教育网</title>
    
    <!--meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"-->
    <meta name="author" content="zhangtao">
    <!--public css-->
    <link rel="stylesheet" href="../newphone/famousteacher/css/public.css">
    <!--global js-->
    <script src="../newphone/famousteacher/js/acc_public.js"></script>
    <script type="text/javascript" src="../newphone/famousteacher/js/global.js"></script>
    <!--component css&&js-->
    <link rel="stylesheet" href="../newphone/famousteacher/css/swiper.min.css">
    <script type="text/javascript" src="../newphone/famousteacher/js/swiper.min.js"></script>
    
<link rel="stylesheet" href="../newphone/famousteacher/css/swiper.min.css">
<script type="text/javascript" src="../newphone/famousteacher/js/swiper.min(1).js"></script>
<!--private css&&js-->
<link rel="stylesheet" href="../newphone/famousteacher/css/list-item.css">
<script type="text/javascript" src="../newphone/famousteacher/js/swiper-logic.js"></script>
<style>.grid {box-sizing:content-box;padding-left: 0.32rem;padding-right: 0.32rem;margin-left: -0.24rem;}.grid:before,.grid:after{content: " ";display: table;}.grid:after {clear: both;}.grid [class^="col-"] {margin-left: 0.24rem;float: left;}.grid .col-1 {width: 0.56rem;}.grid .col-2 {width: 1.36rem;}.grid .col-3 {width: 2.16rem;}.grid .col-4 {width: 2.96rem;}.grid .col-5 {width: 3.7600000000000002rem;}.grid .col-6 {width: 4.5600000000000005rem;}.grid .col-7 {width: 5.36rem;}.grid .col-8 {width: 6.16rem;}.grid .col-9 {width: 6.960000000000001rem;}.grid .col-10 {width: 7.760000000000001rem;}.grid .col-11 {width: 8.56rem;}.grid .col-12 {width: 9.36rem;}</style></head>
<body style="font-size: 24px; height: 1334px;">
    <form method="post" action="http://wap.87870.com/" id="form1">
<div class="aspNetHidden">
<input type="hidden" name="__VIEWSTATE" id="__VIEWSTATE" value="/wEPDwULLTEwOTI1OTYyNzVkZC/E/qGSY3p0gGzFn0D7r32DfVH4ulGpbxbxEkzaereH">
</div>

<div class="aspNetHidden">

	<input type="hidden" name="__VIEWSTATEGENERATOR" id="__VIEWSTATEGENERATOR" value="90059987">
</div>
    <div id="header" class="header">

<div class="header-top">
    <div class="user-center">
        <a href="MyDetail"><img src="../newphone/newphonearticle/img/h-user-center.png"></a>
    </div>
    <div class="logo">
        <img src="../newphone/newphonearticle/img/h-logo.png">
    </div>
</div>
<script type="text/javascript">
	function cancelLogin()
	{
		$.ajax({
			url:"CancelLogin",
			type:"get",
			dataType:"json",
			success:function(data){
				if(data.code == "fail")
					alert("注销失败");
				location.reload();
			},
			error:function(){
				alert("注销失败！");
			}
		});
	}
</script>
<div class="header-nav">
    <ul>
        <li class="nav-item" data-urlpath="WeChatNewArticle">热点</li>
        <li class="nav-item" data-urlpath="WeChatFamousTeacher">名师</li>
        <li class="nav-item" data-urlpath="WeChatVolunteer">志愿者</li>
        <li class="nav-item" data-urlpath="WeChatNewHotAskQuestion">问答</li>
        <li class="nav-item" data-urlpath="WeChatNoticeSystem">通知</li>
        <li class="nav-item on" data-urlpath="WeChatSchool">校园</li>
    </ul>
</div>

    </div>
    <div class="clear"></div>
    <c:if test="${empty sessionScope.userId}">
	    <div class="login-warn">
			<section>
				<p>
					您目前还没有登录哦~~~请点击此处<a href="Login">登录</a>或者<a href="Register">注册</a>
				</p>
				<a class="close-warn">
					<img src="../newphone/famousteacher/img/close.png">
				</a>
			</section>
		</div>
    </c:if>
<input type="hidden" id="columnindex" value="0">
<span id="hiddenpageindex" style="display:none;">1</span>
<span id="hiddenpagecount" style="display:none;">0</span>
<span id="Span2" style="display:none;"></span>

<div class="swiper-view">
			
			<div class="swiper-container swiper-container-horizontal">
				
				
				<div class="swiper-pagination swiper-pagination-clickable"><span class="swiper-pagination-bullet"></span><span class="swiper-pagination-bullet"></span><span class="swiper-pagination-bullet swiper-pagination-bullet-active"></span><span class="swiper-pagination-bullet"></span><span class="swiper-pagination-bullet"></span></div>
			</div>
		</div>
		
        <div class="view">
			<ul class="view-ul">
				
				<li>
                    <!--一位名师-->
                    <!-- 
					<li class="view-ul-li">
                        <a class="article_link" href="#" target="_self">
                            <div class="item-description">
                                <div class="description-title">
                                    <p class="description-title-text">曾经赚足业内眼球的神秘公司Magic Leap魔力正在褪去</p>
                                </div>
                                <div class="description-info">
                                    <div class="info-source fl">
                                        <span>行业</span>
                                        <span>刚刚</span>
                                    </div>
                                    <div class="info-look-count fr">
                                        <img class="fl" src="../newphone/famousteacher/img/tlak-icon.png">
                                        <p class="fl">0</p>
                                    </div>
                                </div>
                            </div>
                            <div class="item-picture">
                                <img src="../newphone/famousteacher/img/fe4201a7-219d-4851-b48d-9d5a47e17fa0.jpg">
                            </div>
                        </a>
                    </li>
                     -->
                    <!--一篇文章-->
                    <a id="load-more" href="javascript:;" style="display: none;">加载更多...</a>
				</li>
                <div class="clear"></div>
			</ul>
		</div>
    <script src="../newphone/famousteacher/js/DataLazyLoad.js"></script>
    <script src="../newphone/school/js/index.js"></script>

    <div id="goTop">
		<img src="../newphone/famousteacher/img/top.png">
	</div>
    <div id="footer" class="footer" style="display: none;">
        <ul>
<li class="mb30">
	<!--<p class="footer-nav">
		首页
		<span>|</span>
		登录
		<span>|</span>
		返回顶部
		<span>|</span>
		关于我们
	</p>-->
</li>
<li class="mb30">
	<p class="company">唐山市睿智文化交流有限公司</p>
</li>
<li>
	<p class="copyright">Copyright © 2001-2016 www.tsjtjyw.com All rights reserved.</p>
</li>
</ul>
    </div>
        <div style="display:none;">
        <script>
            var _hmt = _hmt || [];
            (function () {
                var hm = document.createElement("script");
                hm.src = "//hm.baidu.com/hm.js?7cd50153b76179187abf8de7bbac9871";
                var s = document.getElementsByTagName("script")[0];
                s.parentNode.insertBefore(hm, s);
            })();
        </script>
        <script>
            var _hmt = _hmt || [];
            (function () {
                var hm = document.createElement("script");
                hm.src = "//hm.baidu.com/hm.js?225694c67b2c1d267db850fac9dd0170";
                var s = document.getElementsByTagName("script")[0];
                s.parentNode.insertBefore(hm, s);
            })();
</script>
    </div>
    <script type="text/javascript" src="../newphone/famousteacher/js/public.js"></script>
        
<img src="http://c.cnzz.com/wapstat.php?siteid=1260962857&amp;r=http%3a%2f%2fwww.87870.com%2f&amp;rnd=2083823114" width="0" height="0">
    </form>


</body></html>