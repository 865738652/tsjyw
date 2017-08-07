$(document).ready(function () {
    loadData(1);
});

function initdata() {
    if ($("#hiddencid").val().length <= 0) {
        return;
    }
    
    var url = "../Index/getArticleByModule";
    var parms = {
        action: "hdlist",
        moduleId: $("#hiddencid").val(),
        sort: $("#hiddenorderby").val(),
        pageNumber: $("#PageIndex").val(),
        pageSize: $("#PageSize").val(),
        dt: new Date()
    };
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
                    var CName = "";
                    //if (data.CName != "") {

                    //    document.title = data.CName + "-" + document.title;
                    //}
                    var html = '';
                    $(listdata).each(function (index, lista) {
                    	var url = '../Index/ShowArticle?articleId=' + lista.articleId;
                      
                    	 var title = lista.articleTitle;
                         var imgurl = lista.imagePath == null ? "../images/no.jpg" : lista.imagePath;

                        html += '<li>';
                        html += '<a href="' + url + '" target="_blank">';
                        html += ' <span class="v-imgWrap">';
                        html += '<img class="v-img" width=\"395\" height=\"244\" src="../images/no.jpg"  data-src="' + imgurl + '" />';
                        html += '</span>';
                        html += '<span class="v-text">' + title + '';
                        html += '</span>';
                        html += '<span class="v-tag">';
                        html += '<i class="v-visited">' + lista.articleReadCount + '</i>';
                        html += '<i class="v-review">' + 0 + '</i>';
                        html += '</span>';
                        html += '</a>';
                        html += '</li>';
                    });
                    $('.video-list').html(html);
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

