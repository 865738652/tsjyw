<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<div class="header">
  <div class="midcoment">
    <div class="logo"> 
      <a href="../Index/" title="唐山教育网" target="_blank"><img src="../images/logo.png" height="70"></a> 
    </div>
    <ul class="nav">
      <li id="mn_portal">
        <a href="Shopping">积分商城</a>
      </li>
      <li id="mn_Ne30e">
        <a href="../Index/" hidefocus="true">官网首页</a>
      </li>
      <li id="mn_F1">
        <a href="MyExchange" hidefocus="true">兑换记录</a>
      </li>
      <!--
      <li id="mn_F36">
        <a href="javascript:void(0);" hidefocus="true">资源分享</a>
      </li>
      <li id="mn_F41">
        <a href="javascript:void(0);" hidefocus="true">活动专区</a>
      </li>
      <li id="mn_N96cb">
        <a href="javascript:void(0);" hidefocus="true">专家专区</a>
      </li>
      <li id="mn_N8e2e">
        <a href="javascript:void(0);" hidefocus="true">官网首页</a>
      </li>
      -->
    </ul>
    <!-- 未登录状态 -->
     
    <div class="loginTone"> 
    	<a href="../Account/Register" title="" class="buttonlog logHOV">注册</a>
	    <sec:authorize access="!hasRole('ROLE_USER')">
	    	<a href="javascript:void(0);" onclick="showLoginWindow()" title="" class="buttonlog">登录</a> 
	    </sec:authorize>
	    <sec:authorize access="hasRole('ROLE_USER')">
     	  <a href="#" onclick="document.getElementById('logout').submit();" class="buttonlog" >[注销]</a>
     	  <a href="../Admin/" id="login-pop" class="buttonlog" style="width:100px;"><sec:authentication property="name"/></a>     	  
     	</sec:authorize>
     	<c:url var="logoutUrl" value="/j_spring_security_logout" />
		  <form action="${logoutUrl}" id="logout" method="post">
			  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		  </form>
    </div> 
    <!-- 登录状态 -->
    
    <div class="searchbox">
      <form id="searchform" class="searchform" method="post" autocomplete="off"> 
        <input type="text" class="searchinput" id="searchinput" name="srchtxt">
        <input type="button" value="" class="searchbuton" id="searchbuton" onclick="fondser()">
      </form>
    </div>
  </div>
</div>





