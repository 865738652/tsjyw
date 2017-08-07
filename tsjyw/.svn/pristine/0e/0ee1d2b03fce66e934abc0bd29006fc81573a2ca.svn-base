<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="en">
<head>
    
    <title>在线问答</title>
    <%@include file="head.jsp" %>
    <script type="text/javascript">
		var questionType='<c:out value="${questionType}"/>';
		var isAdmin = false;
		<c:if test="${not empty isAdmin}">
			isAdmin = <c:out value="${isAdmin}"/>
		</c:if>
		$(function(){
			
			$.ajaxSetup({
				cache:false  //关闭AJAX缓存
			});
			loadAskQuestion();
			bindSelect("askQuestionTypeId", "getAskQuestionType", null, "askQuestionTypeId", "askQuestionTypeName", "", "", "全部");
		});

		
		
		function queryParams(params){//配置参数
			temp={  //这里的键的名字和控制器的变量名必须一致
				pageSize:params.pageSize,  //页面大小
				pageNumber:params.pageNumber,//页码
				questionType:'<c:out value="${questionType}"/>'
			};
			<c:if test="${not empty _csrf}">
				temp.${_csrf.parameterName} = "${_csrf.token}";
			</c:if>
			extendTalbeParams(temp, "searchForm");
			return temp;
		}
		function loadAskQuestion()
		{
			var url = "getAllAskQuestion";
			$("#TableAskQuestion").bootstrapTable({
				url:url,
				method:"post",
		    	contentType: "application/x-www-form-urlencoded;charset=utf-8",
		    	queryParams: queryParams,
		    	queryParamsType:"undefined",//参数格式,发送标准的restful类型的参数请求
		    	striped: true,     //使表格带有条纹  
  				pagination: true, //在表格底部显示分页工具栏 
		    	dataType: "json",
			    pagination: true,
			    search: false, //是否显示右上角的搜索框  
			    sidePagination: "server", 
			    //showHeader: false,  隐藏表头
			    idField: "askQuestionId",  //标识哪个字段为id主键  
			    pageSize: 10,  //每一页 的 数量
				pageNumber:1,      //当前页码
				showRefresh:false,  //显示刷新按钮
				//singleSelect:true,  //复选框只能选择一条记录
				clickToSelect:true,  //点击行即可选中单选/复选框
				columns:[
					{
						field:'askQuestionSelect',
						width:'100',
						title:'精选问题',
						checkbox:true,
						formatter:selectFormatter
					},
				 	{
				 		field:'askQuestionTitle',
				 		title:'标题',
				 		width:'300',
				 		align:'center',
				 		valign:'maddle',
				 		sortable:true
				 	},
				 	{
				 		field:'askQuestionTime',
				 		title:'提问时间',
				 		width:200,
				 		align:'center',
				 		valigh:'middle',
				 		formatter:function(value,row,index){
				 			return getFormatDate(new Date(value), "yyyy-MM-dd hh:mm:ss")
				 		}
				 	},
				 	{
				 		field:'askName',
				 		title:'提问人',
				 		width:100,
				 		align:'center',
				 		valigh:'middle'
				 	},
				 	{
				 		field:'askQuestionTypeName',
				 		title:'问题类型',
				 		width:100,
				 		align:'center',
				 		valigh:'middle'
				 	},
				 	{
				 		field:'respCount',
				 		title:'回答数量',
				 		width:100,
				 		align:'center',
				 		valigh:'middle'
				 	},
				 	{		 	
				 		title:'操作',
				 		field:'askQuestionId',
				 		formatter:function(value,row,index){
				 			var html = '<a class="btn btn-info" href="ShowQuestion?askQuestionId=' + value + '">'; 
	                   		html += '<i class="glyphicon glyphicon-zoom-in icon-white"></i>阅读</a> ';
	                   		if (isAdmin) {
		                   		html += '<a class="btn btn-danger" href="#" onclick="deleteQuestion(' + value + ')">'; 
		                   		html += '<i class="glyphicon glyphicon-remove icon-white"></i>删除</a> ';	
	                   		}    
	                   		return html; 
				 		}
				 	}
				 ]
			});
		}
		
		
		function selectFormatter(value,row,index)
		{
			if(value == "1" || value == 1)
			{
				if(!isAdmin){
					return{
						disabled:true,
						checked:true,
					}
				}
				else{
					return{
						//disabled:true,
						checked:true,
					}
				}
			}
			else if(value == "0" || value == 0)
			{
				if(!isAdmin){
					return{
						disabled:true,
						checked:false,
					}
				}
				else{
					return{
						//disabled:true,
						checked:false,
					}
				}
			}
		}
		
		function deleteQuestion(id) {
			if (!confirm("确认要删除提问吗？"))
				return;
				
			$.ajax({
	        	url:"deleteAskQuestion",
	        	type:"get",
	        	data: {askQuestionId: id},
	        	dataType:"json",
	        	success:function(data){
	        		if (data.code != "succ") {
						alert(data.data);
						return;
					}
					alert("删除成功");
	        		$("#TableAskQuestion").bootstrapTable('refresh');
	        	},
			    error: function(XMLHttpRequest, textStatus, errorThrown) {
					alert("网络错误: " + XMLHttpRequest.status + ", " + textStatus);
			    }
	        });
		}

		function searchFunc() {
			$("#TableAskQuestion").bootstrapTable('refresh');
		}

		function clearFunc() {
			$('#searchForm')[0].reset();
			$("#TableAskQuestion").bootstrapTable('refresh');
		}
		
		function SetSelectAskQuestion()
		{
			obj = document.getElementsByName("btSelectItem");
		    askquestionIds = [];
		    for(k in obj){
		        if(obj[k].checked)
		            askquestionIds.push(obj[k].value);
		    }
		    $.ajax({
		    	url:"beSelectAskQuestion",
		    	type:"post",
		    	dataType:"json",
		    	data:{"askquestionIds":JSON.stringify(askquestionIds),"${_csrf.parameterName}":"${_csrf.token}"},
		    	success:function(data){
		    		if(data.code == "succ")
		    		{
		    			alert("设置成功!");
		    			loadAskQuestion();
		    		}
		    		else
		    			alert("设置失败！");
		    	},
		    	error:function(data){
		    		alert("设置失败!");
		    	}
		    });
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
		            <li><a href="#">管理中心</a></li>
       				<li><a href="#">在线问答</a></li>
		        </ul>
		    </div> 
		    <div class="row">
		        <div class="box col-md-12">
		            <div class="box-inner">
		                <div class="box-header well" data-original-title="">
		                    <h2>用户</h2>
					
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
			               		<a class="btn btn-success" href='AskQuestion'>
									<i class="glyphicon glyphicon-plus icon-white"></i>我要提问
							 	</a>
							 	<c:if test="${isAdmin == true}">
							 	<a class="btn btn-success" href='javascript:void(0);' onclick="SetSelectAskQuestion()">
									<i class="glyphicon glyphicon-plus icon-white"></i>设置为精选问题
							 	</a>  
							 	</c:if>      
			                </div>
							<div class="box-content" id="search">
								<form class="form-inline" role="form" id='searchForm'>
								<label class="control-label">问题分类 </label>
								<select class="form-control" id="askQuestionTypeId" name="[Equal]askQuestionTypeId"></select>
								<label class="control-label">标题 </label>
								<input type="text" class="form-control" name="[Like]askQuestionTitle" id="[Like]askQuestionTitle">
								<label class="control-label">内容 </label>
								<input type="text" class="form-control" name="[Like]askQuestionContent" id="[Like]askQuestionContent">
								<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
								<a class="btn btn-success" onclick="searchFunc()" ><i class="glyphicon glyphicon-search icon-white"></i>查询</a> 
								<a class="btn btn-danger" onclick="clearFunc()" ><i class="glyphicon glyphicon-remove icon-white"></i>清除</a> 
								</form>
							</div>
		                    <table id="TableAskQuestion" class="table table-striped table-bordered bootstrap-datatable responsive"></table>
		                </div>
		            </div>
		        </div>
			</div>
		    </div>
		  </div><!--/span-->
	</div> 
</body>
</html>