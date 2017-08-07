<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<!-- saved from url=(0084)http://bbs.87870.com/forum.php?mod=viewthread&gid=1&tid=2682&extra=page%3D1&mobile=2 -->
<html xmlns="http://www.w3.org/1999/xhtml" data-dpr="1" style="font-size: 52.0833px;"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<meta http-equiv="Cache-control" content="no-cache">
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
<meta name="format-detection" content="telephone=no">
<title>问题详情</title>
<link rel="stylesheet" href="../newphone/questionDetial/css/reset.css" type="text/css" media="all">
<link rel="stylesheet" href="../newphone/questionDetial/css/style.css" type="text/css" media="all">
<link rel="stylesheet" href="../newphone/questionDetial/css/touch.css" type="text/css" media="all">
<script src="../newphone/questionDetial/js/jquery-1.8.3.min.js" type="text/javascript"></script>

<script type="text/javascript">var STYLEID = '5', STATICURL = 'static/', IMGDIR = 'static/image/common', VERHASH = 'Xg7', charset = 'utf-8', discuz_uid = '0', cookiepre = 'kfEE_2132_', cookiedomain = '', cookiepath = '/', showusercard = '1', attackevasive = '0', disallowfloat = 'newthread', creditnotice = '1|威望|,2|金钱|,3|贡献|', defaultstyle = '', REPORTURL = 'aHR0cDovL2Jicy44Nzg3MC5jb20vZm9ydW0ucGhwP21vZD12aWV3dGhyZWFkJmdpZD0xJnRpZD0yNjgyJmV4dHJhPXBhZ2UlM0QxJm1vYmlsZT0y', SITEURL = 'http://bbs.87870.com/', JSPATH = 'static/js/';</script>

<script src="../newphone/questionDetial/js/common.js" type="text/javascript" charset="utf-8"></script>

<script src="../newphone/questionDetial/js/adaptive-version2.js" type="text/javascript"></script>
<script src="../newphone/questionDetial/js/index.js" type="text/javascript"></script>
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

	<style type="text/css">
	    .comwordP img { 
		clear: both; 
		display: block; 
		margin:auto; 
		width:100%;
		height:100%;
		} 
    </style>

</head>

<body class="bg" style="margin-bottom: 151px; font-size: 18px;">
<!-- top -->
<div class="top">
   <div class="topL">
<div class="topimg"><a href="WeChatNewArticle" class="icon_userinfo">
	<img src="../newphone/questionDetial/img/logosmall.png">
</a></div>
<i class="topL_yuan"></i>
   </div>
   <div class="topM">
   	问答
   </div>
</div>
<!-- headT -->
<div class="headT">
  <div id="mn_F1" class="headTli">
    <a href="WeChatNewHotAskQuestion?pno=1">常见问题</a>
  </div>
<div id="mn_F36" class="headTli">
<a href="WeChatNewAllAskQuestion?pno=1">全部问题</a>
</div>
   <div id="navcenter" class="headTli">
      <a href="WeChatNewMyAskQuestion?pno=1">我的问题</a>
   </div>
</div>

<!-- main postlist start -->

<div class="comtain">
<div class="topnav">
    	<a href="WeChatNewArticle">首页</a> <em>›</em> 
      <a href="WeChatNewHotAskQuestion?pno=1">问答</a> <em>›</em> 问题详情
    </div>
<div class="comword cl">
    <div class="display pi">
	<div class="viewimg">
		<c:choose>
			<c:when test="${not empty askQuestion.askUserPhotoUrl}">
				<img src="${askQuestion.askUserPhotoUrl}" style="display: inline; visibility: visible;"></div>
			</c:when>
			<c:otherwise>
				<img src="../newphone/questionDetial/img/kongbai.png" style="display: inline; visibility: visible;"></div>
			</c:otherwise>
		</c:choose>
<div class="viewtitR">
<div class="viewtitle">
	<c:out value="${askQuestion.askQuestionTitle}"/>
</div>
<div class="viewtime">
<span>
<a href="#" class="blue"><c:out value="${askQuestion.askName}"/></a>
</span><fmt:formatDate value='${askQuestion.askQuestionTime}' pattern='yyyy-MM-dd'/>
</div>
</div>
</div>	 
<div class="comwordP ">
    <c:out value="${askQuestion.askQuestionContent}" escapeXml="false"/><br/>
    <c:if test="${not empty attachments_img}">
    	<c:forEach var="attachVO" items="${attachments_img}">
    		<img src="${attachVO.attachmentUrl}"/><br/>
    	</c:forEach>
    </c:if>
    <c:if test="${not empty attachments_other}">
    	<c:forEach var="attachVO" items="${attachments_other}">
    		<a href="Wechatdownload?attachmentId=${attachVO.attachmentId}"><c:out value="${attachVO.attachmentName}"/>[点击下载]</a><br/>
    	</c:forEach>
    </c:if>
