package com.arduino.dmo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the device database table.
 * 
 */
@Entity
@NamedQuery(name="Device.findAll", query="SELECT d FROM Device d")
public class Device implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="device_id")
	private Integer deviceId;

	@Lob
	private String description;

	@Column(name="device_name")
	private String deviceName;
	
	@Column(name="ip")
	private String ip;
	@Column(name="port")
	private Integer port;
	@Column(name="state")
	private Integer state;

	//bi-directional many-to-one association to Data
//	@OneToMany(mappedBy="device")
//	private List<Data> data;

	//bi-directional many-to-one association to Room
	@ManyToOne
	@JoinColumn(name="room_id")
	private Room room;

	public Device() {
	}

	public Integer getDeviceId() {
		return this.deviceId;
	}

	public void setDeviceId(Integer deviceId) {
		this.deviceId = deviceId;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDeviceName() {
		return this.deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Integer getState() {
		return this.state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

//	public List<Data> getData() {
//		return this.data;
//	}
//
//	public void setData(List<Data> data) {
//		this.data = data;
//	}
//
//	public Data addData(Data data) {
//		getData().add(data);
//		data.setDevice(this);
//
//		return data;
//	}
//
//	public Data removeData(Data data) {
//		getData().remove(data);
//		data.setDevice(null);
//
//		return data;
//	}

	public Room getRoom() {
		return this.room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

}