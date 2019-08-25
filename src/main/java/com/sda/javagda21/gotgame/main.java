package com.sda.javagda21.gotgame;



import com.sda.javagda21.gotgame.entity.Army;
import com.sda.javagda21.gotgame.service.PlayerService;

import java.util.Arrays;

public class main {
    public static void main(String[] args) {


        Army armyMoja = new Army(200);
        Army armyTwoja = new Army(200);


        PlayerService playerService = new PlayerService();
        System.out.println(Arrays.toString(playerService.fight(100, 100)));

//        Map map = Map.createNewMap();
//        System.out.println("------------------");
//        System.out.println(map.surroundingFields(10));

    }
}