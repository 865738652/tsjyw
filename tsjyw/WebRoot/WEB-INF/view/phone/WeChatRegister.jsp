<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
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
         
         
         /*function isNumberOrLetter(s)//判断是否是数字或字母,用于验证用户名，失去焦点验证。
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
             }*/
    function checkForm()
    {
    	alert("检查开始");
    	var lastname = $("#lastname").val();
    	var lastnameReg =  /^[a-zA-Z0-9_]+$/;
        if(lastnameReg.test(lastname) == false){
            alert("账号格式不正确");
            $("#lastname").val("");
            return false;
        }
        var password = $("#password").val();
        var passwordReg = /^[\w]{4,12}$/;
        if(passwordReg.test(password) == false){
        	alert("密码由4-12位组成");
            $("#password").val("");
            return false;
        }
    	var firstname = $("#firstname").val();
    	if(firstname == ""){
    		alert("昵称格式不正确");
    		$("#firstname").val("");
            return false;
    	}
    	
    	var username = $("#username").val();
    	if(username == ""){
    		alert("用户名格式不正确");
    		$("#username").val("");
            return false;
    	}
    	
    	var email = $("#email").val();
    	var emailReg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
    	if(emailReg.test(email) == false){
    		alert("邮箱格式不正确");
    		$("#email").val("");
            return false;
    	}
    	return true;
    }        
            
            
    </script>

    <title></title>
</head>
<body style="background-color: #EAEDF1">
<!--头部-->
<header style="text-align: center;font-size: 16px">
    <i class="iconfont fl" onclick="javascript:history.go(-1);">&#xe607;</i> 注册
</header>

<div class="join">
    <form class="cmxform" id="signupForm" action="RegisterCheck" method="post">
        <!-- <label>用户名<b id="userInfo"></b></label>
        <div>
            <input type="text" onblur="isNumberOrLetter(value)" placeholder="请输入您的昵称"/>
        </div>

        <label>手机号<b id="phoneInfo"></b></label>
        <div>
            <input type="text"  onblur="checkPhone(value)" placeholder="请输入您的手机号"/>
        </div> -->
        <p>
            <label for="lastname">账号</label>
            <input id="lastname" name="userAccount" type="text" onblur="checkUserAccount(value)" placeholder="由字母、数字组成，不得少于4位数">
            
        </p>
	    <p>
            <label for="firstname">昵称</label>
            <input id="firstname" name="userNickname" type="text" placeholder="请输入您的昵称">
        </p>
        <p>
            <label for="password">密码</label>
            <input id="password" name="userPassword" type="password" placeholder="请输入密码">
        </p>
        <p>
            <label for="confirm_password">验证密码</label>
            <input id="confirm_password" name="confirm_password" type="password" placeholder="请再次输入密码">
        </p>
        <p>
            <label for="username">真实姓名</label>
            <input id="username" name="userName" type="text" placeholder="请输入您的真实姓名">
        </p>



        <p>
            <label>性别</label>
            <div>
                <select name="userGender" id="sex">
                    <option value="0">男</option>
                    <option value="1">女</option>
                </select>
            </div>
        </p>

        <p>
        	<label>生日</label>
        	<div>
            	<input type="date" name="userbirthday" placeholder="请输入出生年月"/>
        	</div>
        </p>
        
        <p>
            <label for="email">邮箱</label>
            <input id="email" name="email" type="text" placeholder="输入邮箱，用于找回密码"/>
        </p>
        
        <p>                              
        <input  id="yhxy_acc" name="yhxy_acc" type="checkbox" class="check" checked="checked" /><a 
         class="xy" href="WeChatTreaty" target="_blank" >我已阅读并同意<font color="red">《唐山家庭教育网网络服务协议》</font></a>
         </p>
        
        <!-- <p>
            <label>微信号</label>
            <div>
                <input type="text" id="weixin" placeholder="请输入您的微信号"/>
            </div>
        </p>

        <p>    
            <label>QQ号</label>
            <div>
                <input type="text" id="qq" placeholder="请输入您的QQ号"/>
            </div>
        </p>

        <p>
            <label>手机号<b id="phoneInfo"></b></label>
            <div>
                <input type="text"  onblur="checkPhone(value)" placeholder="请输入您的手机号"/>
            </div>
        </p>

        <p>
            <label>身份证号</label>
            <div>
                <input type="E-mail" placeholder="请输入您的身份证号"/>
            </div>
        </p> -->
        <!-- 上传头像-->
        <!-- 上传二维码-->
        <!-- <div class="qrcode fr">
            <div id="preview1">
                <img id="imghead1" src="img/head_180.jpg" />
            </div>
            <input type="file" onchange="previewImage1(this)" id="file1" class="upload" >
            <label for="file1"><svg xmlns="http://www.w3.org/2000/svg" width="15" height="12" viewBox="0 0 20 17"><path d="M10 0l-5.2 4.9h3.3v5.1h3.8v-5.1h3.3l-5.2-4.9zm9.3 11.5l-3.2-2.1h-2l3.4 2.6h-3.5c-.1 0-.2.1-.2.1l-.8 2.3h-6l-.8-2.2c-.1-.1-.1-.2-.2-.2h-3.6l3.4-2.6h-2l-3.2 2.1c-.4.3-.7 1-.6 1.5l.6 3.1c.1.5.7.9 1.2.9h16.3c.6 0 1.1-.4 1.3-.9l.6-3.1c.1-.5-.2-1.2-.7-1.5z"></path></svg> <span>上传微信二维码…</span></label>
        </div> -->
        <input type="submit" value="注册" onclick="return checkForm()">
    </form>
</div>
        <!-- @end 活动中心  add by hanjw 20150917-->     

        <script src="../js/wappv.js"></script>   
        <script src="../js/touch.min.js"></script>
        <%@include file="WeChatToolbar.jsp" %>
</body>
</html>