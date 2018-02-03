public class testMain {
    
    public static void main(String[] args) 
    {
        char[][] fishTank = new char[10][20];

        // For 4 fishes, generate location.
        int[][] fishCoordinates = Main.generateRandomPositions(4, fishTank[0].length,
                                                          fishTank.length);

        // For 6 food, generate location.
        int[][] foodCoordinates = Main.generateRandomPositions(2, fishTank[0].length,
                                                          fishTank.length);

        // For 1 hook, generate location.
        int[][] hookCoordinates = Main.generateRandomPositions(1, fishTank[0].length,
                                                          fishTank.length);

        // Run simulator for-ever.
        while (true) {
            // Update the location of Fishes.
            Main.moveAllObjects(fishCoordinates, 4, 0,  fishTank[0].length, fishTank.length);

            // Update the location of Fish Food.
            Main.moveAllObjects(foodCoordinates, -5, 3, fishTank[0].length, fishTank.length);

            // Update the location of Hook.
            Main.moveAllObjects(hookCoordinates, 0, -5, fishTank[0].length, fishTank.length);

            // Fill the tank with water.
            Main.fillTank(fishTank, '-');

            // Place Fish into tank.
            for(int i = 0; i < fishCoordinates.length; i++) {
                Main.placeObjectInTank("1234567", fishTank, fishCoordinates[i][0], fishCoordinates[i][1]);
            }

            // Place Fish Food into tank.
            for(int i = 0; i < foodCoordinates.length; i++) {
                Main.placeObjectInTank("@", fishTank, foodCoordinates[i][0], foodCoordinates[i][1]);
            }

            // Place the Hook into the tank.
            for(int i = 0; i < hookCoordinates.length; i++) {
                Main.placeObjectInTank("H", fishTank, hookCoordinates[i][0], hookCoordinates[i][1]);
            }

            // Draw the tank.
            Main.renderTank(fishTank);

            // Utility.pause(200);
            System.out.println("\n\n\n");
        }
    }
}
    
