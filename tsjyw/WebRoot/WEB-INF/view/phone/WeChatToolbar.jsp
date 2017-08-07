<%@ page language="java" contentType="text/html; charset=utf8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link href='<c:url value="/phone/css/toolbar.css"/>' rel="stylesheet" />
<script> 
function goback(){ 
    window.history.back(-1) 
} 
</script>
<div class="toolbar">
   <!--  <a href="###" class="toolbar-item toolbar-item-weixin">
   <span class="toolbar-layer"></span>
   </a>
   <a href="###" class="toolbar-item toolbar-item-feedback"></a>-->
   <a href="###" class="toolbar-item toolbar-item-app" onclick="goback()">
    <span class="toolbar-layer"></span>
   </a>
   <a href="javascript:scroll(0,0)" id="top" class="toolbar-item toolbar-item-top"></a>
</div>

