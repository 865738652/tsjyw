//init加载
$(document).ready(function () {
    $("#footer").hide();
    listinit();
    dataload();
})
//加载开始
function dataload() {
    $(".view ul").DataLazyLoad({
        load: function (page, unLocked) {
            var max = 10000;
            listinit();
            page = page >= max ? 0 : page + 1;
            unLocked(page);
        }
    });
}
//数据加载
function listinit() {
    var parms = {
        action: "indexlistinit",
        pageNumber: $.trim($("#hiddenpageindex").html()),
        pageSize:10,
        dt: new Date()
    };

    $.ajax({
        type: "POST",
        url: "getMoreFamousTeacher",
        data: parms,
        dataType: "json",
        beforeSend: function () {
            $("#load-more").show();
        },
        success: function (data) {
            if (data != undefined) {
            	html = '';
                if (data.code == "succ"){
                	for(var i=0;i<data.rows.length;i++)
                	{
                		//console.log(data.rows[i]);
                		var url = "WeChatFamousTeacherDetial?famousTeacherId="+data.rows[i].userId;
                		var title = data.rows[i].userName + "<br/>" + data.rows[i].famousTeacherIntro;
                		var img = data.rows[i].userPhotoUrl;
                		html += '<li class="view-ul-li">';
                		html += '<a class="article_link" href="'+url+'" target="_self">';
                		html += '<div class="item-description">';
                		html += '<div class="description-title">';
                		html += '<p class="description-title-text">'+title+'</p>';
                		html += '</div>';
                		html += '<div class="description-info">';
                		html += '<div class="info-source fl">';
                		html += data.rows[i].askQuestionTypeNames;
                		html += '</div>';
                		html += '</div></div>';
                		html += '<div class="item-picture">';
                		html += '<img src="'+img+'">';
                		html += '</div></a></li>';
                	}
                }
                //$(html).appendTo(".view ul");
                $("#load-more").before(html);
                var pags = parseInt($("#hiddenpageindex").html()) + 1;
                $("#hiddenpageindex").html(pags);
                $("#load-more").hide();
                //loadingImg();
                //$(".mask").remove();
                //if (data.length <= 8 && data.length > 0) {
                //    $("#loading").html("加载完了");
                //}
            }
        },
        error: function (xhr, errorType, error) {
            $("#load-more").hide();
        },
        complete: function () {
            $("#load-more").hide();
        }
    });
}
function getLocalTime(nS) {     
	   return new Date(parseInt(nS)).toLocaleString().replace(/年|月/g, "-").replace(/日/g, " ");       
	}     