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
import static ru.name.restaurant.controller.MainController.discount;
import static ru.name.restaurant.controller.MainController.stageDialog;

public class Edit {
    public ChoiceBox idCB;
    public TextField quantity;
    public ChoiceBox menu;
    public Insets edit;
    static ArrayList<TableView> tableList;
    int id, price, idItem;
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
        PlanetDAO editDAdO = new PlanetDAO();

        for (int j = 0; j < observableListsTAble.get(id).size(); j++) {
            editDAdO.load(observableListsTAble.get(id).get(j).getName(), (j + 1) + "");
        }
        ChangeListener<Planet> changeListener2 = (observable, oldValue, newValue) -> {
            idItem = Integer.parseInt(newValue.getName())-1;
            quantity.setText(String.valueOf(observableListsTAble.get(id).get(idItem).getKol()));
            String s = observableListsTAble.get(id).get(idItem).getName();
            for (int i = 0; i < optDAdO.getList().size(); i++) {
                if (optDAdO.getList().get(i).getName().equals(newValue.getCode())) {
                    menu.setValue(optDAdO.getList().get(i));
                    break;
                }
            }


        };
        idCB.getSelectionModel().selectedItemProperty().addListener(changeListener2);
        idCB.setItems(editDAdO.getList());

        optDAdO.load("hish", "рыба 900 руб.");
        optDAdO.load("1", "пицца 100г 40 руб.");
        optDAdO.load("2", "анна 5 лет турмы 1000 руб.");
        optDAdO.load("3", "хз 3234 руб.");
        optDAdO.load("4", "не придумал 000 руб.");
        menu.setItems(optDAdO.getList());
        ChangeListener<Planet> changeListener = (observable, oldValue, newValue) -> {

            switch (newValue.getCode()) {
                case "hish":
                    price = 900;
                    name = "рыба " + price + " руб.";
                    break;
                case "1":
                    price = 40;
                    name = "пицца " + price + " руб.";
                    break;
                case "2":
                    price = 1000;
                    name = "анна " + price + " руб.";
                    break;
                case "3":
                    price = 3234;
                    name = "хз " + price + " руб.";
                    break;
                case "4":
                    price = 000;
                    name = "не придумал " + price + " руб.";
                    break;
            }
        };
        menu.setItems(optDAdO.getList());

        menu.getSelectionModel().selectedItemProperty().addListener(changeListener);
    }

    public void edit(ActionEvent actionEvent) {
        observableListsTAble.get(id).get(idItem).setKol(Integer.parseInt(quantity.getText()));
        observableListsTAble.get(id).get(idItem).setName(name);
        observableListsTAble.get(id).get(idItem).setPrice(price);
        observableListsTAble.get(id).add(new Table(1, 2, 3, "4","5"));
        observableListsTAble.get(id).remove(observableListsTAble.get(id).size() - 1);

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
