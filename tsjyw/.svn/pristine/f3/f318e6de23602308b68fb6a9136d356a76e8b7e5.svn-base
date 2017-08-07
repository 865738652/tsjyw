<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <script type="text/javascript">
        var wapurl = "http://wap.87870.com/xiazai.html";
        if (/(iPhone|iPod|iOS)/i.test(navigator.userAgent)) {
            window.location.href = wapurl;
        } else if (/(Android)/i.test(navigator.userAgent)) {
            window.location.href = wapurl;
        }
    </script>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta name="baidu-site-verification" content="OtlGx8W5Eu" />
    <title>唐山家庭教育网</title>
    <meta name="keywords" content="心理,家教,志愿者" />
    <meta name="description" content="唐山家庭教育网" />
    <link href="../css/style.css" rel="stylesheet" />
    <link rel="stylesheet" type="text/css" href="../css/game.css" />
    <script type="text/javascript" src="../js/jquery.js"></script>
    <!-- <script type="text/javascript" src="../js/zzsc.js"></script> -->
    <script type="text/javascript" src="../js/game.js"></script>
    <script src="../js/lazyloadimg.js"></script>
    <script type="text/javascript">
       $(document).ready(function () {
       	   initdata();
           //lazyLoadImg();
           
       });
       
       function onSelectAgeLevel(obj, id) {
       		$("#ageLevelId").val(id);
       		$("#ageLevel a").each(function() {
       			$(this).removeClass("cur");
       		});
       		$(obj).addClass("cur");
       		$("#PageIndex").val(1);
       		initdata();
       }
       
       function onSelectAskQuestionType(obj, id) {
       		$("#askQuestionTypeId").val(id);
       		$("#questionType a").each(function() {
       			$(this).removeClass("cur");
       		});
       		$(obj).addClass("cur");
       		$("#PageIndex").val(1);
       		initdata();
       }
       
       function onPager(pageNumber) {
       		$("#PageIndex").val(pageNumber)
       		initdata();
       }
       
       function initdata() {
			var url = "../Index/searchVolunteer";
		    var parms = {
		            pageNumber:parseInt($("#PageIndex").val()),
		            pageSize: parseInt($("#PageSize").val()),
		            ageLevelId:parseInt($("#ageLevelId").val()),
		            askQuestionTypeId:parseInt($("#askQuestionTypeId").val()),
		            dt: new Date()
		        };		    
		    
		    $.ajax({
		        url: url,
		        data: parms,
		        dataType: "json",
		        beforeSend: function () {
		            //$('#morelite').show();
		            //$('.news_botword').html("");
		        },
		        success: function (data) {
		            if (data != undefined && data.code == "succ") {
		                var listdata = data.rows;
	                    	                    
	                    $('.game-list').html("");
	                    var start = (parms.pageNumber - 1) * parms.pageSize;
	                    var end = start + parms.pageSize <= listdata.length ? start + parms.pageSize : listdata.length;
	                    for (var index = start; index < end; index++) {
	                    	var lista = listdata[index];
	                        var url = '../Index/ShowVolunteer?userId=' + lista.userId;
	                        var title = lista.userName;
	                        var imgurl = lista.userPhotoUrl == null ? "../images/no.jpg" : lista.userPhotoUrl;
	                        var intro = lista.volunteerIntro;
	                        
	                        var html = '';
	                       
	                        html += '<li><a href="' + url + '" target="_blank">';
	                        html += '<img class="game-img" src="../images/loading.gif"  data-src="' + imgurl + '" width="260" height="163" />';
	                        html += '<span class="game-text">' + title + '</span>';
	                        html += '<div class="game-infoWrap">';
	                        html += '<div class="opacity"></div>';
	                        html += '<div class="game-info">';
	                        html += '<div class="game-tit ios-tit">';
	                        html += '<span class="mobileType">提问</span>';
	                        html += '<span class="game-class">详情</span>';
	                        html += '</div>';
	                        html += '<p class="game-des">' + intro + '</p>';
	                        html += '</div></div></a></li>';
	                        
	                        if (index % 3 == 2)	
	                        	html += '<li class="line clearfix"></li>';	
	                       
	                        $('.game-list').append(html);		                        
	                    }
	                    
	                    var pageSize = parseInt($("#PageSize").val());
	                    var pageNumber = parseInt($("#PageIndex").val());
	                    var pageCount = Math.floor((data.total + pageSize - 1) / pageSize); 
	                    $("#PageCount").val(pageCount);
	                    $("#TotalCount").val(data.total);//TotalCount
	                    
	                    $('.pager').html(""); 
	                    var htm = "";
	                    if (data.total <= pageSize) 
	                    	return;
	                    var begin = (pageNUmber - 5) < 1 ? 1 : (pageNUmber - 5);
	                    var end = (pageNumber + 4) > pageCount ? pageCount : (pageNumber + 4);
	                    if (begin > 1)
	                    	htm += '<a href="#" onclick=onPager(1)>首页</a> ';
	                    	
	                    for (var p = begin; p <= end; p++) {
	                    	if (p == pageNumber)
	                    		htm += '<span>' + p + '</span>'
	                    	else
	                    		htm += '<a href="#" onclick=onPager(' + p + ')>' + p + '</a> ';
	                    }
	                    if (end < pageCount)
	                    	htm += '<a href="#" onclick=onPager(' + pageCount + ')>尾页</a> ';
	                    $('.pager').html(htm);
		            } else {
		                return false;
		            }
		        },
		        error: function (xhr, errorType, error) {
		            //alert(error);
		            return false;
		        },
		        complete: function () {
		            loadingImg();
		        }
		    });
		} 
    </script>
