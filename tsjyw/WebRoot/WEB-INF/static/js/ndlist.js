$(document).ready(function () {
    loadData(1);
});

function initdata() {
    if ($("#hiddencid").val().length <= 0) {
        return;
    }
    
    var url = "../Index/getAllNetCourse";
    var parms = {
        action: "ndlist",
        netCourseTypeId: $("#hiddencid").val(),
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
                    	var url = '../Index/ShowNetCourse?netCourseId=' + lista.netCourseId;
                      
                    	 var title = lista.netCourseName;
                         var imgurl = lista.netCourseImgPath == null ? "../images/no.jpg" : lista.netCourseImgPath;

                        html += '<li>';
                        html += '<a href="' + url + '" target="_blank">';
                        html += ' <span class="v-imgWrap">';
                        html += '<img class="testone" width=\"395\" height=\"244\" src="../images/no.jpg"  data-src="' + imgurl + '" />';
                        html += '</span>';
                        html += '<span class="v-text">' + title + '';
                        html += '</span>';
                        html += '<span class="v-tag">';
                        html += '<i class="v-visited">652</i>';
                        html += '<i class="v-review">1</i>';
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

