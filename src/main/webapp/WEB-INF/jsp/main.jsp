<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/common/meta.jsp"%>
<%@include file="/common/easyui.jsp"%>
<title>办公室节能控制后台管理系统</title>
</head>
<body class="easyui-layout">
	<div data-options="region:'north',split:true" style="height: 100px;">
		<div style="margin-top: 50px; float: left; margin-left: 30px;">
			<span>欢迎您：${LOGIN_SESSION_DATA.name}</span>
		</div>
		<div style="float: right; margin-top: 30px;">
			<ul style="list-style: none; float: right;">
				<li style="float: right;"><a
					href="${contextPath }/login/logOut.do" class="btn btn-default">注销</a></li>
				<li style="margin: 10px; float: right"></li>
				<li style="float: right;"><a href="javascript:void(0)"
					id="updatePassword" class="btn btn-default">修改密码</a></li>
			</ul>

		</div>
	</div>
	<div data-options="region:'west',title:'后台管理',split:true"
		style="width: 180px;">
		<div id="aa" class="easyui-accordion"
			style="width: 170px; height: 420px;">
			<div title="用户管理" data-options="iconCls:'icon-man'"
				style="overflow: auto; padding: 10px;">
				<p>
					<a href="javascript:void(0)" onclick="setFrm(this)"
						data-url="${contextPath }/admin/info.do">账户信息</a>
				</p>
				<p>
					<a href="javascript:void(0)" onclick="setFrm(this)"
						data-url="${contextPath }/user/main.do">用户列表</a>
				</p>
			</div>
			<div title="区域管理"
				data-options="iconCls:'icon-large-clipart',selected:true"
				style="padding: 10px;">
			<p>
				<a href="javascript:void(0)" onclick="setFrm(this)"
						data-url="${contextPath }/room/main.do">房间列表</a>
			</p>
			</div>
			<div title="设备管理"
				data-options="iconCls:'icon-large-shapes',selected:true"
				style="padding: 10px;">
			<p>
				<a href="javascript:void(0)" onclick="setFrm(this)"
						data-url="${contextPath }/device/main.do">设备列表</a>
			</p>
			</div>
			<div title="数据操作"
				data-options="iconCls:'icon-large-chart',selected:true"
				style="padding: 10px;">
			<p>
				<a href="javascript:void(0)" onclick="setFrm(this)"
						data-url="${contextPath }/data/main.do">数据列表</a>
			</p>
			</div>
		</div>
	</div>
	<div data-options="region:'center'"
		style="padding: 5px; background: #eee;">
		<iframe id="adminFrm" style="border: 0px;" width="100%" height="600px"></iframe>
	</div>
	<div id="win" style="padding: 20px;">
		<form id="ff" method="post">
			<div class="form-group">
				<label for="oldPassword">原密码：</label> <input name="oldPassword"
					type="password" class="form-control" id="oldPassword"
					placeholder="原密码">
			</div>
			<div class="form-group">
				<label for="newPassword">新密码：</label> <input type="password"
					class="form-control" id="newPassword" name="newPassword"
					placeholder="新密码">
			</div>
			<div class="form-group">
				<label for="newPassword2">确认密码：</label> <input type="password"
					class="form-control" id="newPassword2" name="newPassword2"
					placeholder="确认密码">
			</div>

			<div id="buttonPanel">
				<input type="button" style="margin-left: 100px;" value="确认" class="btn btn-primary"
					onclick="updatePassword()"> <input type="button" value="取消"
					class="btn btn-default" onclick="cancel()">
			</div>
		</form>
	</div>
	<script type="text/javascript">
		function updatePassword() {
			var oldPassword = $('input[name=oldPassword]').val().trim();
			if (!oldPassword) {
				alert('原密码不能为空');
				return false;
			}
			var newPassword = $('input[name=newPassword]').val().trim();
			if (!newPassword) {
				alert('新密码不能为空');
				return false;
			}
			var newPassword2 = $('input[name=newPassword2]').val().trim();
			if (!newPassword2) {
				alert('确认密码不能为空');
				return false;
			}
			if (newPassword !== newPassword2) {
				alert('两次密码输入不一致');
				return false;
			}
			var url = contextPath + "/admin/updatePassword.do";
			var params = {
				oldPassword : oldPassword,
				newPassword : newPassword,
				newPassword2 : newPassword2
			};
			$.post(url, params, function(data) {
				if (data.result) {
					alert("修改成功");
				} else {
					alert(data.msg);
				}
			}, 'json');
		}

		function cancel() {
			$('#win').window('close');
		}
		function setFrm(t) {
			var url = $(t).data('url');
			$('#adminFrm').attr('src', url);
		}
		function loadUpdatePasswordWin() {
			$('#win').window({
				width : 400,
				height : 400,
				modal : true,
				closed : true,
				collapsible : false,
				minimizable : false,
				maximizable : false,
				title : "修改密码"
			});
			$('#updatePassword').click(function() {
				$('#win').window('open');
			});
		}
		$(function() {
			loadUpdatePasswordWin();
		})
	</script>
</body>
</html>