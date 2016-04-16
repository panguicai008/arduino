<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" >
<head>
    <title>动态添加select的option_jquery中文网_www.jquerycn.cn</title>
</head>
<body>
    <select id="Select">
    </select>
    <input id="Button" type="button" value="button" onclick="javascript:buileselect();" />
    <script type="text/javascript" language="javascript">
    function buileselect()
    {
        var info = "1_123|2_123|3_123";
        var infoarray = info.split("|");
        var count = infoarray.length;
        var select = document.getElementById("Select");
        for(var i = 0; i < count; i++)
        {
            var id = infoarray[i].split("_")[0];
            var name = infoarray[i].split("_")[1];
            select.options.add(new Option(name, id));
        }
    }
    $(function(){
		window.setInterval(buileselect, 1000);
	});
    </script>
</body>
</html>