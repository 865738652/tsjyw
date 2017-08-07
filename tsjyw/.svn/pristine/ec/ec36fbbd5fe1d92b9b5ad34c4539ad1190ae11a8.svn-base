<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="zh-cmn-Hans">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <title>常见问题</title>
	<%@include file="WeChatheader.jsp" %>
	<script type="text/javascript">
		window.onload=function(){
			$("#start").val(1);
			getCommonQuestion();

		} 
		function showMore()
		{
			getCommonQuestion();
		}
		
		function getCommonQuestion()
		{
			var size = 8;
			alert($("#start").val());
			var start = $("#start").val();
			$.ajax({
				url:"getCommonQuestion?start="+start+"&size="+size,
				type:"get",
				dataType:"json",
				beforeSend:function(){
					$("#loadingToast").show();
				},
				success:function(data){
					if(data.code == "succ")
					{
						for(var i=0;i<data.data.length;i++)
						{
							$("#commonQuestionList").append("<div class=\"weui-media-box weui-media-box_text\">"
										+"<h4 class=\"weui-media-box__title\">"+data.data[i].askQuestionTitle+"</h4>"
										+"<p class=\"weui-media-box__desc\">"+data.data[i].askQuestionContent+"</p>"
										+"<ul class=\"weui-media-box__info\">"
										+"<li class=\"weui-media-box__info__meta\">"+data.data[i].askQuestionTypeName+"</li>"
										+"<li class=\"weui-media-box__info__meta weui-media-box__info__meta_extra\">"
										+"<a href=\"WeChatLookQuestion?askQuestionId="+data.data[i].askQuestionId+"\">详情信息</a>"+"</li>"
										+"</ul></div>"
							);
						}
						if(data.data.length < size)
						{
							$("#commonQuestionList").append("暂无数据");
							$("#more").hide();
						}
						else
							$("#start").val(Number(start)+1);
					}
					else
						alert("加载失败");
					$("#loadingToast").hide();
					//$("#toast").show();
					//window.setTimeout(function(){$("#toast").hide()},500);
				},
				error:function(){
					$("#loadingToast").hide();
					alert("加载失败");
				}
			});
		}
	</script>
</head>
 <body ontouchstart>
    <div>
    <div class="page__bd">
        <div class="weui-panel">
            <div class="weui-panel__hd">常见问题</div>
            <div class="weui-panel__bd" id="commonQuestionList">
            </div>
            <div class="weui-panel__ft" id="more">
                <a href="javascript:void(0);" class="weui-cell weui-cell_access weui-cell_link">
                	<input type="hidden" id="start" value="1"/>
                    <div class="weui-cell__bd" onclick="showMore()">查看更多</div>
                    <span class="weui-cell__ft"></span>
                </a>    
            </div>
        </div>
    </div>
        <!-- 成功失败提示框 -->
		
	    <div id="toast" style="display: none;">
	        <div class="weui-mask_transparent"></div>
	        <div class="weui-toast">
	            <i class="weui-icon-success-no-circle weui-icon_toast"></i>
	            <p class="weui-toast__content">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;已完成</p>
	        </div>
	    </div>

	    <div id="loadingToast" style="display:none;">
	        <div class="weui-mask_transparent"></div>
	        <div class="weui-toast">
	            <i class="weui-loading weui-icon_toast"></i>
	            <p class="weui-toast__content">&nbsp;&nbsp;&nbsp;数据加载中</p>
	        </div>
	    </div>
    <%@include file="WeChatBottom.jsp" %>
	</div>
	        <!-- @end 活动中心  add by hanjw 20150917-->     

        <script src="../js/wappv.js"></script>   
        <script src="../js/touch.min.js"></script>
        <%@include file="WeChatToolbar.jsp" %>
  </body>
</html>
