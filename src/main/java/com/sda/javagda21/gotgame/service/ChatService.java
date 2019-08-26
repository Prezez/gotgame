package com.sda.javagda21.gotgame.service;

import com.sda.javagda21.gotgame.gui.GetMessageGui;
import com.sda.javagda21.gotgame.gui.SendMessageGui;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatService {


    private GetMessageGui getMessageGui;
    private SendMessageGui sendMessageGui;

    @Autowired
    public ChatService(GetMessageGui getMessageGui, SendMessageGui sendMessageGui) {
        this.getMessageGui = getMessageGui;
        this.sendMessageGui = sendMessageGui;
    }
}
