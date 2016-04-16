package com.arduino.dmo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the room database table.
 * 
 */
@Entity
@NamedQuery(name="Room.findAll", query="SELECT r FROM Room r")
public class Room implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="room_id")
	private String roomId;

	@Lob
	private String description;

	@Column(name="room_type")
	private String roomType;

	//bi-directional many-to-one association to Device
//	@OneToMany(mappedBy="room")
//	private List<Device> devices;

	public Room() {
	}

	public String getRoomId() {
		return this.roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRoomType() {
		return this.roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

//	public List<Device> getDevices() {
//		return this.devices;
//	}
//
//	public void setDevices(List<Device> devices) {
//		this.devices = devices;
//	}
//
//	public Device addDevice(Device device) {
//		getDevices().add(device);
//		device.setRoom(this);
//
//		return device;
//	}
//
//	public Device removeDevice(Device device) {
//		getDevices().remove(device);
//		device.setRoom(null);
//
//		return device;
//	}

}