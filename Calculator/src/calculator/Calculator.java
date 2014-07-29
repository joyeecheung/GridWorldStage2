package calculator;

import java.awt.*;

import javax.swing.*;

public class Calculator {
    public static void main(String[] args) {
        Runnable app = new CalculatorApp();
        EventQueue.invokeLater(app);
    }
}

class CalculatorApp implements Runnable {
    private static final int DEFAULT_FONT_SIZE = 16;

    public void run() {
        try {
            String gtkLAF = "com.sun.java.swing.plaf.gtk.GTKLookAndFeel";
            UIManager.setLookAndFeel(gtkLAF);
        } catch (ClassNotFoundException | InstantiationException
                | IllegalAccessException
                | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        Font defaultFont = new Font("Ubuntu Mono", Font.PLAIN,
                DEFAULT_FONT_SIZE);
        UIManager.put("Button.font", defaultFont);
        UIManager.put("Label.font", defaultFont);
        UIManager.put("TextField.font", defaultFont);
        CalculatorFrame frame = new CalculatorFrame();
        frame.setTitle("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}