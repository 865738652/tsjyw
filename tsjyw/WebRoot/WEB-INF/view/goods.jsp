<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf8"%>
<script type="text/javascript">
	function fillGoods(goods) {
		$('#goodsId').val(goods.goodsId);
		$('#businessId').val(goods.businessId);
		$('#goodsName').val(goods.goodsName);
		$('#goodsPrice').val(goods.goodsPrice);
		$('#goodsCount').val(goods.goodsCount);
		$('#goodsLimitNumber').val(goods.goodsLimitNumber);
		$('#exchangeStartTime').val(getSmpFormatDateByLong(goods.exchangeStartTime,false));
		$('#exchangeStartTime').datepicker('setDate', new Date(goods.exchangeStartTime)); 
		$('#exchangeStopTime').val(getSmpFormatDateByLong(goods.exchangeStopTime,false));
		$('#exchangeStopTime').datepicker('setDate', new Date(goods.exchangeStopTime));   
		$('#goodsStateId').val(goods.goodsStateId);
		$('#goodsSerial').val(goods.goodsSerial);
	}
	
	function onGoodsShow() {
		$('#exchangeStartTime').datepicker({format: 'yyyy-mm-dd', language: 'zh-CN'}); 
		$('#exchangeStopTime').datepicker({format: 'yyyy-mm-dd', language: 'zh-CN'}); 
	}
	
	function onGoodsSubmit() {
		var s = $('#exchangeStartTime').val();
		var a = $('#exchangeStopTime').val();
		var j = new Date((a).replace(new RegExp("-","gm"),"/")).getTime();
		var l = new Date((s).replace(new RegExp("-","gm"),"/")).getTime();
		$('#exchangeStartTime').val(l);
		$('#exchangeStopTime').val(j);
	}
</script>
	<table align="center">
		<tr>
			<td width="20%"><label class="control-label" for="goodsId">商品代码</label></td>
			<td width="30%"><input   type="text" class="form-control" id="goodsId" name="goodsId"></td>
			<td><label class="control-label" for="businessId">商家</label></td>
			<td><select id="businessId" name="businessId" class="form-control" style="width:180px"></select></td>
		</tr>
		<tr>
			<td><label class="control-label" for="goodsName">商品名</label></td>
			<td><input type="text" class="form-control" id="goodsName" name="goodsName"></td>
			<td><label class="control-label" for="goodsPrice">商品单价</label></td>
			<td><input type="text" class="form-control" id="goodsPrice" name="goodsPrice"></td>
		</tr>
		<tr>
			<td><label class="control-label" for="goodsCount">库存数量</label></td>
			<td><input type="text" class="form-control" id="goodsCount" name="goodsCount"></td>
			<td><label class="control-label" for="goodsLimitNumber">限购数量</label></td>
			<td><input type="text" class="form-control" id="goodsLimitNumber" name="goodsLimitNumber"></td>
		</tr>
		<tr>
			<td><label class="control-label" for="exchangeStartTime">起始时间</label></td>
			<td><input type="text" class="form-control" id="exchangeStartTime" name="exchangeStartTime"></td>
			<td><label class="control-label" for="exchangeStopTime">截止时间</label></td>
			<td><input type="text" class="form-control" id="exchangeStopTime" name="exchangeStopTime"></td>
		</tr>
		<tr>
		    <td><label class="control-label" for="goodsStateId">商品状态</label></td>
			<td><select id="goodsStateId" name="goodsStateId" class="form-control" style="width:180px"></select></td>
			<td><label class="control-label" for="goodsSerial">商品序号</label></td>
			<td><input type="text" class="form-control" id="goodsSerial" name="goodsSerial"></td>
		</tr>
	</table>                          
                     

