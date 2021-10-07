package ex44;

/*
 *  UCF COP3330 Fall 2021 Assignment 3 Solution
 *  Copyright 2021 Michael Gilday
 */

import org.json.JSONException;
import org.junit.jupiter.api.Test;

import com.google.gson.stream.JsonReader;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

//Exercise 44 - Product Search. (Program that takes a product name as input and retrieves the current price and quantity for that product.)
class Main {
    @Test
    public static boolean productSearch(String product_name) throws IOException, JSONException { //Creates JsonReader and loops through the Json text.
        String actualJSONReader = "{\n" +
                "  \"products\" : [\n" +
                "    {\"name\": \"Widget\", \"price\": 25.00, \"quantity\": 5 },\n" +
                "    {\"name\": \"Thing\", \"price\": 15.00, \"quantity\": 5 },\n" +
                "    {\"name\": \"Doodad\", \"price\": 5.00, \"quantity\": 10 }\n" +
                "  ]\n" +
                "}";
        JSONAssert.assertEquals("{\n" +
                "  \"products\" : [\n" +
                "    {\"name\": \"Widget\", \"price\": 25.00, \"quantity\": 5 },\n" +
                "    {\"name\": \"Thing\", \"price\": 15.00, \"quantity\": 5 },\n" +
                "    {\"name\": \"Doodad\", \"price\": 5.00, \"quantity\": 10 }\n" +
                "  ]\n" +
                "}", actualJSONReader, JSONCompareMode.LENIENT);
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

                if(isFound(product_name, name)) {
                    System.out.print("Name: " + name + "\nPrice: " + price + "\nQuantity: " + quantity);
                    return true;
                }
            }
        }
        System.out.print("Sorry, that product was not found in our inventory.\n");
        return false;
    }

    @Test
    public static boolean isFound(String product_name, String name){ //This method returning a 'true' value will allow a print to occur.
        return Objects.equals(product_name, name);
    }

    @Test
    public static void main( String[] args ) throws IOException, JSONException {
        Scanner start_scan = new Scanner(System.in); //Creation of a scanner object to allow for user input.
        String product_name;

        do{
            System.out.print("What is the product name? ");
            product_name = "Widget";
        }while(productSearch(product_name));

        start_scan.close();
    }
}