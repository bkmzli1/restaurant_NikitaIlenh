package ru.name.restaurant.util.table;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ru.name.restaurant.util.Planet;

import java.util.ArrayList;

public class PlanetDAO {
    //лист классов Planet хранящий элементы класса
    private ObservableList<Planet> list;
    //лист классов Planet хранящий классы
    private ArrayList<Planet> arrayList = new ArrayList<>();

    public PlanetDAO() {

        reload();
    }
    //добовляет строчку в лист
    public void load(String code, String name) {
        Planet earth = new Planet(code, name);
        arrayList.add(earth);
        ;
        reload();
    }
    //обнуляет переменную list,а вначале обявляет её
    private void reload() {

        list = FXCollections.observableArrayList(arrayList);
    }

    public ObservableList<Planet> getList() {
        return list;
    }
    //добовляет строчку в лист в конец
    public void add(String code, String name) {
        Planet earth = new Planet(code, name);
        arrayList.add(arrayList.size() - 1, earth);
        reload();
    }

}
