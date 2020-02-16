package ru.name.restaurant.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import ru.name.restaurant.util.print.PaintDemo;
import ru.name.restaurant.util.table.Table;

import java.util.ArrayList;

public class PrintController {
    public ListView list;
    public static ObservableList<Table> tables;
    public static ObservableList<Table> tablesN = FXCollections.observableArrayList();

    private ArrayList<String> customerList = new ArrayList<>();
    ObservableList<CheckBox> checkBoxes = FXCollections.observableArrayList();

    public void initialize() {

        tables = MainController.observableListsTAble.get(MainController.id);
        customerList.add(tables.get(0).getCustomer());
        for (int i = 1; i < tables.size(); i++) {
            boolean b = false;
            for (int j = 0; j < customerList.size(); j++) {
                if (tables.get(i).getCustomer().equals(customerList.get(j))) {
                    b = true;

                }
            }
            if (!b) {
                customerList.add(tables.get(i).getCustomer());

            }
        }
        for (String s :
                customerList) {
            checkBoxes.add(new CheckBox(s));
        }
        list.setItems(checkBoxes);

    }

    public void print(ActionEvent actionEvent) {
        tablesN.clear();
        for (int i = 0; i < tables.size(); i++) {
            for (int j = 0; j < customerList.size(); j++) {
                if (tables.get(i).getCustomer().equals(customerList.get(j))& checkBoxes.get(j).isSelected()) {
                    tablesN.add(tables.get(i));
                    break;
                }
            }
        }
        PaintDemo paintDemo = new PaintDemo(tablesN);
    }
}
