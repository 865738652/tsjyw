<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8">
    <meta name="keywords" content="唐山家教">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
    <meta http-equiv="X-UA-Compatible" content="IE=9" />
    <link href="../css/style.css" rel="stylesheet">
    <link href="../css/index.css" rel="stylesheet">
    <link href="../css/css1.css" rel="stylesheet">
    <title>唐山家庭教育网</title>
    <!--<script type="text/javascript" src="../js/easydialog.min.js"></script>-->
    
    <!--<script type="text/javascript" src="../js/jquery1.js"></script>-->
    <script type="text/javascript" src="../js/jquery.js"></script>
    <script type="text/javascript" src="../js/lazyloadimg.js"></script>
    <!-- <script type="text/javascript" src="../js/Headerhtml.js"></script> -->
    <script type="text/javascript"src="../js/LoadFooter.js"></script>
	<script type="text/javascript"src="../js/loadpublicfun.js"></script>
	<script type="text/javascript" src="../js/swiper.min.js"></script>
	<script type="text/javascript" src="../js/index.js"></script>
	<style>
		#imgBox{ border:1px solid #d3d3d3; padding:5px; background:#fff; width:500px;}
	</style>
	<script type="text/javascript">
		$(function() {
			/* fill volunteer list */
			//show();
			//popCenterWindow(); 
			getHotQuestion(1, 7, function(data) {
				if (data == null || data.rows.length == 0)
					return;
				
				var html = '';
				for (var i = 0; i < 7; i++) {
					if (i < data.rows.length) {
						html += '<li><a class="ellipsis" href="../AskQuestionManage/ShowQuestion?askQuestionId=' + data.rows[i].askQuestionId + '" rel="nofollow" target="_blank">' + data.rows[i].askQuestionTitle;
						html += '<span class="actCal-date">' + getSmpFormatDateByLong(data.rows[i].askQuestionTime, false) + '</span></a></li>';
					}
					else {
						html += '<li><a class="ellipsis" href="" rel="nofollow" target="_blank">“美洋达杯最美亲子阅读家庭” 评选颁奖<span class="actCal-date">2016-09-15</span></a></li>';
					}
				}
				$("#volunteerList").html(html);
			});

			getArticleByModule(72, 5, true, function(data) {
				if (data == null || data.rows.length == 0)
					return;
				
				var html = '';
				for (var i = 0; i < 2; i++) {
					if (i < data.rows.length) {
						var thumbnail = data.rows[i].imagePath != null? data.rows[i].imagePath : "../images/131.jpg";
						htm += '<li><a href="../Index/ShowArticle?articleId=' + data.rows[i].articleId + '" target="_blank"><img src="' + thumbnail + '" style="width:290px;"><span class="ellipsis">' + data.rows[i].articleTitle + '</span></a></li>';
					}
					else
						html += '<li><a href="#" target="_blank"><img src="../images/131.jpg" style="width:290px;"><span class="ellipsis">唐山家庭教育网正式上线</span></a></li>';
				}

				$("bbs-list").html(html);
			});
			
			/* fill teacher list */
			getFamousTeacher(1, 3, function(data) {
				if (data == null || data.rows.length == 0)
					return;
				
				var html = '';
				for (var i = 0; i < 3; i++) {
					var thumbnail = "../images/" + (20 + i) + ".jpg";
					if (i < data.rows.length) {
						thumbnail = (data.rows[i].userPhotoUrl != null ? data.rows[i].userPhotoUrl : thumbnail);
						html += '<li><a class="img" href="../Index/ShowFamousTeacher?userId=' + data.rows[i].userId + '" target="_blank"><img style="width:94px;height:94px" src="' + thumbnail + '"></a>';
						html += '<div class="content"><a class="ellipsis" href="../Index/ShowFamousTeacher?userId=' + data.rows[i].userId + '" target="_blank">' + data.rows[i].userName + '</a>';
						html += '<p class="description">' + data.rows[i].famousTeacherIntro.substr(0, 20) + '</p></div></li>';
					}
					else {
						html += '<li><a class="img" href="" target="_blank"><img src="../images/20.jpg" alt="王老师"></a>';
						html += '<div class="content"><a class="ellipsis" href="" target="_blank">李老师</a>';
						html += '<p class="description">青少年心理教育专家，享受国务院津贴</p></div></li>';
					}
				}
				
				$("#famousTeacher").html(html);
			});

			/* fill learning column */		
			/*	
			getArticleByModule(4, 6, false, function(data) {
				if (data == null || data.rows.length == 0)
					return;
				
				var html = '';
				for (var i = 0; i < 6; i++) {
					if (i < data.rows.length) {
						var thumbnail = data.rows[i].imagePath != null? data.rows[i].imagePath : "../images/25.jpg";
						htm += '<li><a href="../Index/ShowArticle?articleId=' + data.rows[i].articleId + '" target="_blank"><img src="' + thumbnail + '"><span>' + data.rows[i].articleTitle + '</span></a></li>';
					}
					else
						html += '<li><a href="#" target="_blank"><img src="../images/25.jpg"  alt="右脑计划"><span>右脑计划</span></a></li>';
				}
				$("#column_wiki_list").html(html);
			});
			*/
			/* fill video list */
			getArticleByModule(8, 6, false, function(data) {
				if (data == null || data.rows.length == 0)
					return;
				var html = '';
				for (var i = 0; i < 4; i++) {
					if (i < data.rows.length) {
						var thumbnail = data.rows[i].imagePath != null? data.rows[i].imagePath : "../images/15.jpg";
						html += '<li><a href="../Index/ShowArticle?articleId=' + data.rows[i].articleId + '" target="_blank"><img class="video-img" src="' + thumbnail + '"><span  class="video-txt">' + data.rows[i].articleTitle + '</span><i class="video-opacity"></i></a></li>';
					}
					else {
						html += '<li><a href="" target="_blank"><img class="video-img" src="../images/15.jpg" alt="【活动】“美洋达杯最美亲子阅读家庭”"><span class="video-txt">【活动】“美洋达杯最美亲子阅读家庭” 网络评选</span> <i class="video-opacity"></i></a></li>';
					}
				}
				$("#videoList").html(html);
			});

			getAdvertisement(1);
			getAdvertisement(2);
		});
		function show(){
			easyDialog.open({
				container : 'imgBox',
				autoClose : 6000,
				fixed : false,
				 overlay : false
			});
		}
		function closezhe(){
			document.getElementById("imgBox").style.display="none"; 
			document.getElementById("overlay").style.display="none";; 
		}
	</script>
