package com.arduino.dmo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
@NamedQuery(name="Control.findAll", query="SELECT c FROM Control c")
public class Control implements Serializable{
		private static final long serialVersionUID = 1L;
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		private Integer id;
		@Column(name="name")
		private String name;
		
		@Column(name="mode")
		private String mode;
		@Column(name="temperature")
		private String temperature;
		@Column(name="wind_direction")
		private String windDirection;
		@Temporal(TemporalType.TIMESTAMP)
		private Date date;
		@Lob
		private String code;

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		//bi-directional many-to-one association to Device
		@ManyToOne
		@JoinColumn(name="control_device")
		private Device device;

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getMode() {
			return mode;
		}

		public void setMode(String mode) {
			this.mode = mode;
		}

		public String getTemperature() {
			return temperature;
		}

		public void setTemperature(String temperature) {
			this.temperature = temperature;
		}

		public String getWindDirection() {
			return windDirection;
		}

		public void setWindDirection(String windDirection) {
			this.windDirection = windDirection;
		}
		public Date getDate() {
			return date;
		}
		
		public void setDate(Date date) {
			this.date = date;
		}
		public Device getDevice() {
			return device;
		}

		public void setDevice(Device device) {
			this.device = device;
		}
		
		public Control() {
		}


}
