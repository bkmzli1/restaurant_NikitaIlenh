package ru.name.restaurant.controller;


import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import ru.name.restaurant.util.GetTexts;
import ru.name.restaurant.util.Planet;
import ru.name.restaurant.util.table.PlanetDAO;
import ru.name.restaurant.util.table.Table;

import java.util.ArrayList;

import static ru.name.restaurant.build.BuilderElements.textProperty;
import static ru.name.restaurant.controller.MainController.discount;
import static ru.name.restaurant.controller.MainController.stageDialog;

public class AddController {

    public ChoiceBox menu;
    public Spinner quantity;
    public Insets add;
    static ArrayList<TableView> tableList;
    public TextField customer;
    int id, i;
    static ArrayList<ObservableList<Table>> observableListsTAble;
    GetTexts getTexts;
    String name;

    public void initialize() {
        ObservableList<Integer> items = FXCollections.observableArrayList();
        SpinnerValueFactory<Integer> valueFactory = //
                new SpinnerValueFactory.ListSpinnerValueFactory<>(items);

        for (int i = 1; i <=100 ; i++) {
            items.add(i);
        }
        DiscountController.MyConverter converter = new DiscountController.MyConverter();
        valueFactory.setConverter(converter);
        quantity.setValueFactory(valueFactory);
        tableList = MainController.tableList;
        getTexts = MainController.getTexts;
        observableListsTAble = MainController.observableListsTAble;
        id = MainController.id;
        PlanetDAO optDAdO = new PlanetDAO();
        int zz = 0;

        optDAdO.load(zz++, "Тар-тар из телятины",350);
        optDAdO.load(zz++, "Закуска от Остапа к хреновухе",570);
        optDAdO.load(zz++, "Салат с рукколой и креветками",549);
        optDAdO.load(zz++, "Теплый салат с беконом",369);
        optDAdO.load(zz++, "Фиеста",500);
        optDAdO.load(zz++, "Жемчужина",630);
        optDAdO.load(zz++, "Жульен из кальмаров",320);
        optDAdO.load(zz++, "Дорадо на гриле",850);
        optDAdO.load(zz++, "Стейк палтуса с золотистой корочкой",950);
        optDAdO.load(zz++, "Стейк из телятины миньон",597);



        menu.setItems(optDAdO.getList());

        ChangeListener<Planet> changeListener = (observable, oldValue, newValue) -> {
            i = newValue.getCena();
            name = newValue.getName() +i+"р.";

        };
        menu.setItems(optDAdO.getList());
        menu.getSelectionModel().selectedItemProperty().addListener(changeListener);
    }

    public void add(ActionEvent actionEvent) {
        observableListsTAble.get(id).add(new Table(observableListsTAble.get(id).size() + 1, (int) quantity.getValue(), i * (int) quantity.getValue(), name, customer.getText()));

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
