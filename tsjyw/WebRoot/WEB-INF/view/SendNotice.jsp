<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>发送通知</title>
    <%@include file="head.jsp" %>
    <script type="text/javascript">
    	$(document).ready(function() {
    		/*
		  	$("#sel_menu2").select2({
		        tags: true,
		        maximumSelectionLength: 3  //最多能够选择的个数
		    });
		    $("#sel_menu3").select2({
		        tags: true,
		        maximumSelectionLength: 3  //最多能够选择的个数
		    });
		    $("#sel_menu2").prop("disabled", true);
		    $("#sel_menu3").prop("disabled", true);
			*/			
			$.ajax({
		   		url:"getReceiverTree",
		   		dataType:"json",
		   		success:function(data){
		   			if(data.code == "succ")
		   			{
		   				/*
		   				$("#sel_menu1").select2({
							data:data.data,
							placeholder:'请选择学校',
							allowClear:true
						});
						*/
						$('#tree').treeview({
					   		data: data.data,
					   		showCheckbox: true
					    });
					    
					    $('#tree').on('nodeChecked', function(event, node) {
					    	if (!node.checkable) {
					    		alert("没有权限向" + node.text + "发通知，请选择其他目标")
					    		$("#tree").treeview("uncheckNode", [node.nodeId, { silent: true }]);
					    		return;
					    	}
					    	checkChildNodes(node, true);
						}); 
						
						$('#tree').on('nodeUnchecked', function(event, node) {
							checkChildNodes(node, false);
							checkParentNode(node, false);
						});
						
						$('#tree').on('nodeSelected', function(event, node) {
							var ids = node.id.split("-");
							if (ids.length == 3) { // class
								if (node.nodes != null && node.nodes.length > 0)
									return;
									
								fillChildByStudent(node, ids[2]);
								$('#tree').treeview('expandNode', [ node.nodeId, { levels: 2, silent: true } ]);
							}
						});
		   			}
		   			else
		   				alert(data.data);
		   		}
		    });
		   
		   /*
		    * Initialize attachment table
		    */
		   initSamlpleUpload("btnUploadFile", onUploadComplete);
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
				 		title:'附件名称',
				 		width:400,
				 		align:'center',
				 		valigh:'middle'
				 	},
				 	{
				 		field:'attachSize',
				 		title:'附件大小',
				 		width:300,
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
		});
		
		function checkChildNodes(node, checked) {
			if (node.nodes == null)
				return;
				
			for (var i = 0; i < node.nodes.length; i++) {
				if (checked) {
					$("#tree").treeview("checkNode", [node.nodes[i].nodeId, { silent: true }]);
				}
				else {
					$("#tree").treeview("uncheckNode", [node.nodes[i].nodeId, { silent: true }]);
				}
				checkChildNodes(node.nodes[i], checked)
			}
		}
		
		function checkParentNode(node, check) {
			if (node.parentId != null) {
				if (check)
					$("#tree").treeview("checkNode", [node.parentId, { silent: true }]);
				else
					$("#tree").treeview("uncheckNode", [node.parentId, { silent: true }]);
				var pn = $("#tree").treeview("getNode", node.parentId);
				checkParentNode(pn, check);
			}
		}
		
		function parentCheck(node) {
			if (node.parentId == null)
				return false;
			var pn = $("#tree").treeview("getNode", node.parentId);
			return pn.state.checked;
		}
		
		function fillChildByStudent(node, id) {
			var url = "../StudentManage/getStudentBySchoolClass";
			var temp = {
	            pageSize: 1000,   //页面大小
	            pageNumber: 1,  //页码
				schoolClassId:parseInt(id)
	        };
	        <c:if test="${not empty _csrf}">
			temp.${_csrf.parameterName} = "${_csrf.token}";
			</c:if>
			
			$.ajax({
				url:url,			
				data:temp,
				dataType:"json",
				success:function(data) {  
					if (data.code != "succ") {
						alert(data.data);
						return;
					}
					
					for (var i = 0; i < data.rows.length; i++) {
						var child = { 
							'id': node.id + '-' + data.rows[i].userId, 
							'text': data.rows[i].userName,
							'checkable': true,
							'state': {
								checked: node.state.checked,
								disabled: false,
								expanded: false,
								selected: false
							}
						};
						$('#tree').treeview('addNode', [node.nodeId, { node: child}]);			
					}
			    },
			    error: function(XMLHttpRequest, textStatus, errorThrown) {
					alert("网络错误: " + XMLHttpRequest.status + ", " + textStatus);
			    }
			});	
		}
		
		//创建一个类用于保存前台选择的信息
		var chooseResult = new Object();
		chooseResult.schoolid = "";
		chooseResult.gradeid = new Array();
		chooseResult.schoolclassid = new Array();
		chooseResult.noticeTitle = "";
		chooseResult.noticeContent = "";
		//添加一个字段为接受着类型
		chooseResult.noticeType = 0;
		chooseResult.noticeIsReply = new Array();
		// attachments
		var attachs = [];
    	var cur = null;
    	
		//学校选择变化时
		function getGrade(value)
		{
			$("#sel_menu2").empty();
			$("#sel_menu3").empty();
			chooseResult.schoolid = value;
			if(value == "")
			{
				chooseResult.gradeid = new Array();
				chooseResult.schoolclassid = new Array();
				$("#sel_menu2").prop("disabled", true);
				$("#sel_menu3").prop("disabled", true);
			}
			else
			{
				$.ajax({
					url:"getGrade/"+value,
					//type:"post",
					dataType:"json",
					success:function(data){
						if(data.code == "succ")
						{
							$("#sel_menu2").prop("disabled", false);
							$("#sel_menu2").select2({
								data:data.data,
								placeholder:'如果不选默认为全选',
								allowClear:true
							});
						}
						else if(data.code == "fail")
							alert(data.data);
						else
							alert("未知错误");
					}
				});
			}
			
		}
		
		
		//年级选择变化时
		function getClass(value)
		{
			$("#sel_menu3").empty();
			if(value == "")
			{
				chooseResult.gradeid = new Array();
				$("#sel_menu3").prop("disabled", true);
			}
			else
			{
				chooseResult.gradeid = new Array();
				var data = $("#sel_menu2").select2('data');
				//获取了选择的年级id的数组
				for(var i=0;i<data.length;i++){
					chooseResult.gradeid[i] = data[i].id;
				}
		
				var json = {};
				for(var i=0;i<chooseResult.gradeid.length;i++)
				{
				    json[i]=chooseResult.gradeid[i];
				}
				$.ajax({
					url:"getClass/"+value,
					dataType:"json",
					data:{"names":JSON.stringify(json)}, 
					success:function(data){
						if(data.code == "succ")
						{
							$("#sel_menu3").prop("disabled", false);
							$("#sel_menu3").select2({
								data:data.data,
								placeholder:'如果不全默认为全选',
								allowClear:true
							});
						}
						else if(data.code == "fail")
							alert(data.code);
						else
							alert("未知错误");
					}
				});
			}
		}
		
		function getLastChoose(value)
		{
			chooseResult.schoolclassid = new Array();
			if(value == "")
			{
				chooseResult.schoolclassid = new Array();
				$("sel_menu3").empty();
			}
			else
			{
				var data = $("#sel_menu3").select2('data');
				//获取了选择的年级id的数组
				for(var i=0;i<data.length;i++){
					chooseResult.schoolclassid[i] = data[i].id;
				}
			}
		}
		
		/*
		function sendNotice()
		{
		    chooseResult.noticeTitle = $("#exampleInputPassword1").val();
		    chooseResult.noticeContent = $("#noticeContent").val();
		    chooseResult.noticeIsReply = new Array();
		    
		    var obj = document.getElementsByName("noticeCheckBox1");
		    for(var i=0;i<obj.length;i++)
		    {
		    	if(obj[i].checked)
		    		chooseResult.noticeIsReply[i] = obj[i].value;
		    }
		    chooseResult.attachements = attachs;
		    if(chooseResult.noticeTitle == ""){
		    	alert("标题不能为空");
		    	return;
		    }
		    if(chooseResult.noticeContent == ""){
		    	alert("通知内容不能为空");
		    	return;
		    }
		    
		    $.ajax({
		    	url:"sendNotice",
		    	type:"post",
		    	dataType:"json",
		    	data:{"names":JSON.stringify(chooseResult), "${_csrf.parameterName}":"${_csrf.token}"}, 
		    	beforeSend: function () {
			        // 禁用按钮防止重复提交
			        $("#submitnotice").attr({ disabled: "disabled" });
			    },
		    	success:function(data){
		    		if(data.code == "succ")
		    			alert("发送成功");
		    		else if(data.code == "fail")
		    			alert("发送失败:"+data.data);
		    		else
		    			alert("未知错误");
		    	},
		    	complete: function () {
			        $("#submitnotice").removeAttr("disabled");
			    }
		    });
		}
		*/
		function sendNotice()
		{	
		    var param = $('#noticeForm').serialize();
    		for (var i = 0; i < attachs.length; i++) {
    			param += "&attachments[" + i + "].attachName=" + attachs[i].attachName;
    			param += "&attachments[" + i + "].attachSize=" + attachs[i].attachSize;
    			param += "&attachments[" + i + "].attachUrl=" + attachs[i].attachUrl;
    		}
		    
		    var recv = $("#tree").treeview("getChecked");
		    if (recv == null || recv.length == 0) {
		    	alert("请选择通知接收人");
		    	return;
		    }
		    
		    var j = 0;
		    var added = [];
		    for (var i = 0; i < recv.length; i++) {
		    	var flag = false;
		    	for (var k = 0; k < added.length; k++) {
		    		if (added[k] == recv[i].id) {
		    			flag = true;
		    			break;
		    		}
		    	}
		    	if (flag)
		    		continue;
		    		
		    	added.push(recv[i].id);
		    	
		    	if (parentCheck(recv[i]))
		    		continue;
		    	var typeId = "";
		    	var groupId = "";
		    	var ids = recv[i].id.split("-");
		    	groupId = ids[ids.length - 1];
		    	typeId = ids.length; // 1:school, 2:grade, 3:class, 4:personal
		    	param += "&accepts[" + j + "].noticeAcceptGroupId=" + groupId;
		    	param += "&accepts[" + j + "].noticeAcceptTypeId=" + typeId;
		    	j++;
		    }
		    if (!confirm("选择了" + j + "个接收对象，确定发送通知吗？"))
		    	return;
		    	
		    $.ajax({
		    	url:"sendNotice",
		    	type:"post",
		    	dataType:"json",
		    	data:param, 
		    	beforeSend: function () {
			        // 禁用按钮防止重复提交
			        $("#submitnotice").attr({ disabled: "disabled" });
			    },
		    	success:function(data){
		    		if(data.code == "succ") {
		    			alert("发送成功");
		    			window.location.href="../SendNotice/";
		    		}
		    		else if(data.code == "fail")
		    			alert("发送失败:"+data.data);
		    		else
		    			alert("未知错误");
		    	},
		    	complete: function () {
			        $("#submitnotice").removeAttr("disabled");
			    }
		    });
		}
		
		function refreshAttach() {
    		$("#TableAttach").bootstrapTable('load', attachs);
    	}
    	
    	function deleteAttach(i) {
    		if (!confirm("确认要删除附件吗？"))
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
    		alert(cur.attachName);
    		cur.attachSize = data.data.attachSize;
    		cur.attachUrl = data.data.attachUrl;
    		attachs.push(cur);
    		refreshAttach();
    	}
		    	
    </script>
