var windowHeight = 0;
jQuery(document).ready(function ($) {
    windowHeight = parseInt($(window).height());
    loadingImg();
    $(window).scroll(function () {
        loadingImg();
    });
   
});
function loadingImg() {
    var topY = parseInt($(window).scrollTop()) + windowHeight;
    $("img[data-src]").each(function () {
        if (topY >= $(this).offset().top) {
            var data_src = $(this).attr("data-src");
            if (data_src == "") {
                data_src = "../images/no.jpg";
            }
            $(this).attr("src", data_src);
            $(this).removeAttr("data-src");
        }
    }).error(function () {
        $(this).attr("src", "../images/no.jpg");
    });

}