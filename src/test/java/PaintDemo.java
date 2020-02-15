import javax.swing.*;
import java.awt.*;

public class PaintDemo extends JPanel {

    JFrame frame = new JFrame();
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PaintDemo());
    }
    PaintDemo(int i){
        SwingUtilities.invokeLater(() -> new PaintDemo());
    }

    public PaintDemo() {
        setPreferredSize(new Dimension(500, 500));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
        Rectangle rect2 = new Rectangle(40, 20, 120, 50+(5*25));
        g2d.draw(rect2);
        rect2 = new Rectangle(40, 20, 120, 40);
        g2d.draw(rect2);
        g2d.setFont(new Font("Courier", Font.BOLD, 20));
        g2d.drawString("Bkmz", 70, 45);
        for (int i = 0; i < 5; i++) {
            g2d.setFont(new Font("Courier", Font.PLAIN, 10));
            g2d.drawString("Bkmz", 50, 75+(i*25+10));
            g2d.drawString("Bkmz", 130, 75+(i*25+10));
        }


    }
}