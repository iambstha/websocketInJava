package com.websocketL.websocket;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.websocketL.websocket.entity.Sensor;
import com.websocketL.websocket.repository.SensorRepository;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
@AllArgsConstructor
public class MyWebSocketHandler extends TextWebSocketHandler {

	@Autowired
	private SensorRepository sensorRepository;

	private static final Logger logger = LoggerFactory.getLogger(MyWebSocketHandler.class);

	@Override
	protected void handleTextMessage(WebSocketSession webSocketSession, TextMessage message) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			String payload = new String(message.asBytes());
			JsonNode jsonNode = objectMapper.readTree(payload);

			if (jsonNode != null && jsonNode.has("sensor_id") && jsonNode.has("sensor_data")) {
				String sensorId = jsonNode.get("sensor_id").asText();
				int sensorData = jsonNode.get("sensor_data").asInt();
				try {
					Sensor sensor = new Sensor();
					sensor.setDeviceId(sensorId);
					sensor.setSensorData(sensorData);
					sensorRepository.save(sensor);
				} catch (Exception e) {
					logger.error("Error saving sensor data to the database", e);
				}

				logger.info("Received sensor data: device_id={}, sensor_data={}", sensorId, sensorData);
				webSocketSession.sendMessage(new TextMessage("Message received and processed"));

			} else {
				logger.warn("Missing or null values in JSON payload");
				webSocketSession.sendMessage(new TextMessage("Invalid JSON payload"));
			}

		} catch (JsonProcessingException e) {
			logger.error("Error parsing JSON", e);
			webSocketSession.sendMessage(new TextMessage("Error parsing JSON"));
		} catch (IOException e) {
			logger.error("Error sending message", e);
			webSocketSession.sendMessage(new TextMessage("Error sending message"));
		} catch (Exception e) {
			logger.error("Unhandled exception during message handling", e);
			webSocketSession.sendMessage(new TextMessage("Error processing the message"));
		}
	}
}
