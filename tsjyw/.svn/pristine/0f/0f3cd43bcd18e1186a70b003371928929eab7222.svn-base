<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <meta charset="utf-8">
    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
    <title>我要申请</title>
    <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=974e0cfe9ad49ccdf2d73e0a098b8239"></script>
    <script type="text/javascript" class="resources library" src="../js/area.js" ></script>
    <%@include file="head.jsp" %>
	<script type="text/javascript">
    	var applyId=<c:out value="${applyId}"/>;
    	var action ="<c:out value='${action}'/>";
    	var area;
    	var lng;
		var lat;
		var accountposition;
		var map;  		 	
    	$(function(){
    		$.ajaxSetup ({
         		cache: false //关闭AJAX缓存
     		});  
    		if(action=="create"){
    			createApply();
    			createMap(118.185599, 39.641674);
    			}
    		else{
    			modifyApply(applyId);
    		}   		    		
    		fillAgeLevels();
   			fillAskQuestionTypes(2); 
    	});
    	 
		//创建地图函数：
		function createMap(x, y) {
			map = new BMap.Map("baidumap", { enableMapClick: false }); // 创建Map实例
  		    map.centerAndZoom(new BMap.Point(x,y), 13);
				
  		    map.enableScrollWheelZoom();
  		    map.addControl(new BMap.NavigationControl());
  		    map.addControl(new BMap.MapTypeControl({ mapTypes: [BMAP_NORMAL_MAP, BMAP_SATELLITE_MAP] }));
  		    map.disableContinuousZoom();
  		    window.map = map; //将map变量存储在全局
  		  	if(action=="modify"){
				map.addOverlay(new BMap.Marker(new BMap.Point(x,y)));
			}  
  		  	
 		    map.addEventListener("click", function (e) {
 		    getXy(e);
 		    addMarker(); //向地图中添加marker
 		    $('#applyAddress').val(accountposition);
            $('#applyAddrLng').val(lng);
			$('#applyAddrLat').val(lat); 
	          });  	
			}	
    	
    	//地图搜索
		  function searchMap() {
			    $('#applyAddress').val('');
		    	var sel=document.getElementsByTagName("select");	    	
			    var Province=sel[0].options[sel[0].selectedIndex].value.trim();
			    var City=sel[1].options[sel[1].selectedIndex].value.trim();
			    var County=sel[2].options[sel[2].selectedIndex].value.trim();
			    if(County=="县/区")
			    	area = null;
			    else
			    	{
			    	area = Province+City+County; //得到地区
			    	}	        
		        var ls = new BMap.LocalSearch(map);
		         ls.setSearchCompleteCallback(function(rs) {
		             if (ls.getStatus() == BMAP_STATUS_SUCCESS) {
		                 var poi = rs.getPoi(0);
		                 if (poi) {
		                	 createMap(poi.point.lng, poi.point.lat);//创建地图  				                	 
		                 }
		             }
		         });
		         ls.search(area);
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
    	
	    	function createApply() {   			
	   			$('#applyForm')[0].reset();
	   			$("#applyId").val("");
	   			
	   		}
	    	function modifyApply(applyId) {
	   			$('#applyForm')[0].reset();
	   			$.ajax({
					url:"getApply",			
					data:{"applyId":applyId},
					dataType:"json",
					success:function(data) {  
				        if (data.code != "succ") {
	   						alert(data.data);
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
		 				createMap(data.data.applyAddrLng, data.data.applyAddrLat); 
				    },
				    error: function(XMLHttpRequest, textStatus, errorThrown) {
						alert("网络错误: " + XMLHttpRequest.status + ", " + textStatus);
				    }
				});	   						
	   		}
	   		
   			  		
   		
   		function onOKButton() {
   			var url = "modifyApply";
   			
   			if (action == "create")
   				url = "createApply";   	
			
   			if(area==null){
   				alert("请先选择地区");
   				return;
   			}
   			if(document.getElementById("agreement").checked==false)
   			{
   				alert("不同意协议不能注册");
   				return;
   			}
			if (!confirm("请确认提交志愿者申请"))
				return;

   			$('#applyPCC').val(area);
   			var param = $('#applyForm').serialize();
			
   			param = ageLevelOnSubmit(param);
   			param = askQuestionTypeOnSubmit(param);
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
						alert("提交申请成功，请等待管理员审核");
					else
						alert("修改申请成功");
	        		window.location.href = "ShowMyApply";
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
            <form class="form-inline" role="form" id="applyForm">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/> 
            <div id="content" class="col-lg-10 col-sm-10">
                <div>
			        <ul class="breadcrumb">
			            <li>
			                <a href="#">管理中心</a>
			            </li>
			            <li>
			                <a href="#">志愿者申请</a>
			            </li>
			            <li>
			            	<a href="#">我要申请</a>
			            </li>
			        </ul>
    			</div>
				<div class="box-content">					
                	<div id="myTabContent" class="tab-content">
	                    <div class="tab-pane active" id="info">
	                    	<input type="hidden" id="applyId" name="applyId" />
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
									<td width="20%"><label class="control-label" for="applyIntro">选择您所在的地区</label></td>
									<td>
										<select id="volunteerProvince" name="volunteerProvince"></select>  
									    <select id="volunteerCity" name="volunteerCity" ></select>  
									    <select id="volunteerCounty" name="volunteerCounty"></select>
									    <input  type="button" onclick="searchMap();" value="在地图上定位您的城市" />
									    <script type="text/javascript">_init_area();</script>									    
									</td>
								</tr>   
	                        	<tr>
									<td colspan="2" >
										<label class="control-label" for="applyIntro">请在下面的地图上点击您的具体位置</label>
										<input type="hidden"  id="applyAddress" class="form-control" name="applyAddress" style="width:300px;overflow:hidden;" >
									</td>		
								</tr>         													
								<tr>
									<td colspan="2">
										<div id="baidumap" style="width:700px;height:300px;overflow:hidden;"></div>
									</td>
								</tr>
								<tr>
									<td colspan="2" style="te"><label class="control-label" for="applyIntro">请填写个人简介</label></td>	
								</tr>
								<tr>
									<td colspan="2"><textarea id="applyIntro" class="form-control" name="applyIntro" style="width:700px;height:320px;rows="8"  list="2" placeholder="介绍" required></textarea></td>								
								</tr>
								<tr>
									<td colspan="2" style="text-align:center;color:red;">
									
									<div class="checkbox">
					                    <label>
					                        <input type="checkbox" value="" id="agreement">
					                                                                                我已阅读志愿者申请协议
					                           <a class="btn btn-info btn-setting" href="#"><i class="glyphicon glyphicon-info-sign"></i> 阅读协议</a>                                                     
					                                                                                
					                    </label>
					                </div>
					                </td>
								</tr>		
							</table>
						</div>
	             		<input type="hidden"  id="applyId" name="applyId"/>
						<input type="hidden"  id="applyAddrLng" name="applyAddrLng"/>
						<input type="hidden"  id="applyAddrLat" name="applyAddrLat"/> 
						<input type="hidden"  id="applyPCC" name="applyPCC"/>                
                    </div>
                </div>			
			</div>
			
			<div class="modal-footer">
				<a href="#" class="btn btn-default" data-dismiss="modal">取消</a>
				<a href="#" class="btn btn-primary" data-dismiss="modal" onclick="onOKButton()">提交申请</a>
			</div>
			</form>
		</div>
		
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
         aria-hidden="true">

        <div class="modal-dialog">
            <div class="modal-content">
               <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">×</button>
                    <h3> 志愿者注册和使用注意事项：</h3>
                </div>
                <div class="modal-body">
                   
                    
					<p>1、	志愿者请如实填写个人信息，我们保证不对外泄露您的个人资料。</p>
					<p>	2、	志愿者需要具备相关学科和领域的专业知识，保证对学生或家长实施的辅导内容的科学性、专业性负责。志愿者服务时请使用礼貌用语，保障沟通的良性运行。</p>
					<p>	3、	我们将根据用户评价情况，酌情对志愿者的服务给予加减积分处理。志愿者所获积分，可在网站和微信号消费或在积分商城兑换丰厚奖品。</p>
					<p>	4、	网站随时对志愿者的解答、辅导和用户评价进行跟踪整理，如出现恶意解答、不满意总数达到20次（恶意评价除外）或连续五次（恶意评价除外）出现不满意的评价，网站将取消志愿者的志愿者资格，并做封号处理。恶意评价我们将根据交流内容（请志愿者保留即时通讯内容）作出合理评判。
                     </p>
                </div>
                <div class="modal-footer">
                    <a href="#" class="btn btn-default" data-dismiss="modal">关闭</a>
                   <!--  <a href="#" class="btn btn-primary" data-dismiss="modal">Save changes</a> -->
                </div>
            </div>
        </div>
    </div>
	</div>
</body>
</html>