</head>
<body class="game-body">
    <input type="hidden" id="columnindex" value="2" />
    
    <input type="hidden" id="TotalCount" value="0">
    <input type="hidden" id="PageSize" value="18">
    <input type="hidden" id="PageCount" value="1">
    <input type="hidden" id="PageIndex" value="1">
    
    <input type="hidden" id="hiddenorderby" value="1">
    
    <input type="hidden" id="ageLevelId" value="-1"/>
    <Input type="hidden" id="askQuestionTypeId" value="-1"/>
    
    <div id="header"><%@include file="header.jsp" %></div>
    <div class="banner bc">
        
    </div>
    <div class="arrow bc">
        <a href="../Index/">首页</a> &gt; 教师志愿者
    </div>
       
    <div class="w1170 bc clearfix">
        <div class="gameList-wrap">
            <ul class="game-list">
            	<li>
                    <a href="http://d.87870.com/xiazainr-9439.html" target="_blank">
                        <img class="game-img" src="../images/loading.gif" data-src="http://pic.87870.com/upload/images/vr87870/2016/3/15/th_260x163_575ea0c1-26c0-4cd5-938e-65a2e1e5bbd8.jpg" width="260" height="163" alt="韩国全州"/>
                        <span class="game-text">韩国全州</span>
                        <div class="game-infoWrap">
                            <div class="opacity"></div>
                            <div class="game-info">
                                
                                <div class="game-tit ios-tit">
                                    <span class="mobileType">提问</span>
                                    <span class="game-class">详情</span>
                                </div>
                                <p class="game-des">
                                    全州市，韩国西南部城市，位于首尔以南232公里处，面积198.22平方公里，人口55万，是全罗北道政府所在地。
                                </p>
                            </div>
                        </div>
                    </a>
                </li>
                <li class="line clearfix"></li>                
            </ul>
            <div class="pager">
            </div>
        </div>
        <div class="slide-bar">
            <div class="selected-wrap">
                <h2 class="selected-h2">挑选志愿者</h2>
                <h3 class="selected-h3">成长阶段</h3>
                <div class="select-h3Wrap clearfix" id='ageLevel'>
                	<a href="#" class="cur" onclick="onSelectAgeLevel(this, -1)">全部</a>
                	<c:forEach var="ageLevel" items="${ageLevels}"> 
	 					<a href="#" onclick="onSelectAgeLevel(this, ${ageLevel.ageLevelId})">${ageLevel.ageName}</a>
	 				</c:forEach>
                </div>
                <h3 class="selected-h3">擅长领域</h3>
                <div class="select-h3Wrap clearfix" id='questionType'>
                	<a href="#" class="cur" onclick="onSelectAskQuestionType(this, -1)">全部</a> 
                	<c:forEach var="t" items="${askQuestionTypes}"> 
	 					<a href="#" onclick="onSelectAskQuestionType(this, ${t.askQuestionTypeId})">${t.askQuestionTypeName}</a>
	 				</c:forEach>                
                </div>
                
                <div class="classWrap select-h3Wrap clearfix"></div>
            </div>
            <div class="selected-wrap">
                <h2 class="selected-h2">热点话题</h2>
                <ul class="v-ranking">
                	<c:forEach var="ha" items="${hotArticles}" begin="0" varStatus="status"> 
	 					<c:if test="${status.index==0}"><a class="v-rLink v-rLinkb" href="../Index/ShowArticle?articleId=${ha.articleId}" target="_blank"></c:if>
	 					<c:if test="${status.index!=0}"><a class="v-rLink" href="../Index/ShowArticle?articleId=${ha.articleId}" target="_blank"></c:if>
	 						<c:if test="${status.index<3}"><span class="v-rTop v-rTop3">${status.count}</span></c:if>
	 						<c:if test="${status.index>=3}"><span class="v-rTop">${status.count}</span></c:if>
                            <span class="v-rTxt">${ha.articleTitle}</span>
                            <div class="v-rImg" style="display: block;">
                                <img width="250" height="154" src="../images/loading.gif" data-src="${ha.imagePath}" alt="${ha.articleTitle}" />
                            </div>
                        </a>
	 				</c:forEach>                    
                </ul>
            </div>
        </div>
    </div>
    
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
