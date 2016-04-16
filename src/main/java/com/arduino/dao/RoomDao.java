package com.arduino.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arduino.dmo.Room;

public interface RoomDao extends JpaRepository<Room,String> {
	
}