</head>
<body>
	<!--
	<div id="easyDialogBox" style="margin: -173px 0px 0px -256px; padding: 0px; border: none; z-index: 10000; position: absolute; top: 250px; left: 499.5px; display: block;">
		<div id="imgBox" style="display: block; margin: 0px;">
			<a href="javascript:void(0);" class="close" onclick="closezhe()" style="float:right;"><img src="../images/close.jpg"></a>
			<img src="../images/test.jpg" style="width:500px;" alt="">
		</div>
	</div>
	-->
    <div id="header"><%@include file="header.jsp" %></div>
    
    <div class="fullwrap">
        <div class="commen-wrap">
            <div class="castwrap relative">
                <div class="swiper-container">
                    <div class="swiper-wrapper">
                    	<!-- 首页幻灯 -->
                    	<c:if test="${not empty articleSilde}"> 
                    		<c:forEach var="article" items="${articleSilde}" begin="0" end="19"> 
                    			<div class="swiper-slide">
		                            <a class="fl" href="../Index/ShowArticle?articleId=${article.articleId}" target="_blank">
		                            	<c:if test="${empty article.imagePath}"><img src="../images/1.jpg"></c:if>
		                            	<c:if test="${not empty article.imagePath}"><img src="${article.imagePath}"></c:if>
		                            </a>
		                            <div class="description fl">
		                                <a title="“最美亲子阅读家庭”评选活动" href="../Index/ShowArticle?articleId=${article.articleId}" target="_blank" onclick="adclick('0157332');">
		                                    <b class="title">${article.articleTitle}</b>
		                                    <span class="content">
		                                        ${article.articleAbstract}
		                                    </span>
		                                </a>
		                            </div>
		                        </div>
                    		</c:forEach>
                    	</c:if>
						<c:if test="${empty articleSilde}">              
                        <div class="swiper-slide">
                            <a class="fl" href="" target="_blank" onclick="adclick('0157332');">
                                <img src="../images/1.jpg">
                            </a>
                            <div class="description fl">
                                <a title="“最美亲子阅读家庭”评选活动" href="" target="_blank" onclick="adclick('0157332');">
                                    <b class="title">“最美亲子阅读家庭”评选活动</b>
                                    <span class="content">
                                        进一步营造书香家庭氛围，激发家庭成员读书热情，促进家庭亲子教育质量提升，丰富读者个人阅读素养，让读书走进每一个家庭，让读书成为一种习惯，让读书成为一种追求，让读书成为一种时尚。
                                    </span>
                                </a>
                            </div>
                        </div>
                        <div class="swiper-slide">
                            <a class="fl" href="" target="_blank" onclick="adclick('0157332');">
                                <img src="../images/1.jpg">
                            </a>
                            <div class="description fl">
                                <a title="“最美亲子阅读家庭”评选活动" href="" target="_blank" onclick="adclick('0157332');">
                                    <b class="title">“最美亲子阅读家庭”评选活动</b>
                                    <span class="content">
                                        进一步营造书香家庭氛围，激发家庭成员读书热情，促进家庭亲子教育质量提升，丰富读者个人阅读素养，让读书走进每一个家庭，让读书成为一种习惯，让读书成为一种追求，让读书成为一种时尚。
                                    </span>
                                </a>
                            </div>
                        </div>
                        <div class="swiper-slide">
                            <a class="fl" href="" target="_blank" onclick="adclick('0157332');">
                                <img src="../images/1.jpg">
                            </a>
                            <div class="description fl">
                                <a title="“最美亲子阅读家庭”评选活动" href="" target="_blank" onclick="adclick('0157332');">
                                    <b class="title">“最美亲子阅读家庭”评选活动</b>
                                    <span class="content">
                                        进一步营造书香家庭氛围，激发家庭成员读书热情，促进家庭亲子教育质量提升，丰富读者个人阅读素养，让读书走进每一个家庭，让读书成为一种习惯，让读书成为一种追求，让读书成为一种时尚。
                                    </span>
                                </a>
                            </div>
                        </div>
                        <div class="swiper-slide">
                            <a class="fl" href="" target="_blank" onclick="adclick('0157332');">
                                <img src="../images/1.jpg">
                            </a>
                            <div class="description fl">
                                <a title="“最美亲子阅读家庭”评选活动" href="" target="_blank" onclick="adclick('0157332');">
                                    <b class="title">“最美亲子阅读家庭”评选活动</b>
                                    <span class="content">
                                        进一步营造书香家庭氛围，激发家庭成员读书热情，促进家庭亲子教育质量提升，丰富读者个人阅读素养，让读书走进每一个家庭，让读书成为一种习惯，让读书成为一种追求，让读书成为一种时尚。
                                    </span>
                                </a>
                            </div>
                        </div>
                        <div class="swiper-slide">
                            <a class="fl" href="" target="_blank" onclick="adclick('0157332');">
                                <img src="../images/1.jpg">
                            </a>
                            <div class="description fl">
                                <a title="“最美亲子阅读家庭”评选活动" href="" target="_blank" onclick="adclick('0157332');">
                                    <b class="title">“最美亲子阅读家庭”评选活动</b>
                                    <span class="content">
                                        进一步营造书香家庭氛围，激发家庭成员读书热情，促进家庭亲子教育质量提升，丰富读者个人阅读素养，让读书走进每一个家庭，让读书成为一种习惯，让读书成为一种追求，让读书成为一种时尚。
                                    </span>
                                </a>
                            </div>
                        </div>
                        <div class="swiper-slide">
	                        <a class="fl" target="_blank" href="###">
	                            <img alt="" src="img_temp/cast1.jpg">
	                        </a>
	                        <div class="description fl">
	                            <a title="demo即将来袭将带来史无前例的恐怖体验《鬼影实录VR》" target="_blank" href="###">
	                                <b class="title">N卡首款“有物理基础音响模拟引擎” VRworks API加...</b>
	                                <span class="content">
	                                    在近日举行的Nvidia特殊的编辑者日上，Nvidia的首席执行官Jen-Hsun Huang(黄仁勋)宣称，该公司在VR领域主要关注渲染API的VRworks(之前叫做Gameworks VR)，将会被配给一台新的，有物理基础会被配给一电话是配给一...
	                                </span>
	                            </a>
	                        </div>
                    	</div>
						</c:if>
                </div>
            </div>
            <a class="arrow-left" href="javascript:;">&lt;</a>
            <a class="arrow-right" href="javascript:;">&gt;</a>
        </div>
        <div class="hotNews-wrap">
            <p class="hotNews-date">
                <span class="hotNews-month"></span>
                <span class="hotNews-day"></span>
            </p>
            <div class="hotNews-tit">
                <h2 class="hotNewsH2">今日热点</h2>
                <span class="hotNewsTag">headlines</span>
            </div>
            <ul class="hotNewsList">
            	<!-- 今日热点 -->
            	<c:if test="${not empty articleToday}"> 
               		<c:forEach var="article" items="${articleToday}" begin="0" end="2"> 
               			<li><a href="../Index/ShowArticle?articleId=${article.articleId}" target="_blank">${article.articleTitle}</a></li>
               		</c:forEach>
               	</c:if>
				<c:if test="${empty articleSilde}"> 
                <li>
                    <a href="" target="_blank" onclick="adclick('0158237');">关注右脑开发 见证你就是天才的父母！！</a>
                </li>
                <li>
                    <a href="" target="_blank" onclick="adclick('0158236');">献给高考的孩子们—答题方法！！！</a>
                </li>
                <li>
                    <a href="" target="_blank" onclick="adclick('0158235');">少年期学生学习违纪心理表现</a>
                </li>
				</c:if>
            </ul>
        </div>
    </div>
