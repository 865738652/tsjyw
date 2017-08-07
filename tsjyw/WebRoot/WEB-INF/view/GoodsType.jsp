<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf8"%>
<script type="text/javascript">
	function fillGoodsType(checkedArray) {
		$.ajax({
			url:"../GoodsManage/getGoodsType",			
			dataType:"json",
			success:function(data) {  
		        if (data.code != "succ") {
					alert("获取商品分类失败: " + data.data);
					window.history.go(-1);
					return;
				}
				var html = "";
				for (var i = 0; i < data.total; i++) {
					html += "<li style='width: 100px; float: left; display: block;'>";
					html += "<input type='checkbox' class='form-control' name='goodsType' id='goodsType" 
						+ i + "' value='" + data.rows[i].goodsTypeId + "'";
					if (checkedArray != undefined && checkedArray != null) {
						for (var j = 0; j < checkedArray.length; j++)
							if (checkedArray[j].goodsTypeId == data.rows[i].goodsTypeId)
								html += " checked='checked' ";
					}
					html += ">" + data.rows[i].goodsTypeName + "</input>";
					html += "</li>";
				}
				
				$("#goodsType").html(html);	        		      
		    },
		    error: function(XMLHttpRequest, textStatus, errorThrown) {
				alert("网络错误: " + XMLHttpRequest.status + ", " + textStatus);
		    }			    
		});
	}
	
	function goodsTypeOnSubmit(param) {
		var id_array=new Array();  
		$('input[name="goodsType"]:checked').each(function(){  
		    id_array.push($(this).attr('value'));//向数组中添加元素  
		});  
		if (param == null || param.length == 0)
			param = "a=1";
			
		for (var i = 0; i < id_array.length; i++) {
   			param += "&goodsType[" + i + "]=" + id_array[i];
   		}
   		
   		return param;
	}
	
</script>
<div class="form-group has-success has-feedback" id="goodsTypePart">
	<ul id="goodsType" style="width:600px">
	</ul>
</div>          
