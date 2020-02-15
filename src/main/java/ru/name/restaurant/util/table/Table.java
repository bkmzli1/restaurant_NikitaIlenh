package ru.name.restaurant.util.table;

public class Table {
    private int number, kol, price;
    private String name;

    /**
     * @param number номер столбца
     * @param kol    кол столбца
     * @param price  цена
     * @param name   имя
     */
    public Table(int number, int kol, int price, String name) {
        this.number = number;
        this.kol = kol;
        this.price = price;
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getKol() {
        return kol;
    }

    public void setKol(int kol) {
        this.kol = kol;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
