package com.sda.javagda21.gotgame.Config;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;


@Route("login")
public class WebSecruityConfig extends VerticalLayout {{

        TextField textField = new TextField("LOGIN ");
        Label label = new Label();
        Button button = new Button("Login ");




            add(textField, label, button);


        }}


