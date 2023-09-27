package com.fhicks.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class AddToLeaderboard {
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
}
