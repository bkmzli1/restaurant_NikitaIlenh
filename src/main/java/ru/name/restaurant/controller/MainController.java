package ru.name.restaurant.controller;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import ru.name.restaurant.Main;
import ru.name.restaurant.util.GetTexts;
import ru.name.restaurant.util.stage.StageDialog;
import ru.name.restaurant.util.table.Table;
import ru.name.restaurant.util.print.PaintDemo;

import java.util.ArrayList;

import static ru.name.restaurant.build.BuilderElements.buttonBuild;


public class MainController {

    public Text discountText;
    public Text resultText;
    public Text forPaymentText;
    public VBox controlVBox;
    public TabPane tables;

    public static GetTexts getTexts;

    static ArrayList<String> nameListButton = new ArrayList<>();
    static ArrayList<Button> buttonList = new ArrayList<>();
    static ArrayList<TableView> tableList = new ArrayList<>();
   public static ArrayList<ObservableList<Table>> observableListsTAble = new ArrayList<>();

    static StageDialog stageDialog;

    public static int id = 0;
    public static ArrayList<Integer> discount = new ArrayList<Integer>();


    public void initialize() {
        getTexts = new GetTexts(discountText, resultText, forPaymentText);
        nameListButton.add("Добавить заказ");
        nameListButton.add("Добавить скидку");
        nameListButton.add("Удалить заказ");
        nameListButton.add("Очистить заказ");


        for (String name :
                nameListButton) {
            Button button = buttonBuild(name);
            buttonList.add(button);
            controlVBox.getChildren().add(button);
            switch (button.getText()) {
                case "Добавить заказ":

                    buttonDialog(button, "add");
                    break;
                case "Добавить скидку":

                    buttonDialog(button, "discount");
                    break;
                case "Удалить заказ":

                    buttonDialog(button, "remove");
                    break;
                case "Изменить заказ":

                    buttonDialog(button, "edit");
                    break;
                case "Очистить заказ":
                    button.setOnMouseClicked(event -> {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                observableListsTAble.get(id).clear();
                                tableList.get(id).setItems(observableListsTAble.get(id));
                                discount.get(id);
                                forPaymentText.setText("-");
                                resultText.setText("-");
                                discountText.setText("-");



                            }
                        });

                    });

                    break;
            }
        }
        for (int i = 0; i < 10; i++) {


            Tab tab = new Tab("Стол #" + (int) (i + 1));

            TableView<Table> table = new TableView();
            VBox.setVgrow(table, Priority.ALWAYS);
            HBox.setHgrow(table, Priority.ALWAYS);

            TableColumn<Table, String> number = new TableColumn<Table, String>("№");
            number.setCellValueFactory(new PropertyValueFactory<>("number"));
            TableColumn<Table, String> kol = new TableColumn<Table, String>("кол.");
            kol.setCellValueFactory(new PropertyValueFactory<>("kol"));
            TableColumn<Table, String> price = new TableColumn<Table, String>("цена");
            price.setCellValueFactory(new PropertyValueFactory<>("price"));
            TableColumn<Table, String> name = new TableColumn<Table, String>("название");
            name.setCellValueFactory(new PropertyValueFactory<>("name"));
            TableColumn<Table, String> customer = new TableColumn<Table, String>("Заказчик");
            customer.setCellValueFactory(new PropertyValueFactory<>("customer"));
            discount.add(0);

            ObservableList<Table> tableColumnObservableList = FXCollections.observableArrayList();
            observableListsTAble.add(tableColumnObservableList);
            table.getColumns().addAll(number, kol, price, name,customer);

            table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
            tab.setContent(table);
            tableList.add(table);
            tables.getTabs().add(tab);
        }
        tables.setOnMouseClicked(event -> {
            this.id = Integer.parseInt(tables.getSelectionModel().getSelectedItem().getText().replace("Стол #", "")) - 1;
            int price = 0;
            for (Table t :
                    observableListsTAble.get(id)) {
                price += t.getPrice();
            }
            getTexts.resultText.setText(price + " руб.");

            tableList.get(id).setItems(observableListsTAble.get(id));
            getTexts.discountText.setText(discount.get(id) + "");
            if (discount.get(id) == 0) {
                getTexts.discountText.setText("-");
            }
            if (observableListsTAble.get(id).size() == 0) {
                getTexts.resultText.setText("-");
            }
            getTexts.forPaymentText.setText((price - ((price * MainController.discount.get(id)) / 100)) + " Руб.");
        });


    }

    private static void buttonDialog(Button button, String name) {
        button.setOnMouseClicked(event -> {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    stageDialog = new StageDialog(name);
                }
            });

        });
    }


    public static Button getButton(String name) {
        for (Button button :
                buttonList) {
            if (button.getText().equals(name)) {
                return button;
            }
        }
        return null;
    }


    public void print(ActionEvent actionEvent) {
        try {

            //PaintDemo paintDemo = new PaintDemo(observableListsTAble.get(id));
            new StageDialog("print");
        } catch (Exception e) {
        }


    }

    public static ArrayList<ObservableList<Table>> getObservableListsTAble() {
        return observableListsTAble;
    }

    public static void setObservableListsTAble(ArrayList<ObservableList<Table>> observableListsTAble) {
        MainController.observableListsTAble = observableListsTAble;
    }
}


