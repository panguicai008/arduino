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
						<label for="deviceName">设备名称</label> <input type="text"
							class="form-control" id="deviceName" placeholder="设备名称">
					</div>
					<div class="form-group">
						<label for="ip">IP</label> <input type="text"
							class="form-control" id="ip" placeholder="设备IP">
					</div>
					<div class="form-group">
						<label for="port">端口号</label> <input type="text"
							class="form-control" id="port" placeholder="设备端口号">
					</div>
					<div class="form-group">
						<label for="description">描述</label> <input type="text"
							class="form-control" id="description" placeholder="描述">
					</div>
					<div class="form-group">
						<label for="state">设备状态</label> <input type="text"
							class="form-control" id="state" placeholder="设备状态">
					</div>
					<div class="form-group">
						<label for="state">设备所在地</label> <input type="text"
							class="form-control" id="roomId" placeholder="设备所在地">
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
			window.location=contextPath+'/device/main.do';
		}
		function add() {
			var params = {
				deviceName : $('#deviceName').val(),
				ip:$('#ip').val(),
				port:$('#port').val(),
				description : $('#description').val(),
				state : $('#state').val(),
				roomId:$('#roomId').val()
				
			}
			var url = contextPath + '/device/addDevice.do';
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