</head>

<body>
    <%@include file="top.jsp" %>
    <div class="ch-container">
        <div class="row">
         <%@include file="left.jsp"%>

        <div id="content" class="col-lg-10 col-sm-10">
            <!-- content starts -->
	        <div>
		        <ul class="breadcrumb">
		            <li>
		                <a href="#">我的通知</a>
		            </li>
		            <li>
		                <a href="#">发送通知</a>
		            </li>
		        </ul>
	    	</div>
    <div class="row">
    <div class="box col-md-12">
    <div class="box-inner">
    <div class="box-header well" data-original-title="">
        <h2><i class="glyphicon glyphicon-user"></i>选择接收人</h2>

        <div class="box-icon">
            <a href="#" class="btn btn-setting btn-round btn-default"><i class="glyphicon glyphicon-cog"></i></a>
            <a href="#" class="btn btn-minimize btn-round btn-default"><i
                    class="glyphicon glyphicon-chevron-up"></i></a>
            <a href="#" class="btn btn-close btn-round btn-default"><i class="glyphicon glyphicon-remove"></i></a>
        </div>
    </div>
    <div class="box-content">
    <div id="tree"></div>
<!-- 
   	<lable>学校级别</lable>	
	<select id="sel_menu1" class="js-states form-control" onchange="getGrade(this.value)">
		<option></option>
	</select>
	
	<lable>年级级别</lable>
	<select id="sel_menu2" multiple="multiple" class="form-control" onchange="getClass(this.value)"></select>
	
	<lable>班级级别</lable>
	<select id="sel_menu3" multiple="multiple" class="form-control" onchange="getLastChoose(this.value)"></select>
