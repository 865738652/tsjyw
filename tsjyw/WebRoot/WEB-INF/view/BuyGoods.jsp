<div class="login_topclose"></div><%@ page language="java" import="java.util.*,edu.iasd.pojo.ViewModule" pageEncoding="UTF-8"%>
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
<title>${vs.goodsName} </title>
<link href="../Index/BuyGoods?goodsId=${vs.goodsId}" rel="canonical">
<meta name="keywords" content="">
<meta name="description" content="积分商城">
<meta name="generator" content="Discuz! X3.2">
<meta name="author" content="Discuz! Team and Comsenz UI Team">
<meta name="copyright" content="2001-2013 Comsenz Inc.">
<meta name="MSSmartTagsPreventParsing" content="True">
<meta http-equiv="MSThemeCompatible" content="Yes">

<link rel="stylesheet" type="text/css" href="../buygoods/css/style_4_common.css">
<link rel="stylesheet" type="text/css" href="../buygoods/css/style_4_forum_viewthread.css">
<script src="../buygoods/js/hm.js"></script>
<script type="text/javascript">var STYLEID = '4', STATICURL = 'static/', IMGDIR = 'template/87870pc/img', VERHASH = 't48', charset = 'utf-8', discuz_uid = '0', cookiepre = 'kfEE_2132_', cookiedomain = '', cookiepath = '/', showusercard = '1', attackevasive = '0', disallowfloat = 'newthread', creditnotice = '1|威望|,2|金钱|,3|贡献|', defaultstyle = '', REPORTURL = 'aHR0cDovL2Jicy44Nzg3MC5jb20vZm9ydW0ucGhwP21vZD12aWV3dGhyZWFkJnRpZD0xMTAyMA==', SITEURL = 'http://bbs.87870.com/', JSPATH = 'static/js/', CSSPATH = 'data/cache/style_', DYNAMICURL = '';</script>
<script src="../buygoods/js/common.js" type="text/javascript"></script>
<meta name="application-name" content="唐山家庭教育网">
<meta name="msapplication-tooltip" content="唐山家庭教育网">

<link rel="archives" title="87870" href="http://bbs.87870.com/archiver/">
<link href="../css/style.css" rel="stylesheet">
<link href="../css/index.css" rel="stylesheet">

<script src="../buygoods/js/jquery-1.8.3.min.js" type="text/javascript"></script>
<script src="../buygoods/js/jquery.nava.js" type="text/javascript"></script>
<script src="../buygoods/js/comiis_nv.js" type="text/javascript"></script>
<script type="text/javascript">var fid = parseInt('55'), tid = parseInt('11020');</script>
<script src="../buygoods/js/forum_viewthread.js" type="text/javascript"></script>
<script type="text/javascript">zoomstatus = parseInt(1);var imagemaxwidth = '600';var aimgcount = new Array();</script>

<!--  -->
    
    
<script type="text/javascript" src="../js/jquery.js"></script>
	

