<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>兑换记录</title>

<meta name="generator" content="Discuz! X3.2">
<meta name="author" content="Discuz! Team and Comsenz UI Team">
<meta name="copyright" content="2001-2013 Comsenz Inc.">
<meta name="MSSmartTagsPreventParsing" content="True">
<meta http-equiv="MSThemeCompatible" content="Yes">
<%@include file="Shoppingheader.jsp" %>

<link rel="stylesheet" type="text/css" href="../shopping/css/style_4_forum_forumdisplay.css">

<script type="text/javascript">
  var STYLEID = '4', STATICURL = 'static/', IMGDIR = 'template/87870pc/img', VERHASH = 'FEK', charset = 'utf-8', discuz_uid = '0', cookiepre = 'kfEE_2132_', cookiedomain = '', cookiepath = '/', showusercard = '1', attackevasive = '0', disallowfloat = 'newthread', creditnotice = '1|威望|,2|金钱|,3|贡献|', defaultstyle = '', REPORTURL = 'aHR0cDovL2Jicy44Nzg3MC5jb20vZm9ydW0ucGhwP21vZD1mb3J1bWRpc3BsYXkmZmlkPTU3', SITEURL = 'http://bbs.87870.com/', JSPATH = 'static/js/', CSSPATH = 'data/cache/style_', DYNAMICURL = '';
</script>



<meta name="application-name" content="87870">

<meta name="msapplication-tooltip" content="87870">

<meta name="msapplication-task" content="name=论坛首页;action-uri=http://bbs.87870.com/portal.php;icon-uri=http://bbs.87870.com/template/87870pc/img/portal.ico">
<meta name="msapplication-task" content="name=论坛首页;action-uri=http://bbs.87870.com/forum.php;icon-uri=http://bbs.87870.com/template/87870pc/img/bbs.ico">
<link rel="archives" title="87870" href="http://bbs.87870.com/archiver/">

<link rel="alternate" type="application/rss+xml" title="87870 - oculus rift游戏|虚拟现实游戏社区|VR游戏攻略_VR87870交流论坛_VR论坛_全国最大的VR论坛87870_全國最大的VR論壇_全国最大的VR技术论坛_灵境小白和暴风魔镜_OculusRiftvr论坛_VR人才招聘_免费VR资源下载_免费VR视频资源下载_全景视频下载_免费全景资源下载_VR资源网" href="http://bbs.87870.com/forum.php?mod=rss&amp;fid=57&amp;auth=0">

<script src="../shopping/js/forum.js" type="text/javascript"></script>



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

