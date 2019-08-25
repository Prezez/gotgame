package com.sda.javagda21.gotgame.controller;

import com.sda.javagda21.gotgame.model.Message;
import com.sda.javagda21.gotgame.service.ChatService;
import com.sda.javagda21.gotgame.service.HistoryMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ChatController {
//    @Autowired
    private ChatService chatService;
//    @Autowired
    private HistoryMessageService historyMessageService;

    @GetMapping("/message")
    public String getChat(Model model) {
        model.addAttribute("chat",historyMessageService.getRegistreMessage() );
        return "chatPages";
    }

//    @PostMapping("/message")
//    public String addMessage(@RequestParam(name = "message") String message) {
//
//        Message newMessage = new Message(message);
//        historyMessageService.addMessage(newMessage);
//
//        return "chatPages";
//    }


}
