package calculator;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * A panel with calculator buttons and a result display.
 *
 * @author joyeecheung
 *
 */
public class CalculatorPanel extends JPanel
{
    private static final long serialVersionUID = 1L;
    private JTextField leftInput;
    private JLabel opLabel;
    private JTextField rightInput;
    private JTextField output;
    private static final int GRID_WIDTH = 80;
    private static final int GRID_HEIGHT = 50;

    public CalculatorPanel()
    {

        ActionListener command = new CommandAction();
        // add the buttons in a 2 x 5 grid
        setLayout(new GridLayout(2, 5, 1, 1));

        // Construct controls
        leftInput = new JTextField("0.0", 0);
        opLabel = new JLabel("", SwingConstants.CENTER);
        rightInput = new JTextField("0.0", 0);
        JLabel equalLabel = new JLabel("=", SwingConstants.CENTER);
        output = new JTextField("", 0);
        output.setEditable(false);

        // Appearance settings
        leftInput.setPreferredSize(new Dimension(GRID_WIDTH, GRID_HEIGHT));
        leftInput.setHorizontalAlignment(SwingConstants.CENTER);
        rightInput.setHorizontalAlignment(SwingConstants.CENTER);
        output.setHorizontalAlignment(SwingConstants.CENTER);

        // add them to the panel
        add(leftInput);
        add(opLabel);
        add(rightInput);
        add(equalLabel);
        add(output);

        // add the buttons
        addButton("+", command);
        addButton("-", command);
        addButton("*", command);
        addButton("/", command);
        addButton("OK", command);

    }

    /**
     * Adds a button to the panel.
     *
     * @param label
     *            the button label
     * @param listener
     *            the button listener
     */
    private void addButton(String label, ActionListener listener)
    {
        JButton button = new JButton(label);
        button.addActionListener(listener);
        add(button);
    }

    /**
     * Action listeners for the buttons.
     *
     * @author joyeecheung
     */
    private class CommandAction implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent event)
        {
            String cmd = event.getActionCommand();

            // OK button will calculate and display the result
            if (cmd.equals("OK"))
            {
                double left = Double.parseDouble(leftInput.getText());
                double right = Double.parseDouble(rightInput.getText());
                String op = opLabel.getText();
                String result = "" + calculate(left, op, right);
                output.setText(result);
            }
            // operators will be displayed in the opLabel
            else
            {
                opLabel.setText(cmd);
            }
        }
    }

    /**
     * Calculate a op b and return the result.
     *
     * @param a
     *            left operand
     * @param op
     *            operator
     * @param b
     *            right operand
     * @return result of a op b. If op is invalid, return NaN.
     */
    public static double calculate(double a, String op, double b)
    {
        if (op.equals("+"))
        {
            return a + b;
        }
        else if (op.equals("-"))
        {
            return a - b;
        }
        else if (op.equals("*"))
        {
            return a * b;
        }
        else if (op.equals("/"))
        {
            return a / b;
        }
        else if (op.equals("+"))
        {
            return a + b;
        }
        else
        {
            return Double.NaN;
        }
    }
}
