<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link href='<c:url value="/phone/css/picture_style.css"/>' rel="stylesheet" />
<link href='<c:url value="/phone/css/picture_jquery.excoloSlider.css"/>' rel="stylesheet" />
<link href='<c:url value="/phone/css/weui.min.css"/>' rel='stylesheet'>
<link href='<c:url value="/phone/css/weui.css"/>' rel='stylesheet'>
<link href='<c:url value="/phone/css/wechat.css"/>' rel='stylesheet'>

<link href='<c:url value="/phone/css/picture_reset.css"/>' rel="stylesheet" />
<!-- 只要引入这个文件li标签不能用 ，但是logo的标签不能用了 -->
<!-- <link href='<c:url value="/phone/css/picture_style.css"/>' rel="stylesheet" />  -->
<noscript>
    <link rel="stylesheet" href='<c:url value="/phone/css/mobile.min.css"/>' />
</noscript>

<link href="http://alexgorbatchev.com/pub/sh/current/styles/shThemeDefault.css" rel="stylesheet" type="text/css" />




<script src='<c:url value="/phone/js/jquery-1.9.1.min.js"/>'></script>
<script src='<c:url value="/phone/js/zepto.js"/>'></script>
<script type="text/javascript" src="https://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script src='<c:url value="/phone/js/wechat.js"/>'></script>
<script src='<c:url value="/phone/js/area.js"/>'></script>
<script src='<c:url value="/js/SimpleAjaxUploader.js"/>'></script>

<script type="text/javascript">
	function initSamlpleUpload(id, onSuccess) {
		var btn = document.getElementById(id);
		var uploader = new ss.SimpleUpload({
			button: btn,
			url: 'uploadFile',
			name: 'file',
			multipart: true,
			onSubmit:function(){
				$("#ImageloadingToast").show();
			},
			onComplete: function( filename, response ) {
				var data = JSON.parse(response);
				if (onSuccess != undefined)
				{
					onSuccess(data);
					$("#ImageloadingToast").hide();
				}	
			},
			onError: function(filename, errorType, status, statusText) {
				$("#ImageloadingToast").hide();
				alert(errorType + "," + statusText);
			}
		});
	}


</script>


