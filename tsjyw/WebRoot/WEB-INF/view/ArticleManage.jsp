<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="en">
<head>
    
    <title>文章管理</title>
    <%@include file="head.jsp" %>
    <script type="text/javascript">
    	var schoolArticle = <c:out value="${school}" />;
		var schoolid=<c:out value="${schoolid}"/>;
		$(function(){
			
			$.ajaxSetup({
				cache:false  //关闭AJAX缓存
			});
			
			if (schoolArticle && schoolid < 0) {
    			selectSchool(null, null, null, null, function(school) {
    				schoolid = school.schoolId;
    				loadArticle();
    			});	
    		}
    		else
				loadArticle();
		});
		
		function queryParams(params){//配置参数
			var temp;
			if(schoolid >= 0)
				temp={  //这里的键的名字和控制器的变量名必须一致
					pageSize:params.pageSize,  //页面大小
					pageNumber:params.pageNumber,//页码
					schoolId:schoolid,
					recommend:false
				};
			else
				temp={  //这里的键的名字和控制器的变量名必须一致
					pageSize:params.pageSize,  //页面大小
					pageNumber:params.pageNumber,//页码
					recommend:false
				};
			<c:if test="${not empty _csrf}">
				temp.${_csrf.parameterName} = "${_csrf.token}";
			</c:if>
			extendTalbeParams(temp, "searchForm");
			return temp;
		}
		
		function loadArticle()
		{
			var url = "getAllArticle";
			if (schoolid >= 0) {
   				url = "getArticleBySchool";	
   				bindSelect("moduleId", "../ModuleManage/getSchoolTopModule", {schoolId: schoolid}, "moduleId", "moduleName", "", "", "全部");
   			}
   			else {
   				bindSelect("moduleId", "../ModuleManage/getTopModule", null, "moduleId", "moduleName", "", "", "全部");
   			}
			$("#TableArticle").bootstrapTable({
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
			    idField: "SchoolId",  //标识哪个字段为id主键  
			    pageSize: 10,  //每一页 的 数量
				pageNumber:1,      //当前页码
				showRefresh:false,  //显示刷新按钮
				singleSelect:true,  //复选框只能选择一条记录
				clickToSelect:true,  //点击行即可选中单选/复选框
				columns:[
				 	/*{
				 		field:'articleNumber',
				 		title:'文章编号',
				 		width:'100',
				 		align:'center',
				 		valign:'maddle',
				 		sortable:true
				 	},*/
				 	{
				 		field:'articleTitle',
				 		title:'文章标题',
				 		width:550,
				 		align:'center',
				 		valigh:'middle'
				 	},
				 	{
				 		field:'moduleName',
				 		title:'所属栏目',
				 		width:250,
				 		align:'center',
				 		valigh:'middle'
				 	},
				 	/*{
				 		field:'articleTime',
				 		title:'发布时间',
				 		width:200,
				 		align:'center',
				 		valigh:'middle',
				 		formatter:function(value,row,index){
				 			return getFormatDate(new Date(value), "yyyy-MM-dd hh:mm:ss")
				 		}
				 	},
				 	{
				 		field:'articleStateName',
				 		title:'状态',
				 		width:80,
				 		align:'center',
				 		valigh:'middle'
				 	},
				 	{
				 		field:'articleRecommend',
				 		title:'推荐',
				 		width:60,
				 		align:'center',
				 		valigh:'middle',
				 		formatter:function(value,row,index){
				 			return value ? "是":"否";
				 		}
				 	},				 	
				 	{
				 		field:'articleReadCount',
				 		title:'阅读次数',
				 		width:100,
				 		align:'center',
				 		valigh:'middle'
				 	},*/
				 	{		 	
				 		title:'操作',
				 		field:'articleId',
				 		formatter:function(value,row,index){
				 			var a = '<a class="btn btn-info" href="../Index/ShowArticle?articleId=' + value + '">';
   							var b = '<i class="glyphicon glyphicon-zoom-in icon-white"></i>预览</a> ';		                        
	                   		var c = '<a class="btn btn-info" href="Article?schoolid=' + schoolid + '&articleid=' + value + '">'; 
	                   		var d = '<i class="glyphicon glyphicon-edit icon-white"></i>修改</a> ';	                 		
	                   		var e = '<a class="btn btn-danger" href="javascript:void(0);" onclick=\"deleteArticle('+value+')\" >';
	                   		var f = '<i class="glyphicon glyphicon-remove icon-white"></i>删除</a> '; 
	                        return a+b+c+d+e+f; 
				 		}
				 	}
				 ],
				 onLoadSuccess: function (data) {
				 }
			});
		}
		
		function addArticle() {
			window.location.href = 'Article?schoolid=' + schoolid;
		}
		
		function deleteArticle(articleId) {
			if (!confirm("确认删除文章吗？"))
   				return;
   			$.ajax({
				url:"deleteArticle",			
				data:{"articleId":articleId},
				dataType:"json",
				success:function(data) {  
					if (data.code != "succ") {
   						alert(data.data);
   					}
      				$("#TableArticle").bootstrapTable('refresh');
  				},
			    error: function(XMLHttpRequest, textStatus, errorThrown) {
					alert("网络错误: " + XMLHttpRequest.status + ", " + textStatus);
			    }
			});
   		}

		function searchFunc() {
			$("#TableArticle").bootstrapTable('refresh');
		}

		function clearFunc() {
			$('#searchForm')[0].reset();
			$("#TableArticle").bootstrapTable('refresh');
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
            <a href="#">内容管理</a>
        </li>
        <li>
            <a href="#">文章管理</a>
        </li>
    </ul>
</div>

<div class="box-content">                
      <a href='#' class="btn btn-info " onclick='addArticle()'>添加文章</a>              
 </div>

 <div class="box col-md-12">
            <div class="box-inner">
                <div class="box-header well" data-original-title="">
                    <div class="box-icon">
                        <a href="#" class="btn btn-minimize btn-round btn-default"><i
                                class="glyphicon glyphicon-chevron-up"></i></a>
                        <a href="#" class="btn btn-close btn-round btn-default"><i
                                class="glyphicon glyphicon-remove"></i></a>
                    </div>
                </div>
                <div class="box-content">
					<div class="alert alert-info" id="search">
						<form class="form-inline" role="form" id='searchForm'>
						<label class="control-label">标题</label>
						<input type="text" class="form-control" name="[Like]articleTitle" id="[Like]articleTitle">
						<label class="control-label">栏目</label>
						<select class="form-control" id="moduleId" name="[Equal]moduleId"></select>
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
						<a class="btn btn-success" onclick="searchFunc()" ><i class="glyphicon glyphicon-search icon-white"></i>查询</a> 
						<a class="btn btn-danger" onclick="clearFunc()" ><i class="glyphicon glyphicon-remove icon-white"></i>清除</a> 
						</form>
					</div>
                    <table id="TableArticle" class="table table-striped table-bordered responsive"></table>
                </div>
            </div>
</div>
</div>
</div>
</div>
</body>
</html>