<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<META HTTP-EQUIV="pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate">
<META HTTP-EQUIV="expires" CONTENT="Wed, 26 Feb 1997 08:21:57 GMT">
<html lang="en">
<head>
    
    <title>管理商品</title>
    <%@include file="head.jsp" %>
    
    <script type="text/javascript"> 
    	var action = "create";
    	var businessid = <c:out value="${businessId}"/>;    
    	$(function(){
    	
    		$.ajaxSetup ({
         		cache: false //关闭AJAX缓存
     		});
    		loadGoods();
    		bindSelect("goodsStateId","../GoodsManage/getGoodsState",null,"goodsStateId","goodsStateName");
    		
    		//搜索
    		bindSelect("goodsStateId1","../GoodsManage/getGoodsState",null,"goodsStateId","goodsStateName","","","全部");
    		bindSelect("businessId1","../BusinessManage/getAllBusiness",{pageNumber:1, pageSize: 100},"businessId","businessName","","","全部");
    		
    		bindSelect("businessId","../BusinessManage/getAllBusiness",{pageNumber:1, pageSize: 100},"businessId","businessName");
    		bindSelect("goodsTypeId","../GoodsManage/getGoodsType",null,"goodsTypeId","goodsTypeName","","", "全部");
    		initSamlpleUpload("btnUploadFile",onUploadComplete);
    		
    	});
    	
 
    	function queryParams(params) {  //配置参数
	        var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
	            pageSize: params.pageSize,   //页面大小
	            pageNumber: params.pageNumber,
	            businessId : <c:out value="${businessId}"/>  
	        };
	        <c:if test="${not empty _csrf}">
				temp.${_csrf.parameterName} = "${_csrf.token}";
			</c:if>
			extendTalbeParams(temp, "searchForm");
        	return temp;
    	}
    	
    	function loadGoods()
    	{
    		$("#TableGoods").bootstrapTable({
		    	url: "getAllGoods", 
		    	method:"get",
		    	contentType: "application/x-www-form-urlencoded;charset=utf-8",
		    	queryParams: queryParams,
		    	queryParamsType:"undefined",//参数格式,发送标准的restful类型的参数请求
		    	striped: true,     //使表格带有条纹  
  				pagination: true, //在表格底部显示分页工具栏 
		    	dataType: "json",
			    pagination: true,
			    //search: true, //是否显示右上角的搜索框  
			    sidePagination: "server", 
			    idField: "goodsId",  //标识哪个字段为id主键  
			    pageSize: 10,  //每一页 的 数量
				pageNumber:1,     //当前页码
				//showRefresh: true,  //显示刷新按钮  
				singleSelect: true,//复选框只能选择一条记录  
				clickToSelect: true,//点击行即可选中单选/复选框  
				columns: [
				        {
				        	field: 'goodsId', 
		                    title: '商品代码',		 
		                    width: 150,		 
		                    align: 'center',		 
		                    valign: 'middle',		 
		                    sortable: true,
				        },
				        {
				        	field: 'goodsName', 
		                    title: '商品名称',		 
		                    width: 150,		 
		                    align: 'center',		 
		                    valign: 'middle'
				        },
				        {
				        	field: 'goodsPrice', 
		                    title: '商品单价',		 
		                    width: 150,	 
		                    align: 'center',		 
		                    valign: 'middle'
				        },
				        {
				        	field: 'goodsCount', 
		                    title: '库存数量',		 
		                    width: 100,		 
		                    align: 'center',		 
		                    valign: 'middle'
				        },
				        {
				        	field: 'goodsLimitNumber', 
		                    title: '限购数量',		 
		                    width: 100,		 
		                    align: 'center',		 
		                    valign: 'middle'
				        },
				        {
		                    title: '操作',
		                    field: 'goodsId',
		                    formatter:function(value,row,index){
		                    	var a = '<a class="btn btn-info" data-toggle="modal" data-target="#showGoodsDialog" href="javascript:void(0);" onclick=\"showGoods('+value+')\">';
   								var b = '<i class="glyphicon glyphicon-zoom-in icon-white"></i>详情信息</a> ';		                        
	                   			var c = '<a class="btn btn-info" data-toggle="modal" data-target="#goodsDialog" href="javascript:void(0);" onclick=\"modifyGoods('+value+')\">'; 
	                   			var d = '<i class="glyphicon glyphicon-zoom-in icon-white"></i>修改</a> ';
	                   			var g = '<a class="btn btn-info" href="EditGoods?goodsId='+value+'">'; 
	                   			var h = '<i class="glyphicon glyphicon-zoom-in icon-white"></i>介绍商品</a> ';
	                   			var e = '<a class="btn btn-danger" href="javascript:void(0);" onclick=\"deleteGoods('+value+')\" >';
	                   			var f = '<i class="glyphicon glyphicon-zoom-in icon-white"></i>删除</a> ';    			
	                        	return a+b+c+d+g+h+e+f;  
		                  	}
                  		}				        
				]
		    });
    	}
    	
		function showGoods(goodsId)
   		{
   			$.ajax({
				url:"getGoods",			
				data:{"goodsId":goodsId},
				dataType:"json",
				success:function(data) {  
					if (data.code != "succ") {
						alert(data.data);
						$('#showGoodsDialog').modal('hide');
						return;
					}
					
			        fillGoodsDetail(data.data);
			       
			        var html = "";
			        for (var i = 0; i < data.data.goodsTypes.length; i++)
			        	html += data.data.goodsTypes[i].goodsTypeName + " ";
			        $('#goodsTypeIdDetail').text(html);
			        $('#goodsIntroDetail').text(data.data.goodsIntro);
			        if(data.data.goodsImagaPath != null){
			        $('#goodsImagaPathDetail').val(data.data.goodsImagaPath);
   					$('#logoDetail').attr("src",data.data.goodsImagaPath);
			        }
   				
			        
			    },
			    error: function(XMLHttpRequest, textStatus, errorThrown) {
					alert("网络错误: " + XMLHttpRequest.status + ", " + textStatus);
			    }
			});	
   			
   		}
   		
   		$("#goodsDialog").on("hidden.bs.model",function(e){$(this).removeData();}); 
   		
   		function createGoods() {
   			action = "create";
   			$("#title").html("添加商品");
   			$('#goodsForm')[0].reset();
			
			onGoodsShow();
			fillGoodsType();
   		}
   		
   		function modifyGoods(goodsId) {
   			action = "modify";
   			$("#title").html("修改商品信息");
   			$('#goodsForm')[0].reset();
   			onGoodsShow();
			$.ajax({
				url:"getGoods",			
   				data:{"goodsId":goodsId},
				dataType:"json",
				success:function(data) {  
			        if (data.code != "succ") {
   						alert(data.data);
   						$('#goodsDialog').modal('hide');
   						return;
   					}
   					if(data.data.goodsImagaPath != null){
   						$('#goodsImagaPath').val(data.data.goodsImagaPath);
   						$('#logo').attr("src",data.data.goodsImagaPath);
   					}
  			        fillGoods(data.data);
  			        fillGoodsType(data.data.goodsTypes);
  			        $('#goodsIntro').val(data.data.goodsIntro);
			    },
			    error: function(XMLHttpRequest, textStatus, errorThrown) {
					alert("网络错误: " + XMLHttpRequest.status + ", " + textStatus);
			    }
			});	   						
   		}
   		
   		function deleteGoods(goodsId) {
   			if (!confirm("确认删除商品吗？"))
   				return;
   				
   			$.ajax({
				url:"deleteGoods",			
				data:{"goodsId":goodsId},
				dataType:"json",
				success:function(data) {  
					if (data.code != "succ") {
   						alert(data.data);
   						$('#goodsDialog').modal('hide');
   						return;
   					}
      				$("#TableGoods").bootstrapTable('refresh');
  				},
			    error: function(XMLHttpRequest, textStatus, errorThrown) {
					alert("网络错误: " + XMLHttpRequest.status + ", " + textStatus);
			    }
			});
   		}
   		
   		function onUploadComplete(data) {
	   		if (data == null || data == undefined) {
	   			alert("上传错误!");
	   			return;
	   		}
	   		$("#goodsImagaPath").val(data.data.attachUrl);
	   		$('#logo').attr("src", data.data.attachUrl);
	   	}	
	   	
	   	
   		function onOKButton() {
   			var url = "createGoods";
   			if (action == "modify")
   				url = "modifyGoods";
   			onGoodsSubmit();
   			var param = $('#goodsForm').serialize();
 			param = goodsTypeOnSubmit(param);
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
					if (action == "create")
						alert("创建成功");
					else
						alert("修改成功");
	        		$('#goodsDialog').modal('hide');
	        		$("#TableGoods").bootstrapTable('refresh');
	        	},
			    error: function(XMLHttpRequest, textStatus, errorThrown) {
					alert("网络错误: " + XMLHttpRequest.status + ", " + textStatus);
			    }
	        });
   		}   
   		
   		
   		function searchFunc() {
			$("#TableGoods").bootstrapTable('refresh');
		}

		function clearFunc() {
			$('#searchForm')[0].reset();
			$("#TableGoods").bootstrapTable('refresh');
		}
    </script>
    
