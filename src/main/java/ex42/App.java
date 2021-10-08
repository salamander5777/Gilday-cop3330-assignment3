package ex42;

/*
 *  UCF COP3330 Fall 2021 Assignment 3 Solution
 *  Copyright 2021 Michael Gilday
 */

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

//Exercise 42 - Parsing a Data File. (Program that reads in a list from a file called `exercise42_input.txt` and prints it out in a tabular format.)
class Main {
    private static int entry_amount;

    public static ArrayList<String> reader() throws FileNotFoundException { //This method is used for reading in individual lines from the input file.
        File file_input = new File("src/main/java/ex42/exercise42_input.txt"); //Providing an instance for the input file.
        Scanner start_scan = new Scanner(file_input); //Creation of a scanner object that will scan through the input file.
        entry_amount = 0;
        ArrayList<String> array = new ArrayList<>(); //Creation of the initial array that will hold the first name, last name, and salary found in the input text.
        while(start_scan.hasNextLine()){
            String input_name = start_scan.nextLine();
            String[] split_comma = input_name.split(",");
            array.add(split_comma[0]);
            array.add(split_comma[1]);
            array.add(split_comma[2] + "\n");
            entry_amount = entry_amount+3;
        }
        start_scan.close();
        return array;
    }

    public static void main( String[] args ) throws IOException {
        int i = 0;

        //Code block below is used to properly print, in tabular format, the information found in the input text without commas.
        System.out.format("%-10s%-10s%-6s", "Last", "First", "Salary\n--------------------------\n");
        do{
            System.out.format("%-10s%-10s%-6s", reader().get(i), reader().get(i+1), reader().get(i+2));
            i = i+3;
        }while(i < entry_amount);
    }
}