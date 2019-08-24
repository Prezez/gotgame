package com.sda.javagda21.gotgame.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ChatController {

    @RequestMapping(path = "/messages123", method = RequestMethod.GET)
    public String getChat() {
        return "chatPagesForm";
    }


}
