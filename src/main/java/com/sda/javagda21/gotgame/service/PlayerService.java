package com.sda.javagda21.gotgame.service;

import com.sda.javagda21.gotgame.model.Player;
import com.sda.javagda21.gotgame.repository.PlayerRepository;

import java.util.List;

public class PlayerService {

    private PlayerRepository playerRepository;

    // read all players
    public List<Player> getPlayers() {
        return playerRepository.findAll();
    }

    // create new player
    public void addPlayer (Player p) {
        playerRepository.save(p);
    }

    // remove player

    public void removePlayer(Long id) {
        playerRepository.deleteById(id);
    }
}
