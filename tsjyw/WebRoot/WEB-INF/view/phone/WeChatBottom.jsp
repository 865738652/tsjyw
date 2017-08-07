<%@ page language="java" contentType="text/html; charset=utf8" %>

<script type="text/javascript">
	function cancelLogin()
	{
		$("#cancelLogin").show();
	}
	function cancelLoginCancel()
	{
		$("#cancelLogin").hide();
	}
	function cancelLoginOK()
	{
		$.ajax({
			url:"CancelLogin",
			type:"get",
			success:function(){
				$("#dialog1").hide();
				location.reload(true);
			},
			error:function(){
				$("#dialog1").hide();
				location.reload(true);
			}
		});
	}
</script>



<div class="page__bd page__bd_spacing">
		<!--
        <div class="weui-footer weui-footer_fixed-bottom">
            <p class="weui-footer__links">
            	<c:if test="${empty sessionScope.userId}">
            		<a href="Login" class="weui-footer__link">登录</a>
                	<a href="javascript:home();" class="weui-footer__link">注册</a>
                	<a href="WeChatArticle" class="weui-footer__link">首页</a>
            	</c:if>
            	<c:if test="${not empty sessionScope.userId}">
            		<a href="javascript:home();" class="weui-footer__link"><c:out value="${sessionScope.userName}"/></a>
                	<a href="javascript:void(0);" onclick="cancelLogin()" class="weui-footer__link">注销</a>
                	<a href="WeChatArticle" class="weui-footer__link">首页</a>
            	</c:if>
                
            </p>
            <p class="weui-footer__text">Copyright &copy; 2008-2016 weui.io</p>
        </div>
        -->
        <div style="height:60px">
        </div>
        <div class="weui-footer weui-footer_fixed-bottom weui-tabbar">
                <a href="WeChatArticle" class="weui-tabbar__item weui-bar__item_on">
                    <img src="../phone/image/icon_tabbar.png" alt="" class="weui-tabbar__icon">
                    <p class="weui-tabbar__label">首页</p>
                </a>
                <a href="WeChatArticleType" class="weui-tabbar__item">
                    <img src="../phone/image/icon_tabbar.png" alt="" class="weui-tabbar__icon">
                    <p class="weui-tabbar__label">资讯</p>
                </a>
                <a href="WeChatCommonQuestion" class="weui-tabbar__item">
                    <img src="../phone/image/icon_tabbar.png" alt="" class="weui-tabbar__icon">
                    <p class="weui-tabbar__label">问答</p>
                </a>
                
                <c:if test="${empty sessionScope.userId}">
	                <a href="Login" class="weui-tabbar__item">
	                    <img src="../phone/image/icon_tabbar.png" alt="" class="weui-tabbar__icon">
	                    <p class="weui-tabbar__label">登录</p>
	                </a>                	
                </c:if>
                <c:if test="${not empty sessionScope.userId}">
	                <a href="javascript:;" class="weui-tabbar__item" onclick="cancelLogin()">
	                    <img src="../phone/image/icon_tabbar.png" alt="" class="weui-tabbar__icon">
	                    <p class="weui-tabbar__label">注销</p>
	                </a>                	
                </c:if>
        </div>
</div>



<div id="cancelLogin" style="display: none;">
    <div class="weui-mask"></div>
    <div class="weui-dialog">
        <div class="weui-dialog__hd"><strong class="weui-dialog__title">注销登录</strong></div>
        <div class="weui-dialog__bd">是否退出登录</div>
        <div class="weui-dialog__ft">
            <a href="javascript:;" class="weui-dialog__btn weui-dialog__btn_default" onclick="cancelLoginOK()">退出</a>
            <a href="javascript:;" class="weui-dialog__btn weui-dialog__btn_primary" onclick="cancelLoginCancel()">取消</a>
        </div>
    </div>
</div>


