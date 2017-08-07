<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>修改信息</title>
    <link rel="stylesheet" href="../phone/css/main.css"/>
    <script src="../phone/js/jquery-1.11.2.min.js"></script>
    <script src="../phone/js/main.js"></script>
    <script src="../phone/js/upload.js"></script>

    <script src="../phone/js/jquery.js"></script>
    <script src="../phone/js/jquery.validate.min.js"></script>
    <script src="../phone/js/messages_zh.js"></script>

    
    <script type="text/javascript">
        $().ready(function() {
        // 在键盘按下并释放及提交后验证提交表单
          $("#signupForm").validate({
                rules: {
                  firstname: "required",
                  lastname: {
                  	required:true,
                  	minlength:4,
                  	regexp:/^[a-zA-Z0-9_]+$/
                  },
                  username: {
                    required: true,
                    minlength: 2
                  },
                  password: {
                    required: true,
                    minlength: 5
                  },
                  confirm_password: {
                    required: true,
                    minlength: 5,
                    equalTo: "#password"
                  },
                  email: {
                    required: true,
                    email: true
                  },
                  "topic[]": {
                    required: "#newsletter:checked",
                    minlength: 2
                  },
                  agree: "required"
                },
                messages: {
                  firstname: "请输入您的名字",
                  lastname: {
                  	required: "请输入账号",
                    minlength: "账号长度不能小于4 个字母"
                  },
                  username: {
                    required: "请输入用户名",
                    minlength: "用户名必需由两个字母组成"
                  },
                  password: {
                    required: "请输入密码",
                    minlength: "密码长度不能小于 5 个字母"
                  },
                  confirm_password: {
                    required: "请输入密码",
                    minlength: "密码长度不能小于 5 个字母",
                    equalTo: "两次密码输入不一致"
                  },
                  email: "请输入一个正确的邮箱",
                  agree: "请接受我们的声明",
                  topic: "请选择两个主题"
                }
            });
        });
    </script>

    <style>
        .error{
            color:red;
        }
    </style>
    

     <script type="text/javascript">
        
        
         function checkEmail(value)//验证手机号
         {
             var phone = value;
             var phoneReg =  /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
             if(phoneReg.test(phone) == false){
                 alert("邮箱格式不正确！");
                 return false;
             } 
         }
         
         function checkUserAccount(value)
         {
         	var account = value;
            var phoneReg =  /^[a-zA-Z0-9_]+$/;
            if(phoneReg.test(account) == false){
                alert("帐号只能有数字，字母组成");
                $("#lastname").val("");
                return false;
            }
         }
         
         
         function isNumberOrLetter(s)//判断是否是数字或字母,用于验证用户名，失去焦点验证。
         {
            var userName =s
            var regu = /^[0-9a-zA-Z]{4,16}$/;
            if (regu.test(userName) == false)
            {
	            document.getElementById ("userInfo").innerHTML="用户名格式不对！";
	            document.getElementById ("userInfo").style.color="red";
             	return false;
            }
            else
            {
	            document.getElementById ("userInfo").innerHTML="用户名通过！";
	            document.getElementById ("userInfo").style.color="green";
            }
         }
         
         function checkIdentity(s)
         {
         	var userName =s
            var regu =/(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
            if (regu.test(userName) == false)
            {
	            document.getElementById ("identityInfo").innerHTML="身份证号格式不对！";
	            document.getElementById ("identityInfo").style.color="red";
             	return false;
            }
            else
            {
            	document.getElementById ("identityInfo").innerHTML="";
            	return true;
            }
         }
         function checkPhone(s)
         {
         	var isPhone = /^([0-9]{3,4}-)?[0-9]{7,8}$/;
		    var isMob=/^((\+?86)|(\(\+86\)))?(13[012356789][0-9]{8}|15[012356789][0-9]{8}|18[02356789][0-9]{8}|147[0-9]{8}|1349[0-9]{7})$/;
		    var value=s;
		    if(isMob.test(value) || isPhone.test(value))
		    {
		    	document.getElementById ("phoneInfo").innerHTML="";
		        return true;
		    }
		    else{
		        document.getElementById ("phoneInfo").innerHTML="电话号码格式不对！";
	            document.getElementById ("phoneInfo").style.color="red";
             	return false;
		    }
         }
         
         function modifyUser()
         {
         	$.ajax({
         		cache: true,
                type: "POST",
                url:"startModifyUser",
                data:$('#signupForm').serialize(),// 你的formid
                dataType:"json",
                async: false,
                error: function(request) {
                    alert("Connection error");
                },
                success:function(data) {
                    if(data.code == "succ")
                    {
                    	alert("修改成功");
                    	window.location.href="MyDetail"; 
                    }
                    else
                    {
                    	alert("修改失败");
                    }
                }
         	});
         }
         
         
    </script>

    <title></title>
</head>
<body style="background-color: #EAEDF1">
<!--头部-->
<header style="text-align: center;font-size: 16px">
    <i class="iconfont fl" onclick="javascript:history.go(-1);">&#xe607;</i> 修改信息
</header>

<div class="join">
    <form class="cmxform" id="signupForm" action="RegisterCheck" method="get">
    	<input type="hidden" name="userId" value="${user.userId}">
    	<input type="hidden" name="userCode" value="${user.userCode}">
    	<input type="hidden" name="userGender" value="${user.userGender}">
	    <p>
            <label for="firstname">昵称</label>
            <input id="firstname" name="userNickname" type="text" value="${user.userNickname}">
        </p>
        <p>
            <label for="password">密码</label>
            <input id="password" name="userPassword" type="password" value="${user.userPassword}"/>
        </p>
        <p>
            <label for="confirm_password">验证密码</label>
            <input id="confirm_password" name="confirm_password" type="password" placeholder="请再次输入密码" value="${user.userPassword}"/>
        </p>
        <p>
            <label for="username">真实姓名</label>
            <input id="username" name="userName" type="text" value="${user.userName}">
        </p>
        <p>
            <label for="email">邮箱</label>
            <input id="email" name="userEmail" type="text" value="${user.userEmail}">
        </p>
        <p>
            <label>微信号</label>
            <div>
                <input type="text" id="userWeixin" name="userWeixin" value="${user.userWeixin}"/>
            </div>
        </p>

        <p>    
            <label>QQ号<b id="userQq"></b></label>
            <div>
                <input type="text" id="userQq" name="userQq" value="${user.userQq}">
            </div>
        </p>

        <p>
            <label>手机号<b id="phoneInfo"></b></label>
            <div>
                <input type="text" name="userPhone" id="phone" onblur="checkPhone(value)" value="${user.userPhone}">
            </div>
        </p>

        <p>
            <label>身份证号<b id="identityInfo"></b></label>
            <div>
                <input type="text" id="identityCode" name="userIdentityCode" onblur="checkIdentity(value)" value="${user.userIdentityCode}">
            </div>
        </p>
    </form>
    <input type="submit" value="确认修改" onclick="modifyUser()"/>
</div>
        <!-- @end 活动中心  add by hanjw 20150917-->     

        <script src="../js/wappv.js"></script>   
        <script src="../js/touch.min.js"></script>
        <%@include file="WeChatToolbar.jsp" %>
</body>
</html>