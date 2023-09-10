package com.example.geektrust;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

import com.example.geektrust.DirectionEnum.Direction;
import com.example.geektrust.CommandEnum.Command;

public class Main {
    public static void main(String[] args) {
        GMan gman = new GMan();
        InputParser inputParser = new InputParser(gman);

        //Sample code to read from file passed as command line argument
        try {
            // the file to be opened for reading
            FileInputStream fis = new FileInputStream(args[0]);
            Scanner sc = new Scanner(fis); // file to be scanned
            // returns true if there is another line to read
            while (sc.hasNextLine()) {
               //Add your code here to process input commands
                String line = sc.nextLine();
                inputParser.parseAndProcessInput(line);
            }
            sc.close(); // closes the scanner

        } catch (IOException e) {
            System.out.println("Error while reading the file " + e.getMessage());
        }
        catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }

    }

}
