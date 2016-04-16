package com.arduino.test;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.arduino.dao.DataDao;
import com.arduino.dmo.Data;
import com.arduino.util.DateToCalendarUtil;
import com.arduino.web.controller.DataController;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-*.xml")
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
public class DataDaoTest {
	@Autowired
	private DataDao dataDao;
	@Autowired
	private DataController data;

//	@Test
//	public void testDataFind() {
//		List<Data> data = this.dataDao.findAll();
//		for (Data d : data) {
//			System.out.println(d.getId());
//		}
//	}
//
//	@Test
//	public void testDataPage() {
//		Page<Data> dataPages = this.dataDao.findAll(new PageRequest(1, 2));
//		for (Data data : dataPages) {
//			System.out.println(data.getId());
//		}
//
//	}
//	@Test
//	public void testData(){
//		Date time=DateToCalendarUtil.stringToDate2("2016-03-02 09:21:23");
//		
//		System.out.println(this.data.findDataByTime(time).get("dataList"));
//		
//	}
}
