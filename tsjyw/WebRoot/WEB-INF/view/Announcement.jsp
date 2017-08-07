<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>发送公告</title>
    <%@include file="head.jsp" %>
    <script type="text/javascript">
    	$(document).ready(function() {
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
		
    	var attachs = [];
    	var cur = null;
    	
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
    		cur.attachSize = data.data.attachSize;
    		cur.attachUrl = data.data.attachUrl;
    		attachs.push(cur);
    		refreshAttach();
    	}
    
    	function sendAnnouncement()
    	{
    		var announment = new Object();
    		announment.recvObject = $("input[name='optionsRadios']:checked").val();
    		announment.recvObjectId = $("#recvObjectId").val();
    		announment.announmentTitle = $("#noticeTitle").val();
    		announment.announmentContent = $("#noticeContent").val();
    		announment.attachs = attachs;
    		//annoumment.${_csrf.parameterName} = "${_csrf.token}";
    		$.ajax({
    			url:"startSendAnnouncement",
    			type:"post",
    			data:{"annoument":JSON.stringify(announment),"${_csrf.parameterName}":"${_csrf.token}"},
    			dataType:"json",
    			success:function(data){
    				if(data.code == "succ"){
    					alert("发送成功");
    					location.reload();
    				}
    				else{
    					alert("发送失败");
    				}
    				
    			},
    			error:function(){
    				alert("网络错误");
    			}
    		});
    		
    		
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
		                <a href="#">公告</a>
		            </li>
		            <li>
		                <a href="#">发送公告</a>
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
    	<div class="radio">
	        <label>
	            <input type="radio" name="optionsRadios" id="optionsRadios1" value="allSchool" checked>
	            <c:out value="${countyName}"/>&mdash;全体师生
	        </label>
        </div>
        <input type="hidden" value="${countyId}" id="recvObjectId"/>
    	<c:if test="${role == 1}">
    		<div class="radio">
    			<label>
		            <input type="radio" name="optionsRadios" id="optionsRadios2" value="allUser">
		            	全体用户
		        </label>
			</div>
    	</c:if>
    
    
		
    </div>
    </div>
    <!--在这里加是在第一个div-->
    <div class="row">
    <div class="box col-md-12">
        <div class="box-inner">
            <div class="box-header well" data-original-title="">
                <h2><i class="glyphicon glyphicon-edit"></i>公告正文</h2>

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
                        <label for="exampleInputPassword1">公告标题</label>
                        <input type="text" class="form-control" id="noticeTitle" name="noticeTitle" placeholder="请添加公告标题">
                    </div>
                    <div class="row">
        <div class="box col-md-12">
        
                <div class="form-group">
                <input type='button' id='btnUploadFile' class="btn btn-success" value='添加附件'/>
                <table class="table table-striped table-bordered table-hover" id="TableAttach"></table>
                </div>
                
	            <div class="form-group">
	                <label for="exampleInputPassword1">公告内容</label>
	                <textarea class="form-control" id="noticeContent" name="noticeContent" rows="10" placeholder="请输入公告的内容" required id="noticeContent"></textarea>
	            </div>
        </div>
    </div><!--/span-->
                   	<input type="button" value="发送公告" class="btn btn-default" onclick="sendAnnouncement()"/>                   	
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
