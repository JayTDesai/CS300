//
// Title:           Sorting algorithm.
// Files:           Main.java
// Course:          CS 300, Fall 2017
//
// Author:          Jay Desai
// Email:           jdesai2@wisc.edu
// Lecturer's Name: Gary Dahl
//

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

/**
 *  The main class which reads the student records.
 *  Interacts with the specified user input and sorts the data.
 */
public class Main {


    /**
     * Helper method to check if the file really exists.
     *
     * @param filename filename which we are suppose to read.
     * @returns true if file exists or else false.
     */
    private static boolean checkFileExist(String filename) {
        File file = new File(filename);
        return file.exists();
    }

    /**
     * Helper method to print the Student Records.
     * Each line represents one student record.
     *
     * @param records list of student records.
     */
    public static void printRecords(ArrayList<StudentRecord> records) {
        for (StudentRecord rec : records) {
            System.out.println(rec);
        }
    }


    /**
     *  Main - interactive workflow.
     *  We ask the customer for user-input for filename. We read the students
     *  record from that file. If the file has invalid data or file doesn't
     *  exist, we will keep asking for filename.
     *
     *  Once we have valid file, we read the student records and then ask
     *  for sorting options. If the user provides an index of the column which
     *  doesn't exist, we do it based on name of the student (0).
     *  The program takes input from the user, it has to be 2 characters 
     *  (separated by space or continus). The character can be upper or lower
     *  case sensitive.
     */
    public static void main(String[] args) {
        System.out.println("Enter the name of your student data file: ");
        Scanner console = new Scanner(System.in);
        String filename;
        ArrayList<StudentRecord> records = null;

        while (true) {
            filename = console.nextLine().trim();
            // If file exists, exit from the loop.
            if (checkFileExist(filename)) {
                records = StudentRecord.readStudentFile(filename);
                break;
            } else {
                System.out.println("File doesn't exist or has invalid data.\n"
                       + "Please specify valid data file: ");
            }
        }

        // Interactive loop starts here.
        while (true) {
            // Print the student records as exists.
            printRecords(records);
            System.out.print("> ");
            String answer = console.nextLine();
            if (answer.trim().isEmpty()) {
                System.out.println("WARNING: Unrecognized command.");
                continue;
            }
            // remove all the spaces from the string.
            String word = answer.replaceAll(" ", "");
          
            // read the first char and ignore the case.
            char firstChar = word.toLowerCase().charAt(0);
            switch (firstChar) {
                case 'o' :
                    // Optimal Time Complexity O(nlogn) - only Heap Sort provides this characteristic.
                    int sortIndex = Integer.parseInt(word.substring(1));
                    HeapSort heapSort = new HeapSort(sortIndex);
                    heapSort.sort(records);
                    break;
                case 'a' :
                    // Adaptive (Faster for Nearly Sorted Data), but O(n^2) Time Complexity. Insert Sort is suitable.
                    sortIndex = Integer.parseInt(word.substring(1));
                    InsertSort insertSort = new InsertSort();
                    insertSort.setSortColumnIndex(sortIndex);
                    insertSort.sort(records);
                    break;
                case 'f' :
                    // Fewest Swaps - Selection Sort.
                    sortIndex = Integer.parseInt(word.substring(1));
                    SelectionSort selectSort = new SelectionSort();
                    selectSort.setSortColumnIndex(sortIndex);
                    selectSort.sort(records);
                    break;
                case 'q' :
                    // quit the interactive loop.
                    System.exit(0);
                    break;
                default :
                    // invalid input.
                    System.out.println("WARNING: Unrecognized command.");
                    break;
            }
        }
    }
}

/**
 * Represents the StudentRecord.
 * Each student record has 'name' of the student and list of scores for
 * each of the assignments.
 *
 * The static method is defined to read the file and generate the list of
 * student records.
 *
 * We have defined multiple helper methods 
 * - smaller() - which compares the two records based on the column index.
 * - swap() - swaps the two student records.
 * - toString() - method provides string representation of the studentRecord.
 */ 
