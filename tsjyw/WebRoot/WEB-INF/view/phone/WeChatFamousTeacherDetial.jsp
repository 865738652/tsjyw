<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="zh-cmn-Hans">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    
    <%@include file="WeChatheader.jsp" %>
    <title><c:out value="${testData}"/>详情</title>
</head>
<body ontouchstart>
<div>
    <div class="page__bd">
        <article class="weui-article">
            <p>
                <img src="${user.userPhotoUrl}" alt="">
            </p>
            <section>
                <h2 class="title"><c:out value="${user.userName}"/></h2>
                <section>
                    <h3>简介</h3>
                    <p>
                        <c:if test="${famousTeacherRoleId == 9}">
                        	<c:out value="${user.volunteerIntro}" escapeXml="false"/>
                        </c:if>
                        <c:if test="${famousTeacherRoleId == 10}">
                        	<c:out value="${user.famousTeacherIntro}" escapeXml="false"/>
                        </c:if>
                    </p>
                </section>
                <section>
                    <h3>详情信息</h3>
                    <p>
	                    <table>
	                    	<tr>
	                    		<td>姓名：</td>
	                    		<td><c:out value="${user.userName}"/></td>
	                    		<td></td>
	                    		<td></td>
	                    	</tr>
	                    	<tr>
	                    		<td>性别：</td>
	                    		<td>
	                    			<c:if test="${user.userGender == 0}">
			                        	男
			                        </c:if>
			                        <c:if test="${user.userGender == 1}">
			                        	女
			                        </c:if>
	                    		</td>
	                    		<td></td>
	                    		<td></td>
	                    	</tr>
	                    	<tr>
	                    		<td>微信：</td>
	                    		<td><c:out value="${user.userWeixin}"/></td>
	                    		<td></td>
	                    		<td></td>
	                    	</tr>
							<tr>
	                    		<td>QQ</td>
	                    		<td><c:out value="${user.userQq}"/></td>
								<td></td>
	                    		<td></td>
	                    	</tr>
						</table>
                   </p>
                </section>
            </section>
        </article>
        <div class="weui-cells__title">擅长的年龄段</div>
        <div class="weui-cells">
	        <c:choose>
	        	<c:when test="${not empty ageLevels}">
	        		<c:forEach var="ageLevel" items="${ageLevels}">
			            <div class="weui-cell">
			                <div class="weui-cell__bd">
			                    <p><c:out value="${ageLevel.ageName}"/></p>
			                </div>
			            </div> 
	        		</c:forEach>
	        	</c:when>
	        	<c:otherwise>
		            <div class="weui-cell">
		                <div class="weui-cell__bd">
		                    <p>未填写</p>
		                </div>
		            </div>
	        	</c:otherwise>
	        </c:choose>
        </div>

        <div class="weui-cells__title">擅长的问题类型</div>
        <div class="weui-cells">
			<c:choose>
				<c:when test="${not empty askQuestionTypes}">
					<c:forEach var="askQuestionType" items="${askQuestionTypes}">
			            <div class="weui-cell">
			                <div class="weui-cell__bd">
			                    <p><c:out value="${askQuestionType.askQuestionTypeName}"/></p>
			                </div>
			            </div>
					</c:forEach>

				</c:when>
				<c:otherwise>
		            <div class="weui-cell">
		                <div class="weui-cell__bd">
		                    <p>未填写</p>
		                </div>
		            </div>
				</c:otherwise>
			</c:choose>
        </div>
        
        
        <div class="weui-panel">
            <div class="weui-panel__hd">回答过的问题</div>
            <div class="weui-panel__bd">
                <div class="weui-media-box weui-media-box_text">
                	<c:choose>
                		<c:when test="${not empty myAnswerQuestions}">
                			<c:forEach var="myAnswerQuestion" items="${myAnswerQuestions}">
                				<h4 class="weui-media-box__title"><c:out value="${myAnswerQuestion.askQuestionTitle}" escapeXml="false"/></h4>
			                    <p class="weui-media-box__desc"><c:out value="${myAnswerQuestion.askQuestionContent}" escapeXml="false"/></p>
			                    <ul class="weui-media-box__info">
			                        <li class="weui-media-box__info__meta"><c:out value="${myAnswerQuestion.askQuestionTypeName}"/></li>
			                        <li class="weui-media-box__info__meta"><fmt:formatDate value='${myAnswerQuestion.askQuestionTime}' pattern='yyyy-MM-dd'/></li>
			                        <li class="weui-media-box__info__meta weui-media-box__info__meta_extra"><a href="WeChatLookQuestion?askQuestionId=${myAnswerQuestion.askQuestionId}">详情</a></li>
			                    </ul>
                			</c:forEach>
                		</c:when>
                		<c:otherwise>
                			未回答过问题
                		</c:otherwise>
                	</c:choose>
                </div>
            </div>
        </div>
        
        
        
        
        
        
        
        
        
        
        
        <!-- 
        <div class="weui-cells__title">回答过的问题</div>
        <div class="weui-cells">
        	
        	<c:choose>
        		<c:when test="${not empty myAnswerQuestions}">
        			<c:forEach var="myAnswerQuestion" items="${myAnswerQuestions}">
			            <a class="weui-cell weui-cell_access" href="WeChatLookQuestion?askQuestionId=${myAnswerQuestion.askQuestionId}">
			                <div class="weui-cell__bd">
			                    <p><c:out value="${myAnswerQuestion.askQuestionTitle}"/></p>
			                </div>
			                <div class="weui-cell__ft"><fmt:formatDate value='${myAnswerQuestion.askQuestionTime}' pattern='yyyy-MM-dd'/></div>
			            </a>
        			</c:forEach>
        		</c:when>
        		<c:otherwise>
		            <div class="weui-cell">
		                <div class="weui-cell__bd">
		                    <p>空</p>
		                </div>
		            </div>
        		</c:otherwise>
        	</c:choose>
        </div>
         -->
        
    </div>
    <div class="page__bd page__bd_spacing">
        <a href="WeChatAskQuestion?respUserId=${famousTeacherId}&respUserType=${famousTeacherRoleId}" class="weui-btn weui-btn_primary">向他提问</a>
    </div>
</div>
        <!-- @end 活动中心  add by hanjw 20150917-->     

        <script src="../js/wappv.js"></script>   
        <script src="../js/touch.min.js"></script>
        <%@include file="WeChatToolbar.jsp" %>
</body>
</html>