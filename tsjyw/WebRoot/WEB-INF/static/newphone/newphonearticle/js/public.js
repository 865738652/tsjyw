$(function () {
    /*本地静态文件 引用公用header和footer文件*/
    function getRootPath_dc() {
        var pathName = window.location.pathname.substring(1);
        var webName = pathName == '' ? '' : pathName.substring(0, pathName.indexOf('/'));
        if (webName == "") {
            return window.location.protocol + '//' + window.location.host;
        }
        else {
            return window.location.protocol + '//' + window.location.host + '/' + webName;
        }
    }
    var _rootpaht_ = getRootPath_dc();


    /* Header导航 */
    // 点击icon前往个人中心
//    $(".user-center").on('click', function (e) {
//        window.location.href = "/account/index.aspx";
//    });
    // 点击logo前往首页
    $(".logo").on('click', function (e) {
        window.location.href = "../WeChatNewArticle";
    });
    // 点击搜索前往搜索界面
    $(".search").on('click', function (e) {
    	//alert("asd");
        window.location.href = "./WeChatArticleType";
    });
    // 导航
    $(".nav-item").on('click', function (e) {
        console.log(e.target);
        var path = $(this).attr("data-urlpath");

        console.log(path)
        window.location.href = path;
    });
    touch.on($("body"), 'touchmove', function (e) {
        console.log(e.currentTarget.scrollTop);
        var _off_on = e.currentTarget.scrollTop == 0 ? "block" : "none";
        //$("#header > .header-top").css("display", _off_on);
        if (_off_on == "block") {
            if (getCookie("info") == null || getCookie("info") == "") {
                $(".login-warn").show();
            }
        } else {
            $(".login-warn").hide();
        }
    });

    function login_warn() {
        var parms = {
            action: "weblogincheck",
            dt: new Date()
        };
        $.ajax({
            type: "POST",
            url: "/ashx/index.ashx",
            data: parms,
            dataType: "json",
            success: function (data) {

                if (data != undefined) {
                    if (data.status == -100) {
                        if (getCookie("info") == null || getCookie("info") == "") {
                            $(".login-warn").fadeIn("slow", function (e) {
                                $(".login-warn").addClass("block");
                            });
                        }
                    }
                }
            },
            error: function (xhr, errorType, error) {
            }
        });

    }
    // 服务器同志监测到用户未登录请调用下边这个函数
    login_warn();

    /* 关闭未登录提示 */
    $(".close-warn").on('click', function (e) {
        $(".login-warn").fadeOut("slow", function (e) {
            $(".login-warn").addClass("hidden");
        });
        var parms = {
            action: "webloginclose",
            dt: new Date()
        };
        $.ajax({
            type: "POST",
            url: "/ashx/index.ashx",
            data: parms,
            dataType: "json",
            success: function (data) {
            },
            error: function (xhr, errorType, error) {
            }
        });
    });

    function headerinit() {
        var columnid = 0;

        if ($("#columnindex").length > 0) {
            columnid = parseInt($("#columnindex").val());
        }
        $(".nav-item:eq(" + columnid + ")").addClass("on");

    }
    headerinit();
})