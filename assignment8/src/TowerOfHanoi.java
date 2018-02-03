/**
 * The actual implementation of game TowerOfHanoi.
 */
public class TowerOfHanoi {

    // Goal is to move all disks to rods[rods.length-1]
    private Rod[] rods; // rods[0] starts filled to capacity with disks

    /**
     * Constructs a new TowerOfHanoi puzzle with width rods that an hold a max
     * of height disks each.  The first of these rods begins with this maximum
     * (height) number of disks, and each of the other rods begins empty.
     * @param width is the total number of rods in this puzzle.
     * @param height is the number of disks that begin on the first rod.
     */
    public TowerOfHanoi(int width, int height)  throws IllegalArgumentException {
        if (width < 0 || height < 0) {
            throw new IllegalArgumentException();
        }
        rods = new Rod[width];
        for (int i = 0; i < width; i++) {
            if (i == 0) {
                rods[i] = new Rod(height, height);
            } else {
                rods[i] = new Rod(height, 0);
            }
        }
    }

    /**
     * Moves a single disk from the source rod to the target rod.  These rods
     * are indexed using a zero-based index, where 0 references the first rod
     * where all disks begin, and the width-1 references the goal rods.  If
     * moving a disk from this source rod to this destination rod is illegal,
     * then the message "WARNING: Illegal move." should be printed out to the
     * console, instead of moving any disks.
     * @param source is a zero-based index for the rod to move a disk from.
     * @param destination is a zero-based index for the rod that disk moves to.
     */
    public void moveDisk(int source, int destination) {
        if (source < 0 || destination < 0) {
            throw new IllegalArgumentException();
        }
        if (source >= rods.length || destination >= rods.length) {
            throw new IllegalArgumentException();
        }
        if (rods[source].isEmpty()) {
            System.out.println("WARNING: Source rod is empty");
        }
        if (rods[destination].isFull()) {
            System.out.println("WARNING: Destination rod is full");
        }
        try {
            Disk srcDisk = rods[source].peek();
            if (!rods[destination].isEmpty()) {
                Disk dstDisk = rods[destination].peek();
                if (srcDisk.compareTo(dstDisk) > 0) {
                    System.out.println("WARNING: Illegal move.");
                    return;
                }
            }
            rods[destination].push(rods[source].pop());
        } catch (Exception e) {
            System.out.println("Exception caught");
        }
    }

    /**
     * Determines whether the puzzle has been solved.  This happens when the
     * goal rod (index width-1) is full, and each of the other rods are empty.
     * @return true when all disks have been moved from the first to last rod.
     */
    public boolean isSolved() {
        int numRods = rods.length;
        if (!rods[numRods - 1].isFull()) {
            return false;
        }
        for (int i = 0; i < numRods - 1; i++) {
            if (!rods[i].isEmpty()) {
                return false;
            }
        }
        return true;
    }

    /**
     * The string representation of this puzzle is composed of the strings
     * representing each rod.  However the rows of each rod must be combined
     * with the rows of the other rods, so that they appear horizontally
     * aligned in the final string.  Here is an example from a 3x5 game:
     * "     |          |          |     \n" +
     * "     |          |          |     \n" +
     * "    <1>         |          |     \n" +
     * "   <=2=>     <==3==>       |     \n" +
     * "<====5====> <===4===>      |     \n"
     * @return the string representation of this puzzle's current state.
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        String[] allRodStrings = new String[rods.length];
        for (int i = 0; i < rods.length; i++) {
            allRodStrings[i] = new String(rods[i].toString());
        }
        int maxHeight = allRodStrings[0].split("\n").length;
        for (int i = 0; i < maxHeight; i++) {
            for (int j = 0; j < rods.length; j++) {
                String[] lines = allRodStrings[j].split("\n");
                builder.append(lines[i]);
            }
            builder.append("\n");
        }
        return builder.toString();
    }

    /**
     * This method automatically solves the problem of moving number disks from
     * the source rod to the destination rod, by making use of an extra
     * auxiliary rod.
     * @param number is the number of disks being moved.
     * @param source is the zero-based index of the rod disks are moved from.
     * @param destination is the index of the rod that disks are moved to.
     * @param auxiliary is an extra rod index that disks can be moved through.
     */
    public void solve(int number, int source, int destination, int auxiliary) {
        if (number == 0) {
            return;
        } else {
            solve(number - 1, source, auxiliary, destination);
            moveDisk(source, destination);
            System.out.println(this);
            solve(number - 1, auxiliary, destination, source);
        }
    }
}
