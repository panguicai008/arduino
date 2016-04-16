package com.arduino.service.data;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arduino.dao.DataDao;
import com.arduino.dmo.Data;
@Service
public class DataServiceImpl implements DataService {
	@Autowired
	private DataDao dataDao;
	@Override
	public List<Data> findAll() {
		
		return this.dataDao.findAll();
	}
	@Override
	public List<Data> findDataByTime(Date time) {
		
		return this.dataDao.findDataByTime(time);
	}
	@Override
	public List<Data> findDataByDevice(Integer deviceId) {
		
		return this.dataDao.findDataByDevice(deviceId);
	}
	@Override
	public void add(Data data) {
		data.setTime(new Date());
		this.dataDao.save(data);
		
	}
	@Override
	public List<Data> findDataByTimeBetween(Date time1, Date time2) {
		
		return this.dataDao.findDataByTimeBetween(time1, time2);
	}
//	@Override
//	public Data findAvgDataByYearAndMonthAndDayAndHour(int year, int month, int day, int hour) {
//	
//		return this.dataDao.findAvgDataByYearAndMonthAndDayAndHour(year, month, day, hour);
//	}

}
