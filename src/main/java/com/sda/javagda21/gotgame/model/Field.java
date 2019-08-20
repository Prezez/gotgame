package com.sda.javagda21.gotgame.model;

public class Field {

    private Integer fieldNo;
    private Player owner;
    private Integer warriorNo;

    public Field(Integer fieldNo, Player owner, Integer warriorNo) {
        this.fieldNo = fieldNo;
        this.owner = owner;
        this.warriorNo = warriorNo;
    }

    public Field() {
    }

    public Integer getWarriorNo() {
        return warriorNo;
    }

    public void setWarriorNo(Integer warriorNo) {
        this.warriorNo = warriorNo;
    }

    public Integer getFieldNo() {
        return fieldNo;
    }

    public void setFieldNo(Integer fieldNo) {
        this.fieldNo = fieldNo;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Field{" +
                "fieldNo=" + fieldNo +
                ", owner=" + owner +
                ", warriorNo=" + warriorNo +
                '}';
    }
}
