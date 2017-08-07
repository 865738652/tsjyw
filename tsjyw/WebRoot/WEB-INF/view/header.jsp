<%@ page language="java" import="java.util.*,edu.iasd.pojo.ViewModule" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<script type="text/javascript">	
	function bindSelect(id, url, param, valueField, textField, selectedValue, preValue, preText) {
		var ctrl = $("#" + id);
		if (ctrl == null || ctrl == undefined)
			return;
		var data = {};

		if (param != null && param != undefined)
			data = param;

		ctrl.empty();
		$.ajax({
			url: url,	
			type: "get",
			data: data,
			dataType:"json",
			success:function(json) {  
				if (json.code != "succ") {
					alert(json.data);
					return;
				}
				
				if (preValue != undefined && preText != undefined) {
					var selected = (selected == undefined || selected == preValue);
					var opt = "<option value='" + preValue + "' " + selected +"> " + preText + "</option>";
		        	ctrl.append(opt);
				}
				
		        for (var i = 0; i < json.rows.length; i++) {
		        	var selected = json.rows[i][valueField] == selectedValue ? "selected" : "";
		        	var opt = "<option value='" + json.rows[i][valueField] + "' " + selected +"> " + json.rows[i][textField] + "</option>";
		        	ctrl.append(opt);
		        }
		    },
		    error: function(XMLHttpRequest, textStatus, errorThrown) {
				alert("网络错误: " + XMLHttpRequest.status + ", " + textStatus);
		    }
		});	
	}
	
	//扩展Date的format方法   
    Date.prototype.format = function (format) {  
        var o = {  
            "M+": this.getMonth() + 1,  
            "d+": this.getDate(),  
            "h+": this.getHours(),  
            "m+": this.getMinutes(),  
            "s+": this.getSeconds(),  
            "q+": Math.floor((this.getMonth() + 3) / 3),  
            "S": this.getMilliseconds()  
        }  
        if (/(y+)/.test(format)) {  
            format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));  
        }  
        for (var k in o) {  
            if (new RegExp("(" + k + ")").test(format)) {  
                format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));  
            }  
        }  
        return format;  
    }  
    /**   
    *转换日期对象为日期字符串   
    * @param date 日期对象   
    * @param isFull 是否为完整的日期数据,   
    *               为true时, 格式如"2000-03-05 01:05:04"   
    *               为false时, 格式如 "2000-03-05"   
    * @return 符合要求的日期字符串   
    */    
    function getSmpFormatDate(date, isFull) {  
        var pattern = "";  
        if (isFull == true || isFull == undefined) {  
            pattern = "yyyy-MM-dd hh:mm:ss";  
        } else {  
            pattern = "yyyy-MM-dd";  
        }  
        return getFormatDate(date, pattern);  
    }  
    /**   
    *转换当前日期对象为日期字符串   
    * @param date 日期对象   
    * @param isFull 是否为完整的日期数据,   
    *               为true时, 格式如"2000-03-05 01:05:04"   
    *               为false时, 格式如 "2000-03-05"   
    * @return 符合要求的日期字符串   
    */    
  
    function getSmpFormatNowDate(isFull) {  
        return getSmpFormatDate(new Date(), isFull);  
    }  
    /**   
    *转换long值为日期字符串   
    * @param l long值   
    * @param isFull 是否为完整的日期数据,   
    *               为true时, 格式如"2000-03-05 01:05:04"   
    *               为false时, 格式如 "2000-03-05"   
    * @return 符合要求的日期字符串   
    */    
  
    function getSmpFormatDateByLong(l, isFull) {  
        return getSmpFormatDate(new Date(l), isFull);  
    }  
    /**   
    *转换long值为日期字符串   
    * @param l long值   
    * @param pattern 格式字符串,例如：yyyy-MM-dd hh:mm:ss   
    * @return 符合要求的日期字符串   
    */    
  
    function getFormatDateByLong(l, pattern) {  
        return getFormatDate(new Date(l), pattern);  
    }  
    /**   
    *转换日期对象为日期字符串   
    * @param l long值   
    * @param pattern 格式字符串,例如：yyyy-MM-dd hh:mm:ss   
    * @return 符合要求的日期字符串   
    */    
    function getFormatDate(date, pattern) {  
        if (date == undefined) {  
            date = new Date();  
        }  
        if (pattern == undefined) {  
            pattern = "yyyy-MM-dd hh:mm:ss";  
        }  
        return date.format(pattern);  
    }  

	function getArticleByModule(moduleId, count, recommend, onSuccess, onError) {
		var url = "../Index/getArticleByModule";
		var param = {};
		
		if (moduleId == undefined || moduleId == null)
			url = "../Index/getAllArticle";
		else
			param.moduleId = moduleId;
		
		param.pageSize = ((count != undefined && count != null) ? count : 1000);
		param.pageNumber = 1;
		param.recommend = (recommend == true ? true : false);
		
		$.ajax({
			url:url,			
			data:param,
			dataType:"json",
			success:function(data) {  
		        if (data.code != "succ") {
					alert("获取文章列表失败:" + data.data);
					if (onError != undefined)
						onError();	
				}	
				if (onSuccess != undefined)
					onSuccess(data);	      
		    },
		    error: function(XMLHttpRequest, textStatus, errorThrown) {
				alert("网络错误: " + XMLHttpRequest.status + ", " + textStatus);
				onError();
		    }
		    
		});
	}
	
	function getVolunteer(pageNumber, pageSize, onSuccess, onError) {
		var url = "../Index/getAllVolunteer";
		var param = {};		
		param.pageSize = pageSize;
		param.pageNumber = pageNumber;
		
		$.ajax({
			url:url,			
			data:param,
			dataType:"json",
			success:function(data) {  
		        if (data.code != "succ") {
					alert("获取志愿者失败:" + data.data);
					if (onError != undefined)
						onError();	
				}	
				if (onSuccess != undefined)
					onSuccess(data);	      
		    },
		    error: function(XMLHttpRequest, textStatus, errorThrown) {
				alert("网络错误: " + XMLHttpRequest.status + ", " + textStatus);
				onError();
		    }
		    
		});
	}
	
	function getFamousTeacher(pageNumber, pageSize, onSuccess, onError) {
		var url = "../Index/getAllFamousTeacher";
		var param = {};		
		param.pageSize = pageSize;
		param.pageNumber = pageNumber;
		
		$.ajax({
			url:url,			
			data:param,
			dataType:"json",
			success:function(data) {  
		        if (data.code != "succ") {
					alert("获取名师失败:" + data.data);
					if (onError != undefined)
						onError();	
				}	
				if (onSuccess != undefined)
					onSuccess(data);	      
		    },
		    error: function(XMLHttpRequest, textStatus, errorThrown) {
				alert("网络错误: " + XMLHttpRequest.status + ", " + textStatus);
				onError();
		    }
		    
		});
	}
	
	function getHotQuestion(pageNumber, pageSize, onSuccess, onError) {
		var url = "../Index/getHotAskQuestion";
		var param = {};		
		param.pageSize = pageSize;
		param.pageNumber = pageNumber;
		
		$.ajax({
			url:url,			
			data:param,
			dataType:"json",
			success:function(data) {  
		        if (data.code != "succ") {
					alert("获取热点问题失败:" + data.data);
					if (onError != undefined)
						onError();	
				}	
				if (onSuccess != undefined)
					onSuccess(data);	      
		    },
		    error: function(XMLHttpRequest, textStatus, errorThrown) {
				alert("网络错误: " + XMLHttpRequest.status + ", " + textStatus);
				onError();
		    }
		    
		});
	}
	///* 登陆弹窗显示
    $('#login-pop').live('click', function (event) {
    	<sec:authorize access="!hasRole('ROLE_USER')">
        	$('#login-widow,.bg').show();
        </sec:authorize>
    });
	//*/
	/*登陆表单实例
	<form id="loginForm">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/> 
	    <input name="userName" id="userName" type="text" placeholder="Email"/>
	    <input name="password" id="password" type="password" size="40" placeholder="Password"/>
	    <button type="button" onclick="ajaxSubmit()">登录</button>
	    <button type="reset">重置</button>
	</form>
	*/
	function ajaxSubmit(){
		$.ajax({
	        type:"post",
	        url:"../Account/Authenticate",
	        dataType:"json",
	        async:true,
	        data:$("#loginForm").serialize(),
	        success:function(data){
	            if(data.status==1){
	                $('#login-widow,.bg').hide();
	                location.reload();
	            }else{
	                alert(data.info);
	            }
	        },
	        error:function (XMLHttpRequest, textStatus, errorThrown){               
	            if(textStatus=="timeout"){
	                alert("响应超时，请稍后重试");
	            }else{
	            	alert(textStatus+","+errorThrown);
	                alert("登录异常，请稍后重试");
	            }
	        }
	    }); 
	}
	
	function extendTalbeParams(param, formId) {
		$($("#" + formId).serializeArray()).each(function () {
			param[this.name] = this.value;
		});
	}

	function getAdvertisement(advId) {
		$.ajax({
	        type:"get",
	        url:"../Index/getAdvertisement",
	        dataType:"json",
	        async:true,
	        data:{advertisementId: advId},
	        success:function(data){
	            if(data.code == "succ" && data.data.advertisementImgPath != null){
	                $("#advertisement" + advId).attr("src", data.data.advertisementImgPath);
	            }
	        },
	        error:function (XMLHttpRequest, textStatus, errorThrown){  
	        }
	    }); 
	}
