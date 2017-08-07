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

function getStyle(obj, name) {
    if (obj.currentStyle) {
        return obj.currentStyle[name]
    } else {
        return getComputedStyle(obj, false)[name]
    }
}

function getByClass(oParent, nClass) {
    var eLe = oParent.getElementsByTagName('*');
    var aRrent = [];
    for (var i = 0; i < eLe.length; i++) {
        if (eLe[i].className == nClass) {
            aRrent.push(eLe[i]);
        }
    }
    return aRrent;
}

function startMove(obj, att, add) {
    clearInterval(obj.timer)
    obj.timer = setInterval(function() {
        var cutt = 0;
        if (att == 'opacity') {
            cutt = Math.round(parseFloat(getStyle(obj, att)));
        } else {
            cutt = Math.round(parseInt(getStyle(obj, att)));
        }
        var speed = (add - cutt) / 4;
        speed = speed > 0 ? Math.ceil(speed) : Math.floor(speed);
        if (cutt == add) {
            clearInterval(obj.timer)
        } else {
            if (att == 'opacity') {
                obj.style.opacity = (cutt + speed) / 100;
                obj.style.filter = 'alpha(opacity:' + (cutt + speed) + ')';
            } else {
                obj.style[att] = cutt + speed + 'px';
            }
        }

    }, 30)
}

window.onload = function() {
    var oDiv = document.getElementById('playBox');
    var oPre = getByClass(oDiv, 'pre')[0];
    var oNext = getByClass(oDiv, 'next')[0];
    var oUlBig = getByClass(oDiv, 'oUlplay')[0];
    var aBigLi = oUlBig.getElementsByTagName('li');
    var oDivSmall = getByClass(oDiv, 'smalltitle')[0]
    var aLiSmall = oDivSmall.getElementsByTagName('li');

    function tab() {
        for (var i = 0; i < aLiSmall.length; i++) {
            aLiSmall[i].className = '';
        }
        aLiSmall[now].className = 'thistitle'
        startMove(oUlBig, 'left', -(now * aBigLi[0].offsetWidth))
    }
    var now = 0;
    for (var i = 0; i < aLiSmall.length; i++) {
        aLiSmall[i].index = i;
        aLiSmall[i].onclick = function() {
            now = this.index;
            tab();
        }
    }
    oPre.onclick = function() {
        now--
        if (now == -1) {
            now = aBigLi.length;
        }
        tab();
    }
    oNext.onclick = function() {
        now++
        if (now == aBigLi.length) {
            now = 0;
        }
        tab();
    }
    var timer = setInterval(oNext.onclick, 5000) //滚动间隔时间设置
    oDiv.onmouseover = function() {
        clearInterval(timer)
    }
    oDiv.onmouseout = function() {
        timer = setInterval(oNext.onclick, 5000) //滚动间隔时间设置
    }
};

$(function() {

    /* 学校首页 无缝轮播 */
    if ($('#qinZi').length > 0) {

        var box_ul = $('#qinZi').find('ul');
        var box_ul_li = box_ul.find('li');

        /* 克隆一份ul里面的Li标签 插入到 ul 最后面*/
        box_ul_li.clone().appendTo(box_ul);

        /* 重新设置 ul 的宽度并返回宽度值 */
        var ul_width = box_ul.css({
            width: box_ul.find('li').length * box_ul_li.eq(0).outerWidth()
        }).outerWidth();

        /* 创建定时器 */
        var speed = 30;
        var timer = setInterval(ul_move, speed);

        /* ul 滚动函数 */
        function ul_move() {
            /* 获取 ul 的 left 值（0或负数） */
            var ul_left = box_ul.position().left;
            box_ul.css({
                /* 如果 ul_left 的值等于克隆后 ul 宽度的一半就说明整个 ul 滚动到了一半，就给 ul 的 left 值设置为 0 （重新开始），否则就继续 ul_left 的值减一（往左走一像素） */
                left: Math.abs(ul_left) == ul_width / 2 ? 0 : ul_left - 1
            });
        };

        /* 鼠标悬停 ，hover方法里的两个匿名函数第一个是鼠标移入，第二个是鼠标移出 */
        box_ul.hover(
            function() {
                /* 移入时清除定时器（停止滚动） */
                clearInterval(timer);
            },
            function() {
                /* 移出时重新设置定时器 */
                timer = setInterval(ul_move, speed);
            }
        );
    };
});
