package com.fhicks;

import java.util.Random;
import java.util.Scanner;

import com.fhicks.gui.TitleScreen;
import com.fhicks.util.CheckGuess;
import com.fhicks.util.AddToLeaderboard;
import com.fhicks.util.PrefixNumZeros;

public class App {
    static final Random rand = new Random();
    static final Scanner input = new Scanner(System.in);
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

        num = PrefixNumZeros.prefixNumberWithZeros(num, numlength);
        //System.out.println("The number is " + num);
        


        while (!correct) {
            System.out.print("Please enter your guess: ");
            String guess = input.nextLine();
            ++numGuesses;
            if (guess.equalsIgnoreCase(num)) {
                correct = true;
                continue;
            } else {
                CheckGuess.checkGuess(num, guess, easyMode);
            }
        }
        System.out.println("You guessed correctly in "+numGuesses+" Guesses");
        System.out.print("Do you want to be added to the leaderboards? (y/n) ");
        String leaderboards = input.nextLine();
        if (leaderboards.equalsIgnoreCase("y")) {
            System.out.print("Please enter your name: ");
            String name = input.nextLine();
            AddToLeaderboard.addToLeaderboard(name, numGuesses);  

        }
        
    }
}
