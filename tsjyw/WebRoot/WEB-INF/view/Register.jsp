<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>唐山家庭教育网-用户注册</title>
    <meta name="keywords" content="">
    <meta name="description" content="">
    <link href="../css/style.css" rel="stylesheet">
    <link href="../css/personal.css" rel="stylesheet">
    <script type="text/javascript" src="../js/jquery.js"></script>
    <script src="../js/jquery.base64.js" type="text/javascript"></script>
    <script type="text/javascript" src="../js/acc_public.js"></script>
    <!-- <script type="text/javascript" src="../js/validform.js"></script>
    <script type="text/javascript" src="../js/acc_reg.js"></script>-->
    <script type="text/javascript">
    	function validateForm() {
    		return true;
    	}
    	
    	function onRegister() {
    		if (!validateForm())
    			return;
    			
    		var btnobj = $("#acc_submit");
    		btnobj.attr("disabled", false);
    		$.ajax({
                type: "post",
                url: "../Account/userRegister",
                cache: false,
                dataType: 'json',
                data: {
                    userName: $("#username").val(),
                    password: $("#password").val(),
                    realName: $("#realname_acc").val(),
                    idcard: $("#idcard_acc").val(),
                    weixin: $("#weixin_acc").val(),
                    email:$("#email_acc").val(),
                    phone:$("#phone_acc").val(),
                    ${_csrf.parameterName}: "${_csrf.token}"
                    //vcode: $("#vcode").val(),
                },
                beforeSend: function (XMLHttpRequest) {
                    btnobj.val("正在提交...");
                    btnobj.attr("disabled", true);
                },
                success: function (data) {
                    if (data.code == "succ") {//注册成功
                    	btnobj.attr("disabled", false);
                    	btnobj.val("注册成功,正在跳转...");
                    	alert("注册成功");
                    	location.href = "../Account/Login"
                    }
                    else {//注册失败
                    	alert(data.data);
                        //changeimgcode('vcodesj');
                        btnobj.attr("disabled", false);
                        btnobj.val("重新提交");
                    }
                },
                error: function (data) {
                    //alert('系统错误，请尝试重新提交');
                    $this.addClass("resend").siblings(".info").text("系统错误，请尝试重新提交").removeClass("Validform_right").addClass("Validform_wrong");
                    changeimgcode('vcodesj');
                    btnobj.attr("disabled", false);
                    btnobj.val("重新提交");
                }
            });
    	}
    </script>
