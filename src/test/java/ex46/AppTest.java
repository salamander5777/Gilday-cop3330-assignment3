package ex46;

/*
 *  UCF COP3330 Fall 2021 Assignment 3 Solution
 *  Copyright 2021 Michael Gilday
 */

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;
import java.util.Scanner;

//Exercise 46 - Word Frequency Finder. (Program that reads `exercise46_input.txt` and counts the frequency of words in the file.)
class Main {
    public static int word_length; //This is simply used for formatting the later print function.

    @Test
    public static void histogramPrint(Map<String, Integer> sortedWordFrequency){ //This will print the frequencies into a histogram chart.
        for (Map.Entry<String, Integer> storedValue : sortedWordFrequency.entrySet()){
            System.out.format("%-" + (word_length+2) + "s", storedValue.getKey() + ": ");

            for(int i = 0; i < storedValue.getValue(); i++){
                System.out.print("*");
            }
            System.out.print("\n");
        }
    }

    @Test
    public static void main( String[] args ){
        Map<String, Integer> wordFrequency = new HashMap<>(); //Creation of a map that will track each individual word and the amount of times they were input.
        String file_input = "badger badger badger\n" +
                "badger mushroom\n" +
                "mushroom snake badger badger\n" +
                "badger";
        Scanner start_scan = new Scanner(file_input); //Creation of a scanner object that will scan through the input file.
        word_length = 0;

        while(start_scan.hasNextLine()){ //This while loop will read through the input file, adding new words to a hashmap and increasing a frequency counter for each appearance.
            String input_word = start_scan.next();

            if(wordFrequency.containsKey(input_word)){//This will increase the frequency counter.
                int frequencyCount = wordFrequency.get(input_word);
                wordFrequency.put(input_word, ++frequencyCount);
            }
            else{ //This 'else' statement will only be used when the scanned word is new, thus adding it to the hashmap.
                wordFrequency.put(input_word, 1);

                if(input_word.length() > word_length){
                    word_length = input_word.length();
                }
            }
        }
        Map<String, Integer> sortedWordFrequency = new TreeMap<>(wordFrequency);
        histogramPrint(sortedWordFrequency);
        Map<String, Integer> equivalentWordFrequency = new HashMap<>();
        equivalentWordFrequency.put("badger", 7);
        equivalentWordFrequency.put("mushroom", 2);
        equivalentWordFrequency.put("snake", 1);
        assertEquals(sortedWordFrequency, equivalentWordFrequency);
        start_scan.close();
    }
}