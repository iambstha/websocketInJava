package com.websocketL.websocket.service;

import java.util.List;
import java.util.Optional;

import com.websocketL.websocket.entity.Sensor;

public interface SensorService {
	
	public List<Sensor> getSensors();
	
	public Optional<Sensor> getSensorByid(Long id);
	
	public Sensor addSensor(Sensor sensor);

}
