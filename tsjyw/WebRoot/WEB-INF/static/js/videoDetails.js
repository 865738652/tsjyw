/// <reference path="jquery.js" />

var favurl="/Ashx/Favorite.ashx";
$(document).ready(function () {
    $(".sc_link,.sc").click(function () {
        //promptContent("ddd");
        var text = $(".sc_link").html();
        if (text != "收藏") {
            return;
        }
        var cidvalue = $("#hiddenpid").val();
        var parms = {
            action: "favnews",
            cids: cidvalue,
            dt: new Date()
        };
        var isfav = false;
        $.ajax({
            type: "POST",
            url:favurl ,
            data: parms,
            dataType: "json",
            beforeSend: function () {
                $(".sc_link").html("操作中...");
            },
            success: function (data) {
                if (data != undefined) {
                    //alert(data.result);
                    if (data.result == 1) {
                        alert('收藏成功！');
                        $(".sc_link").html("已收藏");
                        $(".sc").addClass("fav").removeClass("sc");
                        isfav = true;
                    }
                    else if (data.result == -200) {
                        alert('已收藏！');
                        $(".sc_link").html("已收藏");
                        $(".sc").addClass("fav").removeClass("sc");
                        isfav = true;
                        return false;
                    }
                    else if (data.result == -500) {
                        //location.href = 'http://www.87870.com/account/login.html?jumpurl=' + escape(location.href);
                        $("#login-pop").click();
                        return false;
                    }
                    else {
                        alert(data.result);
                        alert('收藏失败，请刷新页面后再试！');
                        return false;
                    }
                } else {
                    alert('收藏失败，请刷新页面后再试！');
                    return false;
                }
            },
            error: function (xhr, errorType, error) {
                alert('收藏失败，请刷新页面后再试！');
                return false;
            },
            complete: function () {
                if (isfav) {
                    $(".sc_link").html("已收藏");
                    $(".sc").addClass("fav").removeClass("sc");
                }
                else {
                    $(".sc_link").html("收藏");
                }
            }
        });
    });

    favinit();
});

//初始化收藏状态
function favinit() {
    var cidvalue = $("#hiddenpid").val();
    var parms = {
        action: "favinit",
        cids: cidvalue,
        dt: new Date()
    };
    var isfav = false;
    $.ajax({
        type: "POST",
        url: favurl,
        data: parms,
        dataType: "json",
        beforeSend: function () {
        },
        success: function (data) {
            if (data != undefined) {
                //alert(data.result);
                if (data.result == 1) {
                    var favnum = data.favnum;
                    var dingnum = data.dingnum;
                    if (favnum > 0) {
                        $(".sc_link").html("已收藏");
                        $(".sc").addClass("fav").removeClass("sc");
                    }
                }

            }
        },
        error: function (xhr, errorType, error) {

        }
    });
}