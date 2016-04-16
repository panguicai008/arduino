package com.arduino.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.arduino.dmo.Device;


public interface DeviceDao extends JpaRepository<Device, Integer>{
	public List<Device> findDeviceByRoom(String roomId);
	
	public List<Device> findDeviceByIp(String ip);
	
	
}
