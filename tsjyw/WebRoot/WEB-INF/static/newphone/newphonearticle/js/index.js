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
        action: $("#hiddenmoduleId").val(),
        pageNumber: $.trim($("#hiddenpageindex").html()),
        pageSize:10,
        dt: new Date()
    };

    $.ajax({
        type: "POST",
        url: "WeChatGetMoreArticle",
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
                		var url = "WeChatArticleDetial?articleId="+data.rows[i].articleId;
                		var title = data.rows[i].articleTitle;
                		var img = data.rows[i].imagePath;
                		var model = data.rows[i].moduleName;
                		//console.log(getDateString(data.rows[i].articleTime));
                		//console.log(getLocalTime(data.rows[i].articleTime));
                		//var time = getLocalTime(data.rows[i].articleTime);
                		//var wappubTime = $$.Date.ParseJsonDate(data.rows[i].articleTime);
                		//var time = GetDateString(getLocalTime(data.rows[i].articleTime));
                		var time = getDateString(data.rows[i].articleTime);
                		var readCount = data.rows[i].articleReadCount;
                		html += '<li class="view-ul-li">';
                		html += '<a class="article_link" href="'+url+'" target="_self">';
                		html += '<div class="item-description">';
                		html += '<div class="description-title">';
                		html += '<p class="description-title-text">'+title+'</p>';
                		html += '</div>';
                		html += '<div class="description-info">';
                		html += '<div class="info-source fl">';
                		html += '<span>'+model+'</span>';
                		html += '<span>'+time+'</span>';
                		html += '</div>';
                		html += '<div class="info-look-count fr">';
                		html += '<img class="fl" src="../newphone/newphonearticle/img/tlak-icon.png">';
                		html += '<p class="fl">'+readCount+'</p>';
                		html += '</div></div></div>';
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

function getDateString(diffValue){
	var minute = 1000 * 60;
	var hour = minute * 60;
	var day = hour * 24;
	var halfamonth = day * 15;
	var month = day * 30;
	var now = new Date().getTime();	
	var diffValue = now - diffValue;
	var monthC =diffValue/month;
	var weekC =diffValue/(7*day);
	var dayC =diffValue/day;
	var hourC =diffValue/hour;
	var minC =diffValue/minute;
	if(monthC>=1){
		return "发表于" + parseInt(monthC) + "个月前";
	}
	else if(weekC>=1){
		return "发表于" + parseInt(weekC) + "周前";
	}
	else if(dayC>=1){
		return "发表于"+ parseInt(dayC) +"天前";
	}
	else if(hourC>=1){
		return "发表于"+ parseInt(hourC) +"个小时前";
	}
	else if(minC>=1){
		return "发表于"+ parseInt(minC) +"分钟前";
	}else
		return "刚刚发表";
}