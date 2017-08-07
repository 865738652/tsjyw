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
    <title>文章详情</title>
    <%@include file="WeChatheader.jsp" %>
</head>
<body ontouchstart>
<div>
	<%@include file="WeChatTop.jsp" %>
    <div class="page__hd">
        <h1 class="page__title"><c:out value="${article.articleTitle}"/></h1>
    </div>
    <div class="page__bd">
        <article class="weui-article">
            <section>
                <h2 class="title"><c:out value="${article.articleTitle}"/></h2>
                <section>
                    <h3>文章摘要</h3>
                    <p><c:out value="${article.articleAbstract}"/></p>
                </section>
                <section>
                	<h3>文章内容</h3>
                    <p><c:out value="${article.articleContent}"/></p>
            	</section>
        	</section>       
    	</article>
    </div>
    <%@include file="WeChatBottom.jsp" %>
</div>
</body>
</html>