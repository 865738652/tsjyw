<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="comiis_footer cl">

<div id="ft" class="wp comiis_bottom cl ">

<div class="comiis_demail">
<p><img src="../images/weixin.jpg"  width="90px" height="90px">
</p></div>

<div id="frt">



<!--登录-->
<div id="fwin_login" class="fwinmask" initialized="true" style="position: fixed; z-index: 201; left: 394px; top: 159px;display:none;">
  <style type="text/css">object{visibility:hidden;}</style>
  <table cellpadding="0" cellspacing="0" class="fwin">
    <tbody>
      <tr>
        <td class="t_l"></td>
        <td class="t_c" style="cursor:move" onmousedown="dragMenu($('fwin_login'), event, 1)" ondblclick="hideWindow('login')"></td>
        <td class="t_r"></td>
      </tr>
      <tr>
        <td class="m_l" style="cursor:move" onmousedown="dragMenu($('fwin_login'), event, 1)" ondblclick="hideWindow('login')" )"="">&nbsp;&nbsp;</td>
        <td class="m_c" id="fwin_content_login" fwin="login">
  <div id="main_messaqge_LKy66" fwin="login">
  <div id="layer_login_LKy66" fwin="login">
  <h3 class="flb" id="fctrl_login" style="cursor: move;">
    <em id="returnmessage_LKy66" fwin="login">用户登录</em>
    <span><a href="javascript:;" class="flbc" title="关闭" onclick="closeLoginWindow()">关闭</a></span>
  </h3>
<form name="login" class="cl" id="loginForm">
  <div class="c cl">
    <div class="rfm">
      <table>
        <tbody>
          <tr>
            <th>
              <label for="password3_LKy66">用户名：</label>
            </th>
            <td>
              <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/> 
              <input type="text" name="userName" name="userName" size="30" class="px p_fre">
            </td>
            <td class="tipcol">
              <a href="../Account/Register">立即注册</a>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  <div class="rfm">
  <table>
    <tbody>
      <tr>
        <th>
          <label for="password3_LKy66">密码:</label>
        </th>
        <td>
          <input type="password" id="password" name="password" size="30" class="px p_fre">
        </td>
        <td class="tipcol">
          <a href="javascript:void(0);" title="找回密码">找回密码</a>
        </td>
      </tr>
    </tbody>
  </table>
  </div>

  <div class="rfm  bw0">
    <table>
      <tbody>
        <tr>
          <th></th>
          <td>
            <label for="cookietime_LKy66"><input type="checkbox" class="pc" name="cookietime" id="cookietime_LKy66" tabindex="1" value="2592000" fwin="login">自动登录</label>
          </td>
        </tr>
      </tbody>
    </table>
  </div>

  
</div>
</form>
	<div class="rfm mbw bw0">
    <table width="100%">
      <tbody>
        <tr>
          <th>&nbsp;</th>
        <td>
          <button class="pn pnc" type="submit" name="loginsubmit" value="true" tabindex="1" onclick="ajaxSubmit()"><strong>登录</strong></button>
        </td>
        <td>
        </td>
        </tr>
      </tbody>
    </table>
  	</div>
</div>
</div>
</tbody>
</table>
</div>

<script type="text/javascript">
  function closeLoginWindow()
  {
  	document.getElementById("fwin_login").style.display = 'none';
  }
  function showLoginWindow()
  {
    document.getElementById("fwin_login").style.display = null;
  }
  function closeExchangeWindow()
  {
  	document.getElementById("exchangeModal").style.display = 'none';
  }
  
  function ajaxSubmit()
  {
  	jQuery.ajax({
	        type:"post",
	        url:"../Account/Authenticate",
	        dataType:"json",
	        async:true,
	        data:jQuery("#loginForm").serialize(),
	        success:function(data){
	            if(data.status==1){
	                document.getElementById("fwin_login").style.display = 'none';
	                location.reload();
	            }else{
	                alert(data.info);
	            }
	        },
	        error:function (XMLHttpRequest, textStatus, errorThrown){               
	            if(textStatus=="timeout"){
	                alert("响应超时，请稍后重试");
	            }else{
	                alert("登录异常，请稍后重试");
	            }
	        }
	    });  
  }
  function createExchageRecord()
  {
  	jQuery.ajax({
  		url:"../ExchangeRecordManage/createExchangeRecordManage",
  		data:jQuery("#exchangeForm").serialize(),
  		type:"post",
  		dataType:"json",
  		success:function(data){
  			if(data.code == "succ"){
  				alert("兑换成功");
  				location.reload();
  			}
  			else if(data.code == "fail"){
  				alert("兑换失败:"+data.data);
  			}
  		},
  		error:function(){
  			alert("兑换失败");
  		}
  	});
  }
</script>
<!--//登录-->


