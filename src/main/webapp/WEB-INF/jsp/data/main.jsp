<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/meta.jsp"%>
<%@include file="/common/easyui.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="../js/main.js"></script>

<link rel="stylesheet" href="../css/cloud-admin.css">

	
	<!-- <script src="../js/jquery/jquery-2.0.3.min.js"></script> -->
	<script src="../js/flot/jquery.flot.min.js"></script>
	<script src="../js/flot/jquery.flot.time.min.js"></script>
    <script src="../js/flot/jquery.flot.selection.min.js"></script>
	<script src="../js/flot/jquery.flot.resize.min.js"></script>
    <script src="../js/flot/jquery.flot.pie.min.js"></script>
    <script src="../js/flot/jquery.flot.stack.min.js"></script>
    <script src="../js/flot/jquery.flot.crosshair.min.js"></script>
	<!-- FLOT GROWRAF -->
	<script src="../js/flot-growraf/jquery.flot.growraf.min.js"></script>
	
	<script src="../js/script.js"></script>
	<script src="../js/charts.js"></script>
    <script src="../js/index.js"></script>
</head>
<body>
	<div>
		<iframe width="420" scrolling="no" height="60" frameborder="0" allowTransparency="true" src="http://i.tianqi.com/index.php?c=code&id=12&icon=1&num=5">
		</iframe>
	</div>
	<table id="dg"></table>
	<hr/>
	<p>温度选择：夏季不低于26度，冬季不高于20度</p>
		
		<select id="mode" onchange="toTemperature();"> 
			<option value="-1">--请选择空调模式---</option> 
		</select> 
		<select id="temperature" > 
			<option value="-1">--请选择温度---</option> 
		</select> 

		<select id="windDirection" >
			<option value="-1">--请选择风向--</option>
			<option value="up" >上</option>
			<option value="down" >下</option>
			<option value="left" >左</option>
			<option value="right" >右</option>
		</select>
		<hr/>
		<input id="dd" type="text" class="easyui-datebox" required="required">
		<select id="device" >
			<option value="-1">请选择</option>
		</select>		
		<input id="Button" type="button" value="button" onclick="javascript:loadDevice();" />
		<!-- <input id="device" name="device" value="--请选择deviceName--" class='easyui-combobox'> -->
		<div id="panel"></div>
		<!-- <label for="start">起始时间:</label>
			<input id="start" type="text" name="start"/>&nbsp;&nbsp; -->
		<!-- <label for="end">结束时间:</label>
			<input class="easyui-datetimebox" name="end" id="end"
        data-options="required:true,showSeconds:true" style="width:150px"/> -->
        <!--<div id="container" style="width: 800px; height: 400px; margin: 0 auto"></div> -->
     	
		<div class="row">
			<div class="col-md-12">
				<!-- BOX -->
				<div class="box border blue">
					<div class="box-title">
						<h4><i class="fa fa-bars"></i>温度分析</h4>

					</div>
					<div class="box-body">
						<div id="chart_grow" class="chart"></div>
					</div>
				</div>
				
			</div>
		</div>
		<script>
		function loadDevice() {
			var url = contextPath + '/device/findDeviceName.do'; 
			var deviceSelect=document.getElementById("device");
			$.post(url,{},function(){
				alert(data.toString());
				if(data.success){
					$.each(data.items, function(i,item){
						deviceSelect.options.add(new Option(items[i].id,items[i].value)); 
						   
					});
					
					
				}
			},'json');
			
		/* 	var json=eval("(+'data+)");
			for(var e in json ){ 
				var opt_1=new Option(list[e].name,list[e].value); 
				_mode.add(opt_1); 		
			} 
			$.getJSON(url, function(data){
				$.each(data.items, function(i,item){
					deviceSelect.options.add(new Option(items[i].id,items[i].value)); 
					   
				});
			});
			var _mode=document.getElementById("mode"); 
			for(var e in list){ 
				var opt_1=new Option(list[e].name,list[e].value); 
				_mode.add(opt_1); 		
			} 
			
			/* select.options.add(new Option(name, id)); */
			 
		}
		
		
		$(function() {
			Charts.initOtherCharts();
			loadDevice();
		})
		
		
	</script>
</body></html>