class StudentRecord {
    // name of the student.
    private String name;

    // scores for each of the assignment.
    private ArrayList<Integer> scores;

    // constructor which creates name and size of the scores.
    public StudentRecord(String name, int size) {
        this.name = name;
        scores = new ArrayList<Integer>(size);
    }

    /**
     * Add the score to the 'scores' as we read each entry.
     * @param score assignment score for the student.
     */
    public void addScore(int score) {
        scores.add(score);
    }

    /**
     * Get the name of the student.
     */
    public String getName() {
        return name;
    }

    /**
     * Get all the scores of the student assignment.
     */
    public ArrayList<Integer> getScore() {
        return scores;
    }

    /**
     * String representation of student record.
     */
    public String toString() {
        StringBuilder builder = new StringBuilder(name);
        builder.append(": ");
        builder.append(scores.get(0));
        for (int i = 1; i < scores.size(); i++) {
            builder.append("; ");
            builder.append(scores.get(i));
        }
        return builder.toString();
    }

    /**
     * Helper method which compares the record at indx1 and indx2.
     *
     * @param compareToIndx - column to sort StudentRecord on.
     * @param records - list of student records.
     * @param indx1 - first student record to compare.
     * @param indx2 - second student record to compare against.
     * 
     * @returns - true if first record is smaller than second student record 
     *            for column 'compareToIndx' or false otherwise.
     */
    public static boolean smaller(int compareToIndx, ArrayList<StudentRecord> records,
                                     int indx1, int indx2) {
        StudentRecordComparator compareTo = new StudentRecordComparator(compareToIndx);
        return compareTo.compare(records.get(indx1), records.get(indx2)) < 0;
    }


    /**
     * Helper method to swap the 2 elements in ArrayList.
     * Unlike array, array list has explicit methods for inserting elements at
     * particular index.
     *
     * @param records - list of student records
     * @param i - first record which needs to be swapped.
     * @param j - second record with which need to be swapped.
     *
     */
    public static void swap(ArrayList<StudentRecord> records, int i, int j) {
        StudentRecord tmp = records.get(i);
        records.set(i, records.get(j));
        records.set(j, tmp);
    }

    /**
     * Read the file and populate StudentRecord.
     *
     * @param filename - name of the file which has all the student record information.
     * @returns ArrayList<StudentRecord> - return the list of student records.
     */
    public static ArrayList<StudentRecord> readStudentFile(String filename) {
         ArrayList<StudentRecord> records = new ArrayList<StudentRecord>();
         File file = new File(filename);
         try {
             Scanner sc = new Scanner(file);
             int scoreCount = -1;
             while (sc.hasNextLine()) {
                 String line = sc.nextLine().trim();
                 if (line.isEmpty()) {
                     continue;
                 }
                 String[] tokens = line.split(":");
                 if (tokens.length != 2) {
                     continue;
                 }

                 String name = tokens[0].trim();
                 String tmp = tokens[1].trim();
                 String[] scores = tmp.split(",");
                 StudentRecord record = new StudentRecord(name, scores.length);
                 for (String s : scores) {
                     record.addScore(Integer.parseInt(s.trim()));
                 }
                 records.add(record);
             }
         } catch (FileNotFoundException e) {
             e.printStackTrace();
         } catch (Exception e) {
             e.printStackTrace();
         }
         return records;
    }
}


/**
 * We have to explicitly define StudentRecordComparator. 
 * This is because we want to sort either based on name (0) or based on
 * any score the student has. 
 * This comparator is simple. If column is '0', it compares on STRING
 * compareTo as 'name' is the string.
 * For any columns representing 'score', it does based on integer.
 *
 */
class StudentRecordComparator implements Comparator<StudentRecord> {
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
            // if index is invalid, we go back to 0.
            if (compareBy <= a.getScore().size()) {
                return a.getScore().get(compareBy - 1) - b.getScore().get(compareBy - 1);
            } else {
                return a.getName().compareTo(b.getName());
            }
        }
    }
}

