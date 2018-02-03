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
import java.util.Iterator;
import java.util.function.Function;
import java.util.NoSuchElementException;

/**
 * Generator - This is a generic class which implements Iterable<> and
 * Iterator<> interfaces.
 * It uses 'Function' for dynamically getting the next element.
 *
 * Type Parameters: T - the type of elements returned by this iterator.
 */
public class Generator<T> implements Iterable<T>, Iterator<T> {

    private Integer numberOfElements;
    private int currentIndex;
    private T currentValue;
    private Function<T, T> nextFunction;

    /**
     * Initializes a new Generator<T>.
     * Generator<T> takes the maximum elements the iterator should work and
     * what is the starting element.
     * <p>
     *
     * Each time it's next() method is called, dynamically Function's apply() method
     * is called which returns the 'next' element.
     * <p>
     * After numElements elements have been generated and returned from this
     * next() method, the generator will end: its hasNext() method will return
     * false, and its next() method will throw a NoSuchElementException when
     * called after this point.
     *
     * @param numElements - the number of elements that can be generated
     * @param firstElement - the first element that user has specified.
     * @throws IllegalArgumentException - when numelements is negative
     */

    public Generator(int numElements, T firstElement, Function <T, T> func) throws IllegalArgumentException {
        if (numElements < 0) {
            throw new IllegalArgumentException("numElements");
        }
        numberOfElements = numElements;
        this.currentValue = firstElement;
        currentIndex = 0;
        nextFunction = func;
    }

    /**
     * Implement the abstract method of 'Iterator' class 'hasNext'.
     *
     * @returns true if the iteration has more elements.
     */
    public Iterator<T> iterator() {
        return this;
    }

    /**
     * Implement the abstract method of 'Iterator' class 'hasNext'.
     *
     * @returns true if the iteration has more elements.
     */
    public boolean hasNext() {
        return currentIndex < numberOfElements;
    }

    /**
     * Implement the abstract method of 'Iterator' class 'next'.
     *
     * @returns the next element in the iteration.
     *
     * @throws NoSuchElementException - if the iteration has no more elements.
     */
    public T next() {
        if (currentIndex == numberOfElements) {
            throw new NoSuchElementException();
        }
        currentIndex++;
        currentValue = nextFunction.apply(currentValue);
        return currentValue;
    }
}