</head>

<body>
    <%@include file="top.jsp" %>

    <div class="ch-container">
        <div class="row">
            <%@include file="left.jsp" %>

        <noscript>
            <div class="alert alert-block col-md-12">
                <h4 class="alert-heading">Warning!</h4>

                <p>You need to have <a href="http://en.wikipedia.org/wiki/JavaScript" target="_blank">JavaScript</a>
                    enabled to use this site.</p>
            </div>
        </noscript>

        <div id="content" class="col-lg-10 col-sm-10">
            <!-- content starts -->
                <div>
        <ul class="breadcrumb">
            <li>
                <a href="BusinessManage">商家管理</a>
            </li>
            <li>
                <a href="GoodsManage">商品管理</a>
            </li>
        </ul>
    </div>
    <div class="row">
    <div class="box col-md-12">
    <div class="row">
        <div class="box col-md-12">
            <div class="box-inner">
                <div class="box-header well" data-original-title="">
                    <h2>商品管理</h2>

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
               	<div class="alert alert-info">
                    <a class="btn btn-success" data-toggle="modal"  data-target="#goodsDialog" onclick="createGoods()">
                        <i class="glyphicon glyphicon-plus icon-white"></i>添加商品 </a>               
                </div>
                	
                	
                	<div class="box-content" id="search">
							<form class="form-inline" role="form" id='searchForm'>
							<label class="control-label">商品状态</label>
							<select class="form-control" id="goodsStateId1" name="[Equal]goodsStateId"></select>
							
							<sec:authorize access="hasAuthority('SuperAdmin')">
								<label class="control-label">商家</label>
							<select class="form-control" id="businessId1" name="[Equal]businessId"></select>
							</sec:authorize>
							
							<label class="control-label">商品名称 </label>
							<input type="text" class="form-control" name="[Like]goodsName" id="[Like]goodsName">
							<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
							<a class="btn btn-success" onclick="searchFunc()" ><i class="glyphicon glyphicon-search icon-white"></i>查询</a> 
							<a class="btn btn-danger" onclick="clearFunc()" ><i class="glyphicon glyphicon-remove icon-white"></i>清除</a> 
							</form>
						</div>
                
                
                    <table class="table table-striped table-bordered table-hover" id="TableGoods"></table>
                </div>
            </div>
        </div>

    </div><!--/span-->

    <!-- content ends -->
    </div><!--/#content.col-md-0-->
