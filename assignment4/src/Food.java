//
// Title:           Fish Tank Simulator.
// Files:           Food.java
// Course:          CS 300, Fall 2017
//
// Author:          Jay Desai
// Email:           jdesai2@wisc.edu
// Lecturer's Name: Gary Dahl
//

// This class represents the 'Food' object.
public class Food {
    private int foodX;
    private int foodY;
    private PApplet ref;
    private PImage foodAppearance;

    /**
     * Constructor for Food object.
     * @param thing - PApplet which is the canvas.
     */
    public Food(PApplet thing)
    {
        if (thing != null) {
            ref = thing;
            foodX = Utility.randomInt(ref.width);
            foodY = Utility.randomInt(ref.height);
            foodAppearance = ref.loadImage("images" + java.io.File.separator + "FOOD.png");
            if (foodAppearance == null) {
                System.err.println("Could not load FOOD.png");
            }
        }
    }


    /**
     * Constructor for Food object.
     * @param thing - PApplet which is the canvas.
     * @param x - X-coordinate to place the food object.
     * @param y - Y-coordinate to place the food object.
     */
    public Food(PApplet thing, int x, int y)
    {
        if (thing != null) {
            ref = thing;
            foodX = x;
            foodY = y;
            foodAppearance = ref.loadImage("images" + java.io.File.separator + "FOOD.png");
            if (foodAppearance == null) {
                System.err.println("Could not load FOOD.png");
            }
        }
    }

    /**
     * update - update the position of Hook.
     */
    public void update()
    {
        if (ref != null) {
            int dx = -1;
            int dy = 1;
            foodX = foodX + dx;
            if (foodX < 0) {
                foodX += ref.width;
            }
            foodY = foodY + dy;
            if (foodY >= ref.height) {
                foodY %= ref.height;
            }
            if (foodAppearance != null) {
                ref.image(foodAppearance, foodX, foodY);
            }
        }
    }


    /**
     * distanceTo = Returns the distance from this Food to position X, Y.
     * @param x - x co-ordinate of the position.
     * @param y - y co-ordinate of the position.
     */
    public float distanceTo(int x, int y)
    {
        if (ref != null) {
            while (x < 0) {
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
            double xdistance = Math.abs(foodX - x);
            double ydistance = Math.abs(foodY - y);
            double totalsquared = Math.pow(xdistance, 2) + Math.pow(ydistance, 2);
            float total = (float) Math.sqrt(totalsquared);
            return total;
        } else {
            return 0;
        }
    }


    /**
     * getEaten - Move the food object randomly in horizontal place.
     */
    public void getEaten()
    {
        if (ref != null) {
            foodX = Utility.randomInt(ref.width);
            foodY = 0;
        }
    }
}