</div>
<!-- <div class="commen-wrap firstAd">
    <a href="http://www.87870.com/activity/vrparty" target="_blank" onclick="adclick('01883');" class="fullAd">
        <img src="../images/2.jpg" alt="这是一个广告"></a>
</div> -->

<div class="commen-wrap mb30 pt20">
    <div class="news-wrap ">
        <div class="index-h2Tit relative">
            <h2 class="index-h2">精彩活动</h2>
            <a class="index-more" href="../Index/ShowModule?moduleId=6" target="_blank">更多&gt;&gt;</a>
        </div>
        <ul class="reportList">
        	<c:if test="${not empty articleParty}"> 
           		<c:forEach var="article" items="${articleParty}" begin="0" end="1"> 
           			<li>
	                <a class="reportLink" href="../Index/ShowArticle?articleId=${article.articleId}" target="_blank">	                    
	                    <c:if test="${empty article.imagePath}"><img class="reportImg" src="../images/1.jpg" alt="${article.articleTitle}"></c:if>
		                <c:if test="${not empty article.imagePath}"><img class="reportImg" src="${article.imagePath}" alt="${article.articleTitle}"></c:if>
	                    <span class="reportTxt">${article.articleTitle}</span>
	                </a>
	            	</li>
           		</c:forEach>
            </c:if>
			<c:if test="${empty articleParty}"> 
            <li>
                <a class="reportLink" href="" target="_blank" onclick="adclick('018788');">
                    <img class="reportImg" src="../images/1.jpg" alt="“美洋达杯最美亲子阅读家庭” 网络评选">
                    <span class="reportTxt">“美洋达杯最美亲子阅读家庭” 网络评选</span>
                </a>
            </li>
            <li>
                <a class="reportLink" href="" target="_blank" onclick="adclick('018787');">
                    <img class="reportImg" src="../images/1.jpg" alt="“美洋达杯最美亲子阅读家庭” 网络评选">
                    <span class="reportTxt">“美洋达杯最美亲子阅读家庭” 网络评选</span>
                </a>
            </li>
			</c:if>
        </ul>
        <div class="index-h2Tit mt30 relative">
            <h2 class="index-h2">看文章</h2>
            <a class="index-more" href="../Index/ShowModule?moduleId=1" target="_blank">更多&gt;&gt;</a>
            
        </div>
        <ul class="newsList" >
        	<c:if test="${not empty articleRecommend}"> 
           		<c:forEach var="article" items="${articleRecommend}" begin="0" end="1"> 
           			<li >
		                <a class="newsImgLink" href="../Index/ShowArticle?articleId=${article.articleId}" target="_blank">
		                	<c:if test="${empty article.imagePath}"><img src="../images/1.jpg" ></c:if>
		                	<c:if test="${not empty article.imagePath}"><img src="${article.imagePath}"></c:if>
		                </a>
		                <div class="newsContent">
		                    <div class="newsTit">
		                        <a class="ruanjian" href="../Index/ShowArticle?articleId=${article.articleId}" target="_blank">热点</a>
		                        <a class="newTxtLink ellipsis" href="../Index/ShowArticle?articleId=${article.articleId}" target="_blank">${article.articleTitle}</a>
		                    </div>
		                    <p class="news-date"><fmt:formatDate value='${article.articleTime}' pattern='yyyy-MM-dd'/></p>
		                    <p class="newsDes">
		                       ${article.articleAbstract}
		                    </p>
		                    <p class="clearfix" id="new_12466">
		                        <span class="reviews">0</span>
		                        <span class="visited">${article.articleReadCount}</span>
		                    </p>
		                </div>
		            </li>           			
           		</c:forEach>
            </c:if>
            <c:if test="${empty articleRecommend}">
            <li >
                <a class="newsImgLink" href="" target="_blank">
                    <img src="../images/1.jpg"></a>
                <div class="newsContent">
                    <div class="newsTit">
                        <a class="ruanjian" href="" target="_blank">热点</a>
                        <a class="newTxtLink ellipsis" href="" target="_blank">内行看门道 “美洋达杯最美亲子阅读家庭” 网络评选</a>
                    </div>
                    <p class="news-date">2016-07-25</p>
                    <p class="newsDes">
                        为进一步推动我市“书香校园、书香班级、书香家庭、书香个人” 创建活动，着力营造全民读书、终身学习的良好氛围，丰富市民精神文化生活，唐山家庭教育网特推出“首届美洋达杯最美亲子阅读家庭”网络评选活动，本活动得到了唐山市美洋达摄影有限公司、唐山市睿德学校以及唐山华夏之星艺术培训学校开平分校的大力支持。
                    </p>
                    <p class="clearfix" id="new_12466">
                        <span class="reviews">0</span>
                        <span class="visited">253</span>
                    </p>
                </div>
            </li>
            <li>
                <a class="newsImgLink" href="" target="_blank">
                    <img src="../images/3.jpg"></a>
                <div class="newsContent">
                    <div class="newsTit">
                        <a class="ruanjian" href="" target="_blank">独家</a>
                        <a class="newTxtLink ellipsis" href="" target="_blank">献给高考的孩子们—答题方法！！！</a>
                    </div>
                    <p class="news-date">2016-07-25</p>
                    <p class="newsDes">
                        刚拿到试卷，一般心情比较紧张，建议拿到卷子以后看一下，看看考卷一共几页，有多少道题，了解试卷结构，通览全卷是克服"前面难题做不出，后面易题没时间做"的有效措施，也从根本上防止了"漏做题"。
                    </p>
                    <p class="clearfix" id="new_12465">
                        <span class="reviews">0</span>
                        <span class="visited">161</span>
                    </p>
                </div>
            </li>
            <li>
                <a class="newsImgLink" href="" target="_blank">
                    <img src="../images/3.jpg"></a>
                <div class="newsContent">
                    <div class="newsTit">
                        <a class="ruanjian" href="" target="_blank">独家</a>
                        <a class="newTxtLink ellipsis" href="" target="_blank">献给高考的孩子们—答题方法！！！</a>
                    </div>
                    <p class="news-date">2016-07-25</p>
                    <p class="newsDes">
                        刚拿到试卷，一般心情比较紧张，建议拿到卷子以后看一下，看看考卷一共几页，有多少道题，了解试卷结构，通览全卷是克服"前面难题做不出，后面易题没时间做"的有效措施，也从根本上防止了"漏做题"。
                    </p>
                    <p class="clearfix" id="new_12465">
                        <span class="reviews">0</span>
                        <span class="visited">161</span>
                    </p>
                </div>
            </li>
            <li>
                <a class="newsImgLink" href="" target="_blank">
                    <img src="../images\1.jpg"></a>
                <div class="newsContent">
                    <div class="newsTit">
                        <a class="ruanjian" href="" target="_blank">热点</a>
                        <a class="newTxtLink ellipsis" href="" target="_blank">内行看门道 “美洋达杯最美亲子阅读家庭” 网络评选</a>
                    </div>
                    <p class="news-date">2016-07-25</p>
                    <p class="newsDes">
                        为进一步推动我市“书香校园、书香班级、书香家庭、书香个人” 创建活动，着力营造全民读书、终身学习的良好氛围，丰富市民精神文化生活，唐山家庭教育网特推出“首届美洋达杯最美亲子阅读家庭”网络评选活动，本活动得到了唐山市美洋达摄影有限公司、唐山市睿德学校以及唐山华夏之星艺术培训学校开平分校的大力支持。
                    </p>
                    <p class="clearfix" id="new_12466">
                        <span class="reviews">0</span>
                        <span class="visited">253</span>
                    </p>
                </div>
            </li>
            <li>
                <a class="newsImgLink" href="" target="_blank">
                    <img src="../images/3.jpg"></a>
                <div class="newsContent">
                    <div class="newsTit">
                        <a class="ruanjian" href="" target="_blank">独家</a>
                        <a class="newTxtLink ellipsis" href="" target="_blank">献给高考的孩子们—答题方法！！！</a>
                    </div>
                    <p class="news-date">2016-07-25</p>
                    <p class="newsDes">
                        刚拿到试卷，一般心情比较紧张，建议拿到卷子以后看一下，看看考卷一共几页，有多少道题，了解试卷结构，通览全卷是克服"前面难题做不出，后面易题没时间做"的有效措施，也从根本上防止了"漏做题"。
                    </p>
                    <p class="clearfix" id="new_12465">
                        <span class="reviews">0</span>
                        <span class="visited">161</span>
                    </p>
                </div>
            </li>
            </c:if>
        </ul>
        <a class="newsList-more" target="_blank" href="../Index/ShowModule?moduleId=1">查看更多&gt;&gt;</a>
    </div>
    <div class="index-slide">
        <div class="relative">
            <h2 class="index-h2_noL">活动日历</h2>
            <a class="index-more" href="../Index/ShowModule?moduleId=36" target="_blank">更多&gt;&gt;</a>
        </div>
        <a class="actCal-imgLink" href="" target="_blank" onclick="adclick('01705');">
            <img src="../images/9a06f5b4-79ea-45c8-82db-09231f731a67.jpg"></a>
        <ul class="actCal-list">
        	<c:if test="${not empty articleCalender}"> 
           		<c:forEach var="article" items="${articleCalender}" begin="0" end="3"> 
           			<li>
	                <a class="ellipsis" href="../Index/ShowArticle?articleId=${article.articleId}" rel="nofollow" target="_blank" >	
	                	${article.articleTitle}
                    	<span class="actCal-date"><fmt:formatDate value='${article.articleTime}' pattern='yyyy-MM-dd'/></span>
	                </a>
	                </li>
           		</c:forEach>
            </c:if>
			<c:if test="${empty articleCalender}"> 
            <li>
                <a class="ellipsis" href="" rel="nofollow" target="_blank" onclick="adclick('01265');">
                    “美洋达杯最美亲子阅读家庭” 评选颁奖
                    <span class="actCal-date">2016-09-15</span>
                </a>
            </li>
            <li>
                <a class="ellipsis" href="" target="_blank" onclick="adclick('01264');">
                    “美洋达杯最美亲子阅读家庭” 评选公布
                    <span class="actCal-date">2016-5-24</span>
                </a>
            </li>
            <li>
                <a class="ellipsis" href="" rel="nofollow" target="_blank" onclick="adclick('01263');">
                    “美洋达杯最美亲子阅读家庭” 网络评选
                    <span class="actCal-date">2016-5-12</span>
                </a>
            </li>
            <li>
                <a class="ellipsis" href="" rel="nofollow" target="_blank" onclick="adclick('01262');">
                   “美洋达杯最美亲子阅读家庭” 网络评选
                    <span class="actCal-date">2016-4-13</span>
                </a>
            </li>
			</c:if>
        </ul>
        <div class="ranking">
            <div class="relative">
                <span class="rankingNav active">校园之窗</span>
                <a class="index-more" href="../Index/School" target="_blank">更多&gt;&gt;</a>
            </div>
            <div class="ranking-container">
                <div class="swiper-wrapper">
                    <div class="swiper-slide">
                        <ul class="ranking-game-list">
                        <c:if test="${not empty articleSchool}"> 
			           		<c:forEach var="article" items="${articleSchool}"  varStatus="s" begin="0" end="4"> 			           			
				                <li>
	                                <a href="../School/SchoolShow?articleId=${article.articleId}" target="_blank" title="${article.articleTitle}">
	                                    <c:if test="${s.index < 3}"><span class="top3"></c:if><c:if test="${s.index >= 3}"><span class="top"></c:if>${s.count}</span>
	                                    <c:if test="${empty article.imagePath}"><img src="../images/4.jpg"></c:if>
		                				<c:if test="${not empty article.imagePath}"><img src="${article.imagePath}"></c:if>
	                                    <span class="txt ellipsis">${article.articleTitle}</span>
	                                </a>
	                            </li>
			           		</c:forEach>
			            </c:if>
						<c:if test="${empty articleSchool}"> 
                            <li>
                                <a href="" target="_blank" title="智慧故事">
                                    <span class="top3">1</span>
                                    <img src="../images/4.jpg">
                                    <span class="txt ellipsis">什么是幸福？</span>
                                </a>
                            </li>
                            <li>
                                <a href="" target="_blank" title="时间会告诉你答案">
                                    <span class="top3">2</span>
                                    <img src="../images/5.jpg">
                                    <span class="txt ellipsis">时间会告诉你答案</span>
                                </a>
                            </li>
                            <li>
                                <a href="" target="_blank" title="对于孩子，学会放手是一种大智慧">
                                    <span class="top3">3</span>
                                    <img src="../images\6.png">
                                    <span class="txt ellipsis">对于孩子，学会放手是一种大智慧</span>
                                </a>
                            </li>
                            <li>
                                <a href="" target="_blank" title="珍惜当下生活的每一天">
                                    <span class="top">4</span>
                                    <img src="../images/7.jpg">
                                    <span class="txt ellipsis">珍惜当下生活的每一天</span>
                                </a>
                            </li>
                            <li>
                                <a href="" target="_blank" title="静静等待何尝不是一种智慧！">
                                    <span class="top">5</span>
                                    <img src="../images/8.jpg">
                                    <span class="txt ellipsis">静静等待何尝不是一种智慧！</span>
                                </a>
                            </li>
                        </c:if>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <!--  
        <div class="wiki-library mt20">
            <div class="index-h2Tit relative">
                <h2 class="index-h2">法制与安全</h2>
                <a class="index-more" href="../Index/ShowModule?moduleId=7" target="_blank">更多&gt;&gt;</a>
            </div>
            <div class="swiper-slide">
				<ul class="ranking-game-list">
				<c:if test="${not empty articleSafety}"> 
	           		<c:forEach var="article" items="${articleSafety}"  varStatus="s" begin="0" end="4"> 			           			
		                <li>
                               <a href="../Index/ShowArticle?articleId=${article.articleId}" target="_blank" title="${article.articleTitle}">
                                   <c:if test="${s.index < 3}"><span class="top3"></c:if><c:if test="${s.index >= 3}"><span class="top"></c:if>${s.count}</span>
                                   <c:if test="${empty article.imagePath}"><img src="../images/4.jpg"></c:if>
                				   <c:if test="${not empty article.imagePath}"><img src="${article.imagePath}"></c:if>
                                   <span class="txt ellipsis">${article.articleTitle}</span>
                               </a>
                           </li>
	           		</c:forEach>
	            </c:if>
				<c:if test="${empty articleSafety}"> 
					<li>
						<a href="" target="_blank" title="智慧故事">
							<span class="top3">1</span>
							<img src="../images/4.jpg">
							<span class="txt ellipsis">什么是幸福？</span>
						</a>
					</li>
					<li>
						<a href="" target="_blank" title="时间会告诉你答案">
							<span class="top3">2</span>
							<img src="../images/5.jpg">
							<span class="txt ellipsis">时间会告诉你答案</span>
						</a>
					</li>
					<li>
						<a href="" target="_blank" title="对于孩子，学会放手是一种大智慧">
							<span class="top3">3</span>
							<img src="../images/6.png">
							<span class="txt ellipsis">对于孩子，学会放手是一种大智慧</span>
						</a>
					</li>
					<li>
						<a href="" target="_blank" title="珍惜当下生活的每一天">
							<span class="top">4</span>
							<img src="../images/7.jpg">
							<span class="txt ellipsis">珍惜当下生活的每一天</span>
						</a>
					</li>
					<li>
						<a href="" target="_blank" title="静静等待何尝不是一种智慧！">
							<span class="top">5</span>
							<img src="../images/8.jpg">
							<span class="txt ellipsis">静静等待何尝不是一种智慧！</span>
						</a>
					</li>
				</c:if>
				</ul>
			</div>
        </div>
        -->
        <!-- <a class="index-job-link" href="http://job.87870.com" target="_blank">
            <img src="images\loading.gif" data-src="/2016/img/370-100.jpg"></a>
        <div class="index-job">
            <!-- <a href="###" title="">【Android平台开发工程师】 ▪ 北京  ▪ 15k-20k</a>
        <a href="###" title="">【产品总监】 ▪ 北京  ▪ 15k-20k</a>
        <a href="###" title="">【软件项目经理】 ▪ 北京  ▪ 15k-20k</a>
        <a href="###" title="">【3D场景原画】 ▪ 北京  ▪ 15k-20k</a>
        <a href="###" title="">【社会公共关系经理】 ▪ 北京  ▪ 15k-20k</a>
        -->
        <!-- <a href="http://job.87870.com/Company/Jobview.aspx?id=123" target="_blank" onclick="adclick('018310');" title="【幸福互动（北京）网络科技有限公司】▪ 英文编辑">【幸福互动（北京）网络科技有限公司】▪ 英文编辑</a>
        <a href="http://job.87870.com/Company/Jobview.aspx?id=124" target="_blank" onclick="adclick('01839');" title="【幸福互动（北京）网络科技有限公司】▪ 采编记者">【幸福互动（北京）网络科技有限公司】▪ 采编记者</a>
        <a href="http://job.87870.com/Company/Jobview.aspx?id=72" target="_blank" onclick="adclick('01834');" title="【北京魔视互动科技有限公司】▪ unity3D客户端主程">【北京魔视互动科技有限公司】▪ unity3D客户端主程</a>
        <a href="http://job.87870.com/Company/Jobview.aspx?id=30" target="_blank" onclick="adclick('018312');" title="【天舍（上海）文化传媒有限公司】▪ 技术美术">【天舍（上海）文化传媒有限公司】▪ 技术美术</a>
        <a href="http://job.87870.com/Company/Jobview.aspx?id=90" target="_blank" onclick="adclick('01833');" title="【爱奇艺科技有限公司】▪ 国内VR游戏商务">【爱奇艺科技有限公司】▪ 国内VR游戏商务</a>
    </div> --> 
