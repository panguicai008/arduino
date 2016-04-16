package com.arduino.web.controller;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

//import org.jfree.data.category.CategoryDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.arduino.dao.DataDao;
import com.arduino.dao.DeviceDao;
import com.arduino.dmo.Data;
import com.arduino.dmo.Device;
import com.arduino.service.data.DataService;
import com.arduino.util.DBUtils;
import com.arduino.util.DateToCalendarUtil;



@RequestMapping("data")
@Controller
public class DataController {
	@Autowired
	private DataDao dataDao;
	@RequestMapping("main.do")
	public String main() {
		return "data/main";
	}
	@RequestMapping("list.do")
	@ResponseBody
	public Map<String, Object> list(HttpServletRequest request, int pageNumber, int pageSize) {
		Map<String, Object> map = new HashMap<>();
		Page<Data> pageList = this.dataDao.findAll(new PageRequest(pageNumber, pageSize));
		map.put("rows", pageList.getContent());
		map.put("total", pageList.getTotalElements());
		return map;
	}

	
	@Autowired
	private DataService dataService;
	@RequestMapping("findAll.do")
	@ResponseBody
	public Map<String, Object> loadData() {
		Map<String, Object> map = new HashMap<>();
		List<Data> dataList = this.dataService.findAll();
		map.put("dataList", dataList);
		return map;
	}
	@RequestMapping("dataChart.do")
	@ResponseBody
	public Map<String ,Object> findDataByTimeAndDevice(String thisTime,Integer id){
		//yyyy-MM-dd HH:mm:ss
//		String thisTime=DateToCalendarUtil.dateToString(time);
		int year=Integer.parseInt(thisTime.substring(0, 4));
		int month=Integer.parseInt(thisTime.substring(5,7));
		int day=Integer.parseInt(thisTime.substring(8,10));
		//int hour=Integer.parseInt(thisTime.substring(11,13));
		int hour=8;
		//8-22点
		List<Data>dataList=new ArrayList<Data>();
		java.sql.Connection conn=DBUtils.openConnection();
		double[][] datas = new double[5][15];
		for(int i=0;i<15;i++){
			Data data=new Data();	
			try{
				String sql="SELECT AVG(lm35_outlet),AVG(lm35_intake),AVG(pir),AVG(humidity),AVG(light) FROM DATA WHERE YEAR(TIME)='"+year+"' AND MONTH(TIME)='"+month+"'AND DAY(TIME)='"+day+"'AND HOUR(TIME)='"+hour+"'AND device_id='"+id+"'";
				Statement ps=conn.createStatement();
				ResultSet rs=ps.executeQuery(sql);
				
				while(rs.next()){
					data.setLm35Outlet(rs.getDouble("avg(lm35_outlet)"));
					data.setLm35Intake(rs.getDouble("avg(lm35_intake)"));
					data.setHumidity(rs.getDouble("avg(humidity)"));
					data.setPir(rs.getDouble("avg(pir)"));
					data.setLight(rs.getDouble("avg(light)"));
					this.dataDao.save(data);					
					datas[0][i]=data.getLm35Outlet();
					datas[1][i]=data.getLm35Intake();
					datas[2][i]=data.getHumidity();
					datas[3][i]=data.getPir();
					datas[4][i]=data.getLight();
				}	    
			}
			catch(Exception e){
				e.printStackTrace();
			}
			dataList.add(data);
			hour++;	
		}
		String[] rowKeys = {"出风口温度", "进风口温度", "湿度","热释红外","光照"};
	    String[] columnKeys = {"8点", "9点", "10点", "11点", "12点","13点","14点","15点","16点","17点","18点","19点","20点","21点","22点"};
	   // CategoryDataset dataset = Chart.getBarData(datas, rowKeys, columnKeys);
	    //Chart.createTimeXYChar("折线图", "x轴", "y轴", dataset, "lineAndShap.png");
		
		Map<String ,Object> map=new HashMap<>();
		map.put("dataList", dataList);
		return map;
	}
	@RequestMapping("findDataByTime.do")
	@ResponseBody
	public Map<String, Object> findDataByTime(String thisTime,Integer id){
		//yyyy-MM-dd HH:mm:ss
//		String thisTime=DateToCalendarUtil.dateToString(time);
		int year=Integer.parseInt(thisTime.substring(0, 4));
		int month=Integer.parseInt(thisTime.substring(5,7));
		int day=Integer.parseInt(thisTime.substring(8,10));
		//int hour=Integer.parseInt(thisTime.substring(11,13));
		int hour=8;
		//8-22点
		List<Data>dataList=new ArrayList<Data>();
		java.sql.Connection conn=DBUtils.openConnection();
		double[][] datas = new double[5][15];
		for(int i=0;i<15;i++){
			Data data=new Data();	
			try{
				String sql="SELECT AVG(lm35_outlet),AVG(lm35_intake),AVG(pir),AVG(humidity),AVG(light) FROM DATA WHERE YEAR(TIME)='"+year+"' AND MONTH(TIME)='"+month+"'AND DAY(TIME)='"+day+"'AND HOUR(TIME)='"+hour+"'AND device_id='"+id+"'";
				Statement ps=conn.createStatement();
				ResultSet rs=ps.executeQuery(sql);
				
				while(rs.next()){
					data.setLm35Outlet(rs.getDouble("avg(lm35_outlet)"));
					data.setLm35Intake(rs.getDouble("avg(lm35_intake)"));
					data.setHumidity(rs.getDouble("avg(humidity)"));
					data.setPir(rs.getDouble("avg(pir)"));
					data.setLight(rs.getDouble("avg(light)"));
					this.dataDao.save(data);					
					datas[0][i]=data.getLm35Outlet();
					datas[1][i]=data.getLm35Intake();
					datas[2][i]=data.getHumidity();
					datas[3][i]=data.getPir();
					datas[4][i]=data.getLight();
				}	    
			}
			catch(Exception e){
				e.printStackTrace();
			}
			dataList.add(data);
			hour++;	
		}
		String[] rowKeys = {"出风口温度", "进风口温度", "湿度","热释红外","光照"};
	    String[] columnKeys = {"8点", "9点", "10点", "11点", "12点","13点","14点","15点","16点","17点","18点","19点","20点","21点","22点"};
//	    CategoryDataset dataset = Chart.getBarData(datas, rowKeys, columnKeys);
//	    Chart.createTimeXYChar("折线图", "x轴", "y轴", dataset, "lineAndShap.png");
		
		Map<String ,Object> map=new HashMap<>();
		map.put("dataList", dataList);
		return map;
		
	}
	@RequestMapping("findDataByTimeBetween.do")
	@ResponseBody
	public Map<String, Object> findDataByTime(String time1Str,String time2Str){
		Map<String ,Object> map=new HashMap<>();
		Date time1=DateToCalendarUtil.stringToDate(time1Str);
		Date time2=DateToCalendarUtil.stringToDate(time2Str);
		List<Data>  dataList=this.dataService.findDataByTimeBetween(time1,time2);
		map.put("dataByTimeBetweenList", dataList);
		return map;
		
	}
	@RequestMapping("addPage.do")
	
