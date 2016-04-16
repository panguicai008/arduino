package com.arduino.dmo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the data database table.
 * 
 */
@Entity
@NamedQuery(name="Data.findAll", query="SELECT d FROM Data d")
public class Data implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private Double humidity;

	private Double light;

	@Column(name="lm35_intake")
	private Double lm35Intake;

	@Column(name="lm35_outlet")
	private Double lm35Outlet;

	private Double pir;

	@Temporal(TemporalType.TIMESTAMP)
	private Date time;

	//bi-directional many-to-one association to Device
	@ManyToOne
	@JoinColumn(name="device_id")
	private Device device;

	public Data() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getHumidity() {
		return this.humidity;
	}

	public void setHumidity(Double humidity) {
		this.humidity = humidity;
	}

	public Double getLight() {
		return this.light;
	}

	public void setLight(Double light) {
		this.light = light;
	}

	public Double getLm35Intake() {
		return this.lm35Intake;
	}

	public void setLm35Intake(Double lm35Intake) {
		this.lm35Intake = lm35Intake;
	}

	public Double getLm35Outlet() {
		return this.lm35Outlet;
	}

	public void setLm35Outlet(Double lm35Outlet) {
		this.lm35Outlet = lm35Outlet;
	}

	public Double getPir() {
		return this.pir;
	}

	public void setPir(Double pir) {
		this.pir = pir;
	}

	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Device getDevice() {
		return this.device;
	}

	public void setDevice(Device device) {
		this.device = device;
	}

}