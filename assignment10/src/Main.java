//
// Title:           Dictionary
// Files:           Main.java, Dictionary.java, DefinitionNode.java
// Course:          CS 300, Fall 2017
//
// Author:          Jay Desai
// Email:           jdesai2@wisc.edu
// Lecturer's Name: Gary Dahl
//

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static String USAGE = "==================Dictionary =================\n"
                          + "Enter 'I' to Insert a definition in the dictionary\n"
                          + "Enter 'L' to Lookup a definition in the dictionary\n"
                          + "Enter 'A' to print All the words in the dictionary\n"
                          + "Enter ‘C’ to print the Count of all words in the dictionary\n"
                          + "Enter 'Q' to quit the program\n"
                          + "===========================================\n"
                          + "Please enter your command: ";

    public static void main(String[] args) {
        Dictionary myDict = new Dictionary();
        Scanner console = new Scanner(System.in);
        String answer;
        while (true) {
            System.out.print(USAGE);
            answer = console.nextLine();
            if (answer.trim().isEmpty()) {
                System.out.println("WARNING: Unrecognized command.");
                continue;
            }
            String[] words = answer.split(" ", 3);
            char firstChar = words[0].toLowerCase().charAt(0);
            switch (firstChar) {
                case 'i': // insert.
                    myDict.insert(words[1], words[2]);
                    break;
                case 'l': // lookup
                    if (myDict.getWordCount() == 0) {
                        System.out.println("There are no definitions in this empty dictionary.");
                    } else {
                        String my = myDict.lookup(words[1]);
                        if (my == null) {
                            System.out.println("No definition found for the word " + words[1]);
                        } else {
                            System.out.println(my);
                        }
                    }
                    break;
                case 'a': // print all words
                    ArrayList<String> list = myDict.getAllWords();
                    if (list == null) {
                        System.out.println("Dictionary is empty.");
                    } else {
                        for (String s : list) {
                            System.out.print(s + " ");
                        }
                        System.out.println();
                    }
                    break;
                case 'c': // print number of elements in dictionary
                    System.out.println(myDict.getWordCount());
                    break;
                case 'q':
                    System.exit(0);
            }

        }
    }

}
