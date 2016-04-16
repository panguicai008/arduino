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
				<div class="col-md-5">
					<div class="form-group">
						<label for="roomId">房间名称</label> <input type="text"
							class="form-control" name="roomId" placeholder="房间名称"
							value="${room.roomId }">
					</div>
					<div class="form-group">
						<label for="description">房间描述</label> <input type="text"
							class="form-control" name="description" placeholder="房间描述"
							value="${room.description }">
					</div>
					<div class="form-group">
						<label for="roomType">房间类型</label> <input type="text"
							class="form-control" name="roomType" placeholder="房间类型"
							value="${room.roomType }">
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
					url : '${contextPath}/room/update.do',
					onSubmit : function() {
						if ($('input[name="roomId"]').val().trim() == "") {
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
				window.location = contextPath + '/room/main.do';
			});
		}
		$(function() {
			bindSave();
			bindBack();
		})
	</script>
</body>
</html>