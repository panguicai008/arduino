
  <%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>柱状图像显示区域--X轴非时间轴</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
<script type="text/javascript" src="../js/jquery-1.4.3.min.js"></script>
<script type="text/javascript" src="../js/highcharts.js"></script>
<%@include file="/common/meta.jsp"%>
<%@include file="/common/easyui.jsp"%>

<script type="text/javascript" src="../js/main.js"></script>
<script type="text/javascript" src="../js/highcharts.js"></script>

<script type='text/javascript'>  
    //<![CDATA[   
    function dateToStr(){
		$('#start').datetimebox({
	    	value:"",
	    	required: true,
	    	showSeconds: true
		})
	}
    $(function(){  
                 //声明报表对象  
        var chart = new Highcharts.Chart({  
            chart: {  
                //将报表对象渲染到层上  
            renderTo: 'container'  
        },  
                //设定报表对象的初始数据  
        series: [{  
            data: [29.9, 71.5, 106.4, 129.2, 144.0, 176.0, 135.6, 148.5, 216.4, 194.1, 95.6, 54.4,78,88,67]          
        }]  
    });  
  
        function getForm(){  
        	var url=basePath+'/data/findDataByTime.do';
        	var start=$('#start').datetimebox('getValue');
                                                //使用JQuery从后台获取JSON格式的数据  
            jQuery.getJSON(url, {start:start}, function(data) {  
                                                                //为图表设置值  
                chart.series[0].setData(data);  
            });  
        }  
  
      
        $(document).ready(function() {  
            //每隔3秒自动调用方法，实现图表的实时更新  
            window.setInterval(getForm,3000);   
            dateToStr();
              
        });  
      
  
    });  
    //]]>   
    </script>  

 </head>
  
  <body>
  <label for="start">起始时间:</label>
			<input id="start" type="text" name="start"/>&nbsp;&nbsp;
    	<div id="container" style="width: 800px; height: 400px; margin: 0 auto"></div>	
    	<label for="end">结束时间:</label>
			<input class="easyui-datetimebox" name="end" id="end"
        data-options="required:true,showSeconds:true" style="width:150px"/>	
  </body>
</html>

