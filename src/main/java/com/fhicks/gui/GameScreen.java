package com.fhicks.gui;
import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;


public class GameScreen implements ActionListener{
    private JFrame frame;
    private JPanel panel;
    private JLabel label;
    private JTextField textField;
    private JButton button;

    public GameScreen(final int numDigits, final int numGuesses, final boolean easyMode, final String num) {
        frame = new JFrame("Mastermind");
        panel = new JPanel();
        label = new JLabel("Enter your guess: ");
        textField = new JTextField(numDigits);
        textField.addActionListener(this);
        button = new JButton("Submit");
        button.addActionListener(this);

        panel.add(label);
        panel.add(textField);
        panel.add(button);

        // Limit input to numbers only
        ((AbstractDocument) textField.getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                String newText = fb.getDocument().getText(0, fb.getDocument().getLength()) + text;
                if (newText.matches("\\d*")) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }
        });

        // Limit number of digits
        textField.setDocument(new PlainDocument() {
            @Override
            public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
                if (getLength() + str.length() <= numDigits) {
                    super.insertString(offs, str, a);
                }
            }
        });
        
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Rectangle screenRect = ge.getMaximumWindowBounds();
        int x = (screenRect.width - frame.getWidth()) / 2;
        int y = (screenRect.height - frame.getHeight()) / 2;
        frame.setLocation(x, y);

        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
    public void actionPerformed(ActionEvent e) {
            System.out.println("You entered: " + textField.getText());
            frame.dispose();
    }
}
