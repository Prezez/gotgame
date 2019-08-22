package com.sda.javagda21.gotgame.gui;


import com.sda.javagda21.gotgame.model.Field;
import com.sda.javagda21.gotgame.model.Map;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("game")
public class GameGui extends VerticalLayout {

    Map map;
    Grid grid = new Grid();


    public GameGui(Map map) {

        map = Map.createNewMap();
        Field[][] fields = map.getFields();
        System.out.println(fields);

        for (int i = 0; i < fields.length; i++) {
            HorizontalLayout hl = new HorizontalLayout();
            for (int j = 0; j < fields[i].length; j++) {
                Label label = new Label();
                label.setText(String.valueOf(fields[i][j].getFieldNo()));
                label.setMinWidth("100px");
                label.setId(String.valueOf(fields[i][j].getFieldNo()));
//                if (fields[i][j].getOwner()) {
//                    label.setClassName(fields[i][j].getOwner().getName());
//                }
                hl.add(label);
            }
            add(hl);
        }


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

        add(grid);
    }


}