/**
 * Simple interface used for sorting the student record.
 *
 * JAVA provides default 'sort' functionality but for custom column based
 * sorting, it was good to define the interface itself.
 * This will first set the sorting column index and then we can sort.
 * If sortingIndex is not specified, the 0 column is used (which is name).
 */

interface SortInterface {
    // Method to sort the list of student records based on the column index.
    public void sort(ArrayList<StudentRecord> records);

    // Set the column index for sorting. 
    public void setSortColumnIndex (int index);
}


/**
 * SelectionSort - This class implements the sort interface. The sort interface
 * has only 2 methods - one to set which column you want to sort on and second
 * is the actual sort method.
 * SelectionSort is not optimal with time-complexity and doesn't take any additional
 * space. It has fewest swaps.
 * Time complexity is O(n^2)
 * Space Complexity is O(1).
 */

class SelectionSort implements SortInterface {
    private int sortIndex;

    public SelectionSort() {
        sortIndex = 0;
    }

    public SelectionSort(int indx) {
        sortIndex = indx;
    }

    @Override
    public final void setSortColumnIndex (int indx) {
        sortIndex = indx;
    }

    @Override
    public final void sort(ArrayList<StudentRecord> records) {
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

/**
 * InsertSort - This class implements the sort interface. The sort interface
 * has only 2 methods - one to set which column you want to sort on and second
 * is the actual sort method.
 * InsertSort is not optimal with time-complexity and doesn't take any additional
 * space. It does multiple swaps.
 * Time complexity is O(n^2).
 * Space Complexity is O(1).
 */

class InsertSort implements SortInterface {
    private int sortIndex;

    public InsertSort() {
        sortIndex = 0;
    }

    public InsertSort(int indx) {
        sortIndex = indx;
    }

    @Override
    public final void setSortColumnIndex (int indx) {
        sortIndex = indx;
    }

    @Override
    public final void sort(ArrayList<StudentRecord> records) {
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


/**
 * HeapSort - This class implements the sort interface. The sort interface
 * has only 2 methods - one to set which column you want to sort on and second
 * is the actual sort method.
 * HeapSort is optimal with time-complexity and doesn't take any additional
 * space. It does multiple swaps.
 * Time complexity is O(nlog(n)).
 * Space Complexity is O(1).
 */
class HeapSort implements SortInterface {
    private int sortIndex;
    private int totalElements;

    public HeapSort() {
        sortIndex = 0;
    }

    public HeapSort(int indx) {
        sortIndex = indx;
    }

    @Override
    public final void setSortColumnIndex (int indx) {
        sortIndex = indx;
    }

    @Override
    public final void sort(ArrayList<StudentRecord> records) {
        // Validate the index is within the range. If not, set it to '0'.
        if (sortIndex > records.get(0).getScore().size()) {
            sortIndex = 0; // invalid indx.
        }
        // The actual logic. 
        totalElements = records.size() - 1;
        for (int i = totalElements / 2; i >= 0; i--)
            heapify(records, i);

        for (int i = totalElements; i > 0; i--) {
            StudentRecord.swap(records, 0, i);
            totalElements--;
            heapify(records, 0);
        }
    }

    /**
     * Private method to help with sorting the array list.
     * We keep the counter - totalElements which we need to sort. Since this is
     * a min-heap, first element is already the smallest. We need to keep track
     * of number of elements which need to sort. 'totalElements' is keeping
     * that index number.
     */ 
    private void heapify(ArrayList<StudentRecord> records, int i)
    {
        int left = i * 2; // left-node
        int right = left + 1; // right-node
        int greater = i;

        // Smaller determination.
        if (left <= totalElements && StudentRecord.smaller(sortIndex, records, greater, left)) {
            greater = left;
        }
        if (right <= totalElements && StudentRecord.smaller(sortIndex, records, greater, right)) {
            greater = right;
        }
        // If you find, move to next record.
        if (greater != i) {
            StudentRecord.swap(records, i, greater);
            heapify(records, greater);
        }
    }
}
