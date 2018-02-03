import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class StudentRecord {
    private String name;
    private ArrayList<Integer> scores;

    public StudentRecord(String name, int size) {
        this.name = name;
        scores = new ArrayList<Integer>(size);
    }

    public void addScore(int score) {
        scores.add(score);
    }

    public String getName() {
        return name;
    }

    public ArrayList<Integer> getScore() {
        return scores;
    }

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

    public static boolean smaller(int compareToIndx, ArrayList<StudentRecord> records,
                                     int indx1, int indx2) {
        StudentRecordComparator compareTo = new StudentRecordComparator(compareToIndx);
        return compareTo.compare(records.get(indx1), records.get(indx2)) < 0;
    }


    public static void swap(ArrayList<StudentRecord> records, int i, int j) {
        StudentRecord tmp = records.get(i);
        records.set(i, records.get(j));
        records.set(j, tmp);
    }



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
