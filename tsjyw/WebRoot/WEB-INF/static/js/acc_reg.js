/// <reference path="acc_public.js" />

//获取问号传值的方法,并判断是否存在该问号传值，否则会有脚本错误
function getvl(name) {
    var reg = new RegExp("(^|\\?|&)" + name + "=([^&]*)(\\s|&|$)", "i");
    if (reg.test(location.href)) return unescape(RegExp.$2.replace(/\+/g, " "));
    return "";
};


var jumpurl = getvl("u") || getvl("jump");
jumpurl = document.referrer;
if (jumpurl.indexOf("login.html") >= 0 || jumpurl.indexOf("reg.html") >= 0) {
    jumpurl = "";
}
else {
    if (document.domain == "bbs.87870.com") {
        jumpurl = "http://bbs.87870.com/forum.php";
    }
}

//发送验证码计时器
var is_requesting = true;
var count = 60;
var _outtimer = 60;

function sendCode() {
    //obj.val("1233");
    is_requesting = false;
    //alert(count);
    if (count <= 0) {
        //alert("ccc");
        if (getCookie("outtimer") != null && getCookie("outtimer") != "") {
            deleteCookie("outtimer");
            //alert("ccc");
        }
        count = 60;
        is_requesting = true;
        window.clearTimeout(timer);
        $("#get_mcode").attr("disabled", false);
        $("#get_mcode").val("重发验证码");
        $("#get_mcode").removeClass("resend");
        //$("#login_error1").text("点击获取手机验证码").addClass("Validform_checktip");
        return;
    }
    //alert(count);
    $("#get_mcode").val(count + "秒后重发");
    count--;
    //alert(getCookie("outtimer"));
    if (getCookie("outtimer") == null || getCookie("outtimer") == "") {
        addCookie("outtimer", new Date(), 0.02);
    }

    $("#get_mcode").attr("disabled", true);
    var timer = window.setTimeout(function () { sendCode() }, 1000);

}

