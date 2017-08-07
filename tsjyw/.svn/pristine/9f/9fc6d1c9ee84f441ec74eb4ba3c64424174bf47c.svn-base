/*
 * Data Lazy Load - jQuery plugin for lazy loading data
 *
 * Copyright (c) 2007-2013 logbird
 *
 * Licensed under the MIT license:
 *   http://www.opensource.org/licenses/mit-license.php
 *
 * Project home:
 *   https://github.com/logbird/DataLazyLoad
 *
 * Version:  1.0.0
 *
 */
(function ($, window, undefined) {
    $.fn.DataLazyLoad = function (options) {
        var elements = $(this);
        var settings = {
            //Data Load Offset
            offset: 200,
            //Load data callback
            load: function () { },
            //Which page to load
            page: 2
        }
        if (options) {
            $.extend(settings, options);
        }
        //The height of the browser window
        var winHeight = $(window).height();
        var locked = false;
        var unLock = function (nextPage) {
            //Next load page, 0 is end
            if (nextPage > 0) {
                settings.page = nextPage;
                locked = false;
            }
        }
        var scrollTimer = null;//通过延迟执行达到只执行1次效果
        $(window).scroll(function () {
            var scrollTop = $(window).scrollTop();

            //elements height + elements top - (scrollbar top + browser window height)
            var offset = $(elements).offset().top + $(elements).height() - (scrollTop + winHeight);
            if (offset < settings.offset && !locked) {
                if (scrollTimer) {
                    clearTimeout(scrollTimer);
                }
                scrollTimer = setTimeout(function () {
                    locked = true;
                    //alert(offset);
                    //alert("$(elements).offset().top:" + $(elements).offset().top + " $(elements).height():" + $(elements).height()
                    //        + "  win scrollTop:" + scrollTop + " win Height:" + winHeight);

                    settings.load(settings.page, unLock);
                }, 50);
            }

        });
    }
})(jQuery, window);