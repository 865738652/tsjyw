$(document).ready(function(){
    $(".listTL").click(function(){
        var x = $(this).index();
        $(this).addClass("listTLtwo").siblings().removeClass("listTLtwo");
        $(".memboxone").eq(x).show().siblings().hide();
    })  
    $(".listTnav").click(function(){
        var a = $(this).index();
        $(this).addClass("listTLtwo").siblings().removeClass("listTLtwo");
        $(".listnavbox").toggle();
    })      
    $(".listnavclose").click(function(){
        $(".listnavbox").hide();
    })  
    //footer
    var winH = $(window).height();
    var bodyH = $("body").height();
    var footH = $(".footer").outerHeight();
    var BHi = $(".fixbot").outerHeight();
    
    console.log(winH)
    console.log(bodyH)
    if(bodyH+footH < winH){
        $("body").height(winH).css({"margin-bottom": 0});
        $(".footer").css({"position": "absolute","bottom":0})
    }
    if($("div").hasClass('fixbot')){
        $("body").css({"margin-bottom": (BHi+footH)+"px"});
        if(bodyH < winH){
            $("body").height(winH).css({"margin-bottom": BHi+"px"});
            $(".footer").css({"position": "absolute","bottom":0})
        }
        //$(".footer").css({"position": "fixed","bottom":footH+"px"})
    //$(".footer").css({ "position": "absolute","margin-bottom": "2.5rem","margin-top": ".8rem"});
    //$(".footer").css({ "position": "absolute","margin-bottom": BHi+footH+"px","margin-top": ".3rem"});
    //, "top": bodyH-footH+'px'
    }
    
    // if(bodyH > winH){
    //  // $(".footer").css({ "margin-bottom": "1.5rem","margin-top": "15px", "top": bodyH-footH+'px' });
    // }else{
    //  $(".footer").css({"position": "absolute","bottom":"0px"})
    // }
//	 $(".headTli").click(function(){
//        $(this).addClass("headlihov").siblings().removeClass("headlihov");
//    }) 

    
})