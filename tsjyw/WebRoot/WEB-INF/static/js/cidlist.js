$(document).ready(function () {
    window.setTimeout(function () {
        if ($(".newstop >a").length > 0) {
            $(".newstop >a").each(function () {
                //$(this).removeClass("newtophov");
                if ($(this).attr("data-id") == $("#hiddencid").val()) {
                    $(this).addClass("newtophov");
                }
            });
        }
    }, 10);
    loadData(1);
});

function initdata() {
	var url = "../Index/getArticleByModule";
    var parms;
    if ($("#hiddencid").length > 0) {
        parms = {
            action: "newslist",
            moduleId: $("#hiddencid").val(),
            sort: $("#hiddenorderby").val(),
            pageNumber:$("#PageIndex").val(),
            pageSize: $("#PageSize").val(),
            recommend: false,
            dt: new Date()
        };
    }
    else if ($("#hiddentid").length > 0) {
        parms = {
            action: "tagnewslist",
            tid: $("#hiddentid").val(),
            pageindex: $("#PageIndex").val(),
            pagesize: $("#PageSize").val(),
            dt: new Date()
        };
    }
    else if ($("#hiddenauth").length > 0) {
        parms = {
            action: "authorlist",
            key: $("#hiddenauth").val(),
            pageindex: $("#PageIndex").val(),
            pagesize: $("#PageSize").val(),
            dt: new Date()
        };
    }
    else if ($("#hiddenfabu").length > 0) {
        parms = {
            action: "fabulist",
            fabu: $("#hiddenfabu").val(),
            pageindex: $("#PageIndex").val(),
            pagesize: $("#PageSize").val(),
            dt: new Date()
        };
    }
    
    $.ajax({
        url: url,
        data: parms,
        dataType: "json",
        beforeSend: function () {
            //$('#morelite').show();
            //$('.news_botword').html("");
        },
        success: function (data) {
            if (data != undefined) {
                if (data.code == "succ") {
                    var listdata = data.rows;
                    var pageSize = parseInt($("#PageSize").val());
                    var pageCount = Math.floor((data.total + pageSize - 1) / pageSize); 
                    $("#PageCount").val(pageCount);
                    $("#TotalCount").val(data.total);//TotalCount
                    
                    $('.news_botword').html("");
                    
                    $(listdata).each(function (index, lista) {
                        var url = '../Index/ShowArticle?articleId=' + lista.articleId;
                        var title = lista.articleTitle;
                        var imgurl = lista.imagePath == null ? "../images/no.jpg" : lista.imagePath;
                        var colorclass = 'bgcolor1';

                        var strcolorname = '';
                        var strcolorsize = '';
                        //if (lista.colorName.length > 3) {
                        //    var strcolors = new Array();
                        //    strcolors = lista.colorName.split('-');
                        //    if (strcolors.length > 0) {
                        //        strcolorname = strcolors[0];
                        //        strcolorsize = strcolors[1];
                        //    }
                        //}
                        var html = '';
                        var taglist = new Array();
						if (lista.articleKeyword != null && lista.articleKeyword != '')
							taglist = lista.articleKeyword.split(' ');
						
                        var htmltag = '';
                        $(taglist).each(function (idexi, listta) {
                            if (listta != null && listta != "") {
                                htmltag += '<a href="">' + listta + '</a>';
                            }
                        });
                        //var discuss = '';
                        //if (lista.discuss.length > 0) {
                        //    discuss = lista.discuss;
                        //}
                        //var click = '';
                        //if (lista.click.length > 0) {
                        //    click = lista.click;
                        //}
                        html += '<div class="newsli">';
                        html += '<div class="newsliimg"><a href="' + url + '" target="_blank"><img src="../images/no.jpg"  data-src="' + imgurl + '"></a></div>';
                        html += '<div class="newsliR">';
                        html += '<div class="newsliRtitle">';
                        html += '<span class="newlb" style="background-color:' + strcolorsize + ';display:none;">' + strcolorname + '</span>';
                        html += '<a href="' + url + '" target="_blank">' + title + '</a>';
                        html += '</div>';
                        html += '<div class="nresliRtime">' + getSmpFormatDateByLong(lista.articleTime,false) + '</div>';
                        html += '<div class="newsliP">' +cutstr(lista.articleAbstract,170) + '</div>';
                        html += '<div class="newsliBgjc">';
                        html += '<div class="newsliBgjLeft"><span>关键词：</span> ' + htmltag + '</div>';
                        html += '<div class="newsliBgjcRight">';
                        html += '<div class="look_R">';
                        html += '<span class="lookbg2"></span>';
                        html += '<span class="looknum"><em>' + 0 + '</em></span>';
                        html += '</div>';
                        html += '<div class="look_R">';
                        html += '<span class="lookbg"></span>';
                        html += '<span class="looknum">' + lista.articleReadCount + '</span>';
                        html += '</div>';
                        html += '</div>';
                        html += '</div>';
                        html += '</div>';
                        html += '</div>';

                        $('.news_botword').append(html);
                    });
                }
                else {
                    return false;
                }
            } else {
                return false;
            }
        },
        error: function (xhr, errorType, error) {
            //alert(error);
            return false;
        },
        complete: function () {
            //$('#morelite').hide();
            loadhtml();
            loadingImg();
        }
    });
}

