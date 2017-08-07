<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="baidu-site-verification" content="9C9eVfwjyt">
	<title>
		唐山家庭教育网文章阅读
	</title>
	<meta name="keywords" content="唐山家庭教育网">
	<meta name="description" content="唐山家庭教育网">
	<link href="../css/style.css" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="../css/news.css">
    <script charset="utf-8" src="../js/v.js"></script>
	<script src="../js/hm.js"></script>
	<script src="../js/hm(1).js"></script>
	<script type="text/javascript" src="../js/jquery.js"></script>
    <script src="../js/loadcommon.js" type="text/javascript"></script>
    <script src="../js/lazyloadimg.js"></script>
	<script src="../js/rightCommon.js" type="text/javascript"></script>    
</head>
<body class="newbodybg">
    <input type="hidden" id="columnindex" value="1">
    <input type="hidden" id="hiddenurls" value="http://wap.87870.com/xinwen-0103.html">
    <script type="text/javascript">
        var wapurl = "http://wap.87870.com/";
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
    <div id="header"><%@include file="header.jsp" %></div>

    <div class="gamebox clearfix">
    
    <input type="hidden" id="hiddencid" value="${curSubModule.moduleId}">
    
	<div class="news_nei">
		<div class="new_banner"></div>
            <div class="g_left" style="min-height:885px;">
                <div class="newstop">
                	<a href="../Index/ShowModule?moduleId=${curTopModule.moduleId}" data-id="${curTopModule.moduleId}">最新资讯</a>
                	<c:if test="${not empty curTopModule.childrenModule}"> 
		 				<c:forEach var="child" items="${curTopModule.childrenModule}"> 
		 					<c:if test="${not empty curSubModule && child.moduleId == curSubModule.moduleId}">
		 						<a href="../Index/ShowModule?moduleId=${child.moduleId}" data-id="child.moduleId" class="newtophov">${child.moduleName}</a>
		 					</c:if>
		 					<c:if test="${empty curSubModule || child.moduleId != curSubModule.moduleId}">
		 						<a href="../Index/ShowModule?moduleId=${child.moduleId}" data-id="child.moduleId" >${child.moduleName}</a>
		 					</c:if>
		 				</c:forEach>
		 			</c:if>
                </div>
                <div class="news_botword">
					<div style="text-align:center; width:100%; margin-top:20px;">加载中...</div>
				</div>
                <div class="newspage" id="morelite" style="display: none;">数据加载中...</div>
                <div class="newspage" id="newspage">
				</div>
                <input type="hidden" id="TotalCount" value="0">
                <input type="hidden" id="PageSize" value="8">
                <input type="hidden" id="PageCount" value="1">
                <input type="hidden" id="PageIndex" value="1">
                
                <input type="hidden" id="hiddenorderby" value="1">
            </div>

            <script src="../js/cidlist.js" type="text/javascript"></script>
            <div class="g_right">
                <!--å¹¿åä½-->
                <div class="gr_box">
                    <div class="news_bannimg">
                        <a href="http://z.jd.com/project_details.action?projectId=63609" target="_blank" title="åæå°¼">
							<img src="../images/db7bd143-6385-47ba-8f8a-1ae94a7bcd2f.jpg">
						</a>
                    </div>
                </div>
                <!--ç­ç¹æ°é»æè¡-->
                <div style="width:280px">
                    <div class="video-ranking">
                        <h2 class="v-h2">热点新闻</h2>
                        <ul class="v-ranking">
							
						</ul>
                    </div>
                </div>
            </div>
            <div class="g_lbanner" style="display: none;"></div>            
		</div>
    </div>
    <!--bot-->
    <script src="../js/hotnewslist.js" type="text/javascript"></script>
    <%@include file="footer.jsp" %>
</body>
</html>