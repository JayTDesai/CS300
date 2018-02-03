//
// Title:           Fish Tank Simulator.
// Files:           Fish.java
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
public class Fish {
    public final static int FISHX = 1;
    public final static int FISHY = 0;

    private int x;
    private int y;
    private PApplet processing;
    private PImage fishImage;
    private int dx;
    private int dy;

    /**
     * Fish - constructor.
     * Initialize the position of the Fish object.
     * @param papplet - Processing tank.
     */
    public Fish(@NotNull PApplet papplet) {
        processing = papplet;
        x = Utility.randomInt(papplet.width);
        y = Utility.randomInt(papplet.height);

        dx = FISHX;
        dy = FISHY;
        fishImage = processing.loadImage("images" + java.io.File.separator + "FISH.png");
    }

    /**
     * update - Update the position of Fish object and re-draw.
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
        processing.image(fishImage, x, y);
    }
}
