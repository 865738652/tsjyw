<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf8"%>
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
    
    <title>志愿者申请</title>
    <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=974e0cfe9ad49ccdf2d73e0a098b8239"></script>
    <script type="text/javascript" class="resources library" src="../js/area.js" ></script>
    <%@include file="head.jsp" %>
    
       <script type="text/javascript">
    	 
    	var action = "create"; 
    	var showType = '<c:out value="${showType}"/>';    	
    	$(function(){
    	
    		$.ajaxSetup ({
         		cache: false //关闭AJAX缓存
     		});
    		
    		loadApply(); 
    		bindCheckbox();  	
    	});
    	
    	function queryParams(params) {  //配置参数
    		var temp;
    		temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
	            pageSize: params.pageSize,   //页面大小
	            pageNumber: params.pageNumber,
	            showType:showType
	       	};
	       	<c:if test="${not empty _csrf}">
				temp.${_csrf.parameterName} = "${_csrf.token}";
			</c:if>
        	return temp;
    	}
    	
    	function loadApply()
    	{ 
    		var url = "getAllApply"; 
    							  				
    		$("#TableApply").bootstrapTable({
		    	url:url, 
		    	method:"post",
		    	contentType: "application/x-www-form-urlencoded;charset=utf-8",
		    	queryParams: queryParams,
		    	queryParamsType:"undefined",//参数格式,发送标准的restful类型的参数请求
		    	striped: true,     //使表格带有条纹  
  				pagination: true, //在表格底部显示分页工具栏 
		    	dataType: "json",
			    search: false, //是否显示右上角的搜索框  
			    sidePagination: "server", 
			    //showHeader: false,  隐藏表头
			    idField: "applyId",  //标识哪个字段为id主键  
			    pageSize: 10,  //每一页 的 数量
				pageNumber:1,     //当前页码
				showRefresh: false,  //显示刷新按钮  
				singleSelect: true,//复选框只能选择一条记录  
				clickToSelect: true,//点击行即可选中单选/复选框  
				columns: [
				        {
				        	field: 'userName', 
		                    title: '申请人',		 
		                    width: 150,		 
		                    align: 'center',		 
		                    valign: 'middle'
				        },	
				        {
				        	field: 'userNickname', 
		                    title: '昵称',		 
		                    width: 150,		 
		                    align: 'center',		 
		                    valign: 'middle'
				        },	
				        {
				        	field: 'userAccount', 
		                    title: '账号',		 
		                    width: 150,		 
		                    align: 'center',		 
		                    valign: 'middle'
				        },	
				        {
				        	field: 'applyTime', 
		                    title: '申请时间',		 
		                    width: 200,		 
		                    align: 'center',		 
		                    valign: 'middle',
		                    formatter: function(value,row,index){
		                    	return getSmpFormatDateByLong(value, false);
		                    }
				        },	
				        {
				        
				        	field: 'applyStateName', 
		                    title: '审核状态',		 
		                    width: 150,		 
		                    align: 'center',		 
		                    valign: 'middle'
				        },				        
				        {
		                    title: '操作',
		                    field: 'applyId',
		                    formatter:function(value,row,index){		
		                    	var html = '<a class="btn btn-info" data-toggle="modal" data-target="#showApplyDialog" href="javascript:void(0);" onclick=\"showApply('+value+')\">';
   								html += '<i class="glyphicon glyphicon-zoom-in icon-white"></i>查看</a> '; 
	                   			html += '<a class="btn btn-info" href="Apply?action=modify&applyId=' + value + '">';
	                   			html += '<i class="glyphicon glyphicon-edit icon-white"></i>修改</a> ';	                   			
	                   			html += '<a class="btn btn-danger" href="javascript:void(0);" onclick=\"deleteApply('+value+')\" >';
	                   			html += '<i class="glyphicon glyphicon-remove icon-white"></i>撤销</a> '; 
	                        	return html;  
		                  	}
                  		}				        
				]
		    });
    	}
		
   		$("#applyDialog").on("hidden.bs.model",function(e){$(this).removeData();}); 
   		
   		function showApply(applyId)
		{
			$.ajax({
				url:"getApply",			
				data:{"applyId":applyId},
				dataType:"json",
				success:function(data) {  
					if (data.code != "succ") {
						alert(data.data);
						$('#showApplyDialog').modal('hide');
						return;
					}
					$('#applyId1').val(data.data.applyId);
			        $('#userNameDetail').text(data.data.userName);
			        $('#userAccountDetail').text(data.data.userAccount);
					$('#applyTimeDetail').text(data.data.applyTime);
					$('#applyStateNameDetail').text(data.data.applyStateName);
					$('#applyIntroDetail').html(data.data.applyIntro);
			    }
			});	
		}
		
		function onAudit(action) {
			var param = $('#applyOpinionForm').serialize();
			param += "&action="+action;
			alert(param);
			$.ajax({
				url:"auditApply",	
				type:"post",
    			dataType:"json",
				data:param,
				success:function(data) {  
					if (data.code != "succ") {
						alert(data.data);
						return;
					}
					alert("审核成功");
					$('#showApplyDialog').modal('hide');
					$('#showApplyOpinionDialog').modal('hide');
					$("#TableApply").bootstrapTable('refresh');
			    }
			});	
		}
		
   		function createApply() {  
   			window.location.href = 'Apply';
   			/*action = "create";
   			$("#title").html("我要申请");
   			$('#applyForm')[0].reset();
   			$("#applyId").val("");
   			fillAgeLevels();
   			fillAskQuestionTypes();*/
   		}
   		
   		function modifyApply(applyId) {
   			action = "modify";
   			$("#title").html("修改申请");
   			$('#applyForm')[0].reset();
   			alert(applyId);
			$.ajax({
				url:"getApply",			
				data:{"applyId":applyId},
				dataType:"json",
				success:function(data) {  
			        if (data.code != "succ") {
   						alert(data.data);
   						$('#applyDialog').modal('hide');
   						return;
   					}
  			        $('#applyId').val(data.data.applyId);
					$('#applyTime').val(data.data.applyTime);
					$('#applyIntro').val(data.data.applyIntro);
					$('#applyAddress').val(data.data.applyAddress);
					$('#applyAddrLng').val(data.data.applyAddrLng);
					$('#applyAddrLat').val(data.data.applyAddrLat);
					fillAgeLevels(data.data.ageLevels);
					fillAskQuestionTypes(2, data.data.askQuestionTypes);
			    },
			    error: function(XMLHttpRequest, textStatus, errorThrown) {
					alert("网络错误: " + XMLHttpRequest.status + ", " + textStatus);
			    }
			});	   						
   		}
   		
   		function deleteApply(applyId) {
   			if (!confirm("确认撤销申请吗？"))
   				return;
   				
   			$.ajax({
				url:"deleteApply",			
				data:{"applyId":applyId},
				dataType:"json",
				success:function(data) {  
					if (data.code != "succ") {
   						alert(data.data);
   					}
      				$("#TableApply").bootstrapTable('refresh');
  				},
			    error: function(XMLHttpRequest, textStatus, errorThrown) {
					alert("网络错误: " + XMLHttpRequest.status + ", " + textStatus);
			    }
			});
   		}
   		
   		function onOKButton() {
   			var url = "createApply";
   			
   			if (action == "modify")
   				url = "modifyApply";
   			
   			var param = $('#applyForm').serialize();
   			param = ageLevelOnSubmit(param);
   			param = askQuestionTypeOnSubmit(param);
   			alert(param);
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
						alert("申请成功");
					else
						alert("修改申请成功");
	        		$('#applyDialog').modal('hide');
	        		$("#TableApply").bootstrapTable('refresh');
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
    <!--左侧开始-->
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
           
                <div>
        <ul class="breadcrumb">
            <li>
                <a href="#">管理中心</a>
            </li>
            <li>
                <a href="#">志愿者申请</a>
            </li>
        </ul>
    </div>
<!--表格显示的内容-->
<div class="box col-md-12">
        <div class="box-inner">
                <div class="box-header well" data-original-title="">
                    <h2>志愿者申请</h2>
                    <div class="box-icon">
                        <a href="#" class="btn btn-setting btn-round btn-default"><i class="glyphicon glyphicon-cog"></i></a>
                        <a href="#" class="btn btn-minimize btn-round btn-default"><i class="glyphicon glyphicon-chevron-up"></i></a>
                        <a href="#" class="btn btn-close btn-round btn-default"><i class="glyphicon glyphicon-remove"></i></a>
                    </div>
              </div>
                
                <div class="box-content">
               	<div class="alert alert-info">
               		<a href='#' class="btn btn-success " onclick="createApply()">我要申请</a>
                </div>
                    <table class="table table-striped table-bordered table-hover" id="TableApply"></table>       
            </div>
        </div>
  </div>

<!-- 查看对话框 -->																
<div class="modal fade" id="showApplyDialog" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
         aria-hidden="true">

        <div class="modal-dialog">
            <div class="modal-content">
              <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>  
                    <h4 class="modal-title">查看申请</h4> 
                </div>
                <div class="modal-body">
                   <table width="90%">
                   		<tr>
							<td width="20%"><label  class="control-label">申请人</label></td>
							<td width="30%"><label  id="userNameDetail" class="control-label">申请人</label></td>
							<td width="20%"><label  class="control-label">账号</label></td>
							<td width="30%"><label  id="userAccountDetail" class="control-label">账号</label></td>
						</tr>
						<tr>
							<td><label  class="control-label">申请时间</label></td>
							<td><label  id="applyTimeDetail" class="control-label">申请时间</label></td>
							<td><label  class="control-label">审核状态</label></td>
							<td><label  id="applyStateNameDetail" class="control-label">审核状态</label></td>
						</tr>
						<tr>
							<td><label  class="control-label">个人介绍</label></td>
							<td colspan="3"><div id="applyIntroDetail"></div></td>
                	</table>
                          
                </div>
                <hr>
                <div class="modal-footer">
                	
                	<a href="javascript:void(0)" class="btn btn-primary" id="applyPassButton" onclick="onAudit('pass')">批准</a>
                	<!-- <a href="javascript:void(0)" class="btn btn-primary" id="applyBlockButton" onclick="onAudit('block')">拒绝</a> -->
                	<a class="btn btn-info" data-toggle="modal" data-target="#showApplyOpinionDialog" href="javascript:void(0)">拒绝</a>
                    <a href="#" class="btn btn-primary" data-dismiss="modal">关闭</a>
                </div>
            </div>
        </div>
</div>
<!-- 申请意见及建议 -->
<div class="modal fade" id="showApplyOpinionDialog" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>  
                    <h4 class="modal-title">意见及建议</h4> 
            </div>
            <div class="modal-body">
                <form class="form-inline" role="form" id='applyOpinionForm'>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/> 
                    <div class="form-group has-success has-feedback" id="LookClass2">
	                    <textarea id="applyOpinion" class="form-control" name="applyOpinion" style="width:500px;height:320px;" rows="8" list="2" placeholder="申请意见及建议。" required></textarea>    
						<input type="hidden"  id="applyId1" name="applyId"/>						
					</div>
                </form >               	                                 
            </div>
            <hr>
            <div class="modal-footer">
                <a href="javascript:void(0)" class="btn btn-default " data-dismiss="modal">取消</a>
                <a href="javascript:void(0)" class="btn btn-primary" id="applyOpinionOK" onclick="onAudit('block')">确定</a>
            </div>
        </div>
    </div>        
</div>

<!-- 添加和修改班级对话框 -->
<div class="modal fade" id="applyDialog" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
         aria-hidden="true">

        <div class="modal-dialog" style="width:800px">
            <div class="modal-content">
              <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" >×</button>  
                    <h3><span id='title'>修改申请</span></h3>
                </div>
                <div class="modal-body">
                   <form class="form-inline" role="form" id='applyForm'>
                   		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/> 
                    	<div class="form-group has-success has-feedback" id="LookClass2">
                        <table >  
                        	<tr>
                        		<td width="20%"><label class="control-label" for="applyIntro">年龄阶段</label></td>
                        		<td><%@include file="agelevel.jsp"%></td>
                        	</tr>
                        	<tr>
                        		<td width="20%"><label class="control-label" for="applyIntro">擅长领域</label></td>
                        		<td><%@include file="askquestiontype.jsp"%></td>
                        	</tr>  
                        	 <tr>
								<td width="20%"><label class="control-label" for="applyIntro">请选择地区</label></td>
								<td>
									<select id="province" name="province"></select>  
								    <select id="city" name="city" ></select>  
								    <select id="county" name="county"></select>
								    <script type="text/javascript">_init_area();</script>								
								</td>
							</tr>     
                        	<tr>
								<td width="20%"><label class="control-label" for="applyIntro">请选择地址</label></td>
								<td>
									<input type="text"  id="applyAddress" class="form-control" name="applyAddress" style="width:500px;overflow:hidden;">
								</td>
							</tr>          
							<tr>
								<td style="te"><label class="control-label" for="applyIntro">个人简介</label></td>							
							
								<td><textarea id="applyIntro" class="form-control" name="applyIntro" style="width:500px;height:320px;rows="8"  list="2" placeholder="介绍" required></textarea></td>								
							</tr>							
							<tr>
								<td><label class="control-label" for="applyIntro">选择位置</label></td>
								<td>
									<div id="baidumap" style="width:500px;height:250px;overflow:hidden;"></div>
								</td>
							</tr>
						</table>
						<input type="hidden"  id="applyId" name="applyId"/>
						<input type="hidden"  id="applyAddrLng" name="applyAddrLng"/>
						<input type="hidden"  id="applyAddrLat" name="applyAddrLat"/>
						<input type="hidden"  id="applyPCC" name="applyPCC" >
						</div>
                    </form >
                    
             	</div>
                <div class="modal-footer">
                     <a href="javascript:void(0)" class="btn btn-default " data-dismiss="modal">取消</a>
                     <a href="javascript:void(0)" class="btn btn-primary" id="applyOKButton" onclick="onOKButton()">确定</a>
                </div>
            </div>
        </div>
   </div>  
 </div>
 </div>
 </div>
</body>
</html>
<script type="text/javascript">

    // 当前经纬度

    var lng;
    var lat;
    var accountposition;
     var map;
     map = new BMap.Map("baidumap", { enableMapClick: false }); // 创建Map实例
     map.centerAndZoom(new BMap.Point(118.185599, 39.641674), 13);
     map.enableScrollWheelZoom();
     map.addControl(new BMap.NavigationControl());
     map.addControl(new BMap.MapTypeControl({ mapTypes: [BMAP_NORMAL_MAP, BMAP_SATELLITE_MAP] }));
     map.disableContinuousZoom();
     window.map = map; //将map变量存储在全局
     
   //地图搜索
     function searchMap() {
         var area = document.getElementById("input").value; //得到地区
         var ls = new BMap.LocalSearch(map);
         ls.setSearchCompleteCallback(function(rs) {
             if (ls.getStatus() == BMAP_STATUS_SUCCESS) {
                 var poi = rs.getPoi(0);
                 if (poi) {
                	 map = new BMap.Map("baidumap", { enableMapClick: false }); // 创建Map实例
                     map.centerAndZoom(new BMap.Point(poi.point.lng, poi.point.lat), 13);
                     //createMap(poi.point.lng, poi.point.lat);//创建地图(经度poi.point.lng,纬度poi.point.lat)
                     map.enableScrollWheelZoom();
                     map.addControl(new BMap.NavigationControl());
                     map.addControl(new BMap.MapTypeControl({ mapTypes: [BMAP_NORMAL_MAP, BMAP_SATELLITE_MAP] }));
                     map.disableContinuousZoom();
                     window.map = map; //将map变量存储在全局
                 }
             }
         });
         ls.search(area);
         map.addEventListener("click", getXy);
         map.addEventListener("click", addMarker);
         map.addEventListener("click", function () {
                //addMarker(); //向地图中添加marker

                if (confirm("当前坐标为" + accountposition + "是否确认选择此坐标点？")) {
                    $('#applyAddress').val(accountposition);
                    $('#applyAddrLng').val(lng);
    				$('#applyAddrLat').val(lat);
                }
                else {
                    //returnParent("0,0");
                }
              });
         
     }
    
    //创建marker
    function addMarker() {
        map.clearOverlays();
        var p0 = lng;
        var p1 = lat;
        var point = new BMap.Point(p0, p1);
        var marker = new BMap.Marker(point);
        map.addOverlay(marker);

    }

    function getXy(e) {
        lng = e.point.lng;
        lat = e.point.lat;
        accountposition = lng + "," + lat;
    }   
   
</script>