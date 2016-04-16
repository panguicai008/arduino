package com.arduino.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.arduino.dmo.Data;

public interface DataDao extends JpaRepository<Data,Integer> {
	//此方法用来按小时查询的传感器数据的平均值
	//@Query("SELECT AVG(lm35_outlet),AVG(lm35_intake),AVG(pir),AVG(humidity),AVG(light) FROM DATA WHERE YEAR(TIME)=?1 AND MONTH(TIME)=?1AND DAY(TIME)=?1AND HOUR(TIME)=?1")
	//public String findAvgDataByYearAndMonthAndDayAndHour(int year,int month,int day,int hour);
	public List<Data> findDataByTime(Date time);
	public List<Data> findDataByTimeBetween(Date time1,Date time2);
	public List<Data> findDataByDevice(Integer deviceId);
}