<script type="text/javascript">
	window.onload = function()
	{
		getMyExchangeRecord(-1,appExchangeRecord);
	}
	function getMyExchangeRecord(stateType,onSuccess)
	{
		var exchangeParam = {
			pageNumber:jQuery("#autopbn").attr("pageNumber"),
			stateType:stateType
		};
		jQuery.ajax({
			url:"../ExchangeRecordManage/getMyExchangeRecord",
			type:"get",
			data:exchangeParam,
			dataType:"json",
			success:function(data){
				if(data.code == "succ")
					onSuccess(data);
				else if(data.code == "fail")
					alert(data.data);
			},
			error:function(){
				alert("加载失败");
			}
		});
	}
	//根据状态查询
	function getExchangeRecord(obj)
	{
		var type = obj.getAttribute('value')
		jQuery("#exchangeRecordState").val(type);
		jQuery("#threadlisttableid").html(" ");
		jQuery("#autopbn").attr("pageNumber","1");
		getMyExchangeRecord(type,appExchangeRecord);
	}
	//根据分页查询
	function getMoreExchangeRecord(obj)
	{
		var type = jQuery("#exchangeRecordState").val();
		getMyExchangeRecord(type,appExchangeRecord);
	}
	
	function getLocalTime(nS) {     
       return new Date(parseInt(nS)).toLocaleString().replace(/年|月/g, "-").replace(/日/g, " ");      
    }     
	
	function appExchangeRecord(data)
	{
		for(var i=0;i<data.data.length;i++)
		{
			jQuery("#threadlisttableid").append('<tbody id="stickthread_2682"><tr><td><div class="comiis_postlist cl"><div class="comiis_listtx">'+
			'<a href="javascript:void(0);" class="comiis_listtxs" target="_blank">'+
			'<img src="'+data.data[i].goodsImagaPath+'"></a></div>'+
			'<h2 class="cl"><span class="comiis_common">'+
			'<a href="javascript:;" id="content_2682" class="showcontent y" title="更多操作" onclick="">'+
			getApplyCancelButton(data.data[i].exchangeRecordStateId,data.data[i].exchangeRecordId)+getSureButton(data.data[i].exchangeRecordStateId,data.data[i].exchangeRecordId)+
			'</a>'+
			'<a href="javascript:void(0);" style="font-weight: bold;color: #EE1B2E;">'+data.data[i].goodsName+'&nbsp;&nbsp;</a>'+
			'<img src="../shopping/img/common.gif" alt="attachment" align="absmiddle">'+
			'- <span class="comiis_tc">[消耗积分 <strong>'+data.data[i].exchangePrice+'</strong> ]</span></h2><div class="listli_div"><em class="km_user">'+
			' <a href="javascript:void(0);" c="1" mid="card_7126">'+data.data[i].businessName+'</a></em><em><span>'+getLocalTime(data.data[i].exchangeTime)+'</span></em>'+
			'<em><span>&nbsp;兑换数量:'+data.data[i].exchangeCount+'</span></em>'+
			'<em><span>&nbsp;状态:'+data.data[i].exchangeRecordStateName+'</span></em>'+
			'<em><span>&nbsp;订单编号:'+data.data[i].exchangeRecordSerialNum+'</span></em>'+
			'</div></div></td></tr></tbody>');
			
			
			/*
				jQuery("#threadlisttableid").append('<tbody id="stickthread_2682"><tr><td><div class="comiis_postlist cl"><div class="comiis_listtx">'+
			'<a href="javascript:void(0);" class="comiis_listtxs" target="_blank">'+
			'<img src="./虚拟咖啡屋_87870VR论坛_八卦交流_VR行业交流 -_files/avatar(1).php"></a></div>'+
			'<h2 class="cl"><span class="comiis_common">'+
			'<a href="javascript:;" id="content_2682" class="showcontent y" title="更多操作" onclick="">111</a>'+
			'<a href="javascript:void(0);" style="font-weight: bold;color: #EE1B2E;" onclick="atarget(this)">钢铁侠手办</a>'+
			'<img src="./虚拟咖啡屋_87870VR论坛_八卦交流_VR行业交流 -_files/common.gif" alt="attachment" title="附件" align="absmiddle">'+
			'- <span class="comiis_tc">[消耗积分 <strong> 1000</strong> ]</span></h2><div class="listli_div"><em class="km_user">'+
			' <a href="javascript:void(0);" c="1" mid="card_7126">京东</a></em><em><span>2015-11-17</span>兑换</em>'+
			'<em><a href="javascript:void(0);" target="_blank">占位符</a></em>'+
			'</div></div></td></tr></tbody>');
			*/
			
		}
		if(data.data.length < 5)
		{
			jQuery("#autopbn").html("没有更多了");
			jQuery("#autopbn").removeAttr("onclick");
		}
		else
		{
			var number = jQuery("#autopbn").attr("pageNumber");
			var a = parseInt(number)+1;
			jQuery("#autopbn").attr("pageNumber",a);
		}
	}
	
	function getApplyCancelButton(exchangeRecordStateId,exchangeRecordId)
	{
		if(exchangeRecordStateId == 5)
			return "";
		if(exchangeRecordStateId == 4)
			return "";
		if(exchangeRecordStateId == 3)
			return "";
		return '<span class="y"><a href="javascript:;" id="newspecial" class="listTRbutton" title="申请取消" onclick="cancelExchangeRecord('+exchangeRecordId+')">申请取消</a></span>';
	}
	function getSureButton(exchangeRecordStateId,exchangeRecordId)
	{
		if(exchangeRecordStateId == 5)
			return "";
		if(exchangeRecordStateId == 4)
			return "";
		if(exchangeRecordStateId == 3)
			return "";
		return '<span class="y"><a href="javascript:;" id="newspecial" class="listTRbutton" title="确认收货" onclick="sureExchangeRecord('+exchangeRecordId+')">确认收货</a></span>';
	}
	function cancelExchangeRecord(exchangeRecordId)
	{
		if (!confirm("确认向商家申请取消订单？"))
   				return;
   				
	 	jQuery.ajax({
			url:"../ExchangeRecordManage/cancelExchangeRecord",			
			data:{"exchangeRecordId":exchangeRecordId},
			dataType:"json",
			success:function(data) {  
				if (data.code != "succ") {
	 						alert(data.data);
	 						return;
	 					}
	 					alert('已向商家申请取消');
					},
		    error: function(XMLHttpRequest, textStatus, errorThrown) {
				alert("网络错误: " + XMLHttpRequest.status + ", " + textStatus);
		    }
		});
	}
	function sureExchangeRecord(exchangeRecordId)
	{
		if (!confirm("是否确认收货?"))
   				return;
   				
   			jQuery.ajax({
				url:"../ExchangeRecordManage/sureExchangeRecord",			
				data:{"exchangeRecordId":exchangeRecordId},
				dataType:"json",
				success:function(data) {  
					if (data.code != "succ") {
   						alert(data.data);
   						return;
   					}
   					alert('确认收货成功');
  				},
			    error: function(XMLHttpRequest, textStatus, errorThrown) {
					alert("网络错误: " + XMLHttpRequest.status + ", " + textStatus);
			    }
			});
	}
