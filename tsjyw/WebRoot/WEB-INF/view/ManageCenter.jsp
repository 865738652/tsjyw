<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>管理中心</title>
    <%@include file="head.jsp" %>
</head>

<body>
	<jsp:include page="top.jsp"/>
  <div class="ch-container">
    <div class="row">
        <!-- left menu starts -->
        <%@include file="left.jsp"%>
        <!--/span-->
        <!-- left menu ends -->
        <div id="content" class="col-lg-10 col-sm-10">
                        <!-- content starts -->
            <!--两个链接开始-->
            <div>
                <ul class="breadcrumb">
                    <li>
                        <a href="#">唐山家庭教育网</a>
                    </li>
                    <li>
                        <a href="#">管理中心</a>
                    </li>
                </ul>
            </div>
            <!--两个链接结束-->

            <!--三幅图片开始-->
            <div class=" row">
                <div class="col-md-3 col-sm-3 col-xs-6">
                    <a data-toggle="tooltip" title="6 new members." class="well top-block" href="#">
                        <i class="glyphicon glyphicon-user blue"></i>

                        <div>总共成员</div>
                        <div>507</div>
                        <span class="notification">6</span>
                    </a>
                </div>

                <div class="col-md-3 col-sm-3 col-xs-6">
                    <a data-toggle="tooltip" title="4 new pro members." class="well top-block" href="#">
                        <i class="glyphicon glyphicon-star green"></i>

                        <div>星级用户</div>
                        <div>228</div>
                        <span class="notification green">4</span>
                    </a>
                </div>

                <div class="col-md-3 col-sm-3 col-xs-6">
                    <a data-toggle="tooltip" title="$34 new sales." class="well top-block" href="#">
                        <i class="glyphicon glyphicon-shopping-cart yellow"></i>

                        <div>总资产</div>
                        <div>$13320</div>
                        <span class="notification yellow">$34</span>
                    </a>
                </div>

                <div class="col-md-3 col-sm-3 col-xs-6">
                    <a data-toggle="tooltip" title="12 new messages." class="well top-block" href="#">
                        <i class="glyphicon glyphicon-envelope red"></i>

                        <div>消息通知</div>
                        <div>25</div>
                        <span class="notification red">12</span>
                    </a>
                </div>
            </div>
            <!--三幅图片结束-->

            <!--中间的div开始-->
            <div class="row">
                <div class="box col-md-12">
                    <div class="box-inner">
                        <div class="box-header well">
                            <h2><i class="glyphicon glyphicon-info-sign"></i> 唐山家庭教育网简介</h2>

                            <div class="box-icon">
                                <a href="#" class="btn btn-setting btn-round btn-default"><i
                                        class="glyphicon glyphicon-cog"></i></a>
                                <a href="#" class="btn btn-minimize btn-round btn-default"><i
                                        class="glyphicon glyphicon-chevron-up"></i></a>
                                <a href="#" class="btn btn-close btn-round btn-default"><i
                                        class="glyphicon glyphicon-remove"></i></a>
                            </div>
                        </div>
                        <div class="box-content row">
                            <div class="col-lg-7 col-md-12">
                                <p>
                                    唐山家庭教育网是一个致力于教育的网站
                                </p>
                            </div>
                            <!-- Ads, you can remove these -->
                            <div class="col-lg-5 col-md-12 hidden-xs center-text">
                                <script async src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>
                                <!-- Charisma Demo 4 -->
                                <ins class="adsbygoogle"
                                     style="display:inline-block;width:336px;height:280px"
                                     data-ad-client="ca-pub-5108790028230107"
                                     data-ad-slot="9467443105"></ins>
                                <script>
                                    (adsbygoogle = window.adsbygoogle || []).push({});
                                </script>
                            </div>

                            <div class="col-lg-5 col-md-12 visible-xs center-text">
                                <script async src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>
                                <!-- Charisma Demo 5 -->
                                <ins class="adsbygoogle"
                                     style="display:inline-block;width:250px;height:250px"
                                     data-ad-client="ca-pub-5108790028230107"
                                     data-ad-slot="8957582309"></ins>
                                <script>
                                    (adsbygoogle = window.adsbygoogle || []).push({});
                                </script>
                            </div>
                            <!-- Ads end -->

                        </div>
                    </div>
                </div>
            </div>
            <!--中间的div开始-->
            <!-- content ends -->
        </div><!--/#content.col-md-0-->
    </div><!--/fluid-row-->
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