</script>
<!--登录-->
<div class="loginbox" id="login-widow" style="display:none;">
  <div class="login_toptit">
     <div class="login_toptitname">账号登录</div>
     <div class="login_topclose" id="login_topclose"></div>
  </div>
  <div class="login_bot">
    <div class="login_botleft">
    	<form id="loginForm">
        <div class="one_login" style="display:block">
			<div class="logPword login_info"></div>
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/> 
			<input type="text" id="userName" name="userName" maxlength="16" class="loginput" placeholder="用户名/手机" datatype="username" nullmsg="请填写6-16位账户名或手机号" errormsg="请填写6-16位账户名或手机号" sucmsg=" "> 
			<input type="password" id="password" name="password" maxlength="20" class="loginput2" placeholder="密码" datatype="*6-20" nullmsg="请填写密码" errormsg="请填写6-20位字符组成的密码" sucmsg=" ">
			<!-- <div class="logPthree">
				<input type="text" id="vcode" name="vcode" class="loginput3" maxlength="4" placeholder="验证码" datatype="/^[\w\W]{4}$/" nullmsg="请填写验证码" errormsg="请填写4位验证码">
				<img src="images/verify_code.ashx.png" id="vcodesj" class="log_yzm">
				<span id="change_code">换一张</span>
			</div> -->
		</div>
		<div class="perlog" id="lw-submit" onclick="ajaxSubmit()">登&nbsp;&nbsp;录</div>
		<div class="log_botpassword">
			<a href="../ResetPassword/" class="logpass" target="_blank">忘记密码？</a> 
			<a href="../Account/Register" class="logreg" target="_blank">立即注册>></a>
		</div>
		</form>
	</div>
	<div class="login_botright">
      <!-- <p>可以使用以下方式登录</p>
      <p><a href=""><img src="../images/qq.png" class="logqq"></a><a href=""><img src="../images/wb.png" class="logwb"></a></p> -->
      <span><img style="width:150px;height:150px;"src="../images/weixin.jpg"></span>
      <span>官方微信公众号</span>
    </div>
  </div>