</script>


</head>

<body id="nv_forum" class="pg_forumdisplay comiis_wide kmfn" onkeydown="if(event.keyCode==27) return false;"><div id="baseurl" data-value="http://bbs.87870.com/" style="display:none;"></div>

<!--top-->
<%@include file="Shoppingtop.jsp" %>
<!-- top -->

<div id="append_parent"></div><div id="ajaxwaitid"></div>

<div id="wp" class="wp">


<style id="diy_style" type="text/css"></style>
<!--[diy=diynavtop]--><div id="diynavtop" class="area"></div><!--[/diy]-->
<div id="pt" class="bm cl">
	<div class="z">
		<a href="../Index/" class="nvhm" title="首页">唐山家庭教育网</a> 
		<em>›</em> 
		<a href="Shopping">积分商城</a>
		<em>›</em> 
		<a href="#">兑换记录</a>
	</div>
</div>

<div class="wp">
<!--[diy=diy1]--><div id="diy1" class="area"></div><!--[/diy]-->
</div>

<div class="comiis_listinfo cl gwrl">	<div class="comiis_pn_post">

<!-- 操作按钮 
<span class="y">

<a href="javascript:;" id="newspecial" class="listTRbutton" title="发新帖">操作按钮<i><img src="../shopping/img/jtFFF.png" alt="操作"></i></a>

</span>
-->


<!-- <span class="y"><a href="javascript:;" id="newspecial" class="listTRbutton" title="发新帖">操作按钮</a></span> -->


</div>

<div class="topico">
	<c:choose>
		<c:when test="${not empty user.userPhotoUrl}">
			<img src="${user.userPhotoUrl}" alt="${user.userName}" />
		</c:when>
		<c:otherwise>
			<img src="../shopping/img/common_57_icon.png" alt="${user.userName}" />
		</c:otherwise>
	</c:choose>
</div>

<div class="comiis_infobox">
<div class="comiis_infotit cl">
<h1>
<a href="javascript:void(0);">
	<c:out value="${user.userName}"/>
