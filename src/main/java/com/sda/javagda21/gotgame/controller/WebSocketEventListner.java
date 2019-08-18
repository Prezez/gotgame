package com.sda.javagda21.gotgame.controller;

import com.sda.javagda21.gotgame.model.ChatMessage;
import com.sda.javagda21.gotgame.model.MessageType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
public class WebSocketEventListner {
    public static final Logger logger = LoggerFactory.getLogger(WebSocketEventListner.class);

    @Autowired
    private SimpMessageSendingOperations messageSendingOperations;

    @EventListener
    public void handleWebSocketConnectLisner(SessionConnectedEvent event) {
        logger.info("Received a new socket connection");
    }

    @EventListener
    public void handleWebSockerDisconnectListner(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());

        String userName = (String) headerAccessor.getSessionAttributes().get("username");

        if (userName != null) {
            logger.info("Usser Disconnected " + userName);

            ChatMessage chatMessage = new ChatMessage();
            chatMessage.setType(MessageType.LEAVE);
            chatMessage.setSender(userName);

            messageSendingOperations.convertAndSend("/topic/public");
        }
    }


}
