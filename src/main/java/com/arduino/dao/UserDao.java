package com.arduino.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arduino.dmo.User;

public interface UserDao extends JpaRepository<User, String> {
	public List<User> findByName(String name);
	public List<User> findByPhone(String phone);
}
