package com.sda.javagda21.gotgame.Config;

import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;


@Route("A1")
public class WebSecruityConfig extends VerticalLayout {{

    TextField textField = new TextField("przedstaw się ");
    Label label = new Label();

    add(textField, label );
}
}