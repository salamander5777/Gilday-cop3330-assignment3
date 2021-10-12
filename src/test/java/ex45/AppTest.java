package ex45;

/*
 *  UCF COP3330 Fall 2021 Assignment 3 Solution
 *  Copyright 2021 Michael Gilday
 */

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

//Exercise 45 - Word Finder. (Program that reads 'exercise45_input.txt' and replaces all instances of the word 'utilize' with the word 'use'.)
class Read {
    public static int line_count;

    public static ArrayList<String> reader() throws FileNotFoundException { //This method is used for reading in individual words from the input file.
        File file_input = new File("src/main/java/ex45/exercise45_input.txt"); //Providing an instance for the input file.
        Assertions.assertTrue(file_input.exists());
        Scanner start_scan = new Scanner(file_input); //Creation of a scanner object that will scan through the input file.
        line_count = 0;
        ArrayList<String> array = new ArrayList<>(); //Creation of the initial array that will hold the information of the input text.
        while(start_scan.hasNextLine()){
            String input_line = start_scan.nextLine();
            String replace_utilize = input_line.replaceAll("utilize","use");
            array.add(replace_utilize + "\n");
            line_count++;
        }
        start_scan.close();
        ArrayList<String> expected_array = new ArrayList<>();
        expected_array.add("One should never use the word \"use\" in writing. Use \"use\" instead.\n");
        expected_array.add("For example, \"She uses an IDE to write her Java programs\" instead of \"She\n");
        expected_array.add("uses an IDE to write her Java programs\".\n");
        Assertions.assertEquals(array, expected_array);
        return array;
    }
}

class Main {
    @Test
    public static void main( String[] args ) throws IOException {
        Scanner start_scan = new Scanner(System.in); //Creation of a scanner object to allow for user input.
        System.out.print("What do you want the output file to be named? ");
        String expected_outputFileName_input = "exercise45output";
        String outputFileName_input = start_scan.nextLine();
        Assertions.assertEquals(expected_outputFileName_input, outputFileName_input);

        Read.reader(); //Calls the method used to replace the word 'utilize' in the document

        Files.deleteIfExists(Path.of(outputFileName_input + ".txt"));
        Path outputPath = Files.createTempFile(outputFileName_input, ".txt");
        FileWriter fileWriter = new FileWriter("src/main/java/ex45/" + outputFileName_input + ".txt");
        FileWriter expected_fileWriter = new FileWriter(String.valueOf(outputPath));
        for(int i = 0; i<Read.line_count; i++){
            fileWriter.write(Read.reader().get(i));
            expected_fileWriter.write(Read.reader().get(i));
        }
        fileWriter.close();
        start_scan.close();
    }
}