package ex42;

/*
 *  UCF COP3330 Fall 2021 Assignment 3 Solution
 *  Copyright 2021 Michael Gilday
 */

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

//Exercise 42 - Parsing a Data File. (Program that reads in a list from a file called `exercise42_input.txt` and prints it out in a tabular format.)
class Read {
    public static int entry_amount;

    @Test
    public static ArrayList<String> reader() throws FileNotFoundException { //This method is used for reading in individual lines from the input file.
        File file_input = new File("src/main/java/ex42/exercise42_input.txt"); //Providing an instance for the input file.
        Assertions.assertTrue(file_input.exists());
        Scanner start_scan = new Scanner(file_input); //Creation of a scanner object that will scan through the input file.
        String expected_file_input = "Ling,Mai,55900\n" +
                "Johnson,Jim,56500\n" +
                "Jones,Aaron,46000\n" +
                "Jones,Chris,34500\n" +
                "Swift,Geoffrey,14200\n" +
                "Xiong,Fong,65000\n" +
                "Zarnecki,Sabrina,51500";
        Scanner expected_start_scan = new Scanner(expected_file_input); //Creation of a scanner object that will scan through the expected values.
        entry_amount = 0;
        ArrayList<String> expected_array = new ArrayList<>();
        ArrayList<String> array = new ArrayList<>(); //Creation of the initial array that will hold the first name, last name, and salary found in the input text.
        while(expected_start_scan.hasNextLine() && start_scan.hasNextLine()){
            String input_name = start_scan.nextLine();
            String expected_input_name = expected_start_scan.nextLine();
            String[] split_comma = input_name.split(",");
            String[] expected_split_coma = expected_input_name.split(",");
            array.add(split_comma[0]);
            array.add(split_comma[1]);
            array.add(split_comma[2] + "\n");
            expected_array.add(expected_split_coma[0]);
            expected_array.add(expected_split_coma[1]);
            expected_array.add(expected_split_coma[3] + "\n");
            entry_amount = entry_amount+3;
        }
        start_scan.close();
        expected_start_scan.close();
        Assertions.assertEquals(expected_array, array);
        return array;
    }
}

class Main {
    @Test
    public static void main( String[] args ) throws FileNotFoundException {
        int i = 0;

        //Code block below is used to properly print, in tabular format, the information found in the input text without commas.
        System.out.format("%-10s%-10s%-6s", "Last", "First", "Salary\n--------------------------\n");
        do{
            System.out.format("%-10s%-10s%-6s", Read.reader().get(i), Read.reader().get(i+1), Read.reader().get(i+2));
            i = i+3;
        }while(i < Read.entry_amount);
    }
}