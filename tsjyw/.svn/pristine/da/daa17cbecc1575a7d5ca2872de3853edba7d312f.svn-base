<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <script type="text/javascript">
        var wapurl = "http://wap.87870.com/xiazai.html";
        if (/(iPhone|iPod|iOS)/i.test(navigator.userAgent)) {
            window.location.href = wapurl;
        } else if (/(Android)/i.test(navigator.userAgent)) {
            window.location.href = wapurl;
        }
    </script>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta name="baidu-site-verification" content="OtlGx8W5Eu" />
    <title>唐山家庭教育网</title>
    <meta name="keywords" content="心理,家教,志愿者" />
    <meta name="description" content="唐山家庭教育网" />
    <link href="../css/style.css" rel="stylesheet" />
    <link rel="stylesheet" type="text/css" href="../css/game.css" />
    <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=974e0cfe9ad49ccdf2d73e0a098b8239"></script>
    <script type="text/javascript" src="../js/jquery.js"></script>
    <!-- <script type="text/javascript" src="../js/zzsc.js"></script> -->
    <script type="text/javascript" src="../js/game.js"></script>
    <script type="text/javascript" class="resources library" src="../js/area.js" ></script>
    <script src="../js/lazyloadimg.js"></script>
    <script type="text/javascript">
       $(document).ready(function () {
       	   
		   $('.g_downTnav').on('click','span',function(event) {
				var $this =  $(this),
				$index = $this.attr("data-id"),
				$wrapper = $('div[id^=gameTop]');
				//$wrapperAM = $('A[id^=gamoreA]');
				var na = "gbg" + parseInt($index) % 5;			
		        $this.siblings().removeClass();
		        $this.addClass(na);
		        $wrapper.hide();
		        $wrapper.eq($index).show();
				//$wrapperAM.hide();
				//$wrapperAM.eq($index).show();
		   });
		   
		   loadVolunteer();
		   
		   var map;
		   map = new BMap.Map("baidumap", { enableMapClick: false }); // 创建Map实例
		   map.centerAndZoom(new BMap.Point(118.185599, 39.641674), 14);
		   map.enableScrollWheelZoom();
		   map.addControl(new BMap.NavigationControl());
		   map.addControl(new BMap.MapTypeControl({ mapTypes: [BMAP_NORMAL_MAP, BMAP_SATELLITE_MAP] }));
		   map.disableContinuousZoom();
			 
		   window.map = map; //将map变量存储在全局

		   //map.addEventListener("click", getXy);
		   $.ajax({
				url:"searchVolunteer",
				data: {ageLevelId: -1, askQuestionTypeId: -1},
				dataType:"json",
				success:function(data){
					if(data.code!="succ"){
						alert(data.data);
						return;
					}
					
					onSearchResult(data);					
				}
		   })
		
		   
		   getHotQuestion(1, 6, function(data) {
				if (data == null || data.rows.length == 0)
					return;
				
				var html = '';
				for (var i = 0; i < 6; i++) {                    
					if (i < data.rows.length) {
						html +='<div class="game_newsli">';
						html += '<a title="' + data.rows[i].askQuestionTitle + '" target="_blank" href="../AskQuestionManage/ShowQuestion?askQuestionId=' + data.rows[i].askQuestionId + '">' + data.rows[i].askQuestionTitle;
						html += '</a></div>';
					}
					else {
						html += '<div class="game_newsli">';
		                html += '<a title="《通用模拟器》让你在HoloLens重温儿时经典" target="_blank" href="http://news.87870.com/xinwennr-13983.html">《通用模拟器》让你在HoloLens重温儿时经典';
		                html += '</a></div>';    
					}
				}
				
				$(".game_newsul").html(html);
			});

			bindSelect("ageLevelId", "../Index/getAgeLevel", null, "ageLevelId", "ageName", "-1", "-1", "全部");
			bindSelect("askQuestionTypeId", "../Index/getAskQuestionType", {flag: 2}, "askQuestionTypeId", "askQuestionTypeName", "-1", "-1", "全部");
           //lazyLoadImg();
           
       });	     
       
       function initdata(id, askQuestionTypeId) {
       		var url = "../Index/getAllVolunteer";
		    var parms = {
		            pageNumber:1,
		            pageSize: 8,
		            dt: new Date()
		        };		    
		    
		    $.ajax({
		        url: url,
		        data: parms,
		        dataType: "json",
		        beforeSend: function () {
		            //$('#morelite').show();
		            //$('.news_botword').html("");
		        },
		        success: function (data) {
		            if (data != undefined && data.code == "succ") {
		                var listdata = data.rows;	                    	                    
	                    makehtml(id, listdata);
		            } 
		        },
		        error: function (xhr, errorType, error) {
		            //alert(error);
		            return false;
		        },
		        complete: function () {
		            
		        }
		    });
		} 
		
	    function makehtml(id, listdata) {
	    	var ctrlId = 'gameTop' + id;
            $('#' + ctrlId).html("");
            
            for (var index = 0; index < listdata.length; index++) {
            	var lista = listdata[index];
                var url = '../Index/ShowVolunteer?userId=' + lista.userId;
                var title = lista.userName;
                var imgurl = lista.userPhotoUrl == null ? "../images/no.jpg" : lista.userPhotoUrl;
                var intro = lista.volunteerIntro;
                
                var html = '';
               
                html += '<div class="g_downli">';
                html += '<div class="g_downliimg"><a target="_blank" href="' + url + '">';
                html += '<img width="260" height="163" src="' + imgurl + '"/></a></div>';
                html += '<div class="g_downliname"><a target="_blank" href="' + url + '">' + title + '</a></div>';
                html += '</div>';
               
                $('#' + ctrlId).append(html);                     
            }
	    }
	     
	    //创建marker
	    function onSearchResult(data) {
	        map.clearOverlays();
					
			//var info = new Array(); //存放提示信息窗口对象的数组  
           var marker = new Array(); //存放标注点对象的数组  	
           
           for (var i = 0; i < data.rows.length; i++) {  
           	var p0 = data.rows[i].volunteerAddrLng; //  
               var p1 = data.rows[i].volunteerAddrLat; //按照原数组的point格式将地图点坐标的经纬度分别提出来  
               if (p0 == null || p1 == null)
               	continue;
               
               point = new BMap.Point(p0, p1); //循环生成新的地图点  
               marker[i] = new BMap.Marker(point); //按照地图点坐标生成标记  
               map.addOverlay(marker[i]);  
               //marker[i].setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
               var info=new window.BMap.InfoWindow("<p style=’font-size:12px;lineheight:1.8em;’>" +"姓名：<a href='../Index/ShowVolunteer?userId=" + data.rows[i].userId + "'>"+ data.rows[i].userName + "</a></br>简介：" + data.rows[i].volunteerIntro.substr(0, 20) + "</br></p>"); // 创建信息窗口对象
               marker[i].myInfo = info;                    
           }  
           makehtml(0, data.rows);
           for(var i=0;i<marker.length;i++){  
				marker[i].addEventListener("click", function () {
					this.openInfoWindow(this.myInfo);
				});
			}  
	    }
	    
	    function onSearch(){
	    	var volunteerProvince=$("#volunteerProvince").val().trim();
	    	var volunteerCity=$("#volunteerCity").val().trim();
	    	var volunteerCounty=$("#volunteerCounty").val().trim();
	    	$("#volunteerPCC").val(volunteerProvince+volunteerCity+volunteerCounty);
	    	
			//alert($('#searchForm').serialize());
	    	$.ajax({
	    		url:"searchVolunteer",
	    		type:"post",
		    	dataType:"json",
		    	data:$('#searchForm').serialize(), 
	    		success:function(data){
					if(data.code!="succ"){
						alert(data.data);
						return;
					}
					$("#typeLabel").text($("#askQuestionTypeId").find("option:selected").text());
					onSearchResult(data);
				}
	    	});
			
			var search = new BMap.LocalSearch(volunteerCity, {
				onSearchComplete: function(result){
					if (search.getStatus() == BMAP_STATUS_SUCCESS){
						var res = result.getPoi(0);
						var point = res.point;
						map.centerAndZoom(point,14);
					}
				},
				onInfoHtmlSet:function(poi,html){
				}
			});	 
			search.search(volunteerCounty);
		 }
    </script>
