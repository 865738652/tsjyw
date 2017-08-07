$(function () {
    /*http://www.open-open.com/news/view/fbe781*/
    var _UA_ = navigator.appVersion;
    var _UU_ = navigator.userAgent;
    var browser = {
        versions: function () {
            return {
                trident: _UU_.indexOf('Trident') > -1,
                presto: _UU_.indexOf('Presto') > -1,
                webKit: _UU_.indexOf('AppleWebKit') > -1,
                gecko: _UU_.indexOf('Gecko') > -1 && _UU_.indexOf('KHTML') == -1,

                webApp: _UU_.indexOf('Safari') == -1,
                weixin: _UU_.indexOf('MicroMessenger') > -1,
                qq: _UU_.match(/\sQQ/i) == " qq",

                mobile: !!_UU_.match(/AppleWebKit.*Mobile.*/),
                ios: !!_UU_.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/),
                android: _UU_.indexOf('Android') > -1 || _UU_.indexOf('Linux') > -1,
                iPhone: _UU_.indexOf('iPhone') > -1 || _UU_.indexOf('Mac') > -1,
                iPad: _UU_.indexOf('iPad') > -1,

                isqqBrowser: _UU_.indexOf('MQQBrowser') > -1 || _UU_.indexOf('QQBrowser') > -1, //QQHD
                isucBrowser: _UU_.indexOf('UBrowser') > -1 || _UU_.indexOf('UCBrowser') > -1, //UC
            };
        }(),
        language: (navigator.browserLanguage || navigator.language).toLowerCase()
    }

    //加载

    commentlist();
    favinit();

    /*点击转发*/
    $("#uop-forward").on("click", function (event) {
        console.log(event.target);
        // 移动端
        if (browser.versions.mobile) {
            if (browser.versions.isqqBrowser || browser.versions.isucBrowser) {
                showSpecialShare();
            } else {
                showGeneralShare();
            }
        } else {
            // 前往PC端官网
            window.location.href = "http://87870.com";
        }
    });

    /*撤销分享*/
    $(".share-box-cancel").on("click", function (event) {
        $("#share-guide-native").hide();
    });

    $(".-mob-share-close").on("click", function (event) {
        $("#share-general-native").hide();
    });
	$(".-mob-share-ui-bg").on("click", function (event) {
        $("#share-general-native").hide();
    });
	
    /*通用分享*/
    function showGeneralShare() {
        $("#share-general-native").show();
        $("#share-general-native").css("display", "table-call");
    }

    /*特殊分享（UC浏览器和QQ浏览器） https://github.com/JefferyWang/nativeShare.js*/
    function showSpecialShare() {
        $("#share-guide-native").show();
        $("#share-guide-native").css("display", "table-call");
    }

    /*点击logo去往首页*/
    $(".nav-top").on("click", function (event) {
        history.back(-1);
    });

    /*点击收藏*/
  
    $("#uop-collect").on("click", function (event) {
        var cei = $("#uop-collect").find("img");
        var collectEle = new TimelineMax({ paused: true, foece3D: true });
        collectEle.set(cei, { force3D: true, autoAlpha: 1, scale: 3 })
                  .fromTo(cei, 0.2, { autoAlpha: 1 }, {
                      autoAlpha: 0, scale: 1, ease: Power1.easeIn, onComplete: function () {
                          $("#collect-img").attr("src", "images/op-collected.png");
                          $("#collect-img").css("visibility", "visible");
                          $("#collect-img").css("opacity", "100");
                      }
                  });

        var flag = $(this).attr("flag");
        if (flag == "1") {
            //收藏请求
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
                url: "/ashx/Comment.ashx",
                data: parms,
                dataType: "json",
                beforeSend: function () {

                },
                success: function (data) {
                    //   alert(data.result);
                    if (data.result == -500) {

                       window.location.href = '/account/login.html?u=' + window.location;

                    } else {
                        collectEle.play();
                        $("#uop-collect").attr("flag", "0")
                    }

                },
                error: function (xhr, errorType, error) {
                    //alert('收藏失败，请刷新页面后再试！');
                    return false;
                },
                complete: function () {

                }
            });
        } else {
            Delfav();
        }


    });




    /*文章点赞*/
    var lei = $("#uop-like").find("img");
    var likeEle = new TimelineMax({ paused: true, foece3D: true });
    likeEle.set(lei, { force3D: true, autoAlpha: 1, scale: 3 })
			  .fromTo(lei, 0.2, { autoAlpha: 1 }, {
			      autoAlpha: 0, scale: 1, ease: Power1.easeIn, onComplete: function () {
			          $("#like-img").attr("src", "images/op-likeed.png");
			          $("#like-img").css("visibility", "visible");
			          $("#like-img").css("opacity", "100");
			      }
			  });
    $("#uop-like").on("click", function (event) {


        //点赞请求

        var parms = {
            action: "newszan",
            columnid: $("#hiddencolumnid").val(),
            did: $("#hiddenpid").val(),
            dt: new Date()
        };

        $.ajax({
            type: "post",
            url: "/ashx/Comment.ashx",
            data: parms,
            dataType: "json",
            beforeSend: function () {

            },
            success: function (data) {
                if (data.result == -100) {

                    window.location.href = '/account/login.html?u=' + window.location;
                } else {

                    likeEle.play();
                    $("#uop-like").unbind("click");
                }
            },
            error: function (xhr, errorType, error) {

                //$("#hiddenstorelike").val(0);
                return false;
            },
            complete: function () {
                //$("#hiddenstorelike").val(0);
            }
        });







    });

    /*发送评论*/
    $("#action-send").live("click", function (event) {
        console.log(event.target);
        var textVal = $("#user-text").val();
        if ($.trim(textVal) == "") return false;

        if (textVal != null && textVal != "") {
            // https://github.com/t4t5/sweetalert api
            if (textVal.length == 0) {
                swal("警告...", "评论内容不能为空!", "error");
            } else if (textVal.length > 500) {
                swal("警告...", "评论内容限制500个字!", "error");
            } else if (textVal.length > 0 && textVal.length <= 500) {
                // 发送文章的评论内容

                var parms = {
                    action: "addcomment",
                    cmt: escape(textVal),
                    pid: $("#hiddenpid").val(),
                    pids: 0,
                    columnid: $("#hiddencolumnid").val(),
                    dt: new Date()
                };

                $.ajax({
                    type: "post",
                    url: "/ashx/Comment.ashx",
                    data: parms,
                    dataType: "json",
                    beforeSend: function () {
                        $("#action-send").val("正在提交...");
                        $("#action-send").attr("disabled", true);
                    },
                    success: function (data) {
                        if (data != undefined) {
                            if (data.result == 1) {
                                //   data = '{"result":1,"name":"87870用户","photo":"http://pic.87870.com/upload/images/87870user/2016/10/10/th_150x150_626ea515-f438-46c6-a610-7291fa4e72b0.jpg","dt":"2016-11-22 11:43","cid":105293}';



                                var htmlcmt = '<li class="comment-li"><div class="comment-head">';
                                htmlcmt += '<img src="' + data.photo + '" alt="' + data.name + '"></div>';
                                htmlcmt += '<div class="comment-info"><div class="description">';
                                htmlcmt += '<span class="user-name">' + data.name + '</span>';
                                htmlcmt += '<span class="comment-time">' + GetDateString(new Date(data.dt + ':00')) + '</span></div>';
                                htmlcmt += '<div class="comment-text">';
                                htmlcmt += '<p>' + html_encode($("#user-text").val()) + '</p></div>';
                                htmlcmt += '	<div class="comment-op"><div class="reply-box fr">	<img class="fl" src="images/comment-reply.png">';
                                htmlcmt += '	<span class="action-reply">回复</span></div>';
                                htmlcmt += '<div class="zan-box fr" pid="' + data.cid + '"><img class="fl" src="images/comment-zan.png">';
                                htmlcmt += '<span class="fl">0</span></div></div></div><p class="clear"></p>';


                                htmlcmt += '<ul class="reply-comment">  </ul><p class="clear"></p>';


                                htmlcmt += '<div class="reply-textarea-box">';
                                htmlcmt += '<textarea class="reply-text-area reply-user-text" placeholder="在此输入回复内容..."></textarea>';
                                htmlcmt += '<div class="reply-user-op">';
                                htmlcmt += '<span>限制500个字</span>';
                                htmlcmt += '<a class="action-reply-send"  pid="' + data.cid + '" href="javascript:;">发送</a>';
                                htmlcmt += ' </div>';
                                htmlcmt += '</div></li>';


                                $(".comment").prepend(htmlcmt);
                                $("#user-text").val("");
                                swal("通知：", "评论成功！", "success");

                            }


                            else if (data.result == -100) {
                                //未登录

                                window.location.href = '/account/login.html?u=' + window.location;
                            }

                        }
                    },
                    error: function (xhr, errorType, error) {
                        //alert(error);
                        $("#action-send").val("评论");
                        $("#action-send").attr("disabled", false);
                        return false;
                    },
                    complete: function () {
                        $("#action-send").val("评论");
                        $("#action-send").attr("disabled", false);
                    }
                });



            }
        }
    });

    /*评论点赞*/
    $(".zan-box").live("click", function (event) {

        _$this = $(this);
        if (_$this.attr("data-tag") != undefined && _$this.attr("data-tag") == 1) {
            return false;
        }

        var zei = $(this).find("img");
        var _img_ = $(this).find("img");
        var _span_ = $(this).find("span");



        var zanEle = new TimelineMax({ paused: true, foece3D: true });
        zanEle.set(zei, { force3D: true, autoAlpha: 1, scale: 5 })
                  .fromTo(zei, 0.2, { autoAlpha: 1 }, {
                      autoAlpha: 0, scale: 1, ease: Power1.easeIn, onComplete: function () {
                          _img_.attr("src", "images/comment-zaned.png");
                          _img_.css("visibility", "visible");
                          _img_.css("opacity", "100");
                          _span_.css("color", "red");
                      }
                  });

        var commonid = _$this.attr("pid");
        //点赞请求
        var parms = {
            action: "commentlike",
            did: commonid,
            dt: new Date()
        };

        $.ajax({
            type: "post",
            url: "/ashx/Comment.ashx",
            data: parms,
            dataType: "json",
            beforeSend: function () {

            },
            success: function (data) {
                if (data.result == 1) {
                    var num = parseInt(_span_.text());
                    _span_.text(num + 1);
                    _$this.attr("data-tag", 1);
                    zanEle.play();
                } else if (data.result == -100) {
                    window.location.href = '/account/login.html?u=' + window.location;
                } else if (data.result == -1) {
                    _$this.attr("data-tag", 1);//已点
                }

            },
            error: function (xhr, errorType, error) {
                console.log('error:' + error);
                //$("#hiddenstorelike").val(0);
                return false;
            },
            complete: function () {
                //$("#hiddenstorelike").val(0);
                console.log('complete:');
            }
        });


    });

    /*回复*/
    $(".reply-box").live("click", function (event) {
        // console.log(event.target);
        var _this_ = $(this);
        var thispare = _this_.parent().parent(".comment-info").siblings(".reply-textarea-box");

        if (thispare.css("display") == "none") {
            thispare.show();
        } else {
            thispare.hide();
        }


    });



    /*回复评论*/
    $(".action-reply-send").live("click", function (event) {
        //  console.log(event.target);
        var _this_ = $(this);
        var pid = $(this).attr("pid");
        var textVal = _this_.parent().parent(".reply-textarea-box").find(".reply-user-text").val();
        if (textVal != null && textVal != "") {
            // https://github.com/t4t5/sweetalert api
            if (textVal.length == 0) {
                swal("警告...", "评论内容不能为空!", "error");
            } else if (textVal.length > 500) {
                swal("警告...", "评论内容限制500个字!", "error");
            } else if (textVal.length > 0 && textVal.length <= 500) {
                // 发送对文章评论的评论内容


                var parms = {
                    action: "addcomment",
                    cmt: escape(textVal),
                    pid: $("#hiddenpid").val(),
                    pids: pid,
                    columnid: $("#hiddencolumnid").val(),
                    dt: new Date()
                };

                $.ajax({
                    type: "post",
                    url: "/ashx/Comment.ashx",
                    data: parms,
                    dataType: "json",
                    beforeSend: function () {
                        $("#action-reply-send").val("正在提交...");
                        $("#action-reply-send").attr("disabled", true);
                    },
                    success: function (data) {
                        if (data != undefined) {
                            if (data.result == 1) {



                                var htmlcmt = '';

                                htmlcmt += '<li class="reply-comment-li">';
                                htmlcmt += '<h1 class="rcl-name">' + data.name + '</h1>';
                                htmlcmt += '<p class="rcl-text">' + html_encode(textVal) + '</p>';
                                htmlcmt += '</li>';

                                _this_.parent().parent().siblings(".reply-comment").append(htmlcmt);



                                swal("通知：", "回复成功！", "success");
                                _this_.parent().parent().find("textarea").val('');

                                _this_.parent().parent().hide();

                            }
                            else if (data.result == -100) {
                                //未登录

                                window.location.href = '/account/login.html?u=' + window.location;
                            }
                        }
                    },
                    error: function (xhr, errorType, error) {
                        //alert(error);
                        $("#action-reply-send").val("评论");
                        $("#action-reply-send").attr("disabled", false);
                        return false;
                    },
                    complete: function () {
                        $("#action-reply-send").val("评论");
                        $("#action-reply-send").attr("disabled", false);
                    }
                });








            }
        }
    });



    /*回复内容收起/展开*/
    $("#reply-controller-pu").live("click", function (event) {
        var _this_ = $(this);
        var list = _this_.parent().parent();
        var listCount = list[0].children.length;

        for (var i = 0; i < listCount; i++) {
            if (i > 1 && i < listCount - 1) {
                list[0].children[i].style.display = "none";
            }
            if (i == listCount - 1) {
                $("#reply-controller-pu").hide();
                $("#reply-controller-op").show();
            }
        }
    });

    $("#reply-controller-op").live("click", function (event) {
        var _this_ = $(this);
        var list = _this_.parent().parent();
        var listCount = list[0].children.length;
        for (var i = 0; i < listCount; i++) {
            if (i > 1 && i < listCount - 1) {
                list[0].children[i].style.display = "block";
            }
            if (i == listCount - 1) {
                $("#reply-controller-pu").show();
                $("#reply-controller-op").hide();
            }
        }
    });


    /*加载更多评论内容*/
    $(".newsList-moreWrap").on("click", function (event) {
        commentlist();
    });


});
function html_encode(str) {
    return str.replace(/\r?\n/g, "<br />");
}

