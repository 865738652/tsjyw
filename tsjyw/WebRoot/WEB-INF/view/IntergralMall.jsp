<%@ page language="java" import="java.util.*,edu.iasd.pojo.ViewModule" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>积分商城</title>

<meta name="keywords" content="">
<meta name="description" content="唐山家教网">
<meta name="generator" content="Discuz! X3.2">
<meta name="author" content="Discuz! Team and Comsenz UI Team">
<meta name="copyright" content="2001-2013 Comsenz Inc.">
<meta name="MSSmartTagsPreventParsing" content="True">
<meta http-equiv="MSThemeCompatible" content="Yes">
<!--<base href="http://bbs.87870.com/">-->
<!-- <base href="../"> --><link rel="stylesheet" type="text/css" href="../integralmall/css/style_4_common.css">
<script src="../integralmall/js/hm.js"></script>
<script type="text/javascript">
	var STYLEID = '4', STATICURL = 'static/', IMGDIR = 'template/87870pc/img', VERHASH = 'Noo', charset = 'utf-8', discuz_uid = '0', cookiepre = 'kfEE_2132_', cookiedomain = '', cookiepath = '/', showusercard = '1', attackevasive = '0', disallowfloat = 'newthread', creditnotice = '1|威望|,2|金钱|,3|贡献|', defaultstyle = '', REPORTURL = 'aHR0cDovL2Jicy44Nzg3MC5jb20vcGx1Z2luLnBocD9pZD1hdWN0aW9u', SITEURL = 'http://bbs.87870.com/', JSPATH = 'static/js/', CSSPATH = 'data/cache/style_', DYNAMICURL = '';
</script>
<script src="../integralmall/js/common.js" type="text/javascript"></script>
<link href="../css/style.css" rel="stylesheet">
<link href="../css/index.css" rel="stylesheet">
<!-- 分页函数 -->
<link rel="stylesheet" type="text/css" href="../css/kkpager_blue.css">
<script type="text/javascript" src="../js/kkpager.min.js"></script>
	<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript">
function getParameter(name) { 
	var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)"); 
	var r = window.location.search.substr(1).match(reg); 
	if (r!=null) return unescape(r[2]); return null;
}

//init
$(function(){
	getGoodsByAjax(1,appendGoods);
	var totalPage = <c:out value="${totalPage}"/>;
	var totalRecords = <c:out value="${totalRecords}"/>;
	var pageNo = getParameter('pno');
	if(!pageNo){
		pageNo = 1;
	}
	//生成分页
	//有些参数是可选的，比如lang，若不传有默认值
	kkpager.generPageHtml({
		pno : pageNo,
		//总页码
		total : totalPage,
		//总数据条数
		totalRecords : totalRecords,
		//链接前部
		hrefFormer : 'pager_test',
		//链接尾部
		hrefLatter : '.html',
		mode : 'click',//默认值是link，可选link或者click
		click : function(n){
			getGoodsByAjax(n,appendGoods);
			this.selectPage(n);
		  return false;
		}
	});
});

function getGoodsByAjax(pageNo,onsuccess)
{
	var type = $("#goodsType").val();
	$.ajax({
		url:"getGoodsByAjax",
		type:"get",
		dataType:"json",
		data:{pageNumber:pageNo,type:type},
		success:function(data){
			if(data.code == "succ")
			{
				onsuccess(data);
			}
			else if(data.code == "fail")
			{
				alert("加载数据失败");
			}
		},
		error:function(data){
			alert("数据加载失败");
		}
	});
}
function appendGoods(data)
{
	for(var i=0;i<data.data.length;i++)
	{
		$("#recenthot").find("h2").eq(0).after('<div class="shwLI"><div class="shliimg"><div class="number" title="先到先得">先到先得</div>'+
		'<a href="../Index/BuyGoods?goodsId='+data.data[i].goodsId+'" title="'+data.data[i].goodsName+'">'+
		'<img src="'+data.data[i].goodsImagaPath+'"/></a></div>'+
		'<p class="shname"><a href="../Index/BuyGoods?goodsId='+data.data[i].goodsId+'" target="_blank">'+data.data[i].goodsName+'</a></p>'+
		'<p class="sh_money"><span>竞&nbsp;&nbsp;&nbsp;拍：</span><em>'+data.data[i].goodsPrice+'</em> 金钱</p>'+
		'<p class="a_ct" id="vd_11021" style="display:none;">'+
		'<a href="../Index/BuyGoods?goodsId='+data.data[i].goodsId+'" target="_blank" style="background-image:url(source/plugin/auction/images/auction.png);">查看详情››</a></p>'+
		'<p id="time_11021" class="sh_button">即将开始</p></div>');
	}
	
	
}



