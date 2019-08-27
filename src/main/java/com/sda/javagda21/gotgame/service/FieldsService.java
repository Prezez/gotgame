package com.sda.javagda21.gotgame.service;


import com.sda.javagda21.gotgame.model.Field;
import com.sda.javagda21.gotgame.model.Map;
import com.sda.javagda21.gotgame.model.Player;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FieldsService {

    public boolean checkIfSurroundingFieldsHasAnOwner(Player player, Field field, Map map) {


        if (field.getOwner().getName().equals(player.getName())){
            return false;
        }

        List<Integer> fields = map.surroundingFields(field.getFieldNo());

        for (int i = 0; i < fields.size(); i++) {
            Integer[] fieldPosition = map.getFieldPositionFromFieldNumber(fields.get(i));
            Player owner = map.getFields()[fieldPosition[0]][fieldPosition[1]].getOwner();

            if (!owner.getName().equals(player.getName())){

            } else {
                return true;
            }
        }
        return false;
    }
}
