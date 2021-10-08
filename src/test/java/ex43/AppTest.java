package ex43;

/*
 *  UCF COP3330 Fall 2021 Assignment 3 Solution
 *  Copyright 2021 Michael Gilday
 */

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

//Exercise 43 - Website Generator. (Program that generates a website skeleton using user provided input.)
class Main {
    @Test
    public static void folder_creator(String site_name, String js_answer, String css_answer) throws IOException { //This method is used to create the separate potential folders.
        Path path1 = Files.createTempDirectory("website/" + site_name);
        assertTrue(Files.exists(path1));
        System.out.print("Created ./website/" + site_name);

        if(js_answer.matches("y")){
            Path path2 = Files.createTempDirectory(site_name + "/js");
            assertTrue(Files.exists(path2));
            System.out.print("\nCreated ./website/" + site_name + "/js");
        }
        if(css_answer.matches("y")){
            Path path3 = Files.createTempDirectory(site_name + "/css");
            assertTrue(Files.exists(path3));
            System.out.print("\nCreated ./website/" + site_name + "/css");
        }
    }

    @Test
    public static void html_index(String site_name, String author_name) throws IOException { //This method is used to create the 'index.html' file.
        Path indexPath = Files.createTempFile("website/" + site_name + "/index", ".html");
        assertTrue(Files.exists(indexPath));
        BufferedWriter bw = new BufferedWriter(new FileWriter(String.valueOf(indexPath)));
        bw.write("<html>\n<head>\n  <title>\n       " + site_name + "\n  </title>\n</head>\n    <meta name=\"author\" content=" + "\"" + author_name + "\"" + ">\n"+ "</html>");
        System.out.print("\nCreated ./website/" + site_name + "/index.html");
        bw.close();
    }

    @Test
    public static void main( String[] args ) throws IOException {
        System.out.print("Site name: awesomeco");
        String site_name = "awesomeco"; //Sets the site name.
        System.out.print("Author: Max Power");
        String author_name = "Max Power"; //Sets the author name.
        System.out.print("Do you want a folder for JavaScript? y");
        String js_answer = "y"; //Creates the Javascript folder.
        System.out.print("Do you want a folder for CSS? y");
        String css_answer = "y"; //Creates the CSS folder.

        folder_creator(site_name, js_answer, css_answer);
        html_index(site_name, author_name);
    }
}