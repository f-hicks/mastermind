package com.fhicks.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GameScreen extends JFrame implements ActionListener {
    private JPanel panel;
    private JTextField textField;
    private JButton button;
    private JTable table;
    private DefaultTableModel tableModel;

    private int numDigits;
    private int numGuesses;
    private int currentGuess = 0;
    private ArrayList<Integer> secretCode = new ArrayList<>();

    public GameScreen(int numDigits, int numGuesses, boolean easyMode, String num) {
        this.numDigits = numDigits;
        this.numGuesses = numGuesses;

        for (char c : num.toCharArray()) {
            secretCode.add(Character.getNumericValue(c));
        }

        panel = new JPanel();
        textField = new JTextField(numDigits);
        textField.addActionListener(this);
        button = new JButton("Submit");
        button.addActionListener(this);

        // Create a table with a DefaultTableModel
        tableModel = new DefaultTableModel(numGuesses, numDigits);
        table = new JTable(tableModel);
        table.setTableHeader(null);

        for (int i = 0; i < numGuesses; i++) {
            tableModel.setValueAt(i + 1, i, 0); // Set row numbers
        }

        // Set up renderer to color the cells
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                int guess = row;
                if (guess < currentGuess) {
                    int digit = (int) tableModel.getValueAt(row, column);
                    if (digit == secretCode.get(column - 1)) {
                        comp.setBackground(Color.GREEN);
                    } else if (secretCode.contains(digit)) {
                        comp.setBackground(Color.YELLOW);
                    } else {
                        comp.setBackground(Color.WHITE);
                    }
                } else {
                    comp.setBackground(Color.WHITE);
                }
                return comp;
            }
        };

        // Apply renderer to all cells except the first column
        for (int i = 0; i <= numDigits-1; i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);
        }

        panel.add(textField);
        panel.add(button);
        panel.add(new JScrollPane(table));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(panel);
        pack();
        setLocationRelativeTo(null); // Center the frame
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String input = textField.getText();
        if (input.length() != numDigits) {
            // Handle input error (incorrect length)
            JOptionPane.showMessageDialog(this, "Input must be exactly " + numDigits + " digits.");
        } else {
            // Store the guess in the table
            for (int i = 0; i < numDigits; i++) {
                int digit = Character.getNumericValue(input.charAt(i));
                System.out.println(digit);
                tableModel.setValueAt(digit, -1, -1);
            }

            currentGuess++;

            if (currentGuess == numGuesses) {
                // Handle end of the game (no more guesses)
                JOptionPane.showMessageDialog(this, "Out of guesses. The secret code was: " + secretCode.toString());
                dispose();
            } else {
                textField.setText("");
                table.repaint();
            }
        }
    }    
}
