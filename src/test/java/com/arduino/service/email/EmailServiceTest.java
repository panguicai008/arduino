package com.arduino.service.email;

import java.util.Random;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.arduino.service.email.EmailService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-*.xml")
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
@Transactional
public class EmailServiceTest {
	@Autowired
	private EmailService emailService;

	@Test
	public void testEmailService() {
		int x = 100000 + new Random().nextInt(899999);
		try {
			this.emailService.sendCode("443428773@qq.com",x+"");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
