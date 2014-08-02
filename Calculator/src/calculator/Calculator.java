package calculator;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * The main class of the calculator.
 * @author joyeecheung
 */
public class Calculator
{
    public static void main(String[] args)
    {
        Runnable app = new CalculatorApp();
        EventQueue.invokeLater(app);
    }
}

/**
 * The runnable implementation of calculator.
 * @author joyeecheung
 */
class CalculatorApp implements Runnable
{
    private static final int DEFAULT_FONT_SIZE = 16;

    /** (non-Javadoc)
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run()
    {
        // Use GTK Look & Feel
        try
        {
            String gtkLAF = "com.sun.java.swing.plaf.gtk.GTKLookAndFeel";
            UIManager.setLookAndFeel(gtkLAF);
        }
        catch (ClassNotFoundException | InstantiationException
                | IllegalAccessException
                | UnsupportedLookAndFeelException e)
        {
            e.printStackTrace();
        }

        // UI settings
        Font defaultFont = new Font("Ubuntu Mono", Font.PLAIN,
                DEFAULT_FONT_SIZE);
        UIManager.put("Button.font", defaultFont);
        UIManager.put("Label.font", defaultFont);
        UIManager.put("TextField.font", defaultFont);
        
        // Construct a frame and set it up
        JFrame frame = new JFrame();
        frame.setTitle("Calculator");
        frame.add(new CalculatorPanel());
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}