import java.lang.IllegalStateException;
import java.lang.IllegalArgumentException;
import java.util.Arrays;
import java.util.NoSuchElementException;



public class Rod implements Comparable<Rod> {
    private int numberOfDisks; // tracks the number of disks on this rod
    private Disk[] disks;      // stores references to the disks on this rod
    // index 0: bottom, index discNumber-1: top

    /**
     * Constructs a new rod that can hold a maximum of maxHeight Disks. The
     * numberOfDisks new Disks will be created with sizes between 1 and
     * numberOfDisks (inclusive), and arranged from largest (on bottom) to the
     * smallest (on top) on this Rod.
     * @param maxHeight is the capacity or max number of Disks a rod can hold.
     * @param numberOfDisks is the initial number of Disks created on this rod.
     */
    public Rod(int maxHeight, int numberOfDisks) {
        if (numberOfDisks > maxHeight) {
            throw new IllegalArgumentException();
        }
        if (maxHeight > 9 || maxHeight < 0 || numberOfDisks < 0) {
            throw new IllegalArgumentException();
        }

        this.numberOfDisks = numberOfDisks;
        disks = new Disk[maxHeight];
        for (int i = 0; i < numberOfDisks; i++) {
            disks[i] = new Disk(numberOfDisks -i);
        }
    }

    /**
     * Adds one new Disk to the top of this rod.
     * @param disk is a reference to the Disk being added to this rod.
     * @throws IllegalStateException when this rod is already full to capacity.
     */
    public void push(Disk disk) throws IllegalStateException {
        if (numberOfDisks == disks.length) {
            throw new IllegalStateException();
        }
        if (numberOfDisks > 0) {
            if (disk.compareTo(disks[numberOfDisks - 1]) > 0) {
                throw new IllegalStateException();
            }
        }
        disks[numberOfDisks++] = disk;
    }

    /**
     * Removes and returns one Disk from the top of this rod.
     * @return a reference to the Disk that is being removed.
     * @throws NoSuchElementException when this rod is empty.
     */
    public Disk pop() throws NoSuchElementException {
        if (numberOfDisks == 0) {
            throw new NoSuchElementException();
        }
        Disk disk = disks[--numberOfDisks];
        disks[numberOfDisks] = null;
        return disk;
    }

    /**
     * Returns (without removing) one Disk from the top of this rod.
     * @return a reference to the Disk that is being returned.
     * @throws NoSuchElementException when this rod is empty.
     */
    public Disk peek() throws NoSuchElementException {
        if (numberOfDisks == 0) {
            throw new NoSuchElementException();
        }
        Disk disk = disks[numberOfDisks - 1];
        return disk;
    }

    /**
     * Indicates whether this rod is currently holding zero Disks.
     * @return true when there are no Disks on this rod.
     */
    public boolean isEmpty() {
        return numberOfDisks == 0;
    }

    /**
     * Indicates whether this rod is currently full to its capacity with disks.
     * @return true when the number of Disks on this rod equals its max height.
     */
    public boolean isFull() {
        return numberOfDisks == disks.length;
    }

    /**
     * Compares one rod to another to determine whether it's legal to move the
     * top disk from this rod onto the other.
     * @param other is the destination rod we are considering moving a disk to.
     * @return +1 when moving a disk from this rod to other is legal,
     *         -1 when moving a disk from this rod to other is illegal,
     *         or 0 when this rod is empty and there are no disks to move.
     */
    @Override
    public int compareTo(Rod other) {
        Disk myTop = null;
        try {
            myTop = peek();
        } catch (NoSuchElementException e) {
            return 0; // empty rod.
        }
        Disk otherTop = null;
        try {
            otherTop = other.peek();
        } catch (NoSuchElementException e) {
            return 1;
        }
        if (myTop.compareTo(otherTop) > 0) {
            return -1;
        }
        return 1;
    }


    /**
     * The string representation of this rod includes its max height number
     * of rows separated by and ending with newline characters (\n).  Rows
     * occupied by a disk will include that disk's string representation, and
     * other rows instead contain a single vertical bar character (|).  All
     * rows are centered by surrounding both sides with spaces until they are
     * each capacity*2+1 characters wide.  Example of 5 capacity rod w\3 disks:
     * "     |     \n" +
     * "     |     \n" +
     * "   <=2=>   \n" +
     * "  <==3==>  \n" +
     * "<====5====>\n"
     * @return the string representation of this rod based on its contents.
     */
    @Override
    public String toString() {
        // one for the number and another one for newline character.
        int constSize = disks.length * 2 + 2;
        StringBuilder builder = new StringBuilder();
        for (int i = disks.length; i > 0; i--) {
            Disk disk = disks[i-1];
            if (disk == null) {
                String spaceString = stringOfSize(disks.length, ' ');
                builder.append(spaceString);
                builder.append("|");
                builder.append(spaceString);
            } else {
                String diskString = disk.toString();
                int remainingSize = constSize - diskString.length();
                if (remainingSize % 2 == 1) {
                    String spaceString = stringOfSize(remainingSize/ 2, ' ');
                    builder.append(spaceString);
                    builder.append(diskString);
                    builder.append(spaceString);
                }
            }
            builder.append("\n");
        }
        return builder.toString();
    }

    public static String stringOfSize(int size, char ch)
    {
        final char[] array = new char[size];
        Arrays.fill(array, ch);
        return new String(array);
    }

    public static void main(String[] args) {
        Rod rod = new Rod(9, 4);
        System.out.println(rod);
        Rod rod2 = new Rod(9, 9);
        System.out.println(rod2);
        System.out.println(rod2.isFull());
        Rod rod3 = new Rod(9, 0);
        rod3.push(new Disk(3));
        rod3.push(new Disk(2));
        Disk disk = rod3.peek();
        System.out.print(disk);
        rod3.pop();
        disk = rod3.pop();
        System.out.print(disk);
        System.out.println(rod3);
        System.out.println(rod3.isFull());
        System.out.println(rod3.isEmpty());
    }
}
