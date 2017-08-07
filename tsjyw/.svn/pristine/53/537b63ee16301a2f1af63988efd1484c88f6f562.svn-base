
var pageindex = 0;

function addcomment() {
	
	//alert("1111111111111");
    var cmt = $("#words").val();
    if (cmt.length < 2) {
        alert("评论内容不能低于2字符！");
        return;
    }
    if (cmt.length > 500) {
        alert("评论字数不能超过500字符");
        return;
    }

    var parms = {
        commentContent: escape(cmt),
        articleId: $("#hiddenpid").val(),
        commentTypeId: 1,
    };
    
    if ($("#hiddencsrf").attr("name") != "")
    	parms[$("#hiddencsrf").attr("name")] = $("#hiddencsrf").val();
    
    $.ajax({
    	
        type: "post",
        url: "../Index/createComment",
        data: parms,
        dataType: "json",
        beforeSend: function () {
            $("#btn_commentadd").val("正在提交...");
            $("#btn_commentadd").attr("disabled", true);
        },
        success: function (data) {
            if (data.code != "succ") {
            	var cnum = parseInt($(".discussnum").html()) + 1;
                //$(".discussnum").html(cnum);
                $("#commentlistall dl").remove();
				alert("评论失败，请稍后再试");  
            }
            pageindex = 0;
			commentlist();
			$("#words").val("");
        },
        error: function (xhr, errorType, error) {
            alert(error);
            return false;
        },
        complete: function () {
            $("#btn_commentadd").val("提交");
            $("#btn_commentadd").attr("disabled", false);
        }
    });
}
$(document).ready(function () {
    window.setTimeout(function () {
    	commentlist();
    }, 500);
    $("#initmore").click(function () {
        commentlist();
    });
});

function commentlist() {
    $("#nomore").hide();
    
    //alert($("#cmtindex").html());
    if ($("#hiddenpid").val() == "" || $("#hiddenpid").val() == "0") {
        return;
    }
    var parms = {
        articleId: $("#hiddenpid").val(),//$("#hiddenpid").val()
		commentTypeId: $("#commentTypeId").val(),
        pageNumber: ++pageindex,
        pageSize: 10
    };
    if ($("#hiddencsrf").attr("name") != "")
    	parms[$("#hiddencsrf").attr("name")] = $("#hiddencsrf").val();
	
    $.ajax({
        type: "post",
        url: "../Index/getAllCommentByArticle",
        data: parms,
        dataType: "json",
        cache:false,
        beforeSend: function () {
            
       },
        success: function (data) {
            if (data != undefined) {
                if (data.code == "succ") {
                    //alert($(data.comment).length);
					var pagecount = data.total;
                    
                    if (pagecount > pageindex && pagecount > 1) {
                        $("#initmore").show();
                    }
                    else {
                        if (pageindex > 1) {
                            $("#nomore").show();
                        }
                        $("#initmore").hide();
                    }
					if (pageindex == 1)
						$("#commentlistall").html("");

                   $(data.rows).each(function (index, lista) {
                        var htmlcmt = '';
                       htmlcmt += '<dl class="comment_list clearfix">';
                        htmlcmt += '<dt>';
                        htmlcmt += '    <img src="../images/touxiang.jpg" />';
                        htmlcmt += ' </dt>';
                        htmlcmt += '<dd>';
                       htmlcmt += '    <p>';
                       htmlcmt += '       <span class="ploneRname">' + (lista.userName == null ? '游客' : lista.userName) + '</span>';
                        htmlcmt += '        <span class="ploneTime">' +getSmpFormatDateByLong( lista.commentTime,true) + '</span>';
                       htmlcmt += '    </p>';
                       htmlcmt += '    <p class="ploneRword">';
                        htmlcmt += '        ' +unescape(lista.commentContent) + '';
                       htmlcmt += '     </p>';
                       htmlcmt += ' </dd>';
                        htmlcmt += '</dl>';
                        $("#commentlistall").append(htmlcmt);
                    });
                    //$("#discussnum").html(data.total_item);
                }
            }
        },
       error: function (xhr, errorType, error) {
            alert('加载失败，请刷新页面后再试！');
            return false;
        }
    });
}

