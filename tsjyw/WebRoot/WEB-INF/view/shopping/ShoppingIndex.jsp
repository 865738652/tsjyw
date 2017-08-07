<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- saved from url=(0042)http://bbs.87870.com/plugin.php?id=auction -->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>积分商城</title>

  <meta name="keywords" content="">
  <meta name="description" content=",87870">
  <meta name="generator" content="Discuz! X3.2">
  <meta name="author" content="Discuz! Team and Comsenz UI Team">
  <meta name="copyright" content="2001-2013 Comsenz Inc.">
  <meta name="MSSmartTagsPreventParsing" content="True">
  <meta http-equiv="MSThemeCompatible" content="Yes">
  <script type="text/javascript">
    var STYLEID = '4', STATICURL = 'static/', IMGDIR = 'template/87870pc/img', VERHASH = 'FEK', charset = 'utf-8', discuz_uid = '0', cookiepre = 'kfEE_2132_', cookiedomain = '', cookiepath = '/', showusercard = '1', attackevasive = '0', disallowfloat = 'newthread', creditnotice = '1|威望|,2|金钱|,3|贡献|', defaultstyle = '', REPORTURL = 'aHR0cDovL2Jicy44Nzg3MC5jb20vcGx1Z2luLnBocD9pZD1hdWN0aW9u', SITEURL = 'http://bbs.87870.com/', JSPATH = 'static/js/', CSSPATH = 'data/cache/style_', DYNAMICURL = '';
  </script>

  <%@include file="Shoppingheader.jsp" %>
  <link href="../shopping/css/style_auction.css" type="text/css" rel="stylesheet">
  <script src="../shopping/js/shangcheng.js" type="text/javascript"></script>
  <script src="../shopping/js/jquery-1.8.3.min.js" type="text/javascript"></script>
  <script type="text/javascript">
  	
  	window.onload = function(){
  		getHotBuy(addHotBuyGoods,1);
  		getHotBuy(addHotBuyGoods,2);
  	};
  	function getHotBuy(onSuccess,type)
  	{
  		var searchForm = new Object();
  		searchForm.type = type;
  		var param = {
  			pageNumber:$("#recenthotNumber").val(),
  			search:JSON.stringify(searchForm)
  		}
  		$.ajax({
  			url:"Shopping/getHotBuy",
  			type:"get",
  			data:param,
  			dataType:"json",
  			beforeSend:function(){
  				if(type == 1 || type == "1")
  					$(".recenthotMore").html("正在加载...");
  				else if(type == 2 || type == "2")
  					$(".recentexchangeMore").html("正在加载...");
  			},
  			success:function(data){
  				onSuccess(data,type);
  			},
  			error:function(){
  			
  			}
  			
  		});
  	}
  	
  	function addHotBuyGoods(data,type)
  	{
  		if(type == 1 || type == "1")
  		{
  			
  			var parentNode = $("#recenthot");
  			var moreNode = $(".recenthotMore");
  			var pageNumberNode = $("#recenthotNumber");
  			var stateNode = '<p id="time_11021" class="sh_button">查看详情 >></p></div>';
  		}
  		if(type == 2 || type == "2")
  		{
  			var parentNode = $("#recentexchange");
  			var moreNode = $(".recentexchangeMore");
  			var pageNumberNode = $("#recentexchangepageNumber");
  			var stateNode = '<p id="time_12063" class="sh_button2">已经结束</p>';
  		}
  		if(type == 3)
  		{
  			return;
  		}
  		if(data.code == "succ")
  		{
	  		for(var i=0;i<data.data.length;i++)
			{
				var goodsHtml = '<div class="shwLI"><div class="shliimg"><div class="number" title="先到先得">先到先得</div>'+
				'<a href="../Index/GoodsDetial?goodsId='+data.data[i].goodsId+'" title="'+data.data[i].goodsName+'">'+
				'<img src="'+data.data[i].goodsImagaPath+'"/></a></div>'+
				'<p class="shname"><a href="../Index/GoodsDetial?goodsId='+data.data[i].goodsId+'" target="_blank">'+data.data[i].goodsName+'</a></p>'+
				'<p class="sh_money"><span>价&nbsp;&nbsp;&nbsp;格：</span><em>'+data.data[i].goodsPrice+'</em> 积分</p>'+
				'<p class="a_ct" id="vd_11021" style="display:none;">'+
				'<a href="../Index/GoodsDetial?goodsId='+data.data[i].goodsId+'" target="_blank" style="background-image:url(source/plugin/auction/images/auction.png);">查看详情››</a></p>'+
				stateNode;
				parentNode.append(goodsHtml);
				moreNode.html("下一页 &raquo;");
			}
			if(data.data.length < 8)
			{
				moreNode.html("到达最后一页");
				moreNode.removeAttr('onclick');
			}
			else
			{
				var a = pageNumberNode.val();
				pageNumberNode.val(parseInt(a)+1);
			}
					
  		}
		else
		{
			alert("加载失败");
		}
  	}
  	
  	function getrecenthotMore()
  	{
  		getHotBuy(addHotBuyGoods,1);
  	}
  	function getrecentexchangeMore()
  	{
  		getHotBuy(addHotBuyGoods,2);
  	}
  	
  </script>
