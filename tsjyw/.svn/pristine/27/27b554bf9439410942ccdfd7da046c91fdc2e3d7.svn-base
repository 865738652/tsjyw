<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <title>阅读提问</title>
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
			ue_init("respQuestionContent");
			initSamlpleUpload("btnUploadFile", onUploadComplete);
				
    		if (askQuestionId < 0)
    			return;
    			
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
					$('#askquestionTypeName').text(data.data.askQuestionTypeName);
					$('#askquestionStateName').html(data.data.askQuestionStateName);
					$('#askQuestionTitle').html(data.data.askQuestionTitle);
					$('#askName').html(data.data.askName);
					$('#askQuestionContent').html(data.data.askQuestionContent);
			        $('#askQuestionRewardIntegral').html(data.data.askQuestionRewardIntegral);
			        $('#askQuestionPublic').html(data.data.askQuestionPublic?"公开":"不公开");
			        $('#answerName').html(data.data.respUserId == null ? "无" : data.data.answerName);	
					$('#askQuestionTime').html(getFormatDate(new Date(data.data.askQuestionTime), "yyyy-MM-dd hh:mm:ss"));	
					
					if (data.data.attachments != null && data.data.attachments.length > 0) {
		        		var att="";
		        		for (var j = 0; j < data.data.attachments.length; j++) {
		        			att += '<a href="' + data.data.attachments[j].attachUrl + '">[' + data.data.attachments[j].attachName + ']</a> ';
		        		}
		        		$("#questionAttachment").html(att);
		        	}
			        
			        var html = '<p>共有' + (data.data.responses == null?0:data.data.responses.length) + '个回答</p>';
			        if (data.data.responses == null)
			        	return;
			        	
			        for (var i = 0; i < data.data.responses.length; i++) {
			        	html += '<p>回答' + (i + 1) + ': ' + data.data.responses[i].userName + ' 于 ' + getFormatDate(new Date(data.data.responses[i].respQuestionTime), "yyyy-MM-dd hh:mm:ss") + '</p>';
			        	html += '<div>' + data.data.responses[i].respQuestionContent + '</div>';
			        	if (data.data.responses[i].attachments != null && data.data.responses[i].attachments.length > 0) {
			        		html += '<div>附件: ';
			        		for (var j = 0; j < data.data.responses[i].attachments.length; j++) {
			        			html += '<a href="' + data.data.responses[i].attachments[j].attachUrl + '">[' + data.data.responses[i].attachments[j].attachName + ']</a> ';
			        		}
			        		html += '</div>';
			        	}
			        	html += "<hr/>";			        		
			        }	      
			        $('#responseList').html(html);
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
    	
    	function uploadFile() {
    		ajaxFileUpload("file", onUploadComplete);
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
    		var param = $('#responseForm').serialize();
    		
    		for (var i = 0; i < attachs.length; i++) {
    			param += "&attachments[" + i + "].attachName=" + attachs[i].attachName;
    			param += "&attachments[" + i + "].attachSize=" + attachs[i].attachSize;
    			param += "&attachments[" + i + "].attachUrl=" + attachs[i].attachUrl;
    		}
    		
    		alert(param);
    		
    		var url = "createRespQuestion";
   				
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
					alert("回答成功");
						
	        		window.history.go(-1);
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
            <form class="form-inline" role="form" id="responseForm">
            <c:if test="${not empty _csrf}">
            	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/> 
            </c:if>
            <div id="content" class="col-lg-10 col-sm-10">
                <div>
			        <ul class="breadcrumb">
			            <li>
			                <a href="#">在线问答</a>
			            </li>
			            <li>
			                <a href="#">阅读问题</a>
			            </li>
			        </ul>
    			</div>
				<div class="box-content">
                    <table>
                    	<tr>
	                    	<td colspan='6' sytle="text-align:center">问题: <span id="askQuestionTitle"></span></td>
	                    </tr>
						<tr>
				   			<td><label class="control-label">分类: <span id="askquestionTypeName"></span></label></td>
	                    	<td><label class="control-label">状态: <span id="askquestionStateName"></span></label></td>
	                    	<td><label class="control-label">提问: <span id="askName"></span></label></td>
							<td><label class="control-label">时间: <span id="askQuestionTime"></span></label></td>
	                    	<td><label class="control-label">指定回答: <span id="answerName"></span></label></td>
	                    	<td><label class="control-label">奖励积分: <span id="askQuestionRewardIntegral"></span></label></td>
	                    	<td><label class="control-label"><span id="askQuestionPublic"></span></label></td>
	                    </tr>
						<tr>
	                  		<td colspan='6'><div id="askQuestionContent" style="width:500px;margin-left:32px" rows="12"  list="2" placeholder="提问内容" required></></td>
	                  	</tr>
					</table>
					<div id="questionAttachment"></div>
					<hr/>
					<div id="responseList"></div>
					<div id="newResponse">
					<input type="hidden" id="askQuestionId" name="askQuestionId" value='<c:out value="${askQuestionId}"/>' />
					<table>						
						<tr>
							<td style="vertical-align:top"><label class="control-label" for="askQuestionContent">我要回答</label></td>
	                  		<td><input type="checkbox" id="respQuestionPublic" name="respQuestionPublic" value="true" class="form-control" />公开回答内容</td>
	                  	</tr>
	                  	<tr>
			   				<td colspan="2"><%@include file="UEditor.jsp" %></td>	                    	
	                    </tr>
					</table>
					</div>
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
	
	<!-- 添加附件-->
	<div class="modal fade" id="attachDialog" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog" style="width:800px">
            <div class="modal-content">
              <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" >×</button>  
                    <h3><span id='title'>上传附件</span></h3>
                </div>
                <div class="modal-body">
                   <form class="form-inline" role="form" id='attachForm'>
                    	<div class="form-group has-success has-feedback" id="LookSchool2">
                        <table >
                        	<tr>
								<td width="30%"></td>
								<td width="70%">
									<input style="margin-left:15px;"  type="button" value="上传文件..." class="form-control" id="btnUploadFile" name="upload" />
								</td>
							</tr>
						</table>
						</div>
                    </form >
             	</div>
            </div>
        </div>
	</div>
</body>
</html>
