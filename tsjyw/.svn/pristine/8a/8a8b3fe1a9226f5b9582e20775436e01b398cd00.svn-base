<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html data-dpr="2" style="font-size: 75px;">
		<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<!--编码格式-->
		<meta name="viewport" content="initial-scale=1, maximum-scale=3, minimum-scale=1, user-scalable=no">
		<!--禁止浏览器从本地计算机缓存访问页面内容，这样设定，访问者将无法脱机浏览-->
		<meta http-equiv="Expires" content="-1">
		<meta http-equiv="Pragma" content="no-cache">
		<meta http-equiv="Cache-Control" content="no-cache">
		<!--主题背景-->
		<meta name="theme-color" content="#ffffff">
		<!--禁止百度转码-->
		<meta http-equiv="Cache-Control" content="no-siteapp">
		<!--优先使用IE最新版和Chrome，避免IE使用兼容模式-->
		<meta http-equiv="X-UA-Compatible" content="IE=Edge, chrome=1">
		<!-- 启用 WebApp 全屏模式，伪装App离线应用，删除苹果默认的工具栏和菜单栏 -->
		<meta name="apple-mobile-web-app-capable" content="yes">
		<!--隐藏状态栏/设置状态栏颜色：只有在开启WebApp全屏模式时才生效。content的值为default | black | black-translucent 。-->
		<meta name="apple-mobile-web-app-status-bar-style" content="black-translucent">
		<!--忽略数字自动识别为电话号码/忽略识别邮箱-->
		<meta content="telephone=no,email=no" name="format-detection">

		<!-- 样式重置 -->
		<link rel="stylesheet" href="../newphone/articledetial/css/reset.css">
		<!-- lib.flexible -->
		<script src="http://hm.baidu.com/hm.js?225694c67b2c1d267db850fac9dd0170"></script><script src="http://hm.baidu.com/hm.js?7cd50153b76179187abf8de7bbac9871"></script>
		<script type="text/javascript" src="../newphone/articledetial/js/flexible.js"></script>
		<script type="text/javascript" src="../newphone/articledetial/js/flexible_css.js"></script><style>@charset "utf-8";html{color:#000;background:#fff;overflow-y:scroll;-webkit-text-size-adjust:100%;-ms-text-size-adjust:100%}html *{outline:0;-webkit-text-size-adjust:none;-webkit-tap-highlight-color:rgba(0,0,0,0)}html,body,div,dl,dt,dd,ul,ol,li,h1,h2,h3,h4,h5,h6,pre,code,form,fieldset,legend,input,textarea,p,blockquote,th,td,hr,button,article,aside,details,figcaption,figure,footer,header,hgroup,menu,nav,section{margin:0;padding:0}input,select,textarea{font-size:100%}table{border-collapse:collapse;border-spacing:0}fieldset,img{border:0}abbr,acronym{border:0;font-variant:normal}del{text-decoration:line-through}address,caption,cite,code,dfn,em,th,var{font-style:normal;font-weight:500}ol,ul{list-style:none}caption,th{text-align:left}h1,h2,h3,h4,h5,h6{font-size:100%;font-weight:500}q:before,q:after{content:''}sub,sup{font-size:75%;line-height:0;position:relative;vertical-align:baseline}sup{top:-.5em}sub{bottom:-.25em}a:hover{text-decoration:underline}ins,a{text-decoration:none}</style>
		<meta content="modeName=750-12" name="grid">
		<script type="text/javascript" src="../newphone/articledetial/js/makegrid.js"></script>
		<!-- lib.jquery -->
		<script type="text/javascript" src="../newphone/articledetial/js/jquery.min.js"></script>
        <script type="text/javascript" src="../newphone/articledetial/js/jwolf-1.2.js"></script>
        <script type="text/javascript" src="../newphone/articledetial/js/touch.min.js"></script>
        <script type="text/javascript" src="../newphone/articledetial/js/datecommon.js"></script>
		<title>文章详情</title>
		<meta name="author" content="Yufal">
		<!--global js-->
	    <script type="text/javascript" src="../newphone/articledetial/js/global.js"></script>
		<!--private css&&js-->
		<script type="text/javascript" src="../newphone/articledetial/js/TweenMax.min.js"></script>
		<link rel="stylesheet" href="../newphone/articledetial/css/details.css">
		<script type="text/javascript" src="../newphone/articledetial/js/details.js"></script>
		<!--component css&&js-->
		<link rel="stylesheet" href="../newphone/articledetial/css/sweetalert.css">
		<link rel="stylesheet" href="../newphone/articledetial/css/google.css">
		<script type="text/javascript" src="../newphone/articledetial/js/sweetalert.min.js"></script>
		<!--social-share component css&&js-->
		<link rel="stylesheet" href="../newphone/articledetial/css/share.min.css">
		<script type="text/javascript" src="../newphone/articledetial/js/jquery.share.min.js"></script>
		<script type="text/javascript" src="../newphone/articledetial/js/social-share.min.js"></script>
		<!--nativeShare component css&&js-->
		<link rel="stylesheet" href="../newphone/articledetial/css/nativeShare.css">
		<script type="text/javascript" src="../newphone/articledetial/js/nativeShare.js"></script>
        <script type="text/javascript">
            $(function () {
                $(".text-content p").each(function (index) {
                    if ($(this).find("iframe").length > 0) {
                        var ifurl = $(this).find("iframe").attr("src");
                        if (ifurl.indexOf(".mp4") > 0) {
                            $(this).remove();
                        }
                    }
                    var faceImg=$(this).find("img[title*='smiley-face']")
                    if (faceImg.length > 0) {
                        faceImg.width("20px").height("20px");
                    }
                });
            });
        </script>
	<link rel="stylesheet" type="text/css" href="../newphone/articledetial/css/mob-share.css"><style>.grid {box-sizing:content-box;padding-left: 0.32rem;padding-right: 0.32rem;margin-left: -0.24rem;}.grid:before,.grid:after{content: " ";display: table;}.grid:after {clear: both;}.grid [class^="col-"] {margin-left: 0.24rem;float: left;}.grid .col-1 {width: 0.56rem;}.grid .col-2 {width: 1.36rem;}.grid .col-3 {width: 2.16rem;}.grid .col-4 {width: 2.96rem;}.grid .col-5 {width: 3.7600000000000002rem;}.grid .col-6 {width: 4.5600000000000005rem;}.grid .col-7 {width: 5.36rem;}.grid .col-8 {width: 6.16rem;}.grid .col-9 {width: 6.960000000000001rem;}.grid .col-10 {width: 7.760000000000001rem;}.grid .col-11 {width: 8.56rem;}.grid .col-12 {width: 9.36rem;}</style></head>

	<body style="font-size: 24px;">
        
        

				<!--2级/3级 置顶导航-->
		<div id="navigation">
			<div id="nav-header" class="nav-header">
				<div class="nav-logo">
					<a href="WeChatNewArticle"><img src="../newphone/newphonearticle/img/h-logo.png"></a>
				</div>
				<div class="nav-menu">
					<img src="../newphone/articledetial/img/nav-menu.png">
				</div>
			</div>
			<div id="nav-mark" class="nav-menu-mark"></div>
			<div id="nav-menu" class="nav-menu-content">
				<ul class="menu-list">
	<li class="menu-item">
		<a href="WeChatNewArticle" target="_self">
			<img src="../newphone/articledetial/img/1-hot.png">
			<p>热门</p>
		</a>
	</li>
	<li class="menu-item">
		<a href="WeChatFamousTeacher" target="_self">
			<img src="../newphone/articledetial/img/2-industry.png">
			<p>名师</p>
		</a>
	</li>
	<li class="menu-item">
		<a href="WeChatVolunteer" target="_self">
			<img src="../newphone/articledetial/img/3-game.png">
			<p>志愿者</p>
		</a>
	</li>
	<li class="menu-item">
		<a href="WeChatNewHotAskQuestion" target="_self">
			<img src="../newphone/articledetial/img/4-video.png">
			<p>问答</p>
		</a>
	</li>
	<li class="menu-item">
		<a href=WeChatNoticeSystem" target="_self">
			<img src="../newphone/articledetial/img/5-topic.png">
			<p>通知</p>
		</a>
	</li>
	<c:choose>
		<c:when test="${empty sessionScope.userId}">
			<li class="menu-item">
				<a href="Login" target="_self">
					<img src="../newphone/articledetial/img/7-account.png">
					<p>登录</p>
				</a>
			</li>
		</c:when>
		<c:otherwise>
			<li class="menu-item">
				<a href="#" target="_self" onclick="cancelLogin()">
					<img src="../newphone/articledetial/img/7-account.png">
					<p>注销</p>
				</a>
			</li>
		</c:otherwise>
	</c:choose>
	
</ul>
</div>
</div>
	<script type="text/javascript">
		function cancelLogin()
		{
			$.ajax({
				url:"CancelLogin",
				type:"get",
				dataType:"json",
				success:function(data){
					if(data.code == "fail")
						alert("注销成功");
					location.reload();
				},
				error:function(){
					alter("注销成功");
				}
			});
		}
	</script>

		<!--2级/3级 置顶导航-->
		<div class="view">
            
			
			<!--正文内容-->
			<article>
				<!--正文标题-->
				<section style="float: left;">
					<h1 class="article-title">${article.articleTitle}</h1>
					<p class="article-title-description">
						<span><fmt:formatDate value='${article.articleTime}' pattern='yyyy-MM-dd'/></span>
					</p>
				</section>
                
				
				<!--正文导语-->
				<section style="float: left;">
					<p class="guide-text">
						导语：${article.articleAbstract}
						
					</p>
				</section>
				<!--正文详情-->
				<section class="text-content">
						${article.articleContent}<br/>
                  		<c:if test="${not empty images}">
			       			<c:forEach var="image" items="${images}">
			       				<img src="${image.attachUrl}" style="width:100%"/><br/>
			       				<br/><br/>
			       			</c:forEach>
			       		</c:if>
				</section>
				<!--正文收藏、点赞、转发-->
				
			</article>
			<div class="clear"></div>
            <!--推广-->
           <article>
               
			</article>
            <!--相关推荐-->
			<article>
				<h1 class="recommend-title">相关推荐</h1>

					<ul class="recommend">
						<!-- 
						<li>
							<a class="article_link" href="#" target="_self">
								<div class="item-description">
									<div class="description-title">    
										<p>神秘公司Magic Leap遭殃 原型产品因无法小型化被搁浅</p>
									</div>
									<div class="description-info">    
										<div class="info-source fl">        
											<span></span>        
											<span>昨天</span>    
										</div>    
										<div class="info-look-count fr">        
											<img class="fl" src="../newphone/articledetial/img/tlak-icon.png">        
											<p class="fl">11</p>    
										</div>
									</div>
								</div>
								<div class="item-picture">    
									<img src="../newphone/articledetial/img/e7ab3c3d-d56c-41dc-8b51-2c5c244c2f7a.jpg">
								</div>
							</a>
						</li>
						 -->
						<c:forEach var="relarticle" items="${relates}">
							<li>
								<a class="article_link" href="WeChatArticleDetial?articleId=${relarticle.articleId}" target="_self">
									<div class="item-description">
										<div class="description-title">    
											<p><c:out value="${relarticle.articleTitle}"/></p>
										</div>
										<div class="description-info">    
											<div class="info-source fl">        
												<span></span>        
												<span><fmt:formatDate value='${relarticle.articleTime}' pattern='yyyy-MM-dd'/></span>    
											</div>    
											<div class="info-look-count fr">        
												<img class="fl" src="../newphone/articledetial/img/tlak-icon.png">        
												<p class="fl"><c:out value="${relarticle.articleReadCount}"/></p>    
											</div>
										</div>
									</div>
									<div class="item-picture">    
										<img src="${relarticle.imagePath}">
									</div>
								</a>
							</li>
						</c:forEach>
						
					</ul>
                <!--<a href="javascript:;" id="load-more-xgtj">加载更多...</a>-->
			</article>
			<div class="clear"></div>

			<!--相关评论-->
			<!--
			<article>
				<h1 class="comment-title">相关评论(<span style="color: #f03232;">3</span>)</h1>
				
                
                    
                    <div class="comment-box-warn show-table">
					    <a class="warn-text" href="http://wap.87870.com/account/login.html?u=/newsdetail.aspx?id=17093"><span>登录</span>完成之后才可以评论哟！</a>
				    </div>
                
				
				
				<ul class="comment">
                    <li class="comment-li"><div class="comment-head"><img src="./文章详情_files/th_150x150_444d96f3-cda8-4b24-9387-187bb16f57a8.jpg" alt="波雅·汉库克"></div><div class="comment-info"><div class="description"><span class="user-name">波雅·汉库克</span><span class="comment-time">刚刚</span></div><div class="comment-text"><p>也难怪大家的眼睛都盯着它，毕竟当初太招摇了啊，融了那么多钱，肯定都要看它做出来什么产品呢</p></div>	<div class="comment-op"><div class="reply-box fr">	<img class="fl" src="./文章详情_files/comment-reply.png">	<span class="action-reply">回复</span></div><div class="zan-box fr" pid="120873"><img class="fl" src="./文章详情_files/comment-zan.png"><span class="fl">0</span></div></div></div><p class="clear"></p><ul class="reply-comment">  </ul><p class="clear"></p><div class="reply-textarea-box"><textarea class="reply-text-area reply-user-text" placeholder="在此输入回复内容..." maxlength="480"></textarea><div class="reply-user-op"><span>限制500个字</span><a class="action-reply-send" pid="120873" href="javascript:;">回复</a> </div></div></li><li class="comment-li"><div class="comment-head"><img src="./文章详情_files/Kj4Y4le-tS-ADcibAAGc1cbivT8890.jpg" alt="weihechuan"></div><div class="comment-info"><div class="description"><span class="user-name">weihechuan</span><span class="comment-time">刚刚</span></div><div class="comment-text"><p>还是尽快拿出让人可以公开测试的产品吧，这样才能证明一切</p></div>	<div class="comment-op"><div class="reply-box fr">	<img class="fl" src="./文章详情_files/comment-reply.png">	<span class="action-reply">回复</span></div><div class="zan-box fr" pid="120856"><img class="fl" src="./文章详情_files/comment-zan.png"><span class="fl">0</span></div></div></div><p class="clear"></p><ul class="reply-comment">  </ul><p class="clear"></p><div class="reply-textarea-box"><textarea class="reply-text-area reply-user-text" placeholder="在此输入回复内容..." maxlength="480"></textarea><div class="reply-user-op"><span>限制500个字</span><a class="action-reply-send" pid="120856" href="javascript:;">回复</a> </div></div></li><li class="comment-li"><div class="comment-head"><img src="./文章详情_files/noface.jpg" alt="87870用户"></div><div class="comment-info"><div class="description"><span class="user-name">87870用户</span><span class="comment-time">刚刚</span></div><div class="comment-text"><p>lololens毕竟背靠微软这座大树，技术能力和品牌价值比其他厂商不知道高到哪里去了</p></div>	<div class="comment-op"><div class="reply-box fr">	<img class="fl" src="./文章详情_files/comment-reply.png">	<span class="action-reply">回复</span></div><div class="zan-box fr" pid="120840"><img class="fl" src="./文章详情_files/comment-zan.png"><span class="fl">0</span></div></div></div><p class="clear"></p><ul class="reply-comment">  </ul><p class="clear"></p><div class="reply-textarea-box"><textarea class="reply-text-area reply-user-text" placeholder="在此输入回复内容..." maxlength="480"></textarea><div class="reply-user-op"><span>限制500个字</span><a class="action-reply-send" pid="120840" href="javascript:;">回复</a> </div></div></li><li style="display:none;" class="newsList-moreWrap"><a href="javascript:;" id="load-more-reply">加载更多...</a></li>
                      <li class="nocomment" style="">
                    <div>没有更多评论了！</div>
                </li>
				</ul>
			</article>
			-->
			<!--相关评论-->
		</div>
        <div id="goTop" style="display: block;">
			<img src="../newphone/articledetial/img/top.png">
		</div>
		<!--
        	描述：share components
        -->
			<!--描述：IOS默认浏览器分享-->
        	<script>
        	    mobShare.config({
        	        debug: false, // 开启调试，将在浏览器的控制台输出调试信息
        	        appkey: '197ed83fa4447', // appkey
        	        params: {
        	            url: $("#hiddenwebsiteurl").val() + 'newsdetail.aspx?id=' + $("#hiddenpid").val(), // 分享链接
        	            title: $("#hiddenname").val()+'-中国虚拟现实第一用户平台-虚拟现实游戏|Oculus rift眼镜|VR技术', // 分享标题
        	            description: '87870网站是国内Oculus rift、虚拟现实技术、虚拟现实游戏、虚拟现实眼镜等虚拟现实内容的第一平台,拥有国内最专业的Oculus riftVR虚拟现实设备社区、最新的虚拟现实游戏资源下载等,为国内虚拟现实交互、虚拟现实游戏开发Oculus rift极客发烧友聚集地。', // 分享内容
        	            pic: $("#hiddenimgurl").val(), // 分享图片，使用逗号,隔开
        	            reason: '',//自定义评论内容，只应用与QQ,QZone与朋友网
        	        },
        	        /**
                     * 分享时触发的回调函数
                     * 分享是否成功，目前第三方平台并没有相关接口，因此无法知道分享结果
                     * 所以此函数只会提供分享时的相关信息
                     * 
                     * @param {String} plat 平台名称
                     * @param {Object} params 实际分享的参数 { url: 链接, title: 标题, description: 内容, pic: 图片连接 }
                     */
        	        callback: function (plat, params) {
        	            $("#share-general-native").hide();
        	        }
        	    });
		</script>



		<!--描述：UC&&QQHD浏览器分享-->
		<div id="share-guide-native" class="black-wrp" style="display: none;">
			<div class="share-box" style="bottom: 0px;">
				<div id="share-box-list-native" class="share-box-list">
					<div id="nativeShare"></div>
					<script>
					    var config = {
					        url: $("#hiddenwebsiteurl").val()+'newsdetail.aspx?id=' + $("#hiddenpid").val(),// 分享的网页链接
					        title: $("#hiddenname").val()+'-中国虚拟现实第一用户平台-虚拟现实游戏|Oculus rift眼镜|VR技术',// 标题
					        desc: '87870网站是国内Oculus rift、虚拟现实技术、虚拟现实游戏、虚拟现实眼镜等虚拟现实内容的第一平台,拥有国内最专业的Oculus riftVR虚拟现实设备社区、最新的虚拟现实游戏资源下载等,为国内虚拟现实交互、虚拟现实游戏开发Oculus rift极客发烧友聚集地。',// 描述
					        img: $("#hiddenimgurl").val(),// 图片
					        img_title: '87870虚拟现实网-WAP2.0',// 图片标题
					        from: '87870虚拟现实网-WAP2.0' // 来源
					    };
					    var share_obj = new nativeShare("nativeShare", config);
					</script>目前该分享插件仅支持手机UC浏览器和QQ浏览器
				</div>
				<div class="share-box-title clearfix"></div>
				<div class="share-box-content clearfix"></div>
				<a class="share-box-cancel clearfix">取消</a>
			</div>
		</div>

		        
	

</body></html>