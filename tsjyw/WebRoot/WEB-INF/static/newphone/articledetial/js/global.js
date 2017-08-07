/// <reference path="../account/js/acc_public.js" />

$(function () {
	// 默认隐藏地址栏
	if(document.documentElement.scrollHeight <= document.documentElement.clientHeight) {
		var bodyTag = document.getElementsByTagName('body')[0];
		bodyTag.style.height = document.documentElement.clientWidth / screen.width * screen.height + 'px';
		setTimeout(function() {
			window.scrollTo(0, 1)
		}, 0);
	}
	
	/*http://www.open-open.com/news/view/fbe781*/
    var _UA_ = navigator.appVersion;
    var _UU_ = navigator.userAgent;
	var browser={
	   versions:function(){
	       return {
	            trident: _UU_.indexOf('Trident') > -1, 
	            presto: _UU_.indexOf('Presto') > -1, 
	            webKit: _UU_.indexOf('AppleWebKit') > -1, 
	            gecko: _UU_.indexOf('Gecko') > -1 && _UU_.indexOf('KHTML') == -1,
	            
	            webApp: _UU_.indexOf('Safari') == -1, 
	            weixin: _UU_.indexOf('MicroMessenger') > -1, 
	            qq: _UU_.match(/\sQQ/i) == " qq", 
	            
	            mobile: !!_UU_.match(/AppleWebKit.*Mobile.*/), 
	            ios: !!_UU_.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/), 
	            android: _UU_.indexOf('Android') > -1 || _UU_.indexOf('Linux') > -1, 
	            iPhone: _UU_.indexOf('iPhone') > -1 || _UU_.indexOf('Mac') > -1, 
	            iPad: _UU_.indexOf('iPad') > -1, 
	            
	            isqqBrowser: _UU_.indexOf('MQQBrowser') > -1 || _UU_.indexOf('QQBrowser') > -1, //QQHD
	            isucBrowser: _UU_.indexOf('UBrowser') > -1 || _UU_.indexOf('UCBrowser') > -1, //UC
	       };
	   }(),
	   language:(navigator.browserLanguage || navigator.language).toLowerCase()
	}
	
	if(!browser.versions.mobile){
		//window.location.href = "http://87870.com";
		swal("警告...", "请使用移动设备打开，或者开启浏览器F12模拟器！", "error");
	}
	
	// 初始化栅格系统
	lib.flexible.makeGridMode("750-12");
	
	touch.on($("body"), 'touchmove', function(e) {
	    //console.log(e.currentTarget.scrollTop);
	    var _off_on = e.currentTarget.scrollTop > 1043 ? "block" : "none";
	    if(e.currentTarget.scrollTop < 250){
	    	$("#goTop").css("display","none");
	    	return;
	    }
	    $("#goTop").css("display",_off_on);
	});
	touch.on($("#goTop"), 'touchend', function (e) {
	    $("body").scrollTop(0);
	    $("#goTop").css("display","none");
	    var _off_on = e.currentTarget.scrollTop == 0 ? "block" : "none";
	    //$("#header > .header-top").css("display",_off_on);
	    if (getCookie("info") == null || getCookie("info") == "") {
	        $(".login-warn").show();
	    }
	    e.stopPropagation();
	    return false;
	});
	/*点击logo去往首页*/
	$(".nav-logo").on("click",function(event){
		window.location.href = "/index.aspx";
	});
	
	/*点击打开菜单*/
	var flag=0;
	$(".nav-menu").on("click",function(event){
		// jQuery animate(params, [duration], [easing], [callback])
		$("#nav-mark").show();
		flag=1;
		$("#nav-menu").animate({display:"block",height: 'toggle', opacity: 'toggle'},"fast","swing");
	});
	
	$("#nav-mark").on("click",function(event){
		$("#nav-mark").hide();
		flag=0;
		$("#nav-menu").animate({display:"none",height: 'toggle', opacity: 'toggle'},"fast","swing");
	});
	document.addEventListener('touchmove', function (event) {
		//监听滚动事件
	    if(flag==1){
	    	//判断是遮罩显示时执行，禁止滚屏
	        event.preventDefault();//最关键的一句，禁止浏览器默认行为
	    }
	})
	//使用示例
	//s20是代表20秒
	//h是指小时，如12小时则是：h12
	//d是天数，30天则：d30
	//setCookie("name","hayden","s20");
	
	//	setCookie("name","hayden");
	//	alert(getCookie("name"));
	
	// 读取cookies
	function getCookie(name)
	{
		var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
		if(arr=document.cookie.match(reg))
		return unescape(arr[2]);
		else
		return null;
	}
	
	// 删除cookies
	function delCookie(name)
	{
		var exp = new Date();
		exp.setTime(exp.getTime() - 1);
		var cval=getCookie(name);
		if(cval!=null)
		document.cookie= name + "="+cval+";expires="+exp.toGMTString();
	}

	//设定自定义过期时间
	function setCookie(name,value,time)
	{
		if(time==null){time="h12";}
		var strsec = getsec(time);
		var exp = new Date();
		exp.setTime(exp.getTime() + strsec*1);
		document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString();
	}
	function getsec(str)
	{
		var str1=str.substring(1,str.length)*1;
		var str2=str.substring(0,1);
		if (str2=="s")
		{
			return str1*1000;
		}
		else if (str2=="h")
		{
			return str1*60*60*1000;
		}
		else if (str2=="d")
		{
			return str1*24*60*60*1000;
		}
	}
});