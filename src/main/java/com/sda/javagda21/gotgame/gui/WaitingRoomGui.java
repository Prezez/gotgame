package com.sda.javagda21.gotgame.gui;


import com.sda.javagda21.gotgame.config.AppUser;
import com.sda.javagda21.gotgame.repository.AppUserRepo;
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

    public WaitingRoomGui(AppUserService appUserService, AppUserRepo appUserRepo) throws InterruptedException {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }

        AppUser appUser = appUserService.findByUsername(username);
        System.out.println(appUser.isActive());
        appUser.setActive(true);
        appUserRepo.save(appUser);


        List<AppUser> users = appUserService.findAll();

        System.out.println("Liczba userów " + users.size());
        System.out.println(appUser.getId());
        System.out.println(appUser.getUsername());
        System.out.println(appUser.getRole());
        System.out.println(appUser.isActive());

        List<AppUser> activeUsers = appUserService.findAllByActive(true);
        System.out.println(activeUsers.size());
        System.out.println(activeUsers.get(0).getUsername());

        Label namePlayer1 = new Label(username);
        Label namePlayer2 = new Label("Waiting for second Player! Please wait... ");
        Button refreshButton = new Button("Refresh");
        refreshButton.addClickListener(clickEvent -> UI.getCurrent().getPage().reload());
        add(namePlayer1, namePlayer2, refreshButton);

//        UI.getCurrent().setPollInterval(10000);
//
//UI.getCurrent().addPollListener(UI.getCurrent().addPollListener(pollEvent -> {}));


//        if (users.size() < 2) {
        if (activeUsers.size() < 2) {
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