-->
    </div>
    </div>
    <!--在这里加是在第一个div-->
    <div class="row">
    <div class="box col-md-12">
        <div class="box-inner">
            <div class="box-header well" data-original-title="">
                <h2><i class="glyphicon glyphicon-edit"></i>通知正文</h2>

                <div class="box-icon">
                    <a href="#" class="btn btn-setting btn-round btn-default"><i
                            class="glyphicon glyphicon-cog"></i></a>
                    <a href="#" class="btn btn-minimize btn-round btn-default"><i
                            class="glyphicon glyphicon-chevron-up"></i></a>
                    <a href="#" class="btn btn-close btn-round btn-default"><i
                            class="glyphicon glyphicon-remove"></i></a>
                </div>
            </div>
            <div class="box-content">
                <form role="form" id="noticeForm" action="" method="post">
                    <div class="form-group">
                        <label for="exampleInputPassword1">通知标题</label>
                        <input type="text" class="form-control" id="noticeTitle" name="noticeTitle" placeholder="请添加通知标题">
                    </div>
                    <div class="row">
        <div class="box col-md-12">
                <div class="form-group">
                <input type='button' id='btnUploadFile' class="btn btn-success" value='添加附件'/>
                <table class="table table-striped table-bordered table-hover" id="TableAttach"></table>
                </div>
	            <div class="form-group">
	                <label for="exampleInputPassword1">通知内容</label>
	                <textarea class="form-control" id="noticeContent" name="noticeContent" rows="10" placeholder="请输入通知的内容" required id="noticeContent"></textarea>
	            </div>
                <label for="exampleInputPassword1">过期时间（默认过期时间为一星期）:</label>
                <input type="date" id="noticeOverTime" name="noticeOverTime"/>
        </div>
    </div><!--/span-->
    				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                   	<input type="button" id="submitnotice" value="发送通知" class="btn btn-default" onclick="sendNotice()"/>                   	
                    <!-- <button type="submit" class="btn btn-default" style="margin-left:50px">保存草稿</button> -->
                    <button type="button" class="btn btn-default" style="margin-left:50px">取消发送</button>
                </form>

            </div>
        </div>
    </div>
    <!--/span-->

