package com.sda.javagda21.gotgame.gui;


import com.sda.javagda21.gotgame.model.Field;
import com.sda.javagda21.gotgame.model.Map;
import com.sda.javagda21.gotgame.model.MapService;
import com.sda.javagda21.gotgame.model.Player;
import com.sda.javagda21.gotgame.service.FieldsService;
import com.sda.javagda21.gotgame.service.PlayerService;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Page;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Route("game")
public class GameGui extends VerticalLayout {

    Map map ;
    Grid<Field> grid = new Grid(Field.class);
    Label resultLabel = new Label();
    List<Field> fieldList = new ArrayList<>();
    Player activePlayer;
    HorizontalLayout hl = new HorizontalLayout();


    @Autowired
    public GameGui(MapService mapService, FieldsService fieldsService, PlayerService playerService) {

        map = mapService.loadMap();

        // TODO: jak zainicjować mapę przed powstaniem GUI
        // TODO: jak dodać graczy i przypisać ich do pól

//        Map map = Map.createNewMap();
        Field[][] fields = map.getFields();
        Player player1 = new Player();
        player1.setName("Dave");
        player1.setGold(10);
        player1.setTurn(1);
        player1.setArmy(80);
        Player player2 = new Player();
        player2.setName("John");
        player2.setGold(10);
        player2.setTurn(1);
        player2.setArmy(50);

        fields[0][0].setOwner(player1);
        fields[0][0].setWarriorNo(player1.getArmy());
        fields[fields.length - 1][fields[fields.length - 1].length - 1].setOwner(player2);
        fields[fields.length - 1][fields[fields.length - 1].length - 1].setWarriorNo(player2.getArmy());


        activePlayer = playerService.getActivePlayer(player1, player2);

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

                if (fieldsService.checkIfSurroundingFieldsHasAnOwner(activePlayer, fields[i][j], map)) {
                    Button button = new Button();
                    Field field = fields[i][j];
                    button.setText("Atakuj");
                    button.setMinWidth("100px");
                    button.setMinHeight("100px");
                    button.setId(String.valueOf(fields[i][j].getFieldNo()));
//                    int fieldArmySize = fields[i][j].getWarriorNo();
                    button.addClickListener(buttonClickEvent -> {
                        int[] resultTable = playerService.fight(activePlayer.getArmy(), field.getWarriorNo());
                        if (resultTable[0] > resultTable[1]) {
                            field.getOwner().setArmy(resultTable[1]);
                            field.setOwner(activePlayer);
                            field.setWarriorNo(resultTable[0]);
                            activePlayer.setArmy(resultTable[0]);
                            resultLabel.setText("Winner is: " + activePlayer.getName());
                            fieldList = updateFieldList(fieldList);
                            grid.setItems(fieldList);
                            hl.remove(button);
                            hl.add(label);
//                            if (fieldsService.checkIfSurroundingFieldsHasAnOwner(activePlayer, field, map)) {
//                                Button button1 = new Button();
//                                button1.setText("coś");
//                                hl.add(button1);
//                            }


                        } else {
                            field.getOwner().setArmy(resultTable[1]);
                            field.setWarriorNo(resultTable[1]);
                            activePlayer.setArmy(resultTable[0]);
                            resultLabel.setText("Winner is: " + field.getOwner().getName());
                            fieldList = updateFieldList(fieldList);
                            grid.setItems(fieldList);


                        }
                        mapService.save(map);
                        UI.getCurrent().getPage().reload();
                    });

                    hl.add(button);
                } else {
                    hl.add(label);
                }
            }
            add(hl);
        }


        add(resultLabel);
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

    public List<Field> updateFieldList(List<Field> fieldListToUpdate) {
        for (Field field : fieldListToUpdate) {
            if (!field.getOwner().getName().equals("neutral")) {
                field.setWarriorNo(field.getOwner().getArmy());
            } else {
                field.setWarriorNo(field.getWarriorNo());
            }
        }
        return fieldListToUpdate;
    }

//    public Button buttonFuncionality (Field field, PlayerService playerService) {
//
//        Button button = new Button();
//        button.addClickListener(buttonClickEvent -> {
//            int[] resultTable = playerService.fight(activePlayer.getArmy(), field.getWarriorNo());
//            if (resultTable[0]>resultTable[1]){
//                field.getOwner().setArmy(resultTable[1]);
//                field.setOwner(activePlayer);
//                field.setWarriorNo(resultTable[0]);
//                activePlayer.setArmy(resultTable[0]);
//                resultLabel.setText("Winner is: " + activePlayer.getName());
//                fieldList = updateFieldList(fieldList);
//                grid.setItems(fieldList);
//                hl.remove(button);
//                hl.add(label);
//
//            } else {
//                field.getOwner().setArmy(resultTable[1]);
//                field.setWarriorNo(resultTable[1]);
//                activePlayer.setArmy(resultTable[0]);
//                resultLabel.setText("Winner is: " + field.getOwner().getName());
//                fieldList = updateFieldList(fieldList);
//            }
//        });
//
//        return button;
//    }

//    public static void refreshMap (Map map, FieldsService fieldsService, PlayerService playerService, Player activePlayer){
//        Field[][] fields = map.getFields();
//
//        for (int i = 0; i < fields.length; i++) {
//            HorizontalLayout hl = new HorizontalLayout();
//            for (int j = 0; j < fields[i].length; j++) {
//                fieldList.add(fields[i][j]);
//                Label label = new Label();
//                label.setText(String.valueOf(fields[i][j].getFieldNo()));
//                label.setMinWidth("100px");
//                label.setMinHeight("100px");
//                label.setId(String.valueOf(fields[i][j].getFieldNo()));
//                label.setClassName(fields[i][j].getOwner().getName());
//
//                if (fieldsService.checkIfSurroundingFieldsHasAnOwner(activePlayer, fields[i][j], map)) {
//                    Button button = new Button();
//                    button.setText("Atakuj");
//                    button.setMinWidth("100px");
//                    button.setMinHeight("100px");
//                    button.setId(String.valueOf(fields[i][j].getFieldNo()));
//                    Field field = fields[i][j];
////                    int fieldArmySize = fields[i][j].getWarriorNo();
//                    button.addClickListener(buttonClickEvent -> {
//                        int[] resultTable = playerService.fight(activePlayer.getArmy(), field.getWarriorNo());
//                        if (resultTable[0]>resultTable[1]){
//                            field.getOwner().setArmy(resultTable[1]);
//                            field.setOwner(activePlayer);
//                            field.setWarriorNo(resultTable[0]);
//                            activePlayer.setArmy(resultTable[0]);
//                            resultLabel.setText("Winner is: " + activePlayer.getName());
//                            grid.setItems(fieldList);
//                        } else {
//                            field.getOwner().setArmy(resultTable[1]);
//                            field.setWarriorNo(resultTable[1]);
//                            activePlayer.setArmy(resultTable[0]);
//                            resultLabel.setText("Winner is: " + field.getOwner().getName());
//                        }
//                    });
//                    hl.add(button);
//                } else {
//                    hl.add(label);
//                }
//            }
//            add(hl);
//        }
//    }


}




