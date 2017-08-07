<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>${article.articleTitle} - 唐山家庭教育网</title>
    <link rel="stylesheet" type="text/css" href="../css/content.css">
    <script type="text/javascript" src="../js/jquery-1.11.3.min.js"></script>
    <style>
    .keImgs li a img {
        width: 307px;
        height: 215px;
    }
    </style>
</head>

<body>
    <%@include file="schoolheader.jsp" %>
    <div id="bg">
        <script language="javascript">
        var div = document.getElementById('bg');
        div.className = 'bg1162';
        </script>
        <div class="container">
            <div class="space50"></div>
            <div class="list">
                <div class="current_location">
                    <a href="../School/${school.schoolId}">首页</a> >> <a href="../School/SchoolList?moduleId=${menu.moduleId}">${module.moduleName}</a>
                </div>
                <div class="list_border">
                    <div class="list_channel">
                        ${module.moduleName}
                    </div>
                    <div class="news_list">
                        <div class="space15"></div>
                        <!-- æç« åå®¹å¼å§ -->
                        <div class="title">${article.articleTitle}</div>
                        <div class="info">
                            <span>文章来源: ${school.schoolName} </span>
                            <span>2016-09-06</span>
                        </div>
                        <div class="content">
                            ${article.articleContent}
                        </div>
                        <!-- æç« åå®¹ç»æ -->
                    </div>
                </div>
            </div>
            <div class="clear"></div>
            <div class="space15"></div>
        </div>
    </div>
    <!-- åé¢ç»æ -->
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
