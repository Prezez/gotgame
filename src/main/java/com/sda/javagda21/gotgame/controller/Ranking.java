package com.sda.javagda21.gotgame.controller;

import com.sda.javagda21.gotgame.model.Player;
import com.sda.javagda21.gotgame.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Ranking {

    @Autowired
    private PlayerService playerService;

    @GetMapping("/ranking")
    public String getBookList(Model model) {
        model.addAttribute("ranking", playerService.getPlayers());

        return "rankingPage";
    }

    @GetMapping("/player/add")
    public String addPlayerForm() {
        return "playerFormPage";
    }

    @PostMapping("/player/add")
    public String addPlayer(Model model) {
        model.addAttribute("player", new Player());

        return "redirect:/playerFormPage";
    }

    @GetMapping("/player/remove")
    public String removePlayer(@RequestParam(name = "playerId") Long id) {
        playerService.removePlayer(id);

        return "redirect:/ranking";
    }


}
