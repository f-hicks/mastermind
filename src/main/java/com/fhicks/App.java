package com.fhicks;

import java.util.Random;
import java.util.Scanner;

import com.fhicks.gui.TitleScreen;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class App {
    static final Random rand = new Random();
    static final Scanner input = new Scanner(System.in);

    public static String prefixNumberWithZeros(String num, int numlength) { 
        if (num.length() < numlength) { //prefix numbers that are not already 4/5 digits with 0's
            StringBuffer sb = new StringBuffer();
            sb.append("0".repeat((numlength-num.length()))); 
            sb.append(num);
            num = sb.toString();
        }
        return num;
    }

    public static void addToLeaderboard(String name, int numGuesses) {
        try {
            File leaderboardFile = new File("leaderboard.txt");
            leaderboardFile.createNewFile();
            FileWriter FileWriters = new FileWriter("leaderboard.txt",true);
            FileWriters.write(name+","+String.valueOf(numGuesses)+"\n");
            FileWriters.close();
            System.out.println("Successfully added you to the leaderboard!");
        }
        catch (IOException e) {
            System.out.println("ERROR: unable to create file ");
        } 
    }

    public static int checkGuess(String num, String guess, boolean easyMode) {
        int numCorrect = 0;
        int i = 0;
        while (i <= 3) {
            if (num.charAt(i) == guess.charAt(i)) {
                if (easyMode) {
                    System.out.println("The digit "+num.charAt(i)+" is correct at position "+(i+1));
                }
                ++numCorrect;
            }
            ++i;
        }
        System.out.println("You got "+numCorrect+" digits correct.\nPlease try again.");
        return numCorrect;
    }

    public static void main(String[] args) {
        new TitleScreen();

        boolean correct = false;
        int numGuesses = 0;
        boolean easyMode = false;
        int numlength = 4;
        
        System.out.print("Do you want to play in easy mode? (y/n) ");
        if (input.nextLine().equalsIgnoreCase("y")) {
            easyMode = true;
        } else {
            System.out.print("Do you want to play in hard mode? (y/n) ");
            if ( input.nextLine().equalsIgnoreCase("y")){
                numlength = 5;
            }
        }
    

        String num = String.valueOf(rand.nextInt(Integer.valueOf("9".repeat(numlength))));

        num = prefixNumberWithZeros(num, numlength);
        //System.out.println("The number is " + num);
        


        while (!correct) {
            System.out.print("Please enter your guess: ");
            String guess = input.nextLine();
            ++numGuesses;
            if (guess.equalsIgnoreCase(num)) {
                correct = true;
                continue;
            } else {
                checkGuess(num, guess, easyMode);
            }
        }
        System.out.println("You guessed correctly in "+numGuesses+" Guesses");
        System.out.print("Do you want to be added to the leaderboards? (y/n) ");
        String leaderboards = input.nextLine();
        if (leaderboards.equalsIgnoreCase("y")) {
            System.out.print("Please enter your name: ");
            String name = input.nextLine();
            addToLeaderboard(name, numGuesses);  

        }
        
    }
}
