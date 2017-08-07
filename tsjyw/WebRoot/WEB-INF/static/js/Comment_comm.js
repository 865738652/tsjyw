var hiddenurl = $("#hiddenurl").val();

function addcomment() {
    var cmt = $.trim($("#words").val());
    //alert(cmt.length);
    if (cmt.length < 1) {
        alert("请输入评论内容！");
        return;
    }
    if (cmt.length > 490) {
        alert("字数超过限制字符!");
        return;
    }
    //data.(cmt);
    //data.(escape(cmt));
    var parms = {
        action: "addcomment2016",
        cmt: escape(cmt),
        pid: $("#hiddenpid").val(),
        pids: 0,
        columnid: $("#hidcolumnid").val(),
        dt: new Date()
    };

    $.ajax({
        type: "post",
        url: hiddenurl,
        data: parms,
        dataType: "json",
        beforeSend: function () {
            $("#btn_commentadd").val("正在提交...");
            $("#btn_commentadd").attr("disabled", true);
        },
        success: function (data) {
            if (data != undefined) {
                if (data.result == 1) {
                    //var cnum = parseInt($(".discussnum").html()) + 1;
                    //$(".discussnum").html(cnum);
                    //$("#commentlistall dl").remove();
                    //$("#cmtindex").html(1);
                    //commentlist();

                    var htmlcmt = '';
                    htmlcmt += '<dl class="comment_list clearfix">';
                    htmlcmt += '<dt>';
                    htmlcmt += '<img src="' + data.photo + '" alt="' + data.name + '" />';
                    htmlcmt += '</dt>';
                    htmlcmt += ' <dd>';
                    htmlcmt += '<p>';
                    htmlcmt += '<span class="ploneRname">' + data.name + '</span>';
                    htmlcmt += '<span class="ploneTime">&#x25aa;&nbsp;' + data.dt + '</span>';
                    htmlcmt += '</p>';
                    htmlcmt += '<p class="ploneRword">';
                    htmlcmt += '' + html_encode($("#words").val()); + '';
                    htmlcmt += ' </p>';

                    var childlisthtml = '';

                    htmlcmt += '<div id="conment_area-wrap_' + data.cid + '" style="display:none;">';
                    htmlcmt += ' <div class="comment_area comment_area_' + data.cid + ' ">';

                    htmlcmt += ' </div>';

                    htmlcmt += '<div class="twomore " style="display:none;"><input class="openmore" id="openmore' + data.cid + '" onclick="moreclick(' + data.cid + ')" data-id="' + data.cid + '" type="button" value="展开更多"></div>';
                    htmlcmt += '</div>';

                    htmlcmt += '<div class="clearfix">';
                    htmlcmt += '<div class="ploneRt mt10">';
                    htmlcmt += ' <span class="ploneRtjpl">';
                    htmlcmt += '回复';
                    htmlcmt += '</span>';
                    htmlcmt += '<div class="ploneRzanone zhan' + data.cid + '" data-val="0" onclick="commentlike(' + data.cid + ')">(<span class="span' + data.cid + '">0</span>)</div>';
                    htmlcmt += '</div>';
                    htmlcmt += '</div>';
                    htmlcmt += '<div class="plbothidetwo mt10 plbothidetwo' + data.cid + ' displayN">';
                    htmlcmt += '<div class="pltwobot">';
                    htmlcmt += '<div class="ploneinput">';
                    htmlcmt += '<input type="hidden" id="hiddennick' + data.cid + '" value="' + data.name + '">';
                    htmlcmt += '<textarea  id="txb_Content' + data.cid + '" name="txb_Content' + data.cid + '" group="g' + data.cid + '" class="plinput" placeholder="我有话要说......"></textarea>';
                    htmlcmt += '<input  group="' + data.cid + '" id="line' + data.cid + '" data-qpid="' + data.cid + '" data-id="' + data.cid + '" onclick="addcommentline(' + data.cid + ')" class="send plbuttonimg" type="button" value="回复">';
                    htmlcmt += '</div>';

                    htmlcmt += '</div>';
                    htmlcmt += ' </div>';
                    htmlcmt += '</dd>';
                    htmlcmt += '</dl>';
                    //data.($(".comment_list").length);
                    //if ($(".comment_list").length > 0) {

                    //} else {
                    //    $("#commentlistall").append(htmlcmt);
                    //}
                    $(".comment_heading").after(htmlcmt);
                    $("#words").val("");
                }
                else if (data.result == -100) {
                    //data.(data.msg);
                    $("#login-widow").show();
                    $(".bg").show();
                }
            }
        },
        error: function (xhr, errorType, error) {
            alert(error);
            $("#btn_commentadd").val("评论");
            $("#btn_commentadd").attr("disabled", false);
            return false;
        },
        complete: function () {
            $("#btn_commentadd").val("评论");
            $("#btn_commentadd").attr("disabled", false);
        }
    });
}//
function addcommentline(obj) {
    var cmt = $.trim($("#txb_Content" + obj).val());
    if (cmt.length < 2) {
        alert("回复内容不低于2字符！");
        return;
    }
    if (cmt.length > 450) {
        alert("回复内容字数超过限制字符");
        return;
    }
    //alert(cmt);
    //alert(escape(cmt));
    var parms = {
        action: "addcomment2016",
        cmt: escape(cmt),
        pid: $("#hiddenpid").val(),
        pids: obj,
        columnid: $("#hidcolumnid").val(),
        dt: new Date()
    };
    var pname = $("#hiddennick" + obj).val();
    $.ajax({
        type: "post",
        url: hiddenurl,
        data: parms,
        dataType: "json",
        beforeSend: function () {
            $("#line" + obj).val("正在提交...");
            $("#line" + obj).attr("disabled", true);
        },
        success: function (data) {
            if (data != undefined) {
                if (data.result == 1) {
                    //var cnum = parseInt($(".discussnum").html()) + 1;
                    //$(".discussnum").html(cnum);
                    //$("#commentlistall dl").remove();
                    //$("#cmtindex").html(1);
                    //commentlist();

                    $("#txb_Content" + obj).val("");
                    var listobj = $(".comment_area_" + obj);
                    var htmlcmt = '';
                    htmlcmt += '<dl class="clearfix">';
                    htmlcmt += '<dt>';
                    htmlcmt += '<img src="' + data.photo + '" alt="' + data.name + '" />';
                    htmlcmt += '</dt>';
                    htmlcmt += '<dd>';
                    htmlcmt += '<p>';
                    htmlcmt += '<span class="ploneRname">' + data.name + '</span>';
                    htmlcmt += '<span class="ploneTime">&#x25aa;&nbsp;' + data.dt + '</span>';
                    htmlcmt += '</p>';
                    htmlcmt += '<p class="ploneRword">';
                    htmlcmt += '' + html_encode(cmt) + '';
                    htmlcmt += '</p>';
                    htmlcmt += '</dd>';
                    htmlcmt += '</dl>';
                    //alert(htmlcmt);
                    $("#conment_area-wrap_" + obj).show();
                    $(".comment_area_" + obj).append(htmlcmt);
                    $(".plbothidetwo" + obj).slideUp("500");
                }
                else if (data.result == -100) {

                    $("#login-widow").show();
                    $(".bg").show();
                }
            }
        },
        error: function (xhr, errorType, error) {
            //alert(error);
            $("#line" + obj).val("回复");
            $("#line" + obj).attr("disabled", false);
            return false;
        },
        complete: function () {
            $("#line" + obj).val("回复");
            $("#line" + obj).attr("disabled", false);
        }
    });
}//
$(document).ready(function () {
    window.setTimeout(function () {
        commentlist();
    }, 500);
    $("#initmore").click(function () {
        commentlist();
    });
    //评论列表中-点击"n条回复" 
    $(".ploneRtjpl").live("click", function () {
        var nextObj = $(this).closest('.clearfix').next();
        if (nextObj.css("display") == "block") {
            nextObj.slideUp("500");
            return false;
        }
        $(".plbothidetwo").slideUp("500");
        nextObj.slideDown("500");
        return false;

    });
});


