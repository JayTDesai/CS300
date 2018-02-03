//
// Title:           Fish Tank Simulator.
// Files:           Main.java
// Course:          CS 300, Fall 2017
//
// Author:          Jay Desai
// Email:           jdesai2@wisc.edu
// Lecturer's Name: Gary Dahl
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
// NONE


// Swim-Simulator - Assignment-4.

import java.io.File;
public class Main
{
    public final static int FISHNUMBER = 4;
    public final static int FOODNUMBER = 6;
    public final static int HOOKNUMBER = 1;
    private static SwimSimulation field;

    /**
     * This is the main method and starting point.
     * @param args - any command line arguments will be ignored by this method.
     */
    public static void main(String[] args)
    {   	  
        Utility.startSimulation();
    }

    /**
     * Setup is used to initialize the Data Object.
     *
     * @param data - data object representing tank.
     */
    public static void setup(Data data)
    {
        if (data != null) {
            field = new SwimSimulation(data.processing);
        }
    }

    /**
     * Update: Method is used to update the tank data object.
     *
     * @param data - data object representing tank.
     */
    public static void update(Data data)
    {
        if (data != null) {
            field.update();
        }
    }

    /**
     * onClick - callback when someone clicks in the tank-window.
     * @param data - tank data object.
     * @param mouseX - X co-ordinate where mouse was clicked.
     * @param mouseY - Y co-ordinate where mouse was clicked.
     */
    public static void onClick(Data data, int mouseX, int mouseY)
    {
        if (data != null) {
            field.handleClick(mouseX, mouseY);
        }
    }
}
