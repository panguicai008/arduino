package com.arduino.service.room;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arduino.dao.RoomDao;
import com.arduino.dmo.Room;
import com.arduino.service.room.RoomService;
@Service
public class RoomServiveImpl implements RoomService {
	@Autowired
	private RoomDao roomDao;
	@Override
	public Room findRoomByRoomId(String roomId) {
		
		return this.roomDao.findOne(roomId);
	}

	@Override
	public List<Room> findAllRoom() {
		
		return this.roomDao.findAll();
	}

}
