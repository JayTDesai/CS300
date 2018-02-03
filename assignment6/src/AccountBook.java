//
// Title:           Account Book.
// Files:           AccountBook.java, RecordNode.java
// Course:          CS 300, Fall 2017
//
// Author:          Jay Desai
// Email:           jdesai2@wisc.edu
// Lecturer's Name: Gary Dahl
//

import java.util.Scanner;

/**
 * AccountBook - represents the account book.
 */
public class AccountBook {
    private RecordNode head;
    private RecordNode tail;
    private double balance;
:qu                                       + "1. Enter 'i' to insert a record into the account book\n"
                                       + "2. Enter 'p' to prepend a record into the account book\n"
                                       + "3. Enter 'a' to append a record into the account book\n"
                                       + "4. Enter 'r' to remove a record from the account book\n"
                                       + "5. Enter 'm' to modify a record in the account book\n"
                                       + "6. Enter 'b' to show the overall balance\n"
                                       + "7. Enter 'd' to display all the records and the overall balance\n"
                                       + "8. Enter 's' to show the records and accumulated balance on a day\n"
                                       + "0. Enter 'q' to quit the program\n"
                                       + "==============================\n"
                                       + "Please enter your command: ";

    /**
     * Default constructor for 'AccountBook'.
     */
    public AccountBook() {
        head = null;
        tail = null;
        balance = 0.0;
    }

