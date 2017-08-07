<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf8"%>
<script type="text/javascript">
	var select_url = "../CountyManage/getAllCounty";
	var select_param = {};
	var select_id_ctrl = undefined;
	var select_name_ctrl = undefined;
	var select_callback = undefined;
	var select_data = null;
	
	function selectCountyParams(params) {  //配置参数
        var temp = $.extend(params, select_param);
        <c:if test="${not empty _csrf}">
			temp.${_csrf.parameterName} = "${_csrf.token}";
		</c:if>
       	return temp;
   	}
    	
   	function selectCounty(id_ctrl, name_ctrl, url, param, callback) {
   		if (url != undefined)
   			select_url = url;
   		if (param != undefined && param != null)
   			select_param = param;
   		select_id_ctrl = id_ctrl;
   		select_name_ctrl = name_ctrl;
   		select_callback = callback;
   			
   		$('#selectCountyDialog').modal('show');
   			
   		$("#TableSelectCounty").bootstrapTable({
	    	url: select_url, 
	    	method:"post",
	    	contentType: "application/x-www-form-urlencoded",
	    	queryParams: selectCountyParams,
	    	queryParamsType:"undefined",//参数格式,发送标准的restful类型的参数请求
	    	striped: true,     //使表格带有条纹  
 			pagination: true, //在表格底部显示分页工具栏 
	    	dataType: "json",
		    pagination: true,
		    search: true, //是否显示右上角的搜索框  
		    sidePagination: "server", 
		    idField: "userId",  //标识哪个字段为id主键  
		    pageSize: 10,  //每一页 的 数量
			pageNumber:1,     //当前页码
			showRefresh: true,  //显示刷新按钮  
			singleSelect: true,//复选框只能选择一条记录  
			clickToSelect: true,//点击行即可选中单选/复选框  
			columns: [		        
		        {
		        	field: 'countyName', 
                    title: '区县名称',		 
                    width: 200,		 
                    align: 'center',		 
                    valign: 'middle',		 
                    sortable: true
		        },
		        {
                    title: '操作',
                    width: 200,
                    field: 'countyId',
                    formatter:function(value,row,index){
                    	var html = '<a class="btn btn-info"  href="javascript:void(0);" onclick=\"onSelectCounty('+index+')\">';
 						html += '<i class="glyphicon glyphicon-zoom-in icon-white"></i>选择</a> ';	
 						return html;
                  	}
 				}				        
			],
			onLoadSuccess: function (data) {
				select_data = data;
			}
	    });
   	}
   	
   	function onSelectCounty(index) {
   		if (select_data == null || select_data.rows == null) {
   			alert("加载数据中，请稍后");
   			return;
   		}
   		
   		$('#selectCountyDialog').modal('hide');
   		
   		var row = select_data.rows[index];
   		if (select_id_ctrl != undefined && select_id_ctrl != null) 
   			$("#" + select_id_ctrl).val(row.countyId);
   		if (select_name_ctrl != undefined && select_name_ctrl != null) 
   			$("#" + select_name_ctrl).text(row.countyName);
   		if (select_callback != undefined)
   			select_callback(row);
   	}
	
</script>
<div class="modal fade" id="selectCountyDialog" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
   <div class="modal-dialog"  style="width:450px;text-align:center">
       <div class="modal-content">
           <div class="modal-header">
               <button type="button" class="close" data-dismiss="modal">×</button>
               <h3><span id='title'>选择区县</span></h3>
           </div>
           <div class="modal-body">
                <form class="form-inline" role="form" id='selectCountyForm'>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/> 
               	<div class="form-group has-success has-feedback" id="selectcountyPart">
					<table id="TableSelectCounty" class="table table-striped table-bordered bootstrap-datatable responsive"></table>
				</div> 
               </form>                                                                
           </div>
    	</div>
    </div>
</div> 
                      