</div>
</div>
<!-- advertisement -->
<div class="commen-wrap">	
	<a id="advertisement1" href="" rel="nofollow" target="_blank" onclick="adclick('01653');" class="fullAd">
    <img src="../images/c141746b-8ee7-472a-b3a0-e65e751c9e77.jpg" alt="星耀360"></a>
</div>
<div class="commen-wrap mb28">
<div class="bbs-wrap fl">
    <div class="index-h2Tit relative">
        <h2 class="index-h2">热门博客</h2>
        <a class="index-more" href="" target="_blank">更多&gt;&gt;</a>
    </div>
	<ul class="bbs-list">
        <li>
            <a href="" target="_blank" onclick="adclick('016618');">
                <img src="../images/131.jpg" style="width:290px;">
                <span class="ellipsis">唐山家庭教育网正式上线</span>
            </a>
        </li>
        <li>
            <a href="" target="_blank" onclick="adclick('016619');">
                <img src="../images/132.jpg"  style="width:290px;">
                <span class="ellipsis">唐山家庭教育网正式上线</span>
            </a>
        </li>
    </ul>
</div>
<div class="two-column">
    <div class="recommend-player fl">
        <div class="index-h2Tit relative">
            <h2 class="index-h2">名师咨询</h2>
             <a class="index-more" href="../Index/FamousTeacher" target="_blank">更多&gt;&gt;</a>
        
    </div>
    <ul class="index_contribute pt30 fl" id="famousTeacher">
        <li>
            <a class="img" href="" target="_blank" onclick="adclick('01673');">
                <img src="../images/20.jpg" alt="老王"></a>
            <div class="content">
                <a class="ellipsis" href="" target="_blank" onclick="adclick('01673');">李老师</a>
                <p class="description">青少年心理教育专家，享受国务院津贴</p>
            </div>
        </li>
        <li>
            <a class="img" href="" target="_blank" onclick="adclick('01672');">
                <img src="../images/21.jpg" alt="张老师"></a>
            <div class="content">
                <a class="ellipsis" href="" target="_blank" onclick="adclick('01673');">张老师</a>
                <p class="description">唐山一中英语教研室主任，长期任毕业班英语教师，高考辅导专家</p>
            </div>
        </li>
        <li>
            <a class="img" href="" target="_blank" onclick="adclick('01672');">
                <img src="../images/22.jpg" alt="张老师"></a>
            <div class="content">
                <a class="ellipsis" href="" target="_blank" onclick="adclick('01673');">王老师</a>
                <p class="description">全国校园安全协会主席，致力于校园安全保障和青少年人身安全问题研究</p>
            </div>
        </li>
    </ul>