</div>
<div class="bg"></div>
<div class="top-bar" >
    <div class="commen-wrap h30">
        <p class="fl toplink">
           <span class="fl">唐山家庭教育网</span>
           <span class="top_line">|</span>
           <a id="smCode" href="javascript:;" class="wx_code">
               <img id="mxCode" src="../images/weixin.jpg" alt="微信">
           </a>
           <a href="" target="_blank" class="sina_link">访问新浪微博</a>
           <span class="top_line">|</span>
           <!--
           <a href="" target="_blank" class="top_link">云技术平台</a>
           <span class="top_line">|</span>
             -->
           <a href="AboutUs" target="_blank" class="top_link">关于我们</a>
           <span class="top_line">|</span>
           <a href="../Account/Personal" target="_blank" class="top_link">个人中心</a>
       </p>
       <label class="loginState">
          <a href="../Account/Register" class="reg fr">立即注册</a>
          <!-- 
          	暂时改成统一的登录界面，loadpublicfun.js中注释了设置click事件的代码，
          	将来改回现在这样的异步登录模式，要在spring security中处理两种登录模式 
          -->
     	  <sec:authorize access="!hasRole('ROLE_USER')">
     	   <a href="javascript:;" id="login-pop" class="login fr">登录</a>
     	  </sec:authorize>
     	  <sec:authorize access="hasRole('ROLE_USER')">
     	  <a href="#" onclick="document.getElementById('logout').submit();" class="reg fr">[注销]</a>
     	  <a href="../Admin/" id="login-pop" class="login fr"><sec:authentication property="name"/></a>     	  
     	  </sec:authorize>
     	  <c:url var="logoutUrl" value="/j_spring_security_logout" />
		  <form action="${logoutUrl}" id="logout" method="post">
			  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		  </form>
  	   </label>
   </div>
