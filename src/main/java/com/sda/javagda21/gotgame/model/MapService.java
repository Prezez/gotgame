package com.sda.javagda21.gotgame.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Component
public class MapService {


    public static final Integer MAX_SIZE = 4;
    public static final Integer MIN_SIZE = 1;

    private Map currentMap;

    private Map createNewMap() {
        Field field[][] = new Field[MAX_SIZE][MAX_SIZE];
        Map map = new Map(field);
        Random random = new Random();
        Player player = new Player();
        player.setName("neutral");

        for (int i = 0; i < MAX_SIZE; i++) {
            for (int j = 0; j < MAX_SIZE; j++) {
                Integer warriorNo = random.nextInt(20) + 30;
                Field newField = new Field(i * MAX_SIZE + j, player, warriorNo);
                field[i][j] = newField;
//                System.out.println(newField);
            }
        }
//        System.out.println(map);

        return map;
    }

    public Map loadMap() {

        if (currentMap == null) {
            currentMap = createNewMap();
        }
        return currentMap;

    }

    public void save(Map map) {
        this.currentMap = map;
    }
}
