<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="baidu-site-verification" content="9C9eVfwjyt">
	<title>
		唐山家庭教育网校园之窗
	</title>
	<meta name="keywords" content="唐山家庭教育网">
	<meta name="description" content="唐山家庭教育网">
	<link href="../css/style.css" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="../css/news.css">
	<link rel="stylesheet" type="text/css" href="../css/style_4_search_forum.css?FEK" />
    <script charset="utf-8" src="../js/v.js"></script>
	<script src="../js/hm.js"></script>
	<script src="../js/hm(1).js"></script>
	<script type="text/javascript" src="../js/jquery.js"></script>
    <script src="../js/loadcommon.js" type="text/javascript"></script>
    <script src="../js/lazyloadimg.js"></script>
	<script src="../js/rightCommon.js" type="text/javascript"></script> 
	<script type="text/javascript">
$(document).ready(function () {
    window.setTimeout(function () {
        if ($(".newstop >a").length > 0) {
            $(".newstop >a").each(function () {
                //$(this).removeClass("newtophov");
                if ($(this).attr("data-id") == $("#hiddencid").val()) {
                    $(this).addClass("newtophov");
                }
            });
        }
    }, 10);
    loadData(1);

	var parm = { pageNumber: 1, pageSize: 10000 };
	bindSelect("countyId", "../Index/getAllCounty", parm, "countyId", "countyName", "", "", "全部区县");
	bindSelect("schoolId", "../Index/getAllSchool", parm, "schoolId", "schoolName", "", "", "全部学校");
});

function initdata() {
	var url = "../Index/getAllSchool";

    var parms;
    parms = {
        sort: $("#hiddenorderby").val(),
        pageNumber:$("#PageIndex").val(),
        pageSize: $("#PageSize").val(),
        dt: new Date()
    };
    extendTalbeParams(parms, "searchform");
/*
	var parms = $('#searchform').serialize();
	parms += "&pageNumber=" + $("#PageIndex").val() + "&pageSize=" + $("#PageSize").val();
	alert(parms);
*/   
    $.ajax({
        url: url,
        type: "post",
        data: parms,
        dataType: "json",
        contentType:"application/x-www-form-urlencoded;charset=utf-8",
        beforeSend: function () {
            //$('#morelite').show();
            //$('.news_botword').html("");
        },
        success: function (data) {
            if (data != undefined) {
                if (data.code == "succ") {
                    var listdata = data.rows;
                    var pageSize = parseInt($("#PageSize").val());
                    var pageCount = Math.floor((data.total + pageSize - 1) / pageSize); 
                    $("#PageCount").val(pageCount);
                    $("#TotalCount").val(data.total);//TotalCount
                    
                    $('.news_botword').html("");
                    
                    $(listdata).each(function (index, lista) {
                        var url = '../School/' + lista.schoolId;
                        var title = lista.schoolName;
                        var imageurl = lista.schoolLogo == null ? "../images/no.jpg" : lista.schoolLogo;
                        var colorclass = 'bgcolor1';
                        var strcolorname = '';
                        var strcolorsize = '';
                        var html = '';
                        
                        //var discuss = '';
                        //if (lista.discuss.length > 0) {
                        //    discuss = lista.discuss;
                        //}
                        //var click = '';
                        //if (lista.click.length > 0) {
                        //    click = lista.click;
                        //}
                        html += '<div class="newsli">';
                        html += '<div class="newsliimg"><a href="' + url + '" target="_blank"><img src="../images/no.jpg"  data-src="'+ imageurl +'"></a></div>';
                        html += '<div class="newsliR">';
                        html += '<div class="newsliRtitle">';
                        html += '<span class="newlb" style="background-color:' + strcolorsize + ';display:none;">' + strcolorname + '</span>';
                        html += '<a href="' + url + '" target="_blank">' + title + '</a>';
                        html += '</div>';
                        html += '<div class="nresliRtime">2016-09-01</div>';
                        html += '<div class="newsliP">' +cutstr(lista.schoolIntroduction,170) + '</div>';
                        html += '<div class="newsliBgjc">';
                        html += '<div class="newsliBgjLeft"><span>地址：</span> ' + lista.schoolAddress + '</div>';
                        html += '<div class="newsliBgjcRight">';/*
                        html += '<div class="look_R">';
                        html += '<span class="lookbg2"></span>';
                        html += '<span class="looknum"><em>' + 53 + '</em></span>';
                        html += '</div>';
                        html += '<div class="look_R">';
                        html += '<span class="lookbg"></span>';
                        html += '<span class="looknum">' + 621 + '</span>';
                        html += '</div>';*/
                        html += '</div>';
                        html += '</div>';
                        html += '</div>';
                        html += '</div>';

                        $('.news_botword').append(html);
                    });
                }
                else {
                    return false;
                }
            } else {
                return false;
            }
        },
        error: function (xhr, errorType, error) {
            //alert(error);
            return false;
        },
        complete: function () {
            //$('#morelite').hide();
            loadhtml();
            loadingImg();
        }
    });
}

function onSearchSchool() {
	loadData(1);
}
	</script>   
</head>
<body class="newbodybg">
    <input type="hidden" id="columnindex" value="1">
    <input type="hidden" id="hiddenurls" value="http://wap.87870.com/xinwen-0103.html">
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
    <div id="header"><%@include file="header.jsp" %></div>

    <div class="gamebox clearfix">
    
    <input type="hidden" id="hiddencid" value="${curSubModule.moduleId}">
    
	<div class="news_nei">
		<div class="new_banner"></div>
            <div class="g_left" style="min-height:885px;">
                <div class="newstop">
                	<a href="../Index/School" data-id="${curTopModule.moduleId}">全部学校</a>
                	<!-- 未来这里显示所有学校类别
                	
		 			-->
<div >
		 			<form id="searchform" method="post" autocomplete="off" >
							<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
							县区<select id="countyId" name="[Equal]countyId"></select> 
							学校<select id="schoolId" name="[Equal]schoolId"></select>
							<!--<input type="text" id="searchinput" name="[Like]schoolName">-->
						    <input type="button" value="查询" id="searchbuton" onClick="onSearchSchool()">
					</form>
</div>
                </div>
                <div class="news_botword">
					<div style="text-align:center; width:100%; margin-top:20px;">加载中...</div>
				</div>
                <div class="newspage" id="morelite" style="display: none;">数据加载中...</div>
                <div class="newspage" id="newspage"></div>
                <input type="hidden" id="TotalCount" value="0">
                <input type="hidden" id="PageSize" value="8">
                <input type="hidden" id="PageCount" value="1">
                <input type="hidden" id="PageIndex" value="1">
                
                <input type="hidden" id="hiddenorderby" value="1">
            </div>

            <!--<script src="../js/sdlist.js" type="text/javascript"></script>-->
            <div class="g_right">
                <div class="gr_box">
                    <div class="news_bannimg">
                        <a href="http://z.jd.com/project_details.action?projectId=63609" target="_blank" title="热点新闻">
							<img src="../images/db7bd143-6385-47ba-8f8a-1ae94a7bcd2f.jpg">
						</a>
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
            <div class="g_lbanner" style="display: none;"></div>            
		</div>
    </div>
    <!--bot-->
    <script src="../js/hotnewslist.js" type="text/javascript"></script>
    <%@include file="footer.jsp" %>
</body>
</html>