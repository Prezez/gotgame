package com.sda.javagda21.gotgame.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HistoryMessageService {
    private List<String> registreMessage;


    public HistoryMessageService() {
        registreMessage = new ArrayList<>();


    }

    public List<String> getRegistreMessage() {
        return registreMessage;
    }

    public void addMessage(String message) {
//        LocalDateTime now = LocalDateTime.now();
//        DateTimeFormatter dataNow = DateTimeFormatter.ofPattern("HH:mm:ss");
//        String formatData = now.format(dataNow);
        registreMessage.add(message);
    }
}
