package com.arduino.web.controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.arduino.dao.DeviceDao;
import com.arduino.dao.RoomDao;
import com.arduino.dmo.Device;
import com.arduino.dmo.Room;
import com.arduino.service.device.DeviceService;
import com.arduino.service.room.RoomService;
import com.arduino.util.DBUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


@RequestMapping("device")
@Controller
public class DeviceController {
	@RequestMapping("main.do")
	public String main() {
		//main.jsp
		return "device/main";
	}
	@Autowired
	private DeviceService deviceService;
	@Autowired
	private DeviceDao deviceDao;
	//房间列表
	@RequestMapping("list.do")
	@ResponseBody
	public Map<String, Object> list(HttpServletRequest request, int pageNumber, int pageSize) {
		Map<String, Object> map = new HashMap<>();
		Page<Device> pageList = this.deviceDao.findAll(new PageRequest(pageNumber, pageSize));
		map.put("rows", pageList.getContent());
		map.put("total", pageList.getTotalElements());
		return map;
	}
	@RequestMapping("updatePage.do")
	public String updatePage(Integer deviceId, HttpServletRequest request) {
		request.setAttribute("device", this.deviceDao.findOne(deviceId));
		return "device/update";
	}
	
	@RequestMapping("findAll.do")
	@ResponseBody
	public Map<String, Object> loadDevices() {
		Map<String, Object> map = new HashMap<>();
		List<Device> lists = this.deviceService.findAll();
		map.put("devices", lists);
		return map;
	}
	@RequestMapping("findDeviceName.do")
	@ResponseBody
	public JSONArray findDeviceName(HttpServletRequest request){
		
		Map<Integer ,Object> map=new HashMap<>();
		Map<String,Object> map2=new HashMap<>();
		JSONArray json=new JSONArray();
		JSONObject jo=new JSONObject();
		try{
			Connection conn=DBUtils.openConnection();
			Statement st=conn.createStatement();
			String sql="select distinct(device.device_id),device_name from device,data where data.device_id=device.device_id";
			ResultSet rs=st.executeQuery(sql);
			while(rs.next()){
				int id=Integer.parseInt(rs.getString("device_id"));
				String deviceName=rs.getString("device_name");
				jo.put("id", id);
				
				jo.put("value", deviceName);
				json.add(jo);
				map.put(id, deviceName);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		request.setAttribute("deviceJson", json);
		map2.put("devices", json);
		
		
		return json;
	}

	
	
	
	@RequestMapping("addPage.do")
	public String addPage() {
		return "device/add";
	}
	
	@Autowired
	private RoomService roomService;
	@RequestMapping("addDevice.do")
	@ResponseBody
	public Map<String,Object>addDevice(String deviceName,String ip,Integer port,String description,Integer state,String roomId){
		Map<String ,Object> map=new HashMap<>();
		Room room=this.roomService.findRoomByRoomId(roomId);
		Device device=new Device();
		device.setDeviceName(deviceName);
		device.setIp(ip);
		device.setPort(port);
		device.setDescription(description);
		device.setState(state);
		device.setRoom(room);
		this.deviceDao.save(device);
		map.put("success", true);
		return map;
		
	}
	@RequestMapping("remove.do")
	@ResponseBody
	public Map<String, Object> remove(Device device) {
		Map<String, Object> map = new HashMap<String, Object>();
		this.deviceDao.delete(device.getDeviceId());
		map.put("success", true);
		return map;
	}
	@RequestMapping("findDeviceByRoomId.do")
	@ResponseBody
	public Map<String ,Object> findDeviceByRoomId(String roomId){
		Map<String,Object> map=new HashMap<>();
		List<Device> deviceList=this.deviceService.findDeviceByRoom(roomId);
		map.put("deviceList",deviceList);
		return map;
	}
	@RequestMapping("findDeviceByIp.do")
	@ResponseBody
	public Map<String ,Object> findDeviceByIp(String ip){
		Map<String,Object> map=new HashMap<>();
		List<Device> device=this.deviceService.findDeviceByIp(ip);
		map.put("device",device);
		return map;
	}
	
}
