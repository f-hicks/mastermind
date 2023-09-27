package com.fhicks.gui;
import javax.swing.*;
import java.util.Random;
import java.awt.*;
import java.awt.event.*;

public class ConfigScreen extends JFrame implements ActionListener{

    static final Random rand = new Random();

    private JButton startButton;
    private JButton exitButton;
    private JPanel optionsPanel;
    private JRadioButton easyModeButton;
    private JRadioButton mediumModeButton;
    private JRadioButton hardModeButton;
    private ButtonGroup modeButtonGroup;
    private JComboBox<Integer> numDigitsComboBox;
    private JLabel numOfDigitsText;

    public ConfigScreen() {
        super("Title Screen");

        // Create components for title
        JLabel titleLabel = new JLabel("Welcome to the Game!");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        startButton = new JButton("Start Game");
        startButton.addActionListener(this);
        exitButton = new JButton("Exit");
        exitButton.addActionListener(this);

        //create componenents for config page
        easyModeButton = new JRadioButton("Easy Mode", false);
        mediumModeButton = new JRadioButton("Medium Mode", true);
        hardModeButton = new JRadioButton("Hard Mode", false);
        modeButtonGroup = new ButtonGroup();
        numOfDigitsText = new JLabel("Select the number of digits in the number to guess: ");
        numOfDigitsText.setHorizontalAlignment(JLabel.CENTER);
        numOfDigitsText.setFont(new Font("Arial", Font.BOLD, 12));
        modeButtonGroup.add(easyModeButton);
        modeButtonGroup.add(mediumModeButton);
        modeButtonGroup.add(hardModeButton);
        Integer[] numDigitsOptions = {3, 4, 5, 6, 7, 8};
        numDigitsComboBox = new JComboBox<>(numDigitsOptions);
        numDigitsComboBox.setSelectedIndex(1);
        optionsPanel = new JPanel(new GridLayout(5, 1, 10, 10));
        optionsPanel.setBorder(BorderFactory.createTitledBorder("Select Options"));
        optionsPanel.add(easyModeButton);
        optionsPanel.add(mediumModeButton);
        optionsPanel.add(hardModeButton);
        optionsPanel.add(numOfDigitsText);
        optionsPanel.add(numDigitsComboBox);
        optionsPanel.setVisible(true);

        // Add components to content pane
        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        contentPane.add(titleLabel, BorderLayout.NORTH);
        JPanel buttonPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        buttonPanel.add(startButton);
        buttonPanel.add(exitButton);
        contentPane.add(optionsPanel, BorderLayout.CENTER);
        contentPane.add(buttonPanel, BorderLayout.SOUTH);
        setContentPane(contentPane);

        // Set window properties
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            // Start game
            dispose(); // Close the title screen
            //get number of digits
            int numDigits = (int) numDigitsComboBox.getSelectedItem();
            //get difficulty
            boolean easyMode = false;
            if (easyModeButton.isSelected()) {
                easyMode = true;
            }

            //generate number
            String num = String.valueOf(rand.nextInt(Integer.valueOf("9".repeat(numDigits))));

            new GameScreen(numDigits, 0, easyMode, num);
            // Add code to start the game here
        } else if (e.getSource() == exitButton) {
            // Exit
            dispose(); // Close the title screen
            System.exit(0);
        }
    }
}
