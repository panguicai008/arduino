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
	<div class="container">
		<div class="row">
			<form>
				<div class="col-md-5">
					<div class="form-group">
						<label for="roomId">房间名称</label> <input type="text"
							class="form-control" id="roomId" placeholder="房间名称">
					</div>
					<div class="form-group">
						<label for="description">描述</label> <input type="text"
							class="form-control" id="description" placeholder="描述">
					</div>
					<div class="form-group">
						<label for="roomType">房间类型</label> <input type="text"
							class="form-control" id="roomType" placeholder="房间类型">
					</div>
				</div>
				
				<div class="col-md-12">
					<button type="button" class="btn btn-default" onclick="add()">确定</button>
					<button type="button" class="btn btn-default" onclick="back()">取消</button>
				</div>
			</form>
		</div>
	</div>
	<script>
		function back() {
			window.location=contextPath+'/room/main.do';
		}
		function add() {
			var params = {
				roomId : $('#roomId').val(),
				description : $('#description').val(),
				roomType : $('#roomType').val()
			}
			var url = contextPath + '/room/add.do';
			$.post(url, params, function(data) {
				if (data.success) {
					alert('添加成功');
				} else {
					alert('添加失败');
				}
			}, 'json');
		}
	</script>
</body>
</html>