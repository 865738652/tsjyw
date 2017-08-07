<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="en">
<head>
    
    <title>商品类型</title>
    <%@include file="head.jsp" %>
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
            <a href="#">兑换商品</a>
        </li>
        <li>
            <a href="#">商品类型</a>
        </li>
    </ul>
</div>

<div class="box-content">
                
      <a href="#" class="btn btn-info btn-setting">添加商品类型</a>             
                       
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
                    <table class="table table-striped table-bordered responsive">
                        <thead>
                        <tr>
                            <th>类型编号</th>
                            <th>类型名称</th>
                            <th>所属类型</th>
                            <th>禁用</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>

                        <tr>
                            <td class="center"></td>
                            <td class="center"></td>
                            <td class="center"></td>
                            <td class="center">
                            	<button class="btn btn-sucess btn-xs">
                            	<i class="glyphicon glyphicon-ok"></i>是</button>
                            	<button class="btn btn-fanger btn-xs">
                            	<i class="glyphicon glyphicon-remove"></i>否</button>
                            </td>
                            <td class="center">
                                <a class="btn btn-info" href="#">
                                    <i class="glyphicon glyphicon-edit icon-white"></i>
                                   修改
                                </a>
                                <a class="btn btn-danger" href="#">
                                    <i class="glyphicon glyphicon-trash icon-white"></i>
                                    删除
                                </a>
                            </td>
                        </tr>
                         <tr>
                            <td class="center"></td>
                            <td class="center"></td>
                            <td class="center"></td>
                            <td class="center">
                            	<button class="btn btn-sucess btn-xs">
                            	<i class="glyphicon glyphicon-ok"></i>是</button>
                            	<button class="btn btn-fanger btn-xs">
                            	<i class="glyphicon glyphicon-remove"></i>否</button>
                            </td>
                            <td class="center">
                                <a class="btn btn-info" href="#">
                                    <i class="glyphicon glyphicon-edit icon-white"></i>
                                   修改
                                </a>
                                <a class="btn btn-danger" href="#">
                                    <i class="glyphicon glyphicon-trash icon-white"></i>
                                    删除
                                </a>
                            </td>
                        </tr>

                        </tbody>
                    </table>
                </div>
            </div>
</div>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
         aria-hidden="true">

        <div class="modal-dialog">
            <div class="modal-content">
              <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>  
                    <h4 class="modal-title">添加商品类型</h4> 
                </div>
                <div class="modal-body">
                   <form class="form-inline" role="form">
                        <div class="form-group has-success has-feedback">
                            <label class="control-label" for="inputSuccess4">类型编号</label>
                            <input type="text" class="form-control" id="inputSuccess4">
                            <hr/>
                            <label class="control-label" for="inputSuccess4">类型名称</label>
                            <input type="text" class="form-control" id="inputSuccess4">
                            <hr/>
                            <label class="control-label" for="inputSuccess4">所属类型</label>
                            <input type="text" class="form-control" id="inputSuccess4">
                        </div>
                        <br>
                    </form>
                </div>
                <div class="modal-footer">
                    <a href="#" class="btn btn-default" data-dismiss="modal">取消</a>
                    <a href="#" class="btn btn-primary" data-dismiss="modal">确定</a>
                </div>
            </div>
        </div>
</div>
</body>
</html>
