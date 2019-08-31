package com.sda.javagda21.gotgame.gui;

import com.sda.javagda21.gotgame.config.AppUser;
import com.sda.javagda21.gotgame.repository.AppUserRepo;
import com.sda.javagda21.gotgame.service.AppUserService;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.Route;

import java.util.List;

@Route("win")
public class WinGui extends HorizontalLayout {


    public WinGui(AppUserService appUserService, AppUserRepo appUserRepo){
        Label label = new Label();
        label.setText("Wygrałeś!!");
        add(label);

        List<AppUser> activeUsers = appUserService.findAllByActive(true);

        for (AppUser user : activeUsers) {
            AppUser inactiveUser = user;
            inactiveUser.setActive(false);
            appUserRepo.save(inactiveUser);
        }

    }
}
