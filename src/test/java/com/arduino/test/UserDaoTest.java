package com.arduino.test;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.arduino.dao.UserDao;

import com.arduino.dmo.User;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-*.xml")
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
@Transactional
public class UserDaoTest {
	@Autowired
	private UserDao userDao;
	@Test
	public void testUser() {
		List<User> users = this.userDao.findAll();
		for (User user : users) {
			System.out.println(user.getName());
			
		}
	}

	
}