<style type="text/css">
  	#autopbn{display:block;margin-bottom:10px;border:1px solid rgb(194,213,227);border-radius:3px;text-align:center;}
	#autopbn:hover{background:#fff;text-decoration:none;}
	#autopbn{border:1px solid #e5e5e5;}
	#autopbn{background:#fff;border-radius:0 0 5px 5px;border:1px solid #e5e5e5;border-top:none;color:#aab2bd;font-size:12px;height:24px;letter-spacing:0;line-height:22px;margin:-1px auto 0;padding:0;width:130px;display:block;text-align:center;}
	#autopbn:hover{color:#ff6f3d;text-decoration:none;}
	#autopbn i{display:inline-block;height:0;width:0;margin-right:8px;border-color:#aab2bd transparent transparent;border-style:solid dashed dashed;border-width:5px 5px 0;vertical-align:middle;}
	#autopbn:hover i{border-color:#ff6f3d transparent transparent;}.ie6 
	#autopbn i{display:none;}
</style>
</head>

<body id="nv_plugin" class="pg_auction comiis_wide kmfn">


<!--header开始-->
<%@include file="Shoppingtop.jsp" %>
<!--header结束-->

<!--搜索的两个框开始-->
<!-- 
<div id="append_parent">
  <div id="sctype_ctrl_menu" class="sltm" style="display: none; width: 60px;">
    <ul>
      <li class="current">请选择类型</li>
      <li>兑&nbsp;&nbsp;&nbsp;换</li>
      <li>抽&nbsp;&nbsp;&nbsp;奖</li>
      <li>竞&nbsp;&nbsp;&nbsp;拍</li>
    </ul>
  </div>
  <div id="sctime_ctrl_menu" class="sltm" style="display: none; width: 60px;">
    <ul>
      <li class="current">请选择状态</li>
      <li>正在进行</li>
      <li>即将开始</li>
      <li>已经结束</li>
    </ul>
  </div>
</div>
 -->
<!--搜索的两个框结束-->


<div id="ajaxwaitid"></div>

