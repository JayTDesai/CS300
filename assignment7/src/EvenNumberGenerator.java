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
import java.lang.IllegalArgumentException;
import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 * EvenNumberGenerate - Simplest class which implements Iterable and Iterator
 * interfaces.
 */
public class EvenNumberGenerator implements Iterable<Integer>, Iterator<Integer> {

    private Integer[] myEven = null;
    private int currentIndex;

    /**
     * Initializes a new EvenNumberGenerator to return a single even number
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
     * @throws IllegalArgumentException - when numberOfEvens is negative, or
     *                                    when firstEven is not an even number
     */
    public EvenNumberGenerator(int numberOfEvens, Integer firstEven) throws IllegalArgumentException {
        if (numberOfEvens < 0) {
            throw new IllegalArgumentException("numberOfEvens");
        }
        if (firstEven % 2 != 0) {
            throw new IllegalArgumentException("firstEven");
        }
        myEven = new Integer[numberOfEvens];
        for (int i = 0; i < numberOfEvens; i++) {
            myEven[i] = firstEven + 2*i;
        }
        currentIndex = 0;
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
        return currentIndex < myEven.length;
    }

    /**
     * Implement the abstract method of 'Iterator' class 'next'. 
     *
     * @returns the next element in the iteration.
     *
     * @throws NoSuchElementException - if the iteration has no more elements.
     */
    public Integer next() throws NoSuchElementException {
        if (currentIndex == myEven.length) {
            throw new NoSuchElementException();
        }
        return myEven[currentIndex++];
    }
}
