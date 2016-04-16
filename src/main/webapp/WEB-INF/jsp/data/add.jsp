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
						<label for="deviceId">设备编号</label> <input type="text"
							class="form-control" id="deviceId" placeholder="设备编号">
					</div>
					<div class="form-group">
						<label for="lm35Outlet">出风口</label> <input type="text"
							class="form-control" id="lm35Outlet" placeholder="出风口">
					</div>
					<div class="form-group">
						<label for="lm35Intake">进风口</label> <input type="text"
							class="form-control" id="lm35Intake" placeholder="进风口">
					</div>
					<div class="form-group">
						<label for="pir">热释红外</label> <input type="text"
							class="form-control" id="pir" placeholder="热释红外">
					</div>
					<div class="form-group">
						<label for="humidity">湿度</label> <input type="text"
							class="form-control" id="humidity" placeholder="湿度">
					</div>
					<div class="form-group">
						<label for="light">光照</label> <input type="text"
							class="form-control" id="light" placeholder="光照">
					</div>
					<div class="form-group">
						<label for="time">时间</label><!--<input class="form-control" id="dt" type="text" name="birthday">--> 
						<input class="easyui-datetimebox" type="text"name="birthday" id="dt"
        					data-options="required:true,showSeconds:true" placeholder="时间" style="width:200px;height:35px">
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
		function date(){
			$('#dt').datetimebox({
		    	value: '03/04/2016 12:13:15',
		    	required: true,
		    	showSeconds: true,
		    	style:'height：40px; width:200px'
			});
		}
		
		
		function back() {
			window.location=contextPath+'/data/main.do';
		}
		function add() {
			
			var params = {
				deviceId : $('#deviceId').val(),
				lm35Outlet : $('#lm35Outlet').val(),
				lm35Intake : $('#lm35Intake').val(),
				pir:$('#pir').val(),
				humidity:$('#humidity').val(),
				light:$('#light').val(),
				time:$('#dt').datetimebox('getValue')
				
			}
			var url = contextPath + '/data/add.do';
			$.post(url, params, function(data) {
				if (data.success) {
					alert('添加成功');
				} else {
					alert('添加失败');
				}
			}, 'json');
			alert(time);
		}
		$(function(){
			date();
			
		})
	</script>
</body>
</html>