<div id="wp" class="wp">
  <!--右上角-->
  <div id="pt" class="wp cl">
    <div class="z">
      <a href="javascript:void(0);" class="nvhm">87870</a><em>›</em><a href="http://bbs.87870.com/plugin.php?id=auction">积分商城</a>
    </div>
  </div>
  <!--右上角-->

  <div id="wp_auc" class="wp cl">

    <div class="shopright">
      <div class="shopimg">
        <a href="javascript:void(0);" target="_blank" title="了解积分交易"><img src="../shopping/img/shopimg.jpg"></a>
      </div>
      <div id="userinfo" class="userloading">
        <p id="login">
          <a href="javascript:void(0);">会员登录</a>
        </p>
        <p>登录后，立即查看您的交易积分</p>
      </div>
      <div id="" class="sh_viewleft" style="padding:0;width:auto;">
        <div class="sh_Rtit">热门交易排行</div>
        
        
        <!--一个商品开始-->
          <c:if test="${not empty hotrank}">
    		<c:set var="index" value="0"/>
    		<c:forEach var="item" items="${hotrank}">
    				<c:set var="index" value="${index+1}"/>
	    				<div class="sh_Rbot">
				            <a href="../Index/GoodsDetial?goodsId=${item.goodsId}" target="_blank">
				            <img class="sh_Rimg" src="${item.goodsImagaPath}"></a>
				            <span class="sh_Rp">
				                <p class="sh_Rp1">
				            <span class="order"><c:out value="${index}"/></span>
				                <a href="../Index/GoodsDetial?goodsId=${item.goodsId}" target="_blank" title="<c:out value="${item.goodsName}"/>"><c:out value="${item.goodsName}"/></a>
				                </p>
				                <p class="sh_Rp2">价格：<em><c:out value="${item.goodsPrice}"/></em> 积分</p>
				                <a href="../Index/GoodsDetial?goodsId=${item.goodsId}" target="_blank" class="sh_button">抢&nbsp;&nbsp;&nbsp;购</a>
				                </p>
				            </span>
				        </div>
    		</c:forEach>
    	</c:if>
        <!--一个商品结束-->
      </div>
    </div>

    <!--搜索的框-->
    <!-- 
    <div style="padding-bottom:10px;">
      <form id="scform">
        <table cellspacing="0" cellpadding="0">
          <tbody>
            <tr>
              <td>
                <input type="text" autocomplete="off" class="px z ausc xg1" id="srctxt" name="srctxt" placeholder="请输入搜索内容1">
              </td>
              <td>
                <span class="ftid">
                  <select name="sctype" id="sctype" width="60" class="ps" selecti="0" style="display: none;">
                    <option value="0"></option>
                  </select>
                    <a href="javascript:;" id="sctype_ctrl" style="width:60px" tabindex="1">请选择类型</a>
                </span>
              </td>
              <td>
                <span class="ftid">
                  <select name="sctime" id="sctime" width="60" class="ps" selecti="0" style="display: none;">
                    <option value="0"></option>
                  </select>
                  <a href="javascript:;" id="sctime_ctrl" style="width:60px" tabindex="1">请选择状态</a>
                </span>			
              </td>
              <td>
                <button value="true" class="pn pnc" id="search_submit" name="searchsubmit" type="submit"><strong>搜索</strong></button>
              </td>
            </tr>
          </tbody>
        </table>
      </form>
      <script type="text/javascript">
      initSearchmenu('srctxt');
      simulateSelect('sctype');
      simulateSelect('sctime');
      $('scbar').style.display = 'none';
      </script>
    </div>
     -->
    <!--搜索的框结束-->

    <!--最近热卖和往期交易开始-->
    <div class="shopleft" id="left">
      <div class="sh_lone cl">
        <h2 class="shtitle">
          <!--<a class="sh_more" href="javascript:void(0);">更多</a>-->
          <input type="hidden" value="1" id="recenthotNumber"/>
          <span>最近热卖</span>
        </h2>

        <div class="shword cl" id="recenthot">
          <!--一件商品开始-->
          <!--一件商品结束-->
        </div>
        <hr>
    <a href="javascript:;" id="autopbn" class="recenthotMore" onclick="getrecenthotMore()"><i></i>下一页 &raquo;</a>
      </div>		

      <!--对换流程的图片-->
      <div style="padding-bottom:10px;">
        <img src="../shopping/img/shop_line.jpg">
      </div>		
      <!--对换流程的图片-->


      <div class="sh_lone">			
        <h2 class="shtitle">
          <!--<a href="javascript:void(0);" class="sh_more">更多</a>-->
          <input type="hidden" value="1" id="recentexchangepageNumber">
          <span>往期交易</span>
        </h2>
        <div class="shword cl" id="recentexchange">
          <!--一件商品开始-->
          <!--一件商品结束-->
        </div>
        <hr>
    <a href="javascript:;" id="autopbn" class="recentexchangeMore" onclick="getrecentexchangeMore()"><i></i>查看下一页</a>		
      </div>
    </div>
    <!--最近热卖和往期交易结束-->
    
  </div>
  
</div>


<div style="clear:both;"></div>


<!--底部开始-->
<%@include file="Shoppingbottom.jsp" %>
<!--底部结束-->


</body>
</html>
