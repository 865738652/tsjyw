<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>

<html lang="en">
<head>
   
    <meta charset="utf-8">
    <title>管理年级</title>
    <%@include file="head.jsp" %>
</head>

<body>
    <%@include file="top.jsp"%>
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
            <!-- content starts -->
                <div>
        <ul class="breadcrumb">
            <li>
                <a href="ClassManage">班级管理</a>
            </li>
            <li>
                <a href="CourseTeacher">任课教师管理</a>
            </li>
        </ul>
    </div>
    <div class="row">
    <div class="box col-md-12">
    <div class="row">
        <div class="box col-md-12">
            <div class="box-inner">
                <div class="box-header well" data-original-title="">
                    <h2>任课教师管理</h2>

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
                    <a class="btn btn-success" data-toggle="modal" data-target="#myModal">
                        <i class="glyphicon glyphicon-plus icon-white"></i>
                        添加任课教师
                    </a>
                </div>
                    <table class="table table-bordered table-striped table-condensed">
                        <thead>
                        <tr>
                            <th>课程名称</th>
                            <th>教师名称</th>
                            <th>手机号码</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>高数</td>
                            <td>张三</td>
                            <td class="center">13567890278</td>
                            <td class="center">
                                
                                <a class="btn btn-danger" href="#">
                                    <i class="glyphicon glyphicon-remove icon-white"></i>
                                    删除
                                </a>
                                
                            </td>
                        </tr>
                        <tr>
                            <td>大学英语</td>
                            <td>李四</td>
                            <td class="center">1578930820</td>
                            <td class="center">
                                
                                <a class="btn btn-danger" href="#">
                                    <i class="glyphicon glyphicon-remove icon-white"></i>
                                    删除
                                </a>
                                
                            </td>
                        </tr>
                        </tbody>
                    </table>
                    <ul class="pagination pagination-centered">
                        <li><a href="#">前一页</a></li>
                        <li class="active">
                            <a href="#">1</a>
                        </li>
                        <li><a href="#">2</a></li>
                        <li><a href="#">3</a></li>
                        <li><a href="#">4</a></li>
                        <li><a href="#">下一页</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </div><!--/span-->

    <!-- content ends -->
    </div><!--/#content.col-md-0-->
</div>
<!-- 添加任课教师对话框 -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
         aria-hidden="true">

        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>  
                    <h4 class="modal-title">添加任课教师</h4> 
                </div>
                <div class="modal-body">
                    <table>
                        
                        <tr>
                            <td >
                                <form class="form-inline" role="form">
                                    <label class="control-label" for="inputSuccess4">课程名称</label>
                                <input type="text" class="form-control" id="inputSuccess4">
                                </form>
                                   
                            </td>
                            <td >
                                <form class="form-inline" role="form">
                                    <label class="control-label" for="inputSuccess4">教师名称</label>
                                <input type="text" class="form-control" id="inputSuccess4">
                                </form>
                                   
                            </td>
                            
                        </tr>
                         <tr>
                            <td >
                                <form class="form-inline" role="form">
                                    <label class="control-label" for="inputSuccess4">手机号码</label>
                                <input type="text" class="form-control" id="inputSuccess4">
                                </form>
                                   
                            </td>
                            
                        </tr>
                    
                         
                        
                    </table>
                </div>
                <div class="modal-footer" >
                    <a  href="#" class="btn btn-default" data-dismiss="modal">取消</a>
                    <a   href="#" class="btn btn-primary" data-dismiss="modal">确定</a>
                </div>
            </div>
        </div>
</div>
  </body>
</html>
