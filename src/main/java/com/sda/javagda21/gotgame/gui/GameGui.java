package com.sda.javagda21.gotgame.gui;


import com.sda.javagda21.gotgame.entity.Army;
import com.sda.javagda21.gotgame.model.Field;
import com.sda.javagda21.gotgame.model.Map;
import com.sda.javagda21.gotgame.model.Player;
import com.sda.javagda21.gotgame.service.ArmyService;
import com.sda.javagda21.gotgame.service.FieldsService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Route("game")
public class GameGui extends VerticalLayout {

    Map map;
    Grid<Field> grid = new Grid(Field.class);
    List<Field> fieldList = new ArrayList<>();


    public GameGui(Map map, FieldsService fieldsService) {


        // TODO: jak zainicjować mapę przed powstaniem GUI
        // TODO: jak dodać graczy i przypisać ich do pól
        map = Map.createNewMap();
        Field[][] fields = map.getFields();
        Player player1 = new Player();
        player1.setName("Dave");
        player1.setGold(10);
        player1.setTurn(1);
        player1.setArmy(100);
        Player player2 = new Player();
        player2.setName("John");
        player2.setGold(10);
        player2.setTurn(1);
        player2.setArmy(50);


        fields[0][0].setOwner(player1);
        fields[0][0].setWarriorNo(player1.getArmy());
        fields[0][1].setOwner(player1);
        fields[0][1].setWarriorNo(player1.getArmy());
        fields[0][2].setOwner(player1);
        fields[0][2].setWarriorNo(player1.getArmy());
        fields[fields.length - 1][fields[fields.length - 1].length - 1].setOwner(player2);
        fields[fields.length - 1][fields[fields.length - 1].length - 1].setWarriorNo(player2.getArmy());


//        System.out.println(Arrays.toString(fields));
        for (int i = 0; i < fields.length; i++) {
            HorizontalLayout hl = new HorizontalLayout();
            for (int j = 0; j < fields[i].length; j++) {
                fieldList.add(fields[i][j]);
                Label label = new Label();
                label.setText(String.valueOf(fields[i][j].getFieldNo()));
                label.setMinWidth("100px");
                label.setMinHeight("100px");
                label.setId(String.valueOf(fields[i][j].getFieldNo()));
                label.setClassName(fields[i][j].getOwner().getName());

                if (fieldsService.checkIfSurroundingFieldsHasAnOwner(player1, fields[i][j], map)) {
                    Button button = new Button();
                    button.setText("Atakuj");
                    button.setMinWidth("100px");
                    button.setMinHeight("100px");
                    button.setId(String.valueOf(fields[i][j].getFieldNo()));
                    hl.add(button);
                } else {
                    hl.add(label);
                }
            }
            add(hl);
        }

        grid.setItems(fieldList);
        add(grid);

//        grid.setItems(fields[i]);
//        System.out.println(Arrays.toString(fields[i]));
//        System.out.println(fields[i][i].getFieldNo());
//        Label label = new Label();
//        label.setText(String.valueOf(fields[i][i].getFieldNo()) + fields[i][i].getOwner());
//        add(label);

//        for (Field[] field : fields) {
//            grid.setItems(field);
//            System.out.println(field);
//
//        }

    }


}




