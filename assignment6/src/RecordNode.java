//
// Title:           Account Book.
// Files:           AccountBook.java, RecordNode.java
// Course:          CS 300, Fall 2017
//
// Author:          Jay Desai
// Email:           jdesai2@wisc.edu
// Lecturer's Name: Gary Dahl
//


/**
 * RecordNode stores one day's transaction.
 */

public class RecordNode {
    private int day;
    private double amount;
    private RecordNode next;

    /**
     * Default Constructor.
     */
    public RecordNode() {
        day = 0;
        amount = 0.0;
        next = null;
    }

    /**
     * Constructor with value.
     */
    public RecordNode (int curDay, double curAmount) {
        day = curDay;
        amount = curAmount;
        next = null;
    }

    /**
     * Accessor method for 'day'
     */
    public int getDay() {
        return day;
    }

    /**
     * Mutator method for 'day'
     */
    public void setDay(int day) {
        this.day = day;
    }

    /**
     * Accessor method for amount.
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Mutator method for 'amount'.
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * Accessor method to get 'next'.
     */
    public RecordNode getNext() {
        return next;
    }

    /**
     * Mutator method for 'next'.
     */
    public void setNext(RecordNode next) {
        this.next = next;
    }
}
