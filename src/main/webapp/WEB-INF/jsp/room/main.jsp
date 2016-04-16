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
					url : contextPath + "/room/list.do",
					singleSelect:true,
					columns : [ [ {
						field : 'roomId',
						title : '房间编号',
						width : 100
					}, {
						field : 'description',
						title : '房间描述',
						width : 100
					}, {
						field : 'roomType',
						title : '房间类型',
						width : 100
					}] ],
					queryParams : {
						pageSize : function() {
							return $('#dg').datagrid('getPager').pagination(
									'options').pageSize;
						},
						pageNumber : function() {
							return $('#dg').datagrid('getPager').pagination(
									'options').pageNumber - 1;
						}
					},
					pagination : 'true',
					toolbar : [ {
						iconCls : 'icon-add',
						text:'添加',
						handler : function() {
							window.location=contextPath+'/room/addPage.do';
						}
					}, '-', {
						iconCls : 'icon-edit',
						text:'修改',
						handler : function() {
							var obj=$('#dg').datagrid('getSelected');
							if(obj){
								window.location=contextPath+'/room/updatePage.do?roomId='+obj.roomId;
							}else{
								alert('请选中一行');
							}
						}
					},'-',{
						iconCls : 'icon-remove',
						text:'删除',
						handler : function() {
							var obj=$('#dg').datagrid('getSelected');
							var url=contextPath+'/room/remove.do';
							$.post(url,{roomId:obj.roomId},function(data){
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