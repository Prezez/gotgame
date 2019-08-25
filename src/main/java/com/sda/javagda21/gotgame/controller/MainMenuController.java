package com.sda.javagda21.gotgame.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainMenuController {

    @GetMapping("/main-menu")
    public String mainMenu() {
        return "mainMenuPage";

    }

    @GetMapping("/redirectToWaitingRoom")
    public String redirectToWaitingRoom() {
        return "waitingRoomPage";
    }
}