package com.websocketL.websocket.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.websocketL.websocket.entity.Sensor;
import com.websocketL.websocket.repository.WebSocketRepository;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Service
@AllArgsConstructor
@Getter
@Setter
public class SensorServiceImpl implements SensorService {

	private final WebSocketRepository webSocketRepository;

	@Override
	public List<Sensor> getSensors() {
		return webSocketRepository.findAll();
	}

	@Override
	public Optional<Sensor> getSensorByid(Long id) {
		return webSocketRepository.findById(id);
	}

	@Override
	public Sensor addSensor(Sensor sensor) {
		webSocketRepository.save(sensor);
		return sensor;
	}

}
