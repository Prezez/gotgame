package com.sda.javagda21.gotgame.gui;

import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.Route;

@Route("win")
public class WinGui extends HorizontalLayout {


    public WinGui(){
        Label label = new Label();
        label.setText("Wygrałeś!!");
        add(label);
    }
}
