package com.sda.javagda21.gotgame.service;

import com.sda.javagda21.gotgame.model.Field;
import com.sda.javagda21.gotgame.model.Player;
import com.sda.javagda21.gotgame.repository.PlayerRepository;
import org.springframework.stereotype.Component;


import java.util.List;
import java.util.Random;

import static com.sda.javagda21.gotgame.config.GameProperties.*;

@Component
public class PlayerService {

    private PlayerRepository playerRepository;
    private Player[] players;

    // read all players
    public List<Player> getPlayers() {
        return playerRepository.findAll();
    }

    // create new player
    public void addPlayer(Player p) {
        playerRepository.save(p);
    }

    // remove player
    public void removePlayer(Long id) {
        playerRepository.deleteById(id);
    }


    public int dealDmg(int size) {
        Random random = new Random();
        int damage = 0;

        for (int i = 0; i < size; i++) {
            damage = damage + random.nextInt(3);
        }
        System.out.println("dmg amount: " + damage);
        return damage;

    }


    public int takeDmg(int dmg, int size) {
        int kills = dmg / WARRIOR_HEALTH;
        System.out.println("number of kills: " + kills);
        return size - kills;
    }

    public int[] fight(int attackingArmy, int defendingArmy) {
        int damage = 0;
        int remainingSize = 0;
        int startingAttackingArmySize = attackingArmy;
        int startingDefendingArmySize = defendingArmy;
        int[] result = new int[2];

        System.out.println("Starting attacking Army: " + startingAttackingArmySize);
        System.out.println("Starting defending Army: " + startingDefendingArmySize);

        do {
            damage = (dealDmg(attackingArmy)) * 3/4;
            remainingSize = takeDmg(damage, defendingArmy);
            if (remainingSize < 0) {
                remainingSize = 0;
            }
            System.out.println("remaining people attacker: " + (remainingSize));
            defendingArmy = remainingSize;

            damage = dealDmg(defendingArmy);
            remainingSize = takeDmg(damage, attackingArmy);
            if (remainingSize < 0) {
                remainingSize = 0;
            }
            System.out.println("remaining people defender: " + (remainingSize));
            attackingArmy = remainingSize;


            System.out.println("---------------------------");
        }
        while (attackingArmy > 0 && defendingArmy > 0);


        System.out.println("Attacking army lost: " + (startingAttackingArmySize - attackingArmy) + " people");
        System.out.println("Defending army lost: " + (startingDefendingArmySize - defendingArmy) + " people");

        result[0] = attackingArmy;
        result[1] = defendingArmy;

        return result;
    }

    public Player getActivePlayer(Player player1, Player player2) {
        if (player1.getTurn() == player2.getTurn()) {
            return player1;
        } else {
            return player2;
        }
    }

    public Player[] loadStartingSetup(Field[][] fields) {


        players = new Player[2];
        players = createPlayers();


        fields[0][0].setOwner(players[0]);
        fields[0][0].setWarriorNo(players[0].getArmy());
        fields[fields.length - 1][fields[fields.length - 1].length - 1].setOwner(players[1]);
        fields[fields.length - 1][fields[fields.length - 1].length - 1].setWarriorNo(players[1].getArmy());

        return players;
    }


    private Player[] createPlayers() {
        players = new Player[2];

        Player player1 = new Player();
        player1.setName("Dave");
        player1.setGold(STARTING_GOLD);
        player1.setTurn(1);
        player1.setArmy(STARTING_ARMY);

        Player player2 = new Player();
        player2.setName("John");
        player2.setGold(STARTING_GOLD);
        player2.setTurn(1);
        player2.setArmy(STARTING_ARMY);

        players[0] = player1;
        players[1] = player2;

        return players;

    }

    public Player[] loadPlayers(Field[][] fields) {
        if (players == null) {
            players = loadStartingSetup(fields);
        }

        return players;
    }


    public void updateGoldAmount(Player player, Integer numberOfFieldsOwned){
        int goldOwned = player.getGold();
        player.setGold(goldOwned + numberOfFieldsOwned*GOLD_INCREASE);
    }


}
