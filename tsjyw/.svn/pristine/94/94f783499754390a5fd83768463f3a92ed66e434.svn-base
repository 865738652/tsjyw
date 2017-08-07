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
    <title>发送通知</title>
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
    
    
    
    	function sendNotice()
    	{
    		var noticeObject = new Object();
    		noticeObject.noticeAcceptId =  $("#objectId").find("option:selected").val();
    		noticeObject.noticeAcceptTypeId = $("#objectType").val();
    		noticeObject.noticeTitle = $("#noticeTitle").val();
    		noticeObject.noticeRequireCheck = $("#requireCheck").val();
    		noticeObject.noticeContent = $("#noticeContent").val();
    		noticeObject.noticeAttachs = attachs;
    		//alert(JSON.stringify(noticeObject));
    		$.ajax({
    			url:"startSendNotice",
    			type:"post",
    			dataType:"json",
    			data:{noticeDetial:JSON.stringify(noticeObject)},
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
        <div class="weui-cells__title">选择</div>
        <div class="weui-cells">
			
            <div class="weui-cell weui-cell_select weui-cell_select-after">
                <div class="weui-cell__hd">
                    <label for="" class="weui-label">选择<c:out value="${name}"/></label>
                    <input type="hidden" value="${type}" id="objectType"/>
                </div>
                <div class="weui-cell__bd">
                    <select class="weui-select" name="select2" id="objectId">
                    	<c:choose>
                    		<c:when test="${type == '3'}">
                    			<c:forEach var="schoolClass" items="${recvObjectList}">
                    				<option value="${schoolClass.schoolClassId}"><c:out value="${schoolClass.schoolClassName}"/></option>
                    			</c:forEach>
                    		</c:when>
                    		<c:when test="${type == '2'}">
                    			<c:forEach var="grade" items="${recvObjectList}">
                    				<option value="${grade.gradeId}"><c:out value="${grade.gradeName}"/></option>
                    			</c:forEach>
                    		</c:when>
                    		<c:when test="${type == '1'}">
                    			<c:forEach var="school" items="${recvObjectList}">
                    				<option value="${school.schoolId}"><c:out value="${school.schoolName}"/></option>
                    			</c:forEach>
                    		</c:when>
                    		<c:otherwise>
                    			<option value="1">121212</option>
                    		</c:otherwise>
                    	</c:choose>
                    </select>
                </div>
            </div>
        </div>


        <div class="weui-cells__title">通知正文</div>
        <div class="weui-cells weui-cells_form">
            <div class="weui-cell">
                <div class="weui-cell__hd"><label class="weui-label">通知标题</label></div>
                <div class="weui-cell__bd">
                    <input class="weui-input" id="noticeTitle" placeholder="请输入通知标题"/>
                </div>
            </div>
        </div>
        
        
        
        
        <div class="weui-cells__title">是否要求查阅</div>
        <div class="weui-cells weui-cells_form">
            <div class="weui-cell weui-cell_switch">
                <div class="weui-cell__bd">要求查阅</div>
                <div class="weui-cell__ft">
                    <input class="weui-switch" id="requireCheck" type="checkbox" value="1"/>
                </div>
            </div>
        </div>

        <div class="weui-cells__title">通知内容</div>
        <div class="weui-cells weui-cells_form">
            <div class="weui-cell">
                <div class="weui-cell__bd">
                    <textarea class="weui-textarea" id="noticeContent" placeholder="请输入通知内容" rows="3"></textarea>
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
        
        
        <label for="weuiAgree" class="weui-agree">
            <input id="weuiAgree" type="checkbox" class="weui-agree__checkbox">
            <span class="weui-agree__text">
                阅读并同意<a href="javascript:void(0);">《相关条款》</a>
            </span>
        </label>

        <div class="weui-btn-area">
            <a class="weui-btn weui-btn_primary" href="javascript:" id="showTooltips" onclick="sendNotice()">开始发送</a>
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
	            <p class="weui-toast__content">&nbsp;&nbsp;&nbsp;通知发送中</p>
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
		
		
		<!--   -->
    
    
</div>
        <!-- @end 活动中心  add by hanjw 20150917-->     

        <script src="../js/wappv.js"></script>   
        <script src="../js/touch.min.js"></script>
        <%@include file="WeChatToolbar.jsp" %>
</body>
</html>