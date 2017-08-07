<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
	
	<!-- video -->
	<link href="../phone/css/video-js.min.css" rel="stylesheet">
	<script src="../phone/js/video.min.js"></script>
	<!--  -->
	
    <title>文章详情</title>
    
    
    <style type="text/css">
	    .main img { 
		clear: both; 
		display: block; 
		margin:auto; 
		width:100%;
		height:100%;
		} 
    </style>
    
  </head>
  	
  <body>
<!-- 头部  -->      
         <header class="header-index">
            <div class="header-user">
                <span class="logo">
                    <img style="width:100px;position: absolute;left:10px;top:10px;" src="../images/logol.png"  />
                </span>
                <span class="channel"></span>
            </div>
        </header>
        
        <div class="article-1">
	        <h1 class="h1">${article.articleTitle}</h1>
	        <p class="info">
	        	<a href="javascript:void(0)#comment" class="num">${article.articleReadCount}</a>
	        	<fmt:formatDate value='${article.articleTime}' pattern='yyyy-MM-dd'/>
	        	<span class="name">作者:${article.userName}&nbsp;</span></p>
	        <div class="main" id="main">
	        	${article.articleAbstract}<br/>
	       		${article.articleContent}<br/>
	       		<c:if test="${not empty images}">
	       			<c:forEach var="image" items="${images}">
	       				<img src="${image.attachUrl}" style="width:100%"/><br/>
	       				<br/><br/>
	       			</c:forEach>
	       		</c:if>
	       		<c:if test="${not empty videos}">
				    <c:forEach var="rv" items="${videos}">
		                    <p style="text-align: center;">
		                    	<c:if test="${fn:contains(rv.attachUrl, '../upload/')}">
		                    	<video width="200" height="300" controls="controls">
		                    		<source src="${rv.attachUrl}" type="video/mp4">
		                    	</video>
		                    	</c:if>
		                    	<c:if test="${fn:contains(rv.attachUrl, 'http://')}">
		                    	<embed type="application/x-shockwave-flash" class="edui-faked-video" pluginspage="http://www.macromedia.com/go/getflashplayer" src="${rv.attachUrl}" width="200r" height="440" wmode="transparent" play="true" loop="false" menu="false" allowscriptaccess="never" allowfullscreen="true"/>
		                    	</c:if>
		                    </p>
		               		
		               		<!-- video -->
		               		 <video id="example_video_1" class="video-js vjs-default-skin" controls preload="none" width="640" height="264"
							      poster="http://video-js.zencoder.com/oceans-clip.png"
							      data-setup="{}">
							    
							    <video width="200" height="300" controls="controls">
		                    		<source src="${rv.attachUrl}" type="video/mp4">
		                    	</video>  
							      
							    <source src="http://vjs.zencdn.net/v/oceans.mp4" type='video/mp4' />
							    <source src="http://vjs.zencdn.net/v/oceans.webm" type='video/webm' />
							    <source src="http://vjs.zencdn.net/v/oceans.ogv" type='video/ogg' />
							    <track kind="captions" src="demo.captions.vtt" srclang="en" label="English"></track><!-- Tracks need an ending tag thanks to IE9 -->
							    <track kind="subtitles" src="demo.captions.vtt" srclang="en" label="English"></track><!-- Tracks need an ending tag thanks to IE9 -->
							</video>     
		                    <!--  -->
		               
				    </c:forEach>
		    	</c:if>
	       	</div>
	    </div>
        <div class="hot-news activity-center-section">
            <a href="javascript:void(0)http://huodong.fengniao.com/apply-list-wap.html" class="more">查看更多文章</a>
            <p class="info">唐山家庭教育网</p>
        </div>
        <!-- @end 活动中心  add by hanjw 20150917-->     

        <script src="../js/wappv.js"></script>   
        <script src="../js/touch.min.js"></script>
        <%@include file="WeChatToolbar.jsp" %>
</body>
</html>