//package com.arduino.dao;
//
//import java.util.List;
//
//import javax.transaction.Transactional;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.transaction.TransactionConfiguration;
//
//import com.arduino.dao.UserDao;
//import com.arduino.dao.ZoneDao;
//import com.arduino.dmo.User;
//import com.arduino.dmo.Zone;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("classpath:spring-*.xml")
//@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
//@Transactional
//public class UserDaoTest {
//	@Autowired
//	private UserDao userDao;
//	@Autowired
//	private ZoneDao zoneDao;
//
//	@Test
//	public void testUser() {
//		List<User> users = this.userDao.findAll();
//		for (User user : users) {
//			Zone zone = new Zone();
//			zone.setUser(user);
//			zone.setZoneName("测试");
//			this.zoneDao.save(zone);
//		}
//	}
//
//	@Test
//	public void testZoneFind() {
//		List<Zone> zones = this.zoneDao.findByUser_NameLike("小%");
//		for (Zone zone : zones) {
//			System.out.println(zone.getZoneName());
//		}
//	}
//}
