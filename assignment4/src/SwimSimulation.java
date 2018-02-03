//
// Title: Fish Tank Simulator.
// Files: SwimSimulation.java
// Course: CS 300, Fall 2017
//
// Author: Jay Desai
// Email: jdesai2@wisc.edu
// Lecturer's Name: Gary Dahl
//

import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.zip.DataFormatException;


// This class represents the actual swim tank simulation.
public class SwimSimulation {
    private PApplet processing;
    private Fish[] theFish;
    private Food[] theFood;
    private Hook[] theHook;
    private final static String fileName = "FileOptions.ssf";
    private final static boolean[] theObjects = new boolean[]{false, false, false};

    /**
     * Constructor for SwimSimulation object. If any error are
     * thrown by helper methods, runs the default setup method.
     *
     * @param reference - PApplet which is the canvas.
     */
    public SwimSimulation(PApplet reference) {
        try {
            if (reference != null) {
                processing = reference;
                // Read the data file names from FileOptions.ssf.
                ArrayList<String> fileNames = readSwimSsf();

                // Choose a random data file name and read contents
                String dataFile;
                dataFile = fileNames.get(Utility.randomInt(fileNames.size()));
                readSwimDataFile(dataFile);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.toString());
            setupDefault();
        } catch (DataFormatException e) {
            System.out.println(e.toString());
            setupDefault();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(e.toString());
            setupDefault();
        } catch (Exception e) {
            System.out.println(e.toString());
            setupDefault();
        }
    }

    /**
     *  Update method updates all the fish tank objects.
     */
    public void update() {
        if (processing != null) {
            // Fill the tank background.
            processing.background(0, 255, 255);

            // Update the location of Fishes.
            for (int i = 0; i < theFish.length; i++) {
                theFish[i].update();
            }

            // Update the location of Fish Food.
            for (int i = 0; i < theFood.length; i++) {
                theFood[i].update();
            }

            // Updates the location of the hook.
            for (int i = 0; i < theHook.length; i++) {
                theHook[i].update();
            }

            // Sees if fish can eat food.
            for (int i = 0; i < theFish.length; i++) {
                for (int j = 0; j < theFood.length; j++) {
                    theFish[i].tryToEat(theFood[j]);
                }
            }

            for (int i = 0; i < theFish.length; i++) {
                for (int j = 0; j < theHook.length; j++) {
                    theHook[j].tryToCatch(theFish[i]);
                }
            }
        }
    }

    /**
     * handleClick - callback when someone clicks in the tank-window.
     *
     * @param mouseX - X co-ordinate where mouse was clicked.
     * @param mouseY - Y co-ordinate where mouse was clicked.
     */
    public void handleClick(int mouseX, int mouseY) {
        if (theHook[0] != null) {
            theHook[0].handleClick(mouseX, mouseY);
        }
    }

