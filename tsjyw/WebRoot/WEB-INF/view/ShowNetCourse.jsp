<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head><meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="baidu-site-verification" content="VYkItiSaIv" />
<title>
	唐山家庭教育网
</title>
<meta name="keywords" content="家庭教育" />
<meta name="description" content="唐山家庭教育网" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <script type="text/javascript" src="../js/jquery.js"></script>
    <link href="../css/style.css" rel="stylesheet" />
    <script src="../js/lazyloadimg.js?v=1.0.0"></script>
    <script src="../js/video.js"></script>
    
    <link rel="stylesheet" type="text/css" href="../css/news.css" />
    <link href="../css/video1.css" rel="stylesheet"/>
</head>
<body class="video-body">
    <input type="hidden" id="columnindex" value="3" />
    <input type="hidden" id="hiddenurls" value="" />
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
    <div id="header" ><%@include file="header.jsp" %></div>
    
    <input type="hidden" id="hiddenpid" value="14462" />
     <input type="hidden" id="hidcolumnid" value="2" />
    <input type="hidden" id="hiddenurl" value="/Ashx/Comment.ashx" />
    <input type="hidden" id="hiddenlike" value="0" />
<div class="arrow">
        <a href="../Index/">首页</a> &gt; 
		<a href="../Index/ShowNetCourseModule?netCourseTypeId=-1">专题讲座</a> &gt; 
		<a href="../Index/ShowNetCourseModule?netCourseTypeId=${netcourse.netCourseTypeId}">${netcourse.netCourseTypeName}</a> &gt;	 
		${netcourse.netCourseName}
</div>
<div class="all_video" style="display:none;">
    <div class="big_video">
       
    </div>
