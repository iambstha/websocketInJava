package com.websocketL.websocket;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class MyWebSocketHandler extends TextWebSocketHandler {
	
    @Override
    protected void handleTextMessage(WebSocketSession webSocketSession, TextMessage message) throws Exception {
        String payload = new String(message.asBytes());
        System.out.println("Received: " + payload);
        webSocketSession.sendMessage(new TextMessage(payload));
    }

}
