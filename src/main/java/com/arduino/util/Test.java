package com.arduino.util;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

//import org.jfree.data.category.CategoryDataset;

import com.arduino.dmo.Data;

public class Test {

	public static void main(String[] args) {
//		Date time=new Date();
//		//String thisTime=DateToCalendarUtil.dateToString(time);
//		String thisTime="2016-03-02 09:23:23";
//		System.out.println(thisTime);
//		int year=Integer.parseInt(thisTime.substring(0, 4));
//		int month=Integer.parseInt(thisTime.substring(5,7));
//		int day=Integer.parseInt(thisTime.substring(8,10));
//		
//		System.out.println(year);
//	
//		System.out.println(month);
//		System.out.println(day);
//		
//		
//
//		//yyyy-MM-dd HH:mm:ss
////		String thisTime=DateToCalendarUtil.dateToString(time);
//		
//		//int hour=Integer.parseInt(thisTime.substring(11,13));
//	
//		//8-22点
//		int hour=8;
//		List<Data>dataList=new ArrayList<Data>();
//		java.sql.Connection conn=DBUtils.openConnection();
//		double[][] datas = new double[5][15];
//		for(int i=0;i<15;i++){
//			Data data=new Data();	
//			try{
//				String sql="SELECT AVG(lm35_outlet),AVG(lm35_intake),AVG(pir),AVG(humidity),AVG(light) FROM DATA WHERE YEAR(TIME)='"+year+"' AND MONTH(TIME)='"+month+"'AND DAY(TIME)='"+day+"'AND HOUR(TIME)='"+hour+"'";
//				Statement ps=conn.createStatement();
//				ResultSet rs=ps.executeQuery(sql);
//				
//				while(rs.next()){
//					data.setLm35Outlet(rs.getDouble("avg(lm35_outlet)"));
//					data.setLm35Intake(rs.getDouble("avg(lm35_intake)"));
//					data.setHumidity(rs.getDouble("avg(humidity)"));
//					data.setPir(rs.getDouble("avg(pir)"));
//					data.setLight(rs.getDouble("avg(light)"));
//									
//					datas[0][i]=data.getLm35Outlet();
//					datas[1][i]=data.getLm35Intake();
//					datas[2][i]=data.getHumidity();
//					datas[3][i]=data.getPir();
//					datas[4][i]=data.getLight();
//				}	    
//			}
//			catch(Exception e){
//				e.printStackTrace();
//			}
//			dataList.add(data);
//			hour++;	
//		}
//		String[] rowKeys = {"lm35Outlet", "lm35Intake", "humidity","pir","light"};
//	    String[] columnKeys = {"8点", "9点", "10点", "11点", "12点","13点","14点","15点","16点","17点","18点","19点","20点","21点","22点"};
//	    CategoryDataset dataset = Chart.getBarData(datas, rowKeys, columnKeys);
//	    Chart.createTimeXYChar("折线图", "x轴", "y轴", dataset, "lineAndShap.png");
//		
//		Map<String ,Object> map=new HashMap<>();
//		map.put("dataList", dataList);
		String a="12332";
		System.out.println(a+3);
		int aa=Integer.parseInt(a);
		System.out.println(aa+3);
		
		System.out.println(MD5Util.getSecurityCode("123456"));
		System.out.println(MD5Util.getSecurityCode("000000"));
		
	
		
	
		
		

	}

}
