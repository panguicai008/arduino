package com.arduino.service.user;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arduino.dao.UserDao;
import com.arduino.dmo.User;
import com.arduino.util.MD5Util;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;

	@Override
	public User login(String username, String password) throws Exception {
		User user = userDao.findOne(username);
		if (user == null) {
			throw new Exception("用户不存在");
		}
		if (!user.getPassword().equals(MD5Util.getSecurityCode(password))) {
			throw new Exception("密码错误");
		}
		return user;
	}



	@Override
	@Transactional(value=TxType.REQUIRED,rollbackOn=Throwable.class)
	public void add(User user) {
		user.setRegDate(new Date());
		this.userDao.save(user);
	}



	@Override
	public List<User> findAll() {
		
		return this.userDao.findAll();
	}

}
