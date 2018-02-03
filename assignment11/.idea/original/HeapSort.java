import java.util.ArrayList;

public class HeapSort implements SortInterface {
    private int sortIndex;
    private int totalElements;

    public HeapSort() {
        sortIndex = 0;
    }

    public HeapSort(int indx) {
        sortIndex = indx;
    }

    public void setSortColumnIndex (int indx) {
        sortIndex = indx;
    }

    public void sort(ArrayList<StudentRecord> records) {
        if (sortIndex > records.get(0).getScore().size()) {
            sortIndex = 0; // invalid indx.
        }
        totalElements = records.size() - 1;
        for (int i = totalElements / 2; i >= 0; i--)
            heapify(records, i);

        for (int i = totalElements; i > 0; i--) {
            StudentRecord.swap(records, 0, i);
            totalElements--;
            heapify(records, 0);
        }
    }

    private void heapify(ArrayList<StudentRecord> records, int i)
    {
        int left = i * 2;
        int right = left + 1;
        int greater = i;

        if (left <= totalElements && StudentRecord.smaller(sortIndex, records, greater, left)) {
            greater = left;
        }
        if (right <= totalElements && StudentRecord.smaller(sortIndex, records, greater, right)) {
            greater = right;
        }
        if (greater != i) {
            StudentRecord.swap(records, i, greater);
            heapify(records, greater);
        }
    }
}