</div>
</div>
<div class="pltop">所有的回答</div>

<c:if test="${not empty responses}">  
	<c:forEach var="response" items="${responses}">
		<div class=" comPLwordli cl">
		  <div class="display pi" href="#replybtn_35904">
		<div class="plimg">
		<c:choose>
			<c:when test="${not empty response.userPhotoUrl}">
				<img src="${response.userPhotoUrl}" style="display: inline; visibility: visible;">
			</c:when>
			<c:otherwise>
				<img src="../newphone/questionDetial/img/logosmall.png" style="display: inline; visibility: visible;">
			</c:otherwise>
		</c:choose>
		</div>
		<div class="plrp">
		<div class="viewtitle">
		</div>
		<div class="plrpT">
		<div class="plrpt1">
		<span>
		<a href="#" class="blue"><c:out value="${response.userName}"/></a>
		</span>
		</div>
		<div class="plrpt2">详情</div>
		</div>
		<div class="plrpB"><fmt:formatDate value='${response.respQuestionTime}' pattern='yyyy-MM-dd'/></div>
		</div>
		</div>	 
		<div class="plword ">
			<c:out value="${response.respQuestionContent}" escapeXml="false"/><br/>
			<!-- 
			<c:if test="${not empty response.attachments}">
				<c:forEach var="attachment" items="${response.attachments}">
					<c:if test="${attachment.attachTypeName == 'IMG'}">
						<img src="${attachment.attachUrl}" ><br/> 
					</c:if>
				</c:forEach>
			</c:if>
			 -->
			<c:if test="${not empty response.attachments}">
				<c:forEach var="attachment" items="${response.attachments}">
					
					<a href="Wechatdownload?attachmentId=${attachment.attachId}"><c:out value="${attachment.attachName}"/>[点击下载]</a><br/> 
					
				</c:forEach>
			</c:if>
		</div>
		</div>
	</c:forEach>
</c:if>        
<c:if test="${empty responses}">
	<div class=" comPLwordli cl" id="pid34049">
		<div class="display pi" href="#replybtn_34049">
			<div class="plrp">
				<div class="plrpB">还没有人回答</div>
			</div>
		</div>	 
	</div>
</c:if>
            
<!-- 
<div class=" comPLwordli cl" id="pid34049">
        <div class="display pi" href="#replybtn_34049">
<div class="plimg"><img src="../newphone/questionDetial/img/avatar(9).php" style="display: inline; visibility: visible;"></div>
<div class="plrp">
<div class="viewtitle">
</div>
<div class="plrpT">
<div class="plrpt1">
<span>
<a href="#" class="blue">qq2062475689</a>
</span>
(新手上路)</div>
<div class="plrpt2">5<sup>#</sup></div></div>
<div class="plrpB">2015-11-21 14:45:16</div>
</div>
</div>	 
<div class="plword ">
刚开始，有点不晕，看着看着好了，习惯了，大概就是这样。</div>
</div>
            <div id="post_new">

</div>
 -->
 <!--  -->
<c:choose>
	<c:when test="${not empty IsOrNotAskQuestion}">
		<div class="listline plbotbox fixbot">
		    <a href="WeChatResponseQuestion?askQuestionId=${askQuestion.askQuestionId}">
		        <div class="listbutton2">
		            <i>
		                <img src="../newphone/askQuestion/img/fbtl.png" style="display: inline; visibility: visible;">
		            </i>
		            我要回答
		        </div>
		    </a>
		</div>
	</c:when>
	<c:otherwise>
		<div class="listline plbotbox fixbot">
	    <a href="WeChatAskQuestion?respUserId=all">
	        <div class="listbutton2">
	            <i>
	                <img src="../newphone/askQuestion/img/fbtl.png" style="display: inline; visibility: visible;">
	            </i>
	            我要提问
	        </div>
	    </a>
</div>
	</c:otherwise>
</c:choose>


<!-- main postlist end -->


</div>

<script type="text/javascript">
$('.favbtn').on('click', function() {
var obj = $(this);
$.ajax({
type:'POST',
url:obj.attr('href') + '&handlekey=favbtn&inajax=1',
data:{'favoritesubmit':'true', 'formhash':'51bf5173'},
dataType:'xml',
})
.success(function(s) {
popup.open(s.lastChild.firstChild.nodeValue);
evalscript(s.lastChild.firstChild.nodeValue);
})
.error(function() {
window.location.href = obj.attr('href');
popup.close();
});
return false;
});
</script>

<div class="clear"></div>
<a href="javascript:;" title="返回顶部" class="scrolltop bottom" style="opacity: 0.618; bottom: 8px;"></a>
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
