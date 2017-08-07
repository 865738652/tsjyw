<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh-cmn-Hans">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <title>我要提问</title>
    <%@include file="WeChatheader.jsp" %>
    
    <script type="text/javascript">
        window.onload = function(){
    		initSamlpleUpload("uploadimage",onSuccess);
    		$("#uploaderFiles").on("click", "li", function(){
	            $("#galleryImg").attr("style", this.getAttribute("style"));
	            //删除图片
	            var aa = this.id;
	            var node = this;
	            $("#deleteImage").on("click",function(){
	            	$.ajax({
	            		url:"deleteQuestionImage?attachUrl="+aa,
	            		dataType:"json",
	            		type:"get",
	            		success:function(data){
	            			if(data.code == "succ"){
	            				$("#gallery").fadeOut(100);
	            				node.parentNode.removeChild(node);
	            				deleteAttachInAttachs(aa);
	            			}
	            		}
	            	});
	            });
	            $("#gallery").fadeIn(100);
	        });
	        $("#gallery").on("click", function(){
	            $("#gallery").fadeOut(100);
	    	});
    	}
    	window.attachs = [];
	    var cur = null;
    	function onSuccess(data)
    	{
    		if (data == null || data == undefined) {
                alert("上传错误!");
                return;
            }
            cur = {};            
            cur.attachId = null;                   
            cur.attachName = data.data.attachName;
            cur.attachSize = data.data.attachSize;
            cur.attachUrl = data.data.attachUrl;
            attachs.push(cur);
            $("#uploaderFiles").append('<li class="weui-uploader__file" style="background-image:url('+cur.attachUrl+')" id="'+cur.attachUrl+'"></li>');
    	}
    	
        function deleteAttachInAttachs(aa)
        {
        	for(var i= 0;i<attachs.length;i++)
        	{
        		var a = attachs[i];
        		if(a.attachUrl == aa){
        			attachs.splice(i,1);
        			return;
        		}
        	}
        }
        
        
    	function startAskQuestion()
    	{
    		<c:if test="${empty sessionScope.userId}">;
    			$("#dialog1").show();
    			return;
    		</c:if>
    		
    		var askQuestionObject = new Object();
    		//获取年龄段
    		obj = document.getElementsByName("checkbox1");
		    check_val = [];
		    for(k in obj){
		        if(obj[k].checked)
		            check_val.push(obj[k].value);
		    }
		    obja = document.getElementsByName("IsOrNotPublic");
		    a = [];
		    for(j in obja){
		        if(obja[j].checked)
		        a.push(obja[j].value);
		    }
		    askQuestionObject.ageLevel = check_val;
		    askQuestionObject.askQuestionPublic = a;
		    askQuestionObject.askQuestionType = $("#askQuestionType").find("option:selected").val();
		    askQuestionObject.respUserId = $("#respUserId").val();
		    askQuestionObject.askQuestionTitle = $("#askQuestionTitle").val();
		    askQuestionObject.askQuestionContent = $("#askQuestionContent").val();
		    askQuestionObject.askQuestionAttach = attachs;
    		$.ajax({
    			url:"startAskQuestion",
    			type:"post",
    			data:{askQuestion:JSON.stringify(askQuestionObject)},
    			dataType:"json",
    			beforeSend:function(){
    				$("#loadingToast").show();
    			},
    			success:function(data){
    				if(data.code == "succ"){
    					$("#loadingToast").hide();
    					$("#toast").show();
    					window.setTimeout(function(){$("#toast").hide()},2000);
    					location.reload();
    				}
    				else{
    					$("#loadingToast").hide();
    				}                                                                                                                                                 				
    			},
    			error:function(){
    				$("#loadingToast").hide();
    				$("#dialog2").show();
    			return;
    			}
    		});
    	}
    	
    	function chooseChange()
    	{
    		$("#chooseQuestionType").html($("#askQuestionType").find("option:selected").text());
    	}
    	
    	function dialogHide()
    	{
    		$("#dialog1").hide();
    	}
    	function dialogHide()
    	{
    		$("#dialog2").hide();
    	}
    </script>
    
    
