//
// Title:           Generating Philosophy
// Files:           Main.java EvenNumberGenerator.java
//                  Generator.java NumberGenerator.java
// Course:          CS 300, Fall 2017
//
// Author:          Jay Desai
// Email:           jdesai2@wisc.edu
// Lecturer's Name: Gary Dahl
//

import java.util.function.Function;
import java.util.Scanner;

/**
 * The following application proves the Mathematical theory that 97% or
 * random starting Wikipedia pages will lend on page 'Philosophy'.
 * This class is responsible for taking input from the command line and
 * proving the above theory.
 */
public class Main {
    // Constants.
    private static int MAX_LENGTH = 100;
    private static String WIKI_PREFIX = "/wiki/";

    /**
     * main - This is the main program which will ask for starting
     *        string from console and generate the wiki pages.
     *        Main program will terminate successfully if it found the
     *        match in first 100 links or if no link is found, it exits with -1.
     *
     * @param args[] - unused currently.
     *
     * @throws NoSuchElementException - if 'Philosophy' page is not reached.
     *
     */
    public static void main(String[] args) {
        System.out.print("Please enter a Wikipedia topic: ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String newString = "/wiki/".concat(input.trim().replaceAll(" ", "_"));
        int count = 0;
        System.out.println("\n" + count + ": " + newString);
        for (String s : new Generator<String>(MAX_LENGTH, newString, new NextWikiLinkFunction())) {
            System.out.println(++count + ": " + s.replaceAll(" ", "_"));
            if (s.startsWith("/wiki/Philosophy")) {
                System.exit(0);
            } else if (s.startsWith("FAILED")) {
                System.exit(-1);
            }
        }
    }
}

class DoubleFunction implements Function<Integer, Integer> {
    public Integer apply(Integer input) {
        return input * 2;
    }
}

class AddExclamationFunction implements Function<String, String> {

    public String apply(String value) {
        return value.concat("!");
    }
}
