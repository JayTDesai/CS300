//
// Title:           Fish Tank Simulator.
// Files:           Food.java
// Course:          CS 300, Fall 2017
//
// Author:          Jay Desai
// Email:           jdesai2@wisc.educ
// Lecturer's Name: Gary Dahl
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
// NONE

import org.jetbrains.annotations.NotNull;

// Swim-Simulator - Assignment-3.
public class Food {
    public final static int FOODX = -1;
    public final static int FOODY = 1;
    private int x;
    private int y;
    private PApplet processing;
    private PImage foodImage;
    private int dx;
    private int dy;

    /**
     * Food - constructor.
     * Initialize the position of the Food object.
     * @param papplet - Processing tank.
     */
    public Food(@NotNull PApplet papplet) {
        processing = papplet;
        x = Utility.randomInt(papplet.width);
        y = Utility.randomInt(papplet.height);

        dx = FOODX;
        dy = FOODY;
        foodImage = processing.loadImage("images" + java.io.File.separator + "FOOD.png");
    }

    /**
     * update - Update the position and re-draw.
     */
    public void update() {
        int newX = x + dx;
        if (newX < 0) {
            newX += processing.width;
        } if (newX >= processing.width) {
            newX %= processing.width;
        }
        x = newX;
        int newY = y + dy;
        if (newY < 0) {
            newY += processing.height;
        } if (newY >= processing.height) {
            newY %= processing.height;
        }
        y = newY;
        processing.image(foodImage, x, y);
    }
}
