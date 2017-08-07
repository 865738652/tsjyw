//截取字符串
function cutstr(str, len) {
    var str_length = 0;
    var str_len = 0;
    str_cut = new String();
    str_len = str.length;
    for (var i = 0; i < str_len; i++) {
        a = str.charAt(i);
        str_length++;
        if (escape(a).length > 4) {
            //中文字符的长度经编码之后大于4
            str_length++;
        }
        str_cut = str_cut.concat(a);
        if (str_length >= len) {
            str_cut = str_cut.concat("...");
            return str_cut;
        }
    }
    //如果给定字符串小于指定长度，则返回源字符串；
    if (str_length < len) {
        return str;
    }
}
//判断Class是否存在
function isExitsFunction(funcName) {
    try {
        if (typeof (eval(funcName)) == "function") {
            return true;
        }
    } catch (e) { }
    return false;
}
//加载数据
function loadData() {
    //var myTotalCount = parseInt($("#TotalCount").val());
    //var myPageSize = parseInt($("#PageSize").val());
    //var myPageCount = myTotalCount % myPageSize > 0 ? (myTotalCount / myPageSize) + 1 : (myTotalCount / myPageSize);
    //$("#PageCount").val(myPageCount);
    //var myPageIndex = parseInt($("#PageIndex").val());
    if (isExitsFunction("initdata")) {
        initdata();
    }
}
//加载分页HTML
function loadhtml() {
    var pagehtml = '';
    var PageIndex = parseInt($("#PageIndex").val());
    var PageCount = parseInt($("#PageCount").val());
    var myIndexprev = PageIndex > 1 ? PageIndex - 1 : 1;
    var myIndexnext = PageIndex < PageCount ? PageIndex + 1 : PageCount;
    var Pagelist = 10;
    var startnum = 1;
    var endnum = 10;
    if (PageCount > 0) {
        if (PageIndex > 4) {
            startnum = PageIndex - 4;
        }

        endnum = PageIndex + 5;
        if (endnum >= PageCount) {
            endnum = PageCount;
            if (PageCount >= Pagelist) {
                startnum = PageCount - Pagelist + 1;
            }
        }
        if (endnum < Pagelist && PageCount >= Pagelist) {
            endnum = Pagelist;
        }
        pagehtml += '<a class="first">首页</a>';
        pagehtml += '<a class="prev">上一页</a>';
        for (var i = startnum; i <= endnum; i++) {
            if (i == PageIndex) {
                pagehtml += '<span>' + PageIndex + '</span>';
            }
            else {
                pagehtml += '<a class="page">' + i + '</a>';
            }
        }
        pagehtml += '<a class="next">下一页</a>';
        pagehtml += '<a class="last">尾页</a>';
        pagehtml += '<span>共' + PageCount + '页</span>';
    }

    $("#newspage").html(pagehtml);
    $("#newspage a").css("cursor", "pointer");
    $("#newspage a").click(function () {
        var thisclass = $(this).attr("class");
        if (thisclass == "first") {
            $("#PageIndex").val(1);
        }
        else if (thisclass == "prev") {
            $("#PageIndex").val(myIndexprev);
        }
        else if (thisclass == "next") {
            $("#PageIndex").val(myIndexnext);
        }
        else if (thisclass == "last") {
            $("#PageIndex").val(PageCount);
        }
        else if (thisclass == "page") {
            //alert($(this).html());
            $("#PageIndex").val($(this).html());
            //alert($("#PageIndex").val());
        }
        
        loadData();
        window.setTimeout(function () {
            $("html, body").animate({ scrollTop: 0 }, 1000);
        },500);
    });
}