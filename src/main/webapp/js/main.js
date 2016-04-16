var list=[
{"name":"制热", 
"value":"heat", 
"temperature":[{"name":"17度","value":"17度"}, {"name":"18度","value":"18度"},{"name":"19度","value":"19度"},{"name":"20度","value":"20度"}]},
{"name":"制冷", 
"value":"cold", 
"temperature":[{"name":"26度","value":"26度"}, {"name":"27度","value":"27度"},{"name":"28度","value":"28度"},{"name":"29度","value":"29度"}]},
{"name":"除湿", 
"value":"dehumidification", 
"temperature":[{"name":"17度","value":"17度"}, {"name":"18度","value":"18度"},{"name":"19度","value":"19度"},{"name":"20度","value":"20度"},
{"name":"26度","value":"26度"}, {"name":"27度","value":"27度"},{"name":"28度","value":"28度"},{"name":"29度","value":"29度"}]}];

function init(){ 
	var _mode=document.getElementById("mode"); 
	for(var e in list){ 
		var opt_1=new Option(list[e].name,list[e].value); 
		_mode.add(opt_1); 		
	} 
} 
function toTemperature(){ 
	document.getElementById("temperature").options.length=1; 
	for(var e in list){ 
		if(list[e].value==document.getElementById("mode").value){ 
			for( var p in list[e].temperature){ 
				var opt_2=new Option(list[e].temperature[p].name,list[e].temperature[p].value); 
				document.getElementById("temperature").add(opt_2); 		
			} 
		break; 
		} 
	} 	 
}
function loadTable() {
	$('#dg').datagrid(
			{
				url : contextPath + "/data/list.do",
				singleSelect:true,
				columns : [ [ {
					field : 'id',
					title : '数据编号',
					width : 100
				}, {
					field : 'device',
					title : '设备名',
					width : 100,
					formatter: function(value,row,index){
						if (row.device){
							return row.device.deviceName;
						} else {
							return value;
						}
					}
				}, {
					field : 'lm35Intake',
					title : '进风口温度/°C',
					width : 100
				},{
					field : 'lm35Outlet',
					title : '出风口温度/°C',
					width : 100
				} ,{
					field : 'humidity',
					title : '湿度',
					width : 100
				} ,{
					field : 'pir',
					title : '室内人员情况/V',
					width : 100
				} ,{
					field : 'light',
					title : '室内光照情况/V',
					width : 100
				},{
					field : 'time',
					title :'时间',
					width : 150	,
					formatter: function(value,row,index){
						if (row.time){
							return new Date(row.time).getFullYear()
						    + "-"// "年"
						    + ((new Date(row.time).getMonth() + 1) > 10 ? (new Date(row.time).getMonth() + 1) : "0"
						            + (new Date(row.time).getMonth() + 1))
						    + "-"// "月"
						    + (new Date(row.time).getDate() < 10 ? "0" + new Date(row.time).getDate() : new Date(row.time)
						            .getDate())
						    + " "
						    + (new Date(row.time).getHours() < 10 ? "0" + new Date(row.time).getHours() : new Date(row.time)
						            .getHours())
						    + ":"
						    + (new Date(row.time).getMinutes() < 10 ? "0" + new Date(row.time).getMinutes() : new Date(row.time)
						            .getMinutes())
						    + ":"
						    + (new Date(row.time).getSeconds() < 10 ? "0" + new Date(row.time).getSeconds() : new Date(row.time)
						            .getSeconds());;
						 
						} else {
							return value;
						}
					}
				}] ],queryParams : {
					pageSize : function() {
						return $('#dg').datagrid('getPager').pagination(
								'options').pageSize;
					},
					pageNumber : function() {
						return $('#dg').datagrid('getPager').pagination(
								'options').pageNumber - 1;
					}
				},
				pagination : 'true',
				toolbar : [ {
					iconCls : 'icon-add',
					text:'添加',
					handler : function() {
						window.location=contextPath+'/data/addPage.do';
					}
				}, '-', {
					iconCls : 'icon-edit',
					text:'修改',
					handler : function() {
						var obj=$('#dg').datagrid('getSelected');
						if(obj){
							window.location=contextPath+'/data/updatePage.do?id='+obj.id;
						}else{
							alert('请选中一行');
						}
					}
				},'-',{
					iconCls : 'icon-remove',
					text:'删除',
					handler : function() {
						var obj=$('#dg').datagrid('getSelected');
						var url=contextPath+'/data/remove.do';
						$.post(url,{id:obj.id},function(data){
							if(data.success){
								$('#dg').datagrid('reload');
								alert('删除成功');
							}
						},'json');
					}
				} ,'-',{
					iconCls : 'icon-ok',
					text:'开启设备',
					handler : function() {
						var obj=$('#dg').datagrid('getSelected');
						var name="open";
						var mode=document.getElementById("mode").value;
						var temperature=document.getElementById("temperature").value;
						var windDirection=document.getElementById("windDirection").value;
						var url=contextPath+'/ctrl/open.do';
						$.post(url,{id:obj.id,name:name,mode:mode,temperature:temperature,windDirection:windDirection},function(data,name,mode,temperature,windDirection){
							if(data.success){
								$('#dg').datagrid('reload');
								$('#dg').dategrid('getSelected').addclass('')
								alert('开启成功');
								$('dg').datagrid
							}
						},'json');
					}
				},'-',{
					iconCls : 'icon-no',
					text:'关闭设备',
					handler : function() {
						var obj=$('#dg').datagrid('getSelected');
						
						var name="close";
						var mode=document.getElementById("mode").value;
						var temperature=document.getElementById("temperature").value;
						var windDirection=document.getElementById("windDirection").value;
						var url=contextPath+'/ctrl/close.do';
						$.post(url,{id:obj.id,name:name,mode:mode,temperature:temperature,windDirection:windDirection},function(data,name,mode,temperature,windDirection){
							if(data.success){
								$('#dg').datagrid('reload');
								
								alert('关闭成功');
							}
						},'json');
					}
				} ] 
			});
}


/* function date() {
	var obj=$('#dg');
	var url=contextPath+'/data/dateToStr.do';
	$.post(url,{time:obj.time},function(data){
		if(data.success){
			$('#dg').datagrid('reload');
			alert('成功');
		}
	},'json');
}
function click(){
    $('#toolbar').bind('click', function(){
       
    });
} */

function loadPagination() {
	$('#dg').datagrid('getPager').pagination({
		pageSize : 10,// 每页显示的记录条数，默认为10
		pageList : [ 5, 10, 15 ],// 可以设置每页记录条数的列表
		beforePageText : '第',// 页数文本框前显示的汉字
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录',
	})
}
function dateToStr(){
	$('#start').datetimebox({
    	value:"",
    	required: true,
    	showSeconds: true
	})
}

	/*	var info="1_123|2_123|3_123";
		var infoarray = info.split("|");
		var count = infoarray.length;
		var cc = document.getElementById("#cc");
		for(var i = 0; i < count; i++){
			var id = infoarray[i].split("_")[0];
			var name = infoarray[i].split("_")[1];
			cc.options.add(new Option(name, id));
		}*/
	
	
	/*$('#device').combobox({
	    url:contextPath+'/device/findDeviceName.do',
	    $.post(url,{},function(data){
			if(data.success){
				$('#dg').datagrid('reload');
				
				alert('关闭成功');
			}
		},'json');
	    valueField:'id',
	    textField:'text'
	});*/
	
	

$(function() {
	init();
	toTemperature();
	loadTable();
	loadPagination();
	dateToStr();
	select();
	
})