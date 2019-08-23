package com.sda.javagda21.gotgame.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Component
public class Map {

    public static final Integer MAX_SIZE = 4;
    public static final Integer MIN_SIZE = 1;

    private Field[][] fields;

    public Map() {
    }

    public Map(Field[][] fields) {
        this.fields = fields;
    }

    public Field[][] getFields() {
        return fields;
    }

    public void setFields(Field[][] fields) {
        this.fields = fields;
    }

    public Integer getFieldNumberFromFieldPosition(Integer verticalPosition, Integer horizontalPostion) {
        return verticalPosition * MAX_SIZE + horizontalPostion;
    }

    public Integer[] getFieldPositionFromFieldNumber (Integer fieldNo){
        Integer[] position = new Integer[2];
        position[0]=fieldNo/MAX_SIZE;
        position[1]=Math.floorMod(fieldNo,MAX_SIZE);
        return position;
    }

    public List<Integer> surroundingFields(Integer fieldNo) {
        Integer verticalPosition;
        Integer horizontalPostion;
        List<Integer> surroundingFieldsList = new ArrayList<>();

        verticalPosition = fieldNo / MAX_SIZE;
        horizontalPostion = Math.floorMod(fieldNo, MAX_SIZE);
        System.out.println(verticalPosition + ", " + horizontalPostion);

        if (verticalPosition != (MAX_SIZE - 1)) {
            Integer upperField = getFieldNumberFromFieldPosition(verticalPosition + 1, horizontalPostion);
            surroundingFieldsList.add(upperField);
        }

        if (verticalPosition != (MIN_SIZE - 1)) {
            Integer lowerField = getFieldNumberFromFieldPosition(verticalPosition - 1, horizontalPostion);
            surroundingFieldsList.add(lowerField);
        }

        if (horizontalPostion != (MAX_SIZE - 1)) {
            Integer rightField = getFieldNumberFromFieldPosition(verticalPosition, horizontalPostion + 1);
            surroundingFieldsList.add(rightField);
        }

        if (horizontalPostion != (MIN_SIZE - 1)) {
            Integer leftField = getFieldNumberFromFieldPosition(verticalPosition, horizontalPostion - 1);
            surroundingFieldsList.add(leftField);
        }

        return surroundingFieldsList;
    }

    public static Map createNewMap() {
        Integer size = MAX_SIZE;
        Field field[][] = new Field[MAX_SIZE][MAX_SIZE];
        Map map = new Map(field);
        Random random = new Random();
        Player player = new Player();
        player.setName("neutral");

        for (int i = 0; i < MAX_SIZE; i++) {
            for (int j = 0; j < MAX_SIZE; j++) {
                Integer warriorNo = random.nextInt(20)+30;
                Field newField = new Field(i * MAX_SIZE + j, player, warriorNo);
                field[i][j] = newField;
//                System.out.println(newField);
            }
        }
//        System.out.println(map);

        return map;
    }

    @Override
    public String toString() {
        return "Map{" +
                "fields=" + Arrays.toString(fields) +
                '}';
    }
}
