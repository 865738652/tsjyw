<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
  <head xmls="http://www.w3.org/1999/xhtml">
  	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <meta charset="UTF-8">   
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <link rel="stylesheet" href="../css/main.css"/>
    <script src="../js/jquery-1.11.2.min.js"></script>
    <script src="../js/main.js"></script>
    <title>个人信息</title>
    <%@include file="WeChatheader.jsp" %>
   	<script type="text/javascript">
   		function modifyUser()
   		{
   			window.location.href="WeChatModifyUser"; 
   		}
   	</script>
  </head>
	<body style="background-color: #EAEDF1">
	<!--头部-->
		<header style="text-align: center;font-size: 16px">
		     个人信息
		</header>
			<div class="detail">
		    <div class="attr">
		        <div class="tr clearfix">
		            <div class="td td1 fl">姓名</div>
		            <div class="td td2 fl"><c:out value="${user.userName}" /></div>
		        </div>
		        <div class="tr clearfix">
		            <div class="td td1 fl">昵称</div>
		            <div class="td td2 fl"><c:out value="${user.userNickname}" /></div>
		        </div>
		        <div class="tr clearfix">
		            <div class="td td1 fl">编号</div>
		            <div class="td td2 fl"><c:out value="${user.userCode}" /></div>
		        </div>
		        <div class="tr clearfix">
		            <div class="td td1 fl">身份证号</div>
		            <div class="td td2 fl"><c:out value="${user.userIdentityCode}" /></div>
		        </div>
		        <div class="tr clearfix">
		            <div class="td td1 fl">微信号</div>
		            <div class="td td2 fl"><c:out value="${user.userWeixin}" /></div>
		        </div>
		        <div class="tr clearfix">
		            <div class="td td1 fl">QQ</div>
		            <div class="td td2 fl"><c:out value="${user.userQq}" /></div>
		        </div>
		        <div class="tr clearfix">
		            <div class="td td1 fl">电话号码</div>
		            <div class="td td2 fl"><c:out value="${user.userPhone}" /></div>
		        </div>
		        <div class="tr clearfix">
		            <div class="td td1 fl">邮箱</div>
		            <div class="td td2 fl"><c:out value="${user.userEmail}" /></div>
		        </div>
		        <div class="tr clearfix">
		            <div class="td td1 fl">积分</div>
		            <div class="td td2 fl"><c:out value="${user.userIntegral}" /></div>
		        </div>
		    </div>
		</div>
		<!--  <div class="detail">
		   
		    <div class="attr">
		        <div class="tr clearfix">
		            <div class="td td1 fl">姓名</div>
		            <div class="td td2 fl"><c:out value="${user.userName}" /></div>
		        </div>
		        <div class="tr clearfix">
		            <div class="td td1 fl">昵称</div>
		            <div class="td td2 fl"><c:out value="${user.userNickname}" /></div>
		        </div>
		        <div class="tr clearfix">
		            <div class="td td1 fl">编号</div>
		            <div class="td td2 fl"><c:out value="${user.userCode}" /></div>
		        </div>
		        <div class="tr clearfix">
		            <div class="td td1 fl">身份证号</div>
		            <div class="td td2 fl"><c:out value="${user.userIdentityCode}" /></div>
		        </div>
		        <div class="tr clearfix">
		            <div class="td td1 fl">微信号</div>
		            <div class="td td2 fl"><c:out value="${user.userWeixin}" /></div>
		        </div>
		        <div class="tr clearfix">
		            <div class="td td1 fl">我的地址</div>
		            <div class="td td2 fl"><c:out value="${user.userName}" /></div>
		        </div>
		        <div class="tr clearfix">
		            <div class="td td1 fl">性别</div>
		            <div class="td td2 fl"><c:out value="${user.userGender}" /></div>
		        </div>
		        <div class="tr clearfix">
		            <div class="td td1 fl">OpenID</div>
		            <div class="td td2 fl"><c:out value="${user.userOpenId}" /></div>
		        </div>
		        
		    </div>
		</div>
		-->
		<div class="weui-msg__opr-area">
            <p class="weui-btn-area">
                <a href="WeChatModifyUser" class="weui-btn weui-btn_primary">修改信息</a>
            </p>
        </div>
		        <!-- @end 活动中心  add by hanjw 20150917-->     

        <script src="../js/wappv.js"></script>   
        <script src="../js/touch.min.js"></script>
        
	</body>
</html>
