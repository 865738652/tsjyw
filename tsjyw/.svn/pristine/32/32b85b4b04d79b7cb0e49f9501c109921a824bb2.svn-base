var $load_more_xgtj;
var xgtjPageIndex = 1;
var xgtjPageCount = 1;
$(function () {
    $load_more_xgtj = $("#load-more-xgtj");

    /*加载更多相关推荐*/
    xgtjlist();
    $load_more_xgtj.click(function (event) {
        xgtjlist();
    });

});


function xgtjlist() {

    if ($("#hiddenpid").val() == "" || $("#hiddenpid").val() == "0") {
        return;
    }
    if (xgtjPageIndex > xgtjPageCount) {
        return;
    }

    var parms = {
        action: "xgtjList",
        id: $("#hiddenpid").val(),
        columnid: $("#hiddencolumnid").val(),
        pageIndex: xgtjPageIndex,
        pageSize: 5,
    };
    $load_more_xgtj.html("加载中...");
    $.ajax({
        type: "get",
        url: "/ashx/list.ashx?date=" + new Date().valueOf(),
        data: parms,
        dataType: "json",
        success: function (data) {
            xgtjPageCount = parseInt(data.pageCount);
            if (data == undefined || data.list == undefined || data.list.length == 0 || data.status != 1) {
                if (xgtjPageIndex == 1) {
                    $load_more_xgtj.parent().hide();
                }
                $load_more_xgtj.remove();
                return;
            }

            if (xgtjPageIndex >= xgtjPageCount) {
                $load_more_xgtj.remove();
            }
            $load_more_xgtj.html("加载更多...");

            var html = '';
            $(data.list).each(function (index, item) {
                var wappubTime = $$.Date.ParseJsonDate(item.webpubTime);
                var dateString = GetDateString(wappubTime);

                html += '<li>';
                html += '<a class="article_link" href="newsdetail.aspx?id=' + item.commonid + '" target="_self">';
                html += '<div class="item-description">';
                html += '<div class="description-title">';
                html += '    <p>' + item.name + '</p>';
                html += '</div>';
                html += '<div class="description-info">';
                html += '    <div class="info-source fl">';
                html += '        <span></span>';
                html += '        <span>' + dateString + '</span>';
                html += '    </div>';
                html += '    <div class="info-look-count fr">';
                html += '        <img class="fl" src="images/tlak-icon.png" />';
                html += '        <p class="fl">' + item.discuss + '</p>';
                html += '    </div>';
                html += '</div>';
                html += '</div>';
                html += '<div class="item-picture">';

                //视频
                if (item.columnid == 2) {
                    html += '    <span class="play-tag"></span>';
                }

                html += '    <img src="' + item.imgurl + item.img + '" />';//"218x135_"
                html += '</div>';
                html += '</a>';
                html += '</li>';
            });
            //alert(html);
            html = $(html);
            html.find("img").error(function () {
                $(this).attr('src', "http://www.87870.com/images/no.jpg");
            });

            $(".recommend").append(html);
            xgtjPageIndex++;
        },
        error: function (xhr, errorType, error) {
            //alert('加载失败:' + error);
            return false;
        }
    });
}