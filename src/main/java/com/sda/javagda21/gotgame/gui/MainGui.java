package com.sda.javagda21.gotgame.gui;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("main-menu")
public class MainGui extends VerticalLayout {

    public MainGui() {
        Label label = new Label("MAIN MENU");
        label.setText("MAIN MENU");
        Button start = new Button("START NEW GAME");
        Button options = new Button("OPTIONS");
        Button ranking = new Button("RANKING");
        Button exit = new Button("EXIT");

        start.addClickListener( e-> {
            start.getUI().ifPresent(ui -> ui.navigate("game"));

                });
        add(label, start, options, ranking, exit);

}
}
