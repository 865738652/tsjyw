<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf8"%>
<script type="text/javascript">
	function fillUserDetail(user) {
		$('#userNameDetail').text(user.userName);
		$('#userIdentityCodeDetail').text(user.userIdentityCode);
		$('#userNicknameDetail').text(user.userNickname);
		$('#userAccountDetail').text(user.userAccount);
		$('#userPhoneDetail').text(user.userPhone);
		$('#userbirthdayDetail').text(getSmpFormatDateByLong(user.userbirthday,false));
		$('#userGenderDetail').text(user.userGender == 0?"男":"女");
		$('#userWeixinDetail').text(user.userWeixin);
		$('#userQqDetail').text(user.userQq);
		$('#userEmailDetail').text(user.userEmail);
		$('#userIntegralDetail').text(user.userIntegral);
		$('#userCodeDetail').text(user.userCode);
		if (user.userPhotoUrl != null)
			$('#userPhotoDetail').attr("src", user.userPhotoUrl);
	}
</script>
<div class="form-group has-success has-feedback" id="showTeacher">
	<table width="90%">
		<tr>
			<td width="20%"><label  class="control-label">照片</label></td>
			<td colspan="3"><img id="userPhotoDetail" src="../images/no.jpg" width="300px" height="180px"/></td>
		</tr>
		<tr>
			<td width="20%"><label  class="control-label">姓名</label></td>
			<td width="30%"><label  id="userNameDetail" class="control-label">姓名</label></td>
			<td width="20%"><label  class="control-label">昵称</label></td>
			<td width="30%"><label  id="userNicknameDetail" class="control-label">昵称</label></td>
		</tr>
		<tr>
			<td><label  class="control-label">帐号</label></td>
			<td><label  id="userAccountDetail" class="control-label">帐号</label></td>
			<td><label  class="control-label">编号</label></td>
			<td><label  id="userCodeDetail" class="control-label">编号</label></td>
		</tr>
		<tr>
			<td><label class="control-label">身份证号</label></td>
			<td><label id="userIdentityCodeDetail" class="control-label">身份证号</label></td>
			<td><label  class="control-label">电话</label></td>
			<td><label  id="userPhoneDetail" class="control-label">电话</label></td>
		</tr>
		<tr>
			<td><label  class="control-label">生日</label></td>
			<td><label  id="userbirthdayDetail" class="control-label">生日</label></td>
			<td><label  class="control-label">性别</label></td>
			<td><label  id="userGenderDetail" class="control-label">性别</label></td>
		</tr>
		<tr>
			<td><label  class="control-label">微信</label></td>
			<td><label  id="userWeixinDetail" class="control-label">微信</label></td>
			<td><label  class="control-label">QQ</label></td>
			<td><label  id="userQqDetail" class="control-label">QQ</label></td>
		</tr>
		<tr>
			<td><label  class="control-label">邮箱</label></td>
			<td><label  id="userEmailDetail" class="control-label">邮箱</label></td>
			<td><label  class="control-label">积分</label></td>
			<td><label  id="userIntegralDetail" class="control-label">积分</label></td>
		</tr>
	</table>
</div>                       
 