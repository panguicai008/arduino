package com.arduino.service.data;

import java.util.Date;
import java.util.List;

import com.arduino.dmo.Data;

public interface DataService {
	public List<Data> findAll();
	public List<Data> findDataByTime(Date time);
	public List<Data> findDataByTimeBetween(Date time1,Date time2);
	public List<Data> findDataByDevice(Integer deviceId);
	public void add(Data data);
	//public Data findAvgDataByYearAndMonthAndDayAndHour(int year,int month,int day,int hour);
}
