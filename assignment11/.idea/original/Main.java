import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    private static boolean checkFileExist(String filename) {
        File file = new File(filename);
        return file.exists();
    }

    public static void printRecords(ArrayList<StudentRecord> records) {
        for (StudentRecord rec : records) {
            System.out.println(rec);
        }
    }


    public static void main(String[] args) {
        System.out.println("Enter the name of your student data file: ");
        Scanner console = new Scanner(System.in);
        String filename;

        while (true) {
            filename = console.nextLine().trim();
            if (checkFileExist(filename)) {
                break;
            } else {
                System.out.println ( "Invalid filename specified. Please specify valid data file: " );
            }
        }
        ArrayList<StudentRecord>  records = StudentRecord.readStudentFile(filename);
        for ( StudentRecord r : records) {
            System.out.println(r);
        }

        while (true) {
            System.out.print("> ");
            String answer = console.nextLine();
            if (answer.trim().isEmpty()) {
                System.out.println("WARNING: Unrecognized command.");
                continue;
            }
            String word = answer.replaceAll(" ", "");
            char firstChar = word.toLowerCase().charAt(0);
            switch (firstChar) {
                case 'o' :
                    int sortIndex = Integer.parseInt(word.substring(1));
                    HeapSort heapSort = new HeapSort(sortIndex);
                    heapSort.sort(records);
                    printRecords(records);
                    break;
                case 'a' :
                    sortIndex = Integer.parseInt(word.substring(1));
                    InsertSort insertSort = new InsertSort();
                    insertSort.setSortColumnIndex(sortIndex);
                    insertSort.sort(records);
                    printRecords(records);
                    break;
                case 'f' :
                    sortIndex = Integer.parseInt(word.substring(1));
                    SelectionSort selectSort = new SelectionSort();
                    selectSort.setSortColumnIndex(sortIndex);
                    selectSort.sort(records);
                    printRecords(records);
                    break;
                case 'q' :
                    System.exit(0);
                    break;
                default :
                    System.out.println("WARNING: Unrecognized command.");
                    break;
            }
        }
    }
}
