<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>



<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="baidu-site-verification" content="VYkItiSaIv" />
	<title>唐山家庭教育网</title>
	<meta name="keywords" content="唐山家庭教育网视频,心理讲座" />
	<meta name="description" content="唐山家庭教育网。" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <script type="text/javascript" src="../js/jquery.js"></script>
    <link href="../css/style.css" rel="stylesheet" />
    <script src="../js/lazyloadimg.js?v=1.0.0"></script>
    <script src="../js/video.js"></script>
    <link href="../css/video.css" rel="stylesheet"/>
        
</head>
<body class="video-body">
    <input type="hidden" id="columnindex" value="${article.moduleId}" />
    <input type="hidden" id="hiddenurls" value="www.tsjtjyw.com" />
    <script type="text/javascript">
        var wapurl = "http://www.tsjtjyw.com/";
        if ($("#hiddenurls").length > 0) {
            if ($("#hiddenurls").val().length > 1) {
                wapurl = $("#hiddenurls").val();
            }
        }
        if (/(iPhone|iPod|iOS)/i.test(navigator.userAgent)) {
            window.location.href = wapurl;
        } else if (/(Android)/i.test(navigator.userAgent)) {
            window.location.href = wapurl;
        }
    </script>
    <div id="header" ><%@include file="header.jsp" %></div>
    <input type="hidden" id="TotalCount" value="0"/>
    <input type="hidden" id="PageSize" value="12" />
    <input type="hidden" id="PageCount" value="1" />
    <input type="hidden" id="PageIndex" value="1" />
    <input type="hidden" id="hiddencid" value="${curSubModule.moduleId}" />
    <input type="hidden" id="hiddenorderby" value="1" />
    <div class="mb30 clearfix ad_list">
    	<c:if test="${not empty hotVideo}"> 
			<c:forEach var="hv" items="${hotVideo}"> 
		<a title="${hv.articleTitle}" href="../Index/ShowArticle?articleId=${hv.articleId}" class="grid4" target="_blank">
    		<img width="570" height="407" src="${hv.imagePath}" alt="${hv.articleTitle}" class="grid4-img" />
    		<div class="grid4-opaticy"></div>
    		<div class="grid4-titWrap">${hv.articleTitle}</div>
    		<div class="grid4-desWrap">
    			<p class="grid4-tit">${hv.articleTitle}</p>
        		<p class="grid4-des"></p>
        		<span class="grid4-icon"></span>
    		</div>
		</a>
			</c:forEach>
		</c:if>
        
	</div>
    <div class="video-content clearfix">
        <div class="video-wrap">
            <div class="video-tab">
            	<c:if test="${curTopModule.moduleId == curSubModule.moduleId}">
				<a href="../Index/ShowModule?moduleId=${curTopModule.moduleId}" data-id="${curTopModule.moduleId}" class="cur">全部</a>
				</c:if>
				<c:if test="${curTopModule.moduleId != curSubModule.moduleId}">
				<a href="../Index/ShowModule?moduleId=${curTopModule.moduleId}" data-id="${curTopModule.moduleId}">全部</a>
				</c:if>
               	<c:if test="${not empty curTopModule.childrenModule}"> 
	 				<c:forEach var="child" items="${curTopModule.childrenModule}"> 
	 					<c:if test="${not empty curSubModule && child.moduleId == curSubModule.moduleId}">
	 						<a href="../Index/ShowModule?moduleId=${child.moduleId}" data-id="child.moduleId" class="cur">${child.moduleName}</a>
	 					</c:if>
	 					<c:if test="${empty curSubModule || child.moduleId != curSubModule.moduleId}">
	 						<a href="../Index/ShowModule?moduleId=${child.moduleId}" data-id="child.moduleId" >${child.moduleName}</a>
	 					</c:if>
	 				</c:forEach>
	 			</c:if>       
             
            </div>
           
            <ul class="video-list clearfix">
            <!-- 12条 -->
            
            </ul>
            
            
<!-- AspNetPager 7.5 Copyright:2003-2015 Webdiyer (www.webdiyer.com) -->
<div id="ContentPlaceHolder1_AspNetPager1" class="newspage" style="text-align:center;">
	
</div>
<!-- AspNetPager 7.5 Copyright:2003-2015 Webdiyer (www.webdiyer.com) -->


        </div>
        <div class="video-aside">
            <div class="ranking_list"></div>
             <div class="video-ranking">
    <h2 class="v-h2">视频排行榜</h2>
    <ul class="v-ranking">
        <c:if test="${not empty hotVideo}"> 
			<c:forEach var="hv" items="${hotVideo}" begin="0" end="5" varStatus="status">
				<a class="v-rLink  v-rLinkb" href="../Index/ShowArticle?articleId=${hv.articleId}" target="_blank">
					<c:if test="${status.index<3}"> 
	        		<span class="v-rTop v-rTop3">${status.index+1}</span>
	        		</c:if>
	        		<c:if test="${status.index>=3}"> 
	        		<span class="v-rTop">${status.index+1}</span>
	        		</c:if>
	        		<span class="v-rTxt">${hv.articleId}</span>
	        		<div class="v-rImg" style="display: block;">
	        			<img width="250" height="154"  src="${hv.imagePath}"  alt="${hv.articleTitle}"/>
	        		</div>
	        	</a>
			</c:forEach>
		</c:if>
    </ul>
</div>
<script type="text/javascript">
    jQuery(function ($) {
    	$('.v-ranking li').mouseover(function () { 
       		var $this = $(this);
       		$this.find('.v-rLink').addClass('v-rLinkb');
       		$this.find('.v-rImg').show();
       		$this.siblings('li').find('.v-rLink').removeClass('v-rLinkb');
       		$this.siblings('li').find('.v-rImg').hide();
       	});
        $('.video-ranking').anchor();
    });
    </script>
        </div>
    </div>
     <script>
         $(document).ready(function () {
             var cid = $("#hiddencid").val();
             $(".video-tab a").removeClass("cur");
             $(".video-tab a").each(function () {
                 if ($(this).attr("data-id") == cid) {
                     $(this).addClass("cur");
                 }
             });
         });
            </script>
     
	<script src="../js/loadcommon.js" type="text/javascript"></script>
    <script src="../js/vdlist.js" type="text/javascript"></script>
    
    <%@include file="footer.jsp" %> 
    
    <!-- <script src="http://www.87870.com/2016/js/LoadFooter.js"></script> -->
    <script src="../js/loadpublicfun.js"></script>
    
    <div class="displayN">
        <script>
            var _hmt = _hmt || [];
            (function () {
                var hm = document.createElement("script");
                hm.src = "//hm.baidu.com/hm.js?d913c25b7129c7ba3226975f9ea2dc18";
                var s = document.getElementsByTagName("script")[0];
                s.parentNode.insertBefore(hm, s);
            })();
        </script>
       <script src="http://s4.cnzz.com/z_stat.php?id=1256099334&web_id=1256099334" language="JavaScript"></script>
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
</body>
</html>
