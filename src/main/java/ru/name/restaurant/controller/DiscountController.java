package ru.name.restaurant.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.util.StringConverter;
import ru.name.restaurant.util.table.Table;

import static ru.name.restaurant.controller.MainController.*;

public class DiscountController {
    public Spinner discount;

    public void initialize() {
        ObservableList<Integer> items = FXCollections.observableArrayList();
        SpinnerValueFactory<Integer> valueFactory = //
                new SpinnerValueFactory.ListSpinnerValueFactory<>(items);

        for (int i = 0; i <=100 ; i++) {
            items.add(i);
        }
        MyConverter converter = new MyConverter();
        valueFactory.setConverter(converter);
        discount.setValueFactory(valueFactory);

    }
    class MyConverter extends StringConverter<Integer> {

        @Override
        public String toString(Integer object) {
            return object + "";
        }

        @Override
        public Integer fromString(String string) {
            return Integer.parseInt(string);
        }

    }

    public void discount(ActionEvent actionEvent) {
       // MainController.discount.get(id) = Integer.parseInt(String.valueOf(discount.getValue()));
        MainController.discount.remove(id);
        MainController.discount.add(id,Integer.parseInt(String.valueOf(discount.getValue())));
        if (discount.getId().equals("0")){
            MainController.getTexts.discountText.setText("");
        }else {
            MainController.getTexts.discountText.setText(String.valueOf(discount.getValue()));
        }
        int price = 0;
        for (Table t :
                observableListsTAble.get(id)) {
            price += t.getPrice();
        }
        getTexts.resultText.setText(price + " руб.");

        tableList.get(id).setItems(observableListsTAble.get(id));
        if (observableListsTAble.get(id).size()==0){
            getTexts.resultText.setText("-");
        }
        getTexts.forPaymentText.setText((price - ((price * MainController.discount.get(id)) / 100)) + " Руб.");
        stageDialog.getNewWindow().close();
    }
}
