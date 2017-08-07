	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
	<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
	
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>

	<link href='<c:url value="/css/bootstrap.min.css"/>' rel='stylesheet'>
	<link href='<c:url value="/css/bootstrap-cerulean.min.css"/>' rel="stylesheet">
    <link href='<c:url value="/css/charisma-app.css"/>' rel="stylesheet">
    <link href='<c:url value="/bower_components/fullcalendar/dist/fullcalendar.css"/>' rel='stylesheet'>
    <link href='<c:url value="/bower_components/fullcalendar/dist/fullcalendar.print.css"/>' rel='stylesheet' media='print'>
    <link href='<c:url value="/bower_components/chosen/chosen.min.css"/>' rel='stylesheet'>
    <link href='<c:url value="/bower_components/colorbox/example3/colorbox.css"/>' rel='stylesheet'>
    <link href='<c:url value="/bower_components/responsive-tables/responsive-tables.css"/>' rel='stylesheet'>
    <link href='<c:url value="/bower_components/bootstrap-tour/build/css/bootstrap-tour.min.css"/>' rel='stylesheet'>
    <link href='<c:url value="/css/jquery.noty.css"/>' rel='stylesheet'>
    <link href='<c:url value="/css/noty_theme_default.css"/>' rel='stylesheet'>
    <link href='<c:url value="/css/elfinder.min.css"/>' rel='stylesheet'>
    <link href='<c:url value="/css/elfinder.theme.css"/>' rel='stylesheet'>
    <link href='<c:url value="/css/jquery.iphone.toggle.css"/>' rel='stylesheet'>
    <link href='<c:url value="/css/uploadify.css"/>' rel='stylesheet'>
    <link href='<c:url value="/css/animate.min.css"/>' rel='stylesheet'>
    
    <!-- jQuery -->
    <script src='<c:url value="/js/jquery-1.9.1.min.js"/>'></script>
    <script src='<c:url value="/js/json2.js"/>'></script>
    <script src='<c:url value="/js/ajaxfileupload.js"/>'></script>
	<script src='<c:url value="/js/SimpleAjaxUploader.js"/>'></script>
    <script src='<c:url value="/bower_components/bootstrap/dist/js/bootstrap.min.js"/>'></script>
    
    <link rel="stylesheet" type="text/css" href='<c:url value="/select/select2.css"/>'>
    <script src='<c:url value="/select/select2.js"/>'></script>
    
    <link href='<c:url value="/bootstraptable/bootstrap-table.css"/>' rel='stylesheet'>
    <script src="<c:url value="/bootstraptable/bootstrap-table.min.js"/>"></script>
    <script src="<c:url value="/bootstraptable/bootstrap-table-zh-CN.js"/>"></script>

	
    <link href='<c:url value="/bootstrapdatepicker/bootstrap-datepicker3.min.css"/>' rel='stylesheet'>
    <script src='<c:url value="/bootstrapdatepicker/bootstrap-datepicker.min.js"/>'></script>
    <script src='<c:url value="/bootstrapdatepicker/bootstrap-datepicker.zh-CN.min.js"/>'></script>

	<link href='<c:url value="/bootstrapdatetimepicker/bootstrap-datetimepicker.min.css"/>' rel='stylesheet' media='screen'>
    <script src='<c:url value="/bootstrapdatetimepicker/bootstrap-datetimepicker.min.js"/>'></script>
    <script src='<c:url value="/bootstrapdatetimepicker/bootstrap-datetimepicker.zh-CN.js"/>'></script>
    
    <link href='<c:url value="/bootstraptreeview/bootstrap-treeview.css"/>' rel='stylesheet' media='screen'>
    <script src='<c:url value="/bootstraptreeview/bootstrap-treeview.js"/>'></script>
    
    <!-- The HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

<!-- library for cookie management -->
<script src="<c:url value="/js/jquery.cookie.js"/>"></script>
<!-- calender plugin -->
<script src='<c:url value="/bower_components/moment/min/moment.min.js"/>'></script>
<script src='<c:url value="/bower_components/fullcalendar/dist/fullcalendar.min.js"/>'></script>
<!-- data table plugin -->
<script src='<c:url value="/js/jquery.dataTables.min.js"/>'></script>

<!-- select or dropdown enhancer -->
<script src='<c:url value="/bower_components/chosen/chosen.jquery.min.js"/>'></script>
<!-- plugin for gallery image view -->
<script src='<c:url value="/bower_components/colorbox/jquery.colorbox-min.js"/>'></script>
<!-- notification plugin -->
<script src="<c:url value="/js/jquery.noty.js"/>"></script>
<!-- library for making tables responsive -->
<script src='<c:url value="/bower_components/responsive-tables/responsive-tables.js"/>'></script>
<!-- tour plugin -->
<script src='<c:url value="/bower_components/bootstrap-tour/build/js/bootstrap-tour.min.js"/>'></script>
<!-- star rating plugin -->
<script src='<c:url value="/js/jquery.raty.min.js"/>'></script>
<!-- for iOS style toggle switch -->
<script src='<c:url value="/js/jquery.iphone.toggle.js"/>'></script>
<!-- autogrowing textarea plugin -->
<script src='<c:url value="/js/jquery.autogrow-textarea.js"/>'></script>
<!-- multiple file upload plugin -->
<script src='<c:url value="/js/jquery.uploadify-3.1.min.js"/>'></script>
<!-- history.js for cross-browser state change on ajax -->
<script src='<c:url value="/js/jquery.history.js"/>'></script>
<!-- application script for Charisma demo -->
<script src='<c:url value="/js/charisma.js"/>'></script>

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

	function extendTalbeParams(param, formId) {
		$($("#" + formId).serializeArray()).each(function () {
			param[this.name] = this.value;
		});
	}
	
	function ajaxFileUpload(id, callback){
		var uploadUrl = "../uploadFile";
    	$.ajaxFileUpload({
	    	url:uploadUrl,
	    	secureuri:false,
	    	fileElementId:id,
	    	dataType:'json', 
	        success:function(data, status){ 
	        	if (callback != undefined) 
	        		callback(data);
	        },
	        error:function(data, status, e){ //服务器响应失败时的处理函数
				alert("status=" + status + ": " + e);
				alert("data=" + JSON.stringify(data));
	        }	
    	});
	}

	function initSamlpleUpload(id, onSuccess) {
		var btn = document.getElementById(id);
		var uploader = new ss.SimpleUpload({
			button: btn,
			url: '../uploadFile',
			name: 'file',
			multipart: true,
			customHeaders: { '${_csrf.headerName}': '${_csrf.token}' },
			onComplete: function( filename, response ) {
				var data = JSON.parse(response);
				if (onSuccess != undefined)
					onSuccess(data);
			},
			onError: function(filename, errorType, status, statusText) {
				alert(errorType + "," + statusText);
			}
		});
	}
	function initSamlpleExcelUpload(id,type,objectId,onSuccess)
	{
		var btn = document.getElementById(id);
		var uploader = new ss.SimpleUpload({
			button: btn,
			url: '../uploadExcelFile?excelType='+type+'&objectId='+objectId,
			name: 'file',
			multipart: true,
			customHeaders: { '${_csrf.headerName}': '${_csrf.token}'},
			onSubmit:function(){
				$("#waitWindow").modal('show');
			},
			onComplete: function( filename, response ) {
				$("#waitWindow").modal("hide");
				var data = JSON.parse(response);
				if (onSuccess != undefined)
					onSuccess(data);
			},
			onError: function(filename, errorType, status, statusText) {
				alert(errorType + "," + statusText);
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
</script>
