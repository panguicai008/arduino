<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
					url : contextPath + "/user/userListAjax.do",
					singleSelect:true,
					columns : [ [ {
						field : 'username',
						title : '用户名',
						width : 180
					}, {
						field : 'name',
						title : '姓名',
						width : 100
					}, {
						field : 'phone',
						title : '手机',
						width : 100
					},{
						field : 'address',
						title : '地址',
						width : 100
					} ] ],
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
							window.location=contextPath+'/user/addPage.do';
						}
					}, '-', {
						iconCls : 'icon-edit',
						text:'修改',
						handler : function() {
							var obj=$('#dg').datagrid('getSelected');
							if(obj){
								window.location=contextPath+'/user/updatePage.do?username='+obj.username;
							}else{
								alert('请选中一行');
							}
						}
					},'-',{
						iconCls : 'icon-remove',
						text:'删除',
						handler : function() {
							var obj=$('#dg').datagrid('getSelected');
							var url=contextPath+'/user/remove.do';
							$.post(url,{username:obj.username},function(data){
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