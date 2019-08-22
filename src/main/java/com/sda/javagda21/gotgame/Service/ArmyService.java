package com.sda.javagda21.gotgame.service;

import com.sda.javagda21.gotgame.entity.Army;

import java.util.Random;

public class ArmyService {

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
        int kills = dmg / 3;
        System.out.println("number of kills: " + kills);
        return size - kills;
    }

    public boolean fight(Army attackingArmy, Army defendingArmy) {
        int damage = 0;
        int remainingSize = 0;
        int attackingArmySize = attackingArmy.getSize();
        int defendingArmySize = defendingArmy.getSize();

        do {
            damage = (dealDmg(attackingArmy.getSize()))*3/4;
            remainingSize = takeDmg(damage, defendingArmy.getSize());
            if (remainingSize < 0) {
                remainingSize = 0;
            }
            System.out.println("remaining people attacker: " + (remainingSize));
            defendingArmy.setSize(remainingSize);

            damage = dealDmg(defendingArmy.getSize());
            remainingSize = takeDmg(damage, attackingArmy.getSize());
            if (remainingSize < 0) {
                remainingSize = 0;
            }
            System.out.println("remaining people defender: " + (remainingSize));
            attackingArmy.setSize(remainingSize);

        }
        while (attackingArmy.getSize() > 0 && defendingArmy.getSize() > 0);


        System.out.println("Attacking army lost: " + (attackingArmySize - attackingArmy.getSize()) + " people");
        System.out.println("Defending army lost: " + (defendingArmySize - defendingArmy.getSize()) + " people");
        if (attackingArmy.getSize() == 0) {
            return false;
        } else {
            return true;
        }
    }
}
