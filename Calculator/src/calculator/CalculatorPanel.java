package calculator;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

/**
 * A panel with calculator buttons and a result display.
 * 
 * @author joyeecheung
 *
 */
public class CalculatorPanel extends JPanel {
    private JTextField leftInput;
    private JLabel opLabel;
    private JTextField rightInput;
    private JTextField output;
    private static final int GRID_WIDTH = 80;
    private static final int GRID_HEIGHT = 50;

    public CalculatorPanel() {

        ActionListener command = new CommandAction();
        // add the buttons in a 2 x 5 grid
        setLayout(new GridLayout(2, 5, 1, 1));

        leftInput = new JTextField("0.0", 0);
        opLabel = new JLabel("", JLabel.CENTER);
        rightInput = new JTextField("0.0", 0);
        JLabel equalLabel = new JLabel("=", JLabel.CENTER);
        output = new JTextField("", 0);
        output.setEditable(false);

        leftInput.setPreferredSize(new Dimension(GRID_WIDTH, GRID_HEIGHT));
        leftInput.setHorizontalAlignment(JTextField.CENTER);
        rightInput.setHorizontalAlignment(JTextField.CENTER);
        output.setHorizontalAlignment(JTextField.CENTER);

        add(leftInput);
        add(opLabel);
        add(rightInput);
        add(equalLabel);
        add(output);

        addButton("+", command);
        addButton("-", command);
        addButton("*", command);
        addButton("/", command);
        addButton("OK", command);

    }

    /**
     * Adds a button to the center panel.
     * 
     * @param label
     *            the button label
     * @param listener
     *            the button listener
     */
    private void addButton(String label, ActionListener listener) {
        JButton button = new JButton(label);
        button.addActionListener(listener);
        add(button);
    }

    /**
     * This action executes the command that the button action string denotes.
     */
    private class CommandAction implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            String cmd = event.getActionCommand();

            if (cmd.equals("OK")) {
                double left = Double.parseDouble(leftInput.getText());
                double right = Double.parseDouble(rightInput.getText());
                String op = opLabel.getText();
                String result = "" + calculate(left, op, right);
                output.setText(result);
            } else {
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
    public double calculate(double a, String op, double b) {
        if (op.equals("+")) {
            return a + b;
        } else if (op.equals("-")) {
            return a - b;
        } else if (op.equals("*")) {
            return a * b;
        } else if (op.equals("/")) {
            return a / b;
        } else if (op.equals("+")) {
            return a + b;
        } else {
            return Double.NaN;
        }
    }
}
