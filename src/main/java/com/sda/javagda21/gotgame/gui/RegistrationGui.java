package com.sda.javagda21.gotgame.gui;

import com.sda.javagda21.gotgame.config.AppUser;
import com.sda.javagda21.gotgame.repository.AppUserRepo;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.cert.Extension;


@Route("registration")
public class RegistrationGui extends VerticalLayout {

    private PasswordEncoder passwordEncoder;
    TextField username = new TextField("Username:");
    TextField password = new TextField("Password");
    Button buttonHello = new Button("Registration", new Icon(VaadinIcon.ACCESSIBILITY));
    Label ok = new Label();



    @Autowired
    public RegistrationGui(PasswordEncoder passwordEncoder, AppUserRepo appUserRepo) {
        this.passwordEncoder = passwordEncoder;

        buttonHello.addClickListener(click -> {
            AppUser appUser = new AppUser(username.getValue(), passwordEncoder.encode(password.getValue()),"ROLE_USER");

        appUserRepo.save(appUser);
            UI.getCurrent().navigate("win");
            UI.getCurrent().getPage().reload();

        });
        add(username,password,buttonHello);

    }
}


