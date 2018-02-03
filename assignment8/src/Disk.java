// Disk class.

public class Disk implements Comparable<Disk> {
    private int size; // 1-9: restricts disk movement, and used for drawing

    /**
     * Constructs a new immutable disk object with the specified size.
     * @param size is used for drawing and comparing against other disks.
     * @throws IllegalArgumentException when size is not between 1 and 9.
     */
    public Disk(int size) throws IllegalArgumentException {
        if (size > 9 || size < 1) {
            throw new IllegalArgumentException();
        }
        this.size = size;
    }

    /**
     * Compares one disk to another to determine which is larger, and therefore
     * which can be moved on top of the other.
     * @param other is a reference to the disk we are comparing this one to.
     * @return a positive number when this.size > other.size,
     *         a negative number when this.size < other.size, or
     *         zero when this.size == other.size, or other is null.
     */
    @Override
    public int compareTo(Disk other) {
        int ret = 0;
        if (size > other.size)
            return ++ret;
        else if (size < other.size)
            return --ret;
        else
            return ret;
    }

    /**
     * The string representation of this disk object includes its integer size
     * surrounded by size-1 equals characters (=) on each side, and enclosed
     * within angle brackets (<>).  For example:
     *     size 1: "<1>"
     *     size 2: "<=2=>"
     *     size 3: "<==3==>"
     * @return the string representation of this disk object based on its size.
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(21); // max possible size.
        builder.append('<');
        addEquals(builder);
        builder.append(size);
        addEquals(builder);
        builder.append('>');
        return builder.toString();

    }

    private void addEquals(StringBuilder b) {
        for (int i = 1; i < size; i++) {
            b.append('=');
        }
    }

    public static void main(String[] args) {
        try {
            Disk disk1 = new Disk(-1);
        } catch (IllegalArgumentException e) {
            System.out.println("Caught it");
        }
        try {
            Disk disk2 = new Disk(10);
        } catch (IllegalArgumentException e) {
            System.out.println("Caught it");
        }
        for (int i = 1; i <= 9; i++) {
            Disk disk = new Disk(i);
            System.out.println(disk);
        }
    }
}
