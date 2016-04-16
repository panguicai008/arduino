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
						<label for="id">ID</label> <input type="text" name="id"
							class="form-control" id="id" value="${data.id }" readonly="readonly">
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-5">
					<div class="form-group">
						<label for="deviceId">设备编号</label> <input type="text"
							class="form-control" name="deviceId" placeholder="设备编号" value="${data.device.deviceId}"/>
					</div>
					<div class="form-group">
						<label for="lm35Outlet">出风口</label> <input type="text"
							class="form-control" name="lm35Outlet" placeholder="出风口" value="${data.lm35Outlet}"/>
					</div>
					<div class="form-group">
						<label for="lm35Intake">进风口</label> <input type="text"
							class="form-control" name="description" placeholder="进风口" value="${data.lm35Intake}"/>
					</div>
					<div class="form-group">
						<label for="pir">热释红外</label> <input type="text"
							class="form-control" name="pir" placeholder="热释红外" value="${data.pir}"/>
					</div>
					<div class="form-group">
						<label for="humidity">湿度</label> <input type="text"
							class="form-control" name="humidity" placeholder="湿度" value="${data.humidity}"/>
					</div>
					<div class="form-group">
						<label for="light">光照</label> <input type="text"
							class="form-control" name="light" placeholder="光照" value="${data.light}"/>
					</div>
					<div class="form-group"> 
						<label for="time">时间</label>
						<input type="text" id="time" name="birthday" class="form-control" placeholder="时间" value="${data.time }"/>
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
					url : '${contextPath}/data/update.do',
					onSubmit : function() {
						if ($('input[name="id"]').val().trim() == "") {
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
				window.location = contextPath + '/data/main.do';
			});
		}
		/* function datatime(){
			$("time").datetimebox({
				value: '3/4/2010 2:3',
			    required: true,
			    showSeconds: false
			});
		} */
		$(function() {
			bindSave();
			bindBack();
			/* window.setInterval(datatime,1000); */
		})
	</script>
</body>
</html>