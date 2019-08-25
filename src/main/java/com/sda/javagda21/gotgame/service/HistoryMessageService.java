package com.sda.javagda21.gotgame.service;

import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

@Service
public class HistoryMessageService {
    private Set<String> registreMessage;


    public HistoryMessageService() {
        registreMessage = new TreeSet<>();


    }

    public Set<String> getRegistreMessage() {
        return registreMessage;
    }

    public void addMessage(String message) {
        registreMessage.add(message);
    }
}