</div>
<div class="bbs-topic">
    <div class="index-h2Tit relative">
        <h2 class="index-h2">常见问题</h2>
        <a class="index-more" href="../AskQuestionManage/ShowAllQuestion" target="_blank">更多&gt;&gt;</a>
    </div>
	<!--
    <ul class="publish">
        <li>
            <a href="" target="_blank" onclick="adclick('016818');">
                <img src="../images/23.jpg" alt="">
                <i></i>
                <span>陪伴是最好表达爱的方式</span>
            </a>
        </li>
        <li>
            <a href="" target="_blank" onclick="adclick('016817');">
                <img src="images/24.jpg" alt="">
                <i></i>
                <span>给你的爱，无语伦比</span>
            </a>
        </li>
    </ul>
	-->
	<ul class="actCal-list" id="volunteerList">
		<li>
			<a class="ellipsis" href="" rel="nofollow" target="_blank" onclick="adclick('01265');">
				“美洋达杯最美亲子阅读家庭” 评选颁奖
				<span class="actCal-date">2016-09-15</span>
			</a>
		</li>
		<li>
			<a class="ellipsis" href="" target="_blank" onclick="adclick('01264');">
				“美洋达杯最美亲子阅读家庭” 评选公布
				<span class="actCal-date">2016-5-24</span>
			</a>
		</li>
		<li>
			<a class="ellipsis" href="" rel="nofollow" target="_blank" onclick="adclick('01263');">
				“美洋达杯最美亲子阅读家庭” 网络评选
				<span class="actCal-date">2016-5-12</span>
			</a>
		</li>
		<li>
			<a class="ellipsis" href="" rel="nofollow" target="_blank" onclick="adclick('01262');">
			   “美洋达杯最美亲子阅读家庭” 网络评选
				<span class="actCal-date">2016-4-13</span>
			</a>
		</li>
		<li>
			<a class="ellipsis" href="" rel="nofollow" target="_blank" onclick="adclick('01263');">
				“美洋达杯最美亲子阅读家庭” 网络评选
				<span class="actCal-date">2016-5-12</span>
			</a>
		</li>
		<li>
			<a class="ellipsis" href="" rel="nofollow" target="_blank" onclick="adclick('01262');">
			   “美洋达杯最美亲子阅读家庭” 网络评选
				<span class="actCal-date">2016-4-13</span>
			</a>
		</li>
		<li>
			<a class="ellipsis" href="" rel="nofollow" target="_blank" onclick="adclick('01262');">
			   “美洋达杯最美亲子阅读家庭” 网络评选
				<span class="actCal-date">2016-4-13</span>
			</a>
		</li>
	</ul>
