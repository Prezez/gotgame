package com.sda.javagda21.gotgame.model;

import com.sda.javagda21.gotgame.entity.Army;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;


    private int gold;

  //  private Army army;
    private int turn;
}
