//
// Title:           Fish Tank Simulator.
// Files:           Main.java
// Course:          CS 300, Fall 2017
//
// Author:          Jay Desai
// Email:           jdesai2@wisc.educ
// Lecturer's Name: Gary Dahl
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
// NONE

// Swim-Simulator - Assignment-3.

import org.jetbrains.annotations.NotNull;

public class Main
{
    public final static int FISHNUMBER = 4;
    public final static int FOODNUMBER = 6;
    public final static int HOOKNUMBER = 1;

    private static SwimSimulation swim;

    /**
     * This is the main method and starting point.
     * @param args - any command line arguments will be ignored by this method.
     */
    public static void main(String[] args)
    {
        Utility.startSimulation();
    }

    /**
     * Setup is used to initialize the SwimSimulation Object.
     */
    public static void setup(@NotNull Data data)
    {
        swim = new SwimSimulation(data.processing);
    }

    /**
     * Update: Method is used to update the tank data object.
     */
    public static void update(Data data) {
        swim.update();
    }

    /**
     * onClick - callback when someone clicks in the tank-window.
     * @param mouseX - X co-ordinate where mouse was clicked.
     * @param mouseY - Y co-ordinate where mouse was clicked.
     */
    public static void onClick(Data data, int mouseX, int mouseY)
    {
        swim.handleClick(mouseX, mouseY);
    }
}
