import java.util.ArrayList;

public class InsertSort implements SortInterface {
    private int sortIndex;

    public InsertSort() {
        sortIndex = 0;
    }

    public InsertSort(int indx) {
        sortIndex = indx;
    }

    public void setSortColumnIndex (int indx) {
        sortIndex = indx;
    }

    public void sort(ArrayList<StudentRecord> records) {
        if (sortIndex > records.get(0).getScore().size()) {
            sortIndex = 0; // invalid indx.
        }
        for (int i = 0; i < records.size(); i++) {
            for (int j = i; j > 0; j--) {
                if (StudentRecord.smaller(sortIndex, records, j, j - 1)) {
                    StudentRecord.swap(records, j, j - 1);
                }
            }
        }
    }

}
