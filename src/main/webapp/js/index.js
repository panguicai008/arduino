/**
 * Created by Administrator on 2016/2/25.
 */

function showtime(){
    var date = new Date(); //日期对象
    var now = "";
    now = date.getFullYear()+"年"; //读英文就行了
    now = now + (date.getMonth()+1)+"月"; //取月的时候取的是当前月-1如果想取当前月+1就可以了
    now = now + date.getDate()+"日";
    now = now + date.getHours()+"时";
    now = now + date.getMinutes()+"分";
    now = now + date.getSeconds()+"秒";
    document.getElementById("nowDiv").innerHTML = now; //div的html是now这个字符串
    setTimeout("showtime()",1000); //设置过1000毫秒就是1秒，调用show方法
}
function st(){
    var sheng=document.getElementById('sheng');
    //存省的数组，可自行添加
    var shengs=new Array(new Option("--请选择类型--",''),new Option("单点",'daj'),new Option("电机",'dij'));
    for(var i=0;i<shengs.length;i++){
        sheng.options[i]=shengs[i];
    }
}
//存市的数组，根据省的数组添加
var shis=new Array();
shis[0]=new Array(new Option("--请选择设备--",''));
shis[1]=new Array(new Option("降雨器",'降雨器'),new Option("生长灯",'生长灯'),new Option("水泵",'水泵'));
shis[2]=new Array(new Option("风机",'风机'),new Option("天窗",'天窗'),new Option("侧窗",'侧窗'));
function change(obj){
    var shi=document.getElementById('shi');
    shi.options.length=0;
    var index=sheng.selectedIndex;
    for(var x in shis[index]){
        shi.options[x]=shis[index][x]
    }
}
function add(btn) {
    var ntr = document.createElement("tr")
    ntr.innerHTML =
        '<td><input type="time"></td>'+
        '<td><input type="time"></td>'+
        '<td><input type="number" name="points" min="1" max="10" /></td>'+
        '<td><input type="number" name="points" min="1" max="10" /></td>'+
        '<td align="center"><button class="btn btn-danger bnt-xs" onclick="del(this)"><i class="fa fa-trash-o"></i> 删除</button></td>';
    var tbody = document.getElementById("goods");
    tbody.appendChild(ntr);
}
function add1(btn) {
    var ntr = document.createElement("tr")
    ntr.innerHTML =
        '<td><input type="text"></td>'+
        '<td><input type="number" name="points" min="1" max="10" /></td>'+
        '<td><input type="number" name="points" min="1" max="10" /></td>'+
        '<td><input type="number" name="points" min="1" max="10" /></td>'+
        '<td align="center"><button class="btn btn-danger bnt-xs" onclick="del(this)"><i class="fa fa-trash-o"></i> 删除</button></td>';
    var tbody = document.getElementById("goodss");
    tbody.appendChild(ntr);
}
function del(btn) {
    var tr = btn.parentNode.parentNode;
    tr.parentNode.removeChild(tr);

}
//模版拖动
$(function() {
    $( ".divmove" ).sortable({
        connectWith: ".divmove",
        cancel: ".portlet-toggle",
        placeholder: "portlet-placeholder ui-corner-all"
    });
});
function adddiv(btn){
    var ulnav=document.createElement("LI");
    var divnav=document.createElement("div");
    ulnav.innerHTML='<a href="#box_tab4" data-toggle="tab"><i class="fa fa-map-marker"></i><span class="hidden-inline-mobile">分区二</span></a>';
    divnav.innerHTML=

    $("#ulnav").before(ulnav);
}
function delli()
{
    var list=document.getElementById("myList");
    list.removeChild(list.childNodes[0]);
}

