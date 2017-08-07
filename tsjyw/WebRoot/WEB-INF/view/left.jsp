<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!-- left menu starts -->
<div class="col-sm-2 col-lg-2">
            <div class="sidebar-nav">
                <div class="nav-canvas">
                    <div class="nav-sm nav nav-stacked">

                    </div>
                    <ul class="nav nav-pills nav-stacked main-menu">
                        <li class="nav-header">Main</li>
                        <c:forEach var="menu" items="${sessionScope.adminMenu}">
                        	<c:if test="${fn:length(menu.children) == 0}">
                        		<li>
                            		<a class="ajax-link" href="${menu.adminMenuUrl}"><i class="glyphicon glyphicon-flag"></i><span>${menu.adminMenuName}</span></a>
                        		</li>
                        	</c:if>
                        	<c:if test="${fn:length(menu.children) > 0}">
                        		<li class="accordion">
		                            <a href="${menu.adminMenuUrl}"><i class="glyphicon glyphicon-envelope"></i><span> ${menu.adminMenuName}</span></a>
		                            <ul class="nav nav-pills nav-stacked">
		                            	<c:forEach var="sub" items="${menu.children}">
		                                	<li><a href="${sub.adminMenuUrl}">${sub.adminMenuName}</a></li>
										</c:forEach>
		                            </ul>
		                        </li>
                        	</c:if>
                        </c:forEach>
                        <!--
                        <li><a class="ajax-link" href="index"><i class="glyphicon glyphicon-home"></i><span> 管理中心</span></a>
                        </li>
                        
                        <li class="accordion">
                            <a href="#"><i class="glyphicon glyphicon-envelope"></i><span> 我的通知</span></a><span class="notification red">12</span>
                            <ul class="nav nav-pills nav-stacked">
                                <li><a href="SendNotice">发送通知<span class="notification red">12</span></a></li>

                                <li><a href="RecvNotice">收到的通知<span class="notification red">12</span></a></li>
                                <li><a href="SendedNotice">已发的通知<span class="notification red">12</span></a></li>

                                <li><a href="Draft">草稿<span class="notification red">12</span></a></li>
                                <li><a href="DeleteNotice">删除的通知<span class="notification red">12</span></a></li>
                            </ul>
                        </li>
                        
                        <li>
                            <a class="ajax-link" href="../CountyManage/"><i class="glyphicon glyphicon-flag"></i><span>区县管理</span></a>
                        </li>
                        
                        <li>
                            <a class="ajax-link" href="../SchoolManage/"><i class="glyphicon glyphicon-book"></i><span>学校管理</span></a>
                        </li>
                        <li>
                            <a class="ajax-link" href="../GradeManage/"><i class="glyphicon glyphicon-book"></i><span>年级管理</span></a>
                        </li>
                        <li>
                            <a class="ajax-link" href="../SchoolClassManage/"><i class="glyphicon glyphicon-book"></i><span>班级管理</span></a>
                        </li>
                        
                        <li class="accordion">
                            <a href="#"><i class="glyphicon glyphicon-book"></i><span>网络课堂</span></a>
                            <ul class="nav nav-pills nav-stacked">
                                <li><a href="ReleaseCourse">发布课程</a></li>
                                <li><a href="VisitCourse">浏览课程</a></li>
                                <li><a href="SelectCourse">浏览课程</a></li>
                            </ul>
                        </li>
              
                        <li class="accordion">
                            <a href="#"><i class="glyphicon glyphicon-question-sign"></i><span>在线问答</span></a><span class="notification red">1</span>
                            <ul class="nav nav-pills nav-stacked">
                            	<li><a href="../AskQuestionManage/AskQuestion">我要提问<span class="notification red">12</span></a></li>
                                <li><a href="../AskQuestionManage/ShowMyQuestion">我的问题<span class="notification red">12</span></a></li>
                                <li><a href="../AskQuestionManage/ShowMyRespQuestion">我的回答<span class="notification red">12</span></a></li>
                                <li><a href="../AskQuestionManage/ShowAllQuestion">全部问题<span class="notification red">12</span></a></li>
                            </ul>
                        </li>
                        <li class="accordion">
                            <a href="#"><i class="glyphicon glyphicon-user"></i><span> 用户中心</span></a>
                            <ul class="nav nav-pills nav-stacked">
                                <li><a href="../UserManage/">用户管理<span class="notification red">12</span></a></li>
                                <li><a href="../ApplynManage/ShowMyApply">申请志愿者<span class="notification red">12</span></a></li>
                            	<li><a href="../ApplynManage/ShowAllApply">处理申请<span class="notification red">12</span></a></li>
                                <li><a href="../FamousTeacherManage/">名师管理<span class="notification red">12</span></a></li>
                                <li><a href="../VolunteerManage/">志愿者管理<span class="notification red">12</span></a></li>
                                <li><a href="../UserManage/ShowMe">个人资料<span class="notification red">12</span></a></li> 
                            </ul>
                        </li>
                        <li class="accordion">
                            <a href="#"><i class="glyphicon glyphicon-comment"></i><span> 内容管理</span></a>
                            <ul class="nav nav-pills nav-stacked">
                                <li><a href="../ModuleManage/">栏目管理</a></li>
                                <li><a href="../ArticleManage/">文章管理</a></li>
                            </ul>
                        </li>
                        <li class="accordion">
                            <a href="#"><i class="glyphicon glyphicon-shopping-cart"></i><span>兑换商品</span></a>
                            <ul class="nav nav-pills nav-stacked">
                                <li><a href="CommodityType">商品类型</a></li>
                                <li><a href="CommodityManage">商品管理</a></li>
                                <li><a href="IntergralMall">积分商城</a></li>
                                <li><a href="PurchasedGoods">已购商品</a></li>
                            </ul>
                        </li>
                          -->
                    </ul>
                </div>
            </div>
        </div>
