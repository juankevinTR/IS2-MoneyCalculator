package com.juankevintrujillo.view;

import com.juankevintrujillo.main.Controller;
import com.juankevintrujillo.model.Currency;
import com.juankevintrujillo.model.CurrencyList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Objects;

public class Gui extends JFrame {
    private JPanel mainPanel;

    private JLabel amountLabel;
    private JTextField amountTXT;

    private JLabel convertLabel;
    private JComboBox<String> currenciesBox;

    private JButton cleanButton;
    private JButton convertButton;

    private JLabel resultLabel;
    private JTextField resultTXT;

    public Gui() {
        // Setting colors
        Color bgPanel = Color.decode("#0E192B"); //  #0E192B #0E294B #00825A #5BD36D #FFFFFF
        mainPanel.setBackground(bgPanel);

        // Filling box with all currencies
        CurrencyList currencyList = new CurrencyList();
        for (Currency currency : currencyList.getList().values()) {
            currenciesBox.addItem(currency.getCode());
        }
        currenciesBox.removeItem("EUR");
        // Center the text
        amountTXT.setHorizontalAlignment(JTextField.CENTER);
        resultTXT.setHorizontalAlignment(JTextField.CENTER);

        // This uses the form designer form
        add(mainPanel);

        setTitle("IS2 - Money Calculator");
        setSize(371, 183);
        setResizable(false);

        amountTXT.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent evt) {
                if (!(Character.isDigit(evt.getKeyChar())) &&
                        !(evt.getKeyChar() == KeyEvent.VK_BACK_SPACE) &&
                        !(evt.getKeyChar() == KeyEvent.VK_ENTER) ||
                        ((evt.getKeyChar() == 'º') ||
                                (evt.getKeyChar() == 'ª') ||
                                (evt.getKeyChar() == 'ç') ||
                                (evt.getKeyChar() == 'Ç'))) {
                    JOptionPane.showMessageDialog(null, "Only numbers here", "Error", JOptionPane.ERROR_MESSAGE);
                    resetAll();
                    evt.consume();
                }
                if (!amountTXT.getText().equals("")) {
                    enableOrNotButtons(true);
                    if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
                        convertButton.doClick();
                    }
                } else {
                    enableOrNotButtons(false);
                }
            }
        });

        convertButton.addActionListener(e -> {
            try {
                if (!amountTXT.getText().equals("")) {
                    String currencyTo = Objects.requireNonNull(currenciesBox.getSelectedItem()).toString();

                    Controller ctrl = new Controller(amountTXT.getText(), currencyTo);
                    String result = ctrl.execute();
                    if (result != null) {
                        resultTXT.setText(result + " " + currencyTo);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Write an amount!!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        cleanButton.addActionListener(e -> {
            if (!amountTXT.getText().equals("") || !resultTXT.getText().equals("")) {
                resetAll();
            }
        });
    }

    private void enableOrNotButtons(boolean bool){
        if (bool){
            cleanButton.setEnabled(true);
            convertButton.setEnabled(true);
        }else {
            cleanButton.setEnabled(false);
            convertButton.setEnabled(false);
        }
    }

    private void resetAll() {
        amountTXT.setText("");
        resultTXT.setText("");
        enableOrNotButtons(false);
    }
}