    /**
     * readSwimSsf - This private method reads the only one file: FileOptions.ssf. The
     *               filename has a particular format.
     * @throws DataFormatException if fileOptions is empty
     * @throws FileNotFoundException if fileOptions is inaccessible
     */
    private static ArrayList<String> readSwimSsf()
                                         throws DataFormatException, FileNotFoundException {
        ArrayList<String> allSsdFiles = new ArrayList<String>();
        try {
            // Scanner open the FileOptions.ssf file.
            File file = new File(fileName);
            Scanner sc = new Scanner(file);

            // Read each line from fileOptions.ssf
            while (sc.hasNext()) {
                String line = sc.nextLine().trim();
                String[] splitWords = line.split(";");

                for (int i = 0; i < splitWords.length; i++) {
                    String changePath = splitWords[i].trim().replace('\\', File.separatorChar).
                                                             replace('/',File.separatorChar);
                    allSsdFiles.add(changePath);
                }
            }

            // Checks if the .ssf file is empty
            if (allSsdFiles.size() == 0) {
                throw new DataFormatException("WARNING: Failed to load objects and positions " +
                                              "from file.");
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("WARNING: Could not find or open the " +
                                            "FileOptions.ssf file.");
        }
        return allSsdFiles;
    }


    /**
     * readSwimDataFile - This private method reads the randomly chosen file which
     * contains fish Tank data.
     *
     * @throws FileNotFoundException
     * @throws ArrayIndexOutOfBoundsException
     * @throws DataFormatException
     */
    private void readSwimDataFile(String dataFile)
                                  throws FileNotFoundException, ArrayIndexOutOfBoundsException,
                                         DataFormatException, Exception {
        String objectType = "";
        try {
            File file = new File(dataFile);
            Scanner sc = new Scanner(file);
            String line = sc.nextLine().trim();
            String[] splitWords;

            // Check if the file has more data or 
            // line which you already read has object information.
            while ( sc.hasNext() || line.contains(":")) {
                if (line.isEmpty()) {
                    // Skip empty lines.
                    line = sc.nextLine().trim();
                    continue;
                }
                splitWords = line.split(":");

                // First word is type of the object.
                objectType = splitWords[0].trim();
                // second word is the number of objects.
                int numberOfObjects = Integer.parseInt(splitWords[1].trim());

                // Fish object is found.
                if (objectType.equalsIgnoreCase("FISH")) {
                    // We have found FISH object type already.
                    theObjects[0] = true;
                    theFish = new Fish[numberOfObjects];

                    // Keep track of how many objects have been found so far.
                    int count = 0;
                    while (sc.hasNext()) {
                        line = sc.nextLine().trim();
                        // Skip empty lines.
                        if (line.isEmpty()) {
                            continue;
                        }
                        if (line.contains(",") == false) {
                            break;
                        }
                        if (count == numberOfObjects) {
                            throw new ArrayIndexOutOfBoundsException("WARNING: Number of " +
                                           objectType + " does not match number of " +
                                           objectType + " positions.");
                        }
                        String[] splitCoordinates = line.split(",");
                        theFish[count++] = new Fish(processing,
                                                    Integer.parseInt(splitCoordinates[0].trim()),
                                                    Integer.parseInt(splitCoordinates[1].trim()));
                    }
                    if (count < numberOfObjects) {
                        throw new ArrayIndexOutOfBoundsException("WARNING: Number of " +
                                       objectType + " does not match number of " + objectType + 
                                       " positions.");
                    }
                } else if (objectType.equalsIgnoreCase("FOOD")) { // Food object found.
                    theObjects[1] = true;
                    
                    theFood = new Food[numberOfObjects];
                    // Keep track of how many objects you have found so far.
                    int count = 0;

                    while (sc.hasNext()) {
                        line = sc.nextLine().trim();
                        // Skip empty lines.
                        if (line.isEmpty()) {
                            continue;
                        }
                        // If line doesn't contain co-orinates, break from the loop.
                        if (line.contains(",") == false) {
                            break;
                        }
                        if (count == numberOfObjects) {
                            throw new ArrayIndexOutOfBoundsException("WARNING: Number of " +
                                           objectType + " does not match number of " + objectType +
                                           " positions.");
                        }
                        String[] splitCoordinates = line.split(",");
                        theFood[count++] = new Food(processing,
                                                    Integer.parseInt(splitCoordinates[0].trim()),
                                                    Integer.parseInt(splitCoordinates[1].trim()));
                    }
                    if (count  < numberOfObjects) {
                        throw new ArrayIndexOutOfBoundsException("WARNING: Number of " +
                                       objectType + " does not match number of " + objectType +
                                       " positions.");
                    }
                } else if (objectType.equalsIgnoreCase("HOOK")) { // Hook object found.
                    theObjects[2] = true;
                    theHook = new Hook[numberOfObjects];

                    // Keep track of hook object.
                    int count = 0;
                    while (sc.hasNext()) {
                        line = sc.nextLine().trim();
                        if (line.isEmpty()) {
                            continue;
                        }
                        if (line.contains(",") == false) {
                            break;
                        }
                        if (count == numberOfObjects) {
                            throw new ArrayIndexOutOfBoundsException("WARNING: Number of " +
                                           objectType + " does not match number of " + objectType +
                                           " positions.");
                        }
                        String[] splitCoordinates = line.split(",");
                        theHook[count++] = new Hook(processing,
                                                    Integer.parseInt(splitCoordinates[0].trim()),
                                                    Integer.parseInt(splitCoordinates[1].trim()));
                    }
                    if (count < numberOfObjects) {
                        throw new ArrayIndexOutOfBoundsException("WARNING: Number of " +
                                       objectType + " does not match number of " + objectType +
                                       " positions.");
                    }
                }
                else {
                    throw new NumberFormatException();
                }
            }

            boolean value = testObjects();
            if (value == false) {
                throw new Exception("WARNING: Missing specification for the number and " +
                                    "initial positions of fishes, foods, or hook.");
            }

        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("WARNING: Could not find or open the " + dataFile +
                                            " file.");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ArrayIndexOutOfBoundsException(e.getMessage());
        } catch (NumberFormatException e) {
            throw new DataFormatException("WARNING: Failed to load objects and positions " +
                                          "from file.");
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * This private helper method initializes the positions of 4 fish, 6 food, and 1 hook
     * to random values if the data files are unusable.
     */
    private void setupDefault() {
        theFish = new Fish[Main.FISHNUMBER];
        theFood = new Food[Main.FOODNUMBER];
        theHook = new Hook[Main.HOOKNUMBER];
        for (int i = 0; i < Main.FISHNUMBER; i++) {
           theFish[i] = new Fish(processing);
        }
        for (int i = 0; i < Main.FOODNUMBER; i++) {
            theFood[i] = new Food(processing);
        }
        for (int i =0; i< Main.HOOKNUMBER; i++) {
            theHook[i] = new Hook(processing);
        }
    }

    /**
     * testObjects - This is a helper method to make sure that all objects are initialized at
     *               least once.
     */
    private boolean testObjects() {
        for (int i = 0; i < theObjects.length; i++) {
            if (theObjects[i] == false) {
                return false;
            }
        }
        return true;
    }
}