</div><!--/row-->
    </div>
    <!--/span-->

    </div><!--/row-->


    <!-- content ends -->
    </div><!--/#content.col-md-0-->
</div><!--/fluid-row-->
    <!-- Ad ends -->


</div><!--/row-->
    <hr>


    <footer class="row">
        <p class="col-md-9 col-sm-9 col-xs-12 copyright">&copy; <a href="http://usman.it" target="_blank">Muhammad
                Usman</a> 2012 - 2015</p>

        <p class="col-md-3 col-sm-3 col-xs-12 powered-by">Powered by: <a
                href="http://usman.it/free-responsive-admin-template">Charisma</a></p>
    </footer>

</div><!--/.fluid-container-->

<!--这个是隐藏的弹窗-->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
         aria-hidden="true">

        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">×</button>
                    <h4>请选择收件人</h4>
                </div>
                <div class="modal-body">
                    <div class="box-content">
                    <table class="table table-bordered table-striped table-condensed">
                        <thead>
                        <tr>
                            <th>选择</th>
                            <th>收件人</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td><input type="checkbox"/></td>
                            <td>校长</td>                 
                        </tr>
                        <tr>
                            <td><input type="checkbox"/></td>
                            <td>班主任</td>
                        </tr>
                        </tbody>
                    </table>
                    <ul class="pagination pagination-centered">
                        <li><a href="#">Prev</a></li>
                        <li class="active">
                            <a href="#">1</a>
                        </li>
                        <li><a href="#">2</a></li>
                        <li><a href="#">3</a></li>
                        <li><a href="#">4</a></li>
                        <li><a href="#">Next</a></li>
                    </ul>
                </div>

                </div>
                <div class="modal-footer">
                    <a href="#" class="btn btn-default" data-dismiss="modal">关闭</a>
                    <a href="#" class="btn btn-primary" data-dismiss="modal">确定添加</a>
                </div>
            </div>
        </div>
</div>

</body>
</html>