</script>
<!-- /分页函数 -->
<meta name="application-name" content="唐山教育网">

<meta name="msapplication-tooltip" content="唐山教育网">

<meta name="msapplication-task" content="name=官网首页;action-uri=http://bbs.87870.com/portal.php;icon-uri=http://bbs.87870.com/template/87870pc/img/portal.ico">
<meta name="msapplication-task" content="name=官网首页;action-uri=http://bbs.87870.com/forum.php;icon-uri=http://bbs.87870.com/template/87870pc/img/bbs.ico">



<script src="../integralmall/js/jquery-1.8.3.min.js" type="text/javascript"></script>
<script src="../integralmall/js/jquery.nava.js" type="text/javascript"></script>
<script type="text/javascript">


function showAll(){
        var div=document.getElementById("yhmUL");
        if(div.style.display=="block")
div.style.display="none";
else
//div.style.opacity=1;
div.style.display="block";
    }
function fondser(){
var bdiv=document.getElementById("searchinput");
var search_text=document.getElementById("searchinput").value;
//if(bdiv.style.display=="block"){
if(search_text){
    var base_url = document.getElementById("baseurl").attributes["data-value"].value;
	var search_url = base_url+'search.php?mod=forum&searchid=1&orderby=lastpost&ascdesc=desc&searchsubmit=yes&kw='+search_text;
    var form = document.getElementById("searchform");
  form.submit();
    }
//bdiv.style.display="none";
//}
//else{
bdiv.style.display="block";
        //}
}
jQuery(document).ready(function(){
//底部向上滚动
        var boxLeft = jQuery(".wp").offset().left;
jQuery(".fixedul").css({"left":boxLeft+1210+"px"})

jQuery(".bli2").mouseenter(function(e) {
            jQuery(".wximg").show();
        }).mouseleave(function(){
 jQuery(".wximg").hide();
});

jQuery(window).scroll(function(){
if(jQuery(window).scrollTop()>100){
jQuery(".bli5").fadeIn();
}
else{
jQuery(".bli5").hide();
}
})
jQuery(".bli5").click(function(){
jQuery("html, body").animate({ scrollTop:0},500);
})
})
</script>


</head>

<body id="nv_plugin" class="pg_auction comiis_wide kmfn" onkeydown="if(event.keyCode==27) return false;"><div id="baseurl" data-value="http://bbs.87870.com/" style="display:none;"></div>


<input type="hidden" value="${goodsType}" id="goodsType"/>




<div id="header">
	<%@include file = "header.jsp" %>
</div>
<div id="ajaxwaitid"></div>

<div id="wp" class="wp">
    <link href="../integralmall/css/style_auction.css" type="text/css" rel="stylesheet">
        <div id="pt" class="wp cl">

            <div class="z">
                <a href="#" class="nvhm">唐山教育网</a>
                <em>›</em>
                <a href="../Index/IntegralMall">积分商城</a>
            </div>
        </div>

<div id="wp_auc" class="wp cl">
    <div class="shopright">
        <div class="shopimg"><a href="../Index/IntegralMall" target="_blank" title="了解积分交易">
        <img src="../integralmall/image/shopimg.jpg"></a>
        </div>
            <div id="userinfo" class="userloading">
                <p id="login">
                <a href="../Account/Login" onclick="showWindow(this.href);return false;">会员登录</a>
                </p>
                <p>登录后，立即查看您的交易积分</p>
            </div>
	
<div id="" class="sh_viewleft" style="padding:0;width:auto;">
    <div class="sh_Rtit">热门交易排行</div>
    	<c:if test="${not empty viewGoodss}">
    		<c:set var="index" value="0"/>
    		<c:forEach var="item" items="${viewGoodss}">
    				<c:set var="index" value="${index+1}"/>
	    				<div class="sh_Rbot">
				            <a href="../Index/BuyGoods?goodsId=${item.goodsId}" target="_blank">
				            <img class="sh_Rimg" src="${item.goodsImagaPath}"></a>
				            <span class="sh_Rp">
				                <p class="sh_Rp1">
				            <span class="order"><c:out value="${index}"/></span>
				                <a href="../Index/BuyGoods?goodsId=${item.goodsId}" target="_blank" title="<c:out value="${item.goodsName}"/>"><c:out value="${item.goodsName}"/></a>
				                </p>
				                <p class="sh_Rp2">价格：<em><c:out value="${item.goodsPrice}"/></em> 金钱</p>
				                <a href="../Index/BuyGoods?goodsId=${item.goodsId}" target="_blank" class="sh_button">竞&nbsp;&nbsp;&nbsp;拍</a>
				                </p>
				            </span>
				        </div>
    		</c:forEach>
    	</c:if>
	</div>
