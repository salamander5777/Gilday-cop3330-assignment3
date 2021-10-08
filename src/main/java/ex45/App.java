package ex45;

/*
 *  UCF COP3330 Fall 2021 Assignment 3 Solution
 *  Copyright 2021 Michael Gilday
 */

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

//Exercise 45 - Word Finder. (Program that reads 'exercise45_input.txt' and replaces all instances of the word 'utilize' with the word 'use'.)
class Main {
    private static int line_count;

    public static ArrayList<String> reader() throws FileNotFoundException { //This method is used for reading in individual words from the input file.
        File file_input = new File("src/main/java/ex45/exercise45_input.txt"); //Providing an instance for the input file.
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

    public static void main( String[] args ) throws IOException {
        Scanner start_scan = new Scanner(System.in); //Creation of a scanner object to allow for user input.
        System.out.print("What do you want the output file to be named? ");
        String outputFileName_input = start_scan.nextLine();

        reader(); //Calls the method used to replace the word 'utilize' in the document

        Files.deleteIfExists(Path.of("src/main/website/" + outputFileName_input + ".txt"));
        FileWriter fileWriter = new FileWriter("src/main/java/ex45/" + outputFileName_input + ".txt");
        for(int i = 0; i<line_count; i++){
            fileWriter.write(reader().get(i));
        }
        fileWriter.close();
        start_scan.close();
    }
}