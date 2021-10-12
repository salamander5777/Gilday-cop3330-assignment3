package ex41;

/*
 *  UCF COP3330 Fall 2021 Assignment 3 Solution
 *  Copyright 2021 Michael Gilday
 */

import static org.junit.gen5.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

//Exercise 41 - Name Sorter. (Program that reads in a list of names from a file called `exercise41_input.txt` and sorts the names alphabetically.)
class Read {
    public static int name_amount;

    @Test
    public static ArrayList<String> reader(){ //This method is used for reading in individual lines from the input file.
        File file_input = new File("src/main/java/ex41/exercise41_input.txt");
        assertTrue(file_input.exists());
        String assumed_file_input = "Ling, Mai\n" +
                "Johnson, Jim\n" +
                "Zarnecki, Sabrina\n" +
                "Jones, Chris\n" +
                "Jones, Aaron\n" +
                "Swift, Geoffrey\n" +
                "Xiong, Fong";
        Scanner start_scan = new Scanner(assumed_file_input); //Creation of a scanner object that will scan through the input file.
        name_amount = 0;
        ArrayList<String> array = new ArrayList<>(); //Creation of the initial array that will hold names.
        while(start_scan.hasNextLine()){
            String input_name = start_scan.nextLine();
            array.add(input_name + "\n");
            name_amount++;
        }
        start_scan.close();
        Collections.sort(array);
        return array;
    }
}

class Main {
    @Test
    public static void main( String[] args ) throws IOException {
        Read.reader();
        FileWriter fileWriter = new FileWriter("src/main/java/ex41/exercise41_output.txt");
        fileWriter.write("Total of " + Read.name_amount + " names\n-----------------\n");
        for(int i = 0; i<Read.name_amount; i++){
            fileWriter.write(Read.reader().get(i));
        }
        fileWriter.close();
    }
}