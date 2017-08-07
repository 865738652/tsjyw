<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div class="head">
    <div class="container">
        <div class="left">
            <c:if test="${empty school.schoolLogo}"><img src="../images/logo.png" height="110"></c:if>
            <c:if test="${not empty school.schoolLogo}"><img src="${school.schoolLogo}" height="110"></c:if>
        </div>
        <div class="right channel">
            <ul>
                <li><a href="../School/${school.schoolId}" class="active">首页</a></li>
                <c:forEach var="menu" items="${modules}"> 
					<li>
					<c:if test="${menu.moduleTypeId==4}"><a href="${menu.moduleUrl}"></c:if>
					<c:if test="${menu.moduleTypeId!=4}"><a href="../School/SchoolList?moduleId=${menu.moduleId}"></c:if>
					${menu.moduleName}</a></li>
				</c:forEach>
            </ul>
        </div>
        <div class="clear"></div>
    </div>
</div>