</div>
<div class="gamebox clearfix mt20">
<div class="claerfix w1170 bc">
    <div class="g_left">
        <div class="dt_border">
            <div class="info_wrap clearfix">
                <div class="clearfix">
                    <div class="videoAll_head" style="width:818px;">
                        <h1 style="text-align:center;">${netcourse.netCourseName}</h1>
                        <div class="clearfix">
                            <div class="info_tag fl">
                                
                                2016-10-01<span class="tag_l">来源：<em>tsjtjyw.com</em></span>
                            </div>
                            <div class="info_tag fr">
                                <i class="sc tag_mg"></i>
                                <a href="javascript:;" class="sc_link">收藏</a>
                                <i class="lookbg tag_mg"></i>
                                <i class="looknum">652</i>
                                <i class="lookbg2 tag_mg"></i>
                                <i class="looknum"><em>0</em></i>
                            </div>
                        </div>
                    </div>
                    <a class="videoAll_down" target="_blank" style="display:none;" href=""></a>
                </div>
                <div class="info_guide clearfix">
                    <span class="tag">导读：</span>${netcourse.netCourseIntro}
                </div>
                <div style="text-align:center">
                    
                    <p style='display:none;'><img src='http://video.87870.com/th_300x300_468a97bd-64e8-4f7a-a043-b8853ca6d782.jpg' /></p>
                    <p style="text-align: center;">
                    <embed type="application/x-shockwave-flash" class="edui-faked-video" pluginspage="http://www.macromedia.com/go/getflashplayer" 
                    src="${netcourse.netCourseLink}" width="750" height="440" wmode="transparent" play="true" loop="false" menu="false" 
                    allowscriptaccess="never" allowfullscreen="true"/>
                    </p>
                </div>
                <div class="bshare-custom icon-medium share">
                    <div class="bsPromo bsPromo2"></div>
                    <a title="分享到" href="http://www.bShare.cn/" id="bshare-shareto" class="bshare-more">分享到</a>
                    <a title="分享到微信" class="bshare-weixin" href="javascript:void(0);"></a>
                    <a title="分享到新浪微博" class="bshare-sinaminiblog" href="javascript:void(0);"></a>
                    <a title="分享到QQ空间" class="bshare-qzone" href="javascript:void(0);"></a>
                    <a title="分享到新浪微博" class="bshare-sinaminiblog" href="javascript:void(0);"></a>
                    <a title="分享到人人网" class="bshare-renren" href="javascript:void(0);"></a>
                    <a title="更多平台" class="bshare-more bshare-more-icon more-style-addthis"></a><span class="BSHARE_COUNT bshare-share-count" style="float: none;">0</span>

                </div><script type="text/javascript" charset="utf-8" src="http://static.bshare.cn/b/buttonLite.js#style=-1&amp;uuid=&amp;pophcol=2&amp;lang=zh"></script><script type="text/javascript" charset="utf-8" src="http://static.bshare.cn/b/bshareC0.js"></script>
            </div>
        </div>
        <div class="info_model">
                    <div class="heading clearfix">
                        <h2>相关讲座</h2>
                        
                        <p class="key">
                            <span class="text">关键词： </span>
                            
                          
                            <a class="key_tag" href="/shipintag-1-3570.html" target='_blank'>唐山家庭教育网</a>
                            <a class="key_tag" href="/shipintag-1-10454.html" target='_blank'>心理健康</a>
                            <a class="key_tag" href="/shipintag-1-10455.html" target='_blank'>家庭教育</a>
                            
                        </p>
                        
                    </div>
                    <ul class="other_video clearfix">
                    	<c:if test="${not empty relate}"> 
							<c:forEach var="hv" items="${relate}" begin="0" end="2">
								<li>
									<a href="../Index/ShowNetCourse?netCourseId=${hv.netCourseId}" target="_blank">
										<img src="${hv.netCourseImgPath}" width="260" height="162" alt="${hv.netCourseName}" data-loaded="true">
										<span>${hv.netCourseName}</span><i></i>
									</a> 
								</li>
							</c:forEach>
						</c:if>
                        <!-- 
                        <li><a href="/shipinnr-14232.html" target="_blank"><img src="http://pic.87870.com/upload/images/vr87870/2016/9/18/th_260x159_cf53ac0b-21a6-49f2-b48a-427dea962718.jpg" width="260" height="162" alt="VR谁有料我来曝一曝 87870《VR一周曝》精彩继续" data-loaded="true"><span>VR谁有料我来曝一曝 87870《VR一周曝》精彩继续</span><i></i></a> </li>
                        <li><a href="/shipinnr-13712.html" target="_blank"><img src="http://pic.87870.com/upload/images/vr87870/2016/9/1/th_260x159_a3f10629-db2e-494e-8991-6cd7341b74e1.jpg" width="260" height="162" alt="【87870原创】2016VR-Park生态大会盛况" data-loaded="true"><span>【87870原创】2016VR-Park生态大会盛况</span><i></i></a> </li>
                        <li><a href="/shipinnr-12934.html" target="_blank"><img src="http://pic.87870.com/upload/images/vr87870/2016/8/9/th_260x159_8b079ec1-f9b5-49ab-9002-c655c34853b2.jpg" width="260" height="162" alt="快上车！87美女主持人带你逛2016洛杉矶VRLA峰会" data-loaded="true"><span>快上车！87美女主持人带你逛2016洛杉矶VRLA峰会</span><i></i></a> </li>
                        -->
                    </ul>
                </div>
        <div class="info_ad">

                </div>
       <!-- 添加评论 -->
                <div class="add_word">
                    <p class="title">我有话说：</p>
                    <textarea id="words" class="words txb_Comment" placeholder="我有话要说......"></textarea>
                    <input type="button" class="send btn_SendComment" id="btn_commentadd" onclick="addcomment()" value="评论" />
					<input type = "hidden" id="hiddencsrf" name="${_csrf.parameterName}" value="${_csrf.token}"/> 
					<input type = "hidden" id="commentTypeId" name="commentTypeId" value="4"/>
                </div>
                <!-- 评论列表 -->
                <div class="comment mt10" id="commentlistall">
                    <h2 class="comment_heading">
                        <span class="title">最新评论</span>
                    </h2>
                </div>
                <div class="comment commMore1" id="nomore" style="display:none;"><a href="javascript:;">没有更多评论了</a></div>
                <div class="comment commMore2" id="initmore" style="display:none;"><a href="javascript:;" class="commMoreA">加载更多</a></div>
            <span id="cmtindex" style="display:none;">1</span>
            <input type="hidden" id="cmdpagecount" value="0" />
            </div>
            <div class="g_right">
                <div class="ranking_list"></div>
                <div class="video-ranking">
    <h2 class="v-h2">最新讲座</h2>
    <ul class="v-ranking">
    <!-- 
        <c:if test="${not empty recommend}"> 
			<c:forEach var="hv" items="${recommend}" begin="0" end="9" varStatus="status">
				<a class="v-rLink  v-rLinkb" href="../Index/ShowNetCourse?netCourseId=${hv.netCourseId}" target="_blank">
					<c:if test="${status.index<3}"> 
	        		<span class="v-rTop v-rTop3">${status.index+1}</span>
	        		</c:if>
	        		<c:if test="${status.index>=3}"> 
	        		<span class="v-rTop">${status.index+1}</span>
	        		</c:if>
	        		<span class="v-rTxt">${hv.netCourseName}</span>
	        		<c:if test="${status.index==0}"> <div class="v-rImg" style="display: block;"></c:if>
	        		<c:if test="${status.index>0}"> <div class="v-rImg" style=""></c:if>
	        			<img width="250" height="154"  src="../images/no.jpg" data-src="${hv.netCourseImgPath}"  alt="${hv.netCourseName}"/>
	        		</div>
	        	</a>
			</c:forEach>
		</c:if>
	 -->
    </ul>