</a>
<!-- <span>今日: <strong class="xi1">6</strong><b class="ico_fall">&nbsp;</b> -->
<span class="pipe">/</span>积分: <strong class="xi1">
	<c:out value="${user.userIntegral}"/>
</strong>
<span class="pipe">/</span>排名: <strong class="xi1" title="上次排名:4">
	<c:out value="${rank}"/>
</strong>
<b class="ico_increase">&nbsp;</b></span></h1>
</div>
<div class="comiis_infotxt">
						
</div>

<!-- 
<div>一个简短的介绍</div>
 -->

</div>
</div>
<div class="boardnavr">
<div class="comiis_width">
<div class="comiis_rk">
<div class="comiis_lp">
<div id="ct" class="wp cl comiis_rollzbox ct2km">
<div class="mn">
<div class="drag">
<!--[diy=diy4]--><div id="diy4" class="area"></div><!--[/diy]-->
</div>
<div id="threadlist" class="tl bm bmw">
<div class="th comiis_topinfo kmtops
 cl">
<div class="z">

<!--<span class="z">筛选:&nbsp;</span>-->

<a id="filter_special" href="javascript:;" class="showmenu_outer" onclick="showMenu(this.id)">

<span class="showmenu">全部主题</span>

</a>
<!-- 
<a id="filter_dateline" href="javascript:;" class="showmenu_outer" onclick="showMenu(this.id)">

<span class="showmenu">全部时间</span>

</a>
<span class="z">排序:&nbsp;</span>
<a id="filter_orderby" href="javascript:;" class="showmenu_outer" onclick="showMenu(this.id)">

<span class="showmenu">最后发表</span>

</a>  -->
</div>
	<!-- 
	<div class="z">	
	<a href="javascript:void(0);" class="">最新</a>
	<span class="pipe"> </span>
	<a href="javascript:void(0);" class="">热门</a>
	<span class="pipe"> </span>
	<a href="javascript:void(0);" class="">热帖</a>
	<span class="pipe"> </span>
	<a href="javascript:void(0);" class="">精华</a>
	</div>
	 -->
</div>
<div class="bm_c">
<script type="text/javascript">var lasttime = 1480300211;var listcolspan= '1';</script>
<div id="forumnew" style="display:none"></div>
<form method="post" autocomplete="off" name="moderate" id="moderate">
<input type="hidden" name="formhash" value="51bf5173">
<input type="hidden" name="listextra" value="page%3D1">						
<table summary="forum_57" id="threadlisttableid">
				
<!--一个兑换记录开始-->

<!--一个兑换记录结束-->
</table><!-- end of table "forum_G[fid]" branch 1/3 -->
			
</form>
</div>
<a href="javascript:;" onclick="getMoreExchangeRecord(this)"  id="autopbn" pageNumber="1"><i></i>查看下一页</a>
<script src="../shopping/js/autoloadpage.js" type="text/javascript"></script>
<div class="comiis_pgs cl">	</div>
</div>


<div id="filter_special_menu" class="p_pop km_pop" style="display:none">
<ul>
<input type="hidden" value="-1" id="exchangeRecordState">
	<li>
		<a href="javascript:void(0);" value="-1" onclick="getExchangeRecord(this)">
			全部订单
		</a>
	</li>
<c:forEach var="exchangeRecordState" items="${exchangeRecordStates}">
	<li>
		<a href="javascript:void(0);" value="${exchangeRecordState.exchangeRecordStateId}" onclick="getExchangeRecord(this)">
			<c:out value="${exchangeRecordState.exchangeRecordStateName}"/>
		</a>
	</li>
</c:forEach>
</ul>
</div>

