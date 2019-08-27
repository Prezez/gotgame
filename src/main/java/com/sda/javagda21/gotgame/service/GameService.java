package com.sda.javagda21.gotgame.service;

import com.sda.javagda21.gotgame.model.Field;
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

}