</div>

<div style="padding-bottom:10px;">
    <form action="../Account/Login" onsubmit="searchFocus()" autocomplete="off" method="post" id="scform" target="_self">
        <input type="hidden" value="auction" name="id">
        <input type="hidden" value="search" name="action">
        <input type="hidden" value="" name="sctxt" id="sctxt">
    
<div style="display: none" class="p_pop cl" id="sctype_menu">
    <ul>
        <li>
            <input type="radio" value="1" name="sctype" class="pr" id="sc_type1">
            <label title="!search_type1!" for="sc_type1">!search_type1!</label>
        </li>
        <li>
            <input type="radio" value="2" name="sctype" class="pr" id="sc_type2">
            <label title="!search_type2!" for="sc_type2">!search_type2!</label>
        </li>
    </ul>
</div>


<div style="display: none" class="p_pop cl" id="sctime_menu">
    <ul>
        <li>
            <input type="radio" value="being" name="sctime" class="pr" id="sc_time_being">
            <label title="!search_time_being!" for="sc_time_being">!search_type1!</label>
        </li>
        <li>
            <input type="radio" value="will" name="sctime" class="pr" id="sc_time_will">
            <label title="!search_time_will!" for="sc_time_will">!search_type2!</label>
        </li>
        <li>
            <input type="radio" value="been" name="sctime" class="pr" id="sc_time_been">
            <label title="!search_time_been!" for="sc_time_been">!search_type2!</label>
        </li>
    </ul>
</div>



<div class="shopleft" id="left">
    <div class="sh_lone cl" id="recenthot">
        <h2 class="shtitle">
            <a class="sh_more" href="IntegralMallMore?type=recenthot">更多</a>
            <span>最近热卖</span>
        </h2>
		
<!-- 分页 -->
<div style="width:800px;margin:0 auto;">
<div id="kkpager"></div>
</div>

    	<!-- /分页 -->
    	
	</div>
	    	
	
	
<div style="padding-bottom:10px;">
    <img src="../integralmall/image/shop_line.jpg"></div>
        
 </div>
</div>
</div>
</div>
<script type="text/javascript">
var future=  1475376667*1000;
function ok_lets_go() {

var timelines = $('left').getElementsByTagName('input');
for(var i=0;i<timelines.length;i++) {
you_go(timelines[i].id, (timelines[i].value*1000));
timelines[i].value --;

}
setTimeout("ok_lets_go()", 1000);
}

function you_go(id, now){

days = (now-future) / 1000 / 60 / 60 / 24;

if(now-future < 0){
document.getElementById('time_'+id).innerHTML = '已经结束';
$('vd_'+id).className="a_lt";
return;
}

        dayNum = Math.floor(days);
        hours = (now-future) / 1000 / 60 / 60 - (24 * dayNum);
        houNum = Math.floor(hours);
        if(houNum < 10){
            houNum = "0" + houNum;
        }
        minutes = (now-future) / 1000 / 60 - (24 * 60 * dayNum) - (60 * houNum);
        minNum = Math.floor(minutes);
        if(minNum < 10){
            minNum = "0" + minNum;
        }
        seconds = (now-future) / 1000 - (24 * 60 * 60 * dayNum) - (60 * 60 * houNum) - (60 * minNum);
        secNum = Math.floor(seconds);
        if(secNum < 10){
            secNum = "0" + secNum;
        }
        millisec=(now-future)-(24*60*60*1000*dayNum)-(60*60*1000*houNum)-(60*1000*minNum)-(secNum*1000);
        milli=Math.floor(millisec/10);
        if(milli<10){
            milli="0"+milli;
}
document.getElementById('time_'+id).innerHTML = dayNum ? (dayNum+" 天 "+houNum + ":" + minNum) : (houNum + ":") + (minNum + ":")+ secNum ;
//(dayNum?dayNum+" 天 ":'')+ (houNum + ":") + (minNum + ":")+ secNum;
}

ok_lets_go();
</script></div>


<div style="clear:both;"></div>

<div class="comiis_footer cl">

<div id="ft" class="wp comiis_bottom cl ">

<div class="comiis_demail">
    </p></div>

