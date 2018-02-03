import java.util.ArrayList;

public class SelectionSort implements SortInterface {
    private int sortIndex;

    public SelectionSort() {
        sortIndex = 0;
    }

    public SelectionSort(int indx) {
        sortIndex = indx;
    }

    public void setSortColumnIndex (int indx) {
        sortIndex = indx;
    }

    public void sort(ArrayList<StudentRecord> records) {
        if (sortIndex > records.get(0).getScore().size()) {
            sortIndex = 0; // invalid indx.
        }

        for (int i = 0; i < records.size() - 1; i++) {
            int currIndex = i;
            for (int j = i + 1; j < records.size(); j++) {
                if (StudentRecord.smaller(sortIndex, records, j, currIndex)) {
                    currIndex = j;
                }
            }
            StudentRecord.swap(records, currIndex, i);
        }
    }

}
