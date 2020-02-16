package ru.name.restaurant.util.print;

import javafx.collections.ObservableList;
import ru.name.restaurant.controller.MainController;
import ru.name.restaurant.util.table.Table;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;


public class PaintDemo extends JPanel {
    PaintDemo() {
    }

    ObservableList<Table> tables;
    JFrame frame = new JFrame();


    public static void main(String[] args) {
        //SwingUtilities.invokeLater(() -> new PaintDemo());
    }

    public PaintDemo(ObservableList<Table> tables) {
        //Print print = new Print();
        //print.ddd();
        SwingUtilities.invokeLater(() -> new PaintDemo(1, tables));
    }

    public PaintDemo(int i, ObservableList<Table> tables) {
        this.tables = tables;

        setPreferredSize(new Dimension(500, 500));
        frame.add(this);
        frame.pack();
        frame.setLocationRelativeTo(null);
        setBackground(Color.white);
        frame.setVisible(true);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(Color.black);
        Rectangle rect2 = new Rectangle(40, 20, 350, 50 + (tables.size() * 25));
        g2d.draw(rect2);
        rect2 = new Rectangle(40, 20, 350, 40);
        g2d.draw(rect2);
        g2d.setFont(new Font("Courier", Font.BOLD, 20));
        g2d.drawString("Ресторан", ((40 + 350) / 2) - "Ресторан".length(), 45);
        int i = 1;
        double payment = 0;
        for (Table t : tables) {
            payment += t.getPrice();
            g2d.setFont(new Font("Courier", Font.PLAIN, 15));
            g2d.drawString(t.getName() + "", 50, 43 + (i * 25 + 10));
            g2d.drawString(t.getPrice() + "*" + t.getKol(), 340, 45 + (i * 25 + 10));
            i++;
        }
        //TODO вывод цены
        rect2 = new Rectangle(40, 20 + 50 + (tables.size() * 25), 350, 50);
        g2d.drawString("цена к оплате:" + payment, 50, 20 + 50 + (tables.size() * 25) + 15);
        g2d.draw(rect2);

    }
}
