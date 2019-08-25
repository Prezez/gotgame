package com.sda.javagda21.gotgame.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExitController {
    @GetMapping("/exit")
    public String exitPage() {
        return "exitPage";

    }
}
