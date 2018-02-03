//
// Title:           Fish Tank Simulator.
// Files:           Fish.java
// Course:          CS 300, Fall 2017
//
// Author:          Jay Desai
// Email:           jdesai2@wisc.edu
// Lecturer's Name: Gary Dahl
//

// This class represents the Fish object.
public class Fish {

    private int fishX;
    private int fishY;
    private PApplet ref;
    private PImage fishAppearance;

    /**
     * Constructor for Fish object.
     * @param thing - PApplet which is the canvas.
     */
    public Fish(PApplet thing)
    {
        if (thing != null) {
            ref = thing;
            fishX = Utility.randomInt(ref.width);
            fishY = Utility.randomInt(ref.height);
            fishAppearance = ref.loadImage("images" + java.io.File.separator + "FISH.png");
            if (fishAppearance == null) {
                System.err.println("Could not load FISH.png");
            }
        }
    }

    /**
     * Constructor for Fish object.
     * @param thing - PApplet which is the canvas.
     * @param x - X-coordinate to place the fish object.
     * @param y - Y-coordinate to place the fish object.
     */

    public Fish(PApplet thing, int x, int y)
    {
        if (thing != null) {
            ref = thing;
            fishX = x;
            fishY = y;
            fishAppearance = ref.loadImage("images" + java.io.File.separator + "FISH.png");
            if (fishAppearance == null) {
                System.err.println("Could not load FISH.png");
            }
        }
    }

    /**
     * update - This method is responsible for updating the position of the
     *          Fish object and drawing on the canvas.
     */
    public void update()
    {
        if (ref != null) {
            int dx = 1;
            int dy = 0;
            fishX = fishX + dx;
            fishY = fishY + dy;
            if ( fishX >= ref.width) {
                fishX = 0;
            }
            if (fishAppearance != null) {
                ref.image(fishAppearance, fishX, fishY);
            }
        }
    }

    /**
     * tryToEat - This method tries to eat the Fish food.
     * @param food - This is the Food object which Fish should try to eat.
     */
    public void tryToEat(Food food)
    {
        if (food != null) {
            // If food is closer than 40, update 'food' position.
            if (food.distanceTo(fishX, fishY) <= 40) {
                food.getEaten();
            }
        }
    }

    /**
     * distanceTo = Returns the distance from this Fish to position X, Y.
     * @param x - x co-ordinate of the position.
     * @param y - y co-ordinate of the position.
     */
    public float distanceTo(int x, int y)
    {
        if (ref != null) {
            while( x < 0) {
                x += ref.width;
            }
            if (x >= ref.width) {
                x %= ref.width;
            }
            while (y < 0) {
                y += ref.height;
            }
            if (y >= ref.height) {
                y %= ref.height;
            }
            double xdistance = Math.abs(fishX - x);
            double ydistance = Math.abs(fishY - y);

            // Pythagorus theorm for calculating diagnoal distance.
            double totalsquared = Math.pow(xdistance, 2) + Math.pow(ydistance, 2);
            float total = (float) Math.sqrt(totalsquared);
            return total;
        } else {
            return 0;
        }
    }

    /**
     * getCaught - This method moves this fish to a random position along the
     *             left edge of the screen.
     */
    public void getCaught()
    {
        if (ref != null) {
            fishX = 0;
            fishY = Utility.randomInt(ref.height);
        }
    }
}
