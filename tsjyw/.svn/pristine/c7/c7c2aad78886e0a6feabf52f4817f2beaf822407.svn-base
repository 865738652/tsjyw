<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <title>我要提问</title>
    <%@include file="head.jsp" %>
    <script type="text/javascript">
    	var askQuestionId=<c:out value="${askQuestionId}"/>;
    	var attachs = [];
    	var cur = null;
    	var action = "create";
    	
    	$(function(){		
    		$("#TableAttach").bootstrapTable({
				contentType: "application/x-www-form-urlencoded",
		    	queryParamsType:"undefined",//参数格式,发送标准的restful类型的参数请求
		    	striped: true,     //使表格带有条纹  
  				pagination: true, //在表格底部显示分页工具栏 
		    	dataType: "json",
			    pagination: false,
				singleSelect:true,  //复选框只能选择一条记录
				clickToSelect:true,  //点击行即可选中单选/复选框
				columns:[
				 	{
				 		field:'attachName',
				 		title:'文件名',
				 		width:400,
				 		align:'center',
				 		valigh:'middle'
				 	},
				 	{
				 		field:'attachSize',
				 		title:'文件大小',
				 		width:100,
				 		align:'center',
				 		valigh:'middle',
				 	},
				 	{		 	
				 		title:'操作',
				 		field:'attachId',
				 		formatter:function(value,row,index){	                		
	                   		var a = '<a class="btn btn-danger" href="javascript:void(0);" onclick=\"deleteAttach('+index+')\" >';
	                   		var b = '<i class="glyphicon glyphicon-zoom-in icon-white"></i>删除</a> ';
	                        return a+b; 
				 		}
				 	}
				 ]
			});
			
			bindSelect("askquestionStateId", "getAskQuestionState", null, "askQuestionStateId", "askQuestionStateName");
			bindSelect("askquestionTypeId", "getAskQuestionType", null, "askQuestionTypeId", "askQuestionTypeName");
			ue_init("askQuestionContent");
			initSamlpleUpload("btnUploadFile", onUploadComplete);
				
    		if (askQuestionId < 0) {
    			fillAgeLevels();
    			return;
    		}
    			
    		$.ajax({
				url:"getAskQuestion",			
				data:{"askQuestionId":askQuestionId},
				dataType:"json",
				success:function(data) {  
			        if (data.code != "succ") {
   						alert(data.data);
   						window.history.go(-1);
   						return;
   					}
   					
  			        $('#askQuestionId').val(data.data.askQuestionId);
					$('#askquestionTypeId').val(data.data.askquestionTypeId);
					$('#askquestionStateId').val(data.data.askquestionStateId);
					$('#askQuestionTitle').val(data.data.askQuestionTitle);
					//$('#askQuestionContent').val(data.data.askQuestionContent);
					ue_setContent(data.data.askQuestionContent, false);
			        $('#askQuestionRewardIntegral').val(data.data.askQuestionRewardIntegral);
			        $('#askQuestionPublic').attr("checked", data.data.askQuestionPublic);
			        $('#respUserId').val(data.data.respUserId);
					
					attachs = data.data.attachments;
					fillAgeLevels(data.data.ageLevels);
					refreshAttach();			      
			    },
			    error: function(XMLHttpRequest, textStatus, errorThrown) {
					alert("网络错误: " + XMLHttpRequest.status + ", " + textStatus);
			    }			    
			});
    	});
    	
    	function refreshAttach() {
    		$("#TableAttach").bootstrapTable('load', attachs);
    	}
    	
    	function deleteAttach(i) {
    		if (!confirm("确认要删除吗？"))
    			return;
    			
    		attachs.splice(i, 1);
    		refreshAttach();
    	}
    	
    	function onUploadComplete(data) {
    		if (data == null || data == undefined) {
    			alert("上传错误!");
    			return;
    		}
    		cur = {};    		
    		cur.attachId = null;   	    		
    		cur.attachName = data.data.attachName;
    		cur.attachSize = data.data.attachSize;
    		cur.attachUrl = data.data.attachUrl;
    		attachs.push(cur);
    		refreshAttach();
    	}
    	
    	function onOKButton() {
    		ue_onsubmit();
    		var param = $('#questionForm').serialize();
    		
    		for (var i = 0; i < attachs.length; i++) {
    			param += "&attachments[" + i + "].attachName=" + attachs[i].attachName;
    			param += "&attachments[" + i + "].attachSize=" + attachs[i].attachSize;
    			param += "&attachments[" + i + "].attachUrl=" + attachs[i].attachUrl;
    		}
    		
    		param = ageLevelOnSubmit(param);
    		
    		var url = "createAskQuestion";
   			if (askQuestionId >= 0)
   				url = "modifyAskQuestion";
   				
   			$.ajax({
	        	url:url,
	        	type:"post",
	        	data:param,
	        	dataType:"json",
	        	success:function(data){
	        		if (data.code != "succ") {
						alert(data.data);
						return;
					}
					if (askQuestionId < 0)
						alert("提问成功");
					else
						alert("修改成功");
						
	        		window.location.href="../AskQuestionManage/ShowMyQuestion";
	        	},
			    error: function(XMLHttpRequest, textStatus, errorThrown) {
					alert("网络错误: " + XMLHttpRequest.status + ", " + textStatus);
			    }
	        });
    	}
    	
    	function onSelectFamouseTeacher() {
    		selectUser('respUserId', 'respUserName', '../Index/getAllFamousTeacher');
    	}
    	
    	function onSelectVolunteer() {
    		selectUser('respUserId', 'respUserName', '../Index/getAllVolunteer');
    	}
    </script>
  </head>
  
  <body>
    <%@include file="top.jsp" %>
    <div class="ch-container">
		<div class="row">
            <!-- left menu starts -->
            <%@include file="left.jsp"%>
            <form class="form-inline" role="form" id="questionForm">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/> 
            <div id="content" class="col-lg-10 col-sm-10">
                <div>
			        <ul class="breadcrumb">
			            <li>
			                <a href="#">在线问答</a>
			            </li>
			            <li>
			                <a href="#">我要提问</a>
			            </li>
			        </ul>
    			</div>
				<div class="box-content">
                	<input type="hidden" id="askQuestionId" name="askQuestionId" />
                	<input type="hidden" id="respUserId" name="respUserId" />
                    <table>
                    	<tr>
				   			<td><label class="control-label" for="articleTitle">提问标题</label></td>
	                    	<td colspan='3'><input style="width:500px" type="text" class="form-control" id="askQuestionTitle" name="askQuestionTitle"></td>
	                    </tr>
						<tr>
				   			<td width="15%"><label class="control-label" for="askQuestionRewardIntegral">奖励积分</label></td>
	                    	<td width="35%"><input style="width:200px" type="text" class="form-control" id="askQuestionRewardIntegral" name="askQuestionRewardIntegral"  value="0"/></td>
	                    	<td width="15%"><input type="checkbox" id="askQuestionPublic" name="askQuestionPublic" value="true" class="form-control" checked="chekced" />公开提问</td>
	                    	<td width="35%"></td>
	                    </tr>
	                    <tr>
				   			<td><label class="control-label" for=""askquestionTypeId"">问题分类</label></td>
	                    	<td><select id="askquestionTypeId" name="askquestionTypeId"" class="form-control" style="width:200px"></select></td>
	                    	<td><label class="control-label" for=""askQuestionStateId"">问题状态</label></td>
	                    	<td><select id="askquestionStateId" name="askquestionStateId"" class="form-control" style="width:200px"></select></td>
	                    </tr>
	                    <tr>
							<td ><label class="control-label" for="askQuestionContent">年龄阶段</label></td>
	                  		<td colspan='3'><%@include file="agelevel.jsp"%></td>
	                  	</tr>	
	                  	<tr>
							<td>指定教师</td>
	                  		<td colspan="3">
	                  			<c:if test="${empty replier}">
	                  			<input type="hidden" id="respUserId" name="respUserId" value=""/>
	                  			<span id="respUserName"></span>
	                  			</c:if>
	                  			<c:if test="${not empty replier}">
	                  			<input type="hidden" id="respUserId" name="respUserId" value="${replier.userId}"/>
	                  			<span id="respUserName">${replier.userName}</span>
	                  			</c:if>
	                  			<input type="button" class="form-control" id="selectReply" value="指定名师回答..." onclick="onSelectFamouseTeacher()"/>&nbsp;&nbsp;&nbsp;
	                  			<input type="button" class="form-control" id="selectReply" value="指定志愿者回答..." onclick="onSelectVolunteer()"/>
	                  		</td>
	                  	</tr>                    
						<tr>
							<td style="vertical-align:top"><label class="control-label" for="askQuestionContent">提问内容</label></td>
	                  		<td colspan='3'></td>
	                  	</tr>
	                  	<tr>									
			                <td colspan='4'><%@include file="UEditor.jsp" %></td>
			            </tr>
					</table>
                </div>
                <div class="alert alert-info">
                	<input type='button' id='btnUploadFile' class="btn btn-success" value='添加附件'/>
                </div>
                <table class="table table-striped table-bordered table-hover" id="TableAttach"></table>			
			</div>
			<div class="modal-footer">
				<a href="#" class="btn btn-default" data-dismiss="modal">取消</a>
				<a href="#" class="btn btn-primary" data-dismiss="modal" onclick="onOKButton()">保存</a>
			</div>
			</form>
		</div>
	</div>
	<%@include file="selectuser.jsp" %>
</body>
</html>