    /**
     * Insert a record node into the account book. The money amount can be either
     * negative, meaning the user spent money, or positive, meaning the user
     * received money. If in the account book there are records on the same day, you
     * need to insert the record after the last of them; Otherwise, you need to
     * insert the record between records on earlier days and those on later days.
     *
     * @param day
     *            The day of the record to be inserted.
     * @param amount
     *            The money amount of the record.
     */
    public void insertRecord(int day, double amount) {
        // TODO - add validation checks.
        RecordNode newNode = new RecordNode(day, amount);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            RecordNode tmp = head; // Start of the linked-list.
            RecordNode prev = null; // Previous node pointer.

            while (tmp != null) {
                if (tmp.getDay() <= day) {
                    prev = tmp;
                    tmp = tmp.getNext();
                } else {
                    if (prev == null) {
                        head.setNext(newNode);
                    } else {
                        prev.setNext(newNode);
                    }
                    newNode.setNext(tmp);
                    break;
                }
            }
            // If you are the highest 'day', then you are at the end.
            if (tmp == null) {
                tail.setNext(newNode);
                tail = newNode;
            }
        }
    }

    /**
     * Prepend a record into the account book. The day of the record should be the
     * same as the EARLIEST record in the book. If there haven't been any records in
     * the book yet, you should show user the warning message "WARNING: Unable to
     * prepend a record, for no records in the account book yet." by printing it to
     * the console.
     *
     * @param amount
     *            The money amount of the record to be prepended.
     */
    public void prependRecord(double amount) {
        if (head == null) {
            System.out.println("WARNING: Unable to prepend a record, for no records in the account book yet.");
            return;
        }
        RecordNode node = new RecordNode(head.getDay(), amount);
        node.setNext(head);
        head = node;
    }

    /**
     * Append a record into the account book. Similar as above, the day of the
     * record should be the same as the LATEST record. If there haven't no records
     * in the book yet, you should show user the warning message "WARNING: Unable to
     * append a record, for no records in the account book yet.".
     *
     * @param amount
     *            The money amount of the record to be appended.
     */
    public void appendRecord(double amount) {
        if (tail == null) {
            System.out.println("WARNING: Unable to append a record, for no records in the account book yet.");
            return;
        }
        RecordNode node = new RecordNode(tail.getDay(), amount);
        tail.setNext(node);
        tail = node;
    }

    /**
     * Remove a record from the account book. The two arguments identify which
     * record to remove. E.g., with day being 4 and seq_num being 2, the user
     * would like to delete the second record on the 4th day. If the number of
     * records on day is smaller than seq_num, you show user the warning message
     * "WARNING: Unable to remove a record, for not enough records on the day
     * specified.".
     *
     * @param day
     *            The day of the record to be removed.
     * @param seq_num
     *            The sequence number of the record within the day of it.
     */
    public void removeRecord(int day, int seq_num) {
        RecordNode tmp = head;
        int countSoFar = 1;
        RecordNode prev = null;
        boolean found = true;
        while (tmp != null) {
            if (tmp.getDay() < day) {
                prev = tmp;
                tmp = tmp.getNext();
                continue;
            }
            if (tmp.getDay() == day && countSoFar == seq_num) {
                if (prev == null && head.getNext() == null) {
                    head = null;
                } else if (prev == null && head.getNext() != null) {
                    head = head.getNext();
                } else {
                    prev.setNext(tmp.getNext()); // tmp is now gone.
                    if (prev.getNext() == null) { // it is tail node.
                        tail = prev;
                    } 
                }
                break;
            } else if (tmp.getDay() == day) {
                countSoFar++;
                prev = tmp;
                tmp = tmp.getNext();
                continue;
            } else if (tmp.getDay() > day) {
                found = false;
                break;
            }
        }
        if (tmp == null || found == false) {
            System.out.println("WARNING: Unable to remove a record, for not enough records on the day specified.");
        }
    }

    /**
     * Modify a record in the account book. Similar as above, day and seq_num
     * identify which record to modify, while amount indicates the excepted money
     * amount of the record after modification E.g., with the three arguments being
     * 4 2 100 respectively, the user would like to modify the second record on the
     * 4th day, and change the amount to 100. If the number of records on day is
     * smaller than seq_num , you should show user the warning message "WARNING:
     * Unable to modify a record, for not enough records on the day specified.".
     *
     * @param day
     *            The day of the record to be modified.
     * @param seq_num
     *            The sequence number of the record within the day of it.
     * @param amount
     *            The amount of the record after modified.
     */
    public void modifyRecord(int day, int seq_num, double amount) {
        RecordNode tmp = head;
        int countSoFar = 1;
        RecordNode prev = null;
        boolean found = true;
        while (tmp != null) {
            if (tmp.getDay() < day) {
                prev = tmp;
                tmp = tmp.getNext();
                continue;
            }
            if (tmp.getDay() == day && countSoFar == seq_num) {
                tmp.setAmount(amount);
                break;
            } else if (tmp.getDay() == day) {
                countSoFar++;
                prev = tmp;
                tmp = tmp.getNext();
                continue;
            } else if (tmp.getDay() > day) {
                found = false;
                break;
            }
        }
        if (tmp == null || found == false) {
            System.out.println("WARNING: Unable to modify a record, for not enough records on the day specified.");
        }
    }

    /**
     * Show user the overall balance by printing some leading textual prompt
     * followed by the balance to the console, e.g., "Balance: -90.95". The balance
     * should be initialized as 0 at first, and accumulates as the user
     * insert/prepend/append/remove/modify records.
     */
    public void showBalance() {
        balance = 0;
        RecordNode tmp = head;
        while (tmp != null) {
            balance += tmp.getAmount();
            tmp = tmp.getNext();
        }
        System.out.printf("\nBalance: $%.2f\n", balance);
    }

    /**
     * Display all the records so far as well as the overall balance. If there
     * haven't been no records in the book yet, you should display "No records in
     * the book yet." before displaying the account balance.
     */
    public void display() {
        RecordNode tmp = head;
        boolean found = false;
        while (tmp != null) {
            if (found == false) {
                System.out.printf("Day          Amount\n");
                System.out.println("==================");
                found = true;
            }
            System.out.printf("%-2d           $%.2f\n", tmp.getDay(), tmp.getAmount());
            tmp = tmp.getNext();
        }
        if (found == false) {
            System.out.println("No records in this book yet.");
        }
        showBalance();
    }

    /**
     * Show the records and accumulated balance on the day specified. If in the
     * account book there haven't been any records on the day specified yet, you
     * should display "No records on the day yet." before displaying the accumulated
     * balance.
     *
     * @param day
     *            The day of the summary to be shown.
     */
    public void showDaySummary(int day) {
        RecordNode tmp = head;
        float myDayBalance = 0;
        boolean foundDay = false;
        while (tmp != null) {
            if (tmp.getDay() == day) {
                if (foundDay == false) { // first record found. So need to print header.
                    System.out.printf("Day          Amount\n");
                    System.out.println("==================");
                    foundDay = true;
                }
                myDayBalance += tmp.getAmount();
                System.out.printf("%-2d            $%.2f\n", tmp.getDay(), tmp.getAmount());
                tmp = tmp.getNext();
            } else if (tmp.getDay() < day) {
                tmp = tmp.getNext();
            } else if (tmp.getDay() > day) {
                break;
            }
        }
        if (foundDay == false || tmp == null) {
            System.out.println("No records on the day yet.");
        }
        System.out.printf("\nAccumulated Balance: $%.2f\n", myDayBalance);
    }

    /**
     *  Helper method which validates the 'day' specified.
     *  @param dayNum - the day which needs to be validated.
     *  @returns 'true' if the day is valid (between 1 and 31 inclusive), else false.
     */
    private static boolean validateDayNum(int dayNum) {
        if (dayNum <= 0 || dayNum > 31) {
            System.out.println("WARNING: Invalid day number.");
            return false;
        }
        return true;
    }

    /**
     *  Helper method which validates the 'seq_num' specified.
     *  @param seqNum - sequence number needs to be positive.
     *  @returns 'true' if the seqNum is valid (> 0), else false.
     */
    private static boolean validateSeqNum(int seqNum) {
        if (seqNum <= 0) {
            System.out.println("WARNING: Invalid sequence  number.");
            return false;
        }
        return true;
    }


    /**
     *  main() - run time method.
     *  The method will only quit when 'q' is pressed.
     */
    public static void main(String[] args) {
         AccountBook myBook = new AccountBook();
         Scanner console = new Scanner(System.in);
         int dayNum, seqNum;
         double dayAmount;
         String answer;
         while (true) {
             System.out.print(AccountBook.ACCOUNT_USAGE);
             answer = console.nextLine();
             if (answer.trim().isEmpty()) {
                 System.out.println("WARNING: Unrecognized command.");
                 continue;
             }
             String[] words = answer.split(" ");
             char firstChar = words[0].toLowerCase().charAt(0);
             switch (firstChar) {
                 case 'i' :
                     dayNum = Integer.parseInt(words[1]);
                     if (validateDayNum(dayNum)) {
                         dayAmount = Double.parseDouble(words[2]);
                         myBook.insertRecord(dayNum, dayAmount);
                     }
                     break;
                 case 'p' : 
                     dayAmount = Double.parseDouble(words[1]);
                     myBook.prependRecord(dayAmount);
                     break; 
                 case 'a' : // append
                     dayAmount = Double.parseDouble(words[1]);
                     myBook.appendRecord(dayAmount);
                     break;
                 case 'r' : // remove day seq
                     dayNum = Integer.parseInt(words[1]);
                     if (validateDayNum(dayNum)) {
                         seqNum = Integer.parseInt(words[2]);
                         if (validateSeqNum(seqNum)) {
                             myBook.removeRecord(dayNum, seqNum);
                         }
                     }
                     break;
                 case 'm' : // modify day, seq, amount
                     dayNum = Integer.parseInt(words[1]);
                     if (validateDayNum(dayNum)) {
                         seqNum = Integer.parseInt(words[2]);
                         if (validateSeqNum(seqNum)) {
                             dayAmount = Double.parseDouble(words[3]);
                             myBook.modifyRecord(dayNum, seqNum, dayAmount);
                         }
                     }
                     break;
                 case 'b' : // show balance
                     myBook.showBalance();
                     break;
                 case 'd' :  // display
                     myBook.display();
                     break;
                 case 's' : // show summary for day
                     dayNum = Integer.parseInt(words[1]);
                     if (validateDayNum(dayNum)) {
                         myBook.showDaySummary(dayNum);
                     }
                     break;
                 case 'q' : // exit
                     System.exit(0);
                 default :
                     System.out.println("WARNING: Unrecognized command.");
                     break;
             }
         }
    }
}
