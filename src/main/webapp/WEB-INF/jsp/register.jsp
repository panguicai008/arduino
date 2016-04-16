<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注册界面</title>
<%@include file="/common/meta.jsp"%>
</head>
<body>
	<div class="container">
		<div style="margin-top: 100px;"></div>
		<div class="row">
			<div class="col-xs-4"></div>
			<div class="col-xs-4">
				<form method="post" id="frm"
					action="${contextPath }/register/add.do">
					<table class="table">
						<tr>
							<td>email：</td>
							<td><input type="email" id="username" name="username"
								placeholder="Email"></td>
							<td><button type="button" id="btnSendCode"
									onclick="sendCode()" class="btn btn-default">发送验证码</button></td>
						</tr>
						<tr>
							<td>验证码：</td>
							<td><input required="required" name="activateCode"
								placeholder="验证码"></td>
							<td></td>

						</tr>
						<tr>
							<td>密码：</td>
							<td><input type="password" id="password" name="password"
								required="required" placeholder="密码"
								onmouseleave="passwordConfirm()"></td>
							<td></td>
						</tr>
						<tr>
							<td>确认密码：</td>
							<td><input type="password" id="password2" name="password2"
								required="required" placeholder="确认密码"
								onmouseleave="passwordConfirm()"></td>
							<td><span id="passwordSpan"></span></td>
						</tr>
						<tr>
							<td>名字：</td>
							<td><input type="text" id="name" name="name"
								required="required" placeholder="名字"></td>
							<td></td>
						</tr>
						<tr>
							<td>手机：</td>
							<td><input required="required" name="phone" type="tel"
								placeholder="手机"></td>
							<td></td>
						</tr>
						<tr>
							<td>地址：</td>
							<td><input required="required" name="address"
								placeholder="地址"></td>
							<td></td>
						</tr>
						
						<tr>
							<td colspan="3"><button type="submit" onclick="submit()">提交</button></td>
						</tr>
					</table>
				</form>
			</div>
			<div class="col-xs-4"></div>
		</div>
	</div>
	<script type="text/javascript">
		function passwordConfirm() {
			var password = $('#password').val();
			var password2 = $('#password2').val();
			if (password != password2) {
				$('#passwordSpan').text('两次输入密码不一致');
			} else {
				$('#passwordSpan').text('');
			}
		}
		function sendCode() {
			var email = $('#username').val();
			$.get(contextPath + '/register/sendCode.do', {
				"email" : email
			}, function(data) {
				if (data.result == 'success') {
					var $btn = $('#btnSendCode');
					$btn.attr('disabled', 'disabled');
					var time = 60;
					var timer = window.setInterval(function() {
						if (time > 1) {
							$btn.text(--time + 's之后点击重新发送');
						} else {
							window.clearInterval(timer);
							$btn.removeAttr('disabled');
							$btn.text('发送验证码');
						}
					}, 1000);
				} else {
					alert(data.msg);
				}
			}, 'json')
		}
	</script>
</body>
</html>