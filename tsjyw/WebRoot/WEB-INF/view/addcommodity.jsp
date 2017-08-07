<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <meta charset="utf-8">
    <title>添加商品</title>
    <%@include file="head.jsp" %>
  </head>
  
  <body>
    <%@include file="top.jsp" %>
     <div class="ch-container">
        <div class="row">
            <!-- left menu starts -->
            <%@include file="left.jsp"%>
            <div id="content" class="col-lg-10 col-sm-10">
            <!-- content starts -->
                <div>
			        <ul class="breadcrumb">
			            <li>
			                <a href="#">已购商品</a>
			            </li>
			            <li>
			                <a href="#">商品管理</a>
			            </li>
			            <li>
			            	<a href="#">添加商品</a>
			            </li>
			        </ul>
    			</div>
    			
    			</td>
			 </tr>


			<table>
	         <tr>
			   <td>
			   <form class="form-inline" role="form">
                 <label class="control-label" for="inputSuccess4">商品编号：</label>
                 <input type="text" class="form-control" id="inputSuccess4">
               </form>
				</td>
			   <td>
			   <form class="form-inline" role="form">
                  <div class="form-group">
                    <label class="control-label" for="inputSuccess1">商品名称：</label>
                    <input type="text" class="form-control" id="inputSuccess1">
                </div>
               </form>
				</td>
			 </tr>

			 <tr>
			   <td>
			   <form class="form-inline" role="form">
                  <div class="form-group">
                    <label class="control-label" for="inputSuccess1">商品类型：</label>
                    <input type="text" class="form-control" id="inputSuccess1">
                </div>
               </form>
				</td>
			   <td>
			   <form class="form-inline" role="form">
                  <div class="form-group">
                    <label class="control-label" for="inputSuccess1">限购数量：</label>
                    <input type="text" class="form-control" id="inputSuccess1">
                </div>
               </form>
				</td>
			 </tr>

			  <tr>
			   <td>
			   <form class="form-inline" role="form">
                  <div class="form-group">
                    <label class="control-label" for="inputSuccess1">库存数量：</label>
                    <input type="text" class="form-control" id="inputSuccess1">
                </div>
               </form>
				</td>
				<td>
			   <form class="form-inline" role="form">
                  <div class="form-group">
                    <label class="control-label" for="inputSuccess1">易购数量：</label>
                    <input type="text" class="form-control" id="inputSuccess1">
                </div>
               </form>
				</td>
			</tr>
			
			<tr>
			   <td>
			   <form class="form-inline" role="form">
                  <div class="form-group">
                    <label class="control-label" for="inputSuccess1">兑换积分：</label>
                    <input type="text" class="form-control" id="inputSuccess1">
                </div>
               </form>
				</td>
				<td>
			   <form class="form-inline" role="form">
                  <div class="form-group">
                    <label class="control-label" for="inputSuccess1">状态：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
                    <input type="text" class="form-control" id="inputSuccess1">
                </div>
               </form>
				</td>
			</tr>
		</table>
		
		                             <table>
										<tr>
											<td>
									  			<div class="form-group">
						                 			 <label class="control-label" for="inputSuccess1">商品简介:</label></td>
						                  				<td>
						                				  <textarea style="width:300px;margin-left:32px" rows="8"  list="2" placeholder="请对所加商品做简单描述" required></textarea>
						                  		</div>
						               		</td>
						               </tr>
						            </table>
						            
						            <div class="modal-footer">
						               <a href="#" class="btn btn-default" data-dismiss="modal">取消</a>
									   <a href="#" class="btn btn-primary" data-dismiss="modal">确定</a>
									   </div>
									   
									   </div>
									</div>
							     </div>
							  </body>
						</html>