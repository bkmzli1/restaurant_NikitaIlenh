import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;

public class Printer extends Canvas implements Printable {

    public static void main(String[] arg) {
        PrinterJob printerJob = PrinterJob.getPrinterJob();
        printerJob.setPrintable(new Printer());
        try {
            printerJob.print();
        } catch (PrinterException e) {
            System.err.println(e.getMessage());
        }
    }

    public int print(Graphics g, PageFormat pf, int pageNumber) throws PrinterException {
        if (pageNumber > 0) {
            return (Printable.NO_SUCH_PAGE);
        } else {
            Graphics2D g2 = (Graphics2D) g;
            g2.setFont(new Font("Courier", Font.BOLD, 35));
            g2.setPaint(new GradientPaint(50f, 100f, Color.red,
                    250f, 100f, Color.blue));
            g2.drawString("Hello Java 2D", 50f, 100f);
            return (Printable.PAGE_EXISTS);
        }


    }
}