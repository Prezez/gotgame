package com.sda.javagda21.gotgame.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Map {

    public static final Integer MAX_SIZE = 4;
    public static final Integer MIN_SIZE = 1;

    private Field[][] field;

    public Map(Field[][] field) {
        this.field = field;
    }

    public Integer getFieldNumberFromFieldPosition(Integer verticalPosition, Integer horizontalPostion) {
        return verticalPosition * MAX_SIZE + horizontalPostion;
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

        for (int i = 0; i < MAX_SIZE; i++) {
            for (int j = 0; j < MAX_SIZE; j++) {
                Field newField = new Field(i * MAX_SIZE + j, null);
                field[i][j] = newField;
                System.out.println(newField);
            }
        }
        System.out.println(map);

        return map;
    }

    @Override
    public String toString() {
        return "Map{" +
                "field=" + Arrays.toString(field) +
                '}';
    }
}
