package ex44;

/*
 *  UCF COP3330 Fall 2021 Assignment 3 Solution
 *  Copyright 2021 Michael Gilday
 */

import com.google.gson.stream.JsonReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

//Exercise 44 - Product Search. (Program that takes a product name as input and retrieves the current price and quantity for that product.)
class Search {
    public static boolean productSearch(String product_name) throws IOException{ //Creates JsonReader and loops through the Json text.
        try(JsonReader JSONReader = new JsonReader(new FileReader("src/main/java/ex44/exercise44_input.json"))) {
            JSONReader.beginObject();
            JSONReader.nextName();
            JSONReader.beginArray();
            String name = null;
            Double price = null;
            int quantity = 0;

            while(JSONReader.hasNext()) {
                JSONReader.beginObject();

                while (JSONReader.hasNext()) {
                    String n = JSONReader.nextName();
                    if (n.equals("name")) {
                        name = JSONReader.nextString();
                    }

                    if (n.equals("price")) {
                        price = JSONReader.nextDouble();
                    }

                    if (n.equals("quantity")) {
                        quantity = JSONReader.nextInt();
                    }
                }
                JSONReader.endObject();

                if(Objects.equals(product_name, name)) {
                    System.out.print("Name: " + name + "\nPrice: " + price + "\nQuantity: " + quantity);
                    return false;
                }
            }
        }
        System.out.print("Sorry, that product was not found in our inventory.\n");
        return true;
    }
}

class Main {
    public static String capitalize(String product_name) { //This method is used to capitalize the first letter of the string, so it matches Json formatting.
        String firstLetter = product_name.substring(0,1);
        String restOfLetters = product_name.substring(1);
        firstLetter = firstLetter.toUpperCase();
        product_name = firstLetter + restOfLetters;
        return product_name;
    }

    public static void main( String[] args ) throws IOException{
        Scanner start_scan = new Scanner(System.in); //Creation of a scanner object to allow for user input.
        String product_name;

        do{
            System.out.print("What is the product name? ");
            product_name = start_scan.nextLine(); //Reads in the product name.
            product_name = capitalize(product_name);
        }while(Search.productSearch(product_name));

        start_scan.close();
    }
}