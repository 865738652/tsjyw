<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'TestUpload.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
    
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	 
	<script src="js/SimpleAjaxUploader.js"></script>
	<script type="text/javascript">
		window.onload = function() {  
		    init();  //初始化  
		}  
		/*
		function init() {
			var btnFile = document.getElementById("btnUploadFile");
			new AjaxUpload(btnFile, {  
		    action: 'uploadFile',  
		        data: {},  
		        name: 'file',  
		        customHeaders: { '${_csrf.headerName}': '${_csrf.token}' },
		        onSubmit: function(file, ext) {  
		           
		        },  
		        onComplete: function(file, response) {  
		            alert(response);
		        }  
		    }); 
		}
		*/
		function init() {
			var btn = document.getElementById("btnUploadFile");
			var uploader = new ss.SimpleUpload({
		        button: btn,
		        url: 'uploadFile',
		        name: 'file',
		        multipart: true,
		        customHeaders: { '${_csrf.headerName}': '${_csrf.token}' },
		        onComplete: function( filename, response ) {
		            alert(response);
		          },
		        onError: function() {
		            progressOuter.style.display = 'none';
		            msgBox.innerHTML = 'Unable to upload file';
		          }
			});
		}
	</script>
  </head>
  
  <body>
    <form method="post" action="uploadFile" enctype="multipart/form-data">
    	<input type="button" id="btnUploadFile" value="上传" />
    </form>
  </body>
</html>
