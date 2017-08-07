
var logintoolsurl = "tools/UserAccount.ashx";

//获取问号传值的方法,并判断是否存在该问号传值，否则会有脚本错误
function getvl(name) {
    var reg = new RegExp("(^|\\?|&)" + name + "=([^&]*)(\\s|&|$)", "i");
    if (reg.test(location.href)) return unescape(RegExp.$2.replace(/\+/g, " "));
    return "";
};

var jumpurl = getvl("u") || getvl("jump");

if (jumpurl == null || jumpurl == "") {
    jumpurl = document.referrer;
    //alert(jumpurl);
}
if (jumpurl.indexOf("login.html") >= 0 || jumpurl.indexOf("reg.html") >= 0) {
    jumpurl = "";//
}
function setBackUrl() {
    var authenticationbackurl = jumpurl;
    if (authenticationbackurl == "") {
        authenticationbackurl = "http://account.87870.com/personal/information.aspx";
    }
    authenticationbackurl = $.base64.encode(authenticationbackurl);
    $(".qqdlbut").attr("href", $(".qqdlbut").attr("href") + authenticationbackurl);
    $(".wbdlbut").attr("href", $(".wbdlbut").attr("href") + authenticationbackurl);
}
jQuery(function ($) {
    setBackUrl();
    $("#loginTab a").mouseenter(function (event) {
        var $this = $(this);
        $this.siblings('a').removeClass('current').end().addClass('current');
        var index = $this.index();
        $("div[id^='loginWrapper']").hide();
        $("#loginWrapper" + index).show();
    });

    $("#change_code").on('click',function(){
        changeimgcode('vcodesj');
    });
    //账户名验证
    $("#acc_login").Validform({
        btnSubmit: ".acc_submit",
        tiptype: function (msg, o, cssctl) {
            if (!o.obj.is("form")) {
                var objtip = $("#info");
                cssctl(objtip, o.type);
                objtip.hide().text(msg).fadeIn(300);
            }
        },
        datatype:{
            "username" : function(gets,obj,curform,regxp){
                var reg1=/^[a-zA-Z][a-zA-Z0-9_]{5,15}$/,
                reg2=/^13[0-9]{9}$|14[0-9]{9}|15[0-9]{9}$|18[0-9]{9}$/;
                if(reg1.test(gets)){return true;}
                if(reg2.test(gets)){return true;}
                return false;
            }
        },
        callback: function () {
            //alert(jumpurl);
            var btnobj = $(".acc_submit");
            $.ajax({
                type: "post",
                url: logintoolsurl,
                cache: false,
                dataType: 'json',
                data: {
                    action: "usernameLogin",
                    username:$("#username").val(),
                    password:$("#password").val(),
                    vcode: $("#vcode").val()
                    //,
                    //jumpurl: jumpurl
                },
                beforeSend: function (XMLHttpRequest) {
                    btnobj.text("正在登录，请稍候...");
                    btnobj.attr("disabled", true);
                },
                success: function (data) {
                    // alert(data);
                    //登录结果判断
                    if (data.status == 1) {
                        //登录成功处理
                        btnobj.text("登录成功，正在跳转...");
                        //alert(typeof (data.url));
                        /**************Token*********************/
                        //sendtoken("123456");
                        if (jumpurl == "" || jumpurl == null) {
                            //alert(data.url);
                            location.href = "/personal/information.aspx";
                        }
                        else {
                            location.href = jumpurl;
                        }
                    } else {
                        $("#info").text(data.msg).removeClass('Validform_right').addClass('Validform_wrong');
                        btnobj.attr("disabled", false);
                        btnobj.text("重新登录");
                        changeimgcode('vcodesj');
                    }
                },
                error: function (data) {
                    alert('系统错误，请尝试重新提交' + data.status);
                    btnobj.attr("disabled", false);
                    btnobj.text("重新登录");
                    $("#info").text("状态：" + textStatus + "；出错提示：" + errorThrown);
                    changeimgcode('vcodesj');
                }
            });
            return false;
        }
    }).addRule([
        {
            ele: "#username",
            datatype: "usernamemobile|m",
            nullmsg: "请填写6-16位账户名或手机号",
            errormsg: "请填写6-16位账户名或手机号",
            sucmsg: ""
        },
        {
            ele: "#password",
            datatype: "*6-20",
            nullmsg: "请填写密码",
            errormsg: "请填写6-20位字符组成的密码",
            sucmsg: ""
        },
        {
            ele: "#vcode",
            datatype: "/^[\\w\\W]{4}$/",
            nullmsg: "请填写验证码",
            errormsg: "请填写4位验证码"//,
            //ajaxurl:"loginValid.ashx"
            /*
            * ajaxurl 返回数据说明：
            *   例子：{"info":"demo info","status":"y"}
            *       info:输出提示信息
            *       status:"y"表示验证正确，"n"表示验证错误
            */
        }
    ]);
});