<div id="filter_reward_menu" class="p_pop km_pop" style="display:none" change="forum.php?mod=forumdisplay&amp;fid=57&amp;filter=specialtype&amp;specialtype=reward&amp;rewardtype=&#39;+$(&#39;filter_reward&#39;).value">
<ul>
<li><a href="javascript:void(0);">全部悬赏</a></li>
<li><a href="javascript:void(0);">进行中</a></li></ul>
</div>
<div id="filter_dateline_menu" class="p_pop km_pop" style="display:none">
<ul>
<li><a href="javascript:void(0);">全部时间</a></li>
<li><a href="javascript:void(0);">一天</a></li>
<li><a href="javascript:void(0);">两天</a></li>
<li><a href="javascript:void(0);">一周</a></li>
<li><a href="javascript:void(0);">一个月</a></li>
<li><a href="javascript:void(0);">三个月</a></li>
</ul>
</div>
<div id="filter_orderby_menu" class="p_pop km_pop" style="display:none">
<ul>
<li><a href="javascript:void(0);">默认排序</a></li>
<li><a href="javascript:void(0);">发帖时间</a></li>
<li><a href="javascript:void(0);">回复/查看</a></li>
<li><a href="javascript:void(0);">查看</a></li>
<li><a href="javascript:void(0);">最后发表</a></li>
<li><a href="javascript:void(0);">热门</a></li>
</ul>
</div>
</div>

<!--[diy=diyfastposttop]--><div id="diyfastposttop" class="area"></div><!--[/diy]-->
<script type="text/javascript">
var postminchars = parseInt('10');
var postmaxchars = parseInt('10000');
var disablepostctrl = parseInt('0');
var fid = parseInt('57');
</script>


<!--[diy=diyforumdisplaybottom]--><div id="diyforumdisplaybottom" class="area"></div><!--[/diy]-->
</div>
</div>
</div>
<script type="text/javascript">document.onkeyup = function(e){keyPageScroll(e, 0, 1, 'forum.php?mod=forumdisplay&fid=57&filter=&orderby=lastpost&', 1);}</script>
<script type="text/javascript">checkForumnew_handle = setTimeout(function () {checkForumnew(57, lasttime);}, checkForumtimeout);</script>
</div></div>

      <div class="listRightbox">
        <!--当前用户的框开始-->
        <div class="listRdiv">
          <div class="listRTtit">当前用户</div>
          <div class="listRword">
            <div class="tx30">
              <img src="../shopping/img/avatar(14).php">
            </div>
            <div class="tx30name"><c:out value="${user.userName}"/></div>
          </div>
        </div>
        <!--当前用户的框结束-->
        <!--热卖商品开始-->
        <div class="listRdiv">
          <div class="listRTtit">推荐商品</div>
          	<c:if test="${not empty hotrank}">
          		<c:forEach var="goods" items="${hotrank}">
			        <div class="listRword">
				        <div class="hdone">
				          <div class="hdoneimg">
		                <a href="./GoodsDetial?goodsId=${goods.goodsId}">
		                  <img src="${goods.goodsImagaPath}" alt="${goods.goodsName}"/>
		                </a>
		              </div>
				          <div class="hdonename">
		                <a href="javascript:void(0);" target="_blank"><c:out value="${goods.goodsName}"/></a>
		              </div>
				        </div>
			        </div>         			
          		</c:forEach>
          	</c:if>     
        </div>
        <!--热卖商品结束-->
        
   </div>


<div style="clear:both;height:0px;overflow:hidden;font-size:0;"></div>
</div>
<div class="wp">
<!--[diy=diy3]--><div id="diy3" class="area"></div><!--[/diy]-->
</div>
<script>fixed_top_nv();</script><script src="../shopping/js/jquery.min.js" type="text/javascript"></script>


<div style="clear:both;"></div>
<!--bottom-->
<%@include file="Shoppingbottom.jsp" %>
<!--bottom-->


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


</script>			<div id="discuz_tips" style="display:none;"></div>
			<script type="text/javascript">
				var tipsinfo = '46989062|X3.2|0.6||0||0|7|1480300211|3dca3aa789525e63847f84709dc810b9|2';
			</script>
			


</body></html>
