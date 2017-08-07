/*
 * <li>
 *  <a class='v-rLink v-rLinkb'  href='http://news.87870.com/xinwennr-13709.html' target='_blank' title='87870牵手国富纵横 强强联合开创“家居+VR”新格局'>
 *   <span class='v-rTop v-rTop3'>1</span><span class='v-rTxt'>87870牵手国富纵横 强强联合开创“家居+VR”新格局</span>
 *   <div class='v-rImg' style='display: block;'>
 *    <img src='http://pic.87870.com/upload/images/vr87870/2016/8/31/th_218x135_f5a8ef2e-112b-4016-bc33-7b6379c6a81a.jpg' >
 *   </div>
 *  </a>
 * </li>
 * <li>
 *  <a class='v-rLink '  href='http://news.87870.com/xinwennr-13595.html' target='_blank' title='揭秘地表最强工作室Framestore如何创建VR火星校车'>
 *   <span class='v-rTop v-rTop3'>2</span><span class='v-rTxt'>揭秘地表最强工作室Framestore如何创建VR火星校车</span>
 *   <div class='v-rImg' style='display: block;'>
 *    <img src='http://pic.87870.com/upload/images/vr87870/2016/8/29/th_218x135_9693dc71-fb99-4bc2-909c-b0b82410504c.jpg' >
 *   </div>
 *  </a>
 * </li>
 * <li>
 *  <a class='v-rLink '  href='http://news.87870.com/xinwennr-13605.html' target='_blank' title='210°视场角！ 一个杀手级VR头显EyeForce正在诞生'>
 *   <span class='v-rTop v-rTop3'>3</span><span class='v-rTxt'>210°视场角！ 一个杀手级VR头显EyeForce正在诞生</span>
 *   <div class='v-rImg' style='display: block;'>
 *    <img src='http://pic.87870.com/upload/images/vr87870/2016/8/29/th_218x135_3bfcc5b6-1906-4bdc-ae21-23c0ad4fba17.jpg' >
 *   </div>
 *   </a>
 *  </li>
 *  <li>
 *   <a class='v-rLink '  href='http://news.87870.com/xinwennr-13676.html' target='_blank' title='海天盛筵算很污？那是因为你没看过这部VR小黄片'>
 *    <span class='v-rTop '>4</span><span class='v-rTxt'>海天盛筵算很污？那是因为你没看过这部VR小黄片</span>
 *    <div class='v-rImg' style='display: block;'>
 *     <img src='http://pic.87870.com/upload/images/vr87870/2016/8/31/th_218x135_e7b607c0-9a2f-4108-bc31-50e2b108390b.jpg' >
 *    </div>
 *   </a>
 *  </li>
 */

$(document).ready(function () {
    $.ajax({
        url: "../Index/getHotArticle",
        data: {pageNumber: 1, pageSize: 10},
        dataType: "json",
        beforeSend: function () {
            //$('#morelite').show();
            //$('.news_botword').html("");
        },
        success: function (data) {
            if (data != undefined && data.code == "succ") {
            	var hotlist="";
            	for (var i = 0; i < data.rows.length; i++) {
            		var li = "<li> <a class='v-rLink";
            		if (i == 0)
            			li += " v-rLinkb";
            		li += "' href='../Index/ShowArticle?articleId=" + data.rows[i].articleId + "' target='_blank' title='" + data.rows[i].articleTitle + "'>";
            		li += "<span class='v-rTop";
            		if (i < 3)
            			li += " v-rTop3"
            		li += "'>" + (i+ 1) + "</span><span class='v-rTxt'>" + data.rows[i].articleTitle + "</span>";
            		li += "<div class='v-rImg' style='" + (i == 0 ? "display: block;" : "") + "'><img src='" + (data.rows[i].imagePath == null? "../images/1.jpg" : data.rows[i].imagePath) + "' ></div></a></li>";
            		hotlist += li;
            	}
            	$('.v-ranking').html(hotlist);
            	$('.v-ranking li').mouseover(function () { 
            		var $this = $(this);
            		$this.find('.v-rLink').addClass('v-rLinkb');
            		$this.find('.v-rImg').show();
            		$this.siblings('li').find('.v-rLink').removeClass('v-rLinkb');
            		$this.siblings('li').find('.v-rImg').hide();
            	});
            	$('.video-ranking').anchor();
            }
        },
        error: function (xhr, errorType, error) {
            //alert(error);
            return false;
        }
    });
});