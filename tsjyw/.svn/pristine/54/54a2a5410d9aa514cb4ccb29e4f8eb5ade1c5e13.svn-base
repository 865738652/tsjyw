var domainurl = "";
jQuery(function ($) {
    domainurl = "http://account.87870.com/";
    $(":text,:password").focusin(function () { $(this).toggleClass("cur") }).focusout(function () { $(this).toggleClass("cur") });
    $('#accHeader').load('model/accHeader.html');
    $('#accFooter').load('model/accFooter.html');
});


//切换验证码 注册用
function changecode(str) {
    //alert(str);
    var strObj = document.getElementById(str);
    strObj.src = 'tools/verify_code.ashx?' + Math.random();
    strObj.validform_valid = "false";
}

function changeimgcode(str) {
    //alert(str);
    var strObj = document.getElementById(str);
    strObj.src = domainurl + 'tools/verify_code.ashx?' + Math.random();
    strObj.validform_valid = "false";
}

var regusername = "/^[a-zA-Z][a-zA-Z0-9_]{5,15}$/";//用户名
var usernametip = "账号为字母开头6~16位数字、字母、_";
var regpassword = "/^[a-zA-Z0-9_!@#$%^&*_]{6,16}$/";//密码
var passwordtip = "密码为6-16位的字母、数字、常用字符";
var regtruename = "/^[\\u4E00-\\u9FA5]{2,10}$/";//真实姓名
var regmobile = /^(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/;//手机号


var regmail = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
function checkMail(value) {
    if (!regmail.test(value)) {
        return false;
    }
}


var regmobile = /^^(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/;
function checkMobile(value) {
    if (!regmobile.test(value)) {
        return false;
    }
    return true;
}

//计算时间差
function dateDiff(interval, date1, date2) {
    var objInterval = { 'D': 1000 * 60 * 60 * 24, 'H': 1000 * 60 * 60, 'M': 1000 * 60, 'S': 1000, 'T': 1 };
    var timetype;
    interval = interval.toUpperCase();
    switch (interval) {
        case "D":
            timetype = objInterval.D;
            break;
        case "H":
            timetype = objInterval.H;
            break;
        case "M":
            timetype = objInterval.M;
            break;
        case "S":
            timetype = objInterval.S;
            break;
        case "T":
            timetype = objInterval.T;
            break;
        default:
            timetype = objInterval.T;
            break;
    }
    var dt1 = new Date(Date.parse(date1.toString().replace(/-/g, '/')));
    var dt2 = new Date(Date.parse(date2.toString().replace(/-/g, '/')));
    try {
        //alert(dt2.getTime()- dt1.getTime());
        //alert(eval_r('objInterval.'+interval));
        //alert((dt2.getTime()- dt1.getTime()) / eval_r('objInterval.'+interval));
        return Math.round((dt2.getTime() - dt1.getTime()) / timetype);
    }
    catch (e) {
        return e.message;
    }
}

//获取问号传值的方法,并判断是否存在该问号传值，否则会有脚本错误
function getvl(name) {
    var reg = new RegExp("(^|\\?|&)" + name + "=([^&]*)(\\s|&|$)", "i");
    if (reg.test(location.href)) return unescape(RegExp.$2.replace(/\+/g, " "));
    return "";
};
/*******************************************Cookie处理****************************************************/
//添加Cookie
function addCookie(name, value, expireHours) {
    deleteCookie(name);
    var cookieString = name + "=" + value;

    //判断是否设置过期时间
    if (expireHours > 0) {
        var date = new Date();
        date.setTime(date.getTime + expireHours * 3600 * 1000);
        cookieString = cookieString + "; expire=" + date.toGMTString();
    }
    document.cookie = cookieString;
}

//更新Cookie
function updateCookie(name, value) {
    var oldcookieString = getCookie(name);
    var cookieString = "";

    if (oldcookieString != null && oldcookieString != "" && oldcookieString != ',') {
        var arrCookie = oldcookieString.split(",");

        for (var i = 0; i < arrCookie.length; i++) {
            if (arrCookie[i] != null && arrCookie[i] != "") {
                if (arrCookie[i] != value) {
                    //alert(arrCookie[i]+"_"+value);
                    cookieString += arrCookie[i] + ",";
                }
            }
        }
        cookieString += value + ",";
        addCookie(name, cookieString, 0);
    }
    else {
        addCookie(name, value + ",", 0);
    }
}

//更新Cookie
function updateSingleCookie(name, value) {
    var oldcookieString = getCookie(name);
    var cookieString = "";

    if (oldcookieString != null && oldcookieString != "") {
        deleteCookie(name);
        addCookie(name, value, 0);
    }
    else {
        addCookie(name, value, 0);
    }
}

//获取Cookie
function getCookie(name) {
    var strCookie = document.cookie;
    var arrCookie = strCookie.split("; ");
    for (var i = 0; i < arrCookie.length; i++) {
        var arr = arrCookie[i].split("=");
        if (arr[0] == name) return arr[1];
    }
    return "";
}
//删除Cookie
function deleteCookie(name) {
    var date = new Date();
    date.setTime(date.getTime() - 10000);
    document.cookie = name + "=; expire=" + date.toGMTString();
}


//发送验证码计时器
var is_requesting = true;
var count = 60;
var _outtimer = 60;

function sendCode() {
    //console.log(count);
    is_requesting = false;
    //alert(count);
    if (count <= 0) {
        if (getCookie("outtimer") != null && getCookie("outtimer") != "") {
            deleteCookie("outtimer");
        }
        count = 60;
        is_requesting = true;
        window.clearTimeout(timer);
        //$("#gecode").attr("disabled", false);
        $("#getcode").text("重发验证码");
        $("#getcode").removeClass("resend");
        //$("#login_error1").text("点击获取手机验证码").addClass("Validform_checktip");
        return;
    }
    //alert(count);
    $("#getcode").text(count + "秒后重发");
    count = parseInt(count);
    count--;
    //alert(getCookie("outtimer"));
    if (getCookie("outtimer") == null || getCookie("outtimer") == "") {
        addCookie("outtimer", new Date());
    }

    $("#getcode").attr("disabled", true);
    var timer = window.setTimeout(function () { sendCode() }, 1000);
}