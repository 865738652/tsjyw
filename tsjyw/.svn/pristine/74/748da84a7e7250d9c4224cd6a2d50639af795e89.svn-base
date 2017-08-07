<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="zh-cmn-Hans">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <title>回答问题</title>
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
	            		url:"deleteNoticeAttach?attachUrl="+aa,
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
    
    
    
    	function responseQuestion()
    	{
    		var responObject = new Object();
    		responObject.respQuestionId = $("#questionId").val();
    		responObject.respQuestionContent = $("#respContent").val();
    		responObject.respQuestionAttachs = attachs;
    		$.ajax({
    			url:"startResponQuestion",
    			type:"post",
    			dataType:"json",
    			data:{ResponQuestionDetial:JSON.stringify(responObject)},
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
						alert("请求失败");
					}
    			},
    			error:function(){
    				$("#loadingToast").hide();
    			}
    		});
    	}
    </script>
</head>
<body ontouchstart>
<div>
    <div class="page__bd">
        <div class="weui-article">
            <h3><c:out value="${askQuestion.askName}"/></h3>
            <p><fmt:formatDate value='${askQuestion.askQuestionTime}' pattern='yyyy-MM-dd'/></p>
            <hr/>
            <section>
                <h2 class="title"><c:out value="${askQuestion.askQuestionTitle}"/></h2>
                <section>
                    <p>
                        <c:out value="${askQuestion.askQuestionContent}"   escapeXml="false"/>
                    </p>
                </section>
            </section>
            
            
            <c:if test="${not empty attachments_img}">
       			<c:forEach var="attachment" items="${attachments_img}">
       				<img class="noticealigncenter" src="${attachment.attachmentUrl}"/><br/>
       			</c:forEach>
       		</c:if>
       		<!-- 需要下载的附件 -->
       		<c:if test="${not empty attachments_other}">
       		<div class="page__bd">
		        <div class="weui-cells__title">通知-附件</div>
		        <div class="weui-cells weui-cells_radio">
		        	<c:forEach var="attachment" items="${attachments_other}">
		            <label class="weui-cell weui-check__label" for="x11">
		                <div class="weui-cell__bd">
		                    <p><a href="Wechatdownload?attachmentId=${attachment.attachmentId}"><c:out value="${attachment.attachmentName}"/></a></p>
		                </div>
		            </label>
		            </c:forEach>
		        </div>
		    </div>
		    </c:if>
       		<!-- /需要下载的附件 -->
                

            <c:if test="${not empty ageLevels}">            
	            <div class="button-sp-area">
	                年龄段：
               		<c:forEach var="ageLevel" items="${ageLevels}" varStatus="status">
               			<c:if test="${status.index % 3 == 0}">
               				<a href="javascript:;" class="weui-btn weui-btn_mini weui-btn_primary"><c:out value="${ageLevel.ageName}"/></a>
               			</c:if>
               			<c:if test="${status.index % 3 == 1}">
               				<a href="javascript:;" class="weui-btn weui-btn_mini weui-btn_default"><c:out value="${ageLevel.ageName}"/></a>
               			</c:if>
               			<c:if test="${status.index % 3 == 2}">
               				<a href="javascript:;" class="weui-btn weui-btn_mini weui-btn_warn"><c:out value="${ageLevel.ageName}"/></a>
               			</c:if>
               		</c:forEach>               
	            </div>
	        </c:if>    
	            
	        <c:if test="${not empty askQuestionTypeName}">
            <div class="button-sp-area">
                问题分类:
                <a href="javascript:;" class="weui-btn weui-btn_mini weui-btn_primary"><c:out value="${askQuestionTypeName}"/></a>
            </div>
            </c:if>
        </div>
        
        <input type="hidden" id="questionId" value="${askQuestion.askQuestionId}"/>
        <div class="weui-cells__title">回答内容</div>
        <div class="weui-cells weui-cells_form">
            <div class="weui-cell">
                <div class="weui-cell__bd">
                    <textarea class="weui-textarea" id="respContent" placeholder="回答内容" rows="3"></textarea>
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
    </div>
    
    <div class="page__bd page__bd_spacing">
	    <a href="javascript:" onclick="responseQuestion()" class="weui-btn weui-btn_primary">回答</a>
	</div>
    
    
    
        
    
    <!-- 成功失败提示框 -->
		
	    <div id="toast" style="display: none;">
	        <div class="weui-mask_transparent"></div>
	        <div class="weui-toast">
	            <i class="weui-icon-success-no-circle weui-icon_toast"></i>
	            <p class="weui-toast__content">&nbsp;&nbsp;&nbsp;已完成</p>
	        </div>
	    </div>

	    <div id="loadingToast" style="display:none;">
	        <div class="weui-mask_transparent"></div>
	        <div class="weui-toast">
	            <i class="weui-loading weui-icon_toast"></i>
	            <p class="weui-toast__content">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;回答中</p>
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
		
</div>
        <!-- @end 活动中心  add by hanjw 20150917-->     

        <script src="../js/wappv.js"></script>   
        <script src="../js/touch.min.js"></script>
        <%@include file="WeChatToolbar.jsp" %>
</body>
</html>
