// JavaScript Document
$(document).ready(function(e) {
    //游戏TOP榜		
	$(".gamePHbot").each(function(index, element) {
		$(this).children().children(".gamePHliname:first").addClass("borbobm");
        $(this).children().children(".gamePHliimg:first").addClass("disblock");
	});
	
	$('.gamePHbot .gamePHli').mouseover(function() {
        var $this = $(this);
        $this.find('.gamePHliname').addClass('borbobm');
        $this.find('.gamePHliimg').show();
        $this.siblings('.gamePHli').find('.gamePHliname').removeClass('borbobm');
        $this.siblings('.gamePHli').find('.gamePHliimg').hide();
    });	
});

