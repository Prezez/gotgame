package com.sda.javagda21.gotgame;

import com.sda.javagda21.gotgame.Service.ArmyService;
import com.sda.javagda21.gotgame.model.Army;

public class main {
    public static void main(String[] args) {


        Army armyMoja = new Army(200);
        Army armyTwoja = new Army(200);


        ArmyService armyService = new ArmyService();
        System.out.println(armyService.fight(armyMoja, armyTwoja));
    }
}