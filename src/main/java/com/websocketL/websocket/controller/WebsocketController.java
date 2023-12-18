package com.websocketL.websocket.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

@Controller
@CrossOrigin("*")
public class WebSocketController {

	@MessageMapping("/send-message")
	@SendTo("/topic/receive-message")
	public String sendMessage(String message) throws InterruptedException {
		Thread.sleep(2000);
		System.out.println("Received your message: " + message);
		return "Received your message: " + message;
	}

}
