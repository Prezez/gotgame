package com.sda.javagda21.gotgame.gui;


import com.sda.javagda21.gotgame.config.AppUser;
import com.sda.javagda21.gotgame.repository.AppUserRepo;
import com.sda.javagda21.gotgame.repository.PlayerRepository;
import com.sda.javagda21.gotgame.service.PlayerService;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;


@Route("waiting-room")
@StyleSheet("../frontend/styles/stylesVd.css")
public class WaitingRoomGui extends VerticalLayout {

    private AppUser appUser;


    public WaitingRoomGui() {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }


        Label namePlayer1 = new Label("Enter Your Name:");
        TextField textPlayer1 = new TextField();
        Label namePlayer2 = new Label("Waiting for second Player: ");
        TextField textPlayer2 = new TextField();





//        Stream<Integer> stream = Stream.iterate(0, i -> i + 1);
//        stream.reduce((first, second) -> second).orElse(null);

        Button button = new Button("Start Game");
        Button button2 = new Button("Refresh");
        button2.addClickListener(clickEvent -> UI.getCurrent().getPage().reload());

            button.addClickListener(e -> {
                if (!textPlayer1.getValue().equals("") && !textPlayer2.getValue().equals("")) {
                    button.getUI().ifPresent(ui -> ui.navigate("game"));
                }
            });



        add(namePlayer1, textPlayer1, namePlayer2, textPlayer2, button2, button);
    }


}
