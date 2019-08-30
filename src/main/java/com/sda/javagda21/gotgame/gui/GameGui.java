package com.sda.javagda21.gotgame.gui;


import com.sda.javagda21.gotgame.model.Field;
import com.sda.javagda21.gotgame.model.Map;
import com.sda.javagda21.gotgame.model.Player;
import com.sda.javagda21.gotgame.service.FieldsService;
import com.sda.javagda21.gotgame.service.GameService;
import com.sda.javagda21.gotgame.service.MapService;
import com.sda.javagda21.gotgame.service.PlayerService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.dom.Style;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.shared.ui.LoadMode;
import com.vaadin.flow.theme.Theme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.List;

@Route("game")
//@StyleSheet("vaadin://gameStyle.css")
public class GameGui extends VerticalLayout {

    Map map;
    Grid<Field> grid = new Grid(Field.class);
    Label resultLabel = new Label();
    List<Field> fieldList = new ArrayList<>();
    Player activePlayer;
    Player[] players;
    String playerOneName;
    String playerTwoName;


    @Autowired
    public GameGui(MapService mapService, FieldsService fieldsService, PlayerService playerService, GameService gameService) {

//        TODO: CSS
        UI.getCurrent().getPage()
                .addStyleSheet("src/main/webapp/VAADIN/gameStyle.css", LoadMode.EAGER);

        map = mapService.loadMap();

        Field[][] fields = map.getFields();

        playerOneName = String.valueOf(VaadinSession.getCurrent().getSession().getAttribute("playerOne"));
        playerTwoName = String.valueOf(VaadinSession.getCurrent().getSession().getAttribute("playerTwo"));

        players = playerService.loadPlayers(fields, playerOneName, playerTwoName);

        activePlayer = playerService.getActivePlayer(players[0], players[1]);

        HorizontalLayout playerStatus = new HorizontalLayout();
        Label playerLabel = new Label();
        playerLabel.setText("Player : " + activePlayer.getName());
        playerLabel.setMinHeight("50px");
        playerLabel.setMinWidth("100px");
        playerLabel.setTitle("Player");
        Label playerGoldLabel = new Label();
        playerGoldLabel.setText("Gold : " + activePlayer.getGold());
        playerGoldLabel.setMinHeight("50px");
        playerGoldLabel.setMinWidth("100px");
        playerGoldLabel.setTitle("Gold");
        Label playerWarriorLabel = new Label();
        playerWarriorLabel.setText("Warriors : " + activePlayer.getArmy());
        playerWarriorLabel.setMinHeight("50px");
        playerWarriorLabel.setMinWidth("100px");
        playerWarriorLabel.setTitle("Warriors");

        playerStatus.add(playerLabel, playerGoldLabel, playerWarriorLabel);
        add(playerStatus);



        for (int i = 0; i < fields.length; i++) {
            HorizontalLayout hl = new HorizontalLayout();
            for (int j = 0; j < fields[i].length; j++) {
                fieldList.add(fields[i][j]);
                Label label = new Label();
                label.setText(String.valueOf(fields[i][j].getFieldNo()));
                label.setMinWidth("100px");
                label.setMinHeight("100px");
                label.setId(String.valueOf(fields[i][j].getFieldNo()));
                label.setClassName(fields[i][j].getOwner().getColor());


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
            playerService.increaseGoldAmount(activePlayer, numberOfFieldsOwned);
            mapService.save(map);

            UI.getCurrent().getPage().reload();
        });

        Button buyWarriorsButton = new Button();
        TextField warriorsAmount = new TextField();
        warriorsAmount.setMinWidth("200px");
        warriorsAmount.setMinHeight("100px");
        warriorsAmount.setTitle("Enter Amount");
        warriorsAmount.setLabel("Warrior Amount");
        buyWarriorsButton.setText("Buy Warriors");
        buyWarriorsButton.setMinHeight("100px");
        buyWarriorsButton.setMinWidth("200px");
        buyWarriorsButton.addClickListener(click -> {
            boolean buyWarriorsPositive = playerService.buyWarriors(Integer.valueOf(warriorsAmount.getValue()), activePlayer);
            if (buyWarriorsPositive) {
                fieldList = gameService.updateFieldList(fieldList);
                UI.getCurrent().getPage().reload();
            } else {
                Notification.show("Wrong amount", 10000, Notification.Position.MIDDLE);
            }
        });

        buttonOptionsLayout.add(endTurnButton);
        buttonOptionsLayout.add(warriorsAmount);
        buttonOptionsLayout.add(buyWarriorsButton);
        add(buttonOptionsLayout);

        Button createNewGameButton = new Button();
        createNewGameButton.addClickListener(click -> {
            map = mapService.resetMap();
            players = playerService.resetPlayers(playerOneName, playerTwoName);
            UI.getCurrent().getPage().reload();

//            TODO: dodatkowe okienko potwierdzające wybór
//            TODO: tylko jeden atak na turę
        });
        createNewGameButton.setText("Start New Game");
        createNewGameButton.setMinWidth("630px");
        createNewGameButton.setMinHeight("100px");
        add(createNewGameButton);

        grid.setItems(fieldList);
        add(grid);

        boolean win = gameService.checkIfWin(mapService, activePlayer);

        if (win) {
            UI.getCurrent().navigate("win");
            UI.getCurrent().getPage().reload();
        }

    }

}