</div>
<script type="text/javascript">
    jQuery(function ($) {
    	$.ajax({
	        url: "../Index/getHotNetCourse",
	        data: {pageNumber: 1, pageSize: 10},
	        dataType: "json",
	        beforeSend: function () {
	            //$('#morelite').show();
	            //$('.news_botword').html("");
	        },
	        success: function (data) {
	            if (data != undefined && data.code == "succ") {
	            	var hotlist="";
	            	for (var i = 0; i < data.rows.length; i++) {
	            		var li = "<li> <a class='v-rLink";
	            		if (i == 0)
	            			li += " v-rLinkb";
	            		li += "' href='../Index/ShowNetCourse?netCourseId=" + data.rows[i].netCourseId + "' target='_blank' title='" + data.rows[i].netCourseName + "'>";
	            		li += "<span class='v-rTop";
	            		if (i < 3)
	            			li += " v-rTop3"
	            		li += "'>" + (i+ 1) + "</span><span class='v-rTxt'>" + data.rows[i].netCourseName + "</span>";
	            		li += "<div class='v-rImg' style='" + (i == 0 ? "display: block;" : "") + "'><img src='" + (data.rows[i].netCourseImgPath == null? "../images/1.jpg" : data.rows[i].netCourseImgPath) + "' ></div></a></li>";
	            		hotlist += li;
	            	}
	            	$('.v-ranking').html(hotlist);
	            	$('.v-ranking li').mouseover(function () { 
	            		var $this = $(this);
	            		$this.find('.v-rLink').addClass('v-rLinkb');
	            		$this.find('.v-rImg').show();
	            		$this.siblings('li').find('.v-rLink').removeClass('v-rLinkb');
	            		$this.siblings('li').find('.v-rImg').hide();
	            	});
	            	$('.video-ranking').anchor();
	            }
	        },
	        error: function (xhr, errorType, error) {
	            //alert(error);
	            return false;
	        }
	    });
        $('.video-ranking').anchor();
    });
    </script>
            </div>
</div>
</div>
    <script src="../js/videoDetails.js"></script>
    <link rel="stylesheet" type="text/css" href="../css/style-pl.css" />
    <script src="../js/Comment.js?v=1.0.1" type="text/javascript" ></script>

    
    <%@include file="footer.jsp" %>
    
    <script src="http://www.87870.com/2016/js/loadpublicfun.js"></script>
    
     
    
    <div class="displayN">
        <script>
            var _hmt = _hmt || [];
            (function () {
                var hm = document.createElement("script");
                hm.src = "//hm.baidu.com/hm.js?d913c25b7129c7ba3226975f9ea2dc18";
                var s = document.getElementsByTagName("script")[0];
                s.parentNode.insertBefore(hm, s);
            })();
        </script>
       <script src="http://s4.cnzz.com/z_stat.php?id=1256099334&web_id=1256099334" language="JavaScript"></script>
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
