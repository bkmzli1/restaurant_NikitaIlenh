package ru.name.restaurant.util.print;

import javafx.collections.ObservableList;
import ru.name.restaurant.controller.MainController;
import ru.name.restaurant.util.table.Table;

import java.awt.*;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.ArrayList;

import static ru.name.restaurant.controller.MainController.getTexts;

public class P {
    void ddd() {
        PrinterJob printerJob = PrinterJob.getPrinterJob();
        printerJob.setPrintable(new Print());
        try {
            printerJob.print();
        } catch (PrinterException e) {
            System.err.println(e.getMessage());
        }
    }

    public int print(Graphics g, PageFormat pf, int pageNumber) throws PrinterException {

        ArrayList<ObservableList<Table>> observableListsTAble = MainController.getObservableListsTAble();
        ObservableList<Table> tables = observableListsTAble.get(MainController.id);

        if (pageNumber > 0) {
            return (Printable.NO_SUCH_PAGE);
        } else {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setColor(Color.black);
            Rectangle rect2 = new Rectangle(40, 20, 350, 50 + (tables.size() * 25));
            g2d.draw(rect2);
            rect2 = new Rectangle(40, 20, 350, 40);
            g2d.draw(rect2);
            g2d.setFont(new Font("Courier", Font.BOLD, 20));
            g2d.drawString("Ресторан", ((40 + 350) / 2) - "Ресторан".length(), 45);
            int i = 1;

            for (Table t : tables) {
                g2d.setFont(new Font("Courier", Font.PLAIN, 15));
                g2d.drawString(t.getName() + "", 50, 43 + (i * 25 + 10));
                g2d.drawString(t.getPrice() + "*" + t.getKol(), 340, 45 + (i * 25 + 10));
                i++;
            }
            //TODO вывод цены
            rect2 = new Rectangle(40, 20 + 50 + (tables.size() * 25), 350, 50);
            g2d.drawString("цена к оплате:" + getTexts.forPaymentText.getText(), 50, 20 + 50 + (tables.size() * 25) + 15);
            g2d.draw(rect2);
            return (Printable.PAGE_EXISTS);
        }

    }
}
