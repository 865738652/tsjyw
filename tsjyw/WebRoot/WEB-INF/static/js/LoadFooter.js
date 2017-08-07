$(document).ready(function () {
    //$.ajax({
    //    url: 'http://www.87870.com/2016/Control/Footer.html',
    //    type: 'get',
    //    dataType: 'html',
    //    success: function (data) {
    //        if (data != undefined) {
    //            if ($("#footer").length > 0) {
    //                $("#footer").html(data);
    //                //当点击跳转链接后，回到页面顶部位置
    //                $('#gotop').click(function () {
    //                    $("html, body").animate({ scrollTop: 0 }, 1000, "easeOutQuart");
    //                    return false;
    //                });
    //            }
    //        }
    //    },
    //    error: function (xhr, errorType, error) {
    //        //alert("error:" + error);
    //        //return false;
    //    },
    //});
    var htmlfoot = '<div class="footer bc">' +
                    '<p>' +
                    '<a href="http://www.ts-edu.gov.cn/" target="_blank">唐山市教育局' +
                    '</a>' +
                    ' | ' +
                    ' <a href="http://www.tswomen.org/" target="_blank">唐山市妇联' +
                    '</a>' +
                    ' | ' +
                    '<a href="http://ts.wenming.cn/" target="_blank">唐山市文明办' +
                    '</a>' +
                    ' | ' +
                    '<a href="" target="_blank">网站地图' +
                    '</a>' +
                    ' | ' +
                    '<a href="javascript:void(0);" target="_blank">冀ICP备16017061号-1</a>' +
                    '</p>' +
                    /*
                     '<p>' +
                     ' 冀ICP备16017061号-1 | <a href="http://www.miibeian.gov.cn/state/outPortal/loginPortal.action" target="_blank">京ICP备12034143号-3</a>  |  京公网安备11010602080011号  |  广播电视节目制作经营许可证：（京）字第04386号  |  <a href="http://www.87870.com/2016/images/2357-488.jpg" target="_blank">京网文(2015)2357-448号</a>' +
                     '</p>' +
                     */
                     '<p class="lineH24">' +
                     '唐山市睿智文化交流有限公司 Copyright © 2001-2016 www.tsjtjyw.com All rights reserved.' +
                     '</p>' +
                     '</div>' +
                     '<div id="share">' +
                     '<a id="gotop" title="">返回顶部</a>' +
                     
                     
                     '</div>';
                     //'<p class="creditszfw">' +
     //'<a id="___szfw_logo___" href="https://credit.szfw.org/CX20150817010781590127.html" target="_blank">' +
     //'<img src="http://icon.szfw.org/cert.png" border="0" />' +
     //'</a>' +
     //'</p>';
    $("#footer").html(htmlfoot);
    var $mxCode_foot = $('#mxCode_foot');
    $('#footer').on('mouseover', '#smCode_foot', function () {
        $mxCode_foot.show();
    });
    $('#footer').on('mouseout', '#smCode_foot', function () {
        $mxCode_foot.hide();
    });
    // (function () { document.getElementById("___szfw_logo___").oncontextmenu = function () { return false; } })();
});
