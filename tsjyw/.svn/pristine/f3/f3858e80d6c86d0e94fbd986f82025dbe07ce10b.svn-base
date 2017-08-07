<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf8"%>
<script type="text/javascript">
	$(function () {
		initSamlpleUpload("btnUploadFile", onUploadComplete);
	});
	
	function fillUser(user) {
		$('#userId').val(user.userId);
		$('#userName').val(user.userName);
		$('#userIdentityCode').val(user.userIdentityCode);
		$('#userNickname').val(user.userNickname);
		$('#userAccount').val(user.userAccount);
		$('#userPhone').val(user.userPhone);
		$('#userbirthday').val(getSmpFormatDateByLong(user.userbirthday,false));
		$('#userbirthday').datepicker('setDate', new Date(user.userbirthday));  
		if(user.userGender == 0) {
        	$("#genderMale").attr("checked","checked"); 
        }
        else {
        	$("#genderFemale").attr("checked","checked");
        }
		$('#userWeixin').val(user.userWeixin);
		$('#userQq').val(user.userQq);
		$('#userEmail').val(user.userEmail);
		$('#userIntegral').val(user.userIntegral);
		$('#userPhotoUrl').val(user.userPhotoUrl);
		if (user.userPhotoUrl != null)
			$('#userPhoto').attr("src", user.userPhotoUrl);
	}
	
	function onUserShow(showPassword) {
		if (showPassword)
			$("#passwordRow").show();
		else
			$("#passwordRow").hide();
		$('#userbirthday').datepicker({format: 'yyyy-mm-dd', language: 'zh-CN'}); 
	}
	
	function onUserSubmit() {
		var str = $('#userbirthday').val();
		var l = new Date((str).replace(new RegExp("-","gm"),"/")).getTime();
		$('#userbirthday').val(l);
	}
	
	function onUploadComplete(data) {
   		if (data == null || data == undefined) {
   			alert("上传错误!");
   			return;
   		}
   		$("#userPhotoUrl").val(data.data.attachUrl);
   		$('#userPhoto').attr("src", data.data.attachUrl);
   	}
</script>
<div class="form-group has-success has-feedback" id="userPart">
	<table align="center">
		<tr>
			<td width="20%"><input style="margin-left:15px;"  type="button" value="更换照片..." class="form-control" id="btnUploadFile" name="upload" /></td>
			<td colspan="3"><img id="userPhoto" src="../images/no.jpg" width="300px" height="180px"/></td>
		</tr>
		<tr>
			<td width="20%"><label class="control-label" for="userName">姓名</label></td>
			<td width="30%"><input style="margin-left:15px;"  type="text" class="form-control" id="userName" name="userName"></td>
			<td width="20%"><label class="control-label" for="userNickname">昵称</label></td>
			<td width="30%"><input style="margin-left:15px;"  type="text" class="form-control" id="userNickname" name="userNickname"></td>
		</tr>
		<tr>
			<td><label class="control-label" for="userAccount">帐号</label></td>
			<td><input style="margin-left:15px;"  type="text" class="form-control" id="userAccount" name="userAccount"></td>
			<td><label class="control-label" for="userPhone">电话</label></td>
			<td><input style="margin-left:15px;"  type="text" class="form-control" id="userPhone" name="userPhone"></td>
		</tr>
		<tr id="passwordRow">
			<td><label class="control-label" for="userPassword">密码</label></td>
			<td><input style="margin-left:15px;"  type="password" class="form-control" id="userPassword" name="userPassword"></td>
			<td><label class="control-label" for="confirmPassword">确认密码</label></td>
			<td><input style="margin-left:15px;"  type="password" class="form-control" id="confirmPassword" name="confirmPassword"></td>
		</tr>
		<tr>
			<td><label class="control-label" for="userbirthday">生日</label></td>
			<td><input style="margin-left:15px;"  type="text" class="form-control" id="userbirthday" name="userbirthday"></td>
			<td><label class="control-label">性别</label></td>
			<td>
				<label class="radio-inline">
					<input type="radio" name="userGender" id="genderMale" value="0" class="form-control"/> 男&nbsp;&nbsp;&nbsp;
				</label>
				<label class="radio-inline">
					<input type="radio" name="userGender" id="genderFemale" value="1" class="form-control" /> 女
				</label>
			</td>
		</tr>
		<tr>
			<td><label class="control-label" for="userWeixin">微信</label></td>
			<td><input style="margin-left:15px;"  type="text" class="form-control" id="userWeixin" name="userWeixin"></td>
			<td><label class="control-label" for="userQq">QQ</label></td>
			<td><input style="margin-left:15px;"  type="text" class="form-control" id="userQq" name="userQq"></td>
		</tr>
		<tr>
			<td><label class="control-label" for="userEmail">邮箱</label></td>
			<td><input style="margin-left:15px;"  type="text" class="form-control" id="userEmail" name="userEmail"></td>
			<td><label class="control-label" for="userIntegral">积分</label></td>
			<td><input style="margin-left:15px;"  type="text" class="form-control" id="userIntegral" name="userIntegral"></td>
		</tr>
		<tr>
			<td><label class="control-label" for=userIdentityCode>身份证号</label></td>
			<td><input style="margin-left:15px;"  type="text" class="form-control" id="userIdentityCode" name="userIdentityCode"></td>
		</tr>
	</table>                          
    <input type="hidden" name="userId" id="userId"/>
    <input type="hidden" name="userPhotoUrl" id="userPhotoUrl"/>
</div>                       
