package com.sda.javagda21.gotgame.controller;

import com.sda.javagda21.gotgame.model.Message;
import com.sda.javagda21.gotgame.service.HistoryMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ChatController {

    @Autowired
    public ChatController(HistoryMessageService historyMessageService) {
        this.historyMessageService = historyMessageService;
    } // konstruktor

    private HistoryMessageService historyMessageService; //lista wiadomości

    @GetMapping("/sendMessage")
    public String sendMessage(Model model) {
        model.addAttribute("message", new Message());
        return "sendMessage";
    } // wysyłanie wiadomości

    @PostMapping("/chatRegister")
    public String addMessageForm(@ModelAttribute Message message, Model model) {
        historyMessageService.addMessage(message.getValue());
        historyMessageService.addMessage(message.getUser().getUsername());
        return "sendMessage";
    } //odbieranie wiadomości


    @GetMapping("/chatRegister")
    public String addMessageForm(Model model) {
        model.addAttribute("addMessageForm", historyMessageService.getRegistreMessage());
        return "chatRegister";
    }

}