<c:if test="${not empty viewGoods}">
<!-- 兑换的弹窗 -->
<div id="exchangeModal" class="fwinmask" initialized="true" style="position: fixed; z-index: 201; left: 394px; top: 159px;display:none;">
  <style type="text/css">object{visibility:hidden;}</style>
  <table cellpadding="0" cellspacing="0" class="fwin">
    <tbody>
      <tr>
        <td class="t_l"></td>
        <td class="t_c" style="cursor:move" onmousedown="dragMenu($('fwin_login'), event, 1)" ondblclick="hideWindow('login')"></td>
        <td class="t_r"></td>
      </tr>
      <tr>
        <td class="m_l" style="cursor:move" onmousedown="dragMenu($('fwin_login'), event, 1)" ondblclick="hideWindow('login')" )"="">&nbsp;&nbsp;</td>
        <td class="m_c" id="fwin_content_login" fwin="login">
  <div id="main_messaqge_LKy66" fwin="login">
  <div id="layer_login_LKy66" fwin="login">
  <h3 class="flb" id="fctrl_login" style="cursor: move;">
    <em id="returnmessage_LKy66" fwin="login">兑换商品</em>
    <span><a href="javascript:;" class="flbc" title="关闭" onclick="closeExchangeWindow()">关闭</a></span>
  </h3>
<form name="exchangeForm" class="cl" id="exchangeForm">
  <div class="c cl">
    <div class="rfm">
      <table>
        <tbody>
          <tr>
            <th>
              <label for="password3_LKy66">商品名称：</label>
            </th>
            <td>
              <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/> 
              <input type="hidden" name="goodsId" value="${viewGoods.goodsId}"/>
              <input type="hidden" name="userId" value ="${user.userId}"/>
              <label for="password3_LKy66"><c:out value="${viewGoods.goodsName}"/></label>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  <div class="rfm">
  <table>
    <tbody>
      <tr>
        <th>
          <label for="password3_LKy66">所需积分：</label>
        </th>
        <td>
          <label for="password3_LKy66"><c:out value="${viewGoods.goodsPrice}"/></label>
        </td>
      </tr>
    </tbody>
  </table>
  </div>

  <div class="rfm">
  <table>
    <tbody>
      <tr>
        <th>
          <label for="password3_LKy66">我的积分：</label>
        </th>
        <td>
        	<c:if test="${not empty user}">
        		<c:if test="${empty user.userIntegral}">
        			<label for="password3_LKy66">0</label>
        		</c:if>
        		<c:if test="${not empty user.userIntegral}">
        			<label for="password3_LKy66"><c:out value="${user.userIntegral}"/></label>
        		</c:if>
          	</c:if>
        </td>
      </tr>
    </tbody>
  </table>
  </div>	
	
     <div class="rfm">
  <table>
    <tbody>
      <tr>
        <th>
          <label for="password3_LKy66">剩余数量:</label>
        </th>
        <td>
          <label for="password3_LKy66"><c:out value="${remainCount}"/></label>
        </td>
      </tr>
    </tbody>
  </table>
  </div>	

  <div class="rfm">
  <table>
    <tbody>
      <tr>
        <th>
          <label>限购数量:</label>
        </th>
        <td class="tipcol">
             <c:out value="${viewGoods.goodsLimitNumber}"/>
        </td>
      </tr>
    </tbody>
  </table>
  </div>

  <div class="rfm">
  <table>
    <tbody>
      <tr>
        <th>
          <label for="password3_LKy66">兑换数量:</label>
        </th>
        <td>
          <input type="text" name="exchangeCount" size="6" class="px p_fre">
        </td>
      </tr>
    </tbody>
  </table>
  </div>

  

</div>
</form>
	  <div class="rfm mbw bw0">
    <table width="100%">
      <tbody>
        <tr>
          <th>&nbsp;</th>
        <td>
          <button class="pn pnc" type="submit" onclick="createExchageRecord()"><strong>立即兑换</strong></button>
        </td>
        <td>
        </td>
        </tr>
      </tbody>
    </table>
  </div>
</div>
</div>
</tbody>
</table>
</div>

<!-- 兑换的弹窗 -->
</c:if>






  




<a href="http://www.tsjtjyw.com/Index" rel="nofollow" class="comiis_dlogo" target="_blank"><img src="../images/logo.png" height="80"></a>

<p><a href="http://www.ts-edu.gov.cn/" target="_blank">唐山市教育局</a><span class="pipe">|</span><a href="http://tangshan05366.11467.com/" target="_blank">唐山市妇联</a><span class="pipe">|</span><a href="http://ts.wenming.cn/" target="_blank">唐山市文明办</a><span class="pipe">|</span><a href="http://www.tsjtjyw.com/Index" target="_blank">网站地图</a><span class="pipe">|</span><a href="http://www.tsjtjyw.com/Index" target="_blank">未成年人家长监护</a><span class="pipe">|</span>			

</p>

  <p> 京ICP备16017061号-1| </p>

<p>唐山市睿智文化交流有限公司 Copyright © 2001-2016 www.tsjtjyw.com All rights reserved.</p>
</div>

</div>

</div>


<div class="fixedul" style="left: 1284.5px;">
<!-- 
<a href="javascript:void(0);" target="_blank"><div class="Rboxli bli1"></div></a>
<div class="Rboxli bli2"></div>
<a href="javascript:void(0);" target="_blank"><div class="Rboxli bli3"></div></a>
<a href="javascript:void(0);" target="_blank"><div class="Rboxli bli4"></div></a>

 -->
<div class="Rboxli bli5" style="display: none;"></div>
<div class="wximg"></div>
</div>

<div id="scrolltop">


<span hidefocus="true"><a title="返回顶部" class="scrolltopa" href="javascript:;"><b>返回顶部</b></a></span>
</div>
<!--底部结束-->