</div>
</div>
</div>
<div class="commen-wrap mb60">
<!--
<div class="wiki-photographs">
<div class="index-h2Tit relative">
    <h2 class="index-h2">学习园地</h2>
    <a class="index-more" href="../Index/ShowModule?moduleId=4" target="_blank">更多&gt;&gt;</a>
</div>
<div class="wiki-container">
    <div class="wiki-wrap">
        <ul style="width: 1536px;" id="column_wiki_list" class="column_wiki_list clearfix releative">
            <li>
                <a href="" target="_blank">
                    <img src="../images/25.jpg"  alt="右脑计划">
                    <span>右脑计划</span>
                </a>
            </li>
            <li>
                <a href="" target="_blank">
                    <img src="../images/26.jpg" alt="出差在外也要陪宝宝 VR讲故事">
                    <span>出差在外也要陪宝宝 VR讲故事</span>
                </a>
            </li>
            <li>
                <a href="" target="_blank">
                    <img src="../images/27.jpg" alt="高考寄予">
                    <span>高考寄予 全面提升你的观影体验</span>
                </a>
            </li>
            <li>
                <a href="" target="_blank">
                    <img src="../images/th_218x343_3089b729-fea3-4716-a0b2-8eacb8898212.jpg" alt="节约成本又环保 VR办公">
                    <span>节约成本又环保 VR办公</span>
                </a>
            </li>
            <li>
                <a href="" target="_blank">
                    <img src="../images/th_218x343_8a6bc1ab-be61-49c5-8cec-af10d2ad35c9.jpg" alt="满足你的主角幻想 ">
                    <span>满足你的主角幻想</span>
                </a>
            </li>
            <li>
                <a href="" target="_blank">
                    <img src="../images/th_218x343_6fd43439-2d68-4975-b70e-ef211771e448.jpg" alt="拨开雾霾看璀璨星空">
                    <span>拨开雾霾看璀璨星空</span>
                </a>
            </li>
        </ul>
    </div>
    <a id="wiki_prev" href="javascript:;" class="column_wiki_prev"></a>
    <a id="wiki_next" href="javascript:;" class="column_wiki_next"></a>
