<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="en">
<head>
   
    <title>已选课程</title>
    <%@ include file="head.jsp" %>
</head>

<body>
    <%@ include file="top.jsp" %>

    <div class="ch-container">
        <div class="row">
            <%@ include file="left.jsp" %>
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
                <a href="#">网络课程</a>
            </li>
            <li>
                <a href="#">已选课程</a>
            </li>
        </ul>
    	</div>

    <!-- ******************************已选课程*****************************-->
      <div class="row">
        <div class="box col-md-12">
            <div class="box-inner">
                <div class="box-header well" data-original-title="">
                    <h2>&nbsp;已选课程</h2>

                    <div class="box-icon">
						 <a href="#" class="btn btn-setting btn-round btn-default"><i class="glyphicon glyphicon-cog"></i></a>
                        <a href="#" class="btn btn-minimize btn-round btn-default"><i
                                class="glyphicon glyphicon-chevron-up"></i></a>
                        <a href="#" class="btn btn-close btn-round btn-default"><i
                                class="glyphicon glyphicon-remove"></i></a>
                    </div>

                </div>
                
                <div class="box-content">	
                    <table class="table table-striped table-bordered responsive">
					<div id="text2" class="text2">
						<label>
							&nbsp;&nbsp;课程名称:
							<input type="text" aria-controls="DataTables_Table_0">
						</label>
						<label>
							&nbsp;&nbsp;主讲教师:
							<input type="text" aria-controls="DataTables_Table_0">&nbsp;&nbsp;
						</label>
						<button class="btn btn-success btn-sm" href="#">
						      <i class="glyphicon glyphicon-search icon-white"></i>&nbsp;搜索&nbsp;</button>
                    </div>
                    <br/>
                        <thead>
                        <tr>
                            <th>课程代码</th>
                            <th>课程名称</th>
                            <th>主讲教师</th>
                            <th>收费方式</th>
                            <th>授课日期</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>003</td>
                            <td class="center">小学数学</td>
                            <td class="center">王五</td>
                            <td class="center">在线</td>
                            <!--出生年月+操作的表单-->
                            <td class="center">2012/01/01</td>
                            <td class="center">
                                <a class="btn btn-success" href="#">
                                    <i class="glyphicon glyphicon-zoom-in icon-white"></i>
                                    查看
                                </a>
                                <a class="btn btn-info" href="#">
                                    <i class="glyphicon glyphicon-edit icon-white"></i>
                                    进入课堂
                                </a>
                                <a class="btn btn-danger" href="#">
                                    <i class="glyphicon glyphicon-trash icon-white"></i>
                                    退选
                                </a>
                            </td>
                            
                        </tr>
                        <tr>
                            <td>003</td>
                            <td class="center">小学数学</td>
                            <td class="center">王五</td>
                            <td class="center">在线</td>
                            <!--出生年月+操作的表单-->
                            <td class="center">2012/01/01</td>
                            <td class="center">
                                <a class="btn btn-success" href="#">
                                    <i class="glyphicon glyphicon-zoom-in icon-white"></i>
                                    查看
                                </a>
                                <a class="btn btn-info" href="#">
                                    <i class="glyphicon glyphicon-edit icon-white"></i>
                                    进入课堂
                                </a>
                                <a class="btn btn-danger" href="#">
                                    <i class="glyphicon glyphicon-trash icon-white"></i>
                                    退选
                                </a>
                            </td>
                            
                        </tr>
                        <tr>
                            <td>003</td>
                            <td class="center">小学数学</td>
                            <td class="center">王五</td>
                            <td class="center">在线</td>
                            <!--出生年月+操作的表单-->
                            <td class="center">2012/01/01</td>
                            <td class="center">
                                <a class="btn btn-success" href="#">
                                    <i class="glyphicon glyphicon-zoom-in icon-white"></i>
                                    查看
                                </a>
                                <a class="btn btn-info" href="#">
                                    <i class="glyphicon glyphicon-edit icon-white"></i>
                                    进入课堂
                                </a>
                                <a class="btn btn-danger" href="#">
                                    <i class="glyphicon glyphicon-trash icon-white"></i>
                                    退选
                                </a>
                            </td>
                            
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
        </div>
        <!--/span-->

    </div><!--/row-->


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
            <!--End mc_embed_signup-->
    </div>
    <!-- Ad ends -->
</div>
</body>
</html>

