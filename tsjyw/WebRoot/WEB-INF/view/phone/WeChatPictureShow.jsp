<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

		
<header>
	<!-- 首页蓝条
	<div class="container_12">
		<div class="grid_2 suffix_10"><a href="http://excolo.github.io/Excolo-Slider/" title="Click here to go to the GitHub page for this plugin"><img alt="Slider logo" src="../phone/image/picture_logo.png" /></a></div>
	</div>
	 -->
</header>

<div class="home-device" ><a class="arrow-left" href="#"></a> <a class="arrow-right" href="#"></a>
    <div class="swiper-main" >
      <div class="swiper-container swiper1" >
        <div class="swiper-wrapper" id="zhangtao">
          <c:forEach var="article" items="${articleSilde}">
           		<c:if test="${not empty article.imagePath}">
           			<div class="swiper-slide" >  <a href="WeChatArticleDetial?articleId=${article.articleId}"><img class="swiper-img" src="${article.imagePath}" > </a></div>   
					
           			<!-- <a href="WeChatArticleDetial?articleId=${article.articleId}"><img class="swiper-img" src="${article.imagePath}" > </a> -->        			
            	</c:if>
          </c:forEach>
          <input type="hidden" value="1" id="pictureIndex"/>
          <input type="hidden" value="10" id="pictureCount"/>
        </div>
      </div>
    </div>
    <div class="pagination pagination1"></div>
</div>

<%-- <div class="h20"></div>
	<div class="grid_8" style="width:100%">
		<div id="sliderA" class="slider">
			<c:forEach var="article" items="${articleSilde}">
           		<c:if test="${not empty article.imagePath}">
           			<a href="WeChatArticleDetial?articleId=${article.articleId}"><img src="${article.imagePath}" /></a>
            	</c:if>	
            </c:forEach>
		</div>	
		<div class="h10"></div>		
	</div> --%>
	
<link rel="stylesheet" href='<c:url value="/phone/css/idangerous.swiper.css"/>'>
<link rel="stylesheet" href='<c:url value="/phone/css/swiper-style.css"/>'>
<link rel="stylesheet" href='<c:url value="/phone/css/swiper-demos.css"/>'>
<script src="http://www.lanrenzhijia.com/ajaxjs/jquery.min.js"></script>
<script src='<c:url value="/phone/js/idangerous.swiper-1.9.1.min.js"/>'></script>
<script src='<c:url value="/phone/js/idangerous.swiper.scrollbar-1.2.js"/>'></script>
<script src='<c:url value="/phone/js/swiper-demos.js"/>'></script>

<script src='<c:url value="/phone/js/jquery-1.9.1.min.js"/>'></script>
<script src='<c:url value="/phone/js/picture_jquery.excoloSlider.js"/>'></script>


<script src="http://alexgorbatchev.com/pub/sh/current/scripts/shCore.js" type="text/javascript"></script>
<script src="http://alexgorbatchev.com/pub/sh/current/scripts/shAutoloader.js" type="text/javascript"></script>

    <script>
        // Settings for Adaptive.js
        var ADAPT_CONFIG = {
            path: 'css/',
            dynamic: true,
            range: [
                '0px    to 760px  = mobile.min.css',
                '760px  to 980px  = 720.min.css',
                '980px            = 960.min.css'
            ]
        };

    </script>
<script src='<c:url value="/phone/js/adapt.min.js"/>'></script>
    <!-- <script>
        $(function () {
            $("#sliderA").excoloSlider();
        });
    </script> -->