</head>
<body class="game-body">
    <input type="hidden" id="columnindex" value="5" />
    <div id="header"><%@include file="header.jsp" %>
    </div>
    <div class="game_contain">
    	<div class="game_info" >
				<form id="searchForm" name="searchForm" method="post">
					<select id="volunteerProvince" name="volunteerProvince"></select>  
				    <select id="volunteerCity" name="volunteerCity" ></select>  
				    <select id="volunteerCounty" name="volunteerCounty"></select>
				    年龄阶段 <select id="ageLevelId" name="ageLevelId"></select>
				    专业领域 <select id="askQuestionTypeId" name="askQuestionTypeId"></select>
				    教师姓名 <input id="userName" name="userName" type="text" value="" />
				    <input id="volunteerPCC" name="volunteerPCC" type="hidden" />
				    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/> 
				    <script type="text/javascript">_init_area();</script>
				    <button type="button" class="btn btn-primary " onclick="onSearch()">搜索</button>
			    </form>
		</div>
        <div class="g_imgbox">
            <div id="baidumap" style="width:835px;height:450px;overflow:hidden;"></div>
            <!-- 
            <div class="de_imgAll">

                <div class="scroll_box_content" rel="scroll_box_content">

                    
                    <div class="deBimg_list">
                       
                    </div>
                    
                    
                </div>
            </div>
			-->
        </div>        
        <div class="g_TR">                        
            <a href="../ApplynManage/ShowMyApply" target="_blank">
                <img width="305" height="350" src="../images/8e848020-4361-4e81-b170-476ad5b32526.jpg" alt="《僵尸模拟训练》实况" />
            </a>            
        </div>     
        <div class="gamedown">
            <div class="g_downtop"><span>教师志愿者</span>
                <div class="g_downTnav">
                    <span id='typeLabel' class="gbg0" data-id="0">全部</span>
                    <c:forEach var="t" items="${askQuestionTypes}" begin="0" varStatus="status">
                    	<span data-id="${status.index + 1}">
                   			${t.askQuestionTypeName}
                   		</span> 					
	 				</c:forEach> 
                </div>
                <a href="../Index/MoreVolunteer" class="g_downmore dispnone" id="gamoreA1" style="display: block">更多>></a> 
            </div>
            <div class="g_downbot">
            	<div class="g_downBone" id="gameTop0"></div>
            	<c:forEach var="t" items="${askQuestionTypes}" begin="0" varStatus="status">	
                  	<div class="g_downBone" id="gameTop${status.index + 1}"></div>				
 				</c:forEach>                 
            </div>
        </div>
        
        <!--精品游戏推荐-->
        <div class="game_recome">
            <div class="game_recometop">精品专题讲座</div>
            <div class="game_recomebox">
            	<c:forEach var="r" items="${netcourse}" begin="0" end="3" varStatus="status">
                    <c:if test="${status.index == 0}"><div class="game_recomeli1"></c:if>
                    <c:if test="${status.index > 0}"><div class="game_recomeli2"></c:if>
	                    <a href="../Index/ShowNetCourse?netCourseId=${r.netCourseId}" target="_blank">
	                    	<c:if test="${status.index == 0}">
	                        <img width="365" height="284" src="${r.netCourseImgPath}" alt="${r.netCourseName}" />
	                        </c:if>	 
	                        <c:if test="${status.index > 0}">
	                        <img width="190" height="118" src="${r.netCourseImgPath}" alt="${r.netCourseName}" />
	                        </c:if>                   
	                    	<span>${r.netCourseName}</span>
	                    </a>
	                </div>
                </c:forEach> 
                <!-- 
                <div class="game_recomeli1">
                    <a href="http://d.87870.com/xiazainr-13707.html" target="_blank">
                        <img width="365" height="284" src="../images/69ba9c1c-fac3-4bf6-956a-d788e2e5aad8.jpg" alt="企鹅喧嚣（Penguin Hustle Vive）" /><span>企鹅喧嚣（Penguin Hustle Vive）</span></a>
                    
                </div>
                
                <div class="game_recomeli2">
                    <a href="http://d.87870.com/xiazainr-13743.html" target="_blank">
                        <img width="190" height="118" src="../images/411693b2-d95e-4881-85d5-97a3c1b6b98b.jpg" alt="人类营救计划" /><span>人类营救计划</span></a>
                     
                </div>
                
                <div class="game_recomeli2">
                    <a href="http://d.87870.com/xiazainr-13708.html" target="_blank">
                        <img width="190" height="118" src="../images/a7e705d4-710f-4d54-817c-b205391124c9.jpg" alt="方块射击" /><span>方块射击</span></a>
                     
                </div>
                
                <div class="game_recomeli2">
                    <a href="http://d.87870.com/xiazainr-13636.html" target="_blank">
                        <img width="190" height="118" src="../images/c2cd882b-a52f-4e41-b626-00a920577e1f.jpg" alt="BAMF" /><span>BAMF</span></a>
                     
                </div>
                
                <div class="game_recomeli2">
                    <a href="http://d.87870.com/xiazainr-13711.html" target="_blank">
                        <img width="190" height="118" src="../images/b445e7b4-567e-4e10-b265-858c0a790745.jpg" alt="滑雪峰会" /><span>滑雪峰会</span></a>
                     
                </div>
                 -->
            </div>
        </div>
        <div class="game_news">
            <div class="game_newstitle">最新提问</div>
            <div class="game_newsul">
                
                <div class="game_newsli">
                    <a title="《通用模拟器》让你在HoloLens重温儿时经典" target="_blank" href="http://news.87870.com/xinwennr-13983.html">
                        《通用模拟器》让你在HoloLens重温儿时经典
                    </a>
                </div>                
            </div>
        </div>
        

    </div>
    <!--bot-->
    <div class="clear">
    </div>
    <div id="footer" class="mt20"></div>
    <script src="../js/LoadFooter.js"></script>
    <script src="../js/loadpublicfun.js"></script>
    <div class="displayN">
    <script>
        var _hmt = _hmt || [];
        (function () {
            var hm = document.createElement("script");
            hm.src = "//hm.baidu.com/hm.js?a96d250ab37bce0300949f861327c0a0";
            var s = document.getElementsByTagName("script")[0];
            s.parentNode.insertBefore(hm, s);
        })();
    </script>
        <script>
            var _hmt = _hmt || [];
            (function () {
                var hm = document.createElement("script");
                hm.src = "//hm.baidu.com/hm.js?225694c67b2c1d267db850fac9dd0170";
                var s = document.getElementsByTagName("script")[0];
                s.parentNode.insertBefore(hm, s);
            })();
</script>
<script src="http://s11.cnzz.com/z_stat.php?id=1256099391&web_id=1256099391" language="JavaScript"></script>
<script>

(function () {

    var bp = document.createElement('script');

    bp.src = '//push.zhanzhang.baidu.com/push.js';

    var s = document.getElementsByTagName("script")[0];

    s.parentNode.insertBefore(bp, s);

})();
       
	function loadVolunteer() {   
		initdata(0, -1);           
	<c:forEach var="t" items="${askQuestionTypes}" begin="0" varStatus="status">
		initdata(${status.index + 1}, ${t.askQuestionTypeId});
	</c:forEach>
	}
</script>
    </div>
</body>
</html>