</div><!--/fluid-row-->

    <!-- Ad, you can remove it -->
    <div class="row">
        <div class="col-md-9 col-lg-9 col-xs-9 hidden-xs">
            <script async src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>
            <!-- Charisma Demo 2 -->
            <ins class="adsbygoogle"
                 style="display:inline-block;width:728px;height:90px"
                 data-ad-client="ca-pub-5108790028230107"
                 data-ad-slot="3193373905"></ins>
            <script>
                (adsbygoogle = window.adsbygoogle || []).push({});
            </script>
        </div>
        <div class="col-md-2 col-lg-3 col-sm-12 col-xs-12 email-subscription-footer">
            <div class="mc_embed_signup">
                <form action="//halalit.us3.list-manage.com/subscribe/post?u=444b176aa3c39f656c66381f6&amp;id=eeb0c04e84" method="post" id="mc-embedded-subscribe-form" name="mc-embedded-subscribe-form" class="validate" target="_blank" novalidate>
                    <div>
                        <label>Keep up with my work</label>
                        <input type="email" value="" name="EMAIL" class="email" placeholder="Email address" required>

                        <div class="power_field"><input type="text" name="b_444b176aa3c39f656c66381f6_eeb0c04e84" tabindex="-1" value=""></div>
                        <div class="clear"><input type="submit" value="Subscribe" name="subscribe" class="button"></div>
                    </div>
                </form>
            </div>

            <!--End mc_embed_signup-->
        </div>

    </div>
    <!-- Ad ends -->

    <hr>

	 <!-- 查看商品对话框 -->
	 <div class="modal fade" id="showGoodsDialog" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
         aria-hidden="true">

        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">×</button>
                    <h3>查看商品信息</h3>
                </div>
                <div class="form-group has-success has-feedback">
                	
                	<%@include file="goodsDetail.jsp" %>
                	<table>
                		<tr>
                			<td width="20%"><label  class="control-label">商品类型</label></td>
                			<td width="30%"><label  id="goodsTypeIdDetail" class="control-label">商品类型</label></td>       
                		</tr>
                		<tr>
                			<td width="20%"><label  class="control-label">介绍</label></td>
                			<td width="30%"><label  id="goodsIntroDetail" class="control-label">无</label></td>                			
                		</tr>
                		<tr>
					        <td ><label class="control-label">商品图片</td>
							<td ><img  id="logoDetail"  src="../images/no.jpg"   width="300px" /></td>															
						
						</tr>
						 <input type= "hidden" name="goodsImagaPathDetail" id="goodsImagaPathDetail" value="">
                	</table>
                </div>
                <hr>
                <div class="modal-footer">
                     <a href="#" class="btn btn-primary" data-dismiss="modal">关闭</a>
                </div>
            </div>
        </div>
    </div>
    
    <!--新建和修改商品信息 -->
   <div class="modal fade" id="goodsDialog" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">

        <div class="modal-dialog"  style="width:1000px">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">×</button>
                    <h3><span id='title'>添加商品</span></h3>
                </div>
                <div class="modal-body">
                    <form class="form-inline" role="form" id='goodsForm'>
                    	<%@include file="goods.jsp" %>
                    	<div class="form-group has-success has-feedback" id="showGoods">
                         <table  >
                         	<tr>
						        <td><input type="button" value="网站图标..." class="form-control" id="btnUploadFile" name="upload" /></td>
								<td><img id="logo" src="../images/no.jpg" width="300px" /></td>															
							</tr>
                        	<tr>
                        		<td width="80px"><label class="control-label" >类型</label></td>
                        		<td><%@include file="GoodsType.jsp"%></td> 
                        	</tr>
                        	<tr>
                        		<td style="vertical-align:top"><label class="control-label" for="goodsIntro">介绍</label></td>
								<td><textarea class="form-control" id="goodsIntro" name="goodsIntro" style="width:600px;margin-left:32px" rows="2"  list="2" placeholder="介绍" required></textarea></td>						
							</tr>
						</table> 
						<input type = "hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/> 
						<input type = "hidden" name = "goodsId" id = "goodsId"/>
						<input type = "hidden" name = "businessId" id ="businessId"/>
						<input type = "hidden" name = "schoolLogo" id="schoolLogo"/>
						<input type= "hidden" name="goodsImagaPath" id="goodsImagaPath" value="">
						</div>
                    </form>                                                                
                </div>
                <!-- body -->
                <hr>
                <div class="modal-footer">
                     <a href="javascript:void(0)" class="btn btn-default " data-dismiss="modal">取消</a>
                     <a href="javascript:void(0)" class="btn btn-primary" id="goodsOKButton" onclick="onOKButton()">确定</a>
                </div>
            </div>
        </div>
    </div> 

    <footer class="row">
        <p class="col-md-9 col-sm-9 col-xs-12 copyright">&copy; <a href="http://usman.it" target="_blank">Muhammad
                Usman</a> 2012 - 2015</p>
        <p class="col-md-3 col-sm-3 col-xs-12 powered-by">Powered by: <a
                href="http://usman.it/free-responsive-admin-template">Charisma</a></p>
    </footer>
</div><!--/.fluid-container-->  
</body>
</html>
