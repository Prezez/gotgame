package com.sda.javagda21.gotgame.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChatAllController {

    @GetMapping("/chatAll")
    public String chatAll() {


        return "chatAll";
    }
}
