package boundary;

import java.util.Scanner;
import adt.*;
import control.*;
import dao.*;
import entity.*;
import java.io.IOException;
import utility.TimeUtility;

/**
 * 
 * @author ENG JIA JEAN
 */

public class TutorialGroupUI {

    private static Scanner scanner = new Scanner(System.in);
    private final static int GROUP_MAX_SIZE = 20;

    public static void main(String[] args) {
        int choice = 0;
        String menu = "\nTutorial Group Management Page\n"
                + "======================================\n"
                + "1. Add student into tutorial group\n"
                + "2. Remove student from tutorial group\n"
                + "3. Change tutorial group\n"
                + "4. Search student\n"
                + "5. Display students\n"
                + "6. Filter tutorial group\n"
                + "7. Report\n"
                + "8. Exit";
        do {
            try {
                checkFileInit();
                System.out.println(menu);
                System.out.println("--------------------------------");
                System.out.print("Enter choice : ");
                choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        addStudUI();
                        break;
                    case 2:
                        removeStudUI();
                        break;
                    case 3:
                        changeStudUI();
                        break;
                    case 4:
                        searchStudUI();
                        break;
                    case 5:
                        displayStudUI();
                        break;
                    case 6:
                        filterTutGrpUI();
                        break;
                    case 7:
                        report();
                        break;
                    case 8:
                        System.out.println("\nThanks for using the System\n");
                        break;
                    default:
                        System.out.println("\nPlease enter number range 1-8!\n");
                        break;
                }
            } catch (Exception ex) {
                System.out.println("\nPlease enter Number!\n");
                scanner.nextLine();
            }
        } while (choice != 8);

