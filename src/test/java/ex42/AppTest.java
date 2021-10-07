package ex42;

/*
 *  UCF COP3330 Fall 2021 Assignment 3 Solution
 *  Copyright 2021 Michael Gilday
 */

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Scanner;

//Exercise 42 - Parsing a Data File. (Program that reads in a list from a file called `exercise42_input.txt` and prints it out in a tabular format.)
class Main {
    private static int name_amount;

    @Test
    public static ArrayList<String> reader(){ //This method is used for reading in individual lines from the input file.
        String file_input = "Ling,Mai,55900\n" +
                "Johnson,Jim,56500\n" +
                "Jones,Aaron,46000\n" +
                "Jones,Chris,34500\n" +
                "Swift,Geoffrey,14200\n" +
                "Xiong,Fong,65000\n" +
                "Zarnecki,Sabrina,51500";
        Scanner start_scan = new Scanner(file_input); //Creation of a scanner object that will scan through the input file.
        name_amount = 0;
        ArrayList<String> array = new ArrayList<>(); //Creation of the initial array that will hold the first name, last name, and salary found in the input text.
        while(start_scan.hasNextLine()){
            String input_name = start_scan.nextLine();
            String[] split_comma = input_name.split(",");
            array.add(split_comma[0]);
            array.add(split_comma[1]);
            array.add(split_comma[2] + "\n");
            name_amount = name_amount+3;
        }
        start_scan.close();
        return array;
    }

    @Test
    public static void main( String[] args ){
        int i = 0;

        //Code block below is used to properly print, in tabular format, the information found in the input text without commas.
        System.out.format("%-10s%-10s%-6s", "Last", "First", "Salary\n--------------------------\n");
        do{
            System.out.format("%-10s%-10s%-6s", reader().get(i), reader().get(i+1), reader().get(i+2));
            i = i+3;
        }while(i < name_amount);
    }
}