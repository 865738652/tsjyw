<%@ page language="java" contentType="text/html; charset=utf8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
    <!-- topbar starts -->
<script type="text/javascript">
	function onChangePasswordOKButton() {
	 	url = "../Account/changePassword";
	 				
	 	if ($('#newPassword').val() != $('#confirmPassword').val()) {
	 		alert("密码不匹配");
	 		return;
	 	}
	 		
		$.ajax({
	       	url:url,
	       	type:"post",
	       	data:$('#passwordForm').serialize(),
	       	dataType:"json",
	       	success:function(data){
	       		if (data.code != "succ") {
					alert(data.data);
					return;
				}
				alert("修改成功");
	       		$('#passwordDialog').modal('hide');
	       	},
		    error: function(XMLHttpRequest, textStatus, errorThrown) {
				alert("网络错误: " + XMLHttpRequest.status + ", " + textStatus);
		    }
		});
	} 
</script>
<div class="navbar navbar-default" role="navigation">
    <div class="navbar-inner">
        <button type="button" class="navbar-toggle pull-left animated flip">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="../Index/"> <img alt="Charisma Logo" src="<c:url value="/img/logo20.png"/>" class="hidden-xs"/>
            <span>回到首页</span></a>

        <!-- user dropdown starts -->
        <div class="btn-group pull-right">
            <button class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                <i class="glyphicon glyphicon-user"></i><span class="hidden-sm hidden-xs"><sec:authentication property="name"/></span>
                <span class="caret"></span>
            </button>
			<c:url var="logoutUrl" value="/j_spring_security_logout" />
			<form action="${logoutUrl}" id="logout" method="post">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			</form>
			
            <ul class="dropdown-menu">
				<li><a href="<c:url value="/Account/Personal"/>"">个人资料</a></li>
				<li class="divider"></li>
                <li><a data-toggle="modal"  data-target="#passwordDialog" onclick="onChangePassword()"/>修改密码</a></li>
                <li class="divider"></li>
                <li><a href="#" onclick="document.getElementById('logout').submit();">退出登录</a></li>                
            </ul>
        </div>
        <!-- user dropdown ends -->
        
        <!-- Message dropdown starts -->
        <div class="btn-group pull-right theme-container animated tada">
            <button class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                <i class="glyphicon glyphicon-envelope"></i><span
                    class="hidden-sm hidden-xs">新消息</span>
                <span class="caret"></span>
            </button>
            <ul class="dropdown-menu">
                <li><a href="../RecvNotice/">我的通知</a></li>
                <!-- <span class="notification red">34</span> -->
                <li class="divider"></li>
                <li><a href="../RecvAnnouncement/">站内公告</a></li>
            </ul>
        </div>
        <!-- Message dropdown ends -->

        <!-- theme selector starts --><!-- 
        <div class="btn-group pull-right theme-container animated tada">
            <button class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                <i class="glyphicon glyphicon-tint"></i><span
                    class="hidden-sm hidden-xs"> 更换主题/皮肤</span>
                <span class="caret"></span>
            </button>
            <ul class="dropdown-menu" id="themes">
                <li><a data-value="classic" href="#"><i class="whitespace"></i> Classic</a></li>
                <li><a data-value="cerulean" href="#"><i class="whitespace"></i> Cerulean</a></li>
                <li><a data-value="cyborg" href="#"><i class="whitespace"></i> Cyborg</a></li>
                <li><a data-value="simplex" href="#"><i class="whitespace"></i> Simplex</a></li>
                <li><a data-value="darkly" href="#"><i class="whitespace"></i> Darkly</a></li>
                <li><a data-value="lumen" href="#"><i class="whitespace"></i> Lumen</a></li>
                <li><a data-value="slate" href="#"><i class="whitespace"></i> Slate</a></li>
                <li><a data-value="spacelab" href="#"><i class="whitespace"></i> Spacelab</a></li>
                <li><a data-value="united" href="#"><i class="whitespace"></i> United</a></li>
            </ul>
        </div> -->
        <!-- theme selector ends -->
        
        
		
        <ul class="collapse navbar-collapse nav navbar-nav top-menu">
            <li><a href="#"><i class="glyphicon glyphicon-globe"></i>返回管理中心</a></li>
            <!--
            <li class="dropdown">
                <a href="#" data-toggle="dropdown"><i class="glyphicon glyphicon-star"></i> 个人管理 <span
                        class="caret"></span></a>
                <ul class="dropdown-menu" role="menu">
                    <li><a href="#">个人信息</a></li>
                    <li class="divider"></li>
                    <li><a href="#">我的积分</a></li>
                    <li class="divider"></li>
                    <li><a href="#">账户余额</a></li>
                    <li><a href="#">账户充值</a></li>
                </ul>
            </li>
            -->
			<!--
            <li>
                <form class="navbar-search pull-left">
                    <input placeholder="Search" class="search-query form-control col-md-10" name="query"
                           type="text">
                </form>
            </li>
			-->
        </ul>

    </div>
</div>

<!-- 修改密码 -->
<div class="modal fade" id="passwordDialog" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog"  style="width:800px">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">×</button>
                <h3><span id='title'>修改登录密码</span></h3>
            </div>
            <div class="modal-body">
                <form class="form-inline" role="form" id='passwordForm'>
                	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                	<table width="90%">
                		<tr>
							<td width="20%"><label class="control-label" for="userPassword">原密码</label></td>
							<td><input type="password" class="form-control" id="oldPassword" name="oldPassword"></td>
						</tr>
						<tr>
							<td width="20%"><label class="control-label" for="userPassword">密码</label></td>
							<td><input type="password" class="form-control" id="newPassword" name="newPassword"></td>
						</tr>
						<tr>
							<td><label class="control-label" for="confirmPassword">确认密码</label></td>
							<td><input type="password" class="form-control" id="confirmPassword" name="confirmPassword"></td>
						</tr>
					</table>
                </form>                                                                
            </div>
            <!-- body -->
            <hr>
            <div class="modal-footer">
                 <a href="javascript:void(0)" class="btn btn-default " data-dismiss="modal">取消</a>
                 <a href="javascript:void(0)" class="btn btn-primary" id="OKButton" onclick="onChangePasswordOKButton()">确定</a>
            </div>
        </div>
    </div>
</div> 
<!-- 修改密码end -->