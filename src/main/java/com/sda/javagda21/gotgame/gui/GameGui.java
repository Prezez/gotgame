package com.sda.javagda21.gotgame.gui;


import com.sda.javagda21.gotgame.model.Field;
import com.sda.javagda21.gotgame.model.Map;
import com.sda.javagda21.gotgame.service.GameService;
import com.sda.javagda21.gotgame.service.MapService;
import com.sda.javagda21.gotgame.model.Player;
import com.sda.javagda21.gotgame.service.FieldsService;
import com.sda.javagda21.gotgame.service.PlayerService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Route("game")
public class GameGui extends VerticalLayout {

    Map map;
    Grid<Field> grid = new Grid(Field.class);
    Label resultLabel = new Label();
    List<Field> fieldList = new ArrayList<>();
    Player activePlayer;


    @Autowired
    public GameGui(MapService mapService, FieldsService fieldsService, PlayerService playerService, GameService gameService) {

        map = mapService.loadMap();

        Field[][] fields = map.getFields();

        String playerOneName = String.valueOf(VaadinSession.getCurrent().getSession().getAttribute("playerOne"));
        String playerTwoName = String.valueOf(VaadinSession.getCurrent().getSession().getAttribute("playerTwo"));

        Player[] players = playerService.loadPlayers(fields, playerOneName, playerTwoName);

        activePlayer = playerService.getActivePlayer(players[0], players[1]);

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
                    button.addClickListener(buttonClickEvent -> {
                        int[] resultTable = playerService.fight(activePlayer.getArmy(), field.getWarriorNo());
                        if (resultTable[0] > resultTable[1]) {
                            field.getOwner().setArmy(resultTable[1]);
                            field.setOwner(activePlayer);
                            field.setWarriorNo(resultTable[0]);
                            activePlayer.setArmy(resultTable[0]);
                            resultLabel.setText("Winner is: " + activePlayer.getName());
                            fieldList = gameService.updateFieldList(fieldList);
                            grid.setItems(fieldList);

                        } else {
                            field.getOwner().setArmy(resultTable[1]);
                            field.setWarriorNo(resultTable[1]);
                            activePlayer.setArmy(resultTable[0]);
                            resultLabel.setText("Winner is: " + field.getOwner().getName());
                            fieldList = gameService.updateFieldList(fieldList);
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

        HorizontalLayout buttonOptionsLayout = new HorizontalLayout();
        Button endTurnButton = new Button();
        endTurnButton.setText("End Turn");
        endTurnButton.setMinHeight("100px");
        endTurnButton.setMinWidth("200px");
        endTurnButton.addClickListener(click -> {
            int activePlayerTurnNo = activePlayer.getTurn();
            activePlayer.setTurn(activePlayerTurnNo + 1);
            Integer numberOfFieldsOwned = mapService.numberOfFieldsOwned(activePlayer);
            playerService.updateGoldAmount(activePlayer, numberOfFieldsOwned);
            mapService.save(map);

            UI.getCurrent().getPage().reload();
        });

        Button buyWarriorsButton = new Button();
        buyWarriorsButton.setText("Buy Warriors");
        buyWarriorsButton.setMinHeight("100px");
        buyWarriorsButton.setMinWidth("200px");

        buttonOptionsLayout.add(endTurnButton);
        buttonOptionsLayout.add(buyWarriorsButton);
        add(buttonOptionsLayout);

        grid.setItems(fieldList);
        add(grid);

        boolean win = gameService.checkIfWin(mapService, activePlayer);

        if (win) {
            UI.getCurrent().navigate("win");
            UI.getCurrent().getPage().reload();
        }

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




