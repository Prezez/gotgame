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
            damage = (dealDmg(attackingArmy)) * 3 / 4;
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

    public void loadStartingSetup(Field[][] fields) {


        fields[0][0].setOwner(players[0]);
        fields[0][0].setWarriorNo(players[0].getArmy());
        fields[fields.length - 1][fields[fields.length - 1].length - 1].setOwner(players[1]);
        fields[fields.length - 1][fields[fields.length - 1].length - 1].setWarriorNo(players[1].getArmy());

    }


    public Player[] createPlayers(String playerOneName, String playerTwoName) {
        players = new Player[2];

        Player player1 = setupStartingPlayerParameters(playerOneName);
        Player player2 = setupStartingPlayerParameters(playerTwoName);

        players[0] = player1;
        players[1] = player2;

        return players;

    }

    private Player setupStartingPlayerParameters(String playerName) {
        Player player = new Player();
        player.setName(playerName);
        player.setGold(STARTING_GOLD);
        player.setTurn(1);
        player.setArmy(STARTING_ARMY);

        return player;
    }

    public Player[] loadPlayers(Field[][] fields, String playerOneName, String playerTwoName) {
        if (players == null) {
            if (!playerOneName.equals("null") && !playerTwoName.equals("null")) {
                players = createPlayers(playerOneName, playerTwoName);
            } else {
                players = createPlayers("Green", "Red");
            }
        }


        if (fields[0][0].getOwner().getName().equals("neutral")) {
            loadStartingSetup(fields);
        }

        return players;
    }


    public void increaseGoldAmount(Player player, Integer numberOfFieldsOwned) {
        int goldOwned = player.getGold();
        player.setGold(goldOwned + numberOfFieldsOwned * GOLD_INCREASE);
    }

    public boolean buyWarriors(int warriorAmount, Player player) {
        int amountToBePaid = warriorAmount * WARRIOR_COST;
        boolean enoughGold = spendGold(player, amountToBePaid);
        if (enoughGold && warriorAmount>0) {
            updateWarriorAmount(warriorAmount, player);
            return true;
        }
        return false;
    }

    private void updateWarriorAmount(int warriorAmount, Player player) {
        int currentArmy = player.getArmy();
        player.setArmy(currentArmy + warriorAmount);
    }

    private boolean spendGold(Player player, Integer amount) {
        int currentGold = player.getGold();
        if (currentGold < amount) {
            return false;
        } else {
            player.setGold(currentGold - amount);
            return true;
        }
    }


    public Player[] resetPlayers(String playerOneName, String playerTwoName) {
        if (!playerOneName.equals("null") && !playerTwoName.equals("null")) {
            players = createPlayers(playerOneName, playerTwoName);
        } else {
            players = createPlayers("Green", "Red");
        }
        return players;
    }
}
