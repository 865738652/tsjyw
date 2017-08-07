<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>唐山家庭教育网</title>
	<meta name="keywords" content="家长学校,心理健康,青少年成长" />
	<meta name="description" content="唐山家庭教育网" />
	<link href="../css/style.css" rel="stylesheet" />
	<link href="../css/news.css" rel="stylesheet" />
	<link rel="stylesheet" type="text/css" href="../css/video.css" />
	<script type="text/javascript" src="../js/jquery.js"></script>
    <script type='text/javascript' src="../js/rightCommon.js" ></script>
    <script type='text/javascript' src="../js/lazyloadimg.js"></script>

</head>
<body class="video-body">
    <div id="header" ><%@include file="header.jsp" %></div>
        
    <input type="hidden" id="TotalCount" value="0"/>
    <input type="hidden" id="PageSize" value="12" />
    <input type="hidden" id="PageCount" value="1" />
    <input type="hidden" id="PageIndex" value="1" />
    <input type="hidden" id="hiddencid" value="${curSubModule.moduleId}" />
    <input type="hidden" id="hiddenorderby" value="1" />
    <div class="video-content clearfix">
        <div class="phbann">
         
        </div> 
        <div class="arrow">
	        <a href="../Index/">首页</a> &gt; 
			<a href="../Index/ShowModule?moduleId=${curTopModule.moduleId}">${curTopModule.moduleName}</a> 
			<c:if test="${curTopModule.moduleId != curSubModule.moduleId}">
				 &gt;<a href="../Index/ShowModule?moduleId=${curSubModule.moduleId}">${curSubModule.moduleName}</a>
			</c:if>	
        </div>
        <div class="video-wrap">
            <div class="video-tab">
            	<c:if test="${curTopModule.moduleId == curSubModule.moduleId}">
				<a href="../Index/ShowModule?moduleId=${curTopModule.moduleId}" data-id="${curTopModule.moduleId}" class="cur">${curTopModule.moduleName}</a>
				</c:if>
				<c:if test="${curTopModule.moduleId != curSubModule.moduleId}">
				<a href="../Index/ShowModule?moduleId=${curTopModule.moduleId}" data-id="${curTopModule.moduleId}">${curTopModule.moduleName}</a>
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
            <div class="pageNav" id="newspage">
               
            </div>

        </div>
        <div class="video-aside">
            <!--广告位-->
             <div class="gr_box">
                 <div class="news_bannimg">
                      
                 </div>
             </div>
            <div style="width:280px">
            <div class="video-ranking">
                <h2 class="v-h2">热点新闻</h2>
                <ul class="v-ranking">
                    
                </ul>
            </div>
                </div>
        </div>
    </div>
    <script src="../js/loadcommon.js" type="text/javascript"></script>
    <script src="../js/hdlist.js" type="text/javascript"></script>


    <script src="../js/hotnewslist.js" type='text/javascript'></script>
    <div id="footer" class="mt20"></div>
    <script src="../js/LoadFooter.js"></script>
    <script src="../js/loadpublicfun.js"></script>
    <div style="display:none;">
        <script src="http://s4.cnzz.com/z_stat.php?id=1255577050&web_id=1255577050" language="JavaScript"></script>
        <script>
            var _hmt = _hmt || [];
            (function () {
                var hm = document.createElement("script");
                hm.src = "//hm.baidu.com/hm.js?b2490e8ccebe71a9267da7236b919088";
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
    
</body>
</html>

