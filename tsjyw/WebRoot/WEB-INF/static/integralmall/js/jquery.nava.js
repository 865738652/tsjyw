jQuery.noConflict();
; (function (jQuery) {
    jQuery.fn.extend({
        "nav": function (con) {
			var mnum = jQuery(".event-item").length;
            var $this = jQuery(this), $nav = $this.find('.switch-tab'), t = (con && con.t) || 3000, a = (con && con.a) || 500, i = 0, autoChange = function () {
                $nav.find('a:eq(' + (i + 1 === mnum ? 0 : i + 1) + ')').addClass('current').siblings().removeClass('current');
                $this.find('.event-item:eq(' + i + ')').css('display', 'none').end().find('.event-item:eq(' + (i + 1 === mnum ? 0 : i + 1) + ')').css({
                    display: 'block',
                    opacity: 0
                }).animate({
                    opacity: 1
                }, a, function () {
                    i = i + 1 === mnum ? 0 : i + 1;
                }).siblings('.event-item').css({
                    display: 'none',
                    opacity: 0
                });
            }, st = setInterval(autoChange, t);
            $this.hover(function () {
                clearInterval(st);
                return false;
            }, function () {
                st = setInterval(autoChange, t);
                return false;
            }).find('.switch-nav>a').bind('click', function () {
                var current = $nav.find('.current').index();
                i = jQuery(this).attr('class') === 'prev' ? current - 2 : current;
                autoChange();
                return false;
            }).end().find('.switch-tab>a').bind('click', function () {
                i = jQuery(this).index() - 1;
                autoChange();
                return false;
            });
            return $this;
        }
    });
}(jQuery));
/*
jQuery(document).ready(function(){
	
	//首页商城推荐
	var scrollPic_02 = new ScrollPic();
	scrollPic_02.scrollContId   = "ISL_Cont_1"; //内容容器ID
	scrollPic_02.arrLeftId      = "LeftArr";//左箭头ID
	scrollPic_02.arrRightId     = "RightArr"; //右箭头ID

	scrollPic_02.frameWidth     = 1160;//显示框宽度
	scrollPic_02.pageWidth      = 232; //翻页宽度

	scrollPic_02.speed          = 10; //移动速度(单位毫秒，越小越快)
	scrollPic_02.space          = 10; //每次移动像素(单位px，越大越快)
	scrollPic_02.autoPlay       = false; //自动播放
	scrollPic_02.autoPlayTime   = 3; //自动播放间隔时间(秒)

	scrollPic_02.initialize(); //初始化
							
});*/

