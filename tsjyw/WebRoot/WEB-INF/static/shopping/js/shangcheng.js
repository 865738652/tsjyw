function showAll(){		 
        var div=document.getElementById("yhmUL");
        if(div.style.display=="block")
div.style.display="none";
else
//div.style.opacity=1;
div.style.display="block";
    }
function fondser(){
var bdiv=document.getElementById("searchinput");
var search_text=document.getElementById("searchinput").value;
//if(bdiv.style.display=="block"){
if(search_text){
    var base_url = document.getElementById("baseurl").attributes["data-value"].value;
var search_url = base_url+'search.php?mod=forum&searchid=1&orderby=lastpost&ascdesc=desc&searchsubmit=yes&kw='+search_text;
  var form = document.getElementById("searchform");
  form.submit();
    }
//bdiv.style.display="none";
//}
//else{
bdiv.style.display="block";
        //}

}


jQuery(document).ready(function(){
//底部向上滚动
        var boxLeft = jQuery(".wp").offset().left;
jQuery(".fixedul").css({"left":boxLeft+1210+"px"})

jQuery(".bli2").mouseenter(function(e) {
            jQuery(".wximg").show();
        }).mouseleave(function(){
 jQuery(".wximg").hide();
});

jQuery(window).scroll(function(){
if(jQuery(window).scrollTop()>100){
jQuery(".bli5").fadeIn();
}
else{
jQuery(".bli5").hide();
}
})
jQuery(".bli5").click(function(){
jQuery("html, body").animate({ scrollTop:0},500);
})
})	





var future=  1480244451*1000;
function ok_lets_go() {

var timelines = $('left').getElementsByTagName('input');
for(var i=0;i<timelines.length;i++) {
you_go(timelines[i].id, (timelines[i].value*1000));
timelines[i].value --;

}
setTimeout("ok_lets_go()", 1000);
}

function you_go(id, now){

days = (now-future) / 1000 / 60 / 60 / 24;

if(now-future < 0){
document.getElementById('time_'+id).innerHTML = '已经结束';
$('vd_'+id).className="a_lt";
return;
}

        dayNum = Math.floor(days);
        hours = (now-future) / 1000 / 60 / 60 - (24 * dayNum);
        houNum = Math.floor(hours);
        if(houNum < 10){
            houNum = "0" + houNum;
        }
        minutes = (now-future) / 1000 / 60 - (24 * 60 * dayNum) - (60 * houNum);
        minNum = Math.floor(minutes);
        if(minNum < 10){
            minNum = "0" + minNum;
        }
        seconds = (now-future) / 1000 - (24 * 60 * 60 * dayNum) - (60 * 60 * houNum) - (60 * minNum);
        secNum = Math.floor(seconds);
        if(secNum < 10){
            secNum = "0" + secNum;
        }
        millisec=(now-future)-(24*60*60*1000*dayNum)-(60*60*1000*houNum)-(60*1000*minNum)-(secNum*1000);
        milli=Math.floor(millisec/10);
        if(milli<10){
            milli="0"+milli;
}
document.getElementById('time_'+id).innerHTML = dayNum ? (dayNum+" 天 "+houNum + ":" + minNum) : (houNum + ":") + (minNum + ":")+ secNum ;
//(dayNum?dayNum+" 天 ":'')+ (houNum + ":") + (minNum + ":")+ secNum;
}

ok_lets_go();













_attachEvent(document,'click',function(){hidecomiismenu('comiis_sousuo_menu');});
function hidecomiismenu(id) {

var menuObj = $(id);

var menu = $(menuObj.getAttribute('ctrlid'));

if(ctrlclass = menuObj.getAttribute('ctrlclass')) {

var reg = new RegExp(' ' + ctrlclass);

menu.className = menu.className.replace(reg, '');

}

menuObj.style.display = 'none';

}

function new_showTopLink() {

var ft = $('ft');

if(ft){

var scrolltop = $('scrolltop');

var viewPortHeight = parseInt(document.documentElement.clientHeight);

var scrollHeight = parseInt(document.body.getBoundingClientRect().top);

var basew = parseInt(ft.clientWidth);

var sw = scrolltop.clientWidth;

if (basew < 1500) {

var left = parseInt(fetchOffset(ft)['left']);

left = left < sw ? left * 2 - sw : left;

scrolltop.style.left = ( basew + left ) + 'px';

} else {

scrolltop.style.left = 'auto';

scrolltop.style.right = 0;

}

if (BROWSER.ie && BROWSER.ie < 7) {

scrolltop.style.top = viewPortHeight - scrollHeight - 150 + 'px';

}

if (scrollHeight < -100) {

scrolltop.style.visibility = 'visible';

} else {

scrolltop.style.visibility = 'hidden';

}

}

}

if($("myrepeats")){

$("comiis_key").appendChild($("myrepeats"));

}

if($("qmenu_loop")){

var qmenu_timer, qmenu_scroll_l;

var qmenu_in = 0;

var qmenu_width = 246;

var qmenu_loop = $('qmenu_loop');

var qmenu_all_width = 41 * $('qmenu_loopul').getElementsByTagName("li").length - qmenu_width;

if(qmenu_all_width < 20){

$('qmenu_an').style.display = 'none';

}

}

function qmenu_move(qmenu_lr){

if(qmenu_in == 0 && ((qmenu_lr == 1 && qmenu_loop.scrollLeft < qmenu_all_width) || (qmenu_lr == 0 && qmenu_loop.scrollLeft > 0))){

qmenu_in = 1;

qmenu_scroll_l = qmenu_loop.scrollLeft;

qmenu_timer = setInterval(function(){

qmenu_scroll(qmenu_lr);

}, 10);

}

}

function qmenu_scroll(qmenu_lr){

if((qmenu_lr == 1 && qmenu_loop.scrollLeft >= qmenu_width + qmenu_scroll_l) || (qmenu_lr == 0 && ((qmenu_loop.scrollLeft <= qmenu_scroll_l - qmenu_width) || qmenu_loop.scrollLeft == 0))){

clearInterval(qmenu_timer);

qmenu_in = 0;

}else{

if(qmenu_lr == 1){

qmenu_loop.scrollLeft += Math.round((qmenu_width + qmenu_scroll_l - qmenu_loop.scrollLeft) / 15) + 1;

}else{

qmenu_loop.scrollLeft -= Math.round((qmenu_width - (qmenu_scroll_l - qmenu_loop.scrollLeft)) / 15) + 1;

}

}

}
var _hmt = _hmt || [];
(function() {
  var hm = document.createElement("script");
  hm.src = "//hm.baidu.com/hm.js?a75705ab508dcc91a848d70999542cd2";
  var s = document.getElementsByTagName("script")[0];
  s.parentNode.insertBefore(hm, s);
})();


var tipsinfo = '46989062|X3.2|0.6||0||0|7|1480244451|6da2adcd9fe3d521b835f67423b1e9b8|2';

initSearchmenu('srctxt');
simulateSelect('sctype');
simulateSelect('sctime');
$('scbar').style.display = 'none';