package com.sda.javagda21.gotgame.gui;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("hot-seat")
public class HotSeatGui extends VerticalLayout {


    public HotSeatGui() {
//        Label playerOneLabel = new Label();
//        Label playerTwoLabel = new Label();
        Button startGameButton = new Button();
        TextField playerOne = new TextField();
        TextField playerTwo = new TextField();
        String playerOneName;
        String playerTwoName;

//        playerOneLabel.setText("Player 1 Name");
//        playerTwoLabel.setText("Player 2 Name");
        playerOne.setLabel("Enter Green Player Name");
        playerTwo.setLabel("Enter Red Player Name");
        startGameButton.setText("Start!");
        playerOneName = playerOne.getValue();
        playerTwoName = playerTwo.getValue();
        startGameButton.addClickListener(click -> {


        });

//        add(playerOneLabel);
        add(playerOne);
//        add(playerTwoLabel);
        add(playerTwo);
        add(startGameButton);

    }
}
