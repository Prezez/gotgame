package com.sda.javagda21.gotgame.model;

import java.util.ArrayList;
import java.util.List;

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

}
