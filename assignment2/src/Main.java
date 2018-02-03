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

// Swim-Simulator - Assignment-2.
public class Main
{
    public final static String FISH = "><(('>";
    public final static String FOOD = "*";
    public final static String HOOK = "J";
    public final static int FISHNUMBER = 4;
    public final static int FOODNUMBER = 6;
    public final static int HOOKNUMBER = 1;
    public final static int FISHX = 1;
    public final static int FISHY = 0;
    public final static int FOODX = -1;
    public final static int FOODY = 1;
    public final static int HOOKX = 0;
    public final static int HOOKY = -1;

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
        data.tank = new char[data.processing.height][data.processing.width];

        // Fill the tank background.
        data.processing.background(0, 255, 255);

        // Generate locations for fishes.
        data.fishPositions = generateRandomPositions(FISHNUMBER, data.tank[0].length,
                                                     data.tank[0].length);

        // For 6 food, generate location.
        data.foodPositions = generateRandomPositions(FOODNUMBER, data.tank[0].length,
                                                     data.tank[0].length);

        // For 1 hook, generate location.
        data.hookPositions = generateRandomPositions(HOOKNUMBER, data.tank[0].length,
                                                     data.tank[0].length);
        
    }

    /**
     * Update: Method is used to update the tank data object.
     *
     * @param data - data object representing tank.
     */
    public static void update(Data data) {
        // Update the location of Fishes.
        moveAllObjects(data.fishPositions, FISHX, FISHY, data.tank[0].length, data.tank.length);

        // Update the location of Fish Food.
        moveAllObjects(data.foodPositions, FOODX, FOODY, data.tank[0].length, data.tank.length);

        // Update the location of Hook.
        moveAllObjects(data.hookPositions, HOOKX,
                       -(data.tank.length + 50 - data.hookPositions[0][1]) / 50,
                       data.tank[0].length, data.tank.length);

        // Fill the tank background.
        data.processing.background(0, 255, 255);

        // Place Fish into tank.
        for (int i = 0; i < data.fishPositions.length; i++) {
            placeObjectInTank(FISH,  data.processing, data.fishPositions[i][0],
                              data.fishPositions[i][1]);
        }

        // Place Fish Food into tank.
        for(int i = 0; i < data.foodPositions.length; i++) {
            placeObjectInTank(FOOD, data.processing, data.foodPositions[i][0],
                              data.foodPositions[i][1]);
        }

        // Place the Hook into the tank.
        for(int i = 0; i <data.hookPositions.length; i++) {
            placeObjectInTank(HOOK, data.processing, data.hookPositions[i][0],
                              data.hookPositions[i][1]);
        }
    }

    /**
     * Generates the random positions for specified number of positions to be generated, maximum
     * width and height.
     *
     * @param number indicates the number of random positions to be generated.
     * @param width maximum width.
     * @param height maximum height of the character.
     */
    public static int[][] generateRandomPositions(int number, int width, int height)
    {
        int[][] matrix = new int[number][2];
        for (int i = 0; i < matrix.length; i++) {
            matrix[i][0] = Utility.randomInt(width);
            matrix[i][1] = Utility.randomInt(height);
        }
        return matrix;
    }
  
    /**
     * Place an object in the tank at specified row and column.
     *
     * @param object - the object which needs to be placed in the tank.
     * @param processing - the actual fish tank.
     * @param x - horizontal position in the tank.
     * @param y - vertical position in the tank.
     */
    public static void placeObjectInTank(String object, PApplet processing, int x, int y)
    {
        while (x < 0) {
            x += processing.width;
        }
        if( x >= processing.width) {
            x %= processing.width;
        }
        while (y < 0) {
            y += processing.height;
        }
        if( y >= processing.height) {
            y %= processing.height;
        }
        if (object.equals(FISH)) {
            PImage fishImage = processing.loadImage("images" + java.io.File.separator + "FISH.png");
            processing.image(fishImage, x, y);
        }
        if (object.equals(FOOD)) {
            PImage foodImage = processing.loadImage("images" + java.io.File.separator + "FOOD.png");
            processing.image(foodImage, x, y);
        }
        if (object.equals(HOOK)) {
            PImage hookImage = processing.loadImage("images" + java.io.File.separator + "HOOK.png");
            processing.image(hookImage, x, y);
            // We draw the vertical line at hook position + 4.
            // But if it is beyond the width, we shouldn't.
            if (!(x + 4 >= processing.width)) {
                processing.line(x+4, y, x+4, 0);
            }
        }
    }

    /**
     * Move all the objects in the tank.
     * @param positions - initial positions of the objects
     * @param dx - change in horizontal position.
     * @param dy - change in vertical position.
     * @param width - width of the object.
     * @param height - height of the object.
     */
    public static void moveAllObjects(int[][]positions, int dx, int dy, int width, int height)
    {
        for (int i = 0; i < positions.length; i++) {
            int x = positions[i][0] + dx;
            while(x < 0) {
                x += width;
            } if (x >= width) {
                x %= width;
            }
            positions[i][0] = x;

            int y = positions[i][1] + dy;
            while (y < 0) {
                y += height;
            } if (y >= height) {
                y %= height;
            }
            positions[i][1] = y;
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
        if (mouseX < 0) {
            mouseX = 0;
        } else if (mouseX > data.processing.width) {
            mouseX = data.processing.width -1;
        }
        if (mouseY < 0) {
            mouseY = 0;
        } else if (mouseY > data.processing.height) {
            mouseY = data.processing.height -1;
        }

        for(int i = 0; i < data.hookPositions.length; i++) {
            data.hookPositions[i][0] = mouseX;
            data.hookPositions[i][1] = mouseY;
        }
    }
}