</div>
</div>
-->
</div>
<!-- advertisementId=2 -->
<div class="commen-wrap">
	<a href="" id="advertisement2" rel="nofollow" target="_blank" onclick="adclick('01647');" class="fullAd">
    <img src="../images/562f6d64-f6b5-4157-900f-108707ce33fb.jpg" alt=""></a>
</div>
<div  class="commen-wrap mb28" style="height:580px;">
<div style="width:810px;position:relative; float:left;">
<div class="index-h2Tit relative" style="width:750px;">
    <h2 class="index-h2">精彩视频</h2>
    <a target="_blank" href="../Index/ShowModule?moduleId=8" class="index-more">更多&gt;&gt;</a>
</div>
<ul class="video-list overflow" id="videoList">
    <li>
        <a href="" target="_blank">
            <img class="video-img" src="../images/15.jpg" alt="【活动】“美洋达杯最美亲子阅读家庭”">
            <span class="video-txt">【活动】“美洋达杯最美亲子阅读家庭” 网络评选</span> <i class="video-opacity"></i>
        </a>
    </li>
    <li>
        <a href="" target="_blank">
            <img class="video-img" src="../images/15.jpg" alt="“最美”亲子活动记录">
            <span class="video-txt">“最美”亲子活动记录</span> <i class="video-opacity"></i>
        </a>
    </li>
    <li>
        <a href="" target="_blank">
            <img class="video-img" src="../images/15.jpg" alt="【活动】“美洋达杯最美亲子阅读家庭”">
            <span class="video-txt">【活动】“美洋达杯最美亲子阅读家庭” 网络评选</span> <i class="video-opacity"></i>
        </a>
    </li>
    <li>
        <a href="" target="_blank">
            <img class="video-img" src="../images/15.jpg" alt="【活动】“美洋达杯最美亲子阅读家庭”">
            <span class="video-txt">【活动】“美洋达杯最美亲子阅读家庭” 网络评选</span> <i class="video-opacity"></i>
        </a>
    </li>
    <!--  
    <li>
        <a href="" target="_blank">
            <img class="video-img" src="../images/15.jpg" alt="【活动】“美洋达杯最美亲子阅读家庭”">
            <span class="video-txt">【活动】“美洋达杯最美亲子阅读家庭” 网络评选</span> <i class="video-opacity"></i>
        </a>
    </li>
    <li>
        <a href="" target="_blank">
            <img class="video-img" src="../images/15.jpg" alt="【活动】“美洋达杯最美亲子阅读家庭”">
            <span class="video-txt">【活动】“美洋达杯最美亲子阅读家庭” 网络评选</span> <i class="video-opacity"></i>
        </a>
    </li>
    -->
