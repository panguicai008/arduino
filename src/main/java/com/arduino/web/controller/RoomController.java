package com.arduino.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.arduino.dao.UserDao;
import com.arduino.dao.RoomDao;
import com.arduino.dmo.User;
import com.arduino.dmo.Device;
import com.arduino.dmo.Room;
//查询所有
//添加
//修改
@RequestMapping("room")
@Controller
public class RoomController {
	//列出所有的房间
	@RequestMapping("main.do")
	public String roomList() {
		return "room/main";
	}

	@Autowired
	private RoomDao roomDao;
	//房间列表
	@RequestMapping("list.do")
	@ResponseBody
	public Map<String, Object> list(HttpServletRequest request, int pageNumber, int pageSize) {
		Map<String, Object> map = new HashMap<>();
		Page<Room> pageList = this.roomDao.findAll(new PageRequest(pageNumber, pageSize));
		map.put("rows", pageList.getContent());
		map.put("total", pageList.getTotalElements());
		return map;
	}
	

	@RequestMapping("addPage.do")
	public String addPage() {
		return "room/add";
	}

	@RequestMapping("add.do")
	@ResponseBody
	public Map<String, Object> add(String roomId, String type,String description){
		Map<String, Object> map = new HashMap<>();
		Room room=new Room();
		room.setRoomId(roomId);
		room.setDescription(description);
		room.setRoomType(type);
		this.roomDao.save(room);
		map.put("success", true);
		return map;
	}

	@RequestMapping("updatePage.do")
	public String updatePage(String roomId, HttpServletRequest request) {
		Room room = this.roomDao.findOne(roomId);
		request.setAttribute("room", room);
		return "room/update";
	}

	@RequestMapping("update.do")
	@ResponseBody
	public Map<String, Object> update(String roomId, String roomType,String description){
		Map<String, Object> map = new HashMap<>();
		Room room = this.roomDao.findOne(roomId);
		room.setDescription(description);
		room.setRoomType(roomType);
		roomDao.save(room);
		map.put("result", true);
		return map;
	}

	@RequestMapping("remove.do")
	@ResponseBody
	public Map<String, Object> remove(Room room) {
		Map<String, Object> map = new HashMap<String, Object>();
		this.roomDao.delete(room.getRoomId());
		map.put("success", true);
		return map;
	}

}
