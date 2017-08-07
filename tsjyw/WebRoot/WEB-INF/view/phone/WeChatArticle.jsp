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
    <title>首页</title>
	<%@include file="WeChatheader.jsp" %>

	
</head>
<body ontouchstart>
<div>
	<%@include file="WeChatTop.jsp" %>
    <div class="page__bd">
        <div class="weui-panel weui-panel_access">
            <div class="weui-panel__hd">今日热点</div>
            <div class="weui-panel__bd">
            <c:forEach var="article" items="${articleToday}">
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
                <a href="ShowModule?moduleId=35" class="weui-cell weui-cell_access weui-cell_link">
                    <div class="weui-cell__bd">查看更多</div>
                    <span class="weui-cell__ft"></span>
                </a>    
            </div>
        </div>
        
        <div class="weui-panel weui-panel_access">
            <div class="weui-panel__hd">精彩活动</div>
            <div class="weui-panel__bd">
            <c:forEach var="article" items="${articleParty}">
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
                <a href="ShowModule?moduleId=6" class="weui-cell weui-cell_access weui-cell_link">
                    <div class="weui-cell__bd">查看更多</div>
                    <span class="weui-cell__ft"></span>
                </a>    
            </div>
        </div>
        
        <!-- 活动日历 -->
		<div class="weui-panel weui-panel_access">
            <div class="weui-panel__hd">活动日历</div>
            <div class="weui-panel__bd">
            <c:forEach var="article" items="${articleCalender}">
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
                <a href="ShowModule?moduleId=36" class="weui-cell weui-cell_access weui-cell_link">
                    <div class="weui-cell__bd">查看更多</div>
                    <span class="weui-cell__ft"></span>
                </a>    
            </div>
        </div>
        
        <!-- 校园之窗-->
		<div class="weui-panel weui-panel_access">
            <div class="weui-panel__hd">校园之窗</div>
            <div class="weui-panel__bd">
            <c:forEach var="article" items="${articleSchool}">
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
                <a href="ShowModule?moduleId=34" class="weui-cell weui-cell_access weui-cell_link">
                    <div class="weui-cell__bd">查看更多</div>
                    <span class="weui-cell__ft"></span>
                </a>    
            </div>
        </div>

        <!-- 文章列表-->
		<div class="weui-panel weui-panel_access">
            <div class="weui-panel__hd">文章列表</div>
            <div class="weui-panel__bd">
            <c:forEach var="article" items="${articleRecommend}">
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
                <a href="ShowModule?moduleId=all" class="weui-cell weui-cell_access weui-cell_link">
                    <div class="weui-cell__bd">查看更多</div>
                    <span class="weui-cell__ft"></span>
                </a>    
            </div>
        </div>


        <!-- 法制安全-->
		<div class="weui-panel weui-panel_access">
            <div class="weui-panel__hd">法制安全</div>
            <div class="weui-panel__bd">
            <c:forEach var="article" items="${articleSafety}">
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
                <a href="ShowModule?moduleId=7" class="weui-cell weui-cell_access weui-cell_link">
                    <div class="weui-cell__bd">查看更多</div>
                    <span class="weui-cell__ft"></span>
                </a>    
            </div>
        </div>
    </div>
    <%@include file="WeChatBottom.jsp" %>
</div>
</body>
</html>