</div>
<div class="nav-wrap commen-wrap relative clearfix" >
    <a href="" class="fl logo">
        <img src="../images/logo.png" class="logo_img" alt="" />
        
        <span style="line-height:80px;font-size:16px;width:100px;margin-bottom:0px;" >唐山家庭教育网     
        </span>
        
    </a>
    
    <div class="nav-wrap-sub">
        <ul class="fl nav-list" id="nav-list">
        	
 			<c:if test="${not empty sessionScope.nav_menu}"> 
 				<c:forEach var="menu" items="${sessionScope.nav_menu}"> 
 					<c:choose>
 						<c:when test="${menu.moduleEnName=='Home'}"><li class="home"></c:when>
 						<c:otherwise>
 							<c:choose>
 								<c:when test="${not empty curTopModule && curTopModule.moduleId == menu.moduleId}"><li class="current"></c:when>
 								<c:otherwise><li></c:otherwise>
 							</c:choose>
 						</c:otherwise>
 					</c:choose>
 					
 					<c:if test="${menu.moduleTypeId==4}"><a href="${menu.moduleUrl}"></c:if>
 					<c:if test="${menu.moduleTypeId!=4}"><a href="../Index/ShowModule?moduleId=${menu.moduleId}"></c:if>
 					<span>${menu.moduleName}</span><i>${menu.moduleEnName}</i></a>
 					<c:if test="${not empty menu.childrenModule}">
 						<ul class="nav-sub-list nav-sub1">
 						<c:forEach var="submenu" items="${menu.childrenModule}">
 							<li>
		                        <c:if test="${submenu.moduleTypeId==4}"><a href="${submenu.moduleUrl}"></c:if>
 								<c:if test="${submenu.moduleTypeId!=4}"><a href="../Index/ShowModule?moduleId=${submenu.moduleId}"></c:if>
		                        <i>&#x25aa;</i><span>${submenu.moduleName}</span></a>
		                    </li>
 						</c:forEach>
 						</ul>
 					</c:if>
 					</li>
 				</c:forEach>
 			</c:if>
        </ul>
        
    </div>
  
  
</div>

<div class="nav-sub"  style="display: none;"></div>


