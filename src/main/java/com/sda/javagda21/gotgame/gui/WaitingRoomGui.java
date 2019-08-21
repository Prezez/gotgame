package com.sda.javagda21.gotgame.gui;


import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;



@Route("waiting-room")
public class WaitingRoomGui extends VerticalLayout {


    public WaitingRoomGui() {
        Label namePlayer1 = new Label("Enter name Player 1");
        TextField textPlayer1 = new TextField();
        Label namePlayer2 = new Label("Enter name Player 2");
        TextField textPlayer2 = new TextField();

        Button button = new Button("Start Game");

        add(namePlayer1, textPlayer1, namePlayer2, textPlayer2, button);
    }
}