function YYYYMMDDstart()
{
    MonHead = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];

    //先给年下拉框赋内容
    var y   = new Date().getFullYear();
    for (var i = (y-30); i < (y+30); i++) //以今年为准，前30年，后30年
    document.form1.YYYY.options.add(new Option(" "+ i +" 年", i));

    //赋月份的下拉框
    for (var i = 1; i < 13; i++)
    document.form1.MM.options.add(new Option(" " + i + " 月", i));

    document.form1.YYYY.value = y;
    document.form1.MM.value = new Date().getMonth() + 1;
    var n = MonHead[new Date().getMonth()];
    if (new Date().getMonth() ==1 && IsPinYear(YYYYvalue)) n++;
    writeDay(n); //赋日期下拉框
    document.form1.DD.value = new Date().getDate();
    }
if(document.attachEvent)
window.attachEvent("onload", YYYYMMDDstart);
else
window.addEventListener('load', YYYYMMDDstart, false);
function YYYYDD(str) //年发生变化时日期发生变化(主要是判断闰平年)
{
    var MMvalue = document.form1.MM.options[document.form1.MM.selectedIndex].value;
    if (MMvalue == ""){ var e = document.form1.DD; optionsClear(e); return;}
    var n = MonHead[MMvalue - 1];
    if (MMvalue ==2 && IsPinYear(str)) n++;
    writeDay(n)
    }
function MMDD(str)  //月发生变化时日期联动
{
    var YYYYvalue = document.form1.YYYY.options[document.form1.YYYY.selectedIndex].value;
    if (YYYYvalue == ""){ var e = document.form1.DD; optionsClear(e); return;}
    var n = MonHead[str - 1];
    if (str ==2 && IsPinYear(YYYYvalue)) n++;
    writeDay(n)
    }
function writeDay(n)  //据条件写日期的下拉框
{
    var e = document.form1.DD; optionsClear(e);
    for (var i=1; i<(n+1); i++)
    e.options.add(new Option(" "+ i + " 日", i));
    }
function IsPinYear(year)//判断是否闰平年
{
    return(0 == year%4 && (year%100 !=0 || year%400 == 0));
    }
function optionsClear(e)
{
    e.options.length = 1;
    }
function YYYYMMDDstart1()
{
    MonHead = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];

    //先给年下拉框赋内容
    var y   = new Date().getFullYear();
    for (var i = (y-30); i < (y+30); i++) //以今年为准，前30年，后30年
        document.form2.YYYY1.options.add(new Option(" "+ i +" 年", i));

    //赋月份的下拉框
    for (var i = 1;i< 13; i++)
        document.form2.MM1.options.add(new Option(" " + i + " 月", i));

    document.form2.YYYY1.value = y;
    document.form2.MM1.value = new Date().getMonth() + 1;
    var n = MonHead[new Date().getMonth()];
    if (new Date().getMonth() ==1 && IsPinYear(YYYY1value)) n++;
    writeDay1(n); //赋日期下拉框
    document.form2.DD1.value = new Date().getDate();
}
if(document.attachEvent)
    window.attachEvent("onload", YYYYMMDDstart1);
else
    window.addEventListener('load', YYYYMMDDstart1, false);
function YYYYDD1(str) //年发生变化时日期发生变化(主要是判断闰平年)
{
    var MM1value = document.form2.MM1.options[document.form2.MM1.selectedIndex].value;
    if (MM1value == ""){ var e = document.form2.DD1; optionsClear(e); return;}
    var n = MonHead[MMvalue - 1];
    if (MM1value ==2 && IsPinYear(str)) n++;
    writeDay1(n)
}
function MMDD1(str)  //月发生变化时日期联动
{
    var YYYY1value = document.form2.YYYY1.options[document.form2.YYYY1.selectedIndex].value;
    if (YYYY1value == ""){ var e = document.form2.DD1; optionsClear(e); return;}
    var n = MonHead[str - 1];
    if (str ==2 && IsPinYear(YYYYvalue)) n++;
    writeDay1(n)
}
function writeDay1(n)  //据条件写日期的下拉框
{
    var e = document.form2.DD1; optionsClear(e);
    for (var i=1; i<(n+1); i++)
        e.options.add(new Option(" "+ i + " 日", i));
}





