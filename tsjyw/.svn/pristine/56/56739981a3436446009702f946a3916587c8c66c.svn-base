<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${school.schoolName}</title>
    <link type="text/css" rel="stylesheet" href="../css/style_out.css"/>
    <link type="text/css" rel="stylesheet" href="../css/css.css"/>
    <link type="text/css" rel="stylesheet"  href="../css/tFocus.css">
    <script type="text/javascript" src="../js/jquery-1.11.3.min.js"></script>
    <script type="text/javascript" src="../js/functions.js"></script>
    <style>
    .keImgs li a img {
        width: 307px;
        height: 215px;
    }
    </style>
</head>

<body>
    <%@include file="schoolheader.jsp" %>
    
    <div class="pic">
        <div class="container picbg">
            <div id="playBox">
                <div class="pre"></div>
                <div class="next"></div>
                <div class="smalltitle">
                    <ul>
                    	<c:forEach var="article" items="${topArticles}" begin="0" end="4"> 
                        <li class="thistitle">${article.articleTitle}</li>
                        </c:forEach>
                    </ul>
                </div>
                <ul class="oUlplay">
                	<c:forEach var="article" items="${topArticles}" begin="0" end="4"> 
                    <li><a href="../School/SchoolShow?articleId=${article.articleId}"><img src="${article.imagePath}"></a></li>
                    </c:forEach>                    
                </ul>
            </div>
        </div>
    </div>
    
    <div class="news">
        <div class="space15"></div>
        <div class="left movepic">
            <div class="keChgImg">
                <ul class="keImgs">
                	<c:forEach var="article" items="${picArticles}" begin="0" end="4"> 
                    <li>
                        <a href="../School/SchoolShow?articleId=${article.articleId}" target="_blank"><img src="${article.imagePath}" alt="${article.articleTitle}" /></a>
                    </li>
                    </c:forEach>                    
                </ul>
                <div class="keImgTitleBg"></div>
                <div class="keImgBtn">
                    <ul>
                    	<c:forEach var="article" items="${picArticles}" varStatus="status" begin="0" end="4"> 
                    	<c:if test="${status.index == 0}"><li class="keCor1 keImgCutLi"></c:if>
                        <c:if test="${status.index > 0}"><li class="keCor${status.index+1}"></c:if>
	                        ${status.index + 1}<img src="images/chgSq.gif" width="11" height="3" />
	                    </li>
	                    </c:forEach>
                    </ul>
                </div>
                <div class="keImgTxt">
                    <ul>
                    	<c:forEach var="article" items="${picArticles}" varStatus="status" begin="0" end="4"> 
                    	<c:if test="${status.index == 0}">
                    		<li><a class="cor_blue" href="../School/SchoolShow?articleId=${article.articleId}" target="_blank">${article.articleTitle}</a></li>
                    	</c:if>
                        <c:if test="${status.index > 0}">
                        	<li class="cor_blue"><a class="cor_blue" href="../School/SchoolShow?articleId=${article.articleId}" target="_blank">${article.articleTitle}</a></li>
						</c:if>
                        </c:forEach>
                    </ul>
                </div>
                <img class="keChgBtnL" src="images/chgBtnL.png" width="40" height="40" />
                <img class="keChgBtnR" src="images/chgBtnR.png" width="40" height="40" />
            </div>
        </div>
        <div class="left list1">
            <div class="top">
                	校园新闻
            </div>
            <div class="nwes_border">
                <div class="news_list">
                    <ul>
                    	<c:forEach var="article" items="${xinwen}" varStatus="status" begin="0" end="5"> 
                    	<li><a href="../School/SchoolShow?articleId=${article.articleId}" target="_blank" title="${article.articleTitle}">${article.articleTitle}</a></li>
	                    </c:forEach>
                    </ul>
                </div>
                <div class="list_more">
                    <c:if test="${fn:length(xinwen)>0}"> <a href="../School/SchoolList?moduleId=${xinwen[0].moduleId}">更多</a></c:if>
                </div>
            </div>
        </div>
        <div class="left list2">
            <div class="top">
               	 父母学堂 
            </div>
            <div class="nwes_border">
                <div class="news_list">
                    <ul>
                        <c:forEach var="article" items="${fumu}" varStatus="status" begin="0" end="5"> 
                    	<li><a href="../School/SchoolShow?articleId=${article.articleId}" target="_blank" title="${article.articleTitle}">${article.articleTitle}</a></li>
	                    </c:forEach>
                    </ul>
                </div>
                <div class="list_more">
                    <c:if test="${fn:length(fumu)>0}"> <a href="../School/SchoolList?moduleId=${fumu[0].moduleId}">更多</a></c:if>
                </div>
            </div>
        </div>
        <div class="clear"></div>
    </div>
    
    <div class="news">
        <div class="space15"></div>
        <div class="lunbotop">
            	亲子展示
        </div>
        <div id="qinZi">
            <ul>
			<c:forEach var="article" items="${qinzi}"> 
			<li><a href="../School/SchoolShow?articleId=${article.articleId}" target="_blank" title="${article.articleTitle}">
			<c:if test="${not empty article.imagePath}">
			<img src="${article.imagePath}" alt="">
			</c:if>
			<c:if test="${empty article.imagePath}">
			<img src="../images/1.jpg" alt="">
			</c:if>
            <h3>${article.articleTitle}</h3>
			</a></li>
			</c:forEach>
			<!--
                <li>
                    <a href="###">
                        <img src="../images/1.jpg" alt="">
                        <h3>宣传图片1</h3>
                    </a>
                </li>
                <li>
                    <a href="###">
                        <img src="../images/2.jpg" alt="">
                        <h3>宣传图片2</h3>
                    </a>
                </li>
                <li>
                    <a href="###">
                        <img src="../images/3.jpg" alt="">
                        <h3>宣传图片3</h3>
                    </a>
                </li>
                <li>
                    <a href="###">
                        <img src="../images/4.jpg" alt="">
                        <h3>宣传图片4</h3>
                    </a>
                </li>
			-->
            </ul>
        </div>
    </div>
    
    <div class="news">
        <div class="space15"></div>
        <div class="left list3">
            <div class="top">
               	 家教动态
            </div>
            <div class="nwes_border">
                <div class="left">
                    <div class="news_list">
                        <ul>
                            <c:forEach var="article" items="${jiajiao}" varStatus="status" begin="0" end="5"> 
	                    	<li><a href="../School/SchoolShow?articleId=${article.articleId}" target="_blank" title="${article.articleTitle}">${article.articleTitle}</a></li>
		                    </c:forEach>
                        </ul>
                    </div>
                    <div class="list_more">
                        <c:if test="${fn:length(jiajiao)>0}"> <a href="../School/SchoolList?moduleId=${jiajiao[0].moduleId}">更多</a> </c:if>
                    </div>
                </div>
                <div class="clear"></div>
            </div>
        </div>
        <div class="left list1">
            <div class="top">
                                                         德育天地
            </div>
            <div class="nwes_border">
                <div class="news_list">
                    <ul>
                        <c:forEach var="article" items="${deyu}" varStatus="status" begin="0" end="5"> 
                    	<li><a href="../School/SchoolShow?articleId=${article.articleId}" target="_blank" title="${article.articleTitle}">${article.articleTitle}</a></li>
	                    </c:forEach>
                    </ul>
                </div>
                <div class="list_more">
                    <c:if test="${fn:length(deyu)>0}"> <a href="../School/SchoolList?moduleId=${deyu[0].moduleId}">更多</a></c:if>
                </div>
            </div>
        </div>
        
        <div class="left list5">
            <div class="top">
                                                       心理导航
            </div>
            <div class="nwes_border">
                <div class="news_list">
                    <ul>
                        <c:forEach var="article" items="${xinli}" varStatus="status" begin="0" end="5"> 
                    	<li><a href="../School/SchoolShow?articleId=${article.articleId}" target="_blank" title="${article.articleTitle}">${article.articleTitle}</a></li>
	                    </c:forEach>
                    </ul>
                </div>
                <div class="list_more repair">
                    <c:if test="${fn:length(xinli)>0}"> <a href="../School/SchoolList?moduleId=${xinli[0].moduleId}">更多</a></c:if>
                </div>
            </div>
        </div>
        
        <div class="clear"></div>
    </div>
    <!-- ç¬¬äºè¡åè¡¨ç»æ -->

    <!-- 
    <div class="news">
        <div class="space15"></div>
        <div class="left list3">
            <div class="top">
                å¶ä»åè¡¨
            </div>
            <div class="nwes_border">
                <div class="left">
                    <div class="news_list">
                        <ul>
                            <li><a href="###" target="_blank" title="å­¦çä¿¡æ¯ç½ç»ä¸­å¿">å­¦çä¿¡æ¯ç½ç»ä¸­å¿</a></li>
                            <li><a href="###" target="_blank" title="å¿æ¿èä¹å®¶">å¿æ¿èä¹å®¶</a></li>
                            <li><a href="###" target="_blank" title="åä¸å°±ä¸æå¯¼">åä¸å°±ä¸æå¯¼</a></li>
                            <li><a href="###" target="_blank" title="åå®¢ä¸­å¿">åå®¢ä¸­å¿</a></li>
                            <li><a href="###" target="_blank" title="ä¾æ³æ²»æ ¡">ä¾æ³æ²»æ ¡</a></li>
                            <li><a href="###" target="_blank" title="å³å¿ä¸ä¸ä»£å§åä¼">å³å¿ä¸ä¸ä»£å§åä¼</a></li>
                        </ul>
                    </div>
                    <div class="list_more">
                        &gt;&gt;&gt; <a href="###">æ´å¤</a>
                    </div>
                </div>
                <div class="clear"></div>
            </div>
        </div>
        <div class="left list1">
            <div class="top">
                å¶ä»åè¡¨
            </div>
            <div class="nwes_border">
                <div class="news_list">
                    <ul>
                        <li><a href="###" target="_blank" title="[å¹¿å·æ¥æ¥] 18æå­¦æ ¡å¥éå¹¿ä¸çä¸æµé«èé¢æ ¡å»ºè®¾å¬ç¤ºåå">[å¹¿å·æ¥æ¥] 18æå­¦æ ¡å¥éå¹¿ä¸çä¸æµé«èé¢æ ¡å»ºè®¾å¬ç¤ºåå</a></li>
                        <li><a href="###" target="_blank" title="[åæ¹æ¥æ¥] è¿é«æ ¡ç¨5å¹´æ¶é´è®©ææâè½ä¸è½ä¸â">[åæ¹æ¥æ¥] è¿é«æ ¡ç¨5å¹´æ¶é´è®©ææâè½ä¸è½ä¸â</a></li>
                        <li><a href="###" target="_blank" title="[åæ¹æ¥æ¥] èå¸ä¸åæ¯âéé¥­ç¢âï¼æ·±å³è¿æé«æ ¡è®©ææâè½ä¸è½ä¸â">[åæ¹æ¥æ¥] èå¸ä¸åæ¯âéé¥­ç¢âï¼æ·±å³è¿æé«æ ¡è®©ææâè½ä¸è½ä¸â</a></li>
                        <li><a href="###" target="_blank" title="[åæ¹æ¥æ¥] æ·±å³å¯¹å£å¸®æ¶åå¹³å¿é¦ä¸ªææ¯æè²ç²¾åæ¶è´«é¡¹ç®è½å°">[åæ¹æ¥æ¥] æ·±å³å¯¹å£å¸®æ¶åå¹³å¿é¦ä¸ªææ¯æè²ç²¾åæ¶è´«é¡¹ç®è½å°</a></li>
                        <li><a href="###" target="_blank" title="[æ·±å³åæ¥] æ·±èé¢æ¶è´«éåè´¹å¹è®­">[æ·±å³åæ¥] æ·±èé¢æ¶è´«éåè´¹å¹è®­</a></li>
                        <li><a href="###" target="_blank" title="[å¤å°ç½] å¨å½é«æ ¡åæ°åä¸ æ·±èé¢å¥é50å¼º">[å¤å°ç½] å¨å½é«æ ¡åæ°åä¸ æ·±èé¢å¥é50å¼º</a></li>
                    </ul>
                </div>
                <div class="list_more">
                    >>> <a href="###">æ´å¤</a>
                </div>
            </div>
        </div>
        
        <div class="left list5">
            <div class="top">
                å¶ä»åè¡¨
            </div>
            <div class="nwes_border">
                <div class="news_list">
                    <ul>
                        <li><a href="###" target="_blank" title="[å¹¿å·æ¥æ¥] 18æå­¦æ ¡å¥éå¹¿ä¸çä¸æµé«èé¢æ ¡å»ºè®¾å¬ç¤ºåå">[å¹¿å·æ¥æ¥] 18æå­¦æ ¡å¥éå¹¿ä¸çä¸æµé«èé¢æ ¡å»ºè®¾å¬ç¤ºåå</a></li>
                        <li><a href="###" target="_blank" title="[åæ¹æ¥æ¥] è¿é«æ ¡ç¨5å¹´æ¶é´è®©ææâè½ä¸è½ä¸â">[åæ¹æ¥æ¥] è¿é«æ ¡ç¨5å¹´æ¶é´è®©ææâè½ä¸è½ä¸â</a></li>
                        <li><a href="###" target="_blank" title="[åæ¹æ¥æ¥] èå¸ä¸åæ¯âéé¥­ç¢âï¼æ·±å³è¿æé«æ ¡è®©ææâè½ä¸è½ä¸â">[åæ¹æ¥æ¥] èå¸ä¸åæ¯âéé¥­ç¢âï¼æ·±å³è¿æé«æ ¡è®©ææâè½ä¸è½ä¸â</a></li>
                        <li><a href="###" target="_blank" title="[åæ¹æ¥æ¥] æ·±å³å¯¹å£å¸®æ¶åå¹³å¿é¦ä¸ªææ¯æè²ç²¾åæ¶è´«é¡¹ç®è½å°">[åæ¹æ¥æ¥] æ·±å³å¯¹å£å¸®æ¶åå¹³å¿é¦ä¸ªææ¯æè²ç²¾åæ¶è´«é¡¹ç®è½å°</a></li>
                        <li><a href="###" target="_blank" title="[æ·±å³åæ¥] æ·±èé¢æ¶è´«éåè´¹å¹è®­">[æ·±å³åæ¥] æ·±èé¢æ¶è´«éåè´¹å¹è®­</a></li>
                        <li><a href="###" target="_blank" title="[å¤å°ç½] å¨å½é«æ ¡åæ°åä¸ æ·±èé¢å¥é50å¼º">[å¤å°ç½] å¨å½é«æ ¡åæ°åä¸ æ·±èé¢å¥é50å¼º</a></li>
                    </ul>
                </div>
                <div class="list_more repair">
                    >>> <a href="###">æ´å¤</a>
                </div>
            </div>
        </div>
        
        <div class="clear"></div>
        <div class="space15"></div>
    </div>
    -->

    <!-- åºé¨å¼å§ -->
    <div class="foot">
        <div class="container">
            <div class="left foot_describe">
                <ul>
                    <li>Copyright &copy; 2010-2016 益铭科技. All Rights Reserved</li>
                </ul>
            </div>
            <div class="clear"></div>
        </div>
    </div>
    <!-- åºé¨ç»æ -->
</body>

</html>
