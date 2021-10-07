package ex41;

/*
 *  UCF COP3330 Fall 2021 Assignment 3 Solution
 *  Copyright 2021 Michael Gilday
 */

import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

//Exercise 41 - Name Sorter. (Program that reads in a list of names from a file called `exercise41_input.txt` and sorts the names alphabetically.)
class Main {
    private static int name_amount;

    @Test
    public static ArrayList<String> reader() { //This method is used for reading in individual lines from the input file.
        String file_input = "Ling, Mai\n" +
                "Johnson, Jim\n" +
                "Zarnecki, Sabrina\n" +
                "Jones, Chris\n" +
                "Jones, Aaron\n" +
                "Swift, Geoffrey\n" +
                "Xiong, Fong";
        Scanner start_scan = new Scanner(file_input); //Creation of a scanner object that will scan through the input file.
        name_amount = 0;
        ArrayList<String> array = new ArrayList<>(); //Creation of the initial array that will hold names found in the input text.
        while(start_scan.hasNextLine()){
            String input_name = start_scan.nextLine();
            array.add(input_name + "\n");
            name_amount++;
        }
        start_scan.close();
        Collections.sort(array);
        return array;
    }

    @Test
    public static void main( String[] args ) throws IOException {
        reader();
        FileWriter fileWriter = new FileWriter("src/test/java/ex41/exercise41_output.txt");
        fileWriter.write("Total of " + name_amount + " names\n-----------------\n");
        for(int i = 0; i<name_amount; i++){
            fileWriter.write(reader().get(i));
        }
        fileWriter.close();
    }
}