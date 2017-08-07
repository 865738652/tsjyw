<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
    <title>个人资料</title>
    <%@include file="head.jsp" %>
    <script type="text/javascript">
    	$(function() {
    		var bir = '${user.userbirthday}'.substr(0, 10);
    		$('#userbirthdayDetail').text(bir);
    		initSamlpleUpload("btnUploadFile", onUploadComplete);
    	});
    	
    	function onUploadComplete(data) {
    		if (data == null || data == undefined) {
    			alert("上传错误!");
    			return;
    		}
    		
    		var param = {
    			userPhotoUrl: data.data.attachUrl,
    			${_csrf.parameterName}: "${_csrf.token}"
    		};
    		$.ajax({
	        	url: '../Account/changePhoto',
	        	type:"post",
	        	data:param,
	        	dataType:"json",
	        	success:function(data){
	        		if (data.code != "succ") {
						alert(data.data);
						return;
					}
					alert("修改成功");
	        		$('#UserDialog').modal('hide');
	        		window.location.href='../Account/Personal?r=' + Math.random();
	        	},
			    error: function(XMLHttpRequest, textStatus, errorThrown) {
					alert("网络错误: " + XMLHttpRequest.status + ", " + textStatus);
			    }
	        });
    		
			$("#photo").attr("src", data.data.attachUrl);
    	}
    	
    	function modifyPersonal() {
   			$("#title").html("修改个人资料");
   			$('#userForm')[0].reset();
   			onUserShow(false);
			$.ajax({
				url:"../UserManage/getUser",			
				data:{"userId":${user.userId}},
				dataType:"json",
				success:function(data) {  
			        if (data.code != "succ") {
   						alert(data.data);
   						$('#UserDialog').modal('hide');
   						return;
   					}
  			        fillUser(data.data);
			    },
			    error: function(XMLHttpRequest, textStatus, errorThrown) {
					alert("网络错误: " + XMLHttpRequest.status + ", " + textStatus);
			    }
			});	   						
   		}
   		
   		function onOKButton() {
   			url = "../UserManage/modifyUser";
   			
			onUserSubmit();			
   			$.ajax({
	        	url:url,
	        	type:"post",
	        	data:$('#userForm').serialize(),
	        	dataType:"json",
	        	success:function(data){
	        		if (data.code != "succ") {
						alert(data.data);
						return;
					}
					alert("修改成功");
	        		$('#personalDialog').modal('hide');
	        		window.location.href='../Account/Personal?r=' + Math.random();
	        	},
			    error: function(XMLHttpRequest, textStatus, errorThrown) {
					alert("网络错误: " + XMLHttpRequest.status + ", " + textStatus);
			    }
	        });
   		} 
    </script>
  </head>
  
  <body>
    <%@include file="top.jsp" %>
    <div class="ch-container">
		<div class="row">
            <!-- left menu starts -->
            <%@include file="left.jsp"%>
            <div id="content" class="col-lg-10 col-sm-10">
                <div>
			        <ul class="breadcrumb">
			            <li>
			                <a href="#">用户中心</a>
			            </li>
			            <li>
			                <a href="#">个人资料</a>
			            </li>
			        </ul>
    			</div>
				<div class="box-content">
                    <table>
						<tr>
				   			<td width="15%">照片</td>
	                    	<td width="35%">
	                    		<c:if test="${not empty user.userPhotoUrl}">
	                    			<img id='photo' width='250' src='${user.userPhotoUrl}' />
	                    		</c:if>
	                    		<c:if test="${empty user.userPhotoUrl}">
	                    			<img id='photo' width='250' src='../images/no.jpg' />
	                    		</c:if>
	                    	</td>
	                    	<td width="15%"><input type="button" value="更换照片..." class="form-control" id="btnUploadFile" name="upload" /></td>
	                    	<td width="35%">
	                    		<a class="btn btn-success" data-toggle="modal"  data-target="#personalDialog" onclick="modifyPersonal()" >
                        			<i class="glyphicon glyphicon-plus icon-white"></i>修改个人资料</a>
	                    	</td>
	                    </tr>
	                    <tr>
							<td><label  class="control-label">姓名</label></td>
							<td><label  id="userNameDetail" class="control-label">${user.userName}</label></td>
							<td><label  class="control-label">昵称</label></td>
							<td><label  id="userNicknameDetail" class="control-label">${user.userNickname}</label></td>
						</tr>
						<tr>
							<td><label  class="control-label">帐号</label></td>
							<td><label  id="userAccountDetail" class="control-label">${user.userAccount}</label></td>
							<td><label  class="control-label">编号</label></td>
							<td><label  id="userCodeDetail" class="control-label">${user.userCode}</label></td>
						</tr>
						<tr>
							<td><label class="control-label">身份证号</label></td>
							<td><label id="userIdentityCodeDetail" class="control-label">${user.userIdentityCode}</label></td>
							<td><label  class="control-label">电话</label></td>
							<td><label  id="userPhoneDetail" class="control-label">${user.userPhone}</label></td>
						</tr>
						<tr>
							<td><label  class="control-label">生日</label></td>
							<td><label  id="userbirthdayDetail" class="control-label"></label></td>
							<td><label  class="control-label">性别</label></td>
							<td><label  id="userGenderDetail" class="control-label"><c:if test = "${user.userGender==0}">男</c:if><c:if test = "${user.userGender==1}">女</c:if></label></td>
						</tr>
						<tr>
							<td><label  class="control-label">微信</label></td>
							<td><label  id="userWeixinDetail" class="control-label">${user.userWeixin}</label></td>
							<td><label  class="control-label">QQ</label></td>
							<td><label  id="userQqDetail" class="control-label">${user.userQq}</label></td>
						</tr>
						<tr>
							<td><label  class="control-label">邮箱</label></td>
							<td><label  id="userEmailDetail" class="control-label">${user.userEmail}</label></td>
							<td><label  class="control-label">积分</label></td>
							<td><label  id="userIntegralDetail" class="control-label">${user.userIntegral}</label></td>
						</tr>
					</table>
                </div>		
			</div>
		</div>
	</div>
	
	<!-- 修改和添加用户作管理员 -->
    <div class="modal fade" id="personalDialog" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog"  style="width:800px">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">×</button>
                    <h3><span id='title'>修改个人信息</span></h3>
                </div>
                <div class="modal-body">
                    <form class="form-inline" role="form" id='userForm'>
                    	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/> 
                    	<%@include file="user.jsp" %>
                    </form>                                                                
                </div>
                <!-- body -->
                <hr>
                <div class="modal-footer">
                     <a href="javascript:void(0)" class="btn btn-default " data-dismiss="modal">取消</a>
                     <a href="javascript:void(0)" class="btn btn-primary" id="OKButton" onclick="onOKButton()">确定</a>
                </div>
            </div>
        </div>
    </div> 
    <!-- 修改管理员end -->
</body>
</html>
