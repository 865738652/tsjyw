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
    
    <title>栏目管理</title>
    <%@include file="head.jsp" %>
    
    <script type="text/javascript"> 
    	var parentId = 0;
    	var action = "create";
    	var schoolModule = <c:out value="${school}" />;
    	var schoolid = <c:out value="${schoolid}"/>;   
    	$(function(){
    	
    		$.ajaxSetup ({
         		cache: false //关闭AJAX缓存
     		});
    		
    		if (schoolModule && schoolid < 0) {
    			selectSchool(null, null, null, null, function(school) {
    				schoolid = school.schoolId;
    				loadModule();
    			});	
    		}
    		else
    			loadModule();
    		bindSelect("moduleStateId", "getModuleState", null, "moduleStateId", "moduleStateName");
    		bindSelect("moduleTypeId", "getModuleType", null, "moduleTypeId", "moduleTypeName");    		
    	});
    	
    	function queryParams(params) {  //配置参数
	        var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
	            pageSize: params.pageSize,   //页面大小
	            pageNumber: params.pageNumber,  //页码
	        };
	        if (schoolModule)
	        	temp.schoolId = schoolid;
	        <c:if test="${not empty _csrf}">
				temp.${_csrf.parameterName} = "${_csrf.token}";
			</c:if>
			extendTalbeParams(temp, "searchForm");
        	return temp;
    	}
    	
    	function childQueryParams(params) {  //配置参数
	        var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
	            pageSize: params.pageSize,   //页面大小
	            pageNumber: params.pageNumber,  //页码
	            parentId: parentId
	        }
        	return temp;
    	}
    	
    	function loadModule()
    	{
    		var url = schoolModule ? "getSchoolTopModule" : "getTopModule";
 
    		$("#TableModule").bootstrapTable({
		    	url: url, 
		    	method:"post",
		    	detailView: true,
		    	contentType: "application/x-www-form-urlencoded;charset=utf-8",
		    	queryParams: queryParams,
		    	queryParamsType:"undefined",//参数格式,发送标准的restful类型的参数请求
		    	striped: true,     //使表格带有条纹  
		    	dataType: "json",
			    pagination: false,
			    search: false, //是否显示右上角的搜索框  
			    sidePagination: "server", 
			    //showHeader: false,  隐藏表头
			    idField: "moduleId",  //标识哪个字段为id主键  
			    //pageSize: 10,  //每一页 的 数量
				//pageNumber:1,     //当前页码
				//pageList: [3,6,9],  
				//idField: "ProductId",  //标识哪个字段为id主键  
				//showToggle: false,   //名片格式  
				//cardView: false,//设置为True时显示名片（card）布局  
				//showColumns: true, //显示隐藏列    
				showRefresh: false,  //显示刷新按钮  
				singleSelect: true,//复选框只能选择一条记录  
				clickToSelect: true,//点击行即可选中单选/复选框  
				//toolbar: "#toolbar", //设置工具栏的Id或者class  
				//silent: true,  //刷新事件 
				columns: [
				        {
				        	field: 'moduleName', 
		                    title: '栏目名称',		 
		                    width: 250,		 
		                    align: 'center',		 
		                    valign: 'middle',		 
		                    sortable: true,
				        },
				        {
				        	field: 'moduleNumber', 
		                    title: '栏目编号',		 
		                    width: 150,		 
		                    align: 'center',		 
		                    valign: 'middle'
				        },
				        {
				        	field: 'moduleSerial', 
		                    title: '序号',		 
		                    width: 50,		 
		                    align: 'center',		 
		                    valign: 'middle'
				        },
				        {
				        	field: 'moduleTypeName', 
		                    title: '类型',		 
		                    width: 100,	 
		                    align: 'center',		 
		                    valign: 'middle'
				        },
				        {
				        	field: 'moduleStateName', 
		                    title: '状态',		 
		                    width: 100,		 
		                    align: 'center',		 
		                    valign: 'middle'
				        },
				        {
				        	field: 'moduleIsShow', 
		                    title: '首页显示',		 
		                    width: 100,		 
		                    align: 'center',		 
		                    valign: 'middle',
		                    formatter:function(value,row,index){
		                    	return value ? "显示":"不显示";
		                    }
				        },
				        {
		                    title: '操作',
		                    field: 'moduleId',
		                    formatter:function(value,row,index){
		                    	var html = '<a class="btn btn-info" data-toggle="modal" data-target="#showModuleDialog" href="javascript:void(0);" onclick=\"showModule('+value+')\">';
   								html += '<i class="glyphicon glyphicon-zoom-in icon-white"></i>详情信息</a> ';		                        
if (row.moduleNumber.indexOf('000') == 0 || 
(row.moduleName != '首页幻灯' && row.moduleName != '图片轮显' && row.moduleName != '校园新闻' &&
row.moduleName != '父母学堂' && row.moduleName != '家教动态' && row.moduleName != '德育天地' &&
row.moduleName != '心理导航' && row.moduleName != '亲子展示')) {
	                   			html += '<a class="btn btn-info" data-toggle="modal" data-target="#moduleDialog" href="javascript:void(0);" onclick=\"modifyModule('+value+')\">'; 
	                   			html += '<i class="glyphicon glyphicon-edit icon-white"></i>修改</a> ';
	                   			html += '<a class="btn btn-danger" href="javascript:void(0);" onclick=\"deleteModule('+value+')\" >';
	                   			html += '<i class="glyphicon glyphicon-remove icon-white"></i>删除</a> '; 
}   			
	                        	return html;  
		                  	}
                  		}				        
				],
				onLoadSuccess: function (data) {
					
				},
				onExpandRow: function (index, row, $detail) {
					loadChildModule(index, row, $detail);
      			}
		    });
    	}
    	
    	function loadChildModule(index, row, $detail) {
    		parentId = row.moduleId;
    		var cur_table = $detail.html('<table></table>').find('table');
    		$(cur_table).bootstrapTable({
    			url: 'getChildModule',
    			method: 'get',
    			contentType: "application/x-www-form-urlencoded",
    			queryParams: childQueryParams,//{ "parentId": parentid },
    			queryParamsType:"undefined",
				clickToSelect: true,  
				pagination: true,
				sidePagination: "server", 
				dataType:"json", 
				idField: "moduleId",
				uniqueId: "moduleId",
    			columns: [
    				{
			        	field: 'moduleName', 
	                    title: '栏目名称',		 
	                    width: 100,		 
	                    align: 'center',		 
	                    valign: 'middle',		 
	                    sortable: true,
			        },
			        {
			        	field: 'moduleNumber', 
	                    title: '栏目编号',		 
	                    width: 100,		 
	                    align: 'center',		 
	                    valign: 'middle'
			        },
			        {
			        	field: 'moduleSerial', 
	                    title: '序号',		 
	                    width: 100,		 
	                    align: 'center',		 
	                    valign: 'middle'
			        },
			        {
			        	field: 'moduleTypeName', 
	                    title: '类型',		 
	                    width: 50,	 
	                    align: 'center',		 
	                    valign: 'middle'
			        },
			        {
			        	field: 'moduleStateName', 
	                    title: '状态',		 
	                    width: 100,		 
	                    align: 'center',		 
	                    valign: 'middle'
			        },
			        {
	                    title: '操作',
	                    field: 'moduleId',
	                    formatter:function(value,row1,index1){
	                    	var a = '<a class="btn btn-info" data-toggle="modal" data-target="#showModuleDialog" href="javascript:void(0);" onclick=\"showModule('+value+')\">';
  								var b = '<i class="glyphicon glyphicon-zoom-in icon-white"></i>详情信息</a> ';		                        
                   			var c = '<a class="btn btn-info" data-toggle="modal" data-target="#moduleDialog" href="javascript:void(0);" onclick=\"modifyModule('+value+')\">'; 
                   			var d = '<i class="glyphicon glyphicon-edit icon-white"></i>修改</a> ';
                   			var e = '<a class="btn btn-danger" href="javascript:void(0);" onclick=\"deleteModule('+value+')\" >';
                   			var f = '<i class="glyphicon glyphicon-remove icon-white"></i>删除</a> ';    			
                        	return a+b+c+d+e+f;  
	                  	}
                 	}	
    			],
				onLoadSuccess: function (data) {
				}
 			});
    	}
    	
		function showModule(moduleId)
   		{
   			$.ajax({
				url:"getModule",			
				data:{"moduleId":moduleId},
				dataType:"json",
				success:function(data) {  
					if (data.code != "succ") {
						alert(data.data);
						$('#showModuleDialog').modal('hide');
						return;
					}
					
			        $("#moduleNumberDetail").text(data.data.moduleNumber);
			        $("#moduleNameDetail").text(data.data.moduleName);
			        $("#moduleSerialDetail").text(data.data.moduleSerial);
			        $("#moduleIsShowDetail").text(data.data.moduleIsShow?"是":"否");
			        $("#moduleStateNameDetail").text(data.data.moduleStateName);
			        $("#moduleTypeNameDetail").text(data.data.moduleTypeName);
					$("#moduleEnNameDetail").text(data.data.moduleEnName);
					$("#moduleUrlDetail").text(data.data.moduleUrl);
			    },
			    error: function(XMLHttpRequest, textStatus, errorThrown) {
					alert("网络错误: " + XMLHttpRequest.status + ", " + textStatus);
			    }
			});	   			
   		}
   		
   		$("#moduleDialog").on("hidden.bs.model",function(e){$(this).removeData();}); 
   		
   		function createModule() {
   			action = "create";
   			$("#title").html("添加新栏目");   		
   			onModuleShow();   	
   			if (schoolModule && schoolid >= 0)
   				$("#schoolId").val(schoolid);		
   		}
   		
   		function modifyModule(moduleId) {
   			action = "modify";
   			$("#title").html("修改栏目");   	
   			onModuleShow();
   			
			$.ajax({
				url:"getModule",			
				data:{"moduleId":moduleId},
				dataType:"json",
				success:function(data) {  
			        if (data.code != "succ") {
   						alert(data.data);
   						$('#moduleDialog').modal('hide');
   						return;
   					}
  			        
			        $("#moduleId").val(data.data.moduleId);
			        $("#moduleNumber").val(data.data.moduleNumber);
			        $("#moduleName").val(data.data.moduleName);
			        $("#moduleEnName").val(data.data.moduleEnName);
					$("moduleUrl").val(data.data.moduleUrl);
			        $("#moduleSerial").val(data.data.moduleSerial);			        
			        if(data.data.moduleIsShow) {
			        	$("#showModule").attr("checked","checked"); 
			        }
			        else {
			        	$("#hideModule").attr("checked","checked");
			        }
			        $("#moduleStateId").val(data.data.moduleStateId);
			        $("#moduleTypeId").val(data.data.moduleTypeId);
			        $("#parentModuleId").val(data.data.parentModuleId);
			        if (schoolModule)
			        	$("#schoolId").val(data.data.schoolId);
			    },
			    error: function(XMLHttpRequest, textStatus, errorThrown) {
					alert("网络错误: " + XMLHttpRequest.status + ", " + textStatus);
			    }
			});	   						
   		}
   		
   		function onModuleShow() {
   			$('#moduleForm')[0].reset();
   			$("#moduleId").val("");
   			$("#schoolId").val("");
   			
   			if (schoolModule) {
   				bindSelect("parentModuleId", "getSchoolTopModule", {"schoolId":schoolid}, "moduleId", "moduleName", null, "", "无");
   			}
   			else {
   				bindSelect("parentModuleId", "getTopModule", null, "moduleId", "moduleName", null, "", "无");
   			}
   		}
   		
   		function deleteModule(moduleId) {
   			if (!confirm("确认删除栏目吗？"))
   				return;
   				
   			$.ajax({
				url:"deleteModule",			
				data:{"moduleId":moduleId},
				dataType:"json",
				success:function(data) {  
					if (data.code != "succ") {
   						alert(data.data);
   						$('#moduleDialog').modal('hide');
   					}
      				$("#TableModule").bootstrapTable('refresh');
  				},
			    error: function(XMLHttpRequest, textStatus, errorThrown) {
					alert("网络错误: " + XMLHttpRequest.status + ", " + textStatus);
			    }
			});
   		}
   		
   		function onOKButton() {
   			var url = "createModule";
   			if (action == "modify")
   				url = "modifyModule";
   				
   			$.ajax({
	        	url:url,
	        	type:"post",
	        	data:$('#moduleForm').serialize(),
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
	        		$('#moduleDialog').modal('hide');
	        		$("#TableModule").bootstrapTable('refresh');
	        	},
			    error: function(XMLHttpRequest, textStatus, errorThrown) {
					alert("网络错误: " + XMLHttpRequest.status + ", " + textStatus);
			    }
	        });
   		}   
		
		function searchFunc() {
			$("#TableModule").bootstrapTable('refresh');
		}

		function clearFunc() {
			$('#searchForm')[0].reset();
			$("#TableModule").bootstrapTable('refresh');
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
                <a href="#">栏目管理</a>
            </li>
        </ul>
    </div>
    <div class="row">
    <div class="box col-md-12">
    <div class="row">
        <div class="box col-md-12">
            <div class="box-inner">
                <div class="box-header well" data-original-title="">
                    <h2>栏目管理</h2>

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
						<a class="btn btn-success" data-toggle="modal"  data-target="#moduleDialog" onclick="createModule()">
							<i class="glyphicon glyphicon-plus icon-white"></i>添加新栏目 </a>             
					</div>
					<div class="alert alert-info" id="search">
						<form class="form-inline" role="form" id='searchForm'>
						<label class="control-label">名称</label>
						<input type="text" class="form-control" name="[Like]moduleName" id="[Like]moduleName">
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
						<a class="btn btn-success" onclick="searchFunc()" ><i class="glyphicon glyphicon-search icon-white"></i>查询</a> 
						<a class="btn btn-danger" onclick="clearFunc()" ><i class="glyphicon glyphicon-remove icon-white"></i>清除</a> 
						</form>
					</div>
                    <table class="table table-striped table-bordered table-hover" id="TableModule"></table>
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

	 <!-- 查看栏目对话框 -->
	 <div class="modal fade" id="showModuleDialog" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
         aria-hidden="true">

        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">×</button>
                    <h3>查看栏目信息</h3>
                </div>
                <div class="modal-body">
                	<table width="90%">
                		<tr>
                			<td width="20%"><label  class="control-label">编号</label></td>
                			<td width="30%"><label  id="moduleNumberDetail" class="control-label">编号</label></td>
                			<td width="20%"><label  class="control-label">名称</label></td>
                			<td width="30%"><label  id="moduleNameDetail" class="control-label">名称</label></td>
                		</tr>
                		<tr>
                			<td width="20%"><label  class="control-label">序号</label></td>
                			<td width="30%"><label  id="moduleSerialDetail" class="control-label">序号</label></td>
                			<td width="20%"><label  class="control-label">导航显示</label></td>
                			<td width="30%"><label  id="moduleIsShowDetail" class="control-label">首页显示</label></td>
                		</tr>
                		<tr>
                			<td width="20%"><label  class="control-label">状态</label></td> 
                			<td width="30%"><label  id="moduleStateNameDetail" class="control-label">状态</label></td>
                			<td width="20%"><label  class="control-label">类型</label></td>
                			<td width="30%"><label  id="moduleTypeNameDetail" class="control-label">类型</label></td>
                		</tr>
						<tr>
                			<td width="20%"><label  class="control-label">英文名称</label></td>
                			<td width="30%"><label  id="moduleEnNameDetail" class="control-label">英文名称</label></td>
                			<td width="20%"><label  class="control-label">静态链接</label></td>
                			<td width="30%"><label  id="moduleUrlDetail" class="control-label">静态链接</label></td>
                		</tr>
                	</table>
                </div>
                <hr>
                <div class="modal-footer">
                     <a href="#" class="btn btn-primary" data-dismiss="modal">关闭</a>
                </div>
            </div>
        </div>
    </div>
    
    <!--新建和修改栏目信息 -->
   <div class="modal fade" id="moduleDialog" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">

        <div class="modal-dialog"  style="width:800px">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">×</button>
                    <h3><span id='title'>修改栏目信息</span></h3>
                </div>
                <div class="modal-body">
                    <form class="form-inline" role="form" id='moduleForm'>                    	
                    	<div class="form-group has-success has-feedback" id="LookModule2">
                    	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/> 
                    	<input type="hidden" id="moduleId" name="moduleId" />
                    	<input type="hidden" id="schoolId" name="schoolId" />
                        <table  width="680px">
                        	<tr>
                        		<td width="20%"><label class="control-label" for="moduleNumber">编号</label></td>
								<td width="30%"><input type="text" id="moduleNumber" name="moduleNumber" class="form-control"></select></td>
								<td width="20%"><label class="control-label" for="moduleName">名称</label></td>
								<td width="30%"><input type="text" id="moduleName" name="moduleName" class="form-control"></td>								
							</tr>
							<tr>
                        		<td width="20%"><label class="control-label" for="moduleSerial">序号</label></td>
								<td width="30%"><input type="text" id="moduleSerial" name="moduleSerial" class="form-control"></select></td>
								<td width="20%"><label class="control-label" for="moduleSerial">首页显示</label></td>
								<td>
									<label class="radio-inline">
										<input type="radio" name="moduleIsShow" id="showModule" value="true"> 显示&nbsp;&nbsp;&nbsp;
									</label>
									<label class="radio-inline">
										<input type="radio" name="moduleIsShow" id="hideModule" value="false"> 不显示
									</label><br/>
								</td>							
							</tr>
							<tr>
                        		<td width="20%"><label class="control-label" for="moduleStateId">状态</label></td>
								<td width="30%"><select id="moduleStateId" name="moduleStateId" class="form-control"></select></td>
								<td width="20%"><label class="control-label" for="moduleTypeId">类型</label></td>
								<td width="30%"><select id="moduleTypeId" name="moduleTypeId" class="form-control"></td>								
							</tr>
							<tr>
                        		<td width="20%"><label class="control-label" for="parentModuleId">父栏目</label></td>
								<td width="30%"><select id="parentModuleId" name="parentModuleId" class="form-control"></select></td>
								<td width="20%"><label class="control-label" for="moduleName">英文名称</label></td>
								<td width="30%"><input type="text" id="moduleEnName" name="moduleEnName" class="form-control"></td>								
							</tr>
							<tr>
                        		<td><label class="control-label" for="moduleUrl">链接地址</label></td>
								<td colspan="3"><input type="text" id="moduleUrl" name="moduleUrl" class="form-control"></td>
																
							</tr>
						</table>
						</div>
                    </form>                                                                
                </div>
                <!-- body -->
                <hr>
                <div class="modal-footer">
                     <a href="javascript:void(0)" class="btn btn-default " data-dismiss="modal">取消</a>
                     <a href="javascript:void(0)" class="btn btn-primary" id="moduleOKButton" onclick="onOKButton()">确定</a>
                </div>
            </div>
        </div>
    </div> 
    
    <%@include file="selectschool.jsp" %>
	
    <footer class="row">
        <p class="col-md-9 col-sm-9 col-xs-12 copyright">&copy; <a href="http://usman.it" target="_blank">Muhammad
                Usman</a> 2012 - 2015</p>

        <p class="col-md-3 col-sm-3 col-xs-12 powered-by">Powered by: <a
                href="http://usman.it/free-responsive-admin-template">Charisma</a></p>
    </footer>

</div><!--/.fluid-container-->

</body>
</html>