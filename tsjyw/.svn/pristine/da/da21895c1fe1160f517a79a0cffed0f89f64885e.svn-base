$(document).ready(function () {
    var pid = $("#hiddenpid").val();
    if (pid <= 0) {
        return;
    }
    //$(".v-ranking").load("/2016/Control/hotnews2016.html", function (response, status, xhr) {
    //    if (status == "success") {
    //        $('.v-ranking li').mouseover(function () {
    //            var $this = $(this);
    //            $this.find('.v-rLink').addClass('v-rLinkb');
    //            $this.find('.v-rImg').show();
    //            $this.siblings('li').find('.v-rLink').removeClass('v-rLinkb');
    //            $this.siblings('li').find('.v-rImg').hide();
    //        });
    //    }
    //});
    //$('.video-ranking').anchor();
    window.setTimeout(function () {
        $('.v-ranking li').mouseover(function () {
            var $this = $(this);
            $this.find('.v-rLink').addClass('v-rLinkb');
            $this.find('.v-rImg').show();
            $this.siblings('li').find('.v-rLink').removeClass('v-rLinkb');
            $this.siblings('li').find('.v-rImg').hide();
        });
        $('.video-ranking').anchor();
    }, 100);
    //initdata();
    //window.setTimeout(function () {
       // othernews();
    //}, 400);
    
    //bkwordcheck();
    //$(".sc_link").click(function () {
        
    //});
});

function initdata() {
    if ($("#hiddenpid").val().length < 1) {
        return;
    }
    var parms = {
        action: "newsinfo",
        articleId: $("#hiddenpid").val(),
        dt: new Date()
    };
    $.ajax({
        type: "get",
        url: "../ArticleManage/getArticle",
        data: parms,
        dataType: "json",
        success: function (data) {
            if (data != undefined) {
                if (data.code == "succ") {
                	var article = data.data;
                	document.title = article.articleTitle + "-" + document.title;
                    $(".info_wrap").find("h1").html(article.articleTitle);
                    var viewtime = '<span class="tag_l">' + '2016-09-15' + '</span>';
                    if (article.userName != "") {
                        viewtime += '<span class="tag_l">来源：<em>' + article.userName + '</em></span>';
                    }
                    if (article.userName != "") {
                        viewtime += '<span class="tag_l">作者：<em><a href="#">' + article.userName + '</a></em></span>';
                    }
                    if (article.userName != "") {
                        //$(".fabuadmin").show();
                        //$(".fabuuser").html(lista.fabuadmin);
                        viewtime += '<span class="tag_l">发布者：<em>' + article.userName + '</em></span>';
                        viewtime += '<span class="tag_l"><em id="favoriteAuth" onclick="fouceclick()">【订阅】</em></span>';
                    }
                    $(".infofabudate").html(viewtime);
                    $(".clicknum").html(article.articleReadCount);
                    $(".discussnum").html(0);//0103-VR行业动态
/*
                    if (lista.cname != "") {
                        //alert(lista.cname);
                        var clist = lista.cname.split('-');
                        if (clist.length > 1) {
                            var cidname = clist[1];
                            var cid = clist[0];

                            var cliststr = '<a href="http://www.87870.com/">首页</a> &gt; <a href="http://news.87870.com/xinwennr-' + cid + '.html">' + cidname + '</a> &gt; 正文';
                            $(".arrow").html(cliststr);
                            $("#hiddencid").val(cid);
                        }
                    }
*/
                    $(".info_guide").html("<span class=\"tag\">导读：</span>" + article.articleAbstract);

                    $(".content").html(article.articleContent);
                    $("#hiddenbkword").val("");
                    $("#hiddenbkid").val("");
                    
                    var taglist = new Array();
                    taglist = "01-家庭教育,02-心理健康,03-家长学校".split(',');
                    var htmltag = '';
                    $(taglist).each(function (idexi, listta) {
                        if (listta != "") {
                            var newlisttag = listta.split('-');
                            //htmltag += ' <a href="/en/xinwentag-' + newlisttag[0] + '.html">' + newlisttag[1] + '</a>';//
                            htmltag += ' <a class="key_tag" target="_blank" href="http://news.87870.com/xinwentag2016-1-' + newlisttag[0] + '.html">' + newlisttag[1] + '</a>';
                        }
                    })
                    $(htmltag).appendTo(".key");
                    window.setTimeout(function () {
                        bkwordcheck();
                        commentlist();
                    }, 200);
                }
            }
        },
        error: function (xhr, errorType, error) {
            //alert(error);
            return false;
        }
    });
}


function othernews() {
    var parms = {
        action: "newsother",
        articleId: $("#hiddenpid").val(),
        count: 10,
        dt: new Date()
    };
    $.ajax({
        type: "get",
        url: "../Index/getRelateArticle",
        data: parms,
        dataType: "json",
        beforeSend: function () {
        },
        success: function (data) {
            if (data.code == "succ") {
            	var listdata = data.rows;
                $(listdata).each(function (index, lista) {
                    //var url = '/EN/xinwennr-' + lista.commonId + '.html';
                    var url = '../Index/ShowArticle?articleId=' + lista.articleId;
                    var title = lista.articleName;
                    var imgurl = lista.imagePath;
                    var colorclass = '';
                    var html = '';
                    html = '<li>&#x25aa;<a href="' + url + '" target="_blank" title="' + title + '">' + title + '</a></li>';

                    $(html).appendTo(".other_news");
                });
            }
        },
        error: function (xhr, errorType, error) {
            //alert('加载失败，请刷新页面后再试3！');
            return false;
        }
    });
}

function bkwordcheck() {
    if ($(".content p").length > 0) {
        var listindex = new Array();
        $(".content p").each(function (index) {
            //alert($(this).html());
            if ($(this).find("img").length > 0) {
                listindex[index] = 1;
                //alert(
                
            }
            else {
                listindex[index] = 0;
            }
            if (index > 0) {
                if (listindex[index - 1] == 1) {
                    if ($(this).find("a").length > 0) {
                        $(this).find("a").each(function () {
                            var linkhref = $(this).attr("href");
                            //alert(linkhref);
                            if (linkhref.indexOf("bk.87870.com") > 0) {
                                $(this).replaceWith($(this).html());
                            }
                        });
                    }
                }
            }
        });
        $(".content img").each(function (index) {
            $(this).attr("alt", "").attr("title", "");
        });
        var strbkword = $("#hiddenbkword").val();
        var strbkid = $("#hiddenbkid").val();
        if (strbkword.length > 0) {
            var strbk = strbkword.split(',');
            var strbkid = strbkid.split(',');
            for (var i = 0; i < strbk.length; i++) {
                if (strbk[i].length > 0) {
                    var str = "<a href=\"http://bk.87870.com/baikenr-" + strbkid[i] + ".html\"  target='_blank' style=\"color: rgb(84, 141, 212); text-decoration: underline;\" title=\"" + strbk[i] + "\">" + strbk[i] + "</a>";
                    $("." + strbkid[i]).replaceWith(function (index) {
                        if (index == 0) {
                            return str;
                        }
                        else {
                            return strbk[i];
                        }
                    });
                }
            }
        }
        var hiddenpidvalue = $("#hiddenpid").val();
        if (hiddenpidvalue == "9334") {
            if ($(this).find("a").length > 0) {
                $(this).find("a").each(function () {
                    var linkhref = $(this).attr("href");
                    if (linkhref == "http://bk.87870.com/baikenr-6149.html") {
                        $(this).replaceWith("ui");
                    }
                });
            }
        }
    }
}