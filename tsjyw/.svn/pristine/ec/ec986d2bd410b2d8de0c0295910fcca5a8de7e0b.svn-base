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
	var url = "../Index/getAllSchool";
	/*
    var parms;
    parms = {
        sort: $("#hiddenorderby").val(),
        pageNumber:$("#PageIndex").val(),
        pageSize: $("#PageSize").val(),
        dt: new Date()
    };
    */
	var parms = $('#searchform').serialize();
	parms += "&pageNumber=" + $("#PageIndex").val() + "&pageSize=" + $("#PageSize").val();
	alert(parms);
   
    $.ajax({
        url: url,
        data: parms,
        dataType: "json",
        contentType:"application/x-www-form-urlencoded;charset=utf-8",
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
                        var url = '../School/' + lista.schoolId;
                        var title = lista.schoolName;
                        var imageurl = lista.schoolLogo == null ? "../images/no.jpg" : lista.schoolLogo;
                        var colorclass = 'bgcolor1';
                        var strcolorname = '';
                        var strcolorsize = '';
                        var html = '';
                        var taglist = new Array();
                        taglist = "01-学校教育,02-青少年心理".split(',');
                        var htmltag = '';
                        $(taglist).each(function (idexi, listta) {
                            if (listta != "") {
                                var newlisttag = listta.split('-');
                                htmltag += '<a href="">' + newlisttag[1] + '</a>';
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
                        html += '<div class="newsliimg"><a href="' + url + '" target="_blank"><img src="../images/no.jpg"  data-src="'+ imageurl +'"></a></div>';
                        html += '<div class="newsliR">';
                        html += '<div class="newsliRtitle">';
                        html += '<span class="newlb" style="background-color:' + strcolorsize + ';display:none;">' + strcolorname + '</span>';
                        html += '<a href="' + url + '" target="_blank">' + title + '</a>';
                        html += '</div>';
                        html += '<div class="nresliRtime">2016-09-01</div>';
                        html += '<div class="newsliP">' +cutstr(lista.schoolIntroduction,170) + '</div>';
                        html += '<div class="newsliBgjc">';
                        html += '<div class="newsliBgjLeft"><span>关键词：</span> ' + htmltag + '</div>';
                        html += '<div class="newsliBgjcRight">';
                        html += '<div class="look_R">';
                        html += '<span class="lookbg2"></span>';
                        html += '<span class="looknum"><em>' + 53 + '</em></span>';
                        html += '</div>';
                        html += '<div class="look_R">';
                        html += '<span class="lookbg"></span>';
                        html += '<span class="looknum">' + 621 + '</span>';
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

function onSearchSchool() {
	loadData(1);
}

