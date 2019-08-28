package com.sda.javagda21.gotgame.service;

import com.sda.javagda21.gotgame.model.Field;
import com.sda.javagda21.gotgame.model.Map;
import com.sda.javagda21.gotgame.model.Player;
import org.springframework.stereotype.Component;

import java.util.Random;

import static com.sda.javagda21.gotgame.config.GameProperties.MAX_SIZE;

@Component
public class MapService {


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
            }
        }


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

    public Integer numberOfFieldsOwned(Player player) {
        Field[][] fields = currentMap.getFields();
        Integer result = 0;

        for (int i = 0; i < fields.length; i++) {
            for (int j = 0; j < fields[i].length; j++) {
                Field currentField = fields[i][j];
                if (currentField.getOwner().getName().equals(player.getName())) {
                    result += 1;
                }
            }
        }

        System.out.println(player.getName() + " ma " + result);
        return result;
    }

    public Integer numberOfFields() {
        Integer counter = 0;

        Field[][] fields = currentMap.getFields();

        for (int i = 0; i < fields.length; i++) {
            for (int j = 0; j < fields[i].length; j++) {
                Field currentField = fields[i][j];
                counter++;
            }
        }
        return counter;
    }

}

