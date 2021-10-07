package ex45;

/*
 *  UCF COP3330 Fall 2021 Assignment 3 Solution
 *  Copyright 2021 Michael Gilday
 */

import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

//Exercise 45 - Word Finder. (Program that reads 'exercise45_input.txt' and replaces all instances of the word 'utilize' with the word 'use'.)
class Main {
    private static int word_count;

    @Test
    public static ArrayList<String> reader(){ //This method is used for reading in individual words from the input file.
        String file_input = "One should never utilize the word \"utilize\" in writing. Use \"use\" instead.\n" +
                "For example, \"She uses an IDE to write her Java programs\" instead of \"She\n" +
                "utilizes an IDE to write her Java programs\".";
        Scanner start_scan = new Scanner(file_input); //Creation of a scanner object that will scan through the input file.
        word_count = 0;
        ArrayList<String> array = new ArrayList<>(); //Creation of the initial array that will hold names found in the input text.
        while(start_scan.hasNextLine()){
            String input_name = start_scan.nextLine();
            String replace_utilize = input_name.replaceAll("utilize","use");
            array.add(replace_utilize + "\n");
            word_count++;
        }
        start_scan.close();
        return array;
    }

    @Test
    public static void main( String[] args ) throws IOException {
        System.out.print("What do you want the output file to be named? ");
        String outputFileName_input = "exercise45output";

        reader(); //Calls the method used to replace the word 'utilize' in the document

        Files.deleteIfExists(Path.of("src/main/website/" + outputFileName_input + ".txt"));
        FileWriter fileWriter = new FileWriter("src/main/java/ex45/" + outputFileName_input + ".txt");
        for(int i = 0; i<word_count; i++){
            fileWriter.write(reader().get(i));
        }
        fileWriter.close();
    }
}