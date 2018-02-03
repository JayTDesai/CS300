///
// Title:           Fish Tank Simulator.
// Files:           Hook.java
// Course:          CS 300, Fall 2017
//
// Author:          Jay Desai
// Email:           jdesai2@wisc.edu
// Lecturer's Name: Gary Dahl
//

// This class represents the 'Hook' object.
public class Hook {
    private int hookX;
    private int hookY;
    private PApplet ref;
    private PImage hookAppearance;

    /**
     * Constructor for Hook object.
     * @param thing - PApplet which is the canvas.
     */
    public Hook(PApplet thing)
    {
        if (thing != null) {
            ref = thing;
            hookX = Utility.randomInt(ref.width);
            hookY = Utility.randomInt(ref.height);
            hookAppearance = ref.loadImage("images" + java.io.File.separator + "HOOK.png");
            if (hookAppearance == null) {
                System.err.println("Could not load HOOK.png");
            }
        }
    }

    /**
     * Constructor for Hook object.
     * @param thing - PApplet which is the canvas.
     * @param x - X-coordinate to place the hook object.
     * @param y - Y-coordinate to place the hook object.
     */
    public Hook(PApplet thing, int x, int y)
    {
        if (thing != null) {
            ref = thing;
            hookX = x;
            hookY = y;
            hookAppearance = ref.loadImage("images" + java.io.File.separator + "HOOK.png");
            if (hookAppearance == null) {
                System.err.println("Could not load HOOK.png");
            }
        }
    }


    /**
     * update - update the position of Hook.
     */
    public void update() {
        if (ref != null) {
            int dx = 0;
            // Hook needs to move faster when is closer to top.
            int dy = -(ref.height + 50 - hookY) / 50;
            hookX = hookX + dx;
            hookY = hookY + dy;
            if (hookY < 0) {
                hookY += ref.height;
            }
            if (hookAppearance != null) {
                ref.image(hookAppearance, hookX, hookY);
            }
            if (hookX + 4 < ref.width) {
                ref.line(hookX + 4, hookY, hookX + 4, 0);
            }
        }
    }


    /**
     * handleClick - callback when someone clicks in the tank-window.
     * @param mouseX - X co-ordinate where mouse was clicked.
     * @param mouseY - Y co-ordinate where mouse was clicked.
     */
    public void handleClick(int mouseX, int mouseY) {
        if (ref != null) {
            if (mouseX < 0) {
                mouseX = 0;
            } else if (mouseX >= ref.width) {
                mouseX = ref.width - 1;
            }
            if (mouseY < 0) {
                mouseY = 0;
            } else if (mouseY >= ref.height) {
                mouseY = ref.height - 1;
            }
            hookX = mouseX;
            hookY = mouseY;
        }
    }

    /**
     * tryToCatch - This method tries to catch the Fish.
     * @param fish - This is the Fish object which Hook should try to catch.
     */
    public void tryToCatch(Fish fish)
    {
        if (fish != null) {
                // If fish is closer than 40, update 'fish' position.
            if (fish.distanceTo(hookX, hookY) <= 40) {
                fish.getCaught();
            }
        }
    }
}
