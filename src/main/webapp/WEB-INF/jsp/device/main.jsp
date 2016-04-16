<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/common/meta.jsp"%>
<%@include file="/common/easyui.jsp"%>
</head>
<body>
<table id="dg"></table>
	<script type="text/javascript">
	function loadTable() {
		$('#dg').datagrid(
				{
					url : contextPath + "/device/list.do",
					singleSelect:true,
					columns : [ [ {
						field : 'deviceId',
						title : 'ID',
						width : 100
					}, {
						field : 'deviceName',
						title : '设备名称',
						width : 100
					}, {
						field : 'ip',
						title : 'IP',
						width : 100
					},{
						field : 'port',
						title : '端口号',
						width : 100
					},{
						field : 'description',
						title : '描述',
						width : 200
					} ,{
						field : 'state',
						title : '状态',
						width : 100
					} ,{
						field : 'room',
						title : '中心',
						width : 100,
						formatter: function(value,row,index){
							if (row.room){
								return row.room.roomId;
							} else {
								return value;
							}
						}
						/* formatter:function(value,row,index){
							return value.roomId
						} */
					}] ],
					queryParams : {
						pageSize : function() {
							return $('#dg').datagrid('getPager').pagination(
									'options').pageSize;
						},
						pageNumber : function() {
							return $('#dg').datagrid('getPager').pagination(
									'options').pageNumber-1;
						}
					},
					pagination : 'true',
					toolbar : [ {
						iconCls : 'icon-add',
						text:'添加',
						handler : function() {
							window.location=contextPath+'/device/addPage.do';
						}
					}, '-', {
						iconCls : 'icon-edit',
						text:'修改',
						handler : function() {
							var obj=$('#dg').datagrid('getSelected');
							if(obj){
								window.location=contextPath+'/device/updatePage.do?deviceId='+obj.deviceId;
							}else{
								alert('请选中一行');
							}
						}
					},'-',{
						iconCls : 'icon-remove',
						text:'删除',
						handler : function() {
							var obj=$('#dg').datagrid('getSelected');
							var url=contextPath+'/device/remove.do';
							$.post(url,{deviceId:obj.deviceId},function(data){
								if(data.success){
									$('#dg').datagrid('reload');
									alert('删除成功');
								}
							},'json');
						}
					} ]
				});
	}

	function loadPagination() {
		$('#dg').datagrid('getPager').pagination({
			pageSize : 10,// 每页显示的记录条数，默认为10
			pageList : [ 5, 10, 15 ],// 可以设置每页记录条数的列表
			beforePageText : '第',// 页数文本框前显示的汉字
			afterPageText : '页    共 {pages} 页',
			displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录',
		})
	}
	$(function() {
		loadTable();
		loadPagination();
	})
	</script>
</body>
</html>