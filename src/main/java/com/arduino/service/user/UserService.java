package com.arduino.service.user;

import java.util.List;

import com.arduino.dmo.User;

public interface UserService {
	List<User> findAll();

	User login(String name, String password) throws Exception;

	void add(User user);
}
