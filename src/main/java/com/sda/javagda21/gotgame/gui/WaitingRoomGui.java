package com.sda.javagda21.gotgame.gui;


import com.sda.javagda21.gotgame.config.AppUser;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;


@Route("waiting-room")
@StyleSheet("../frontend/styles/stylesVd.css")
public class WaitingRoomGui extends VerticalLayout {

    AppUser appUser = new AppUser();

    public WaitingRoomGui() {
        Label namePlayer1 = new Label("Enter Your Name:");
        TextField textPlayer1 = new TextField();
        Label namePlayer2 = new Label("Waiting for second Player: ");
        TextField textPlayer2 = new TextField();


        Button button = new Button("Start Game");
        Button button2 = new Button( "Refresh");
        button2.addClickListener(clickEvent -> UI.getCurrent().getPage().reload());
        button.addClickListener(e->{
            button.getUI().ifPresent(ui -> ui.navigate("game"));});



        add(namePlayer1, textPlayer1, namePlayer2, textPlayer2, button2, button);
    }


}
