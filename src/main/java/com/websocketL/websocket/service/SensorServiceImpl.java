package com.websocketL.websocket.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.websocketL.websocket.entity.Sensor;
import com.websocketL.websocket.repository.SensorRepository;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Service
@AllArgsConstructor
@Getter
@Setter
public class SensorServiceImpl implements SensorService {

	private final SensorRepository sensorRepository;

	@Override
	public List<Sensor> getSensors() {
		return sensorRepository.findAll();
	}

	@Override
	public Optional<Sensor> getSensorByid(Long id) {
		
		return sensorRepository.findById(id);
	}

	@Override
	public Sensor addSensor(Sensor sensor) {
		sensorRepository.save(sensor);
		return sensor;
	}

}
