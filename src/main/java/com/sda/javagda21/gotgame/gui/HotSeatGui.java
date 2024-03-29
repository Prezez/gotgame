package com.sda.javagda21.gotgame.gui;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

@Route("hot-seat")
public class HotSeatGui extends VerticalLayout {


    public HotSeatGui() {
        Button startGameButton = new Button();
        TextField playerOne = new TextField();
        TextField playerTwo = new TextField();
        String playerOneName;
        String playerTwoName;

        playerOne.setLabel("Enter Green Player Name");
        playerTwo.setLabel("Enter Red Player Name");
        startGameButton.setText("Start!");

        playerOneName = playerOne.getValue();
        playerTwoName = playerTwo.getValue();
        startGameButton.addClickListener(click -> {

            if (playerOne.getValue().equals("") || playerTwo.getValue().equals("")) {
                Notification.show("Name cannot be empty!", 10000, Notification.Position.MIDDLE);
            } else {
                if (!playerOne.getValue().equals(playerTwo.getValue())) {
                    VaadinSession.getCurrent().getSession().setAttribute("playerOne", playerOne.getValue());
                    VaadinSession.getCurrent().getSession().setAttribute("playerTwo", playerTwo.getValue());
                    getUI().ifPresent(ui -> ui.navigate("game"));
                } else {
                    Notification.show("Names cannot be the same!", 10000, Notification.Position.MIDDLE);
                }
            }
        });

        add(playerOne);
        add(playerTwo);
        add(startGameButton);


    }
}
