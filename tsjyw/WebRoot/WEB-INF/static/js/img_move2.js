$(function() {

    var wVal = 307;
    var Timer = null;
    $(".keImgs").width($(".keImgs").find("li").size() * wVal)
    var keCutNum = 0;
    var relNum = $(".keImgBtn li").size();

    function srcMove() {
        $(".keImgs").animate({
            "left": -keCutNum * wVal
        }, 100);
        $(".keImgBtn li").removeClass("keImgCutLi");
        $(".keImgBtn li").eq(keCutNum).addClass("keImgCutLi");
        $(".keImgTxt li").hide();
        $(".keImgTxt li").eq(keCutNum).show();
    }

    function keChgImg() {
        keCutNum++;
        if (keCutNum == relNum) {
            keCutNum = 0;
        }
        srcMove();
    }
    var Timer = setInterval(keChgImg, 5000)
    $(".keChgImg").hover(function() {
        clearInterval(Timer)
    }, function() {
        Timer = setInterval(keChgImg, 5000)
    });
    $(".keChgBtnR").click(function() {
        keCutNum++;
        if (keCutNum == relNum) {
            keCutNum = 0;
        }
        srcMove();
    });
    $(".keChgBtnL").click(function() {
        keCutNum--;
        if (keCutNum < 0) {
            keCutNum = relNum - 1;
        }
        srcMove();
    });
    $(".keImgBtn li").hover(function() {
        keCutNum = $(".keImgBtn li").index(this);
        srcMove();
    });
    //End
});