function commentlist() {
    $("#nomore").hide();
    //data.($("#cmtindex").html());
    if ($("#hiddenpid").val() == "" || $("#hiddenpid").val() == "0") {
        return;
    }
    var parms = {
        action: "commentlist2016",
        pid: $("#hiddenpid").val(),//$("#hiddenpid").val()
        cid: $("#hiddencid").val(),
        index: $("#cmtindex").html(),
        dt: new Date()
    };
    //data.($("#cmtindex").html());
    $.ajax({
        type: "post",
        url: hiddenurl,
        data: parms,
        dataType: "json",
        cache: false,
        beforeSend: function () {

        },
        success: function (data) {
            if (data != undefined) {
                if (data.result == 1) {
                    //data.($(data.comment).length);
                    var pagecount = parseInt(data.total_page);
                    var pageindex = parseInt(data.current_page);

                    $("#cmdpagecount").val(pagecount);

                    if (pagecount > pageindex && pagecount > 1) {
                        $("#initmore").show();
                        $("#cmtindex").html(pageindex + 1);
                    }
                    else {
                        if (pageindex > 1) {
                            $("#nomore").show();
                        }
                        $("#initmore").hide();
                    }
                    $(data.comment).each(function (index, lista) {
                        var htmlcmt = '';

                        htmlcmt += '<dl class="comment_list clearfix">';
                        htmlcmt += '<dt>';
                        htmlcmt += '<img src="' + lista.usr_thumb + '" alt="' + lista.usr_nickname + '" />';
                        htmlcmt += '</dt>';
                        htmlcmt += ' <dd>';
                        htmlcmt += '<p>';
                        htmlcmt += '<span class="ploneRname">' + lista.usr_nickname + '</span>';
                        htmlcmt += '<span class="ploneTime">&#x25aa;&nbsp;' + lista.created_at + '</span>';
                        htmlcmt += '</p>';
                        htmlcmt += '<p class="ploneRword">';
                        htmlcmt += '' + unescape(lista.comment) + '';
                        htmlcmt += ' </p>';

                        var childlisthtml = '';
                        var conmenttyle = 'display:none;';

                        var childcount = 0;
                        $(lista.childlist).each(function (indexs, child_list) {
                            var linshistyle = '';
                            if (indexs > 4) {
                                linshistyle = 'style="display:none;"';
                            }
                            childlisthtml += '<dl class="clearfix" ' + linshistyle + '>';
                            childlisthtml += '<dt>';
                            childlisthtml += '<img src="' + child_list.photo + '" alt="' + child_list.name + '" />';
                            childlisthtml += '</dt>';
                            childlisthtml += '<dd>';
                            childlisthtml += '<p>';
                            childlisthtml += '<span class="ploneRname">' + child_list.name + '</span>';
                            childlisthtml += '<span class="ploneTime">&#x25aa;&nbsp;' + child_list.dt + '</span>';
                            childlisthtml += '</p>';
                            childlisthtml += '<p class="ploneRword">';
                            childlisthtml += '' + html_encode(child_list.cmt) + '';
                            childlisthtml += '</p>';
                            childlisthtml += '</dd>';
                            childlisthtml += '</dl>';
                            childcount += 1;
                        })


                        if (childcount > 0) {
                            conmenttyle = '';
                        }
                        htmlcmt += '<div id="conment_area-wrap_' + lista.cid + '" style="' + conmenttyle + '">';
                        htmlcmt += ' <div class="comment_area comment_area_' + lista.cid + ' ">';
                        htmlcmt += '' + childlisthtml + '';
                        //htmlcmt += '<dl class="clearfix">';
                        //htmlcmt += '<dt>';
                        //htmlcmt += '<img src="../images/no.jpg" alt="苏三" />';
                        //htmlcmt += '</dt>';
                        //htmlcmt += '<dd>';
                        //htmlcmt += '<p>';
                        //htmlcmt += '<span class="ploneRname">苏三</span>';
                        //htmlcmt += '<span class="ploneTime">&#x25aa;&nbsp;2015年10月28日 07:35</span>';
                        //htmlcmt += '</p>';
                        //htmlcmt += '<p class="ploneRword">';
                        //htmlcmt += '回复 <span class="color">张三李四王二麻子</span>：站上才找到下载地址';
                        //htmlcmt += '</p>';
                        //htmlcmt += '</dd>';
                        //htmlcmt += '</dl>';
                        htmlcmt += ' </div>';
                        var morestyle = 'style="display:none;"';
                        if ($(lista.childlist).length > 5) {
                            morestyle = '';
                        }
                        htmlcmt += '<div class="twomore " ' + morestyle + '><input class="openmore" id="openmore' + lista.cid + '" onclick="moreclick(' + lista.cid + ')" data-id="' + lista.cid + '" type="button" value="展开更多"></div>';
                        htmlcmt += '</div>';

                        htmlcmt += '<div class="clearfix">';
                        htmlcmt += '<div class="ploneRt mt10">';
                        htmlcmt += ' <span class="ploneRtjpl">';
                        htmlcmt += '回复';
                        htmlcmt += '</span>';
                        htmlcmt += '<div  class="ploneRzanone zhan' + lista.cid + '" data-val="0" onclick="commentlike(' + lista.cid + ')">(<span class="span' + lista.cid + '">' + lista.like + '</span>)</div>';
                        htmlcmt += '</div>';
                        htmlcmt += '</div>';
                        htmlcmt += '<div class="plbothidetwo mt10 plbothidetwo' + lista.cid + ' displayN">';
                        htmlcmt += '<div class="pltwobot">';
                        htmlcmt += '<div class="ploneinput">';
                        htmlcmt += '<input type="hidden" id="hiddennick' + lista.cid + '" value="' + lista.usr_nickname + '">';
                        htmlcmt += '<textarea  id="txb_Content' + lista.cid + '" name="txb_Content' + lista.cid + '" group="g' + lista.cid + '" class="plinput" placeholder="我有话要说......"></textarea>';
                        htmlcmt += '<input  group="' + lista.cid + '" id="line' + lista.cid + '" data-qpid="' + lista.cid + '" data-id="' + lista.cid + '" onclick="addcommentline(' + lista.cid + ')" class="send plbuttonimg" type="button" value="回复">';
                        htmlcmt += '</div>';

                        htmlcmt += '</div>';
                        htmlcmt += ' </div>';
                        htmlcmt += '</dd>';
                        htmlcmt += '</dl>';
                        $("#commentlistall").append(htmlcmt);
                    });
                    //$("#discussnum").html(data.total_item);
                }
            }
        },
        error: function (xhr, errorType, error) {
            //alert('加载失败:' + error);
            return false;
        }
    });
}

