package com.fhicks.gui;

import com.fhicks.util.CustomCellRenderer;

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
    private int currentGuess = 0;
    private ArrayList<Integer> secretCode = new ArrayList<>();
    JTextField textBox = new JTextField();

    public GameScreen(int numDigits, int numGuesses, boolean easyMode, String num) {
        this.numDigits = numDigits;

        for (char c : num.toCharArray()) {
            secretCode.add(Character.getNumericValue(c));
        }

        panel = new JPanel();
        textField = new JTextField(numDigits);
        textField.addActionListener(this);
        button = new JButton("Submit");
        button.addActionListener(this);

        // Create a table with a DefaultTableModel
        tableModel = new DefaultTableModel(10, numDigits);
        table = new JTable(tableModel);
        table.setTableHeader(null);
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer());
        table.setValueAt("a string", 0, 0);


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
                tableModel.setValueAt(digit, currentGuess, i);
                if (digit == secretCode.get(i)) {
                    table.getColumnModel().getColumn(i).setCellRenderer(new CustomCellRenderer(currentGuess, i, Color.GREEN));
                } else if (secretCode.contains(digit)) {
                    table.getColumnModel().getColumn(i).setCellRenderer(new CustomCellRenderer(currentGuess, i, Color.YELLOW));
                } else {
                    table.getColumnModel().getColumn(i).setCellRenderer(new CustomCellRenderer(currentGuess, i, Color.RED));
                }
                
            }
            currentGuess++;


            //check if guess is correct
            System.out.println(input + " " + secretCode.toString().replaceAll("[\\[\\], ]", ""));
            if (input.equals(secretCode.toString().replaceAll("[\\[\\], ]", ""))) {
                // Handle end of the game (correct guess)
                JOptionPane.showMessageDialog(this, "Correct guess!\n It took you " + currentGuess + " guesses.");
                dispose();
                System.exit(0);
            }


            if (currentGuess == 10) {
                // Handle end of the game (no more guesses)
                JOptionPane.showMessageDialog(this, "Out of guesses. The secret code was: " + secretCode.toString());
                dispose();
                System.exit(1);
            } else {
                textField.setText("");
                table.repaint();
            }
        }
    }    
}
