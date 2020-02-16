package ru.name.restaurant.util;

//класс создания списков
public class Planet {

    private int code;
    private String name;
    int cena;
    /**
     * @param code своиобразный индификатор для определения что сроботал именно тот элемент на который нажали
     * @param name какое текст будет отоброжаться в списке
     */
    public Planet(int code, String name, int cena) {
        this.code = code;
        this.name = name;
        this.cena = cena;
    }




    // getИмяПременной получаем пременную
//

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCena() {
        return cena;
    }

    public void setCena(int cena) {
        this.cena = cena;
    }

    //получение имени переменной списка
    @Override
    public String toString() {
        return this.name;
    }

}

