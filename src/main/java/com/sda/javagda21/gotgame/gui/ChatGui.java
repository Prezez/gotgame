package com.sda.javagda21.gotgame.gui;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.*;

@Route("chat")
@Component
public class ChatGui extends VerticalLayout {

    @Autowired
    public ChatGui(GetMessageGui getMessageGui, SendMessageGui sendMessageGui) {

        Label labelChat = new Label();
        labelChat.setText("Witaj na chacie: ");

    }
}
