<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登陆界面</title>
<%@include file="/common/meta.jsp"%>
</head>
<body>
	<div class="container">
		<div style="margin-top: 100px;"></div>
		<div class="row">
			<div class="col-xs-4"></div>
			<div class="col-xs-4">
				<form method="post" action="${contextPath }/login/submit.do">
					<div class="form-group">
						<label for="username">邮箱：</label> <input type="email"
							class="form-control" required="required" id="username" name="username"
							placeholder="Email">
					</div>
					<div class="form-group">
						<label for="password">密码：</label> <input type="password"
							class="form-control" required="required" id="password" name="password"
							placeholder="Password">
					</div>
					<div>${LOGIN_ERROR_MSG }</div>
					<div>
						<button type="submit" class="btn btn-default">登陆</button>
						<button id="register" type="button" class="btn btn-default">注册</button>
					</div>
				</form>
			</div>
			<div class="col-xs-4"></div>
		</div>
	</div>
	<script>
		function bindRegister() {
			function handler() {
				window.location = contextPath + '/register/page.do';
			}
			$('#register').bind('click', handler);
		}
		$(function() {
			bindRegister();
		});
	</script>
</body>
</html>