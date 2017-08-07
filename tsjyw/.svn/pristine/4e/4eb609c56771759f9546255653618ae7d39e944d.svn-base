<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>唐山家庭教育网</title>
	<meta name="keywords" content="唐山家庭教育网,家长学校,心理健康,青少年" />
	<meta name="description" content="唐山家庭教育网" />
	<link href="../css/style.css" rel="stylesheet" />
	<link href="../css/news.css" rel="stylesheet" />
	<link rel="stylesheet" type="text/css" href="../css/video.css" />
	<script type="text/javascript" src="../js/jquery.js"></script>
   	<script src="../js/rightCommon.js" type='text/javascript'></script>
   	<script src="../js/lazyloadimg.js"></script>

</head>
<body class="video-body">
    <div id="header" ><%@include file="header.jsp" %></div>        
    <input type="hidden" id="hiddenpid" value="${article.articleId}" />
    <span id="cmtindex" style="display:none;">1</span>
    <input type="hidden" id="cmdpagecount" value="0" />
	<div class="qp_like"><!-- 赞同反对点击显示+1 容器 --></div>
	<div class="qp_pageprompt"><!-- 操作频繁请重试 容器 --></div>
	<div class="gamebox clearfix">
		<div class="claerfix w1170 bc">
			<div class="phbann"></div> 
			<div class="arrow">
     			<a href="../Index/">首页</a> &gt; 
				<a href="../Index/ShowModule?moduleId=${curTopModule.moduleId}">${curTopModule.moduleName}</a> &gt; 
				<c:if test="${curTopModule.moduleId != curSubModule.moduleId}">
					<a href="../Index/ShowModule?moduleId=${curSubModule.moduleId}">${curSubModule.moduleName}</a> &gt;
				</c:if>		 
				${article.articleTitle}
			</div>
    <div class="g_left">
        <div class="dt_border">
            <div class="info_wrap clearfix">
                <h1 class="tjtitle">${article.articleTitle}</h1>
                <div class="clearfix">
                    <div class="info_tag fl">                        
                        <span class="tag_l" >来源：<em>tsjtjyw.com</em></span>
                        <span class="tag_l"><fmt:formatDate value='${article.articleTime}' pattern='yyyy-MM-dd'/></span>
                    </div>
                    <div class="info_tag fr">
                        <i class="sc tag_mg"></i>
                        <a href="javascript:;" class="sc_link" id="favP">收藏</a>
                        <i class="lookbg tag_mg"></i>
                        <i class="looknum clicknum">${article.articleReadCount}</i>
                        <i class="lookbg2 tag_mg"></i>
                        <i class="looknum"><em class="disnum">0</em></i>
                    </div>
                </div>
                <div class="info_guide">
                    <span class="tag">导读：</span>${article.articleAbstract}
                </div>
                   <!--照片幻灯-->
                <div class="photobox">
                    <div class="photobig">
                        <div id="picBox" class="ph_bigbox">
                            <ul class="bigimglist"> 
                            	<c:if test="${not empty photos}"> 
					 				<c:forEach var="photo" items="${photos}"> 
					 					<li><img  originalsrc="${photo.attachUrl}" src="${photo.attachUrl}"></li>
					 				</c:forEach>
					 			</c:if> 
                            </ul>
                        </div>
                       <div class="ph_numcount"><span id="countTag" class="current">1</span>/<span class="phnumzg">${fn:length(photos)}</span></div>
                       <a class="next lefttop" href="javascript:;" id="nextTop" ></a>
                       <a class="prev righttop" href="javascript:;"  id="prevTop"></a>
                    </div>
                    <div class="photosmall">
                    <div class="ph_smallbox">
                       <div class="ph_smallwline" id="listBox">
                          <ul class="ph_smalllineUL">
	                        <c:if test="${not empty photos}"> 
				 				<c:forEach var="photo" items="${photos}"  begin="0" varStatus="status"> 
				 					<c:if test="${status.index==0}"><li class="current"><img  src="${photo.attachUrl}"></li></c:if>
				 					<c:if test="${status.index!=0}"><li class=""><img src="${photo.attachUrl}"></li></c:if>
				 				</c:forEach>
				 			</c:if>                              
                          </ul>
                       </div>
                       <a href="javascript:;" class="smallprev lefttop" id="prev"></a>
                       <a href="javascript:;" class="smallnext righttop" id="next"></a>
                    </div>
                    <c:if test="${not empty prev}">
                    <a class="prev_photo" href="../Index/ShowArticle?articleId=${prev.articleId}">
                    	<img src="${prev.imagePath}" alt="" />
                    </c:if>
                    <c:if test="${empty prev}">
                    <a class="prev_photo" href="#">
                    	<img src="../images/no.jpg" alt="" />
                    </c:if>
                            <i></i>
                            <em></em>
                            <span>上个图集</span>
                    </a>
                    <c:if test="${not empty next}">
                    <a class="next_photo" href="../Index/ShowArticle?articleId=${next.articleId}">
                        <img src="${next.imagePath}" alt="" />
                    </c:if>
                    <c:if test="${empty next}">
                    <a class="next_photo" href="#">
                    	<img src="../images/no.jpg" alt="" />
                    </c:if>
                        <i></i>
                        <em></em>
                        <span>下个图集</span>
                    </a>
                    </div>
                   <div class="clear"></div>
                </div>
                   <!---->
               
                <div class="share">
				<!--
                    <div class="bshare-custom icon-medium-plus">
                        <a title="分享到" class="bshare-more" id="bshare-shareto" href="http://www.bShare.cn/">分享到</a>
                        <a title="分享到微信" class="bshare-weixin"></a>
                        <a title="分享到QQ空间" class="bshare-qzone"></a>
                        <a title="分享到新浪微博" class="bshare-sinaminiblog">
                        </a><a title="分享到人人网" class="bshare-renren"></a>
                        <a title="分享到腾讯微博" class="bshare-qqmb"></a>
                        <a title="更多平台" class="bshare-more bshare-more-icon more-style-addthis"></a><span class="BSHARE_COUNT bshare-share-count">0</span></div><script src="http://static.bshare.cn/b/buttonLite.js#style=-1&amp;uuid=&amp;pophcol=2&amp;lang=zh" type="text/javascript" charset="utf-8"></script><script src="http://static.bshare.cn/b/bshareC0.js" type="text/javascript" charset="utf-8"></script>
				-->
                </div>
            </div>
        </div>
        <div class="info_model">
            <div class="heading clearfix">
                <h2>相关图集</h2>
                <P class="key">
                    <span class="text">关键词： </span>
					<c:if test="${not empty article.articleKeyword}">
					<c:set value="${ fn:split(article.articleKeyword, ' ') }" var="keywords" />
					<c:forEach items="${ keywords }" var="keyword">
					<a class="key_tag" target="_blank" href="">${keyword}</a>
					</c:forEach>
					</c:if>
					<c:if test="${empty article.articleKeyword}">					
					<a class="key_tag" target="_blank" href="">家长学校</a>
					<a class="key_tag" target="_blank" href="">心理健康</a>
					<a class="key_tag" target="_blank" href="">青少年成长</a>
					</c:if>
                </P>
            </div>
            <ul class="other_video clearfix">
            	<c:if test="${not empty relates}"> 
	 				<c:forEach var="relate" items="${relates}"  begin="0" end="2" varStatus="status"> 
	 					<li>
                			<a href="../Index/ShowArticle?articleId=${relate.articleId}">
                				<img src="${relate.imagePath}" >
                				<span>${relate.articleTitle}</span><i></i>
                			</a>
                		</li>                
	 				</c:forEach>
	 			</c:if>  
            </ul>
        </div>
       
        <!-- 添加评论 -->

            <div class="add_word">
                <p class="title">我有话说：</p>
                 <textarea name="txb_Content0" id="words" class="words" placeholder="我有话要说......"></textarea>
                <input  data-qid="0" data-qpid="0" group="g0" data-id="0" type="button" name="send" id="btn_commentadd" onclick="addcomment()" class="send" value="提交"><a id="zxpl"></a>
				<input type = "hidden" id="hiddencsrf" name="${_csrf.parameterName}" value="${_csrf.token}"/>
				<input type = "hidden" id="commentTypeId" name="commentTypeId" value="1"/>
            </div>
            <!-- 评论列表 -->
            <div class="comment mt10" id="commentlistall">
                <h2 class="comment_heading">
                    <span class="title">最新评论</span>
                </h2>
              
            </div>
        <div class="comment commMore1" id="nomore" style="display:none;"><a href="javascript:;">没有更多评论了</a>
            </div>
            <div class="comment commMore2" id="initmore" style="display:none;"><a href="javascript:;" class="commMoreA">加载更多</a>
            </div>

    </div>
           <div class="g_right">
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
</div>
        <script type="text/javascript" src="../js/photo.js"></script>
    <script src="../js/fav.js"></script>
    <script src="../js/Comment.js?v=1.0.0.1"></script>

    <script src="../js/hotnewslist.js" type='text/javascript'></script>
    <%@include file="footer.jsp" %>    
</body>
</html>

