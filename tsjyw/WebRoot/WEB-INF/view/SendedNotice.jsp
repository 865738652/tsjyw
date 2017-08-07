<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="en">
<head>
    
    <title>已发通知</title>
    <%@include file="head.jsp" %>

	<script type="text/javascript">
		$(document).ready(function(){
			loadMySendedNotice();
		});
		
		function deleteNotice(noticeId)
		{
			if(!confirm("是否删除？"))
				return;
			$.ajax({
				url:"../deleteNotice",
				type:"get",
				data:{"noticeId":noticeId},
				dataType:"json",
				success:function(data){
					if(data.code == "succ")
					{
						alert("删除成功");
						loadMySendedNotice();
					}
					else if(data.code == "fail")
					{
						alert("删除失败");
					}	
				},
				error:function(){
					alert("网络错误");
				}
			});
		}
		
		function queryParams(params){//配置参数
			var temp;
			temp={  //这里的键的名字和控制器的变量名必须一致
				pageSize:params.pageSize,  //页面大小
				pageNumber:params.pageNumber//页码
				};
			<c:if test="${not empty _csrf}">
				temp.${_csrf.parameterName} = "${_csrf.token}";
			</c:if>
			
			return temp;
		}


		function loadMySendedNotice()
		{
			var url = "getAllSendedNotice";
			$("#tableSendedNotice").bootstrapTable({
				url:url,
				method:"post",
		    	contentType: "application/x-www-form-urlencoded",
		    	queryParams: queryParams,
		    	queryParamsType:"undefined",//参数格式,发送标准的restful类型的参数请求
		    	striped: true,     //使表格带有条纹  
				pagination: true, //在表格底部显示分页工具栏 
		    	dataType: "json",
			    search: false, //是否显示右上角的搜索框  
			    sidePagination: "server", 
			    //showHeader: false,  隐藏表头
			    idField: "noticeId",  //标识哪个字段为id主键  
			    pageSize: 10,  //每一页 的 数量
				pageNumber:1,      //当前页码
				showRefresh:false,  //显示刷新按钮
				singleSelect:true,  //复选框只能选择一条记录
				clickToSelect:true,  //点击行即可选中单选/复选框
				columns:[
				 	{
				 		field:'noticeTitle',
				 		title:'通知标题',
				 		width:'200',
				 		align:'center',
				 		valign:'maddle',
				 		sortable:true,
				 	},
				 	{
				 		field:'noticeTime',
				 		title:'发送时间',
				 		width:100,
				 		align:'center',
				 		valigh:'middle',
				 		formatter:function(value,row,index){
						 	return getFormatDate(new Date(value))
						}
				 	},
				 	{
				 		field:'noticeOverTime',
				 		title:'是否过期',
				 		width:50,
				 		align:'center',
				 		valigh:'middle',
				 		formatter:function(value,row,index){
						 	return new Date(value).valueOf()<new Date().valueOf()?"是":"否";
						}
				 	},
				 	{
				 		field: 'noticeIsOrNotReply', 
		                title: '须回执',		 
		                width: 50,	 
		                align: 'center',		 
		                valign: 'middle',
				 		formatter:function(value,row,index){
		                	return value == 0 ?"否":"是";
		                }	
				 	},
				 	{
				 		field:'noticeAcceptTypeName',
				 		title:'类别',
				 		width:50,
				 		align:'center',
				 		valigh:'middle'
				 	},
				 	{		 	
				 		title:'操作',
				 		field:'noticeId',
				 		width:50,
				 		formatter:function(value,row,index){
				 			var a = '<a class="btn btn-info" href="../LookNotice/'+value+'">';
							var b = '<i class="glyphicon glyphicon-zoom-in icon-white"></i>查看</a> ';
							var c = '<a class="btn btn-info" href="javascript:void(0);" onclick="deleteNotice('+value+')">';
							var d = '<i class="glyplicon glyphicon-zoom-in icon-white"></i>删除</a>';
									                        	 	
		                    return a+b+c+d;
				 		}
				 	}
				 ],
				 onLoadSuccess: function (data) {
				 }
			});
		}
		

		
	</script>    
    

</head>

<body>
<%@include file="top.jsp"%>
    <div class="ch-container">
        <div class="row">
            <%@include file="left.jsp" %>

        <div id="content" class="col-lg-10 col-sm-10">
            <!-- content starts -->
                <div>
        <ul class="breadcrumb">
            <li>
                <a href="#">我的通知</a>
            </li>
            <li>
                <a href="#">已发的通知</a>
            </li>
        </ul>
    </div>

    <div class="row">
    <div class="box col-md-12">
    <div class="row">
        <div class="box col-md-12">
            <div class="box-inner">
                <div class="box-header well" data-original-title="">
                    <h2>已发的通知</h2>

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
                    <table class="table table-striped table-bordered table-hover" id="tableSendedNotice"></table>
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

    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
         aria-hidden="true">

        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">×</button>
                    <h3>Settings</h3>
                </div>
                <div class="modal-body">
                    <p>Here settings can be configured...</p>
                </div>
                <div class="modal-footer">
                    <a href="#" class="btn btn-default" data-dismiss="modal">Close</a>
                    <a href="#" class="btn btn-primary" data-dismiss="modal">Save changes</a>
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
</div>
</body>
</html>