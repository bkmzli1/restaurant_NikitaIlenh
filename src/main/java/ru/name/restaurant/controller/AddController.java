package ru.name.restaurant.controller;


import javafx.beans.value.ChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import ru.name.restaurant.util.GetTexts;

import ru.name.restaurant.util.Planet;
import ru.name.restaurant.util.table.PlanetDAO;
import ru.name.restaurant.util.table.Table;

import java.util.ArrayList;

import static ru.name.restaurant.build.BuilderElements.textProperty;
import static ru.name.restaurant.controller.MainController.*;

public class AddController {

    public ChoiceBox menu;
    public TextField quantity;
    public Insets add;
    static ArrayList<TableView> tableList;
    int id, i;
    static ArrayList<ObservableList<Table>> observableListsTAble;
    GetTexts getTexts;
    String name;

    public void initialize() {
        textProperty(quantity);
        tableList = MainController.tableList;
        getTexts = MainController.getTexts;
        observableListsTAble = MainController.observableListsTAble;
        id = MainController.id;
        PlanetDAO optDAdO = new PlanetDAO();
        optDAdO.load("hish", "рыба 900 руб.");
        optDAdO.load("1", "пицца 100г 40 руб.");
        optDAdO.load("2", "анна 5 лет турмы 1000 руб.");
        optDAdO.load("3", "хз 3234 руб.");
        optDAdO.load("4", "не придумал 000 руб.");
        menu.setItems(optDAdO.getList());
        ChangeListener<Planet> changeListener = (observable, oldValue, newValue) -> {
            switch (newValue.getCode()) {
                case "hish":
                    i = 900;
                    name = "рыба " + i + " руб.";
                    break;
                case "1":
                    i = 40;
                    name = "пицца " + i + " руб.";
                    break;
                case "2":
                    i = 1000;
                    name = "анна " + i + " руб.";
                    break;
                case "3":
                    i = 3234;
                    name = "хз " + i + " руб.";
                    break;
                case "4":
                    i = 000;
                    name = "не придумал " + i + " руб.";
                    break;
            }

        };
        menu.setItems(optDAdO.getList());
        menu.getSelectionModel().selectedItemProperty().addListener(changeListener);
    }

    public void add(ActionEvent actionEvent) {
        observableListsTAble.get(id).add(new Table(observableListsTAble.get(id).size() + 1, Integer.parseInt(quantity.getText()), i * Integer.parseInt(quantity.getText()), name));

        int price = 0;
        for (Table t :
                observableListsTAble.get(id)) {
            price += t.getPrice();
        }
        getTexts.resultText.setText(price + " руб.");
        getTexts.forPaymentText.setText((price - ((price * discount.get(id)) / 100)) + " руб.");
        tableList.get(id).setItems(observableListsTAble.get(id));
        stageDialog.getNewWindow().close();
    }


}
