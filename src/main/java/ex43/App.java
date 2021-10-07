package ex43;

/*
 *  UCF COP3330 Fall 2021 Assignment 3 Solution
 *  Copyright 2021 Michael Gilday
 */

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

//Exercise 43 - Website Generator. (Program that generates a website skeleton using user provided input.)
class Main {
    public static void folder_creator(String site_name, String js_answer, String css_answer) throws IOException { //This method is used to create the separate potential folders.
        Files.createDirectories(Paths.get("src/main/website/" + site_name));
        System.out.print("Created ./website/" + site_name);

        if(js_answer.matches("y")){
            Files.createDirectories(Paths.get("src/main/website/" + site_name + "/js"));
            System.out.print("\nCreated ./website/" + site_name + "/js");
        }
        if(css_answer.matches("y")){
            Files.createDirectories(Paths.get("src/main/website/" + site_name + "/css"));
            System.out.print("\nCreated ./website/" + site_name + "/css");
        }
    }

    public static void html_index(String site_name, String author_name) throws IOException { //This method is used to create the 'index.html' file.
        Files.deleteIfExists(Path.of("src/main/website/" + site_name + "/index.html"));
        Files.createFile(Paths.get("src/main/website/" + site_name + "/index.html"));
        BufferedWriter bw = new BufferedWriter(new FileWriter("src/main/website/" + site_name + "/index.html"));
        bw.write("<html>\n<head>\n  <title>\n       " + site_name + "\n  </title>\n</head>\n    <meta name=\"author\" content=" + "\"" + author_name + "\"" + ">\n"+ "</html>");
        System.out.print("\nCreated ./website/" + site_name + "/index.html");
        bw.close();
    }


    public static void main( String[] args ) throws IOException {
        Scanner start_scan = new Scanner(System.in); //Creation of a scanner object to allow for user input.

        System.out.print("Site name: ");
        String site_name = start_scan.nextLine(); //Reads in the name used for the site.
        System.out.print("Author: ");
        String author_name = start_scan.nextLine(); //Reads in the name of the site's author.
        System.out.print("Do you want a folder for JavaScript? ");
        String js_answer = start_scan.nextLine(); //Reads in whether the user wants a JavaScript folder for the site.
        System.out.print("Do you want a folder for CSS? ");
        String css_answer = start_scan.nextLine(); //Reads in whether the user wants a CSS folder for the site.

        folder_creator(site_name, js_answer, css_answer);
        html_index(site_name, author_name);
        start_scan.close();
    }
}