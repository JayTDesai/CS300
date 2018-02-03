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

import java.util.NoSuchElementException;
import java.util.function.Function;
import java.util.Iterator;

/**
 * NumberGenerator - It generates single even number each time its next()
 * method is called.
 * This class implements the Iterable<> ana Iterator<> interfaces.
 */

public class NumberGenerator implements Iterable<Integer>, Iterator<Integer> {
    private int numberOfEvens;
    private int currentIndex;
    private int currentValue;
    private Function<Integer, Integer> nextFunction; // Function<T,T> which generates next number.

    /**
     * Initializes a new NumberGenerator to return a single even number
     * each time it's next() method is called.  The first even number returned
     * in this way is firstEven.  Subsequent even numbers returned in this way
     * will be the smallest even number that is larger than the previous.
     * <p>
     * After numberOfEvens numbers have been generated and returned from this
     * next() method, the generator will end: its hasNext() method will return
     * false, and its next() method will throw a NoSuchElementException when
     * called after this point.
     *
     * @param numberOfEvens - the number of evens that can be generated
     * @param firstEven - the first and smallest even that will be generated
     * @throws IllegalArgumentException - when numberOfEvens is negative.
     */
    public NumberGenerator(int numberOfEvens, Integer firstEven, Function <Integer, Integer> func)
                           throws IllegalArgumentException {
        if (numberOfEvens < 0) {
            throw new IllegalArgumentException("numberOfEvens");
        }
        this.numberOfEvens = numberOfEvens;
        this.currentValue = firstEven % 2 == 0 ? firstEven : firstEven + 1;
        currentIndex = 0;
        nextFunction = func;
    }

    /**
     * Implement the abstract method of 'Iterable' interface which returns the Iterator.
     * We are returning current class itself as we have implemented Iterator interface.
     */
    public Iterator<Integer> iterator() {
        return this;
    }

    /**
     * Implement the abstract method of 'Iterator' class 'hasNext'.
     *
     * @returns true if the iteration has more elements.
     */
    public boolean hasNext() {
        return currentIndex < numberOfEvens;
    }

    /**
     * Implement the abstract method of 'Iterator' class 'next'.
     *
     * @returns the next element in the iteration.
     *
     * @throws NoSuchElementException - if the iteration has no more elements.
     */
    public Integer next() throws NoSuchElementException {
        if (currentIndex == numberOfEvens) {
            throw new NoSuchElementException();
        }
        currentIndex++;
        currentValue = nextFunction.apply(currentValue);
        return currentValue;
    }
}
