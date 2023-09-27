package com.fhicks.util;
public class CheckGuess {
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
}
