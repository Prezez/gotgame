package com.sda.javagda21.gotgame.gui;


import com.sda.javagda21.gotgame.config.AppUser;
import com.sda.javagda21.gotgame.service.AppUserService;
import com.vaadin.flow.component.PollEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.DataChangeEvent;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

@Route("waiting-room")
@StyleSheet("../frontend/styles/stylesVd.css")
public class WaitingRoomGui extends VerticalLayout {

    private AppUser appUser;

    public WaitingRoomGui(AppUserService appUserService) throws InterruptedException {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }


        List<AppUser> users = appUserService.findAll();
        System.out.println("Liczba" + users.size());

        Label namePlayer1 = new Label(username);
        Label namePlayer2 = new Label("Waiting for second Player! Please wait... ");
        Button refreshButton = new Button("Refresh");
        refreshButton.addClickListener(clickEvent -> UI.getCurrent().getPage().reload());
        add(namePlayer1, namePlayer2, refreshButton);

//        UI.getCurrent().setPollInterval(10000);
//
//UI.getCurrent().addPollListener(UI.getCurrent().addPollListener(pollEvent -> {}));
        if (users.size() < 2) {
            Thread.sleep(5000);
            UI.getCurrent().getPage().reload();
        } else {
            String playerOne = users.get(0).getUsername();
            String playerTwo = users.get(1).getUsername();
            VaadinSession.getCurrent().getSession().setAttribute("playerOne", playerOne);
            VaadinSession.getCurrent().getSession().setAttribute("playerTwo", playerTwo);
            UI.getCurrent().navigate("game");
            UI.getCurrent().getPage().reload();
        }

    }
}

