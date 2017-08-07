<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="zh-cmn-Hans">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <title>资讯列表</title>
	<%@include file="WeChatheader.jsp" %>
</head>
<body ontouchstart>

<div>
<%@include file="WeChatTop.jsp" %>
    <div class="page__bd">
        <div class="weui-panel weui-panel_access">
            <div class="weui-panel__hd"><c:out value="${moduleName}"/></div>
            <div class="weui-panel__bd">
            <c:forEach var="article" items="${articles}">
           		<c:choose>
            		<c:when test="${empty article.imagePath}">
            			<!-- 文章中不包含封面图片 -->
            			<div class="weui-media-box weui-media-box_text">
		                    <h4 class="weui-media-box__title"><c:out value="${article.articleTitle}"/></h4>
		                    <p class="weui-media-box__desc"><c:out value="${article.articleAbstract}"/></p>
		                    <ul class="weui-media-box__info">
		                        <li class="weui-media-box__info__meta"><c:out value="${article.userName}"/></li>
		                        <li class="weui-media-box__info__meta"><fmt:formatDate value='${article.articleTime}' pattern='yyyy-MM-dd'/></li>
		                        <li class="weui-media-box__info__meta weui-media-box__info__meta_extra"><a href="WeChatArticleDetial?articleId=${article.articleId}">查看</a></li>
		                    </ul>
		                </div>
            		</c:when>
            		<c:otherwise>
            			<a href="WeChatArticleDetial?articleId=${article.articleId}" class="weui-media-box weui-media-box_appmsg">
		                    <div class="weui-media-box__hd">
		                        <img class="weui-media-box__thumb" src="${article.imagePath}" alt="">
		                    </div>
		                    <div class="weui-media-box__bd">
		                        <h4 class="weui-media-box__title"><c:out value="${article.articleTitle}"/></h4>
		                        <p class="weui-media-box__desc"><c:out value="${article.articleAbstract}"/></p>
		                    </div>
		                </a>
            		</c:otherwise>
            	</c:choose>
            </c:forEach>
            </div>
            <div class="weui-panel__ft">
                <a href="#" class="weui-cell weui-cell_access weui-cell_link">
                    <div class="weui-cell__bd">查看更多</div>
                    <span class="weui-cell__ft"></span>
                </a>    
            </div>
        </div>
    </div>
    <%@include file="WeChatBottom.jsp" %>
</div>
        <!-- @end 活动中心  add by hanjw 20150917-->     

        <script src="../js/wappv.js"></script>   
        <script src="../js/touch.min.js"></script>
        <%@include file="WeChatToolbar.jsp" %>
</body>
</html>
