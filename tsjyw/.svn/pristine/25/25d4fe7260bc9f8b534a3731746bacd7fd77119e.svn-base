<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" data-dpr="1" style="font-size: 52.0833px;">
<head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<meta http-equiv="Cache-control" content="no-cache">
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
<meta name="format-detection" content="telephone=no">
<title>通知系统</title>
<link rel="stylesheet" href="../newphone/noticesystem/css/reset.css" type="text/css" media="all">
<link rel="stylesheet" href="../newphone/noticesystem/css/style.css" type="text/css" media="all">
<link rel="stylesheet" href="../newphone/noticesystem/css/touch.css" type="text/css" media="all">
<script src="../newphone/noticesystem/js/jquery-1.8.3.min.js" type="text/javascript"></script>



<script src="../newphone/noticesystem/js/common.js" type="text/javascript" charset="utf-8"></script>

<script src="../newphone/noticesystem/js/adaptive-version2.js" type="text/javascript"></script>
<script src="../newphone/noticesystem/js/index.js" type="text/javascript"></script>




</head>

<body class="bg" style="height: 667px; margin-bottom: 0px; font-size: 18px;">
<!-- top -->
<div class="top">
   <div class="topL">
<div class="topimg">
	<a href="WeChatNewArticle" class="icon_userinfo">
<img src="../newphone/questionDetial/img/logosmall.png">
</a></div>
<i class="topL_yuan"></i>
   </div>
   <div class="topM">
   	通知
   </div>
</div>
<!-- headT -->




<!-- main forumlist start -->
<div class="comtain" id="wp">
  <!--一个开始-->
  <div class="connavli cl">
    <div class="connavliimg cl">
      <a href="RecvNotice"><img src="../newphone/noticesystem/img/common_60_icon.png" align="left" alt=""></a>
    </div>
    <a href="RecvNotice"><span class="connavlip">查看通知</span>
    </a>
  </div>
  <!--结束-->
  <c:if test="${IsOrNotCanSendNotice == 1}">
	  <div class="connavli cl">
	    <div class="connavliimg cl">
	      <a href="SendNotice"><img src="../newphone/noticesystem/img/common_55_icon.png" align="left" alt=""></a>
	    </div>
	    <a href="SendNotice"><span class="connavlip">发送通知</span></a>
	  </div>
	  <div class="connavli cl">
	    <div class="connavliimg cl">
	      <a href="WeChatSendedNotice"><img src="../newphone/noticesystem/img/common_55_icon.png" align="left" alt=""></a>
	    </div>
	    <a href="WeChatSendedNotice"><span class="connavlip">已发通知</span></a>
	  </div>
  </c:if>
  <div class="connavli cl">
    <div class="connavliimg cl">
      <a href="WeChatRecvAnnouncement"><img src="../newphone/noticesystem/img/common_55_icon.png" align="left" alt=""></a>
    </div>
    <a href="WeChatRecvAnnouncement"><span class="connavlip">查看公告</span></a>
  </div>
  <div class="clear"></div>
</div>
<!-- main forumlist end -->



<!-- footer -->
<footer>
        <div class="footer" id="footer" style="position: absolute; bottom: 0px;">
<p>
    唐山市睿智文化交流有限公司<br>
     Copyright © 2001-2016 www.tsjtjyw.com All rights reserved.
</p></div>
    </footer>
    
</body></html>




