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
class Read {
    public static int line_count;

    public static ArrayList<String> reader() { //This method is used for reading in individual words from the input file.
        String file_input = "One should never utilize the word \"utilize\" in writing. Use \"use\" instead.\n" +
                "For example, \"She uses an IDE to write her Java programs\" instead of \"She\n" +
                "utilizes an IDE to write her Java programs\".";
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
        return array;
    }
}

class Main {
    @Test
    public static void main( String[] args ) throws IOException {
        System.out.print("What do you want the output file to be named? exercise45output");
        String outputFileName_input = "exercise45output";

        Read.reader(); //Calls the method used to replace the word 'utilize' in the document

        Files.deleteIfExists(Path.of(outputFileName_input + ".txt"));
        Path outputPath = Files.createTempFile(outputFileName_input, ".txt");
        FileWriter fileWriter = new FileWriter(String.valueOf(outputPath));
        for(int i = 0; i<Read.line_count; i++){
            fileWriter.write(Read.reader().get(i));
        }
        fileWriter.close();
    }
}