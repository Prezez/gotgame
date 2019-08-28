package com.sda.javagda21.gotgame.service;

import com.sda.javagda21.gotgame.model.Field;
import com.sda.javagda21.gotgame.model.Player;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {

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


    public boolean checkIfWin(MapService mapService, Player activePlayer) {

        Integer fieldsOwned = mapService.numberOfFieldsOwned(activePlayer);
        Integer numberOfFields = mapService.numberOfFields();
        boolean win = false;
        if (fieldsOwned==numberOfFields) {
            win = true;
        }

        return win;
    }
}