</head>
<body ontouchstart>
<div>
    <div class="page__bd">
		
		<input type="hidden" value="${respUserId}" id="respUserId"/>
		
		
		
		
		<c:if test="${not empty ageLevels}">
	        <div class="weui-cells__title">年龄阶段</div>
	        <div class="weui-cells weui-cells_checkbox">
	        	
	        	<c:forEach var="ageLevel" items="${ageLevels}">
		            <label class="weui-cell weui-check__label" for="${ageLevel.ageLevelId}">
		                <div class="weui-cell__hd">
		                    <input type="checkbox" class="weui-check" name="checkbox1" id="${ageLevel.ageLevelId}" value="${ageLevel.ageLevelId}">
		                    <i class="weui-icon-checked"></i>
		                </div>
		                <div class="weui-cell__bd">
		                    <p><c:out value="${ageLevel.ageName}"/></p>
		                </div>
		            </label>
	        	</c:forEach>
	        </div>
		</c:if>
        
        <!-- 问题分类 -->
        <div class="weui-cells">
            <div class="weui-cell weui-cell_select weui-cell_select-after">
                <div class="weui-cell__hd">
                    <label for="" class="weui-label">问题分类</label>
                </div>
                <div class="weui-cell__bd">
                    <select class="weui-select" name="select2" id="askQuestionType">
                    	<option>未分类</option>
                        <c:forEach var="askQuestionType" items="${askQuestionTypes}">
                        	<option value="${askQuestionType.askQuestionTypeId}"><c:out value="${askQuestionType.askQuestionTypeName}"/></option>
                        </c:forEach>
                    </select>
                </div>
            </div>
        </div>
        <!-- /问题分类 -->
        
        
        

        <div class="weui-cells__title">标题</div>
        <div class="weui-cells weui-cells_form">
            <div class="weui-cell">
                <div class="weui-cell__hd">
                    <label class="weui-label">提问标题</label>
                </div>
                <div class="weui-cell__bd">
                    <input class="weui-input" placeholder="请输入提问题目"  id="askQuestionTitle">
                </div>
            </div>
        </div>
        


        <div class="weui-cells__title">提问内容</div>
        <div class="weui-cells weui-cells_form">
            <div class="weui-cell">
                <div class="weui-cell__bd">
                    <textarea class="weui-textarea" placeholder="请输入提问内容" rows="3" id="askQuestionContent"></textarea>
                    <div class="weui-textarea-counter"><span>0</span>/200</div>
                </div>
            </div>
        </div>
		<!-- 附件 -->
		<div class="weui-gallery" id="gallery">
            <span class="weui-gallery__img" id="galleryImg"></span>
            <div class="weui-gallery__opr">
                <a href="javascript:" class="weui-gallery__del" id="deleteImage">
                    <i class="weui-icon-delete weui-icon_gallery-delete"></i>
                </a>
            </div>
        </div>

        <div class="weui-cells weui-cells_form">
            <div class="weui-cell">
                <div class="weui-cell__bd">
                    <div class="weui-uploader">
                        <div class="weui-uploader__hd">
                            <p class="weui-uploader__title">图片上传</p>
                            <div class="weui-uploader__info">0</div>
                        </div>
                        <div class="weui-uploader__bd">
                            <ul class="weui-uploader__files" id="uploaderFiles">
								
                            </ul>
                            <div class="weui-uploader__input-box">
                                <input id="uploadimage" class="weui-uploader__input" type="file" accept="image/*" multiple/>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
		<!-- 附件结束 -->








		<div class="weui-cells__title">是否公开</div>
        <div class="weui-cells weui-cells_form">
            <div class="weui-cell weui-cell_switch">
                <div class="weui-cell__bd">公开</div>
                <div class="weui-cell__ft">
                    <input class="weui-switch" type="checkbox" value="1" name="IsOrNotPublic" checked="checked"/>
                </div>
            </div>
        </div>




        <label for="weuiAgree" class="weui-agree">
            <input id="weuiAgree" type="checkbox" class="weui-agree__checkbox">
            <span class="weui-agree__text">
                阅读并同意<a href="javascript:void、
                (0);">《相关条款》</a>
            </span>
        </label>

        <div class="weui-btn-area">
            <a class="weui-btn weui-btn_primary" href="javascript:" id="showTooltips" onclick="startAskQuestion()">提问</a>
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
	            <p class="weui-toast__content">&nbsp;&nbsp;&nbsp;问题提交中</p>
	        </div>
	    </div>
	    
	    
	    <!-- 图片上传中 -->
	    <div id="ImageloadingToast" style="display:none;">
	        <div class="weui-mask_transparent"></div>
	        <div class="weui-toast">
	            <i class="weui-loading weui-icon_toast"></i>
	            <p class="weui-toast__content">&nbsp;&nbsp;&nbsp;图片上传中</p>
	        </div>
	    </div>
	    <!-- /图片上传中 -->
		<div id="dialog1" style="display: none;">
	        <div class="weui-mask"></div>
	        <div class="weui-dialog">
	            <div class="weui-dialog__hd"><strong class="weui-dialog__title">未登录</strong></div>
	            <div class="weui-dialog__bd">对不起，你没有登陆，请登录后在提问</div>
	            <div class="weui-dialog__ft">
	                <a href="javascript:;" class="weui-dialog__btn weui-dialog__btn_default" onclick="dialogHide()">留在本页</a>
	                <a href="Login" class="weui-dialog__btn weui-dialog__btn_primary">前去登录</a>
	            </div>
	        </div>
    	</div>
    	<!-- 填写不完整提示框 -->
    	<div id="dialog2" style="display: none;">
	        <div class="weui-mask"></div>
	        <div class="weui-dialog">
	            <div class="weui-dialog__hd"><strong class="weui-dialog__title">信息填写不完整</strong></div>
	            <div class="weui-dialog__bd">对不起，你填写的信息不完整，请认证检查后再提交</div>
	            <div class="weui-dialog__ft">
	                <a href="javascript:;" class="weui-dialog__btn weui-dialog__btn_default" onclick="dialogHide()">返回填写</a>
	            </div>
	        </div>
    	</div>
		<!--   -->
</div>
        <!-- @end 活动中心  add by hanjw 20150917-->     

        <script src="../js/wappv.js"></script>   
        <script src="../js/touch.min.js"></script>
        <%@include file="WeChatToolbar.jsp" %>
</body>
</html>