package com.websocketL.websocket.resource;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.websocketL.websocket.entity.Sensor;
import com.websocketL.websocket.service.SensorService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class SensorResource {

	@Autowired
	private final SensorService sensorService;

	@GetMapping(value = "/sensor", produces = "application/json")
	public List<Sensor> getSensors() {
		return sensorService.getSensors();
	}

	@GetMapping(value = "/sensor/{id}", produces = "application/json")
	public Optional<Sensor> getSensorById(@PathVariable("id") Long id) {
		return sensorService.getSensorByid(id);
	}

}
