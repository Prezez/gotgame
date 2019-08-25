package com.sda.javagda21.gotgame.config;

import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;


@Route("AAA")
public class WebSecruityConfig extends VerticalLayout {{

    TextField textField = new TextField("przedstaw się ");
    Label label = new Label();

    add(textField, label );
}
}