	public String addPage(){
		return "data/add";
	}
	@Autowired
	private DeviceDao deviceDao;
	
	@RequestMapping("add.do")
	@ResponseBody
	public Map<String ,Object> add(Integer deviceId,Double lm35Intake,Double lm35Outlet,Double pir,Double humidity,Double light,String time){
		Data data=new Data();
		Map<String ,Object> map=new HashMap<>();
		Device device=this.deviceDao.findOne(deviceId);
		data.setDevice(device);
		data.setLm35Intake(lm35Intake);
		data.setLm35Outlet(lm35Outlet);
		data.setPir(pir);
		data.setHumidity(humidity);
		data.setLight(light);
		System.out.println(light);
		System.out.println(time);
		Date oneDate=DateToCalendarUtil.stringToDate(time);
		data.setTime(oneDate);
		this.dataDao.save(data);
		map.put("success", true);
		return map;	
	}
	@RequestMapping("updatePage.do")
	
	public String updatePage(Integer id ,HttpServletRequest request){
		
		Data data=this.dataDao.findOne(id);
		request.setAttribute("data", data);
		
		return "data/update";
	}
	
	
	

	@RequestMapping("update.do")
	@ResponseBody
	public Map<String, Object> update(Integer id,Integer deviceId,Double lm35Intake,Double lm35Outlet,Double pir,Double humidity,Double light,Date time){
		Map<String, Object> map = new HashMap<>();
		Data data = this.dataDao.findOne(id);
		data.setDevice(this.deviceDao.findOne(deviceId));
		data.setHumidity(humidity);
		data.setLight(light);
		data.setLm35Intake(lm35Intake);
		data.setLm35Outlet(lm35Outlet);
		data.setPir(pir);
		data.setTime(time);
		dataDao.save(data);
		map.put("result", true);
		return map;
	}

	@RequestMapping("remove.do")
	@ResponseBody
	public Map<String ,Object> remove(Data data){		
		Map<String ,Object> map=new HashMap<>();	
		this.dataDao.delete(data.getId());
		map.put("success", true);
		return map;	
	}


	
}

