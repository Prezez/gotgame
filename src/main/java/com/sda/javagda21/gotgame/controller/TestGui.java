package com.sda.javagda21.gotgame.controller;


import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestGui {


    @GetMapping("/test1")
    public String getTest1() {
        return "test1";
    }


    @GetMapping("/test2")
    public String getTest2() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }


    @GetMapping("/test3")
    public String getTest3() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
