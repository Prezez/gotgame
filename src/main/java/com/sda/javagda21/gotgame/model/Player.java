package com.sda.javagda21.gotgame.model;

import com.sda.javagda21.gotgame.entity.Army;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "player")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;


    private int gold;

    private int turn;
    private int army;

    public int getArmy() {
        return army;
    }

    public void setArmy(int army) {
        this.army = army;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Player)) return false;
        Player player = (Player) o;
        return getGold() == player.getGold() &&
                getTurn() == player.getTurn() &&
                getArmy() == player.getArmy() &&
                Objects.equals(getId(), player.getId()) &&
                Objects.equals(getName(), player.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getGold(), getTurn(), getArmy());
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gold=" + gold +
                ", turn=" + turn +
                ", army=" + army +
                '}';
    }
}
