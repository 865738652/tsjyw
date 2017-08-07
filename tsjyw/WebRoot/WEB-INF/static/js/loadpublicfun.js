$(document).ready(function () {

    var navIndex = 100;// serverIndex;
    var data_url = "";// searchUrl
    function InitColumnAndSearch() {
        var columnindex = $("#columnindex");
        if (columnindex.length > 0) {
            navIndex = columnindex.val();
        }
        var search_list = $("#search_list");
        if (navIndex >= search_list.find("li").length) {
            return;
        }
        var txt = search_list.find("li").eq(navIndex).text();
        $("#search_label").text(txt);
        if (data_url == undefined) {
            return;
        }
        data_url = search_list.find("li").eq(navIndex).attr("data-url");
        $("#search_label").attr("data-url", data_url);
    }
    function headerloadOK() {
        InitColumnAndSearch();
        var $scrollTop = $(window).scrollTop();
        if ($scrollTop > 99) {
            $(window).scrollTop($scrollTop - 1);
        }
        $(window).scroll(function () {
            if ($(window).scrollTop() > 100) {
                $('#header').find('.top-bar').css({ height: 0 });
                $('#header').stop().css({ height: 41 }).addClass('header-scale');
                $('#header').find('.logo_img,.nav-wrap').stop().css({ height: 40 });
                $('#header').find('.nav-list > li >a').stop().css({ height: '40px' });
                $('#header').find('.nav-list > li > a > span').stop().css({ lineHeight: '40px' });
                $('#header').css({ overflow: 'visible' });
                $('#header').find('.nav-wrap').css({ overflow: 'visible' });
            }
            if ($(window).scrollTop() > 600) {
                $('#gotop').fadeIn();
            }
            if ($(window).scrollTop() < 100) {
                $('#header').find('.top-bar').stop().css({ height: 30 });
                $('#header').stop().css({ height: 111 }).removeClass('header-scale');
                $('#header').find('.logo_img,.nav-wrap').stop().css({ height: 80 });
                $('#header').find('.nav-list > li >a').stop().css({ height: '76px' });
                $('#header').find('.nav-list > li > a > span').stop().css({ lineHeight: '62px' });
                $('#header').css({ overflow: 'visible' });
                $('#gotop').fadeOut()
                $('#header').find('.nav-wrap').css({ overflow: 'visible' });
            }
        });
        /*导航脚本*/
        $('#nav-list >li').eq(navIndex).addClass('current');
        $('#nav-list >li').on('mouseenter', function () {
            var $index = $(this).index(), $this = $(this);
            $this.addClass('current');
            if ($this.find('ul').size()) {
                $('.nav-sub').show();
                var $navSubList = $(this).find('.nav-sub-list');
                $navSubList.width($navSubList.children('li').size() * 130).fadeIn('fast');
                var left = "-"+($index)*111+"px";
                $navSubList.css("left",left);
            }
            tempIndex = $index;
        });
        $('#nav-list >li').on('mouseleave', function () {
            var $index = $(this).index();
            if (tempIndex != navIndex) {
                $(this).removeClass('current')
            }
            $(this).find('.nav-sub-list').fadeOut('fast');
            $('.nav-sub').hide();
        });
        /*搜索脚本*/
        $('.search').on('click', function () {
            $('.nav-wrap-sub').hide();
            $('.search_wrap').show();
            //$('#header').css({'background-color':'#393f48'});
            //$('.logo').width(80);
        });
        $('.search_close').on('click', function () {
            $('.nav-wrap-sub').show();
            $('.search_wrap').hide();
            //$('#header').css({'background-color':'#fff'});
            //$('.logo').width(205);
        });
        //$('#search_label').click(function (event) {
        //    event.stopPropagation();
        //    $('#search_list').toggle();

        //});
        //$('#search_list').on('click', 'li', function (event) {
        //    var $this = $(this),
        //        $text = $this.text();
        //    data_url = $this.attr("data-url");
        //    $('#search_label').text($text);
        //    if (data_url != undefined) {
        //        $('#search_label').attr("data-url", data_url);
        //    }
        //    $(this).closest('ul').hide();
        //    $('#serarch_txt').attr('data-type', $text);
        //});
        function Searchlist() {
            var text = $("#serarch_txt").val();
            if ($.trim(text) == "") {
                $("#serarch_txt").focus();
                return;
            }
            data_url = "http://www.87870.com/Searchlist.aspx?s=" + escape(text);// data_url.replace("{0}", text);
            location.href = data_url;
        }
        $("#serarch_btn").on('click', Searchlist);
        $("#serarch_txt").on('keypress', function (e) {
            //var keycode = event.which;
            if (e.which == 13) {
                Searchlist();
            }
        })
        /*$(document).click(function (event) {
            // 模拟select 脚本监听
            var container = document.getElementById('search_list');
            if (!$.contains(container, event.target)) {
                $(container).hide();
            }
        });*/

    }
    var tempIndex = 0;

    headerloadOK();




    function changeimgcode(str) {
        //alert(str);
        var strObj = document.getElementById(str);
        strObj.src = 'http://account.87870.com/tools/verify_code.ashx?' + Math.random();
        strObj.validform_valid = "false";
    }
    //登录窗口
    $("#change_code").live('click', function () {
        changeimgcode('vcodesj');
    });
    /*
    $('#login-pop').live('click', function (event) {
        event.stopPropagation();
        $('#login-widow,.bg').show();
    });
    */
    $('.login_topclose').live("click", function (event) {
        $('#login-widow,.bg').hide();
    });
    $('#gotop').live("click", function (event) {
        $("html, body").animate({ scrollTop: 0 }, 500, "swing");
        return false;
    });


    // function getvl(name) {
    //     var reg = new RegExp("(^|\\?|&)" + name + "=([^&]*)(\\s|&|$)", "i");
    //     if (reg.test(location.href)) return unescape(RegExp.$2.replace(/\+/g, " "));
    //     return "";
    // };

    // var jumpurl = getvl("jump") || getvl("u");

    // if (jumpurl == null || jumpurl == "") {
    //     jumpurl = document.referrer;
    //     //alert(jumpurl);
    //     if (jumpurl == "http://www.87870.com/account/reg.html") {
    //         jumpurl = "http://www.87870.com/";//
    //     }
    // }


    // window.setTimeout(function () {
    //     var obj = $(".loginState");
    //     var objContent = "<a href=\"http://account.87870.com/account/reg.html\" class=\"reg fr\">立即注册</a> <a href=\"javascript:;\" id=\"login-pop\" class=\"login fr\">登录</a><a href=\"http://www.87870.com/ParentsCare/index.html\" target=\"_blank\" class=\"gamejh fr\">《网络游戏未成年人家长监护工程》</a>";
    //     $.ajax({
    //         type: "get",
    //         url: "http://account.87870.com/account/tools/SSO.ashx",
    //         dataType: "jsonp",
    //         jsonp: "callback",
    //         data: "act=login&callback=?",
    //         beforeSend: function (XMLHttpRequest) {
    //             obj.after().html("loading...");
    //         },
    //         success: function (json) {
    //             //alert(json.status);
    //             if (json.status == "1") {
    //                 objContent = "<a href=\"http://account.87870.com/account/logout.aspx\" class=\"reg fr\">退出</a> <a href=\"http://account.87870.com/personal/information.aspx\" class=\"login fr\">" + json.username + "</a><a href=\"http://www.87870.com/ParentsCare/index.html\" target=\"_blank\" class=\"gamejh fr\">《网络游戏未成年人家长监护工程》</a>";
    //             }
    //             else {
    //                 jQuery.getScript("http://www.87870.com/2016/js/validform.js?v=1.0.2").done(function () {
    //                     login();
    //                 }).fail(function () {

    //                 });
    //             }
    //             obj.after().html(objContent);
    //         },
    //         error: function (XMLHttpRequest, textStatus, errorThrown) {
    //             obj.after().html(objContent);
    //         }
    //     });
    // }, 200);
    // function login() {
    //     //账户名验证
    //     $('#login-widow').Validform({
    //         btnSubmit: "#lw-submit",
    //         tiptype: function (msg, o, cssctl) {
    //             if (!o.obj.is("form")) {
    //                 var objtip = $(".login_info");
    //                 cssctl(objtip, o.type);
    //                 objtip.html(msg);
    //             }
    //         },
    //         datatype: {
    //             "username": function (gets, obj, curform, regxp) {
    //                 var reg1 = /^[a-zA-Z][a-zA-Z0-9_]{5,16}$/,
    //                 reg2 = /^13[0-9]{9}$|14[0-9]{9}|15[0-9]{9}$|18[0-9]{9}$/;
    //                 if (reg1.test(gets)) { return true; }
    //                 if (reg2.test(gets)) { return true; }
    //                 return false;
    //             }
    //         },
    //         callback: function () {
    //             var btnobj = $("#lw-submit");
    //             var loginname = $("#username").val();
    //             $.ajax({
    //                 type: "post",
    //                 cache: false,
    //                 async: false,
    //                 contentType: "application/json; charset=utf-8",
    //                 url: "http://account.87870.com/account/tools/ua.ashx",
    //                 dataType: "jsonp",
    //                 data: {
    //                     action: "usernameLogin",
    //                     username: $("#username").val(),
    //                     password: $("#password").val(),
    //                     vcode: $("#vcode").val()
    //                 },
    //                 jsonp: "callback",
    //                 beforeSend: function (XMLHttpRequest) {
    //                     btnobj.val("正在登录，请稍候...");
    //                     btnobj.attr("disabled", true);
    //                 },
    //                 success: function (data) {
    //                     //登录成功处理
    //                     if (data.status == 1) {
    //                         //location.reload();
    //                         //登录成功处理
    //                         btnobj.val("登录成功");
    //                         $("#login-widow").hide();
    //                         $(".bg").hide();
    //                         $(".loginState").html('<a href=\"http://account.87870.com/account/logout.aspx\" class=\"reg fr\">退出</a> <a href=\"http://account.87870.com/personal/information.aspx\" class=\"login fr\">' + loginname + '</a><a href=\"http://www.87870.com/ParentsCare/index.html\" target=\"_blank\" class=\"gamejh fr\">《网络游戏未成年人家长监护工程》</a>');
    //                         //alert(typeof (data.url));
    //                         /**************Token*********************/
    //                         //sendtoken("123456");
    //                         if (jumpurl == "" || jumpurl == null) {
    //                             //alert(data.url);
    //                             //location.href = "/";
    //                         }
    //                         else {
    //                             //location.href = jumpurl;
    //                         }
    //                     } else {
    //                         $(".login_info").text(data.msg);
    //                         btnobj.attr("disabled", false);
    //                         btnobj.val("重新登录");
    //                         changeimgcode('vcodesj');
    //                     }
    //                 },
    //                 error: function (XMLHttpRequest, textStatus, errorThrown) {
    //                     alert('系统错误，请尝试重新提交' + data.status);
    //                     btnobj.attr("disabled", false);
    //                     btnobj.val("重新登录");
    //                     $(".login_info").text("状态：" + textStatus + "；出错提示：" + errorThrown);
    //                     changeimgcode('vcodesj');
    //                 }
    //             });
    //             return false;
    //         }
    //     }).addRule([
    //         {
    //             ele: "#username",
    //             datatype: "username",
    //             nullmsg: "请填写6-16位账户名或手机号",
    //             errormsg: "请填写6-16位账户名或手机号",
    //             sucmsg: " "
    //         },
    //         {
    //             ele: "#password",
    //             datatype: "*6-20",
    //             nullmsg: "请填写密码",
    //             errormsg: "请填写6-20位字符组成的密码",
    //             sucmsg: " "
    //         },
    //         {
    //             ele: "#vcode",
    //             datatype: "/^[\\w\\W]{4}$/",
    //             nullmsg: "请填写验证码",
    //             errormsg: "请填写4位验证码"
    //             /*
    //             * ajaxurl 返回数据说明：
    //             *   例子：{"info":"demo info","status":"y"}
    //             *       info:输出提示信息
    //             *       status:"y"表示验证正确，"n"表示验证错误
    //             */
    //         }
    //     ]);

    // }
});