        //Return back to Main Page
        ///
        ///
    } //end psvm

    public static void checkFileInit() throws IOException {
        StudTutProgInitializer stpi = new StudTutProgInitializer();
        stpi.historyInit();
        stpi.progInit();
        stpi.studInit();
        stpi.tutInit();
    }

    private static void addStudUI() throws IOException {
        int choice = -1;
        do {
            StudControl studControl = new StudControl();
            TutControl tutControl = new TutControl();
            ListInterface<String> tempTutGrp = new adt.ArrayList<>();
            DoublyLinkedList<Student> stud = studControl.getStudents();
            DoublyLinkedList<TutorialGroup> tutGrp = tutControl.getTutorialGrp();
            String id, name;
            int age;
            double cgpa = -1;
            try {
                System.out.println("");
                int num = 0;
                for (int i = 0; i < tutGrp.size(); i++) {
                    if (tutGrp.get(i + 1).getGroupSize() < GROUP_MAX_SIZE) {
                        num++;
                        System.out.println(num + ". " + tutGrp.get(i + 1).getTutorialGrpId());
                        tempTutGrp.add(tutGrp.get(i + 1).getTutorialGrpId());
                    }
                }
                System.out.println("--------------------------------");
                System.out.print("Please select a group to add students (-1 to quit) > ");
                choice = scanner.nextInt();
                scanner.nextLine();
                System.out.println("");
                if (choice > 0 && choice <= tempTutGrp.size()) {
                    int ok = 4; // 4 - correct
                    String errorMsg = "";
                    do {
                        System.out.println(errorMsg);
                        System.out.print("Student's ID (exp 23WMR00000 ) : ");
                        id = scanner.nextLine();
                        ok = studControl.checkStudId(id);
                        if (ok == 1) {
                            errorMsg = "Id' format invalid!";
                        } else if (ok == 2) {
                            errorMsg = "Id existed!";
                        } else if (ok == 3) {
                            errorMsg = "Id must have a length of 10!";
                        }
                    } while (ok != 4);

                    System.out.print("Student's Name : ");
                    name = scanner.nextLine();
                    System.out.print("Student's Age : ");
                    age = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Student's CGPA : ");
                    cgpa = scanner.nextDouble();
                    scanner.nextLine();
                    while (cgpa < 0.0 || cgpa > 4.0) {
                        System.out.println("CGPA must be in the range of 0.0 and 4.0!");
                        System.out.print("Student's CGPA : ");
                        cgpa = scanner.nextDouble();
                        scanner.nextLine();
                    }
                    System.out.println("");
                    System.out.println("Confirm student's information : ");
                    System.out.println("--------------------------------");
                    System.out.println("ID             : " + id);
                    System.out.println("Name           : " + name);
                    System.out.println("Age            : " + age);
                    System.out.println("CGPA           : " + cgpa);
                    System.out.println("Tutorial Group : " + tempTutGrp.get(choice - 1));
                    System.out.println("--------------------------------");
                    String confirmation = "";
                    do {
                        System.out.print("Confirm to add? (y/n) > ");
                        confirmation = scanner.nextLine();
                    } while (!confirmation.equals("y") && !confirmation.equals("Y") && !confirmation.equals("n") && !confirmation.equals("N"));

                    if (confirmation.equals("y") || confirmation.equals("Y")) {
                        String student = id + "," + name + "," + age + ","
                                + cgpa + "," + tempTutGrp.get(choice - 1) + "\n";
                        tutControl.addGrpSize(tempTutGrp.get(choice - 1));
                        String actionPerformed = TimeUtility.time() + id
                                + " has been added into " + tempTutGrp.get(choice - 1) + "\n";
                        studControl.addStud(student, actionPerformed);
                        System.out.println("Successfully added");
                    } else {
                        System.out.println("Failed to add");
                    }
                    do {
                        System.out.print("Continue to add? (y/n) > ");
                        confirmation = scanner.nextLine();
                    } while (!confirmation.equals("y") && !confirmation.equals("Y") && !confirmation.equals("n") && !confirmation.equals("N"));

                    if (confirmation.equals("n") || confirmation.equals("N")) {
                        break;
                    }
                } else if (choice == -1) {

                } else {
                    System.out.println("Please enter a valid range");
                }
            } catch (Exception ex) {
                System.out.println("");
                System.out.println("Please enter number! \n");
                scanner.nextLine();
                choice = 0;
            }
        } while (choice != -1);
    }

    private static void removeStudUI() throws IOException {
        String id = "";
        int choice = 0;
        scanner.nextLine();
        do {
            StudControl studControl = new StudControl();
            TutControl tutControl = new TutControl();
            DoublyLinkedList<Student> stud = studControl.getStudents();
            DoublyLinkedList<TutorialGroup> tutGrp = tutControl.getTutorialGrp();
            try {
                System.out.println("");
                System.out.print("Enter Student's ID (-1 to quit) > ");
                id = scanner.nextLine();
                System.out.println("");
                try {
                    choice = Integer.parseInt(id);
                } catch (NumberFormatException ex) {
                }
                int num = -1;
                String confirmation = "";
                if (choice != -1) {
                    for (int i = 0; i < stud.size(); i++) {
                        if (stud.get(i + 1).getId().equals(id)) {
                            num = i + 1;
                            break;
                        }
                    }
                    if (num != -1) {
                        System.out.println("Student's information : ");
                        System.out.println("------------------------");
                        System.out.println("ID             : " + stud.get(num).getId());
                        System.out.println("Name           : " + stud.get(num).getName());
                        System.out.println("Age            : " + stud.get(num).getAge());
                        System.out.println("CGPA            : " + stud.get(num).getCgpa());
                        System.out.println("Tutorial Group : " + stud.get(num).getTutGrpId());
                        System.out.println("------------------------");
                        do {
                            System.out.print("Confirm to delete? (y/n) > ");
                            confirmation = scanner.nextLine();
                        } while (!confirmation.equals("y") && !confirmation.equals("Y") && !confirmation.equals("n") && !confirmation.equals("N"));
                        String tutGrpId = stud.get(num).getTutGrpId();
                        if (confirmation.equals("y") || confirmation.equals("Y")) {
                            stud.remove(num);
                            String actionPerformed = TimeUtility.time() + id
                                    + " has been deleted from " + tutGrpId + "\n";
                            studControl.delStud(stud, actionPerformed);
                            tutControl.subtractGrpSize(tutGrpId);
                            System.out.println("Successfully deleted");
                        } else {
                            System.out.println("Failed to delete");
                        }
                    } else {
                        System.out.println("");
                        System.out.println("No Student Record Found!");
                        System.out.println("");
                    }

                    do {
                        System.out.print("Continue to delete? (y/n) > ");
                        confirmation = scanner.nextLine();
                    } while (!confirmation.equals("y") && !confirmation.equals("Y") && !confirmation.equals("n") && !confirmation.equals("N"));

                    if (confirmation.equals("n") || confirmation.equals("N")) {
                        break;
                    }
//                    scanner.nextLine();
                }

            } catch (IOException ex) {
                System.out.println("");
                System.out.println("Failed to read file... \n");
                scanner.nextLine();
                choice = 0;
            }
        } while (choice != -1);

        //Ask user to enter Student ID
        //Then display the Student information
        //Ask user whether wanted to delete?
    }

    private static void changeStudUI() throws IOException {
        String id = "";
        int choice = 0;
        scanner.nextLine();
        do {
            StudControl studControl = new StudControl();
            TutControl tutControl = new TutControl();
            DoublyLinkedList<Student> stud = studControl.getStudents();
            DoublyLinkedList<TutorialGroup> tutGrp = tutControl.getTutorialGrp();
            ListInterface<String> tempTutGrp = new adt.ArrayList<>();
            try {
                System.out.println("");
                System.out.print("Enter Student's ID (-1 to quit) > ");
                id = scanner.nextLine();
                System.out.println("");

                try {
                    choice = Integer.parseInt(id);
                } catch (NumberFormatException ex) {
                }
                int num = -1;
                String confirmation = "";
                if (choice != -1) {
                    for (int i = 0; i < stud.size(); i++) {
                        if (stud.get(i + 1).getId().equals(id)) {
                            num = i + 1;
                            break;
                        }
                    }
                    if (num != -1) {
                        System.out.println("Current student's information : ");
                        System.out.println("--------------------------------");
                        System.out.println("ID             : " + stud.get(num).getId());
                        System.out.println("Name           : " + stud.get(num).getName());
                        System.out.println("Age            : " + stud.get(num).getAge());
                        System.out.println("CGPA           : " + stud.get(num).getCgpa());
                        System.out.println("Tutorial Group : " + stud.get(num).getTutGrpId());
                        System.out.println("--------------------------------");
                        System.out.println("");

                        int tutNum = 0;
                        int choice2 = 0;
                        do {
                            try {
                                tutNum = 0;
                                tempTutGrp.clear();
                                for (int i = 0; i < tutGrp.size(); i++) {
                                    if (tutGrp.get(i + 1).getGroupSize() < GROUP_MAX_SIZE) {
                                        tutNum++;
                                        System.out.println(tutNum + ". " + tutGrp.get(i + 1).getTutorialGrpId());
                                        tempTutGrp.add(tutGrp.get(i + 1).getTutorialGrpId());
                                    }
                                }
                                System.out.println("--------------------------------");
                                System.out.print("Please select a group to add current students > ");
                                choice2 = scanner.nextInt(); // 1 = tempTutGrp.get(choice2 - 1)
                                scanner.nextLine();
                                if (choice2 <= 0 || choice2 > tutNum) {
                                    System.out.println("");
                                    System.out.println("Please enter valid ranges of group provided");
                                    System.out.println("");
                                }
                            } catch (Exception ex) {
                                System.out.println("");
                                System.out.println("Please enter Number!");
                                System.out.println("");
                                scanner.nextLine();
                            }

                        } while (choice2 <= 0 || choice2 > tutNum);

                        System.out.println("");

                        do {
                            System.out.print("Confirm to change to " + tempTutGrp.get(choice2 - 1) + "? (y/n) > ");
                            confirmation = scanner.nextLine();
                        } while (!confirmation.equals("y") && !confirmation.equals("Y") && !confirmation.equals("n") && !confirmation.equals("N"));

                        if (confirmation.equals("y") || confirmation.equals("Y")) {
                            String oldTutGrpId = stud.get(num).getTutGrpId();
                            tutControl.modifyGrp(tutGrp, oldTutGrpId, tempTutGrp.get(choice2 - 1));
                            stud.get(num).setTutGrpId(tempTutGrp.get(choice2 - 1));
                            String student = "";
                            for (int i = 0; i < stud.size(); i++) {
                                student += stud.get(i + 1).getId() + "," + stud.get(i + 1).getName()
                                        + "," + stud.get(i + 1).getAge() + ","
                                        + stud.get(i + 1).getCgpa()
                                        + "," + stud.get(i + 1).getTutGrpId() + "\n";
                            }
                            String actionPerformed = TimeUtility.time() + id
                                    + " has been changed from " + oldTutGrpId + " to "
                                    + tempTutGrp.get(choice2 - 1) + "\n";
                            studControl.changeStud(student, actionPerformed);
                            System.out.println("Successfully changed");
                        } else {
                            System.out.println("Failed to change");
                        }
                    } else {
                        System.out.println("");
                        System.out.println("No Student Record Found!");
                        System.out.println("");
                    }

                    do {
                        System.out.print("Continue to change? (y/n) > ");
                        confirmation = scanner.nextLine();
                    } while (!confirmation.equals("y") && !confirmation.equals("Y") && !confirmation.equals("n") && !confirmation.equals("N"));

                    if (confirmation.equals("n") || confirmation.equals("N")) {
                        break;
                    }
                }

            } catch (IOException ex) {
                System.out.println("");
                System.out.println("Failed to read file... \n");
                scanner.nextLine();
                choice = 0;
            }
        } while (choice != -1);
    }

    private static void searchStudUI() {
        //use contains
        int choice = -1;
        do {
            ListInterface<Student> stud2 = new adt.ArrayList<>();
            try {
                System.out.println("");
                System.out.println("1. Search by Student's Name");
                System.out.println("2. Search by Student's ID");
                System.out.println("--------------------------------");
                System.out.print("Please select a search method (-1 to quit) > ");
                choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1:
                        searchByName();
                        break;
                    case 2:
                        searchByStudId();
                        break;
                    default:
                        System.out.println("Please enter between valid ranges!");
                        break;
                }
            } catch (Exception ex) {
                System.out.println("");
                System.out.println("Please enter number! \n");
                scanner.nextLine();
                choice = 0;
            }
        } while (choice != -1);
    }

    private static void searchByName() throws IOException {
        System.out.print("Keywords > ");
        String keyword = scanner.nextLine();

        StudControl studControl = new StudControl();

        String actionPerformed = TimeUtility.time() + keyword + " has been searched\n";

        ListInterface<Student> tempStud = studControl.searchStudName(keyword, actionPerformed);
        System.out.println("");
        if (tempStud.isEmpty()) {
            System.out.println("No Record Found!");
            System.out.println("");
        } else {
            System.out.printf("   %-20s %-20s %-10s %-10s %-10s\n", "Student ID", "Student Name", "Age", "CGPA", "Tutorial Group ID");
            System.out.println("-------------------------------------------------------------------------------------");
            for (int i = 0; i < tempStud.size(); i++) {
                System.out.printf((i + 1) + ". %-20s %-20s %-10s %-10.2f %-10s\n",
                        tempStud.get(i).getId(), tempStud.get(i).getName(), tempStud.get(i).getAge(),
                        tempStud.get(i).getCgpa(), tempStud.get(i).getTutGrpId());
            }
            System.out.println("-------------------------------------------------------------------------------------");
            System.out.println("Results Found : " + tempStud.size());
            System.out.println("-------------------------------------------------------------------------------------");
        }
        tempStud.clear();
    }

    private static void searchByStudId() throws IOException {
        System.out.print("Keywords > ");
        String keyword = scanner.nextLine();

        StudControl studControl = new StudControl();

        String actionPerformed = TimeUtility.time() + keyword + " has been searched\n";

        ListInterface<Student> tempStud = studControl.searchStudId(keyword, actionPerformed);
        System.out.println("");
        if (tempStud.isEmpty()) {
            System.out.println("No Record Found!");
            System.out.println("");
        } else {
            System.out.printf("   %-20s %-20s %-10s %-10s %-10s\n", "Student ID", "Student Name", "Age", "CGPA", "Tutorial Group ID");
            System.out.println("-------------------------------------------------------------------------------------");
            for (int i = 0; i < tempStud.size(); i++) {
                System.out.printf((i + 1) + ". %-20s %-20s %-10s %-10.2f %-10s\n",
                        tempStud.get(i).getId(), tempStud.get(i).getName(), tempStud.get(i).getAge(),
                        tempStud.get(i).getCgpa(), tempStud.get(i).getTutGrpId());
            }
            System.out.println("-------------------------------------------------------------------------------------");
            System.out.println("Results Found : " + tempStud.size());
            System.out.println("-------------------------------------------------------------------------------------");
        }
        tempStud.clear();
    }

    private static void displayStudUI() throws IOException {
        int choice = -1;
        do {
            StudControl studControl = new StudControl();
            TutControl tutControl = new TutControl();
            ListInterface<Student> stud2 = new adt.ArrayList<>();
            DoublyLinkedList<Student> stud = studControl.getStudents();
            DoublyLinkedList<TutorialGroup> tutGrp = tutControl.getTutorialGrp();
            try {
                System.out.println("");
                for (int i = 0; i < tutGrp.size(); i++) {
                    System.out.println((i + 1) + ". " + tutGrp.get(i + 1).getTutorialGrpId());
                }
                System.out.println("--------------------------------");
                System.out.print("Please select a group to display students (-1 to quit) > ");
                choice = scanner.nextInt();
                System.out.println("");
                if (choice > 0 && choice <= tutGrp.size()) {
                    for (int i = 0; i < stud.size(); i++) {
                        if (stud.get(i + 1).getTutGrpId().equals(tutGrp.get(choice).getTutorialGrpId())) {
                            stud2.add(stud.get(i + 1));
                        }
                    }
                    if (stud2.isEmpty()) {
                        System.out.println("No Students Record Found!");
                    } else {
                        System.out.printf("   %-20s %-20s %-10s %-10s\n", "Student ID", "Student Name", "Age", "CGPA");
                        System.out.println("--------------------------------------------------------------");
                        for (int i = 0; i < stud2.size(); i++) {
                            System.out.printf((i + 1) + ". %-20s %-20s %-10s %-10.2f\n",
                                    stud2.get(i).getId(), stud2.get(i).getName(),
                                    stud2.get(i).getAge(), stud2.get(i).getCgpa());
                        }
                        System.out.println("--------------------------------------------------------------");
                        System.out.println("Total Number Of Students in "
                                + tutGrp.get(choice).getTutorialGrpId() + " : " + stud2.size());
                        System.out.println("--------------------------------------------------------------");
                    }
                    String actionPerformed = TimeUtility.time() + tutGrp.get(choice).getTutorialGrpId() + " has been displayed\n";
//                    FileUtility.append("history.txt", actionPerformed);
                    studControl.displayStud(actionPerformed);
                    stud2.clear();
                } else if (choice == -1) {

                } else {
                    System.out.println("Please enter a valid range");
                }

            } catch (Exception ex) {
                System.out.println("");
                System.out.println("Please enter number! \n");
                scanner.nextLine();
                choice = 0;
            }
        } while (choice != -1);
        //Ask Which tutorial Group user wants
        //Then based on the option displayed out

    }

    private static void filterTutGrpUI() {
        int choice = -1;
        do {
            ListInterface<Student> stud2 = new adt.ArrayList<>();
            try {
                System.out.println("");
                System.out.println("1. Filter through Group Size");
                System.out.println("2. Filter through Group's Programme");
                System.out.println("-------------------------------------");
                System.out.print("Please select a filter (-1 to quit) > ");
                choice = scanner.nextInt();
                System.out.println("");

                switch (choice) {
                    case 1:
                        filterByGrpSize();
                        break;
                    case 2:
                        filterByGrpProg();
                        break;
                    case -1:
                        break;
                    default:
                        System.out.println("Please enter a valid range");
                        break;
                }

            } catch (Exception ex) {
                System.out.println("");
                System.out.println("Please enter number! \n");
                scanner.nextLine();
                choice = 0;
            }
        } while (choice != -1);
    }

    private static void filterByGrpSize() throws IOException {
        StudControl studControl = new StudControl();
        TutControl tutControl = new TutControl();
        ListInterface<TutorialGroup> grpOk = new adt.ArrayList<>();
        ListInterface<TutorialGroup> grpXok = new adt.ArrayList<>();
        DoublyLinkedList<Student> stud = studControl.getStudents();
        DoublyLinkedList<TutorialGroup> tutGrp = tutControl.getTutorialGrp();
        if (!tutGrp.isEmpty()) {
            for (int i = 0; i < tutGrp.size(); i++) {
                if (tutGrp.get(i + 1).getGroupSize() < 20) {
                    grpOk.add(tutGrp.get(i + 1));
                } else {
                    grpXok.add(tutGrp.get(i + 1));
                }
            }
            System.out.println("Available groups that can add students  ");
            System.out.println("======================================");

            if (!grpOk.isEmpty()) {

                System.out.printf("   %-10s %-10s %-10s \n", "TutGrp", "GrpSize", "ProgId");
                System.out.println("--------------------------------------");
                for (int i = 0; i < grpOk.size(); i++) {
                    System.out.printf((i + 1) + ". %-10s %-10d %-10s \n", grpOk.get(i).getTutorialGrpId(),
                            grpOk.get(i).getGroupSize(), grpOk.get(i).getProgrammeID());
                }
                System.out.println("--------------------------------------");
            } else {
                System.out.println("No Group meet the criteria");
            }
            System.out.println("");
            System.out.println("Groups that cannot add students  ");
            System.out.println("======================================");
            if (!grpXok.isEmpty()) {
                System.out.printf("   %-10s %-10s %-10s\n", "TutGrp", "GrpSize", "ProgId");
                System.out.println("--------------------------------------");
                for (int i = 0; i < grpXok.size(); i++) {
                    System.out.printf((i + 1) + ". %-10s %-10d %-10s\n", grpXok.get(i).getTutorialGrpId(),
                            grpXok.get(i).getGroupSize(), grpXok.get(i).getProgrammeID());
                }
                System.out.println("--------------------------------------");
            } else {
                System.out.println("No Group meet the criteria");
            }
            System.out.println("");
        } else {
            System.out.println("");
            System.out.println("No Group Found!");
            System.out.println("");
        }
        try {
            String actionPerformed = TimeUtility.time() + "Filter Group using Group Size\n";
//            FileUtility.append("history.txt", actionPerformed);
            studControl.filterGrp(actionPerformed);
        } catch (IOException ex) {

        }
    }

    private static void filterByGrpProg() throws IOException {
        StudControl studControl = new StudControl();
        TutControl tutControl = new TutControl();
        ProgControl progControl = new ProgControl();
        DoublyLinkedList<Student> stud = studControl.getStudents();
        DoublyLinkedList<TutorialGroup> tutGrp = tutControl.getTutorialGrp();
        DoublyLinkedList<Programme> prog = progControl.getProg();
        ListInterface<TutorialGroup> tempGrp = new adt.ArrayList<>();
        for (int i = 0; i < prog.size(); i++) {
            for (int j = 0; j < tutGrp.size(); j++) {
                if (prog.get(i + 1).getProgrammeID().equals(tutGrp.get(j + 1).getProgrammeID())) {
                    tempGrp.add(tutGrp.get(j + 1));
                }
            }
            if (!tempGrp.isEmpty()) {
                System.out.println("");
                System.out.println("\t " + prog.get(i + 1).getProgrammeID());

                System.out.println("======================");
                System.out.printf("   %-10s %-10s \n", "TutGrp", "GrpSize");
                System.out.println("----------------------");
                for (int k = 0; k < tempGrp.size(); k++) {
                    System.out.printf((k + 1) + ". %-10s %-10d \n", tempGrp.get(k).getTutorialGrpId(),
                            tempGrp.get(k).getGroupSize());
                }
                System.out.println("----------------------");
                tempGrp.clear();
            }
        }
        try {
            String actionPerformed = TimeUtility.time() + "Filter Group using Programme\n";
            studControl.filterGrp(actionPerformed);
        } catch (IOException ex) {

        }
    }

    private static void report() {
        int choice = -1;
        do {
            ListInterface<Student> stud2 = new adt.ArrayList<>();
            try {
                System.out.println("");
                System.out.println("1. Track Activities History Report");
                System.out.println("2. Average CGPA of Group");
                System.out.println("------------------------------------");
                System.out.print("Please select a report to view (-1 to quit) > ");
                choice = scanner.nextInt();
                System.out.println("");

                switch (choice) {
                    case 1:
                        trackActivities();
                        break;
                    case 2:
                        avgCgpaGrpReport();
                        break;
                    case -1:
                        break;
                    default:
                        System.out.println("Please enter a valid range");
                        break;
                }

            } catch (Exception ex) {
                System.out.println("");
                System.out.println("Please enter number! \n");
                scanner.nextLine();
                choice = 0;
            }
        } while (choice != -1);
    }

    private static void trackActivities() throws IOException {
        StudControl studControl = new StudControl();
        ArrayList<String[]> history = studControl.generateHistory();
        System.out.println("");
        System.out.println("\t\t\t\t    Activities History Report");
        System.out.println("\t\t================================================================================");
        System.out.printf("\t\t  %-15s %-15s %-20s\n", "Date", "Time", "Action Performed");
        System.out.println("\t\t--------------------------------------------------------------------------------");
        for (int i = 0; i < history.size(); i++) {
            System.out.printf("\t\t%-15s %-15s %-20s \n", history.get(i)[0], history.get(i)[1], history.get(i)[2]);
        }
        System.out.println("\t\t--------------------------------------------------------------------------------");
        System.out.println("");
    }

    private static void avgCgpaGrpReport() throws IOException {
        StudControl studControl = new StudControl();
        TutControl tutControl = new TutControl();
        DoublyLinkedList<Student> stud = studControl.getStudents();
        DoublyLinkedList<TutorialGroup> tutGrp = tutControl.getTutorialGrp();
        System.out.println("");
        System.out.println("\t\t\t ==================================");
        System.out.println("\t\t\t| Report of Average CGPA of Group  |");
        System.out.println("\t\t\t|==================================|");
        System.out.printf("\t\t\t| %-10s %-10s %-10s |\n", "GroupID", "GroupSize", "AvgCGPA");
        System.out.println("\t\t\t|----------------------------------|");
        for (int i = 0; i < tutGrp.size(); i++) {
            double cgpa = 0.0;
            int num = 0;
            for (int j = 0; j < stud.size(); j++) {
                if (tutGrp.get(i + 1).getTutorialGrpId().equals(stud.get(j + 1).getTutGrpId())) {
                    cgpa += stud.get(j + 1).getCgpa();
                    num++;
                }
            }
            double avg = cgpa / num;
            System.out.printf("\t\t\t|  %-10s   %-5d   %-10.2f |\n", tutGrp.get(i + 1).getTutorialGrpId(),
                    tutGrp.get(i + 1).getGroupSize(), avg);
        }
        System.out.println("\t\t\t ==================================");
        System.out.println("");
    }
} //end TutorialGroupUI Class
