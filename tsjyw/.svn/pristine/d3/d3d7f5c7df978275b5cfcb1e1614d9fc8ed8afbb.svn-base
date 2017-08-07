$(document).ready(function () {
    $("#favP").click(function () {
        var text = $("#favP").html();
        if (text != "收藏") {
            return;
        }
        var cidvalue = $("#hiddenpid").val();
        var parms = {
            action: "favnews",
            cids: cidvalue,
            columnid: $("#hiddencolumnid").val(),
            dt: new Date()
        };
        var isfav = false;
        $.ajax({
            type: "post",
            url: "/Ajax/Ashx/News/Favorite_News.ashx",
            data: parms,
            dataType: "json",
            beforeSend: function () {
                $("#favP").html("操作中...");
            },
            success: function (data) {
                if (data != undefined) {
                    if (data.result == 1) {
                        //alert('收藏成功！');
                        $("#favP").html("已收藏");
                        isfav = true;
                    }
                    else if (data.result == -200) {
                        //alert('已收藏！');
                        $("#favP").html("已收藏");
                        isfav = true;
                        return false;
                    }
                    else if (data.result == -500) {
                        //location.href = 'http://www.87870.com/account/login.html?jumpurl=' + escape(location.href);
                        $("#login-widow").show();
                        $(".bg").show();
                        return false;
                    }
                    else {
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
                    $("#favP").html("已收藏");
                }
                else {
                    $("#favP").html("收藏");
                }
            }
        });
    });


    window.setTimeout(function () {
        favinit();
    }, 1000);

});
function fouceclick() {
    var text = $("#favoriteAuth").html();
    //alert(text);
    if (text != "订阅") {
        return;
    }
    var cidvalue = $("#hiddenpid").val();
    var parms = {
        action: "favadmin",
        cids: cidvalue,
        columnid: $("#hiddencolumnid").val(),
        dt: new Date()
    };
    //alert(2);
    var isfav = false;
    $.ajax({
        type: "post",
        url: "/Ajax/Ashx/News/Favorite_News.ashx",
        data: parms,
        dataType: "json",
        beforeSend: function () {
            $("#favoriteAuth").html("操作中...");
        },
        success: function (data) {
            if (data != undefined) {
                if (data.result == 1) {
                    //alert('订阅成功！');
                    $("#favoriteAuth").html("已订阅");
                    isfav = true;
                }
                else if (data.result == -200) {
                    //alert('已订阅！');
                    $("#favoriteAuth").html("已订阅");
                    isfav = true;
                    return false;
                }
                else if (data.result == -500) {
                    $("#login-widow").show();
                    $(".bg").show();
                    return false;
                }
                else {
                    alert('订阅失败，请刷新页面后再试！');
                    return false;
                }
            } else {
                alert('订阅失败，请刷新页面后再试！');
                return false;
            }
        },
        error: function (xhr, errorType, error) {
            alert('订阅失败，请刷新页面后再试！');
            return false;
        },
        complete: function () {
            if (isfav) {
                $("#favoriteAuth").html("已订阅");
            }
            else {
                $("#favoriteAuth").html("订阅");
            }
        }
    });
}
function favinit() {
    var cidvalue = $("#hiddenpid").val();
    var parms = {
        action: "favinit",
        cids: cidvalue,
        columnid: $("#hiddencolumnid").val(),
        dt: new Date()
    };
    var isfav = false;
    $.ajax({
        type: "POST",
        url: "/Ajax/Ashx/News/Favorite_News.ashx",
        data: parms,
        dataType: "json",
        beforeSend: function () {
        },
        success: function (data) {
            if (data != undefined) {
                if (data.result == 1) {
                    var favnum = data.favnum;
                    var dingnum = data.dingnum;
                    if (favnum > 0) {
                        $("#favP").html("已收藏");
                    }
                    if (dingnum > 0) {
                        $("#favoriteAuth").html("已订阅");
                    }
                }

            }
        },
        error: function (xhr, errorType, error) {

        }
    });
}