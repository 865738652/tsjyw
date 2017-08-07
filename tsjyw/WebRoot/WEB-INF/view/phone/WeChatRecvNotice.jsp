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
<html xmlns="http://www.w3.org/1999/xhtml" data-dpr="1" style="font-size: 52.0833px;"><head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<meta http-equiv="Cache-control" content="no-cache">
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
<meta name="format-detection" content="telephone=no">

<title>我的通知</title>
<link rel="stylesheet" href="../newphone/askQuestion/css/reset.css" type="text/css" media="all">
<link rel="stylesheet" href="../newphone/askQuestion/css/style.css" type="text/css" media="all">
<link rel="stylesheet" href="../newphone/askQuestion/css/touch.css" type="text/css" media="all">
<script src="../newphone/askQuestion/js/jquery-1.8.3.min.js" type="text/javascript"></script>

<script type="text/javascript">var STYLEID = '5', STATICURL = 'static/', IMGDIR = 'static/image/common', VERHASH = 'Xg7', charset = 'utf-8', discuz_uid = '0', cookiepre = 'kfEE_2132_', cookiedomain = '', cookiepath = '/', showusercard = '1', attackevasive = '0', disallowfloat = 'newthread', creditnotice = '1|威望|,2|金钱|,3|贡献|', defaultstyle = '', REPORTURL = 'aHR0cDovL2Jicy44Nzg3MC5jb20vZm9ydW0ucGhwP21vZD1mb3J1bWRpc3BsYXkmZ2lkPTEmZmlkPTYwJm1vYmlsZT0y', SITEURL = 'http://bbs.87870.com/', JSPATH = 'static/js/';</script>

<script src="../newphone/askQuestion/js/common.js" type="text/javascript" charset="utf-8"></script>

<script src="../newphone/askQuestion/js/adaptive-version2.js" type="text/javascript"></script>
<script src="../newphone/askQuestion/js/index.js" type="text/javascript"></script>
<script>
    window['adaptive'].desinWidth = 720;// 设计图宽度
    window['adaptive'].baseFont = 18; // body 字体大小
    window['adaptive'].init();  // 初始化
</script>

<script typte="text/javascript">
function GetQueryurl(name)
{

     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");

     var r = window.location.search.substr(1).match(reg);

     if(r!=null)return  unescape(r[2]); return null;

}

window.onload= function setNav()
{
//alert(GetQueryurl("gid"));


if(GetQueryurl("mod")=="forum" || GetQueryurl("mod")=="forumlist" || GetQueryurl("mod")=="forumdisplay" || GetQueryurl("mod")=="viewthread")
{
if(GetQueryurl("gid")==1)
{
document.getElementById("mn_F1").className="headTli headlihov";
}
else if(GetQueryurl("gid")==36)
{
document.getElementById("mn_F36").className="headTli headlihov";
}
}
else if(GetQueryurl("mod")=="logging" || GetQueryurl("mod")=="space")
{
document.getElementById("navcenter").className="headTli headlihov";
}
};
</script>

</head>

<body class="bg" style="margin-bottom: 126px; font-size: 18px;">
<!-- top -->
<div class="top">
   <div class="topL">
<div class="topimg"><a href="WeChatNewArticle" class="icon_userinfo">
<img src="../newphone/questionDetial/img/logosmall.png" zsrc="#" style="display: inline; visibility: visible;">
</a></div>
<i class="topL_yuan"></i>
   </div>
   <div class="topM">
   	我的通知
   </div>
</div>
<!-- headT -->




<div class="comtain">

    <div class="threadlist">
        <ul class="listul">
            <c:forEach var="notice" items="${notices}">
	            <li class="listul_li">
	                <a href="LookNotice?noticeId=${notice.noticeId}" style="font-weight: bold;color: #EE1B2E;">
	                    <div class="td-tit listlitop">
	                        <i class="icon icon_top"></i>
	                    		<c:out value="${notice.noticeTitle}"/>                                    
	                    </div>
	                    <div class="td-info cl listlibot">
	                        <span class="list_man">
	                            <img src="../newphone/askQuestion/img/logo.jpg" style="display: inline; visibility: visible;">
	                        </span>
	                        <span class="z ml_5"><c:out value="${notice.noticeAcceptTypeName}"/>&nbsp;-&nbsp;
	                        	<fmt:formatDate value='${notice.noticeTime}' pattern='yyyy-MM-dd'/>
	                        </span>
	                        
	                    </div>
	                </a>
	            </li>
            </c:forEach>
            <%@include file="page.jsp" %>
            
        </ul>
    </div>
    <div style="height:500px;"></div>

<!-- 
<div class="listline plbotbox fixbot">
    <a href="#">
        <div class="listbutton2">
            <i>
                <img src="../newphone/askQuestion/img/fbtl.png" style="display: inline; visibility: visible;">
            </i>
            发送通知
        </div>
    </a>
</div>
 -->

<div class="clear"></div>
</div>
<div class="pullrefresh" style="display:none;"></div>
<div id="mask" style="display:none;"></div>
<!-- footer -->
<footer>
        <div class="footer" id="footer">
<p>
    唐山市睿智文化交流有限公司<br>
     Copyright © 2001-2016 www.tsjtjyw.com All rights reserved.
</p></div>
    </footer>
    
</body></html>