function moreclick(obj) {
    var btn = $("#openmore" + obj);
    //data.(btn.val());
    if (btn.val() == "展开更多") {
        $(".comment_area_" + obj + " .clearfix").show();//comment_area comment_area_1951 
        btn.val("隐藏部分");
    }
    else {
        $(".comment_area_" + obj + " .clearfix").each(function (index, datas) {
            if (index > 4) {
                $(this).hide();
            }
        });
        btn.val("展开更多");
    }
}

function commentlike(obj) {
    var cmt = $("#hiddenlike").val();
    var zhanval = $('.zhan' + obj).attr("data-val");
    if (zhanval != "0") {
        return false;
    }
    zhanimg(obj);
    //alert(cmt);
    if (cmt != "0") {
        return;
    }
    var parms = {
        action: "commentlike",
        did: obj,
        dt: new Date()
    };

    $.ajax({
        type: "post",
        url: hiddenurl,
        data: parms,
        dataType: "json",
        beforeSend: function () {
            $("#hiddenlike").val(obj);
        },
        success: function (data) {
            if (data != undefined) {
                //if (data.result == 1) {
                //    var span = $(".span" + obj);
                //    var next = parseInt(span.html()) + 1;
                //    span.html(next);
                //}
                //else if (data.result == 2) {
                //    var span = $(".span" + obj);
                //    var next = parseInt(span.html()) - 1;
                //    span.html(next);
                //}
                //else if (data.result == -100) {

                //    $("#login-widow").show();
                //    $(".bg").show();
                //}
            }
        },
        error: function (xhr, errorType, error) {
            alert(error);
            $("#hiddenlike").val(0);
            return false;
        },
        complete: function () {
            $("#hiddenlike").val(0);
        }
    });
}

function html_encode(str) {
    return str.replace(/\r?\n/g, "<br />");
}

function zhanimg(obj) {
    var zhan = $('.zhan' + obj);
    var dataval = zhan.attr("data-val");
    if (dataval == 0) {
        zhan.css("color", "#de1c1c").css("background-position", "0 -52px").css("cursor", "default");
        var left = parseInt(zhan.offset().left) + 10, top = parseInt(zhan.offset().top) - 10, objs = zhan;
        zhan.append('<div id="zhan_' + obj + '"><b>+1<\/b></\div>');
        $('#zhan_' + obj).css({ 'position': 'absolute', 'z-index': '1', 'color': '#C30', 'left': left + 'px', 'top': top + 'px' }).animate({ top: top - 10, left: left + 10 }, 'slow', function () {
            $('#zhan_' + obj).fadeIn('fast').remove();
            var Num = parseInt(objs.find('span').text());
            Num++;
            objs.find('span').text(Num);
        });
        zhan.attr("data-val", "1");
    }
}