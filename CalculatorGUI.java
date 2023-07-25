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
        setTitle("Aesthetic Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 400);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(255, 255, 255));

        // Create and add the text field for input and display
        textField = new JTextField();
        textField.setFont(new Font("Arial", Font.PLAIN, 28));
        textField.setBackground(new Color(240, 240, 240));
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
            button.setFont(new Font("Arial", Font.PLAIN, 24));
            button.setBackground(new Color(200, 200, 200));
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
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }

            CalculatorGUI calculator = new CalculatorGUI();
            calculator.setVisible(true);
        });
    }
}