<script type="text/javascript">

	$(function(){
		$("#login_topclose").click(function(){
			$('#login-widow,.bg').hide();
		});
	});
	
 	function showAll()
 	{
        var div=document.getElementById("yhmUL");
        if(div.style.display=="block")
			div.style.display="none";
		else
		div.style.display="block";
    }
	function fondser()
	{
		var bdiv=document.getElementById("searchinput");
		var search_text=document.getElementById("searchinput").value;
		//if(bdiv.style.display=="block"){
		if(search_text){
    		var base_url = document.getElementById("baseurl").attributes["data-value"].value;
			var search_url = base_url+'search.php?mod=forum&searchid=1&orderby=lastpost&ascdesc=desc&searchsubmit=yes&kw='+search_text;
  			var form = document.getElementById("searchform");
  			form.submit();
    	}
		bdiv.style.display="block";
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

<style type="text/css">
.aucimg {border:1px solid #E3E3E3;background-color:#E3E3E3;padding:2px;width:250px;float:left;color:#1C1C1C;cursor:pointer;}
.aucdetail {margin:0 0 0 265px;padding:0 0 0 5px;color:#1C1C1C;padding:0 5px;font-size:12px;}
.aucdetail a{color:#5A5A96;}
.aucdetail li{list-style:none; border-bottom:1px dashed #CDCDCD;height:26px;line-height:26px;padding-left:10px;}
.aucdetail li span{float:left;width:70px;}
.aucdetail em{margin-left:10px;}
.aucdetail img{vertical-align:middle;cursor:pointer;}
.aucdetail .auc_title{color:#F26C4F;font-weight:700;}

#auctips_menu {border:1px double #E3E3E3;background-color:#E5EDF2;padding:5px 10px;width:200px;line-height:22px;}
#auc_involve{margin:10px 0 0 90px;margin:10px 0 0 70px\9;}


#auc_pg .pg {line-height:15px;float;left;margin-top:5px;}
#auc_pg .pg a, #auc_pg .pg strong, #auc_pg .pgb a {
  background-repeat: no-repeat;
  color: #333333;
  display: inline;
  float:left;
  font-size: 10px;
  height: 16px;
  margin-left: 4px;
  overflow: hidden;
  padding: 0 4px;
  text-decoration: none;
}
#auc_pg .pg a.nxt{background:none;}

.list_tb {
    -moz-border-bottom-colors: none;
    -moz-border-image: none;
    -moz-border-left-colors: none;
    -moz-border-right-colors: none;
    -moz-border-top-colors: none;
    border-color: #BACFDD #E3E5E7 #E3E5E7;
    border-right: 1px solid #E3E5E7;
    border-style: solid;
    border-width: 1px;
    margin-top: 10px;
    text-align: left;
}
.list_tb span {
    background: url("source/plugin/auction/images/icon03.gif") no-repeat scroll left center transparent;
    color: #333333;
    font-weight: bold;
    margin: 5px;
    padding: 0 0 0 20px;
    text-align: left;
    width:auto;
    float:left;
    font-weight:700;
}
.list_lt {
    background: none repeat scroll 0 0 #FCFCFC;
    border-top: 1px solid #E3ECF2;
    color: #666666;
    font-size: 12px;
    height: 25px;
    padding-left: 10px;
    vertical-align: middle;
    width:auto;
    word-break:break-all;
}


.list_bt {background: url("source/plugin/auction/images/list_bg.gif") repeat scroll center top transparent; height: 25px; vertical-align: bottom;}
.list_lt{}
.list_tb .gray{background:none;padding:0;}
.list_tb .gray a{color:#999;}
.auction_price .auc_title{color:#F26C4F;}
.auction_price {margin:10px;}
.auction_price a{color:#1B7FC6;}
.myauclog td,.myauclog th{background-color:#eee;}
</style>

<script type="text/javascript" id="lmTmbVVclVTcTqUcllmaengWXYbgXWWW" charset="UTF-8" src="../buygoods/js/ajax.js"></script>
</head>

<body id="nv_forum" class="pg_viewthread comiis_wide kmfn" onkeydown="if(event.keyCode==27) return false;">
	<div id="baseurl" data-value="http://bbs.87870.com/" style="display:none;"></div>

	<div id="header">
		<%@include file = "header.jsp" %>
	</div>

	<div id="append_parent"></div>
	<div id="ajaxwaitid"></div>

	<div id="wp" class="wp">
	<div id="diynavtop" class="area"></div>

	<div id="pt" class="bm cl">
		<div class="z">
			<a href="../Index/" class="nvhm" title="首页">唐山家庭教育网</a><em>›</em>
			<a href="../Index/">综合讨论</a><em>›</em>
			<a href="../Index/IntegralMall">积分商城</a> <em>›</em>
			<a href="../Index/BuyGoods?goodsId=${vs.goodsId}">${vs.goodsName}</a>
	</div>
	</div>

	<div class="wp">	
		<div id="diy1" class="area"></div>
	</div>


	<div class="comiis_listinfo cl pr_35 gwrl">
		<div class="comiis_pn_post">
		<span class="y">

		<i><i>

</i></i></span><i><i>

</i></i></div><i><i>

 
<div class="comiis_infobox">

<div class="comiis_infotit cl">

<span class="y" style="display:none;">

<a href="../Index/BuyGoods?goodsId=${vs.goodsId}" id="a_favorite" class="fa_fav" onclick="showWindow();">收藏本版 <strong class="xi1" id="number_favorite" style="display:none;">(<span id="number_favorite_num">0</span>)</strong></a>

<a href="../Index/BuyGoods?goodsId=${vs.goodsId}" class="fa_rss" target="_blank" title="RSS">订阅</a>


</span>

<h1>
<a href="../Index/IntegralMall">积分商城</a>
</h1>
<a href="../Index/BuyGoods?goodsId=${vs.goodsId}">${vs.goodsName}</a> 
</div>


<div>唐山家庭教育网积分商城，积分兑换商品，并可在站内游览信息。</div>


</div>

</i></i></div><i><i>

<div id="pgt" class="pgs mbm cl" style="display:none;">

<a id="newspecial" onmouseover="return false;" href="javascript:;" title="发新帖">
  <img src="../buygoods/image/pn_post.png" alt="发新帖"></a>

</div>
<div class="wp">
<div id="diy1s" class="area"></div>
</div>
<div class="boardnav">
<div id="ct" class="wp cl comiis_rollzbox">
<div id="postlist" class="pl bm gnob comiis_x0">
<div class="comiis_snvbt">
<div class="viewtit">
  <span class="y comiis_sxy" style=" display:block;">
</span>
    </div>
<div class="title">

<span class="z">
</span>

<h2 class="">

<span id="thread_subject">${vs.goodsName}</span>

</h2>

<span class="z kmfz">

<a href="../Index/BuyGoods" onclick="return copyThreadUrl()" class="kmcopy">[复制链接]</a>

</span>

</div>
</div>



<div id="post_3554233" class="comiis_vrx">
  <table id="pid3554233" class="plhin" summary="pid3554233" cellspacing="0" cellpadding="0">
    <tbody>
    <tr><td class="pls" rowspan="2">
      <div id="favatar3554233" class="pls favatar">
      <a name="newpost"></a>
      <div class="p_pop blk bui comiis_vuimg card_gender_0" id="userinfo3554233" style="display:none;">
      <div class="m z">
      <div id="userinfo3554233_ma"></div>
      </div>
<div class="i y">
<div>
<strong>
  <a href="../Index/BuyGoods" target="_blank" class="xi2">wangmeng</a></strong>
    <em>当前离线</em>
  </div><dl class="cl">
    <dt>积分</dt><dd><a href="../Index/BuyGoods" target="_blank" class="xi2">1000137</a></dd>
    </dl>
    <div class="imicn">
      <a href="../Index/BuyGoods" target="_blank" title="查看详细资料">
        <img src="../Index/BuyGoods" alt="查看详细资料"></a>
    </div>
<div id="avatarfeed">
  <span id="threadsortswait"></span>
</div>
</div>
</div>
<div>
<div class="avatar"><a href="../Account/Personal" class="avtm" target="_blank">
  <img src="../buygoods/image/avatar.jpg"></a>
</div>
<div class="pi">
<div class="authi">
  <a href="javascript:void(0);" target="_blank"><c:out value="${bussiness.businessName}"/></a>
</div>						<!--用户名-->
</div>
</div>
<div class="qdsmile">
  <li><center>商家简介</center>
    <table>
      <tbody>
        <tr>
          <th>
            <img src="../buygoods/image/ch.gif">
          </th>
          <th>
	          <font size="5px">擦汗</font>
	          <br>
	          <span title="2016-10-28 13:06">昨天&nbsp;13:06</span>
          </th>
         </tr>
          
      	</tbody></table></li>
          </div>
          <p>地址:<c:out value="${bussiness.businessStr}"/></p>
          <p>电话:<c:out value="${bussiness.businessPhone}"/></p>
          <p>微信:<c:out value="${bussiness.businessWeixin}"/></p>
          <p>QQ:<c:out value="${bussiness.businessQq}"/></p>
          <div class="tns xg2"><table cellspacing="0" cellpadding="0">
            <tbody>
              <tr>
                <th>
                  <p>
                    <a href="../Index/BuyGoods?goodsId=${vs.goodsId}" class="xi2">89</a>
                  </p>商品</th><th><p>
                  <a href="../Index/BuyGoods?goodsId=${vs.goodsId}" class="xi2">138</a>
                  </p>信誉</th><td>
                  <p><a href="../Index/BuyGoods?goodsId=${vs.goodsId}" class="xi2">
                  <span title="1000137">评分</span>
                  </a>
                </p>积分</td></tr>
        </tbody>
      </table>
      <p>简介:<c:out value="${bussiness.businessIntro}"/></p>
    </div>

<p><em><a href="../Account/Person" target="_blank">管理员</a></em></p>


<p>
	<span>
		<c:if test="${not empty bussinessManagers}">
			<c:forEach var="user" items="${bussinessManagers}">
				<img src="${user.userPhotoUrl}" alt="Rank: 9">
			</c:forEach>
		</c:if>
		
	</span>
</p>



<dl class="pil cl">
	<dt>积分</dt><dd><a href="../Index/BuyGoods?goodsId=${vs.goodsId}" target="_blank" class="xi2">1000137</a></dd>
</dl>

<dl class="pil cl"></dl><ul class="comiis_o cl">
<li><a href="../Index/BuyGoods?goodsId=${vs.goodsId}" onclick="showWindow();" title="发消息" class="fxxbuton">发消息</a></li>
</ul>
</div>
</td>
<td class="tdline"></td>

<td class="plc">
<div class="pi">


<div class="pti">
<div class="pdbt">
</div>
<div class="authi">
<em id="authorposton3554233">					<i></i>
发表于
2016-9-29 11:50:45</em>
<span class="pipe">|</span>
<a href="../Index/BuyGoods?goodsId=${vs.goodsId}" rel="nofollow">只看该作者</a>
<span class="pipe">|</span>&nbsp;
  <a href="../Index/BuyGoods?goodsId=${vs.goodsId}" target="_blank">只看大图</a>
<span class="none">
  <img src="../buygoods/image/arw_r.gif" class="vm" alt="回帖奖励"></span>
<span class="pipe show">|</span>
  <a href="../Index/BuyGoods?goodsId=${vs.goodsId}" class="show">倒序浏览</a>
<span class="pipe show">|
</span>&nbsp;
  <a href="javascript:;" onclick="readmode();" class="show">阅读模式</a>
</div>
</div>
</div>

<div class="pct">
  <style type="text/css">.pcb{margin-right:0}</style>
    <div class="pcb">

<div class="pcbs">




<div class="auc pbm cl">
<div class="aucimg">

<img src="${vs.goodsImagaPath}" width="250">

</div>
<div class="aucdetail">
<dl>
<li><span>交易类型:</span>
<em class="auc_title">
抽&nbsp;&nbsp;&nbsp;奖

</em>
<em style="margin-left:80px;">
<img src="../buygoods/image/rules.gif" id="auctips" onmouseover="showMenu(this.id);">
</em>
</li>
<li><span>物品类型:</span><em class="">
 <c:forEach var="item" items="${vg}">
	<c:out value="${item.goodsTypeName}"></c:out>
</c:forEach>
<%-- <c:if test="${not empty vg}">
	<c:forEach var="item" items="${vg}">
		<c:choose>
			<c:when test="${item.goodsId=='1'}">
				<c:out value="${item.goodsTypeName}"></c:out>
			</c:when>
		</c:choose>
	</c:forEach>
</c:if> --%>
</em></li>
<li><span>物品名称:</span><em class="">${vs.goodsName}</em></li>
<li><span>物品数量:</span><em class="">共 <font color="#F26C4F">${vs.goodsCount }</font> 件</em></li>
<li><span>限购数量:</span><em class="">限购<font color="#F26C4F">${vs.goodsLimitNumber }</font> 件 </em></li>
<li><span>开始时间:</span><em>${vs.exchangeStartTime }</em></li>
<li><span>结束时间:</span><em>${vs.exchangeStopTime}</em></li>
<li><span>剩余时间:</span><em id="timeleft" class="auc_title">已经结束</em></li>


<li><span>价格:</span>
<em class="auc_title">${vs.goodsPrice}</em><em>金钱</em></li>

</dl>
<dd><p class="pns" id="auc_involve">
	<c:if test="${not empty goodsMessage}">
		<button class="pn" value="true" name="ijoin" id="ijoin"><span><c:out value="${goodsMessage}"/></span></button>
	</c:if>
	<c:if test="${empty goodsMessage}">
		<button class="pn" value="true" name="ijoin" id="ijoin" onclick="buyGoods()"><span>兑换</span></button>
	</c:if>
<span class="gray" style="width:80px;float:none;"><a href="../Index/" target="_blank" style="color:#1B7FC6;padding-left:20px;vertical-align: middle;">查看交易需知</a></span>
</p></dd>
</div>
</div>
<div id="auc_list_tmp" style="display:none;"></div>
<div id="auctips_menu" style="display:none">兑换规则：用户按照发布的积分出价，物品件数有几件，最终就可在此界面兑换商品。
<br>
兑换成功后，系统将自动扣除积分。虚拟物品的卡密将会自动发送，中奖用户可以在抽奖页面查看卡密。</div>
<div id="confirm" style="display:none;"></div>



<table cellspacing="0" cellpadding="0"><tbody><tr><td class="t_f" id="postmessage_3554233">
</div>
</div><br>
<div align="center">DIY鉴赏结束</div><br>
<br>
<div align="center">${vs.goodsIntro}</div><br>
<br>
<div align="center"><div align="center">50J，你出不了国，买不了烟，上不了飞机，做不了船；</div><div align="center">但你能参与积分兑换，说不定神秘礼物就到你怀中了。</div><br>
<div align="center"><font color="#000000">刮开有奖————》</font><font color="#000"><font style="background-color:rgb(0, 0, 0)">就算不想要也能拉低中奖率</font></font></div><br>
<div align="center"><font style="background-color:rgb(249, 249, 249)">------------------------------------------</font></div><br>
<div align="center"><font style="background-color:rgb(249, 249, 249)">中了的少年，请通过我头像下方的发消息</font></div><br>
<div align="center"><font style="background-color:rgb(249, 249, 249)">或者加<font color="#ff0000">QQ：274550196</font>领取奖励</font></div><br>
<div align="center"><font style="background-color:rgb(249, 249, 249)">------------------------------------------</font></div><br>
<div align="center"><br>
<img id="aimg_o8MkJ" onclick="zoom(this, this.src, 0, 0, 0)" class="zoom" width="368" src="./缤纷国庆 由你点缀—— 爱蜜莉雅3D立体绅士欧派鼠标垫 - 积分商城 - 87870_files/114512a0279toay9ayxj71.jpg" border="0" alt=""> <br>
<div align="center"><font color="#000000">（听说中奖和被雷P的概率一样，所以。。。）</font></div><div align="center"><font color="#000000"><br>
</font></div><div align="center"><font color="#ff0000">兑奖请提供：</font><br>
1.姓名、地址、手机号<br>
2.兑换截图（通过站内信兑奖无需提供截图）</div><div align="center"><font color="#666666"><blockquote>实物奖励，全国包邮，海外无法寄送哦~</blockquote></font></div></div></div><br>
<br>
</td></tr></tbody></table>
<div class="ptg mbm mtn">
<a title="包邮" href="../Index/IntegralMall/BuyGoods" target="_blank">包邮</a>,
<a title="手机号" href="../Index/IntegralMall/BuyGoods" target="_blank">手机号</a>,
<a title="3D立体" href="../Index/IntegralMall/BuyGoods" target="_blank">3D立体</a>,
<a title="鼠标垫" href="../Index/IntegralMall/BuyGoods" target="_blank">鼠标垫</a>,
<a title="艾米莉" href="../Index/IntegralMall/BuyGoods" target="_blank">艾米莉</a></div>
<div class="modact">本主题由 wangmeng 于 2016-10-7 09:03 打开</div>
</div>
<div id="comment_3554233" class="cm">
</div>
<div id="post_rate_div_3554233"></div>
<script type="text/javascript">var auc_list_tmp = $('auc_list_tmp');if(auc_list_tmp !== null){document.write(auc_list_tmp.innerHTML);auc_list_tmp.innerHTML='';}
function lalala(){
	ajaxget('plugin.php?id=auction:involve&operation=view&tid=11020&page=1', 'list_ajax');
	$('list_ajax').style.display = 'block';
}
if($('list_ajax')){setTimeout('lalala()', 1000);}
</script>
<div class="auc pbm cl">
<table class="list_tb" style="border-bottom:none;">
<tbody><tr>
<td class="list_bt" colspan="4">
  <span class="">
    <a href="javascript:;" onclick="" id="list_001">出价记录</a>
  </span>
  <span class="gray">
    <a href="javascript:;" onclick="">获奖记录</a>
  </span></td>
</tr>
<tr>

<th class="list_lt" width="20%">用户名</th>
<th class="list_lt" width="40%">兑换时间</th>
<th class="list_lt" width="20%">兑换积分</th>
<th class="list_lt" width="20%">兑换状态</th>

</tr>
</tbody></table>
<div id="list_ajax"><table class="list_tb" style="margin-top:0;">
<tbody><tr>
<td class="list_lt" width="20%"><a href="../Index/IntegralMall/BuyGoods">风</a></td>
<td class="list_lt" width="40%">2016-10-07 11:47:41</td>
<td class="list_lt" width="20%">50</td>
<td class="list_lt" width="20%">
失败
</td>
</tr>
<tr>
<td class="list_lt" width="20%"><a href="../Index/IntegralMall/BuyGoods">alexzhang</a></td>
<td class="list_lt" width="40%">2016-10-07 11:05:20</td>
<td class="list_lt" width="20%">50</td>
<td class="list_lt" width="20%">
失败
</td>
</tr>
<tr>
<td class="list_lt" width="20%"><a href="../Index/IntegralMall/BuyGoods">移步◐倾天</a></td>
<td class="list_lt" width="40%">2016-10-07 11:04:27</td>
<td class="list_lt" width="20%">50</td>
<td class="list_lt" width="20%">
失败
</td>
</tr>
<tr>
<td class="list_lt" width="20%"><a href="../Index/IntegralMall/BuyGoods">lannvwang</a></td>
<td class="list_lt" width="40%">2016-10-07 10:57:39</td>
<td class="list_lt" width="20%">50</td>
<td class="list_lt" width="20%">
失败
</td>
</tr>
<tr>
<td class="list_lt" width="20%"><a href="../Index/IntegralMall/BuyGoods">VR流</a></td>
<td class="list_lt" width="40%">2016-10-07 10:56:38</td>
<td class="list_lt" width="20%">50</td>
<td class="list_lt" width="20%">
<em style="color:#F26C4F;">成功</em></td>
</tr>
<tr>
<td class="list_lt" width="20%"><a href="../Index/IntegralMall/BuyGoods">wangpushaohua</a></td>
<td class="list_lt" width="40%">2016-10-07 10:56:36</td>
<td class="list_lt" width="20%">50</td>
<td class="list_lt" width="20%">
失败
</td>
</tr>
<tr>
<td class="list_lt" width="20%"><a href="../Index/IntegralMall/BuyGoods">jie123123</a></td>
<td class="list_lt" width="40%">2016-10-07 10:55:21</td>
<td class="list_lt" width="20%">50</td>
<td class="list_lt" width="20%">
失败
</td>
</tr>
<tr>
<td class="list_lt" width="20%"><a href="../Index/IntegralMall/BuyGoods">a2194883</a></td>
<td class="list_lt" width="40%">2016-10-07 10:53:00</td>
<td class="list_lt" width="20%">50</td>
<td class="list_lt" width="20%">
失败
</td>
</tr>
<tr>
<td class="list_lt" width="20%"><a href="../Index/IntegralMall/BuyGoods">q6876956</a></td>
<td class="list_lt" width="40%">2016-10-07 10:39:10</td>
<td class="list_lt" width="20%">50</td>
<td class="list_lt" width="20%">
失败
</td>
</tr>
</tbody></table>
</div>
</div></div>


</div>
</td></tr>

</tbody></table>
</div>

<div id="postlistreply" class="pl comiis_postlistreply"><div id="post_new" class="viewthread_table" style="display: none"></div></div>


</div>

<form method="post" autocomplete="off" name="modactions" id="modactions">

<input type="hidden" name="formhash" value="eb5e5435">

<input type="hidden" name="optgroup">

<input type="hidden" name="operation">

<input type="hidden" name="listextra" value="">

<input type="hidden" name="page" value="1">

</form>


<div id="diyfastposttop" class="area"></div>

<script type="text/javascript">document.onkeyup = function(e){keyPageScroll(e, 0, 1, 'forum.php?mod=viewthread&tid=11020', 1);}</script>


</div></div>


<div class="wp mtn">

<!--[diy=diy3]--><div id="diy3" class="area"></div><!--[/diy]-->

</div>


<script type="text/javascript">


function succeedhandle_followmod(url, msg, values) {

var fObj = $('followmod_'+values['fuid']);

if(values['type'] == 'add') {

fObj.innerHTML = '不收听';

fObj.href = 'home.php?mod=spacecp&ac=follow&op=del&fuid='+values['fuid'];

} else if(values['type'] == 'del') {

fObj.innerHTML = '收听TA';

fObj.href = 'home.php?mod=spacecp&ac=follow&op=add&hash=eb5e5435&fuid='+values['fuid'];

}

}


fixed_avatar([3554233,3719407,3719409,3719411,3719416,3719420,3719423,3719428,3719431,3719523], 1);


</script>

<script src="../buygoods/js/jquery.min.js" type="text/javascript"></script>

</i></i></div><i><i>


<div style="clear:both;"></div>

<div class="comiis_footer cl">

<div id="ft" class="wp comiis_bottom cl ">

<div id="frt">

<a href="http://www.87870.com/" rel="nofollow" class="comiis_dlogo" target="_blank"><img src="../buygoods/image/logo.jpg" height="80"></a>

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

	<p>唐ICP证160473号   |   唐ICP备12034143号-3   |   唐公网安备 11010502031349号    |   广播电视节目制作经营许可证：（唐）字第04386号   |   唐网文(2015)2357-448号</p>
    <p>华北理工大学 Copyright © 2001-2016 tsjtjyw.com All rights reserved.</p>
</div>

</div>

</div>


<div class="fixedul" style="left: 1321.5px;">
<a href="http://bbs.87870.com/plugin.php?id=dsu_paulsign:sign" target="_blank"></a>
    <div class="Rboxli bli2"></div>
<a href="../Index/" target="_blank">
  <div class="Rboxli bli3"></div></a>
<a href="../Index/" target="_blank">
  <div class="Rboxli bli4"></div></a>
    <div class="Rboxli bli5" style="display: none;"></div>
      <div class="wximg"></div>
</div>

<div id="scrolltop">


<span><a href="http://bbs.87870.com/forum.php?mod=post&action=reply&fid=55&tid=11020&extra=&page=1" onclick="showWindow()" class="replyfast" title="快速回复"><b>快速回复</b></a></span>


<span hidefocus="true"><a title="返回顶部" onclick="window.scrollTo()" class="scrolltopa" href="javascript:;"><b>返回顶部</b></a></span>


<span>


<a href="http://bbs.87870.com/forum.php?mod=forumdisplay&fid=55" hidefocus="true" class="returnlist" title="返回列表"><b>返回列表</b></a>


</span>


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

<script type="text/javascript">
		function buyGoods()
		{
			<sec:authorize access="!hasRole('ROLE_USER')">
			if(confirm("是否前去登录？"))
			{
				$('#login-widow').show();				
			}
			</sec:authorize>
			alert("6");
		}
	</script>


		
<div id="discuz_tips" style="display:none;"></div>
			<script type="text/javascript">
				var tipsinfo = '46989062|X3.2|0.6||0||0|7|1477708344|7e6c411094537f7d0159c71f37f7e589|2';
			</script>
			<script src="../buygoods/js/discuz_tips.js" type="text/javascript" charset="UTF-8"></script>


</i></i></body></html>