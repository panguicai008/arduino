package com.arduino.service.room;

import java.util.List;

import com.arduino.dmo.Room;

public interface RoomService {
	public Room findRoomByRoomId(String roomId);
	public  List<Room>  findAllRoom();
}
