package com.arduino.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.text.ParseException;


public class DateToCalendarUtil {
	//2016-02-25 09:26:30
	public static String dateToString(Date time){
		//time的获取方式：
		//long timew=System.currentTimeMillis();
		//Date date=new Date();
		//Timestamp now = new Timestamp(System.currentTimeMillis());
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr=sdf.format(time);
		return dateStr;
	}
	//16-2-25 上午9:26
	public String dateToShort(Date time){
		DateFormat shortDateFormat=DateFormat.getDateTimeInstance(DateFormat.SHORT,DateFormat.SHORT);
		String dateStr=shortDateFormat.format(time);
		return dateStr;
		
	}
	//2016-2-25 9:26:30
	public String dateToMedium(Date time){
		DateFormat mediumDateFormat=DateFormat.getDateTimeInstance(DateFormat.MEDIUM,DateFormat.MEDIUM);
		String dateStr=mediumDateFormat.format(time);
		return dateStr;
		
	}
	//2016年2月25日 上午09时26分30秒
	public String dateToLong(Date time){
		DateFormat longDateFormat=DateFormat.getDateTimeInstance(DateFormat.LONG,DateFormat.LONG);
		String dateStr=longDateFormat.format(time);
		return dateStr;
		
	}
	//2016年2月25日 星期四 上午09时26分30秒 CST
	public String dateToFull(Date time){
		DateFormat fullDateFormat=DateFormat.getDateTimeInstance(DateFormat.FULL,DateFormat.FULL);
		String dateStr=fullDateFormat.format(time);
		return dateStr;
		
	}
	
	//jQuery中的时间格式MM/dd/yyyy HH:mm:ss需要将此格式转换成正常的yyyy-MM-dd HH:mm:ss
	public static  Date stringToDate(String str){
		SimpleDateFormat sdf=new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		Date date=new Date();
		try{
			date=sdf.parse(str);
		}catch(ParseException e){
			e.printStackTrace();
		}
		return date;
	}
	public static  Date stringToDate2(String str){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date=new Date();
		try{
			date=sdf.parse(str);
		}catch(ParseException e){
			e.printStackTrace();
		}
		return date;
	}
	
	public String calendarToString(Calendar calendar){
		Calendar cal=new GregorianCalendar();
		int year=cal.get(Calendar.YEAR);
		int month=cal.get(Calendar.MONTH)+1;//Calendar.MONTH为0；
		int day=cal.get(Calendar.DAY_OF_MONTH);
		int weekNumber=cal.get(Calendar.DAY_OF_WEEK);
		String week="";
		switch(weekNumber){
			case 1: week="星期天";break;
			case 2: week="星期一";break;
			case 3: week="星期二";break;
			case 4: week="星期三";break;
			case 5: week="星期四";break;
			case 6: week="星期五";break;
			default: week="星期六";break;		
		}
		int hour=cal.get(Calendar.HOUR_OF_DAY);
		int minute=cal.get(Calendar.MINUTE);
		int second=cal.get(Calendar.SECOND);
		String calStr="";
		String h,m,s; 
		if(hour<10) 
			h = "0"+hour; 
		else 
			h = hour+"";	
		if(minute<10) 
			m = "0"+minute; 
		else 
			m = minute+""; 
		if(second<10) 
			s = "0"+second; 
		else 
			s = second+"";
		calStr="今天是："+year+"年"+month+"月"+day+"日"+week+h+":"+m+":"+s;
		return calStr;
		
		
	}
}
