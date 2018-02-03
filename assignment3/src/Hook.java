//
// Title:           Fish Tank Simulator.
// Files:           Hook.java
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
public class Hook {
    public final static int HOOKX = 0;
    public final static int HOOKY = -1;
    private int x;
    private int y;
    private PApplet processing;
    private PImage hookImage;
    private int dx;
    private int dy;

    /**
     * Hook - constructor.
     * Initialize the position of the Hook object.
     * @param papplet - Processing tank.
     */
    public Hook(@NotNull PApplet papplet) {
        processing = papplet;
        x = Utility.randomInt(papplet.width);
        y = Utility.randomInt(papplet.height);
        dx = HOOKX;
        dy = HOOKY;
        hookImage = processing.loadImage("images" + java.io.File.separator + "HOOK.png");
    }

    /**
     * update - update the position and re-draw the hook.
     */
    public void update() {
        int newX = x + dx;
        if (newX < 0) {
            newX += processing.width;
        } if (newX >= processing.width) {
            newX %= processing.width;
        }
        x = newX;
        dy = -(processing.height + 50 - y) / 50;
        int newY = y + dy;
        if (newY < 0) {
            newY += processing.height;
        } if (newY >= processing.height) {
            newY %= processing.height;
        }
        y = newY;

        processing.image(hookImage, x, y);
        // We draw the vertical line at hook position + 4.
        // But if it is beyond the width, we shouldn't.
        if (!(x + 4 >= processing.width)) {
            processing.line(x+4, y, x+4, 0);
        }
    }

    /**
     * handleClick - listen to mouse click and adjust the position.
     * @param mouseX - x position of mouse-click.
     * @param mouseY - y position of mouse-click.
     */
    public void handleClick(int mouseX, int mouseY) {
        if (mouseX < 0) {
            mouseX = 0;
        } else if (mouseX > processing.width) {
            mouseX = processing.width -1;
        }
        if (mouseY < 0) {
            mouseY = 0;
        } else if (mouseY > processing.height) {
            mouseY = processing.height -1;
        }
        x = mouseX;
        y = mouseY;
    }
}
