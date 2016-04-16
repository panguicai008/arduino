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
<select id="device"></select>
<input type="button" name="choose" value="点击我" onclick="loadDevice();"/>

<div id="panel"></div>
	<script>
		function loadDevice() {
			var url = contextPath + '/device/findAll.do';
			var deviceSelect=document.getElementById("device");
			$.get(url, null, function(data) {
				if(data.devices){
					for(var v=0;v<data.devices;++v){
						var d=data.devices[v];
						deviceSelect.options.add(new Option(d.deviceName,d.deviceName));
						$('#panel').append(d.deviceName);
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