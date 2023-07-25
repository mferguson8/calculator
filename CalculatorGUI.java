import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorGUI extends JFrame implements ActionListener {
    private JTextField textField;
    private String currentInput = "";
    private double result = 0;
    private char lastOperator = ' ';

    public CalculatorGUI() {
        // Create and configure the JFrame
        setTitle("Simple Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 400);
        setLayout(new BorderLayout());

        // Create and add the text field for input and display
        textField = new JTextField();
        textField.setFont(new Font("Arial", Font.PLAIN, 20));
        textField.setEditable(false);
        textField.setHorizontalAlignment(JTextField.RIGHT);
        add(textField, BorderLayout.NORTH);

        // Create and add the buttons for digits and operations
        JPanel buttonsPanel = new JPanel(new GridLayout(5, 4, 5, 5));
        String[] buttonLabels = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "=", "+"
        };

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.setFont(new Font("Arial", Font.PLAIN, 20));
            button.addActionListener(this);
            buttonsPanel.add(button);
        }

        add(buttonsPanel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (Character.isDigit(command.charAt(0)) || command.equals(".")) {
            currentInput += command;
            textField.setText(currentInput);
        } else if (command.equals("=")) {
            calculateResult();
            currentInput = Double.toString(result);
            textField.setText(currentInput);
            lastOperator = ' ';
        } else if (command.equals("C")) {
            currentInput = "";
            result = 0;
            lastOperator = ' ';
            textField.setText("");
        } else {
            if (!currentInput.isEmpty()) {
                calculateResult();
                currentInput = "";
            }
            lastOperator = command.charAt(0);
        }
    }

    private void calculateResult() {
        if (!currentInput.isEmpty()) {
            double value = Double.parseDouble(currentInput);
            switch (lastOperator) {
                case '+':
                    result += value;
                    break;
                case '-':
                    result -= value;
                    break;
                case '*':
                    result *= value;
                    break;
                case '/':
                    if (value != 0) {
                        result /= value;
                    } else {
                        textField.setText("Error: Division by zero");
                    }
                    break;
                default:
                    result = value;
                    break;
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CalculatorGUI calculator = new CalculatorGUI();
            calculator.setVisible(true);
        });
    }
}
