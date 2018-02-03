import java.util.Comparator;

public class StudentRecordComparator implements Comparator<StudentRecord> {
    private static int compareBy = 0;

    public StudentRecordComparator(int compareBy) {
        this.compareBy = compareBy;
    }

    public StudentRecordComparator compareBy(int compareBy) {
        this.compareBy = compareBy;
        return this;
    }

    public int compare(StudentRecord a, StudentRecord b) {
        if (compareBy == 0) {
            return a.getName().compareTo(b.getName());
        } else {
            if (compareBy <= a.getScore().size()) {
                return a.getScore().get(compareBy - 1) - b.getScore().get(compareBy - 1);
            } else {
                return a.getName().compareTo(b.getName());
            }
        }
    }
}