/*评论列表*/
function commentlist() {


    if ($("#hiddenpid").val() == "" || $("#hiddenpid").val() == "0") {
        return;
    }
    var parms = {
        action: "commentlist",
        pid: $("#hiddenpid").val(),//$("#hiddenpid").val()
        cid: $("#hiddencid").val(),
        index: $("#cmtindex").val(),
        dt: new Date()
    };

    $.ajax({
        type: "post",
        url: "/ashx/Comment.ashx",
        data: parms,
        dataType: "json",
        cache: false,
        beforeSend: function () {
            $("#load-more-reply").text("加载中...");
        },
        success: function (data) {
            if (data != undefined) {
                if (data.result == 1) {
                    $("#load-more-reply").text("加载更多...");
                    var pagecount = parseInt(data.total_page);
                    var pageindex = parseInt(data.current_page);

                    $("#cmdpagecount").val(pagecount);

                    if (pagecount > pageindex && pagecount > 1) {

                        $(".newsList-moreWrap").show();
                        $("#cmtindex").val(pageindex + 1);
                        $("ul.comment").find("li.nocomment").hide();
                    }
                    else {
                        if (pagecount <= pageindex) {
                            var noli = $("ul.comment").find("li.nocomment");
                            //无法加载更多
                            noli.find("div").html('没有更多评论了！');
                            noli.show();
                        }
                        $(".newsList-moreWrap").hide();
                    }

                    $(data.comment).each(function (index, lista) {

                        var ddd = lista.created_at + ":01";
                        var wappubTime = new Date(ddd.replace("-", "/").replace("-", "/"));
                        var dateString = GetDateString(wappubTime);

                        var htmlcmt = '<li class="comment-li"><div class="comment-head">';
                        htmlcmt += '<img src="' + lista.usr_thumb + '" alt="' + lista.usr_nickname + '"></div>';
                        htmlcmt += '<div class="comment-info"><div class="description">';
                        htmlcmt += '<span class="user-name">' + lista.usr_nickname + '</span>';
                        htmlcmt += '<span class="comment-time">' + dateString + '</span></div>';
                        htmlcmt += '<div class="comment-text">';
                        htmlcmt += '<p>' + html_encode(lista.comment) + '</p></div>';
                        htmlcmt += '	<div class="comment-op"><div class="reply-box fr">	<img class="fl" src="images/comment-reply.png">';
                        htmlcmt += '	<span class="action-reply">回复</span></div>';
                        htmlcmt += '<div class="zan-box fr" pid="' + lista.cid + '"><img class="fl" src="images/comment-zan.png">';
                        htmlcmt += '<span class="fl">' + lista.like + '</span></div></div></div><p class="clear"></p>';
                        htmlcmt += '<ul class="reply-comment"> ';


                        var childcount = 0;
                        $(lista.childlist).each(function (indexs, child_list) {
                            var linshistyle = '';
                            if (indexs > 1) {
                                linshistyle = 'style="display:none;"';
                            }

                            htmlcmt += '<li class="reply-comment-li" ' + linshistyle + '>';
                            htmlcmt += '<h1 class="rcl-name">' + child_list.name + '</h1>';
                            htmlcmt += '<p class="rcl-text">' + html_encode(child_list.cmt) + '</p>';
                            htmlcmt += '</li>';

                            childcount += 1;
                        })
                        if (childcount > 2) {
                            htmlcmt += '<li class="reply-comment-li">';
                            htmlcmt += '<a href="javascript:;" id="reply-controller-pu" style="display: none;">隐藏部分</a>';
                            htmlcmt += '<a href="javascript:;" id="reply-controller-op" style="display: block;">展开全部</a>';
                            htmlcmt += '</li>';
                        }


                        htmlcmt += ' </ul><p class="clear"></p>';

                        htmlcmt += '<div class="reply-textarea-box">';
                        htmlcmt += '<textarea class="reply-text-area reply-user-text" placeholder="在此输入回复内容..." maxlength="480"></textarea>';
                        htmlcmt += '<div class="reply-user-op">';
                        htmlcmt += '<span>限制500个字</span>';
                        htmlcmt += '<a class="action-reply-send"  pid="' + lista.cid + '" href="javascript:;">回复</a>';
                        htmlcmt += ' </div>';
                        htmlcmt += '</div></li>';



                        $(htmlcmt).insertBefore($(".newsList-moreWrap"));

                    });
                    //$("#discussnum").html(data.total_item);
                }
                else if (data.result == -7) {
                    var noli = $("ul.comment").find("li.nocomment");
                    if ($("ul.comment").find("li").length <= 2) {
                        //无数据
                        noli.find("div").html('还没有评论，赶快去抢沙发吧！');
                    }
                    noli.show();
                }
            }
        },
        error: function (xhr, errorType, error) {
            //alert('加载失败:' + error);
            return false;
        }
    });
}
/*收藏加载*/
function favinit() {
    var cidvalue = $("#hiddenpid").val();
    var parms = {
        action: "favinit",
        cids: cidvalue,
        columnid: $("#hiddencolumnid").val(),
        dt: new Date()
    };

    $.ajax({
        type: "POST",
        url: "/ashx/Comment.ashx",
        data: parms,
        dataType: "json",
        beforeSend: function () {
        },
        success: function (data) {
            if (data != undefined) {
                if (data.result == 1) {


                    var favnum = data.favnum;

                    if (favnum > 0) {
                        $("#collect-img").attr("src", "images/op-collected.png");
                        $("#collect-img").css("visibility", "visible");
                        $("#collect-img").css("opacity", "100");
                        $("#uop-collect").attr("flag", "0")
                    }
                }
            }

        },
        error: function (xhr, errorType, error) {

        }
    });




}


function Delfav() {
    //收藏请求
    var cidvalue = $("#hiddenpid").val();
    var parms = {
        action: "dfavnews",
        cids: cidvalue,
        dt: new Date()
    };
    var isfav = false;
    $.ajax({
        type: "post",
        url: "/ashx/Comment.ashx",
        data: parms,
        dataType: "json",
        beforeSend: function () {

        },
        success: function (data) {

            if (data.result == -500) {
  window.location.href = '/account/login.html?u=' + window.location;
             
            } else {
                $("#collect-img").attr("src", "images/op-collect.png");
                $("#collect-img").css("visibility", "inherit");
                $("#collect-img").css("opacity", "1");
                $("#uop-collect").attr("flag", "1")
            }

        },
        error: function (xhr, errorType, error) {
            //alert('收藏失败，请刷新页面后再试！');
            return false;
        },
        complete: function () {

        }
    });


}



