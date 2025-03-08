
// Currency Converter with Modern UI using Swing
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class CurrencyConverter extends JFrame {
    private JComboBox<String> fromCurrency, toCurrency;
    private JTextField amountField, resultField;
    private JButton convertButton;
    private HashMap<String, Double> exchangeRates;

    public CurrencyConverter() {
        setTitle("Currency Converter");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 2, 10, 10));
        setLocationRelativeTo(null);

        // Initialize exchange rates (Example rates)
        exchangeRates = new HashMap<>();
        exchangeRates.put("USD", 1.0);
        exchangeRates.put("EUR", 0.92);
        exchangeRates.put("INR", 82.0);
        exchangeRates.put("GBP", 0.78);

        // UI Components
        add(new JLabel("From Currency:"));
        fromCurrency = new JComboBox<>(exchangeRates.keySet().toArray(new String[0]));
        add(fromCurrency);

        add(new JLabel("To Currency:"));
        toCurrency = new JComboBox<>(exchangeRates.keySet().toArray(new String[0]));
        add(toCurrency);

        add(new JLabel("Amount:"));
        amountField = new JTextField();
        add(amountField);

        convertButton = new JButton("Convert");
        add(convertButton);

        add(new JLabel("Converted Amount:"));
        resultField = new JTextField();
        resultField.setEditable(false);
        add(resultField);

        // Convert Button Action
        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                convertCurrency();
            }
        });

        setVisible(true);
    }

    private void convertCurrency() {
        try {
            double amount = Double.parseDouble(amountField.getText());
            String from = (String) fromCurrency.getSelectedItem();
            String to = (String) toCurrency.getSelectedItem();
            double convertedAmount = (amount / exchangeRates.get(from)) * exchangeRates.get(to);
            resultField.setText(String.format("%.2f", convertedAmount));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid amount.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new CurrencyConverter();
    }
}
