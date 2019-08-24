package com.sda.javagda21.gotgame.model;

import org.springframework.stereotype.Component;

import javax.persistence.Entity;

//@Entity
public class Gold {

    private int currentValue;
    private final int maxTresury = 99999999;

    public Gold(int currentValue) {
        this.currentValue = currentValue;
    }

    public int getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(int currentValue) {
        this.currentValue = currentValue;
    }

    public int getMaxTresury() {
        return maxTresury;
    }
}
