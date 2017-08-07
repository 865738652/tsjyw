<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>密码修改成功</title>
  <%@ include file="head.jsp" %>
    <script type="text/javascript">
    
    
    </script>
</head>
<body>
    <div class="ch-container">
    <div class="row"> 
    <div class="row">
        <div class="col-md-12 center login-header">
            <h3>密码修改成功</h3>
        </div>
        <!--/span-->
    </div><!--/row-->

    <div class="row">
        <div class="well col-md-5 center login-box">
            <div class="alert alert-info">
                 　  你的密码已经修改成功<br/>
                 请根据邮箱中的密码在登录界面上登录                                 
            </div>
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/> 
                <fieldset>
                	<!--  
                    <div class="input-group input-group-lg">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-email red"></i></span>
                        <input type="text" id="email" name="email" class="form-control" placeholder="邮箱">
                    </div>
                     -->
                    <p class=" center col-md-6">
                        <button type="submit" class="btn"><a href="../Account/Login/" >返回登陆界面</button>
                    </p>
                 
                
                
                </fieldset>
            </form>
        </div>
        <!--/span-->
    </div><!--/row-->
</div><!--/fluid-row-->
</div>
</body>
</html>

