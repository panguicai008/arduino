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
		<form id="ff">
			<div class="row">
				<div class=col-md-5>
					<div class="form-group">
						<label for="deviceId">ID</label> <input name="deviceId"
							class="form-control" value="${device.deviceId }" readonly="readonly">
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-5">
					<div class="form-group">
						<label for="deviceName">设备名称</label> <input type="text"
							class="form-control" name="deviceName" placeholder="设备名称"
							value="${device.deviceName }">
					</div>
					<div class="form-group">
						<label for="ip">设备IP</label> <input type="text"
							class="form-control" name="ip" placeholder="设备IP"
							value="${device.ip }">
					</div>
					<div class="form-group">
						<label for="port">设备端口号</label> <input type="text"
							class="form-control" name="port" placeholder="设备端口号"
							value="${device.port }">
					</div>
					<div class="form-group">
						<label for="description">设备描述</label> <input type="text"
							class="form-control" name="description" placeholder="设备描述"
							value="${device.description }">
					</div>
					
					<div class="form-group">
						<label for="state">设备状态</label> <input type="text"
							class="form-control" name="state" placeholder="设备状态"
							value="${device.state }">
					</div>
					<div class="form-group">
						<label for="roomId">设备所在地</label> <input type="text"
							class="form-control" name="roomId" placeholder="设备所在地"
							value="${device.room.roomId }">
					</div>
				</div>
				

				</div>
				<div class="col-md-12">
					<button type="button" class="btn btn-default" id="btnSave">确定</button>
					<button type="button" class="btn btn-default" id="btnBack">取消</button>
				</div>
			</div>
		</form>
	</div>
	<script type="text/javascript">
		function bindSave() {
			$('#btnSave').bind('click', function() {
				$('#ff').form({
					url : '${contextPath}/device/update.do',
					onSubmit : function() {
						if ($('input[name="deviceId"]').val().trim() == "") {
							return false;
						}
						return true;
					},
					success : function(data) {
						var object = eval('(' + data + ')');
						if (object.result) {
							alert('保存成功');
						} else {
							alert('保存失败');
						}
					}
				});
				$('#ff').submit();
			});
		}
		function bindBack() {
			$('#btnBack').click(function() {
				window.location = contextPath + '/device/main.do';
			});
		}
		$(function() {
			bindSave();
			bindBack();
		})
	</script>
</body>
</html>