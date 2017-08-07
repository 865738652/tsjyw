<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>${school.schoolName}</title>
    <link type="text/css" rel="stylesheet" href="../css/list.css">
    <script type="text/javascript" src="../js/jquery-1.11.3.min.js"></script>
    <style>
    .keImgs li a img {
        width: 307px;
        height: 215px;
    }
    </style>
    <script type="text/javascript">
    	$(function(){
    		loadArticle();
    	});
    	
    	function loadArticle() {
    		var params = {
    			moduleId: ${module.moduleId},
    			pageNumber: parseInt($("#pageNumber").val()),
    			pageSize: parseInt($("#pageSize").val()),
    			recommend: false
    		};
    		$.ajax({
		        url: "../Index/getArticleByModule",
		        data: params,
		        dataType: "json",
		        success: function (data) {
		            if (data.code == "succ") {
	                    var listdata = data.rows;
	                    var pageSize = parseInt($("#pageSize").val());
	                    var pageCount = Math.floor((data.total + pageSize - 1) / pageSize); 
	                    $("#pageCount").val(pageCount);
	                    $("#totalCount").val(data.total);
	                    
	                    var CName = "";
	                    var html = '';
	                    $(listdata).each(function (index, lista) {
	                    	var url = '../School/SchoolShow?articleId=' + lista.articleId;	                      
	                    	var title = lista.articleTitle;
	
	                        html += '<li><span class="left title">';
	                        html += '<a href="' + url + '" target="_blank">'  + title + '</a></span>';
	                        html += '<span class="right data">2016-09-06</span></li>';
	                    });
	                    $('#articleList').html(html);
	                    makePager();
	                }
	                else {
	                    return false;
	                }
		        },
		        error: function (xhr, errorType, error) {
		            //alert(error);
		            return false;
		        }
		    });
    	}
    	
    	function makePager() {
    		var pagehtml = '';
		    var PageIndex = parseInt($("#pageNumber").val());
		    var PageCount = parseInt($("#pageCount").val());
		    var myIndexprev = PageIndex > 1 ? PageIndex - 1 : 1;
		    var myIndexnext = PageIndex < PageCount ? PageIndex + 1 : PageCount;
		    var Pagelist = 10;
		    var startnum = 1;
		    var endnum = 10;
		    if (PageCount > 0) {
		        if (PageIndex > 4) {
		            startnum = PageIndex - 4;
		        }
		
		        endnum = PageIndex + 5;
		        if (endnum >= PageCount) {
		            endnum = PageCount;
		            if (PageCount >= Pagelist) {
		                startnum = PageCount - Pagelist + 1;
		            }
		        }
		        if (endnum < Pagelist && PageCount >= Pagelist) {
		            endnum = Pagelist;
		        }
		        pagehtml += '<li class="previous-off"><a>首页</a></li>';
		        pagehtml += '<li class="previous-off"><a>上一页</li>';
		        for (var i = startnum; i <= endnum; i++) {
		            if (i == PageIndex) {
		                pagehtml += '<li class="active"><a>' + PageIndex + '</a></li>';
		            }
		            else {
		                pagehtml += '<li><a>' + i + '</a></li>';
		            }
		        }
		        pagehtml += '<li class="next"><a>下一页</a></li>';
		        pagehtml += '<li class="next"><a>尾页</a></li>';
		        pagehtml += '<span>共' + PageCount + '页</span>';
		    }
		
		    $("#pagination-digg").html(pagehtml);
		    $("#pagination-digg a").css("cursor", "pointer");
		    $("#pagination-digg a").click(function () {
		        var p = $(this).text();
		        if (thisclass == "首页") {
		            $("#pageNumber").val(1);
		        }
		        else if (thisclass == "上一页") {
		            $("#pageNumber").val(myIndexprev);
		        }
		        else if (thisclass == "下一页") {
		            $("#pageNumber").val(myIndexnext);
		        }
		        else if (thisclass == "尾页") {
		            $("#pageNumber").val(PageCount);
		        }
		        else {
		            $("#pageNumber").val(p);
		        }
		        
		        loadArticle();
		        		        
		        window.setTimeout(function () {
		            $("html, body").animate({ scrollTop: 0 }, 1000);
		        },500);
		    });
    	}
    </script>
</head>

<body>
    <%@include file="schoolheader.jsp" %>
    <div id="bg">
        <script language="javascript">
        var div = document.getElementById('bg');
        div.className = 'bg1162';
        </script>
        <div class="container">
            <div class="space50"></div>
            <div class="left channel">
                <div class="father">分类栏目</div>
                <div class="children">
                    <ul>
                    	<c:forEach var="menu" items="${modules}"> 
		 					<c:if test="${menu.moduleId==module.moduleId}"><li class="selected"></c:if>
		 					<c:if test="${menu.moduleId!=module.moduleId}"><li></c:if>
		 					<c:if test="${menu.moduleTypeId!=4}"><a href="../School/SchoolList?moduleId=${menu.moduleId}"></c:if>
		 					${menu.moduleName}</a></li>
	 					</c:forEach>
                    </ul>
                </div>
            </div>
            <div class="right list">
                <div class="current_location">
                    <a href="../School/${school.schoolId}">首页</a> >> <a href="../School/SchoolList?moduleId=${menu.moduleId}">${module.moduleName}</a>
                </div>
                <div class="list_border">
                    <div class="list_channel">
                        ${module.moduleName}
                    </div>
                    <div class="news_list">
                        <div class="space15"></div>
                        <ul id="articleList"></ul>
                    </div>
                    <div class="pape">
                        <input type="hidden" id="pageSize" value="20" />
                        <input type="hidden" id="pageNumber" value="1" />
                        <input type="hidden" id="pageCount" value="0" />
                        <input type="hidden" id="totalCount" value="0" />
                        <ul id="pagination-digg"></ul>
                    </div>
                </div>
            </div>
            <div class="clear"></div>
            <div class="space15"></div>
        </div>
    </div>
    <div class="foot">
        <div class="container">
            <div class="left foot_describe">
                <ul>
                    <li>Copyright &copy; 2010-2016 益铭科技. All Rights Reserved</li>
                </ul>
            </div>
            <div class="clear"></div>
        </div>
    </div>
    <!-- åºé¨ç»æ -->
</body>

</html>
