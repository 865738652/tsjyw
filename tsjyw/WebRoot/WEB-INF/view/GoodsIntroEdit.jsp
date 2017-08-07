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
    <title>商品简介</title>
    <%@include file="head.jsp" %>
    <script type="text/javascript">
    	function onOKButton() {
    		ue_onsubmit();

    		var param = $('#articleForm').serialize();
    		alert(param);
    		
    		url = "ModifyGoodsIntro";
   				
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
			                <a href="#">积分商城</a>
			            </li>
			            <li>
			                <a href="#">商品管理</a>
			            </li>
			            <li>
			            	<a href="#">商品简介</a>
			            </li>
			        </ul>
    			</div>
				<div class="box-content">
					<ul class="nav nav-tabs" id="myTab">
	                    <li class="active"><a href="#info">商品简介</a></li>
                	</ul>
                	<div id="myTabContent" class="tab-content">
	                    <div class="tab-pane active" id="info">
	                    	<input type="hidden" id="goodsId" name="goodsId" value="${goodsId}"/>
	                        <table>
			                  	<tr>
									<td style="vertical-align:top"><label class="control-label" for="inputSuccess1">商品简介</label></td>
			                  		<td colspan='3'></td>
			                  	</tr>		
			                  	<tr>									
			                  		<td colspan='4'><%@include file="UEditor.jsp" %></td>
			                  	</tr>			                  	
							</table>
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
</body>
</html>
