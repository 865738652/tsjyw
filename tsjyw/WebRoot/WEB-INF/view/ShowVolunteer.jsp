<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta name="baidu-site-verification" content="OtlGx8W5Eu" />
    <title>唐山家庭教育网</title>
    <meta name="keywords" content="唐山家庭教育网"  />
    <meta name="description" content="唐山家庭教育网" />
    <link href="../css/style.css" rel="stylesheet"/>
    <link rel="stylesheet" type="text/css" href="../css/game.css"/>
    <script type="text/javascript" src="../js/jquery.js"></script>
    <script type="text/javascript" src="../js/jquery.easing.1.3.js"></script>
    <script type="text/javascript" src="../js/lazyloadimg.js"></script>
    
    <script type="text/javascript" src="../js/roundabout.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            loadingImg();
            $('#gameInfo-contain').roundabout({
                shape: 'square',
                //minScale:1,
                minOpacity: 1,
                btnNext: '#next',
                btnPrev: '#prev'
            });
           
        });

    </script>


</head>
<body class="game-body">    

<script type="text/javascript">

</script>
        
    <input type="hidden" id="columnindex" value="1" />
    <input type="hidden" id="hiddenpid" value="${volunteer.userId}" />
    <script type="text/javascript">
        var wapurl = "http://www.tsjtjywcom/";
        if ($("#hiddenpid").length > 0) {
            if ($("#hiddenpid").val().length > 1) {
                wapurl = "http://www.tsjtjywcom/xiazainr-" + $("#hiddenpid").val() + ".html";
            }
        }
        if (/(iPhone|iPod|iOS)/i.test(navigator.userAgent)) {
            window.location.href = wapurl;
        } else if (/(Android)/i.test(navigator.userAgent)) {
            window.location.href = wapurl;
        }
        
    </script>
        
    <div class="qp_pageprompt">
        <!-- 操作频繁请重试 容器 -->
    </div>
    <div id="header"><%@include file="header.jsp" %></div>
    
    <div class="arrow bc">
        <a href="../Index/">首页</a> &gt; 
        <a href="../Index/Volunteer">志愿者</a> &gt; 
        ${volunteer.userName}老师
    </div>
    <div class="w1170 bc">
        <div class="gameInfo-wrap clearfix">
            <div class="gameInfo-img">
            	<c:if test="${not empty volunteer.userPhotoUrl}"><img src="../images/loading.gif" data-src="${volunteer.userPhotoUrl}" width="420" height="265" alt=" ${volunteer.userName}"/></c:if>
                <c:if test="${empty volunteer.userPhotoUrl}"><img src="../images/loading.gif" data-src="../images/no.jpg" width="420" height="265" alt=" ${volunteer.userName}"/></c:if>
            </div>
            <div class="gameInfo-note">
                <h1 class="gameInfo-h1">${volunteer.userName} 老师</h1>
                <div class="gameInfo-comment">
                    <i class="gameInfo-tag"></i>
                    <p>
                        <span class="color-orange">教师简介：</span>${volunteer.volunteerIntro}
                    </p>
                </div>
                <div class="gameInfo-param">
                    <span class="param w195">
                        <span class="param-tit">姓名：</span>${volunteer.userName}
                    </span>
                    <span class="param w180">
                        <span class="param-tit">年龄：</span>${age}
                    </span>
                    <span class="param w130">
                        <span class="param-tit">性别：</span><c:if test="${volunteer.userGender==0}">男</c:if><c:if test="${volunteer.userGender==1}">女</c:if>
                    </span>
                    <span class="param w195">
                        <span class="param-tit">专业：</span><c:if test="${not empty volunteer.askQuestionTypes}">${volunteer.askQuestionTypes[0].askQuestionTypeName}</c:if>
                    </span>
                    <span class="param w180" title="Cedar Fair">
                        <span class="param-tit">教龄：</span>10年以上
                    </span>
                    <span class="param w130">
                        <span class="param-tit">位置：</span><c:if test="${not empty volunteer.volunteerPCC}">${volunteer.volunteerPCC}</c:if>
                    </span>
                    <span class="param w195">
                        <span class="param-tit">星级：</span><span class="paramGrade paramGrade5" ></span>
                    </span>
                    <span class="param w180" title="无">
                        <span class="param-tit">微信：</span>${volunteer.userWeixin}
                    </span>
                    <span class="param w130">
                        <span class="param-tit">QQ：</span>${volunteer.userQq}
                    </span>
                </div>
                <div class="gameInfo-down">                    
                    <a id="lbt_download" class="download qp_download" href="../AskQuestionManage/AskQuestion?replyId=${volunteer.userId}">我要提问</a>
                    <span class="dwonloaded">9</span>
                    <span class="viewed">597</span>
                </div>
                
                <div class="QRcode">
                    <img src="http://qr.topscan.com/api.php?text=http://wap.87870.com/dl.aspx?id=13846" alt=""/>
                    扫描二维码<br/>与名师微信互动
                </div>                
            </div>
        </div>
        <div class="gameInfo-content clearfix">
            <div class="gameInfo-h2Wrap">                
                <div class="gameInfo-h2Contain">
                    <h2 class="gameInfo-h2"><span>教师介绍</span></h2>
                    <div class="gameInfo-info">
                        <p>${volunteer.volunteerIntro}</p>
                    
                        <div class="gameInfo-tags">
                            专业领域：&emsp;
                       <c:forEach var="t" items="${volunteer.askQuestionTypes}">
                       <a href="#" target="_blank">${t.askQuestionTypeName}</a> 
                       </c:forEach> 
                       </div>                       
                    </div>
                </div>
                
                <!-- 添加评论 -->
                <div class="add_word">
                    <p class="title">我有话说：</p>
                    <textarea id="words" class="words txb_Comment" placeholder="我有话要说......"></textarea>
                    <input type="button" class="send btn_SendComment"  id="btn_commentadd" onclick="addcomment()" value="提交" />
					<input type = "hidden" id="hiddencsrf" name="${_csrf.parameterName}" value="${_csrf.token}"/> 
					<input type = "hidden" id="commentTypeId" name="commentTypeId" value="3"/>
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
            <div class="gameInfo-like">
                <h2 class="gameInfo-likeH2">
                    <span>猜您关注</span>
                </h2>
                <ul class="gameInfo-likeList">
                    <c:forEach var="r" items="${recommend}" begin="0" end="3">
                    <li>
                        <a href="../Index/ShowVolunteer?userId=${volunteer.userId}" target="_blank">
                        	<c:if test="${not empty r.userPhotoUrl}">
                            <img width="260" height="160" src="../images/loading.gif" data-src="${r.userPhotoUrl}" alt="${r.userName}" />
                            </c:if>
                            <c:if test="${empty r.userPhotoUrl}">
                            <img width="260" height="160" src="../images/loading.gif" data-src="../images/no.jpg" alt="${volunteer.userName}" />
                            </c:if>
                            <span>${r.userName}老师</span>
                        </a>
                    </li>              
                    </c:forEach>      
                </ul>
            </div>
        </div>
    </div>
    
    <script type="text/javascript" src="../js/Comment.js?v=1.0.0.1"></script>
    <%@include file="footer.jsp" %> 
<script>

   (function () {

       var bp = document.createElement('script');

       bp.src = '//push.zhanzhang.baidu.com/push.js';

       var s = document.getElementsByTagName("script")[0];

       s.parentNode.insertBefore(bp, s);

   })();
 </script>
</body>
</html>
