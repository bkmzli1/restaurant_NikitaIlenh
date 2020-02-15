package ru.name.restaurant.controller;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.ChoiceBox;
import ru.name.restaurant.util.table.PlanetDAO;
import ru.name.restaurant.util.table.Table;

import static ru.name.restaurant.controller.MainController.*;

public class RemoveController {

    public ChoiceBox menu;
    public Insets add;

    int id = MainController.id;

    public void initialize() {
        PlanetDAO optDAdO = new PlanetDAO();
        for (int i = 0; i < observableListsTAble.get(id).size(); i++) {
            optDAdO.load("" + i, observableListsTAble.get(id).get(i).getNumber() + "");
        }

        menu.setItems(optDAdO.getList());
    }

    public void remove(ActionEvent actionEvent) {
        for (int i = 0; i < observableListsTAble.get(id).size(); i++) {
            if (observableListsTAble.get(id).get(i).getNumber() == Integer.parseInt(String.valueOf(menu.getValue()))) {
                observableListsTAble.get(id).remove(i);
            }
        }
        for (int i = 0; i < observableListsTAble.get(id).size(); i++) {
            observableListsTAble.get(id).get(i).setNumber(i+1);
        }

        int price = 0;
        for (Table t :
                observableListsTAble.get(id)) {
            price += t.getPrice();
        }
        getTexts.resultText.setText(price + "руб.");

        tableList.get(id).setItems(observableListsTAble.get(id));
        if (observableListsTAble.get(id).size() == 0) {
            getTexts.resultText.setText("-");
        }
        getTexts.forPaymentText.setText((price - ((price * MainController.discount.get(id)) / 100)) + " Руб.");

        stageDialog.getNewWindow().close();
    }
}