</ul>
</div>
<div class="wiki-library mt20">
            <div class="index-h2Tit relative">
                <h2 class="index-h2">法制与安全</h2>
                <a class="index-more" href="../Index/ShowModule?moduleId=7" target="_blank">更多&gt;&gt;</a>
            </div>
            <div class="swiper-slide">
				<ul class="ranking-game-list" style="height:530px;">
				<c:if test="${not empty articleSafety}"> 
	           		<c:forEach var="article" items="${articleSafety}"  varStatus="s" begin="0" end="4"> 			           			
		                <li>
                               <a href="../Index/ShowArticle?articleId=${article.articleId}" target="_blank" title="${article.articleTitle}">
                                   <c:if test="${s.index < 3}"><span class="top3"></c:if><c:if test="${s.index >= 3}"><span class="top"></c:if>${s.count}</span>
                                   <c:if test="${empty article.imagePath}"><img src="../images/4.jpg"></c:if>
                				   <c:if test="${not empty article.imagePath}"><img src="${article.imagePath}"></c:if>
                                   <span class="txt ellipsis">${article.articleTitle}</span>
                               </a>
                           </li>
	           		</c:forEach>
	            </c:if>
				<c:if test="${empty articleSafety}"> 
					<li>
						<a href="" target="_blank" title="智慧故事">
							<span class="top3">1</span>
							<img src="../images/4.jpg">
							<span class="txt ellipsis">什么是幸福？</span>
						</a>
					</li>
					<li>
						<a href="" target="_blank" title="时间会告诉你答案">
							<span class="top3">2</span>
							<img src="../images/5.jpg">
							<span class="txt ellipsis">时间会告诉你答案</span>
						</a>
					</li>
					<li>
						<a href="" target="_blank" title="对于孩子，学会放手是一种大智慧">
							<span class="top3">3</span>
							<img src="../images/6.png">
							<span class="txt ellipsis">对于孩子，学会放手是一种大智慧</span>
						</a>
					</li>
					<li>
						<a href="" target="_blank" title="珍惜当下生活的每一天">
							<span class="top">4</span>
							<img src="../images/7.jpg">
							<span class="txt ellipsis">珍惜当下生活的每一天</span>
						</a>
					</li>
					<li>
						<a href="" target="_blank" title="静静等待何尝不是一种智慧！">
							<span class="top">5</span>
							<img src="../images/8.jpg">
							<span class="txt ellipsis">静静等待何尝不是一种智慧！</span>
						</a>
					</li>
				</c:if>
				</ul>
			</div>
        </div>
</div>
<!-- <div class="friend-link">
<div class="friend bc">
友情链接：
<a href="http://vrguancha.net/" rel="nofollow" target="_blank" onclick="adclick('011527');">VR观察</a>
|
<a href="http://vr.sina.com.cn/" rel="nofollow" target="_blank" onclick="adclick('011519');">新浪VR</a>
|
<a href="http://uploadvr.com/" rel="nofollow" target="_blank" onclick="adclick('011520');">Uploadvr</a>
|
<a href="http://www.ourpalm.com/" rel="nofollow" target="_blank" onclick="adclick('011522');">掌趣科技</a>
|
<a href="http://ivr.baidu.com/" rel="nofollow" target="_blank" onclick="adclick('011523');">百度VR</a>
|
<a href="http://bbs.ivr.baidu.com/" rel="nofollow" target="_blank" onclick="adclick('011524');">百度VR社区</a>
|
<a href="http://www.news.cn/vr/" rel="nofollow" target="_blank" onclick="adclick('011521');">新华网VR</a>
|
<a href="http://www.913vr.com/" rel="nofollow" target="_blank" onclick="adclick('011526');">913VR</a>
</div>
</div> -->
<div id="footer"></div>
<input type="hidden" id="hiddenclicktype" value="1">
<input type="hidden" id="hiddenclickid" value="-100">
<!-- <script src="en\js\PageVisit.js" type="text/javascript"></script> -->
<!-- <script type="text/javascript">
    //function addclick(cid) {
    //    if (cid != undefined && cid != "") {
    //        $.ajax({
    //            type: "post",
    //            url: "http://hd.87870.com/Ajax/ashx/Click/Activecilck.ashx?id=" + escape(cid)
    //        });
    //    }
        //}
        function adclick(cid) {
            if (cid != undefined && cid != "") {
                $.ajax({
                    type: "post",
                    url: "/ashx/indexad.ashx?c=" + escape(cid)
                });
            }
        }
</script> -->
<!-- <div style="display:none;">
<script src="http://s95.cnzz.com/z_stat.php?id=1255576699&web_id=1255576699" language="JavaScript"></script>
<script src="http://s11.cnzz.com/z_stat.php?id=1256099300&web_id=12560993 00" language="JavaScript"></script>-->
<!-- <script>
        var _hmt = _hmt || [];
        (function () {
            var hm = document.createElement("script");
            hm.src = "//hm.baidu.com/hm.js?225694c67b2c1d267db850fac9dd0170";
            var s = document.getElementsByTagName("script")[0];
            s.parentNode.insertBefore(hm, s);
        })();
    </script> -->
    
    
</div>

</body>
</html>
