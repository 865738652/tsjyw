<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf8"%>
<script type="text/javascript">
	var select_url = "../SchoolManage/getAllSchool";
	var select_param = {};
	var select_callback = undefined;
	var select_data = null;
	
	function selectSchoolParams(params) {  //配置参数
        var temp = $.extend(params, select_param);
        <c:if test="${not empty _csrf}">
			temp.${_csrf.parameterName} = "${_csrf.token}";
		</c:if>
		extendTalbeParams(temp, "selectSchoolForm");
       	return temp;
   	}
    	
   	function selectSchool(id_ctrl, name_ctrl, url, param, callback) {
   		if (url != undefined && url != null)
   			select_url = url;
   		if (param != undefined && param != null)
   			select_param = param;
   		select_id_ctrl = id_ctrl;
   		select_name_ctrl = name_ctrl;
   		select_callback = callback;
   			
   		$('#selectSchoolDialog').modal('show');
   			
   		$("#TableSelectSchool").bootstrapTable({
	    	url: select_url, 
	    	method:"post",
	    	contentType: "application/x-www-form-urlencoded",
	    	queryParams: selectSchoolParams,
	    	queryParamsType:"undefined",//参数格式,发送标准的restful类型的参数请求
	    	striped: true,     //使表格带有条纹  
 			pagination: true, //在表格底部显示分页工具栏 
	    	dataType: "json",
		    pagination: true,
		    search: false, //是否显示右上角的搜索框  
		    sidePagination: "server", 
		    idField: "schoolId",  //标识哪个字段为id主键  
		    pageSize: 10,  //每一页 的 数量
			pageNumber:1,     //当前页码
			showRefresh: false,  //显示刷新按钮  
			singleSelect: true,//复选框只能选择一条记录  
			clickToSelect: true,//点击行即可选中单选/复选框  
			columns: [
		        {
				 		field:'schoolNumber',
				 		title:'学校代码',
				 		width:'100',
				 		align:'center',
				 		valign:'maddle',
				 		sortable:true,
				 	},
				 	{
				 		field:'schoolName',
				 		title:'学校名称',
				 		width:150,
				 		align:'center',
				 		valigh:'middle'
				 	},
				 	{
				 		field:'schoolContactInformation',
				 		title:'联系方式',
				 		width:150,
				 		align:'center',
				 		valigh:'middle',
				 	},
				 	{		 	
				 		title:'选择',
				 		field:'schoolId',
				 		formatter:function(value,row,index){
				 			var html = '<a class="btn btn-info"  href="javascript:void(0);" onclick=\"onSelectSchool('+index+')\">';
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
   	
   	function onSelectSchool(index) {
   		if (select_data == null || select_data.rows == null) {
   			alert("加载数据中，请稍后");
   			return;
   		}
   		
   		$('#selectSchoolDialog').modal('hide');
   		
   		var row = select_data.rows[index];
   		if (select_id_ctrl != undefined && select_id_ctrl != null) 
   			$("#" + select_id_ctrl).val(row.schoolId);
   		if (select_name_ctrl != undefined && select_name_ctrl != null) 
   			$("#" + select_name_ctrl).text(row.schoolName);
   		if (select_callback != undefined)
   			select_callback(row);
   	}

	function searchSelectSchool() {
		$("#TableSelectSchool").bootstrapTable('refresh');
	}

	function clearSelectSchool() {
		$('#selectSchoolForm')[0].reset();
		$("#TableSelectSchool").bootstrapTable('refresh');
	}

</script>
<div class="modal fade" id="selectSchoolDialog" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
   <div class="modal-dialog"  style="width:800px">
       <div class="modal-content">
           <div class="modal-header">
               <button type="button" class="close" data-dismiss="modal">×</button>
               <h3><span id='title'>选择学校</span></h3>
           </div>
           <div class="modal-body">
				<form class="form-inline" role="form" id='selectSchoolForm'>
				<label class="control-label">学校名称</label>
				<input type="text" class="form-control" name="[Like]schoolName" id="[Like]schoolName">
				<a class="btn btn-success" onclick="searchSelectSchool()" ><i class="glyphicon glyphicon-plus icon-white"></i>查询</a> 
				<a class="btn btn-success" onclick="clearSelectSchool()" ><i class="glyphicon glyphicon-plus icon-white"></i>清除</a>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/> 
               	<div class="form-group has-success has-feedback" id="selectschoolPart">
					<table id="TableSelectSchool" class="table table-striped table-bordered bootstrap-datatable responsive"></table>
				</div> 
               </form>                                                                
           </div>
    	</div>
    </div>
</div> 
                      
