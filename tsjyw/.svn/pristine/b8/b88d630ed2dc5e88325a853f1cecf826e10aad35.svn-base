<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf8"%>
<script type="text/javascript">
	var select_url = "../UserManage/getAllUser";
	var select_param = {};
	var select_id_ctrl = undefined;
	var select_name_ctrl = undefined;
	var select_callback = undefined;
	var select_data = null;
	
	function selectUserParams(params) {  //配置参数
        var temp = $.extend(params, select_param);
        <c:if test="${not empty _csrf}">
			temp.${_csrf.parameterName} = "${_csrf.token}";
		</c:if>
		extendTalbeParams(temp, "selectUserForm");
       	return temp;
   	}
    	
   	function selectUser(id_ctrl, name_ctrl, url, param, callback) {
   		if (url != undefined)
   			select_url = url;
   		if (param != undefined && param != null)
   			select_param = param;
   		select_id_ctrl = id_ctrl;
   		select_name_ctrl = name_ctrl;
   		select_callback = callback;
   			
   		$('#selectUserDialog').modal('show');

   		$("#TableSelectUser").bootstrapTable({
	    	url: select_url, 
	    	method:"post",
	    	contentType: "application/x-www-form-urlencoded;charset=utf-8",
	    	queryParams: selectUserParams,
	    	queryParamsType:"undefined",//参数格式,发送标准的restful类型的参数请求
	    	striped: true,     //使表格带有条纹  
 			pagination: true, //在表格底部显示分页工具栏 
	    	dataType: "json",
		    pagination: true,
		    search: false, //是否显示右上角的搜索框  
		    sidePagination: "server", 
		    idField: "userId",  //标识哪个字段为id主键  
		    pageSize: 10,  //每一页 的 数量
			pageNumber:1,     //当前页码
			showRefresh: false,  //显示刷新按钮  
			singleSelect: true,//复选框只能选择一条记录  
			clickToSelect: true,//点击行即可选中单选/复选框  
			columns: [
		        {
		        	field: 'userName', 
                    title: '姓名',		 
                    width: 150,		 
                    align: 'center',		 
                    valign: 'middle',		 
                    sortable: true
		        },
		        {
		        	field: 'userNickname', 
                    title: '昵称',		 
                    width: 200,		 
                    align: 'center',		 
                    valign: 'middle',		 
                    sortable: true
		        },
		        {
		        	field: 'userAccount', 
                    title: '帐号',		 
                    width: 200,		 
                    align: 'center',		 
                    valign: 'middle'
		        },
		        {
		        	field: 'userGender', 
                    title: '性别',		 
                    width: 100,	 
                    align: 'center',		 
                    valign: 'middle',
                    formatter:function(value,row,index){
                    	return value == 0 ? "男":"女";
                    }
		        },
		        {
                    title: '操作',
                    field: 'userId',
                    formatter:function(value,row,index){
                    	var html = '<a class="btn btn-info"  href="javascript:void(0);" onclick=\"onSelectUser('+index+')\">';
 						html += '<i class="glyphicon glyphicon-zoom-in icon-white"></i>选择</a> ';	
 						return html;
                  	}
 				}				        
			],
			onLoadSuccess: function (data)	 {
				select_data = data;
			}
	    });
	    $("#TableSelectUser").bootstrapTable('refresh',{url:select_url});
   	}
   	
   	function onSelectUser(index) {
   		if (select_data == null || select_data.rows == null) {
   			alert("加载数据中，请稍后");
   			return;
   		}
   		
   		$('#selectUserDialog').modal('hide');
   		
   		var row = select_data.rows[index];
   		if (select_id_ctrl != undefined && select_id_ctrl != null) 
   			$("#" + select_id_ctrl).val(row.userId);
   		if (select_name_ctrl != undefined && select_name_ctrl != null) 
   			$("#" + select_name_ctrl).text(row.userName);
   		if (select_callback != undefined)
   			select_callback(row);
   	}

	function searchSelectUser() {
		$("#TableSelectUser").bootstrapTable('refresh');
	}

	function clearSelectUser() {
		$('#selectUserForm')[0].reset();
		$("#TableSelectUser").bootstrapTable('refresh');
	}
	
</script>
<div class="modal fade" id="selectUserDialog" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
   <div class="modal-dialog"  style="width:800px">
       <div class="modal-content">
           <div class="modal-header">
               <button type="button" class="close" data-dismiss="modal">×</button>
               <h3><span id='title'>选择用户</span></h3>
           </div>
           <div class="modal-body">
                <form class="form-inline" role="form" id='selectUserForm'>
                <label class="control-label">姓名</label>
				<input type="text" class="form-control" name="[Like]userName" id="[Like]userName">
				<label class="control-label">昵称</label>
				<input type="text" class="form-control" name="[Like]userNickName" id="[Like]userNickName">
				<a class="btn btn-success" onclick="searchSelectUser()" ><i class="glyphicon glyphicon-search icon-white"></i>查询</a> 
				<a class="btn btn-danger" onclick="clearSelectUser()" ><i class="glyphicon glyphicon-remove icon-white"></i>清除</a>
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/> 
				<p/>
               	<div class="form-group has-success has-feedback" id="selectuserPart">
					<table id="TableSelectUser" class="table table-striped table-bordered bootstrap-datatable responsive"></table>
				</div> 
               </form>                                                                
           </div>           
    	</div>
    </div>
</div> 
                      
