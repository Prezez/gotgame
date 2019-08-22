package com.sda.javagda21.gotgame;

import com.sda.javagda21.gotgame.model.Map;
import com.sda.javagda21.gotgame.entity.Army;
import com.sda.javagda21.gotgame.service.ArmyService;

import java.util.Arrays;

public class main {
    public static void main(String[] args) {


        Army armyMoja = new Army(200);
        Army armyTwoja = new Army(200);


        ArmyService armyService = new ArmyService();
        System.out.println(armyService.fight(armyMoja, armyTwoja));

        Map map = Map.createNewMap();
        System.out.println("------------------");
        System.out.println(map.surroundingFields(10));

    }
}