//
// Title:           Fish Tank Simulator.
// Files:           SwimSimulation.java
// Course:          CS 300, Fall 2017
//
// Author:          Jay Desai
// Email:           jdesai2@wisc.educ
// Lecturer's Name: Gary Dahl
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
// NONE

// Swim-Simulator - Assignment-3.

public class SwimSimulation {
    private PApplet processing;
    private Fish[] fishes;
    private Food[] food;
    private Hook[] hooks;

    /**
     * SwimSimulator - constructor.
     * Initializes the Fish tank simulator.
     * @param papplet - Processing tank.
     */
    public SwimSimulation(PApplet papplet) {
        processing = papplet;
        fishes = new Fish[Main.FISHNUMBER];
        for (int i = 0; i < Main.FISHNUMBER; i++) {
            fishes[i] = new Fish(processing);
        }
        food = new Food[Main.FOODNUMBER];
        for (int i = 0; i < Main.FOODNUMBER; i++) {
            food[i] = new Food(processing);
        }
        hooks = new Hook[Main.HOOKNUMBER];
        for (int i = 0; i < Main.HOOKNUMBER; i++) {
            hooks[i] = new Hook(processing);
        }
    }

    /**
     * update - Update the position of Fish object and re-draw.
     */
    public void update() {
        // Fill the tank background.
        processing.background(0, 255, 255);

        // Update the location of Fishes.
        for (int i = 0; i < Main.FISHNUMBER; i++) {
            fishes[i].update();
        }

        // Place Fish Food into tank.
        for(int i = 0; i < Main.FOODNUMBER; i++) {
            food[i].update();
        }

        // Place Hook into tank.
        for(int i = 0; i < Main.HOOKNUMBER; i++) {
            hooks[i].update();
        }

    }

    /**
     * handleClick - callback when someone clicks in the tank-window.
     * @param mouseX - X co-ordinate where mouse was clicked.
     * @param mouseY - Y co-ordinate where mouse was clicked.
     */
    public void handleClick(int mouseX, int mouseY)
    {
        for (int i = 0; i < Main.HOOKNUMBER; i++) {
            hooks[i].handleClick(mouseX, mouseY);
        }
    }
}
