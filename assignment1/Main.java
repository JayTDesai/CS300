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
//
// Students who get help from sources other than their partner must fully
// acknowledge and credit those sources of help here.  Instructors and TAs do
// not need to be credited here, but tutors, friends, relatives, room mates
// strangers, etc do.  If you received no outside help from either type of
// source, then please explicitly indicate NONE.
//
// Persons:         (identify each person and describe their help in detail)
// Online Sources:  (identify each URL and describe their assistance in detail)
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
import java.util.Random;


public class Main
{
    public final static int WIDTH = 32;
    public final static int HEIGHT = 8;
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
        char[][] fishTank = new char[HEIGHT][WIDTH];
        char defaultChar = '~';

        // Fill the tank with default.
        fillTank(fishTank, defaultChar);
 
        // For 4 fishes, generate location.
        int[][] fishCoordinates = generateRandomPositions(FISHNUMBER, fishTank[0].length,
                                                          fishTank.length);

        // For 6 food, generate location.
        int[][] foodCoordinates = generateRandomPositions(FOODNUMBER, fishTank[0].length,
                                                          fishTank.length);

        // For 1 hook, generate location.
        int[][] hookCoordinates = generateRandomPositions(HOOKNUMBER, fishTank[0].length,
                                                          fishTank.length);

        // Run simulator for-ever.
        while (true) {
            // Update the location of Fishes.
            moveAllObjects(fishCoordinates, FISHX, FISHY, fishTank[0].length, fishTank.length);

            // Update the location of Fish Food.
            moveAllObjects(foodCoordinates, FOODX, FOODY, fishTank[0].length, fishTank.length);

            // Update the location of Hook.
            moveAllObjects(hookCoordinates, HOOKX, HOOKY, fishTank[0].length, fishTank.length);

            // Fill the tank with water.
            fillTank(fishTank, defaultChar);

            // Place Fish into tank.
            for(int i = 0; i < fishCoordinates.length; i++) {
                placeObjectInTank(FISH, fishTank, fishCoordinates[i][0], fishCoordinates[i][1]);
            }

            // Place Fish Food into tank.
            for(int i = 0; i < foodCoordinates.length; i++) {
                placeObjectInTank(FOOD, fishTank, foodCoordinates[i][0], foodCoordinates[i][1]);
            }

            // Place the Hook into the tank.
            for(int i = 0; i < hookCoordinates.length; i++) {
                placeObjectInTank(HOOK, fishTank, hookCoordinates[i][0], hookCoordinates[i][1]);
            }

            // Draw the tank.
            renderTank(fishTank);

            // Utility.pause(200);
            System.out.println("\n\n\n");
        }
    }

    /**
     * Copies the water character into every position in the tank array. The two-dimensional tank
     * array can have dimensions of any size(s).
     *
     * @param fishTank will contain all water characters after this method is called.
     * @param water is the character copied into the tank.
     */
    public static void fillTank(char[][] fishTank, char water)
    {
        for (int i = 0; i < fishTank.length; i++) {
            for (int j = 0; j < fishTank[i].length; j++) {
                fishTank[i][j] = water;
            }
        }
    }


    /**
     * Prints the contents of the tank into the console in row major order, so that the
     * smallest row indexes are on top and the smallest column indexes are on the left. For
     * example:
     * tank[0][0] tank[0][1] tank[0][2] ...
     * tank[1][0] tank[1][1] tank[1][2] ...
     * ...
     * Each row is on its own line, and this method should work for two-dimensional tanks with
     * dimensions of any size.
     *
     * @param fishTank contains the characters that will be printed to the console.
     */
     public static void renderTank(char[][] fishTank)
     {
         for (int i = 0; i < fishTank.length; i++) {
             for (int j = 0; j < fishTank[i].length; j++) {
                 System.out.print(fishTank[i][j]);
             }
             System.out.println();
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
        Random rand = new Random();
        for (int i = 0; i < matrix.length; i++) {
            matrix[i][0] = rand.nextInt(width);
            //matrix[i][0] = Utility.randomInt(width);
            matrix[i][1] = rand.nextInt(height);
            //matrix[i][1] = Utility.randomInt(height);
        }
        return matrix;
    }

    /**
     * Place an object in the tank at specified row and column.
     *
     * @param object - the object which needs to be placed in the tank.
     * @param fishTank - the actual fish tank.
     * @param x - horizontal position in the tank.
     * @param y - vertical position in the tank.
     */
     public static void placeObjectInTank(String object, char[][] fishTank, int x, int y)
     {
         int width = fishTank[0].length;
         int height = fishTank.length;
         for (int i = 0; i < object.length(); i++) {
             if (x < 0) {
                x += width;
             } else if (x >= width - 1) {
                x %= width;
             }
             if (y < 0) {
                y += height;
             } else if (y >= height -1) {
                y %= height;
             }
             int indx = object.length() - i - 1;
             fishTank[y][x] = object.charAt(indx);
             x--;
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
            if (x < 0) {
                x += width;
            } else if (x >= width - 1) {
                x %= width;
            }
            positions[i][0] = x;
            int y = positions[i][1] + dy;
            if (y < 0) {
                y += height;
            } else if (y >= height - 1) {
                y %= height;
            }
            positions[i][1] = y;
        } 
    }
}
