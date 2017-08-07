
jQuery(function ($) {
    $.fn.anchor = function (e) {
        var t,
        i = $(window),
        n = $(this),
        a = n.parent(),
        o = {
            position: n.css('position'),
            display: n.css('display'),
            top: n.css('top'),
            right: n.css('right'),
            bottom: n.css('bottom'),
            left: n.css('left'),
            width: n[0].style.width
        };
        return e = e || {
        },
        e.anchor = e.anchor || n,
        e.anchor = e.anchor instanceof $ ? e.anchor : $(e.anchor),
        t = e.anchor.offset(),
        i.off('.anchor').on('scroll.anchor resize.anchor', function () {
            i.height() - n.outerHeight() > 130 && i.scrollTop() > t.top + (e.isBottom ? e.anchor.height()  : 0) ? n.css({
                position: 'fixed',
                display: 'block',
                top: 42,
                width: a.width()
            }).data('anchor', !0)  : (n.data('anchor') && n.css(o).data('anchor', !1), t = e.anchor.offset())
        }).triggerHandler('scroll.anchor'),
        n
    }
    $('.v-ranking li').mouseover(function () {
        var $this = $(this);
        $this.find('.v-rLink').addClass('v-rLinkb');
        $this.find('.v-rImg').show();
        $this.siblings('li').find('.v-rLink').removeClass('v-rLinkb');
        $this.siblings('li').find('.v-rImg').hide();
    });
   
});