<div id="frt">
    <a href="../Index/" rel="nofollow" class="comiis_dlogo" target="_blank">
    <img src="../integralmall/image/lo.jpg" height="80"></a>
    <p>
    <a href="" target="_blank">关于我们</a>
        <span class="pipe">|</span>
    <a href="" target="_blank">信息举报</a>
        <span class="pipe">|</span>
    <a href="" target="_blank">招聘信息</a>
        <span class="pipe">|</span>
    <a href="" target="_blank">网站地图</a>
        <span class="pipe">|</span>
    <a href="" target="_blank">未成年人家长监护</a>
        <span class="pipe">|</span>
    </p>
</div>
</div>
</div>
 

<!--<script type="text/javascript">_attachEvent(window, 'scroll', function () { new_showTopLink(); });checkBlind();</script>-->


<script type="text/javascript">


_attachEvent(document,'click',function(){hidecomiismenu('comiis_sousuo_menu');});
function hidecomiismenu(id) {

var menuObj = $(id);

var menu = $(menuObj.getAttribute('ctrlid'));

if(ctrlclass = menuObj.getAttribute('ctrlclass')) {

var reg = new RegExp(' ' + ctrlclass);

menu.className = menu.className.replace(reg, '');

}

menuObj.style.display = 'none';

}

function new_showTopLink() {

var ft = $('ft');

if(ft){

var scrolltop = $('scrolltop');

var viewPortHeight = parseInt(document.documentElement.clientHeight);

var scrollHeight = parseInt(document.body.getBoundingClientRect().top);

var basew = parseInt(ft.clientWidth);

var sw = scrolltop.clientWidth;

if (basew < 1500) {

var left = parseInt(fetchOffset(ft)['left']);

left = left < sw ? left * 2 - sw : left;

scrolltop.style.left = ( basew + left ) + 'px';

} else {

scrolltop.style.left = 'auto';

scrolltop.style.right = 0;

}

if (BROWSER.ie && BROWSER.ie < 7) {

scrolltop.style.top = viewPortHeight - scrollHeight - 150 + 'px';

}

if (scrollHeight < -100) {

scrolltop.style.visibility = 'visible';

} else {

scrolltop.style.visibility = 'hidden';

}

}

}

if($("myrepeats")){

$("comiis_key").appendChild($("myrepeats"));

}

if($("qmenu_loop")){

var qmenu_timer, qmenu_scroll_l;

var qmenu_in = 0;

var qmenu_width = 246;

var qmenu_loop = $('qmenu_loop');

var qmenu_all_width = 41 * $('qmenu_loopul').getElementsByTagName("li").length - qmenu_width;

if(qmenu_all_width < 20){

$('qmenu_an').style.display = 'none';

}

}

function qmenu_move(qmenu_lr){

if(qmenu_in == 0 && ((qmenu_lr == 1 && qmenu_loop.scrollLeft < qmenu_all_width) || (qmenu_lr == 0 && qmenu_loop.scrollLeft > 0))){

qmenu_in = 1;

qmenu_scroll_l = qmenu_loop.scrollLeft;

qmenu_timer = setInterval(function(){

qmenu_scroll(qmenu_lr);

}, 10);

}

}

function qmenu_scroll(qmenu_lr){

if((qmenu_lr == 1 && qmenu_loop.scrollLeft >= qmenu_width + qmenu_scroll_l) || (qmenu_lr == 0 && ((qmenu_loop.scrollLeft <= qmenu_scroll_l - qmenu_width) || qmenu_loop.scrollLeft == 0))){

clearInterval(qmenu_timer);

qmenu_in = 0;

}else{

if(qmenu_lr == 1){

qmenu_loop.scrollLeft += Math.round((qmenu_width + qmenu_scroll_l - qmenu_loop.scrollLeft) / 15) + 1;

}else{

qmenu_loop.scrollLeft -= Math.round((qmenu_width - (qmenu_scroll_l - qmenu_loop.scrollLeft)) / 15) + 1;

}

}

}
</script>
<script>
var _hmt = _hmt || [];
(function() {
  var hm = document.createElement("script");
  hm.src = "//hm.baidu.com/hm.js?a75705ab508dcc91a848d70999542cd2";
  var s = document.getElementsByTagName("script")[0];
  s.parentNode.insertBefore(hm, s);
})();


</script>           
		<div id="discuz_tips" style="display:none;"></div>
 <script type="text/javascript">
     var tipsinfo = '46989062|X3.2|0.6||0||0|7|1475376667|5a47ab3e77554699b4ac0ea14c5a9ff1|2';
 </script>
 <script src="../integralmall/js/discuz_tips.js" type="text/javascript" charset="UTF-8"></script>





</body></html>