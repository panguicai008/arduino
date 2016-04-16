package com.arduino.service.device;

import java.util.List;
import java.util.Map;

import com.arduino.dmo.Device;

public interface DeviceService {
	List<Device> findAll();
	public List<Device> findDeviceByRoom(String roomId);
	public List<Device> findDeviceByIp(String ip);
	public void save(Device device);
	public Map<Integer, Boolean> sendCommand(Device device) throws Exception;
}