var byuid = getvl("r");


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
    //alert(jumpurl.indexOf("login.html"));
    //deleteCookie("outtimer");
    $("#regGuide").on('click', 'a', function (event) {
        var $this = $(this);
        $this.siblings('a').removeClass('current').end().addClass('current');
        var index = $this.index();
        $("div[id^='acc_reg']").hide();
        $("#acc_reg" + index).show();
    });

    //换一张
    $("#change_code").on('click', function () {
        changeimgcode('vcodesj');
    });

    var regtoolsurl = "tools/UserAccount.ashx";//注册异步地址

    //账户名验证
    var btnobj = $("#acc_submit");
    btnobj.attr("disabled", false);
    $("#acc_reg0").Validform({
        btnSubmit: "#acc_submit",
        tiptype: function (msg, o, cssctl) {
            if (!o.obj.is("form")) {
                var objtip = o.obj.siblings(".info");
                cssctl(objtip, o.type);
                objtip.hide().text(msg).fadeIn(300);
            }
        },
        callback: function () {
            //alert("ddd")
            $.ajax({
                type: "post",
                url: regtoolsurl,
                cache: false,
                dataType: 'json',
                data: {
                    action: "usernameReg",
                    username: $("#username").val(),
                    password: $("#password").val(),
                    //realname: escape($("#realname_acc").val()),
                    //idcard: $("#idcard_acc").val(),
                    vcode: $("#vcode").val(),
                    byuid: byuid
                },
                beforeSend: function (XMLHttpRequest) {
                    btnobj.val("正在提交...");
                    btnobj.attr("disabled", true);
                },
                success: function (data) {
                    if (data.status == "1") {//注册成功
                        // alert(jumpurl);
                        if (jumpurl == "" || jumpurl == null) {
                            //alert(data.status);
                            changeimgcode('vcodesj');
                            btnobj.attr("disabled", false);
                            $("#span_acc").text(data.username);
                            $("#safelevel").html("您的安全级别为：" + data.safelevel);
                            $("#sucessdiv").show();
                            $("#regdiv").remove();
                        }
                        else {
                            mobbtnobj.attr("disabled", false);
                            mobbtnobj.text("注册成功,正在跳转...");
                            location.href = jumpurl;
                        }
                    }
                    if (data.status == "0") {//注册失败
                        changeimgcode('vcodesj');
                        btnobj.attr("disabled", false);
                        btnobj.val("重新提交");
                    }
                },
                error: function (data) {
                    //alert('系统错误，请尝试重新提交');
                    $this.addClass("resend").siblings(".info").text("系统错误，请尝试重新提交").removeClass("Validform_right").addClass("Validform_wrong");
                    changeimgcode('vcodesj');
                    btnobj.attr("disabled", false);
                    btnobj.val("重新提交");
                }
            });
            return false;
        }
    }).addRule([
        {
            ele: "#username",
            datatype: regusername,
            nullmsg: "请以字母开头，长度6~16位。可包含数字、字母、_",
            errormsg: usernametip,
            sucmsg: "\b",
            ajaxurl: "tools/ExistCheck.ashx?act=CheckUserName"/*用户名实时验证路径*/

            /*
            * ajaxurl 返回数据说明：
            *	例子：{"info":"demo info","status":"y"}
            *		info:输出提示信息
            *		status:"y"表示验证正确，"n"表示验证错误
            */
        },
        {
            ele: "#password",
            datatype: regpassword,
            nullmsg: "请填写密码",
            errormsg: passwordtip,
            sucmsg: "\b"
        },
	    {
	        ele: "#password_acc2",
	        datatype: "*",
	        recheck: "password",
	        nullmsg: "请再次填写密码",
	        errormsg: "确认密码与密码不一致",
	        sucmsg: "\b"
	    },
//	    {
//	        ele: "#realname_acc",
//	        datatype: regtruename,
//	        nullmsg: "请填写真实姓名",
//	        errormsg: "真实姓名无效",
//	        sucmsg: "\b"
//	    },
//	    {
//	        ele: "#idcard_acc",
//	        datatype: "idcard",
//	        nullmsg: "请填写二代身份证18位",
//	        errormsg: "请填写正确身份证号",
//	        sucmsg: "\b",
//	        ajaxurl: "tools/ExistCheck.ashx?act=CheckIdCard"
//	    },
        {
            ele: "#vcode",
            datatype: "/^[\\w\\W]{4}$/",
            nullmsg: "请填写验证码",
            errormsg: "请填写4位验证码",
            //ajaxurl: "loginValid.ashx",
            sucmsg: "\b",
            ajaxurl: "tools/ExistCheck.ashx?act=CheckPageCode" //验证码实时验证路径

            /*
            * ajaxurl 返回数据说明：
            *   例子：{"info":"demo info","status":"y"}
            *       info:输出提示信息
            *       status:"y"表示验证正确，"n"表示验证错误
            */
        },
	    {
	        ele: "#yhxy_acc",
	        datatype: "*",
	        nullmsg: "请选择同意服务条款",
	        errormsg: "请选择同意服务条款",
	        sucmsg: "\b"
	    }
    ]);



    /*********************************************************手机注册项********************************************************/
    //发送手机注册验证码
    //var is_requesting = true;
    $("#get_mcode").attr("disabled", false);
    $("#get_mcode").on("click", function () {
        //alert("dd");
        //if (!is_requesting) { return }
        //$("#mob_vcode")[0].validform_valid="false";
        //var count = 60,$this = $(this);
        //$this.addClass("resend").siblings(".info").text("短信验证码已发送到您的手机，请耐心等待。").removeClass("Validform_wrong").removeClass("Validform_right");

        if (getCookie("outtimer") != null && getCookie("outtimer") != "") {

            var starTime = new Date(Date.parse(getCookie("outtimer")));
            //alert(starTime);
            var endTime = new Date();
            _outtimer = dateDiff('s', starTime, endTime); //计算相差秒数
            //if (parseInt(_outtimer) > 60) {
            //    _outtimer = 60;
            //}
            //alert(_outtimer);
            _outtimer = count - parseInt(_outtimer);

            if (_outtimer > 0) {
                count = _outtimer;
                $("#mvcode").siblings(".info").text("60秒内只能发送一次验证码！").removeClass("Validform_right").addClass("Validform_wrong");
                sendCode();
                return false;
            }
            //return false;
        }

        var mobile = $("#mobile").val();
        //alert(mobile);
        if (mobile == null || mobile == "") {
            //$("#login_error1").text("请填写手机号").removeClass("Validform_right").addClass("Validform_wrong");
            $("#mobile").siblings(".info").text("请填写手机号").removeClass("Validform_right").addClass("Validform_wrong");
            //alert(mobile);
            return false;
        }
        //验证手机
        if (checkMobile(mobile) == false) {
            $("#mobile").siblings(".info").text("手机号格式不正确").removeClass("Validform_right").addClass("Validform_wrong");
            return false;
        }

        //图片验证码
        var imgvcode = $("#mvcode").val();
        if (imgvcode == null || imgvcode == "") {
            $("#mvcode").siblings(".info").text("请填写验证码").removeClass("Validform_right").addClass("Validform_wrong");
            return false;
        }


        $.ajax({
            type: "POST",
            url: "tools/getMobileCode.ashx",
            cache: false,
            dataType: "json",
            data: {
                action: "getmobileCode",
                act: "reg",
                mobile: mobile,
                imgvcode: imgvcode
            },
            timeout: 20000,
            beforeSend: function (XMLHttpRequest) {

                if (getCookie("outtimer") != null && getCookie("outtimer") != "") {

                    var starTime = new Date(Date.parse(getCookie("outtimer")));
                    //alert(starTime);
                    var endTime = new Date();
                    _outtimer = dateDiff('s', starTime, endTime); //计算相差秒数

                    if (parseInt(_outtimer) > 60) {
                        //alert(_outtimer);
                        _outtimer = 60;
                    }
                    _outtimer = count - parseInt(_outtimer);

                    if (_outtimer > 0) {
                        count = _outtimer;
                        ("#mvcode").siblings(".info").text("60秒内只能发送一次验证码！").removeClass("Validform_right").addClass("Validform_wrong");
                        sendCode();
                        return false;
                    }
                    //return false;
                }
                //alert(imgvcode);
            },
            success: function (data, textStatus) {
                //alert(data);
                if (data.status == 1) {
                    alert("短信验证码已发送到您的手机，请耐心等待。");
                    //$("#mvcode").siblings(".info").text("短信验证码已发送到您的手机，请耐心等待。").removeClass("Validform_wrong").addClass("Validform_right");
                    sendCode();
                    changeimgcode('mvcodesj');
                    //$("#login_error1").text("短信验证码已发送到您的手机，请耐心等待。");
                } else {
                    changeimgcode('mvcodesj');
                    //$("#login_error1").text(data.msg);
                    $("#mvcode").siblings(".info").text(data.msg).removeClass("Validform_right").addClass("Validform_wrong");
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                //$("#login_error1").text("状态：" + textStatus + "；出错提示：" + errorThrown);
                //$("#login_error1").text("发送失败：系统异常，请重试！");
                $("#mvcode").addClass("resend").siblings(".info").text("发送失败：系统异常，请重试！").removeClass("Validform_right").addClass("Validform_wrong");
                changeimgcode('mvcodesj');
            }
        });

    });

    //手机注册
    var mobbtnobj = $("#mob_submit");
    $("#acc_reg1").Validform({
        btnSubmit: mobbtnobj,
        tiptype: function (msg, o, cssctl) {
            if (!o.obj.is("form")) {
                var objtip = o.obj.siblings(".info");
                cssctl(objtip, o.type);
                objtip.hide().text(msg).fadeIn("300");
            }
        },
        callback: function () {
            $.ajax({
                type: "POST",
                url: regtoolsurl,
                cache: false,
                dataType: 'json',
                data: {
                    action: "mobileReg",
                    mobile: $("#mobile").val(),
                    password: $("#mob_password").val(),
                    //realname: escape($("#realname_mob").val()),
                    //idcard: $("#idcard_mob").val(),
                    mobilecode: $("#mob_vcode").val(),
                    byuid: byuid
                },
                beforeSend: function (XMLHttpRequest) {
                    //mobbtnobj.attr("disabled", true);
                    //mobbtnobj.text("正在登录，请稍候...");
                },
                success: function (data) {
                    //alert(data.status);
                    if (data.status == "1") {//注册成功

                        if (jumpurl == "" || jumpurl == null) {
                            //alert(data.status);
                            changeimgcode('mvcodesj');
                            mobbtnobj.attr("disabled", false);
                            $("#span_acc").text(data.username);
                            $("#safelevel").html("您的安全级别为：" + data.safelevel);
                            $("#sucessdiv").show();
                            $("#regdiv").remove();
                        }
                        else {
                            mobbtnobj.attr("disabled", false);
                            mobbtnobj.text("注册成功,正在跳转...");
                            location.href = jumpurl;
                        }
                    }




                    if (data.status == "0") {//注册失败
                        changeimgcode('mvcodesj');
                        mobbtnobj.attr("disabled", false);
                        mobbtnobj.val("重新提交");
                        mobbtnobj.addClass("resend");
                        $("#tipresult").text(data.msg).removeClass("Validform_right").addClass("Validform_wrong");
                    }
                },
                error: function (data, ts, e) {
                    mobbtnobj.attr("disabled", false);
                    mobbtnobj.text("重新登录");
                    //$("#login_error1").text("状态：" + textStatus + "；出错提示：" + errorThrown);
                    mobbtnobj.addClass("resend");//.siblings(".info").text("系统错误，请尝试重新提交").removeClass("Validform_right").addClass("Validform_wrong");
                    $("#tipresult").text("系统错误，请尝试重新提交").removeClass("Validform_right").addClass("Validform_wrong");
                    changeimgcode('mvcodesj');
                }
            });
            return false;
        }
    }).addRule([
{
    ele: "#mobile",
    datatype: "m",
    nullmsg: "请填写手机号",
    errormsg: "手机号格式错误",
    sucmsg: "\b",
    ajaxurl: "tools/ExistCheck.ashx?act=CheckMobile"/*用户名实时验证路径*/
},
{
    ele: "#mob_password",
    datatype: regpassword,
    nullmsg: "请填写密码",
    errormsg: passwordtip,
    sucmsg: "\b"
},
{
    ele: "#password_mob2",
    datatype: "*",
    recheck: "mob_password",
    nullmsg: "请再次填写密码",
    errormsg: "确证密码与密码不一致",
    sucmsg: "\b"
},
//{
//    ele: "#realname_mob",
//    datatype: regtruename,
//    nullmsg: "请填写真实姓名",
//    errormsg: "真实姓名无效",
//    sucmsg: "\b"
//},
//{
//    ele: "#idcard_mob",
//    datatype: "idcard",
//    nullmsg: "请填写二代身份证18位",
//    errormsg: "请填写正确身份证号",
//    sucmsg: "\b",
//    ajaxurl: "tools/ExistCheck.ashx?act=CheckIdCard"
//},
{
    ele: "#mob_vcode",
    datatype: "/^\\d{6}$/",
    nullmsg: "请填写动态密码",
    errormsg: "请填写6位动态密码",
    sucmsg: "\b"
    /*
    ,
    ajaxurl: "/loginValid.ashx?"
    
    * ajaxurl 返回数据说明：
    *   例子：{"info":"demo info","status":"y"}
    *       info:输出提示信息
    *       status:"y"表示验证正确，"n"表示验证错误
    */
},
{
    ele: "#yhxy_mob",
    datatype: "*",
    nullmsg: "请选择同意服务条款",
    errormsg: "请选择同意服务条款",
    sucmsg: "\b"
}
    ]);
    //deleteCookie("outtimer");
});








