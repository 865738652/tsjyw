<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>

    <title>登录</title>
    <%@ include file="head.jsp" %>
</head>

<body>
<div class="ch-container">
    <div class="row">
        
    <div class="row">
        <div class="col-md-12 center login-header">
            <h3>欢迎登录唐山家庭教育网</h3>
        </div>
        <!--/span-->
    </div><!--/row-->

    <div class="row">
        <div class="well col-md-5 center login-box">
            <div class="alert alert-info">
            	<c:choose>
            		<c:when test="${not empty error}">
            			<font style="color:red;"><c:out value="${error}"/></font>
            		</c:when>
            		<c:otherwise>
            			请输入用户名和密码
            		</c:otherwise>
            	</c:choose>
            </div>
            <form action="../Account/Authenticate" method="post">
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/> 
                <fieldset>
                    <div class="input-group input-group-lg">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-user red"></i></span>
                        <input type="text" id="userName" name="userName" class="form-control" placeholder="用户名">
                    </div>
                    <div class="clearfix"></div><br>

                    <div class="input-group input-group-lg">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-lock red"></i></span>
                        <input id="password" name="password" type="password" class="form-control" placeholder="密码">
                        
                        
                    </div>
                    <div class="clearfix"></div>

                    <div class="input-prepend">
                        <label class="remember" for="remember"><input type="checkbox" id="remember"> 记住我已登录</label>
                    </div>
                    <div class="clearfix"></div>

                    <p class=" center col-md-6">
                        <button type="submit" class="btn btn-primary">登录</button>
                    </p>
                    <p class=" center col-md-6">
                    	<button type="button" class="btn "><a href="../ResetPassword/" >忘记密码？</a></button>
                    	<button type="button" class="btn "><a href="Register" >注册用户</a></button>
                    </p>
                    
                    
                    
                </fieldset>
            </form>
        </div>
        <!--/span-->
    </div><!--/row-->
</div><!--/fluid-row-->

</div><!--/.fluid-container-->


</body>
</html>
