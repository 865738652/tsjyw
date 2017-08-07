<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <%@include file="head.jsp" %> 
    <title>重置登录密码</title>     
<script type="text/javascript">
//设置提示语
function setWarning(id,value) {
    $(document).ready(function(){
        $(id).html(value);
    })
}
</script>
</head>
<body>
   <div class="ch-container">
    <div class="row"> 
    <div class="row">
        <div class="col-md-12 center login-header">

    <div class="alermpassword"><strong class="f18">通过发到您认证邮箱内的密码重置邮件可重新设置登录密码</strong><br />
      <br />
      
      <div class="changepassword center"  style="width:430px; ">
    <form action="SendEmail" method="POST">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/> 
    <div class="inputtask">
    	<div class="form-group">
    	<label>&nbsp;&nbsp;账号：&nbsp;&nbsp;&nbsp;&nbsp;</label>
    	<input type="text" name="account"  class="form-control" placeholder="账号"/>
    	</div>
    	<br/>
    	<div class="form-group">
        <label>&nbsp;&nbsp;邮箱地址：</label>
        <input type="text" name="email" class="form-control" placeholder="邮箱"/>
        </div>
    </div>
    <br>
    <div ><input type="submit" class="btn btn-primary" name="submit"  value="立即发送邮件重置密码"/>
    </div>
    </form>
    <strong class="orange" letf>提示：</strong><br />
                        请在上面输入您的用户的账号和电子邮箱地址 ,<p>点击&quot;立即发送邮件重置密码&quot;按钮后,</p>系统会将随机的密码邮件发送到该邮箱。<br />
                        在收到密码邮件后，请根据邮件的新密码在登录界面中登录。   </div>
    </div>
  </div>
  </div>
  </div>
  </div>
  
  </body>
</html>
