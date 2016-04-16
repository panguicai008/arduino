package com.arduino.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arduino.dmo.Control;

public interface ControlDao extends JpaRepository<Control,Integer> {
	public Control findByNameAndModeAndTemperatureAndWindDirection(String name,String mode,String temperature,String windDirection);

}
