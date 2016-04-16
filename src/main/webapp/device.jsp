<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>设备</title>
<%@include file="/common/meta.jsp"%>
</head>
<body>
<div id="panel"></div>
	<script>
		function loadDevice() {
			var url = contextPath + '/device/findAll.do';
			$.get(url, null, function(data) {
				if(data.devices){
					for(var v=0;v<data.devices;++v){
						var d=data.devices[v];
						$('#panel').append(d.ip)
					}
				}
			}, 'json')
		}
		$(function(){
			window.setInterval(loadDevice, 1000);
		});
	</script>
</body>
</html>