</head>
<body class="body_bg">
    <!-- header -->
    <div class="login_bar">
        <div class="w1170 bc clearfix">
            <p class="bar fr pr10" id="header">
               <!-- <script type="text/javascript" src="/personal/js/loadheader.js?v=1.0"></script>-->
            </p>
            <p class="pl10 color85">唐山家庭教育网</p>
        </div>
    </div>
    <!-- header end-->
    <div class="person_banner">
        <div class="w1170 banner_hei bc relative">
            <h1 class="person_h1">账户中心</h1>
        </div>
    </div>
    <div class="tag w1170 bc">
        <a href="http://www.tsjtjyw.com">首页</a> &gt; 账户中心 &gt; 用户注册
    </div>
    <div class="qp_pageprompt" style="display: none">
    </div>
    <div class="w1170 bc clearfix personal">
        <div id="personalContent" class="personal_content_login">
            <div class="content_wp" id="sucessdiv" style="display: none;">
                <div class="content_item no-pb">
                    <fieldset>
                        <legend>注册账号</legend>
                        <div class="cg">
                            <div class="gxn">
                                <dl>
                                    <dt>
                                        <img alt="" src="../images/reg03.png"></dt>
                                    <dd>
                                        <h3>  恭喜您！注册成功</h3>
                                        <p><span id="span_acc"></span></p>
                                        <!--<a class="xfbtn" href="/account/usermanage.aspx">·µ»ØÎÒµÄÍ¨ÐÐÖ¤</a>-->
                                    </dd>
                                </dl>
                            </div>
                            <div class="aq">
                                <p id="safelevel">您的安全级别为：<span class="d">低</span><span class="">中</span><span class="">高</span></p>
                                <p class="attention"> 温馨提示：</p>
                                <p>为提升账号安全建议您<a href="/account/myphone.aspx">绑定手机</a>、<a href="/account/myemail.aspx">绑定邮箱</a>、
                                <a href="###">设置密码问题提示</a></p>
                            </div>
                        </div>
                    </fieldset>
                </div>
            </div>
            <div class="content_wp" id="regdiv">
                <form>
                <div class="content_item no-pb">
                    <fieldset>
                        <legend>注册账号</legend>
                        <div class="line mb30">
                        </div>
                        <div id="regGuide" class="login_tab bc">
                            <a class="current">账号注册</a><!--<a>手机注册</a>  -->
                        </div>
                        <div id="acc_reg0" class="text_wrapper ">
                            <div id="accWrapper">
                                <div class="reg_wrapper">
                                    <dl>
                                        <dt>用户名</dt>
                                        <dd>
                                            <input type="text" class="txt" id="username" name="username" placeholder="用户名" />&nbsp;&nbsp;&nbsp;<font color="red" >**</font><span
                                                class="info">请以字母开头，长度6~16位。可包含数字、字母、_</span></dd>
                                    </dl>
                                    <dl>
                                        <dt>密码</dt>
                                        <dd>
                                            <input type="password" class="txt" id="password" name="password" placeholder="密码" />&nbsp;&nbsp;&nbsp;<font color="red">**</font><span
                                                class="info">密码长度6-16位，可包含字母、数字和常用字符，不可带空格</span></dd>
                                    </dl>
                                    <dl>
                                        <dt>确认密码</dt>
                                        <dd>
                                            <input type="password" name="password_acc2" id="password_acc2" class="txt" placeholder="确认密码" />&nbsp;&nbsp;&nbsp;<font color="red">**</font><span
                                                class="info"></span></dd>
                                    </dl>
                                    <div class="line mb15">
                                    </div>
                                   <dl>
                                        <dt>真实姓名</dt>
                                        <dd>
                                            <input type="text" name="realname_acc" id="realname_acc" class="txt" placeholder="真实姓名" /><span
                                                class="info"></span></dd>
                                    </dl>
                                    <dl>
                                        <dt>微信号</dt>
                                        <dd>
                                            <input type="text" name="weixin_acc" id="weixin_acc" class="txt" placeholder="微信号"><span
                                                class="info">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;通过微信发送与您相关的通知公告</span></dd>
                                    </dl>
                                    <dl>
                                        <dt>邮箱</dt>
                                        <dd>
                                            <input type="text" name="email_acc" id="email_acc" class="txt" placeholder="邮箱号">&nbsp;&nbsp;&nbsp;<font color="red">**</font><span
                                                class="info">此邮箱用于找回密码</span></dd>
                                    </dl>
                                    <dl>
                                        <dt>手机号</dt>
                                        <dd>
                                            <input type="text" name="phone_acc" id="phone_acc" class="txt" placeholder="手机号"><span
                                                class="info"></span></dd>
                                    </dl>
                                    <!-- 
                                    <dl>
                                        <dt>验证码</dt>
                                        <dd>
                                            <input class="regtxt txt" id="vcode" name="vcode" placeholder="验证码" />
                                            <img id="vcodesj" src="tools/verify_code.ashx" width="93" height="40" />
                                            <a id="change_code" class="reg_change" href="javascript:;">换一张</a> <span class="info">
                                            </span>
                                        </dd>
                                    </dl>-->
                                    <div class="line mb5">
                                    </div>
                                    
                                    <dl class="mb5">
                                        <dt>&nbsp;</dt>
                                        <dd>
                                            <input id="yhxy_acc" name="yhxy_acc" type="checkbox" class="check" checked="checked" /><a 
                                                class="xy" href="Treaty" target="_blank" >我已阅读并同意<font color="red">《唐山家庭教育网网络服务协议》</font></a>
                                                
                                          </dd>
                                    </dl>
                                    
                                    <dl>
                                        <dt>&nbsp;</dt>                                                                                                                                                                                  
                                        <dd>
                                            <input id="acc_submit" class="acc_submit to_reg" type="button" value="注册" onclick="onRegister()"/></dd>
                                    </dl>
                                    <dl>
                                        <dt>&nbsp;</dt>
                                        <dd><a href="login.html" class="reglog">立即登录</a><span class="qqwbdlp">第三方登录：</span><a href="/qq/default.aspx?redirectUri=" class="qqdlbut"></a><a href="/sina/default.aspx?redirectUri=" class="wbdlbut"></a> </dd>
                                    </dl>
                                    <div class="clear">
                                    </div>
                                </div>
                                <!--<div class="attention">
                                    <h3>
                                        温馨提示：</h3>
                                    <p>
                                        1、身份证信息将作为账号所有权的最终凭证。身份证信息一经填写不能修改，请认真填写。</p>
                                    <p>
                                        2、请您填写真实的身份信息，如果您的当前年龄小于18周岁，您将会被纳入防沉迷系统。</p>
                                    <p>
                                        3、相关内容请查阅<a href="http://www.87870.com/ParentsCare/index.html" target="_blank">《网络游戏未成年人家长监护工程》</a></p>
                                </div>-->
                            </div>
                        </div><!-- 
                        <div id="acc_reg1" class="text_wrapper displayN">
                            <div id="mobWrapper">
                                <div class="reg_wrapper">
                                    <dl>
                                        <dt>手机号</dt>
                                        <dd>
                                            <input type="text" class="txt" id="mobile" name="mobile" placeholder="手机号码" />
                                            <span class="info">11位手机号码 </span>
                                        </dd>
                                    </dl>
                                    <dl>
                                        <dt>密码</dt>
                                        <dd>
                                            <input type="password" class="txt" id="mob_password" name="mob_password" placeholder="密码" />
                                            <span class="info">由6-20位字符组成</span>
                                        </dd>
                                    </dl>
                                    <dl>
                                        <dt>确认密码</dt>
                                        <dd>
                                            <input type="password" name="password_mob2" id="password_mob2" class="txt" placeholder="确认密码" /><span
                                                class="info">确认密码</span>
                                        </dd>
                                    </dl>
                                    <div class="line mb15">
                                    </div>
                                    <dl>
                                        <dt>真实姓名</dt>
                                        <dd>
                                            <input type="text" name="realname_mob" id="realname_mob" class="txt" placeholder="真实姓名" /><span
                                                class="info">真实姓名</span></dd>
                                    </dl>
                                    <dl>
                                        <dt>身份证号</dt>
                                        <dd>
                                            <input type="text" name="idcard_mob" id="idcard_mob" class="txt" placeholder="身份证号码" /><span
                                                class="info">身份证号码</span></dd>
                                    </dl>
                                    <dl>
                                        <dt>验证码</dt>
                                        <dd>
                                            <input class="mobile_code_reg txt vcode" id="mvcode" name="vcode" placeholder="验证码" />
                                            <img id="mvcodesj" src="tools/verify_code.ashx" width="93" height="40" />
                                            <a id="mchange_code" class="change_code" href="javascript:changeimgcode('mvcodesj');">
                                                换一张</a> <span class="info">验证码</span>
                                        </dd>
                                    </dl>
                                    <dl>
                                        <dt>短信验证码</dt>
                                        <dd>
                                            <input class="mobile_code_reg txt vcode" id="mob_vcode" name="vcode" placeholder="短信验证码" />
                                            <input type="button" id="get_mcode" name="get_mcode" class="get_mcode" value="获取手机验证码" />
                                            <span class="info">短信验证码</span>
                                        </dd>
                                    </dl>
                                    <div class="line mb5">
                                    </div>
                                    <dl class="mb5">
                                        <dt>&nbsp;</dt>
                                        <dd>
                                            <input id="yhxy_mob" name="yhxy_acc" type="checkbox" class="check" checked="checked" /><a
                                                class="xy" href="/account/87870xy.html" target="_blank">我已阅读并同意《87870网络服务协议》</a><span
                                                    class="info"></span></dd>
                                    </dl>
                                    <dl>
                                        <dt>&nbsp;</dt>
                                        <dd>
                                            <input id="mob_submit" class="to_reg" type="button" value="注册"></dd>
                                    </dl>
                                    <div class="clear">
                                    </div>
                                </div>
                                <div class="attention">
                                    <h3>
                                        温馨提示：</h3>
                                    <p>
                                        1、身份证信息将作为账号所有权的最终凭证。身份证信息一经填写不能修改，请认真填写。</p>
                                    <p>
                                        2、请您填写真实的身份信息，如果您的当前年龄小于18周岁，您将会被纳入防沉迷系统。</p>
                                    <p>
                                        3、相关内容请查阅<a href="http://www.87870.com/ParentsCare/index.html" target="_blank">《网络游戏未成年人家长监护工程》</a></p>
                                    <p>
                                        4、认证的手机号码将作为账号所有权的最高权限判断，请填写正确常用的手机号码，并不要告诉他人相关信息。</p>
                                    <p>
                                        5、我们不会绑定任何收费服务，不会泄露您的手机号，请放心使用。</p>
                                </div>
                            </div>
                        </div> -->
                    </fieldset>
                </div>
                </form>
            </div>
        </div>
    </div>
    <%@include file="footer.jsp" %>
</body>
</html>
