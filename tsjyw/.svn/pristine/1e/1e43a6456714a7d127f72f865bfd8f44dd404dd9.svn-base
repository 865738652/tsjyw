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
    <title>添加文章</title>
    <%@include file="head.jsp" %>
    <script type="text/javascript">
    	var articleid=<c:out value="${articleid}"/>;
    	var schoolid=<c:out value="${schoolid}"/>;
		var blog=<c:out value="${blog}"/>;
    	var pictures = [];
    	var videos = [];
    	var cur = null;
    	var fileType = "picture";
    	var action = "create";
    	
    	$(function(){	    		                        
    		$("#TablePicture").bootstrapTable({
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
				 		field:'attachUrl',
				 		title:'图片',
				 		width:'200',
				 		align:'center',
				 		valign:'maddle',
				 		sortable:true,
				 		formatter:function(value,row,index){
				 			return "<img style='width:180px;' src='" + value +"'/>";
				 		}
				 	},
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
				 		field:'attachSerial',
				 		title:'序号',
				 		width:100,
				 		align:'center',
				 		valigh:'middle'
				 	},
				 	{		 	
				 		title:'操作',
				 		field:'attachId',
				 		formatter:function(value,row,index){	                        
	                   		var a = '<a class="btn btn-info" data-toggle="modal" data-target="#attachDialog" href="javascript:void(0);" onclick=\"modifyAttach(\'picture\','+index+')\">'; 
	                   		var b = '<i class="glyphicon glyphicon-zoom-in icon-white"></i>修改</a> ';	                 		
	                   		var c = '<a class="btn btn-danger" href="javascript:void(0);" onclick=\"deleteAttach(\'picture\','+index+')\" >';
	                   		var d = '<i class="glyphicon glyphicon-zoom-in icon-white"></i>删除</a> ';
	                        return a+b+c+d; 
				 		}
				 	}
				 ],
				 onLoadSuccess: function (data) {
				 }
			});
			
			$("#TableVideo").bootstrapTable({
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
				 		field:'attachSerial',
				 		title:'序号',
				 		width:100,
				 		align:'center',
				 		valigh:'middle'
				 	},
				 	{		 	
				 		title:'操作',
				 		field:'attachId',
				 		formatter:function(value,row,index){	                        
	                   		var a = '<a class="btn btn-info" data-toggle="modal" data-target="#attachDialog" href="javascript:void(0);" onclick=\"modifyAttach(\'video\','+index+')\">'; 
	                   		var b = '<i class="glyphicon glyphicon-zoom-in icon-white"></i>修改</a> ';	                 		
	                   		var c = '<a class="btn btn-danger" href="javascript:void(0);" onclick=\"deleteAttach(\'video\','+index+')\" >';
	                   		var d = '<i class="glyphicon glyphicon-zoom-in icon-white"></i>删除</a> ';
	                        return a+b+c+d; 
				 		}
				 	}
				 ],
				 onLoadSuccess: function (data) {
				 }
			});
			
			bindSelect("articleStateId", "getArticleState", null, "articleStateId", "articleStateName");
			if (blog == 1) {
				bindSelect("moduleId", "../ModuleManage/getChildModule", {parentId: 72}, "moduleId", "moduleName");
			}
			else {
				if (schoolid < 0)			
					bindSelect("moduleId", "../ModuleManage/getAllModule", null, "moduleId", "moduleName");
				else
					bindSelect("moduleId", "../ModuleManage/getAllModuleBySchool", {schoolId: schoolid}, "moduleId", "moduleName");
			}
			ue_init("ArticleContent");

			$('#articleTime').datetimepicker({
				language:  'zh-CN',
				todayBtn:  1,
				autoclose: 1,
				todayHighlight: 1,
				startView: 2,
				forceParse: 0,
				format: "yyyy-mm-dd hh:ii",
				pickerPosition: "bottom-left"
			});
			$('#articleTime').val(getFormatDate(new Date(), "yyyy-MM-dd hh:mm"));			

			initSamlpleUpload("btnUploadFile", onUploadComplete);
			
    		if (articleid < 0)
    			return;
    			
    		$.ajax({
				url:"getArticle",			
				data:{"articleId":articleid},
				dataType:"json",
				success:function(data) {  
			        if (data.code != "succ") {
   						alert(data.data);
   						window.history.go(-1);
   						return;
   					}
   					
  			        $('#articleId').val(data.data.articleId);
					$('#moduleId').val(data.data.moduleId);
					$('#articleNumber').val(data.data.articleNumber);
					$('#articleStateId').val(data.data.articleStateId);
					$('#articleRecommend').attr("checked", data.data.articleRecommend);
					$('#schoolContactInformation').val(data.data.schoolContactInformation);
			        $('#articleTitle').val(data.data.articleTitle);
			        $('#articleKeyword').val(data.data.articleKeyword);
			        //$('#articleTime').val(data.data.articleTime);
					$('#articleTime').val(getFormatDate(new Date(data.data.articleTime), "yyyy-MM-dd hh:mm"));
					//$('#articleContent').val(data.data.articleContent);					
					ue_setContent(data.data.articleContent, false);
					$('#articleAbstract').val(data.data.articleAbstract);
					
					refreshGallery();			      
			    },
			    error: function(XMLHttpRequest, textStatus, errorThrown) {
					alert("网络错误: " + XMLHttpRequest.status + ", " + textStatus);
			    }
			    
			});
			
			$.ajax({
				url:"getImageByArticle",			
				data:{"articleId":articleid},
				dataType:"json",
				success:function(data) {  
			        if (data.code != "succ") {
   						alert("获取图片列表失败：" + data.data);
   						window.history.go(-1);
   						return;
   					}
   					
  			        pictures = data.rows;
					refreshGallery();			      
			    },
			    error: function(XMLHttpRequest, textStatus, errorThrown) {
					alert("网络错误: " + XMLHttpRequest.status + ", " + textStatus);
			    }
			    
			});
			
			$.ajax({
				url:"getVideoByArticle",			
				data:{"articleId":articleid},
				dataType:"json",
				success:function(data) {  
			        if (data.code != "succ") {
   						alert("获取视频列表失败：" + data.data);
   						window.history.go(-1);
   						return;
   					}
   					
  			        videos = data.rows;
					refreshGallery();			      
			    },
			    error: function(XMLHttpRequest, textStatus, errorThrown) {
					alert("网络错误: " + XMLHttpRequest.status + ", " + textStatus);
			    }
			    
			});			
    	});
    	
    	function addAttach(t) {
    		action = "create";
    		fileType = t;
    		cur = {};
    		cur.attachId = null;
    		if (fileType == "picture") {
    			$("#preview").html("<img  style='width:400px' src='' />");
    			$("#imageShow").show();
    			$("#imageSelect").show();
    		}
    		else {
    			$("#preview").html("<input type='text' style='width:500px' class='form-control' id='videoPath' value='' />");
    			$("#imageShow").hide();
    			$("#imageSelect").hide();
    		}
    		
    		$("#attachForm")[0].reset();
    		$("#isThumbnail").attr("checked", false);
    		$("#isShowYes").attr("checked","checked");
    		$("#isShowNo").attr("checked",false);
    		$('#attachDialog').modal('show');
    	}
    	
    	function modifyAttach(t, i) {
    		action = "modify";
    		fileType = t;
    		cur = t == "picture"? pictures[i] : videos[i];
    		
    		if (fileType == "picture") {
    			$("#preview").html("<img  style='width:400px' src='" + cur.attachUrl + "' />");
    			$("#imageShow").show();
    			$("#imageSelect").show();
    		}
    		else {
    			$("#preview").html("<input type='text' style='width:500px' class='form-control' id='videoPath' value='" + cur.attachUrl + "' />");
    			$("#imageShow").hide();
    			$("#imageSelect").hide();
    		}
    		    			
    		$("#attachForm")[0].reset();
    		$("#attachSerial").val(cur.attachSerial);
    		$("#attachIntro").val(cur.attachIntro);
    		$("#isThumbnail").attr("checked", cur.isThumbnail);
    		if (cur.isShow) {
    			$("#isShowYes").attr("checked","checked");
    			$("#isShowNo").attr("checked",false);
    		}
    		else{
    			$("#isShowYes").attr("checked",false);
    			$("#isShowNo").attr("checked","checked");
    		}
    	}
    	
    	function deleteAttach(t, i) {
    		if (!confirm("确认要删除吗？"))
    			return;
    			
    		if (t == "picture")
    			pictures.splice(i, 1);
    		else
    			videos.splice(i, 1);
    		refreshGallery();
    	}
    	
    	function onUploadComplete(data) {
    		if (data == null || data == undefined) {
    			alert("上传错误!");
    			return;
    		}
    		cur.attachName = data.data.attachName;
    		cur.attachSize = data.data.attachSize;
    		cur.attachUrl = data.data.attachUrl;
    		
			if (fileType == "picture") 
    			$("#preview").html("<img  style='width:400px' src='" + cur.attachUrl + "' />");
    	}
    	
    	function onAttachOKButton() {
    		cur.attachSerial = $("#attachSerial").val();
    		cur.attachIntro = $("#attachIntro").val();
    		cur.isThumbnail = $("#isThumbnail").is(':checked');
    		cur.isShow = ($("input[name='isShow']:checked").val() == "true");
    		
    		if (fileType == 'video') {
    			cur.attachName = $("#videoPath").val();
	    		cur.attachSize = 0;
	    		cur.attachUrl = $("#videoPath").val();
    		}
    		
    		if (cur.attachUrl == null || cur.attachUrl == "") {
    			alert("文件路径不能空");
    			return;
    		}
    		
    		if (action == "create") {
    			if (fileType == "picture")
    				pictures.push(cur);
    			else if (fileType == "video")
    				videos.push(cur);
    		}
    		$('#attachDialog').modal('hide');
    		refreshGallery();
    	}
    	
    	function refreshGallery() {
    		$("#TablePicture").bootstrapTable('load', pictures);
    		$("#TableVideo").bootstrapTable('load', videos);
    	}
    	
    	function onOKButton() {
    		ue_onsubmit();
			var str = $('#articleTime').val();
			var l = new Date((str).replace(new RegExp("-","gm"),"/")).getTime();
			$('#articleTime').val(l);

    		var param = $('#articleForm').serialize();
    		for (var i = 0; i < pictures.length; i++) {
    			param += "&pictures[" + i + "].attachId=" + (pictures[i].attachId == null ? "" : pictures[i].attachId);
    			param += "&pictures[" + i + "].attachName=" + pictures[i].attachName;
    			param += "&pictures[" + i + "].attachIntro=" + pictures[i].attachIntro;
    			param += "&pictures[" + i + "].attachSize=" + pictures[i].attachSize;
    			param += "&pictures[" + i + "].attachUrl=" + pictures[i].attachUrl;
    			param += "&pictures[" + i + "].attachSerial=" + pictures[i].attachSerial;
    			param += "&pictures[" + i + "].isThumbnail=" + pictures[i].isThumbnail;
    			param += "&pictures[" + i + "].isShow=" + pictures[i].isShow;
    		}
    		
    		for (var i = 0; i < videos.length; i++) {
    			param += "&videos[" + i + "].attachId=" + (videos[i].attachId == null ? "" : videos[i].attachId);
    			param += "&videos[" + i + "].attachName=" + videos[i].attachName;
    			param += "&videos[" + i + "].attachIntro=" + videos[i].attachIntro;
    			param += "&videos[" + i + "].attachSize=" + videos[i].attachSize;
    			param += "&videos[" + i + "].attachUrl=" + videos[i].attachUrl;
    			param += "&videos[" + i + "].attachSerial=" + videos[i].attachSerial;
    		}
    		    		
    		var url = "createArticle";
   			if (articleid >= 0)
   				url = "modifyArticle";
   				
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
					if (articleid < 0)
						alert("创建成功");
					else
						alert("修改成功");
						
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
            <form class="form-inline" role="form" id="articleForm">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/> 
            <div id="content" class="col-lg-10 col-sm-10">
                <div>
			        <ul class="breadcrumb">
			            <li>
			                <a href="#">内容管理</a>
			            </li>
			            <li>
			                <a href="#">文章管理</a>
			            </li>
			            <li>
			            	<a href="#">添加文章</a>
			            </li>
			        </ul>
    			</div>
				<div class="box-content">
					<ul class="nav nav-tabs" id="myTab">
	                    <li class="active"><a href="#info">基本内容</a></li>
	                    <li><a href="#pictures">图片展示</a></li>
	                    <li><a href="#videos">在线视频</a></li>
                	</ul>
                	<div id="myTabContent" class="tab-content">
	                    <div class="tab-pane active" id="info">
	                    	<input type="hidden" id="articleId" name="articleId" />
	                        <table>
								<tr>
						   			<td width="15%"><label class="control-label" for="moduleId">所属栏目</label></td>
			                    	<td width="35%"><select id="moduleId" name="moduleId" class="form-control" style="width:200px"></select></td>
			                    	<td width="15%"><label class="control-label" for="articleNumber">文章编号</label></td>
			                    	<td width="35%"><input type="text" class="form-control" id="articleNumber" name="articleNumber" style="width:200px"></td>
			                    </tr>
			                    <tr>
						   			<td><label class="control-label" for=""articleTime"">发布时间</label></td>
			                    	<td><input type="text" class="form-control" id="articleTime" name="articleTime" style="width:200px"></td>
			                    	<td><input type="checkbox" id="articleRecommend" name="articleRecommend" value="true" class="form-control" />推荐文章</td>
			                    	<td></td>
			                    </tr>
			                    <tr>
						   			<td width="15%"><label class="control-label" for=""articleStateId"">文章状态</label></td>
			                    	<td width="35%"><select id="articleStateId" name="articleStateId"" class="form-control" style="width:200px"></select></td>
			                    	<td width="15%"><label class="control-label" for="articleKeyword">文章关键字</label></td>
			                    	<td width="35%"><input type="text" class="form-control" id="articleKeyword" name="articleKeyword" style="width:200px"></td>
			                    	<td></td>
			                    	<td></td>
			                    </tr>
								<tr>
						   			<td><label class="control-label" for="articleTitle">文章标题</label></td>
			                    	<td colspan='3'><input style="width:500px" type="text" class="form-control" id="articleTitle" name="articleTitle"></td>
			                    </tr>
								<tr>
									<td style="vertical-align:top"><label class="control-label" for="articleAbstract">内容摘要</label></td>
			                  		<td colspan='3'><textarea class="form-control" id="articleAbstract" name="articleAbstract" style="width:500px" rows="4"  list="2" placeholder="内容摘要" required></textarea></td>
			                  	</tr>
			                  	<tr>
									<td style="vertical-align:top"><label class="control-label" for="inputSuccess1">文章内容</label></td>
			                  		<td colspan='3'></td>
			                  	</tr>		
			                  	<tr>									
			                  		<td colspan='4'><%@include file="UEditor.jsp" %></td>
			                  	</tr>			                  	
							</table>
	                    </div>
	                    <div class="tab-pane" id="pictures">
	                        <div class="alert alert-info">
			                	<a class="btn btn-success" data-toggle="modal"  data-target="#attachlDialog" onclick="addAttach('picture')">
			                        <i class="glyphicon glyphicon-plus icon-white"></i> 添加图片 </a>
			                </div>
			                <table class="table table-striped table-bordered table-hover" id="TablePicture"></table>
	                    </div>
	                    <div class="tab-pane" id="videos">
	                        <div class="alert alert-info">
			                	<a class="btn btn-success" data-toggle="modal"  data-target="#attachlDialog" onclick="addAttach('video')">
			                        <i class="glyphicon glyphicon-plus icon-white"></i> 添加视频 </a>
			                </div>
			                <table class="table table-striped table-bordered table-hover" id="TableVideo"></table>
	                    </div>
                    </div>
                </div>			
			</div>
			<div class="modal-footer">
				<a href="#" class="btn btn-default" data-dismiss="modal">取消</a>
				<a href="#" class="btn btn-primary" data-dismiss="modal" onclick="onOKButton()">保存</a>
			</div>
			</form>
		</div>
	</div>
	
	<!-- 添加和修改图片视频-->
	<div class="modal fade" id="attachDialog" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
         aria-hidden="true">

        <div class="modal-dialog" style="width:800px">
            <div class="modal-content">
              <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" >×</button>  
                    <h3><span id='title'>多媒体信息</span></h3>
                </div>
                <div class="modal-body">
                   <form class="form-inline" role="form" id='attachForm'>
                   		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/> 
                    	<div class="form-group has-success has-feedback" id="LookSchool2">
                        <table >
                        	<tr id="imageSelect">
								<td width="30%"></td>
								<td width="70%">
									<input style="margin-left:15px;"  type="button" value="选择文件..." class="form-control" id="btnUploadFile" name="upload" />
								</td>
							</tr>
							<tr>
								<td><label class="control-label" for="attachSerial">文件</label></td>
								<td id="preview"><img  style="width:400px" src="" /></td>
							</tr>
							<tr id = "imageShow">
								<td><input type="checkbox" class="form-control" id="isThumbnail" name="isThumbnail" value="false" /><label class="control-label" for="isThumbnail">标题图片</label> </td>
								<td id="preview">
									<label class="radio-inline">
									<input type="radio" name="isShow" id="isShowYes" value="true" checked class="form-control"/> 在正文中显示&nbsp;&nbsp;&nbsp;
									</label>
									<label class="radio-inline">
									<input type="radio" name="isShow" id="isShowNo" value="false" class="form-control" /> 不在正文中显示
									</label>
								</td>
							</tr>
							<tr>
								<td><label class="control-label" for="attachSerial">序号</label></td>
								<td><input type="text" style="width:500px" class="form-control" id="attachSerial" name="attachSerial"></td>
							</tr>
							<tr>
								<td><label class="control-label" for="attachIntro">说明</label></td>
								<td><textarea id="attachIntro" name="attachIntro" class="form-control" style="width:500px" rows="4"  list="2" placeholder="文章内容" required></textarea></td>
							</tr>
						</table>
						</div>
                    </form >
             	</div>
                <div class="modal-footer">
                     <a href="javascript:void(0)" class="btn btn-default " data-dismiss="modal">取消</a>
                     <a href="javascript:void(0)" class="btn btn-primary" id="attachOKButton" onclick="onAttachOKButton()">确定</a>
                </div>
            </div>
        </div>
	</div>
</body>
</html>
