package com.websocketL.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class MyWebSocketHandler extends TextWebSocketHandler {

	private static final Logger logger = LoggerFactory.getLogger(MyWebSocketHandler.class);

	@Override
	protected void handleTextMessage(WebSocketSession webSocketSession, TextMessage message) throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			String payload = new String(message.asBytes());
			JsonNode jsonNode = objectMapper.readTree(payload);

			String sensorType = jsonNode.get("sensorType").asText();
			int sensorValue = jsonNode.get("sensorValue").asInt();

			logger.info("sensorType: {}, sensorValue: {}", sensorType, sensorValue);

		} catch (Exception e) {
			logger.error("Exception during message handling", e);
		}
	}

}
