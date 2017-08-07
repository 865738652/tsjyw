<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf8"%>
<script type="text/javascript">
	function fillGoodsDetail(goods) {
		$('#goodsIdDetail').text(goods.goodsId);
		$('#businessIdDetail').text(goods.businessName);
		$('#goodsNameDetail').text(goods.goodsName);
		$('#goodsPriceDetail').text(goods.goodsPrice);
		$('#goodsCountDetail').text(goods.goodsCount);
		$('#goodsLimitNumberDetail').text(goods.goodsLimitNumber);
		$('#exchangeStartTimeDetail').text(getSmpFormatDateByLong(goods.exchangeStartTime,false));
		$('#exchangeStopTimeDetail').text(getSmpFormatDateByLong(goods.exchangeStopTime,false));
		$('#goodsStateIdDetail').text(goods.goodsStateName);
		$('#goodsSerialDetail').text(goods.goodsSerial);
	}
</script>
<div class="form-group has-success has-feedback" id="showGoods">
	<table width="90%">
		<tr>
			<td width="20%"><label  class="control-label">商品代码</label></td>
			<td width="30%"><label  id="goodsIdDetail" class="control-label">商品代码</label></td>
			<td width="20%"><label  class="control-label">商家</label></td>
			<td width="30%"><label  id="businessIdDetail" class="control-label">商家</label></td>
		</tr>
		<tr>
			<td><label  class="control-label">商品名称</label></td>
			<td><label  id="goodsNameDetail" class="control-label">商品名称</label></td>
			<td><label  class="control-label">商品单价</label></td>
			<td><label  id="goodsPriceDetail" class="control-label">商品代价</label></td>
		</tr>
		<tr>
			<td><label class="control-label">库存数量</label></td>
			<td><label id="goodsCountDetail" class="control-label">库存数量</label></td>
			<td><label  class="control-label">限购数量</label></td>
			<td><label  id="goodsLimitNumberDetail" class="control-label">限购数量</label></td>
		</tr>
		<tr>
			<td><label  class="control-label">商品状态</label></td>
			<td><label  id="goodsStateIdDetail" class="control-label">商品状态</label></td>
			<td><label  class="control-label">商品序号</label></td>
			<td><label  id="goodsSerialDetail" class="control-label">商品序号</label></td>
		</tr>
		<tr>
			<td><label  class="control-label">起始时间</label></td>
			<td><label  id="exchangeStartTimeDetail" class="control-label">起始时间</label></td>
			<td><label  class="control-label">截止时间</label></td>
			<td><label  id="exchangeStopTimeDetail" class="control-label">截止时间</label></td>
		</tr>
	</table>
</div>                       
 