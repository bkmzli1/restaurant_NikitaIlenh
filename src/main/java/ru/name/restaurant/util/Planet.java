package ru.name.restaurant.util;

//класс создания списков
public class Planet {

    private String code;
    private String name;

    /**
     * @param code своиобразный индификатор для определения что сроботал именно тот элемент на который нажали
     * @param name какое текст будет отоброжаться в списке
     */
    public Planet(String code, String name) {

        this.code = code;
        this.name = name;
    }

    // getИмяПременной получаем пременную
// setИмяПременной изменить пременную
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //получение имени переменной списка
    @Override
    public String toString() {
        return this.name;
    }

}

